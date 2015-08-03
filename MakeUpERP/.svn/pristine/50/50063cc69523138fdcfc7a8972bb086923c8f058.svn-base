/**
 * Copyright (c) 2011 Noah
 * All right reserved.
 */
package com.ga.erp.biz.activity.activity;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.click.ActionResult;

import com.ga.click.control.GaConstant;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.util.GaUtil;

/**
 * 类描述
 * @author SPE
 * @create_time 2012-7-5 下午04:20:28
 * @project ShopMgr
 */
public class ActivityParamEditPage extends UnitPage {
  
  private static final long serialVersionUID = 1L;
  ActivityParamEditView formView;
  
  public void initController() throws Exception {
    
    formView = new ActivityParamEditView("formView", this.modelMap);
    
    PageEvent pageEvent = this.regPageLoadEvent("pageLoad");
    pageEvent.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE, "DISCOUNT_PARAM_ID"); 
    
    ActionButton action = new ActionButton(this.getClass(), "save", "保存", this.getSelfUrl(), null);
    action.addParam(GaConstant.FIXPARAM_EDITMODE, this.modelMap.getPageEditMode());
    action.addParam("isSave", true);
    if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_NEW) {
      SubmitTool.submitMvc(action,this.modelMap.getPreNavInfoStr());  
    } else {
      SubmitTool.submitMvc(action,this.modelMap.getPreNavInfoStr());
    } 
    pageEvent = this.regPageEvent(action, "save");
    pageEvent.addEventParam(this.formView, PageEvent.PARAMTYPE_DATAMAP);
    action.bindForm(this.formView.getViewID());
    this.formView.regAction(action);
    
  }
  
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    FormLayout formLayout = new FormLayout("", this.formView.getDataForm(), 2);

    formView.getDataForm().setLayout(formLayout);
    layout.addControl("","参数信息","参数信息","",this.formView);
  
    return layout;
  }
  
  public void pageLoad(Long activityID) {
	  
  }
  
  public ActionResult save(Map<String,Object> detailForm) {
	  long type = (Long)detailForm.get("DISCOUNT_TYPE");
	  long commodityType = (Long)detailForm.get("COMMODITY_RANGE_TYPE");
	  
	  if(commodityType == 2 ){
		  if(detailForm.get("NEWBRANDSELECT.SORT_ID") == null){
		    return this.createReturnJSON(false, "请选择分类！", false, false, "", "");
		  }
		  detailForm.put("TARGET_ID", detailForm.get("NEWBRANDSELECT.SORT_ID"));
		  detailForm.put("TARGET_NAME", detailForm.get("NEWSORTSELECT.SORT_NAME"));
	  }
	  if(commodityType == 3 ){
		  if(detailForm.get("NEWBRANDSELECT.BRAND_ID") == null){
		    return this.createReturnJSON(false, "请选择品牌！", false, false, "", "");
		  }
		  detailForm.put("TARGET_ID", detailForm.get("NEWBRANDSELECT.BRAND_ID"));
		  detailForm.put("TARGET_NAME", detailForm.get("NEWBRANDSELECT.NAME"));
	  }
	  if(commodityType == 4 ){
		  if(detailForm.get("selectCommodity.COMMODITY_ID") == null){
		    return this.createReturnJSON(false, "请选择商品！", false, false, "", "");
		  }
		  detailForm.put("TARGET_ID", detailForm.get("selectCommodity.COMMODITY_ID"));
		  detailForm.put("TARGET_NAME", detailForm.get("selectCommodity.COMMODITY_NAME"));
	  }
	  if(type == 7){
		  if(detailForm.get("selectCardBatch.CARD_BATCH_ID") == null){
			    return this.createReturnJSON(false, "请选择电子券批次！", false, false, "", "");
			  }
			  detailForm.put("TARGET_ID", detailForm.get("selectCardBatch.CARD_BATCH_ID"));
			  detailForm.put("TARGET_NAME", detailForm.get("selectCardBatch.NAME"));
	  }
	  String errorMsg = "";
	  BigDecimal minMoney = (BigDecimal)detailForm.get("MIN_MONEY");
	  BigDecimal value = (BigDecimal)detailForm.get("VALUE");
	  BigDecimal minCount = (BigDecimal)detailForm.get("MIN_COUNT");

	  if(type == 1){//满金额加钱赠送商品
			if(minMoney == null || minMoney.longValue() < 0){
				errorMsg =  "买满金额不能为空或小于0（不设置金额请填写0）！";
			}
			else if(value == null || value.longValue() <= 0){
				errorMsg =  "附件金额不能为空或小于（不设置金额请填写0）！";
			}else if( detailForm.get("selectCommodity.COMMODITY_ID") == null){
				errorMsg =  "请选择赠送的商品！";
			}
	  }else if(type == 2){//减免固定金额
		
		if(minMoney == null || minMoney.longValue() < 0){
			errorMsg =  "买满金额不能为空或小于0（不设置金额请填写0）！";
		}
		else if(value == null || value.longValue() <= 0){
			errorMsg =  "减免金额不能为空或小于等于0！";
		}
	  }else if(type == 3){//指定商品特价
		  if(value == null || value.longValue() <= 0){
			errorMsg =  "减免金额不能为空或小于等于0！";
		  }
	  }else if(type == 4){//满金额打折（直接折扣金额设置为0即可）
		
		if(minMoney == null || minMoney.longValue() < 0){
			errorMsg =  "买满金额不能为空或小于0（不设置金额请填写0）！";
		}
		else if(value == null || value.longValue() <= 0){
			errorMsg =  "折扣金额不能为空或小于等于0！";
		}
	  }else if(type == 5){//满数量打折
		 if(minCount == null || minCount.longValue() < 0){
			errorMsg =  "买满数量不能为空或小于0（不设置数量请填写0）！";
		 }
		 else if(value == null || value.longValue() <= 0){
			errorMsg =  "折扣金额不能为空或小于等于0！";
		 }
	  }else if(type == 7){//满金额赠送优惠券
		  if(minCount == null || minCount.longValue() < 0){
				errorMsg =  "买满数量不能为空或小于0（不设置金额请填写0）！";
		  }
		  if(detailForm.get("selectCardBatch.CARD_BATCH_ID") == null){
				errorMsg =  "请选择电子券批次！";
		  }
	  }else if(type == 8){//组合商品
		  
	  }else if(type == 9){//满数量加钱赠送商品
			if(minCount == null || minCount.longValue() < 0){
				errorMsg =  "买满数量不能为空或小于0（不设置金额请填写0）！";
			}
			else if(value == null || value.longValue() <= 0){
				errorMsg =  "附件金额不能为空或小于（不设置金额请填写0）！";
			}else if( detailForm.get("selectCommodity.COMMODITY_ID") == null){
				errorMsg =  "请选择赠送的商品！";
			}
	  }
	  if(!GaUtil.isNullStr(errorMsg)){
		return this.createReturnJSON(false, errorMsg, false, false, "", "");
	  }
	  
	  ActivityBiz biz = new ActivityBiz(getUserACL());
	  if(biz.editActivityParam(detailForm)){
        return this.createReturnJSON(true, "编辑成功!", true, false, "paramListView", "");
	  }else{
		return this.createReturnJSON(false, "编辑失败!", false, false, "", "");  
	  }
  }
}
