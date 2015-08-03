package com.ga.erp.biz.content.ad;

import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

/**
 * 过期广告
 */
public class AdInvalidMainPage extends UnitPage {

	private static final long serialVersionUID = 2L;
	private ListView adList;

	@Override
	public void initController() throws Exception {
		this.adList = new AdListView("adList", this.modelMap);
		this.adList.setQueryRows(3);
		
		PageEvent event = this.regPageLoadEvent("pageLoad");
	    event.addEventParam(this.adList, PageEvent.PARAMTYPE_PAGEQUERY);
	}

	@Override
	public Layout initLayout() {
		ViewPageLayout layout = new ViewPageLayout(this);
	    layout.addControl(this.adList);
	    return layout;
	}
	
	public void pageLoad(QueryPageData queryPagedata) {
	    try {
	    	AdBiz biz = new AdBiz(this.getUserACL());
	    	this.adList.bindData(biz.queryInvalidAdList(queryPagedata, this.adList.getFieldList()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException("查询失败，请稍后再试");
		}
	}

}
