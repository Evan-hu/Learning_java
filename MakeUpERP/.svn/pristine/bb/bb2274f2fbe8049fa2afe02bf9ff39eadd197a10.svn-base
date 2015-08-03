package com.ga.erp.biz.settlement.settlement;

import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.dbutil.PageResult;
import com.ga.click.mvc.UserACL;
import com.ga.click.page.BizUtil;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.ACLBiz;

public class SettlementBiz extends ACLBiz {

  public SettlementBiz(UserACL userACL) {
    super(userACL);
  }

	public PageResult querySettlementList(QueryPageData queryPageData, List<DbField> dbFieldList, String objType, int type) {
		String sql = "SELECT DISTINCT s.*, sl.TYPE  FROM SETTLEMENT s LEFT JOIN SETTLEMENT_LOG sl ON sl.SETTLEMENT_ID = s.SETTLEMENT_ID ";
		
		String whereSql = "";
		whereSql = 1 == type ? whereSql + " s.STATE = 1 " : whereSql; 
		whereSql = (GaUtil.isNullStr(objType)) ? whereSql : (GaUtil.isNullStr(whereSql) ? whereSql + " s.OBJECT_TYPE = " + objType : whereSql + " AND s.OBJECT_TYPE = " + objType);
		return BizUtil.queryListBySQL(sql, whereSql, "s.SETTLEMENT_ID DESC", dbFieldList, queryPageData, this.userACL);
	}
	
	public Map<String, Object> quetySettlementMap(Long id, List<DbField> dbFieldList) {
		String sql = "SELECT s.*, sl.TYPE  FROM SETTLEMENT s JOIN SETTLEMENT_LOG sl ON sl.SETTLEMENT_ID = s.SETTLEMENT_ID  WHERE s.SETTLEMENT_ID = ?";
		return DbUtils.queryMap(dbFieldList, sql, id);
	}
	
	public PageResult quetySettlementLogList(Long id, List<DbField> dbFieldList, QueryPageData queryPageData) {
		String sql = "SELECT * FROM SETTLEMENT_LOG";
		String whereSql = " SETTLEMENT_ID = " + id;
		return BizUtil.queryListBySQL(sql, whereSql, " CREATE_TIME DESC", dbFieldList, queryPageData, this.userACL);
	}
	
	public Map<String, Object> quetySettlementLogMap(Long id, List<DbField> dbFieldList) {
		String sql = "SELECT SL.* NAME FROM SETTLEMENT_LOG SL WHERE SL.SETTLEMENT_LOG_ID = ?";
		return DbUtils.queryMap(dbFieldList, sql, id);
	}
	
	public void payment(Long id) {
	  String sql = "SELECT S.* FROM SETTLEMENT S WHERE S.SETTLEMENT_ID = ?";
	  Map<String, Object> map = DbUtils.queryMap(DbUtils.getConnection(), sql, id);
	  String updateSql = "UPDATE SETTLEMENT SET STATE = 2,REAL_MONEY = ?,PAY_TIME = NOW() WHERE SETTLEMENT_ID = ?"; 
	  DbUtils.update(updateSql, map.get("SHOULD_MONEY") + "", id);
	}
}
