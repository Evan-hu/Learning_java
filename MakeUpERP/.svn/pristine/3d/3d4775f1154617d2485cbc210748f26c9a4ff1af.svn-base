package com.ga.erp.biz.content.stationmsg;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class StationMsgFormView extends FormView {

  public StationMsgFormView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }
  @Override
  public void initField(){
	  
    List<DbField> dbFields = new ArrayList<DbField>();
    
    DbField field = new DbField("ID","STATION_MSG_ID",GaConstant.DT_LONG); 
    field.setInput(GaConstant.INPUT_HIDDEN);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "STATION_MSG_ID");
	dbFields.add(field);
	    
	field = new DbField("标题", "TITLE", GaConstant.DT_STRING);
	field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
	field.getFieldVerify().setRequire(true);
	dbFields.add(field);
	
	field = new DbField("类型", "TYPE", GaConstant.DT_INT);
	field.setInput(GaConstant.INPUT_HIDDEN);
	dbFields.add(field);

    field = new DbField("创建人","TRUENAME",GaConstant.DT_STRING);
	field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
	field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    dbFields.add(field);
    
    field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
	field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
	field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
	dbFields.add(field);
	
	field = new DbField("状态", "STATE", GaConstant.DT_INT);
	field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
	field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
	field.setInput(GaConstant.INPUT_SELECT);
	field.setLookupData(SystemUtil.getStatusMap());
	field.setVerifyFormula("", true);
	dbFields.add(field);
	
	field = new DbField("内容", "CONTENT", GaConstant.DT_STRING);
	field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
	field.setInput(GaConstant.INPUT_TEXTAREA);
	field.addInputAttributeMap("rows", "4");
	field.addInputAttributeMap("cols", "70");
	field.getFieldVerify().setRequire(true);
	dbFields.add(field);
	
	field = new DbField("备注", "NOTE", GaConstant.DT_STRING);
	field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
	field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
	field.setInput(GaConstant.INPUT_TEXTAREA);
	field.addInputAttributeMap("rows", "2");
	field.addInputAttributeMap("cols", "70");
	dbFields.add(field);
	
    this.fieldList = dbFields;
  }
}
