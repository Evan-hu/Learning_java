package com.ga.erp.biz.statistic.store;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.CachedRowSet;
import com.ga.click.util.GaUtil;
import com.ga.click.dbutil.DbUtils;
//import com.noah.ebiz.norder.activity.ActivityUtil;
import com.ga.click.util.charts.Charts;
import com.ga.click.util.charts.ChartsContainer;

public class SaleAnalysisBiz{
  
  private String begTime = null;
  private String endTime = null;
  private String beforeBegTime = null;//上一周期开始时间
  private String beforeEndTime = null;//上一周期结束时间
  private List<Map<String, Object>> list = null;
  
  public SaleAnalysisBiz(){
    
  }
  
  public ChartsContainer queryHouseCnt(String begDateStr, String endDateStr, int daySelectItem, int timeScope, 
      HttpServletRequest request,List<Map<String,Object>> activityList,String storeIDs,Integer ratio) throws SQLException {
    Calendar ca = Calendar.getInstance();
    ChartsContainer container;
    
    boolean ifUseUserTime = false;
    
    String timeFieldCode = "CREATE_TIME";
    
    String sql;
    String whereSql;
    
    String beginStr = "";
    String endStr = "";
    //时间不为空 返回true
    if(!GaUtil.isNullStr(begDateStr) || !GaUtil.isNullStr(endStr) ){
      beginStr = begDateStr != null ? begDateStr : GaUtil.formatDate("yyyy-MM-dd HH:mm:ss", new Date());
      endStr = endDateStr != null ? endDateStr : GaUtil.formatDate("yyyy-MM-dd HH:mm:ss", new Date());
      ifUseUserTime = true;
    }
    
    //初始化时间段选择  
    if( !ifUseUserTime ){//时间为空
      switch( daySelectItem ){
        case 1:
          ca.set(Calendar.HOUR_OF_DAY, 0);
          ca.set(Calendar.MINUTE, 0);
          ca.set(Calendar.SECOND, 0);
          beginStr = GaUtil.getDateStr(ca.getTime());
          
          ca.set(Calendar.HOUR_OF_DAY, 23);
          ca.set(Calendar.MINUTE, 59);
          ca.set(Calendar.SECOND, 59);
          endStr = GaUtil.getDateStr(ca.getTime());
          
          whereSql = timeFieldCode+" BETWEEN '"+beginStr+"' AND '"+endStr+"'";
          break;
        case 2:
          ca.set(Calendar.DATE, ca.get(Calendar.DATE) - 1);
          
          ca.set(Calendar.HOUR_OF_DAY, 0);
          ca.set(Calendar.MINUTE, 0);
          ca.set(Calendar.SECOND, 0);
          beginStr = GaUtil.getDateStr(ca.getTime());
          
          ca.set(Calendar.HOUR_OF_DAY, 23);
          ca.set(Calendar.MINUTE, 59);
          ca.set(Calendar.SECOND, 59);
          endStr = GaUtil.getDateStr(ca.getTime());
          
          whereSql = timeFieldCode+" BETWEEN '"+beginStr+"' AND '"+endStr+"'";
          break;
        case 3:
          ca.set(Calendar.HOUR_OF_DAY, 23);
          ca.set(Calendar.MINUTE, 59);
          ca.set(Calendar.SECOND, 59);
          endStr = GaUtil.getDateStr(ca.getTime());
          
          ca.set(Calendar.DATE, ca.get(Calendar.DATE) - 7);
          ca.set(Calendar.HOUR_OF_DAY, 0);
          ca.set(Calendar.MINUTE, 0);
          ca.set(Calendar.SECOND, 0);
          beginStr = GaUtil.getDateStr(ca.getTime());
          
          
          whereSql = timeFieldCode+" BETWEEN '"+beginStr+"' AND '"+endStr+"'";
          break;
        case 4:
          ca.set(Calendar.HOUR_OF_DAY, 23);
          ca.set(Calendar.MINUTE, 59);
          ca.set(Calendar.SECOND, 59);
          endStr = GaUtil.getDateStr(ca.getTime());
          
          ca.set(Calendar.DATE, ca.get(Calendar.DATE) - 30);
          ca.set(Calendar.HOUR_OF_DAY, 0);
          ca.set(Calendar.MINUTE, 0);
          ca.set(Calendar.SECOND, 0);
          beginStr = GaUtil.getDateStr(ca.getTime());
          
          whereSql = timeFieldCode+" BETWEEN '"+beginStr+"' AND '"+endStr+"'";
          break;
        case 5:
          ca.set(Calendar.WEEK_OF_YEAR, ca.get(Calendar.WEEK_OF_YEAR) - 1);
          ca.set(Calendar.DAY_OF_WEEK, 2);
          ca.set(Calendar.HOUR_OF_DAY, 0);
          ca.set(Calendar.MINUTE, 0);
          ca.set(Calendar.SECOND, 0);
          beginStr = GaUtil.getDateStr(ca.getTime());
          
          ca.set(Calendar.DATE, ca.get(Calendar.DATE) + 6);
          ca.set(Calendar.HOUR_OF_DAY, 23);
          ca.set(Calendar.MINUTE, 59);
          ca.set(Calendar.SECOND, 59);
          endStr = GaUtil.getDateStr(ca.getTime());

          whereSql = timeFieldCode+" BETWEEN '"+beginStr+"' AND '"+endStr+"'";
          break;
        case 6:
          ca.set(Calendar.MONTH, ca.get(Calendar.MONTH) - 1);
          ca.set(Calendar.DAY_OF_MONTH, 1);
          ca.set(Calendar.HOUR_OF_DAY, 0);
          ca.set(Calendar.MINUTE, 0);
          ca.set(Calendar.SECOND, 0);
          beginStr = GaUtil.getDateStr(ca.getTime());
          
          ca.set(Calendar.MONTH, ca.get(Calendar.MONTH) + 1);
          ca.set(Calendar.DATE, ca.get(Calendar.DATE) - 1);
          ca.set(Calendar.HOUR_OF_DAY, 23);
          ca.set(Calendar.MINUTE, 59);
          ca.set(Calendar.SECOND, 59);
          endStr = GaUtil.getDateStr(ca.getTime());
          
          whereSql = timeFieldCode+" BETWEEN '"+beginStr+"' AND '"+endStr+"'";
          break;
        case 7:
          String begin = request.getParameter("beginTime");
          String end = request.getParameter("endTime");
          
          if(begin == null){
            if(end == null){
              whereSql = " 1 = 1 ";  
            } else {
              Date beginDate = GaUtil.getStrDate(end);
              ca.setTime(beginDate);
              ca.set(Calendar.HOUR_OF_DAY, 0);
              ca.set(Calendar.MINUTE, 0);
              ca.set(Calendar.SECOND, 0);
              beginStr = GaUtil.getDateStr(ca.getTime());
            }
          } else {
            if(end == null){
              Date beginDate = GaUtil.getStrDate(begin);
              ca.setTime(beginDate);
              ca.set(Calendar.HOUR_OF_DAY, 0);
              ca.set(Calendar.MINUTE, 0);
              ca.set(Calendar.SECOND, 0);
              beginStr = GaUtil.getDateStr(ca.getTime());
            } else {
              Date beginDate = GaUtil.getStrDate(begin);
              ca.setTime(beginDate);
              ca.set(Calendar.HOUR_OF_DAY, 0);
              ca.set(Calendar.MINUTE, 0);
              ca.set(Calendar.SECOND, 0);
              beginStr = GaUtil.getDateStr(ca.getTime());
              
              Date endDate = GaUtil.getStrDate(begin);
              ca.setTime(endDate);
              ca.set(Calendar.HOUR_OF_DAY, 23);
              ca.set(Calendar.MINUTE, 59);
              ca.set(Calendar.SECOND, 59);
              endStr = GaUtil.getDateStr(ca.getTime());
            }
          }
          whereSql = timeFieldCode+" BETWEEN '"+beginStr+"' AND '"+endStr+"'";
          break;
        default:
          whereSql = " 1 = 1 ";
          break;
      }
    } else {
      whereSql = timeFieldCode+" BETWEEN '"+beginStr+"' AND '"+endStr+"'";
    }
    //折扣查询
    double dratio=ratio==0?1:ratio/100.0;
    //初始化类型SQL
    sql="SELECT s.STORE_NAME,ss.ALL_MONEY,ss.ALL_ORDER FROM " +
    		"(SELECT store_id ,SUM(TOTAL_MONEY*"+dratio+") ALL_MONEY,COUNT(STORE_SALES_ORDER_ID) ALL_ORDER FROM store_sales_order " +
    		"WHERE TYPE=1 AND "+whereSql+" GROUP BY store_id ) ss " +
    		" JOIN STORE s ON s.STORE_ID=ss.STORE_ID";
    
    //权限查询
    sql+=GaUtil.isNullStr(storeIDs)?"":" where s.STORE_ID in("+storeIDs+")";
    
    activityList.addAll(DbUtils.queryMapList(DbUtils.getConnection(), sql));
    container = new ChartsContainer("订单金额", "test",  "0", ChartsContainer.TYPE_PIE);
    BigDecimal allMoney = new BigDecimal(0);
    if(sql != null ){
      CachedRowSet rs = DbUtils.query(sql);
      while(rs.next()){
        allMoney = allMoney.add(new BigDecimal(rs.getString("ALL_MONEY")));
        container.addXaxisElement("订单金额", new Charts(rs.getString("STORE_NAME"),rs.getDouble("ALL_MONEY")/100));
      }  
    }
    container.setContainerSize(1000,500);
    container.setTitle("分店销售额占比", null);
    container.setYAxisStyle("high", "金额");
    container.setXAxisStyle("right", "金额");
    container.setRadix("元");
    
    return container;
  }
}
