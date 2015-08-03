package com.ga.erp.biz.store.storesalesdetail;

import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.dbutil.PageResult;
import com.ga.click.mvc.UserACL;
import com.ga.click.page.BizUtil;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.ACLBiz;

public class SalesDetailBiz extends ACLBiz {

	public SalesDetailBiz(UserACL userACL) {
		super(userACL);
	}

	public PageResult querySalesDetailList(QueryPageData pageData,List<DbField> fieldList,Long salesOrderId) {
		String sql = "select c.COMMODITY_NAME,a.*  from STORE_COMM sc "
				+ "join (select sad.*,sao.STORE_SALES_ORDER_NUM from STORE_SALES_DETAIL sad "
				+ "join STORE_SALES_ORDER sao on sad.STORE_SALES_ORDER_ID = sao.STORE_SALES_ORDER_ID) a on a.STORE_COMM_ID=sc.STORE_COMM_ID "
				+ "join COMMODITY c on sc.COMMODITY_ID = c.COMMODITY_ID";
		String where = "";
		if(!GaUtil.isNullStr(salesOrderId + "")) {
		  where += "a.STORE_SALES_ORDER_ID = " + salesOrderId;
    }
		return BizUtil.queryListBySQL(sql, where, "sc.STORE_COMM_ID desc",
				fieldList, pageData, this.userACL);
	}

}
