package com.ga.erp.biz.member.viprule;

import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.UserACL;
import com.ga.click.page.BizUtil;
import com.ga.click.page.QueryPageData;
import com.ga.erp.biz.ACLBiz;

public class VipRuleBiz extends ACLBiz {

	public VipRuleBiz(UserACL userACL) {
		super(userACL);
	}

	public String publicSql = "select * from VIP_RULE";

	public PageResult queryVipRuleList(QueryPageData pageData,
			List<DbField> fieldList) {
		return BizUtil.queryListBySQL(publicSql, "", "VIP_LV desc", fieldList,
				pageData, this.userACL);
	}

	public Map<String, Object> queryVipRuleDetail(Long id,
			List<DbField> fieldList) throws BizException {
		return DbUtils.queryMap(fieldList,
				publicSql + " where VIP_RULE_ID = ?", id);
	}

	private void chkValue(Map<String, Object> valueMap) {
		StringBuffer buffer = new StringBuffer("");
		int count = 1;
		if ((valueMap.get("MIN_AMOUNT") + "").equals("0")) {
			buffer.append("<br>" + count + "，请输入消费下限；");
			count++;
		}
		if ((valueMap.get("MAX_AMOUNT") + "").equals("0")) {
			buffer.append("<br>" + count + "，请输入消费上限；");
			count++;
		}
		if (buffer.length() > 0) {
			throw new BizException(buffer.toString());
		}
	}

	public void saveVipRule(Map<String, Object> valueMap, Boolean isAdd) {
		try {
			chkValue(valueMap);
			DbUtils.begin();
			if (isAdd) {
				DbUtils.add("VIP_RULE", valueMap, null);
			} else {
				DbUtils.update("VIP_RULE", valueMap, null, "VIP_RULE_ID");
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
