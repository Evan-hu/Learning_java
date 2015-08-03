package com.ga.erp.biz.stock.inventorydiffbill.comm;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;

public class CommListView extends ListView{

	public CommListView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap,GaConstant.EDITMODE_DISP);
	}
	  @Override
	  public void initField() throws Exception {
		  
	       List<DbField> dbFields = new ArrayList<DbField>();
		    
		  	DbField field = new DbField("ID","DIFF_BILL_COMM_ID",GaConstant.DT_LONG); 
		  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "DIFF_BILL_COMM_ID");
		  	field.setColumnDisplay(false, 0, true);
		  	field.setPK(true);
		  	dbFields.add(field);
		  	
			field = new DbField("商品名","COMMODITY_NAME",GaConstant.DT_STRING);
			field.setQueryOpera(GaConstant.QUERY_LIKE, 1, true);
			field.setInputCustomStyle("width:80px;");
		  	dbFields.add(field);
		  	
			field = new DbField("仓库名","NAME",GaConstant.DT_STRING);
			field.setQueryOpera(GaConstant.QUERY_EQUALS, 2, true);
			field.setInputCustomStyle("width:80px;");
		  	dbFields.add(field);
		  	
			field = new DbField("差异单流水账号","BILL_NUM",GaConstant.DT_INT);
			field.setQueryOpera(GaConstant.QUERY_LIKE, 3, true);
			field.setInputCustomStyle("width:80px;");
			dbFields.add(field);
			
			field = new DbField("系统库存","STOCK_CNT",GaConstant.DT_INT);
			dbFields.add(field);
			
			field = new DbField("实盘数量","INVENTORY_CNT",GaConstant.DT_INT);
			dbFields.add(field);
			
			field = new DbField("盈亏数","DIFF_CNT",GaConstant.DT_INT);
			field.setAliasCode("dbc");
			field.setQueryOpera(GaConstant.QUERY_GREATER_THAN, 4, true);
			field.setInputCustomStyle("width:80px;");
			dbFields.add(field);
			
			field = new DbField("盘点进货价","PRICE",GaConstant.DT_MONEY);
			dbFields.add(field);
			
			field = new DbField("原库存进货价","OLD_PRICE",GaConstant.DT_MONEY);
			dbFields.add(field);
			
			field = new DbField("盈亏金额","DIFF_AMOUNT",GaConstant.DT_MONEY);
			field.setAliasCode("dbc");
			field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 5, false);
			field.setInputCustomStyle("width:80px;");
			dbFields.add(field);
			
			this.fieldList = dbFields;
	  }
	

}
