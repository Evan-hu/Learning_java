package com.ga.erp.biz.supplier.commodity;

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
public class SupplierCommodityDetailPage extends UnitPage {

  private FormView supplierForm;

  @Override
  public void initController() throws Exception {

    this.supplierForm = new SupplierCommodityFormView("supplierCommodityDetail", this.modelMap);

    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.supplierForm, PageEvent.PARAMTYPE_QUERYVALUE, "SUPPLIER_COMMODITY_ID");
    
    ActionButton action = new ActionButton(this.getClass(), "saveSupplierCommodity", "保存", this.getSelfUrl(), null);
    action.addParam(GaConstant.FIXPARAM_EDITMODE, this.modelMap.getPageEditMode());
    SubmitTool.submitMvc(action,this.modelMap.getPreNavInfoStr());
    event = this.regPageEvent(action, "saveSupplierCommodity");
    event.addEventParam(this.supplierForm, PageEvent.PARAMTYPE_DATAMAP);
    action.bindForm(this.supplierForm.getViewID());
    this.supplierForm.regAction(action);
  }

  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    FormLayout formLayout = new FormLayout("",this.supplierForm.getDataForm(), 2);
    
    this.supplierForm.getDataForm().setLayout(formLayout);
    layout.addControl("基本信息","",supplierForm);
    return layout;
  }

  public void pageLoad(Long supplierCommID) {
    try {   
    Map<String, Object> map = this.supplierForm.getViewParam();
    if(map.get("SUPPLIER_ID") != null){
      this.supplierForm.getFieldList().remove(5);
    }
    if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
      SupplierCommodityBiz biz = new SupplierCommodityBiz(this.getUserACL());
      this.supplierForm.bindData(biz.querySupplierMap(supplierCommID));
      }else{
    	  this.supplierForm.bindData(this.supplierForm.getViewParam());
      }
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"页面加载异常");
    }
  }

  public ActionResult saveSupplierCommodity(Map<String, Object> valuesMap) {
      if (this.getModelMap().getPageEditMode() == GaConstant.EDITMODE_NEW) {
        SupplierCommodityBiz biz = new SupplierCommodityBiz(this.getUserACL());
        biz.saveSupplierCommodity(valuesMap, true);
      } else if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
        SupplierCommodityBiz biz = new SupplierCommodityBiz(this.getUserACL());
        biz.saveSupplierCommodity(valuesMap, false);
      }
      return this.createReturnJSON(true, "保存供应商商品成功", true, false, "supplierCommList", "");
  }
}
