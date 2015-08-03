package com.ga.erp.biz.settlement.settlement;

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

public class SettlementLogListView extends ListView  {

  public SettlementLogListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }
  
  @Override
  public void initField() throws Exception {
    
    List<DbField> fieldList = new ArrayList<DbField>();
    
    DbField field = new DbField("ID","SETTLEMENT_LOG_ID",GaConstant.DT_LONG);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "SETTLEMENT_LOG_ID");
    field.setColumnDisplay(false,0,true);
    fieldList.add(field);
    
//    field = new DbField("类型","SYS_CODE_ID",GaConstant.DT_INT);
//    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 0, false);
//    field.setLookupData(SystemUtil.getSettleTypeMap());
//    fieldList.add(field);

    field = new DbField("应收金额","RECEIVE_MONEY",GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 1, false);
    field.setInputCustomStyle("width:80px;");
    fieldList.add(field);
    
    field = new DbField("应付金额","PAY_MONEY",GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 2, false);
    field.setInputCustomStyle("width:80px;");
    fieldList.add(field);
   
    field = new DbField("发生额类型","TYPE",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 3, false);
    Map<String, Object> option = new LinkedHashMap<String, Object>();
  	option.put("1", "货款");
  	option.put("2", "服务费");
  	option.put("3", "期次结转");
  	option.put("4", "其他费用");
  	option.put("5", "物流费用");
  	field.setLookupData(new LookupDataSet(option));
    fieldList.add(field);
    
    field = new DbField("结算单据类型","OBJECT_TYPE",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 3, false);
    option = new LinkedHashMap<String, Object>();
    option.put("1", "供应商往来业务单");
    option.put("2", "门店往来业务单");
    option.put("3", "费用单");
    field.setLookupData(new LookupDataSet(option));
    fieldList.add(field);

    field = new DbField("单据ID","OBJECT_ID",GaConstant.DT_INT);
    fieldList.add(field);

    field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, 4, false);
    field.setInputCustomStyle("width:115px;");
    fieldList.add(field);
    
    this.fieldList  = fieldList;
  }
}
