package com.ga.erp.biz.comm;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.control.StringDecorator;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class CommListView extends ListView {
	
  public CommListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {
	  
  	List<DbField> dbFields = new ArrayList<DbField>();
  	    
  	DbField field = new DbField("ID","COMMODITY_ID",GaConstant.DT_LONG);
  	field.setPK(true);
  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "COMMODITY_ID");
  	field.setColumnDisplay(false, 0, true);
  	dbFields.add(field);
	
  	field = new DbField("商品名称", "COMMODITY_NAME", GaConstant.DT_STRING);
  	ActionButton action = new ActionButton(this.getClass(),"COMMODITY_ID","{value}","/comm/comm_detail.htm",null);
    SubmitTool.submitToDialog(action, "COMMODITY_NAME","商品详情", 1000, 650, this.modelMap.getPreNavInfoStr());
    StringDecorator decorator = new StringDecorator();
    decorator.setLinkDecorator(action);
    decorator.setStringFormat("<font color='red'>{value}</font>");
    action.bindTableRowData(this.viewID);
    action.addParam(GaConstant.FIXPARAM_EDITMODE,GaConstant.EDITMODE_EDIT);
    field.setColumnDecorator("", decorator);
    field.setColumnDisplay(true, 80, true);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
    field.setInputCustomStyle("width:80px;");
  	dbFields.add(field);
  	
  	field =new DbField("类型", "TYPE", GaConstant.DT_INT);
  	field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 1, false);
  	Map<String, Object> option = new LinkedHashMap<String, Object>();
  	option.put("1", "普通商品");
  	option.put("2", "赠品");
  	option.put("3", "试用品");
  	field.setLookupData(new LookupDataSet(option));
  	field.setColumnDisplay(true, 80, false);
  	dbFields.add(field);
  	
//  	field = new DbField("商品简称", "COMMODITY_SNAME", GaConstant.DT_STRING);
//  	field.setQueryOpera(GaConstant.QUERY_LIKE, 1, false);
//  	field.setInputCustomStyle("width:80px;");
//    field.setColumnDisplay(true,80, false);
//  	dbFields.add(field);
  	
  	field = new DbField("货号", "CODE", GaConstant.DT_STRING);
  	field.setQueryOpera(GaConstant.QUERY_LIKE, 2, false);
  	field.setInputCustomStyle("width:80px;");
    field.setColumnDisplay(true,80, false);
  	dbFields.add(field);
  	
	  field = new DbField("自编码", "CUSTOM_CODE", GaConstant.DT_STRING);
  	field.setQueryOpera(GaConstant.QUERY_LIKE, 3, false);
  	field.setInputCustomStyle("width:80px;");
  	field.setColumnDisplay(true,80, false);
  	dbFields.add(field);
  	
  	field = new DbField("助记码", "MNEMONIC_CODE", GaConstant.DT_STRING);
  	field.setQueryOpera(GaConstant.QUERY_LIKE, 4, false);
  	field.setInputCustomStyle("width:80px;");
  	field.setColumnDisplay(true,80, false);
  	dbFields.add(field);
  
  	field = new DbField("分类", "SORT_NAME", GaConstant.DT_STRING);
  	field.setQueryOpera(GaConstant.QUERY_LIKE, GaConstant.INPUT_POPSELECT, 5, false, true);
  	field.setPopSelect("SORTSELECT","SORT_NAME",true,
  		        "/comm/sort_sele.htm",
  		        "SORT_ID,SORT_NAME,cid_sortTree",300,400);
  	field.setInputCustomStyle("width:120px;");
  	field.setColumnDisplay(true, 80, false);
  	dbFields.add(field);
  	
  	field = new DbField("品牌", "NAME", GaConstant.DT_STRING);
  	field.setQueryOpera(GaConstant.QUERY_LIKE, GaConstant.INPUT_POPSELECT, 7, false, true);
  	field.setPopSelect("BRANDSELECT","NAME",true,
  	        "/comm/brand_main.htm",
  	        "BRAND_ID,NAME,cid_brandList",800,400);
  	field.setInputCustomStyle("width:120px;");
  	field.setColumnDisplay(true, 80, false);
  	dbFields.add(field);
  	
  	field = new DbField("批发价","TRADE_PRICE",GaConstant.DT_MONEY);
  	field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_TEXT, 8, false);
    field.setInputCustomStyle("width:50px;");
    field.setColumnDisplay(true,80, false);
    dbFields.add(field);
    
  	field = new DbField("采购价","PURCHASE_PRICE",GaConstant.DT_MONEY);
  	field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_TEXT, 9, false);
    field.setInputCustomStyle("width:50px;");
    field.setColumnDisplay(true,0, false);
    dbFields.add(field);
    
	field = new DbField("配送价","SEND_PRICE",GaConstant.DT_MONEY);
  	field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_TEXT, 10, false);
    field.setInputCustomStyle("width:50px;");
    field.setColumnDisplay(true,0, false);
    dbFields.add(field);
    
	field = new DbField("零售价","SELL_PRICE",GaConstant.DT_MONEY);
  	field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_TEXT, 11, false);
    field.setInputCustomStyle("width:50px;");
    field.setColumnDisplay(true,0, false);
    dbFields.add(field);
    
    field = new DbField("会员价","MEMBER_PRICE",GaConstant.DT_MONEY);
  	field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_TEXT, 12, false);
    field.setInputCustomStyle("width:50px;");
    field.setColumnDisplay(true,0, false);
    dbFields.add(field);
    
