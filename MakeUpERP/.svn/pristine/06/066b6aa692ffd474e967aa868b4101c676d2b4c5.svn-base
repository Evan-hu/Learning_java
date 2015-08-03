package com.ga.erp.biz.store;

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
import com.ga.erp.biz.ACLBiz;

public class StoreBiz extends ACLBiz {

  public StoreBiz(UserACL userACL) {
    super(userACL);
  }

  public String publicSql = "select s.*,a.AREA_NAME,o.TRUENAME, case WHEN ACTIVE_CODE is null " +
  		                      "then 0 else 1 end IS_ACTIVE from STORE s JOIN AREA a " +
  		                      "on s.AREA_ID = a.AREA_ID JOIN OP o on s.OP_ID = o.OP_ID";
  
  public PageResult queryStoreList(QueryPageData pageData, Map<String, Object> valueMap, List<DbField> fieldList, Long commId) {
    String where = " 1 = 1";
    if(commId != null){
      publicSql += " join STORE_COMM sc on s.STORE_ID = sc.STORE_ID";
      where += " AND sc.COMMODITY_ID = " + commId;
    }    
    if(valueMap != null && valueMap.get("IS_ACTIVE") != null){
      String ext = (valueMap.get("IS_ACTIVE") + "").equals("1") ? "NOT" : "";
      where += " AND s.ACTIVE_CODE IS " + ext +" NULL";
    }
	  return BizUtil.queryListBySQL(publicSql, where, "s.STORE_ID desc", fieldList, pageData, this.userACL);
  }
  
  public Map<String, Object> queryStoreDetail(Long id, List<DbField> fieldList) throws BizException {
	  return DbUtils.queryMap(fieldList, publicSql + " where s.STORE_ID = ?", id);
  }

  public void saveStore(Map<String,Object> valueMap, Boolean isAdd) {
    try {      
      String updateField = "STORE_NAME,AREA_ID,NOTE";  
      if(isAdd){
    	  valueMap.put("LEFT_MONEY", 0);
    	  valueMap.put("ALL_PAY", 0);
    	  valueMap.put("ALL_CONSUME_ORDER", 0);
    	  valueMap.put("ALL_CONSUME", 0);
    	  valueMap.put("ALL_CONSUME_MONEY", 0);
    	  valueMap.put("ALL_RETURN_MONEY", 0);
    	  valueMap.put("ALL_RETURN_ORDER", 0);
    	  valueMap.put("OP_ID", this.userACL.getUserID());
    	  valueMap.put("STATE", 1);
    	  Map<String,String> funcMap = new HashMap<String, String>();	
    	  funcMap.put("CREATE_TIME", "NOW()");
    	  DbUtils.add("STORE",valueMap,funcMap,updateField + ",STORE_NUM,STORE_TYPE_ID,TYPE," +
    	  		"LEFT_MONEY,ALL_CONSUME_ORDER,ALL_PAY,ALL_CONSUME_MONEY,ALL_RETURN_MONEY,OP_ID,STATE,CREATE_TIME");
      }else{
    	  DbUtils.update("STORE",valueMap,null,updateField,"STORE_ID");
      } 
    } catch (BizException e) {
       throw e;
     } catch(Exception ex) {
       ex.printStackTrace(); 
       throw new BizException(BizException.SYSTEM,"±£¥Ê ß∞‹",ex);
    }
  }
  
}
