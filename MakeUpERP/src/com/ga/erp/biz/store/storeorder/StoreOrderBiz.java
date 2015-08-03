package com.ga.erp.biz.store.storeorder;

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
import com.ga.erp.util.SystemUtil;

public class StoreOrderBiz extends ACLBiz {

  public StoreOrderBiz(UserACL userACL) {
    super(userACL);
  }

  public String publicSql = "select so.*,o.TRUENAME,s.STORE_NAME,se.CODE from STORE_ORDER so JOIN OP o on so.OP_ID = o.OP_ID" +
      " JOIN STORE s on so.STORE_ID = s.STORE_ID LEFT JOIN SETTLEMENT se on so.SETTLEMENT_ID = se.SETTLEMENT_ID"; 
 
  public String publicCommSql = "select soc.*,s.STORE_NAME,c.COMMODITY_NAME from STORE_ORDER_COMM soc JOIN STORE_ORDER so on" +
      " soc.STORE_ORDER_ID = so.STORE_ORDER_ID JOIN COMMODITY c on soc.COMMODITY_ID = c.COMMODITY_ID JOIN STORE s on soc.STORE_ID = s.STORE_ID";
  
  public PageResult queryStoreOrderList(QueryPageData pageData,List<DbField> fieldList,String type) {
    String where = "so.DELETE_STATE = 1";
    if(GaUtil.isValidStr(type)){
      where += " AND so.TYPE=" + type;
    }
    return BizUtil.queryListBySQL(publicSql,where, "so.STORE_ORDER_ID desc", fieldList, pageData, this.userACL);
  }
  
  public Map<String, Object> queryStoreOrderDetail(Long id) throws BizException {
    return DbUtils.queryMap(DbUtils.getConnection(), publicSql + " where so.STORE_ORDER_ID = ?", id);
  }

  public void saveStoreOrder(Map<String,Object> valuesMap, Boolean isAdd) {
    try{
     String updateFieldStr = "STORE_ID,STORE_ORDER_NUM,P_STORE_ORDER_ID," +
        "TYPE,ORDER_MONEY,ACTURE_MONEY,DELIVERY_TYPE,PREDICT_DILIVERY_TIME," +
        "CREATE_TIME,OP_ID,RECEIVE_TIME,STATE,IS_DIFF,IS_DIFF_SETTLED,NOTE"; 
     if(isAdd){
       if(valuesMap.get("STORE_ID") == null){
         throw new BizException("请选择门店");
       }
       if(valuesMap.get("STORE_ORDER_NUM") == null){
         throw new BizException("请填写订单编号");
       }
        valuesMap.put("OP_ID", this.userACL.getUserID());
        valuesMap.put("STATE", 1);
        /**
         * @TODO 结算、差异处理、订单金额、实际订单金额
         */
        valuesMap.put("ORDER_MONEY", 0); //实际应为计算得知
        valuesMap.put("ACTURE_MONEY", 0);
        Map<String, String> funcMap = new HashMap<String, String>();
        funcMap.put("CREATE_TIME", "NOW()");
        DbUtils.add("STORE_ORDER", valuesMap, funcMap, updateFieldStr);
      } else {
        DbUtils.update("STORE_ORDER", valuesMap, null,"NOTE","STORE_ORDER_ID");
      }
     } catch (BizException e) {
       throw e;
     } catch(Exception ex) {
       ex.printStackTrace(); 
       throw new BizException(BizException.SYSTEM,"保存失败",ex);
     }
  }
  
  public PageResult queryOrderCommList(QueryPageData pageData,List<DbField> fieldList, Long id) throws BizException {
    String where = id == null ? "" : "so.STORE_ORDER_ID = " + id;
    return BizUtil.queryListBySQL(publicCommSql, where, "soc.STORE_ORDER_COMM_ID desc", fieldList, pageData, this.userACL);
  }
  
  public Map<String, Object> queryOrderCommDetail(List<DbField> fieldList, Long id) throws BizException {
    return DbUtils.queryMap(fieldList, publicCommSql + " where soc.STORE_ORDER_COMM_ID = ?", id);
  }
  
  public void saveOrderComm(Map<String,Object> valuesMap, Boolean isAdd) {
    try{
      if(isAdd){
        DbUtils.add("STORE_ORDER_COMM", valuesMap, null, "STORE_ORDER_ID,COMMODITY_ID,STORE_COMM_ID,STORE_ID,DISTRIBUTE_PRICE,ORDER_CNT,ACTUAL_CNT,SEND_CNT,STATE,DIFF_REASON,NOTE");
      } else {
        DbUtils.update("STORE_ORDER_COMM", valuesMap, null,"ACTUAL_CNT,DIFF_REASON,NOTE","STORE_ORDER_COMM_ID");
      }
     } catch (BizException e) {
       throw e;
     } catch(Exception ex) {
       ex.printStackTrace(); 
       throw new BizException(BizException.SYSTEM,"保存失败",ex);
     }
  }
  /**
  * 查询门店订单物流记录
  * @param orderFormID 订单ID
  * @param dbFieldList 字段列表
  * @return
  */
  public List<Map<String,Object>> queryOrderDeliveryRecord(Long id,List<DbField> dbFieldList) {
     Map<String,Object> deliveryMap=DbUtils.queryMap(DbUtils.getConnection(), "select D.DELIVERY_NAME,D.DELIVERY_CODE,S.DISTRIBUTE_NUM from DELIVERY_ORG D join STORE_ORDER S on D.DELIVERY_ORG_ID = S.DELIVERY_ORG_ID where S.STORE_ORDER_ID = ?", id);
     return SystemUtil.resolveDeliveryRecord(deliveryMap);
  }
}