//  	field = new DbField("总采购","PURCHASE_AMOUNT",GaConstant.DT_INT);
//  	field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_TEXT, 13, false);
//  	field.setInputCustomStyle("width:50px;");
//  	field.setColumnDisplay(true, 80, false);
//  	dbFields.add(field);
//  	
//	  field = new DbField("总配送","SEND_AMOUNT",GaConstant.DT_INT);
//  	field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_TEXT, 14, false);
//  	field.setInputCustomStyle("width:50px;");
//  	field.setColumnDisplay(true, 80, false);
//  	dbFields.add(field);
//  	
//  	field = new DbField("总销售","SELL_AMOUNT",GaConstant.DT_MONEY);
//  	field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 15, false);
//  	field.setInputCustomStyle("width:50px;");
//  	field.setColumnDisplay(true, 80, false);
//  	dbFields.add(field);
  
//  	field = new DbField("创建人","TRUENAME",GaConstant.DT_STRING);
//  	field.setColumnDisplay(true, 80, false);
//  	dbFields.add(field);
//  	
    field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
    field.setAliasCode("c");
    field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_DATETIME, 16, true);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setInputCustomStyle("width:80px;");
    field.setColumnDisplay(true, 150, false);
    dbFields.add(field);
//  
//    field = new DbField("上次修改时间","LAST_TIME",GaConstant.DT_DATETIME);
//    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
//    field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_DATETIME, 17, true);
//    field.setInputCustomStyle("width:80px;");
//    field.setColumnDisplay(true, 80, false);
//    dbFields.add(field);
//    
//    field = new DbField("上次修改人","LAST_OP_NAME",GaConstant.DT_STRING);
//    field.setQueryOpera(GaConstant.QUERY_LIKE, 18, true);
//    field.setColumnDisplay(true, 80, false);
//    dbFields.add(field);

    field = new DbField("状态","STATE",GaConstant.DT_INT);
  	field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 19, false);
  	field.setAliasCode("c");
  	field.setColumnDisplay(true, 80, false);
    field.setLookupData(SystemUtil.getStatusMap());
    dbFields.add(field);
      
    field = new DbField("采购状态","PURCHASE_STATE",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 20, false);
  	option = new LinkedHashMap<String, Object>();
  	option.put("0", "停购");
  	option.put("1", "正常");
  	field.setLookupData(new LookupDataSet(option));
    field.setColumnDisplay(true, 80, false);
    dbFields.add(field);
    
    this.fieldList = dbFields;
  }
  
}
