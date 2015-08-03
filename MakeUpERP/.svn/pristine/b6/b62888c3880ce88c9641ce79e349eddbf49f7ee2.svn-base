package com.ga.erp.biz.statistic.store;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.management.MXBean;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.sql.rowset.CachedRowSet;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.exception.BizException;
import com.ga.click.util.GaUtil;
import com.ga.click.util.charts.Charts;
import com.ga.click.util.charts.ChartsContainer;

/**
 * 订单统计
 * 
 * @author 
 * 
 */
public class OrderBiz {

  public int sourceType = 0;// 0:订单量 1:订单金额 2:平均订单金额
  public int timeType = 0; // 0:按日 1:按周 2:按月
  public int timeScope = 0; // 0:今天 1:昨天 2:最近7天 3:上周 4:上月 5:最近30天 6:时间对比
  public int isCompare = 0; // 0:不对比1:对比

  public String beginTime1 = null;// 对比时间段前一个开始时间
  public String endTime1 = null;// 对比时间段前一个结束时间

  public String beginTime2 = null;
  public String endTime2 = null;
  
  Calendar ca = Calendar.getInstance();

  public ChartsContainer container = null;
  public List<ChartsContainer> containers = null;
  public List<Charts> charses = null;
  public List<Map<String, Object>> mapList = null;
  public Map<String, Object> allMap = null;

  public Long maxPrice = 0L;
  public Long minPrice = 0L;
  
  private String storeIDs="";
  private Integer ratio;

  /**
   * 设置订单概况相关参数
   */
  public void setOrderFacts(int sourceType, int timeType, String beginTime1,
      String endTime1, String beginTime2, String endTime2, int timeScope,
      int isCompare, Long maxPrice, Long minprice ,String storeIDs,Integer ratio) {
    this.sourceType = sourceType;
    this.timeType = timeType;
    this.beginTime1 = !"".equals(beginTime1) ? beginTime1 : beginTime1;
    this.beginTime2 = !"".equals(beginTime2) ? beginTime2 : beginTime2;
    
    this.endTime1 = !"".equals(endTime1) ? endTime1 : endTime1;
    this.endTime2 = !"".equals(endTime2) ? endTime2 : endTime2;
    this.timeScope = timeScope;
  
    this.isCompare = isCompare;
    this.maxPrice = maxPrice/100;
    this.minPrice = minprice/100;
    this.storeIDs=storeIDs;
    this.ratio=ratio;
  }

