package com.ga.erp.biz.comm.sort;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class SortFormView extends FormView{

  public SortFormView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }
  
  @Override
  public void initField() throws Exception {
	  
    List<DbField> dbFields = new ArrayList<DbField>();
    
    DbField field = new DbField("ID","SORT_ID",GaConstant.DT_LONG); 
    field.setInput(GaConstant.INPUT_HIDDEN);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "SORT_ID");
    dbFields.add(field);
    
  	field = new DbField("分类名称", "SORT_NAME", GaConstant.DT_STRING);
  	field.getFieldVerify().setRequire(true);
  	dbFields.add(field);
  	
  	field = new DbField("上级分类ID","P_ID", GaConstant.DT_LONG);
  	field.setPopSelect(this.viewEditMode == GaConstant.EDITMODE_NEW ? "selectNew" : "selectEdit", "SORT_ID", false);
  	
  	dbFields.add(field);
    
    field = new DbField("上级分类名称<font color='red'>*</font>","PSORT_NAME",GaConstant.DT_STRING);
    field.setPopSelect(this.viewEditMode == GaConstant.EDITMODE_NEW ? "selectNew" : "selectEdit","SORT_NAME",true,
        "/comm/sort_sele.htm",
        "SORT_ID,SORT_NAME,cid_sortTree",300,400);
        
    dbFields.add(field);
      
    field = new DbField("编码","CODE",GaConstant.DT_STRING);
    field.setInputCustomStyle("width:50px;");
    dbFields.add(field);
      
    field = new DbField("提成比率","CUT_RATIO",GaConstant.DT_DOUBLE);
    field.setDefaultValue(0.0);
    field.setInputCustomStyle("width:50px;");
    field.setTipInfo("&nbsp;&nbsp;%");
    dbFields.add(field);
    
  	field = new DbField("排序","RANK_NUM",GaConstant.DT_STRING);
  	field.setInputCustomStyle("width:50px;");
  	dbFields.add(field);
  	    
  	field = new DbField("状态","STATE",GaConstant.DT_INT);
  	field.setInput(GaConstant.INPUT_SELECT);
  	field.setLookupData(SystemUtil.getStatusMap());
  	field.setVerifyFormula("", true);
  	dbFields.add(field);
  	    
  	field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setInputMode(GaConstant.EDITMODE_DISP, GaConstant.INPUTMODE_READONLY);
    dbFields.add(field);
      
    field = new DbField("备注", "NOTE", GaConstant.DT_STRING);
    field.setInput(GaConstant.INPUT_TEXTAREA);
    field.addInputAttributeMap("rows", "3");
    field.addInputAttributeMap("cols", "30");
    dbFields.add(field);
    
    this.fieldList = dbFields;
    
  }
}
