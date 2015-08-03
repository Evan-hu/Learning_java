package com.ga.erp.biz.store.storesalesdetail;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.util.SystemUtil;

public class SalesDetailListView extends ListView {

	public SalesDetailListView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap, GaConstant.EDITMODE_DISP);
	}

	@Override
	public void initField() throws Exception {

		List<DbField> dbFields = new ArrayList<DbField>();

		DbField field = new DbField("明细ID", "STORE_SALES_DETAIL_ID",
				GaConstant.DT_LONG);
		field.setColumnDisplay(false, 0, true);
		field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ,
				"STORE_SALES_DETAIL_ID");
		dbFields.add(field);

		field = new DbField("订单编号", "STORE_SALES_ORDER_NUM", GaConstant.DT_INT);
//		field.setColumnDisplay(false, 0, true);
//		field.setQueryOpera(GaConstant.QUERY_LIKE, 1, false);
		dbFields.add(field);

		field = new DbField("商品名", "COMMODITY_NAME", GaConstant.DT_STRING);
		field.setQueryOpera(GaConstant.QUERY_LIKE, 2, false);
		dbFields.add(field);

		field = new DbField("商品数量", "COMM_CNT", GaConstant.DT_INT);
		dbFields.add(field);

		field = new DbField("零售价", "RETAIL_PRICE", GaConstant.DT_MONEY);
		field.setInputCustomStyle("width:50px;");
		dbFields.add(field);

		field = new DbField("购买价", "BUY_PRICE", GaConstant.DT_MONEY);
		field.setInputCustomStyle("width:50px;");
		dbFields.add(field);

		field = new DbField("是否为会员价", "IS_PRICE", GaConstant.DT_INT);
		field.setInputCustomStyle("width:50px;");
		field.setLookupData(SystemUtil.getYesNoMap());
		dbFields.add(field);

		this.fieldList = dbFields;
	}

}
