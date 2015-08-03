package com.ga.erp.biz.system.audits.config;

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

public class AudConfigBiz extends ACLBiz {

  public AudConfigBiz(UserACL userACL) {
    super(userACL);
  }

  public String puclicSql = "select * from AUDITING_CONFIG";
  
  public PageResult queryAudConfigList(QueryPageData pageData,List<DbField> fieldList) {
	  return BizUtil.queryListBySQL(puclicSql, "", "AUDITING_CONFIG_ID desc", fieldList, pageData, this.userACL);
  }
  
  public Map<String, Object> queryAudConfigDetail(Long id, List<DbField> fieldList) throws BizException {
	  return DbUtils.queryMap(fieldList, puclicSql + " where AUDITING_CONFIG_ID = ?", id);
  }

  private void chkValue(Map<String,Object> valueMap){
    StringBuffer buffer = new StringBuffer("");
    int count = 1;
    if(GaUtil.isNullStr(valueMap.get("SYS_CODE_NAME") + "")){
      buffer.append("<br>"+count+"，请选择业务名称；");
      count++; 
      }
//    if(GaUtil.isNullStr(valueMap.get("IS_REPEAT") + "")){
//      buffer.append("<br>"+count+"，请选择是否允许多次审核；");
//      count++; 
//    }
    if(buffer.length() > 0){
       throw new BizException(buffer.toString());
    }
  }
  
  public void saveAudConfig(Map<String,Object> valueMap, Boolean isAdd) {
    try { 
      this.chkValue(valueMap);
      String updateField = "SYS_CODE_ID,SYS_CODE_NAME,STATE,CHECK_LOSE_TYPE,CHECK_IN_TYPE,CHECK_SUCCESS_TYPE";  
      if(isAdd){
        valueMap.put("STATE", -1);
    	  DbUtils.add("AUDITING_CONFIG",valueMap,null,updateField + "");
      }else{
    	  DbUtils.update("AUDITING_CONFIG",valueMap,null,updateField,"AUDITING_CONFIG_ID");
      } 
    } catch (BizException e) {
       throw e;
     } catch(Exception ex) {
       ex.printStackTrace(); 
       throw new BizException(BizException.SYSTEM,"保存失败",ex);
    }
  }
  
}
