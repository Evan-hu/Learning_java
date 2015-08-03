package com.ga.erp.biz.member.vipLoyaltyAn;

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
import com.ga.erp.util.SystemUtil;

public class VipLoyaltyAnBiz extends ACLBiz{

	public VipLoyaltyAnBiz(UserACL userACL) {
		super(userACL);
	}

	public PageResult queryStockList(QueryPageData queryPageData,List<DbField> dbFieldList/*,Map<String, Object> queryMap*/) {

	  String wheresql="";
	  if(!this.userACL.isAllOp()){
	    
	      String storeIDs=this.userACL.getStoreID();
	        wheresql =GaUtil.isNullStr(storeIDs)?"":"a.STORE_ID in("+storeIDs+") "; 
	    }
	  
	  //уш©ш╠х  DISCOUNT_RATIO
	  Integer ratio =this.userACL.getDiscRatio();
	  ratio=ratio==0?1:ratio;
	     
		String sql = "select *  from (SELECT m.STORE_ID,m.MEMBER_ID,m.MEMBER_NUM,m.ACTIVATE_TIME,m.ALL_AMOUNT AS ALL_AMOUNT,m.TRUENAME, " +
				"m.LAST_TIME,m.all_order,ROUND(m.ALL_AMOUNT*"+ratio+"/m.all_order,2) AS AVG_ORDER_AMOUNT," +
				" ROUND(m.ALL_AMOUNT/PERIOD_DIFF(m.LAST_TIME,m.ACTIVATE_TIME),2) AS AVG_MONTH_AMOUNT ,s.STORE_NAME " +
				"FROM member m join store s on m.store_id = s.store_id) a";
		
		return BizUtil.queryListBySQL(sql, wheresql, "a.MEMBER_ID desc",dbFieldList, queryPageData, this.userACL);
	}
	
	 public Map<String, Object> queryMemberDetail(Long id, List<DbField> fieldList) throws BizException {
		
		 String publicSql = "select m.*,s.STORE_NAME from MEMBER m JOIN STORE s on m.STORE_ID = s.STORE_ID";
		 
		  return DbUtils.queryMap(fieldList, publicSql + " where m.MEMBER_ID = ?", id);
	  }


}
