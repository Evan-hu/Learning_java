package com.ga.erp.biz.settlement.settlement;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;

public class SettlementLogFormView extends FormView {

	public SettlementLogFormView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap);
	}

	@Override
	public void initField() throws Exception {

		List<DbField> fieldList = new ArrayList<DbField>();

		DbField field = new DbField("ID", "SETTLEMENT_LOG_ID", GaConstant.DT_LONG);
		field.setInput(GaConstant.INPUT_HIDDEN);
	    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "SETTLEMENT_LOG_ID");
		fieldList.add(field);

//		field = new DbField("费用类型","NAME",GaConstant.DT_STRING);
//		field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
//		field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
//	    field.setColumnDisplay(true,80,false);
////	    field.setAliasCode("ST");
//	    fieldList.add(field);

	    field = new DbField("应收金额","RECEIVE_MONEY",GaConstant.DT_MONEY);
	    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
	    field.setColumnDisplay(true,80, false);
	    fieldList.add(field);
	    
	    field = new DbField("应付金额","PAY_MONEY",GaConstant.DT_MONEY);
	    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
	    field.setColumnDisplay(true,80, false);
	    fieldList.add(field);
	   
	    field = new DbField("发生额类型","TYPE",GaConstant.DT_INT);
	    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
	    field.setInput(GaConstant.INPUT_SELECT);
	    field.setColumnDisplay(true,80, false);
	    Map<String, Object> option = new LinkedHashMap<String, Object>();
	  	option.put("1", "货款");
	  	option.put("2", "服务费");
	  	option.put("3", "期次结转");
	 	option.put("4", "其他费用");
	  	field.setLookupData(new LookupDataSet(option));
	    fieldList.add(field);
	    
	    field = new DbField("结算单据类型","OBJECT_TYPE",GaConstant.DT_INT);
	    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
	    field.setColumnDisplay(true,80, false);
	    fieldList.add(field);

	    field = new DbField("单据ID","OBJECT_ID",GaConstant.DT_INT);
	    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
	    field.setColumnDisplay(true,80, false);
	    fieldList.add(field);
	    
	    field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
	    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
	    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
	    field.setQueryOpera(GaConstant.QUERY_BETWEEN, 10, false);
	    field.setInputCustomStyle("width:115px;");
	    field.setColumnDisplay(true,80, false);
	    fieldList.add(field);
	    
		field = new DbField("备注", "NOTE", GaConstant.DT_STRING);
		field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
		field.setInput(GaConstant.INPUT_TEXTAREA);
		field.addInputAttributeMap("rows", "3");
		field.addInputAttributeMap("cols", "98");
		fieldList.add(field);

		this.fieldList = fieldList;
	}
}
