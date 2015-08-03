package com.ga.erp.biz.system.audits.config;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.util.SystemUtil;

public class AudConfigListView extends ListView {
	
  public AudConfigListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {
	  
    List<DbField> dbFields = new ArrayList<DbField>();
	    
  	DbField field = new DbField("ID","AUDITING_CONFIG_ID",GaConstant.DT_LONG); 
  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "AUDITING_CONFIG_ID");
  	field.setColumnDisplay(false, 0, true);
  	dbFields.add(field);
  	
   	field = new DbField("编码ID", "SYS_CODE_ID", GaConstant.DT_INT);
   	field.setColumnDisplay(false, 0, true);
   	dbFields.add(field);
  	
  	field = new DbField("业务名称", "SYS_CODE_NAME", GaConstant.DT_STRING);
  	field.setQueryOpera(GaConstant.QUERY_LIKE, 2, true);
  	field.setInputCustomStyle("width:70px");
    dbFields.add(field);
  	
    field = new DbField("状态", "STATE", GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT, 3, true);
    field.setLookupData(SystemUtil.getStatusMap());
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

//    field = new DbField("是否允许多次审核", "IS_REPEAT", GaConstant.DT_INT);
//    field.setLookupData(SystemUtil.getYesNoMap());
//    dbFields.add(field);
    
//    field = new DbField("审核次数上限", "MAX_CNT", GaConstant.DT_INT);
//    dbFields.add(field);

    this.fieldList = dbFields;
  }
  
}
