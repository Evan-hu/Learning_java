package com.ga.erp.biz.system;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;


public class OpPasswordFromView extends FormView{

  public OpPasswordFromView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }
  
  @Override
  public void initField() throws Exception {
    List<DbField> dbField = new ArrayList<DbField>();
    DbField field = new DbField("ID", "OP_ID", GaConstant.DT_LONG);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "OP_ID");
    field.setInput(GaConstant.INPUT_HIDDEN);
    field.setPK(true);
    dbField.add(field);
     
    field = new DbField("帐号", "USERNAME", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    dbField.add(field);
    
    field = new DbField("真实姓名", "TRUENAME", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    dbField.add(field);
    
    field = new DbField("密码 <font color='red'>*</font>", "PASSWORD", GaConstant.DT_PASSWORD);
    dbField.add(field);

    field = new DbField("确认密码<font color='red'>*</font>", "RE_PASSWORD", GaConstant.DT_PASSWORD);
    dbField.add(field);
    
    this.fieldList = dbField;
  }

}
