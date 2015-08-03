package com.ga.erp.biz.stock.inventorybill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;

public class BillFormView extends FormView {

	public BillFormView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap);
	}

	@Override
	  public void initField() throws Exception {
		  
	       List<DbField> dbFields = new ArrayList<DbField>();
		    
		   DbField field = new DbField("ID","INVENTORY_BILL_ID",GaConstant.DT_LONG); 
		   field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "INVENTORY_BILL_ID");
		   field.setInput(GaConstant.INPUT_HIDDEN);
		   field.setPK(true);
		   dbFields.add(field);
		   
		   field = new DbField("盘点流水号<font color='red'>*</font>","INVENTORY_FLOW_NUM",GaConstant.DT_STRING);
		   dbFields.add(field);
		   
		   field = new DbField("盘点批次编码ID","INVENTORY_BATCH_ID",GaConstant.DT_LONG);
		   field.setPopSelect("BATCH_SELE", "INVENTORY_BATCH_ID", false);
		   dbFields.add(field);
		   
		   field = new DbField("盘点批次编码<font color='red'>*</font>","INVENTORY_BATCH_NUM",GaConstant.DT_STRING);
		 if(this.viewEditMode==GaConstant.EDITMODE_NEW){
		   field.setPopSelect("BATCH_SELE","INVENTORY_BATCH_NUM",true,
		              "/stock/batch_main.htm",
		              "INVENTORY_BATCH_ID,INVENTORY_BATCH_NUM,TYPE,TYPE_NAME,SCOPE,cid_inBatchList,callback_setAddValue",800,400);
		 }
		   field.setInputCustomStyle("width:100px;");
		   dbFields.add(field);
		   
//		   field = new DbField("创建人ID","OP_ID",GaConstant.DT_INT);
//		   field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
//		   field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_HIDDEN);
//		   dbFields.add(field);
		   
		   field = new DbField("盘点类型","TYPE",GaConstant.DT_INT);
		   Map<String, Object> map=new HashMap<String,Object>();
		  if(this.viewEditMode==GaConstant.EDITMODE_NEW){
		   field.setInputMode(GaConstant.EDITMODE_NEW,GaConstant.INPUTMODE_HIDDEN);
		  }else{
		   field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
		   field.setInput(GaConstant.INPUT_SELECT);
     
       map.put("1", "仓库");map.put("2", "门店");
       field.setLookupData(new LookupDataSet(map));
		  }
       dbFields.add(field);

       field = new DbField("盘点对象名","TYPE_NAME",GaConstant.DT_STRING);
       if(this.viewEditMode==GaConstant.EDITMODE_NEW){
         field.setPopSelect("BATCH_SELE", "TYPE_NAME", true);
       }
       dbFields.add(field);
       
       field = new DbField("盘点范围","SCOPE",GaConstant.DT_INT);
       if(this.viewEditMode==GaConstant.EDITMODE_NEW){
         field.setPopSelect("BATCH_SELE", "SCOPE", false);
        }else{
       field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
       field.setInput(GaConstant.INPUT_SELECT);
       map=new HashMap<String,Object>();
       map.put("1", "全场盘点");
       map.put("2", "单品盘点");
       map.put("3", "分类盘点");
       map.put("4", "品牌盘点");
       field.setLookupData(new LookupDataSet(map));
        }
       dbFields.add(field);
		   
		   field = new DbField("创建人","TRUENAME",GaConstant.DT_STRING);
		   field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		   field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
		   dbFields.add(field);

		   field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
		   field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
		   field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		   field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
		   dbFields.add(field);

		   field = new DbField("备注", "NOTE", GaConstant.DT_STRING);
		   field.setInput(GaConstant.INPUT_TEXTAREA);
		   field.addInputAttributeMap("rows", "3");
		   field.addInputAttributeMap("cols", "25");
		   dbFields.add(field);
		   
		   this.fieldList = dbFields;
	  }	
	
}


