package com.ga.erp.biz.stock.inventorybatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class BatchListView extends ListView{


	  public BatchListView(String viewID, ModelMap modelMap) {
	    
	    super(viewID, modelMap,GaConstant.EDITMODE_DISP);
	}

	@Override
	  public void initField() throws Exception {
		  
	       List<DbField> dbFields = new ArrayList<DbField>();
		    
		   DbField field = new DbField("ID","INVENTORY_BATCH_ID",GaConstant.DT_LONG); 
		   field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "INVENTORY_BATCH_ID");
		   field.setColumnDisplay(false, 0, true);
		   dbFields.add(field);
		   
		   field = new DbField("盘点批次编码","INVENTORY_BATCH_NUM",GaConstant.DT_STRING);
		   field.setQueryOpera(GaConstant.QUERY_LIKE, 1, true);
		   field.setInputCustomStyle("width:80px;");
		   dbFields.add(field);
		  	
//		   field = new DbField("盘点类型","TYPE",GaConstant.DT_INT);
//		   field.setAliasCode("i");
//		   field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 2, true);
//		   Map<String, Object> map=new HashMap<String,Object>();
//		   map.put("1", "仓库");map.put("2", "门店");
//		   field.setLookupData(new LookupDataSet(map));
//		   field.setInputCustomStyle("width:80px;");
//		   dbFields.add(field);

		   field = new DbField("盘点对象名","TYPE_NAME",GaConstant.DT_STRING);
		   dbFields.add(field);
		   
		   field = new DbField("盘点范围","SCOPE",GaConstant.DT_INT);
		   Map<String, Object> map=new HashMap<String,Object>();
		   map.put("1", "全场盘点");
		   map.put("2", "单品盘点");
		   map.put("3", "分类盘点");
		   map.put("4", "品牌盘点");
		   field.setLookupData(new LookupDataSet(map));
		   field.setInputCustomStyle("width:80px;");
		   dbFields.add(field);
		   
		   field = new DbField("创建人","TRUENAME",GaConstant.DT_STRING);
		   dbFields.add(field);
		   
		   field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
		   field.setAliasCode("i");
		   field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
		   field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_DATETIME, 3, true);
		   dbFields.add(field);
			
		   field = new DbField("状态","STATE",GaConstant.DT_INT);
		 //设置查询列别名
		   field.setAliasCode("i");
		   field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT, 4,true);
			//设置select数据
		   field.setLookupData(SystemUtil.getStatusMap());
		   dbFields.add(field);
		   
	
		   field = new DbField("删除状态", "DELETE_STATE", GaConstant.DT_LONG);
		    field.setColumnDisplay(false,0,true);   
		    field.setIsFilterCode(true);
		    dbFields.add(field);
		  	  
		   this.fieldList = dbFields;
	  }
	

}
