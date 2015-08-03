package com.ga.erp.biz.store.storeorder.comm;

import java.util.Map;
import org.apache.click.ActionResult;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.erp.biz.store.storeorder.StoreOrderBiz;

public class OrderCommDetailPage extends UnitPage {
	
  private static final long serialVersionUID = 1L;
  private FormView orderCommForm;
  
  @Override
  public void initController() throws Exception {
	  
    this.orderCommForm = new OrderCommFormView("orderCommForm",this.modelMap);   
    
    PageEvent event = this.regPageLoadEvent("pageLoad");   
    event.addEventParam(this.orderCommForm, PageEvent.PARAMTYPE_QUERYVALUE, "STORE_ORDER_COMM_ID");
    
    this.orderCommForm.regAddSaveEvent("saveOrderComm", "saveOrderComm", this, true);
    this.orderCommForm.regEditSaveEvent("saveOrderComm", "saveOrderComm", this, true);
    
  }

  @Override
  public Layout initLayout() {
  	FormLayout formLayOut = new FormLayout("",this.orderCommForm.getDataForm(),2);
  	this.orderCommForm.getDataForm().setLayout(formLayOut);
  	ViewPageLayout layout = new ViewPageLayout(this);
  	layout.addControl(this.orderCommForm);
    return layout;
  }

  public void pageLoad(Long orderCommId) {
	try {   
	  for (DbField field : this.orderCommForm.getFieldList()) {
	    if(!(field.getFieldCode().equals("ACTUAL_CNT") || field.getFieldCode().equals("DIFF_REASON") 
	        || field.getFieldCode().equals("NOTE"))){
	      field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
	    }
    }
	  if(this.modelMap.getPageEditMode() == GaConstant.EDITMODE_NEW){
	    this.orderCommForm.bindData(this.orderCommForm.getViewParam());
	  } else {
	    StoreOrderBiz biz = new StoreOrderBiz(this.getUserACL());
	    this.orderCommForm.bindData(biz.queryOrderCommDetail(this.orderCommForm.getFieldList(), orderCommId));
	  }
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"页面加载异常");
    }
  }
  
  public ActionResult saveOrderComm(Map<String, Object> valueMap) {
    try {
      StoreOrderBiz biz = new StoreOrderBiz(this.getUserACL());
      if(this.modelMap.getPageEditMode() == GaConstant.EDITMODE_NEW){
        biz.saveOrderComm(valueMap, true);
      } else {
        biz.saveOrderComm(valueMap, false);
      }
      return this.createReturnJSON(true, "保存成功", true, false, "orderCommList", "");
    } catch (BizException e) {
      throw e;
    } catch (Exception ex) {
      throw new BizException("加载页面异常");
    }
  }
  
}
