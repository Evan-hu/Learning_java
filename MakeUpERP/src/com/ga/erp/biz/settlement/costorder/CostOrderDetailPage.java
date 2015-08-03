package com.ga.erp.biz.settlement.costorder;

import java.util.HashMap;
import java.util.Map;

import org.apache.click.ActionResult;

import com.ga.click.control.GaConstant;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;

public class CostOrderDetailPage extends UnitPage {
	
  private static final long serialVersionUID = 1L;
  private FormView costOrderForm;
  String type = "";
  
  @Override
  public void initController() throws Exception {
	  
    this.costOrderForm = new CostOrderFormView("costOrderForm",this.modelMap);   
    
    PageEvent event = this.regPageLoadEvent("pageLoad");   
    event.addEventParam(this.costOrderForm, PageEvent.PARAMTYPE_QUERYVALUE, "COST_ORDER_ID");
    type = this.modelMap.getRequest().getParameter("type");
    
    this.costOrderForm.regAddSaveEvent("saveCostOrder", "saveCostOrder", this, true);
    this.costOrderForm.regEditSaveEvent("saveCostOrder", "saveCostOrder", this, true);

  }

  @Override
  public Layout initLayout() {
  	FormLayout formLayOut = new FormLayout("",this.costOrderForm.getDataForm(),2);
  	this.costOrderForm.getDataForm().setLayout(formLayOut);
  	ViewPageLayout layout = new ViewPageLayout(this);
  	if(this.modelMap.getPageEditMode() == GaConstant.EDITMODE_NEW){
  	  layout.addControl(this.costOrderForm);
  	} else {
  	  layout.addControl("基本信息","",this.costOrderForm);
  	}
    return layout;
  }

  public void pageLoad(Long costOrderId) {
    try {
      if(!type.equals("3")){
        this.costOrderForm.getFieldByCode("OBJECT_ID").setPopSelect("OBJECTSELECT", "SUPPLIER_ID", false);
        this.costOrderForm.getFieldByCode("OBJECT_NAME").setPopSelect("OBJECTSELECT","SUPPLIER_NAME",true,"/supplier/supplier_main.htm?type=" + type,
            "SUPPLIER_ID,SUPPLIER_NAME,cid_supplierList", 800,400);
      }
      if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
        CostOrderBiz biz = new CostOrderBiz(this.getUserACL());
        this.costOrderForm.bindData(biz.queryCostOrderDetail(costOrderId,this.costOrderForm.getFieldList()));
      }else{
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("OBJECT_TYPE", type);
       this.costOrderForm.bindData(map);
      }
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM, "页面加载异常");
    }
  }
  
  public ActionResult saveCostOrder(Map<String, Object> valueMap) {
    try {
      CostOrderBiz biz = new CostOrderBiz(this.getUserACL());
      if(this.modelMap.getPageEditMode() == GaConstant.EDITMODE_NEW){
        biz.saveCostOrder(valueMap, true);
      } else {
        biz.saveCostOrder(valueMap, false);
      }
      return this.createReturnJSON(true, "保存成功", true, true, "", "/costorder/costorder_main.htm?type=" + valueMap.get("OBJECT_TYPE"));
    } catch (BizException e) {
      throw e;
    } catch (Exception ex) {
      throw new BizException("加载页面异常");
    }
  }
  
}
