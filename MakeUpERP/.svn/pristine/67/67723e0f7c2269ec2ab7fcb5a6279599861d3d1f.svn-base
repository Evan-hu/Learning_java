package com.ga.erp.biz.system;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;

public class OpRoleListView extends ListView {

  public OpRoleListView(String viewID, ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }
  
  @Override
  public void initField() throws Exception {
    // TODO Auto-generated method stub
    List<DbField> fields = new ArrayList<DbField>();
    DbField field = new DbField("OP_ROLE_ID","OP_ROLE_ID",GaConstant.DT_LONG);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "OP_ROLE_ID");
    field.setPK(true);
    field.setColumnDisplay(false, 0, true);
    fields.add(field);
    
    field = new DbField("½ÇÉ«Ãû³Æ","ROLE_NAME",GaConstant.DT_STRING);
    field.setColumnDisplay(true, 250, false);
    fields.add(field);
    
    this.fieldList = fields;
  }
}
