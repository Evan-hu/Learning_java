package com.ga.erp.biz.system.area.catalogue;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;

public class CatalogueFormView extends FormView {

	public CatalogueFormView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap);
	}

	@Override
	public void initField() throws Exception {

		List<DbField> fieldList = new ArrayList<DbField>();

		DbField field = new DbField("�ɹ�Ŀ¼ID", "CATALOGUE_ID", GaConstant.DT_LONG);
		field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "CATALOGUE_ID");
		field.setInput(GaConstant.INPUT_HIDDEN);
		fieldList.add(field);
		
		field = new DbField("����ID", "AREA_ID", GaConstant.DT_LONG);
		field.setInput(GaConstant.INPUT_HIDDEN);
		field.setColumnDisplay(false,0,true);
		field.setQueryParamFormSource(GaConstant.INPUT_REQUEST,"AREA_ID");
		fieldList.add(field);
	    
	  field = new DbField("��������","AREA_NAME",GaConstant.DT_STRING);
	  field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
		field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
	  fieldList.add(field);
	    
	  field = new DbField("�ɹ�Ŀ¼����","NAME",GaConstant.DT_STRING);
	  fieldList.add(field);
	    
	  field = new DbField("������","USERNAME",GaConstant.DT_STRING);
	  field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
		field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
	  fieldList.add(field);

		field = new DbField("��������", "CREATE_TIME", GaConstant.DT_DATETIME);
		field.setFormat(new CustomFormat(GaConstant.DT_DATETIME, "yyyy-MM-dd HH:mm:ss", false));
		field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
		field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		fieldList.add(field);
	    
	  field = new DbField("��ע", "NOTE", GaConstant.DT_STRING);
	  field.setInput(GaConstant.INPUT_TEXTAREA);
	  field.addInputAttributeMap("cols", "80");
	  field.addInputAttributeMap("rows", "3");
	  fieldList.add(field);
	    
	  this.fieldList  = fieldList;

	}
}
