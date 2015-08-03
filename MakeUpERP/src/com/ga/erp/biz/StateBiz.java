package com.ga.erp.biz;

import java.util.Map;

import com.ga.click.dbutil.DbUtils;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.UserACL;

public class StateBiz extends ACLBiz {

	public StateBiz(UserACL userACL) {
		super(userACL);
	}
	
	/**
	 * 查询目标TABLE是否含有指定字段
	 * @return
	 */
	public boolean haveColumn(String tableName,String columnName){
	  Map<String,Object> map = DbUtils.queryMap(DbUtils.getConnection(), "show columns from "+tableName+" like ?",columnName);
	  return map!= null  && !map.isEmpty();
	}
	
	public void startObject(Object id,String tableName) {
	    this.startOrStopObject(id, 1L, "对象启用", "该对象已经是启用状态",tableName, "STATE");
	  }
	public void delObject(Object id,String tableName) {
	    this.startOrStopObject(id, 0L, "对象删除", "该对象已经是删除状态",tableName, "DELETE_STATE");
	  }
	  public void stopObject(Object id,String tableName) {
	    this.startOrStopObject(id, 0L, "对象废止", "该对象已经是废止状态",tableName, "STATE");
	  }
	  
	  private void startOrStopObject(Object id, Long state, String actionMessage,String exceptionMessage,String tableName, String updateField) {
	    try {
//	      String sql = "select count(*) from "+tableName+"  where "+tableName+"_ID in (" + id + ") and "+(state < 0 ? "DELETE_STATE" : "STATE")+" = ?";
	      String sql = "select count(*) from "+tableName+"  where "+tableName+"_ID in (" + id + ") and "+ updateField +" = ?";
	      Long cnt = DbUtils.queryLong(sql, state);
	      if (cnt > 0) {
	        throw new BizException(exceptionMessage);
	      } else {
	        sql = "update "+tableName+" set "+ updateField +" = '" + state + "' where "+tableName+"_ID in  ("+ id + ")";
	        DbUtils.begin();
	        DbUtils.execute(sql);
	        }
	        DbUtils.commit();
	    } catch (BizException e) {
	      throw e;
	    } catch (Exception ex) {
	      ex.printStackTrace();
	      throw new BizException(BizException.SYSTEM, ex.getMessage(), ex);
	    } finally {
	      DbUtils.rollback();
	    }
	  }
}
