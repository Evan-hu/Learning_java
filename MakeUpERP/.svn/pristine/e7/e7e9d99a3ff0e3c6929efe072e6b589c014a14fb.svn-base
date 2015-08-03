package com.ga.erp.biz.member.viprule;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;

public class VipRuleListView extends ListView {

	public VipRuleListView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap, GaConstant.EDITMODE_DISP);
	}

	@Override
	public void initField() throws Exception {

		List<DbField> dbFields = new ArrayList<DbField>();

		DbField field = new DbField("ID", "VIP_RULE_ID", GaConstant.DT_LONG);
		field.setColumnDisplay(false, 0, true);
		dbFields.add(field);

		field = new DbField("会员等级级别", "VIP_LV", GaConstant.DT_INT);
		dbFields.add(field);

		field = new DbField("等级名称", "VIP_NAME", GaConstant.DT_STRING);
		dbFields.add(field);

		field = new DbField("期间累计消费下限", "MIN_AMOUNT", GaConstant.DT_MONEY);
		field.setInputCustomStyle("width:50px;");
		dbFields.add(field);

		field = new DbField("期间累计消费上限", "MAX_AMOUNT", GaConstant.DT_MONEY);
		field.setInputCustomStyle("width:50px;");
		dbFields.add(field);

		field = new DbField("折扣率", "RATE", GaConstant.DT_INT);
		dbFields.add(field);

		this.fieldList = dbFields;
	}

}
