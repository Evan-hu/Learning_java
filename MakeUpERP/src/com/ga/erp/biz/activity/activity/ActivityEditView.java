/**
 * Copyright (c) 2011 Noah
 * All right reserved.
 */
package com.ga.erp.biz.activity.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.control.DbFieldFormat;
import com.ga.click.control.LookupDataSet;
import com.ga.click.control.GaConstant;
import com.ga.click.control.editor.Editor;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;

/**
 * 类描述
 * @author SPE
 * @create_time 2012-7-5 下午04:18:24
 * @project ShopMgr
 */
public class ActivityEditView extends FormView {

  public ActivityEditView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }
  
  @Override
  public void initField() throws Exception {
    List<DbField> dbField = new ArrayList<DbField>();
    
    DbField field = new DbField("活动名称", "NAME", GaConstant.DT_STRING);
    field.setMaxLength(10);
    dbField.add(field);

    field = new DbField("活动ID", "DISCOUNT_ACTIVITY_ID", GaConstant.DT_LONG);
    field.setQueryParamFormSource(GaConstant.INPUT_REQUEST, "DISCOUNT_ACTIVITY_ID");
    field.setPK(true);
    field.setBindRowData(true);
    field.setInput(GaConstant.INPUT_HIDDEN);
    dbField.add(field);
    
    field = new DbField("活动类型", "TYPE", GaConstant.DT_LONG);
    field.setInput(GaConstant.INPUT_SELECT);
    field.setVerifyFormula("", true);
    Map<String,Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("1", "折扣");
    lookup.put("2", "特价");
    lookup.put("3", "买满送");
    lookup.put("4", "组合商品");
    field.setLookupData(new LookupDataSet(lookup));
    field.setDefaultValue("1");
    field.addInputAttributeMap("onchange", "activityChangeType(this)");
    dbField.add(field);
    
    field = new DbField("范围选择", "COMMODITY_RANGE_TYPE", GaConstant.DT_STRING);
    field.setInput(GaConstant.INPUT_SELECT);
    field.setVerifyFormula("", true);
    lookup = new LinkedHashMap<String, Object>();
    lookup.put("1", "全场");
    lookup.put("2", "分类");
    lookup.put("3", "品牌");
    lookup.put("4", "商品");
    field.setLookupData(new LookupDataSet(lookup));
    field.setDefaultValue("1");
    dbField.add(field);
    

    field = new DbField("开始时间", "START_TIME", GaConstant.DT_DATETIME);
    field.setFormat(new DbFieldFormat(GaConstant.DT_DATETIME, "yyyy-MM-dd HH:ss",false));
    dbField.add(field);

    field = new DbField("结束时间", "END_TIME", GaConstant.DT_DATETIME);
    field.setFormat(new DbFieldFormat(GaConstant.DT_DATETIME, "yyyy-MM-dd HH:ss",false));
    dbField.add(field);

//    field = new DbField("订单数", "ORDER_COUNT", GaConstant.DT_INT);
//    field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
//    field.setInputMode(GaConstant.EDITMODE_NEW,GaConstant.INPUTMODE_HIDDEN);
//    dbField.add(field);
//
//    field = new DbField("订单金额", "ORDER_AMOUNT", GaConstant.DT_MONEY);
//    field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
//    field.setInputMode(GaConstant.EDITMODE_NEW,GaConstant.INPUTMODE_HIDDEN);
//    dbField.add(field);

    field = new DbField("创建人", "USERNAME", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
    field.setInputMode(GaConstant.EDITMODE_NEW,GaConstant.INPUTMODE_HIDDEN);
    dbField.add(field);

    field = new DbField("创建时间", "CREATE_TIME", GaConstant.DT_DATETIME);
    field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
    field.setInputMode(GaConstant.EDITMODE_NEW,GaConstant.INPUTMODE_HIDDEN);
    dbField.add(field);

    Editor editor = new Editor("活动描述", "CONTENT", GaConstant.DT_STRING,Editor.UP_LOAD_OBJ_ACTIVITY);
    editor.setToolsMode(GaConstant.EDITOR_MODE_SIMPLE);
    editor.setRows(10);
    editor.setCols(70);
    editor.bindAddAttribute();
    dbField.add(editor);
    field = new DbField("备注", "NOTE", GaConstant.DT_STRING);
    field.setInput(GaConstant.INPUT_TEXTAREA);
    field.setInputCustomStyle("width:590px;");
    dbField.add(field);
    

    field = new DbField("状态", "STATE", GaConstant.DT_LONG);
   field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
   field.setInputMode(GaConstant.EDITMODE_NEW,GaConstant.INPUTMODE_READONLY);

    field.setInput(GaConstant.INPUT_SELECT);
    field.setVerifyFormula("", true);
    lookup = new HashMap<String, Object>();
    lookup.put("1", "正常");
    lookup.put("0", "无效");
    field.setLookupData(new LookupDataSet(lookup));
    dbField.add(field);
    
   
    
    this.fieldList = dbField;
  }
}
