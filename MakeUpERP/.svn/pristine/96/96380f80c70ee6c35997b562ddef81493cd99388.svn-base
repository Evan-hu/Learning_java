package com.ga.erp.biz.system.area.cataloguecomm;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;

public class CatalogueCommFormView extends FormView {

	public CatalogueCommFormView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap);
	}

	@Override
	public void initField() throws Exception {

		List<DbField> fieldList = new ArrayList<DbField>();

		DbField field = new DbField("采购商品ID", "CATALOGUE_COMM_ID", GaConstant.DT_LONG);
		field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "CATALOGUE_COMM_ID");
		field.setInput(GaConstant.INPUT_HIDDEN);
		fieldList.add(field);
		
		field = new DbField("采购目录ID", "CATALOGUE_ID", GaConstant.DT_LONG);
		field.setInput(GaConstant.INPUT_HIDDEN);
		fieldList.add(field);
		
		field = new DbField("采购单名称","NAME",GaConstant.DT_STRING);
		field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
		field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		fieldList.add(field);
		
		field = new DbField("商品ID","COMMODITY_ID", GaConstant.DT_LONG);
		field.setPopSelect("selectCommodity", "COMMODITY_ID", false);
	    fieldList.add(field);
	    
        field = new DbField("商品名称","COMMODITY_NAME",GaConstant.DT_STRING);
        field.setPopSelect("selectCommodity","COMMODITY_NAME",true,
        		"/comm/comm_main.htm", "COMMODITY_ID,COMMODITY_NAME," +
        				"PURCHASE_PRICE,SELL_PRICE,MEMBER_PRICE,cid_commList",800,500);
        fieldList.add(field);
	    
        field = new DbField("采购价","PURCHASE_PRICE",GaConstant.DT_MONEY);
    	field.setPopSelect("selectCommodity", "PURCHASE_PRICE", true);
        fieldList.add(field);
       
        field = new DbField("售价","RETAIL_PRICE",GaConstant.DT_MONEY);
    	field.setPopSelect("selectCommodity", "SELL_PRICE", true);
        fieldList.add(field);
       
        field = new DbField("会员价","MEMBER_PRICE",GaConstant.DT_MONEY);
    	field.setPopSelect("selectCommodity", "MEMBER_PRICE", true);
        fieldList.add(field);
	    
		this.fieldList = fieldList;

	}
}
