package com.ga.erp.biz.stock;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;

public class StockFormView extends FormView {

	public StockFormView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap);
	}
	
	@Override
	  public void initField() throws Exception {
		
		  List<DbField> fieldList = new ArrayList<DbField>();
			    
		  DbField field = new DbField("ID","STOCK_ID",GaConstant.DT_LONG); 
		  field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "STOCK_ID");
		  field.setInput(GaConstant.INPUT_HIDDEN);
		  fieldList.add(field);
		  	
			field = new DbField("�ֿ���<font color='red'>*</font>","NAME",GaConstant.DT_STRING);
			fieldList.add(field);
		  	
			field = new DbField("�ֿ����<font color='red'>*</font>","ACREAGE",GaConstant.DT_INT);
			fieldList.add(field);
			
			field = new DbField("���ڵ���ID","AREA_ID", GaConstant.DT_LONG);
	    field.setPopSelect("selectNew", "AREA_ID", false);
	    fieldList.add(field);
		  	
			field = new DbField("���ڵ���<font color='red'>*</font>","AREA_NAME",GaConstant.DT_STRING);
			//ѡ��ؼ�   
			field.setPopSelect("selectNew","AREA_NAME",true,
		              "/system/area_sele.htm",
		              "AREA_ID,AREA_NAME,cid_areaTree", 230, 400);
		  fieldList.add(field);
//	
//			field = new DbField("���ڵ���<font color='red'>*</font>","AREA_ID",GaConstant.DT_STRING);
//			fieldList.add(field);
			
	    this.fieldList = fieldList;
	    	 
	}

}
