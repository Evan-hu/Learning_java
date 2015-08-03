package com.ga.erp.biz.member.viplog;

import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class VipLogMainPage extends UnitPage {

	private static final long serialVersionUID = 1L;
	private ListView vipLogList;

	@Override
	public void initController() throws Exception {

		this.vipLogList = new VipLogListView("vipLogList", this.modelMap);
		this.vipLogList.setQueryRows(1);

		PageEvent event = this.regPageLoadEvent("pageLoad");
		event.addEventParam(this.vipLogList, PageEvent.PARAMTYPE_PAGEQUERY);
	}

	@Override
	public Layout initLayout() {
		ViewPageLayout layout = new ViewPageLayout(this);
		layout.addControl(this.vipLogList);
		return layout;
	}

	public void pageLoad(QueryPageData queryPagedata) {
		try {
			VipLogBiz biz = new VipLogBiz(this.getUserACL());
			this.vipLogList.bindData(biz.queryVipLogList(queryPagedata,this.vipLogList.getFieldList(),null));
		} catch (BizException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BizException(BizException.SYSTEM, "“≥√Êº”‘ÿ“Ï≥£");
		}
	}

}
