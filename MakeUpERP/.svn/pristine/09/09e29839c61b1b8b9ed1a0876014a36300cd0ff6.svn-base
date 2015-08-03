package com.ga.erp.biz.settlement.costorder;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class CostOrderMainPage extends UnitPage{
	
  private static final long serialVersionUID = 1L;
  private ListView costOrderList;
  String type = "";
  @Override
  public void initController() throws Exception {
	  
    type = this.modelMap.getRequest().getParameter("type");
    this.costOrderList = new CostOrderListView("costOrderList", this.modelMap);
    this.costOrderList.setQueryRows(2);

    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.costOrderList, PageEvent.PARAMTYPE_PAGEQUERY);
    
    ActionButton action = this.costOrderList.regAddAction("addCostOrder","新增业务单", "/costorder/costorder_detail.htm");
    SubmitTool.submitToDialog(action, "addCostOrder", "新增业务单",800,300,this.modelMap.getPreNavInfoStr());
    action.addParam("type", type);
    
    action = this.costOrderList.regEditAction("editCostOrder","查看/编辑", "/costorder/costorder_detail.htm");
    SubmitTool.submitToDialog(action, "editCostOrder", "查看/编辑",800,420,this.modelMap.getPreNavInfoStr());
    action.addParam("type", type);
    
  }
  
  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.costOrderList);
    return layout;
  }
  
  public void pageLoad(QueryPageData queryPagedata) {
    try {
      CostOrderBiz biz = new CostOrderBiz(this.getUserACL());
      this.costOrderList.bindData(biz.queryCostOrderList(queryPagedata, this.costOrderList.getFieldList(),type,""));
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM, "页面加载异常");
    }
  }
  
}
