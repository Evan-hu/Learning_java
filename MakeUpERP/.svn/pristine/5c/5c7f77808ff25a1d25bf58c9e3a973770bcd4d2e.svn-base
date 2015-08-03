package com.ga.erp.biz.system.audits.config;

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
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.ClickUtil;
import com.ga.erp.biz.system.audits.role.AuditsRoleBiz;
import com.ga.erp.biz.system.audits.role.AuditsRoleListView;

public class AudConfigDetailPage extends UnitPage {

		private static final long serialVersionUID = 1L;
		private FormView audConfigForm;
		private ListView auditsRoleList;

		@Override
		public void initController() throws Exception {

			this.audConfigForm = new AudConfigFormView("audConfigForm", this.modelMap);
			this.auditsRoleList = new AuditsRoleListView("auditsRoleList", this.modelMap);
		  this.auditsRoleList.showQuery(false);
			
			PageEvent event = this.regPageLoadEvent("pageLoad");
			event.addEventParam(this.audConfigForm, PageEvent.PARAMTYPE_QUERYVALUE,"AUDITING_CONFIG_ID");

			ActionButton action = new ActionButton(this.getClass(), "saveAudConfig","保存", this.getSelfUrl(), null);
			action.bindForm(this.audConfigForm.getViewID(), true);
			SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());

			action.addParam(GaConstant.FIXPARAM_EDITMODE,this.modelMap.getPageEditMode());
			this.audConfigForm.regAction(action);

			event = this.regPageEvent(action, "saveAudConfig");
			event.addEventParam(this.audConfigForm, PageEvent.PARAMTYPE_DATAMAP);
			
		  action = this.auditsRoleList.regAddAction("newRole", "新建","/system/AuditsRole_detail.htm");
		  SubmitTool.submitToDialog(action, "newRole", "新建", 720, 250,this.modelMap.getPreNavInfoStr());
		  action.bindViewParam(this.audConfigForm, "AUDITING_CONFIG_ID", "AUDITING_CONFIG_ID", false);
      action.bindViewParam(this.audConfigForm, "SYS_CODE_NAME", "AUDITING_CONFIG_ID", false);
		  
		  action = this.auditsRoleList.regEditAction("editRole", "编辑", "/system/AuditsRole_detail.htm");
		  SubmitTool.submitToDialog(action, "editRole", "编辑", 720, 260, this.modelMap.getPreNavInfoStr());
		  
		  event = this.regListViewLoadEvent(this.auditsRoleList, "reloadAuditsRoleList");
	    event.addEventParam(this.audConfigForm, PageEvent.PARAMTYPE_QUERYVALUE,"AUDITING_CONFIG_ID");
	    event.addEventParam(this.auditsRoleList, PageEvent.PARAMTYPE_PAGEQUERY);
		}

		@Override
		public Layout initLayout() {
			FormLayout formLayout = new FormLayout("", this.audConfigForm.getDataForm(),2);
			this.audConfigForm.getDataForm().setLayout(formLayout);
			ViewPageLayout layout = new ViewPageLayout(this);
			layout.addControl(this.audConfigForm);
			layout.addControl("基本信息", "", this.audConfigForm);
			if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
				layout.addControl("审核角色", "", this.auditsRoleList);
			}
			return layout;
		}

		public void pageLoad(Long audConfigId) {
			try {   
			  if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
				  AudConfigBiz biz = new AudConfigBiz(this.getUserACL()); 
				  this.audConfigForm.bindData(biz.queryAudConfigDetail(audConfigId, this.audConfigForm.getFieldList()));
				  AuditsRoleBiz aBiz = new AuditsRoleBiz(this.getUserACL());
				  this.auditsRoleList.bindData(aBiz.queryAuditsRoleList(new QueryPageData(),this.auditsRoleList.getFieldList(), audConfigId));
			  }
		    } catch(BizException ex) {
		      throw ex;
		    } catch(Exception e) {
		      throw new BizException(BizException.SYSTEM,"页面加载异常");
		    }
		  }

		public ActionResult saveAudConfig(Map<String, Object> valueMap) {
			AudConfigBiz biz = new AudConfigBiz(this.getUserACL());
			if (this.getModelMap().getPageEditMode() == GaConstant.EDITMODE_NEW) {
				biz.saveAudConfig(valueMap, true);
			} else if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
				biz.saveAudConfig(valueMap, false);
			}
			return this.createReturnJSON(true, "保存成功", true, false,"audConfigList", "");
		}
		
		public ActionResult reloadAuditsRoleList(Long audConfigId ,QueryPageData pageData) {
	    try {
	      AuditsRoleBiz biz = new AuditsRoleBiz(this.getUserACL());
	      ClickUtil.setControlLayoutH(this.auditsRoleList.getViewControl(), 100);
	      this.auditsRoleList.bindData(biz.queryAuditsRoleList(pageData ,this.auditsRoleList.getFieldList(), audConfigId));
	      return this.linkView(this.auditsRoleList);
	    } catch (BizException e) {
	      throw e;
	    } catch (Exception ex) {
	      throw new BizException("加载页面异常");
	    }
	  }
		
	}
