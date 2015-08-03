package com.ga.erp.biz.stock.inventorybill.comm;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;

public class CommListView extends ListView{

	public CommListView(String viewID, ModelMap modelMap) {
	   super(viewID, modelMap,GaConstant.EDITMODE_DISP);
	}
	  @Override
	  public void initField() throws Exception {
		  
	       List<DbField> dbFields = new ArrayList<DbField>();
		    
		  	DbField field = new DbField("ID","INVENTORY_COMM_ID",GaConstant.DT_LONG); 
		  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "INVENTORY_COMM_ID");
		  	field.setColumnDisplay(false, 0, true);
		  	field.setPK(true);
		  	dbFields.add(field);
		  	
		  	field = new DbField("��ƷID","COMMODITY_ID",GaConstant.DT_STRING);
		  	field.setColumnDisplay(false, 0, true);
        dbFields.add(field);
		  	
  			field = new DbField("��Ʒ��","COMMODITY_NAME",GaConstant.DT_STRING);
  		  dbFields.add(field);
  		  
  		  field = new DbField("����id","OBJECT_ID",GaConstant.DT_STRING);
  		  field.setColumnDisplay(false, 0, true);
        dbFields.add(field);
        
  			field = new DbField("�̵������","OBJECT_NAME",GaConstant.DT_STRING);
  		  dbFields.add(field);
  		  
  //			field = new DbField("�̵㵥��ˮ��","INVENTORY_FLOW_NUM",GaConstant.DT_INT);
  //			field.setQueryOpera(GaConstant.QUERY_LIKE, 3, true);
//			field.setInputCustomStyle("width:80px;");
//			dbFields.add(field);
			
  			field = new DbField("ϵͳ���","STOCK_INNAGE",GaConstant.DT_INT);
  			dbFields.add(field);
  			
  			field = new DbField("ʵ�̿��","INVENTORY_INNAGE",GaConstant.DT_INT);
  			dbFields.add(field);
  			
  			this.fieldList = dbFields;
	  }
	

}
