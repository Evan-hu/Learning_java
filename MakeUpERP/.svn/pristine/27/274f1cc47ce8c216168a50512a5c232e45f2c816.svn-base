package com.ga.erp.biz.member.viprule;

import java.util.Map;

import org.apache.click.ActionResult;

import com.ga.click.control.GaConstant;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;

public class VipRuleDetailPage extends UnitPage {

	private static final long serialVersionUID = 1L;
	private FormView vipRuleForm;

	@Override
	public void initController() throws Exception {

		this.vipRuleForm = new VipRuleFormView("vipRuleForm", this.modelMap);

		PageEvent event = this.regPageLoadEvent("pageLoad");
		event.addEventParam(this.vipRuleForm, PageEvent.PARAMTYPE_QUERYVALUE,
				"VIP_RULE_ID");

		ActionButton action = new ActionButton(this.getClass(), "saveVipRule",
				"保存", this.getSelfUrl(), null);
		action.bindForm(this.vipRuleForm.getViewID(), true);
		SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());

		action.addParam(GaConstant.FIXPARAM_EDITMODE,
				this.modelMap.getPageEditMode());
		this.vipRuleForm.regAction(action);

		event = this.regPageEvent(action, "saveVipRule");
		event.addEventParam(this.vipRuleForm, PageEvent.PARAMTYPE_DATAMAP);
	}

	@Override
	public Layout initLayout() {
		FormLayout formLayout = new FormLayout("",
				this.vipRuleForm.getDataForm(), 2);
		this.vipRuleForm.getDataForm().setLayout(formLayout);
		ViewPageLayout layout = new ViewPageLayout(this);
		layout.addControl(this.vipRuleForm);
		return layout;
	}

	public void pageLoad(Long VipRuleId) {
		try {
			if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
				VipRuleBiz biz = new VipRuleBiz(this.getUserACL());
				Map<String, Object> valuesMap = biz.queryVipRuleDetail(
						VipRuleId, this.vipRuleForm.getFieldList());
				this.vipRuleForm.bindData(valuesMap);
			}
		} catch (BizException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BizException(BizException.SYSTEM, "页面加载异常");
		}
	}

	public ActionResult saveVipRule(Map<String, Object> valueMap) {
		VipRuleBiz biz = new VipRuleBiz(this.getUserACL());
		if (this.getModelMap().getPageEditMode() == GaConstant.EDITMODE_NEW) {
			biz.saveVipRule(valueMap, true);
		} else if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
			biz.saveVipRule(valueMap, false);
		}
		return this.createReturnJSON(true, "保存成功", true, false, "vipRuleList",
				"");
	}
}
