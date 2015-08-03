package com.ga.erp.biz.content.msgsend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.UserACL;
import com.ga.click.page.BizUtil;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.FileUploadUtil;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.ACLBiz;

public class MsgSendBiz extends ACLBiz {

  public MsgSendBiz(UserACL userACL) {
    super(userACL);
  }

  public PageResult queryShopList(QueryPageData pageData,List<DbField> fieldList) {
	  return BizUtil.queryListBySQL("select s.*,m.USERNAME from SHOP s JOIN MEMBER m on s.MEMBER_ID = m.MEMBER_ID", 
			  "", "s.SHOP_ID desc", fieldList, pageData, this.userACL);
  }
  
  /**
   * 查询详细
   * @param fieldList
   * @param id
   * @return
   * @throws BizException
   */
  public Map<String, Object> queryShopDetail(Long id) throws BizException {
	  return DbUtils.queryMap(DbUtils.getConnection(), "select s.*,m.USERNAME from SHOP s" +
	  		" JOIN MEMBER m on s.MEMBER_ID = m.MEMBER_ID where s.SHOP_ID = ?", id);
  }

  /**
   *  新建
   * @param valueMap
   * @param isAdd
   */
  public void saveShop(Map<String,Object> valueMap, Boolean isAdd) {
    try {      
      String updateField = "MEMBER_ID,TYPE,SHOP_NAME,SHOP_LICENSE," +
        		"LEGAL,TEL,ADDR,SCOPE,ID_CARD,ALIPAY,UNIONPAY,COMPANY_IMG_URL," +
          		"PERSONAL_IMG_URL,IS_RECOMMEND,IS_CHECK," +
          		"IS_OPEN_PERSONAL,SHOP_TYPE,LONG_ITUDE,LAT_ITUDE,STATE,NOTE,CONTENT,SHOP_REVIEW_STAR";   
      if((valueMap.get("IS_RECOMMEND")+"").equals("1")){
    		 updateField = updateField + ",RECOMMEND_NUM";
    	 }
      if((valueMap.get("IS_RECOMMEND")+"").equals("1")){
    		 updateField = updateField + ",CHECK_NUM";
    	 }
      if(!GaUtil.isNullStr((valueMap.get("COMPANY_IMG_URL") + ""))){
          FileUploadUtil.uploadSave(valueMap, "COMPANY_IMG_URL", "shopcom", Long.valueOf(valueMap.get("SHOP_ID") + ""));
        }
      if(!GaUtil.isNullStr((valueMap.get("PERSONAL_IMG_URL") + ""))){
          FileUploadUtil.uploadSave(valueMap, "PERSONAL_IMG_URL", "shopper", Long.valueOf(valueMap.get("SHOP_ID") + ""));
        }
      if(isAdd){
    	  Map<String,String> funcMap = new HashMap<String, String>();	
    	  funcMap.put("CREATE_TIME", "now()");
    	  DbUtils.add("SHOP",valueMap,funcMap,updateField + ",CREATE_TIME");
      }else{
    	  DbUtils.update("SHOP",valueMap,null,updateField,"SHOP_ID");
      } 
    } catch (BizException e) {
       throw e;
     } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"保存失败",ex);
    }
  }
}
