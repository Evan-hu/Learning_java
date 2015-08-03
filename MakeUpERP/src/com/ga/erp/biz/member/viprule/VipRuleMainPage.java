package com.ga.erp.biz.member.viprule;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class VipRuleMainPage extends UnitPage {

	private static final long serialVersionUID = 1L;
	private ListView vipRuleList;

	@Override
	public void initController() throws Exception {

		this.vipRuleList = new VipRuleListView("vipRuleList", this.modelMap);
		this.vipRuleList.showQuery(false);
		this.vipRuleList.showPage(false);

		PageEvent event = this.regPageLoadEvent("pageLoad");
		event.addEventParam(this.vipRuleList, PageEvent.PARAMTYPE_PAGEQUERY);

		ActionButton action = this.vipRuleList.regAddAction("newVipRule", "新增",
				"/member/viprule/viprule_detail.htm");
		SubmitTool.submitToDialog(action, "newVipRule", "新建", 620, 200,
				this.modelMap.getPreNavInfoStr());

		action = this.vipRuleList.regEditAction("editVipRule", "查看/编辑",
				"/member/viprule/viprule_detail.htm");
		SubmitTool.submitToDialog(action, "editVipRule", "查看/编辑", 620, 200,
				this.modelMap.getPreNavInfoStr());
	}

	@Override
	public Layout initLayout() {
		ViewPageLayout layout = new ViewPageLayout(this);
		layout.addControl(this.vipRuleList);
		return layout;
	}

	public void pageLoad(QueryPageData pageData) {
		try {
			VipRuleBiz biz = new VipRuleBiz(this.getUserACL());
			this.vipRuleList.bindData(biz.queryVipRuleList(pageData,this.vipRuleList.getFieldList()));
		} catch (BizException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BizException(BizException.SYSTEM, "页面加载异常");
		}
	}

}
