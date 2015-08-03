package com.ga.erp.biz.system;

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
import com.ga.erp.util.SystemUtil;

public class OpFormView extends FormView{

  public OpFormView(String viewID, ModelMap modelMap) {
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
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setTipInfo("&nbsp;&nbsp;&nbsp;建议使用英文或数字组合");
    dbFields.add(field);
    
    field = new DbField("真实姓名", "TRUENAME", GaConstant.DT_STRING);
    field.getFieldVerify().setRequire(true);
    dbFields.add(field);
    
    field = new DbField("密码<font color='red'>*</font>", "PASSWORD", GaConstant.DT_PASSWORD);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_HIDDEN);
    dbFields.add(field);

    field = new DbField("确认密码<font color='red'>*</font>", "RE_PASSWORD", GaConstant.DT_PASSWORD);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_HIDDEN);
    dbFields.add(field);
    
    field = new DbField("类型","TYPE",GaConstant.DT_INT);
    field.setInput(GaConstant.INPUT_HIDDEN);
    dbFields.add(field);
    
    field = new DbField("门店ID","STORE_ID",GaConstant.DT_LONG);
    field.setInput(GaConstant.INPUT_HIDDEN);
    dbFields.add(field);

    field = new DbField("供应商ID","SUPPLIER_ID",GaConstant.DT_LONG);
    field.setInput(GaConstant.INPUT_HIDDEN);
    dbFields.add(field);
    
    field = new DbField("供应商ID","SUPPLIER_ID",GaConstant.DT_LONG);
    field.setInput(GaConstant.INPUT_HIDDEN);
    dbFields.add(field);
    
    field = new DbField("店员类型","IS_CASHIER",GaConstant.DT_INT);
    field.setInput(GaConstant.INPUT_SELECT);
    field.setVerifyFormula("", true);
   
    
    Map<String, Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("1", "收银员");
    lookup.put("0", "营业员");
    field.setLookupData( new LookupDataSet(lookup));
    field.setDefaultValue("0");
    dbFields.add(field);
    
    field = new DbField("EMAIL", "EMAIL", GaConstant.DT_STRING);
    dbFields.add(field);

    field = new DbField("邮编", "POSTCODE", GaConstant.DT_STRING);
    dbFields.add(field);

    field = new DbField("联系电话", "LINK_TEL", GaConstant.DT_STRING);
    dbFields.add(field);

    field = new DbField("联系地址", "LINK_ADDR", GaConstant.DT_STRING);
    dbFields.add(field);

    field = new DbField("创建时间", "CREATE_TIME", GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    dbFields.add(field);
    
    field = new DbField("结束时间", "END_TIME", GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    dbFields.add(field);
    
    field = new DbField("备注", "NOTE", GaConstant.DT_STRING);
    field.setInput(GaConstant.INPUT_TEXTAREA);
    field.addInputAttributeMap("rows", "3");
    field.addInputAttributeMap("cols", "50");
    dbFields.add(field);
    
    this.fieldList = dbFields;
  }
}
