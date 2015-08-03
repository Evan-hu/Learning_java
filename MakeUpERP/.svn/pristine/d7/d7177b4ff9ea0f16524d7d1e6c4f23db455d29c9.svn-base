package com.ga.erp.biz.stock.inventorydiffbill.comm;

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
		    
		  	DbField field = new DbField("ID","DIFF_BILL_COMM_ID",GaConstant.DT_LONG); 
		  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "DIFF_BILL_COMM_ID");
		  	field.setInput(GaConstant.INPUT_HIDDEN);
		  	field.setPK(true);
		  	dbFields.add(field);
		  	
//		  	field = new DbField("商品ID","COMM_ID", GaConstant.DT_LONG);
//	      dbFields.add(field);
		  	 
  			field = new DbField("商品名<font color='red'>*</font>","COMMODITY_NAME",GaConstant.DT_STRING);
  			dbFields.add(field);
  		  	
//  			field = new DbField("仓库ID","STOCK_ID", GaConstant.DT_LONG);
//  	    dbFields.add(field);
  		  	
  			field = new DbField("仓库名<font color='red'>*</font>","NAME",GaConstant.DT_STRING);
  			dbFields.add(field);
  			
//  			field = new DbField("差异单ID","INVENTORY_DIFF_BILL_ID", GaConstant.DT_LONG);
//  	    dbFields.add(field);
//  		  	
  			field = new DbField("差异单流水账号<font color='red'>*</font>","BILL_NUM",GaConstant.DT_STRING);
  			dbFields.add(field);
  			
  			field = new DbField("系统库存数","STOCK_CNT",GaConstant.DT_INT);
  			dbFields.add(field);
  			
  			field = new DbField("实盘数量","INVENTORY_CNT",GaConstant.DT_INT);
  			dbFields.add(field);
  			
  			field = new DbField("盈亏数","DIFF_CNT",GaConstant.DT_INT);
  			dbFields.add(field);
  			
  			field = new DbField("盘点进货价","PRICE",GaConstant.DT_MONEY);
        dbFields.add(field);
        
        field = new DbField("原库存进货价","OLD_PRICE",GaConstant.DT_MONEY);
        dbFields.add(field);
        
        field = new DbField("盈亏金额","DIFF_AMOUNT",GaConstant.DT_MONEY);
  			dbFields.add(field);
  			
			
  			field = new DbField("差异原因","RESON",GaConstant.DT_STRING);
  			field.setInput(GaConstant.INPUT_TEXTAREA);
        field.addInputAttributeMap("rows", "3");
        field.addInputAttributeMap("cols", "25");
  			dbFields.add(field);
  		
  	    this.fieldList = dbFields;
  	}

}
