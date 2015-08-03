/**
 * Copyright (c) 2011 Noah
 * All right reserved.
 */
package com.ga.erp.biz.activity.activity;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.click.ActionResult;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.ClickUtil;
import com.ga.click.util.GaUtil;

/**
 * 类描述
 * @author SPE
 * @create_time 2012-7-5 下午04:20:28
 * @project ShopMgr
 */
public class ActivityEditPage extends UnitPage {

  private static final long serialVersionUID = 1L;
  ActivityEditView formView;
  //订单信息
  ActivityOrderListView orderListView;
  
  ActivityStoreListView storeListView;
  
  ActivityParamListView paramListView;
  
  public void initController() throws Exception {
    
    formView = new ActivityEditView("formView", this.modelMap);
    
    orderListView = new ActivityOrderListView("orderListView", this.modelMap);
    this.orderListView.showPage(true);
    
    PageEvent pageEvent = this.regPageLoadEvent("pageLoad");
    pageEvent.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE, "DISCOUNT_ACTIVITY_ID"); 
    pageEvent.addEventParam(this.orderListView, PageEvent.PARAMTYPE_PAGEQUERY);
    pageEvent.addEventParam(this.orderListView, PageEvent.PARAMTYPE_QUERYMAP);
    
    storeListView = new ActivityStoreListView("activityStoreListView",this.modelMap);
    this.storeListView.showPage(true);
    
    paramListView = new ActivityParamListView("paramListView",this.modelMap);
    this.paramListView.showPage(true);
    storeListView.setMultiSelect(true);
    
    ActionButton action = new ActionButton(this.getClass(), "save", "保存", this.getSelfUrl(), null);
    action.addParam(GaConstant.FIXPARAM_EDITMODE, this.modelMap.getPageEditMode());
    action.addParam("isSave", true);
    SubmitTool.submitMvc(action,this.modelMap.getPreNavInfoStr());
    pageEvent = this.regPageEvent(action, "save");
    pageEvent.addEventParam(this.formView, PageEvent.PARAMTYPE_DATAMAP);
    action.bindForm(this.formView.getViewID());
    this.formView.regAction(action);
    
    if(this.modelMap.getPageEditMode() != GaConstant.EDITMODE_NEW && this.modelMap.getRequest().getParameter("isSave") == null){
      action = this.orderListView.regEditAction("editOrderForm", "查看","/order/order_detail.htm");
      action.bindTableRowData(this.orderListView.getViewID());
      SubmitTool.submitToDialog(action, "orderCheck","订单明细",  800, 600, this.modelMap.getPreNavInfoStr());
      
      action = this.storeListView.regAddAction("addActivityStore", "新增参与门店", "/activity/activity_store_edit.htm");
      action.bindViewParam(formView, "DISCOUNT_ACTIVITY_ID", "DISCOUNT_ACTIVITY_ID", false);
      SubmitTool.submitToDialog(action,"storeAdd", "新增参与门店",700,300,this.modelMap.getPreNavInfoStr());
      
      storeListView.regStateAction(this.getSelfUrl(), this,"STORE_ACTIVITY");
      
      action = this.paramListView.regAddAction("addActivityParam", "新增参数", "/include/activity/activity_param_edit.jsp");
      action.bindViewParam(formView, "DISCOUNT_ACTIVITY_ID", "DISCOUNT_ACTIVITY_ID", false);
      action.bindViewParam(formView, "TYPE", "TYPE", false);
      SubmitTool.submitToDialog(action,"paramAdd", "新增参数",600,400,this.modelMap.getPreNavInfoStr());
      
      action = this.paramListView.regEditAction("editActivityParam", "编辑","/include/activity/activity_param_edit.jsp");
      action.bindViewParam(formView, "DISCOUNT_ACTIVITY_ID", "DISCOUNT_ACTIVITY_ID", false);
      action.bindTableRowData(this.paramListView.getViewID());
      
      SubmitTool.submitToDialog(action, "paramEdit","参数信息",  600, 400, this.modelMap.getPreNavInfoStr());
      
      pageEvent = this.regListViewLoadEvent(this.orderListView, "queryOrderList");
      pageEvent.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE, "DISCOUNT_ACTIVITY_ID");
      pageEvent.addEventParam(this.orderListView, PageEvent.PARAMTYPE_PAGEQUERY);
      pageEvent.addEventParam(this.orderListView, PageEvent.PARAMTYPE_QUERYMAP);
      
