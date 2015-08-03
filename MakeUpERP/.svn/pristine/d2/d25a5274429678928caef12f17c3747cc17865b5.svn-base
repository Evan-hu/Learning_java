package com.ga.erp.biz.store.storecataloguecomm;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;

public class StoreCatalogueCommListView extends ListView  {

  public StoreCatalogueCommListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }
  
  @Override
  public void initField() throws Exception {
    
    List<DbField> fieldList = new ArrayList<DbField>();
    
    DbField field = new DbField("门店采购商品ID","STORE_CATALOGUE_COMM_ID",GaConstant.DT_LONG);
    field.setColumnDisplay(false,0,true);   
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "STORE_CATALOGUE_COMM_ID");
    fieldList.add(field);
    
    field = new DbField("门店ID","STORE_ID",GaConstant.DT_LONG);
    field.setAliasCode("SCC");
    field.setIsFilterCode(true);
    field.setColumnDisplay(false,0,true);   
    fieldList.add(field);
    
    field = new DbField("门店名称","STORE_NAME",GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
    fieldList.add(field);
    
    field = new DbField("商品ID","COMMODITY_ID",GaConstant.DT_LONG);
    field.setColumnDisplay(false,0,true);   
    fieldList.add(field);

    field = new DbField("商品名称","COMMODITY_NAME",GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 1, false);
    fieldList.add(field);
    
    field = new DbField("采购价","PURCHASE_PRICE",GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_MONEY, 2, false);
    field.setAliasCode("SCC");
    field.setInputCustomStyle("width:80px;");
    fieldList.add(field);
    
    field = new DbField("零售价","RETAIL_PRICE",GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 3, false);
    field.setInputCustomStyle("width:80px;");
    field.setAliasCode("SCC");
    fieldList.add(field);

    field = new DbField("会员价","MEMBER_PRICE",GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 4, false);
    field.setInputCustomStyle("width:80px;");
    field.setAliasCode("SCC");
    fieldList.add(field);
    
    this.fieldList  = fieldList;
  }
}
