package com.ga.erp.biz.activity.cardbatch;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.rowset.CachedRowSet;
import com.ga.click.control.DbField;
import com.ga.click.control.LookupDataSet;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.UserACL;
import com.ga.click.page.BizUtil;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.ACLBiz;

public class BatchBiz extends ACLBiz {

  public BatchBiz(UserACL userACL) {
    super(userACL);
  }
  
  public PageResult queryCardBatchLists(QueryPageData pageData,Map<String, Object> vMap,List<DbField> dbFields){
    try {
      String sql="select ca.*,o.TRUENAME from CARD_BATCH ca JOIN OP o on ca.OP_ID = o.OP_ID";
      return BizUtil.queryListBySQL(sql, "", "ca.CARD_BATCH_ID desc", dbFields, pageData, this.userACL);
    } catch (Exception e) {
      e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"查询信息失败");
    }
  }
  
  public Map<String, Object> queryBatchInfoOfCard(Long batchID,List<DbField> dbFields){
    try {
      String sql="select * from CARD_BATCH where CARD_BATCH_ID= ? ";
      return DbUtils.queryMap(dbFields, sql, batchID);
    } catch (Exception e) {
      e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"查询信息失败");
    }
  }
  
  public void updateBatchInfoOfCard(Map<String, Object> vMap){
    try {
      vMap.put("BEGIN_TIME", Timestamp.valueOf((String)vMap.get("BEGIN_TIME")));
      vMap.put("END_TIME", Timestamp.valueOf((String)vMap.get("END_TIME")));
      String updateFieldsString="NAME,BEGIN_TIME,END_TIME,MIN_MONEY,SAVE_MONEY,STATE,NOTE";
      DbUtils.begin();
      DbUtils.update("CARD_BATCH", vMap, null, updateFieldsString, "CARD_BATCH_ID");
      DbUtils.commit();
    } catch (Exception e) {
      e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"更新信息失败");
    }
  }
  
  public void addBatchInfoOfCard(Map<String, Object> vMap,Map<String, String> funcMap){
    try {
      int autoNums = Integer.parseInt(""+vMap.get("AUTO_COUNT"));
      if(autoNums>10000){
        throw new BizException(BizException.SYSTEM,"发放的电子卷数量不能超过10000");
      }
      vMap.put("OP_ID", this.userACL.getUserID());
      vMap.put("BEGIN_TIME", Timestamp.valueOf((String)vMap.get("BEGIN_TIME")));
      vMap.put("END_TIME", Timestamp.valueOf((String)vMap.get("END_TIME")));
      String insertBatchFields="CARD_BATCH_ID,NAME,TYPE,STATE,MIN_MONEY,SAVE_MONEY,"+
                            "BEGIN_TIME,END_TIME,NOTE,OP_ID,CREATE_TIME";
      String autoCardFields="CARD_ID,CARD_BATCH_ID,OP_ID,CARD_NUM,CREATE_TIME,STATE,GET_TYPE";
      DbUtils.begin();
      DbUtils.add("CARD_BATCH", vMap, funcMap, insertBatchFields);
      Map<String, Object> autoCardMap = new HashMap<String, Object>();
      autoCardMap.put("OP_ID", this.userACL.getUserID());
      autoCardMap.put("STATE", 1);
      autoCardMap.put("GET_TYPE", 0);
      //**********自动生成电子卷
      for (int i = 0; i < autoNums; i++) {
        StringBuffer cardNum = new StringBuffer(GaUtil.encodeToMD5(GaUtil.getDateStr(new Date())));
        cardNum.delete(0, 16);
        cardNum.insert(4, "-");
        cardNum.insert(9, "-");
        cardNum.insert(14, "-");
        autoCardMap.put("CARD_NUM", cardNum.toString());
        DbUtils.add("CARD", autoCardMap, funcMap, autoCardFields);
      }
      DbUtils.commit();
    } catch (BizException e) {
      DbUtils.rollback();
      throw e;
    }
    catch (Exception e) {
      DbUtils.rollback();
      e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"增加信息失败");
    }
  }
  
  /**
   * 查询所有电子卷批次名称
   * @return
   */
  public List<Map<String , Object>> queryBatchNameMapOfList(){
    try {
      String sql="select distinct NAME from CARD_BATCH order by NAME ";
      return DbUtils.queryMapList(DbUtils.getConnection(), sql);
    } catch (Exception e) {
      e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"查询信息失败");
    }
  }
  
  public List<Map<String , Object>> queryBatchIDAndNameMapOfList(){
    try {
      String sql="select distinct CARD_BATCH_ID,NAME from CARD_BATCH order by NAME asc ";
      return DbUtils.queryMapList(DbUtils.getConnection(), sql);
    } catch (Exception e) {
      e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"查询信息失败");
    }
  }
  
  public LookupDataSet queryCardBatchLookupDataSet(){
    try {
      String sql="select distinct CARD_BATCH_ID,NAME from CARD_BATCH order by NAME asc ";
      CachedRowSet rs = DbUtils.query(sql);
      return new LookupDataSet(rs, "CARD_BATCH_ID", "NAME");
    } catch (Exception e) {
      e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"查询信息失败");
    }
  }
  
  
  
}
