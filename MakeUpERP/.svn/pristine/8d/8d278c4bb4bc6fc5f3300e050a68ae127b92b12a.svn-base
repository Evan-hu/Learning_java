package com.ga.erp.biz.system.audits.role;

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

public class AuditsRoleDetailPage extends UnitPage {

	private static final long serialVersionUID = 1L;
	private FormView auditsRoleForm;

	@Override
	public void initController() throws Exception {
		this.auditsRoleForm = new AuditsRoleFormView("auditsRoleForm", this.modelMap);

		PageEvent event = this.regPageLoadEvent("pageLoad");
		event.addEventParam(this.auditsRoleForm, PageEvent.PARAMTYPE_QUERYVALUE,"AUDITING_ROLE_ID");
		
		ActionButton action = new ActionButton(this.getClass(), "saveAuditingRole","保存", this.getSelfUrl(), null);
    action.bindForm(this.auditsRoleForm.getViewID(), true);
    SubmitTool.submitToParentTable(action,this.modelMap.getPreNavInfoStr(),"auditsRoleList");

    action.addParam(GaConstant.FIXPARAM_EDITMODE,this.modelMap.getPageEditMode());
    this.auditsRoleForm.regAction(action); 
    
//    event = this.regPageEvent(action, "saveAuditingRole");
//    event.addEventParam(this.auditsRoleForm, PageEvent.PARAMTYPE_DATAMAP);
	}

	@Override
	public Layout initLayout() {
		FormLayout formLayout = new FormLayout("",this.auditsRoleForm.getDataForm(), 2);
		this.auditsRoleForm.getDataForm().setLayout(formLayout);
		ViewPageLayout layout = new ViewPageLayout(this);
		layout.addControl(this.auditsRoleForm);
		return layout;
	}

	public void pageLoad(Long AuditingRoleId) {
	  Map<String, Object> map = this.auditsRoleForm.getViewParam();
		try {
			if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
				AuditsRoleBiz biz = new AuditsRoleBiz(this.getUserACL());
				Map<String, Object> valuesMap = biz.queryAuditingRoleDetail(AuditingRoleId, this.auditsRoleForm.getFieldList());
				this.auditsRoleForm.bindData(valuesMap);
			}else {
			  this.auditsRoleForm.bindData(map);
      }
		} catch (BizException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BizException(BizException.SYSTEM, "页面加载异常");
		}
	}

	public ActionResult saveAuditingRole(Map<String, Object> valueMap) {
	  AuditsRoleBiz biz = new AuditsRoleBiz(this.getUserACL());
		if (this.getModelMap().getPageEditMode() == GaConstant.EDITMODE_NEW) {
			biz.saveAuditingRole(valueMap, true);
		} else if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
			biz.saveAuditingRole(valueMap, false);
		}
		return this.createReturnJSON(true, "保存成功", true, false, "auditsRoleList","");
	}
}
