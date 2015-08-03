package com.ga.erp.biz.activity.regactivity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class RegActivityFormView extends FormView {

  public RegActivityFormView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }

  @Override
  public void initField() throws Exception {

    List<DbField> dbFields = new ArrayList<DbField>();

    DbField field = new DbField("ID", "REG_ACTIVITY_ID", GaConstant.DT_LONG);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "REG_ACTIVITY_ID");
    field.setInput(GaConstant.INPUT_HIDDEN);
    dbFields.add(field);
    
    field = new DbField("活动形式", "TYPE", GaConstant.DT_INT);
    field.setInput(GaConstant.INPUT_SELECT);
    Map<String,Object> cycle = new LinkedHashMap<String, Object>();
    cycle.put("1", "赠送积分");
    cycle.put("2", "赠送礼券");
    field.setLookupData(new LookupDataSet(cycle));
    field.setVerifyFormula("", true);
    field.addInputAttributeMap("onChange","changePopUrl('inventory',this.value);");
    dbFields.add(field);
    
    field = new DbField("礼券ID","OBJECT_ID", GaConstant.DT_LONG);
    field.setInput(GaConstant.INPUT_HIDDEN);
    field.setPopSelect("selectCardBatch", "CARD_BATCH_ID", false);
    dbFields.add(field);
       
    field = new DbField("礼券名称<font color='red'>*</font>","NAME",GaConstant.DT_STRING);
    field.setPopSelect("selectCardBatch","NAME",true, "/activity/cardbatch_main.htm", "CARD_BATCH_ID,NAME,cid_batchListView",1000,300);
    dbFields.add(field);
    
    field = new DbField("赠送值", "GIVE_VALUE", GaConstant.DT_LONG);
    dbFields.add(field);
    
    field = new DbField("活动开始时间", "BEG_TIME", GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
//    field.setInputCustomStyle("width:80px;");
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, 5, false);
    dbFields.add(field);
       
    field = new DbField("活动结束时间", "END_TIME", GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
//    field.setInputCustomStyle("width:80px;");
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, 6, false);
    dbFields.add(field);
    
    field = new DbField("累计参与会员", "ALL_MEMBER_CNT", GaConstant.DT_LONG);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
    dbFields.add(field);
      
    field = new DbField("累计参与门店", "ALL_STORE_CNT", GaConstant.DT_LONG);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
    dbFields.add(field);
    
    field = new DbField("累计销售金额", "ALL_SELL_CNT", GaConstant.DT_MONEY);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
    dbFields.add(field);
    
    field = new DbField("累计优惠金额", "ALL_SALE_CNT", GaConstant.DT_MONEY);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
    dbFields.add(field);
    
    field = new DbField("累计发放值", "ALL_SEND_VALUE", GaConstant.DT_INT);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
    dbFields.add(field);
    
    field = new DbField("累计发放数", "ALL_SEND_CNT", GaConstant.DT_INT);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
    dbFields.add(field);
    
    field = new DbField("审核状态", "CHECK_STATE", GaConstant.DT_INT);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
    cycle = new LinkedHashMap<String, Object>();
    cycle.put("-1", "审核不通过");
    cycle.put("0", "待审核");
    cycle.put("1", "审核通过");
    field.setLookupData(new LookupDataSet(cycle));
    dbFields.add(field);
    
    field = new DbField("创建时间", "CREATE_TIME", GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
    dbFields.add(field);
    
    field = new DbField("状态","STATE",GaConstant.DT_INT);
    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
    field.setInput(GaConstant.INPUT_SELECT);
    field.setLookupData(SystemUtil.getStatusMap());
    field.setVerifyFormula("", true);
    dbFields.add(field);

    this.fieldList = dbFields;
  }

}
