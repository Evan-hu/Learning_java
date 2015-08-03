package com.ga.erp.biz.purchase.list;

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

public class PurListBiz extends ACLBiz{

	public PurListBiz(UserACL userACL) {
		super(userACL);
	}

	public PageResult queryPurList(QueryPageData queryPageData, List<DbField> dbFieldList, Long purOrderId) {
	  String where = "po.DELETE_STATE = 1";
	  if(purOrderId != null){
	    where += " AND pl.PURCHASE_ORDER_ID = " + purOrderId;
	  }
		String sql = "SELECT pl.* ,c.COMMODITY_NAME,s.SUPPLIER_NAME,po.PURCHASE_ORDER_NUM " +
				"FROM purchase_list pl " +
				"JOIN purchase_order po ON po.PURCHASE_ORDER_ID=pl.PURCHASE_ORDER_ID " +
				"JOIN commodity c ON pl.COMMODITY_ID=c.COMMODITY_ID " +
				"JOIN supplier_commodity sc ON sc.SUPPLIER_COMMODITY_ID=pl.SUPPLIER_COMMODITY_ID " +
				"JOIN SUPPLIER s ON s.SUPPLIER_ID=pl.SUPPLIER_ID";
		return BizUtil.queryListBySQL(sql, where, "pl.PURCHASE_LIST_ID desc",dbFieldList, queryPageData, this.userACL);
	}
	
	public Map<String, Object> queryPurDetail(Long id) {
		String sql = "SELECT pl.* ,c.COMMODITY_NAME,s.SUPPLIER_NAME ,po.PURCHASE_ORDER_NUM " +
				"FROM purchase_list pl " +
				"JOIN purchase_order po ON po.PURCHASE_ORDER_ID=pl.PURCHASE_ORDER_ID " +
				"JOIN commodity c ON pl.COMMODITY_ID=c.COMMODITY_ID " +
				"JOIN supplier_commodity sc ON sc.SUPPLIER_COMMODITY_ID=pl.SUPPLIER_COMMODITY_ID " +
				"JOIN SUPPLIER s ON s.SUPPLIER_ID=pl.SUPPLIER_ID"+
				"where pl.PURCHASE_LIST_ID=?";
		return DbUtils.queryMap(DbUtils.getConnection(),sql, id);
	}

	public void savePurList(Map<String, Object> valuesMap, boolean isAdd) {
		String updateFiled="PURCHASE_ORDER_ID,COMMODITY_ID,SUPPLIER_COMMODITY_ID,SUPPLIER_ID,PURCHASE_CNT," +
				"PURCHASE_PRICE,SUPPLY_CNT,REALITY_CNT,SEND_CNT,DIFF_NOTE";
		if (isAdd) {
			
			checkMap(valuesMap);
			DbUtils.add("PURCHASE_LIST", valuesMap,null,
					updateFiled);
		} else {
			DbUtils.update("PURCHASE_LIST", valuesMap, null,updateFiled,"PURCHASE_LIST_ID");
		}	
		
	}

	private void checkMap(Map<String, Object> valuesMap) {
		 StringBuffer buffer = new StringBuffer("");
		    int count = 1;
		if (GaUtil.isNullStr((valuesMap.get("PURCHASE_ORDER_ID") + ""))) {
			buffer.append("<br>"+count+"，请选择采购清单；");
	        count++;
		}
		if (GaUtil.isNullStr((valuesMap.get("COMMODITY_ID") + ""))) {
			buffer.append("<br>"+count+"，请选择商品；");
	        count++;
		}
		if (GaUtil.isNullStr((valuesMap.get("SUPPLIER_COMMODITY_ID") + ""))) {
			buffer.append("<br>"+count+"，请选择供应商商品；");
	        count++;
		}
		if (GaUtil.isNullStr((valuesMap.get("SUPPLIER_ID") + ""))) {
			buffer.append("<br>"+count+"，请选择供应商；");
	        count++;
		}
		if(buffer.length() > 0){
		       throw new BizException(buffer.toString());
		}

	}

}
