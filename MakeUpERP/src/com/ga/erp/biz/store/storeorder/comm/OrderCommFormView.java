package com.ga.erp.biz.store.storeorder.comm;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.util.SystemUtil;

public class OrderCommFormView extends FormView {

  public OrderCommFormView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }

  @Override
  public void initField() throws Exception {

    List<DbField> dbFields = new ArrayList<DbField>();

    DbField field = new DbField("ID","STORE_ORDER_COMM_ID",GaConstant.DT_LONG); 
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "STORE_ORDER_COMM_ID");
    field.setInput(GaConstant.INPUT_HIDDEN);
    dbFields.add(field);
        
    field = new DbField("门店ID", "STORE_ORDER_ID", GaConstant.DT_LONG);
    field.setInput(GaConstant.INPUT_HIDDEN);
    dbFields.add(field);
    
    field = new DbField("门店ID", "STORE_ID", GaConstant.DT_LONG);
    field.setPopSelect("STORESELECT", "STORE_ID", false);
    dbFields.add(field);

    field = new DbField("门店名", "STORE_NAME", GaConstant.DT_STRING);
    if (this.viewEditMode == GaConstant.EDITMODE_NEW) {
      field.setPopSelect("STORESELECT", "STORE_NAME", true,
          "/store/store_main.htm",
          "STORE_ID,STORE_NAME,cid_storeList", 1000, 480);
    } 
    dbFields.add(field);
    
    field = new DbField("商品ID", "COMMODITY_ID", GaConstant.DT_LONG);
    field.setPopSelect("COMMSELECT", "COMMODITY_ID", false);
    dbFields.add(field);
    
    field = new DbField("商品名称", "COMMODITY_NAME", GaConstant.DT_STRING);
    if(this.viewEditMode == GaConstant.EDITMODE_NEW){
      field.setPopSelect("COMMSELECT", "COMMODITY_NAME", true,
          "/comm/comm_main.htm",
          "COMMODITY_ID,COMMODITY_NAME,SEND_PRICE,cid_commList", 1000, 480);
    }
    dbFields.add(field);
    
    field = new DbField("配送价", "DISTRIBUTE_PRICE", GaConstant.DT_MONEY);
    field.setInputCustomStyle("width:100px;");
    if(this.viewEditMode == GaConstant.EDITMODE_NEW){
      field.setPopSelect("COMMSELECT", "SEND_PRICE", true);
    }
    dbFields.add(field);
    
    field = new DbField("申请数量", "ORDER_CNT", GaConstant.DT_INT);
    field.setInputCustomStyle("width:80px;");
    field.setDefaultValue(1);
    dbFields.add(field);

    field = new DbField("实际数量", "ACTUAL_CNT", GaConstant.DT_INT);
    field.setInputCustomStyle("width:80px;");
    field.setDefaultValue(1);
    dbFields.add(field);
    
    field = new DbField("赠送数量", "SEND_CNT", GaConstant.DT_INT);
    field.setInputCustomStyle("width:80px;");
    field.setDefaultValue(0);
    dbFields.add(field);
    
    field = new DbField("差异原因", "DIFF_REASON", GaConstant.DT_STRING);
    field.setInput(GaConstant.INPUT_TEXTAREA);
    field.addInputAttributeMap("rows", "3");
    field.addInputAttributeMap("cols", "25");
    dbFields.add(field);
    
    field = new DbField("备注", "NOTE", GaConstant.DT_STRING);
    field.setInput(GaConstant.INPUT_TEXTAREA);
    field.addInputAttributeMap("rows", "3");
    field.addInputAttributeMap("cols", "25");
    dbFields.add(field);
    
    field = new DbField("状态", "STATE", GaConstant.DT_INT);
    field.setInput(GaConstant.INPUT_SELECT);
    field.setLookupData(SystemUtil.getStatusMap());
    field.setVerifyFormula("", true);
    dbFields.add(field);
    
    this.fieldList = dbFields;
  }

}
