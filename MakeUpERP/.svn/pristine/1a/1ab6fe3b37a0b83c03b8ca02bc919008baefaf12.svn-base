package com.ga.erp.biz.stock.stockcomm;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;

public class StockCommFormView extends FormView {

	public StockCommFormView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap);
	}
	
	@Override
	  public void initField() throws Exception {
		
		List<DbField> dbFields = new ArrayList<DbField>();
			    
		DbField field = new DbField("ID","STOCK_COMM_ID",GaConstant.DT_LONG); 
	  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "STOCK_COMM_ID");
	  	field.setInput(GaConstant.INPUT_HIDDEN);
	  	field.setPK(true);
	  	dbFields.add(field);
	  	
  	  field = new DbField("商品ID","COMMODITY_ID", GaConstant.DT_LONG);
      field.setPopSelect("commSelect", "COMMODITY_ID", false);
          dbFields.add(field);
  	  	
  		field = new DbField("商品名<font color='red'>*</font>","COMMODITY_NAME",GaConstant.DT_STRING);
  		//选择控件   
  		field.setPopSelect("commSelect","COMMODITY_NAME",true,
  	              "/comm/comm_main.htm",
  	              "COMMODITY_ID,COMMODITY_NAME,PURCHASE_PRICE,TRADE_PRICE,SELL_PRICE,SEND_PRICE,MEMBER_PRICE,PURCHASE_AMOUNT,cid_commList",800,400);
  		dbFields.add(field);
  	  	
  	  field = new DbField("仓库ID","STOCK_ID", GaConstant.DT_LONG);
      field.setPopSelect("stockselect", "STOCK_ID", false);
      dbFields.add(field);
  	  	
  		field = new DbField("仓库名<font color='red'>*</font>","NAME",GaConstant.DT_STRING);
  		field.setPopSelect("stockselect","NAME",true,
  	              "/stock/stock_main.htm",
  	              "STOCK_ID,NAME,cid_stockList",700,300);
  		dbFields.add(field);
  	  	
  		field = new DbField("货架ID","SHELF_ID", GaConstant.DT_LONG);
      field.setPopSelect("selectNew3", "SHELF_ID", false);
      dbFields.add(field);
  	  	
  		field = new DbField("货架编号<font color='red'>*</font>","SHELF_NUM",GaConstant.DT_STRING);
  		field.setPopSelect("selectNew3","SHELF_NUM",true,
  	              "/stock/shelf_sele.htm",
  	              "SHELF_ID,SHELF_NUM,cid_shelfTree",300,400);
  		dbFields.add(field);
  	  	
  		/****************************公用View刷新页面特殊处理，不需要操作数据库********************************/
  		field = new DbField("刷新ViewID","RELOAD_VIEW",GaConstant.DT_STRING);
  		field.setInput(GaConstant.INPUT_HIDDEN);
  		dbFields.add(field);
  		/****************************公用View刷新页面特殊处理，不需要操作数据库********************************/
  		
  		field = new DbField("进货价","STOCK_PRICE",GaConstant.DT_MONEY);
  		field.setPopSelect("commSelect", "PURCHASE_PRICE", true);
  		dbFields.add(field);
  		
  		field = new DbField("成本价","COST_PRICE",GaConstant.DT_MONEY);
  		dbFields.add(field);
  		
  		field = new DbField("批发价","WHOLESALE_PRICE",GaConstant.DT_MONEY);
  		field.setPopSelect("commSelect", "TRADE_PRICE", true);
  		dbFields.add(field);
  		
  		field = new DbField("零售价","RETAIL_PRICE",GaConstant.DT_MONEY);
  		field.setPopSelect("commSelect", "SELL_PRICE", true);
  		dbFields.add(field);
  		
  		field = new DbField("会员价","MEM_PRICE",GaConstant.DT_MONEY);
  		field.setPopSelect("commSelect", "MEMBER_PRICE", true);
  		dbFields.add(field);
  		
  		field = new DbField("配送价","DISTRIBUT_PRICE",GaConstant.DT_MONEY);
  		field.setPopSelect("commSelect", "SEND_PRICE", true);
  		dbFields.add(field);
  		
  		field = new DbField("库存数","INNAGE",GaConstant.DT_INT);
//  		field.setPopSelect("commSelect", "PURCHASE_AMOUNT", true);
  		dbFields.add(field);
  		
  		field = new DbField("库存上限","UPPER_LIMIT",GaConstant.DT_INT);
  		dbFields.add(field);
  		
  		field = new DbField("库存下限","LOWER_LIMIT",GaConstant.DT_INT);
  		dbFields.add(field);
  		
  	  this.fieldList = dbFields;	  	
    }   	 
	
}


