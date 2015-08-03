package com.ga.erp.biz.comm;

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

public class CommFormView extends FormView {

  public CommFormView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }

  @Override
  public void initField() throws Exception {

     List<DbField> dbFields = new ArrayList<DbField>();
    
     DbField field = new DbField("ID", "COMMODITY_ID", GaConstant.DT_LONG);
     field.setInput(GaConstant.INPUT_HIDDEN);
     field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "COMMODITY_ID");
     dbFields.add(field);

     field = new DbField("��Ʒ����<font color='red'>*</font>", "COMMODITY_NAME",GaConstant.DT_STRING);
     dbFields.add(field);

     field = new DbField("����","TYPE",GaConstant.DT_INT);
  	 field.setInput(GaConstant.INPUT_SELECT);
     Map<String, Object> option = new LinkedHashMap<String, Object>();
     option.put("1", "��ͨ��Ʒ");
     option.put("2", "��Ʒ");
     option.put("3", "����Ʒ");
     field.setLookupData(new LookupDataSet(option));
     field.setVerifyFormula("", true);
     dbFields.add(field);
     
     field = new DbField("��Ʒ���", "COMMODITY_SNAME", GaConstant.DT_STRING);
	   dbFields.add(field);
	
  	 field = new DbField("����<font color='red'>*</font>", "CODE", GaConstant.DT_STRING);
  	 dbFields.add(field);
  	 
  	 field = new DbField("�Ա���", "CUSTOM_CODE", GaConstant.DT_STRING);
  	 dbFields.add(field);
  	 
  	 field = new DbField("������", "MNEMONIC_CODE", GaConstant.DT_STRING);
  	 dbFields.add(field);
  	 
  	 field = new DbField("����ID", "SORT_ID", GaConstant.DT_LONG);
     field.setPopSelect("NEWSORTSELECT", "SORT_ID", false);
     dbFields.add(field);
    
     field = new DbField("����<font color='red'>*</font>", "SORT_NAME", GaConstant.DT_STRING);
     field.setPopSelect("NEWSORTSELECT","SORT_NAME",true,
            "/comm/sort_sele.htm",
            "SORT_ID,SORT_NAME,cid_sortTree",200,300);
     dbFields.add(field);
    
     field = new DbField("Ʒ��ID","BRAND_ID", GaConstant.DT_LONG);
     field.setPopSelect("NEWBRANDSELECT", "BRAND_ID", false);
     dbFields.add(field); 
    
     field = new DbField("Ʒ��<font color='red'>*</font>","NAME",GaConstant.DT_STRING);
     field.setPopSelect("NEWBRANDSELECT","NAME",true,
        "/comm/brand_main.htm",
        "BRAND_ID,NAME,cid_brandList", 800,300);
     dbFields.add(field);

     field = new DbField("��λ","UNIT",GaConstant.DT_STRING);
     field.setInputCustomStyle("width:50px;");
     dbFields.add(field);
     
     field = new DbField("����","ADDR",GaConstant.DT_STRING);
     field.setInputCustomStyle("width:100px;");
     dbFields.add(field);
     
     field = new DbField("��Ʒ���","COMMODITY_SPEC",GaConstant.DT_STRING);
     dbFields.add(field);
     
     field = new DbField("�������<font color='red'>*</font>","STOCK_SPEC",GaConstant.DT_STRING);
     dbFields.add(field);

     field = new DbField("��ע", "NOTE", GaConstant.DT_STRING);
     field.setInput(GaConstant.INPUT_TEXTAREA);
     field.addInputAttributeMap("rows", "6");
     field.addInputAttributeMap("cols", "120");
  	 dbFields.add(field);
	
     /**********************************�۸�**************************************/
     field = new DbField("�ο�������<font color='red'>*</font>", "TRADE_PRICE", GaConstant.DT_MONEY);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     field.setInputCustomStyle("width:50px;");
     dbFields.add(field);
     
     field = new DbField("�ο��ɹ���<font color='red'>*</font>", "PURCHASE_PRICE", GaConstant.DT_MONEY);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     field.setInputCustomStyle("width:50px;");
     dbFields.add(field);       
     
     field = new DbField("�ο����ͼ�<font color='red'>*</font>", "SEND_PRICE", GaConstant.DT_MONEY);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     field.setInputCustomStyle("width:50px;");
     dbFields.add(field);
     
     field = new DbField("�ο����ۼ�<font color='red'>*</font>", "SELL_PRICE", GaConstant.DT_MONEY);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     field.setInputCustomStyle("width:50px;");
     dbFields.add(field);
     
     field = new DbField("�ο���Ա��<font color='red'>*</font>", "MEMBER_PRICE", GaConstant.DT_MONEY);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     field.setInputCustomStyle("width:50px;");
     dbFields.add(field);
     /**********************************�۸�**************************************/
     
     
     /**********************************����**************************************/
     field = new DbField("�ۼƲɹ���", "PURCHASE_AMOUNT", GaConstant.DT_INT);
     field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     field.setDefaultValue(0);
     field.setInputCustomStyle("width:50px;");
     dbFields.add(field);
     
     field = new DbField("�ۼ�������", "SEND_AMOUNT", GaConstant.DT_INT);
     field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     field.setDefaultValue(0);
     field.setInputCustomStyle("width:50px;");
     dbFields.add(field);
     
     field = new DbField("�ۼ�������", "SELL_AMOUNT", GaConstant.DT_INT);
     field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     field.setDefaultValue(0);
     field.setInputCustomStyle("width:50px;");
     dbFields.add(field);
     
     field = new DbField("�ۼ��˻���", "RETURN_AMOUNT", GaConstant.DT_INT);
     field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     field.setDefaultValue(0);
     field.setInputCustomStyle("width:50px;");
     dbFields.add(field);
     
     field = new DbField("�ۼƻ�����", "LOSS_AMOUNT", GaConstant.DT_INT);
     field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     field.setDefaultValue(0);
     field.setInputCustomStyle("width:50px;");
     dbFields.add(field);
     /**********************************����**************************************/
    
     
     /**********************************״̬**************************************/
     field = new DbField("������", "TRUENAME", GaConstant.DT_STRING);
     field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     dbFields.add(field);
 
     field = new DbField("����ʱ��","CREATE_TIME",GaConstant.DT_DATETIME);
     field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
     field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     dbFields.add(field);
    
     field = new DbField("����޸���", "LAST_OP_NAME", GaConstant.DT_STRING);
     field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     dbFields.add(field);
 
     field = new DbField("����޸�ʱ��","LAST_TIME",GaConstant.DT_DATETIME);
     field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
     field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     dbFields.add(field);

     field = new DbField("״̬","STATE",GaConstant.DT_INT);
     field.setInput(GaConstant.INPUT_SELECT);
     field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
  	 field.setVerifyFormula("", true);
  	 field.setLookupData(SystemUtil.getStatusMap());
     dbFields.add(field);
     
     field = new DbField("�ɹ�״̬","PURCHASE_STATE",GaConstant.DT_INT);
     field.setInput(GaConstant.INPUT_SELECT);
     field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
  	 option = new LinkedHashMap<String, Object>();
  	 option.put("0", "ͣ��");
  	 option.put("1", "����");
  	 field.setLookupData(new LookupDataSet(option));
  	 field.setVerifyFormula("", true);
     dbFields.add(field);
     /**********************************״̬**************************************/
     
     this.fieldList = dbFields;
  }

}
