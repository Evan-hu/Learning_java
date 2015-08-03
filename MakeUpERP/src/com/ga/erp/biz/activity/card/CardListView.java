package com.ga.erp.biz.activity.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.control.DbFieldFormat;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.control.StringDecorator;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.activity.cardbatch.BatchBiz;
import com.ga.erp.util.SystemUtil;

public class CardListView extends ListView {

  public CardListView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap,GaConstant.EDITMODE_DISP);
  }
  
  @Override
  public void initField() throws Exception {
    
    List<DbField> dbFields = new ArrayList<DbField>();
    
    DbField field = new DbField("ȯID", "CARD_ID", GaConstant.DT_LONG);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "CARD_ID");
    field.setColumnDisplay(false, 0, true);
    dbFields.add(field);
    
    field = new DbField("�Ż�ȯ��", "CARD_NUM", GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 1, false);
    ActionButton action = new ActionButton(this.getClass(),"CARD_ID","{value}","/activity/card_detail.htm",null);
    SubmitTool.submitToDialog(action, "CARD_ID","�Ż�ȯ����", 600, 350, this.modelMap.getPreNavInfoStr());
    StringDecorator decorator = new StringDecorator();
    decorator.setLinkDecorator(action);
    action.bindTableRowData(this.viewID);
    action.addParam(GaConstant.FIXPARAM_EDITMODE,GaConstant.EDITMODE_EDIT);
    field.setColumnDecorator("", decorator);
    dbFields.add(field);

    field = new DbField("��������", "CARD_BATCH_ID", GaConstant.DT_LONG);
    field.setAliasCode("cb");
    field.setColumnDisplay(true, 0, true);
    field.setLookupData(new BatchBiz(getUserACL()).queryCardBatchLookupDataSet());
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 2, false);
    dbFields.add(field);
    
    field = new DbField("��û�Ա���", "MEMBER_NUM", GaConstant.DT_STRING);
    field.setColumnDisplay(true, 100, false);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 3, false);
    field.setInputCustomStyle("width:80px");
    dbFields.add(field);
    
    field = new DbField("�绰����", "TEL", GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 4, false);
    dbFields.add(field);
    
    field = new DbField("ʹ��ʱ��", "USE_TIME", GaConstant.DT_DATETIME);
    field.setAliasCode("c");
    field.setFormat(new DbFieldFormat(GaConstant.DT_DATETIME, "yyyy-MM-dd HH:mm:ss", false));
    field.setColumnDisplay(true, 160, false);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, 10, false);
    dbFields.add(field);
    
    field = new DbField("�Ƿ���", "IS_GET", GaConstant.DT_INT);
    field.setLookupData(SystemUtil.getYesNoMap());
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 10,false,false);
    dbFields.add(field);
    
    field  =  new DbField("״̬", "STATE", GaConstant.DT_LONG);
    field.setAliasCode("c");
    field.setColumnDisplay(true, 80, true);
    Map<String, Object> lookupState  =  new HashMap<String, Object>();
    lookupState.put("0", "��Ч");
    lookupState.put("1", "δʹ��");
    lookupState.put("2", "��ʹ��");
    field.setLookupData(new LookupDataSet(lookupState));
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 11, false);
    dbFields.add(field);
    
    field = new DbField("��������", "CREATE_TIME", GaConstant.DT_DATETIME);
    field.setFormat(new DbFieldFormat(GaConstant.DT_DATETIME, "yyyy-MM-dd HH:mm:ss", false));
    dbFields.add(field);
    
    this.fieldList = dbFields;
  }

}
