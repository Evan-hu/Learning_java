package com.ga.erp.biz.store.storeorder.toOrder;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;

public class ToOrderListView extends ListView {
	
  public ToOrderListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {
	  
    List<DbField> dbFields = new ArrayList<DbField>();
	    
  	DbField field = new DbField("货号","CODE",GaConstant.DT_STRING);
  	dbFields.add(field);
  	
  	field = new DbField("商品名称","COMMODITY_NAME",GaConstant.DT_STRING);
    dbFields.add(field);
     
    field = new DbField("进货规格","STOCK_SPEC",GaConstant.DT_STRING);
    dbFields.add(field);
    
    field = new DbField("商品条码","CODE",GaConstant.DT_STRING);
    dbFields.add(field);
    
  	field = new DbField("门店","STORE_NAME",GaConstant.DT_STRING);
  	dbFields.add(field);
  	
    field = new DbField("进货价","RETAIL_PRICE",GaConstant.DT_MONEY);
    dbFields.add(field);
    
    field = new DbField("当前库存","INNAGE",GaConstant.DT_LONG);
    dbFields.add(field);
    
    field = new DbField("DC库存","SINNAGE",GaConstant.DT_LONG);
    dbFields.add(field);
    
    field = new DbField("建议补货","NEED_INNAGE",GaConstant.DT_LONG);
    dbFields.add(field);
    
    this.fieldList = dbFields;
    
  }
  
}
