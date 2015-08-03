/**
 * Copyright (c) 2011 Noah
 * All right reserved.
 */
package com.ga.erp.biz.activity.activity;

import java.sql.Connection;
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

/**
 * 类描述
 * 
 * @author SPE
 * @create_time 2012-7-5 下午03:37:56
 * @project ShopMgr
 */
public class ActivityBiz extends ACLBiz {

	public ActivityBiz(UserACL userACL) {
		super(userACL);
	}

	public PageResult queryActivityList(QueryPageData queryData,
			List<DbField> dbFieldList, String typeStr) {
		String whereSql = "";
		if (GaUtil.isNumber(typeStr)) {
			whereSql = " type = " + typeStr;
		} else {
			// 只显示普通活动
			// whereSql = " type = 0";
		}
		String selectSQL = "select d.*,o.USERNAME from DISCOUNT_ACTIVITY d  join OP o on o.OP_ID = D.CREATOR_ID ";
		String where = whereSql;
		return BizUtil.queryListBySQL(selectSQL, where, "d.CREATE_TIME desc",
				dbFieldList, queryData, this.userACL);
	}

	 public PageResult queryActivityListMap(QueryPageData pageData,List<DbField> fieldList, Long storeId) {
	    String where = storeId == null ? "" : "sa.STORE_ID = " + storeId;
	    String sql = "select d.*,o.USERNAME from DISCOUNT_ACTIVITY d  join OP o on o.OP_ID = D.CREATOR_ID join STORE_ACTIVITY sa on d.DISCOUNT_ACTIVITY_ID=sa.DISCOUNT_ACTIVITY_ID";
	    return BizUtil.queryListBySQL(sql, where, "d.CREATE_TIME desc",fieldList, pageData, this.userACL);
	  }
	 
	public Map<String, Object> queryDetail(Long activityId,List<DbField> dbFieldList) throws BizException {
		try {
			Long idv = activityId;
			return DbUtils.queryMap(dbFieldList,"SELECT d.*,o.USERNAME FROM DISCOUNT_ACTIVITY d join OP o  on d.CREATOR_ID = o.OP_ID WHERE DISCOUNT_ACTIVITY_ID=?",idv);

		} catch (BizException e) {
			throw e;
		} catch (Exception ex) {
			throw new BizException(BizException.SYSTEM, "执行查询失败", ex);
		}
	}

	public PageResult queryRuleList(QueryPageData queryData, Long acitivtyID,
			List<DbField> dbFieldList) {
		String sql = "select * from ACTIVITY_RULE ";
		return BizUtil.queryListBySQL(sql, " DISCOUNT_ACTIVITY_ID = "
				+ acitivtyID + "", "DISCOUNT_ACTIVITY_ID desc ", dbFieldList,
				queryData, this.userACL);
	}

	public PageResult queryOrderList(QueryPageData queryData, Long activityID,
			List<DbField> dbFieldList) {
		String sql = "select SO.CREATE_TIME,ST.STORE_NAME,SO.STORE_SALES_ORDER_NUM,SO.TOTAL_MONEY,SO.DISCOUNT_MONEY,SO.TYPE,SO.PAY_MONEY from (select STORE_SALES_ORDER_ID,DISCOUNT_ACTIVITY_ID from  DISCOUNT_ORDER_PARAM o group by STORE_SALES_ORDER_ID,DISCOUNT_ACTIVITY_ID) S join STORE_SALES_ORDER SO on S.STORE_SALES_ORDER_ID = SO.STORE_SALES_ORDER_ID "
				+ " join STORE ST on SO.STORE_ID = ST.STORE_ID ";
	  String where = "DISCOUNT_ACTIVITY_ID=" +activityID;
		return BizUtil.queryListBySQL(sql, where, " SO.STORE_SALES_ORDER_ID ",
				dbFieldList, queryData, this.userACL);
	}

	public PageResult queryStoreList(QueryPageData queryData, Long activityID,
			List<DbField> dbFieldList) {
		String sql = "select S.*,SO.STORE_NAME from STORE_ACTIVITY S join STORE SO on S.STORE_ID = SO.STORE_ID ";
		String where = "DISCOUNT_ACTIVITY_ID=" +activityID;
		return BizUtil.queryListBySQL(sql, where, " S.STORE_ACTIVITY_ID ",
				dbFieldList, queryData, this.userACL);
	}

	public PageResult queryParamList(QueryPageData queryData, Long activityID,List<DbField> dbFieldList) {
		String sql = "select * from DISCOUNT_PARAM ";
		String where = "DISCOUNT_ACTIVITY_ID ="+ activityID;
		return BizUtil.queryListBySQL(sql, where, " DISCOUNT_PARAM_ID  ",dbFieldList, queryData, this.userACL);
	}

