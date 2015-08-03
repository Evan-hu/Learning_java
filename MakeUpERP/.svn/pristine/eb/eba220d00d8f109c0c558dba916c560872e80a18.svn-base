package com.ga.erp.biz.system.area.cataloguecomm;

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
public class CatalogueCommDetailPage extends UnitPage {

  private FormView formView;

  @Override
  public void initController() throws Exception {

    this.formView = new CatalogueCommFormView("catalogueCommFormViewDetail", this.modelMap);

    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE, "CATALOGUE_COMM_ID");
    
    ActionButton action = new ActionButton(this.getClass(), "saveCatalogueComm", "保存", this.getSelfUrl(), null);
    action.addParam(GaConstant.FIXPARAM_EDITMODE, this.modelMap.getPageEditMode());
    SubmitTool.submitMvc(action,this.modelMap.getPreNavInfoStr());
    event = this.regPageEvent(action, "saveCatalogueComm");
    event.addEventParam(this.formView, PageEvent.PARAMTYPE_DATAMAP);
    action.bindForm(this.formView.getViewID());
    this.formView.regAction(action);
  }

  @Override
  public Layout initLayout() {
	  
    ViewPageLayout layout = new ViewPageLayout(this);
    FormLayout formLayout = new FormLayout("",this.formView.getDataForm(), 2);
    this.formView.getDataForm().setLayout(formLayout);
    layout.addControl(formView);
    if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
    }
    return layout;
  }

  public void pageLoad(Long catalogueCommId) {
    try {   
    if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
      CatalogueCommBiz biz = new CatalogueCommBiz(this.getUserACL());
      this.formView.bindData(biz.queryCatalogueCommMap(this.formView.getFieldList(), catalogueCommId));
      }else {
          this.formView.bindData(this.formView.getViewParam());
      }
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"页面加载异常");
    }
  }

  public ActionResult saveCatalogueComm(Map<String, Object> valuesMap) {
      if (this.getModelMap().getPageEditMode() == GaConstant.EDITMODE_NEW) {
        CatalogueCommBiz biz = new CatalogueCommBiz(this.getUserACL());
        biz.saveCatalogueComm(valuesMap, true);
      } else if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
        CatalogueCommBiz biz = new CatalogueCommBiz(this.getUserACL());
        biz.saveCatalogueComm(valuesMap, false);
      }
      return this.createReturnJSON(true, "保存商品成功", true, false, "catalogueCommListView", "");
  }
  
}
