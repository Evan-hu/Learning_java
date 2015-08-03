package com.ga.erp.biz.comm.brand;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.util.SystemUtil;

public class BrandFormView extends FormView {

  public BrandFormView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }

  @Override
  public void initField() throws Exception {

    List<DbField> dbFields = new ArrayList<DbField>();

    DbField field = new DbField("ID", "BRAND_ID", GaConstant.DT_LONG);
    field.setInput(GaConstant.INPUT_HIDDEN);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "BRAND_ID");
    dbFields.add(field);
    
  	field = new DbField("Ʒ����","NAME",GaConstant.DT_STRING);
  	field.getFieldVerify().setRequire(true);
  	dbFields.add(field);
  	
	  field = new DbField("����","CODE",GaConstant.DT_STRING);
  	dbFields.add(field);
  	
  	field = new DbField("��ɱ���","CUT_RATIO",GaConstant.DT_DOUBLE);
  	field.setDefaultValue(0.0);
  	field.setInputCustomStyle("width:50px");
  	field.setTipInfo("&nbsp;&nbsp;%");
  	dbFields.add(field);
  	
  	field = new DbField("����ֵ","RANK_NUM",GaConstant.DT_INT);
  	field.getFieldVerify().setRequire(true);
  	field.setInputCustomStyle("width:50px");
  	field.setDefaultValue("1");
  	field.setTipInfo("����ֵ��1��100");
  	dbFields.add(field);
  	
    dbFields.addAll(SystemUtil.getNormalFieldList());
    
    this.fieldList = dbFields;
  }

}
