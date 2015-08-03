package com.ga.erp.biz.store.storewalletlog;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class WalletLogMainPage extends UnitPage {

	private static final long serialVersionUID = 1L;
	private ListView walletLogList;

	@Override
	public void initController() throws Exception {

		this.walletLogList = new WalletLogListView("walletLogList",this.modelMap);
		this.walletLogList.setQueryRows(1);

		PageEvent event = this.regPageLoadEvent("pageLoad");
		event.addEventParam(this.walletLogList, PageEvent.PARAMTYPE_PAGEQUERY);

		ActionButton action = this.walletLogList.regEditAction("lookWalletLog",
				"查看详细", "/store/storewalletlog/walletlog_detail.htm");
		SubmitTool.submitToDialog(action, "lookWalletLog", "查看详细", 750, 300,
				this.modelMap.getPreNavInfoStr());
	}

	@Override
	public Layout initLayout() {
		ViewPageLayout layout = new ViewPageLayout(this);
		layout.addControl(this.walletLogList);
		return layout;
	}

	public void pageLoad(QueryPageData queryPagedata) {
		try {
			WalletLogBiz biz = new WalletLogBiz(this.getUserACL());
			this.walletLogList.bindData(biz.queryWalletLogList(queryPagedata,this.walletLogList.getFieldList(), null));
		} catch (BizException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BizException(BizException.SYSTEM, "页面加载异常");
		}
	}

}
