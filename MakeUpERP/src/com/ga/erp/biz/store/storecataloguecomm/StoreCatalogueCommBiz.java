package com.ga.erp.biz.store.storecataloguecomm;

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

public class StoreCatalogueCommBiz extends ACLBiz {

  public StoreCatalogueCommBiz(UserACL userACL) {
    super(userACL);
  }
public String publicSql = "SELECT  SCC.* ,C.COMMODITY_NAME,S.STORE_NAME  FROM  STORE_CATALOGUE_COMM SCC  " +
		"JOIN COMMODITY C ON C.COMMODITY_ID = SCC.COMMODITY_ID  JOIN STORE S ON S.STORE_ID = SCC.STORE_ID";
	
public PageResult queryStoreTypeList(QueryPageData queryPageData, List<DbField> dbFieldList) {
		return BizUtil.queryListBySQL(publicSql, "SCC.STORE_ID = " + 1 + " ", "SCC.STORE_CATALOGUE_COMM_ID DESC", dbFieldList, queryPageData, this.userACL);
	}

	public Map<String, Object> queryStoreCatalogueCommMap(Long id, List<DbField> dbFieldList) {
 		return DbUtils.queryMap(dbFieldList, publicSql + " WHERE SCC.STORE_CATALOGUE_COMM_ID = ?", id);
	}
  
	private void chkValue(Map<String, Object> valuesMap) {
		if (GaUtil.isNullStr(valuesMap.get("COMMODITY_ID") + "")) {
			throw new BizException("请选择商品");
		}
		if (GaUtil.isNullStr(valuesMap.get("PURCHASE_PRICE") + "") || (valuesMap.get("PURCHASE_PRICE") + "").equals("0")) {
			throw new BizException("请输入采购价");
		}
		if (GaUtil.isNullStr(valuesMap.get("RETAIL_PRICE") + "") || (valuesMap.get("RETAIL_PRICE") + "").equals("0")) {
			throw new BizException("请输入零售价");
		}
		if (GaUtil.isNullStr(valuesMap.get("MEMBER_PRICE") + "") || (valuesMap.get("MEMBER_PRICE") + "").equals("0")) {
			throw new BizException("请输入会员价");
		}
	}

	/**
	 * 在登录做完了过后，这里的的store_ID要修改成当前门店用户ID
	 * @param valuesMap
	 */
	public void saveNewStoreCatalogueComm(Map<String, Object> valuesMap) {
//		valuesMap.put("STORE_ID", this.userACL.getStoreID());
		valuesMap.put("STORE_ID", 1);
		DbUtils.add("STORE_CATALOGUE_COMM", valuesMap, null, "PURCHASE_PRICE,MEMBER_PRICE,RETAIL_PRICE,COMMODITY_ID,STORE_ID");
	}

	public void saveStoreCatalogueComm(Map<String, Object> valuesMap, boolean isAdd) {
		try {		
			this.chkValue(valuesMap);
			DbUtils.begin();
			if (isAdd) {
				this.saveNewStoreCatalogueComm(valuesMap);
			} else {
				DbUtils.update("STORE_CATALOGUE_COMM", valuesMap, null, "PURCHASE_PRICE,MEMBER_PRICE,RETAIL_PRICE,COMMODITY_ID", "STORE_CATALOGUE_COMM_ID");
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
}
