package com.ga.erp.biz.store.returnOrder;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class StoreReturnDetailPage extends UnitPage {

	private static final long serialVersionUID = 1L;
	private StoreReturnFormView formView;
	private StoreReturnCommList returnCommList;
	
	@Override
	public void initController() throws Exception {
		
		 this.formView = new StoreReturnFormView("storeReturnDetail", this.modelMap);
		 this.returnCommList = new StoreReturnCommList("storeReturnComm",this.modelMap);
		 
		 PageEvent event = this.regPageLoadEvent("pageLoad");
		 event.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE,"STORE_RETURN_ORDER_ID");
		 event.addEventParam(this.returnCommList, PageEvent.PARAMTYPE_PAGEQUERY);
	}

	@Override
	public Layout initLayout() {
		FormLayout formLayOut = new FormLayout("",this.formView.getDataForm(),2);
	  	this.formView.getDataForm().setLayout(formLayOut);
	  	
	  	ViewPageLayout layout = new ViewPageLayout(this);
		layout.addControl("","退货单","退货单详情","",this.formView);
		layout.addControl("","退货单商品列表","商品列表","",this.returnCommList);
	    return layout;
	}
	
    public void pageLoad(Long id,QueryPageData queryPageData) {
		 try {
		     StoreReturnBiz biz  = new StoreReturnBiz(this.getUserACL());
		     for (DbField field : formView.getFieldList()) {
		    	 field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
		     }
		     this.formView.bindData(biz.queryStoreReturnDetail(id));
		     this.returnCommList.bindData(biz.queryStoreReturnComm(queryPageData, this.returnCommList.getFieldList(),id));
		    } catch(BizException ex) {
		         throw ex;
		    }  catch(Exception e) {
		          throw new BizException(BizException.SYSTEM,"页面加载异常");
		    }
		  }
	  
}
