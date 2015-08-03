package com.ga.erp.biz.system.audits.role;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;

public class AuditsRoleFormView extends FormView {

	public AuditsRoleFormView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap);
	}

	@Override
	public void initField() throws Exception {

		List<DbField> dbFields = new ArrayList<DbField>();

		DbField field = new DbField("��˽�ɫID", "AUDITING_ROLE_ID", GaConstant.DT_LONG);
		field.setInput(GaConstant.INPUT_HIDDEN);
		field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "AUDITING_ROLE_ID");
		dbFields.add(field);

		field = new DbField("��ɫID","ROLE_ID",GaConstant.DT_INT);
    field.setPopSelect("NEWROLESELECT", "ROLE_ID", false);
    dbFields.add(field);
		
		field = new DbField("��ɫ����", "ROLE_NAME", GaConstant.DT_STRING);
    field.getFieldVerify().setRequire(true);
    field.setInputCustomStyle("width:180px");
		field.setPopSelect("NEWROLESELECT","ROLE_NAME",true,"/system/role_main.htm","ROLE_ID,ROLE_NAME,cid_roleList", 720,400);
		field.setColumnDisplay(true, 80, false);
		dbFields.add(field);

		
		field = new DbField("����ID","AUDITING_CONFIG_ID",GaConstant.DT_INT);
    field.setPopSelect("NEWCODESELECT1", "AUDITING_CONFIG_ID", false);
    dbFields.add(field);

    field = new DbField("ҵ������", "SYS_CODE_NAME", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_NEW,GaConstant.INPUTMODE_HIDDEN);
    field.setPopSelect("NEWCODESELECT1","SYS_CODE_NAME",true,"/system/syscode_sele.htm",
        "AUDITING_CONFIG_ID,SYS_CODE_NAME,cid_syscodeTree", 300,400);
    dbFields.add(field);

		field = new DbField("����", "STEP", GaConstant.DT_INT);
		field.setInputCustomStyle("width:100px");
		dbFields.add(field);

		field = new DbField("˵��", "NOTE", GaConstant.DT_STRING);
    field.setInput(GaConstant.INPUT_TEXTAREA);
    field.addInputAttributeMap("rows", "5");
    field.addInputAttributeMap("cols", "60");
		dbFields.add(field);

		this.fieldList = dbFields;
	}
}
