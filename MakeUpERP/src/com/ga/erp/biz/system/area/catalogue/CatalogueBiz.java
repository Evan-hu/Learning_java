package com.ga.erp.biz.system.area.catalogue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.UserACL;
import com.ga.click.page.BizUtil;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.ACLBiz;

public class CatalogueBiz extends ACLBiz {

  public CatalogueBiz(UserACL userACL) {
    super(userACL);
  }

	public PageResult queryCatalogueList(Long aeraID, List<DbField> dbFieldList) {
		String sql = "SELECT C.*, A.AREA_ID,A.AREA_NAME, O.USERNAME FROM CATALOGUE  C" +
				" JOIN AREA A ON A.AREA_ID = C.AREA_ID" +
				" JOIN OP O ON O.OP_ID = C.OP_ID";
		return BizUtil.queryListBySQL(sql, "C.AREA_ID = " + aeraID, "C.CATALOGUE_ID DESC", dbFieldList, null, this.userACL);
	}

	public Map<String, Object> querySupplierMap(Long supplierCommodityID) {
		String sql = "SELECT C.*, a.AREA_ID,A.AREA_NAME,O.USERNAME FROM CATALOGUE  C JOIN AREA A ON A.AREA_ID = C.AREA_ID  JOIN OP O ON O.OP_ID = C.OP_ID WHERE C.CATALOGUE_ID = ?";
		Map<String, Object> valuesMap = DbUtils.queryMap(DbUtils.getConnection(), sql, supplierCommodityID);
		return valuesMap;
	}

	private void chkValue(Map<String, Object> valuesMap) {
		if (GaUtil.isNullStr(valuesMap.get("NAME") + "")) {
			throw new BizException("请输入采购目录");
		}
	}
	
	public void saveNewCatalogue(Map<String, Object> valuesMap) {
		
		Map<String, String> funcMap = new HashMap<String, String>();
		funcMap.put("CREATE_TIME", "NOW()");
		valuesMap.put("OP_ID", this.userACL.getUserID());
		DbUtils.add("CATALOGUE", valuesMap, funcMap, "NAME,CREATE_TIME,AREA_ID,NOTE,OP_ID");
	}
	
	public void saveCatalogue(Map<String, Object> valuesMap, boolean isAdd) {
		try {			
			this.chkValue(valuesMap);
			DbUtils.begin();
			if (isAdd) {
				this.saveNewCatalogue(valuesMap);
			} else {
					DbUtils.update("CATALOGUE", valuesMap, null, "NAME,NOTE", "CATALOGUE_ID");	
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
