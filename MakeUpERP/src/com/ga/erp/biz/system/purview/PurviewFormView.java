package com.ga.erp.biz.system.purview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;

public class PurviewFormView extends FormView{

  public PurviewFormView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }
  
  @Override
  public void initField() throws Exception {
    funcMap = new HashMap<String,String>();
    List<DbField> dbField = new ArrayList<DbField>();
    
    DbField field = new DbField("ID","PURVIEW_ID",GaConstant.DT_LONG); 
    field.setPK(true);
    field.setInput(GaConstant.INPUT_HIDDEN);
    field.setQueryParamFormSource(GaConstant.INPUT_REQUEST, "PURVIEW_ID");
    dbField.add(field);
    
    field = new DbField("权限名称", "PURVIEW_NAME", GaConstant.DT_STRING);
    dbField.add(field);
    field.setQueryParamFormSource(GaConstant.INPUT_REQUEST, "PURVIEW_NAME");
    
    field = new DbField("权限标记", "MARK", GaConstant.DT_STRING);
    dbField.add(field);
    
    field = new DbField("序号", "RANK_NUM", GaConstant.DT_LONG);
    dbField.add(field);
    
    field = new DbField("连接地址", "FORM_URL", GaConstant.DT_STRING);
    dbField.add(field);
    
    field = new DbField("上级名称","P_NAME",GaConstant.DT_STRING);
    field.setPopSelect(this.viewEditMode == GaConstant.EDITMODE_NEW ? "opSelect" : "opDisp", "PURVIEW_NAME" ,true,
        "/system/purview_select.htm",
        "PURVIEW_ID,PURVIEW_NAME,cid_purviewTree", 230, 400);
    dbField.add(field);
    if (this.viewEditMode == GaConstant.EDITMODE_NEW) {
      field.setQueryParamFormSource(GaConstant.INPUT_REQUEST, "P_NAME");
    }
    
    field = new DbField("上级ID","P_ID",GaConstant.DT_LONG);
    field.setPopSelect("opSelect", "PURVIEW_ID", false);
    dbField.add(field);
    if (this.viewEditMode == GaConstant.EDITMODE_NEW) {
      field.setQueryParamFormSource(GaConstant.INPUT_REQUEST, "P_ID");
    }
    
    field = new DbField("类型","TYPE",GaConstant.DT_LONG);
    field.setInput(GaConstant.INPUT_SELECT);
    field.setVerifyFormula("", true);
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT,0,false);
    Map<String, Object>  lookup = new LinkedHashMap<String, Object>();
    lookup.put("1", "菜单");
    lookup.put("2", "按钮");
    field.setLookupData(new LookupDataSet(lookup));
    dbField.add(field);
    
    if(this.viewEditMode == GaConstant.EDITMODE_DISP){
      field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
      field.setInputMode(GaConstant.EDITMODE_DISP, GaConstant.INPUTMODE_READONLY);
      field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm",false));
      field.setInputMode(GaConstant.EDITMODE_DISP, GaConstant.INPUTMODE_READONLY);
      field.setInputMode(GaConstant.EDITMODE_DISP, GaConstant.INPUTMODE_READONLY);
      dbField.add(field);
    }
       
    field = new DbField("状态","STATE",GaConstant.DT_INT);
    field.setInput(GaConstant.INPUT_SELECT);
    field.setVerifyFormula("", true);
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT,0,false);
    lookup = new HashMap<String, Object>();
    lookup.put("1", "正常");
    lookup.put("0", "无效");
    field.setLookupData(new LookupDataSet(lookup));
    dbField.add(field);
    
    field = new DbField("备注", "NOTE", GaConstant.DT_STRING);
    field.setInput(GaConstant.INPUT_TEXTAREA);
    dbField.add(field);
    field.addInputAttributeMap("rows", "5");
    field.addInputAttributeMap("cols", "60");
    this.fieldList = dbField;
  }

}
