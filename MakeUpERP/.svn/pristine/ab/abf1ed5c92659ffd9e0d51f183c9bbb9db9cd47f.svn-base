package com.ga.erp.biz.purchase.list;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class PurListMainPage extends UnitPage{

	private static final long serialVersionUID = 1L;
	private ListView purList;
	 
	@Override
	public void initController() throws Exception {
		
		purList = new PurListView("purList", this.modelMap);
		purList.setQueryRows(2);
		
		PageEvent event = this.regPageLoadEvent("pageLoad");
		event.addEventParam(this.purList, PageEvent.PARAMTYPE_PAGEQUERY);
		    
		ActionButton action = this.purList.regAddAction("newPurList","增加采购清单","/purchase/purList_detail.htm");
		SubmitTool.submitToDialog(action,"newPurList", "增加采购清单",800,330,this.modelMap.getPreNavInfoStr());
		    
		action = this.purList.regEditAction("editPurList","查看/编辑", "/purchase/purList_detail.htm");
		SubmitTool.submitToDialog(action, "editPurList", "查看/编辑",800,330,this.modelMap.getPreNavInfoStr());
	}

	@Override
	public Layout initLayout() {
		  ViewPageLayout layout = new ViewPageLayout(this);
		  layout.addControl(this.purList);
		  return layout;
	}
	
	 public void pageLoad(QueryPageData queryPageData) {
		    try {      
		    	PurListBiz biz  = new PurListBiz(this.getUserACL());
		    	this.purList.bindData(biz.queryPurList(queryPageData,this.purList.getFieldList(), null));    
		    } catch(BizException ex) {
		      throw ex;
		    } catch(Exception e) {
		      throw new BizException(BizException.SYSTEM,"页面加载异常");
		    }
		  }
	 

}
