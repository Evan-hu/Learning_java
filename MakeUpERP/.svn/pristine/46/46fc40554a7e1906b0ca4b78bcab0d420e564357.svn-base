package com.ga.erp.biz.activity.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.control.LookupDataSet;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;

public class CardFormView extends FormView {

  public CardFormView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }
  
  @Override
  public void initField() throws Exception {
    
    List<DbField> dbFields=new ArrayList<DbField>();
    DbField field=null;
    
    field=new DbField("CardID", "CARD_ID", GaConstant.DT_LONG);
    field.setInput(GaConstant.INPUT_HIDDEN);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "CARD_ID");
    dbFields.add(field);
    
    field=new DbField("电子券编号", "CARD_NUM", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setInputCustomStyle("width:300px;background-color:#FFFFFF;border:none");
    dbFields.add(field);
    
    field = new DbField("电话号码", "TEL", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setTipInfo("(会员收到短信的号码)");
    dbFields.add(field);
    
    field=new DbField("修改状态 ", "NEWSTATE", GaConstant.DT_LONG);
    field.setVerifyFormula("", true);
    field.setInput(GaConstant.INPUT_SELECT);
    Map<String, Object> lookNewldState=new HashMap<String, Object>();
    lookNewldState.put("0", "无效");
    lookNewldState.put("1", "未使用");
    lookNewldState.put("2", "已使用");
    field.setLookupData(new LookupDataSet(lookNewldState));
    field.setInputCustomStyle("width:100px");
    dbFields.add(field);

    field=new DbField("说明", "EXPLAIN", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setInputCustomStyle("width:375px;background-color:#FFFFFF;border:none;color:#FF0000");
    dbFields.add(field);
    
    field=new DbField("备注", "NOTE", GaConstant.DT_STRING);
    field.setInput(GaConstant.INPUT_TEXTAREA);
    field.addInputAttributeMap("rows", "3");
    field.addInputAttributeMap("cols", "57");
    dbFields.add(field);

    this.funcMap.put("CREATE_TIME", "sysdate");
    this.fieldList=dbFields;
  }

}
