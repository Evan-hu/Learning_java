package com.ga.erp.biz.settlement.settlement;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class SettlementMainPage extends UnitPage {

  private static final long serialVersionUID = 1L;
  private ListView settlementListView;
  
  @Override
  public void initController() throws Exception {
    
    settlementListView = new SettlementListView("settlementListView",this.modelMap);
    this.settlementListView.setQueryRows(3);
    
    PageEvent event  = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.settlementListView,PageEvent.PARAMTYPE_PAGEQUERY);

    ActionButton action = this.settlementListView.regEditAction("editSetlement", "查看详情", "/settlement/settlement_detail.htm");
    SubmitTool.submitToDialog(action, "editSetlement","查看详情",900, 600,this.modelMap.getPreNavInfoStr());
   }

  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.settlementListView);
    return layout;	
  }
  
  public void pageLoad(QueryPageData queryPageData) {
    try {      
      SettlementBiz biz  = new SettlementBiz(this.getUserACL());
      //最后的0表示查询所有
      this.settlementListView.bindData(biz.querySettlementList(queryPageData,this.settlementListView.getFieldList(), "", 0));      
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"页面加载异常");
    }
  }
}
