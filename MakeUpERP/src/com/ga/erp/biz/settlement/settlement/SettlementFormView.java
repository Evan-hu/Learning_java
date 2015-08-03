package com.ga.erp.biz.settlement.settlement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;

public class SettlementFormView extends FormView {

	public SettlementFormView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap);
	}

	@Override
	public void initField() throws Exception {

		List<DbField> fieldList = new ArrayList<DbField>();

		DbField field = new DbField("ID", "SETTLEMENT_ID", GaConstant.DT_LONG);
	  field.setInput(GaConstant.INPUT_HIDDEN);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "SETTLEMENT_ID");
    fieldList.add(field);
    
    field = new DbField("�����������","OBJECT_TYPE",GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setInput(GaConstant.INPUT_SELECT);
    field.setColumnDisplay(true,80, false);
    Map<String, Object> option = new LinkedHashMap<String, Object>();
    option.put("1", "��Ӧ��");
    option.put("2", "�ŵ�");
    option.put("3", "��ͻ�");
    option.put("4", "��������");
    field.setLookupData(new LookupDataSet(option));
    fieldList.add(field);
    
    field = new DbField("����������","TYPE",GaConstant.DT_INT);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setInput(GaConstant.INPUT_SELECT);
    field.setColumnDisplay(true, 80, false);
    option = new LinkedHashMap<String, Object>();
    option.put("1", "����");
    option.put("2", "�����");
    option.put("3", "�ڴν�ת");
    option.put("4", "��������");
    option.put("5", "��������");
    field.setLookupData(new LookupDataSet(option));
    fieldList.add(field);

	  field = new DbField("��ʼʱ��","START_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setColumnDisplay(true,80, false);
    fieldList.add(field);
    
    field = new DbField("����ʱ��","END_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setColumnDisplay(true,80, false);
    fieldList.add(field);
   
    field = new DbField("����ת��Ӧ��","PRE_RECEIVE_MONEY",GaConstant.DT_MONEY);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setColumnDisplay(true,80, false);
    fieldList.add(field);

    field = new DbField("����Ӧ��","RECEIVE_MONEY",GaConstant.DT_MONEY);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setColumnDisplay(true,80, false);
    fieldList.add(field);
    
    field = new DbField("����Ӧ��","PAY_MONEY",GaConstant.DT_MONEY);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setColumnDisplay(true,80, false);
    fieldList.add(field);
    
    field = new DbField("����Ӧ����","SHOULD_MONEY",GaConstant.DT_MONEY);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setColumnDisplay(true,80, false);
    fieldList.add(field);
    
    field = new DbField("����ʵ����","REAL_MONEY",GaConstant.DT_MONEY);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setColumnDisplay(true,80, false);
    fieldList.add(field);
    
    field = new DbField("����״̬","STATE",GaConstant.DT_INT);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setInput(GaConstant.INPUT_SELECT);
    option = new HashMap<String, Object>();
  	option.put("0", "������");
  	option.put("1", "������");
  	option.put("2", "�Ѹ���");
  	field.setLookupData(new LookupDataSet(option));
    field.setColumnDisplay(true, 80, false);
    fieldList.add(field);

    field = new DbField("����ʱ��","PAY_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setColumnDisplay(true,80, false);
    fieldList.add(field);
    
    field = new DbField("����������","TYPE",GaConstant.DT_INT);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setInput(GaConstant.INPUT_SELECT);
    option = new LinkedHashMap<String, Object>();
    option.put("1", "����");
    option.put("2", "�����");
    option.put("3", "�ڴν�ת");
    option.put("4", "��������");
    field.setLookupData(new LookupDataSet(option));
    field.setColumnDisplay(true,80,false);
    fieldList.add(field);
    
    field = new DbField("��ע", "NOTE", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setInput(GaConstant.INPUT_TEXTAREA);
    field.addInputAttributeMap("rows", "3");
    field.addInputAttributeMap("cols", "98");
    fieldList.add(field);

    this.fieldList = fieldList;
	}
}
