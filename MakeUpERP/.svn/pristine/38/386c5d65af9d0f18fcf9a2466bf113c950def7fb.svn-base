package com.ga.erp.biz.purchase.order;

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

public class PurOrderListView extends ListView{

	public PurOrderListView(String viewID, ModelMap modelMap) {
		super(viewID, modelMap,GaConstant.EDITMODE_DISP);
	}
	  @Override
	  public void initField() throws Exception {
		  
	    List<DbField> dbFields = new ArrayList<DbField>();
		    
		  DbField field = new DbField("ID","PURCHASE_ORDER_ID",GaConstant.DT_LONG); 
		  field.setPK(true);
		  field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "PURCHASE_ORDER_ID");
		  field.setColumnDisplay(false, 0, true);
		  dbFields.add(field);
		  
		  field = new DbField("单据编号","PURCHASE_ORDER_NUM",GaConstant.DT_INT);
		  field.setAliasCode("po");
		  field.setQueryOpera(GaConstant.QUERY_LIKE, 1, true);
		  field.setColumnDisplay(false, 0, true);

		  dbFields.add(field);	
		  
		  field = new DbField("供应商","SUPPLIER_NAME",GaConstant.DT_STRING);
		  field.setQueryOpera(GaConstant.QUERY_LIKE, 2, true);
		  field.setColumnDisplay(false, 0, true);

		  dbFields.add(field);	
		  
		  field = new DbField("上级订单编号","P_PURCHASE_ORDER_NUM",GaConstant.DT_INT);
		  field.setAliasCode("ppo.PURCHASE_ORDER_NUM");
		  field.setColumnDisplay(false, 0, true);

		  field.setQueryOpera(GaConstant.QUERY_LIKE, 3, true);

		  dbFields.add(field);	
		  
		  field = new DbField("配送机构","DELIVERY_NAME",GaConstant.DT_STRING);
		  field.setColumnDisplay(true, 120, true);

		  dbFields.add(field);	
		  
		  field = new DbField("结算code","CODE",GaConstant.DT_INT);
		  field.setColumnDisplay(true, 120, true);

		  dbFields.add(field);	
		  
		  field = new DbField("仓库","NAME",GaConstant.DT_STRING);
		  field.setColumnDisplay(true, 120, true);

		  dbFields.add(field);	
		  
		  field = new DbField("送货地址","DILIVERY_ADDR_ID",GaConstant.DT_STRING);
		  field.setColumnDisplay(true, 120, true);

		  dbFields.add(field);	
		  
		  field = new DbField("采购员","TRUENAME",GaConstant.DT_STRING);
		  field.setColumnDisplay(true, 120, true);

		  dbFields.add(field);	
		  
		  field = new DbField("交货期限","DELIVERY_TIME",GaConstant.DT_DATETIME);
		  field.setColumnDisplay(true, 120, true);

		  field.setAliasCode("po");
		  field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd", false));
		  dbFields.add(field);	
		  
		  field = new DbField("订单金额","ORDER_CNT",GaConstant.DT_MONEY);
		  field.setColumnDisplay(true, 120, true);

		  field.setAliasCode("po");
			field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 4, false);
		  dbFields.add(field);	
		  
		  field = new DbField("实际金额","REALITY_PRICE",GaConstant.DT_MONEY);
		  field.setColumnDisplay(true, 120, true);

		  dbFields.add(field);	
		  
		  field = new DbField("商品类数","COMM_SORT_CNT",GaConstant.DT_INT);
		  field.setColumnDisplay(true, 120, true);

		  dbFields.add(field);	
		  
		  field = new DbField("商品总件数","COMM_CNT",GaConstant.DT_INT);
		  field.setColumnDisplay(true, 120, true);

		  dbFields.add(field);	
		  
		  field = new DbField("物流费用","DISTRIBUTE_MONEY",GaConstant.DT_MONEY);
		  field.setColumnDisplay(true, 120, true);

		  field.setAliasCode("po");
		  field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_MONEY,5, true);
		  field.setInputCustomStyle("width:80px;");
		  dbFields.add(field);
		  
		  field = new DbField("物流号","DISTRIBUTE_NUM",GaConstant.DT_INT);
		  field.setColumnDisplay(true, 120, true);

		  dbFields.add(field);	
		  
	    field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
		  field.setColumnDisplay(true, 120, true);

	    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
	    dbFields.add(field); 
		  
		  field = new DbField("差异是否存在","IS_DIFF",GaConstant.DT_INT);
		  field.setColumnDisplay(true, 120, true);

		  field.setAliasCode("po");
		  field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT,6,true);
		  field.setLookupData(SystemUtil.getYesNoMap());
		  field.setAliasCode("po");
		  dbFields.add(field);	
		  
		  field = new DbField("差异是否解决","DIFF_IS_SETTLED",GaConstant.DT_INT);
		  field.setColumnDisplay(true, 120, true);

		  field.setAliasCode("po");
		  field.setLookupData(SystemUtil.getYesNoMap());
		  field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT,7,true);
		  field.setAliasCode("po");
		  dbFields.add(field);	
		  
		  field = new DbField("状态","STATE",GaConstant.DT_INT);
		  field.setColumnDisplay(true, 120, true);

		  field.setAliasCode("po");
		  field.setLookupData(SystemUtil.getStatusMap());
		  field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT,8,true);
		  dbFields.add(field);
		  
		  field = new DbField("单据状态","BILL_STATE",GaConstant.DT_INT);
		  field.setColumnDisplay(true, 120, true);

		  Map<String, Object> lookup = new LinkedHashMap<String, Object>();
      lookup = new LinkedHashMap<String, Object>();
      field.setAliasCode("po");
        lookup.put("1", "待受理（审核通过）");
        lookup.put("2", "待备货");
        lookup.put("3", "发货中");
        lookup.put("4", "已收货（完成）");
      field.setLookupData(new LookupDataSet(lookup));
      field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT,9,true);
      dbFields.add(field);
      
      field = new DbField("审核状态","CHECK_STATE",GaConstant.DT_INT);
      field.setAliasCode("po");
	  field.setColumnDisplay(true, 120, true);

      field.setLookupData(SystemUtil.getAuditsMap());
      field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT,10,true);
      dbFields.add(field);
		  
	  	this.fieldList = dbFields;
	  }
	

}
