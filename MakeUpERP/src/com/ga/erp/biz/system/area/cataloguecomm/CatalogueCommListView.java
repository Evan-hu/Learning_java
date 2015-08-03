package com.ga.erp.biz.system.area.cataloguecomm;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;

public class CatalogueCommListView extends ListView {

	public CatalogueCommListView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap, GaConstant.EDITMODE_DISP);
	}

	@Override
	public void initField() throws Exception {

		List<DbField> fieldList = new ArrayList<DbField>();

		DbField field = new DbField("��ƷID", "CATALOGUE_COMM_ID",
				GaConstant.DT_LONG);
		field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ,
				"CATALOGUE_COMM_ID");
		field.setColumnDisplay(false, 0, true);
		field.setPK(true);
		fieldList.add(field);

		field = new DbField("�ɹ�Ŀ¼ID", "CATALOGUE_ID", GaConstant.DT_LONG);
		field.setColumnDisplay(false, 0, true);
		fieldList.add(field);

		field = new DbField("�ɹ�Ŀ¼����", "NAME", GaConstant.DT_STRING);
		field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
		fieldList.add(field);

		field = new DbField("��Ʒ����", "COMMODITY_NAME", GaConstant.DT_STRING);
		field.setQueryOpera(GaConstant.QUERY_LIKE, 1, false);
		fieldList.add(field);

		field = new DbField("�ɹ���", "PURCHASE_PRICE", GaConstant.DT_MONEY);
		field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY,
				2, true);
		field.setInputCustomStyle("width:80px;");
		field.setAliasCode("CC");
		fieldList.add(field);

		field = new DbField("�ۼ�", "RETAIL_PRICE", GaConstant.DT_MONEY);
		field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY,
				3, false);
		field.setInputCustomStyle("width:80px;");
		field.setAliasCode("CC");
		fieldList.add(field);

		field = new DbField("��Ա��", "MEMBER_PRICE", GaConstant.DT_MONEY);
		field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY,
				4, false);
		field.setInputCustomStyle("width:80px;");
		field.setAliasCode("CC");
		fieldList.add(field);

		this.fieldList = fieldList;
	}
}
