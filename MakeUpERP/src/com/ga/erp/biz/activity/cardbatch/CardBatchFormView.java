package com.ga.erp.biz.activity.cardbatch;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class CardBatchFormView extends FormView {

  public CardBatchFormView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }
  
  @Override
  public void initField() throws Exception {
    List<DbField> dbFields = new ArrayList<DbField>();
    DbField field = null;
    
    field = new DbField("CID", "CARD_BATCH_ID", GaConstant.DT_LONG);
    field.setInput(GaConstant.INPUT_HIDDEN);
    field.setPK(true);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "CARD_BATCH_ID");
    dbFields.add(field);
    
    field = new DbField("��������", "NAME", GaConstant.DT_STRING);
    dbFields.add(field);
    
    field = new DbField("��С�������", "MIN_MONEY", GaConstant.DT_MONEY);
    field.setInputCustomStyle("width:50px;");
    dbFields.add(field);
    
    field = new DbField("�Żݽ��", "SAVE_MONEY", GaConstant.DT_MONEY);
    field.setDefaultValue(0);
    field.setInputCustomStyle("width:50px;");
    dbFields.add(field);
    
    field = new DbField("״̬", "STATE", GaConstant.DT_LONG);
    field.setVerifyFormula("", true);
    field.setInput(GaConstant.INPUT_SELECT);
    field.setLookupData(SystemUtil.getStatusMap());
    field.setDefaultValue(1);
    dbFields.add(field);
    
    field = new DbField("��ʼʱ��", "BEGIN_TIME", GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    dbFields.add(field);
    
    field = new DbField("����ʱ��", "END_TIME", GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    dbFields.add(field);
    
    field = new DbField("����ʱ��", "CREATE_TIME", GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    dbFields.add(field);
    
    if (this.viewEditMode == GaConstant.EDITMODE_NEW){
      field = new DbField("��������", "AUTO_COUNT", GaConstant.DT_INT);
      field.setDefaultValue(0);
      dbFields.add(field);
    }
    
    field = new DbField("��ע", "NOTE", GaConstant.DT_STRING);
    field.setInput(GaConstant.INPUT_TEXTAREA);
    field.addInputAttributeMap("rows", "3");
    field.addInputAttributeMap("cols", "73");
    dbFields.add(field);
    
    this.funcMap.put("CREATE_TIME", "sysdate");
    this.fieldList = dbFields;
  }

}
