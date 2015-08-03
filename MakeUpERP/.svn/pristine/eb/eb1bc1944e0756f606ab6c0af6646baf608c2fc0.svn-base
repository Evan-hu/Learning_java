package com.ga.erp.biz.system.audits.example;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.StringDecorator;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;

public class AuditsExampleFormView extends FormView {

	public AuditsExampleFormView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap);
	}

	@Override
	public void initField() throws Exception {

		List<DbField> dbFields = new ArrayList<DbField>();

		DbField field = new DbField("审核实例ID", "AUDITING_EXAMPLE_ID",GaConstant.DT_LONG);
		field.setColumnDisplay(false, 0, true);
		field.setInput(GaConstant.INPUT_HIDDEN);
		field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "AUDITING_EXAMPLE_ID");
		dbFields.add(field);
		
		field = new DbField("编码ID", "SYS_CODE_ID", GaConstant.DT_INT);
		field.setInput(GaConstant.INPUT_HIDDEN);
	    field.setColumnDisplay(false, 0, true);
	    dbFields.add(field);
	  

		field = new DbField("业务名称", "OBJECT_NAME", GaConstant.DT_STRING);
		field.addInputAttributeMap("target", "row_data");
		field.addInputAttributeMap("class", "selected");
		dbFields.add(field);
		
	    StringDecorator decorator = new StringDecorator();
	    ActionButton button = new ActionButton(this.getClass(),"openObject","<font color='red'>[查看]</font>","/activity/activity_edit.htm",null);
	    button.addParam(GaConstant.FIXPARAM_EDITMODE,GaConstant.EDITMODE_EDIT);
	    button.addParam("_rowdata", "{row_data}");
	    button.setAttribute("paramValue", "1");
//	    button.bindForm(this.viewID,true);
	    SubmitTool.submitToDialog(button, "newmemberDlg", "查看", 900, 600,this.modelMap.getPreNavInfoStr());
	    decorator.setLinkDecorator(button);
	    decorator.setAdd(true);
	    button.setAttribute("style", "text-decoration: none");
	    field.setColumnDecorator("", decorator);
		
		field = new DbField("业务类型", "SYS_CODE_NAME", GaConstant.DT_STRING);
		dbFields.add(field);
		
		
		field = new DbField("业务ID", "BIZ_ID", GaConstant.DT_INT);
		field.setInput(GaConstant.INPUT_HIDDEN);
        field.setColumnDisplay(false, 0, true);
        dbFields.add(field);
        
        field = new DbField("提交审核时间", "CREATE_TIME", GaConstant.DT_DATETIME);
		field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss", false));
		field.setInputCustomStyle("width:130px;");
		dbFields.add(field);
		
		field = new DbField("最近审核时间", "LAST_TIME", GaConstant.DT_DATETIME);
		field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss", false));
		field.setInputCustomStyle("width:130px;");
		dbFields.add(field);
    
	    field = new DbField("最后审核人", "LAST_OP_ID", GaConstant.DT_STRING);
	    field.setQueryOpera(GaConstant.QUERY_LIKE, 2, false);
	    dbFields.add(field);
	    
	    field = new DbField("当前审核步骤", "NOW_STEP", GaConstant.DT_INT);
        dbFields.add(field);
	    
	    field = new DbField("总共审核步骤", "ALL_STEP", GaConstant.DT_INT);
	    dbFields.add(field);
		this.fieldList = dbFields;
	}

}
