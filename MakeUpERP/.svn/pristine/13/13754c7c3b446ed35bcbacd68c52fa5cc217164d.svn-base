/**
 * Copyright (c) 2011 Noah
 * All right reserved.
 */
package com.ga.erp.biz.activity.activity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.util.SystemUtil;

/**
 * 类描述
 * @author SPE
 * @create_time 2012-7-5 下午04:22:04
 * @project ShopMgr
 */
public class ActivityParamListView extends ListView {

  public ActivityParamListView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap, GaConstant.EDITMODE_DISP);
  }

  public void initField() throws Exception {
    List<DbField> dbField = new ArrayList<DbField>();
    
    DbField field = new DbField("参数名称", "PARAM_NAME", GaConstant.DT_STRING);
    field.setColumnDisplay(true, 80, true);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
    dbField.add(field);
    
    field = new DbField("参数ID", "DISCOUNT_PARAM_ID", GaConstant.DT_STRING);
    field.setColumnDisplay(false, 80, true);
    dbField.add(field);
    
  
    field = new DbField("优惠方式", "DISCOUNT_TYPE", GaConstant.DT_STRING);
    field.setColumnDisplay(true, 80, true);
    Map<String, Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("1", "加钱赠送商品");
    lookup.put("2", "减免固定金额");
    lookup.put("3", "指定商品特价");
    lookup.put("4", "满金额打折");
    lookup.put("5", "满数量打折");
    lookup.put("7", "赠送优惠券");
    lookup.put("8", "组合商品");
    lookup.put("9", "满数量加钱赠送商品");
    field.setLookupData(new LookupDataSet(lookup));
    dbField.add(field);
    field = new DbField("状态","STATE",GaConstant.DT_LONG);
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 12, false);
    field.setLookupData(SystemUtil.getStatusMap());
    field.setColumnDisplay(true, 100, true);
    dbField.add(field);  
    
    this.fieldList = dbField;
  }
}
