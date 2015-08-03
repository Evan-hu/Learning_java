package com.ga.erp.biz.supplier.contract;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;

public class ContractListView extends ListView {

	public ContractListView(String viewID, ModelMap modelMap) {
		   super(viewID,modelMap,GaConstant.EDITMODE_DISP);
		
	}
	  @Override
	  public void initField() throws Exception {
		  
	    List<DbField> dbFields = new ArrayList<DbField>();
	    
	  	DbField field = new DbField("ID","CONTRACT_ID",GaConstant.DT_LONG); 
	  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "CONTRACT_ID");
	  	field.setColumnDisplay(false, 0, true);
	  	dbFields.add(field);
	  	
			field = new DbField("供应商","SUPPLIER_NAME",GaConstant.DT_STRING);
			field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_POPSELECT, 1, false, true);
			field.setPopSelect("SUPPLIERSELECT","SUPPLIER_NAME",true,
		              "/supplier/supplier_main.htm",
		              "SUPPLIER_ID,SUPPLIER_NAME,cid_supplierList",800,400);
			field.setInputCustomStyle("width:100px;");
	  	dbFields.add(field);
		  	
			field = new DbField("合同编号","CONTRACT_NUM",GaConstant.DT_INT);
			field.setQueryOpera(GaConstant.QUERY_EQUALS, 2, true);
			field.setInputCustomStyle("width:80px;");
  	  dbFields.add(field);
		  	
			field = new DbField("合同开始日期","BEG_TIME",GaConstant.DT_DATETIME);
			field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
			field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_DATETIME, 3, true);
	  	dbFields.add(field);
		  	
			field = new DbField("合同结束时间","END_TIME",GaConstant.DT_DATETIME);
			field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
			field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_DATETIME, 4, true);
		  	dbFields.add(field);
		  	
			field = new DbField("内容","CONTENT",GaConstant.DT_STRING);
		  	dbFields.add(field);
		  	
			field = new DbField("创建人ID","OP_ID",GaConstant.DT_LONG);
			field.setColumnDisplay(false, 0,false);
			field.setAliasCode("o");
	  	dbFields.add(field);
	  	
	  	field = new DbField("创建人","TRUENAME",GaConstant.DT_STRING);
      dbFields.add(field);
        
			field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
			//设置查询列别名
			field.setAliasCode("c");
			field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
			field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_DATETIME, 5, true);
		  dbFields.add(field);
		  	
	  	 this.fieldList = dbFields;
	  }
	
}
