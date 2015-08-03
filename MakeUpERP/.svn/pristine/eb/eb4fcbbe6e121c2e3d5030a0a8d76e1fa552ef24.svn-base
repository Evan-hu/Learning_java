package com.ga.erp.biz.settlement.suppliersettlement.costordersettlement;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.mvc.UserACL;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.ACLBiz;

public class CostOrderSettlementBiz extends ACLBiz{

  public CostOrderSettlementBiz(UserACL userACL) {
    super(userACL);
  }

  public void settle(String ids, String type) {
  
    String sql = "SELECT * FROM COST_ORDER WHERE COST_ORDER_ID IN(" +ids+ ")";
    List<Map<String, Object>> list = DbUtils.queryMapList(DbUtils.getConnection(), sql);
    try{
    Integer money = 0;
    Timestamp startTime = Timestamp.valueOf(list.get(0).get("CREATE_TIME") + "");  
    Timestamp endTime = startTime;  

      for (Map<String, Object> map : list) {
          Timestamp createTime = Timestamp.valueOf(map.get("CREATE_TIME") + "");
          startTime = startTime.after(createTime) ? createTime : startTime;
          endTime = endTime.before(createTime) ? createTime : endTime;
          money += (Integer)map.get("MONEY");
      }
      
      Map<String, Object> valuesMap = new HashMap<String, Object>();
      Map<String, String> funcFieldMap = new HashMap<String, String>();
      valuesMap.put("OBJECT_ID", list.get(0).get("OBJECT_ID"));
      valuesMap.put("OBJECT_TYPE", type);
      valuesMap.put("CODE", 1);
      valuesMap.put("START_TIME", startTime);
      valuesMap.put("END_TIME", endTime);
      valuesMap.put("RECEIVE_MONEY", money);
      valuesMap.put("PAY_MONEY", money);
      valuesMap.put("SHOULD_MONEY", money);
      valuesMap.put("STATE", 1);
      funcFieldMap.put("PAY_TIME", "NOW()");
      DbUtils.begin();
      DbUtils.add("SETTLEMENT", valuesMap, funcFieldMap);
      Long settlementId = GaUtil.getLastId();
      DbUtils.update("UPDATE COST_ORDER SET SETTLEMENT_ID = " + settlementId + ",SETTLE_STATE = 2  WHERE COST_ORDER_ID IN (" + ids + ")");
      for(Map<String, Object> m : list) {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        Map<String, String> funcMap = new HashMap<String, String>();
        valueMap.put("SETTLEMENT_ID", settlementId);
        valueMap.put("RECEIVE_MONEY", m.get("MONEY") + "");
        valueMap.put("PAY_MONEY", m.get("MONEY") + "");
        valueMap.put("TYPE", "4");
        //这里的OBJECT_TYPE 表示结算单据类型 ， 3 表示费用单
        valueMap.put("OBJECT_TYPE", 3);
        valueMap.put("OBJECT_ID", m.get("COST_ORDER_ID") + "");
        funcMap.put("CREATE_TIME", "now()");
        DbUtils.add("SETTLEMENT_LOG", valueMap, funcMap);
      }
      DbUtils.close();
    }catch(Exception e){
      e.printStackTrace();
      DbUtils.rollback();
    }
  }
}