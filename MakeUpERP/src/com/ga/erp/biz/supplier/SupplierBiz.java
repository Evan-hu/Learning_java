package com.ga.erp.biz.supplier;

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

public class SupplierBiz extends ACLBiz {

  public SupplierBiz(UserACL userACL) {
    super(userACL);
  }

	public PageResult querySupplierList(QueryPageData pageData,List<DbField> dbFieldList,Long areaID, String type) {
		// type为1时表示供应商，2表示大客户
	  String sql = "SELECT s.* FROM SUPPLIER s";
	  String where = " s.DELETE_STATE = 1 " + (areaID == null ? "" : " AND s.AREA_ID = " + areaID);
	  where = (!GaUtil.isNullStr(type)) && "2".equals(type) ? where + " AND s.TYPE = 2 " : where + " AND s.TYPE = 1 ";
		return BizUtil.queryListBySQL(sql, where, "s.SUPPLIER_ID desc",
				dbFieldList, pageData, this.userACL);
	}

	public Map<String, Object> querySupplierMap(Long supplierID) {
		String sql = "SELECT s.*, a.AREA_NAME AREA_NAME FROM SUPPLIER s " +
				"JOIN AREA a ON a.AREA_ID = s.AREA_ID WHERE s.SUPPLIER_ID = ?";
		Map<String, Object> valuesMap = DbUtils.queryMap(
				DbUtils.getConnection(), sql, supplierID);
		return valuesMap;
	}

	 public PageResult querySuppList(QueryPageData queryPageData,List<DbField> dbFieldList,Long commId) {
	    String sql = "SELECT s.* FROM SUPPLIER s join SUPPLIER_COMMODITY sc on s.SUPPLIER_ID=sc.SUPPLIER_ID";
	    return BizUtil.queryListBySQL(sql, "where sc.COMMODITY_ID=? and s.DELETE_STATE = 1 " + commId, "s.SUPPLIER_ID desc",dbFieldList, queryPageData, this.userACL);
	  }
	
	 public List<Map<String, Object>> querySupListMap(List<DbField> fieldList, Long commId) {
	    String sql = "SELECT s.* FROM SUPPLIER s join SUPPLIER_COMMODITY sc on s.SUPPLIER_ID=sc.SUPPLIER_ID";
	    return DbUtils.queryMapList(fieldList, sql + " where sc.COMMODITY_ID = ?  and s.DELETE_STATE = 1", commId);
	  }
	 
	private void chkValue(Map<String, Object> valuesMap) {
		if (GaUtil.isNullStr(valuesMap.get("SUPPLIER_NAME") + "")) {
			throw new BizException("请输入账号");
		}
		if (GaUtil.isNullStr(valuesMap.get("LINK_USER") + "")) {
			throw new BizException("请输入联系人");
		}
		if (GaUtil.isNullStr(valuesMap.get("LINK_TEL") + "")) {
			throw new BizException("请输入联系电话");
		}
		if (GaUtil.isNullStr(valuesMap.get("TAX") + "")) {
			throw new BizException("请输入税务登记证件号");
		}
		if (GaUtil.isNullStr(valuesMap.get("LICENSE_NO") + "")) {
			throw new BizException("请输入营业执照号");
		}
		if (GaUtil.isNullStr(valuesMap.get("ADDR") + "")) {
			throw new BizException("请输入地址");
		}
		if (GaUtil.isNullStr(valuesMap.get("AREA_ID") + "")) {
			throw new BizException("请选择地区");
		}
	}

	public void saveNewSupplier(Map<String, Object> valuesMap) {
		if (isExists(valuesMap.get("SUPPLIER_NAME") + "")) {
			throw new BizException("账号已存在");
		}
		Map<String, String> funcMap = new HashMap<String, String>();
		funcMap.put("CREATE_TIME", "NOW()");
		valuesMap.put("STATE", 1);
		valuesMap.put("LEVEL", 1);
		valuesMap.put("CREATOR_ID", this.userACL.getUserID());
		DbUtils.add("SUPPLIER", valuesMap, funcMap, "TAX,STATE,ZIP_CODE,SUPPLIER_NAME,LINK_USER,CREATE_TIME,LEVEL,NOTE,AREA_ID,LINK_TEL,BANK,FOX,QQ,LICENSE_NO,EMAIL,ADDR,TYPE,CREATOR_ID,CHECK_TYPE,CHECK_CYCLE,CHECK_MONTH");
	}

	public void saveSupplier(Map<String, Object> valuesMap, String type, boolean isAdd) {
		try {
			this.chkValue(valuesMap);
			valuesMap.put("TYPE", type);
			DbUtils.begin();
			if (isAdd) {
				this.saveNewSupplier(valuesMap);
			} else {
				DbUtils.update("SUPPLIER", valuesMap, null, "LEVEL,SUPPLIER_NAME,LINK_USER,LINK_TEL,QQ,FOX,TAX,EMAIL,LICENSE_NO,ZIP_CODE,BANK,ADDR,NOTE,AREA_ID,CHECK_TYPE,CHECK_CYCLE,CHECK_MONTH", "SUPPLIER_ID");
			}
			DbUtils.commit();
		} catch (BizException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BizException(BizException.SYSTEM, "保存供应商异常");
		} finally {
			DbUtils.rollback();
		}
	}

	public Boolean isExists(String userName) {
		String sql = "select * from SUPPLIER where SUPPLIER_NAME = ?";
		return DbUtils.queryMap(DbUtils.getConnection(), sql, userName) != null;
	}

	public List<Map<String, Object>> querySupplierOpList(Long supplierId){
    return DbUtils.queryMapList(DbUtils.getConnection(), "select * from OP_SUPPLIER os JOIN OP o " +
                                "on os.OP_ID = o.OP_ID where os.SUPPLIER_ID = ?", supplierId);
  }
	
}
