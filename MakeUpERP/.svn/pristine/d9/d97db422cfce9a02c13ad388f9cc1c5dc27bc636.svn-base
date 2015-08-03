package com.ga.erp.biz.settlement.settlement;

import com.ga.click.control.GaConstant;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;

public class SettlementLogDetailPage extends UnitPage {

	private static final long serialVersionUID = 1L;
	private SettlementLogFormView formView;

	  @Override
	  public void initController() throws Exception {

	    this.formView = new SettlementLogFormView("settlementLogDetail", this.modelMap);
	    PageEvent event = this.regPageLoadEvent("pageLoad");
	    event.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE,"SETTLEMENT_LOG_ID");
	}

	@Override
	public Layout initLayout() {
		FormLayout formLayOut = new FormLayout("",this.formView.getDataForm(),2);
		formLayOut.mergeCellField("auto", 3, 0, 1, "NOTE");
		
	  	this.formView.getDataForm().setLayout(formLayOut);
	  	ViewPageLayout layout = new ViewPageLayout(this);
	  	layout.addControl(this.formView);
	    this.formView.getDataForm().setLayout(formLayOut);
	    return layout;
	}
	
	  public void pageLoad(Long id) {
		    try {
		    	 if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
		    		 SettlementBiz biz  = new SettlementBiz(this.getUserACL());
		    		 this.formView.bindData(biz.quetySettlementLogMap(id, this.formView.getFieldList()));
		    	 }
		    } catch(BizException ex) {
		         throw ex;
		    }  catch(Exception e) {
		          throw new BizException(BizException.SYSTEM,"“≥√Êº”‘ÿ“Ï≥£");
		    }
		  }
}
	  

