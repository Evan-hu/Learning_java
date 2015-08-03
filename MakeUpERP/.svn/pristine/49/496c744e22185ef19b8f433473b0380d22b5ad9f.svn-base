package com.ga.erp.biz.stock.inventorydiffbill;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;

public class DiffBillFormView extends FormView {

	
	public DiffBillFormView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap);
		// TODO Auto-generated constructor stub
	}


	@Override
	  public void initField() throws Exception {
		
		    List<DbField> dbFields = new ArrayList<DbField>();
			    
		  	DbField field = new DbField("ID","INVENTORY_DIFF_BILL_ID",GaConstant.DT_LONG); 
		  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "INVENTORY_DIFF_BILL_ID");
		  	field.setInput(GaConstant.INPUT_HIDDEN);
		  	dbFields.add(field);
		  	
		  	field = new DbField("盘点批次ID","INVENTORY_BATCH_ID",GaConstant.DT_LONG);
			dbFields.add(field);
			   
			field = new DbField("盘点批次编码<font color='red'>*</font>","INVENTORY_BATCH_NUM",GaConstant.DT_STRING);
			dbFields.add(field);
			
			field = new DbField("差异流水账号<font color='red'>*</font>","BILL_NUM",GaConstant.DT_STRING);
			field.getFieldVerify().setRequire(true);
  		dbFields.add(field);
  		  	
  		 field = new DbField("库存数量","STOCK_CNT",GaConstant.DT_MONEY);
  	  dbFields.add(field);
  	        
  	  field = new DbField("实盘数量","INVENTORY_CNT",GaConstant.DT_INT);
  	  dbFields.add(field); 
    	  
      field = new DbField("库存金额","STOCK_AMOUNT",GaConstant.DT_INT);
  	  dbFields.add(field);
  	      
  	  field = new DbField("实盘金额","INVENTORY_AMOUNT",GaConstant.DT_INT);
  	  dbFields.add(field);
  	  
			field = new DbField("盈亏数","DIFF_CNT",GaConstant.DT_INT);
			dbFields.add(field);
			
			field = new DbField("盈亏金额","DIFF_AMOUNT",GaConstant.DT_INT);
			dbFields.add(field);
			
			field = new DbField("处理人","TRUENAME",GaConstant.DT_STRING);
			field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);   
			field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);   
			field.setInputCustomStyle("width:80px;");
			dbFields.add(field);
			
			field = new DbField("处理时间","CREATE_TIME",GaConstant.DT_DATETIME);
			field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);   
			field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);   
			field.setInputCustomStyle("width:80px;");
			dbFields.add(field);	
			
			field = new DbField("备注", "NOTE", GaConstant.DT_STRING);
			field.setInput(GaConstant.INPUT_TEXTAREA);
			field.addInputAttributeMap("rows", "3");
			field.addInputAttributeMap("cols", "25");
			dbFields.add(field);
			
	        this.fieldList = dbFields;
	    	 
	}

}
