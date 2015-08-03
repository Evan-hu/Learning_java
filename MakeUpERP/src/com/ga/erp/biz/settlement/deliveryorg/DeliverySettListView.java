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
    
    DbField field = new DbField("��������","TYPE",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
    Map<String, Object> option = new LinkedHashMap<String, Object>();
    option.put("1", "��Ӧ��ҵ��");
    option.put("2", "�ŵ�ҵ��");
    field.setLookupData(new LookupDataSet(option));
    fieldList.add(field);
    
    field = new DbField("��������","OBJECT_NAME",GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
    fieldList.add(field);
    
    field = new DbField("����ID","ORDER_ID",GaConstant.DT_LONG);
//    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "ORDER_ID");
    field.setColumnDisplay(true,0,true);   
    fieldList.add(field);
    
    field = new DbField("���ݱ��","ORDER_NUM",GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
    fieldList.add(field);

    field = new DbField("��������","DELIVERY_NAME",GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
    fieldList.add(field);
    
    field = new DbField("��������","DISTRIBUTE_MONEY",GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
    fieldList.add(field);
    
    field = new DbField("������","DISTRIBUTE_NUM",GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
    fieldList.add(field);
    
    field = new DbField("����ʱ��","CREATE_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    fieldList.add(field);
    
    this.fieldList  = fieldList;
  }
}
