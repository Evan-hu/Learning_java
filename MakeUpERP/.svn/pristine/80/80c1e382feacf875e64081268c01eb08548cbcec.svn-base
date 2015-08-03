package com.ga.erp.biz.settlement.suppliersettlement;

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

public class SuppSettListView extends ListView  {

  public SuppSettListView(String viewID,ModelMap modelMap) {
    super(viewID, modelMap,GaConstant.EDITMODE_DISP);
  }
  
  @Override
  public void initField() throws Exception {
    
    List<DbField> fieldList = new ArrayList<DbField>();
    
    DbField field = new DbField("供应商ID","SUPPLIER_ID",GaConstant.DT_LONG);
    field.setColumnDisplay(false,0,true);   
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "SUPPLIER_ID");
    field.setIsFilterCode(true);
    field.setPK(true);
    fieldList.add(field);
    
    field = new DbField("供应商名称", "SUPPLIER_NAME", GaConstant.DT_STRING);
    ActionButton action = new ActionButton(this.getClass(),"SUPPLIERDETAIL","{value}","/supplier/supplier_detail.htm",null);
    SubmitTool.submitToDialog(action, "SUPPLIER_NAME","供应商详情", 860, 550, this.modelMap.getPreNavInfoStr());
    StringDecorator decorator = new StringDecorator();
    decorator.setLinkDecorator(action);
    decorator.setStringFormat("<center>{value}</center>");
    action.bindTableRowData(this.viewID);
    action.addParam(GaConstant.FIXPARAM_EDITMODE,GaConstant.EDITMODE_EDIT);
    field.setColumnDecorator("", decorator);
    field.setInputCustomStyle("width:80px;");
    field.setAliasCode("s");
    field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
    fieldList.add(field);

    field = new DbField("结算方式","CHECK_TYPE",GaConstant.DT_INT);
    Map<String, Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("1", "临时指定");
    lookup.put("2", "指定账期");
    lookup.put("3", "指定日期");
    lookup.put("4", "货到付款");
    field.setLookupData(new LookupDataSet(lookup));
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 4, false);
    fieldList.add(field);
    
    field = new DbField("结算周期（天）","CHECK_CYCLE",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 5, false);
    fieldList.add(field);
    
    field = new DbField("结算日期","CHECK_MONTH",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 6, false);
    fieldList.add(field);
    
    field = new DbField("本期应付货款","MONEY",GaConstant.DT_MONEY);
    fieldList.add(field);
    
    this.fieldList  = fieldList;
  }
}
