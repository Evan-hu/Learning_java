package com.ga.erp.biz.settlement.storesettlement;

import java.util.HashMap;
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
import com.ga.erp.biz.settlement.costorder.CostOrderBiz;
import com.ga.erp.biz.settlement.suppliersettlement.costordersettlement.CostOrderSettlementBiz;

public class StoreSettlementBiz extends ACLBiz{

  public StoreSettlementBiz(UserACL userACL) {
    super(userACL);
  }
  
  public void save(String ids, String objType ,String note) {
  
    String sql = "SELECT * FROM STORE_ORDER WHERE STORE_ORDER_ID IN(" +ids+ ")";
    List<Map<String, Object>> list = DbUtils.queryMapList(DbUtils.getConnection(), sql);
    try{
      String timeSql = "SELECT MIN(so.CREATE_TIME) BEGINTIME , MAX(so.CREATE_TIME) ENDTIME , SUM(so.ACTURE_MONEY)  MONEY, so.STORE_ID FROM STORE_ORDER so  WHERE so.STORE_ORDER_ID IN(" +ids+ ")";
      Map<String, Object> map = DbUtils.queryMap(DbUtils.getConnection(), timeSql);
      Map<String, Object> valuesMap = new HashMap<String, Object>();
      Map<String, String> funcMap = new HashMap<String, String>();
      valuesMap.put("OBJECT_ID", map.get("STORE_ID") + "");
      valuesMap.put("OBJECT_TYPE", objType);
      valuesMap.put("CODE", 2);// 需要自动生成结算流水号
      valuesMap.put("START_TIME", map.get("BEGINTIME") + "");
      valuesMap.put("END_TIME", map.get("ENDTIME") + "");
      valuesMap.put("RECEIVE_MONEY", map.get("MONEY") + "");
      valuesMap.put("PAY_MONEY", map.get("MONEY") + "");
      valuesMap.put("SHOULD_MONEY", map.get("MONEY") + "");
      valuesMap.put("STATE", 1);
      valuesMap.put("NOTE", note);
      funcMap.put("PAY_TIME", "NOW()");
      DbUtils.begin();
      DbUtils.add("SETTLEMENT", valuesMap, funcMap);
      Long settlementId = GaUtil.getLastId();
      DbUtils.update("UPDATE STORE_ORDER SET SETTLEMENT_ID = " + settlementId + ",SETTLE_STATE = 2  WHERE STORE_ORDER_ID IN (" + ids + ")");
      funcMap.remove("PAY_TIME");
      funcMap.put("CREATE_TIME", "NOW()");
      for(Map<String, Object> m : list) {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put("SETTLEMENT_ID", settlementId);
        valueMap.put("RECEIVE_MONEY", m.get("ACTURE_MONEY") + "");
        valueMap.put("PAY_MONEY", m.get("ACTURE_MONEY") + "");
        valueMap.put("TYPE", "1");
        valueMap.put("OBJECT_TYPE", objType);
        valueMap.put("OBJECT_ID", m.get("STORE_ORDER_ID") + "");
        DbUtils.add("SETTLEMENT_LOG", valueMap, funcMap);
      }
      DbUtils.close();
    }catch(Exception e){
      e.printStackTrace();
      DbUtils.rollback();
    }
  }
  
  public void autoSettlement() {
    //门店按帐期自动结算
    String sql1 = "SELECT  s.*, IFNULL(s.LAST_END_TIME,s.CREATE_TIME)  BEGIN_TIME, date_sub(IFNULL(s.LAST_END_TIME,s.CREATE_TIME), " +
    		          "interval -s.CHECK_CYCLE day) END_TIME  FROM STORE s   WHERE  s.STATE = 1 AND s.CHECK_TYPE = 2  AND DATEDIFF( NOW(), " +
    		          "IFNULL(s.LAST_END_TIME,s.CREATE_TIME) ) >= s.CHECK_CYCLE";
    List<Map<String, Object>> list = DbUtils.queryMapList(DbUtils.getConnection(), sql1);
    for(Map<String, Object> map : list) {
      String queryWhereSql = " AND so.CREATE_TIME BETWEEN '" +map.get("BEGIN_TIME")+ "' AND '" + map.get("END_TIME") + "'";
      try{
        DbUtils.begin();
        String sql = "UPDATE STORE SET LAST_END_TIME = '"+ map.get("END_TIME") +"' WHERE STORE_ID = "+ map.get("STORE_ID");
        DbUtils.update(sql);
        autoSave(map, queryWhereSql, "[门店按帐期(每"+ map.get("CHECK_CYCLE") +"天)自动结算]");
        DbUtils.commit();
      }catch (Exception e) {
        DbUtils.rollback();
      }
    }
  //门店按日期自动结算
    String sql2 = "SELECT s.*, date_sub(now(),interval 1 month) BEGIN_TIME, now() END_TIME   FROM STORE s  where s.STATE = 1 AND s.CHECK_TYPE = 3  AND DAY(CURDATE())  = s.CHECK_MONTH";
    List<Map<String, Object>> list2 = DbUtils.queryMapList(DbUtils.getConnection(), sql2);
    for(Map<String, Object> map : list2) {
      String queryWhereSql = " AND so.CREATE_TIME BETWEEN '" +map.get("BEGIN_TIME")+ "' AND '" + map.get("END_TIME") + "'";
      autoSave(map, queryWhereSql, "[门店按日期（每月" + map.get("CHECK_MONTH") + "号）自动结算]");
    }
  }
  
