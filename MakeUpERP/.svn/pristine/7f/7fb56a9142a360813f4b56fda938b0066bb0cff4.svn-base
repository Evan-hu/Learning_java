package com.ga.erp.biz.content.ad;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.StringDecorator;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.click.util.FileUploadUtil;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class AdListView extends ListView {
	
  public AdListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {
	  
  	List<DbField> dbFields = new ArrayList<DbField>();
  	    
  	DbField field = new DbField("ID","AD_ID",GaConstant.DT_LONG); 
  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "AD_ID");
  	field.setColumnDisplay(false, 0, true);
  	dbFields.add(field);
  	    
  	field = new DbField("缩略图","CONTENT",GaConstant.DT_STRING);
    StringDecorator decorator = new StringDecorator();
    decorator.setImgDecorator(FileUploadUtil.getPicVrPath("ad/") + "{value}",100, 50);
    field.setColumnDecorator("", decorator);
    field.setColumnDisplay(true, 120, false);
    dbFields.add(field);

    field = new DbField("发布店铺", "STORE_NAME", GaConstant.DT_STRING);
    field.setPopSelect("STORESELECT","STORE_NAME",true,
            "/store/store_main.htm",
            "STORE_ID,STORE_NAME,cid_storeList,_ids", 800,400);
    field.setColumnDisplay(false, 0, false);
    dbFields.add(field);

    field = new DbField("广告名称", "AD_NAME", GaConstant.DT_STRING);
  	field.setQueryOpera(GaConstant.QUERY_LIKE, 1, false);
  	ActionButton action = new ActionButton(this.getClass(),"AD_ID","{value}","/content/ad_detail.htm",null);
    SubmitTool.submitToDialog(action, "AD_ID","广告详情", 800, 480, this.modelMap.getPreNavInfoStr());
    decorator = new StringDecorator();
    decorator.setLinkDecorator(action);
    action.bindTableRowData(this.viewID);
    action.addParam(GaConstant.FIXPARAM_EDITMODE,GaConstant.EDITMODE_EDIT);
    field.setColumnDecorator("", decorator);
  	dbFields.add(field);
  	
    field = new DbField("开始时间","BEG_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_DATETIME, 2, true);
    dbFields.add(field);
    
    field = new DbField("结束时间","END_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_DATETIME, 3, true);
    dbFields.add(field);
	
    field = new DbField("创建人", "TRUENAME", GaConstant.DT_STRING);
  	field.setQueryOpera(GaConstant.QUERY_LIKE, 4, false);
  	dbFields.add(field);
  	
    field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_DATETIME, 5, true);
    dbFields.add(field);
    
    field = new DbField("状态","STATE",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT, 6, true);
    field.setLookupData(SystemUtil.getStatusMap());
    field.setAliasCode("a");
    dbFields.add(field);

    this.fieldList = dbFields;
  }
  
}
