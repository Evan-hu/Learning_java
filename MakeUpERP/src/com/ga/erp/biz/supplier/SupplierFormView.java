package com.ga.erp.biz.supplier;

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

public class SupplierFormView extends FormView {

	public SupplierFormView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap);
	}

	@Override
	public void initField() throws Exception {

		List<DbField> fieldList = new ArrayList<DbField>();

		DbField field = new DbField("��Ӧ��ID", "SUPPLIER_ID", GaConstant.DT_LONG);
		field.setInput(GaConstant.INPUT_HIDDEN);
	  field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "SUPPLIER_ID");
		fieldList.add(field);

		field = new DbField("�û���<font color='red'>*</font>", "SUPPLIER_NAME", GaConstant.DT_STRING);
		fieldList.add(field);

		field = new DbField("��ϵ��<font color='red'>*</font>", "LINK_USER",
				GaConstant.DT_STRING);
		fieldList.add(field);

		field = new DbField("��ϵ�绰<font color='red'>*</font>", "LINK_TEL",
				GaConstant.DT_STRING);
		fieldList.add(field);

		field = new DbField("QQ<font color='red'>*</font>", "QQ", GaConstant.DT_STRING);
		field.setInputCustomStyle("width:80px;");
		fieldList.add(field);

		field = new DbField("����", "FOX", GaConstant.DT_STRING);
		fieldList.add(field);

		field = new DbField("˰��Ǽ�֤����<font color='red'>*</font>", "TAX", GaConstant.DT_STRING);
		field.setInputCustomStyle("width:280px;");
		fieldList.add(field);

		field = new DbField("����<font color='red'>*</font>", "EMAIL", GaConstant.DT_STRING);
		fieldList.add(field);

		field = new DbField("Ӫҵִ�պ�<font color='red'>*</font>", "LICENSE_NO", GaConstant.DT_STRING);
		field.setInputCustomStyle("width:280px;");
		fieldList.add(field);

		field = new DbField("�ʱ�<font color='red'>*</font>", "ZIP_CODE", GaConstant.DT_STRING);
		fieldList.add(field);

		field = new DbField("������<font color='red'>*</font>", "BANK", GaConstant.DT_STRING);
		field.setInputCustomStyle("width:280px;");
		fieldList.add(field);

		field = new DbField("�ȼ�", "LEVEL", GaConstant.DT_STRING);
		field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
		fieldList.add(field);

		field = new DbField("����", "TYPE", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
		field.setInput(GaConstant.INPUT_SELECT);
		Map<String, Object> option = new LinkedHashMap<String, Object>();
		option.put("1", "��Ӧ��");
		option.put("2", "��ͻ�");
		field.setLookupData(new LookupDataSet(option));
		field.setVerifyFormula("", true);
		fieldList.add(field);

    field = new DbField("���㷽ʽ","CHECK_TYPE",GaConstant.DT_INT);
    field.setInput(GaConstant.INPUT_SELECT);
    Map<String, Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("1", "��ʱָ��");
    lookup.put("2", "ָ������");
    lookup.put("3", "ָ������");
    lookup.put("4", "��������");
    field.setLookupData(new LookupDataSet(lookup));
    fieldList.add(field);
    
    field = new DbField("�������ڣ��죩","CHECK_CYCLE",GaConstant.DT_INT);
    fieldList.add(field);
    
    field = new DbField("��������","CHECK_MONTH",GaConstant.DT_INT);
    fieldList.add(field);
		
		field = new DbField("��������", "CREATE_TIME", GaConstant.DT_DATETIME);
		field.setFormat(new CustomFormat(GaConstant.DT_DATETIME, "yyyy-MM-dd HH:mm:ss", false));
		field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
		fieldList.add(field);

//		field = new DbField("״̬", "STATE", GaConstant.DT_INT);
//		field.setInput(GaConstant.INPUT_SELECT);
//		field.setLookupData(SystemUtil.getStatusMap());
//		field.setVerifyFormula("", true);
//		fieldList.add(field);
		
		field = new DbField("����ID","AREA_ID", GaConstant.DT_LONG);
		field.setInput(GaConstant.INPUT_HIDDEN);
		field.setPopSelect("selectArea", "AREA_ID", false);
	  fieldList.add(field);
	     
    field = new DbField("��������<font color='red'>*</font>","AREA_NAME",GaConstant.DT_STRING);
    field.setPopSelect("selectArea","AREA_NAME",true, "/system/area_sele.htm", "AREA_ID,AREA_NAME,cid_areaTree",300,400);
    fieldList.add(field);
		
		field = new DbField("��ַ<font color='red'>*</font>", "ADDR", GaConstant.DT_STRING);
		field.setInputCustomStyle("width:530px;");
		fieldList.add(field);

		field = new DbField("��ע", "NOTE", GaConstant.DT_STRING);
		field.setInput(GaConstant.INPUT_TEXTAREA);
		field.addInputAttributeMap("rows", "3");
		field.addInputAttributeMap("cols", "98");
		fieldList.add(field);

		this.fieldList = fieldList;

	}
}
