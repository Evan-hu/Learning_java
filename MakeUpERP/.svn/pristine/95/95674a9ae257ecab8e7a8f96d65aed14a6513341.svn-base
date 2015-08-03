package com.ga.erp.biz.activity.scorelog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.control.StringDecorator;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class ScoreLogListView extends ListView {
	
  public ScoreLogListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {
	  
	List<DbField> dbFields = new ArrayList<DbField>();
	  
	DbField field = new DbField("积分使用记录ID", "SCORE_RECORD_ID",GaConstant.DT_LONG );
	field.setColumnDisplay(false, 0, true); 
	dbFields.add(field);
	  
	field = new DbField("积分操作类型", "ACTION_TYPE",GaConstant.DT_INT);
	Map<String, Object> option = new HashMap<String, Object>();
	option.put("1", "规则产生");
	option.put("2", "手动分配");
	option.put("3", "积分消费");
	field.setLookupData(new LookupDataSet(option));
	field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 1, false);
	dbFields.add(field);
	
	field = new DbField("产生积分", "SCORE", GaConstant.DT_MONEY);
	field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_MONEY, 2, false);
	field.setAliasCode("sl");
	field.setInputCustomStyle("width:50px;");
	dbFields.add(field);
	
	field = new DbField("当前积分", "NOW_SCORE", GaConstant.DT_MONEY);
	dbFields.add(field);
	
	field = new DbField("积分规则", "RULE_NAME", GaConstant.DT_STRING);
	field.setQueryOpera(GaConstant.QUERY_LIKE, 3, false);
	dbFields.add(field);
	

	field = new DbField("会员", "USERNAME",GaConstant.DT_STRING);
//	field.setAliasCode("m");
//	field.setQueryOpera(GaConstant.QUERY_LIKE, 4, false);
	dbFields.add(field);
	
	field = new DbField("业务类型", "BIZ_TYPE",GaConstant.DT_INT);
	option = new HashMap<String, Object>();
	option.put("1", "用户订单");
	option.put("2", "管理员手动修改");
	field.setLookupData(new LookupDataSet(option));
	field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 6, false);
	dbFields.add(field);
	
	field = new DbField("关联对象title", "NEWBIZ_NAME", GaConstant.DT_STRING);
	field.setColumnDisplay(false,120, false);
	dbFields.add(field);
	
	field = new DbField("关联对象", "BIZ_NAME", GaConstant.DT_STRING);
	field.setQueryOpera(GaConstant.QUERY_LIKE, 7, false);
	field.setMaxLength(15);
	StringDecorator decorator = new StringDecorator();
	decorator.setStringFormat("<font title='{NEWBIZ_NAME}' style='cursor:pointer;'>{value}</font>");
	field.setColumnDecorator("", decorator);
	dbFields.add(field);

	field = new DbField("产生时间", "CREATE_TIME", GaConstant.DT_DATETIME);
	field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
	field.setInputCustomStyle("width:80px;");
	field.setQueryOpera(GaConstant.QUERY_BETWEEN, 8, false);
	dbFields.add(field);
	    
	field = new DbField("状态", "STATE",GaConstant.DT_INT);
	field.setAliasCode("sl");
	field.setLookupData(SystemUtil.getStatusMap());
	field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 9, false);
	dbFields.add(field);
	
	field = new DbField("备注title", "NEWNOTE", GaConstant.DT_STRING);
	field.setColumnDisplay(false,10, false);
	dbFields.add(field);
	
	field = new DbField("备注", "NOTE", GaConstant.DT_STRING);
	field.setColumnDisplay(true,120, false);
	decorator = new StringDecorator();
	decorator.setStringFormat("<font title='{NEWNOTE}' style='cursor:pointer;'>{value}</font>");
	field.setColumnDecorator("", decorator);
	field.setMaxLength(50);
	dbFields.add(field);

    this.fieldList = dbFields;
  }
  
}
