package com.ga.erp.biz.modifyprice.comm;

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
			    
		  	DbField field = new DbField("ID","MODIFY_PRICE_COMMODITY_ID",GaConstant.DT_LONG); 
		  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "MODIFY_PRICE_COMMODITY_ID");
		  	field.setInput(GaConstant.INPUT_HIDDEN);
		  	field.setPK(true);
		  	dbFields.add(field);
		  	//1
		  	field = new DbField("商品ID","COMMODITY_ID", GaConstant.DT_LONG);
	      field.setPopSelect("mdcommSelect", "COMMODITY_ID", false);
	      dbFields.add(field);
	      //2
			  field = new DbField("对象商品ID","OBJECT_ID", GaConstant.DT_LONG);
			  field.setPopSelect("mdcommSelect", "STOCK_ID", false);
	      dbFields.add(field);
		  	//3
			  field = new DbField("对象商品名<font color='red'>*</font>","OBJECT_NAME",GaConstant.DT_STRING);
			  field.setPopSelect("mdcommSelect", "COMMODITY_NAME", true);
			  dbFields.add(field);
			  
			  //4
			  field = new DbField("原采购价","OLD_PURCHASE_PRICE",GaConstant.DT_MONEY);
        field.setPopSelect("mdcommSelect", "PURCHASE_PRICE", true);
        dbFields.add(field);
        //5
        field = new DbField("现采购价","PURCHASE_PRICE",GaConstant.DT_MONEY);
        dbFields.add(field);
  		 //6
        field = new DbField("原配送价","OLD_SEND_PRICE",GaConstant.DT_MONEY);
        field.setPopSelect("mdcommSelect", "SEND_PRICE", true);
        dbFields.add(field);
        //7
        field = new DbField("现配送价","SEND_PRICE",GaConstant.DT_MONEY);
        dbFields.add(field);
        //8
        field = new DbField("原零售价","OLD_SELL_PRICE",GaConstant.DT_MONEY);
        field.setPopSelect("mdcommSelect", "SELL_PRICE", true);
        dbFields.add(field);
        //9
        field = new DbField("现零售价","SELL_PRICE",GaConstant.DT_MONEY);
        dbFields.add(field);
        //10
        field = new DbField("原批发价","OLD_TRADE_PRICE",GaConstant.DT_MONEY);
        field.setPopSelect("mdcommSelect", "TRADE_PRICE", true);
        dbFields.add(field);
        //11
        field = new DbField("现批发价","TRADE_PRICE",GaConstant.DT_MONEY);
        dbFields.add(field);
        //12
        field = new DbField("原会员价","OLD_MEMBER_PRICE",GaConstant.DT_MONEY);
        field.setPopSelect("mdcommSelect", "MEMBER_PRICE", true);
        dbFields.add(field);
        //13
        field = new DbField("现会员价","MEMBER_PRICE",GaConstant.DT_MONEY);
        dbFields.add(field);
			
	      this.fieldList = dbFields;
	    	 
	}
	
}
