package com.ga.erp.biz.system.deliveryorg;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class DeliveryMainPage extends UnitPage{
	
  private static final long serialVersionUID = 1L;	
  private ListView deliveryList;
  
  @Override
  public void initController() throws Exception {
	  
    this.deliveryList = new DeliveryListView("deliveryList", this.modelMap);

    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.deliveryList, PageEvent.PARAMTYPE_PAGEQUERY);
    
    ActionButton action = this.deliveryList.regAddAction("newDelivery","新建","/system/delivery_detail.htm");
    SubmitTool.submitToDialog(action,"newDelivery", "新建物流机构",800,300,this.modelMap.getPreNavInfoStr());
    
    action = this.deliveryList.regEditAction("editDelivery","查看/编辑", "/system/delivery_detail.htm");
    SubmitTool.submitToDialog(action, "editDelivery", "查看/编辑",800,300,this.modelMap.getPreNavInfoStr());
  }
  
  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.deliveryList);
    return layout;
  }
  
  public void pageLoad(QueryPageData queryPagedata) {
    try {
      DeliveryBiz biz = new DeliveryBiz(this.getUserACL());
      this.deliveryList.bindData(biz.queryDeliveryList(queryPagedata, this.deliveryList.getFieldList()));
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM, "页面加载异常");
    }
  }
  
}
