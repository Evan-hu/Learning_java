package com.ga.erp.biz.system.audits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class AuditsListView extends ListView  {

  public AuditsListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap, GaConstant.EDITMODE_DISP);
  }
  
  @Override
  public void initField() throws Exception {
    
    List<DbField> dbFields = new ArrayList<DbField>();
    
    DbField field = new DbField("ID","AUDITING_ID",GaConstant.DT_LONG);
    field.setColumnDisplay(false,0,true);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "AUDITING_ID");
    dbFields.add(field);
    
    field= new DbField("业务ID", "BIZ_ID", GaConstant.DT_INT);
    field.setColumnDisplay(false, 0, false);
    dbFields.add(field);
    
    field = new DbField("审核实例ID", "AUDITING_EXAMPLE_ID",GaConstant.DT_LONG);
    field.setColumnDisplay(false, 0, true);
    dbFields.add(field);
    
    field= new DbField("审核人", "TRUENAME", GaConstant.DT_STRING);
    dbFields.add(field);
    
    field= new DbField("审核步骤", "STEP", GaConstant.DT_INT);
    dbFields.add(field);
    
    field = new DbField("审核时间","CREATE_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, 2, false);
    field.setInputCustomStyle("width:80px;");
    dbFields.add(field);
    
    field= new DbField("审核结果", "RESULT_CODE", GaConstant.DT_INT);
    field.setLookupData(SystemUtil.getAuditsMap());
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT,3, false);
    dbFields.add(field);
    
    field = new DbField("审核说明", "RESULT_NOTE", GaConstant.DT_STRING);
    field.setColumnDisplay(false, 150, false);
//    field.setInput(GaConstant.INPUT_TEXTAREA);
//	field.addInputAttributeMap("rows", "5");
//	field.addInputAttributeMap("cols", "60");
    field.setMaxLength(200);
    dbFields.add(field);
    
    this.fieldList  = dbFields;
  }
}
