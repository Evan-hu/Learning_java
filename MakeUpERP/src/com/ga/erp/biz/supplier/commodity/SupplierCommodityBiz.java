package com.ga.erp.biz.supplier.commodity;

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

public class SupplierCommodityBiz extends ACLBiz {

  public SupplierCommodityBiz(UserACL userACL) {
    super(userACL);
  }

  public String publicSql = "SELECT SC.*, S.SUPPLIER_NAME, C.COMMODITY_NAME FROM SUPPLIER_COMMODITY SC " +
                            "JOIN SUPPLIER S ON  S.SUPPLIER_ID  = SC.SUPPLIER_ID " +
                            "JOIN COMMODITY C ON C.COMMODITY_ID = SC.COMMODITY_ID";
	public PageResult querySupplierCommList(QueryPageData queryPageData, List<DbField> dbFieldList, Long supplierId) {
		String where = " S.DELETE_STATE = 1 " + (supplierId == null ? "" : " AND S.SUPPLIER_ID = " + supplierId);
		return BizUtil.queryListBySQL(publicSql, where, "SC.SUPPLIER_COMMODITY_ID DESC", dbFieldList, queryPageData, this.userACL);
	}

	public Map<String, Object> querySupplierMap(Long supplierCommodityID) {
		Map<String, Object> valuesMap = DbUtils.queryMap(DbUtils.getConnection(), publicSql + " where  SC.SUPPLIER_COMMODITY_ID = ?", supplierCommodityID);
		return valuesMap;
	}

	private void chkValue(Map<String, Object> valuesMap) {
		if (GaUtil.isNullStr(valuesMap.get("COMMODITY_ID") + "")) {
			throw new BizException("请选择商品");
		}
		if (GaUtil.isNullStr(valuesMap.get("SUPPLY_PRICE") + "") || (valuesMap.get("SUPPLY_PRICE") + "").equals("0")) {
			throw new BizException("请输入供货价");
		}
		if (GaUtil.isNullStr(valuesMap.get("MAX_SUPPLY_PRICE") + "") || (valuesMap.get("MAX_SUPPLY_PRICE") + "").equals("0")) {
			throw new BizException("请输入最高供货价");
		}
		if (GaUtil.isNullStr(valuesMap.get("MIN_SUPPLY_PRICE") + "") || (valuesMap.get("MIN_SUPPLY_PRICE") + "").equals("0")) {
			throw new BizException("请输入最低供货价");
		}
		if (GaUtil.isNullStr(valuesMap.get("RECENTLYy_SUPPLY_PRICE") + "") || (valuesMap.get("RECENTLYy_SUPPLY_PRICE") + "").equals("0")) {
			throw new BizException("请输入最近供货价");
		}
		if (GaUtil.isNullStr(valuesMap.get("COST_PRICE") + "") || (valuesMap.get("COST_PRICE") + "").equals("0")) {
			throw new BizException("请输入成本价");
		}
	}

	public void saveNewSupplierCommodity(Map<String, Object> valuesMap) {
		DbUtils.add("SUPPLIER_COMMODITY", valuesMap, null, "MAX_SUPPLY_PRICE,SUPPLIER_COMMODITY_ID,STATE,SUPPLY_PRICE,RECENTLYy_SUPPLY_PRICE,COMMODITY_ID,RANK_NUM,COST_PRICE,MIN_SUPPLY_PRICE,SUPPLIER_ID");
	}

	public void saveSupplierCommodity(Map<String, Object> valuesMap, boolean isAdd) {
		try {			
			this.chkValue(valuesMap);
			DbUtils.begin();
			if (isAdd) {
				this.saveNewSupplierCommodity(valuesMap);
			} else {
					DbUtils.update("SUPPLIER_COMMODITY", valuesMap, null, "MAX_SUPPLY_PRICE,SUPPLY_PRICE,RECENTLYy_SUPPLY_PRICE,COMMODITY_ID,COST_PRICE,MIN_SUPPLY_PRICE,RANK_NUM,SUPPLIER_ID,STATE", "SUPPLIER_COMMODITY_ID");	
			}
			DbUtils.commit();
		} catch (BizException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BizException(BizException.SYSTEM, "保存供应商商品异常");
		} finally {
			DbUtils.rollback();
		}
	}

	public Boolean isExists(String userName) {
		String sql = "select * from SUPPLIER where SUPPLIER_NAME = ?";
		return DbUtils.queryMap(DbUtils.getConnection(), sql, userName) != null;
	}
}
