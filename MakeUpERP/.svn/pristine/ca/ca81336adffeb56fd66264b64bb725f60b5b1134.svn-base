package com.ga.erp.biz.settlement.costorder;

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
import com.ga.erp.util.SystemUtil;

public class CostOrderListView extends ListView {
	
  public CostOrderListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {
	  
    List<DbField> dbFields = new ArrayList<DbField>();
	    
  	DbField field = new DbField("费用单ID","COST_ORDER_ID",GaConstant.DT_LONG); 
  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "COST_ORDER_ID");
  	field.setColumnDisplay(false, 0, true);
  	field.setPK(true);
  	dbFields.add(field);
  	
  	field = new DbField("业务单据号","COST_ORDER_NUM",GaConstant.DT_STRING); 
  	field.setQueryOpera(GaConstant.QUERY_LIKE,GaConstant.INPUT_TEXT, 1, true);
    dbFields.add(field);
  	    
  	field = new DbField("编码ID","SYS_CODE_ID",GaConstant.DT_INT);
  	field.setColumnDisplay(false, 0, true);
  	dbFields.add(field);
  	
  	field = new DbField("业务名称", "SYS_CODE_NAME", GaConstant.DT_STRING);
    field.setInputCustomStyle("width:70px");
    dbFields.add(field);
    
    field = new DbField("对象ID","OBJECT_ID",GaConstant.DT_LONG);
    field.setColumnDisplay(false, 0, true);
    dbFields.add(field);
    
  	field = new DbField("对象类型","OBJECT_TYPE",GaConstant.DT_INT);
  	field.setColumnDisplay(false, 0, true);
  	Map<String, Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("1", "供应商");
    lookup.put("2", "大客户");
    lookup.put("3", "门店");
    field.setLookupData(new LookupDataSet(lookup));
  	dbFields.add(field);
  	
  	field = new DbField("对象名称", "OBJECT_NAME", GaConstant.DT_STRING);
    dbFields.add(field);

    field = new DbField("发生金额","MONEY",GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_MONEY, 4, true);
    dbFields.add(field);
    
    field = new DbField("收支方式","BUDGET",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_LIKE,GaConstant.INPUT_TEXT, 5, true);
    Map<String, Object> op = new LinkedHashMap<String, Object>();
    op.put("1", "收入");
    op.put("2", "支出");
    field.setLookupData(new LookupDataSet(op));
    dbFields.add(field);

    field = new DbField("付款时间","PAY_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_DATETIME, 6, true);
    dbFields.add(field);
    
    field = new DbField("审核状态", "CHECK_STATE", GaConstant.DT_LONG);
    field.setIsFilterCode(true);
    field.setLookupData(SystemUtil.getAuditsMap());
    dbFields.add(field);
    
    field = new DbField("删除状态", "DELETE_STATE", GaConstant.DT_LONG);
    field.setIsFilterCode(true);
    field.setLookupData(SystemUtil.getStatusMap());
    dbFields.add(field);

    field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
    field.setIsFilterCode(true);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_DATETIME, 7, true);
    dbFields.add(field);
    
    field = new DbField("创建人","TRUENAME",GaConstant.DT_STRING);
    field.setInputCustomStyle("width:80px;");
    dbFields.add(field);

    field = new DbField("状态","STATE",GaConstant.DT_LONG);
    field.setAliasCode("co");
    Map<String, Object> stateMap = new LinkedHashMap<String, Object>();
    stateMap.put("0", "待结算");
    stateMap.put("1", "待付款");
    stateMap.put("2", "已结算");
    field.setLookupData(new LookupDataSet(stateMap));
    dbFields.add(field);
  
    this.fieldList = dbFields;
    
  }
  
}
