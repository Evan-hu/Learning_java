package com.ga.erp.biz.store.returnOrder;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class StoreReturnMainPage extends UnitPage{

	private static final long serialVersionUID = 1L;
	private ListView storeReturnList;
	 
	@Override
	public void initController() throws Exception {
		
		 storeReturnList = new StoreReturnListView("storeReturnList", this.modelMap);
		
	   PageEvent event = this.regPageLoadEvent("pageLoad");
	   event.addEventParam(this.storeReturnList, PageEvent.PARAMTYPE_PAGEQUERY);
		    
//		    ActionButton action = this.storeReturnList.regAddAction("newStoreReturn","增加退货订单","/store/returnOrder_detail.htm");
//		    SubmitTool.submitToDialog(action,"newStoreReturn", "增加退货订单",800,630,this.modelMap.getPreNavInfoStr());
//		    
	    ActionButton  action = this.storeReturnList.regEditAction("editStoreReturn","查看", "/store/returnOrder_detail.htm");
	    SubmitTool.submitToDialog(action, "editStoreReturn", "查看",800,650,this.modelMap.getPreNavInfoStr());
	}

	@Override
	public Layout initLayout() {
  	  ViewPageLayout layout = new ViewPageLayout(this);
      layout.addControl(this.storeReturnList);
      return layout;
	}
	
	 public void pageLoad(QueryPageData queryPageData) {
		    try {   
		      String state = this.modelMap.getRequest().getParameter("state");
		    	StoreReturnBiz biz  = new StoreReturnBiz(this.getUserACL());
		     this.storeReturnList.bindData(biz.queryStoreReturnList(queryPageData,this.storeReturnList.getFieldList(),null,state));    
		    } catch(BizException ex) {
		      throw ex;
		    } catch(Exception e) {
		      throw new BizException(BizException.SYSTEM,"页面加载异常");
		    }
		  }
	 

}
