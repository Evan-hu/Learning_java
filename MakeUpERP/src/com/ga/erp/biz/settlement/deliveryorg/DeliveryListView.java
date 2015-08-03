package com.ga.erp.biz.settlement.deliveryorg;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;

public class DeliveryListView extends ListView {
	
  public DeliveryListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {
	  
	List<DbField> dbFields = new ArrayList<DbField>();
	
	DbField field = new DbField("ID","DELIVERY_ORG_ID",GaConstant.DT_LONG); 
	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "DELIVERY_ORG_ID");
	field.setColumnDisplay(false, 0, true);
	dbFields.add(field);
	    
	field = new DbField("����", "DELIVERY_NAME", GaConstant.DT_STRING);
	field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
	dbFields.add(field);
	
	field = new DbField("��˾����", "DELIVERY_CODE", GaConstant.DT_STRING);
	field.setQueryOpera(GaConstant.QUERY_LIKE, 2, false);
	dbFields.add(field);
	
	field = new DbField("���㷽ʽ","CHECK_TYPE",GaConstant.DT_INT);
  Map<String, Object> lookup = new LinkedHashMap<String, Object>();
  lookup.put("1", "��ʱָ��");
  lookup.put("2", "ָ������");
  lookup.put("3", "ָ������");
  lookup.put("4", "��������");
  field.setLookupData(new LookupDataSet(lookup));
  field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 4, false);
  dbFields.add(field);
  
  field = new DbField("�������ڣ��죩","CHECK_CYCLE",GaConstant.DT_INT);
  field.setQueryOpera(GaConstant.QUERY_LIKE, 5, false);
  dbFields.add(field);
  
  field = new DbField("��������","CHECK_MONTH",GaConstant.DT_INT);
  field.setQueryOpera(GaConstant.QUERY_LIKE, 6, false);
  dbFields.add(field);
  
  field = new DbField("����Ӧ������","MONEY",GaConstant.DT_MONEY);
  dbFields.add(field);

    this.fieldList = dbFields;
  }
  
}
