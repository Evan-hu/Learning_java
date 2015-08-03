package com.ga.erp.biz.supplier.contract;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class ContractFormView extends FormView  {
	
	public ContractFormView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap);
	}

	@Override
	  public void initField() throws Exception {
		
		    List<DbField> fieldList = new ArrayList<DbField>();
			    
		  	DbField field = new DbField("ID","CONTRACT_ID",GaConstant.DT_LONG); 
		  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "CONTRACT_ID");
		  	field.setInput(GaConstant.INPUT_HIDDEN);
		  	fieldList.add(field);
		  	
			field = new DbField("供应商ID","SUPPLIER_ID", GaConstant.DT_LONG);
	        field.setPopSelect("selectNew", "SUPPLIER_ID", false);
	        fieldList.add(field);
		  	
			field = new DbField("供应商<font color='red'>*</font>","SUPPLIER_NAME",GaConstant.DT_STRING);
			//选择控件   
			field.setPopSelect("selectNew","SUPPLIER_NAME",true,
		              "/supplier/supplier_main.htm",
		              "SUPPLIER_ID,SUPPLIER_NAME,cid_supplierList",800,400);
		  	fieldList.add(field);
		  	
			field = new DbField("合同编号<font color='red'>*</font>","CONTRACT_NUM",GaConstant.DT_INT);
		  	fieldList.add(field);
		  	
			field = new DbField("合同开始日期<font color='red'>*</font>","BEG_TIME",GaConstant.DT_DATETIME);
			field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss", false));
		    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
		    fieldList.add(field);
	  	
	    	field = new DbField("合同结束时间<font color='red'>*</font>","END_TIME",GaConstant.DT_DATETIME);
		    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss", false));
		    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
		  	fieldList.add(field);
		  	
			field = new DbField("内容<font color='red'>*</font>","CONTENT",GaConstant.DT_STRING);
			field.setInput(GaConstant.INPUT_TEXTAREA);
		    field.addInputAttributeMap("rows", "3");
		    field.addInputAttributeMap("cols", "84");
		  	fieldList.add(field);
		  	
			field = new DbField("创建人","TRUENAME",GaConstant.DT_STRING);
		    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
		  	fieldList.add(field);
		  	
			field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
			field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss", false));
		    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
		  	fieldList.add(field);
		  	
			field = new DbField("状态","STATE",GaConstant.DT_INT);
			field.setInput(GaConstant.INPUT_SELECT);
		  	field.setLookupData(SystemUtil.getStatusMap());
		  	field.setVerifyFormula("", true);
		    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		  	fieldList.add(field);
		  	
		  	this.fieldList = fieldList;
	  }
}
