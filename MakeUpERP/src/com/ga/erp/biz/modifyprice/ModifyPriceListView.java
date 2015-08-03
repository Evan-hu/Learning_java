package com.ga.erp.biz.modifyprice;

import java.util.ArrayList;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class ModifyPriceListView extends ListView{


	  /**
   * @param viewID
   * @param modelMap
   */
  public ModifyPriceListView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }

	@Override
	  public void initField() throws Exception {
		  
	     List<DbField> dbFields = new ArrayList<DbField>();
		    
		   DbField field = new DbField("ID","MODIFY_PRICE_ID",GaConstant.DT_LONG); 
		   field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "MODIFY_PRICE_ID");
		   field.setColumnDisplay(false, 0, true);
		   field.setPK(true);
		   dbFields.add(field);
		   
		   field = new DbField("单据编码","BILL_NUM",GaConstant.DT_STRING);
		   field.setQueryOpera(GaConstant.QUERY_LIKE, 1, true);
		   field.setInputCustomStyle("width:80px;");
		   dbFields.add(field);
		   
		   field = new DbField("对象ID","OBJECT_ID",GaConstant.DT_STRING);
		   field.setColumnDisplay(false, 0, true);
       dbFields.add(field);
		   
		   field = new DbField("对象名称","OBJECT_NAME",GaConstant.DT_STRING);
		   field.setQueryOpera(GaConstant.QUERY_LIKE, 2, true);
       dbFields.add(field);
       
       field = new DbField("创建人","TRUENAME",GaConstant.DT_STRING);
       dbFields.add(field);
       
       field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
       field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
       field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_DATETIME, 3, true);
       dbFields.add(field);
 
       field = new DbField("状态","STATE",GaConstant.DT_INT);
       field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT, 4,true);
       field.setLookupData(SystemUtil.getStatusMap());
       dbFields.add(field);
		   
		   this.fieldList = dbFields;
	  }
}
