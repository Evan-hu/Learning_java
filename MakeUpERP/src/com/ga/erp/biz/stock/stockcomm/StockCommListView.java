package com.ga.erp.biz.stock.stockcomm;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.util.SystemUtil;

public class StockCommListView extends ListView{

	public StockCommListView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap,GaConstant.EDITMODE_DISP);
	}

	  @Override
	  public void initField() throws Exception {
		  
	     List<DbField> dbFields = new ArrayList<DbField>();
		    
		   DbField field = new DbField("ID","STOCK_COMM_ID",GaConstant.DT_LONG); 
		   field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "STOCK_COMM_ID");
		   field.setColumnDisplay(false, 0, true);
		   dbFields.add(field);
		   
		   field = new DbField("商品ID","COMMODITY_ID",GaConstant.DT_STRING);
		   field.setColumnDisplay(false, 0, true);
		   dbFields.add(field);
		   
		   field = new DbField("商品名","COMMODITY_NAME",GaConstant.DT_STRING);
		   field.setQueryOpera(GaConstant.QUERY_LIKE, 1, true);
		   field.setInputCustomStyle("width:80px;");
		   dbFields.add(field);
		  	
		   field = new DbField("仓库ID","STOCK_ID",GaConstant.DT_STRING);
		   field.setColumnDisplay(false, 0, true);
		   dbFields.add(field);
		   
		   field = new DbField("仓库名","NAME",GaConstant.DT_STRING);
		   field.setQueryOpera(GaConstant.QUERY_EQUALS, 2, true);
		   field.setInputCustomStyle("width:80px;");
		   dbFields.add(field);
		  	
		   field = new DbField("货架编号","SHELF_NUM",GaConstant.DT_INT);
		   field.setQueryOpera(GaConstant.QUERY_LIKE, 3, true);
		   field.setInputCustomStyle("width:80px;");
		   dbFields.add(field);
			
		   field = new DbField("进货价","STOCK_PRICE",GaConstant.DT_MONEY);
		   dbFields.add(field);
			
		   field = new DbField("成本价","COST_PRICE",GaConstant.DT_MONEY);
		   dbFields.add(field);
			
		   field = new DbField("批发价","WHOLESALE_PRICE",GaConstant.DT_MONEY);
		   dbFields.add(field);
			
		   field = new DbField("零售价","RETAIL_PRICE",GaConstant.DT_MONEY);
		   dbFields.add(field);
			
		   field = new DbField("会员价","MEM_PRICE",GaConstant.DT_MONEY);
		   dbFields.add(field);
			
		   field = new DbField("配送价","DISTRIBUT_PRICE",GaConstant.DT_MONEY);
		   dbFields.add(field);
			
		   field = new DbField("库存数","CNT",GaConstant.DT_INT);
		   field.setQueryOpera(GaConstant.QUERY_LESS_EQUALS, 4, true);
		   field.setInputCustomStyle("width:80px;");
		   dbFields.add(field);
			
		   field = new DbField("库存上限","UPPER_LIMIT",GaConstant.DT_INT);
		   dbFields.add(field);
			
		   field = new DbField("库存下限","LOWER_LIMIT",GaConstant.DT_INT);
		   dbFields.add(field);
			
		   field = new DbField("状态","STATE",GaConstant.DT_INT);
		   field.setAliasCode("s");//设置查询列别名
		   field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT, 5,true);
		   field.setLookupData(SystemUtil.getStatusMap());//设置select数据
		   dbFields.add(field);		
			
		   this.fieldList = dbFields;
	  }
	

}
