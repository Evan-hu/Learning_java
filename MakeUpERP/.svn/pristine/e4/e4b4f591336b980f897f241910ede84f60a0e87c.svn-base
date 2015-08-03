package com.ga.erp.biz.store.storesalesorder;

import org.apache.click.ActionResult;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;
import com.ga.erp.biz.store.storesalesdetail.SalesDetailBiz;
import com.ga.erp.biz.store.storesalesdetail.SalesDetailListView;

public class SalesOrderDetailPage extends UnitPage {

  private static final long serialVersionUID = 1L;
  private FormView formView;
	private ListView salesDetailList;
	
	@Override
	public void initController() throws Exception {

		this.formView = new SalesOrderFormView("salesDetail", this.modelMap);
		this.salesDetailList = new SalesDetailListView("salesList",this.modelMap);
		this.salesDetailList.showPage(false);
		this.salesDetailList.showQuery(false);
		
		PageEvent event = this.regPageLoadEvent("pageLoad");
		event.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE,"STORE_SALES_ORDER_ID");
		event.addEventParam(this.salesDetailList,PageEvent.PARAMTYPE_PAGEQUERY);
		
		ActionButton action = new ActionButton(this.getClass(), "cancel", "取消",this.getSelfUrl(), null);
		SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());
		this.regPageActionButton(action, formView, salesDetailList);
		this.regPageEvent(action, "cancel");
	}

	@Override
	public Layout initLayout() {
		ViewPageLayout layout = new ViewPageLayout(this);
		FormLayout formLayout = new FormLayout("", this.formView.getDataForm(),	2);

		this.formView.getDataForm().setLayout(formLayout);
		layout.addControl("订单基本信息",this.formView);
	  layout.addControl("订单明细", this.salesDetailList);
		return layout;
	}

	public void pageLoad(Long salesOrderId, QueryPageData queryPageData) {
		try {
			SalesOrderBiz biz = new SalesOrderBiz(this.getUserACL());
			SalesDetailBiz sdBiz = new SalesDetailBiz(this.getUserACL());
			for (DbField field : formView.getFieldList()) {
				field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
			}
			this.formView.bindData(biz.querySalesOrderMap(salesOrderId));
			this.salesDetailList.bindData(sdBiz.querySalesDetailList(queryPageData,this.salesDetailList.getFieldList(),salesOrderId));

		} catch (BizException ex) {
			throw ex;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException(BizException.SYSTEM, "页面加载异常");
		}
	}

	public ActionResult cancel(ModelMap modelMap) {
		return this.createReturnJSON(true, "重新加载页面", false, false, "", "");
	}
	
}
