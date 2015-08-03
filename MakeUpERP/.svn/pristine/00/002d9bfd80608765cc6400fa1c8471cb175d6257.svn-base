package com.ga.erp.biz.store;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class StoreFormView extends FormView {

  public StoreFormView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }

  @Override
  public void initField() throws Exception {

    List<DbField> dbFields = new ArrayList<DbField>();

    DbField field = new DbField("ID", "STORE_ID", GaConstant.DT_LONG);
    field.setInput(GaConstant.INPUT_HIDDEN);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "STORE_ID");
    dbFields.add(field);
    
    field = new DbField("门店名","STORE_NAME",GaConstant.DT_STRING);
  	dbFields.add(field);
  	
  	field = new DbField("门店编号", "STORE_NUM", GaConstant.DT_STRING);
   	field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
  	dbFields.add(field);
  	
    field = new DbField("地区ID","AREA_ID",GaConstant.DT_LONG);
    field.setPopSelect("NEWAREASELECT", "AREA_ID", false);
    dbFields.add(field);
    
  	field = new DbField("地区", "AREA_NAME", GaConstant.DT_STRING);
    field.setPopSelect("NEWAREASELECT","AREA_NAME",true,"/system/area_sele.htm",
            "AREA_ID,AREA_NAME,cid_areaTree", 300,400);
    dbFields.add(field);
  	
    field = new DbField("上次通讯时间","LAST_MSG_TIME",GaConstant.DT_DATETIME);
   	field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
   	field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
   	field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
   	dbFields.add(field);
   	
    field = new DbField("剩余金额", "LEFT_MONEY", GaConstant.DT_MONEY);
    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);   
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);   
    field.setInputCustomStyle("width:80px;");
    dbFields.add(field);
    
    field = new DbField("累计消费", "ALL_CONSUME", GaConstant.DT_MONEY);
    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);   
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);   
    field.setInputCustomStyle("width:80px;");
    dbFields.add(field);   
    
    field = new DbField("累计充值", "ALL_PAY", GaConstant.DT_MONEY);
    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);   
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);   
    field.setInputCustomStyle("width:80px;");
    dbFields.add(field); 
    
    field = new DbField("累计销售额", "ALL_CONSUME_MONEY", GaConstant.DT_MONEY);
    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);   
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);   
    field.setInputCustomStyle("width:80px;");
    dbFields.add(field); 
    
    field = new DbField("累计销售单", "ALL_CONSUME_ORDER", GaConstant.DT_LONG);
    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);   
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);   
    field.setInputCustomStyle("width:80px;");
    dbFields.add(field); 
    
    field = new DbField("累计退货金额", "ALL_RETURN_MONEY", GaConstant.DT_MONEY);
    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);   
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);   
    field.setInputCustomStyle("width:80px;");
    dbFields.add(field); 
    
    field = new DbField("累计退货单", "ALL_RETURN_ORDER", GaConstant.DT_LONG);
    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);   
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);   
    field.setInputCustomStyle("width:80px;");
    dbFields.add(field); 
    
    dbFields.addAll(SystemUtil.getNormalFieldList());
    
    this.fieldList = dbFields;
  }

}
