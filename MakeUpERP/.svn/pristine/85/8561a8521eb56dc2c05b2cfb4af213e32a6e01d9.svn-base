/**
 * Copyright (c) 2011 Noah
 * All right reserved.
 */
package com.ga.erp.biz.activity.activity;

import java.util.Map;

import org.apache.click.ActionResult;

import com.ga.click.control.GaConstant;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;

public class ActivityStoreEditPage extends UnitPage {
  
  private static final long serialVersionUID = 1L;
  ActivityStoreEditView formView;
  
  public void initController() throws Exception {
    
    formView = new ActivityStoreEditView("activityStoreEditView", this.modelMap);
    
    PageEvent pageEvent = this.regPageLoadEvent("pageLoad");
    pageEvent.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE, "STORE_ACTIVITY_ID"); 
    
    ActionButton action = new ActionButton(this.getClass(), "save", "保存", this.getSelfUrl(), null);
    action.addParam(GaConstant.FIXPARAM_EDITMODE, this.modelMap.getPageEditMode());
    SubmitTool.submitMvc(action,this.modelMap.getPreNavInfoStr());
    
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
    formLayout.mergeCellField("auto", 1, 0, 2, "NOTE");
    return layout;
  }
  
  public void pageLoad(Long activityID) {
	  try {
	      ActivityBiz biz = new ActivityBiz(this.getUserACL());
	      if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
	        //配送地址
	        this.formView.bindData(biz.queryStoreDetail(activityID, this.formView.getFieldList()));
	      }else{
	    	  this.formView.bindData(this.formView.getViewParam());
	      }
	      //判断当前是否允许保存      
	    } catch(BizException e) {
	      throw e;
	    } catch(Exception ex) {
	      throw new BizException("加载页面异常");
	    }
  }
  
  public ActionResult save(Map<String,Object> detailForm) {
	  ActivityBiz biz = new ActivityBiz(getUserACL());
	  if(biz.editActivityStore(detailForm)){
        return this.createReturnJSON(true, "指定参与门店成功", true, false, "activityStoreListView", "");
	  }else{
		return this.createReturnJSON(false, "编辑失败(同一个活动下门店只能参与一次!)", false, false, "", "");  
	  }
  }
}
