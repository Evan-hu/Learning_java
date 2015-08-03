package com.ga.erp.biz.activity.regactivity;

import java.util.Map;
import org.apache.click.ActionResult;
import com.ga.click.control.GaConstant;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;

@SuppressWarnings("serial")
public class RegActivityDetailPage extends UnitPage {
	
  private FormView regActivityFormView;
  
  @Override
  public void initController() throws Exception {
	  
    this.regActivityFormView = new RegActivityFormView("regActivityFormView",this.modelMap);   
    
    PageEvent event = this.regPageLoadEvent("pageLoad");   
    event.addEventParam(this.regActivityFormView, PageEvent.PARAMTYPE_QUERYVALUE, "REG_ACTIVITY_ID");
    
    ActionButton action  = new ActionButton(this.getClass(),"saveRegActivity","保存",this.getSelfUrl(),null);
    action.bindForm(this.regActivityFormView.getViewID(),true);
    SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());
    
    action.addParam(GaConstant.FIXPARAM_EDITMODE, this.modelMap.getPageEditMode());
    this.regActivityFormView.regAction(action);
    
    event = this.regPageEvent(action, "saveRegActivity");
    event.addEventParam(this.regActivityFormView, PageEvent.PARAMTYPE_DATAMAP);
  }

  @Override
  public Layout initLayout() {
      
  	FormLayout formLayOut = new FormLayout("",this.regActivityFormView.getDataForm(),2);
  	this.regActivityFormView.getDataForm().setLayout(formLayOut);
  	ViewPageLayout layout = new ViewPageLayout(this);
  	layout.addControl(this.regActivityFormView);
    return layout;
  }

  public void pageLoad(Long id) {
	try { 
	  if(this.modelMap.getPageEditMode() == GaConstant.EDITMODE_NEW){
//		  this.regActivityFormView.bindData(this.regActivityFormView.getViewParam());
	  }else if(this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
		  RegActivityBiz biz = new RegActivityBiz(this.getUserACL()); 
		  this.regActivityFormView.bindData(biz.queryRegActivityDetail(id, this.regActivityFormView.getFieldList()));
       }
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"页面加载异常");
    }
  }
  
  public ActionResult saveRegActivity(Map<String, Object> valueMap) {
      return this.createReturnJSON(true, "保存成功", true, false, "ruleList", "");
  }
}
