package com.ga.erp.biz.system.audits.role;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;

public class AuditsRoleListView extends ListView {

	public AuditsRoleListView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap, GaConstant.EDITMODE_DISP);
	}

	@Override
	public void initField() throws Exception {

		List<DbField> dbFields = new ArrayList<DbField>();

		DbField field = new DbField("ID", "AUDITING_ROLE_ID",
				GaConstant.DT_LONG);
		field.setColumnDisplay(false, 0, true);
		field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "AUDITING_ROLE_ID");
		dbFields.add(field);

		field = new DbField("ҵ������", "SYS_CODE_NAME", GaConstant.DT_STRING);
		dbFields.add(field);

		field = new DbField("��˲���", "STEP", GaConstant.DT_INT);
		dbFields.add(field);
		
		field = new DbField("��ɫ����", "ROLE_NAME", GaConstant.DT_STRING);
		dbFields.add(field);
		
		field = new DbField("˵��", "NOTE", GaConstant.DT_STRING);
		field.setInput(GaConstant.INPUT_TEXTAREA);
		field.addInputAttributeMap("rows", "5");
		field.addInputAttributeMap("cols", "60");
		dbFields.add(field);

		this.fieldList = dbFields;
	}

}
