package com.ga.erp.biz.stock.stockalert;

import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class StockAlertMainPage extends UnitPage{

	private static final long serialVersionUID = 1L;
	private ListView stockAlertList;
	private String type;
	@Override
	public void initController() throws Exception {
		
	  this.stockAlertList = new StockAlertListView("stockAlertList", this.modelMap);
		type = this.modelMap.getRequest().getParameter("type");
		PageEvent event = this.regPageLoadEvent("pageLoad");
		event.addEventParam(this.stockAlertList, PageEvent.PARAMTYPE_PAGEQUERY);
	    
	}

	@Override
	public Layout initLayout() {
		ViewPageLayout layout = new ViewPageLayout(this);
		layout.addControl(this.stockAlertList);
		return layout;
	}
	
	 public void pageLoad(QueryPageData queryPageData) {
    try {
      StockAlertBiz biz = new StockAlertBiz(this.getUserACL());
      this.stockAlertList.bindData(biz.queryStockCommList(queryPageData,this.stockAlertList.getFieldList(), type));
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM, "“≥√Êº”‘ÿ“Ï≥£");
    }
  }
}
