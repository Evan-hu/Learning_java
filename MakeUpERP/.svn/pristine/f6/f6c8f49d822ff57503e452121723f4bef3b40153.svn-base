package com.ga.erp.biz.stock;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class StockMainPage extends UnitPage{

	private static final long serialVersionUID = 1L;
	private ListView stockList;
	 
	@Override
	public void initController() throws Exception {
		
		stockList = new StockListView("stockList", this.modelMap);
		
		   PageEvent event = this.regPageLoadEvent("pageLoad");
		    event.addEventParam(this.stockList, PageEvent.PARAMTYPE_PAGEQUERY);
		    
		    ActionButton action = this.stockList.regAddAction("newContract","添加仓库","/stock/stock_detail.htm");
		    SubmitTool.submitToDialog(action,"newContract", "添加仓库",400,300,this.modelMap.getPreNavInfoStr());
		    
		    action = this.stockList.regEditAction("editContract","查看/编辑", "/stock/stock_detail.htm");
		    SubmitTool.submitToDialog(action, "editContract", "查看/编辑",800,300,this.modelMap.getPreNavInfoStr());
	}
/**
 * 初始化页面布局
 */
	@Override
	public Layout initLayout() {
		  ViewPageLayout layout = new ViewPageLayout(this);
		  layout.addControl(this.stockList);
		  return layout;
	}
	/**
	 * 页面加载时 调用方法
	 * @param queryPageData
	 */
	 public void pageLoad(QueryPageData queryPageData) {
		    try {      
		      StockBiz biz  = new StockBiz(this.getUserACL());
		     this.stockList.bindData(biz.queryStockList(queryPageData,this.stockList.getFieldList()));    
		    } catch(BizException ex) {
		      throw ex;
		    } catch(Exception e) {
		      throw new BizException(BizException.SYSTEM,"页面加载异常");
		    }
		  }
	 

}
