package com.ga.erp.biz.member.viplog;

import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.dbutil.PageResult;
import com.ga.click.mvc.UserACL;
import com.ga.click.page.BizUtil;
import com.ga.click.page.QueryPageData;
import com.ga.erp.biz.ACLBiz;

public class VipLogBiz extends ACLBiz {

	public VipLogBiz(UserACL userACL) {
		super(userACL);
	}

	public String publicSql = "select v.*,m.STORE_ID,m.MEMBER_NUM from VIP_LOG v join MEMBER m on v.MEMBER_ID = m.MEMBER_ID";

	public PageResult queryVipLogList(QueryPageData pageData,List<DbField> fieldList,Long memberId) {
	  String where = memberId == null ? "" : "v.MEMBER_ID = " + memberId;
		return BizUtil.queryListBySQL(publicSql, where , "v.CREATE_TIME desc",fieldList, pageData, this.userACL);
	}

}
