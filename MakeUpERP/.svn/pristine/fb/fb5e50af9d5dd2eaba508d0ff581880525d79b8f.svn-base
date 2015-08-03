package com.ga.erp.biz.stock.inventorybill;

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

public class BillMainPage extends UnitPage{

	private static final long serialVersionUID = 1L;
	private ListView inventoryBill;
	 
	@Override
	public void initController() throws Exception {
		
		inventoryBill = new BillListView("BillList", this.modelMap);
		
		PageEvent event = this.regPageLoadEvent("pageLoad");
		event.addEventParam(this.inventoryBill, PageEvent.PARAMTYPE_PAGEQUERY);
	    
	    ActionButton action = this.inventoryBill.regAddAction("newinbill","增加盘点单","/stock/bill_mtable.htm");
	    SubmitTool.submitToDialog(action,"newinventory", "增加盘点单",800,500,this.modelMap.getPreNavInfoStr());
	    
	    action = this.inventoryBill.regEditAction("mtestOp","查看", "/stock/bill_mtable.htm");
	    SubmitTool.submitToDialog(action,"mtestOp", "查看",1000,500,this.modelMap.getPreNavInfoStr());
	      
	    inventoryBill.regStateAction(this.getSelfUrl(), this,"INVENTORY_BILL");
	    
	}

	@Override
	public Layout initLayout() {
		ViewPageLayout layout = new ViewPageLayout(this);
		ClickUtil.setControlLayoutH(this.inventoryBill.getViewControl(), 1200);
		layout.addControl(this.inventoryBill);
		return layout;
	}
	
	 public void pageLoad(QueryPageData queryPageData) {
		 try {      
			   BillBiz biz  = new BillBiz(this.getUserACL());
			   int type = Integer.parseInt(this.modelMap.getRequest().getParameter("type"));
		       this.inventoryBill.bindData(biz.queryInventoryBillList(queryPageData, this.inventoryBill.getFieldList(),type));    
		    } catch(BizException ex) {
		      throw ex;
		    } catch(Exception e) {
		      throw new BizException(BizException.SYSTEM,"页面加载异常");
		    }
		  }
	  
	  
}
	 
