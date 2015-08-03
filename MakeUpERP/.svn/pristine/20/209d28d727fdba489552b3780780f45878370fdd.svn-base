package com.ga.erp.biz.store.storewalletlog;

import java.util.Map;

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
import com.ga.click.mvc.ModelMap;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;

public class WalletLogDetailPage extends UnitPage {

	private static final long serialVersionUID = 1L;
	private FormView walletLogForm;

	@Override
	public void initController() throws Exception {

		this.walletLogForm = new WalletLogFormView("walletLogForm",this.modelMap);

		PageEvent event = this.regPageLoadEvent("pageLoad");
		event.addEventParam(this.walletLogForm, PageEvent.PARAMTYPE_QUERYVALUE,"STORE_WALLET_LOG_ID");

		ActionButton action = new ActionButton(this.getClass(), "cancel", "取消",this.getSelfUrl(), null);
		SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());
		this.walletLogForm.regAction(action);
		this.regPageEvent(action, "cancel");
	}

	@Override
	public Layout initLayout() {
		FormLayout formLayout = new FormLayout("",
				this.walletLogForm.getDataForm(), 2);
		this.walletLogForm.getDataForm().setLayout(formLayout);
		ViewPageLayout layout = new ViewPageLayout(this);
		layout.addControl(this.walletLogForm);
		return layout;
	}

	public void pageLoad(Long walletLogId) {
		try {
		  WalletLogBiz biz = new WalletLogBiz(this.getUserACL());
      Map<String, Object> valuesMap = biz.queryWalletLogDetail(walletLogId, this.walletLogForm.getFieldList());
      for (DbField field : this.walletLogForm.getFieldList()) {
        field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
      }
      this.walletLogForm.bindData(valuesMap);
		} catch (BizException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BizException(BizException.SYSTEM, "页面加载异常");
		}
	}

	public ActionResult cancel(ModelMap modelMap) {
		return this.createReturnJSON(true, "重新加载页面", true, false, "opList", "");
	}
}
