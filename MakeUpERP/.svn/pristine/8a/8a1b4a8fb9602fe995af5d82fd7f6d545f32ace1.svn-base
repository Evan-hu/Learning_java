package com.ga.erp.biz.member.pwd;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;

public class MemberPwdFromView extends FormView{

  public MemberPwdFromView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }
  
  @Override
  public void initField() throws Exception {
	  
    List<DbField> dbField = new ArrayList<DbField>();
    
    DbField field = new DbField("ID", "MEMBER_ID", GaConstant.DT_LONG);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "MEMBER_ID");
    field.setInput(GaConstant.INPUT_HIDDEN);
    dbField.add(field);
     
    field = new DbField("ª·‘±±‡∫≈", "MEMBER_NUM", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    dbField.add(field);
    
    field = new DbField("◊¢≤·√≈µÍ", "STORE_NAME", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    dbField.add(field);
    
    field = new DbField("√‹¬Î <font color='red'>*</font>", "PASSWORD", GaConstant.DT_PASSWORD);
    dbField.add(field);

    field = new DbField("»∑»œ√‹¬Î<font color='red'>*</font>", "RE_PASSWORD", GaConstant.DT_PASSWORD);
    dbField.add(field);
    
    this.fieldList = dbField;
  }

}
