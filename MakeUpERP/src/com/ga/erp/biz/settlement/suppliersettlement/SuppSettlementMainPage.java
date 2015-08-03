package com.ga.erp.biz.settlement.suppliersettlement;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class SuppSettlementMainPage extends UnitPage {

  private static final long serialVersionUID = 1L;
  private ListView suppSettListView;
  private String type = "";
  //feable
  @Override
  public void initController() throws Exception {
    
    type = (String) this.modelMap.getRequest().getParameter("type");
    suppSettListView = new SuppSettListView("suppSettListView",this.modelMap);
    this.suppSettListView.setQueryRows(2);
    suppSettListView.setMultiSelect(true);
    PageEvent event  = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.suppSettListView,PageEvent.PARAMTYPE_PAGEQUERY);

    ActionButton action = this.suppSettListView.regEditAction("mtsuppsettlement","结算", "/settlement/supplier_settlement_mtpage.htm");
    SubmitTool.submitToDialog(action,"mtsuppsettlement", "结算",1100,700,this.modelMap.getPreNavInfoStr());
    action.addParam("type", type);
    
//    action = this.suppSettListView.regEditAction("mtsuppsettlement","费用结算", "/settlement/costorder_settlement_mtpage.htm");
//    SubmitTool.submitToDialog(action,"mtsuppsettlement", "结算",1100,700,this.modelMap.getPreNavInfoStr());
//    action.addParam("type", type);
    
//    ActionButton action = this.supplierListView.regEditAction("settlementEdit","结算","/settlement/supplier_settlement_detail.htm");
//    SubmitTool.submitToDialog(action, "settlementEdit","结算",1000, 600,this.modelMap.getPreNavInfoStr());
  }

  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.suppSettListView);
    return layout;	
  }
  
  public void pageLoad(QueryPageData queryPageData) {
    try {      
      SuppSettlementBiz biz = new SuppSettlementBiz(this.getUserACL());
      this.suppSettListView.bindData(biz.querySettSupplier(queryPageData, this.suppSettListView.getFieldList(), type));      
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"页面加载异常");
    }
  }
}
