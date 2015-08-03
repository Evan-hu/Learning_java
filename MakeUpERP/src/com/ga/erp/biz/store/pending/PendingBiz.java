package com.ga.erp.biz.store.pending;

import java.util.List;
import java.util.Map;

import com.ga.click.dbutil.DbUtils;
import com.ga.click.mvc.UserACL;
import com.ga.erp.biz.ACLBiz;

public class PendingBiz extends ACLBiz {

  public PendingBiz(UserACL userACL) {
    super(userACL);
  }
  
  public List<Map<String, Object>> queryCountInfo(){
	  String sql="select 1 type,count(*) cnt from STORE_COMM sc \n" +//门店库存告警
			  " where sc.INNAGE_BALANCE <= sc.INNAGE_LOWER_LIMIT and sc.INNAGE_BALANCE >= 0 "+
	      " UNION\n" +
	      "select 2 type,count(*) cnt from STORE_COMM sc \n" +//门店负库存
        " where sc.INNAGE_BALANCE < 0 "+
        " UNION\n" +
        "select 3 type,count(*) cnt from STOCK_COMM skc \n" +//仓库库存告警
        " where skc.INNAGE <= skc.LOWER_LIMIT and skc.INNAGE >= 0 "+
        " UNION\n" +
        "select 4 type,count(*) cnt from STOCK_COMM skc \n" +//仓库负库存
        " where skc.INNAGE <0 " +
        " UNION\n" +
        "select 5 type,count(*) cnt from STORE_ORDER so \n" +//店间直调申请单
        " where so.TYPE = 1" +
        " UNION\n" +
        "select 6 type,count(*) cnt from STORE_ORDER so \n" +//店间直调收货单
        " where so.TYPE = 5 ";
        
	   return DbUtils.queryMapList(DbUtils.getConnection(), sql);
	  }
  
  public List<Map<String, Object>> queryOrderCountInfo(){
    String sql="select STATE,count(*) cnt from PURCHASE_ORDER group by STATE " +
        " UNION\n" +
        "select 1 type,count(*) cnt from PURCHASE_ORDER \n" +
        " where TO_DAYS(CREATE_TIME) = TO_DAYS(NOW()) ";
     return DbUtils.queryMapList(DbUtils.getConnection(), sql);
    }


  public List<Map<String, Object>> queryDetail(){
    String sql= "select STATE ,count(*) cnt from STORE_RETURN_ORDER group by STATE";
    return DbUtils.queryMapList(DbUtils.getConnection(), sql);

  }  
  
  public List<Map<String, Object>> queryWaitAuditInfo(){
    String sql = "select count(*) cnt  from AUDITING where RESULT_CODE=0 ";
    return DbUtils.queryMapList(DbUtils.getConnection(), sql);
    
    
  }
}   
