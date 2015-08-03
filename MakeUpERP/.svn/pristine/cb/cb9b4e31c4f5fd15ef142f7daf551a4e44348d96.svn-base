package com.ga.erp.biz.settlement.suppliersettlement;

import java.text.ParseException;
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
import com.ga.erp.biz.settlement.costorder.CostOrderBiz;
import com.ga.erp.biz.settlement.suppliersettlement.costordersettlement.CostOrderSettlementBiz;

public class SuppSettlementBiz extends ACLBiz{

  public SuppSettlementBiz(UserACL userACL) {
    super(userACL);
  }

//  public PageResult queryStockList(QueryPageData queryPageData,List<DbField> dbFieldList, Long supplierId) {
//    String sql = "SELECT po.*,o.TRUENAME,s.SUPPLIER_NAME,ppo.PURCHASE_ORDER_NUM as P_PURCHASE_ORDER_NUM ,d.DELIVERY_NAME,st.NAME,sm.CODE " +
//        "FROM purchase_order po JOIN SUPPLIER s ON po.SUPPLIER_ID =s.SUPPLIER_ID " +
//        " LEFT  JOIN purchase_order ppo ON po.P_PURCHASE_ORDER_ID=ppo.PURCHASE_ORDER_ID " +
//        " JOIN delivery_org d ON d.DELIVERY_ORG_ID= po.DELIVERY_ORG_ID " +
//        " JOIN stock st ON po.STOCK_ID=st.STOCK_ID " +
//        " left JOIN settlement sm ON sm.SETTLEMENT_ID=po.SETTLEMENT_ID"+
//        " JOIN OP o ON o.OP_ID=po.OP_ID";
//    //往来业务单据中的state为4表示发货已完成
//    String whereSql = "s.DELETE_STATE = 1 AND po.STATE = 4 AND po.SETTLE_STATE = 0 AND po.DELETE_STATE = 1";
//    if(!GaUtil.isNullStr(supplierId + "")) {
//      whereSql += " AND po.SUPPLIER_ID = " + supplierId;
//    }
////    if(!GaUtil.isNullStr(type)){
////      whereSql += " AND po.TYPE="+type;
////    }
//    return BizUtil.queryListBySQL(sql, whereSql, " po.PURCHASE_ORDER_ID desc", dbFieldList, queryPageData, this.userACL);
//  }
//  
  public void save(String ids, String objType, String note) {
  
    String sql = "SELECT * FROM PURCHASE_ORDER WHERE PURCHASE_ORDER_ID IN(" +ids+ ")";
    List<Map<String, Object>> list = DbUtils.queryMapList(DbUtils.getConnection(), sql);
    try{
    check(list);
      String queryTimeSql = "SELECT  MIN(po.CREATE_TIME) BEGIN_TIME, MAX(po.CREATE_TIME) END_TIME, SUM(po.REALITY_PRICE) MONEY, po.SUPPLIER_ID FROM PURCHASE_ORDER po WHERE po.PURCHASE_ORDER_ID   IN (" + ids +
      		") AND (po.IS_DIFF = 1 AND po.DIFF_IS_SETTLED = 1  OR  po.IS_DIFF=0)";
      Map<String, Object> timeMap = DbUtils.queryMap(DbUtils.getConnection(), queryTimeSql);
      Map<String, Object> valuesMap = new HashMap<String, Object>();
      Map<String, String> funcMap = new HashMap<String, String>();
      valuesMap.put("OBJECT_ID", timeMap.get("SUPPLIER_ID") + "");
      valuesMap.put("OBJECT_TYPE", objType);
      valuesMap.put("CODE", 123123);//TODO 结算流水号
      valuesMap.put("START_TIME", timeMap.get("BEGIN_TIME") + "");
      valuesMap.put("END_TIME", timeMap.get("END_TIME") + "");
      valuesMap.put("RECEIVE_MONEY", timeMap.get("MONEY") + "");
      valuesMap.put("PAY_MONEY", timeMap.get("MONEY") + "");
      valuesMap.put("SHOULD_MONEY", timeMap.get("MONEY") + "");
      valuesMap.put("STATE", 1);
      valuesMap.put("NOTE", note);
      funcMap.put("PAY_TIME", "NOW()");
      DbUtils.begin();
      DbUtils.add("SETTLEMENT", valuesMap, funcMap);
      Long settlementId = GaUtil.getLastId();
      DbUtils.update("UPDATE PURCHASE_ORDER PO SET SETTLEMENT_ID = " + settlementId + ",SETTLE_STATE = 2  WHERE PO.PURCHASE_ORDER_ID IN (" + ids + ")");
      funcMap.remove("PAY_TIME");
      funcMap.put("CREATE_TIME", "NOW()");
      for(Map<String, Object> m : list) {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put("SETTLEMENT_ID", settlementId);
        valueMap.put("RECEIVE_MONEY", m.get("REALITY_PRICE") + "");
        valueMap.put("PAY_MONEY", m.get("REALITY_PRICE") + "");
        valueMap.put("TYPE", "1");
        valueMap.put("OBJECT_TYPE", 1);
        valueMap.put("OBJECT_ID", m.get("PURCHASE_ORDER_ID") + "");
        DbUtils.add("SETTLEMENT_LOG", valueMap, funcMap);
      }
      DbUtils.commit();
    }catch(Exception e){
      e.printStackTrace();
      DbUtils.rollback();
    }
  }
  
