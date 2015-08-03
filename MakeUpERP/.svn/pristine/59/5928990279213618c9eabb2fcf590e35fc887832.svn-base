package com.ga.erp.biz.statistic.abc;

import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.ModelMap;
import com.ga.click.mvc.UserACL;
import com.ga.click.page.BizUtil;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.ACLBiz;

public class AbcBiz extends ACLBiz {

  public AbcBiz(UserACL userACL) {
    super(userACL);
  }
  
  public /*PageResult*/ List<Map<String, Object>> queryComm(QueryPageData queryPagedata, List<DbField> dbFieldList) {
    
    
    //手动查询
    List<String> storeN=queryPagedata.GetQueryValues("STORE_NAME");
    String storeName = "";
    if(storeN != null){
      storeName =  storeN.get(0);
    }
    //门店
    String storeSql=GaUtil.isNullStr(storeName) ? "" : " and st.STORE_NAME='"+storeName+"' ";
    
    List<String> time= queryPagedata.GetQueryValues("CREATETIME");
    //时间段查询
    if(time!=null&&time.size()<2){
      throw new BizException("请选择完整的时间段");
    }
   
    String begainTiem=time!=null?time.get(0):"";
    String endTime=time!=null?time.get(1):"";
    if(time!=null&&!GaUtil.compareTimeStr(begainTiem, endTime, "yyyy-MM-dd")){
      throw new BizException("请选择正确的时间段");
    }
    String timeSql=GaUtil.isNullStr(begainTiem)?" where 1 = 1 ":"join STORE_SALES_ORDER sso on sso.STORE_SALES_ORDER_id =s.STORE_SALES_ORDER_id where sso.CREATE_TIME BETWEEN" +
    		" '"+begainTiem+"' and '"+endTime +"' ";
    
   //折扣比
    Integer ratio = this.userACL.getDiscRatio();
    double dratio = ratio==0?1.00:ratio*1.00/100;
    
    //权限设置 
    String storeId = this.userACL.getStoreID();
    String whereSql=GaUtil.isNullStr(storeId)?"":" s.STORE_ID IN("+storeId+") ";
 
    String sql="SELECT *, ROUND(scnt*"+dratio+"/ 100 * 1.1, 2) CNT, ROUND(scnt / total * 100, 2) AS szb, ROUND(leiji / total * 100, 2) AS zb, " +
    		"scnt / total SZHANBI, leiji / total ZHANBI , IF( leiji / total < 0.9, IF( leiji / total < 0.7, 'A', IF(scnt / total > 0.7, 'A', 'B') ), 'C' ) ABC" +
    		" FROM (SELECT store_name, COMMODITY_NAME, scnt, (SELECT SUM(i.scnt) FROM (SELECT SUM(s.COMM_CNT * s.RETAIL_PRICE) scnt " +
    		"FROM store_sales_detail s JOIN store st ON st.store_id = s.store_id " +
    		 timeSql+
         " "+whereSql+" "+storeSql+
    		" GROUP BY s.COMMODITY_ID ORDER BY scnt DESC) i WHERE i.scnt >=o.scnt ) leiji, " +
    		"(SELECT SUM(s.COMM_CNT * s.RETAIL_PRICE) scnt FROM store_sales_detail s JOIN store st ON st.store_id = s.store_id " +
    		 timeSql+
         " "+whereSql+" "+storeSql+
    		") total FROM (SELECT st.store_name, c.COMMODITY_ID, c.COMMODITY_NAME, SUM(s.COMM_CNT * s.RETAIL_PRICE) scnt " +
    		"FROM store_sales_detail s JOIN commodity c ON c.COMMODITY_ID = s.COMMODITY_ID JOIN store st ON st.store_id = s.store_id " +
    		 timeSql+
         " "+whereSql+" "+storeSql+
    		" GROUP BY s.COMMODITY_ID ORDER BY scnt DESC) o ORDER BY scnt DESC) t";
    
//    PageResult result = BizUtil.queryListBySQL(sql, "", "", fields, queryPagedata, null);
    List<Map<String, Object>> result = DbUtils.queryMapList(DbUtils.getConnection(), sql);
    System.out.println(queryPagedata.GetPageParam());
    return result;
//     return  BizUtil.queryListBySQL(sql, "", "", dbFieldList, queryPagedata, this.userACL);
  }
}
