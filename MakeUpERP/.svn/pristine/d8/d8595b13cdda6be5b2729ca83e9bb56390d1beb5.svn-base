package com.ga.erp.biz.stock.shelf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.util.SystemUtil;

public class ShelfFormView extends FormView{

  public ShelfFormView(String viewID, ModelMap modelMap) {
	super(viewID, modelMap);
	}
  
  public void initField() throws Exception {
	  
    List<DbField> dbFields = new ArrayList<DbField>();
    DbField field = new DbField("ID","SHELF_ID",GaConstant.DT_LONG); 
    field.setInput(GaConstant.INPUT_HIDDEN);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "SHELF_ID");
    dbFields.add(field);
    
	field = new DbField("货架编号<font color='red'>*</font>", "SHELF_NUM", GaConstant.DT_INT);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setInputMode(GaConstant.EDITMODE_DISP, GaConstant.INPUTMODE_READONLY);
  	dbFields.add(field);
  	
  	field = new DbField("上级货架ID","P_SHELF_ID", GaConstant.DT_LONG);
      if(this.viewEditMode == GaConstant.EDITMODE_NEW){
        field.setPopSelect("selectNew", "SHELF_ID", false);
      }else{
        field.setPopSelect("selectEdit", "SHELF_ID", false);
      }
    dbFields.add(field);
     
    field = new DbField("上级货架编号<font color='red'>*</font>","P_SHELF_NUM",GaConstant.DT_INT);		
      if(this.viewEditMode == GaConstant.EDITMODE_NEW){
          field.setPopSelect("selectNew","SHELF_NUM",true,
              "/stock/shelf_sele.htm",
              "SHELF_ID,SHEL_NUM,cid_shelfTree",300,400);
        }else{
          field.setPopSelect("selectEdit","SHELF_NUM",true,
              "/stock/shelf_sele.htm",
              "SHELF_ID,SHELF_NUM,cid_shelfTree",300,400);
        }
      dbFields.add(field);
      
    field = new DbField("仓库ID","STOCKS_ID", GaConstant.DT_LONG);
    if(this.viewEditMode == GaConstant.EDITMODE_NEW){
        field.setPopSelect("stockselect", "STOCK_ID", false);
      }else{
        field.setPopSelect("stockEdit", "STOCK_ID", false);
      }
    dbFields.add(field);
    
    field = new DbField("仓库<font color='red'>*</font>","NAME",GaConstant.DT_STRING);
    if(this.viewEditMode == GaConstant.EDITMODE_NEW){
      field.setPopSelect("stockselect","NAME",true,
          "/stock/stock_main.htm",
          "STOCK_ID,NAME,cid_stockList",600,300);
    }else{
      field.setPopSelect("stockEdit","NAME",true,
          "/stock/stock_main.htm",
          "STOCK_ID,NAME,cid_stockList",600,300);
    }
    dbFields.add(field);
	
  	    
    field = new DbField("类型","TYPE",GaConstant.DT_INT);
    field.setInput(GaConstant.INPUT_SELECT);
    Map<String, Object> map=new HashMap<String,Object>();
	map.put("1", "货架");map.put("2", "货位");
	field.setLookupData(new LookupDataSet(map));
    field.setVerifyFormula("", true);
    dbFields.add(field);
    
    field = new DbField("状态","STATE",GaConstant.DT_INT);
    field.setInput(GaConstant.INPUT_SELECT);
    field.setLookupData(SystemUtil.getStatusMap());
    field.setVerifyFormula("", true);
    dbFields.add(field);
    
    field = new DbField("备注", "NOTE", GaConstant.DT_STRING);
    field.setInput(GaConstant.INPUT_TEXTAREA);
    field.addInputAttributeMap("rows", "3");
    field.addInputAttributeMap("cols", "30");
    dbFields.add(field);
    
    
    this.fieldList = dbFields;
    
  }
	
}
