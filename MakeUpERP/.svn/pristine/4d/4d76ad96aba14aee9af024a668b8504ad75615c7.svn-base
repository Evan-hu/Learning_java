package com.ga.erp.biz.member.activelog;

import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.dbutil.PageResult;
import com.ga.click.mvc.UserACL;
import com.ga.click.page.BizUtil;
import com.ga.click.page.QueryPageData;
import com.ga.erp.biz.ACLBiz;

public class ActiveLogBiz extends ACLBiz {

	public ActiveLogBiz(UserACL userACL) {
		super(userACL);
	}

	public String publicSql = "select a.*,m.STORE_ID,m.MEMBER_NUM from ACTIVE_LOG a join MEMBER m on a.MEMBER_ID = m.MEMBER_ID ";

	public PageResult queryActLogList(QueryPageData pageData,List<DbField> fieldList,Long memberId) {
	  String where = memberId == null ? "" : "a.MEMBER_ID =" + memberId;
		return BizUtil.queryListBySQL(publicSql, where, "a.CREATE_TIME desc",fieldList, pageData, this.userACL);
	}
	
}
