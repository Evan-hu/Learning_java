package com.ga.erp.biz.member;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.control.StringDecorator;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class MemberListView extends ListView {
	
  public MemberListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }

  @Override
  public void initField() throws Exception {
	  
  	List<DbField> dbFields = new ArrayList<DbField>();
  	    
  	DbField field = new DbField("ID","MEMBER_ID",GaConstant.DT_LONG); 
  	field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "MEMBER_ID");
  	field.setColumnDisplay(false, 0, true);
  	dbFields.add(field);
  	    
  	field = new DbField("会员编号", "MEMBER_NUM", GaConstant.DT_STRING);
  	ActionButton action = new ActionButton(this.getClass(),"MEMBER_ID","{value}","/member/member_detail.htm",null);
    SubmitTool.submitToDialog(action, "MEMBER_ID","会员详情", 800, 450, this.modelMap.getPreNavInfoStr());
    StringDecorator decorator = new StringDecorator();
    decorator.setLinkDecorator(action);
    decorator.setStringFormat("<font color='red'>{value}</font>");
    action.bindTableRowData(this.viewID);
    action.addParam(GaConstant.FIXPARAM_EDITMODE,GaConstant.EDITMODE_EDIT);
    field.setColumnDecorator("", decorator);
  	field.setQueryOpera(GaConstant.QUERY_LIKE, 0, false);
  	field.setColumnDisplay(true, 100, false);
  	dbFields.add(field);
  	 
  	field = new DbField("会员等级", "VIP_LV", GaConstant.DT_INT);
  	dbFields.add(field);
  	
    field = new DbField("真实姓名", "TRUENAME", GaConstant.DT_STRING);
    field.setColumnDisplay(true, 100, false);
    dbFields.add(field);
    
  	field = new DbField("性别", "SEX", GaConstant.DT_INT);
  	Map<String, Object> option = new LinkedHashMap<String, Object>();
  	option.put("1", "男");
  	option.put("2", "女");
  	field.setLookupData(new LookupDataSet(option));
  	field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT, 2, false);
  	dbFields.add(field);
      
  	field = new DbField("邮箱", "EMAIL", GaConstant.DT_STRING);
  	dbFields.add(field);
	
  	field = new DbField("累计消费","ALL_AMOUNT",GaConstant.DT_MONEY);
  	field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 3, false);
  	field.setInputCustomStyle("width:50px;");
  	dbFields.add(field);
  	
  	field = new DbField("累计积分","SCORE_AMOUNT",GaConstant.DT_MONEY);
  	field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 4, false);
  	field.setInputCustomStyle("width:50px;");
  	dbFields.add(field);
  	
  	field = new DbField("剩余积分","SCORE_BALANCE",GaConstant.DT_MONEY);
  	field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_MONEY, 5, false);
  	field.setInputCustomStyle("width:50px;");
  	dbFields.add(field);
	
  	field = new DbField("最后消费时间","LAST_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_DATETIME, 6, false);
    dbFields.add(field);
    
  	field = new DbField("最后消费门店","LAST_STORE_NAME",GaConstant.DT_STRING);
  	dbFields.add(field);
  	
	  field = new DbField("注册时间","REG_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_DATETIME, 7, false);
    dbFields.add(field);
    
    field = new DbField("门店ID", "STORE_ID", GaConstant.DT_LONG);
    field.setAliasCode("m");
    field.setColumnDisplay(false, 0, false);
    field.setIsFilterCode(true);
    dbFields.add(field);
    
    field = new DbField("注册门店", "STORE_NAME", GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, GaConstant.INPUT_POPSELECT, 1, false, true);
    field.setPopSelect("STORESELECT","STORE_NAME",true,
        "/store/store_main.htm",
        "STORE_ID,STORE_NAME,cid_storeList",800,450);
    field.setInputCustomStyle("width:120px;");
    field.setColumnDisplay(true, 80, false);
    dbFields.add(field);
    
    field = new DbField("状态","STATE",GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT, 8, true);
    field.setLookupData(SystemUtil.getStatusMap());
    dbFields.add(field);

    field = new DbField("删除状态", "DELETE_STATE", GaConstant.DT_LONG);
    field.setColumnDisplay(false,0,true);   
    field.setIsFilterCode(true);
    dbFields.add(field);
    
    this.fieldList = dbFields;
  }
  
}
