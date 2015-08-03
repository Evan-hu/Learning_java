package com.ga.erp.biz.stock.innageadjust;

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

public class AdjustBiz extends ACLBiz{

	public AdjustBiz(UserACL userACL) {
		super(userACL);
	}

	public PageResult queryStockList(QueryPageData queryPageData,List<DbField> dbFieldList) {
		String sql = "SELECT i.*,o.TRUENAME ,s.NAME FROM INNAGE_ADJUST i JOIN STOCK s ON i.`STOCK_ID`=s.`STOCK_ID`" +
				" JOIN OP o ON o.OP_ID=i.OP_ID";
		return BizUtil.queryListBySQL(sql, "", "i.INNAGE_ADJUST_ID desc",
				dbFieldList, queryPageData, this.userACL);
	}
	
	public Map<String, Object> queryStockDetail(Long id) {
		return DbUtils.queryMap(DbUtils.getConnection(),"SELECT  i.*,o.TRUENAME ,s.NAME FROM INNAGE_ADJUST i JOIN STOCK s ON i.`STOCK_ID`=s.`STOCK_ID`" +
				" JOIN OP o ON o.OP_ID=i.OP_ID where i.INNAGE_ADJUST_ID=?", id);
	}

	public void saveInnageAd(Map<String, Object> valuesMap, boolean isAdd) {

		if (isAdd) {
			checkMap(valuesMap);
			String updateFile = "STOCK_ID,BILLS_ID,BILLS_NUM,WAY," +
					"REASON,OP_ID,CREATE_TIME,STATE,NOTE";
			Map<String, String> funcMap = new HashMap<String, String>();
			funcMap.put("CREATE_TIME", "now()");
			valuesMap.put("OP_ID", this.userACL.getUserID());
			valuesMap.put("STATE", 1);
			DbUtils.add("INNAGE_ADJUST", valuesMap, funcMap, updateFile);
			
		} else {
			String updateFile = "INVENTORY_BATCH_ID,BILL_NUM,STOCK_PRICE,STOCK_AMOUNT," +
					"INVENTORY_CNT,DIFF_CNT,STATE";
			DbUtils.update("INNAGE_ADJUST", valuesMap, null,updateFile,"INNAGE_ADJUST_ID");
		}	
		
	}

	private void checkMap(Map<String, Object> valuesMap) {
		 StringBuffer buffer = new StringBuffer("");
		    int count = 1;
		if (GaUtil.isNullStr((valuesMap.get("STOCK_ID") + ""))) {
			buffer.append("<br>"+count+"，请x选择写仓库名；");
	        count++;
		}
		if (GaUtil.isNullStr((valuesMap.get("BILLS_ID") + ""))) {
			buffer.append("<br>"+count+"，请选择单据；");
	        count++;
		}
		if(buffer.length() > 0){
		       throw new BizException(buffer.toString());
		}

	}

}
