/**
 * Copyright (c) 2014 GA
 * All right reserved.
 */
package com.ga.erp.biz.store.pending;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.StringDecorator;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;

/**
 * 类描述
 * @author jzk
 * @create_time 2014-5-1 上午11:30:15
 * @project MakeUpERP
 */
public class PendingFormView extends FormView {

  public PendingFormView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }

  @Override
  public void initField() throws Exception {
    
    List<DbField> dbField = new ArrayList<DbField>();
    
    
/*********************************************提示信息*********************************************/
    DbField field = new DbField("门店库存告警", "STORE_ALERT", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    StringDecorator decorator = new StringDecorator();
    
//    <input type="button" value="查询" class="searchButton" _windownav="navTab,m_11032" _prewindownav="navTab,main" 
//        _actionid="ListForm.qrybutton" \
//        onclick="doFormPost(this,'pagerForm')" url="" id="order_qrybutton" name="ListForm_qrybutton">
    
    decorator.setStringFormat("<a rel='m_431' target='navTab' href='/store/storealert_main.htm?type=2'>{value}条</a>");
    field.setColumnDecorator("", decorator);
    dbField.add(field);
    
    field = new DbField("门店负库存", "STORE_MINUS_ALERT", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_432' target='navTab' href='/store/storealert_main.htm?type=1'>{value}条</a>");
    field.setColumnDecorator("", decorator);
    dbField.add(field);
    
    field = new DbField("仓库库存告警", "STOCK_ALERT", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_821' target='navTab' href='/stock/stockalert_main.htm?type=2'>{value}条</a>");
    field.setColumnDecorator("", decorator);
    dbField.add(field);
    
    field = new DbField("仓库负库存", "STOCK_MINUS_ALERT", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_822' target='navTab' href='/stock/stockalert_main.htm?type=1'>{value}条</a>");
    field.setColumnDecorator("", decorator);
    dbField.add(field);
    
    field = new DbField("店间直调申请单", "STORE_DIRECT_APPLY_ORDER", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_711' target='navTab' href='/store/storeorder_main.htm?type=1'>{value}条</a>");
    field.setColumnDecorator("", decorator);
    dbField.add(field);
    
    field = new DbField("店间直调收货单", "STORE_DIRECT_RECIVE_ORDER", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_731' target='navTab' href='/store/storeorder_main.htm?type=5'>{value}条</a>");
    field.setColumnDecorator("", decorator);
    dbField.add(field);
/*************************************************************************************************/
    
    
    
/*********************************************订单概况*********************************************/
    field = new DbField("已完成(已收货)采购订单数", "ORDER_COMPLETE", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    dbField.add(field); 
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_1411' target='navTab' href='/purchase/purOrder_main.htm?state=4'>{value}条</a>");
    field.setColumnDecorator("", decorator);
    
    field = new DbField("发货中采购订单数", "ORDER_SENDING", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    dbField.add(field);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_1411' target='navTab' href='/purchase/purOrder_main.htm?state=3'>{value}条</a>");
    field.setColumnDecorator("", decorator);
    
    field = new DbField("待备货采购订单数", "ORDER_PENDING_STOCK", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    dbField.add(field);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_1411' target='navTab' href='/purchase/purOrder_main.htm?state=2'>{value}条</a>");
    field.setColumnDecorator("", decorator);
    
    field = new DbField("待受理(审核通过)采购订单数", "ORDER_PENDING_ACCEPT", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    dbField.add(field);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_1411' target='navTab' href='/purchase/purOrder_main.htm?state=1'>{value}条</a>");
    field.setColumnDecorator("", decorator);
    
    field = new DbField("已废止采购订单数", "ORDER_CANCEL", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    dbField.add(field);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_1411' target='navTab' href='/purchase/purOrder_main.htm?state=0'>{value}条</a>");
    field.setColumnDecorator("", decorator);
    
    field = new DbField("待审核采购订单数", "ORDER_WAIT_AUDIT", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    dbField.add(field);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_1411' target='navTab' href='/purchase/purOrder_main.htm?state=-1'>{value}条</a>");
    field.setColumnDecorator("", decorator);
    
    field = new DbField("审核不通过采购订单数", "ORDER_AUDIT_NO_PASS", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    dbField.add(field);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_1411' target='navTab' href='/purchase/purOrder_main.htm?state=-2'>{value}条</a>");
    field.setColumnDecorator("", decorator);
    
    field = new DbField("拒绝受理采购订单数", "ORDER_REFUSE_ACCEPT", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    dbField.add(field);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_1411' target='navTab' href='/purchase/purOrder_main.htm?state=-3'>{value}条</a>");
    field.setColumnDecorator("", decorator);
    
    field = new DbField("今日采购订单数", "ORDER_TODAY", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    dbField.add(field);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_1411' target='navTab' href='/purchase/purOrder_main.htm?state=5'>{value}条</a>");
    field.setColumnDecorator("", decorator);
/*************************************************************************************************/   
    
    
/**********************************************待处理事务***************************************************/ 
    field = new DbField("待处理退货申请", "COMMODITY_RETURN_APPLY", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    dbField.add(field);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_422' target='navTab' href='/store/returnOrder_main.htm?state=1'>{value}条</a>");
    field.setColumnDecorator("", decorator);
    
    field = new DbField("待审核事务", "WAIT_AUDIT_BUSINESS", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    dbField.add(field);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_12021' target='navTab' href='/system/audits_main.htm?state=0'>{value}条</a>");
    field.setColumnDecorator("", decorator);
/*************************************************************************************************/
    
    
    this.fieldList = dbField;
  
  }
}