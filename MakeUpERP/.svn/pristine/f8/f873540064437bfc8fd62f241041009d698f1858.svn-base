package com.ga.erp.biz.stock.inventorydiffbill.comm;

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

public class CommBiz extends ACLBiz{


	public CommBiz(UserACL userACL) {
		super(userACL);
	}
	private  String pubSql = "SELECT dbc.* ,c.COMMODITY_NAME,s.NAME,idb.BILL_NUM " +
      "FROM DIFF_BILL_COMM dbc " +
      "JOIN COMMODITY c ON dbc.COMM_ID=c.COMMODITY_ID " +
      "JOIN stock s ON s.STOCK_ID=dbc.STOCK_ID " +
      "JOIN INVENTORY_DIFF_BILL idb ON idb.INVENTORY_DIFF_BILL_ID=dbc.INVENTORY_DIFF_BILL_ID ";
	public PageResult queryDBCommList(QueryPageData queryPageData,List<DbField> dbFieldList ,Long indiffId) {
	  
	 String where= indiffId==null?"1=1":" dbc.INVENTORY_DIFF_BILL_ID = "+indiffId;
		return BizUtil.queryListBySQL(pubSql, where+" and idb.DELETE_STATE <>-1", "dbc.DIFF_BILL_COMM_ID desc",
				dbFieldList, queryPageData, this.userACL);
	}
	public Map<String, Object> queryDBCommDetail(Long id) {
	  
		return DbUtils.queryMap(DbUtils.getConnection(),pubSql+" where dbc.DIFF_BILL_COMM_ID=? and idb.DELETE_STATE <>-1", id);
	}

	public void saveDBComm(Map<String, Object> valuesMap, boolean isAdd) {
		String updateFiled="COMM_ID,STOCK_ID,INVENTORY_DIFF_BILL_ID,STOCK_CNT" +
				",INVENTORY_CNT,DIFF_CNT,PRICE,OLD_PRICE,DIFF_AMOUNT,RESON";
		checkMap(valuesMap);
		if (isAdd) {
			
			DbUtils.add("DIFF_BILL_COMM", valuesMap,null,	updateFiled);
		} else {
			DbUtils.update("DIFF_BILL_COMM", valuesMap, null,updateFiled,"DIFF_BILL_COMM_ID");
		}	
		
	}

	private void checkMap(Map<String, Object> valuesMap) {
		 StringBuffer buffer = new StringBuffer("");
		 int count = 1;
		if (GaUtil.isNullStr((valuesMap.get("COMM_ID") + ""))) {
			buffer.append("<br>"+count+"，请选择商品名；");
	        count++;
		}
		if (GaUtil.isNullStr((valuesMap.get("STOCK_ID") + ""))) {
			buffer.append("<br>"+count+"，请选择仓库；");
	        count++;
		}
		if (GaUtil.isNullStr((valuesMap.get("INVENTORY_DIFF_BILL_ID") + ""))) {
			buffer.append("<br>"+count+"，请选择盘点异常单；");
	        count++;
		}
		if (GaUtil.isNullStr((valuesMap.get("STOCK_CNT") + ""))) {
			buffer.append("<br>"+count+"，请选填写库存；");
	        count++;
		}
		if(buffer.length() > 0){
		       throw new BizException(buffer.toString());
		}
	}

  
}
