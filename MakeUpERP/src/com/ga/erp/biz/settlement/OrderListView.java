package com.ga.erp.biz.settlement;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;

public class OrderListView extends ListView{

	public OrderListView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap,GaConstant.EDITMODE_DISP);
	}
	  @Override
	  public void initField() throws Exception {
		  
	    List<DbField> dbFields = new ArrayList<DbField>();
		    
		  DbField field = new DbField("����ID","ORDER_ID",GaConstant.DT_LONG); 
		  field.setPK(true);
		  field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "ORDER_ID");
		  field.setColumnDisplay(false, 0, true);
		  dbFields.add(field);
		  
      field = new DbField("��������","OBJECT_TYPE",GaConstant.DT_STRING);
      field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT,1,true);
      Map<String, Object> lookup = new LinkedHashMap<String, Object>();
        lookup.put("1", "��Ӧ��");
        lookup.put("2", "��ͻ�");
        lookup.put("3", "�ŵ�");
        lookup.put("4", "��������");
      field.setLookupData(new LookupDataSet(lookup));
      dbFields.add(field);  
		  
      field = new DbField("��������","ORDER_TYPE",GaConstant.DT_INT);
      field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT,2,true);
      lookup = new LinkedHashMap<String, Object>();
        lookup.put("1", "��Ӧ������ҵ��");
        lookup.put("2", "�ŵ�����ҵ��");
        lookup.put("3", "���õ�");
      field.setLookupData(new LookupDataSet(lookup));
      dbFields.add(field);
      
      field = new DbField("����������","TYPE",GaConstant.DT_INT);
      field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 3, false);
      lookup = new LinkedHashMap<String, Object>();
      lookup.put("1", "����");
      lookup.put("2", "�����");
      lookup.put("3", "�ڴν�ת");
      lookup.put("4", "��������");
      lookup.put("5", "��������");
      field.setLookupData(new LookupDataSet(lookup));
      dbFields.add(field);
      
		  field = new DbField("���ݱ��","ORDER_NUM",GaConstant.DT_INT);
		  field.setQueryOpera(GaConstant.QUERY_LIKE, 4, true);
		  dbFields.add(field);	
		  
		  field = new DbField("���","MONEY",GaConstant.DT_MONEY);
			field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 5, false);
		  dbFields.add(field);	
		  
		  field = new DbField("Ӧ�����","PAY_MONEY",GaConstant.DT_MONEY);
		  dbFields.add(field);	
		  
	    field = new DbField("����ʱ��","CREATE_TIME",GaConstant.DT_DATETIME);
	    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
	    dbFields.add(field); 
	    
	    this.fieldList = dbFields;
	  }
	

}
