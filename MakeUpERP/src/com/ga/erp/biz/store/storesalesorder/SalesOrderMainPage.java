package com.ga.erp.biz.store.storesalesorder;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class SalesOrderMainPage extends UnitPage {

	private static final long serialVersionUID = 1L;
	private ListView salesOrderListView;

	@Override
	public void initController() throws Exception {

		salesOrderListView = new SalesOrderListView("salesOrderListView", this.modelMap);
		this.salesOrderListView.setQueryRows(2);

		PageEvent event = this.regPageLoadEvent("pageLoad");
		event.addEventParam(this.salesOrderListView,PageEvent.PARAMTYPE_PAGEQUERY);

		ActionButton action = this.salesOrderListView.regEditAction("editSalesOrder", "查看订单",
				"/store/storesalesorder/salesorder_detail.htm");
		SubmitTool.submitToDialog(action, "editSalesOrder", "查看订单", 900, 630,
				this.modelMap.getPreNavInfoStr());	
		
	}

	@Override
	public Layout initLayout() {
		ViewPageLayout layout = new ViewPageLayout(this);
		layout.addControl(this.salesOrderListView);
		return layout;
	}

	public void pageLoad(QueryPageData queryPageData) {
		try {
			SalesOrderBiz biz = new SalesOrderBiz(this.getUserACL());
			StringBuilder builder = new StringBuilder("");
      PageResult pr = biz.querySalesOrderList(queryPageData,this.salesOrderListView.getFieldList(), builder);
      this.salesOrderListView.setQueryTip(builder.toString());
      this.salesOrderListView.bindData(pr);
		} catch (BizException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BizException(BizException.SYSTEM, "页面加载异常");
		}
	}
}
