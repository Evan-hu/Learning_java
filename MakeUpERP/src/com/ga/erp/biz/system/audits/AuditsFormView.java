package com.ga.erp.biz.system.audits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class AuditsFormView extends FormView {

  /**
   * @param viewID
   * @param modelMap
   */
  public AuditsFormView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }

  @Override
  public void initField() throws Exception {
    
    List<DbField> dbFields = new ArrayList<DbField>();
    
    DbField field = new DbField("ID","AUDITING_ID",GaConstant.DT_LONG);
    field.setInput(GaConstant.INPUT_HIDDEN);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "AUDITING_ID");
    dbFields.add(field);
    
    field = new DbField("ID","SYS_CODE_ID",GaConstant.DT_LONG);
    field.setInput(GaConstant.INPUT_HIDDEN);
    dbFields.add(field);
    
    
    field= new DbField("��˶���ID", "BIZ_ID", GaConstant.DT_LONG);
    field.setInput(GaConstant.INPUT_HIDDEN);
    field.setQueryParamFormSource(GaConstant.INPUT_REQUEST,"BIZ_ID");
    dbFields.add(field);
    
    field= new DbField("ҵ������", "OBJECT_NAME", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_READONLY);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    dbFields.add(field);
    
    field = new DbField("���ʵ��ID", "AUDITING_EXAMPLE_ID",GaConstant.DT_LONG);
    field.setInput(GaConstant.INPUT_HIDDEN);
    dbFields.add(field);
    
    field= new DbField("��˲���", "STEP", GaConstant.DT_INT);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    dbFields.add(field);
    
    field= new DbField("�����", "TRUENAME", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    dbFields.add(field);
    
    field = new DbField("���ʱ��","CREATE_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    dbFields.add(field);
    
    field= new DbField("��˽��", "RESULT_CODE", GaConstant.DT_INT);
    field.setInput(GaConstant.INPUT_SELECT);
    field.setLookupData(SystemUtil.getAuditsMap());
    field.setVerifyFormula("", true);
    dbFields.add(field);
    
    field = new DbField("���˵��", "RESULT_NOTE", GaConstant.DT_STRING);
    field.setInput(GaConstant.INPUT_TEXTAREA);
    field.addInputAttributeMap("rows", "3");
    field.addInputAttributeMap("cols", "50");
    dbFields.add(field);
    
    this.fieldList = dbFields;
  }
}
