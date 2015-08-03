package com.ga.erp.biz.settlement.costorder;

import java.text.SimpleDateFormat;
import java.util.Date;
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

public class CostOrderBiz extends ACLBiz {

  public CostOrderBiz(UserACL userACL) {
    super(userACL);
  }

  public String publicSql = "select co.*,sc.SYS_CODE_NAME,o.TRUENAME from COST_ORDER co JOIN SYS_CODE sc on sc.SYS_CODE_ID = co.SYS_CODE_ID join OP o on o.OP_ID=co.OP_ID"; 
  
  public PageResult queryCostOrderList(QueryPageData pageData,List<DbField> fieldList,String type,String id) {
    String where = "";
    where += !GaUtil.isNullStr(type) ? " co.OBJECT_TYPE=" + type : "";
    where += (!GaUtil.isNullStr(id)) ? (!GaUtil.isNullStr(where) ? " AND co.SETTLE_STATE = 0 AND  co.OBJECT_ID =" + id : " co.SETTLE_STATE = 0 AND  co.OBJECT_ID =" + id) : ""; 
	  return BizUtil.queryListBySQL(publicSql,where, "co.CREATE_TIME desc", fieldList, pageData, this.userACL);
  }
  
  public Map<String, Object> queryCostOrderDetail(Long id,List<DbField> fieldList) throws BizException {
	  return DbUtils.queryMap(fieldList, publicSql + " where co.COST_ORDER_ID = ?", id);
  }

  public void saveCostOrder(Map<String, Object> valueMap, Boolean isAdd) {
    try {
      System.out.println(valueMap.get("OBJECT_TYPE"));
      String updateField = "SYS_CODE_ID,OBJECT_ID,OBJECT_NAME,BUDGET,PAY_TIME,NOTE";
      DbUtils.begin();
      if (isAdd) {
        checkMap(valueMap);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Long opId = this.userACL.getUserID();
        valueMap.put("CREATE_TIME",df.format(new Date()));
        valueMap.put("OP_ID", opId);
        valueMap.put("STATE", "1");
        valueMap.put("CHECK_STATE", "0");
        valueMap.put("DELETE_STATE", "1");
        DbUtils.add("COST_ORDER",valueMap,null, updateField + ",DELETE_STATE,CHECK_STATE,COST_ORDER_NUM,STATE,MONEY,OP_ID,CREATE_TIME,COST_ORDER_ID,OBJECT_TYPE");
      } else {
        DbUtils.update("COST_ORDER", valueMap, null, updateField, "COST_ORDER_ID");
      }
      DbUtils.commit();
    } catch (BizException e) {
      throw e;
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new BizException(BizException.SYSTEM, "保存失败", ex);
    } finally {
    }
  }

  private void checkMap(Map<String, Object> valuesMap) {
    StringBuffer buffer = new StringBuffer("");
       int count = 1;
   if (GaUtil.isNullStr((valuesMap.get("COST_ORDER_NUM") + ""))) {
     buffer.append("<br>"+count+"，请填写业务单据号；");
         count++;
   }
   if (GaUtil.isNullStr((valuesMap.get("MONEY") + ""))) {
     buffer.append("<br>"+count+"，请填写发生金额；");
         count++;
   }
   if (GaUtil.isNullStr((valuesMap.get("BUDGET") + ""))) {
     buffer.append("<br>"+count+"，请选择收支方式；");
         count++;
   }
   if(buffer.length() > 0){
          throw new BizException(buffer.toString());
   }
 }
  
  public List<Map<String,Object>> queryCostOrderSettList(List<DbField> fieldList,String type, String id) {
    String coSql = "SELECT co.COST_ORDER_ID ORDER_ID , co.COST_ORDER_NUM ORDER_NUM ,MONEY , MONEY PAY_MONEY , "+type+" AS OBJECT_TYPE ,3 AS ORDER_TYPE, 4 AS TYPE, CREATE_TIME FROM COST_ORDER co WHERE co.OBJECT_ID = "+ id +"  AND co.OBJECT_TYPE = " +type+ " AND co.SETTLE_STATE <> 2  AND co.STATE = 1 AND co.DELETE_STATE = 1 ";
    return DbUtils.queryMapList(fieldList, coSql);
  }
}
