package com.ga.erp.biz.supplier.commodity;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.util.SystemUtil;

public class SupplierCommodityFormView extends FormView {

	public SupplierCommodityFormView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap);
	}

	@Override
	public void initField() throws Exception {

		List<DbField> fieldList = new ArrayList<DbField>();

		DbField field = new DbField("供应商商品ID", "SUPPLIER_COMMODITY_ID", GaConstant.DT_LONG);
		field.setInput(GaConstant.INPUT_HIDDEN);
	    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "SUPPLIER_COMMODITY_ID");
		fieldList.add(field);
		
		field = new DbField("商品ID","COMMODITY_ID", GaConstant.DT_LONG);
		field.setInput(GaConstant.INPUT_HIDDEN);
		field.setPopSelect("selectCommodity", "COMMODITY_ID", false);
    fieldList.add(field);
	     
    field = new DbField("商品名称","COMMODITY_NAME",GaConstant.DT_STRING);
    field.setPopSelect("selectCommodity","COMMODITY_NAME",true, "/comm/comm_main.htm", "COMMODITY_ID,COMMODITY_NAME,cid_commList",800,500);
    fieldList.add(field);
    
    field = new DbField("供货价","SUPPLY_PRICE",GaConstant.DT_MONEY);
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

    field = new DbField("最高供货价","MAX_SUPPLY_PRICE",GaConstant.DT_MONEY);
    fieldList.add(field);
   
    field = new DbField("最低供货价","MIN_SUPPLY_PRICE",GaConstant.DT_MONEY);
    fieldList.add(field);
   
    field = new DbField("最近供货价","RECENTLYy_SUPPLY_PRICE",GaConstant.DT_MONEY);
    fieldList.add(field);

    field = new DbField("成本价","COST_PRICE",GaConstant.DT_MONEY);
    fieldList.add(field);
    
    field = new DbField("供应优先级","RANK_NUM",GaConstant.DT_LONG);
    fieldList.add(field);
	    
		field = new DbField("状态", "STATE", GaConstant.DT_INT);
		field.setInput(GaConstant.INPUT_SELECT);
		field.setLookupData(SystemUtil.getStatusMap());
		field.setVerifyFormula("", true);
		fieldList.add(field);
	    
		this.fieldList = fieldList;

	}
}
