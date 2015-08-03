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
		  	
			field = new DbField("订单编号","STORE_SALES_ORDER_NUM",GaConstant.DT_INT);
			field.setQueryOpera(GaConstant.QUERY_LIKE, 0, true);
			field.setInputCustomStyle("width:80px;");
		  dbFields.add(field);
		  	
		  field = new DbField("门店ID","STORE_ID",GaConstant.DT_LONG);
		  field.setAliasCode("s");
	    field.setIsFilterCode(true);
	    field.setColumnDisplay(false, 0, true);
	    dbFields.add(field);
	    
	    field = new DbField("门店","STORE_NAME",GaConstant.DT_STRING);
	    field.setQueryOpera(GaConstant.QUERY_LIKE, 1, true);
	    dbFields.add(field);
//	    field = new DbField("门店","STORE_NAME",GaConstant.DT_STRING);
//	    field.setQueryOpera(GaConstant.QUERY_LIKE, 1, true);
//	    ActionButton action = new ActionButton(this.getClass(),"STORE_ID","{value}","/store/store_detail.htm",null);
//	    SubmitTool.submitToDialog(action, "STORE_NAME","门店详情", 1000, 380, this.modelMap.getPreNavInfoStr());
//	    StringDecorator decorator = new StringDecorator();
//	    decorator.setLinkDecorator(action);
//	    action.bindTableRowData(this.viewID);
//	    action.addParam(GaConstant.FIXPARAM_EDITMODE,GaConstant.EDITMODE_EDIT);
//	    field.setColumnDecorator("", decorator);
//	    field.setInputCustomStyle("width:90px");
//	    field.setAliasCode("sso");
//	    dbFields.add(field);

	    field = new DbField("会员ID","MEMBER_ID",GaConstant.DT_LONG); 
	    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "MEMBER_ID");
	    field.setColumnDisplay(false, 0, true);
	    dbFields.add(field);
	      
	    field = new DbField("会员编号", "MEMBER_NUM", GaConstant.DT_STRING);
	    field.setQueryOpera(GaConstant.QUERY_LIKE, 2, false);
	    dbFields.add(field);
//	    field = new DbField("会员编号", "MEMBER_NUM", GaConstant.DT_STRING);
//	    action = new ActionButton(this.getClass(),"MEMBER_ID","{value}","/member/member_detail.htm",null);
//	    SubmitTool.submitToDialog(action, "MEMBER_ID","会员详情", 800, 480, this.modelMap.getPreNavInfoStr());
//	    decorator = new StringDecorator();
//	    decorator.setLinkDecorator(action);
//	    decorator.setStringFormat("<font color='red'>{value}</font>");
//	    action.bindTableRowData(this.viewID);
//	    action.addParam(GaConstant.FIXPARAM_EDITMODE,GaConstant.EDITMODE_EDIT);
//	    field.setColumnDecorator("", decorator);
//	    field.setQueryOpera(GaConstant.QUERY_LIKE, 2, false);
//	    field.setColumnDisplay(true, 100, false);
//	    dbFields.add(field);
	    
			field = new DbField("营业员","TRUENAME",GaConstant.DT_STRING);
			field.setQueryOpera(GaConstant.QUERY_LIKE, 3, true);
			field.setInputCustomStyle("width:80px;");
		  dbFields.add(field);
		  	
			field = new DbField("POS机","NAME",GaConstant.DT_STRING);
			field.setQueryOpera(GaConstant.QUERY_LIKE, 4, true);
			field.setInputCustomStyle("width:80px;");
			dbFields.add(field);
			
			field = new DbField("订单原金额","ORDER_MONEY",GaConstant.DT_MONEY);
			field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 5, false);
			dbFields.add(field);
			
			field = new DbField("退还现金","RETURN_CASH",GaConstant.DT_MONEY);
			dbFields.add(field);
			
			field = new DbField("退还银行卡金额","RETURN_BANK",GaConstant.DT_MONEY);
			dbFields.add(field);
			
			field = new DbField("退还积分","RETURN_SCORE",GaConstant.DT_INT);
			dbFields.add(field);
			
			field = new DbField("退还优惠券金额","RETURN_COUPON",GaConstant.DT_MONEY);
			dbFields.add(field);
			
			field = new DbField("退货日期","CREATE_TIME",GaConstant.DT_DATETIME);
			field.setAliasCode("sro");
			field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
			field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_DATETIME, 6, true);
			dbFields.add(field);

			field = new DbField("状态","STATE",GaConstant.DT_INT);
			field.setAliasCode("sro");
			field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT,7,true);
			Map<String, Object> lookup = new LinkedHashMap<String, Object>();
		  lookup.put("1", "待处理");
		  lookup.put("2", "已完成");
		  lookup.put("3", "拒绝处理");
		  field.setLookupData(new LookupDataSet(lookup));
			dbFields.add(field);
//			
//			field = new DbField("退货原因","REASON",GaConstant.DT_STRING);
//			dbFields.add(field);
//			
//			field = new DbField("备注","NOTE",GaConstant.DT_STRING);
//			dbFields.add(field);
//			
			this.fieldList = dbFields;
	  }
	

}
