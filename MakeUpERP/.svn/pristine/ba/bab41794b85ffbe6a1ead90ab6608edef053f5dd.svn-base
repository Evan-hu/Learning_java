package com.ga.erp.biz.settlement.storesettlement;

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

public class StoreSettListView extends ListView {
	
  public StoreSettListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {
	  
    List<DbField> dbFields = new ArrayList<DbField>();
	    
  	DbField field = new DbField("ID","STORE_ID",GaConstant.DT_LONG); 
  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "STORE_ID");
  	field.setColumnDisplay(false, 0, true);
  	field.setPK(true);
  	field.setIsFilterCode(true);
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
  	
  	field = new DbField("结算方式","CHECK_TYPE",GaConstant.DT_INT);
    Map<String, Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("1", "临时指定");
    lookup.put("2", "指定账期");
    lookup.put("3", "指定日期");
    lookup.put("4", "货到付款");
    field.setLookupData(new LookupDataSet(lookup));
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 4, false);
    dbFields.add(field);
    
    field = new DbField("结算周期（天）","CHECK_CYCLE",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 5, false);
    dbFields.add(field);
    
    field = new DbField("结算日期","CHECK_MONTH",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 6, false);
    dbFields.add(field);
    
    field = new DbField("本期应付金额","MONEY",GaConstant.DT_MONEY);
    dbFields.add(field);
    
    this.fieldList = dbFields;
  }
  
}