      pageEvent = this.regListViewLoadEvent(this.storeListView, "queryStoreList");
      pageEvent.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE, "DISCOUNT_ACTIVITY_ID");
      pageEvent.addEventParam(this.storeListView, PageEvent.PARAMTYPE_PAGEQUERY);
      pageEvent.addEventParam(this.storeListView, PageEvent.PARAMTYPE_QUERYMAP);
      
      pageEvent = this.regListViewLoadEvent(this.paramListView, "queryParamList");
      pageEvent.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE, "DISCOUNT_ACTIVITY_ID");
      pageEvent.addEventParam(this.paramListView, PageEvent.PARAMTYPE_PAGEQUERY);
      pageEvent.addEventParam(this.paramListView, PageEvent.PARAMTYPE_QUERYMAP);
      
    }
  }
  
  
  
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    FormLayout formLayout = new FormLayout("", this.formView.getDataForm(), 2);
    if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_NEW) {
      formLayout.mergeCellField("auto", 3, 0, 2, "CONTENT");
      formLayout.mergeCellField("auto", 4, 0, 2, "NOTE");
    } else {
      formLayout.mergeCellField("auto", 4, 0, 2, "CONTENT");
      formLayout.mergeCellField("auto", 5, 0, 2, "NOTE");
    }
    formView.getDataForm().setLayout(formLayout);
    layout.addControl("","活动信息","活动信息","",this.formView);
    if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
      layout.addControl("","活动信息","活动方式","",this.paramListView);
      layout.addControl("","活动信息","店铺参与情况","",this.storeListView);
      layout.addControl("","活动信息","订单信息","",this.orderListView);
      
      
    }
    return layout;
  }
  
  public void pageLoad(Long activityID, QueryPageData queryData, Map<String,Object> queryMap) {
    try {
      ActivityBiz biz = new ActivityBiz(this.getUserACL());
      if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
    	  
    	Map<String,Object> map = biz.queryDetail(activityID, this.formView.getFieldList());
    	long type = (Long)map.get("TYPE");
    	if(type == 2l || type == 4l){
    	  DbField field = this.formView.getFieldByCode("COMMODITY_RANGE_TYPE");
    	  HashMap<String, Object> lookup = new LinkedHashMap<String, Object>();
    	  lookup.put("4", "商品");
    	  field.setLookupData(new LookupDataSet(lookup));
    	}
        //配送地址
        this.formView.bindData(map);
        
        this.orderListView.bindData(biz.queryOrderList(queryData, activityID, this.orderListView.getFieldList()));
        
        this.storeListView.bindData(biz.queryStoreList(queryData, activityID, this.storeListView.getFieldList()));
        
        this.paramListView.bindData(biz.queryParamList(queryData, activityID, this.paramListView.getFieldList()));
        
      }
      //判断当前是否允许保存      
    } catch(BizException e) {
      throw e;
    } catch(Exception ex) {
      throw new BizException("加载订单明细页面异常");
    }
  }
  
