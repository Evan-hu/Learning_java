package com.ga.erp.biz.system.audits.config;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.util.SystemUtil;

public class AudConfigFormView extends FormView {

  public AudConfigFormView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }
  
  @Override
  public void initField() throws Exception {

    List<DbField> dbFields = new ArrayList<DbField>();

    DbField field = new DbField("配置ID", "AUDITING_CONFIG_ID", GaConstant.DT_LONG);
    field.setInput(GaConstant.INPUT_HIDDEN);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "AUDITING_CONFIG_ID");
    dbFields.add(field);
    
    field = new DbField("编码ID","SYS_CODE_ID",GaConstant.DT_INT);
    field.setPopSelect("NEWCODESELECT", "SYS_CODE_ID", false);
  	dbFields.add(field);

  	field = new DbField("业务名称", "SYS_CODE_NAME", GaConstant.DT_STRING);
  	field.setInputCustomStyle("width:140px");
  	field.setPopSelect("NEWCODESELECT","SYS_CODE_NAME",true,"/system/syscode_sele.htm",
        "SYS_CODE_ID,SYS_CODE_NAME,cid_syscodeTree", 300,400);
    dbFields.add(field);
  	
    field = new DbField("审核中允许修改", "CHECK_IN_TYPE", GaConstant.DT_INT);
    field.setInputMode(GaConstant.EDITMODE_NEW,GaConstant.INPUTMODE_HIDDEN);
    field.setInput(GaConstant.INPUT_SELECT);
    field.setLookupData(SystemUtil.getYesNoMap());
    field.setVerifyFormula("", true);
    dbFields.add(field);
    
    field = new DbField("审核不通过允许修改", "CHECK_LOSE_TYPE", GaConstant.DT_INT);
    field.setInputMode(GaConstant.EDITMODE_NEW,GaConstant.INPUTMODE_HIDDEN);
    field.setInput(GaConstant.INPUT_SELECT);
    field.setLookupData(SystemUtil.getYesNoMap());
    field.setVerifyFormula("", true);
    dbFields.add(field);
    
    field = new DbField("审核通过允许修改", "CHECK_SUCCESS_TYPE", GaConstant.DT_INT);
    field.setInputMode(GaConstant.EDITMODE_NEW,GaConstant.INPUTMODE_HIDDEN);
    field.setInput(GaConstant.INPUT_SELECT);
    field.setLookupData(SystemUtil.getYesNoMap());
    field.setVerifyFormula("", true);
    dbFields.add(field);

    
    field = new DbField("状态", "STATE", GaConstant.DT_INT);
    field.setInputMode(GaConstant.EDITMODE_NEW,GaConstant.INPUTMODE_HIDDEN);
    field.setInput(GaConstant.INPUT_SELECT);
    field.setLookupData(SystemUtil.getStatusMap());
    field.setVerifyFormula("", true);
    dbFields.add(field);

    
    
//    field = new DbField("是否允许多次审核", "IS_REPEAT", GaConstant.DT_INT);
//    field.setInput(GaConstant.INPUT_SELECT);
//    field.setLookupData(SystemUtil.getYesNoMap());
//    dbFields.add(field);
    
//    field = new DbField("审核次数上限", "MAX_CNT", GaConstant.DT_INT);
//    field.setInputCustomStyle("width:50px;");
//    dbFields.add(field);
    
    this.fieldList = dbFields;
  }

}
