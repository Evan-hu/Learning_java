package com.ga.erp.biz.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class OpListView extends ListView {
  public OpListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {

    List<DbField> dbFields = new ArrayList<DbField>();
    
    DbField field = new DbField("OP_ID","OP_ID",GaConstant.DT_LONG);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "OP_ID");
    field.setColumnDisplay(false, 0, true);
    dbFields.add(field);
    
    field = new DbField("门店管理员ID", "OP_STORE_ID", GaConstant.DT_LONG);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "OP_STORE_ID");
    field.setColumnDisplay(false, 0, true);
    dbFields.add(field);
    
    field = new DbField("供应商管理员ID", "OP_SUPPLIER_ID", GaConstant.DT_LONG);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "OP_SUPPLIER_ID");
    field.setColumnDisplay(false, 0, true);
    dbFields.add(field);
    
    field = new DbField("账号","USERNAME",GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 0, true);
    dbFields.add(field);
    
    field = new DbField("真实姓名","TRUENAME",GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 0, true);
    dbFields.add(field);
    
    field = new DbField("联系电话","LINK_TEL",GaConstant.DT_STRING);
    dbFields.add(field);
    
    field = new DbField("联系地址","LINK_ADDR",GaConstant.DT_STRING);
    dbFields.add(field);
    
    field = new DbField("操作员类型","TYPE",GaConstant.DT_LONG);
    field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT, 0, true);
    Map<String,Object> option = new HashMap<String, Object>();
    option = new LinkedHashMap<String, Object>();
    option.put("1", "系统管理员");
    option.put("2", "门店管理员");
    option.put("3", "供应商管理员");
    field.setLookupData(new LookupDataSet(option));
    field.setColumnDisplay(true, 80, false);
    dbFields.add(field);
    
    field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    dbFields.add(field);
    
    field = new DbField("结束时间","END_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    dbFields.add(field);
    
    field = new DbField("店员类型","IS_CASHIER",GaConstant.DT_LONG);
    field.setInput(GaConstant.INPUT_SELECT);
    Map<String, Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("1", "收银员");
    lookup.put("0", "营业员");
    field.setLookupData( new LookupDataSet(lookup));
    dbFields.add(field);
    
    field = new DbField("状态","STATE",GaConstant.DT_LONG);
    field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT, 0, true);
    field.setLookupData(SystemUtil.getStatusMap());
    field.setColumnDisplay(true, 50, false);
    dbFields.add(field);

    this.fieldList = dbFields;
  }
  
}
