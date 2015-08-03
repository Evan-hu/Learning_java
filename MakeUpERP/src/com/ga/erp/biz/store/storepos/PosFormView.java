package com.ga.erp.biz.store.storepos;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.util.SystemUtil;

public class PosFormView extends FormView {

	public PosFormView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap);
	}

	@Override
	public void initField() throws Exception {

		List<DbField> dbFields = new ArrayList<DbField>();

		DbField field = new DbField("ID", "POS_ID", GaConstant.DT_LONG);
		field.setInput(GaConstant.INPUT_HIDDEN);
		field.setPK(true);
		field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "POS_ID");
		dbFields.add(field);

		field = new DbField("POS�����", "POS_NUM", GaConstant.DT_STRING);
		field.setColumnDisplay(true, 80, false);
		field.getFieldVerify().setRequire(true);
		dbFields.add(field);
		
		field = new DbField("�ŵ�ID", "STORE_ID", GaConstant.DT_LONG);
		field.setPopSelect("STORESELECT", "STORE_ID", false);
		dbFields.add(field);

		field = new DbField("�ŵ���", "STORE_NAME", GaConstant.DT_STRING);
		if (this.viewEditMode == GaConstant.EDITMODE_NEW) {
			field.setPopSelect("STORESELECT", "STORE_NAME", true,
					"/store/store_main.htm",
					"STORE_ID,STORE_NAME,cid_storeList", 1200, 480);
		} else if (this.viewEditMode == GaConstant.EDITMODE_EDIT) {
			field.setInputMode(GaConstant.EDITMODE_EDIT,
					GaConstant.INPUTMODE_READONLY);
		}
		field.setColumnDisplay(true, 80, false);
		dbFields.add(field);

		field = new DbField("�������", "NAME", GaConstant.DT_STRING);
		field.setInputCustomStyle("width:135px");
		field.setColumnDisplay(true, 80, false);
		dbFields.add(field);

		field = new DbField("MAC��ַ", "MAC_ADDR", GaConstant.DT_STRING);
		dbFields.add(field);

		field = new DbField("Ψһʶ����", "ID_CODE", GaConstant.DT_STRING);
		dbFields.add(field);

		field = new DbField("�Ƿ�����", "LOGIN_STATE", GaConstant.DT_INT);
		field.setInput(GaConstant.INPUT_SELECT);
		field.setLookupData(SystemUtil.getYesNoMap());
		field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);// ������½�������
		field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);// ����Ǳ༭����ֻ��
		dbFields.add(field);

		field = new DbField("״̬", "STATE", GaConstant.DT_INT);
		field.setInput(GaConstant.INPUT_SELECT);
		field.setLookupData(SystemUtil.getStatusMap());
		field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
		dbFields.add(field);

		field = new DbField("�ۼ����۽��", "ALL_CONSUME_MONEY", GaConstant.DT_MONEY);
		field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
		field.setInputCustomStyle("width:80px;");
		dbFields.add(field);

		field = new DbField("�ۼ����۵���", "ALL_CONSUME_ORDER", GaConstant.DT_INT);
		field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
		field.setInputCustomStyle("width:80px;");
		dbFields.add(field);

		field = new DbField("�ۼ��˻����", "ALL_RETURN_MONEY", GaConstant.DT_MONEY);
		field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
		field.setInputCustomStyle("width:80px;");
		dbFields.add(field);

		field = new DbField("�ۼ��˻�����", "ALL_RETURN_ORDER", GaConstant.DT_INT);
		field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
		field.setInputCustomStyle("width:80px;");
		dbFields.add(field);

		field = new DbField("��ע", "NOTE", GaConstant.DT_STRING);
		field.setInput(GaConstant.INPUT_TEXTAREA);
		field.addInputAttributeMap("rows", "5");
		field.addInputAttributeMap("cols", "60");
		dbFields.add(field);

		this.fieldList = dbFields;
	}
}
