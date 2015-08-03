package com.ga.erp.biz.activity.scorerule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.util.SystemUtil;

public class ScoreRuleListView extends ListView {
	
  public ScoreRuleListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {
	  
	 List<DbField> dbFields = new ArrayList<DbField>();
	    
	 DbField field = new DbField("积分规则ID", "SCORE_RULE_ID",GaConstant.DT_LONG );
	 field.setColumnDisplay(false, 0, true);  
	 field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "SCORE_RULE_ID");
	 dbFields.add(field);
	 
	 field = new DbField("规则名称", "RULE_NAME", GaConstant.DT_STRING);
	 field.setQueryOpera(GaConstant.QUERY_LIKE, 1, false);
	 dbFields.add(field);
	    
	 field = new DbField("产生积分", "SCORE", GaConstant.DT_MONEY);
	 dbFields.add(field);
	    
	 field = new DbField("时间间隔", "TIME_PERIOD", GaConstant.DT_LONG);
	 field.setColumnDisplay(true, 120, false);
	 dbFields.add(field);
	    
	 field = new DbField("奖励周期","REWARD_CYCLE",GaConstant.DT_INT);
	 Map<String,Object> cycle = new LinkedHashMap<String, Object>();
	 cycle.put("0", "一次性");
	 cycle.put("1", "天");
	 cycle.put("2", "小时");
	 cycle.put("3", "分钟");
	 cycle.put("4", "不限周期");
	 field.setLookupData(new LookupDataSet(cycle));
	 field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT,2,false);
	 dbFields.add(field);
	    
	 field = new DbField("奖励次数", "REWARD_TIME", GaConstant.DT_LONG);
	 dbFields.add(field);
	   
	 field = new DbField("奖励方式", "REWARD_TYPE", GaConstant.DT_LONG);
	 field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 3,false);
	 Map<String, Object> type = new HashMap<String, Object>();
	 type.put("0", "加分");
	 type.put("1", "减分");
	 field.setLookupData(new LookupDataSet(type));
	 dbFields.add(field);
	    
	 field = new DbField("创建类型", "CREATE_TYPE", GaConstant.DT_INT);
	 field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT, 5,false);
	 Map<String, Object> option = new LinkedHashMap<String, Object>();
	 option.put("1", "数量");
	 option.put("2", "百分比");
	 field.setLookupData(new LookupDataSet(option));
	 dbFields.add(field); 
	    
	 field = new DbField("状态","STATE",GaConstant.DT_INT);
	 field.setLookupData(SystemUtil.getStatusMap());
	 field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 6,false);
	 dbFields.add(field);

    this.fieldList = dbFields;
  }
  
}
