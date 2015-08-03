package com.ga.erp.biz.activity.scorelog;

import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.dbutil.PageResult;
import com.ga.click.mvc.UserACL;
import com.ga.click.page.BizUtil;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.ACLBiz;

public class ScoreLogBiz extends ACLBiz {

  public ScoreLogBiz(UserACL userACL) {
    super(userACL);
  }

  public PageResult queryScoreLogList(QueryPageData pageData,List<DbField> fieldList, Long memberId, String type) {
	  String where = " 1 = 1 ";
	  if(memberId != null){
		where += "AND sl.MEMBER_ID = " + memberId;  
	  }
	  if(!GaUtil.isNullStr(type)){
   	    where += "AND sl.TYPE = " + type;  
	  }
	  
	  return BizUtil.queryListBySQL("select sl.*,m.TRUENAME,sr.RULE_NAME,sl.BIZ_NAME NEWBIZ_NAME,sl.NOTE NEWNOTE\n" +
					  "  from SCORE_LOG sl\n" + 
					  "  join SCORE_RULE sr\n" + 
					  "  on sl.SCORE_RULE_ID = sr.SCORE_RULE_ID\n" + 
					  "  join MEMBER m\n" + 
					  "  on sl.MEMBER_ID = m.MEMBER_ID",where, "sl.SCORE_LOG_ID desc",
					  fieldList, pageData, this.userACL);
      }
  
  
  
}