  private void autoSave(Map<String, Object> map,String queryWhereSql, String note) {
    String sql = "SELECT STORE_ORDER_ID FROM  STORE_ORDER so JOIN STORE s ON s.STORE_ID = so.STORE_ID  WHERE s.STATE = 1  AND (so.STATE = 3 OR so.STATE = 2) AND so.SETTLE_STATE = 0 AND so.DELETE_STATE = 1 AND s.STORE_ID = ?" + queryWhereSql;
    List<Map<String,Object>> list = DbUtils.queryMapList(DbUtils.getConnection(), sql, map.get("STORE_ID") + "");
    String ids = "";
    for(Map<String,Object> objMap : list) {
      ids += objMap.get("STORE_ORDER_ID") + ",";
    }
    if(!GaUtil.isNullStr(ids)) {
      save(ids.substring(0, ids.length() - 1), "3", note);
    } 
  }
  
  public PageResult querySettStore(QueryPageData queryPageData, List<DbField> dbFieldList) {
    String sql = "SELECT * FROM (SELECT  s.*, ifnull(SUM(so.ACTURE_MONEY) , 0) + ifnull(SUM(co.MONEY),0)  " +
    		"MONEY ,so.STORE_ORDER_ID FROM STORE s LEFT JOIN STORE_ORDER so on so.STORE_ID = s.STORE_ID AND so.STATE=1" +
    		" AND so.SETTLE_STATE = 0 AND so.DELETE_STATE = 1 LEFT JOIN COST_ORDER co ON co.OBJECT_ID = s.STORE_ID AND " +
    		"co.OBJECT_TYPE = 3 AND co.DELETE_STATE = 1  AND co.STATE = 1  AND co.SETTLE_STATE <> 2  GROUP BY s.STORE_ID ) TEMP";
    return BizUtil.queryListBySQL(sql, "", " STORE_ID DESC ", dbFieldList, queryPageData, this.userACL);
  }
  
  public List<Map<String,Object>> querySettList(QueryPageData pageData,List<DbField> dbFieldList, String objType, String id) {
    
    //sql 中TYPE =2 表示业务单
    String poSql = "SELECT  so.STORE_ORDER_ID ORDER_ID , so.STORE_ORDER_NUM ORDER_NUM ," +
    		"so.ACTURE_MONEY MONEY, so.ACTURE_MONEY  PAY_MONEY ,"+objType+"  AS  OBJECT_TYPE,2 AS ORDER_TYPE, 1 AS TYPE, CREATE_TIME " +
    		"FROM STORE_ORDER so  WHERE so.STORE_ID = "+id+" AND so.STATE = 1 AND so.SETTLE_STATE = 0 AND so.DELETE_STATE = 1";
    CostOrderBiz biz = new CostOrderBiz(this.userACL);
    List<Map<String,Object>> soList = DbUtils.queryMapList(dbFieldList, poSql);
    List<Map<String,Object>> coList = biz.queryCostOrderSettList(dbFieldList, objType, id);
    coList.addAll(soList);
    return coList;
  }
  
  public void settle(List<Map<String, Object>> listData) {
    String coIdsStr = "";
    String soIdStr = "";
    String objectType = listData.get(0).get("OBJECT_TYPE").toString();
    for(Map<String, Object> map : listData) {
      coIdsStr += "3".equals(map.get("ORDER_TYPE") + "") ? map.get("ORDER_ID")+ "," : ""; 
      soIdStr += "2".equals(map.get("ORDER_TYPE") + "") ? map.get("ORDER_ID") + "," : "";
    }
    if(!GaUtil.isNullStr(soIdStr)) {
      save(soIdStr.substring(0,soIdStr.length()-1), objectType, "[门店手动结算]");
    }
    if(!GaUtil.isNullStr(coIdsStr)) {
      CostOrderSettlementBiz biz = new CostOrderSettlementBiz(this.userACL);
      biz.settle(coIdsStr.substring(0,coIdsStr.length()-1), objectType);
    }
  }
}