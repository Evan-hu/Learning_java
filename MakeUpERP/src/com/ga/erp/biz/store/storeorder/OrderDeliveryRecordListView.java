package com.ga.erp.biz.store.storeorder;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;

public class OrderDeliveryRecordListView extends ListView  {
  public OrderDeliveryRecordListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {
    List<DbField> dbField = new ArrayList<DbField>();
        
    DbField field = new DbField("处理时间","DEAL_TIME",GaConstant.DT_DATETIME);
    field.setColumnDisplay(true, 120, true);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm",false)); 
    dbField.add(field);
    
    field = new DbField("处理信息","DEAL_MESSAGE",GaConstant.DT_STRING);  
    field.setColumnDisplay(true, 400, true);
    dbField.add(field);    
    
    field = new DbField("操作人","DEAL_ADMIN",GaConstant.DT_STRING);
    field.setColumnDisplay(true, 0, true);
    dbField.add(field);
    
    
    this.fieldList = dbField;
  }
}
