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
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

/**
 * 类描述
 * @author SPE
 * @create_time 2012-7-5 下午03:25:54
 * @project ShopMgr
 */
public class ActivityListView extends ListView {

  public ActivityListView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {
    List<DbField> dbField = new ArrayList<DbField>();
    
    DbField field = new DbField("活动名称","NAME",GaConstant.DT_STRING);
    field.setAliasCode("d");
    field.setQueryOpera(GaConstant.QUERY_LIKE,0,false);
    dbField.add(field);
    
    field = new DbField("活动类型", "TYPE", GaConstant.DT_STRING);
    field.setAliasCode("d");
    field.setInput(GaConstant.INPUT_SELECT);
    field.setVerifyFormula("", true);
    Map<String,Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("1", "折扣");
    lookup.put("2", "特价");
    lookup.put("3", "买满送");
    lookup.put("4", "组合商品");
    field.setLookupData(new LookupDataSet(lookup));
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT,0,false);
    dbField.add(field);
    
    field = new DbField("创建人","USERNAME",GaConstant.DT_STRING);
    dbField.add(field);
    field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_STRING);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd",false));
    dbField.add(field);
    field = new DbField("ID","DISCOUNT_ACTIVITY_ID",GaConstant.DT_LONG);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "DISCOUNT_ACTIVITY_ID");
    field.setColumnDisplay(false, 0, true);
    field.setPK(true);
    dbField.add(field);
    
    field = new DbField("开始时间","START_TIME",GaConstant.DT_DATETIME);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN,0,false);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd",false));
    dbField.add(field);
    
    field = new DbField("结束时间","END_TIME",GaConstant.DT_STRING);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd",false));
    dbField.add(field);
    
//    field = new DbField("订单数","ORDER_COUNT",GaConstant.DT_INT);
//    dbField.add(field);
//      
//    field = new DbField("订单金额","ORDER_AMOUNT",GaConstant.DT_STRING);
//    dbField.add(field);
    
    field = new DbField("审核状态","CHECK_STATE",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT,0,false);
  
    field.setLookupData(SystemUtil.getAuditsMap());
    dbField.add(field);
    
    field = new DbField("状态","STATE",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT,0,false);
    lookup = new HashMap<String, Object>();
    lookup.put("2", "已结束");
    lookup.put("1", "启用中");
    lookup.put("0", "无效");
    field.setLookupData(new LookupDataSet(lookup));
    field.setAliasCode("d");
    dbField.add(field);
    
    field = new DbField("删除状态", "DELETE_STATE", GaConstant.DT_LONG);
    field.setColumnDisplay(false,0,true);   
    field.setIsFilterCode(true);
    dbField.add(field);
    this.fieldList = dbField;
  }
}
