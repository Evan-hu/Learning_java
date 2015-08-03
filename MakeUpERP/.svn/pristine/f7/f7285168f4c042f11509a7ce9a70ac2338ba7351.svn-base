package com.ga.erp.biz.store.returnOrder;

import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.dbutil.PageResult;
import com.ga.click.mvc.UserACL;
import com.ga.click.page.BizUtil;
import com.ga.click.page.QueryPageData;
import com.ga.erp.biz.ACLBiz;

public class StoreReturnBiz extends ACLBiz{

	public StoreReturnBiz(UserACL userACL) {
		super(userACL);
	}

	public String publicSql = "SELECT sro.*, sso.STORE_SALES_ORDER_NUM,m.MEMBER_NUM,s.STORE_NAME,o.TRUENAME,p.NAME " +
      "from STORE_RETURN_ORDER sro " +
      "join STORE_SALES_ORDER sso on sro.STORE_SALES_ORDER_ID=sso.STORE_SALES_ORDER_ID " +
      "join OP o on o.OP_ID=sro.OP_ID " +
      "join POS p on p.POS_ID=sro.POS_ID " +
      "join STORE s on sso.STORE_ID=s.STORE_ID " +
      "join MEMBER m on sso.MEMBER_ID=m.MEMBER_ID ";
	
	public PageResult queryStoreReturnList(QueryPageData queryPageData,List<DbField> dbFieldList,Long storeId,String state) {
	  String where = "";
	  if(state !=null){
  	  if(storeId == null){
          where = "sro.STATE=" + state;
  	  }else {
        where = "sso.STORE_ID = " + storeId;
      }
	  }
		return BizUtil.queryListBySQL(publicSql, where, "sro.STORE_RETURN_ORDER_ID desc",
				dbFieldList, queryPageData, this.userACL);
	}
	
	public Map<String, Object> queryStoreReturnDetail(Long id) {
		return DbUtils.queryMap(DbUtils.getConnection(),publicSql + "where sro.STORE_RETURN_ORDER_ID=?", id);
	}
	 
	public PageResult queryStoreReturnComm(QueryPageData queryPageData,List<DbField> dbFieldList,Long id) {
		String sql = "SELECT src.*,c.COMMODITY_NAME FROM store_return_comm src " +
				"JOIN store_comm sc ON sc.STORE_COMM_ID= src.STORE_COMM_ID " +
				"JOIN commodity c ON sc.COMMODITY_ID=c.COMMODITY_ID " ;
		return BizUtil.queryListBySQL(sql, "src.store_return_order_ID="+id, "src.store_return_comm_ID desc",
				dbFieldList, queryPageData, this.userACL);
	}

}
