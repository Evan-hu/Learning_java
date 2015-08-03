package com.ga.erp.biz.system.deliveryorg;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.util.SystemUtil;

public class DeliveryListView extends ListView {
	
  public DeliveryListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {
	  
	List<DbField> dbFields = new ArrayList<DbField>();
	
	DbField field = new DbField("ID","DELIVERY_ORG_ID",GaConstant.DT_LONG); 
	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "DELIVERY_ORG_ID");
	field.setColumnDisplay(false, 0, true);
	dbFields.add(field);
	    
	field = new DbField("名称", "DELIVERY_NAME", GaConstant.DT_STRING);
	field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
	dbFields.add(field);
	
	field = new DbField("公司编码", "DELIVERY_CODE", GaConstant.DT_STRING);
	field.setQueryOpera(GaConstant.QUERY_LIKE, 2, false);
	dbFields.add(field);
	
	field = new DbField("运费", "PRICE", GaConstant.DT_MONEY);
	field.setQueryOpera(GaConstant.QUERY_LIKE, 1, false);
	dbFields.add(field);
	
	field = new DbField("货物在途时间", "DELIVERY_TIME", GaConstant.DT_STRING);
	dbFields.add(field);
	
	field = new DbField("状态","STATE",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT, 3, true);
    field.setLookupData(SystemUtil.getStatusMap());
    dbFields.add(field);

    this.fieldList = dbFields;
  }
  
}
