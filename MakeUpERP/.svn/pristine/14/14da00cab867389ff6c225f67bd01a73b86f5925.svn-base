package com.ga.erp.biz.system.deliveryorg;

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

public class DeliveryBiz extends ACLBiz {

  public DeliveryBiz(UserACL userACL) {
    super(userACL);
  }

  public PageResult queryDeliveryList(QueryPageData pageData,List<DbField> fieldList) {
	  return BizUtil.queryListBySQL("select * from DELIVERY_ORG", "", "DELIVERY_ORG_ID desc",
			  fieldList, pageData, this.userACL);
  }
  
  public Map<String, Object> queryDeliveryDetail(List<DbField> dbFieldList, Long id) throws BizException {
	  return DbUtils.queryMap(dbFieldList, "select * from DELIVERY_ORG where DELIVERY_ORG_ID = ?", id);
  }
  
  private void checkValue(Map<String, Object> valueMap){
	  StringBuffer buffer = new StringBuffer("");
      int count = 1;
      if(GaUtil.isNullStr(valueMap.get("DELIVERY_NAME") + "")){
        buffer.append("<br>"+count+"，请输入配送机构名；");
        count++;
      }	
      if(GaUtil.isNullStr(valueMap.get("PRICE") + "") || (valueMap.get("PRICE") + "").equals("0")){
        buffer.append("<br>"+count+"，请输入运费；");
        count++;
      }	
      if(buffer.length() > 0){
          throw new BizException(buffer.toString());
      }
  }
  
  public void saveDelivery(Map<String,Object> valueMap, Boolean isAdd) {
    try {     
      checkValue(valueMap);
      String updateField = "DELIVERY_NAME,PRICE,DELIVERY_TIME,DELIVERY_CODE," +
      		"CHECK_TYPE,CHECK_CYCLE,CHECK_MONTH,NOTE";   
      if(isAdd){
        updateField += ",OP_ID,CREATE_TIME,STATE";
        if(GaUtil.isValidStr(valueMap.get("LAST_END_TIME") + "")){
          updateField += ",LAST_END_TIME";
        }
        valueMap.put("STATE", 1);
        valueMap.put("OP_ID", this.userACL.getUserID());
        Map<String, String> funcMap = new HashMap<String, String>();
        funcMap.put("CREATE_TIME", "NOW()");
    	  DbUtils.add("DELIVERY_ORG",valueMap,funcMap,updateField);
      }else{
    	  DbUtils.update("DELIVERY_ORG",valueMap,null,updateField,"DELIVERY_ORG_ID");
      } 
    } catch (BizException e) {
       throw e;
     } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"保存失败",ex);
    }
  }
}
