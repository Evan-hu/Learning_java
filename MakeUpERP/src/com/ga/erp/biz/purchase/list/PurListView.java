package com.ga.erp.biz.purchase.list;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;

public class PurListView extends ListView{

	public PurListView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap, GaConstant.EDITMODE_DISP);
	}
	  @Override
	  public void initField() throws Exception {
		  
	    List<DbField> dbFields = new ArrayList<DbField>();
		    
		  DbField field = new DbField("ID","PURCHASE_LIST_ID",GaConstant.DT_LONG); 
		  field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "PURCHASE_LIST_ID");
		  field.setColumnDisplay(false, 0, true);
		  dbFields.add(field);
		  
		  field = new DbField("单据编号","PURCHASE_ORDER_NUM",GaConstant.DT_STRING);
		  field.setAliasCode("po");
		  field.setQueryOpera(GaConstant.QUERY_LIKE, 1, true);
		  field.setInputCustomStyle("width:80px;");
		  dbFields.add(field);	
		  
		  field = new DbField("商品名","COMMODITY_NAME",GaConstant.DT_STRING);
		  field.setAliasCode("c");
		  field.setQueryOpera(GaConstant.QUERY_LIKE, 2, true);
		  field.setInputCustomStyle("width:80px;");
		  dbFields.add(field);
		  	
		  field = new DbField("供应商商品名","ACREAGE",GaConstant.DT_STRING);
		  dbFields.add(field);
		  	
		  field = new DbField("供应商","SUPPLIER_NAME",GaConstant.DT_STRING);
		  field.setAliasCode("s");
		  field.setQueryOpera(GaConstant.QUERY_LIKE, 3, true);
		  field.setInputCustomStyle("width:80px;");
		  dbFields.add(field);
		  
		  field = new DbField("采购数","PURCHASE_CNT",GaConstant.DT_INT);
		  dbFields.add(field);
		  
		  field = new DbField("采购价格","PURCHASE_PRICE",GaConstant.DT_MONEY);
		  field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_MONEY,4, true);
		  dbFields.add(field);
		
		  
		  field = new DbField("供应商供货数","SUPPLY_CNT",GaConstant.DT_INT);
		  field.setAliasCode("pl");
		  field.setQueryOpera(GaConstant.QUERY_LIKE,5, true);
		  field.setInputCustomStyle("width:80px;");
		  dbFields.add(field);
		  
		  field = new DbField("实际到货数","REALITY_CNT",GaConstant.DT_INT);
		  field.setAliasCode("pl");
		  field.setQueryOpera(GaConstant.QUERY_LIKE, 6, true);
		  field.setInputCustomStyle("width:80px;");
		  dbFields.add(field);
		  
		  field = new DbField("赠送数","SEND_CNT",GaConstant.DT_INT);
		  dbFields.add(field);
		  
//		  field = new DbField("差异备注","DIFF_NOTE",GaConstant.DT_STRING);
//		  field.setQueryOpera(GaConstant.QUERY_LIKE, 3, true);
//		  field.setInputCustomStyle("width:80px;");
//		  dbFields.add(field);
		  
	  	  this.fieldList = dbFields;
	  }
	

}
