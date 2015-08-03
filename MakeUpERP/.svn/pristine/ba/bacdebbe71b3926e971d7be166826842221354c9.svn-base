package com.ga.erp.biz.member.viplog;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;

public class VipLogListView extends ListView {

	public VipLogListView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap, GaConstant.EDITMODE_DISP);
	}

	@Override
	public void initField() throws Exception {

		List<DbField> dbFields = new ArrayList<DbField>();

		DbField field = new DbField("等级变更记录ID", "VIP_LOG_ID",GaConstant.DT_LONG);
		field.setColumnDisplay(false, 0, true);
		field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "VIP_LOG_ID");
		dbFields.add(field);

		field = new DbField("门店ID", "STORE_ID", GaConstant.DT_LONG);
    field.setColumnDisplay(false, 0, false);
    field.setIsFilterCode(true);
    dbFields.add(field);
    
		field = new DbField("会员编号", "MEMBER_NUM", GaConstant.DT_STRING);
		field.setQueryOpera(GaConstant.QUERY_LIKE, 1, false);
		dbFields.add(field);

		field = new DbField("变更前等级", "BEFORE_LV", GaConstant.DT_INT);
		dbFields.add(field);

		field = new DbField("变更后等级", "AFTER_LV", GaConstant.DT_INT);
		dbFields.add(field);

		field = new DbField("变更时累计金额", "AMOUNT", GaConstant.DT_MONEY);
		field.setInputCustomStyle("width:50px;");
		dbFields.add(field);

		field = new DbField("变更时间", "CREATE_TIME", GaConstant.DT_DATETIME);
		field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,
				"yyyy-MM-dd HH:mm:ss", false));
		field.setInputCustomStyle("width:80px;");
		field.setQueryOpera(GaConstant.QUERY_BETWEEN,
				GaConstant.INPUT_DATETIME, 2, false);
		dbFields.add(field);

		this.fieldList = dbFields;
	}

}
