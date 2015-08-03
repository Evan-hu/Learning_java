package com.ga.erp.biz.statistic.abc;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.StringDecorator;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class AbcListView extends ListView {
	
  public AbcListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {
	  
    List<DbField> dbFields = new ArrayList<DbField>();
	    
  	DbField field = new DbField("商品ID","COMMODITY_ID",GaConstant.DT_LONG); 
  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "COMMODITY_ID");
  	field.setColumnDisplay(false, 0, true);
  	dbFields.add(field);
  	
  	
  	field = new DbField("门店", "STORE_NAME", GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, GaConstant.INPUT_POPSELECT, 1, false, true);
    field.setPopSelect("STORESELECT","STORE_NAME",true,
        "/store/store_main.htm",
        "STORE_ID,STORE_NAME,cid_storeList",800,450);
    field.setInputCustomStyle("width:120px;");
    field.setColumnDisplay(true, 80, false);
    dbFields.add(field);
  	
  	field = new DbField("创建时间","CREATETIME",GaConstant.DT_DATETIME); 
  	field.setColumnDisplay(false, 0, true);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd",false));
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, 2, true);
    field.getFieldVerify().setRequire(true);
    dbFields.add(field);
    
  	    
  	field = new DbField("商品名","COMMODITY_NAME",GaConstant.DT_STRING);
  	dbFields.add(field);
//  	
//  	field = new DbField("ROW","ROW_NUMBER",GaConstant.DT_STRING);
//    dbFields.add(field);
  	
  	field = new DbField("销售金额","CNT",GaConstant.DT_DOUBLE);
    dbFields.add(field);
    
    field = new DbField("销售占比","SZB",GaConstant.DT_DOUBLE);
    StringDecorator decorator = new StringDecorator();
    decorator.setStringFormat("{value}%");
    field.setColumnDecorator("", decorator);
    dbFields.add(field);
    
    field = new DbField("累计销售占比","ZB",GaConstant.DT_DOUBLE);
     decorator = new StringDecorator();
    decorator.setStringFormat("{value}%");
    field.setColumnDecorator("", decorator);
    dbFields.add(field);
    
    field = new DbField("ABC分类","ABC",GaConstant.DT_STRING);
    dbFields.add(field);
    
  	
//  	field = new DbField("分类","SORT_NAME",GaConstant.DT_STRING);
////    field.setQueryOpera(GaConstant.QUERY_LIKE,GaConstant.INPUT_TEXT, 1, true);
//    field.setQueryInputType(GaConstant.QUERY_LIKE);
//    field.setColumnDisplay(true, 0, false);
//    dbFields.add(field);
//    
//    field = new DbField("品牌","NAME",GaConstant.DT_STRING);
////    field.setQueryOpera(GaConstant.QUERY_LIKE,GaConstant.INPUT_TEXT, 1, true);
//    field.setColumnDisplay(true, 0, false);
//    dbFields.add(field);
//    
//  	field = new DbField("销售总量", "COMM_CNT", GaConstant.DT_LONG);
//    field.setInputCustomStyle("width:70px");
//    dbFields.add(field);
//
//    field = new DbField("总销售额", "ALL_MONEY", GaConstant.DT_MONEY);
//    field.setInputCustomStyle("width:70px");
//    dbFields.add(field); 
//    
//    field = new DbField("平均售价","BUY_PRICE",GaConstant.DT_MONEY);
//    field.setColumnDisplay(true, 0, false);
//    dbFields.add(field);
//    
//
//    field = new DbField("库存", "INNAGE_BALANCE",GaConstant.DT_INT);
//    field.setColumnDisplay(true, 0, false);
//    dbFields.add(field);
//
//    field = new DbField("状态","STATE",GaConstant.DT_LONG);
//    field.setLookupData(SystemUtil.getStatusMap());
//    dbFields.add(field);
  
    this.fieldList = dbFields;
    
  }
  
}
