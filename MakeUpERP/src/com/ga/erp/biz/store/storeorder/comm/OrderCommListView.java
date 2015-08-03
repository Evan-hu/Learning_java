package com.ga.erp.biz.store.storeorder.comm;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.StringDecorator;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.util.SystemUtil;

public class OrderCommListView extends ListView {
	
  public OrderCommListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {
	  
    List<DbField> dbFields = new ArrayList<DbField>();
	    
  	DbField field = new DbField("ID","STORE_ORDER_COMM_ID",GaConstant.DT_LONG); 
  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "STORE_ORDER_COMM_ID");
  	field.setColumnDisplay(false, 0, true);
  	dbFields.add(field);
  	    
  	field = new DbField("门店ID","STORE_ID",GaConstant.DT_LONG);
  	field.setColumnDisplay(false, 0, true);
  	dbFields.add(field);
  	
  	field = new DbField("门店名","STORE_NAME",GaConstant.DT_STRING);
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
  	
  	field = new DbField("ID","COMMODITY_ID",GaConstant.DT_LONG);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "COMMODITY_ID");
    field.setColumnDisplay(false, 0, true);
    dbFields.add(field);
  
    field = new DbField("商品名称", "COMMODITY_NAME", GaConstant.DT_STRING);
    action = new ActionButton(this.getClass(),"COMMODITY_ID","{value}","/comm/comm_detail.htm",null);
    SubmitTool.submitToDialog(action, "COMMODITY_NAME","商品详情", 1000, 600, this.modelMap.getPreNavInfoStr());
    decorator = new StringDecorator();
    decorator.setLinkDecorator(action);
    decorator.setStringFormat("<font color='red'>{value}</font>");
    action.bindTableRowData(this.viewID);
    action.addParam(GaConstant.FIXPARAM_EDITMODE,GaConstant.EDITMODE_EDIT);
    field.setColumnDecorator("", decorator);
    field.setColumnDisplay(true, 80, true);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 2, false);
    field.setInputCustomStyle("width:80px;");
    dbFields.add(field);
    
    field = new DbField("配送价", "DISTRIBUTE_PRICE", GaConstant.DT_MONEY);
    dbFields.add(field);
    
    field = new DbField("申请数量", "ORDER_CNT", GaConstant.DT_INT);
    dbFields.add(field);

    field = new DbField("实际数量", "ACTUAL_CNT", GaConstant.DT_INT);
    dbFields.add(field);
    
    field = new DbField("赠送数量", "SEND_CNT", GaConstant.DT_INT);
    dbFields.add(field);
    
    field = new DbField("状态", "STATE", GaConstant.DT_INT);
    field.setAliasCode("soc");
    field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT, 3, true);
    field.setLookupData(SystemUtil.getStatusMap());
    dbFields.add(field);
    
    this.fieldList = dbFields;
    
  }
  
}
