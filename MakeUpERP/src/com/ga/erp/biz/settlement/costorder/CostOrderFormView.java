package com.ga.erp.biz.settlement.costorder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class CostOrderFormView extends FormView {

  public CostOrderFormView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }

  @Override
  public void initField() throws Exception {

    List<DbField> dbFields = new ArrayList<DbField>();

    DbField field = new DbField("费用单ID","COST_ORDER_ID",GaConstant.DT_LONG); 
    field.setInput(GaConstant.INPUT_HIDDEN);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "COST_ORDER_ID");
    dbFields.add(field);
    
    field = new DbField("业务单据号","COST_ORDER_NUM",GaConstant.DT_STRING); 
    field.getFieldVerify().setRequire(true);
    dbFields.add(field);
        
    field = new DbField("编码ID","SYS_CODE_ID",GaConstant.DT_LONG);
    field.setPopSelect("NEWCODESELECT", "SYS_CODE_ID", false);
    dbFields.add(field);
    
    field = new DbField("业务名称", "SYS_CODE_NAME", GaConstant.DT_STRING);
    field.setInputCustomStyle("width:140px");
    field.setPopSelect("NEWCODESELECT","SYS_CODE_NAME",true,"/system/syscode_sele.htm",
        "SYS_CODE_ID,SYS_CODE_NAME,cid_syscodeTree", 300,400);
    dbFields.add(field);

    field = new DbField("对象ID","OBJECT_ID",GaConstant.DT_LONG);
    field.setPopSelect("OBJECTSELECT", "STORE_ID", false);
    dbFields.add(field);
    
    field = new DbField("名称", "OBJECT_NAME", GaConstant.DT_STRING);
    field.setPopSelect("OBJECTSELECT","STORE_NAME",true,"/store/store_main.htm",
        "STORE_ID,STORE_NAME,cid_storeList", 800,400);
    field.setInputCustomStyle("width:130px");
    dbFields.add(field);
    
    field = new DbField("对象类型","OBJECT_TYPE",GaConstant.DT_INT);
    field.setInput(GaConstant.INPUT_HIDDEN);
    dbFields.add(field);

    field = new DbField("发生金额","MONEY",GaConstant.DT_MONEY);
    field.getFieldVerify().setRequire(true);
    field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
    dbFields.add(field);
    
    field = new DbField("收支方式","BUDGET",GaConstant.DT_INT);
    field.setInput(GaConstant.INPUT_SELECT);
    Map<String, Object> op = new LinkedHashMap<String, Object>();
    op.put("1", "收入");
    op.put("2", "支出");
    field.setLookupData(new LookupDataSet(op));
    field.setVerifyFormula("", true);
    dbFields.add(field);

    field = new DbField("付款时间","PAY_TIME",GaConstant.DT_DATETIME);
    field.setInputMode(GaConstant.EDITMODE_NEW,GaConstant.INPUTMODE_HIDDEN);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    dbFields.add(field);
    
    field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
    field.setInputMode(GaConstant.EDITMODE_NEW,GaConstant.INPUTMODE_HIDDEN);
    field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    dbFields.add(field);
    
    field = new DbField("创建人","TRUENAME",GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_NEW,GaConstant.INPUTMODE_HIDDEN);
    field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
    field.setInputCustomStyle("width:80px;");
    dbFields.add(field);
    
    field = new DbField("审核状态", "CHECK_STATE", GaConstant.DT_LONG);
    field.setInputMode(GaConstant.EDITMODE_NEW,GaConstant.INPUTMODE_HIDDEN);
    field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
    field.setInput(GaConstant.INPUT_SELECT);
    field.setLookupData(SystemUtil.getAuditsMap());
    dbFields.add(field);
    
    field = new DbField("状态","STATE",GaConstant.DT_LONG);
    field.setInputMode(GaConstant.EDITMODE_NEW,GaConstant.INPUTMODE_HIDDEN);
    field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
    field.setInput(GaConstant.INPUT_SELECT);
    Map<String, Object> stateMap = new LinkedHashMap<String, Object>();
    stateMap.put("0", "待结算");
    stateMap.put("1", "待付款");
    stateMap.put("2", "已结算");
    field.setLookupData(new LookupDataSet(stateMap));
    field.setVerifyFormula("", true);
    dbFields.add(field);

    field = new DbField("备注", "NOTE", GaConstant.DT_STRING);
    field.setInput(GaConstant.INPUT_TEXTAREA);
    field.addInputAttributeMap("rows", "5");
    field.addInputAttributeMap("cols", "60");
    field.setMaxLength(200);
    dbFields.add(field);
    
    this.fieldList = dbFields;
  }

}
