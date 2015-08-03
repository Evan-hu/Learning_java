package com.ga.erp.biz.comm.brand;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.StringDecorator;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class BrandListView extends ListView {
	
  public BrandListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {
	  
    List<DbField> dbFields = new ArrayList<DbField>();
	    
  	DbField field = new DbField("ID","BRAND_ID",GaConstant.DT_LONG); 
  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "BRAND_ID");
  	field.setColumnDisplay(false, 0, true);
  	dbFields.add(field);
  	    
  	field = new DbField("品牌名","NAME",GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 1, true);
    ActionButton action = new ActionButton(this.getClass(),"BRAND_ID","{value}","/comm/brand_detail.htm",null);
    SubmitTool.submitToDialog(action, "BRAND_ID","品牌详情", 800, 310, this.modelMap.getPreNavInfoStr());
    StringDecorator decorator = new StringDecorator();
    decorator.setLinkDecorator(action);
    action.bindTableRowData(this.viewID);
    action.addParam(GaConstant.FIXPARAM_EDITMODE,GaConstant.EDITMODE_EDIT);
    field.setColumnDecorator("", decorator);
  	dbFields.add(field);
  	
  	field = new DbField("编码","CODE",GaConstant.DT_STRING);
  	dbFields.add(field);
  	
  	field = new DbField("提成比率","CUT_RATIO",GaConstant.DT_INT);
  	decorator = new StringDecorator();
  	decorator.setStringFormat("{value}%");
  	field.setColumnDecorator("", decorator);
  	dbFields.add(field);
  	
  	field = new DbField("排序值","RANK_NUM",GaConstant.DT_INT);
  	dbFields.add(field);
  	
  	field = new DbField("创建人","TRUENAME",GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 2, true);
    field.setInputCustomStyle("width:80px;");
  	dbFields.add(field);
	
    field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
    field.setAliasCode("b");
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setInputCustomStyle("width:80px;");
    field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_DATETIME, 3, true);
    dbFields.add(field);
    
    field = new DbField("状态","STATE",GaConstant.DT_INT);
    field.setAliasCode("b");
    field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT, 5, true);
    field.setLookupData(SystemUtil.getStatusMap());
    dbFields.add(field);

    this.fieldList = dbFields;
  }
  
}
