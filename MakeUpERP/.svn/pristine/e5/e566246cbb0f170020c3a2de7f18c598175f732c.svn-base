package com.ga.erp.biz.content.ad;

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

public class AdPublishDetailPage extends UnitPage {
	
  private static final long serialVersionUID = 1L;
  private FormView adForm;
  
  @Override
  public void initController() throws Exception {
	  
    this.adForm = new AdFormView("ad",this.modelMap);   
    
    PageEvent event = this.regPageLoadEvent("pageLoad");   
    event.addEventParam(this.adForm, PageEvent.PARAMTYPE_QUERYVALUE, "AD_ID");
    
    ActionButton action  = new ActionButton(this.getClass(),"saveAd","保存",this.getSelfUrl(),null);
    action.bindForm(this.adForm.getViewID(),true);
    SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());
    
    action.addParam(GaConstant.FIXPARAM_EDITMODE, this.modelMap.getPageEditMode());
    this.adForm.regAction(action);
    
    event = this.regPageEvent(action, "saveAd");
    event.addEventParam(this.adForm, PageEvent.PARAMTYPE_DATAMAP);
  }

    @Override
    public Layout initLayout() {
    	FormLayout formLayout = new FormLayout("",this.adForm.getDataForm(),2);
    	formLayout.mergeCellField("auto", this.modelMap.getPageEditMode() == GaConstant.EDITMODE_NEW ? 5 : 7, 0, 1, "NOTE");
    	formLayout.mergeCellField("auto", this.modelMap.getPageEditMode() == GaConstant.EDITMODE_NEW ? 6 : 8, 0, 1, "CONTENT");
    	this.adForm.getDataForm().setLayout(formLayout);
    	ViewPageLayout layout = new ViewPageLayout(this);
    	layout.addControl(this.adForm);
        return layout;
    }

    public void pageLoad(Long adId) {
  	  try {   
  	    if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
    		  AdBiz biz = new AdBiz(this.getUserACL()); 
    		  Map<String, Object> valuesMap = biz.queryAdDetail(adId, this.adForm.getFieldList());
    		  valuesMap.put("CONTENT", FileUploadUtil.getPicVrPath("ad") + "/" + valuesMap.get("AD_ID") + ".jpg");
    		  valuesMap.put("AD_ID", adId);
    		  this.adForm.bindData(valuesMap);
         }
      } catch(BizException ex) {
        throw ex;
      } catch(Exception e) {
        throw new BizException(BizException.SYSTEM, "页面加载异常");
      }
    }
  
  /**
   * 保存
   */
  public ActionResult saveAd(Map<String, Object> valueMap) {
	  AdBiz biz = new AdBiz(this.getUserACL());
      if (this.getModelMap().getPageEditMode() == GaConstant.EDITMODE_NEW) {
        biz.saveAd(valueMap, true);
      } else if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
        biz.saveAd(valueMap, false);
      }
      return this.createReturnJSON(true, "保存成功", true, false, "adList", "");
  }
}
