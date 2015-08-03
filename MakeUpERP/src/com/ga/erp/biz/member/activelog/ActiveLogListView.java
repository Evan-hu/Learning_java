package com.ga.erp.biz.member.activelog;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;

public class ActiveLogListView extends ListView {

	public ActiveLogListView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap, GaConstant.EDITMODE_DISP);
	}

	@Override
	public void initField() throws Exception {

		List<DbField> dbFields = new ArrayList<DbField>();

		DbField field = new DbField("激活日志ID", "ACTIVE_LOG_ID",
				GaConstant.DT_LONG);
		field.setColumnDisplay(false, 0, true);
		field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "ACTIVE_LOG_ID");
		dbFields.add(field);

		field = new DbField("门店ID", "STORE_ID", GaConstant.DT_LONG);
    field.setColumnDisplay(false, 0, false);
    field.setIsFilterCode(true);
    dbFields.add(field);
    
		field = new DbField("会员编号", "MEMBER_NUM", GaConstant.DT_STRING);
		field.setQueryOpera(GaConstant.QUERY_LIKE, 1, false);
		dbFields.add(field);

		field = new DbField("验证码", "AUTH_CODE", GaConstant.DT_STRING);
		dbFields.add(field);

		field = new DbField("创建日期", "CREATE_TIME", GaConstant.DT_DATETIME);
		field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,
				"yyyy-MM-dd HH:mm:ss", false));
		field.setInputCustomStyle("width:80px;");
		field.setQueryOpera(GaConstant.QUERY_BETWEEN,
				GaConstant.INPUT_DATETIME, 2, false);
		dbFields.add(field);

		field = new DbField("激活日期", "ACTIVE_TIME", GaConstant.DT_DATETIME);
		field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,
				"yyyy-MM-dd HH:mm:ss", false));
		field.setInputCustomStyle("width:80px;");
		field.setQueryOpera(GaConstant.QUERY_BETWEEN,
				GaConstant.INPUT_DATETIME, 3, false);
		dbFields.add(field);

		this.fieldList = dbFields;
	}

}
