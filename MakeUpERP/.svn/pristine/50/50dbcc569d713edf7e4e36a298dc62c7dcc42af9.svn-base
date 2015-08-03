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

		DbField field = new DbField("订单ID", "STORE_SALES_ORDER_ID",
				GaConstant.DT_LONG);
		field.setInput(GaConstant.INPUT_HIDDEN);
		field.setColumnDisplay(false, 0, true);
		field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ,
				"STORE_SALES_ORDER_ID");
		fieldList.add(field);

		field = new DbField("订单编号", "STORE_SALES_ORDER_NUM", GaConstant.DT_STRING);		
		fieldList.add(field);

		field = new DbField("会员编号", "MEMBER_NUM", GaConstant.DT_STRING);		
		fieldList.add(field);

		field = new DbField("门店编号", "STORE_NUM", GaConstant.DT_STRING);
		fieldList.add(field);

		field = new DbField("营业员", "TRUENAME", GaConstant.DT_STRING);		
		fieldList.add(field);

		field = new DbField("POS机编号", "POS_NUM", GaConstant.DT_STRING);		
		fieldList.add(field);

		field = new DbField("合计金额", "TOTAL_MONEY", GaConstant.DT_MONEY);		
		fieldList.add(field);

		field = new DbField("优惠金额", "DISCOUNT_MONEY", GaConstant.DT_MONEY);		
		fieldList.add(field);

		field = new DbField("应付金额", "PAY_MONEY", GaConstant.DT_MONEY);		
		fieldList.add(field);

		field = new DbField("商品种类", "COMM_SORT_CNT", GaConstant.DT_INT);		
		fieldList.add(field);

		field = new DbField("商品数量", "COMM_CNT", GaConstant.DT_INT);		
		fieldList.add(field);

		field = new DbField("找零", "CHANGES", GaConstant.DT_MONEY);		
		fieldList.add(field);

		field = new DbField("银行卡付款", "BANK_AMOUNT", GaConstant.DT_MONEY);
		fieldList.add(field);

		field = new DbField("现金付款", "CASH_AMOUNT", GaConstant.DT_MONEY);
		fieldList.add(field);

		field = new DbField("积分付款", "SCORE_AMOUNT", GaConstant.DT_MONEY);
		fieldList.add(field);

		field = new DbField("是否使用优惠券", "IS_COUPON", GaConstant.DT_INT);
		field.setInput(GaConstant.INPUT_SELECT);
		field.setLookupData(SystemUtil.getYesNoMap());
		fieldList.add(field);

		field = new DbField("优惠券抵扣金额", "COUPON_DEDUCTION", GaConstant.DT_MONEY);
		fieldList.add(field);

		field = new DbField("是否参与促销活动", "IS_PROMOTION", GaConstant.DT_INT);
		field.setInput(GaConstant.INPUT_SELECT);
		field.setLookupData(SystemUtil.getYesNoMap());
		fieldList.add(field);

		field = new DbField("交易时间", "CREATE_TIME", GaConstant.DT_DATETIME);
		field.setFormat(new CustomFormat(GaConstant.DT_DATETIME, "yyyy-MM-dd HH:mm:ss", false));
		fieldList.add(field);

		field = new DbField("状态", "STATE", GaConstant.DT_INT);
		field.setInput(GaConstant.INPUT_SELECT);
		field.setLookupData(SystemUtil.getStatusMap());
		fieldList.add(field);

		field = new DbField("类型", "TYPE", GaConstant.DT_INT);
		field.setInput(GaConstant.INPUT_SELECT);
		field.setLookupData(SystemUtil.getOrderTypeMap());
		fieldList.add(field);

		this.fieldList = fieldList;

	}
}
