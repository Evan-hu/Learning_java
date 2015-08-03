package com.ga.erp.biz.store.storesalesorder;

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

public class SalesOrderListView extends ListView {

	public SalesOrderListView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap,GaConstant.EDITMODE_DISP);
	}

	@Override
	public void initField() throws Exception {

		List<DbField> fieldList = new ArrayList<DbField>();

		DbField field = new DbField("����ID", "STORE_SALES_ORDER_ID",GaConstant.DT_LONG);
		field.setColumnDisplay(false, 0, true);
		field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ,"STORE_SALES_ORDER_ID");
		fieldList.add(field);

		field = new DbField("�������", "STORE_SALES_ORDER_NUM", GaConstant.DT_INT);
		field.setColumnDisplay(true, 60, false);
		field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
		fieldList.add(field);

    field = new DbField("�ŵ�ID","STORE_ID",GaConstant.DT_LONG);
    field.setAliasCode("o");
    field.setIsFilterCode(true);
    field.setColumnDisplay(false, 0, true);
    fieldList.add(field);
    
    field = new DbField("�ŵ�","STORE_NAME",GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 1, true);
    ActionButton action = new ActionButton(this.getClass(),"STORE_ID","{value}","/store/store_detail.htm",null);
    SubmitTool.submitToDialog(action, "STORE_NAME","�ŵ�����", 1000, 480, this.modelMap.getPreNavInfoStr());
    StringDecorator decorator = new StringDecorator();
    decorator.setLinkDecorator(action);
    action.bindTableRowData(this.viewID);
    action.addParam(GaConstant.FIXPARAM_EDITMODE,GaConstant.EDITMODE_EDIT);
    field.setColumnDecorator("", decorator);
    field.setInputCustomStyle("width:90px");
    fieldList.add(field);

    field = new DbField("ID","MEMBER_ID",GaConstant.DT_LONG); 
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "MEMBER_ID");
    field.setColumnDisplay(false, 0, true);
    fieldList.add(field);
        
    field = new DbField("��Ա���", "MEMBER_NUM", GaConstant.DT_STRING);
    action = new ActionButton(this.getClass(),"MEMBER_ID","{value}","/member/member_detail.htm",null);
    SubmitTool.submitToDialog(action, "MEMBER_ID","��Ա����", 800, 480, this.modelMap.getPreNavInfoStr());
    decorator = new StringDecorator();
    decorator.setLinkDecorator(action);
    decorator.setStringFormat("<font color='red'>{value}</font>");
    action.bindTableRowData(this.viewID);
    action.addParam(GaConstant.FIXPARAM_EDITMODE,GaConstant.EDITMODE_EDIT);
    field.setColumnDecorator("", decorator);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 1, false);
    field.setColumnDisplay(true, 100, false);
    fieldList.add(field);

    field = new DbField("ӪҵԱ", "TRUENAME", GaConstant.DT_STRING);
    field.setAliasCode("c");
    field.setColumnDisplay(true, 60, false);
    field.setInputCustomStyle("width:80px");
    field.setQueryOpera(GaConstant.QUERY_LIKE, 3, false);
    fieldList.add(field);

    field = new DbField("POS��", "POS_NUM", GaConstant.DT_STRING);
    field.setAliasCode("d");
    field.setColumnDisplay(true, 60, false);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 4, false);
    fieldList.add(field);
    
    field = new DbField("�ϼƽ��", "TOTAL_MONEY", GaConstant.DT_MONEY);
    fieldList.add(field);

    field = new DbField("�Żݽ��", "DISCOUNT_MONEY", GaConstant.DT_MONEY);
    fieldList.add(field);

    field = new DbField("Ӧ�����", "PAY_MONEY", GaConstant.DT_MONEY);
    fieldList.add(field);
    
    field = new DbField("����", "TYPE", GaConstant.DT_INT);
    field.setLookupData(SystemUtil.getOrderTypeMap());
    fieldList.add(field);

    field = new DbField("����ʱ��", "CREATE_TIME", GaConstant.DT_DATETIME);
    field.setAliasCode("o");
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss", false));
    field.setInputCustomStyle("width:80px;");
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_DATETIME, 5, false);
    fieldList.add(field);

		field = new DbField("��Ʒ����", "COMM_SORT_CNT", GaConstant.DT_INT);
		fieldList.add(field);

		field = new DbField("��Ʒ����", "COMM_CNT", GaConstant.DT_INT);
		fieldList.add(field);

		field = new DbField("�Ƿ�ʹ���Ż�ȯ", "IS_COUPON", GaConstant.DT_INT);
		field.setLookupData(SystemUtil.getYesNoMap());
		fieldList.add(field);

		field = new DbField("�Ƿ��������", "IS_PROMOTION", GaConstant.DT_INT);
		field.setLookupData(SystemUtil.getYesNoMap());
		fieldList.add(field);

		this.fieldList = fieldList;
	}
}
