package com.ga.erp.biz.store.storesalesdetail;

import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class SalesDetailMainPage extends UnitPage {

	private static final long serialVersionUID = 1L;
	private ListView salesDetailList;

	@Override
	public void initController() throws Exception {

		this.salesDetailList = new SalesDetailListView("salesDetailList",this.modelMap);
		this.salesDetailList.setQueryRows(1);

		PageEvent event = this.regPageLoadEvent("pageLoad");
		event.addEventParam(this.salesDetailList, PageEvent.PARAMTYPE_PAGEQUERY);

	}

	@Override
	public Layout initLayout() {
		ViewPageLayout layout = new ViewPageLayout(this);
		layout.addControl(this.salesDetailList);
		return layout;
	}

	public void pageLoad(QueryPageData queryPagedata) {
		try {
			SalesDetailBiz biz = new SalesDetailBiz(this.getUserACL());
			this.salesDetailList.bindData(biz.querySalesDetailList(
					queryPagedata, this.salesDetailList.getFieldList(),null));
		} catch (BizException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BizException(BizException.SYSTEM, "“≥√Êº”‘ÿ“Ï≥£");
		}
	}
}
