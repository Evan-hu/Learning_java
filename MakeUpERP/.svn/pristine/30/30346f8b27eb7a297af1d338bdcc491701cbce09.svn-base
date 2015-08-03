package com.ga.erp.biz.stock.innageadjust;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;

public class AdjustFormView extends FormView {

	public AdjustFormView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap);
	}
	
	@Override
	  public void initField() throws Exception {
		List<DbField> dbFields = new ArrayList<DbField>();
	    
	  	DbField field = new DbField("ID","INNAGE_ADJUST_ID",GaConstant.DT_LONG); 
	  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "INNAGE_ADJUST_ID");
	    field.setInput(GaConstant.INPUT_HIDDEN);
	  	dbFields.add(field);
	  	
	  	field = new DbField("仓库ID","STOCK_ID", GaConstant.DT_LONG);
        field.setPopSelect("stockselect", "STOCK_ID", false);
        dbFields.add(field);
	  	
		field = new DbField("仓库名<font color='red'>*</font>","NAME",GaConstant.DT_STRING);
		field.setPopSelect("stockselect","NAME",true,
	              "/stock/stock_main.htm",
	              "STOCK_ID,NAME,cid_stockList",700,300);
		dbFields.add(field);
	  	
		field = new DbField("单据ID","BILLS_ID",GaConstant.DT_INT);
	  	dbFields.add(field);
	  	
		field = new DbField("单据编码","BILLS_NUM",GaConstant.DT_INT);
		dbFields.add(field);
		
		field = new DbField("方式","WAY",GaConstant.DT_INT);
		dbFields.add(field);
		
		field = new DbField("原因","REASON",GaConstant.DT_STRING);
		dbFields.add(field);
		
		field = new DbField("经手人","TRUENAME",GaConstant.DT_STRING);
		field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);   
		field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
		dbFields.add(field);
		   
		field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
		field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
		field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);   
		field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
		dbFields.add(field);
		
		field = new DbField("备注", "NOTE", GaConstant.DT_STRING);
		field.setInput(GaConstant.INPUT_TEXTAREA);
		field.addInputAttributeMap("rows", "3");
		field.addInputAttributeMap("cols", "25");
		dbFields.add(field);
			
		this.fieldList = dbFields;
	    	 
	}

}
