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
	    
  	DbField field = new DbField("����","CODE",GaConstant.DT_STRING);
  	dbFields.add(field);
  	
  	field = new DbField("��Ʒ����","COMMODITY_NAME",GaConstant.DT_STRING);
    dbFields.add(field);
     
    field = new DbField("�������","STOCK_SPEC",GaConstant.DT_STRING);
    dbFields.add(field);
    
    field = new DbField("��Ʒ����","CODE",GaConstant.DT_STRING);
    dbFields.add(field);
    
  	field = new DbField("�ŵ�","STORE_NAME",GaConstant.DT_STRING);
  	dbFields.add(field);
  	
    field = new DbField("������","RETAIL_PRICE",GaConstant.DT_MONEY);
    dbFields.add(field);
    
    field = new DbField("��ǰ���","INNAGE",GaConstant.DT_LONG);
    dbFields.add(field);
    
    field = new DbField("DC���","SINNAGE",GaConstant.DT_LONG);
    dbFields.add(field);
    
    field = new DbField("���鲹��","NEED_INNAGE",GaConstant.DT_LONG);
    dbFields.add(field);
    
    this.fieldList = dbFields;
    
  }
  
}
