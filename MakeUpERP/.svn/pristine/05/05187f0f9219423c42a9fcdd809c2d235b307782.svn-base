package com.ga.erp.biz.member.activelog;

import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class ActiveLogMainPage extends UnitPage {

	private static final long serialVersionUID = 1L;
	private ListView activeLogList;

	@Override
	public void initController() throws Exception {

		this.activeLogList = new ActiveLogListView("activeLogList",
				this.modelMap);
		this.activeLogList.setQueryRows(1);

		PageEvent event = this.regPageLoadEvent("pageLoad");
		event.addEventParam(this.activeLogList, PageEvent.PARAMTYPE_PAGEQUERY);
	}

	@Override
	public Layout initLayout() {
		ViewPageLayout layout = new ViewPageLayout(this);
		layout.addControl(this.activeLogList);
		return layout;
	}

	public void pageLoad(QueryPageData queryPagedata) {
		try {
			ActiveLogBiz biz = new ActiveLogBiz(this.getUserACL());
			this.activeLogList.bindData(biz.queryActLogList(queryPagedata,this.activeLogList.getFieldList(),null));
		} catch (BizException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BizException(BizException.SYSTEM, "“≥√Êº”‘ÿ“Ï≥£");
		}
	}

}
