package com.ga.erp.biz.store.storeorder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.control.StringDecorator;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class StoreOrderListView extends ListView {
	
  public StoreOrderListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {
	  
    List<DbField> dbFields = new ArrayList<DbField>();
	    
  	DbField field = new DbField("ID","STORE_ORDER_ID",GaConstant.DT_LONG); 
  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "STORE_ORDER_ID");
  	field.setColumnDisplay(false, 0, true);
  	field.setPK(true);
  	dbFields.add(field);
  	    
  	field = new DbField("门店ID","STORE_ID",GaConstant.DT_LONG);
  	field.setAliasCode("so");
  	field.setIsFilterCode(true);
  	field.setColumnDisplay(false, 0, true);
  	dbFields.add(field);
  	
  	field = new DbField("门店","STORE_NAME",GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 1, true);
    ActionButton action = new ActionButton(this.getClass(),"STORE_ID","{value}","/store/store_detail.htm",null);
    SubmitTool.submitToDialog(action, "STORE_NAME","门店详情", 1000, 380, this.modelMap.getPreNavInfoStr());
    StringDecorator decorator = new StringDecorator();
    decorator.setLinkDecorator(action);
    action.bindTableRowData(this.viewID);
    action.addParam(GaConstant.FIXPARAM_EDITMODE,GaConstant.EDITMODE_EDIT);
    field.setColumnDecorator("", decorator);
    field.setInputCustomStyle("width:90px");
  	dbFields.add(field);
  	
  	field = new DbField("订单号","STORE_ORDER_NUM",GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 2, true);
    action = new ActionButton(this.getClass(),"STORE_ORDER_NUM","{value}","/store/storeorder_detail.htm",null);
    SubmitTool.submitToDialog(action, "STORE_ORDER_NUM","订单详情", 800, 440, this.modelMap.getPreNavInfoStr());
    decorator = new StringDecorator();
    decorator.setLinkDecorator(action);
    action.bindTableRowData(this.viewID);
    action.addParam(GaConstant.FIXPARAM_EDITMODE,GaConstant.EDITMODE_EDIT);
    field.setColumnDecorator("", decorator);
    field.setInputCustomStyle("width:90px");
  	dbFields.add(field);
  	
  /*	field = new DbField("关联订单ID","P_STORE_ORDER_ID",GaConstant.DT_LONG);
  	field.setColumnDisplay(false, 0, true);
  	dbFields.add(field);
  	
  	field = new DbField("关联订单号","P_STORE_ORDER_NUM",GaConstant.DT_STRING);
    action = new ActionButton(this.getClass(),"P_STORE_ORDER_ID","{value}","/store/storeorder_detail.htm",null);
    SubmitTool.submitToDialog(action, "P_STORE_ORDER_NUM","关联订单详情", 800, 320, this.modelMap.getPreNavInfoStr());
    decorator = new StringDecorator();
    decorator.setLinkDecorator(action);
    action.bindTableRowData(this.viewID);
    action.addParam(GaConstant.FIXPARAM_EDITMODE,GaConstant.EDITMODE_EDIT);
    field.setColumnDecorator("", decorator);
    field.setInputCustomStyle("width:90px");
  	dbFields.add(field);*/
  	
  	field = new DbField("结算ID","SETTLEMENT_ID",GaConstant.DT_LONG);
  	field.setColumnDisplay(false, 0, true);
  	field.setAliasCode("se");
  	dbFields.add(field);
  	
  	field = new DbField("结算流水号","CODE",GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 4, true);
    action = new ActionButton(this.getClass(),"SETTLEMENT_ID","{value}","/settlement/settlement_detail.htm",null);
    SubmitTool.submitToDialog(action, "CODE","结算详情", 1000, 570, this.modelMap.getPreNavInfoStr());
    decorator = new StringDecorator();
    decorator.setLinkDecorator(action);
    action.bindTableRowData(this.viewID);
    action.addParam(GaConstant.FIXPARAM_EDITMODE,GaConstant.EDITMODE_EDIT);
    field.setColumnDecorator("", decorator);
    field.setInputCustomStyle("width:90px");
  	dbFields.add(field);
  	
  	field = new DbField("订单金额","ORDER_MONEY",GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_MONEY, 5, true);
    field.setInputCustomStyle("width:90px");
  	dbFields.add(field);
  	
  	field = new DbField("实际金额","ACTURE_MONEY",GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_MONEY, 6, true);
    field.setInputCustomStyle("width:90px");
    dbFields.add(field);
    
    field = new DbField("配送方式","DELIVERY_TYPE",GaConstant.DT_INT);
    Map<String, Object> option = new LinkedHashMap<String, Object>();
    option.put("1", "正常配送");
    option.put("2", "货到齐后统一配送");
    option.put("3", "分单配送");
    field.setLookupData(new LookupDataSet(option));
    field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT, 7, true);
    dbFields.add(field);
    
    field = new DbField("预计配送时间","PREDICT_DILIVERY_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_DATETIME, 8, true);
    dbFields.add(field);
    
    field = new DbField("收货时间","RECEIVE_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_DATETIME, 9, true);
    dbFields.add(field);
    
    field = new DbField("创建人","TRUENAME",GaConstant.DT_STRING);
    field.setInputCustomStyle("width:80px;");
    field.setAliasCode("so");
    dbFields.add(field);
  
    field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
    field.setAliasCode("so");
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    dbFields.add(field);
    
    field = new DbField("状态","STATE",GaConstant.DT_INT);
    field.setLookupData(SystemUtil.getStatusMap());
    field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT,12,true);
    dbFields.add(field);
    
    field = new DbField("单据状态","BILL_STATE",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT,13, true);
    Map<String, Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("3", "全部发货");
    lookup.put("2", "部分发货");
    lookup.put("1", "待处理");
    field.setAliasCode("so");
    field.setLookupData(new LookupDataSet(lookup));
    dbFields.add(field);
    
    field = new DbField("删除状态", "DELETE_STATE", GaConstant.DT_LONG);
    field.setColumnDisplay(false,0,true);   
    field.setIsFilterCode(true);
    dbFields.add(field);
    
    field = new DbField("审核状态", "CHECK_STATE", GaConstant.DT_LONG);
    field.setColumnDisplay(false,0,true);   
    field.setIsFilterCode(true);
    dbFields.add(field);
    
    this.fieldList = dbFields;
    
  }
  
}