  private void check(List<Map<String, Object>> listData) {
    if(0 == listData.size()){
      throw new BizException("结算对象为空！");
    }
  }
  
  public void autoSettlement() throws BizException, ParseException{
    
    //供应商大客户按帐期自动结算
    String supplierSql = "SELECT  s.*, IFNULL(s.LAST_END_TIME,s.CREATE_TIME)   BEGIN_TIME, date_sub(IFNULL(s.LAST_END_TIME,s.CREATE_TIME),interval -s.CHECK_CYCLE day) END_TIME  FROM SUPPLIER s " +
    		"where s.DELETE_STATE = 1 AND s.STATE = 1 AND s.CHECK_TYPE = 2  AND DATEDIFF( NOW(), IFNULL(s.LAST_END_TIME,s.CREATE_TIME) ) >= s.CHECK_CYCLE";
    List<Map<String, Object>> list = DbUtils.queryMapList(DbUtils.getConnection(), supplierSql);
    for(Map<String, Object> map : list) {
      String queryWhereSql = " AND po.CREATE_TIME BETWEEN '" +map.get("BEGIN_TIME")+ "' AND '" + map.get("END_TIME") + "'";
      String sql = "UPDATE SUPPLIER SET LAST_END_TIME = '"+ map.get("END_TIME") +"' WHERE SUPPLIER_ID = "+ map.get("SUPPLIER_ID");
      try{
        DbUtils.begin();
        DbUtils.update(sql);
        autoSave(map, queryWhereSql, "[供应商按帐期(每"+ map.get("CHECK_CYCLE") +"天)自动结算]");
        DbUtils.commit();
      }catch (Exception e) {
        DbUtils.rollback();
      }
    }
    
    //供应商大客户按日期自动结算
    String costomSql = "SELECT s.*, date_sub(now(),interval 1 month) BEGIN_TIME, now() END_TIME   FROM SUPPLIER s where s.DELETE_STATE = 1 AND s.STATE = 1 AND s.CHECK_TYPE = 3  AND DAY(CURDATE())  = s.CHECK_MONTH";
    List<Map<String, Object>> costomList = DbUtils.queryMapList(DbUtils.getConnection(), costomSql);
    for(Map<String, Object> map : costomList) {
      String queryWhereSql = " AND po.CREATE_TIME BETWEEN '" +map.get("BEGIN_TIME")+ "' AND '" + map.get("END_TIME") + "'";
      try{
        DbUtils.begin();
        autoSave(map, queryWhereSql, "[供应商按日期（每月" + map.get("CHECK_MONTH") + "号）自动结算]");
        DbUtils.commit();
      }catch (Exception e) {
        DbUtils.rollback();
      }
    }
  }
  
