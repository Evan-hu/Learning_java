package com.ga.erp.biz.store.storewalletlog;

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
import com.ga.erp.util.SystemUtil;

public class WalletLogFormView extends FormView {

	public WalletLogFormView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap);
	}

	@Override
	public void initField() throws Exception {

		List<DbField> dbFields = new ArrayList<DbField>();

		DbField field = new DbField("ID", "STORE_WALLET_LOG_ID",GaConstant.DT_LONG);
		field.setInput(GaConstant.INPUT_HIDDEN);
		field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ,	"STORE_WALLET_LOG_ID");
		dbFields.add(field);

		field = new DbField("门店编号", "STORE_NUM", GaConstant.DT_STRING);
		dbFields.add(field);

		field = new DbField("对象类型", "OBJECT_TYPE", GaConstant.DT_INT);
	  field.setLookupData(SystemUtil.getObjectTypeMap());
	  field.setInput(GaConstant.INPUT_SELECT);
		dbFields.add(field);

		field = new DbField("订单号","STORE_ORDER_NUM",GaConstant.DT_STRING);
		dbFields.add(field);

		field = new DbField("增减方式", "TYPE", GaConstant.DT_INT);
		field.setInput(GaConstant.INPUT_SELECT);
		Map<String, Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("1", "增加");
    lookup.put("0", "减少");
    field.setLookupData(new LookupDataSet(lookup));
		dbFields.add(field);

		field = new DbField("发生金额", "MONEY", GaConstant.DT_MONEY);
		dbFields.add(field);

		field = new DbField("当前金额", "NOW_MONEY", GaConstant.DT_MONEY);
		dbFields.add(field);

		field = new DbField("发生时间", "CREATE_TIME", GaConstant.DT_DATETIME);
		field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss", false));
		dbFields.add(field);

		field = new DbField("备注", "NOTE", GaConstant.DT_STRING);
		field.setInput(GaConstant.INPUT_TEXTAREA);
		field.addInputAttributeMap("rows", "5");
		field.addInputAttributeMap("cols", "30");
		dbFields.add(field);

		this.fieldList = dbFields;
	}
}
