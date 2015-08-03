package com.ga.erp.biz.system.role;

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

public class RoleListView extends ListView {
  public RoleListView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap, GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {
    
    List<DbField> dbField = new ArrayList<DbField>();
    DbField field = new DbField("ID", "ROLE_ID", GaConstant.DT_LONG);
    field.setPK(true);
    field.setColumnDisplay(false, 0, true);
    dbField.add(field);
    
    field = new DbField("角色名称", "ROLE_NAME", GaConstant.DT_STRING);
    field.setColumnDisplay(true, 200, true);
    field.setQueryOpera(GaConstant.QUERY_LIKE,0,false);
    dbField.add(field);
    
    
    field = new DbField("创建人","USERNAME",GaConstant.DT_STRING);
    field.setColumnDisplay(true, 200, false);
    dbField.add(field);
    
    field = new DbField("创建时间", "CREATE_TIME", GaConstant.DT_DATETIME);
    field.setColumnDisplay(true, 200, false);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm",false));
    dbField.add(field);
    
    field = new DbField("状态","STATE",GaConstant.DT_LONG);
    field.setAliasCode("R");
    field.setQueryOpera(GaConstant.QUERY_LIKE,GaConstant.INPUT_SELECT, 0, true);
    field.setInput(GaConstant.INPUT_SELECT);    
    Map<String,Object> lookup = new HashMap<String, Object>();
    lookup.put("0", "废止");
    lookup.put("1", "正常");
    field.setLookupData(new LookupDataSet(lookup));
    field.setColumnDisplay(true, 100, false);
    dbField.add(field);

    field = new DbField("OP_ID", "OP_ID", GaConstant.DT_LONG);
    field.setQueryParamFormSource(GaConstant.INPUT_REQUEST, "OP_ID");
    field.setColumnDisplay(false, 0, true);
    dbField.add(field);
    this.fieldList = dbField;
  }
}
