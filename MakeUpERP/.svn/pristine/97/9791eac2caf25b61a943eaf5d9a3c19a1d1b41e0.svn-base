package com.ga.erp.biz.store.storeorder;

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

public class StoreOrderFormView extends FormView {

  public StoreOrderFormView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }

  @Override
  public void initField() throws Exception {

    List<DbField> dbFields = new ArrayList<DbField>();

    DbField field = new DbField("ID","STORE_ORDER_ID",GaConstant.DT_LONG); 
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "STORE_ORDER_ID");
    field.setInput(GaConstant.INPUT_HIDDEN);
    dbFields.add(field);
        
    field = new DbField("门店ID", "STORE_ID", GaConstant.DT_LONG);
    field.setPopSelect("STORESELECT", "STORE_ID", false);
    dbFields.add(field);

    field = new DbField("申请店铺", "STORE_NAME", GaConstant.DT_STRING);
    if (this.viewEditMode == GaConstant.EDITMODE_NEW) {
      field.setPopSelect("STORESELECT", "STORE_NAME", true,
          "/store/store_main.htm",
          "STORE_ID,STORE_NAME,cid_storeList", 1000, 480);
    } 
    dbFields.add(field);
    
    field = new DbField("发货店铺", "SEND_STORE_ID", GaConstant.DT_LONG);
    field.setPopSelect("SEND_STORESELECT", "STORE_ID", false);
    dbFields.add(field);
    
    field = new DbField("发货店铺", "SEND_STORE_NAME", GaConstant.DT_STRING);
    if (this.viewEditMode == GaConstant.EDITMODE_NEW) {
      field.setPopSelect("SEND_STORESELECT", "STORE_NAME", true,
          "/store/store_main.htm",
          "STORE_ID,STORE_NAME,cid_storeList", 1000, 480);
    } 
    dbFields.add(field);
    
    field = new DbField("类型","TYPE",GaConstant.DT_INT);
    field.setInput(GaConstant.INPUT_HIDDEN);
    dbFields.add(field);

    field = new DbField("订单号","STORE_ORDER_NUM",GaConstant.DT_STRING);
    field.getFieldVerify().setRequire(true);
    dbFields.add(field);
    
   /* field = new DbField("关联订单ID", "P_STORE_ORDER_ID", GaConstant.DT_LONG);
    field.setPopSelect("PORDERSELECT", "STORE_ORDER_ID", false);
    dbFields.add(field);

    field = new DbField("关联订单号", "P_STORE_ORDER_NUM", GaConstant.DT_STRING);
    if (this.viewEditMode == GaConstant.EDITMODE_NEW) {
      field.setPopSelect("PORDERSELECT", "STORE_ORDER_NUM", true,
          "/store/storeorder_main.htm",
          "STORE_ORDER_ID,STORE_ORDER_NUM,cid_storeOrderList", 1000, 480);
    } */
    dbFields.add(field);
    
    if(this.viewEditMode == GaConstant.EDITMODE_EDIT){
      field = new DbField("结算流水号","CODE",GaConstant.DT_STRING);
      dbFields.add(field);

      field = new DbField("订单金额","ORDER_MONEY",GaConstant.DT_MONEY);
      field.setInputCustomStyle("width:80px;");
      dbFields.add(field);
      
      field = new DbField("实际金额","ACTURE_MONEY",GaConstant.DT_MONEY);
      field.setInputCustomStyle("width:80px;");
      dbFields.add(field);
    }
    
    field = new DbField("配送方式","DELIVERY_TYPE",GaConstant.DT_INT);
    field.setInput(GaConstant.INPUT_SELECT);
    Map<String, Object> option = new LinkedHashMap<String, Object>();
    option.put("1", "正常配送");
    option.put("2", "货到齐后统一配送");
    option.put("3", "分单配送");
    field.setLookupData(new LookupDataSet(option));
    field.setVerifyFormula("", true);
    dbFields.add(field);
    
    field = new DbField("配送单号","DISTRIBUTE_NUM",GaConstant.DT_STRING);
    dbFields.add(field);
    
    field = new DbField("配送费用","DISTRIBUTE_MONEY",GaConstant.DT_MONEY);
    dbFields.add(field);
    
    field = new DbField("预计配送时间","PREDICT_DILIVERY_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    field.getFieldVerify().setRequire(true);
    dbFields.add(field);
    
    field = new DbField("收货时间","RECEIVE_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
    dbFields.add(field);
    
    dbFields.addAll(SystemUtil.getNormalFieldList());
    
    this.fieldList = dbFields;
  }

}
