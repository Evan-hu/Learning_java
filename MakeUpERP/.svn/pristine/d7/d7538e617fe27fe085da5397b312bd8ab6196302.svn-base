package com.ga.erp.biz.stock.inventorybill.comm;

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
	private ListView stockList;
	 
	@Override
	public void initController() throws Exception {
		
		stockList = new CommListView("ICommList", this.modelMap);
		stockList.setExport(true);
		
		PageEvent event = this.regPageLoadEvent("pageLoad");
		event.addEventParam(this.stockList, PageEvent.PARAMTYPE_PAGEQUERY);
		    	    
		ActionButton action = this.stockList.regAddAction("newContract","增加盘点商品","/stock/billcomm_detail.htm");
		SubmitTool.submitToDialog(action,"newContract", "增加盘点商品",800,230,this.modelMap.getPreNavInfoStr());
		    
		action = this.stockList.regEditAction("editContract","查看/编辑", "/stock/billcomm_detail.htm");
		SubmitTool.submitToDialog(action, "editContract", "查看/编辑",800,230,this.modelMap.getPreNavInfoStr());
		
	}

	@Override
	public Layout initLayout() {
		  ViewPageLayout layout = new ViewPageLayout(this);
		  layout.addControl(this.stockList);
		  return layout;
	}
	
	 public void pageLoad(QueryPageData queryPageData) {
	   try {      
       CommBiz biz  = new CommBiz(this.getUserACL());
      this.stockList.bindData(biz.queryICommList(queryPageData,this.stockList.getFieldList(),null));    
     } catch(BizException ex) {
       throw ex;
     } catch(Exception e) {
       throw new BizException(BizException.SYSTEM,"页面加载异常");
     }
	 }

}
