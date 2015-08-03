package com.ga.erp.biz.store;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.StringDecorator;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.util.SystemUtil;

public class StoreListView extends ListView {
	
  public StoreListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {
	  
    List<DbField> dbFields = new ArrayList<DbField>();
	    
  	DbField field = new DbField("ID","STORE_ID",GaConstant.DT_LONG); 
  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "STORE_ID");
  	field.setColumnDisplay(false, 0, true);
  	field.setPK(true);
  	field.setIsFilterCode(true);
  	dbFields.add(field);
  	    
  	field = new DbField("ID", "OP_STORE_ID", GaConstant.DT_LONG);
  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "OP_STORE_ID");
    field.setColumnDisplay(false, 0, true);
    dbFields.add(field);
    
  	field = new DbField("门店名","STORE_NAME",GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 1, true);
    ActionButton action = new ActionButton(this.getClass(),"STORE_ID","{value}","/store/store_detail.htm",null);
    SubmitTool.submitToDialog(action, "STORE_NAME","门店详情", 1000, 440, this.modelMap.getPreNavInfoStr());
    StringDecorator decorator = new StringDecorator();
    decorator.setLinkDecorator(action);
    action.bindTableRowData(this.viewID);
    action.addParam(GaConstant.FIXPARAM_EDITMODE,GaConstant.EDITMODE_EDIT);
    field.setColumnDecorator("", decorator);
    field.setInputCustomStyle("width:90px");
  	dbFields.add(field);
  	
   	field = new DbField("门店编号", "STORE_NUM", GaConstant.DT_STRING);
   	field.setQueryOpera(GaConstant.QUERY_LIKE, 2, true);
   	field.setInputCustomStyle("width:70px");
   	dbFields.add(field);
  	
  	field = new DbField("地区", "AREA_NAME", GaConstant.DT_STRING);
  	field.setQueryOpera(GaConstant.QUERY_LIKE, GaConstant.INPUT_POPSELECT, 3, true);
    field.setPopSelect("AREASELECT","AREA_NAME",true,"/system/area_sele.htm",
            "AREA_ID,AREA_NAME,cid_areaTree", 300,400);
    dbFields.add(field);
  	
    field = new DbField("剩余金额", "LEFT_MONEY", GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 4, true);
    field.setInputCustomStyle("width:50px;");
    dbFields.add(field);

    field = new DbField("累计消费", "ALL_CONSUME", GaConstant.DT_MONEY);
    field.setInputCustomStyle("width:50px;");
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 5, true);
    dbFields.add(field);
    
    field = new DbField("累计充值", "ALL_PAY", GaConstant.DT_MONEY);
    field.setInputCustomStyle("width:50px;");
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 6, true);
    dbFields.add(field);
    
    field = new DbField("总销售额", "ALL_CONSUME_MONEY", GaConstant.DT_MONEY);
    field.setInputCustomStyle("width:50px;");
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 7, true);
    dbFields.add(field);
    
    field = new DbField("总销售量", "ALL_CONSUME_ORDER", GaConstant.DT_LONG);
    field.setInputCustomStyle("width:50px;");
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_TEXT, 8, true);
    dbFields.add(field);
    
    dbFields.addAll(SystemUtil.getNormalListFieldList("s", 9));

    field = new DbField("是否激活","IS_ACTIVE",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT, 12, false, false);
    field.setLookupData(SystemUtil.getYesNoMap());
    dbFields.add(field);
    
    this.fieldList = dbFields;
  }
  
}
