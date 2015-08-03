package com.ga.erp.biz.activity.scorerule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.util.SystemUtil;

public class ScoreRuleFormView extends FormView {

  public ScoreRuleFormView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }

  @Override
  public void initField() throws Exception {

    List<DbField> dbFields = new ArrayList<DbField>();

    DbField field = new DbField("积分规则id", "SCORE_RULE_ID", GaConstant.DT_LONG);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "SCORE_RULE_ID");
    field.setInput(GaConstant.INPUT_HIDDEN);
    dbFields.add(field);
    
    field = new DbField("规则名称<font color='red'>*</font>", "RULE_NAME", GaConstant.DT_STRING);
    dbFields.add(field);
    
    field = new DbField("产生积分<font color='red'>*</font>", "SCORE", GaConstant.DT_MONEY);
    field.setInputCustomStyle("width:50px;");
    field.setDefaultValue("0");
    field.setTipInfo("0表示非固定积分");
    dbFields.add(field);
    
    field = new DbField("时间间隔", "TIME_PERIOD", GaConstant.DT_LONG);
    field.setInputCustomStyle("width:50px;");
    dbFields.add(field);
    
    field = new DbField("奖励周期","REWARD_CYCLE",GaConstant.DT_INT);
    field.setInput(GaConstant.INPUT_SELECT);
    Map<String,Object> option = new LinkedHashMap<String, Object>();
    option.put("0", "一次性");
    option.put("1", "天");
    option.put("2", "小时");
    option.put("3", "分钟");
    option.put("4", "不限周期");
    field.setLookupData(new LookupDataSet(option));
    field.setVerifyFormula("", true);
    dbFields.add(field);
    
    field = new DbField("奖励次数", "REWARD_TIME", GaConstant.DT_LONG);
    field.setInputCustomStyle("width:50px;");
    field.setDefaultValue("0");
    field.setTipInfo("0表示不限次数");
    dbFields.add(field);
   
    field = new DbField("奖励方式", "REWARD_TYPE", GaConstant.DT_LONG);
    field.setInput(GaConstant.INPUT_SELECT);
    option = new HashMap<String, Object>();
    option.put("0", "加分");
    option.put("1", "减分");
    field.setLookupData(new LookupDataSet(option));
    field.setVerifyFormula("", true);
    dbFields.add(field);
    
	field = new DbField("创建类型", "CREATE_TYPE", GaConstant.DT_INT);
	 field.setInput(GaConstant.INPUT_SELECT);
	option = new HashMap<String, Object>();
	option.put("1", "数量");
	option.put("2", "百分比");
	field.setLookupData(new LookupDataSet(option));
	field.setVerifyFormula("", true);
	dbFields.add(field);
    
    field = new DbField("状态","STATE",GaConstant.DT_INT);
    field.setInput(GaConstant.INPUT_SELECT);
    field.setLookupData(SystemUtil.getStatusMap());
    field.setVerifyFormula("", true);
    dbFields.add(field);

    this.fieldList = dbFields;
  }

}
