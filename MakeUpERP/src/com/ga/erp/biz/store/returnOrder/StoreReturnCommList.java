package com.ga.erp.biz.store.returnOrder;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;

public class StoreReturnCommList extends ListView{

	public StoreReturnCommList(String viewID, ModelMap modelMap) {
		super(viewID, modelMap,GaConstant.EDITMODE_DISP);
	}

	  @Override
	  public void initField() throws Exception {
		  
		  	List<DbField> dbFields = new ArrayList<DbField>();
		  
		 	DbField field = new DbField("ID","STORE_RETURN_COMM_ID",GaConstant.DT_LONG); 
		  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "STORE_RETURN_COMM_ID");
		  	field.setColumnDisplay(false,0,true); 
		  	dbFields.add(field);
		  	
//			field = new DbField("订单编号","STORE_RETURN_ORDER_NUM",GaConstant.DT_STRING);
//			field.setQueryOpera(GaConstant.QUERY_LIKE, 1, true);
//			field.setInputCustomStyle("width:80px;");
//		  	dbFields.add(field);
		  	
			field = new DbField("商品名","COMMODITY_NAME",GaConstant.DT_STRING);
			field.setAliasCode("c");
			field.setQueryOpera(GaConstant.QUERY_LIKE, 1, true);
			field.setInputCustomStyle("width:80px;");
		  	dbFields.add(field);
		  	
			field = new DbField("价格","PRICE",GaConstant.DT_MONEY);
			field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_MONEY, 2, true);
			field.setInputCustomStyle("width:80px;");
			dbFields.add(field);
			
			field = new DbField("退货数量","CNT",GaConstant.DT_INT);
			dbFields.add(field);
			
			this.fieldList = dbFields;
	  }
	

}
