package com.ga.erp.biz.supplier.commodity;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.util.SystemUtil;

public class SupplierCommodityListView extends ListView  {

  public SupplierCommodityListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }
  
  @Override
  public void initField() throws Exception {
    
    List<DbField> fieldList = new ArrayList<DbField>();
    
    DbField field = new DbField("供应商商品ID","SUPPLIER_COMMODITY_ID",GaConstant.DT_LONG);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "SUPPLIER_COMMODITY_ID");
    field.setColumnDisplay(false, 0, true);
    fieldList.add(field);
    
//    field = new DbField("供应商ID", "SUPPLIER_ID", GaConstant.DT_LONG);
//    field.setColumnDisplay(false,0,true);
//    field.setAliasCode("SC");
//    field.setIsFilterCode(true);
//    fieldList.add(field);
    
    field = new DbField("供应商","SUPPLIER_NAME",GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_POPSELECT, 1, false, true);
    field.setPopSelect("SUPPLIERSELECT","SUPPLIER_NAME",true,
                "/supplier/supplier_main.htm",
                "SUPPLIER_ID,SUPPLIER_NAME,cid_supplierList",800,400);
    field.setInputCustomStyle("width:100px;");
    fieldList.add(field);
    
    field = new DbField("商品ID","COMMODITY_ID",GaConstant.DT_LONG);
    field.setColumnDisplay(false, 0, false);
    field.setAliasCode("SC");
    fieldList.add(field);
    
    field = new DbField("商品名称","COMMODITY_NAME",GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
    fieldList.add(field);
    
    field = new DbField("供货价","SUPPLY_PRICE",GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 1, false);
    field.setInputCustomStyle("width:80px;");
    fieldList.add(field);
    
    field = new DbField("最高供货价","MAX_SUPPLY_PRICE",GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 2, false);
    field.setInputCustomStyle("width:80px;");
    fieldList.add(field);
   
    field = new DbField("最低供货价","MIN_SUPPLY_PRICE",GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 3, false);
    field.setInputCustomStyle("width:80px;");
    fieldList.add(field);
   
    field = new DbField("最近供货价","RECENTLYy_SUPPLY_PRICE",GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 4, false);
    field.setInputCustomStyle("width:80px;");
    fieldList.add(field);

    field = new DbField("供应优先级","RANK_NUM",GaConstant.DT_LONG);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 5, false);
    field.setInputCustomStyle("width:80px;");
    fieldList.add(field);
    
    field = new DbField("成本价","COST_PRICE",GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 6, false);
    field.setInputCustomStyle("width:80px;");
    fieldList.add(field);

    field = new DbField("状态", "STATE", GaConstant.DT_INT);
    field.setLookupData(SystemUtil.getStatusMap());
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 7, false);
    field.setAliasCode("SC");
    fieldList.add(field);
    
    this.fieldList  = fieldList;
  }
}
