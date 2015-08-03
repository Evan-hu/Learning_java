package com.ga.erp.biz.store.storepos;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.util.SystemUtil;

public class PosListView extends ListView {

	public PosListView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap, GaConstant.EDITMODE_DISP);
	}

	@Override
	public void initField() throws Exception {

		List<DbField> dbFields = new ArrayList<DbField>();

		DbField field = new DbField("ID", "POS_ID", GaConstant.DT_LONG);
		field.setColumnDisplay(false, 0, true);
		field.setPK(true);
		field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "POS_ID");
		dbFields.add(field);
		
		field = new DbField("STORE_ID","STORE_ID",GaConstant.DT_LONG);
		field.setAliasCode("p");
		field.setColumnDisplay(false, 0,false);
		field.setIsFilterCode(true);
		dbFields.add(field);
		
		field = new DbField("POS�����", "POS_NUM", GaConstant.DT_STRING);
		field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
		dbFields.add(field);

	  field = new DbField("�ŵ���", "STORE_NAME", GaConstant.DT_STRING);
	  field.setQueryOpera(GaConstant.QUERY_LIKE, 5, false);
	  dbFields.add(field);

		field = new DbField("�Ƿ�����", "LOGIN_STATE", GaConstant.DT_INT);
		field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT,
				1, false);
		field.setLookupData(SystemUtil.getYesNoMap());
		dbFields.add(field);
		
		field = new DbField("�ۼ����۽��", "ALL_CONSUME_MONEY", GaConstant.DT_MONEY);
		field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY,2, false);
		field.setInputCustomStyle("width:50px;");
		field.setAliasCode("p");
		dbFields.add(field);

		field = new DbField("�ۼ����۵���", "ALL_CONSUME_ORDER", GaConstant.DT_LONG);
		dbFields.add(field);

		field = new DbField("�ۼ��˻����", "ALL_RETURN_MONEY", GaConstant.DT_MONEY);
		field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY,4, false);
		field.setAliasCode("p");
		field.setInputCustomStyle("width:50px;");
		dbFields.add(field);

		field = new DbField("�ۼ��˻�����", "ALL_RETURN_ORDER", GaConstant.DT_INT);
		dbFields.add(field);	

		field = new DbField("�������", "NAME", GaConstant.DT_STRING);
		dbFields.add(field);

		field = new DbField("MAC��ַ", "MAC_ADDR", GaConstant.DT_STRING);
		dbFields.add(field);

    field = new DbField("״̬", "STATE", GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT,6, false);
    field.setLookupData(SystemUtil.getStatusMap());
    field.setAliasCode("p");
    dbFields.add(field);
    
		this.fieldList = dbFields;
	}

}
