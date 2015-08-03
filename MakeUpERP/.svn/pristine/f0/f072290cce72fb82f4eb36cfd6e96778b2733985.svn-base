package com.ga.erp.biz.activity.regactivity;

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
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.ACLBiz;

public class RegActivityBiz extends ACLBiz {

  public RegActivityBiz(UserACL userACL) {
    super(userACL);
  }
  String  publicSql = " SELECT ra.* ,o.TRUENAME, NAME  FROM REG_ACTIVITY ra JOIN OP o ON o.OP_ID = ra.OP_ID" +
      " LEFT JOIN CARD_BATCH cb ON cb.CARD_BATCH_ID = ra.OBJECT_ID AND ra.TYPE = 2";

  String queryStoreSql = "select s.*,a.AREA_NAME,o.TRUENAME, case WHEN ACTIVE_CODE is null " +
      "then 0 else 1 end IS_ACTIVE from STORE s JOIN AREA a " +
      "on s.AREA_ID = a.AREA_ID JOIN OP o on s.OP_ID = o.OP_ID";
  public PageResult queryRegActivityList(QueryPageData pageData,List<DbField> fieldList) {
    return BizUtil.queryListBySQL(publicSql, "", "ra.REG_ACTIVITY_ID DESC", fieldList, pageData, this.userACL);
  }
  
  public Map<String, Object> queryRegActivityDetail(Long id,List<DbField> fieldList) throws BizException {
	  return DbUtils.queryMap(fieldList, publicSql + " where ra.REG_ACTIVITY_ID = ?", id);
  }
  
  public PageResult queryStoreList(QueryPageData pageData, List<DbField> fieldList, String id, boolean isEdit) {
    String where =isEdit ? " s.STORE_ID NOT IN (SELECT STORE_ID FROM REG_ACTIVITY_STORE WHERE REG_ACTIVITY_ID = " + id + ")" : "";
    return BizUtil.queryListBySQL(queryStoreSql, where, "s.STORE_ID desc", fieldList, pageData, this.userACL);
  }
  
  public PageResult queryRegStoreList(QueryPageData pageData, List<DbField> fieldList, String id) {
    String where = " s.STORE_ID IN (SELECT STORE_ID FROM REG_ACTIVITY_STORE WHERE REG_ACTIVITY_ID = " + id + ")";
    return BizUtil.queryListBySQL(queryStoreSql, where, "s.STORE_ID desc", fieldList, pageData, this.userACL);
  }
  
  public PageResult queryRegMemberList(QueryPageData pageData,List<DbField> fieldList, Long id) {
    String sql = "SELECT mra.*,c.CARD_NUM , m.TRUENAME FROM MEMBER_REG_ACTIVITY mra  JOIN MEMBER m ON m.MEMBER_ID = mra.MEMBER_ID LEFT JOIN CARD c ON c.CARD_ID = mra.OBJECT_ID AND mra.TYPE = 2";
    String where = " mra.REG_ACTIVITY_ID = " + id;
    return BizUtil.queryListBySQL(sql, where, "mra.MEMBER_REG_ACTIVITY_ID desc", fieldList, pageData, this.userACL);
  }
  
  private void chkValue(Map<String,Object> valueMap){
	if(valueMap.get("BEG_TIME") == null || GaUtil.isNullStr(valueMap.get("BEG_TIME") + "")){
       throw new BizException("请输入活动开始时间");
     }
    if(valueMap.get("END_TIME") == null || GaUtil.isNullStr(valueMap.get("END_TIME") + "")){
   	   throw new BizException("请输入活动结束时间");
     }
    if("2".equals(valueMap.get("TYPE") + "")) {
      if(valueMap.get("OBJECT_ID") == null || GaUtil.isNullStr(valueMap.get("OBJECT_ID") + "")){
        throw new BizException("请选择礼券批次");
      }
    }
  if(valueMap.get("GIVE_VALUE") == null || GaUtil.isNullStr(valueMap.get("GIVE_VALUE") + "")){
    throw new BizException("请输入赠送值");
  }
  if((valueMap.get("BEG_TIME")+"").compareTo(valueMap.get("END_TIME") + "") >= 0){
    throw new BizException("开始时间必须小于结束时间");
  }  
  }

  public void save(Map<String, Object> formData, List<Map<String, Object>> listData) {
    
    chkValue(formData);
    //下面是判断当前时新建模式还是编辑模式
    Boolean isEdit = GaUtil.isNullStr(formData.get("REG_ACTIVITY_ID") + "") ? false : true;
    Map<String, String> funcMap = new HashMap<String, String>();
    funcMap.put("CREATE_TIME", "now()");
    
    formData.put("OP_ID", this.userACL.getUserID());
    formData.put("STATE", 1);
    formData.put("CHECK_STATE", 0);
    if("1".equals(formData.get("TYPE") + "")) {
      formData.remove("OBJECT_ID");
    }
    try {
      DbUtils.begin();
      if(isEdit) {
        DbUtils.update("REG_ACTIVITY", formData, funcMap, "OP_ID,CREATE_TIME,TYPE,OBJECT_ID,GIVE_VALUE,BEG_TIME,END_TIME,STATE,CHECK_STATE", "REG_ACTIVITY_ID");
      }else{
        DbUtils.add("REG_ACTIVITY", formData, funcMap, "OP_ID,CREATE_TIME,TYPE,OBJECT_ID,GIVE_VALUE,BEG_TIME,END_TIME,STATE,CHECK_STATE");
      }
      String regActivityId = isEdit ? formData.get("REG_ACTIVITY_ID") + "": GaUtil.getLastId() + "";
      for(Map<String, Object> map : listData) {
        DbUtils.execute("INSERT INTO REG_ACTIVITY_STORE(REG_ACTIVITY_ID, STORE_ID) VALUES (?,?)",regActivityId,  map.get("STORE_ID"));
      }
      DbUtils.commit();
    } catch (BizException e) {
      DbUtils.rollback();
      throw e;
     } catch(Exception ex) {
       DbUtils.rollback();
      throw new BizException(BizException.SYSTEM,"保存失败",ex);
    }
  }
}
