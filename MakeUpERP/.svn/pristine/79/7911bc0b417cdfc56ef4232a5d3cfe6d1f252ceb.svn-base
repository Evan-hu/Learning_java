package com.ga.erp.biz.system.purview;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.UserACL;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.ACLBiz;

public class PurviewBiz extends ACLBiz {

  public PurviewBiz(UserACL userACL) {
    super(userACL);
  }

  public ResultSet queryPurviewList(Long pId,boolean isAll) {
	    try {        
	      return DbUtils.query("select * from PURVIEW where ID_PATH like '%/"+pId+"/%' "+(isAll ? "" : "and STATE_PATH not like '%0%'")+" ");
	    } catch (Exception e) {
	      e.printStackTrace();
	      throw new BizException(BizException.SYSTEM,"查询系统权限列表失败");
	    }
	  }
  
  /**
   * 查询详细
   * @param fieldList
   * @param id
   * @return
   * @throws BizException
   */
  public Map<String, Object> queryPurviewDetail(List<DbField> fieldList,Long id) throws BizException {
    try {
        ResultSet rs = DbUtils.query("select p.*,pu.PURVIEW_NAME P_NAME from PURVIEW p left join PURVIEW pu on p.P_ID = pu.PURVIEW_ID where P.PURVIEW_ID = ?", id);
        return GaUtil.getDataMap(fieldList, rs);
    } catch (BizException e) {
    	e.printStackTrace();
        throw e;
    } catch (Exception ex) {
        ex.printStackTrace();
        throw new BizException(BizException.SYSTEM, "执行查询失败", ex);
    }
  }
  
  /**
   * 判断所选中是否自己的子
   * @param sortID  本分类
   * @param chkSortID 所选中的分类
   */
  public boolean isSelfChild(Long sortID,Long chkSortID) {
	    String sql = " select count(*) from PURVIEW where PURVIEW_ID = ? and ID_PATH like '%/"+sortID+"/%'";
	    Long cnt = DbUtils.queryLong(sql,chkSortID);
	    if (cnt > 0 ) {
	      return true;
	    } else {
	      return false;
	    }
	  }
  
  /**
   * 更新权限信息
   * @param valueMap
   * @param funcMap
   * @param pkFieldName
   */
  public void updatePurview(Map<String,Object> valueMap,Map<String,String> funcMap,String pkFieldName) {
    try {      
      if (valueMap.get("PURVIEW_ID") == null) {
        throw new BizException("没有指定要保存的权限ID");
      }
      DbUtils.begin();
      DbUtils.update("PURVIEW",valueMap,funcMap,"PURVIEW_ID,P_ID,PURVIEW_NAME,TYPE,MARK,STATE,RANK_NUM,NOTE,FORM_URL",pkFieldName);
      GaUtil.updateTreeLevel(DbUtils.getConnection(), "PURVIEW", valueMap.get("PURVIEW_ID"), valueMap.get("P_ID"), valueMap.get("STATE"));
      DbUtils.commit();
    } catch (BizException e) {
      throw e;
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"执行保存失败",ex);
    }
    finally {
      DbUtils.rollback();
    }
  }
  
  /**
   * 新建权限
   * @param valueMap
   * @param funcMap
   */
  public void saveNewPurview(Map<String,Object> valueMap,Map<String,String> funcMap) {
    try {      
      DbUtils.begin();
      funcMap.put("CREATE_TIME", "now()");
      DbUtils.add("PURVIEW",valueMap,funcMap,"P_ID,PURVIEW_NAME,TYPE,FORM_URL,MARK,STATE,RANK_NUM,CREATE_TIME,NOTE");
      GaUtil.updateTreeLevel(DbUtils.getConnection(), "PURVIEW", GaUtil.getLastId(), valueMap.get("P_ID"), valueMap.get("STATE"));
      DbUtils.commit();
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"执行新建失败",ex);
    }
    finally {
      DbUtils.rollback();
    }
  }
  
}
