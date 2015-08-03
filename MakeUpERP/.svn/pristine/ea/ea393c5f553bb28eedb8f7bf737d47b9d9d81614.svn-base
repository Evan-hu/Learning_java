/**
 * Copyright (c) 2011 Noah
 * All right reserved.
 */
package com.ga.erp.biz.activity.activity;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;

public class ActivityStoreEditView extends FormView {

  public ActivityStoreEditView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }
  @Override
  public void initField() throws Exception {
	  List<DbField> dbField = new ArrayList<DbField>();
    DbField field = new DbField("参数ID", "STORE_ACTIVITY_ID", GaConstant.DT_LONG);
    field.setQueryParamFormSource(GaConstant.INPUT_REQUEST, "STORE_ACTIVITY_ID");
    field.setPK(true);
    field.setBindRowData(true);
    field.setInput(GaConstant.INPUT_HIDDEN);
    dbField.add(field);

    field = new DbField("活动ID", "DISCOUNT_ACTIVITY_ID", GaConstant.DT_LONG);
    field.setQueryParamFormSource(GaConstant.INPUT_REQUEST, "DISCOUNT_ACTIVITY_ID");
    field.setInput(GaConstant.INPUT_HIDDEN);
    dbField.add(field);

 	 field = new DbField("门店ID", "STORE_ID", GaConstant.DT_LONG);
    field.setPopSelect("STORESELECT", "STORE_ID", false);
    dbField.add(field);
   
    field = new DbField("参与店铺<font color='red'>*</font>", "STORE_NAME", GaConstant.DT_STRING);
    field.setPopSelect("STORESELECT","STORE_NAME",true,
           "/store/store_main.htm",
           "STORE_ID,STORE_NAME,cid_storeList",800,500);
    dbField.add(field);
    
    field = new DbField("备注", "NOTE", GaConstant.DT_STRING);
    field.setInput(GaConstant.INPUT_TEXTAREA);
    field.setInputCustomStyle("width:400px;");
    dbField.add(field);
    
    
    this.fieldList = dbField;
  }
}