	public void addActivity(Map<String, Object> detailMap, Long opId) {
		try {
			DbUtils.begin();

			detailMap.put("CHECK_STATE", 1);
			detailMap.put("ORDER_COUNT", 0);
			detailMap.put("ORDER_AMOUNT", 0);
			detailMap.put("CREATOR_ID", opId);
			detailMap.put("STATE", 1L);
			detailMap.put("MIN_MONEY", -1);
			detailMap.put("MIN_COUNT", -1);
			Map<String, String> funcMap = new HashMap<String, String>();

			String addFiled = "NAME,STATE,START_TIME,END_TIME,CREATE_TIME,CREATOR_ID,NOTE,ORDER_COUNT,ORDER_AMOUNT,CONTENT,TYPE,COMMODITY_RANGE_TYPE";
			funcMap.put("CREATE_TIME", "now()");
			funcMap.put("START_TIME", "DATE_FORMAT(?,'%Y-%m-%d %H:%i:%s')");
			funcMap.put("END_TIME", "DATE_FORMAT(?,'%Y-%m-%d %H:%i:%s')");
			detailMap.put("OBJECT_NAME", detailMap.get("NAME"));
			DbUtils.add("DISCOUNT_ACTIVITY", detailMap, funcMap, addFiled);
			DbUtils.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException("保存活动时异常");
		} finally {
			DbUtils.rollback();
		}
	}

	public void updateActivity(Map<String, Object> detailMap, Long opId) {
		try {
			DbUtils.begin();
			Map<String, String> funcMap = new HashMap<String, String>();
			funcMap.put("START_TIME", "DATE_FORMAT(?,'%Y-%m-%d %H:%i:%s')");
			funcMap.put("END_TIME", "DATE_FORMAT(?,'%Y-%m-%d %H:%i:%s')");
			String addFiled = "NAME,START_TIME,END_TIME,NOTE,CONTENT,TYPE,COMMODITY_RANGE_TYPE";
			DbUtils.update("DISCOUNT_ACTIVITY", detailMap, funcMap, addFiled,"DISCOUNT_ACTIVITY_ID");
			DbUtils.commit();
		} catch (BizException e) {
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new BizException("保存异常");
		} finally {
			DbUtils.rollback();
		}
	}

	public Map<String, Object> queryActivityById(Long id) {
		return DbUtils.queryMap(DbUtils.getConnection(),"SELECT * FROM DISCOUNT_ACTIVITY   WHERE DISCOUNT_ACTIVITY_ID=?",id);
	}

	public boolean editActivityParam(Map<String, Object> detailMap) {
		try {
			
			
			Long activityParamId = (Long) detailMap.get("DISCOUNT_PARAM_ID");
			String addFiled = "DISCOUNT_PARAM_ID,PARAM_NAME,DISCOUNT_ACTIVITY_ID,TARGET_NAME,TARGET_ID,VALUE,ADD_MONEY,ADD_SCORE,STATE,COMMODITY_AMOUNT,DISCOUNT_TYPE,MIN_MONEY,MIN_COUNT,NOTE";
			if (activityParamId == null || activityParamId.equals(0L)) {// 新增
				DbUtils.add("DISCOUNT_PARAM", detailMap, null, addFiled);	
			} else {// 编辑
				DbUtils.update("DISCOUNT_PARAM", detailMap, null, addFiled,"DISCOUNT_PARAM_ID");
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public Map<String, Object> queryStoreDetail(Long activityId,
			List<DbField> dbFieldList) throws BizException {
		try {
			Long idv = activityId;
			return DbUtils.queryMap(dbFieldList,"SELECT d.* FROM STORE_ACTIVITY d WHERE DISCOUNT_ACTIVITY_ID=?",idv);

		} catch (BizException e) {
			throw e;
		} catch (Exception ex) {
			throw new BizException(BizException.SYSTEM, "执行查询失败", ex);
		}
	}
	public boolean editActivityStore(Map<String, Object> detailMap) {
		try {
			Long activityParamId = (Long) detailMap.get("STORE_ACTIVITY_ID");
			String addFiled = "STORE_ACTIVITY_ID,STORE_ID,DISCOUNT_ACTIVITY_ID,NOTE";
			if (activityParamId == null || activityParamId.equals(0L)) {// 新增
				DbUtils.add("STORE_ACTIVITY", detailMap, null, addFiled);
			} else {// 编辑
				DbUtils.update("STORE_ACTIVITY", detailMap, null, addFiled,"STORE_ACTIVITY_ID");
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
