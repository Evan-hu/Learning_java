package com.ga.erp.biz.statistic.member;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.rowset.CachedRowSet;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.util.GaUtil;
import com.ga.click.util.charts.Charts;
import com.ga.click.util.charts.ChartsContainer;

public class MemGroupBiz {

  private String beginTimeSelect1;
  private String endTimeSelect1;
  private String sequence;
  private int top;
  private int type;
  private int sourceType;
  private String storeIDs;
  List<Map<String,Object>> mapList;
  private int ratio;
  @SuppressWarnings("unchecked")
  public MemGroupBiz(String beginTimeSelect1, String endTimeSelect1,
      String sequence, int top, int commodityType, int sourceType,List mapList,String storeIDs,Integer ratio) {
    this.beginTimeSelect1 = beginTimeSelect1;
    this.endTimeSelect1 = endTimeSelect1;
    this.sequence = sequence;
    this.top = top - 5;
    this.type = commodityType;
    this.sourceType = sourceType ;
    this.mapList=mapList;
    this.storeIDs=storeIDs;
    this.ratio=ratio;
  }

  
  public ChartsContainer queryMemberGroup()  {

     String[] cntOrMoney={"AVG_ORDER","AVG_MONEY"};
     String[] groupType={"SEX","AGE"};
     String[] radix={"单","元"};
     String[] Yaxis={"数量","金额"};
     String[] elment={"客单量","客单价"};
     String[] xAxis={"性别","年龄段"};
     String[] sexStr={"男","女"};
     String[] ageStr={"1-9年龄段","10-19年龄段","20-29年龄段","30-39年龄段","40-49年龄段","50-59年龄段","60-69年龄段","70-79年龄段","80-89年龄段"};
     
     
     //折扣
     double dratio=ratio==0?1:ratio/100.0;
     
     
     String sql="SELECT t1.*,t2.TOTAL_MEMBER,ROUND(t1.total_money/t2.total_member,2) AVG_MONEY,ROUND(t1.total_order/t2.total_member,2) AVG_ORDER " +
     		"FROM (SELECT m."+groupType[sourceType]+",m.STORE_ID,round(SUM(s.TOTAL_MONEY*"+dratio+"/100),2) TOTAL_MONEY,COUNT(s.STORE_SALES_ORDER_ID) TOTAL_ORDER " +
     		"FROM store_sales_order s JOIN (SELECT MEMBER_ID,SEX,STORE_ID,ROUND((DATE_FORMAT(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(birth)), '%Y')+0)/10) AS AGE " +
     		"FROM member ) m ON s.member_id=m.member_id WHERE s.CREATE_TIME BETWEEN '"+beginTimeSelect1+"' AND '20140625' AND s.TYPE=1 GROUP BY m."+groupType[sourceType]+") t1 " +
     		"JOIN(SELECT men."+groupType[sourceType]+", COUNT(men.MEMBER_ID) AS total_member " +
     		"FROM (SELECT MEMBER_ID,SEX,ROUND((DATE_FORMAT(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(birth)), '%Y')+0)/10) AS AGE " +
     		"FROM member WHERE member_id IN (SELECT DISTINCT m2.member_id FROM store_sales_order s2 JOIN member m2 " +
     		"ON s2.member_id=m2.member_id WHERE s2.CREATE_TIME BETWEEN '"+beginTimeSelect1+"' AND '"+endTimeSelect1+"' AND s2.TYPE=1 ) )men GROUP BY men."+groupType[sourceType]+" )t2 " +
     		"ON t1."+groupType[sourceType]+"=t2."+groupType[sourceType]+" ";
     
     //根据管理员权限  查询
    String whereSql=GaUtil.isNullStr(storeIDs)?"where 1=1 ":"where STORE_ID  in ("+storeIDs+") "; 
    sql+=whereSql;
    sql+=" order by "+cntOrMoney[type]+" ";
    //升序或者降序
    sql+=sequence;
    //控制返回数量
   
    sql+=" limit 0,"+top;
    CachedRowSet rowSet = DbUtils.query(sql);
    
    ChartsContainer charts = new ChartsContainer("MemGroupComm", "0",
        ChartsContainer.TYPE_COLUMN);
    try {
      if(sourceType==0){ 
      while (rowSet.next()) { 
        charts.addXaxisElement(elment[type], new Charts(sexStr[rowSet.getInt(groupType[sourceType])] ,
            rowSet.getObject(cntOrMoney[type])));
        Map<String,Object> map=new HashMap<String, Object>();
          map.put("source", sexStr[rowSet.getInt(groupType[sourceType])]);
          map.put("TOTAL_MONEY", rowSet.getObject("TOTAL_MONEY"));
          map.put("TOTAL_MEMBER", rowSet.getObject("TOTAL_MEMBER"));
          map.put("TOTAL_ORDER", rowSet.getObject("TOTAL_ORDER"));
          map.put("AVG_MONEY", rowSet.getObject("AVG_MONEY"));
          map.put("AVG_ORDER", rowSet.getObject("AVG_ORDER"));
          mapList.add(map);
          
        }
      }else{
        
        while (rowSet.next()) {
          
          charts.addXaxisElement(elment[type], new Charts(ageStr[(int) Double.parseDouble(rowSet.getString(groupType[sourceType]))] ,
              rowSet.getObject(cntOrMoney[type])));
          Map<String,Object> map=new HashMap<String, Object>();
          map.put("source", ageStr[(int) Double.parseDouble(rowSet.getString(groupType[sourceType]))]);
          map.put("TOTAL_MONEY", rowSet.getObject("TOTAL_MONEY"));
          map.put("TOTAL_MEMBER", rowSet.getObject("TOTAL_MEMBER"));
          map.put("TOTAL_ORDER", rowSet.getObject("TOTAL_ORDER"));
          map.put("AVG_MONEY", rowSet.getObject("AVG_MONEY"));
          map.put("AVG_ORDER", rowSet.getObject("AVG_ORDER"));
          mapList.add(map);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    charts.setContainerSize(1000, 400);
    charts.setTitle("会员消费群体分析", "");
    charts.setYAxisStyle("high", Yaxis[type]);
    charts.setXAxisStyle("right", xAxis[sourceType]);
    charts.setRadix(radix[type]);
    return charts;
  }
}
