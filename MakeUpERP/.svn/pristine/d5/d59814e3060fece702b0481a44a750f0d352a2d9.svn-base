package com.ga.erp.biz.purchase.list;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;

public class PurListFormView extends FormView {

	public PurListFormView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap);
	}

	 public void initField() throws Exception {
	   
	   List<DbField> dbFields = new ArrayList<DbField>();
     
     DbField field = new DbField("ID","PURCHASE_LIST_ID",GaConstant.DT_LONG); 
     field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "PURCHASE_LIST_ID");
     field.setInput(GaConstant.INPUT_HIDDEN);
     dbFields.add(field);
   
     field = new DbField("采购订单id","PURCHASE_ORDER_ID", GaConstant.DT_LONG);
     field.setPopSelect("purOrderselect", "PURCHASE_ORDER_ID", false);
     dbFields.add(field);
       
     field = new DbField("采购订单编号","PURCHASE_ORDER_NUM",GaConstant.DT_STRING);
     field.setPopSelect("purOrderselect","PURCHASE_ORDER_NUM",true,
                 "/purchase/purOrder_main.htm",
                 "PURCHASE_ORDER_ID,PURCHASE_ORDER_NUM,cid_purOrderList",800,400);
     dbFields.add(field);
     
     field = new DbField("商品ID","COMMODITY_ID", GaConstant.DT_LONG);
     field.setPopSelect("commSelect", "COMMODITY_ID", false);
     dbFields.add(field);
     
     field = new DbField("商品名<font color='red'>*</font>","COMMODITY_NAME",GaConstant.DT_STRING);
   //选择控件   
     field.setPopSelect("commSelect","COMMODITY_NAME",true,
               "/comm/comm_main.htm",
               "COMMODITY_ID,COMMODITY_NAME,cid_commList",800,400);
     dbFields.add(field);
     
//     field = new DbField("供应商商品ID","SUPPLIER_COMMODITY_ID", GaConstant.DT_LONG);
//     field.setPopSelect("scommSelect", "SUPPLIER_COMMODITY_ID", false);
//     dbFields.add(field);
     
     field = new DbField("供应商商品ID<font color='red'>*</font>","SUPPLIER_COMMODITY_ID",GaConstant.DT_LONG);
     field.setPopSelect("scommSelect","SUPPLIER_COMMODITY_ID",true,
               "/supplier/commodity/suppliercommodity_main.htm",
               "SUPPLIER_COMMODITY_ID,cid_supplierCommodityListView",800,400);
     dbFields.add(field);
     
     field = new DbField("供应商ID","SUPPLIER_ID", GaConstant.DT_LONG);
     field.setPopSelect("selectNew", "SUPPLIER_ID", false);
     dbFields.add(field);
     
     field = new DbField("供应商<font color='red'>*</font>","SUPPLIER_NAME",GaConstant.DT_STRING);
   //选择控件   
     field.setPopSelect("selectNew","SUPPLIER_NAME",true,
               "/supplier/supplier_main.htm",
               "SUPPLIER_ID,SUPPLIER_NAME,cid_supplierList",800,400);
     dbFields.add(field);
   
     field = new DbField("采购数","PURCHASE_CNT",GaConstant.DT_INT);
     dbFields.add(field);
   
     field = new DbField("采购价格","PURCHASE_PRICE",GaConstant.DT_MONEY);
     dbFields.add(field);
   
     
     field = new DbField("供应商供货数","SUPPLY_CNT",GaConstant.DT_INT);
     dbFields.add(field);
     
     field = new DbField("实际到货数","REALITY_CNT",GaConstant.DT_INT);
     dbFields.add(field);
     
     field = new DbField("赠送数","SEND_CNT",GaConstant.DT_INT);
     dbFields.add(field);
     
     field = new DbField("差异备注","DIFF_NOTE",GaConstant.DT_STRING);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     field.setInput(GaConstant.INPUT_TEXTAREA);
     field.addInputAttributeMap("rows", "3");
     field.addInputAttributeMap("cols", "20");
     dbFields.add(field);
   
     this.fieldList = dbFields;
		  
	 }
}
