package com.ga.erp.biz.settlement.deliveryorg;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;

public class DeliverySettListView extends ListView  {

  public DeliverySettListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }
  
  @Override
  public void initField() throws Exception {
    
    List<DbField> fieldList = new ArrayList<DbField>();
    
    DbField field = new DbField("单据类型","TYPE",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
    Map<String, Object> option = new LinkedHashMap<String, Object>();
    option.put("1", "供应商业务单");
    option.put("2", "门店业务单");
    field.setLookupData(new LookupDataSet(option));
    fieldList.add(field);
    
    field = new DbField("对象名称","OBJECT_NAME",GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
    fieldList.add(field);
    
    field = new DbField("单据ID","ORDER_ID",GaConstant.DT_LONG);
//    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "ORDER_ID");
    field.setColumnDisplay(true,0,true);   
    fieldList.add(field);
    
    field = new DbField("单据编号","ORDER_NUM",GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
    fieldList.add(field);

    field = new DbField("物流机构","DELIVERY_NAME",GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
    fieldList.add(field);
    
    field = new DbField("物流费用","DISTRIBUTE_MONEY",GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
    fieldList.add(field);
    
    field = new DbField("物流号","DISTRIBUTE_NUM",GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
    fieldList.add(field);
    
    field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    fieldList.add(field);
    
    this.fieldList  = fieldList;
  }
}
