package com.ga.erp.biz.settlement.settlement;

import org.apache.click.ActionResult;

import com.ga.click.control.GaConstant;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.ClickUtil;

public class SettlementDetailPage extends UnitPage {

	private static final long serialVersionUID = 1L;
	private SettlementFormView formView;
	private SettlementLogListView logListView;

	  @Override
	  public void initController() throws Exception {

	    this.formView = new SettlementFormView("settlementDetail", this.modelMap);
	    this.logListView = new SettlementLogListView("settlementLogList", this.modelMap);
	    this.logListView.setQueryRows(2);
	    
	    PageEvent event = this.regPageLoadEvent("pageLoad");
	    event.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE,"SETTLEMENT_ID");
	    event.addEventParam(this.logListView, PageEvent.PARAMTYPE_PAGEQUERY);
	    
	    ActionButton action = this.logListView.regEditAction("editSetlementLog", "查看详情", "/settlement/settlement_log_detail.htm");
	    SubmitTool.submitToDialog(action, "editSetlementLog","查看详情",860, 300,this.modelMap.getPreNavInfoStr());
	    

	    
	    event = this.regListViewLoadEvent(this.logListView, "reloadLog");
	    event.addEventParam(this.logListView,PageEvent.PARAMTYPE_PAGEQUERY); 
	    event.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE, "SETTLEMENT_ID");
	}

	@Override
	public Layout initLayout() {
		FormLayout formLayOut = new FormLayout("",this.formView.getDataForm(),2);
		formLayOut.mergeCellField("auto", 5, 0, 1, "NOTE");
		
	  	this.formView.getDataForm().setLayout(formLayOut);
	  	ViewPageLayout layout = new ViewPageLayout(this);
	  	layout.addControl(this.formView);
	    this.formView.getDataForm().setLayout(formLayOut);
	    layout.addControl("查看详情","", formView);
      layout.addControl("结算日志","", logListView);
	    return layout;
	}
	
	  public void pageLoad(Long id, QueryPageData queryPageData) {
		    try {
		    	 if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
		    		 SettlementBiz biz  = new SettlementBiz(this.getUserACL());
		    		 this.formView.bindData(biz.quetySettlementMap(id, this.formView.getFieldList()));
		    		 this.logListView.bindData(biz.quetySettlementLogList(id, this.logListView.getFieldList(), queryPageData));
		    	 }
		    } catch(BizException ex) {
		         throw ex;
		    }  catch(Exception e) {
		          throw new BizException(BizException.SYSTEM,"页面加载异常");
		    }
		  }
	  
	  public ActionResult reloadLog(QueryPageData pageData, Long id) {
			try {
			  SettlementBiz biz = new SettlementBiz(this.getUserACL());
			  if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
			      this.logListView.bindData(biz.quetySettlementLogList(id, this.logListView.getFieldList(), pageData));
			  }
			  ClickUtil.setControlLayoutH(this.logListView.getViewControl(), 170);
			    return this.linkView(this.logListView);
			  } catch(BizException e) {
			     throw e;
			  } catch(Exception ex) {
			     throw new BizException("加载页面异常");
			 }
		  }
}
	  

