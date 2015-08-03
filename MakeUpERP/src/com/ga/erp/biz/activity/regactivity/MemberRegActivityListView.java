package com.ga.erp.biz.activity.regactivity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;

public class MemberRegActivityListView extends ListView {
	
  public MemberRegActivityListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {
	  
	 List<DbField> dbFields = new ArrayList<DbField>();
	    
	 DbField field = new DbField("ID", "MEMBER_REG_ACTIVITY_ID",GaConstant.DT_LONG );
	 field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "MEMBER_REG_ACTIVITY_ID");
	 field.setColumnDisplay(false, 0, true);  
	 field.setPK(true);
	 dbFields.add(field);
	 
	 field = new DbField("会员名称", "TRUENAME", GaConstant.DT_STRING);
	 field.setQueryOpera(GaConstant.QUERY_LIKE, 1, false);
	 dbFields.add(field);
	    
	 field = new DbField("活动形式", "TYPE", GaConstant.DT_INT);
	 Map<String,Object> cycle = new LinkedHashMap<String, Object>();
   cycle.put("1", "赠送积分");
   cycle.put("2", "赠送礼券");
   field.setLookupData(new LookupDataSet(cycle));
   field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT,2,false);
	 dbFields.add(field);
	 
   field = new DbField("礼券编号", "CARD_NUM", GaConstant.DT_STRING);
   field.setQueryOpera(GaConstant.QUERY_LIKE, 3, false);;
   field.setColumnDisplay(true, 0, false);
   field.setAliasCode("c");
   dbFields.add(field);
   
   field = new DbField("获得值", "OBJECT_VALUE", GaConstant.DT_STRING);
   field.setQueryOpera(GaConstant.QUERY_LIKE, 4, false);
   field.setColumnDisplay(true, 0, false);
   dbFields.add(field);
   
   field = new DbField("创建时间", "CREATE_TIME", GaConstant.DT_DATETIME);
   field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
//   field.setInputCustomStyle("width:80px;");
   field.setQueryOpera(GaConstant.QUERY_BETWEEN, 5, false);
   field.setAliasCode("mra");
   dbFields.add(field);
   
    this.fieldList = dbFields;
  }
  
}
