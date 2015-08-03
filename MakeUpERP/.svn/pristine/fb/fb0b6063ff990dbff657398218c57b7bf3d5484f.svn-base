package com.ga.erp.biz.settlement.deliveryorg;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class DeliverySettMainPage extends UnitPage{
	
  private static final long serialVersionUID = 1L;	
  private ListView deliveryListView;
  String type = "";
  
  @Override
  public void initController() throws Exception {
    type = this.modelMap.getRequest().getParameter("type");
    this.deliveryListView = new DeliveryListView("deliveryList", this.modelMap);

    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.deliveryListView, PageEvent.PARAMTYPE_PAGEQUERY);
    
    ActionButton action = this.deliveryListView.regEditAction("mtdeliverysett","结算", "/deliveryorg/deliveryorg_mtpage.htm");
    SubmitTool.submitToDialog(action,"mtdeliverysett", "结算",1100,700,this.modelMap.getPreNavInfoStr());
    action.addParam("type", type);
    
//    action = this.deliveryListView.regEditAction("mtsuppsettlement","费用结算", "/settlement/costorder_settlement_mtpage.htm");
//    SubmitTool.submitToDialog(action,"mtsuppsettlement", "费用结算",1100,700,this.modelMap.getPreNavInfoStr());
//    action.addParam("type", 4);
  }
  
  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.deliveryListView);
    return layout;
  }
  
  public void pageLoad(QueryPageData queryPagedata) {
    try {
      DeliverySettBiz biz = new DeliverySettBiz(this.getUserACL());
      this.deliveryListView.bindData(biz.querySettDeliveryOrg(queryPagedata, this.deliveryListView.getFieldList()));
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM, "页面加载异常");
    }
  }
}
