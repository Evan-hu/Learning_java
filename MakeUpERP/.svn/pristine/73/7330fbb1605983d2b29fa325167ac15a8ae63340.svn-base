/**
 * Copyright (c) 2014 GA
 * All right reserved.
 */
package com.ga.erp.biz.member.viplog;

import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.dbutil.PageResult;
import com.ga.click.mvc.UserACL;
import com.ga.click.page.BizUtil;
import com.ga.click.page.QueryPageData;
import com.ga.erp.biz.ACLBiz;

/**
 * ¿‡√Ë ˆ
 * @author jzk
 * @create_time 2014-5-2 …œŒÁ11:53:54
 * @project MakeUpERP
 */
public class ConsumeLogBiz extends ACLBiz{

  /**
   * @param userACL
   */
  public ConsumeLogBiz(UserACL userACL) {
    super(userACL);
  }

  public PageResult queryConsumeLogList(QueryPageData pageData,List<DbField> fieldList){
    String sql = "select sso.*,m.MEMBER_NUM,s.STORE_NAME,o.TRUENAME from STORE_SALES_ORDER sso " +
    		"join MEMBER m on sso.MEMBER_ID=m.MEMBER_ID " +
    		"join STORE s on sso.STORE_ID=s.STORE_ID " +
    		"join OP o on sso.OP_ID=o.OP_ID";
    String where = "sso.TYPE=1";
    return BizUtil.queryListBySQL(sql, where, "sso.CREATE_TIME desc", fieldList, pageData, this.userACL);
     
  }
}
