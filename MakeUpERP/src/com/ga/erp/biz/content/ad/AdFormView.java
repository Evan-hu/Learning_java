package com.ga.erp.biz.content.ad;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class AdFormView extends FormView{

  public AdFormView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }
  
  @Override
  public void initField() throws Exception {
	  
    List<DbField> dbFields = new ArrayList<DbField>();
    
    DbField field = new DbField("ID","AD_ID",GaConstant.DT_LONG); 
    field.setInput(GaConstant.INPUT_HIDDEN);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "AD_ID");
    dbFields.add(field);
    
    field = new DbField("广告名称","AD_NAME",GaConstant.DT_STRING);
    field.getFieldVerify().setRequire(true);
    dbFields.add(field);
    
    field = new DbField("发布店铺ID", "SHOP_ID", GaConstant.DT_LONG);
    field.setPopSelect("SHOPSELECT", "SHOP_ID", false);
    dbFields.add(field);
    
    field = new DbField("发布店铺<font color='red'>*</font>", "SHOP_NAME", GaConstant.DT_STRING);
    field.setPopSelect("SHOPSELECT","SHOP_NAME",true,
            "/system/shop_main.htm",
            "SHOP_ID,SHOP_NAME,cid_shopList,_ids", 800,400);
    
    field = new DbField("发布店铺ID", "STORE_ID", GaConstant.DT_LONG);
    field.setPopSelect("STORESELECT", "STORE_ID", false);
    dbFields.add(field);
    
    field = new DbField("发布店铺", "STORE_NAME", GaConstant.DT_STRING);
    field.setPopSelect("STORESELECT","STORE_NAME",true,"/store/store_main.htm",
            "STORE_ID,STORE_NAME,cid_storeList,_ids", 800,400);
    field.getFieldVerify().setRequire(true);
    dbFields.add(field);
    
    field = new DbField("开始时间","BEG_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
//    field.getFieldVerify().setRequire(true);
    dbFields.add(field);
    
    field = new DbField("结束时间<font color='red'>*</font>","END_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.getFieldVerify().setRequire(true);
    dbFields.add(field);
	
    dbFields.addAll(SystemUtil.getNormalFieldList());
    
    field = new DbField("图片<font color='red'>*</font>", "CONTENT", GaConstant.DT_STRING);
  	field.setInput(GaConstant.INPUT_UPLOAD);
  	field.setFileUpload(true, "*.jpg", 500, 160);
  	dbFields.add(field);
    
    this.fieldList = dbFields;
  }

}
