package com.ga.erp.biz.system.audits.role;

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

public class AuditsRoleBiz extends ACLBiz {

	public AuditsRoleBiz(UserACL userACL) {
		super(userACL);
	}

	public String publicSql = "select ar.*,r.ROLE_NAME,ac.SYS_CODE_NAME from AUDITING_ROLE ar " +
			"join ROLE r on ar.ROLE_ID = r.ROLE_ID " +
			"join AUDITING_CONFIG ac on ac.AUDITING_CONFIG_ID=ar.AUDITING_CONFIG_ID";
	
	public PageResult queryAuditsRoleList(QueryPageData pageData,List<DbField> fieldList,Long audConfigId) {
	  String where = audConfigId == null ? "" : " ar.AUDITING_CONFIG_ID = " + audConfigId;
		return BizUtil.queryListBySQL(publicSql, where, "ar.STEP",fieldList, pageData, this.userACL);
	}

	public Map<String, Object> queryAuditingRoleDetail(Long id,List<DbField> fieldList) throws BizException {
	    return DbUtils.queryMap(fieldList,publicSql + " where ar.AUDITING_ROLE_ID = ?", id);
	}
	 
  private void chkValue(Map<String,Object> valueMap){
    StringBuffer buffer = new StringBuffer("");
    int count = 1;
    if(GaUtil.isNullStr(valueMap.get("ROLE_ID") + "")){
      buffer.append("<br>"+count+"£¬ÇëÑ¡Ôñ½ÇÉ«Ãû³Æ£»");
      count++; 
      }
    if(GaUtil.isNullStr(valueMap.get("STEP") + "")){
      buffer.append("<br>"+count+"£¬ÇëÊäÈë²½Öè£»");
      count++; 
    }
    if(buffer.length() > 0){
       throw new BizException(buffer.toString());
    }
  }
  
  
	 public void saveAuditingRole(Map<String, Object> valueMap, Boolean isAdd ) {
	    try {
	      DbUtils.begin();
	      this.chkValue(valueMap);
	      String updateField = "ROLE_ID,AUDITING_CONFIG_ID,STEP,NOTE";
	      Long maxSetp = 0l;
	      if (isAdd) {
	        DbUtils.add("AUDITING_ROLE", valueMap, null, updateField);
	      } else {
	        DbUtils.update("AUDITING_ROLE", valueMap, null,updateField, "AUDITING_ROLE_ID");
	      }
	      maxSetp = DbUtils.queryLong("select max(setp) from AUDITING_ROLE where AUDITING_CONFIG_ID = ?",valueMap.get("AUDITING_CONFIG_ID"));
	      DbUtils.update("update AUDITING_EXAMPLE set ALL_STEP = ? where AUDITING_CONFIG_ID = ?", maxSetp,valueMap.get("AUDITING_CONFIG_ID"));
	      DbUtils.commit();
	    } catch (BizException e) {
	      throw e;
	    } catch (Exception ex) {
	      ex.printStackTrace();
	      throw new BizException(BizException.SYSTEM, "±£´æÊ§°Ü", ex);
	    }
	  }
	 
}
