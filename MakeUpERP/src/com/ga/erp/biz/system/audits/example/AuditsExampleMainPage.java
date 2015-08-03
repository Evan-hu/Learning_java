package com.ga.erp.biz.system.audits.example;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;
import com.ga.erp.biz.system.audits.AuditsBiz;

public class AuditsExampleMainPage extends UnitPage {

	private static final long serialVersionUID = 1L;
	private ListView auditsExampleList;

	@Override
	public void initController() throws Exception {

		this.auditsExampleList = new AuditsExampleListView("auditsExampleList", this.modelMap);
		PageEvent event = this.regPageLoadEvent("pageLoad");
		event.addEventParam(this.auditsExampleList, PageEvent.PARAMTYPE_PAGEQUERY);
		
		ActionButton action = this.auditsExampleList.regEditAction("editAudeg","查看审核信息", "/system/audexample_detail.htm");
    SubmitTool.submitToDialog(action, "editAudeg", "查看审核信息",730,350,this.modelMap.getPreNavInfoStr());
	}

	@Override
	public Layout initLayout() {
		ViewPageLayout layout = new ViewPageLayout(this);
		layout.addControl(this.auditsExampleList);
		return layout;
	}

	public void pageLoad(QueryPageData queryPagedata) {
		try {
			AuditsBiz biz = new AuditsBiz(this.getUserACL());
			this.auditsExampleList.bindData(biz.queryAudExampleList(queryPagedata,this.auditsExampleList.getFieldList()));
		} catch (BizException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BizException(BizException.SYSTEM, "页面加载异常");
		}
	}
}
