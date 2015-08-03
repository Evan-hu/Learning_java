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
import com.ga.erp.util.SystemUtil;

/**
 * 类描述
 * @author SPE
 * @create_time 2012-7-5 下午04:22:04
 * @project ShopMgr
 */
public class ActivityStoreListView extends ListView {

  public ActivityStoreListView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap, GaConstant.EDITMODE_DISP);
  }

  public void initField() throws Exception {
    List<DbField> dbField = new ArrayList<DbField>();
    
    DbField field = new DbField("店铺名称", "STORE_NAME", GaConstant.DT_STRING);
    field.setColumnDisplay(true, 80, true);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
    dbField.add(field);
    
    field = new DbField("STORE_ACTIVITY_ID", "STORE_ACTIVITY_ID", GaConstant.DT_LONG);
    field.setColumnDisplay(false, 80, true);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "STORE_ACTIVITY_ID");
    field.setPK(true);
    dbField.add(field);
    
    field = new DbField("活动订单数","ORDER_COUNT",GaConstant.DT_LONG);
    field.setColumnDisplay(true, 80, false);
    dbField.add(field);  
    
    field = new DbField("累计总金额","TOTAL_MONEY",GaConstant.DT_MONEY);
    field.setColumnDisplay(true, 80, false);
    dbField.add(field);  
    
    field = new DbField("累计优惠金额","DISCOUNT_MONEY",GaConstant.DT_MONEY);
    field.setColumnDisplay(true, 80, false);
    dbField.add(field); 
    
    field = new DbField("累计实付金额","PAY_MONEY",GaConstant.DT_MONEY);
    field.setColumnDisplay(true, 80, false);
    dbField.add(field);
    
    field = new DbField("状态","STATE",GaConstant.DT_LONG);
    
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 12, false);
    field.setLookupData(SystemUtil.getStatusMap());
    field.setColumnDisplay(true, 100, true);
    field.setAliasCode("s");
    dbField.add(field);  
    
    this.fieldList = dbField;
  }
}
