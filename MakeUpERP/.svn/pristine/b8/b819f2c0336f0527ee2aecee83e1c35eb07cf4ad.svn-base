package com.ga.erp.biz.system.deliveryorg;

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

public class DeliveryDetailPage extends UnitPage {
	
  private static final long serialVersionUID = 1L;
  private FormView deliveryForm;
  
  @Override
  public void initController() throws Exception {
	  
    this.deliveryForm = new DeliveryFormView("deliveryForm",this.modelMap);   
    
    PageEvent event = this.regPageLoadEvent("pageLoad");   
    event.addEventParam(this.deliveryForm, PageEvent.PARAMTYPE_QUERYVALUE, "DELIVERY_ORG_ID");
    
    ActionButton action  = new ActionButton(this.getClass(),"saveShop","保存",this.getSelfUrl(),null);
    action.bindForm(this.deliveryForm.getViewID(),true);
    SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());
    
    action.addParam(GaConstant.FIXPARAM_EDITMODE, this.modelMap.getPageEditMode());
    this.deliveryForm.regAction(action);
    
    event = this.regPageEvent(action, "saveDelivery");
    event.addEventParam(this.deliveryForm, PageEvent.PARAMTYPE_DATAMAP);
  }

  @Override
  public Layout initLayout() {
	FormLayout formLayOut = new FormLayout("",this.deliveryForm.getDataForm(),2);
	this.deliveryForm.getDataForm().setLayout(formLayOut);
	ViewPageLayout layout = new ViewPageLayout(this);
	layout.addControl(this.deliveryForm);
    return layout;
  }

  public void pageLoad(Long id) {
	try {   
	  if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
		  DeliveryBiz biz = new DeliveryBiz(this.getUserACL()); 
		  this.deliveryForm.bindData(biz.queryDeliveryDetail(this.deliveryForm.getFieldList(), id));
       }
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"页面加载异常");
    }
  }
  
  public ActionResult saveDelivery(Map<String, Object> valueMap) {
	  DeliveryBiz biz = new DeliveryBiz(this.getUserACL());
      if (this.getModelMap().getPageEditMode() == GaConstant.EDITMODE_NEW) {
        biz.saveDelivery(valueMap, true);
      } else if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
        biz.saveDelivery(valueMap, false);
      }
      return this.createReturnJSON(true, "保存成功", true, false, "deliveryList", "");
  }
}
