package com.ga.erp.biz.stock.inventorybill.comm;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;

public class CommFormView extends FormView {

	
	public CommFormView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap);
	}

	@Override
	  public void initField() throws Exception {
		
		    List<DbField> dbFields = new ArrayList<DbField>();
			    
		  	DbField field = new DbField("ID","INVENTORY_COMM_ID",GaConstant.DT_LONG); 
		  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "INVENTORY_COMM_ID");
		  	field.setInput(GaConstant.INPUT_HIDDEN);
		  	field.setPK(true);
		  	dbFields.add(field);
		  	
		  	field = new DbField("商品ID","COMMODITY_ID", GaConstant.DT_LONG);
	      field.setPopSelect("commSelect", "COMMODITY_ID", false);
	      dbFields.add(field);
	      
//	      field = new DbField("库存商品ID","STOCK_COMM_ID", GaConstant.DT_LONG);
//        field.setPopSelect("commSelect", "STOCK_COMM_ID", false);
//        dbFields.add(field);
	      
			  field = new DbField("商品名<font color='red'>*</font>","COMMODITY_NAME",GaConstant.DT_STRING);
			  //选择控件   
			  field.setPopSelect("commSelect","COMMODITY_NAME",true,
		              "/stock/comm_main.htm",
		              "COMMODITY_ID,COMMODITY_NAME,STOCK_ID,NAME,CNT,cid_stockCommList",800,400);
			  dbFields.add(field);
		  	
			  field = new DbField("仓库ID","OBJECT_ID", GaConstant.DT_LONG);
			  field.setPopSelect("commSelect", "STOCK_ID", false);
	      dbFields.add(field);
		  	
			  field = new DbField("仓库名<font color='red'>*</font>","OBJECT_NAME",GaConstant.DT_STRING);
			  field.setPopSelect("commSelect", "NAME", true);
			  dbFields.add(field);
			  
			  //如果为门店
			  field = new DbField("门店ID","OBJECT_ID", GaConstant.DT_LONG);
        field.setPopSelect("commSelect", "STORE_ID", false);
        dbFields.add(field);
        
        field = new DbField("门店名<font color='red'>*</font>","OBJECT_NAME",GaConstant.DT_STRING);
        field.setPopSelect("commSelect", "STORE_NAME", true);
        dbFields.add(field);
			
//			  field = new DbField("盘点单ID","INVENTORY_BILL_ID", GaConstant.DT_LONG);
//	      field.setPopSelect("billselect", "INVENTORY_BILL_ID", false);
//	      dbFields.add(field);
//	        
//  			field = new DbField("盘点单流水号<font color='red'>*</font>","INVENTORY_FLOW_NUM",GaConstant.DT_STRING);
//  			field.setPopSelect("billselect","INVENTORY_FLOW_NUM",true,
//  		              "/stock/inventoryBill_main.htm",
//  		              "INVENTORY_BILL_ID,INVENTORY_FLOW_NUM,cid_BillList",700,300);
//  			dbFields.add(field);
  			
  			field = new DbField("系统库存","STOCK_INNAGE",GaConstant.DT_INT);
  			field.setPopSelect("commSelect", "CNT", true);
  			dbFields.add(field);
  			
  			field = new DbField("实盘库存","INVENTORY_INNAGE",GaConstant.DT_INT);
  		
  			dbFields.add(field);
			
	      this.fieldList = dbFields;
	    	 
	}
	
}
