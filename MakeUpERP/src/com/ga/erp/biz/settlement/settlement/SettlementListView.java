package com.ga.erp.biz.settlement.settlement;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.control.StringDecorator;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;

public class SettlementListView extends ListView  {

  public SettlementListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }
  
  @Override
  public void initField() throws Exception {
    
    List<DbField> fieldList = new ArrayList<DbField>();
    
    DbField field = new DbField("ID","SETTLEMENT_ID",GaConstant.DT_LONG);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "SETTLEMENT_ID");
    field.setColumnDisplay(false,0,true);   
    fieldList.add(field);
    
    field = new DbField("�����������","OBJECT_TYPE",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 3, false);
    Map<String, Object> option = new LinkedHashMap<String, Object>();
    option.put("1", "��Ӧ��");
    option.put("2", "��ͻ�");
    option.put("3", "�ŵ�");
    option.put("4", "��������");
    field.setLookupData(new LookupDataSet(option));
    field.setColumnDisplay(true,80,false);
    fieldList.add(field);
    
    field = new DbField("����������","TYPE",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 3, false);
    option = new LinkedHashMap<String, Object>();
    option.put("1", "����");
    option.put("2", "�����");
    option.put("3", "�ڴν�ת");
    option.put("4", "��������");
    option.put("5", "��������");
    field.setLookupData(new LookupDataSet(option));
    field.setColumnDisplay(true,80,false);
    fieldList.add(field);
    
    field = new DbField("������ˮ��","CODE",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
    field.setColumnDisplay(true,80,false);
    fieldList.add(field);
    
    field = new DbField("��ʼʱ��","START_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, 1, false);
    field.setInputCustomStyle("width:115px;");
    field.setColumnDisplay(true,80, false);
    fieldList.add(field);
    
    field = new DbField("����ʱ��","END_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, 2, false);
    field.setInputCustomStyle("width:115px;");
    field.setColumnDisplay(true,80, false);
    fieldList.add(field);
   
    field = new DbField("����ת��Ӧ��","PRE_RECEIVE_MONEY",GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 4, false);
  	field.setInputCustomStyle("width:80px;");
    field.setColumnDisplay(true,80, false);
    fieldList.add(field);

    field = new DbField("����Ӧ��","RECEIVE_MONEY",GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 5, false);
  	field.setInputCustomStyle("width:80px;");
    field.setColumnDisplay(true,80, false);
    fieldList.add(field);
    
    field = new DbField("����Ӧ��","PAY_MONEY",GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 6, false);
  	field.setInputCustomStyle("width:80px;");
    field.setColumnDisplay(true,80, false);
    fieldList.add(field);
    
    field = new DbField("����Ӧ����","SHOULD_MONEY",GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 7, false);
  	field.setInputCustomStyle("width:80px;");
    field.setColumnDisplay(true,80, false);
    fieldList.add(field);
    
    field = new DbField("����ʵ����","REAL_MONEY",GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 8, false);
  	field.setInputCustomStyle("width:80px;");
    field.setColumnDisplay(true,80, false);
    fieldList.add(field);

    field = new DbField("����ʱ��","PAY_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, 10, false);
    field.setInputCustomStyle("width:115px;");
    field.setColumnDisplay(true,80, false);
    fieldList.add(field);
    
    field = new DbField("����״̬","STATE",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 9, false);
    option = new LinkedHashMap<String, Object>();
  	option.put("0", "������");
  	option.put("1", "������");
  	option.put("2", "�Ѹ���");
  	field.setLookupData(new LookupDataSet(option));
  	field.setInputCustomStyle("color:red;");
  	StringDecorator decorator = new StringDecorator();
  	decorator.setStringFormat("<font color='red'>{value}</font>");
  	field.setColumnDecorator("", decorator);
    field.setColumnDisplay(true, 80, false);
    fieldList.add(field);

    this.fieldList  = fieldList;
  }
}
