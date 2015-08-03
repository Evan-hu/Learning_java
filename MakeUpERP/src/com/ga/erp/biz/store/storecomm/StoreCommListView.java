package com.ga.erp.biz.store.storecomm;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.util.SystemUtil;

public class StoreCommListView extends ListView {
	
  public StoreCommListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {
	  
    List<DbField> dbFields = new ArrayList<DbField>();
	    
  	DbField field = new DbField("ID","STORE_COMM_ID",GaConstant.DT_LONG); 
  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "STORE_COMM_ID");
  	field.setColumnDisplay(false, 0, true);
  	dbFields.add(field);
  	    
  	field = new DbField("�ŵ�", "STORE_NAME", GaConstant.DT_STRING);
  	field.setQueryOpera(GaConstant.QUERY_LIKE, GaConstant.INPUT_POPSELECT, 0, false, true);
  	field.setPopSelect("STORESELECT","NAME",true,
  	        "/store/store_main.htm",
  	        "STORE_ID,STORE_NAME,cid_storeList",800,400);
  	dbFields.add(field);
  	
  	field = new DbField("��ƷID", "COMMODITY_ID", GaConstant.DT_STRING);
  	field.setColumnDisplay(false, 0, true);
  	dbFields.add(field);
  	
  	field = new DbField("��Ʒ��", "COMMODITY_NAME", GaConstant.DT_STRING);
  	field.setQueryOpera(GaConstant.QUERY_LIKE, GaConstant.INPUT_POPSELECT, 1, false, true);
  	field.setPopSelect("COMMSELECT","COMMODITY_NAME",true,
  	        "/comm/comm_main.htm",
  	        "COMMODITY_ID,COMMODITY_NAME,cid_commList",800,400);
  	dbFields.add(field);
  	
    field = new DbField("���ۼ�", "RETAIL_PRICE", GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 2, true);
    dbFields.add(field);

    field = new DbField("��Ա��", "MEMBER_PRICE", GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 3, true);
    dbFields.add(field);
    
    field = new DbField("ƽ����", "ABERAGE_PRICE", GaConstant.DT_MONEY);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 4, true);
    dbFields.add(field);
    
    field = new DbField("��������", "ALL_AMOUNT", GaConstant.DT_LONG);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_TEXT, 5, true);
    dbFields.add(field);
    
    field = new DbField("�ۼƿ��", "INNAGE_AMOUNT", GaConstant.DT_LONG);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 4, true);
    dbFields.add(field);
   
    field = new DbField("ʣ����", "CNT", GaConstant.DT_LONG);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 4, true);
    dbFields.add(field);
    
    field = new DbField("�������", "UPPER_LIMIT", GaConstant.DT_LONG);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 4, true);
    dbFields.add(field);
    
    field = new DbField("�ۼ�����", "LOWER_LIMIT", GaConstant.DT_LONG);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 4, true);
    dbFields.add(field);
    
    field = new DbField("״̬","STATE",GaConstant.DT_INT);
    field.setAliasCode("sc");
    field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT, 5, true);
    field.setLookupData(SystemUtil.getStatusMap());
    dbFields.add(field);

    this.fieldList = dbFields;
  }
  
}
