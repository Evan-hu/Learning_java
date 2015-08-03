/**
 * Copyright (c) 2011 Noah
 * All right reserved.
 */
package com.ga.erp.biz.activity.activity;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

/**
 * 类描述
 * @author SPE
 * @create_time 2012-7-5 下午04:22:04
 * @project ShopMgr
 */
public class ActivityOrderListView extends ListView {

  public ActivityOrderListView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap, GaConstant.EDITMODE_DISP);
  }

  public void initField() throws Exception {
    List<DbField> dbField = new ArrayList<DbField>();
    
    DbField field = new DbField("订单编号", "STORE_SALES_ORDER_NUM", GaConstant.DT_STRING);
    field.setColumnDisplay(true, 80, true);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
    dbField.add(field);
    
    field = new DbField("总金额","TOTAL_MONEY",GaConstant.DT_MONEY);
    field.setColumnDisplay(true, 80, false);
    dbField.add(field);  
    
    field = new DbField("优惠金额","DISCOUNT_MONEY",GaConstant.DT_MONEY);
    field.setColumnDisplay(true, 80, false);
    dbField.add(field);  
    
    field = new DbField("实付金额","PAY_MONEY",GaConstant.DT_MONEY);
    field.setColumnDisplay(true, 80, false);
    dbField.add(field); 
    
    
    field = new DbField("订单类型","TYPE",GaConstant.DT_LONG);
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 12, false);
    field.setLookupData(SystemUtil.getOrderTypeMap());
    field.setColumnDisplay(true, 100, true);
    field.setAliasCode("so");
    dbField.add(field);    
    
    field = new DbField("下单时间","CREATE_TIME",GaConstant.DT_DATETIME);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN,10,false);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm",false));
    field.setColumnDisplay(true, 120, false);
    field.setInputCustomStyle("width:100px");
    dbField.add(field);
    
    field = new DbField("下单门店","STORE_NAME",GaConstant.DT_LONG);
    field.setColumnDisplay(true, 100, true);
    dbField.add(field); 
    
    this.fieldList = dbField;
  }
}
