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
    
  	field = new DbField("会员编号", "MEMBER_NUM", GaConstant.DT_STRING);
  	field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
  	dbFields.add(field);
  	
  	field = new DbField("注册门店","STORE_NAME",GaConstant.DT_STRING);
  	field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
  	dbFields.add(field);
  	
  	field = new DbField("性别", "SEX", GaConstant.DT_INT);
  	field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
  	field.setInput(GaConstant.INPUT_SELECT);
  	Map<String,Object> option = new LinkedHashMap<String, Object>();
  	option.put("1", "男");
  	option.put("2", "女");
  	field.setLookupData(new LookupDataSet(option));
  	field.setVerifyFormula("", true);
  	dbFields.add(field);
  	
  	field = new DbField("联系方式<font color='red'>*</font>","TEL",GaConstant.DT_STRING);
  	dbFields.add(field);
  	
  	field = new DbField("邮箱","EMAIL",GaConstant.DT_STRING);
  	dbFields.add(field);
  	
  	field = new DbField("真实姓名","TRUENAME",GaConstant.DT_STRING);
  	field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
  	dbFields.add(field);
  
  	field = new DbField("生日","LAST_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd",true));
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    dbFields.add(field);
    
    field = new DbField("注册时间","REG_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    dbFields.add(field);

    field = new DbField("最后消费时间","LAST_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    dbFields.add(field);
    
    field = new DbField("最后消费门店","LAST_STORE_NAME",GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    dbFields.add(field);
    
    field = new DbField("累计消费","LAST_AMOUNT",GaConstant.DT_MONEY);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
  	field.setInputCustomStyle("width:50px;");
  	dbFields.add(field);
  	
  	field = new DbField("累计积分","SCORE_AMOUNT",GaConstant.DT_MONEY);
  	field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
  	field.setInputCustomStyle("width:50px;");
  	dbFields.add(field);
  	
  	field = new DbField("剩余积分","SCORE_BALANCE",GaConstant.DT_MONEY);
  	field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
  	field.setInputCustomStyle("width:50px;");
  	dbFields.add(field);

  	field = new DbField("备注", "NOTE", GaConstant.DT_STRING);
    field.setInput(GaConstant.INPUT_TEXTAREA);
    field.addInputAttributeMap("rows", "3");
    field.addInputAttributeMap("cols", "40");
    dbFields.add(field);
    
    this.fieldList = dbFields;
  }

}
