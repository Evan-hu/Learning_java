package com.ga.erp.biz.store.storepos;

import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.UserACL;
import com.ga.click.page.AutoSQLUtil;
import com.ga.click.page.BizUtil;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.PagedQuery;
import com.ga.erp.biz.ACLBiz;

public class PosBiz extends ACLBiz {

	public PosBiz(UserACL userACL) {
		super(userACL);
	}

	public String publicSql = "select p.*,s.STORE_NAME from POS p JOIN STORE s on p.STORE_ID = s.STORE_ID";

	public PageResult queryPosList(Long storeID,QueryPageData pageData,List<DbField> fieldList, StringBuilder sb) {
	  String where = storeID == null ? "" : "p.STORE_ID = " + storeID;
    PagedQuery query = AutoSQLUtil.createSql(publicSql,where, "s.STORE_ID desc", fieldList, pageData);
    List<Map<String, Object>> groupList;
	  try {
      groupList = query.queryOther("select round((sum(ALL_CONSUME_MONEY)/100),2) ACM,sum(ALL_CONSUME_ORDER) ACO,round((sum(ALL_RETURN_MONEY)/100),2) ARM,sum(ALL_RETURN_ORDER) ARO from (#QUERY_SQL#) t ");
   
    if (groupList != null) {
      for(Map<String,Object> builder : groupList) {
        sb.append("销售总金额：【￥<b style='color:red;'>"+builder.get("ACM")+"</b>】," +
                  "销售总单数：【<b style='color:red;'>"+builder.get("ACO")+"</b>】," +
                  "退货总金额：【￥<b style='color:red;'>"+builder.get("ARM")+"</b>】," +
        					"退货总单数：【<b style='color:red;'>"+builder.get("ARO")+"</b>】");
      }
    }
    } catch (Exception e) {
      e.printStackTrace();
    }
		return BizUtil.queryListBySQL(publicSql, "", "p.POS_NUM",fieldList, pageData, this.userACL);
	}
	
	public List<Map<String, Object>> queryPosListMap(List<DbField> fieldList, Long storeId) {
		return DbUtils.queryMapList(fieldList, publicSql + " where s.STORE_ID = ?", storeId);
	}

	public Map<String, Object> queryPosDetail(Long id, List<DbField> fieldList)
			throws BizException {
		return DbUtils.queryMap(fieldList, publicSql + " where p.POS_ID = ?",id);
	}

	public void savePos(Map<String, Object> valueMap, Boolean isAdd) {
		try {
			String updateField = "NAME,POS_NUM,MAC_ADDR,ID_CODE,STATE,NOTE,STORE_ID";
			DbUtils.begin();
			if (isAdd) {
				valueMap.put("STATE", 1);
				valueMap.put("LOGIN_STATE", 0);
				valueMap.put("ALL_CONSUME_ORDER", 0);
				valueMap.put("ALL_RETURN_ORDER", 0);
				DbUtils.add(
						"POS",
						valueMap,
						null,
						updateField
								+ ",LOGIN_STATE,ALL_CONSUME_MONEY,ALL_CONSUME_ORDER,ALL_RETURN_MONEY,ALL_RETURN_ORDER");
			} else {
				DbUtils.update("POS", valueMap, null, updateField, "POS_ID");
			}
			DbUtils.commit();
		} catch (BizException e) {
			throw e;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new BizException(BizException.SYSTEM, "保存失败", ex);
		} finally {
		}
	}

}
