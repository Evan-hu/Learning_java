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
		    
		  DbField field = new DbField("单据ID","ORDER_ID",GaConstant.DT_LONG); 
		  field.setPK(true);
		  field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "ORDER_ID");
		  field.setColumnDisplay(false, 0, true);
		  dbFields.add(field);
		  
      field = new DbField("对象类型","OBJECT_TYPE",GaConstant.DT_STRING);
      field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT,1,true);
      Map<String, Object> lookup = new LinkedHashMap<String, Object>();
        lookup.put("1", "供应商");
        lookup.put("2", "大客户");
        lookup.put("3", "门店");
        lookup.put("4", "物流机构");
      field.setLookupData(new LookupDataSet(lookup));
      dbFields.add(field);  
		  
      field = new DbField("单据类型","ORDER_TYPE",GaConstant.DT_INT);
      field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT,2,true);
      lookup = new LinkedHashMap<String, Object>();
        lookup.put("1", "供应商往来业务单");
        lookup.put("2", "门店往来业务单");
        lookup.put("3", "费用单");
      field.setLookupData(new LookupDataSet(lookup));
      dbFields.add(field);
      
      field = new DbField("发生额类型","TYPE",GaConstant.DT_INT);
      field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 3, false);
      lookup = new LinkedHashMap<String, Object>();
      lookup.put("1", "货款");
      lookup.put("2", "服务费");
      lookup.put("3", "期次结转");
      lookup.put("4", "其他费用");
      lookup.put("5", "物流费用");
      field.setLookupData(new LookupDataSet(lookup));
      dbFields.add(field);
      
		  field = new DbField("单据编号","ORDER_NUM",GaConstant.DT_INT);
		  field.setQueryOpera(GaConstant.QUERY_LIKE, 4, true);
		  dbFields.add(field);	
		  
		  field = new DbField("金额","MONEY",GaConstant.DT_MONEY);
			field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 5, false);
		  dbFields.add(field);	
		  
		  field = new DbField("应付金额","PAY_MONEY",GaConstant.DT_MONEY);
		  dbFields.add(field);	
		  
	    field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
	    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
	    dbFields.add(field); 
	    
	    this.fieldList = dbFields;
	  }
	

}
