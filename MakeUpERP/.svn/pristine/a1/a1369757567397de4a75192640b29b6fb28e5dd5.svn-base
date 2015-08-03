package com.ga.erp.biz.content.stationmsg;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.util.SystemUtil;

public class MsgSendListView extends ListView {
	
  public MsgSendListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {
	  
  	List<DbField> dbFields = new ArrayList<DbField>();
  	    
  	DbField field = new DbField("ID","MESSAGE_SEND_ID",GaConstant.DT_LONG); 
  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "MESSAGE_SEND_ID");
  	field.setColumnDisplay(false, 0, true);
  	dbFields.add(field);
  	    
  	field = new DbField("标题", "TITLE", GaConstant.DT_STRING);
  	field.setQueryOpera(GaConstant.QUERY_LIKE, 1, false);
  	dbFields.add(field);
  	
    field = new DbField("对象类型","OBJECT_TYPE",GaConstant.DT_STRING);
    Map<String, Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("1", "会员");
    lookup.put("2", "门店");
    lookup.put("3", "供应商");
    field.setLookupData(new LookupDataSet(lookup));
    dbFields.add(field);
  	
  	field = new DbField("对象名称","OBJECT_NAME",GaConstant.DT_STRING);
  	field.setQueryOpera(GaConstant.QUERY_LIKE, 3, false, false);
  	dbFields.add(field);

    field = new DbField("状态","STATE",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 4, false);
    field.setLookupData(SystemUtil.getMessageSendStateMap());
    field.setAliasCode("MS");
    dbFields.add(field);

    this.fieldList = dbFields;
  }
}
