package com.ga.erp.biz.store.storeorder;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;
import com.ga.erp.util.SystemUtil;

public class StoreOrderMainPage extends UnitPage{
	
  private static final long serialVersionUID = 1L;
  private ListView storeOrderList;
  String type = "";
  
  @Override
  public void initController() throws Exception {
	  
    type = this.modelMap.getRequest().getParameter("type");
    this.storeOrderList = new StoreOrderListView("storeOrderList", this.modelMap);
    this.storeOrderList.setQueryRows(2);

    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.storeOrderList, PageEvent.PARAMTYPE_PAGEQUERY);
    
    ActionButton action = this.storeOrderList.regAddAction("addStoreOrder","新增业务单",  (type!= null && "2".equals(type))  ?  "/include/store/storeOrderGuide.jsp" : "/store/storeorder_detail.htm");
    SubmitTool.submitToDialog(action, "addStoreOrder", "新增业务单",750,470,this.modelMap.getPreNavInfoStr());
    action.addParam("type", type);
    
    action = this.storeOrderList.regEditAction("editStoreOrder","查看/编辑", "/store/storeorder_detail.htm");
    SubmitTool.submitToDialog(action, "editStoreOrder", "查看/编辑",800,450,this.modelMap.getPreNavInfoStr());
    action.addParam("type", type);
    
    this.storeOrderList.regStateAction(this.getSelfUrl(), this, "STORE_ORDER");
    
  }
  
  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.storeOrderList);
    return layout;
  }
  
  public void pageLoad(QueryPageData queryPagedata) {
    try {
      int orderType = Integer.parseInt(type);
      if(orderType != SystemUtil.STORE_ORDER_DC_RECEIPT && orderType != SystemUtil.STORE_ORDER_DC_RETURN){
        this.storeOrderList.removeDbField("CODE","SETTLEMENT_ID");
      }
      StoreOrderBiz biz = new StoreOrderBiz(this.getUserACL());
      this.storeOrderList.bindData(biz.queryStoreOrderList(queryPagedata, this.storeOrderList.getFieldList(),type));
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM, "页面加载异常");
    }
  }
  
}
