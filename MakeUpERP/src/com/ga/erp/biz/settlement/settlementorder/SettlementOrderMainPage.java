package com.ga.erp.biz.settlement.settlementorder;

import org.apache.click.ActionResult;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;
import com.ga.erp.biz.settlement.settlement.SettlementBiz;
import com.ga.erp.biz.settlement.settlement.SettlementListView;

@SuppressWarnings("serial")
public class SettlementOrderMainPage extends UnitPage{
	
  private ListView settlementListView;
  private String type;
  @Override
  public void initController() throws Exception {
	  
    this.settlementListView = new SettlementListView("settlementOrderListView", this.modelMap);
    this.settlementListView.setQueryRows(3);
    type = this.modelMap.getRequest().getParameter("type");
    
    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.settlementListView, PageEvent.PARAMTYPE_PAGEQUERY);
    
    ActionButton action = this.settlementListView.regEditAction("editSettlementOrder","查看详情", "/settlement/settlement_detail.htm");
    SubmitTool.submitToDialog(action, "editSettlementOrder", "查看详情",800,480,this.modelMap.getPreNavInfoStr());
    
    action = this.settlementListView.regEditAction("payment","确认付款",this.getSelfUrl());
    SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());
    action.bindTableRowData(this.settlementListView.getViewID());
    event = this.regPageEvent(action, "payment");
    event.addEventParam(this.settlementListView, PageEvent.PARAMTYPE_QUERYVALUE,"SETTLEMENT_ID");
  }
  
  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.settlementListView);
    return layout;
  }
  
  public void pageLoad(QueryPageData queryPagedata) {
    try {
        SettlementBiz biz = new SettlementBiz(this.getUserACL());
        //下面方法中的1表示查询未付款的记录
        this.settlementListView.bindData(biz.querySettlementList(queryPagedata, this.settlementListView.getFieldList(), type, 1));
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM, "页面加载异常");
    }
  }
  
  public ActionResult payment(Long id) {
    SettlementBiz biz = new SettlementBiz(this.getUserACL());
    biz.payment(id);
    return createReturnJSON(true, "确认付款成功！", true, false, this.settlementListView.getViewID(), "");
  }
}
