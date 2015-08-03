package com.ga.erp.biz.store.storewalletlog;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.control.StringDecorator;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class WalletLogListView extends ListView {

	public WalletLogListView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap, GaConstant.EDITMODE_DISP);
	}

	@Override
	public void initField() throws Exception {

		List<DbField> dbFields = new ArrayList<DbField>();

		DbField field = new DbField("记录ID", "STORE_WALLET_LOG_ID",GaConstant.DT_LONG);
		field.setColumnDisplay(false, 0, true);
		field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ,"STORE_WALLET_LOG_ID");
		dbFields.add(field);

		field = new DbField("门店ID", "STORE_ID", GaConstant.DT_LONG);
		field.setColumnDisplay(false, 0, true);
		field.setIsFilterCode(true);
		field.setAliasCode("w");
    dbFields.add(field);
    
		field = new DbField("门店编号", "STORE_NUM", GaConstant.DT_INT);
		field.setQueryOpera(GaConstant.QUERY_LIKE, 1, false);
		dbFields.add(field);

		field = new DbField("对象类型", "OBJECT_TYPE", GaConstant.DT_INT);
		field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_NUMTEXT,2, false);
		field.setLookupData(SystemUtil.getObjectTypeMap());
		dbFields.add(field);

		field = new DbField("对象ID", "OBJECT_ID", GaConstant.DT_LONG);
		field.setColumnDisplay(false, 0, true);
		dbFields.add(field);
		
		field = new DbField("订单号","STORE_ORDER_NUM",GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 3, true);
    ActionButton action = new ActionButton(this.getClass(),"STORE_ORDER_NUM","{value}","/store/storeorder_detail.htm?STORE_ORDER_ID={OBJECT_ID}",null);
    SubmitTool.submitToDialog(action, "STORE_ORDER_NUM","订单详情", 800, 450, this.modelMap.getPreNavInfoStr());
    StringDecorator decorator = new StringDecorator();
    decorator.setLinkDecorator(action);
    action.addParam(GaConstant.FIXPARAM_EDITMODE,GaConstant.EDITMODE_EDIT);
    field.setColumnDecorator("", decorator);
    field.setInputCustomStyle("width:90px");
    dbFields.add(field);

		field = new DbField("增减方式", "TYPE", GaConstant.DT_INT);
		field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT,3, false);
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
		field.setInputCustomStyle("width:80px;");
		field.setQueryOpera(GaConstant.QUERY_BETWEEN,
				GaConstant.INPUT_DATETIME, 4, false);
		dbFields.add(field);

		this.fieldList = dbFields;
	}

}
