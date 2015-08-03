package com.ga.erp.biz.activity.regactivity;

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

public class RegActivityListView extends ListView {
	
  public RegActivityListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {
	  
	 List<DbField> dbFields = new ArrayList<DbField>();
	    
	 DbField field = new DbField("ID", "REG_ACTIVITY_ID",GaConstant.DT_LONG );
	 field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "REG_ACTIVITY_ID");
	 field.setColumnDisplay(false, 0, true);  
	 field.setPK(true);
	 dbFields.add(field);
	 
	 field = new DbField("创建人", "TRUENAME", GaConstant.DT_STRING);
	 field.setQueryOpera(GaConstant.QUERY_LIKE, 1, false);
	 dbFields.add(field);
	    
	 field = new DbField("活动形式", "TYPE", GaConstant.DT_INT);
	 Map<String,Object> cycle = new LinkedHashMap<String, Object>();
   cycle.put("1", "赠送积分");
   cycle.put("2", "赠送礼券");
   field.setLookupData(new LookupDataSet(cycle));
   field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT,2,false);
   field.setAliasCode("ra");
	 dbFields.add(field);
	    
	 field = new DbField("赠送值", "GIVE_VALUE", GaConstant.DT_LONG);
	 field.setColumnDisplay(true, 0, false);
	 dbFields.add(field);
	 
	 field = new DbField("礼券批次", "NAME", GaConstant.DT_STRING);
   field.setColumnDisplay(true, 0, false);
   dbFields.add(field);
	    
   field = new DbField("活动开始时间", "BEG_TIME", GaConstant.DT_DATETIME);
   field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
   field.setInputCustomStyle("width:80px;");
   field.setQueryOpera(GaConstant.QUERY_BETWEEN, 5, false);
   dbFields.add(field);
	    
   field = new DbField("活动结束时间", "END_TIME", GaConstant.DT_DATETIME);
   field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
   field.setInputCustomStyle("width:80px;");
   field.setQueryOpera(GaConstant.QUERY_BETWEEN, 6, false);
   field.setAliasCode("ra");
   dbFields.add(field);
   
	 field = new DbField("累计参与会员", "ALL_MEMBER_CNT", GaConstant.DT_LONG);
//	 field.setQueryOpera(GaConstant.QUERY_LIKE, 7, false);
	 dbFields.add(field);
	   
   field = new DbField("累计参与门店", "ALL_STORE_CNT", GaConstant.DT_LONG);
//   field.setQueryOpera(GaConstant.QUERY_LIKE, 8, false);
   dbFields.add(field);
   
   field = new DbField("累计销售金额", "ALL_SELL_CNT", GaConstant.DT_MONEY);
   field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 9, false);
   field.setInputCustomStyle("width:80px;");
   dbFields.add(field);
	 
   field = new DbField("累计优惠金额", "ALL_SALE_CNT", GaConstant.DT_MONEY);
//   field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 10, false);
//   field.setInputCustomStyle("width:80px;");
   dbFields.add(field);
   
   field = new DbField("累计发放值", "ALL_SEND_VALUE", GaConstant.DT_INT);
//   field.setQueryOpera(GaConstant.QUERY_LIKE, 11, false);
   dbFields.add(field);
   
   field = new DbField("累计发放数", "ALL_SEND_CNT", GaConstant.DT_INT);
//   field.setQueryOpera(GaConstant.QUERY_LIKE, 12, false);
   dbFields.add(field);
   
   field = new DbField("审核状态", "CHECK_STATE", GaConstant.DT_INT);
   field.setQueryOpera(GaConstant.QUERY_LIKE, 13, false);
   cycle = new LinkedHashMap<String, Object>();
   cycle.put("-1", "审核不通过");
   cycle.put("0", "待审核");
   cycle.put("1", "审核通过");
   field.setLookupData(new LookupDataSet(cycle));
   field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT,2,false);
   dbFields.add(field);
   
   field = new DbField("创建时间", "CREATE_TIME", GaConstant.DT_DATETIME);
   field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
//   field.setInputCustomStyle("width:80px;");  
   field.setQueryOpera(GaConstant.QUERY_BETWEEN, 14, false);
   field.setAliasCode("ra");
   dbFields.add(field);
   
	 field = new DbField("状态","STATE",GaConstant.DT_INT);
	 field.setLookupData(SystemUtil.getStatusMap());
	 field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 15,false);
	 dbFields.add(field);
	 
    this.fieldList = dbFields;
  }
  
}
