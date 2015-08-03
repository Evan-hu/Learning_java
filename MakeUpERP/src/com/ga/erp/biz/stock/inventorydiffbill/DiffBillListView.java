package com.ga.erp.biz.stock.inventorydiffbill;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.util.SystemUtil;

public class DiffBillListView extends ListView{

	public DiffBillListView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap);
	}
	  @Override
	  public void initField() throws Exception {
		  
	       List<DbField> dbFields = new ArrayList<DbField>();
		    
		  	DbField field = new DbField("ID","INVENTORY_DIFF_BILL_ID",GaConstant.DT_LONG); 
		  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "INVENTORY_DIFF_BILL_ID");
		  	field.setColumnDisplay(false, 0, true);
		  	dbFields.add(field);
		  	
			field = new DbField("�̵�����","INVENTORY_BATCH_NUM",GaConstant.DT_STRING);
			field.setQueryOpera(GaConstant.QUERY_LIKE, 1, true);
			field.setInputCustomStyle("width:80px;");
		  	dbFields.add(field);
		  	
			field = new DbField("������ˮ�˺�","BILL_NUM",GaConstant.DT_STRING);
			field.setQueryOpera(GaConstant.QUERY_EQUALS, 2, true);
			field.setInputCustomStyle("width:80px;");
			dbFields.add(field);
		  	
			field = new DbField("�������","STOCK_CNT",GaConstant.DT_MONEY);
			dbFields.add(field);
	      
			field = new DbField("ʵ������","INVENTORY_CNT",GaConstant.DT_INT);
			dbFields.add(field); 	
			
			field = new DbField("�����","STOCK_AMOUNT",GaConstant.DT_INT);
			dbFields.add(field);
			
		  field = new DbField("ʵ�̽��","INVENTORY_AMOUNT",GaConstant.DT_INT);
		  dbFields.add(field);
		
			
			field = new DbField("ӯ����","DIFF_CNT",GaConstant.DT_INT);
			field.setQueryOpera(GaConstant.QUERY_GREATER_THAN, 4, true);
			dbFields.add(field);
			
			field = new DbField("ӯ�����","DIFF_AMOUNT",GaConstant.DT_MONEY);
			field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 5, false);
			dbFields.add(field);
			
			field = new DbField("������","TRUENAME",GaConstant.DT_STRING);
			dbFields.add(field);
			
			field = new DbField("����ʱ��","CREATE_TIME",GaConstant.DT_DATETIME);
			dbFields.add(field);		
		 
			field = new DbField("״̬","STATE",GaConstant.DT_INT);
			field.setAliasCode("id");
			field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 6, true);
		  field.setLookupData(SystemUtil.getStatusMap());
			dbFields.add(field);
			 
			this.fieldList = dbFields;
	  }
	

}
