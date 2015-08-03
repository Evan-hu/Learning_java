package com.ga.erp.biz.member.viprule;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;

public class VipRuleFormView extends FormView {

	public VipRuleFormView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap);
	}

	@Override
	public void initField() throws Exception {

		List<DbField> dbFields = new ArrayList<DbField>();

		DbField field = new DbField("ID", "VIP_RULE_ID", GaConstant.DT_LONG);
		field.setInput(GaConstant.INPUT_HIDDEN);
		field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "VIP_RULE_ID");
		dbFields.add(field);

		field = new DbField("会员等级级别", "VIP_LV", GaConstant.DT_INT);
		field.setColumnDisplay(true, 80, false);
		dbFields.add(field);

		field = new DbField("等级名称", "VIP_NAME", GaConstant.DT_STRING);
		field.setColumnDisplay(true, 80, false);
		dbFields.add(field);

		field = new DbField("期间累计消费下限", "MIN_AMOUNT", GaConstant.DT_MONEY);
		field.setInputCustomStyle("width:100px");
		field.setTipInfo("  元");
		dbFields.add(field);

		field = new DbField("期间累计消费上限", "MAX_AMOUNT", GaConstant.DT_MONEY);
		field.setInputCustomStyle("width:100px");
		field.setTipInfo("  元");
		dbFields.add(field);

		field = new DbField("折扣率", "RATE", GaConstant.DT_INT);
		field.setInputCustomStyle("width:30px;");
		field.setTipInfo("  %");
		dbFields.add(field);

		this.fieldList = dbFields;
	}
}
