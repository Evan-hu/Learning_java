package com.ga.erp.biz.store.storeorder.toOrder;

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

public class ToOrderFormView extends FormView {

  public ToOrderFormView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }

  @Override
  public void initField() throws Exception {

    List<DbField> dbFields = new ArrayList<DbField>();

    DbField field = new DbField("ID","STORE_ORDER_ID",GaConstant.DT_LONG); 
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "STORE_ORDER_ID");
    field.setInput(GaConstant.INPUT_HIDDEN);
    dbFields.add(field);
        
    field = new DbField("�ŵ�ID", "STORE_ID", GaConstant.DT_LONG);
    field.setPopSelect("STORESELECT", "STORE_ID", false);
    dbFields.add(field);

    field = new DbField("�ŵ���", "STORE_NAME", GaConstant.DT_STRING);
    if (this.viewEditMode == GaConstant.EDITMODE_NEW) {
      field.setPopSelect("STORESELECT", "STORE_NAME", true,
          "/store/store_main.htm",
          "STORE_ID,STORE_NAME,cid_storeList", 1000, 480);
    } 
    dbFields.add(field);
    
    field = new DbField("����","TYPE",GaConstant.DT_INT);
    field.setInput(GaConstant.INPUT_HIDDEN);
   /* Map<String, Object> option = new LinkedHashMap<String, Object>();
    option.put("1", "���ֱ�����뵥");
    option.put("2", "�ŵ�Ҫ�����뵥");
    option.put("3", "�ŵ��˻����뵥");
    option.put("4", "DC������");
    option.put("5", "���ֱ���ջ���");
    option.put("6", "�ŵ�Ҫ���ջ���");
    option.put("7", "�ŵ��˻��ջ�����DC�ջ���");
    field.setLookupData(new LookupDataSet(option));
    field.setVerifyFormula("", true);*/
    dbFields.add(field);

    field = new DbField("������","STORE_ORDER_NUM",GaConstant.DT_STRING);
    dbFields.add(field);
    
    field = new DbField("��������ID", "P_STORE_ORDER_ID", GaConstant.DT_LONG);
    field.setPopSelect("ORDERSELECT", "STORE_ORDER_ID", false);
    dbFields.add(field);

    field = new DbField("����������", "STORE_ORDER_NUM", GaConstant.DT_STRING);
    if (this.viewEditMode == GaConstant.EDITMODE_NEW) {
      field.setPopSelect("ORDERSELECT", "STORE_ORDER_NUM", true,
          "/store/storeorder_main.htm",
          "STORE_ORDER_ID,STORE_ORDER_NUM,cid_storeOrderList", 1000, 480);
    } 
    dbFields.add(field);
    
    if(this.viewEditMode == GaConstant.EDITMODE_EDIT){
      field = new DbField("������ˮ��","CODE",GaConstant.DT_STRING);
      dbFields.add(field);

      field = new DbField("�������","ORDER_MONEY",GaConstant.DT_MONEY);
      field.setInputCustomStyle("width:80px;");
      dbFields.add(field);
      
      field = new DbField("ʵ�ʽ��","ACTURE_MONEY",GaConstant.DT_MONEY);
      field.setInputCustomStyle("width:80px;");
      dbFields.add(field);
    }
    
    field = new DbField("���ͷ�ʽ","DELIVERY_TYPE",GaConstant.DT_INT);
    field.setInput(GaConstant.INPUT_SELECT);
    Map<String, Object> option = new LinkedHashMap<String, Object>();
    option.put("1", "��������");
    option.put("2", "�������ͳһ����");
    option.put("3", "�ֵ�����");
    field.setLookupData(new LookupDataSet(option));
    field.setVerifyFormula("", true);
    dbFields.add(field);
    
    field = new DbField("Ԥ������ʱ��","PREDICT_DILIVERY_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.getFieldVerify().setRequire(true);
    dbFields.add(field);
    
    field = new DbField("�ջ�ʱ��","RECEIVE_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    dbFields.add(field);
    
    dbFields.addAll(SystemUtil.getNormalFieldList());
    
    this.fieldList = dbFields;
  }

}