  private List<Map<String, Object>> queryIds(Long supplierId, String settledate) {
    String sql = "SELECT po.PURCHASE_ORDER_ID FROM PURCHASE_ORDER po WHERE " +
    		"po.STATE = 4 AND po.SETTLE_STATE = 0 AND po.SUPPLIER_ID = ? " + settledate;
        return DbUtils.queryMapList(DbUtils.getConnection(), sql, supplierId);
  }
  
  private void autoSave(Map<String,Object> map, String queryDate, String note) {
    List<Map<String,Object>> list = queryIds(Long.parseLong(map.get("SUPPLIER_ID") +""), queryDate);
    String ids = "";
    for(Map<String,Object> objMap : list) {
      ids += objMap.get("PURCHASE_ORDER_ID") + ",";
    }
    if(!GaUtil.isNullStr(ids)) {
      save(ids.substring(0, ids.length() - 1), map.get("TYPE") + "", note);
    } 
  }
  
  public List<Map<String,Object>> querySettList(QueryPageData pageData,List<DbField> dbFieldList, String objType, String id) {
    
    //sql 中TYPE =1 表示供应商业务单
    String poSql = "SELECT  po.PURCHASE_ORDER_ID ORDER_ID , po.PURCHASE_ORDER_NUM ORDER_NUM , " +
    		" po.REALITY_PRICE MONEY, po.REALITY_PRICE  PAY_MONEY ," +objType+ " AS  OBJECT_TYPE,1 AS ORDER_TYPE, 1 AS TYPE, CREATE_TIME" +
    		" FROM PURCHASE_ORDER po  WHERE po.SUPPLIER_ID = " +id+ " AND po.STATE = 1 AND po.SETTLE_STATE = 0 AND po.DELETE_STATE = 1";
    CostOrderBiz biz = new CostOrderBiz(this.userACL);
    List<Map<String,Object>> poList = DbUtils.queryMapList(dbFieldList, poSql);
    List<Map<String,Object>> coList = biz.queryCostOrderSettList(dbFieldList, objType, id);
    coList.addAll(poList);
    return coList;
  }
  
  public PageResult querySettSupplier(QueryPageData pageData,List<DbField> dbFieldList, String type) {
    
    String sql = "SELECT * FROM (SELECT s.*,  ifnull(SUM(po.REALITY_PRICE) , 0) +  ifnull(SUM(co.MONEY),0)  MONEY  FROM SUPPLIER s " +
    		" LEFT JOIN PURCHASE_ORDER po ON po.SUPPLIER_ID = s.SUPPLIER_ID " +
    		" AND po.SETTLE_STATE = 0 AND po.DELETE_STATE = 1 AND po.STATE = 4" +
    		" LEFT JOIN COST_ORDER co ON co.OBJECT_ID = s.SUPPLIER_ID AND OBJECT_TYPE = " +type +
    		" AND co.DELETE_STATE = 1  AND co.STATE = 1  AND co.SETTLE_STATE <> 2" +
    		" WHERE s.DELETE_STATE = 1  AND s.TYPE = " +type+ " GROUP BY s.SUPPLIER_ID) temp ";
    
    return BizUtil.queryListBySQL(sql, "", " SUPPLIER_ID DESC", dbFieldList, pageData, this.userACL);
  }
  
  public void settle(List<Map<String, Object>> listData) {
    String coIdsStr = "";
    String poIdStr = "";
    String objectType = listData.get(0).get("OBJECT_TYPE").toString();
    String note = "1".equals(objectType) ? "供应商" : "大客户";
    for(Map<String, Object> map : listData) {
      coIdsStr += "3".equals(map.get("ORDER_TYPE") + "") ? map.get("ORDER_ID")+ "," : ""; 
      poIdStr += "1".equals(map.get("ORDER_TYPE") + "") ? map.get("ORDER_ID") + "," : "";
    }
    if(!GaUtil.isNullStr(poIdStr)) {
      save(poIdStr.substring(0,poIdStr.length()-1), objectType, "["+note+"手动结算]");
    }
    if(!GaUtil.isNullStr(coIdsStr)) {
      CostOrderSettlementBiz biz = new CostOrderSettlementBiz(this.userACL);
      biz.settle(coIdsStr.substring(0,coIdsStr.length()-1), objectType);
    }
  }
}