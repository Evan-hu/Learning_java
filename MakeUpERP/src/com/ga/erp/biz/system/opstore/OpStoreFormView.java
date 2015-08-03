package com.ga.erp.biz.system.opstore;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;

public class OpStoreFormView extends FormView{

  public OpStoreFormView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }

  @Override
  public void initField() throws Exception {
    
    List<DbField> dbFields = new ArrayList<DbField>();
    
    DbField field = new DbField("ID", "OP_ID", GaConstant.DT_LONG);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "OP_ID");
    field.setInput(GaConstant.INPUT_HIDDEN);
    dbFields.add(field);
    
    field = new DbField("帐号<font color='red'>*</font>", "USERNAME", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_READONLY);
    dbFields.add(field);
    
    field = new DbField("真实姓名", "TRUENAME", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_READONLY);
    dbFields.add(field);
    
    field = new DbField("门店ID", "STORE_IDS", GaConstant.DT_STRING);
    field.setPopSelect("STORESELECT", "STORE_ID", false);
    dbFields.add(field);
    
    field = new DbField("管理门店", "STORE_NAME", GaConstant.DT_STRING);
    field.setPopSelect("STORESELECT","STORE_NAME",true,
        "/store/store_main.htm",
        "STORE_ID,STORE_NAME,cid_storeList,_ids",800,450);
    field.setInputCustomStyle("width:120px;");
    dbFields.add(field);
    
    this.fieldList = dbFields;
  }
}
