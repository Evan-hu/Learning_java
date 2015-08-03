package com.ga.erp.biz.content.msgsend;

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

public class MsgSendListView extends ListView {
	
  public MsgSendListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {
	  
	List<DbField> dbFields = new ArrayList<DbField>();
	    
	DbField field = new DbField("ID","SHOP_ID",GaConstant.DT_LONG); 
	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "SHOP_ID");
	field.setColumnDisplay(false, 0, true);
	dbFields.add(field);
	    
	field = new DbField("�̼�����", "SHOP_NAME", GaConstant.DT_STRING);
	field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
	dbFields.add(field);
	
	field = new DbField("��Ա�ʺ�", "USERNAME", GaConstant.DT_STRING);
	field.setQueryOpera(GaConstant.QUERY_LIKE, 1, false);
	dbFields.add(field);
	
	field = new DbField("����","TYPE",GaConstant.DT_INT);
	field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT, 2, true);
	Map<String,Object> option = new LinkedHashMap<String, Object>();
	option.put("1", "�����̼�");
	option.put("2", "��ҵ�̼�");
	field.setLookupData(new LookupDataSet(option));
	dbFields.add(field);
	    
    field = new DbField("��������","SHOP_TYPE",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT, 3, true);
    option = new LinkedHashMap<String, Object>();
    option.put("3", "������");
    field.setLookupData(new LookupDataSet(option));
    dbFields.add(field);
    
	field = new DbField("���˴���", "LEGAL", GaConstant.DT_STRING);
	dbFields.add(field);
	
	field = new DbField("��ϵ�绰", "TEL", GaConstant.DT_STRING);
	dbFields.add(field);
    
    field = new DbField("�Ƿ��Ƽ�","IS_RECOMMEND",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT, 4, true);
    field.setLookupData(SystemUtil.getYesNoMap());
    dbFields.add(field);
    
    field = new DbField("�Ƿ���֤","IS_CHECK",GaConstant.DT_INT);
	field.setInput(GaConstant.INPUT_SELECT);
	field.setLookupData(SystemUtil.getYesNoMap());
	dbFields.add(field);
	
	field = new DbField("�Ƿ������˵���","IS_OPEN_PERSONAL",GaConstant.DT_INT);
	field.setInput(GaConstant.INPUT_SELECT);
	field.setLookupData(SystemUtil.getYesNoMap());
	dbFields.add(field);
	
    field = new DbField("����ʱ��","CREATE_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    dbFields.add(field);
    
    field = new DbField("״̬","STATE",GaConstant.DT_INT);
    field.setAliasCode("s");
    field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT, 5, true);
    field.setLookupData(SystemUtil.getStatusMap());
    field.setColumnDisplay(true, 50, false);
    dbFields.add(field);

    this.fieldList = dbFields;
  }
  
}
