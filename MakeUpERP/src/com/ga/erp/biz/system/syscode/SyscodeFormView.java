package com.ga.erp.biz.system.syscode;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.util.SystemUtil;

public class SyscodeFormView extends FormView{

  public SyscodeFormView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }
  
  @Override
  public void initField() throws Exception {

    List<DbField> dbField = new ArrayList<DbField>();
    
    DbField field = new DbField("±àÂë","SYS_CODE_ID",GaConstant.DT_LONG); 
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "SYS_CODE_ID");
    field.setInput(GaConstant.INPUT_HIDDEN);
    dbField.add(field);
    
    field = new DbField("±àÂëÃû³Æ", "SYS_CODE_NAME", GaConstant.DT_STRING);
    dbField.add(field);

    field = new DbField("±àÂëÖµ", "SYS_CODE_VALUE", GaConstant.DT_STRING);
    dbField.add(field);
    
    field = new DbField("ÉÏ¼¶±àÂë","P_NAME",GaConstant.DT_STRING);
    field.setPopSelect(this.viewEditMode == GaConstant.EDITMODE_NEW ? "selectNew" : "selectEdit", "SYS_CODE_NAME" ,true,
        "/system/syscode_sele.htm",
        "SYS_CODE_ID,SYS_CODE_NAME,cid_syscodeTree", 230, 400);
    dbField.add(field);
    if (this.viewEditMode == GaConstant.EDITMODE_NEW) {
      field.setQueryParamFormSource(GaConstant.INPUT_REQUEST, "P_NAME");
    }
    
    field = new DbField("ÉÏ¼¶±àÂëID","P_CODE",GaConstant.DT_LONG);
    field.setPopSelect(this.viewEditMode == GaConstant.EDITMODE_NEW ? "selectNew" : "selectEdit", "SYS_CODE_ID", false);
    dbField.add(field);
    if (this.viewEditMode == GaConstant.EDITMODE_NEW) {
      field.setQueryParamFormSource(GaConstant.INPUT_REQUEST, "P_CODE");
    }
           
    field = new DbField("×´Ì¬","STATE",GaConstant.DT_INT);
    field.setInput(GaConstant.INPUT_SELECT);
    field.setVerifyFormula("", true);
    field.setLookupData(SystemUtil.getStatusMap());
    dbField.add(field);
    
    field = new DbField("±¸×¢", "NOTE", GaConstant.DT_STRING);
    field.setInput(GaConstant.INPUT_TEXTAREA);
    dbField.add(field);
    field.addInputAttributeMap("rows", "5");
    field.addInputAttributeMap("cols", "60");
    
    this.fieldList = dbField;
    
  }

}
