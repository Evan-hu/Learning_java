package com.ga.erp.biz.system.audits.example;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class AuditsExampleListView extends ListView {

	public AuditsExampleListView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap, GaConstant.EDITMODE_DISP);
	}

	@Override
	public void initField() throws Exception {

		List<DbField> dbFields = new ArrayList<DbField>();

		DbField field = new DbField("审核实例ID", "AUDITING_EXAMPLE_ID",GaConstant.DT_LONG);
		field.setColumnDisplay(false, 0, true);
		field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "AUDITING_EXAMPLE_ID");
		dbFields.add(field);
		
		field = new DbField("编码ID", "SYS_CODE_ID", GaConstant.DT_INT);
	    field.setColumnDisplay(false, 0, true);
	    dbFields.add(field);
	  
	    field = new DbField("业务名称", "OBJECT_NAME", GaConstant.DT_STRING);
		field.setQueryOpera(GaConstant.QUERY_LIKE, 1, false);
		dbFields.add(field);
	  
		field = new DbField("业务类型", "SYS_CODE_NAME", GaConstant.DT_STRING);
		field.setQueryOpera(GaConstant.QUERY_LIKE, 1, false);
		dbFields.add(field);
		
		field = new DbField("业务ID", "BIZ_ID", GaConstant.DT_INT);
	    field.setColumnDisplay(false, 0, true);
	    dbFields.add(field);

		field = new DbField("当前审核步骤", "NOW_STEP", GaConstant.DT_INT);
		dbFields.add(field);
    
	    field = new DbField("总共审核步骤", "ALL_STEP", GaConstant.DT_INT);
	    dbFields.add(field);


	    field= new DbField("审核状态", "STATE", GaConstant.DT_INT);
	    field.setInput(GaConstant.INPUT_SELECT);
	    Map<String, Object> auditsMap = new LinkedHashMap<String, Object>();
        auditsMap.put("0", "待审核");
        auditsMap.put("1", "审核中");
        auditsMap.put("2", "审核结束");
	    field.setLookupData(new LookupDataSet(auditsMap));
	    field.setVerifyFormula("", true);
	    dbFields.add(field);
	    
	    field = new DbField("最后审核人", "LAST_OP_ID", GaConstant.DT_STRING);
	    field.setQueryOpera(GaConstant.QUERY_LIKE, 2, false);
	    dbFields.add(field);

		field = new DbField("最后审核时间", "LAST_TIME", GaConstant.DT_DATETIME);
		field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss", false));
	    field.setInputCustomStyle("width:80px;");
		field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_DATETIME, 3, false);
		dbFields.add(field);

		this.fieldList = dbFields;
	}

}
