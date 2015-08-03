package com.ga.erp.biz.modifyprice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;

public class ModifyPriceFormView extends FormView {

	public ModifyPriceFormView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap);
	}

	@Override
	  public void initField() throws Exception {
		  
	     List<DbField> dbFields = new ArrayList<DbField>();
		    
		   DbField field = new DbField("ID","MODIFY_PRICE_ID",GaConstant.DT_LONG); 
		   field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "MODIFY_PRICE_ID");
		   field.setInput(GaConstant.INPUT_HIDDEN);
		   field.setPK(true);
		   dbFields.add(field);
		   
		   field = new DbField("单据编码<font color='red'>*</font>","BILL_NUM",GaConstant.DT_STRING);
		   field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
		   dbFields.add(field);
		   
		   field = new DbField("对象ID","OBJECT_ID",GaConstant.DT_LONG);
		   field.setInput(GaConstant.INPUT_HIDDEN);
		   dbFields.add(field);
		   
		   //动态生成，门店，仓库，供应商
		   field = new DbField("对象名称","OBJECT_NAME",GaConstant.DT_STRING);
		   field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
		   dbFields.add(field);
		
		   field = new DbField("创建人","TRUENAME",GaConstant.DT_STRING);
		   field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		   field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
		   dbFields.add(field);

		   field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
		   field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
		   field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		   field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
		   dbFields.add(field);

	     field = new DbField("原因", "REASON", GaConstant.DT_STRING);
	     field.setInput(GaConstant.INPUT_TEXTAREA);
	     field.addInputAttributeMap("rows", "3");
	     field.addInputAttributeMap("cols", "25");
	     dbFields.add(field);
		   
		   field = new DbField("备注", "NOTE", GaConstant.DT_STRING);
		   field.setInput(GaConstant.INPUT_TEXTAREA);
		   field.addInputAttributeMap("rows", "3");
		   field.addInputAttributeMap("cols", "25");
		   dbFields.add(field);
		   
		   this.fieldList = dbFields;
	  }	
	
}


