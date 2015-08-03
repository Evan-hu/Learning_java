package com.ga.erp.biz.stock.innageadjust;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class AdjustListView extends ListView{

	public AdjustListView(String viewID, ModelMap modelMap) {
		
		super(viewID, modelMap,GaConstant.EDITMODE_DISP);
	}
	  @Override
	  public void initField() throws Exception {
		  
	       List<DbField> dbFields = new ArrayList<DbField>();
		    
		  	DbField field = new DbField("ID","INNAGE_ADJUST_ID",GaConstant.DT_LONG); 
		  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "INNAGE_ADJUST_ID");
		  	field.setColumnDisplay(false, 0, true);
		  	dbFields.add(field);
		  	
			field = new DbField("仓库名","NAME",GaConstant.DT_STRING);
			field.setQueryOpera(GaConstant.QUERY_LIKE, 1, true);
			field.setInputCustomStyle("width:80px;");
		  	dbFields.add(field);
		  	
			field = new DbField("单据ID","BILLS_ID",GaConstant.DT_INT);
			field.setQueryOpera(GaConstant.QUERY_EQUALS, 2, true);
			field.setInputCustomStyle("width:80px;");
		  	dbFields.add(field);
		  	
			field = new DbField("单据编码","BILLS_NUM",GaConstant.DT_INT);
			field.setQueryOpera(GaConstant.QUERY_LIKE, 3, true);
			field.setInputCustomStyle("width:80px;");
			dbFields.add(field);
			
			field = new DbField("方式","WAY",GaConstant.DT_INT);
			field.setQueryOpera(GaConstant.QUERY_LIKE, 4, true);
			field.setInputCustomStyle("width:80px;");
			dbFields.add(field);
			
			field = new DbField("原因","REASON",GaConstant.DT_STRING);
			field.setQueryOpera(GaConstant.QUERY_LIKE, 5, true);
			field.setInputCustomStyle("width:80px;");
			dbFields.add(field);
			
			field = new DbField("经手人","TRUENAME",GaConstant.DT_STRING);
			field.setQueryOpera(GaConstant.QUERY_LIKE, 6, true);
			field.setInputCustomStyle("width:80px;");
			dbFields.add(field);
			   
			field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
			field.setAliasCode("i");
			field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
			field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_DATETIME, 7, true);
			dbFields.add(field);
				
			field = new DbField("状态","STATE",GaConstant.DT_INT);
			 //设置查询列别名
			field.setAliasCode("i");
			field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT, 8,true);
				//设置select数据
			field.setLookupData(SystemUtil.getStatusMap());
			dbFields.add(field);
			   
			this.fieldList = dbFields;
	  }
	

}
