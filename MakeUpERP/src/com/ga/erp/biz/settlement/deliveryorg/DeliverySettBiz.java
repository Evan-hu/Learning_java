package com.ga.erp.biz.settlement.deliveryorg;

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

public class DeliverySettBiz extends ACLBiz {

  public DeliverySettBiz(UserACL userACL) {
    super(userACL);
  }
  
//  public List<Map<String, Object>> querySettList(String id, List<DbField> dbFieldList) {
//    String supplierSql = 
//        "SELECT po.CREATE_TIME,po.PURCHASE_ORDER_ID as ORDER_ID,po.PURCHASE_ORDER_NUM as ORDER_NUM, po.DISTRIBUTE_MONEY, po.DISTRIBUTE_NUM,SUPPLIER_NAME as OBJECT_NAME, do.DELIVERY_NAME, 1 as TYPE " +
//    		"FROM PURCHASE_ORDER po JOIN SUPPLIER s ON s.SUPPLIER_ID = po.SUPPLIER_ID  JOIN DELIVERY_ORG do ON do.DELIVERY_ORG_ID = po.DELIVERY_ORG_ID  WHERE po.DELIVERY_ORG_ID = ? AND po.TYPE !=1 AND po.TYPE != 8" +
//    		" AND po.DISTRIBUTE_SETTLEMENT_ID IS NULL";
//    List<Map<String, Object>> suppList = DbUtils.queryMapList(dbFieldList, supplierSql, id);
//    
//    String storeSql = 
//        "SELECT so.CREATE_TIME, so.STORE_ORDER_ID  ORDER_ID, so.STORE_ORDER_NUM ORDER_NUM, so.DISTRIBUTE_MONEY , so.DISTRIBUTE_NUM, s.STORE_NAME OBJECT_NAME, do.DELIVERY_NAME, 2 as TYPE" +
//    		" FROM STORE_ORDER so JOIN STORE s  ON s.STORE_ID = so.STORE_ID JOIN DELIVERY_ORG  do ON do.DELIVERY_ORG_ID = so.DELIVERY_ORG_ID WHERE so.DELIVERY_ORG_ID = ? AND so.TYPE != 1 AND so.TYPE != 4  AND so.TYPE != 7 AND so.STATE != 0 AND so.STATE != 1" +
//    		" AND so.DISTRIBUTE_SETTLEMENT_ID IS NULL";
//    List<Map<String, Object>> storeList = DbUtils.queryMapList(dbFieldList, storeSql, id);
//    storeList.addAll(suppList);
//    return storeList;
//  }
  
