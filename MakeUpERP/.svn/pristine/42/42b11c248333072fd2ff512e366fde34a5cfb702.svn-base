package com.ga.erp.biz.stock.inventorybill.comm;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.CachedRowSet;

import com.ga.click.control.DbField;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.UserACL;
import com.ga.click.page.BizUtil;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.ACLBiz;

public class CommBiz extends ACLBiz{


	public CommBiz(UserACL userACL) {
		super(userACL);
	}

	private String pubSql="";
	
	public PageResult queryICommList(QueryPageData queryPageData,List<DbField> dbFieldList, Long billId) {
	  
    int objectype=0;
    //区分盘点类型  门店或者仓库 再确定盘点对象名
   if(billId!=null){
     CachedRowSet  row=DbUtils.query("SELECT ib.TYPE FROM inventory_bill i JOIN inventory_batch ib " +
     		"ON i.INVENTORY_BATCH_ID=ib.INVENTORY_BATCH_ID WHERE i.INVENTORY_BILL_ID=?", billId);
     try {
      row.next();
      objectype=row.getInt("TYPE");
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
   if(objectype==1){
      pubSql="SELECT i.*, iba.INVENTORY_BATCH_NUM, " +
         "c.COMMODITY_NAME, ib.INVENTORY_FLOW_NUM ,s.NAME OBJECT_NAME FROM " +
         "INVENTORY_COMM i JOIN stock s ON i.OBJECT_ID = s.STOCK_ID " +
         "JOIN COMMODITY c ON i.COMMODITY_ID = c.COMMODITY_ID " +
         "JOIN INVENTORY_BILL ib ON ib.INVENTORY_BILL_ID = i.INVENTORY_BILL_ID " +
         "JOIN INVENTORY_BATCH iba on ib.INVENTORY_BATCH_ID = iba.INVENTORY_BATCH_ID";
   }else{
     pubSql="SELECT i.*, iba.INVENTORY_BATCH_NUM, " +
         "c.COMMODITY_NAME, ib.INVENTORY_FLOW_NUM ,s.STORE_NAME OBJECT_NAME FROM " +
         "INVENTORY_COMM i JOIN store s ON i.OBJECT_ID = s.store_id " +
         "JOIN COMMODITY c ON i.COMMODITY_ID = c.COMMODITY_ID " +
         "JOIN INVENTORY_BILL ib ON ib.INVENTORY_BILL_ID = i.INVENTORY_BILL_ID " +
         "JOIN INVENTORY_BATCH iba on ib.INVENTORY_BATCH_ID = iba.INVENTORY_BATCH_ID";
   }
   
   return BizUtil.queryListBySQL(pubSql, "i.INVENTORY_BILL_ID = " + billId+" and ib.DELETE_STATE <>-1 ", "i.INVENTORY_COMM_ID desc",dbFieldList, queryPageData, this.userACL);
   }
   return  new PageResult(); 
    
  }
	
	public Map<String, Object> queryICommDetail(Long id) {
 
  //区分盘点类型  门店或者仓库 再确定盘点对象名
	  int objectype=0;
    CachedRowSet  row=DbUtils.query("SELECT ib.TYPE FROM inventory_bill i JOIN inventory_batch ib " +
        "ON i.INVENTORY_BATCH_ID=ib.INVENTORY_BATCH_ID JOIN inventory_comm ic " +
        "ON ic.INVENTORY_BILL_ID=i.INVENTORY_BILL_ID WHERE ic.INVENTORY_COMM_ID=?", id);
    try {
     row.next();
     objectype=row.getInt("TYPE");
     
   } catch (SQLException e) {
     e.printStackTrace();
   }
  if(objectype==1){
     pubSql="SELECT i.*, iba.INVENTORY_BATCH_NUM, " +
        "c.COMMODITY_NAME, s.NAME OBJECT_NAME,ib.INVENTORY_FLOW_NUM  FROM " +
        "INVENTORY_COMM i JOIN stock s ON i.OBJECT_ID = s.STOCK_ID " +
        "JOIN COMMODITY c ON i.COMMODITY_ID = c.COMMODITY_ID " +
        "JOIN INVENTORY_BILL ib ON ib.INVENTORY_BILL_ID = i.INVENTORY_BILL_ID " +
        "JOIN INVENTORY_BATCH iba on ib.INVENTORY_BATCH_ID = iba.INVENTORY_BATCH_ID";
  }else{
    pubSql="SELECT i.*,iba.INVENTORY_BATCH_NUM, " +
        "c.COMMODITY_NAME,s.STORE_NAME OBJECT_NAME,ib.INVENTORY_FLOW_NUM  FROM " +
        "INVENTORY_COMM i JOIN store s ON i.OBJECT_ID = s.store_id " +
        "JOIN COMMODITY c ON i.COMMODITY_ID = c.COMMODITY_ID " +
        "JOIN INVENTORY_BILL ib ON ib.INVENTORY_BILL_ID = i.INVENTORY_BILL_ID " +
        "JOIN INVENTORY_BATCH iba on ib.INVENTORY_BATCH_ID = iba.INVENTORY_BATCH_ID";
  }
   
		return DbUtils.queryMap(DbUtils.getConnection(),pubSql+" WHERE i.INVENTORY_COMM_ID=? and ib.DELETE_STATE <>-1", id);
	}
	
	public List<Map<String, Object>> queryICommDetail(List<DbField> fieldList, Long storeId) {
		return DbUtils.queryMapList(fieldList, pubSql + " where i.INVENTORY_BILL_ID = ?", storeId);
	}


	public void saveIComm(Map<String, Object> valuesMap, boolean isAdd) {

		if (isAdd) {
			checkMap(valuesMap);
			DbUtils.add("INVENTORY_COMM", valuesMap,null,
					"COMMODITY_ID,STOCK_ID,INVENTORY_BILL_ID,STOCK_INNAGE,INVENTORY_INNAGE");
		} else {
			DbUtils.update("INVENTORY_COMM", valuesMap, null,
					"COMMODITY_ID,STOCK_ID,INVENTORY_BILL_ID,STOCK_INNAGE,INVENTORY_INNAGE","INVENTORY_COMM_ID");
		}	
		
	}

	private void checkMap(Map<String, Object> valuesMap) {
		 StringBuffer buffer = new StringBuffer("");
		    int count = 1;
		if (GaUtil.isNullStr((valuesMap.get("COMMODITY_ID") + ""))) {
			buffer.append("<br>"+count+"，请选择商品名；");
	        count++; 
		}
		if (GaUtil.isNullStr((valuesMap.get("STOCK_ID") + ""))) {
			buffer.append("<br>"+count+"，请选择仓库；");
	        count++;
		}
		if (GaUtil.isNullStr((valuesMap.get("INVENTORY_BILL_ID") + ""))) {
			buffer.append("<br>"+count+"，请选择盘点流水单；");
	        count++;
		}
		if (GaUtil.isNullStr((valuesMap.get("STOCK_INNAGE") + ""))) {
			buffer.append("<br>"+count+"，请选择系统库存；");
	        count++;
		}
		if (GaUtil.isNullStr((valuesMap.get("INVENTORY_INNAGE") + ""))) {
			buffer.append("<br>"+count+"，请填写实际盘点库存；");
	        count++;
		}
		
		if(buffer.length() > 0){
		       throw new BizException(buffer.toString());
		}

	}

}
