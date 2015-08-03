package com.ga.erp.biz.store.returnOrder;

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

public class StoreReturnFormView extends FormView {

	public StoreReturnFormView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap);
	}
	
	@Override
	  public void initField() throws Exception {
		
		    List<DbField> dbFields = new ArrayList<DbField>();
			    
		  	DbField field = new DbField("ID","STORE_RETURN_ORDER_ID",GaConstant.DT_LONG); 
		  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "STORE_RETURN_ORDER_ID");
		  	field.setInput(GaConstant.INPUT_HIDDEN);
		  	dbFields.add(field);
		  	
			field = new DbField("订单编号","STORE_SALES_ORDER_NUM",GaConstant.DT_STRING);
		  	dbFields.add(field);
		  	
			field = new DbField("营业员","TRUENAME",GaConstant.DT_STRING);
			field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
			field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
		  	dbFields.add(field);
		  	
			field = new DbField("POS机","NAME",GaConstant.DT_STRING);

			dbFields.add(field);
			
			field = new DbField("订单原金额","ORDER_MONEY",GaConstant.DT_MONEY);
			dbFields.add(field);
			
			field = new DbField("退还现金","RETURN_CASH",GaConstant.DT_MONEY);
			dbFields.add(field);
			
			field = new DbField("退还银行卡金额","RETURN_BANK",GaConstant.DT_MONEY);
			dbFields.add(field);
			
			field = new DbField("退还积分","RETURN_SCORE",GaConstant.DT_INT);
			dbFields.add(field);
			
			field = new DbField("退还优惠券金额","RETURN_COUPON",GaConstant.DT_MONEY);
			dbFields.add(field);
			
			field = new DbField("退货日期","CREATE_TIME",GaConstant.DT_DATETIME);
			field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
			field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
			dbFields.add(field);

			field = new DbField("状态","STATE",GaConstant.DT_INT);
			field.setInput(GaConstant.INPUT_SELECT);
			Map<String, Object> lookup = new LinkedHashMap<String, Object>();
		    lookup.put("1", "待处理");
		    lookup.put("2", "已完成");
		    lookup.put("3", "拒绝处理");
		    field.setLookupData(new LookupDataSet(lookup));
		    field.setVerifyFormula("", true);
			dbFields.add(field);

			field = new DbField("退货原因","REASON",GaConstant.DT_STRING);
			field.setInput(GaConstant.INPUT_TEXTAREA);
			field.addInputAttributeMap("rows", "3");
			field.addInputAttributeMap("cols", "25");
			dbFields.add(field);
			
			field = new DbField("备注","NOTE",GaConstant.DT_STRING);
			field.setInput(GaConstant.INPUT_TEXTAREA);
			field.addInputAttributeMap("rows", "3");
			field.addInputAttributeMap("cols", "25");
			dbFields.add(field);

			this.fieldList = dbFields;
	    	 
	}

}