  public void save(Map<String, Object> formData, List<Map<String, Object>> listData, String note) {
    check(listData);
    String minTime = listData.get(0).get("CREATE_TIME") + "";
    String maxTime = listData.get(0).get("CREATE_TIME") + "";
    String objType = listData.get(0).get("OBJECT_TYPE") + "";
    String suppIds = "";
    String storeIds = "";
    String coIds = "";
    Long money = 0L;
    for (Map<String, Object> map : listData) {
      minTime = minTime.compareTo(map.get("CREATE_TIME") + "") == 1 ? map.get("CREATE_TIME") + "" : minTime;
      maxTime = maxTime.compareTo(map.get("CREATE_TIME") + "") == -1 ? map.get("CREATE_TIME") + "" : maxTime;
      String idStr = map.get("ORDER_ID") + "";
      if("1".equals(map.get("ORDER_TYPE") + "")) {
        money += Long.parseLong(map.get("MONEY") + "");
        suppIds +=  idStr + ",";
      }else if("2".equals(map.get("ORDER_TYPE") + "")) {
        money += Long.parseLong(map.get("MONEY") + "");
        storeIds += idStr + ",";
      }else {
        coIds += idStr + ",";
      }
    }
    try{
      if(!GaUtil.isNullStr(storeIds) || !GaUtil.isNullStr(suppIds)) {
        Map<String, Object> valuesMap = new HashMap<String, Object>();
        valuesMap.put("OBJECT_ID", formData.get("DELIVERY_ORG_ID") + "");
        valuesMap.put("OBJECT_TYPE", objType);// 4 表示配送机构
        valuesMap.put("CODE", 4444);//TODO 结算流水号
        valuesMap.put("START_TIME",minTime);
        valuesMap.put("END_TIME", maxTime);
        valuesMap.put("RECEIVE_MONEY", money);
        valuesMap.put("PAY_MONEY", money);
        valuesMap.put("SHOULD_MONEY", money);
        valuesMap.put("STATE", 1);
        valuesMap.put("NOTE", note);
        DbUtils.begin();
        DbUtils.add("SETTLEMENT", valuesMap, null);
        Long settlementId = GaUtil.getLastId();
        if(suppIds.length() != 0) {
          DbUtils.update("UPDATE PURCHASE_ORDER PO SET DISTRIBUTE_SETTLEMENT_ID = " + settlementId + " WHERE PO.PURCHASE_ORDER_ID IN (" + suppIds.substring(0, suppIds.length()-1) + ")");
        }
        if(storeIds.length() != 0) {
          DbUtils.update("UPDATE STORE_ORDER SET DISTRIBUTE_SETTLEMENT_ID = " + settlementId + ",SETTLE_STATE = 2  WHERE STORE_ORDER_ID IN (" + storeIds.substring(0, storeIds.length()-1) + ")");
        }
        for(Map<String, Object> m : listData) {
          String id = m.get("ORDER_ID") + "";
          String type =storeIds.contains(id) ? "2" : "1";
          Map<String, Object> valueMap = new HashMap<String, Object>();
          Map<String, String> funcMap = new HashMap<String, String>();
          valueMap.put("SETTLEMENT_ID", settlementId);
          valueMap.put("RECEIVE_MONEY", m.get("DISTRIBUTE_MONEY") + "");
          valueMap.put("PAY_MONEY", m.get("DISTRIBUTE_MONEY") + "");
          valueMap.put("TYPE", "5");//5 表示物流费用
          //这里的OBJECT_TYPE 表示结算单据类型 ，1.供应商往来业务单 2. 门店往来业务单
          valueMap.put("OBJECT_TYPE", type);
          valueMap.put("OBJECT_ID", id);
          funcMap.put("CREATE_TIME", "now()");
          DbUtils.add("SETTLEMENT_LOG", valueMap, funcMap);
        }
      }
      //下面是结算费用单据
      if(!GaUtil.isNullStr(coIds)) {
        CostOrderSettlementBiz biz = new CostOrderSettlementBiz(this.userACL);
        biz.settle(coIds.substring(0,coIds.length()-1), objType);
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
  
  public void autoSettlement() {
    //配送机构按帐期自动结算
    String deliverySql = "SELECT  do.*, IFNULL(do.LAST_END_TIME,do.CREATE_TIME)  BEGIN_TIME, " +
    		"date_sub(IFNULL(do.LAST_END_TIME,do.CREATE_TIME),interval -do.CHECK_CYCLE day) END_TIME " +
    		"FROM DELIVERY_ORG do WHERE do.CHECK_TYPE = 2  AND do.STATE = 1 " +
    		"AND DATEDIFF( NOW(), IFNULL(do.LAST_END_TIME,do.CREATE_TIME) ) >=do.CHECK_CYCLE";
    List<Map<String, Object>> list = DbUtils.queryMapList(DbUtils.getConnection(), deliverySql);
    for(Map<String, Object> map : list) {
      List<Map<String, Object>> listData = queryAutoSettList(map);
      String sql = "UPDATE DELIVERY_ORG SET LAST_END_TIME = '"+ map.get("END_TIME") +"' WHERE DELIVERY_ORG_ID = "+ map.get("DELIVERY_ORG_ID");
      try{
        DbUtils.begin();
        DbUtils.update(sql);
        save(map, listData, "[配送机构按帐期(每"+ map.get("CHECK_CYCLE") +"天)结算]");
        DbUtils.commit();
      }catch (Exception e) {
        DbUtils.rollback();
      }
    }
    
    //配送机构按日期自动结算
    deliverySql = "SELECT do.*,date_sub(now(),interval 1 month) BEGIN_TIME, now() END_TIME  FROM DELIVERY_ORG do WHERE do.STATE = 1 AND do.CHECK_TYPE = 3 AND DAY(CURDATE())  = do.CHECK_MONTH";
    list = DbUtils.queryMapList(DbUtils.getConnection(), deliverySql);
    for(Map<String, Object> map : list) {
      try{
        List<Map<String, Object>> listData = queryAutoSettList(map);
        DbUtils.begin();
        save(map, listData, "[配送机构按日期（每月" + map.get("CHECK_MONTH") + "号）自动结算]");
        DbUtils.commit();
      }catch (Exception e) {
        DbUtils.rollback();
      }
    }
  }
  
  private List<Map<String, Object>> queryAutoSettList(Map<String, Object> map) {
    String supplierSql = "  SELECT po.CREATE_TIME,po.PURCHASE_ORDER_ID ORDER_ID," +
    		" po.DISTRIBUTE_MONEY, po.DISTRIBUTE_NUM, 1 as TYPE  FROM PURCHASE_ORDER po " +
    		" WHERE po.DELIVERY_ORG_ID = ? AND po.TYPE !=1 AND po.TYPE != 8  " +
    		" AND po.DISTRIBUTE_SETTLEMENT_ID IS NULL " +
    		" AND po.CREATE_TIME BETWEEN   '" +map.get("BEGIN_TIME")+ "' AND  '" +map.get("END_TIME")+ "' ";
    List<Map<String, Object>> suppList = DbUtils.queryMapList(DbUtils.getConnection(), supplierSql, map.get("DELIVERY_ORG_ID").toString());
    
    String storeSql = " SELECT so.CREATE_TIME, so.STORE_ORDER_ID  ORDER_ID, so.DISTRIBUTE_MONEY , 2 as TYPE  FROM STORE_ORDER so " +
    		" WHERE so.DELIVERY_ORG_ID =? AND so.TYPE != 1 AND so.TYPE != 4  AND so.TYPE != 7 AND so.STATE = 1 AND" +
    		" so.DISTRIBUTE_SETTLEMENT_ID IS NULL  AND so.CREATE_TIME BETWEEN   '" +map.get("BEGIN_TIME")+ "' AND  '" +map.get("END_TIME")+ "' ";
    List<Map<String, Object>> storeList = DbUtils.queryMapList(DbUtils.getConnection(), storeSql, map.get("DELIVERY_ORG_ID").toString());
    storeList.addAll(suppList);
    return storeList;
  }
  
  public PageResult querySettDeliveryOrg(QueryPageData pageData,List<DbField> dbFieldList) {
    
    String sql = "SELECT * FROM (SELECT do.* ,IFNULL(SUM(po.REALITY_PRICE),0) + IFNULL(SUM(so.ACTURE_MONEY),0) + IFNULL(SUM(co.MONEY),0)  MONEY FROM DELIVERY_ORG do " +
    		"LEFT  JOIN  PURCHASE_ORDER  po ON po.DELIVERY_ORG_ID = do.DELIVERY_ORG_ID " +
    		"AND po.DELETE_STATE = 1 AND po.TYPE !=1 AND po.TYPE != 8 AND po.DISTRIBUTE_SETTLEMENT_ID IS NULL " +
    		"LEFT JOIN STORE_ORDER so ON so.DELIVERY_ORG_ID = do.DELIVERY_ORG_ID " +
    		"AND so.TYPE != 1  AND so.TYPE != 4  AND so.TYPE != 7 AND so.STATE != 0 AND so.STATE != 1 AND so.DISTRIBUTE_SETTLEMENT_ID IS NULL " +
    		"LEFT JOIN COST_ORDER co ON co.OBJECT_ID = do.DELIVERY_ORG_ID AND co.OBJECT_TYPE = 4 " +
    		"AND co.DELETE_STATE = 1 AND co. SETTLE_STATE != 2 AND co.STATE =1 GROUP BY do.DELIVERY_ORG_ID ) TEMP";
    return BizUtil.queryListBySQL(sql, "", " DELIVERY_ORG_ID DESC", dbFieldList, pageData, this.userACL);
  }
  
  public List<Map<String,Object>> querySettList(List<DbField> dbFieldList, String objType, String id) {
    //sql 中TYPE =1 业务单
    String soSql = "SELECT  so.STORE_ORDER_ID ORDER_ID , so.STORE_ORDER_NUM ORDER_NUM," +
    		    "so.DISTRIBUTE_MONEY MONEY, so.DISTRIBUTE_MONEY  PAY_MONEY ,"+objType+"  AS  OBJECT_TYPE,2 AS ORDER_TYPE, 5 AS TYPE, CREATE_TIME " +
    				"FROM STORE_ORDER so  WHERE so.DELIVERY_ORG_ID = " +id+ " AND so.STATE = 1 AND so.DELETE_STATE = 1 AND  so.DISTRIBUTE_SETTLEMENT_ID" +
    				" IS NULL AND so.TYPE != 1 AND so.TYPE != 4  AND so.TYPE != 7";
   
    String poSql = "SELECT  po.PURCHASE_ORDER_ID ORDER_ID, po.PURCHASE_ORDER_NUM ORDER_NUM, po.DISTRIBUTE_MONEY MONEY, po.DISTRIBUTE_MONEY  PAY_MONEY " +
    		    "," +objType+ " AS  OBJECT_TYPE,1 AS ORDER_TYPE, 1 AS TYPE, CREATE_TIME FROM PURCHASE_ORDER po  WHERE po.DELIVERY_ORG_ID =  "+id+" AND po.STATE = 1  " +
    				"AND po.DELETE_STATE = 1 AND po.DISTRIBUTE_SETTLEMENT_ID IS NULL";
    
    CostOrderBiz biz = new CostOrderBiz(this.userACL);
    List<Map<String,Object>> soList = DbUtils.queryMapList(dbFieldList, soSql);
    List<Map<String,Object>> poList = DbUtils.queryMapList(dbFieldList, poSql);
    List<Map<String,Object>> coList = biz.queryCostOrderSettList(dbFieldList, objType, id);
    coList.addAll(soList);
    coList.addAll(poList);
    return coList;
  }
}
