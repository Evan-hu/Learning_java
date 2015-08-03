package com.ga.erp.biz.system.deliveryorg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class DeliveryFormView extends FormView {

  public DeliveryFormView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }

  @Override
  public void initField() throws Exception {

    List<DbField> dbFields = new ArrayList<DbField>();

    DbField field = new DbField("ID","DELIVERY_ORG_ID",GaConstant.DT_LONG); 
  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "DELIVERY_ORG_ID");
  	field.setInput(GaConstant.INPUT_HIDDEN);
  	dbFields.add(field);
  	    
  	field = new DbField("��������<font color='red'>*</font>", "DELIVERY_NAME", GaConstant.DT_STRING);
  	dbFields.add(field);
  	
  	field = new DbField("��˾����", "DELIVERY_CODE", GaConstant.DT_STRING);
  	dbFields.add(field);
  	
  	field = new DbField("�˷�<font color='red'>*</font>", "PRICE", GaConstant.DT_MONEY);
  	field.setInputCustomStyle("width:50px;");
  	dbFields.add(field);
  	
  	field = new DbField("������;ʱ��", "DELIVERY_TIME", GaConstant.DT_STRING);
  	field.setInputCustomStyle("width:50px;");
  	dbFields.add(field);

    field = new DbField("���㷽ʽ","CHECK_TYPE",GaConstant.DT_INT);
    field.setInput(GaConstant.INPUT_SELECT);
    Map<String, Object> option = new HashMap<String, Object>();
    option.put("1", "��ʱָ��");
    option.put("2", "ָ������");
    option.put("3", "ָ������");
    option.put("4", "��������");
    field.setLookupData(new LookupDataSet(option));
    field.setVerifyFormula("", true);
    dbFields.add(field);
    
    field = new DbField("��������","CHECK_CYCLE",GaConstant.DT_STRING);
    field.getFieldVerify().setRequire(true);
    field.setInputCustomStyle("WIDTH:50PX;");
    dbFields.add(field);

    field = new DbField("���ڽ���ʱ��","LAST_END_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    dbFields.add(field);
    
    field = new DbField("�½�����","CHECK_MONTH",GaConstant.DT_INT);
    field.setInputCustomStyle("WIDTH:50PX;");
    dbFields.add(field);
    
  	field = new DbField("״̬","STATE",GaConstant.DT_INT);
  	field.setInput(GaConstant.INPUT_SELECT);
    field.setLookupData(SystemUtil.getStatusMap());
    field.setVerifyFormula("", true);
    dbFields.add(field);
    
    field = new DbField("��ע", "NOTE", GaConstant.DT_STRING);
    field.setInput(GaConstant.INPUT_TEXTAREA);
    field.addInputAttributeMap("rows", "3");
    field.addInputAttributeMap("cols", "40");
    dbFields.add(field);
    
    this.fieldList = dbFields;
  }

}
