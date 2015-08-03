package com.ga.erp.biz.activity.scorerule;

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

public class ScoreRuleBiz extends ACLBiz {

  public ScoreRuleBiz(UserACL userACL) {
    super(userACL);
  }

  public PageResult queryRuleList(QueryPageData pageData,List<DbField> fieldList) {
	return BizUtil.queryListBySQL("SELECT * FROM SCORE_RULE", "", "SCORE_RULE_ID DESC", fieldList, pageData, this.userACL);
  }
  
  public Map<String, Object> queryRuleDetail(Long id,List<DbField> fieldList) throws BizException {
	  return DbUtils.queryMap(fieldList, "SELECT * FROM SCORE_RULE where SCORE_RULE_ID = ?", id);
  }
  
  private void chkValue(Map<String,Object> valueMap){
	if(valueMap.get("RULE_NAME") == null || GaUtil.isNullStr(valueMap.get("RULE_NAME") + "")){
       throw new BizException("请输入规则名称");
     }
    if(valueMap.get("SCORE") == null || GaUtil.isNullStr(valueMap.get("SCORE") + "")){
   	   throw new BizException("请输入产生积分");
     }
    if(valueMap.get("REWARD_TIME") == null || GaUtil.isNullStr(valueMap.get("REWARD_TIME") + "")){
       throw new BizException("请输入奖励次数");
     }
  }

  public void saveRule(Map<String,Object> valueMap, Boolean isAdd) {
    try {    
      chkValue(valueMap);
      String updateField = "RULE_NAME,SCORE,TIME_PERIOD,REWARD_CYCLE,REWARD_TIME,REWARD_TYPE,STATE,CREATE_TYPE";   
      if(isAdd){
    	  DbUtils.add("SCORE_RULE",valueMap,null,updateField);
      }else{
    	  DbUtils.update("SCORE_RULE",valueMap,null,updateField,"SCORE_RULE_ID");
      } 
    } catch (BizException e) {
       throw e;
     } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"保存失败",ex);
    }
  }
  
}
