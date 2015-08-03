package com.ga.erp.biz.modifyprice.comm;

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

	private String pubSql="select mpc.* ,c.commodity_name from MODIFY_PRICE_COMMODITY mpc join commodity c on c.commodity_id=mpc.commodity_id ";

	public PageResult queryMoPricCommList(QueryPageData queryPageData,List<DbField> dbFieldList, Long MoPriceId) {
 
	  return BizUtil.queryListBySQL(pubSql, "mpc.MODIFY_PRICE_ID = " + MoPriceId, "mpc.MODIFY_PRICE_COMMODITY_ID desc",dbFieldList, queryPageData, this.userACL);
  }
	
	public Map<String, Object> querymdCommDetail(Long id) {
 
		return DbUtils.queryMap(DbUtils.getConnection(),pubSql+" WHERE mpc.MODIFY_PRICE_COMMODITY_ID=?", id);
	}
	
//	public List<Map<String, Object>> queryICommDetail(List<DbField> fieldList, Long storeId) {
//		return DbUtils.queryMapList(fieldList, pubSql + " where i.INVENTORY_BILL_ID = ?", storeId);
//	}


	public void saveIComm(Map<String, Object> valuesMap, boolean isAdd) {

		if (isAdd) {
			checkMap(valuesMap);
			DbUtils.add("MODIFY_PRICE_COMMODITY", valuesMap,null,
					"COMMODITY_ID,OBJECT_ID,MODIFY_PRICE_ID,PURCHASE_PRICE,OLD_PURCHASE_PRICE,SEND_PRIC,OLD_SEND_PRICE," +
					"SELL_PRICE,OLD_SELL_PRICE,TRADE_PRICE,OLD_TRADE_PRICE,MEMBER_PRICE,OLD_TRADE_PRICE");
		} else {
			DbUtils.update("MODIFY_PRICE_COMMODITY", valuesMap, null,
			    "COMMODITY_ID,OBJECT_ID,MODIFY_PRICE_ID,PURCHASE_PRICE,SEND_PRIC," +
          "SELL_PRICE,TRADE_PRICE,MEMBER_PRICE");
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
