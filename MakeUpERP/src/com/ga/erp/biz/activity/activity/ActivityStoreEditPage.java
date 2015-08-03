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
    
    ActionButton action = new ActionButton(this.getClass(), "save", "����", this.getSelfUrl(), null);
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
    layout.addControl("","������Ϣ","������Ϣ","",this.formView);
    formLayout.mergeCellField("auto", 1, 0, 2, "NOTE");
    return layout;
  }
  
  public void pageLoad(Long activityID) {
	  try {
	      ActivityBiz biz = new ActivityBiz(this.getUserACL());
	      if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
	        //���͵�ַ
	        this.formView.bindData(biz.queryStoreDetail(activityID, this.formView.getFieldList()));
	      }else{
	    	  this.formView.bindData(this.formView.getViewParam());
	      }
	      //�жϵ�ǰ�Ƿ�������      
	    } catch(BizException e) {
	      throw e;
	    } catch(Exception ex) {
	      throw new BizException("����ҳ���쳣");
	    }
  }
  
  public ActionResult save(Map<String,Object> detailForm) {
	  ActivityBiz biz = new ActivityBiz(getUserACL());
	  if(biz.editActivityStore(detailForm)){
        return this.createReturnJSON(true, "ָ�������ŵ�ɹ�", true, false, "activityStoreListView", "");
	  }else{
		return this.createReturnJSON(false, "�༭ʧ��(ͬһ������ŵ�ֻ�ܲ���һ��!)", false, false, "", "");  
	  }
  }
}
