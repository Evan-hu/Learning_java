package com.ga.erp.biz.content.ad;

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

public class AdBiz extends ACLBiz { 

  public AdBiz(UserACL userACL) {
    super(userACL);
  }

  public PageResult queryAdList(QueryPageData pageData,List<DbField> fieldList) {
    String sql = "select a.*,o.TRUENAME from AD a join OP o on a.OP_ID = o.OP_ID ";
	  return BizUtil.queryListBySQL(sql,"", "AD_ID desc", fieldList, pageData, this.userACL);
  }
  
  /**
   * 查询过期的广告
   */
  public PageResult queryInvalidAdList(QueryPageData pageData,List<DbField> fieldList){
	  return BizUtil.queryListBySQL("select * from AD", " END_TIME < NOW() ",
			  "AD_ID desc", fieldList, pageData, this.userACL);
  }
  
  /**
   * 查询详细
   */
  public Map<String, Object> queryAdDetail(Long id, List<DbField> fieldList) throws BizException {
	  return DbUtils.queryMap(DbUtils.getConnection(), "select * from AD where AD_ID = ?", id);
  }

//public PageResult queryAdListMap(QueryPageData pageData,List<DbField> fieldList, Long storeId) {
//String sql = "select * from AD ad join AD_PUBLISH ap on ad.AD_ID=ap.AD_ID join STORE s on ap.STORE_ID=s.STORE_ID";
//return BizUtil.queryListBySQL(sql, " where ap.STORE_ID = ?" + storeId, "AD_ID desc", fieldList, pageData, this.userACL);
//}

  public PageResult queryAdListByStore(QueryPageData pageData,List<DbField> fieldList,Long storeId) {
    String sql = "select a.*,o.TRUENAME from AD a JOIN OP o on a.OP_ID = o.OP_ID join AD_PUBLISH ap on ap.AD_ID=a.AD_ID";
    String where = "ap.STORE_ID = " + storeId;
    return BizUtil.queryListBySQL(sql,where, "a.AD_ID desc", fieldList, pageData, this.userACL);
  }
  
  public void saveAd(Map<String,Object> valueMap, Boolean isAdd) {
    try {      
      String updateField = "AD_NAME,CONTENT,AD_WIDTH,AD_HEIGHT,BEG_TIME,END_TIME," +
      		               "RANK_NUM,NOTE,STATE";    
      if(isAdd){
    	  Map<String,String> funcMap = new HashMap<String, String>();	
    	  funcMap.put("CREATE_TIME", "NOW()");
    	  valueMap.put("STATE", 1);
    	  valueMap.put("OP_ID", this.userACL.getUserID());
    	  DbUtils.add("AD",valueMap,funcMap,updateField + ",OP_ID,CREATE_TIME");
    	  if(valueMap.get("CONTENT") != null && !GaUtil.isNullStr(valueMap.get("CONTENT") + "")){
            FileUploadUtil.uploadSave(valueMap, "CONTENT", "ad", GaUtil.getLastId());
          }
      }else{
    	  DbUtils.update("AD",valueMap,null,updateField,"AD_ID");
    	  if(valueMap.get("CONTENT") != null && !GaUtil.isNullStr(valueMap.get("CONTENT") + "")){
            FileUploadUtil.uploadSave(valueMap, "CONTENT", "ad", Long.parseLong(valueMap.get("AD_ID") + ""));
          }
      } 
    } catch (BizException e) {
        throw e;
    } catch(Exception ex) {
    	ex.printStackTrace();
        throw new BizException(BizException.SYSTEM,"保存失败",ex);
    }
  }
  
}
