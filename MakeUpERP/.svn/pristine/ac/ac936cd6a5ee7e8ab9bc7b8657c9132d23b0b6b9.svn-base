/**
 * Copyright (c) 2011 Noah
 * All right reserved.
 */
package com.ga.erp.biz.activity.activity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.util.SystemUtil;

/**
 * 类描述
 * @author SPE
 * @create_time 2012-7-5 下午04:18:24
 * @project ShopMgr
 */
public class ActivityParamEditView extends FormView {

  public ActivityParamEditView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }
  @Override
  public void initField() throws Exception {
	  List<DbField> dbField = new ArrayList<DbField>();
    DbField field = new DbField("参数名称", "PARAM_NAME", GaConstant.DT_STRING);
    field.setMaxLength(10);
    dbField.add(field);
    
    field = new DbField("参数ID", "DISCOUNT_PARAM_ID", GaConstant.DT_LONG);
    field.setQueryParamFormSource(GaConstant.INPUT_REQUEST, "DISCOUNT_PARAM_ID");
    field.setPK(true);
    field.setBindRowData(true);
    field.setInput(GaConstant.INPUT_HIDDEN);
    dbField.add(field);

    field = new DbField("活动ID", "DISCOUNT_ACTIVITY_ID", GaConstant.DT_LONG);
    field.setInput(GaConstant.INPUT_HIDDEN);
    dbField.add(field);
    
    field = new DbField("范围类型", "COMMODITY_RANGE_TYPE", GaConstant.DT_LONG);
    field.setInput(GaConstant.INPUT_HIDDEN);
    dbField.add(field);
    
    
    field = new DbField("优惠方式", "DISCOUNT_TYPE", GaConstant.DT_LONG);
    field.setColumnDisplay(true, 80, true);
    field.setInput(GaConstant.INPUT_SELECT);
    Map<String, Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("1", "满金额加钱赠送商品");
    lookup.put("2", "满金额减免固定金额");
    lookup.put("3", "指定商品特价");
    lookup.put("4", "满金额打折");
    lookup.put("5", "满数量打折");
    lookup.put("6", "赠送优惠券");
    lookup.put("7", "组合商品");
    field.setLookupData(new LookupDataSet(lookup));
    dbField.add(field);

    field = new DbField("状态", "STATE", GaConstant.DT_LONG);
    field.setInput(GaConstant.INPUT_SELECT);
    field.setVerifyFormula("", true);
    field.setLookupData(SystemUtil.getStatusMap());
    dbField.add(field);
    
    field = new DbField("目标ID", "TARGET_ID", GaConstant.DT_LONG);
    field.setInput(GaConstant.INPUT_HIDDEN);
    dbField.add(field);
    
    field = new DbField("目标名称", "TARGET_NAME", GaConstant.DT_STRING);
    dbField.add(field);
    
    field = new DbField("优惠值", "VALUE", GaConstant.DT_MONEY);
    dbField.add(field);
    
    field = new DbField("附加金额", "ADD_MONEY", GaConstant.DT_MONEY);
    dbField.add(field);
    
    field = new DbField("MIN_MONEY", "MIN_MONEY", GaConstant.DT_MONEY);
    dbField.add(field);
    
    field = new DbField("MIN_COUNT", "MIN_COUNT", GaConstant.DT_MONEY);
    dbField.add(field);
    
    field = new DbField("附加积分", "ADD_SCORE", GaConstant.DT_MONEY);
    dbField.add(field);
    
    field = new DbField("商品折扣件数", "COMMODITY_AMOUNT", GaConstant.DT_LONG);
    dbField.add(field);
    
    field = new DbField("商品名称", "selectCommodity.COMMODITY_NAME", GaConstant.DT_STRING);
    dbField.add(field);
    
    field = new DbField("商品ID", "selectCommodity.COMMODITY_ID", GaConstant.DT_LONG);
    dbField.add(field);
    
    field = new DbField("电子券ID", "selectCardBatch.CARD_BATCH_ID", GaConstant.DT_LONG);
    dbField.add(field);
    field = new DbField("电子券名称", "selectCardBatch.NAME", GaConstant.DT_STRING);
    dbField.add(field);
    
    field = new DbField("分类名称", "NEWSORTSELECT.SORT_NAME", GaConstant.DT_STRING);
    dbField.add(field);
    
    field = new DbField("分类ID", "NEWBRANDSELECT.SORT_ID", GaConstant.DT_LONG);
    dbField.add(field);
    
    field = new DbField("品牌名称", "NEWBRANDSELECT.NAME", GaConstant.DT_STRING);
    dbField.add(field);
    
    field = new DbField("品牌ID", "NEWBRANDSELECT.BRAND_ID", GaConstant.DT_LONG);
    dbField.add(field);
    
    field = new DbField("活动类型", "TYPE", GaConstant.DT_LONG);
    dbField.add(field);
    
    
    field = new DbField("备注", "NOTE", GaConstant.DT_STRING);
    field.setInput(GaConstant.INPUT_TEXTAREA);
    field.setInputCustomStyle("width:590px;");
    dbField.add(field);
    
    
    this.fieldList = dbField;
  }
}
