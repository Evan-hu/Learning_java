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
 * ������
 * @author jzk
 * @create_time 2014-5-1 ����11:30:15
 * @project MakeUpERP
 */
public class PendingFormView extends FormView {

  public PendingFormView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }

  @Override
  public void initField() throws Exception {
    
    List<DbField> dbField = new ArrayList<DbField>();
    
    
/*********************************************��ʾ��Ϣ*********************************************/
    DbField field = new DbField("�ŵ���澯", "STORE_ALERT", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    StringDecorator decorator = new StringDecorator();
    
//    <input type="button" value="��ѯ" class="searchButton" _windownav="navTab,m_11032" _prewindownav="navTab,main" 
//        _actionid="ListForm.qrybutton" \
//        onclick="doFormPost(this,'pagerForm')" url="" id="order_qrybutton" name="ListForm_qrybutton">
    
    decorator.setStringFormat("<a rel='m_431' target='navTab' href='/store/storealert_main.htm?type=2'>{value}��</a>");
    field.setColumnDecorator("", decorator);
    dbField.add(field);
    
    field = new DbField("�ŵ긺���", "STORE_MINUS_ALERT", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_432' target='navTab' href='/store/storealert_main.htm?type=1'>{value}��</a>");
    field.setColumnDecorator("", decorator);
    dbField.add(field);
    
    field = new DbField("�ֿ���澯", "STOCK_ALERT", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_821' target='navTab' href='/stock/stockalert_main.htm?type=2'>{value}��</a>");
    field.setColumnDecorator("", decorator);
    dbField.add(field);
    
    field = new DbField("�ֿ⸺���", "STOCK_MINUS_ALERT", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_822' target='navTab' href='/stock/stockalert_main.htm?type=1'>{value}��</a>");
    field.setColumnDecorator("", decorator);
    dbField.add(field);
    
    field = new DbField("���ֱ�����뵥", "STORE_DIRECT_APPLY_ORDER", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_711' target='navTab' href='/store/storeorder_main.htm?type=1'>{value}��</a>");
    field.setColumnDecorator("", decorator);
    dbField.add(field);
    
    field = new DbField("���ֱ���ջ���", "STORE_DIRECT_RECIVE_ORDER", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_731' target='navTab' href='/store/storeorder_main.htm?type=5'>{value}��</a>");
    field.setColumnDecorator("", decorator);
    dbField.add(field);
/*************************************************************************************************/
    
    
    
/*********************************************�����ſ�*********************************************/
    field = new DbField("�����(���ջ�)�ɹ�������", "ORDER_COMPLETE", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    dbField.add(field); 
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_1411' target='navTab' href='/purchase/purOrder_main.htm?state=4'>{value}��</a>");
    field.setColumnDecorator("", decorator);
    
    field = new DbField("�����вɹ�������", "ORDER_SENDING", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    dbField.add(field);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_1411' target='navTab' href='/purchase/purOrder_main.htm?state=3'>{value}��</a>");
    field.setColumnDecorator("", decorator);
    
    field = new DbField("�������ɹ�������", "ORDER_PENDING_STOCK", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    dbField.add(field);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_1411' target='navTab' href='/purchase/purOrder_main.htm?state=2'>{value}��</a>");
    field.setColumnDecorator("", decorator);
    
    field = new DbField("������(���ͨ��)�ɹ�������", "ORDER_PENDING_ACCEPT", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    dbField.add(field);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_1411' target='navTab' href='/purchase/purOrder_main.htm?state=1'>{value}��</a>");
    field.setColumnDecorator("", decorator);
    
    field = new DbField("�ѷ�ֹ�ɹ�������", "ORDER_CANCEL", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    dbField.add(field);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_1411' target='navTab' href='/purchase/purOrder_main.htm?state=0'>{value}��</a>");
    field.setColumnDecorator("", decorator);
    
    field = new DbField("����˲ɹ�������", "ORDER_WAIT_AUDIT", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    dbField.add(field);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_1411' target='navTab' href='/purchase/purOrder_main.htm?state=-1'>{value}��</a>");
    field.setColumnDecorator("", decorator);
    
    field = new DbField("��˲�ͨ���ɹ�������", "ORDER_AUDIT_NO_PASS", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    dbField.add(field);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_1411' target='navTab' href='/purchase/purOrder_main.htm?state=-2'>{value}��</a>");
    field.setColumnDecorator("", decorator);
    
    field = new DbField("�ܾ�����ɹ�������", "ORDER_REFUSE_ACCEPT", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    dbField.add(field);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_1411' target='navTab' href='/purchase/purOrder_main.htm?state=-3'>{value}��</a>");
    field.setColumnDecorator("", decorator);
    
    field = new DbField("���ղɹ�������", "ORDER_TODAY", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    dbField.add(field);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_1411' target='navTab' href='/purchase/purOrder_main.htm?state=5'>{value}��</a>");
    field.setColumnDecorator("", decorator);
/*************************************************************************************************/   
    
    
/**********************************************����������***************************************************/ 
    field = new DbField("�������˻�����", "COMMODITY_RETURN_APPLY", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    dbField.add(field);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_422' target='navTab' href='/store/returnOrder_main.htm?state=1'>{value}��</a>");
    field.setColumnDecorator("", decorator);
    
    field = new DbField("���������", "WAIT_AUDIT_BUSINESS", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    field.setDefaultValue(0);
    dbField.add(field);
    decorator = new StringDecorator();
    decorator.setStringFormat("<a rel='m_12021' target='navTab' href='/system/audits_main.htm?state=0'>{value}��</a>");
    field.setColumnDecorator("", decorator);
/*************************************************************************************************/
    
    
    this.fieldList = dbField;
  
  }
}