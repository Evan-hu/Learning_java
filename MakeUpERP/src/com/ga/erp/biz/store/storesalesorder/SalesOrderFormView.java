package com.ga.erp.biz.store.storesalesorder;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class SalesOrderFormView extends FormView {

	public SalesOrderFormView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap);
	}

	@Override
	public void initField() throws Exception {

		List<DbField> fieldList = new ArrayList<DbField>();

		DbField field = new DbField("����ID", "STORE_SALES_ORDER_ID",
				GaConstant.DT_LONG);
		field.setInput(GaConstant.INPUT_HIDDEN);
		field.setColumnDisplay(false, 0, true);
		field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ,
				"STORE_SALES_ORDER_ID");
		fieldList.add(field);

		field = new DbField("�������", "STORE_SALES_ORDER_NUM", GaConstant.DT_STRING);		
		fieldList.add(field);

		field = new DbField("��Ա���", "MEMBER_NUM", GaConstant.DT_STRING);		
		fieldList.add(field);

		field = new DbField("�ŵ���", "STORE_NUM", GaConstant.DT_STRING);
		fieldList.add(field);

		field = new DbField("ӪҵԱ", "TRUENAME", GaConstant.DT_STRING);		
		fieldList.add(field);

		field = new DbField("POS�����", "POS_NUM", GaConstant.DT_STRING);		
		fieldList.add(field);

		field = new DbField("�ϼƽ��", "TOTAL_MONEY", GaConstant.DT_MONEY);		
		fieldList.add(field);

		field = new DbField("�Żݽ��", "DISCOUNT_MONEY", GaConstant.DT_MONEY);		
		fieldList.add(field);

		field = new DbField("Ӧ�����", "PAY_MONEY", GaConstant.DT_MONEY);		
		fieldList.add(field);

		field = new DbField("��Ʒ����", "COMM_SORT_CNT", GaConstant.DT_INT);		
		fieldList.add(field);

		field = new DbField("��Ʒ����", "COMM_CNT", GaConstant.DT_INT);		
		fieldList.add(field);

		field = new DbField("����", "CHANGES", GaConstant.DT_MONEY);		
		fieldList.add(field);

		field = new DbField("���п�����", "BANK_AMOUNT", GaConstant.DT_MONEY);
		fieldList.add(field);

		field = new DbField("�ֽ𸶿�", "CASH_AMOUNT", GaConstant.DT_MONEY);
		fieldList.add(field);

		field = new DbField("���ָ���", "SCORE_AMOUNT", GaConstant.DT_MONEY);
		fieldList.add(field);

		field = new DbField("�Ƿ�ʹ���Ż�ȯ", "IS_COUPON", GaConstant.DT_INT);
		field.setInput(GaConstant.INPUT_SELECT);
		field.setLookupData(SystemUtil.getYesNoMap());
		fieldList.add(field);

		field = new DbField("�Ż�ȯ�ֿ۽��", "COUPON_DEDUCTION", GaConstant.DT_MONEY);
		fieldList.add(field);

		field = new DbField("�Ƿ��������", "IS_PROMOTION", GaConstant.DT_INT);
		field.setInput(GaConstant.INPUT_SELECT);
		field.setLookupData(SystemUtil.getYesNoMap());
		fieldList.add(field);

		field = new DbField("����ʱ��", "CREATE_TIME", GaConstant.DT_DATETIME);
		field.setFormat(new CustomFormat(GaConstant.DT_DATETIME, "yyyy-MM-dd HH:mm:ss", false));
		fieldList.add(field);

		field = new DbField("״̬", "STATE", GaConstant.DT_INT);
		field.setInput(GaConstant.INPUT_SELECT);
		field.setLookupData(SystemUtil.getStatusMap());
		fieldList.add(field);

		field = new DbField("����", "TYPE", GaConstant.DT_INT);
		field.setInput(GaConstant.INPUT_SELECT);
		field.setLookupData(SystemUtil.getOrderTypeMap());
		fieldList.add(field);

		this.fieldList = fieldList;

	}
}
