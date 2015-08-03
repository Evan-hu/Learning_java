package com.ga.erp.biz.stock.inventorydiffbill.comm;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class CommMainPage extends UnitPage{

	private static final long serialVersionUID = 1L;
	private ListView DBCommList;
	 
	@Override
	public void initController() throws Exception {
		
		DBCommList = new CommListView("DBCommList", this.modelMap);
		
		PageEvent event = this.regPageLoadEvent("pageLoad");
		event.addEventParam(this.DBCommList, PageEvent.PARAMTYPE_PAGEQUERY);
		    	    
		ActionButton action = this.DBCommList.regAddAction("newDBComm","增加盘点商品","/stock/billcomm_detail.htm");
		SubmitTool.submitToDialog(action,"newDBComm", "增加盘点商品",800,300,this.modelMap.getPreNavInfoStr());
		    
		action = this.DBCommList.regEditAction("editDBComm","查看/编辑", "/stock/billcomm_detail.htm");
		SubmitTool.submitToDialog(action, "editDBComm", "查看/编辑",800,300,this.modelMap.getPreNavInfoStr());
	}

	@Override
	public Layout initLayout() {
		  ViewPageLayout layout = new ViewPageLayout(this);
		  layout.addControl(this.DBCommList);
		  return layout;
	}
	
	 public void pageLoad(QueryPageData queryPageData) {
		    try {      
		    	CommBiz biz  = new CommBiz(this.getUserACL());
		     this.DBCommList.bindData(biz.queryDBCommList(queryPageData,this.DBCommList.getFieldList(),null));    
		    } catch(BizException ex) {
		      throw ex;
		    } catch(Exception e) {
		      throw new BizException(BizException.SYSTEM,"页面加载异常");
		    }
		  }
	 

}
