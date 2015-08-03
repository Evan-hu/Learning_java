package com.ga.erp.biz.stock.inventorybill;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class BillListView extends ListView{


	  public BillListView(String viewID, ModelMap modelMap) {
	    
		super(viewID, modelMap,GaConstant.EDITMODE_DISP);
	}


	@Override
	  public void initField() throws Exception {
		  
	       List<DbField> dbFields = new ArrayList<DbField>();
		    
		   DbField field = new DbField("ID","INVENTORY_BILL_ID",GaConstant.DT_LONG); 
		   field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "INVENTORY_BILL_ID");
		   field.setColumnDisplay(false, 0, true);
		   field.setPK(true);
		   dbFields.add(field);
		   
		   field = new DbField("盘点流水号","INVENTORY_FLOW_NUM",GaConstant.DT_STRING);
		   field.setQueryOpera(GaConstant.QUERY_LIKE, 1, true);
		   field.setInputCustomStyle("width:80px;");
		   dbFields.add(field);
		   
		   
		   field = new DbField("盘点类型","TYPE",GaConstant.DT_STRING);
       field.setColumnDisplay(false, 0, true);
       dbFields.add(field);
       
       
		   field = new DbField("盘点批次编码","INVENTORY_BATCH_ID",GaConstant.DT_STRING);
		   field.setColumnDisplay(false, 0, true);
		   dbFields.add(field);
		   
		   field = new DbField("盘点批次编码","INVENTORY_BATCH_NUM",GaConstant.DT_STRING);
		   field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_POPSELECT, 2, false, true);
		   field.setPopSelect("INVENTORYSELECT","INVENTORY_BATCH_NUM",true,
		              "/stock/inventoryBatch_main.htm",
		              "INVENTORY_BATCH_ID,INVENTORY_BATCH_NUM,cid_inventoryBatchList",800,400);
		   field.setInputCustomStyle("width:100px;");
		   dbFields.add(field);
		  
		   field = new DbField("创建人","TRUENAME",GaConstant.DT_STRING);
		   dbFields.add(field);
		   
		   field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
		   field.setAliasCode("i");
		   field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
		   field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_DATETIME, 3, true);
		   dbFields.add(field);
			
		   field = new DbField("状态","STATE",GaConstant.DT_INT);
		   field.setAliasCode("i");
		   field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT, 4,true);
		   field.setLookupData(SystemUtil.getStatusMap());
		   dbFields.add(field);
		   
		 
		  	  
		   this.fieldList = dbFields;
	  }
	

}
