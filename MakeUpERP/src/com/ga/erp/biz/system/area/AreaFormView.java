package com.ga.erp.biz.system.area;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class AreaFormView extends FormView{

  public AreaFormView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }
  
  @Override
  public void initField() throws Exception {
	  
    funcMap = new HashMap<String,String>();
    
    List<DbField> dbField = new ArrayList<DbField>();
    
    DbField field = new DbField("地区ID","AREA_ID",GaConstant.DT_LONG); 
    field.setPK(true);
    field.setInput(GaConstant.INPUT_HIDDEN);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "AREA_ID");
    dbField.add(field);
    
    field = new DbField("地区名称", "AREA_NAME", GaConstant.DT_STRING);
    dbField.add(field);
    
    field = new DbField("上级地区ID","P_ID",GaConstant.DT_LONG);
    field.setPopSelect("opSelect", "AREA_ID", false);
    dbField.add(field);
    if (this.viewEditMode == GaConstant.EDITMODE_NEW) {
      field.setQueryParamFormSource(GaConstant.INPUT_REQUEST, "P_ID");
    }
    
    field = new DbField("上级地区","P_NAME",GaConstant.DT_STRING);
    field.setPopSelect(this.viewEditMode == GaConstant.EDITMODE_NEW ? "AREASELECT" : "AREASELECTEDIT", "AREA_NAME" ,true,
        "/system/area_sele.htm",
        "AREA_ID,AREA_NAME,cid_areaTree", 230, 400);
    dbField.add(field);
    if (this.viewEditMode == GaConstant.EDITMODE_NEW) {
      field.setQueryParamFormSource(GaConstant.INPUT_REQUEST, "P_NAME");
    }
    
    if(this.viewEditMode == GaConstant.EDITMODE_DISP){
      field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
      field.setInputMode(GaConstant.EDITMODE_DISP, GaConstant.INPUTMODE_READONLY);
      field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
      dbField.add(field);
    }
       
    field = new DbField("状态","STATE",GaConstant.DT_INT);
    field.setInput(GaConstant.INPUT_SELECT);
    field.setVerifyFormula("", true);
    field.setLookupData(SystemUtil.getStatusMap());
    dbField.add(field);
    
    field = new DbField("备注", "NOTE", GaConstant.DT_STRING);
    field.setInput(GaConstant.INPUT_TEXTAREA);
    dbField.add(field);
    field.addInputAttributeMap("rows", "5");
    field.addInputAttributeMap("cols", "60");
    this.fieldList = dbField;
  }

}
