package com.ga.erp.biz.stock.inventorybill;

import java.sql.SQLException;
import java.util.HashMap;
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

public class BillBiz extends ACLBiz {


	public BillBiz(UserACL userACL) {
		super(userACL);
	}

	private String pubSql="SELECT i.*,ib.INVENTORY_BATCH_NUM ,ib.TYPE,ib.TYPE_NAME,ib.SCOPE,o.TRUENAME FROM INVENTORY_BILL i JOIN INVENTORY_BATCH ib " +
			"ON ib.INVENTORY_BATCH_ID=i.INVENTORY_BATCH_ID JOIN OP o ON o.OP_ID=i.OP_ID";
	
	public PageResult queryInventoryBillList(QueryPageData queryPageData,List<DbField> dbFieldList,int type) {
	  String where = "i.DELETE_STATE = 1 and ib.DELETE_STATE = 1 and IB.TYPE = " + type;
	  if(GaUtil.isValidStr(userACL.getStoreID())){
      where += " AND ib.TYPE = 2 AND ib.TYPE_ID in (" + userACL.getStoreID() + ")";
    }
		return BizUtil.queryListBySQL(pubSql, where, "i.INVENTORY_BILL_ID desc",
				dbFieldList, queryPageData, this.userACL);
	}
	
	public Map<String, Object> queryInventoryBillDetail(Long id) {
		return DbUtils.queryMap(DbUtils.getConnection(),pubSql + " where i.INVENTORY_BILL_ID = ? and i.DELETE_STATE = 1 and ib.DELETE_STATE = 1", id);
	}
	
	public List<Map<String, Object>> queryInventoryBillDetail(List<DbField> fieldList, Long id) {
		return DbUtils.queryMapList(fieldList, pubSql + " where i.INVENTORY_BATCH_ID = ?  and i.DELETE_STATE = 1 and ib.DELETE_STATE = 1", id);
	}
	//根据参数查询id
	public Long queryId(String billNum){
	  Long id=null;
	 CachedRowSet rowSet= DbUtils.query("select INVENTORY_BILL_ID from INVENTORY_BILL where  INVENTORY_FLOW_NUM=?", billNum);
	 try {
    rowSet.next();
    id= rowSet.getLong("INVENTORY_BILL_ID");
  } catch (SQLException e) {
    e.printStackTrace();
  }
	  return  id;
	}
	

	public void saveInventoryBill(Map<String, Object> valuesMap, boolean isAdd) {
		String dbFile = "INVENTORY_BATCH_ID,INVENTORY_FLOW_NUM,NOTE,OP_ID,STATE,CREATE_TIME";
		checkMap(valuesMap);
		if (isAdd) {
			Map<String, String> funcMap = new HashMap<String, String>();
			funcMap.put("CREATE_TIME", "now()");
			valuesMap.put("OP_ID", this.userACL.getUserID());
			valuesMap.put("STATE", 1);
			DbUtils.add("INVENTORY_BILL", valuesMap, funcMap, dbFile);
			
		} else {
			DbUtils.update("INVENTORY_BILL", valuesMap, null, "NOTE",
					"INVENTORY_BILL_ID");
		}

	}

	private void checkMap(Map<String, Object> valuesMap) {
		 StringBuffer buffer = new StringBuffer("");
		    int count = 1;
		if (GaUtil.isNullStr((valuesMap.get("INVENTORY_BATCH_ID") + ""))) {
			buffer.append("<br>"+count+"，请选盘点批次；");
	        count++;
		}
		if (GaUtil.isNullStr((valuesMap.get("INVENTORY_FLOW_NUM") + ""))) {
			buffer.append("<br>"+count+"，请填写盘点流水账号；");
	        count++;
		}
		
		
		if(buffer.length() > 0){
		       throw new BizException(buffer.toString());
		}
	}
}
