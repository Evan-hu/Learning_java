package com.ga.erp.biz.member;

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

public class MemberBiz extends ACLBiz {

  public MemberBiz(UserACL userACL) {
    super(userACL);
  }

  public String publicSql = "select m.*,s.STORE_NAME,vr.VIP_LV from MEMBER m " +
  		"join STORE s on m.STORE_ID = s.STORE_ID " +
  		"join VIP_RULE vr on m.VIP_RULE_ID=vr.VIP_RULE_ID";
  
  public PageResult queryMemberList(QueryPageData pageData,List<DbField> fieldList,Long storeId) {
    String where = storeId == null ? "" : "m.STORE_ID = " + storeId;
	  return BizUtil.queryListBySQL(publicSql, where, "m.MEMBER_ID desc", fieldList, pageData, this.userACL);
  }
  
  public Map<String, Object> queryMemberDetail(Long id, List<DbField> fieldList) throws BizException {
	  return DbUtils.queryMap(fieldList, publicSql + " where m.MEMBER_ID = ?", id);
  }
  
  public void saveMember(Map<String,Object> valueMap, Boolean isAdd) {
    try {      
      DbUtils.update("MEMBER",valueMap,null,"TEL,EMAIL,NOTE","MEMBER_ID");
    } catch (BizException e) {
       throw e;
    } catch(Exception ex) {
       throw new BizException(BizException.SYSTEM, "±£¥Ê ß∞‹",ex);
    }
  }
  
  public void updateMemberPwd(Map<String, Object> valMap){
    try {      
      DbUtils.update("UPDATE MEMBER set PWD = ? where MEMBER_ID = ?", valMap.get("PASSWORD"), valMap.get("MEMBER_ID"));	
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"–ﬁ∏ƒ√‹¬Î ß∞‹£°",ex);
    }
  }
}
