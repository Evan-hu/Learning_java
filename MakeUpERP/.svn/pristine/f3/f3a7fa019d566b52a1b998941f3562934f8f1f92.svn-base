package com.ga.erp.biz.content.stationmsg;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class StationMsgListView extends ListView {
	
  public StationMsgListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {
	  
  	List<DbField> dbFields = new ArrayList<DbField>();
  	    
  	DbField field = new DbField("ID","STATION_MSG_ID",GaConstant.DT_LONG); 
  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "STATION_MSG_ID");
  	field.setColumnDisplay(false, 0, true);
  	dbFields.add(field);
  	    
  	field = new DbField("标题", "TITLE", GaConstant.DT_STRING);
  	field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
  	dbFields.add(field);
	
    field = new DbField("创建人","TRUENAME",GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 2, true);
    dbFields.add(field);
    
    field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_DATETIME, 3, true);
    dbFields.add(field);
    
    field = new DbField("状态","STATE",GaConstant.DT_INT);
    field.setAliasCode("s");
    field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT, 4, true);
    field.setLookupData(SystemUtil.getStationMsgStateMap());
    field.setColumnDisplay(true, 50, false);
    dbFields.add(field);

    this.fieldList = dbFields;
  }
}
