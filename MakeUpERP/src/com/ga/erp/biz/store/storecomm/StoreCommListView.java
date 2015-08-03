package com.ga.erp.biz.store.storecomm;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.util.SystemUtil;

public class StoreCommListView extends ListView {
	
  public StoreCommListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {
	  
    List<DbField> dbFields = new ArrayList<DbField>();
	    
  	DbField field = new DbField("ID","STORE_COMM_ID",GaConstant.DT_LONG); 
  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "STORE_COMM_ID");
  	field.setColumnDisplay(false, 0, true);
  	dbFields.add(field);
  	    
  	field = new DbField("门店", "STORE_NAME", GaConstant.DT_STRING);
  	field.setQueryOpera(GaConstant.QUERY_LIKE, GaConstant.INPUT_POPSELECT, 0, false, true);
  	field.setPopSelect("STORESELECT","NAME",true,
  	        "/store/store_main.htm",
  	        "STORE_ID,STORE_NAME,cid_storeList",800,400);
  	dbFields.add(field);
  	
  	field = new DbField("商品ID", "COMMODITY_ID", GaConstant.DT_STRING);
  	field.setColumnDisplay(false, 0, true);
  	dbFields.add(field);
  	
  	field = new DbField("商品名", "COMMODITY_NAME", GaConstant.DT_STRING);
  	field.setQueryOpera(GaConstant.QUERY_LIKE, GaConstant.INPUT_POPSELECT, 1, false, true);
  	field.setPopSelect("COMMSELECT","COMMODITY_NAME",true,
  	        "/comm/comm_main.htm",
  	        "COMMODITY_ID,COMMODITY_NAME,cid_commList",800,400);
  	dbFields.add(field);
  	
    field = new DbField("零售价", "RETAIL_PRICE", GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 2, true);
    dbFields.add(field);

    field = new DbField("会员价", "MEMBER_PRICE", GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 3, true);
    dbFields.add(field);
    
    field = new DbField("平均价", "ABERAGE_PRICE", GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 4, true);
    dbFields.add(field);
    
    field = new DbField("总销售量", "ALL_AMOUNT", GaConstant.DT_LONG);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_TEXT, 5, true);
    dbFields.add(field);
    
    field = new DbField("累计库存", "INNAGE_AMOUNT", GaConstant.DT_LONG);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 4, true);
    dbFields.add(field);
   
    field = new DbField("剩余库存", "CNT", GaConstant.DT_LONG);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 4, true);
    dbFields.add(field);
    
    field = new DbField("库存上限", "UPPER_LIMIT", GaConstant.DT_LONG);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 4, true);
    dbFields.add(field);
    
    field = new DbField("累计下限", "LOWER_LIMIT", GaConstant.DT_LONG);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 4, true);
    dbFields.add(field);
    
    field = new DbField("状态","STATE",GaConstant.DT_INT);
    field.setAliasCode("sc");
    field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT, 5, true);
    field.setLookupData(SystemUtil.getStatusMap());
    dbFields.add(field);

    this.fieldList = dbFields;
  }
  
}
