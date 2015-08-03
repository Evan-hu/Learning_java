package com.ga.erp.biz.store.storewalletlog;

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

public class WalletLogBiz extends ACLBiz {

	public WalletLogBiz(UserACL userACL) {
		super(userACL);
	}

	public String publicSql = "select w.*,s.STORE_NUM,so.STORE_ORDER_NUM from STORE_WALLET_LOG w join STORE s " +
			"on w.STORE_ID = s.STORE_ID LEFT JOIN STORE_ORDER so on w.OBJECT_ID = so.STORE_ORDER_ID";

	public PageResult queryWalletLogList(QueryPageData pageData,List<DbField> fieldList,Long storeId) {
	  String where = storeId == null ? "" : "w.STORE_ID = " + storeId;
		return BizUtil.queryListBySQL(publicSql, where, "w.CREATE_TIME desc",fieldList, pageData, this.userACL);
	}

	public Map<String, Object> queryWalletLogDetail(Long id,List<DbField> fieldList) throws BizException {
		return DbUtils.queryMap(fieldList, publicSql
				+ " where w.STORE_WALLET_LOG_ID = ?", id);
	}
}
