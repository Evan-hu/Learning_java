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

		field = new DbField("�ŵ���", "STORE_NUM", GaConstant.DT_STRING);
		dbFields.add(field);

		field = new DbField("��������", "OBJECT_TYPE", GaConstant.DT_INT);
	  field.setLookupData(SystemUtil.getObjectTypeMap());
	  field.setInput(GaConstant.INPUT_SELECT);
		dbFields.add(field);

		field = new DbField("������","STORE_ORDER_NUM",GaConstant.DT_STRING);
		dbFields.add(field);

		field = new DbField("������ʽ", "TYPE", GaConstant.DT_INT);
		field.setInput(GaConstant.INPUT_SELECT);
		Map<String, Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("1", "����");
    lookup.put("0", "����");
    field.setLookupData(new LookupDataSet(lookup));
		dbFields.add(field);

		field = new DbField("�������", "MONEY", GaConstant.DT_MONEY);
		dbFields.add(field);

		field = new DbField("��ǰ���", "NOW_MONEY", GaConstant.DT_MONEY);
		dbFields.add(field);

		field = new DbField("����ʱ��", "CREATE_TIME", GaConstant.DT_DATETIME);
		field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss", false));
		dbFields.add(field);

		field = new DbField("��ע", "NOTE", GaConstant.DT_STRING);
		field.setInput(GaConstant.INPUT_TEXTAREA);
		field.addInputAttributeMap("rows", "5");
		field.addInputAttributeMap("cols", "30");
		dbFields.add(field);

		this.fieldList = dbFields;
	}
}
