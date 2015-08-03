package com.ga.erp.biz.stock.inventorybatch.imports;

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

public class ImportFormView extends FormView {

	public ImportFormView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap);
	}
	
	 public void initField() throws Exception {
		  
		 List<DbField> dbFields = new ArrayList<DbField>();
		    
		 DbField field = new DbField("ID","INVENTORY_BATCH_ID",GaConstant.DT_LONG); 
		 field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "INVENTORY_BATCH_ID");
		 field.setInput(GaConstant.INPUT_HIDDEN);
		 dbFields.add(field);  
		 
		 field = new DbField("盘点批次编码<font color='red'>*</font>","INVENTORY_BATCH_NUM",GaConstant.DT_STRING);
		 dbFields.add(field);
		  	
		 field = new DbField("盘点类型<font color='red'>*</font>","TYPE",GaConstant.DT_INT);
		 field.setInput(GaConstant.INPUT_SELECT);
		 Map<String, Object> map = new LinkedHashMap<String,Object>();
		 map.put("1", "仓储");
		 map.put("2", "门店");
		 field.setLookupData(new LookupDataSet(map));
		 field.setVerifyFormula("", true);
		 dbFields.add(field);
		 
		 field = new DbField("盘点范围<font color='red'>*</font>","SCOPE",GaConstant.DT_INT);
		 field.setInput(GaConstant.INPUT_SELECT);
		 map = new LinkedHashMap<String,Object>();
		 map.put("1", "全场盘点");
		 map.put("2", "单品盘点");
		 map.put("3", "分类盘点");
		 map.put("4", "品牌盘点");
		 field.setLookupData(new LookupDataSet(map));
		 field.setVerifyFormula("", true);
		 dbFields.add(field);
		 
		 field = new DbField("创建人","TRUENAME",GaConstant.DT_STRING);
		 field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		 field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
		 dbFields.add(field);
		 
		 field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
		 field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss", false));
		 field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		 field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
		 dbFields.add(field);
		 
		 field = new DbField("导入数据","FILEPATH",GaConstant.DT_STRING);
		 dbFields.add(field);
		 
		 field = new DbField("文件","FILEPATH",GaConstant.DT_STRING);
	   field.setInput(GaConstant.INPUT_UPLOAD);
	   field.setFileUpload(false, "*.csv;*.xls");
		 dbFields.add(field);
		 
	   this.fieldList = dbFields;	  	
    }   	 
	
}


