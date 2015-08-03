package com.ga.erp.biz.stock.inventorydiffbill;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.UserACL;
import com.ga.click.page.BizUtil;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.ACLBiz;

public class DiffBillBiz extends ACLBiz{
	public DiffBillBiz(UserACL userACL) {
		super(userACL);
	}
	private String pubSql = "SELECT id.*, ib.INVENTORY_BATCH_NUM , o.TRUENAME FROM INVENTORY_DIFF_BILL id " +
			"JOIN inventory_batch ib ON id.INVENTORY_BATCH_ID = ib.INVENTORY_BATCH_ID " +
			"JOIN OP o ON o.OP_ID = id.OP_ID ";
	public PageResult queryIDiffBillList(QueryPageData queryPageData,List<DbField> dbFieldList,int type) {
		return BizUtil.queryListBySQL(pubSql, "ib.DELETE_STATE = 1 and id.DELETE_STATE = 1 and ib.type = "+type+"", "id.INVENTORY_DIFF_BILL_ID desc",
				dbFieldList, queryPageData, this.userACL);
	}
	public Map<String, Object> queryIDiffBillDetail(Long id) {
		return DbUtils.queryMap(DbUtils.getConnection(),pubSql+" WHERE id.INVENTORY_DIFF_BILL_ID=? and ib.DELETE_STATE <>-1 and id.DELETE_STATE <>-1", id);
	}
	public void saveIDiffBill(Map<String, Object> valuesMap, boolean isAdd) {
		if (isAdd) {
			checkMap(valuesMap);
			String updateFile = "INVENTORY_BATCH_ID,BILL_NUM,STOCK_PRICE,STOCK_AMOUNT," +
					"INVENTORY_CNT,DIFF_CNT,DIFF_AMOUNT,OP_ID,CREATE_TIME,STATE";
			Map<String, String> funcMap = new HashMap<String, String>();
			funcMap.put("CREATE_TIME", "now()");
			valuesMap.put("OP_ID", this.userACL.getUserID());
			valuesMap.put("STATE", 1);
			DbUtils.add("INVENTORY_DIFF_BILL", valuesMap, funcMap, updateFile);
		} else {
			String updateFile = "INVENTORY_BATCH_ID,BILL_NUM,DIFF_AMOUNT,STOCK_PRICE,STOCK_AMOUNT," +
					"INVENTORY_CNT,DIFF_CNT,STATE";
			DbUtils.update("INVENTORY_DIFF_BILL", valuesMap, null,updateFile,"INVENTORY_DIFF_BILL_ID");
		}	
	}

	private void checkMap(Map<String, Object> valuesMap) {
		 StringBuffer buffer = new StringBuffer("");
		    int count = 1;
		if (GaUtil.isNullStr((valuesMap.get("INVENTORY_BATCH_ID") + ""))) {
			buffer.append("<br>"+count+"，请选择盘点批次；");
	        count++;
		}
		if (GaUtil.isNullStr((valuesMap.get("BILL_NUM") + ""))) {
			buffer.append("<br>"+count+"，请选择差异流水账号；");
	        count++;
		}
//		if (GaUtil.isNullStr((valuesMap.get("INVENTORY_BILLS_ID") + ""))) {
//			buffer.append("<br>"+count+"，请选择盘点流水单；");
//	        count++;
//		}
//		if (GaUtil.isNullStr((valuesMap.get("STOCK_INNAGE") + ""))) {
//			buffer.append("<br>"+count+"，请选择系统库存；");
//	        count++;
//		}
//		if (GaUtil.isNullStr((valuesMap.get("INVENTORY_INNAGE") + ""))) {
//			buffer.append("<br>"+count+"，请填写实际盘点库存；");
//	        count++;
//		}
		
		if(buffer.length() > 0){
		       throw new BizException(buffer.toString());
		}

	}

}
