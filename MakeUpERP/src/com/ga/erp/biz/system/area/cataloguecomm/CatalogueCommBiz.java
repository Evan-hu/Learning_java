package com.ga.erp.biz.system.area.cataloguecomm;

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

public class CatalogueCommBiz extends ACLBiz {

  public CatalogueCommBiz(UserACL userACL) {
    super(userACL);
  }

	public PageResult queryCatalogueCommList(QueryPageData queryPageData, List<DbField> dbFieldList, Long catalogueId) {
		String where = catalogueId == null ? "" : "CA.CATALOGUE_ID = " + catalogueId;
		String sql = "SELECT CC.*, C.COMMODITY_NAME, CA.NAME   FROM CATALOGUE_COMM  CC " +
					 "JOIN COMMODITY C ON C.COMMODITY_ID = CC.COMMODITY_ID " +
					 "JOIN CATALOGUE CA  ON CA.CATALOGUE_ID = CC.CATALOGUE_ID";
		return BizUtil.queryListBySQL(sql, where, "CC.CATALOGUE_COMM_ID DESC", dbFieldList, queryPageData, this.userACL);
	}

	public Map<String, Object> queryCatalogueCommMap(List<DbField> dbFieldList, Long catalogueCommID) {
		String sql = "SELECT CC.*, C.COMMODITY_NAME, CA.NAME   FROM CATALOGUE_COMM  CC " +
					 "JOIN COMMODITY C ON C.COMMODITY_ID = CC.COMMODITY_ID " +
					 "JOIN CATALOGUE CA  ON CA.CATALOGUE_ID = CC.CATALOGUE_ID WHERE CC.CATALOGUE_COMM_ID = ? ";
		Map<String, Object> valuesMap = DbUtils.queryMap(dbFieldList, sql, catalogueCommID);
		return valuesMap;
	}

	private void chkValue(Map<String, Object> valuesMap) {
		if (GaUtil.isNullStr(valuesMap.get("COMMODITY_ID") + "")) {
			throw new BizException("请选择商品");
		}
		if (GaUtil.isNullStr(valuesMap.get("PURCHASE_PRICE") + "") || (valuesMap.get("PURCHASE_PRICE") + "").equals("0")) {
			throw new BizException("请输入采购价");
		}
		if (GaUtil.isNullStr(valuesMap.get("RETAIL_PRICE") + "") || (valuesMap.get("RETAIL_PRICE") + "").equals("0")) {
			throw new BizException("请输入售价");
		}
		if (GaUtil.isNullStr(valuesMap.get("MEMBER_PRICE") + "") || (valuesMap.get("MEMBER_PRICE") + "").equals("0")) {
			throw new BizException("请输入会员价");
		}
	}

	public void saveNewCatalogueComm(Map<String, Object> valuesMap) {
		try {
			DbUtils.add("CATALOGUE_COMM", valuesMap, null, "CATALOGUE_ID,COMMODITY_ID,PURCHASE_PRICE,RETAIL_PRICE,MEMBER_PRICE");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveCatalogueComm(Map<String, Object> valuesMap, boolean isAdd) {
		try {			
			this.chkValue(valuesMap);
			DbUtils.begin();
			if (isAdd) {
				this.saveNewCatalogueComm(valuesMap);
			} else {
					DbUtils.update("CATALOGUE_COMM", valuesMap, null, "COMMODITY_ID,PURCHASE_PRICE,RETAIL_PRICE,MEMBER_PRICE", "CATALOGUE_COMM_ID");
			}
			DbUtils.commit();
		} catch (BizException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BizException(BizException.SYSTEM, "保存目录商品异常");
		} finally {
			DbUtils.rollback();
		}
	}
}
