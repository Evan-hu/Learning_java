package com.ga.erp.biz.comm.sort;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.UserACL;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.ACLBiz;

public class SortBiz extends ACLBiz {

  public SortBiz(UserACL userACL) {
    super(userACL);
  }

  public ResultSet querySortList(Long pId) {
	  try {    
	    return DbUtils.query("select * from sort order by nlevel,sort_id");
	  } catch (Exception e) {
		e.printStackTrace();  
	    throw new BizException(BizException.SYSTEM,"查询分类列表失败");
	  }  
  }
  
  public Map<String,Object> querySortDetail(List<DbField> fieldList, Long sortId) {
    try {      
      String sql=
        "select s.*, ps.SORT_NAME as PSORT_NAME from SORT s left join\n" +
        " SORT ps\n" +
        " on s.P_ID = ps.SORT_ID\n" + 
        "   where s.SORT_ID = ?";
      return DbUtils.queryMap(fieldList, sql, sortId);
    }catch (BizException e) {
      throw e;
    }catch(Exception ex) {
      ex.printStackTrace();
      throw new BizException(BizException.SYSTEM,"查询分类详情失败",ex);
    }
  }

  /**
   * 判断所选中分类是否自己的子分类
   * @param sortID  本分类
   * @param chkSortID 所选中的分类
   */
  public boolean isSelfChild(String sortId, String chkSortId) {
	return DbUtils.queryLong("select * from SORT where SORT_ID = ? and ID_PATH like '%/"+sortId+"/%'", chkSortId) != null;
  }
  
  public void chkValue(Map<String, Object> sortMap){
    StringBuffer buffer=new StringBuffer("");
    int count=1;
    if(GaUtil.isNullStr(sortMap.get("SORT_NAME")+"")){
      buffer.append("<br>"+count+"，请输入分类名称；");
      count++;
    }
    if(GaUtil.isNullStr(sortMap.get("P_ID")+"")){
      buffer.append("<br>"+count+"，请选择上级分类；");
      count++;
    }
    if(GaUtil.isNullStr(sortMap.get("RANK_NUM")+"")){
      buffer.append("<br>"+count+"，请输入排序值；");
      count++;
    }
    if(!GaUtil.isNullStr(sortMap.get("CUT_RATIO")+"") && (Double.parseDouble(sortMap.get("CUT_RATIO")+"") > 100 || Double.parseDouble(sortMap.get("CUT_RATIO")+"") < 0)){
      buffer.append("<br>"+count+"，提成率不能小于0或大于100%；");
      count++;
    }
    if(0 != buffer.length()){
      throw new BizException(buffer.toString());
    }
  }
  
  public void saveSort(Map<String,Object> sortMap, Boolean isAdd) {
    try {      
      chkValue(sortMap);
      DbUtils.begin();
      String updateFields = "SORT_NAME,P_ID,CODE,CUT_RATIO,STATE,RANK_NUM,NOTE";
      Object sortId = null;
      if(isAdd){
    	  Map<String,String> funcMap = new HashMap<String, String>();
    	  sortMap.put("STATE", 1);
    	  funcMap.put("CREATE_TIME", "now()");
    	  DbUtils.add("SORT",sortMap,funcMap, updateFields + ",CREATE_TIME");
    	  sortId = GaUtil.getLastId();
      }else{
    	  sortId = sortMap.get("SORT_ID");
    	  DbUtils.update("SORT", sortMap, null, updateFields, "SORT_ID");
      }
      GaUtil.updateTreeLevel(DbUtils.getConnection(), "SORT", sortId, sortMap.get("P_ID"), sortMap.get("STATE"));
      DbUtils.commit();
    } catch (BizException e) {
    	DbUtils.rollback();
       throw e;
    } catch(Exception ex) {
    	DbUtils.rollback();
       ex.printStackTrace();
       throw new BizException(BizException.SYSTEM, "保存失败", ex);
    }
  }
  
}
