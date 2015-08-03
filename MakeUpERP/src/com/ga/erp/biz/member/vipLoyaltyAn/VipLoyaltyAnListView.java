package com.ga.erp.biz.member.vipLoyaltyAn;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.StringDecorator;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class VipLoyaltyAnListView extends ListView{

	public VipLoyaltyAnListView(String viewID, ModelMap modelMap) {
		
		super(viewID, modelMap,GaConstant.EDITMODE_DISP);
	}
	  @Override
	  public void initField() throws Exception {
		  
	        List<DbField> dbFields = new ArrayList<DbField>();
		    
		  	DbField field = new DbField("ID","MEMBER_ID",GaConstant.DT_LONG); 
		  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "MEMBER_ID");
		  	field.setColumnDisplay(false, 0, true);
		  	dbFields.add(field);
		  	
		  	 field = new DbField("门店ID", "STORE_ID", GaConstant.DT_LONG);
		  	 
		      field.setColumnDisplay(false, 0, false);
		      dbFields.add(field);
		      
		      field = new DbField("注册门店", "STORE_NAME", GaConstant.DT_STRING);
		      field.setQueryOpera(GaConstant.QUERY_LIKE, GaConstant.INPUT_POPSELECT, 1, false, true);
		      field.setPopSelect("STORESELECT","STORE_NAME",true,
		          "/store/store_main.htm",
		          "STORE_ID,STORE_NAME,cid_storeList",800,450);
		      field.setInputCustomStyle("width:120px;");
		      field.setColumnDisplay(true, 80, false);
		      dbFields.add(field);
		  	
  			field = new DbField("会员编号","MEMBER_NUM",GaConstant.DT_STRING);
  			ActionButton action = new ActionButton(this.getClass(),"MEMBER_NUM","{value}","/member/vipLoyaltyAn_detail.htm",null);
		    SubmitTool.submitToDialog(action, "MEMBER_NUM","会员详情", 800, 380, this.modelMap.getPreNavInfoStr());
		    StringDecorator decorator = new StringDecorator();
		    decorator.setLinkDecorator(action);
		    decorator.setStringFormat("<font color='red'>{value}</font>");
		    action.bindTableRowData(this.viewID);
		    action.addParam(GaConstant.FIXPARAM_EDITMODE,GaConstant.EDITMODE_EDIT);
		    field.setColumnDecorator("", decorator);
		    field.setColumnDisplay(true, 80, true);
			field.setQueryOpera(GaConstant.QUERY_LIKE, 1, true);
			field.setInputCustomStyle("width:80px;");
		  	dbFields.add(field);
		  	
			field = new DbField("真实姓名","TRUENAME",GaConstant.DT_STRING);
			field.setQueryOpera(GaConstant.QUERY_LIKE, 2, true);
			field.setInputCustomStyle("width:80px;");
		  	dbFields.add(field);
		  	
			field = new DbField("累计消费金额","ALL_AMOUNT",GaConstant.DT_MONEY);
			field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_MONEY, 3, true);
			dbFields.add(field);
			
			field = new DbField("累计订单数","ALL_ORDER",GaConstant.DT_INT);
			field.setQueryOpera(GaConstant.QUERY_GREATER_EQUALS, 4, true);
			dbFields.add(field);
			
			field = new DbField("平均月购买价格","AVG_MONTH_AMOUNT",GaConstant.DT_MONEY);
			field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_MONEY, 5, true);
			dbFields.add(field);
			
			field = new DbField("平均客单价","AVG_ORDER_AMOUNT",GaConstant.DT_MONEY);
			field.setAliasCode("a");
			field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_MONEY, 6, true);
			dbFields.add(field);
			
			field = new DbField("最近购买日期","LAST_TIME",GaConstant.DT_DATETIME);
			field.setAliasCode("a");
			field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
			field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_DATETIME, 7, true);
			dbFields.add(field);
			
			field = new DbField("激活日期","ACTIVATE_TIME",GaConstant.DT_DATETIME);
//			field.setAliasCode("a");
//			field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
//			field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_DATETIME, 8, true);
			dbFields.add(field);
			
			this.fieldList = dbFields;
	  }
	

}
