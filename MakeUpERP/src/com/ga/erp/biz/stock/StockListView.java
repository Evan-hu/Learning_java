package com.ga.erp.biz.stock;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;

public class StockListView extends ListView{

	public StockListView(String viewID, ModelMap modelMap) {
		
		super(viewID, modelMap,GaConstant.EDITMODE_DISP);
	}
	  @Override
	  public void initField() throws Exception {
		  
	       List<DbField> dbFields = new ArrayList<DbField>();
		    
		  	DbField field = new DbField("ID","STOCK_ID",GaConstant.DT_LONG); 
		  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "STOCK_ID");
		  	field.setColumnDisplay(false, 0, true);
		  	dbFields.add(field);
		  	
  			field = new DbField("仓库名","NAME",GaConstant.DT_STRING);
  			field.setQueryOpera(GaConstant.QUERY_LIKE, 1, true);
  			field.setInputCustomStyle("width:80px;");
  		  dbFields.add(field);
  		  	
  			field = new DbField("仓库面积","ACREAGE",GaConstant.DT_INT);
  			field.setQueryOpera(GaConstant.QUERY_EQUALS, 2, true);
  			field.setInputCustomStyle("width:80px;");
  		  dbFields.add(field);
  		  	
  			field = new DbField("所在地区","AREA_NAME",GaConstant.DT_STRING);
  			field.setQueryOpera(GaConstant.QUERY_LIKE, 3, true);
  			field.setInputCustomStyle("width:80px;");
  			dbFields.add(field);
			//选择地区
//			field.setQueryOpera(GaConstant.QUERY_LIKE, GaConstant.INPUT_POPSELECT, 1, false, true);
//			field.setPopSelect("SUPPLIERSELECT","SUPPLIER_NAME",true,
//		              "/supplier/supplier_main.htm",
//		              "SUPPLIER_ID,SUPPLIER_NAME,cid_supplierList",800,400);
//			field.setInputCustomStyle("width:100px;");
//		  dbFields.add(field);
	  	  this.fieldList = dbFields;
	  }
	

}
