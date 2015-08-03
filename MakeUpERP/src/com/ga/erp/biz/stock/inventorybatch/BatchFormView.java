package com.ga.erp.biz.stock.inventorybatch;

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

public class BatchFormView extends FormView {

	public BatchFormView(String viewID, ModelMap modelMap) {
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
		 map.put("1", "仓库");
		 map.put("2", "门店");
		 field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_READONLY);
		 field.setLookupData(new LookupDataSet(map));
		 field.setVerifyFormula("", true);
		// field.setDefaultValue("2");
		 //field.addInputAttributeMap("onChange","changePopUrl('inventory',this.value);");
		 dbFields.add(field);
		 
		 //放大镜选择具体对象
		 field = new DbField("盘点对象ID","TYPE_ID",GaConstant.DT_LONG);
		 field.setPopSelect("typeSelect", "STORE_ID", false);
		 dbFields.add(field);
		 
		 field = new DbField("盘点对象名<font color='red'>*</font>","TYPE_NAME",GaConstant.DT_STRING);
		 if(this.viewEditMode == GaConstant.EDITMODE_NEW){
			 field.setPopSelect("typeSelect","STORE_NAME",true,
					 "/store/store_main.htm",
					 "STORE_ID,STORE_NAME,cid_storeList",800,400);
		 }
//		 field.setPopSelect("typeSelect","STORE_NAME",true,
//		          "/store/store_main.htm",//门店信息页面获取
//		          "STORE_ID,STORE_NAME,cid_storeList",600,300);
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
		 field.setDefaultValue("3");
		 field.addInputAttributeMap("onChange","changeScopeUrl('scope',this.value);");
		 dbFields.add(field);
		 
		 //放大镜选择
		 field = new DbField("分类品牌ID","OBJECT_IDS",GaConstant.DT_STRING);
		 field.setPopSelect("scopeSelect", "SORT_ID", false);
		 dbFields.add(field);
		 
		 field = new DbField("分类品牌","OBJECT_NAMES",GaConstant.DT_STRING);
     if(this.viewEditMode == GaConstant.EDITMODE_NEW){
		 field.setPopSelect("scopeSelect","SORT_NAME",true,
		          "/comm/sort_sele.htm",
		          "SORT_ID,SORT_NAME,cid_sortTree",1000,600);
		 field.setTipInfo("<br/><font color='red'>该选项只在分类与品牌盘点有效</font>");
     }
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
		
	
		 field = new DbField("备注","NOTE",GaConstant.DT_STRING);
		 field.setInput(GaConstant.INPUT_TEXTAREA);
		 field.addInputAttributeMap("rows", "3");
		 field.addInputAttributeMap("cols", "30");
		 dbFields.add(field);
  	
	  	this.fieldList = dbFields;	  	
    }   	 
	
}


