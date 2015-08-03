package com.ga.erp.biz.store.returnOrder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.control.StringDecorator;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;

public class StoreReturnListView extends ListView{

	public StoreReturnListView(String viewID, ModelMap modelMap) {
		
		super(viewID, modelMap,GaConstant.EDITMODE_DISP);
	}
	  @Override
	  public void initField() throws Exception {
	    
		  
	    List<DbField> dbFields = new ArrayList<DbField>();
		    
		  DbField field = new DbField("ID","STORE_RETURN_ORDER_ID",GaConstant.DT_LONG); 
		  field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "STORE_RETURN_ORDER_ID");
		  field.setColumnDisplay(false, 0, true);
		  dbFields.add(field);
		  	
			field = new DbField("�������","STORE_SALES_ORDER_NUM",GaConstant.DT_INT);
			field.setQueryOpera(GaConstant.QUERY_LIKE, 0, true);
			field.setInputCustomStyle("width:80px;");
		  dbFields.add(field);
		  	
		  field = new DbField("�ŵ�ID","STORE_ID",GaConstant.DT_LONG);
		  field.setAliasCode("s");
	    field.setIsFilterCode(true);
	    field.setColumnDisplay(false, 0, true);
	    dbFields.add(field);
	    
	    field = new DbField("�ŵ�","STORE_NAME",GaConstant.DT_STRING);
	    field.setQueryOpera(GaConstant.QUERY_LIKE, 1, true);
	    dbFields.add(field);
//	    field = new DbField("�ŵ�","STORE_NAME",GaConstant.DT_STRING);
//	    field.setQueryOpera(GaConstant.QUERY_LIKE, 1, true);
//	    ActionButton action = new ActionButton(this.getClass(),"STORE_ID","{value}","/store/store_detail.htm",null);
//	    SubmitTool.submitToDialog(action, "STORE_NAME","�ŵ�����", 1000, 380, this.modelMap.getPreNavInfoStr());
//	    StringDecorator decorator = new StringDecorator();
//	    decorator.setLinkDecorator(action);
//	    action.bindTableRowData(this.viewID);
//	    action.addParam(GaConstant.FIXPARAM_EDITMODE,GaConstant.EDITMODE_EDIT);
//	    field.setColumnDecorator("", decorator);
//	    field.setInputCustomStyle("width:90px");
//	    field.setAliasCode("sso");
//	    dbFields.add(field);

	    field = new DbField("��ԱID","MEMBER_ID",GaConstant.DT_LONG); 
	    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "MEMBER_ID");
	    field.setColumnDisplay(false, 0, true);
	    dbFields.add(field);
	      
	    field = new DbField("��Ա���", "MEMBER_NUM", GaConstant.DT_STRING);
	    field.setQueryOpera(GaConstant.QUERY_LIKE, 2, false);
	    dbFields.add(field);
//	    field = new DbField("��Ա���", "MEMBER_NUM", GaConstant.DT_STRING);
//	    action = new ActionButton(this.getClass(),"MEMBER_ID","{value}","/member/member_detail.htm",null);
//	    SubmitTool.submitToDialog(action, "MEMBER_ID","��Ա����", 800, 480, this.modelMap.getPreNavInfoStr());
//	    decorator = new StringDecorator();
//	    decorator.setLinkDecorator(action);
//	    decorator.setStringFormat("<font color='red'>{value}</font>");
//	    action.bindTableRowData(this.viewID);
//	    action.addParam(GaConstant.FIXPARAM_EDITMODE,GaConstant.EDITMODE_EDIT);
//	    field.setColumnDecorator("", decorator);
//	    field.setQueryOpera(GaConstant.QUERY_LIKE, 2, false);
//	    field.setColumnDisplay(true, 100, false);
//	    dbFields.add(field);
	    
			field = new DbField("ӪҵԱ","TRUENAME",GaConstant.DT_STRING);
			field.setQueryOpera(GaConstant.QUERY_LIKE, 3, true);
			field.setInputCustomStyle("width:80px;");
		  dbFields.add(field);
		  	
			field = new DbField("POS��","NAME",GaConstant.DT_STRING);
			field.setQueryOpera(GaConstant.QUERY_LIKE, 4, true);
			field.setInputCustomStyle("width:80px;");
			dbFields.add(field);
			
			field = new DbField("����ԭ���","ORDER_MONEY",GaConstant.DT_MONEY);
			field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 5, false);
			dbFields.add(field);
			
			field = new DbField("�˻��ֽ�","RETURN_CASH",GaConstant.DT_MONEY);
			dbFields.add(field);
			
			field = new DbField("�˻����п����","RETURN_BANK",GaConstant.DT_MONEY);
			dbFields.add(field);
			
			field = new DbField("�˻�����","RETURN_SCORE",GaConstant.DT_INT);
			dbFields.add(field);
			
			field = new DbField("�˻��Ż�ȯ���","RETURN_COUPON",GaConstant.DT_MONEY);
			dbFields.add(field);
			
			field = new DbField("�˻�����","CREATE_TIME",GaConstant.DT_DATETIME);
			field.setAliasCode("sro");
			field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
			field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_DATETIME, 6, true);
			dbFields.add(field);

			field = new DbField("״̬","STATE",GaConstant.DT_INT);
			field.setAliasCode("sro");
			field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT,7,true);
			Map<String, Object> lookup = new LinkedHashMap<String, Object>();
		  lookup.put("1", "������");
		  lookup.put("2", "�����");
		  lookup.put("3", "�ܾ�����");
		  field.setLookupData(new LookupDataSet(lookup));
			dbFields.add(field);
//			
//			field = new DbField("�˻�ԭ��","REASON",GaConstant.DT_STRING);
//			dbFields.add(field);
//			
//			field = new DbField("��ע","NOTE",GaConstant.DT_STRING);
//			dbFields.add(field);
//			
			this.fieldList = dbFields;
	  }
	

}
