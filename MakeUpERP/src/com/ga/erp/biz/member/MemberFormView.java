package com.ga.erp.biz.member;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;

public class MemberFormView extends FormView{

  public MemberFormView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }
  
  @Override
  public void initField() throws Exception {
	  
    List<DbField> dbFields = new ArrayList<DbField>();
    
    DbField field = new DbField("ID","MEMBER_ID",GaConstant.DT_LONG); 
    field.setInput(GaConstant.INPUT_HIDDEN);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "MEMBER_ID");
    dbFields.add(field);
    
  	field = new DbField("��Ա���", "MEMBER_NUM", GaConstant.DT_STRING);
  	field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
  	dbFields.add(field);
  	
  	field = new DbField("ע���ŵ�","STORE_NAME",GaConstant.DT_STRING);
  	field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
  	dbFields.add(field);
  	
  	field = new DbField("�Ա�", "SEX", GaConstant.DT_INT);
  	field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
  	field.setInput(GaConstant.INPUT_SELECT);
  	Map<String,Object> option = new LinkedHashMap<String, Object>();
  	option.put("1", "��");
  	option.put("2", "Ů");
  	field.setLookupData(new LookupDataSet(option));
  	field.setVerifyFormula("", true);
  	dbFields.add(field);
  	
  	field = new DbField("��ϵ��ʽ<font color='red'>*</font>","TEL",GaConstant.DT_STRING);
  	dbFields.add(field);
  	
  	field = new DbField("����","EMAIL",GaConstant.DT_STRING);
  	dbFields.add(field);
  	
  	field = new DbField("��ʵ����","TRUENAME",GaConstant.DT_STRING);
  	field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
  	dbFields.add(field);
  
  	field = new DbField("����","LAST_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd",true));
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    dbFields.add(field);
    
    field = new DbField("ע��ʱ��","REG_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    dbFields.add(field);

    field = new DbField("�������ʱ��","LAST_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    dbFields.add(field);
    
    field = new DbField("��������ŵ�","LAST_STORE_NAME",GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    dbFields.add(field);
    
    field = new DbField("�ۼ�����","LAST_AMOUNT",GaConstant.DT_MONEY);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
  	field.setInputCustomStyle("width:50px;");
  	dbFields.add(field);
  	
  	field = new DbField("�ۼƻ���","SCORE_AMOUNT",GaConstant.DT_MONEY);
  	field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
  	field.setInputCustomStyle("width:50px;");
  	dbFields.add(field);
  	
  	field = new DbField("ʣ�����","SCORE_BALANCE",GaConstant.DT_MONEY);
  	field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
  	field.setInputCustomStyle("width:50px;");
  	dbFields.add(field);

  	field = new DbField("��ע", "NOTE", GaConstant.DT_STRING);
    field.setInput(GaConstant.INPUT_TEXTAREA);
    field.addInputAttributeMap("rows", "3");
    field.addInputAttributeMap("cols", "40");
    dbFields.add(field);
    
    this.fieldList = dbFields;
  }

}
