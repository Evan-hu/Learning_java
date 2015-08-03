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
    
    field = new DbField("结算对象类型","OBJECT_TYPE",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 3, false);
    Map<String, Object> option = new LinkedHashMap<String, Object>();
    option.put("1", "供应商");
    option.put("2", "大客户");
    option.put("3", "门店");
    option.put("4", "物流机构");
    field.setLookupData(new LookupDataSet(option));
    field.setColumnDisplay(true,80,false);
    fieldList.add(field);
    
    field = new DbField("发生额类型","TYPE",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 3, false);
    option = new LinkedHashMap<String, Object>();
    option.put("1", "货款");
    option.put("2", "服务费");
    option.put("3", "期次结转");
    option.put("4", "其他费用");
    option.put("5", "物流费用");
    field.setLookupData(new LookupDataSet(option));
    field.setColumnDisplay(true,80,false);
    fieldList.add(field);
    
    field = new DbField("结算流水号","CODE",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
    field.setColumnDisplay(true,80,false);
    fieldList.add(field);
    
    field = new DbField("开始时间","START_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, 1, false);
    field.setInputCustomStyle("width:115px;");
    field.setColumnDisplay(true,80, false);
    fieldList.add(field);
    
    field = new DbField("结束时间","END_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, 2, false);
    field.setInputCustomStyle("width:115px;");
    field.setColumnDisplay(true,80, false);
    fieldList.add(field);
   
    field = new DbField("上期转出应收","PRE_RECEIVE_MONEY",GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 4, false);
  	field.setInputCustomStyle("width:80px;");
    field.setColumnDisplay(true,80, false);
    fieldList.add(field);

    field = new DbField("本期应收","RECEIVE_MONEY",GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 5, false);
  	field.setInputCustomStyle("width:80px;");
    field.setColumnDisplay(true,80, false);
    fieldList.add(field);
    
    field = new DbField("本期应付","PAY_MONEY",GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 6, false);
  	field.setInputCustomStyle("width:80px;");
    field.setColumnDisplay(true,80, false);
    fieldList.add(field);
    
    field = new DbField("本期应结金额","SHOULD_MONEY",GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 7, false);
  	field.setInputCustomStyle("width:80px;");
    field.setColumnDisplay(true,80, false);
    fieldList.add(field);
    
    field = new DbField("本期实结金额","REAL_MONEY",GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 8, false);
  	field.setInputCustomStyle("width:80px;");
    field.setColumnDisplay(true,80, false);
    fieldList.add(field);

    field = new DbField("结算时间","PAY_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, 10, false);
    field.setInputCustomStyle("width:115px;");
    field.setColumnDisplay(true,80, false);
    fieldList.add(field);
    
    field = new DbField("结算状态","STATE",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 9, false);
    option = new LinkedHashMap<String, Object>();
  	option.put("0", "待结算");
  	option.put("1", "待付款");
  	option.put("2", "已付款");
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
