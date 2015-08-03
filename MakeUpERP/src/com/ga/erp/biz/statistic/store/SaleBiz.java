package com.ga.erp.biz.statistic.store;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;


import com.ga.click.dbutil.DbUtils;
import com.ga.click.util.GaUtil;
import com.ga.click.util.charts.Charts;
import com.ga.click.util.charts.ChartsContainer;

public class SaleBiz {

  private String beginTimeSelect1;
  private String endTimeSelect1;
  private String sequence;
  private int top;
  private int type;
  private String storeIDs;
  private Integer ratio;

  public SaleBiz(String beginTimeSelect1, String endTimeSelect1,
      String sequence, int top, int commodityType,String storeIDs,Integer ratio) {
    this.beginTimeSelect1 = beginTimeSelect1;
    this.endTimeSelect1 = endTimeSelect1;
    this.sequence = sequence;
    this.top = top - 5;
    this.type = commodityType;
    this.storeIDs=storeIDs;
    this.ratio=ratio;
  }

  /**
   * 热点广告位图
   * 
   * @throws SQLException
   */
  public ChartsContainer queryHotSaleComm()  {

     String[] cntOrMoney={"TOTAL_CNT","TOTAL_MONEY"};
     String[] radix={"个","元"};
     String[] Yaxis={"数量","金额"};
     String[] elment={"销售数量","销售金额"};
     
     //折扣查询
     double dratio=ratio==0?1:ratio/100.0;
     
     String sql="select SUM(ssd.COMM_CNT) TOTAL_CNT, SUM(BUY_PRICE*"+dratio+") TOTAL_MONEY,c.COMMODITY_NAME,sc.STORE_ID FROM store_sales_detail ssd " +
 		  " JOIN store_sales_order sso ON ssd.STORE_SALES_ORDER_ID=sso.STORE_SALES_ORDER_ID " +
      " JOIN STORE_COMM sc ON sc.STORE_COMM_ID=ssd.STORE_COMM_ID"+
 		  " JOIN commodity c ON c.COMMODITY_ID=ssd.commodity_id WHERE sso.TYPE=1 AND sso.CREATE_TIME BETWEEN '"+beginTimeSelect1+"' " +
 		  "AND '"+endTimeSelect1+"'";
    
     //根据管理员权限  查询
     String whereSql=GaUtil.isNullStr(storeIDs)?" ":" and sc.STORE_ID in ("+storeIDs+") "; 
     
     sql+=whereSql;
     sql+=" GROUP BY c.COMMODITY_ID ";
     sql+=" order by "+cntOrMoney[type]+" ";
    //升序或者降序
     sql+=sequence;
    //控制返回数量
   
    sql+=" limit 0,"+top;
    CachedRowSet rowSet = DbUtils.query(sql);
    
    ChartsContainer charts = new ChartsContainer("HotSaleComm", "0",
        ChartsContainer.TYPE_COLUMN);
    try {
      while (rowSet.next()) { 
        sql=rowSet.getString("COMMODITY_NAME");
        sql=elment[type];
        sql=cntOrMoney[type];
        charts.addXaxisElement(elment[type], new Charts(rowSet.getString("COMMODITY_NAME") ,
            rowSet.getObject(cntOrMoney[type])));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    

    charts.setContainerSize(1000, 400);
    charts.setTitle("热销商品", "");
    charts.setYAxisStyle("high", Yaxis[type]);
    charts.setXAxisStyle("right", "商品名");
    charts.setRadix(radix[type]);
    return charts;
  }
}
