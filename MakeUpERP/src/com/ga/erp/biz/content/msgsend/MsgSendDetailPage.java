package com.ga.erp.biz.content.msgsend;

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
import com.ga.click.util.FileUploadUtil;

@SuppressWarnings("serial")
public class MsgSendDetailPage extends UnitPage {
	
  private FormView shopForm;
  
  @Override
  public void initController() throws Exception {
	  
    this.shopForm = new MsgSendFormView("shopForm",this.modelMap);   
    
    PageEvent event = this.regPageLoadEvent("pageLoad");   
    event.addEventParam(this.shopForm, PageEvent.PARAMTYPE_QUERYVALUE, "SHOP_ID");
    
    ActionButton action  = new ActionButton(this.getClass(),"saveShop","保存",this.getSelfUrl(),null);
    action.bindForm(this.shopForm.getViewID(),true);
    SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());
    
    action.addParam(GaConstant.FIXPARAM_EDITMODE, this.modelMap.getPageEditMode());
    this.shopForm.regAction(action);
    
    event = this.regPageEvent(action, "saveShop");
    event.addEventParam(this.shopForm, PageEvent.PARAMTYPE_DATAMAP);
  }

  @Override
  public Layout initLayout() {
	FormLayout formLayOut = new FormLayout("",this.shopForm.getDataForm(),2);
	formLayOut.mergeCellField("auto", this.modelMap.getPageEditMode() == 1 ? 12 : 13, 0, 1, "CONTENT");
	this.shopForm.getDataForm().setLayout(formLayOut);
	ViewPageLayout layout = new ViewPageLayout(this);
	layout.addControl(this.shopForm);
    return layout;
  }

  public void pageLoad(Long shopId) {
	try {   
	  if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
		  MsgSendBiz biz = new MsgSendBiz(this.getUserACL()); 
		  Map<String, Object> valuesMap = biz.queryShopDetail(shopId);
		  FileUploadUtil.downloadSet(valuesMap, "COMPANY_IMG_URL","shopcom");
		  FileUploadUtil.downloadSet(valuesMap, "PERSONAL_IMG_URL","shopper");
		  this.shopForm.bindData(valuesMap);
       }
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"页面加载异常");
    }
  }
  
  /**
   * 保存店铺
   */
  public ActionResult saveShop(Map<String, Object> valueMap) {
	  MsgSendBiz biz = new MsgSendBiz(this.getUserACL());
      if (this.getModelMap().getPageEditMode() == GaConstant.EDITMODE_NEW) {
        biz.saveShop(valueMap, true);
      } else if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
        biz.saveShop(valueMap, false);
      }
      return this.createReturnJSON(true, "保存成功", true, false, "shopList", "");
  }
}
