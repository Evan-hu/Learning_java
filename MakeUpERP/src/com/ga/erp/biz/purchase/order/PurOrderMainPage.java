package com.ga.erp.biz.purchase.order;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.ClickUtil;

public class PurOrderMainPage extends UnitPage{

	private static final long serialVersionUID = 1L;
	private ListView purList;
	String type = ""; 
	
	@Override
	public void initController() throws Exception {
		
	  type = this.modelMap.getRequest().getParameter("orderType");
		purList = new PurOrderListView("purOrderList", this.modelMap);
		purList.setExport(true);
		purList.setQueryRows(2);
		
		PageEvent event = this.regPageLoadEvent("pageLoad");
		event.addEventParam(this.purList, PageEvent.PARAMTYPE_PAGEQUERY);
		    
		ActionButton action = this.purList.regAddAction("newPurOrder","增加业务单", (type != null && "4".equals(type))  ?  "/include/store/storeOrderGuide.jsp?targetType=1" : "/purchase/purOrder_detail.htm");
		SubmitTool.submitToDialog(action,"newPurOrder", "增加业务单",800,450,this.modelMap.getPreNavInfoStr());
		action.addParam("orderType", type);
		    
		action = this.purList.regEditAction("editPurOrder","查看/编辑", "/purchase/purOrder_detail.htm");
		SubmitTool.submitToDialog(action, "editPurOrder", "查看/编辑",800,560,this.modelMap.getPreNavInfoStr());
	 
	}

	@Override
	public Layout initLayout() {
		  ViewPageLayout layout = new ViewPageLayout(this);
		  layout.addControl(this.purList);
		  ClickUtil.setControlLayoutH(this.purList.getViewControl(),0);
		  return layout;
	}
	
	 public void pageLoad(QueryPageData queryPageData) {
	   String state = this.modelMap.getRequest().getParameter("state");
		    try {      
		    	PurOrderBiz biz  = new PurOrderBiz(this.getUserACL());
		    	Long supplierId = this.getUserACL().getSupplierID();
		    	this.purList.bindData(biz.queryStockList(queryPageData,this.purList.getFieldList(),  supplierId > 0 ? supplierId : null, type,state));    
		    } catch(BizException ex) {
		      throw ex;
		    } catch(Exception e) {
		      throw new BizException(BizException.SYSTEM,"页面加载异常");
		    }
		  }
	 

}