//  private boolean isActivityInProgress() {
//    if (formView.getDataForm() != null) {
//      String startTimeStr = formView.getDataForm().getField("START_TIME").getValue();
//      String endTimeStr = formView.getDataForm().getField("END_TIME").getValue();
//      String ruleStateStr = formView.getDataForm().getField("STATE").getValue();
//      Date nowTime = new Date();
//      if (!GaUtil.isNullStr(startTimeStr) && !GaUtil.isNullStr(endTimeStr) && !GaUtil.isNullStr(ruleStateStr)) {
//        Long state = Long.parseLong(ruleStateStr);
//        if (state != 1L) {
//          return false;
//        }
//        Date stratTime = GaUtil.getStrDate(startTimeStr, "yyyy-MM-dd");
//        Date endTime = GaUtil.getStrDate(endTimeStr, "yyyy-MM-dd");
//        return nowTime.after(stratTime) && nowTime.before(endTime);
//      }
//    }
//    return true;
//  }
//  
  public ActionResult save(Map<String,Object> detailForm) {
    
    try {
      ActivityBiz biz = new ActivityBiz(this.getUserACL());
      String typeStr = String.valueOf(getContext().getSessionAttribute("activityType"));
      if (!GaUtil.isNumber(typeStr)) {
        typeStr = "0";
      }
      //detailForm.put("SHOP_ID", 1L);
      if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_NEW) {
        //add
        biz.addActivity(detailForm, this.getUserACL().getUserID());
        return this.createReturnJSON(true, "保存成功", false, true, "formView", this.getSelfUrl());
      } else {
        //edit
        Map<String,Object> oldActivity = biz.queryActivityById((Long)detailForm.get("DISCOUNT_ACTIVITY_ID"));
        int oldState = 0;
        if(oldActivity != null){
          oldState = (Integer)oldActivity.get("STATE");
        }
//        if (!isActivityInProgress() || oldState == 0l || (Long)detailForm.get("STATE") == 0l) {
          biz.updateActivity(detailForm, this.getUserACL().getUserID());
//        }else{
//          return this.createReturnJSON(false, "修改失败，正在进行中的活动必须废止后，才能修改!", false, false, "", "");
//        }
        return this.createReturnJSON(true, "修改成功!", true, false, "activityListView", "");
      }
    }  catch(BizException e) {
      throw e;
    } catch(Exception ex) {
      ex.printStackTrace();
      throw new BizException("活动保存处理异常");
    }
  }
  
  
  public ActionResult queryOrderList(Long activityID, QueryPageData pageData, Map<String,Object> queryMap) {
    try {
      ActivityBiz biz = new ActivityBiz(this.getUserACL());
      this.orderListView.bindData(biz.queryOrderList(pageData, activityID, this.orderListView.getFieldList()));
      ClickUtil.setControlLayoutH(this.orderListView.getViewControl(), 140);
      return this.linkView(this.orderListView);	
      //判断当前是否允许保存
    } catch(BizException e) {
      throw e;
    } catch(Exception ex) {
      throw new BizException("加载订单列表页面异常");
    }
  }
  
  public ActionResult queryStoreList(Long activityID, QueryPageData pageData, Map<String,Object> queryMap) {
	    try {
	      ActivityBiz biz = new ActivityBiz(this.getUserACL());
	      this.storeListView.bindData(biz.queryStoreList(pageData, activityID, this.storeListView.getFieldList()));
	      ClickUtil.setControlLayoutH(this.storeListView.getViewControl(), 140);
	      return this.linkView(this.storeListView);
	      //判断当前是否允许保存      
	    } catch(BizException e) {
	      throw e;
	    } catch(Exception ex) {
	      throw new BizException("加载订单列表页面异常");
	    }
	  }
  public ActionResult queryParamList(Long activityID, QueryPageData pageData, Map<String,Object> queryMap) {
	    try {
	      ActivityBiz biz = new ActivityBiz(this.getUserACL());
	      this.paramListView.bindData(biz.queryParamList(pageData, activityID, this.paramListView.getFieldList()));
	      ClickUtil.setControlLayoutH(this.paramListView.getViewControl(), 140);
	      return this.linkView(this.paramListView);
	      //判断当前是否允许保存 
	    } catch(BizException e) {
	      throw e;
	    } catch(Exception ex) {
	      throw new BizException("加载订单列表页面异常");
	    }
	  }
}
