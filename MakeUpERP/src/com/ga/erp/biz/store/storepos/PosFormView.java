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

		field = new DbField("POS机编号", "POS_NUM", GaConstant.DT_STRING);
		field.setColumnDisplay(true, 80, false);
		field.getFieldVerify().setRequire(true);
		dbFields.add(field);
		
		field = new DbField("门店ID", "STORE_ID", GaConstant.DT_LONG);
		field.setPopSelect("STORESELECT", "STORE_ID", false);
		dbFields.add(field);

		field = new DbField("门店名", "STORE_NAME", GaConstant.DT_STRING);
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

		field = new DbField("计算机名", "NAME", GaConstant.DT_STRING);
		field.setInputCustomStyle("width:135px");
		field.setColumnDisplay(true, 80, false);
		dbFields.add(field);

		field = new DbField("MAC地址", "MAC_ADDR", GaConstant.DT_STRING);
		dbFields.add(field);

		field = new DbField("唯一识别码", "ID_CODE", GaConstant.DT_STRING);
		dbFields.add(field);

		field = new DbField("是否在线", "LOGIN_STATE", GaConstant.DT_INT);
		field.setInput(GaConstant.INPUT_SELECT);
		field.setLookupData(SystemUtil.getYesNoMap());
		field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);// 如果是新建则隐藏
		field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);// 如果是编辑，则只读
		dbFields.add(field);

		field = new DbField("状态", "STATE", GaConstant.DT_INT);
		field.setInput(GaConstant.INPUT_SELECT);
		field.setLookupData(SystemUtil.getStatusMap());
		field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
		dbFields.add(field);

		field = new DbField("累计销售金额", "ALL_CONSUME_MONEY", GaConstant.DT_MONEY);
		field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
		field.setInputCustomStyle("width:80px;");
		dbFields.add(field);

		field = new DbField("累计销售单数", "ALL_CONSUME_ORDER", GaConstant.DT_INT);
		field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
		field.setInputCustomStyle("width:80px;");
		dbFields.add(field);

		field = new DbField("累计退货金额", "ALL_RETURN_MONEY", GaConstant.DT_MONEY);
		field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
		field.setInputCustomStyle("width:80px;");
		dbFields.add(field);

		field = new DbField("累计退货单数", "ALL_RETURN_ORDER", GaConstant.DT_INT);
		field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
		field.setInputCustomStyle("width:80px;");
		dbFields.add(field);

		field = new DbField("备注", "NOTE", GaConstant.DT_STRING);
		field.setInput(GaConstant.INPUT_TEXTAREA);
		field.addInputAttributeMap("rows", "5");
		field.addInputAttributeMap("cols", "60");
		dbFields.add(field);

		this.fieldList = dbFields;
	}
}
