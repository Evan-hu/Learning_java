package com.ga.erp.biz.purchase.order;

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
import com.ga.erp.util.SystemUtil;

public class PurOrderFormView extends FormView {

	public PurOrderFormView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap);
	}

	 public void initField() throws Exception {

  		  List<DbField> dbFields = new ArrayList<DbField>();
  		  DbField field = new DbField("ID","PURCHASE_ORDER_ID",GaConstant.DT_LONG); 
  		  field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "PURCHASE_ORDER_ID");
  		  field.setInput(GaConstant.INPUT_HIDDEN);
  		  dbFields.add(field);
  		  
  		  field = new DbField("单据编号<font color='red'>*</font>","PURCHASE_ORDER_NUM",GaConstant.DT_INT);
  		  dbFields.add(field);	
  		  
  		  field = new DbField("供应商ID","SUPPLIER_ID", GaConstant.DT_LONG);
  	    field.setPopSelect("selectNew", "SUPPLIER_ID", false);
	      dbFields.add(field);
	      
	      field = new DbField("供应商<font color='red'>*</font>","SUPPLIER_NAME",GaConstant.DT_STRING);
	      field.setPopSelect("selectNew","SUPPLIER_NAME",true,
		              "/supplier/supplier_main.htm",
		              "SUPPLIER_ID,SUPPLIER_NAME,cid_supplierList",800,400);
	      dbFields.add(field);	
		  
	      field = new DbField("上级订单ID","P_PURCHASE_ORDER_ID", GaConstant.DT_LONG);
	      field.setPopSelect("purOrderselect", "PURCHASE_ORDER_ID", false);
	      dbFields.add(field);
	      
	      field = new DbField("上级订单编号","P_PURCHASE_ORDER_NUM",GaConstant.DT_STRING);
	      field.setPopSelect("purOrderselect","PURCHASE_ORDER_NUM",true,
		              "/purchase/purOrder_main.htm",
		              "PURCHASE_ORDER_ID,PURCHASE_ORDER_NUM,cid_purOrderList",800,400);
	      dbFields.add(field);
		  
	      
	      field = new DbField("配送机构ID","DELIVERY_ORG_ID", GaConstant.DT_LONG);
	      field.setPopSelect("deliverySelect", "DELIVERY_ORG_ID", false);
	      dbFields.add(field);
	      
	      field = new DbField("配送机构<font color='red'>*</font>","DELIVERY_NAME",GaConstant.DT_STRING);
	    //选择控件   
	      field.setPopSelect("deliverySelect","DELIVERY_NAME",true,
	                "/system/delivery_main.htm",
	                "DELIVERY_ORG_ID,DELIVERY_NAME,cid_deliveryList",800,400);
	      dbFields.add(field);

		  
		    field = new DbField("结算id","SETTLEMENT_ID", GaConstant.DT_LONG);
        field.setPopSelect("settlementSelect", "SETTLEMENT_ID", false);
        dbFields.add(field);
      
        field = new DbField("结算流水<font color='red'>*</font>","CODE",GaConstant.DT_STRING);
      //选择控件   
        field.setPopSelect("settlementSelect","CODE",true,
                "/settlement/settlement_main.htm",
                "SETTLEMENT_ID,CODE,cid_settlementListView",800,400);
       dbFields.add(field);
		  
		    field = new DbField("仓库ID","STOCK_ID", GaConstant.DT_LONG);
		    field.setPopSelect("stockselect", "STOCK_ID", false);
	      dbFields.add(field);
		  	
	      field = new DbField("仓库名<font color='red'>*</font>","NAME",GaConstant.DT_STRING);
	      field.setPopSelect("stockselect","NAME",true,
		              "/stock/stock_main.htm",
		              "STOCK_ID,NAME,cid_stockList",700,300);
	      dbFields.add(field);	
		  
  		  field = new DbField("送货地址<font color='red'>*</font>","DILIVERY_ADDR_ID",GaConstant.DT_STRING);
  		  dbFields.add(field);	
  		  
  		  
  		  field = new DbField("采购员","TRUENAME",GaConstant.DT_STRING);
  		  field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
  		  field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
  		  
  		  dbFields.add(field);	
  		  
  		  field = new DbField("交货期限<font color='red'>*</font>","DELIVERY_TIME",GaConstant.DT_DATETIME);
  		  field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd", false));
  		  dbFields.add(field);	
  		  
  		  field = new DbField("订单金额","ORDER_CNT",GaConstant.DT_MONEY);
  		  dbFields.add(field);	
  		  
  		  field = new DbField("实际金额","REALITY_PRICE",GaConstant.DT_MONEY);
  		  dbFields.add(field);	
  		  
  		  field = new DbField("商品类数","COMM_SORT_CNT",GaConstant.DT_INT);
  		  dbFields.add(field);	
  		  
  		  field = new DbField("商品总件数","COMM_CNT",GaConstant.DT_INT);
  		  dbFields.add(field);	
  		  
  		  field = new DbField("物流费用","DISTRIBUTE_MONEY",GaConstant.DT_MONEY);
  		  dbFields.add(field);
  		  
  		  field = new DbField("物流号","DISTRIBUTE_NUM",GaConstant.DT_INT);
  		  dbFields.add(field);	
  		  
  		  field = new DbField("类型","TYPE",GaConstant.DT_INT);
  		  field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
  		  field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_READONLY);
  		  field.setInput(GaConstant.INPUT_SELECT);
  		  Map<String, Object> lookup = new LinkedHashMap<String, Object>();
  	      lookup.put("1", "采购订货单");
  	      lookup.put("2", "采购发货单（供应商发货）");
  	      lookup.put("3", "采购收货单");
  	      lookup.put("4", "赠品收货单");
  	      lookup.put("5", "DC退货单");
  	      lookup.put("6", "大客户要货单");
  	      lookup.put("7", "大客户销售单");
  	      lookup.put("8", "大客户退货单");
  	      field.setLookupData(new LookupDataSet(lookup));
  	      field.setVerifyFormula("", true);
  		  dbFields.add(field);
  		  
  		  field = new DbField("差异是否存在","IS_DIFF",GaConstant.DT_INT);
  		  field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
  		  field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_NORMAL);
  		  field.setInput(GaConstant.INPUT_SELECT);
  		  field.setLookupData(SystemUtil.getYesNoMap());
  		  field.setVerifyFormula("", true);
  		  dbFields.add(field);	
  		  
  		  field = new DbField("差异是否解决","DIFF_IS_SETTLED",GaConstant.DT_INT);
  		  field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
  		  field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_NORMAL);
  		  field.setInput(GaConstant.INPUT_SELECT);
  		  field.setLookupData(SystemUtil.getYesNoMap());
  		  field.setVerifyFormula("", true);
  		  dbFields.add(field);	
  		  
  		  field = new DbField("状态","STATE",GaConstant.DT_INT);
  		  field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
  		  field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_NORMAL);
  		  field.setInput(GaConstant.INPUT_SELECT);
  		  lookup = new LinkedHashMap<String, Object>();
  	      lookup.put("-3", "拒绝受理");
  	      lookup.put("-2", "审核不通过");
  	      lookup.put("-1", "待审核");
  	      lookup.put("0", "废除");
  	      lookup.put("1", "待受理（审核通过）");
  	      lookup.put("2", "待备货");
  	      lookup.put("3", "发货中");
  	      lookup.put("4", "已收货（完成）");
  		  field.setLookupData(new LookupDataSet(lookup));
  		  field.setVerifyFormula("", true);
  		  dbFields.add(field);
  		  
  		  field = new DbField("备注","NOTE",GaConstant.DT_STRING);
  		  field.setInput(GaConstant.INPUT_TEXTAREA);
  		  field.addInputAttributeMap("rows", "3");
  		  field.addInputAttributeMap("cols", "20");
  		  dbFields.add(field);	
  		  
  		  field = new DbField("差异处理备注","DIFF_NOTE",GaConstant.DT_STRING);
  		  field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
  		  field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_NORMAL);
  		  field.setInput(GaConstant.INPUT_TEXTAREA);
  		  field.addInputAttributeMap("rows", "3");
  		  field.addInputAttributeMap("cols", "20");
  		  dbFields.add(field);	
		  
	  	  this.fieldList = dbFields;			    
			  
	 }
}
