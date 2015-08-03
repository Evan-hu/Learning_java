package com.ga.erp.biz.activity.scorelog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.control.StringDecorator;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class ScoreLogListView extends ListView {
	
  public ScoreLogListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {
	  
	List<DbField> dbFields = new ArrayList<DbField>();
	  
	DbField field = new DbField("����ʹ�ü�¼ID", "SCORE_RECORD_ID",GaConstant.DT_LONG );
	field.setColumnDisplay(false, 0, true); 
	dbFields.add(field);
	  
	field = new DbField("���ֲ�������", "ACTION_TYPE",GaConstant.DT_INT);
	Map<String, Object> option = new HashMap<String, Object>();
	option.put("1", "�������");
	option.put("2", "�ֶ�����");
	option.put("3", "��������");
	field.setLookupData(new LookupDataSet(option));
	field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 1, false);
	dbFields.add(field);
	
	field = new DbField("��������", "SCORE", GaConstant.DT_MONEY);
	field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_MONEY, 2, false);
	field.setAliasCode("sl");
	field.setInputCustomStyle("width:50px;");
	dbFields.add(field);
	
	field = new DbField("��ǰ����", "NOW_SCORE", GaConstant.DT_MONEY);
	dbFields.add(field);
	
	field = new DbField("���ֹ���", "RULE_NAME", GaConstant.DT_STRING);
	field.setQueryOpera(GaConstant.QUERY_LIKE, 3, false);
	dbFields.add(field);
	

	field = new DbField("��Ա", "USERNAME",GaConstant.DT_STRING);
//	field.setAliasCode("m");
//	field.setQueryOpera(GaConstant.QUERY_LIKE, 4, false);
	dbFields.add(field);
	
	field = new DbField("ҵ������", "BIZ_TYPE",GaConstant.DT_INT);
	option = new HashMap<String, Object>();
	option.put("1", "�û�����");
	option.put("2", "����Ա�ֶ��޸�");
	field.setLookupData(new LookupDataSet(option));
	field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 6, false);
	dbFields.add(field);
	
	field = new DbField("��������title", "NEWBIZ_NAME", GaConstant.DT_STRING);
	field.setColumnDisplay(false,120, false);
	dbFields.add(field);
	
	field = new DbField("��������", "BIZ_NAME", GaConstant.DT_STRING);
	field.setQueryOpera(GaConstant.QUERY_LIKE, 7, false);
	field.setMaxLength(15);
	StringDecorator decorator = new StringDecorator();
	decorator.setStringFormat("<font title='{NEWBIZ_NAME}' style='cursor:pointer;'>{value}</font>");
	field.setColumnDecorator("", decorator);
	dbFields.add(field);

	field = new DbField("����ʱ��", "CREATE_TIME", GaConstant.DT_DATETIME);
	field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
	field.setInputCustomStyle("width:80px;");
	field.setQueryOpera(GaConstant.QUERY_BETWEEN, 8, false);
	dbFields.add(field);
	    
	field = new DbField("״̬", "STATE",GaConstant.DT_INT);
	field.setAliasCode("sl");
	field.setLookupData(SystemUtil.getStatusMap());
	field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 9, false);
	dbFields.add(field);
	
	field = new DbField("��עtitle", "NEWNOTE", GaConstant.DT_STRING);
	field.setColumnDisplay(false,10, false);
	dbFields.add(field);
	
	field = new DbField("��ע", "NOTE", GaConstant.DT_STRING);
	field.setColumnDisplay(true,120, false);
	decorator = new StringDecorator();
	decorator.setStringFormat("<font title='{NEWNOTE}' style='cursor:pointer;'>{value}</font>");
	field.setColumnDecorator("", decorator);
	field.setMaxLength(50);
	dbFields.add(field);

    this.fieldList = dbFields;
  }
  
}
