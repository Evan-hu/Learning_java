package com.ga.erp.biz.settlement.storesettlement;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class StoreSettlementMainPage extends UnitPage {

  private static final long serialVersionUID = 1L;
  private ListView storeSettListView;
  private String type = "";
  
  @Override
  public void initController() throws Exception {
    
    type = this.modelMap.getRequest().getParameter("type");
    storeSettListView = new StoreSettListView("storeSettListView",this.modelMap);
    this.storeSettListView.setQueryRows(1);
    storeSettListView.setMultiSelect(true);
    PageEvent event  = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.storeSettListView,PageEvent.PARAMTYPE_PAGEQUERY);
    
    ActionButton action = this.storeSettListView.regEditAction("storeSettlement","结算", "/settlement/store_settlement_mtpage.htm");
    SubmitTool.submitToDialog(action,"storeSettlement", "结算",1100,700,this.modelMap.getPreNavInfoStr());
    action.addParam("type", type);
    
//    action = this.storeSettListView.regEditAction("storeSettlement","费用结算", "/settlement/costorder_settlement_mtpage.htm");
//    SubmitTool.submitToDialog(action,"storeSettlement", "费用结算",1100,700,this.modelMap.getPreNavInfoStr());
//    action.addParam("type", type);
  }

  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.storeSettListView);
    return layout;	
  }
  
  public void pageLoad(QueryPageData queryPageData) {
    try {      
         StoreSettlementBiz biz = new StoreSettlementBiz(this.getUserACL());
         this.storeSettListView.bindData(biz.querySettStore(queryPageData, this.storeSettListView.getFieldList()));
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"页面加载异常");
    }
  }
}
