package com.ga.erp.biz.store.storealert;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.util.SystemUtil;

public class StoreAlertListView extends ListView {
	
  public StoreAlertListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {

    String type = this.modelMap.getRequest().getParameter("type");
    List<DbField> dbFields = new ArrayList<DbField>();
      
    DbField field = new DbField("ID","STORE_COMM_ID",GaConstant.DT_LONG); 
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "STORE_COMM_ID");
    field.setColumnDisplay(false, 0, true);
    dbFields.add(field);
        
    field = new DbField("�ŵ�ID", "STORE_ID", GaConstant.DT_LONG);
    field.setAliasCode("sc");
    field.setColumnDisplay(false, 0, false);
    field.setIsFilterCode(true);
    dbFields.add(field);
    
    field = new DbField("�ŵ�", "STORE_NAME", GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, GaConstant.INPUT_POPSELECT, 0, false, true);
    field.setPopSelect("STORESELECT"+type,"STORE_NAME",true,"/store/store_main.htm",
          "STORE_ID,STORE_NAME,cid_storeList",800,400);
    dbFields.add(field);
    
    field = new DbField("��Ʒ��", "COMMODITY_NAME", GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, GaConstant.INPUT_POPSELECT, 1, false, true);
    field.setPopSelect("COMMSELECT"+type,"COMMODITY_NAME",true,"/comm/comm_main.htm",
            "COMMODITY_ID,COMMODITY_NAME,cid_commList",800,400);
    dbFields.add(field);
    
    field = new DbField("ʣ����", "INNAGE_BALANCE", GaConstant.DT_LONG);
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 2, true);
    dbFields.add(field);
    
    field = new DbField("�������", "INNAGE_UPPER_LIMIT", GaConstant.DT_LONG);
    dbFields.add(field);
    
    field = new DbField("�������", "INNAGE_LOWER_LIMIT", GaConstant.DT_LONG);
    dbFields.add(field);
    
    field = new DbField("���ۼ�", "RETAIL_PRICE", GaConstant.DT_MONEY);
    dbFields.add(field);

    field = new DbField("��Ա��", "MEMBER_PRICE", GaConstant.DT_MONEY);
    dbFields.add(field);
    
    field = new DbField("ƽ����", "ABERAGE_PRICE", GaConstant.DT_MONEY);
    dbFields.add(field);
    
    field = new DbField("��������", "ALL_AMOUNT", GaConstant.DT_LONG);
    dbFields.add(field);
    
    field = new DbField("�ۼƿ��", "INNAGE_AMOUNT", GaConstant.DT_LONG);
    dbFields.add(field);

    field = new DbField("״̬","STATE",GaConstant.DT_INT);
    field.setAliasCode("sc");
    field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT, 3, true);
    field.setLookupData(SystemUtil.getStatusMap());
    dbFields.add(field);

    this.fieldList = dbFields;
  }
  
}
