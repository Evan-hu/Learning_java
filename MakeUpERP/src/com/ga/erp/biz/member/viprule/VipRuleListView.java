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

		field = new DbField("��Ա�ȼ�����", "VIP_LV", GaConstant.DT_INT);
		dbFields.add(field);

		field = new DbField("�ȼ�����", "VIP_NAME", GaConstant.DT_STRING);
		dbFields.add(field);

		field = new DbField("�ڼ��ۼ���������", "MIN_AMOUNT", GaConstant.DT_MONEY);
		field.setInputCustomStyle("width:50px;");
		dbFields.add(field);

		field = new DbField("�ڼ��ۼ���������", "MAX_AMOUNT", GaConstant.DT_MONEY);
		field.setInputCustomStyle("width:50px;");
		dbFields.add(field);

		field = new DbField("�ۿ���", "RATE", GaConstant.DT_INT);
		dbFields.add(field);

		this.fieldList = dbFields;
	}

}
