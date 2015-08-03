package com.ga.erp.biz.store.storesalesorder;

import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.dbutil.PageResult;
import com.ga.click.mvc.UserACL;
import com.ga.click.page.AutoSQLUtil;
import com.ga.click.page.BizUtil;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.PagedQuery;
import com.ga.erp.biz.ACLBiz;

public class SalesOrderBiz extends ACLBiz {

	public SalesOrderBiz(UserACL userACL) {
		super(userACL);
	}

	public String publicSql="SELECT o.*,a.MEMBER_NUM,b.STORE_NAME,c.TRUENAME,d.POS_NUM FROM STORE_SALES_ORDER o " +
                          " join MEMBER a on o.MEMBER_ID=a.MEMBER_ID " +
                          " join STORE b on b.STORE_ID=o.STORE_ID " +
                          " join OP c on c.OP_ID=o.OP_ID " +
                          " join POS d on d.POS_ID=o.POS_ID";
                          
	public PageResult querySalesOrderList(QueryPageData queryPageData,List<DbField> dbFieldList,StringBuilder sb) {
	  PagedQuery query = AutoSQLUtil.createSql(publicSql,"", "", dbFieldList, queryPageData);
    List<Map<String, Object>> groupList;
    try {
      groupList = query.queryOther("select round((sum(TOTAL_MONEY)/100),2) TM,sum(COMM_CNT) CC,round((sum(PAY_MONEY)/100),2) PM from (#QUERY_SQL#) t ");
   
    if (groupList != null) {
      for(Map<String,Object> builder : groupList) {
        sb.append("订单销售总金额：【￥<b style='color:red;'>"+builder.get("TM")+"</b>】," +
                  "实收总金额：【￥<b style='color:red;'>"+builder.get("PM")+"</b>】," +
                  "销售商品总数量：【<b style='color:red;'>"+builder.get("CC")+"</b>】");
      }
    }
    } catch (Exception e) {
      e.printStackTrace();
    }
		return BizUtil.queryListBySQL(publicSql, "", "o.STORE_SALES_ORDER_ID desc",dbFieldList, queryPageData, this.userACL);
	}

	public Map<String, Object> querySalesOrderMap(Long id) {
		String sql = publicSql + " where STORE_SALES_ORDER_ID=?";
		Map<String, Object> valuesMap = DbUtils.queryMap(DbUtils.getConnection(), sql, id);
		return valuesMap;
	}

  public PageResult querySalesOrderList(QueryPageData queryPageData,List<DbField> fieldList, Long storeId) {
    String where = storeId == null ? "" : "o.STORE_ID = " + storeId;
    return BizUtil.queryListBySQL(publicSql, where, "o.STORE_SALES_ORDER_ID desc",fieldList, queryPageData, this.userACL);
  }
  
}
