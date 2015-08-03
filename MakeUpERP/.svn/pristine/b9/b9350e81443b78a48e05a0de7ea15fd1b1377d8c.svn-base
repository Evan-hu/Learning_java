package com.ga.erp.biz.store.storepos;

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

public class PosMainPage extends UnitPage {

	private static final long serialVersionUID = 1L;
	private ListView posList;

	@Override
	public void initController() throws Exception {

		this.posList = new PosListView("posList", this.modelMap);
		this.posList.setQueryRows(2);

		PageEvent event = this.regPageLoadEvent("pageLoad");
		event.addEventParam(this.posList, PageEvent.PARAMTYPE_PAGEQUERY);

		ActionButton action = this.posList.regAddAction("newPos", "新建",
				"/store/storepos/pos_detail.htm");
		SubmitTool.submitToDialog(action, "newPos", "新建", 800, 270,
				this.modelMap.getPreNavInfoStr());

		action = this.posList.regEditAction("editPos", "查看/编辑",
				"/store/storepos/pos_detail.htm");
		SubmitTool.submitToDialog(action, "editPos", "查看/编辑", 1000, 360,
				this.modelMap.getPreNavInfoStr());
		
		posList.regStateAction(this.getSelfUrl(), this,"POS");
	}

	@Override
	public Layout initLayout() {
		ViewPageLayout layout = new ViewPageLayout(this);
		layout.addControl(this.posList);
		return layout;
	}

	public void pageLoad(QueryPageData queryPageData) {
		try {
			PosBiz biz = new PosBiz(this.getUserACL());
			StringBuilder builder = new StringBuilder("");
			PageResult pr = biz.queryPosList(null, queryPageData,this.posList.getFieldList(), builder);
			this.posList.setQueryTip(builder.toString());
			this.posList.bindData(pr);
		} catch (BizException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BizException(BizException.SYSTEM, "页面加载异常");
		}
	}

}
