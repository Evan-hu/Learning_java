package com.ga.erp.biz.stock.inventorydiffbill;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class DiffBillMainPage extends UnitPage{

	private static final long serialVersionUID = 1L;
	private ListView IDiffBillList;
	 
	@Override
	public void initController() throws Exception {
		
		IDiffBillList = new DiffBillListView("IDiffBillList", this.modelMap);
		
		PageEvent event = this.regPageLoadEvent("pageLoad");
		event.addEventParam(this.IDiffBillList, PageEvent.PARAMTYPE_PAGEQUERY);
		
//		ActionButton action = this.IDiffBillList.regAddAction("newContract","增加盘点差异单","/stock/diffbill_mtable.htm");
//		SubmitTool.submitToDialog(action,"newContract", "增加盘点差异单",800,300,this.modelMap.getPreNavInfoStr());
//		    
//		action = this.IDiffBillList.regEditAction("editContract","查看/编辑", "/stock/diffbill_detail.htm");
//		SubmitTool.submitToDialog(action, "editContract", "查看/编辑",800,330,this.modelMap.getPreNavInfoStr());
		
		 ActionButton  action = this.IDiffBillList.regEditAction("mtestOp","查看", "/stock/diffbill_mtable.htm");
     SubmitTool.submitToDialog(action,"mtestOp", "查看",1000,600,this.modelMap.getPreNavInfoStr());
		
		IDiffBillList.regStateAction(this.getSelfUrl(), this,"INVENTORY_DIFF_BILL");
	}

	@Override
	public Layout initLayout() {
		  ViewPageLayout layout = new ViewPageLayout(this);
		  layout.addControl(this.IDiffBillList);
		  return layout;
	}
	
	 public void pageLoad(QueryPageData queryPageData) {
	    try {
	    	  DiffBillBiz biz  = new DiffBillBiz(this.getUserACL());
	    	  int type = Integer.parseInt(this.modelMap.getRequest().getParameter("type"));
	        this.IDiffBillList.bindData(biz.queryIDiffBillList(queryPageData,this.IDiffBillList.getFieldList(),type));    
	    } catch(BizException ex) {
	       throw ex;
	    } catch(Exception e) {
	       throw new BizException(BizException.SYSTEM,"页面加载异常");
	    }
	  }
}
