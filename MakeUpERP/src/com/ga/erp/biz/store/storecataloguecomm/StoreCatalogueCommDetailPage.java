package com.ga.erp.biz.store.storecataloguecomm;

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
public class StoreCatalogueCommDetailPage extends UnitPage {

  private FormView storeCommodityformView;

  @Override
  public void initController() throws Exception {

    this.storeCommodityformView = new StoreCatalogueCommFormView("storeCatalogueCommFormView", this.modelMap);

    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.storeCommodityformView, PageEvent.PARAMTYPE_QUERYVALUE, "STORE_CATALOGUE_COMM_ID");
    
    ActionButton action = new ActionButton(this.getClass(), "saveStoreCatalogueComm", "保存", this.getSelfUrl(), null);
    action.addParam(GaConstant.FIXPARAM_EDITMODE, this.modelMap.getPageEditMode());
    SubmitTool.submitMvc(action,this.modelMap.getPreNavInfoStr());
    event = this.regPageEvent(action, "saveStoreCatalogueComm");
    event.addEventParam(this.storeCommodityformView, PageEvent.PARAMTYPE_DATAMAP);
    action.bindForm(this.storeCommodityformView.getViewID());
    this.storeCommodityformView.regAction(action);
  }

  @Override
  public Layout initLayout() {
	  
	  	FormLayout formLayout = new FormLayout("",this.storeCommodityformView.getDataForm(),2);
	  	this.storeCommodityformView.getDataForm().setLayout(formLayout);
	  	ViewPageLayout layout = new ViewPageLayout(this);
	  	layout.addControl(this.storeCommodityformView);
	    return layout;
  }

  public void pageLoad(Long id) {
    try {   
    if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
      StoreCatalogueCommBiz biz = new StoreCatalogueCommBiz(this.getUserACL());
      this.storeCommodityformView.bindData(biz.queryStoreCatalogueCommMap(id, this.storeCommodityformView.getFieldList()));
      }
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"页面加载异常");
    }
  }

  public ActionResult saveStoreCatalogueComm(Map<String, Object> valuesMap) {
      if (this.getModelMap().getPageEditMode() == GaConstant.EDITMODE_NEW) {
        StoreCatalogueCommBiz biz = new StoreCatalogueCommBiz(this.getUserACL());
        biz.saveStoreCatalogueComm(valuesMap, true);
      } else if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
        StoreCatalogueCommBiz biz = new StoreCatalogueCommBiz(this.getUserACL());
        biz.saveStoreCatalogueComm(valuesMap, false);
      }
      return this.createReturnJSON(true, "保存商品成功", true, false, "storeCatalogueCommListView", "");
  }
}
