package com.ga.erp.biz.system.area.catalogue;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.DbFieldFormat;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;

public class CatalogueListView extends ListView  {

  public CatalogueListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }
  
  @Override
  public void initField() throws Exception {
    
    List<DbField> fieldList = new ArrayList<DbField>();
    
    DbField field = new DbField("采购目录ID","CATALOGUE_ID",GaConstant.DT_LONG);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "CATALOGUE_ID");
    field.setColumnDisplay(false,0,true);   
    fieldList.add(field);
    
    field = new DbField("地区ID","AREA_ID",GaConstant.DT_LONG);
    field.setColumnDisplay(false,0,true);   
    field.setQueryParamFormSource(GaConstant.INPUT_REQUEST, "AREA_ID");
    fieldList.add(field);
    
    field = new DbField("地区名称","AREA_NAME",GaConstant.DT_STRING);
    fieldList.add(field);
    
    field = new DbField("采购目录名称","NAME",GaConstant.DT_STRING);
    fieldList.add(field);
    
    field = new DbField("创建者","USERNAME",GaConstant.DT_STRING);
    fieldList.add(field);

    field = new DbField("创建日期","CREATE_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new DbFieldFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    fieldList.add(field);
    
    this.fieldList  = fieldList;
  }
}
