package com.ga.erp.biz.system.audits.example;

import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.StringDecorator;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.system.audits.AuditsBiz;
import com.ga.erp.biz.system.audits.AuditsFormView;
import com.ga.erp.biz.system.audits.AuditsListView;

public class AuditsExampleDetailPage extends UnitPage {

	private static final long serialVersionUID = 1L;
	private AuditsExampleFormView auditsExampleFormView;
	private ListView auditsList;
	@Override
	public void initController() throws Exception {
		this.auditsExampleFormView = new AuditsExampleFormView("auditsExampleFormView", this.modelMap);
	    PageEvent event = this.regPageLoadEvent("pageLoad");
	    event.addEventParam(this.auditsExampleFormView, PageEvent.PARAMTYPE_QUERYVALUE,"AUDITING_EXAMPLE_ID");
	    this.auditsList = new AuditsListView("auditsList",this.modelMap);
	    auditsList.showPage(false);
	    auditsList.showQuery(false);
//	    event  = this.regPageLoadEvent("pageLoad");
	    ActionButton action = this.auditsList.regEditAction("eidtAudit", "查看", "/system/audits_detail.htm");
	    SubmitTool.submitToDialog(action, "eidtAudit","查看",700, 320,this.modelMap.getPreNavInfoStr());
	}

	@Override
	public Layout initLayout() {
		ViewPageLayout layout = new ViewPageLayout(this);
	    FormLayout formLayout = new FormLayout("",this.auditsExampleFormView.getDataForm(),2);
	    formLayout.mergeCellField("auto",0, 0, 2, "OBJECT_NAME");

	    this.auditsExampleFormView.getDataForm().setLayout(formLayout);
		layout.addControl("审核基本信息","",this.auditsExampleFormView);
	    layout.addControl("审核详情","", this.auditsList);
	    
		return layout;
	}

	public void pageLoad(Long id) {
		try {
			AuditsBiz biz = new AuditsBiz(this.getUserACL());
			this.auditsList.bindData(biz.queryAuditngForExampleId(id));
			Map<String,Object> audExampleMap = biz.queryAudExampleDetail(id);
			DbField filed = this.auditsExampleFormView.getFieldByCode("OBJECT_NAME");
			ActionButton btn = filed.getColumnDecorator().getButton();
			if(!GaUtil.isNullStr( (String)audExampleMap.get("PARAM_VALUE"))){
			  btn.setAttribute("paramValue", (String)audExampleMap.get("PARAM_VALUE"));
			}
			this.auditsExampleFormView.bindData(audExampleMap);
		} catch (BizException ex) {
			throw ex;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizException(BizException.SYSTEM, "页面加载异常");
		}
	}
}