  /**
   * 查询订单概况
   * 
   * @return ChartsContainer 图表类
   */
  public ChartsContainer queryOrder() {
    container = new ChartsContainer("", "orderFacts", "0",
        ChartsContainer.TYPE_LINE);// 新建一个线状图
    try {
   
      String[] unitTip = { "单", "元", "元" };
      String[] titleTip = { "订单量", "订单金额", "订单平均金额" };

      //折扣查询
      double dratio=ratio==0?1:ratio/100.0;
      
      String sql="select * FROM (SELECT st.days ,SUM(s.TOTAL_MONEY*"+dratio+"/100) TOTAL_MONEY,COUNT(s.STORE_SALES_ORDER_ID) TOTOAL_ORDER," +
          "SUM(s.TOTAL_MONEY*"+dratio+"/100)/COUNT(s.STORE_SALES_ORDER_ID) AVG_ORDER_MONEY " +
          "FROM store_sales_order s " ;
   // 0:按日 1:按周 2:按月
      String typeSql;
      switch (timeType) {
        case 0 :
          typeSql="DATE_FORMAT(CREATE_TIME ,'%Y-%m-%d') ";
          break;
        case 1 :
          typeSql="DATE_FORMAT(CREATE_TIME,'%Y-%m-%a')";
          break;
        case 2 :
          typeSql="DATE_FORMAT(CREATE_TIME,'%Y-%m')";
          break;  
          
        default :
          typeSql="DATE_FORMAT(CREATE_TIME,'%Y-%m-%d')";
          break;
      }
        sql+=  "JOIN (SELECT "+typeSql+" days,STORE_SALES_ORDER_ID " ;
      String whereSql;
      String timeFieldCode="CREATE_TIME";
      String[] source={"TOTOAL_ORDER","TOTAL_MONEY","AVG_ORDER_MONEY"};
      
   // 0:今天 1:昨天 2:最近7天 3:上周 4:上月 5:最近30天 6:时间对比
      whereSql = timeFieldCode+" BETWEEN '"+this.beginTime1+"' AND '"+this.endTime1+"'";
      
      whereSql+=GaUtil.isNullStr(storeIDs)?"":" and STORE_ID in("+storeIDs+") ";
      sql+=  "FROM store_sales_order  where "+whereSql+") st " +
          "ON s.STORE_SALES_ORDER_ID=st.STORE_SALES_ORDER_ID " +
          "GROUP BY st.days ) so ";
    //如果具有价格限制
      if(minPrice!=0L|| maxPrice!=0L){
        if(maxPrice==0L){
          sql+=" where so.TOTAL_MONEY > "+minPrice ;
        }else if(minPrice==0L){
          sql+=" where so.TOTAL_MONEY < "+maxPrice ;
          }else if((minPrice<maxPrice)){
            sql+=" where so.TOTAL_MONEY BETWEEN "+minPrice+" AND "+maxPrice;
            }
    }
      int rowSize = 0;
     CachedRowSet rowSet = DbUtils.query(sql);
       rowSize = 0;
      while (rowSet.next()) {
        ++rowSize;
        container.addXaxisElement(this.beginTime1.substring(0, 10) + "/"
            + this.endTime1.substring(0, 10) + titleTip[sourceType], new Charts(
            rowSet.getString("days"), rowSet.getObject(source[sourceType])));
      }
      
      if (isCompare == 1 && !"".equals(beginTime2) && !"".equals(endTime2)) {//对比参数判断,如有则添加另一条图形显示
        rowSize = 0;
        sql=sql.replaceAll(sql.substring(sql.indexOf("BETWEEN"),sql.indexOf(") st")), " BETWEEN '"+beginTime2+"' AND '"+endTime2+"'");
        CachedRowSet rowSet2 = DbUtils.query(sql);
        while (rowSet2.next()) {
          ++rowSize;
          container.addXaxisElement(this.beginTime2.substring(0, 10) + "/"
              + this.endTime2.substring(0, 10) + titleTip[sourceType], new Charts(
              rowSet2.getString("days"), rowSet2.getObject(source[sourceType])));
        }
      }
      

      container.setXAxisStyle(rowSize < 10, 0);// 小于10不显示
      container.setExtendFormStyle(null, null, titleTip[sourceType]);
      container.setContainerSize(780, 350);
      container.setTitle(titleTip[sourceType], "");
      container.setYAxisStyle("high", "");
      container.setRadix(unitTip[sourceType]);
      return container;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

//  /**
//   * 根据订单状态查询订单销售额
//   * 
//   * @param state
//   *          订单状态
//   * @param beginTime
//   *          开始时间
//   * @param endTime
//   *          结束时间
//   * @return
//   */
//  public Map<String, Object> queryOrderMoney(Long state, String beginTime,
//      String endTime) {
//    String sql = "select COUNT(*) cnt, nvl(sum(o.MONEY/100),0) summoney from "
//        + "ORDER_FORM o where 1=1";
//    if (state != null) {
//      sql += " and o.STATE=" + state;
//    }
//    if (maxPrice != minPrice) {
//      sql += "and MONEY > " + minPrice + " and MONEY < " + maxPrice + " ";
//    }
//    sql += " and o.CREATE_TIME>=to_date('" + beginTime
//        + "', 'yyyy-MM-dd hh24:mi:ss') and o.CREATE_TIME<=to_date('" + endTime
//        + "', 'yyyy-MM-dd hh24:mi:ss')";
//    return DbUtils.queryMap(DbUtils.getConnection(), sql);
//  }
//
//  /**
//   *有效订单数查询
//   * 
//   * 
//   */
//  public Map<String, Object> queryOrderMoney1(String beginTime, String endTime) {
//    String sql = "select COUNT(*) cnt, nvl(sum(o.MONEY/100),0) summoney from "
//        + "ORDER_FORM o where 1=1";
//    sql += " and o.STATE in (-1,1,2,3,4,5)";
//    if (maxPrice != minPrice) {
//      sql += "and MONEY > " + minPrice + " and MONEY < " + maxPrice + " ";
//    }
//    sql += " and o.CREATE_TIME>=to_date('" + beginTime
//        + "', 'yyyy-MM-dd hh24:mi:ss') and o.CREATE_TIME<=to_date('" + endTime
//        + "', 'yyyy-MM-dd hh24:mi:ss')";
//    return DbUtils.queryMap(DbUtils.getConnection(), sql);
//  }
//
//  /**
//   * 根据两个时间字符串计算时间差
//   * 
//   * @param beginTime
//   * @param endTime
//   * @param type
//   *          0:相差天数,1:相差周数, 2:相差月份
//   * @return
//   */
//  public int timeBetween(String beginTime, String endTime, int type) {
//    try {
//      String[] timeFormate = new String[] { "yyyy-MM-dd", "yyyy-MM-dd",
//          "yyyy-MM" };
//      SimpleDateFormat sdfs = new SimpleDateFormat(timeFormate[type]);
//      Date bTime = sdfs.parse(beginTime);
//      Date eTime = sdfs.parse(endTime);
//      Calendar bcal = Calendar.getInstance();
//      Calendar ecal = Calendar.getInstance();
//      bcal.setTime(bTime);
//      ecal.setTime(eTime);
//      if (type == 0) {
//        int days = (int) ((ecal.getTimeInMillis() - bcal.getTimeInMillis()) / (24 * 60 * 60 * 1000));
//        return days <= 0 ? 1 : days + 1;
//      } else if (type == 1) {
//        int weeks = (int) ((ecal.getTimeInMillis() - bcal.getTimeInMillis()) / (24 * 60 * 60 * 1000 * 7));
//        return weeks <= 0 ? 1 : weeks + 1;
//      } else if (type == 2) {
//        int months = 1;
//        if (bcal.get(Calendar.YEAR) != ecal.get(Calendar.YEAR)) {
//          months = (ecal.get(Calendar.YEAR) - bcal.get(Calendar.YEAR)) * 12;
//        }
//        months = months - bcal.get(Calendar.MONTH) + ecal.get(Calendar.MONTH);
//        return months + 1;
//      }
//      return 0;
//    } catch (Exception e) {
//      e.printStackTrace();
//      return 0;
//    }
//  }
//
//  /**
//   * 会员首购订单
//   */
//  public Long firstPurchase() {
//    Long i = 0L;
//    String sql = ""
//        + "select count(*)  from(  "
//        + "select * from "
//        
//        + "order_form o "
//        + "join ( "
//        + "select * from ( "
//        + "select o.member_id,count(*) t from "
//        + "order_form o "
//        + "group by member_id) t where t.t = 1 ) t on  o.member_id = t.member_id "
//        + "where o.create_time > to_date('" + beginTime1
//        + "', 'yyyy-MM-dd hh24:mi:ss') and o.CREATE_TIME <=to_date('"
//        + endTime1 + "', 'yyyy-MM-dd hh24:mi:ss') )";
//    i = DbUtils.queryLong(sql);
//    return i;
//  }

}
