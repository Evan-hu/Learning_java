package com.ga.erp.biz.stock.innageadjust.comm;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class AdjustCommMainPage extends UnitPage{

	private static final long serialVersionUID = 1L;
	private ListView InnageAdList;
	 
	@Override
	public void initController() throws Exception {
		
		InnageAdList = new AdjustCommListView("InnageAdList", this.modelMap);
		
		PageEvent event = this.regPageLoadEvent("pageLoad");
		event.addEventParam(this.InnageAdList, PageEvent.PARAMTYPE_PAGEQUERY);
		    
		ActionButton action = this.InnageAdList.regAddAction("newInnageAd","新建库存调整","/stock/adjust_detail.htm");
		SubmitTool.submitToDialog(action,"newInnageAd", "新建库存调整",800,240,this.modelMap.getPreNavInfoStr());
		    
		action = this.InnageAdList.regEditAction("editInnageAd","查看/编辑", "/stock/adjust_detail.htm");
		SubmitTool.submitToDialog(action, "editInnageAd", "查看/编辑",800,260,this.modelMap.getPreNavInfoStr());
		    
		this.InnageAdList.regStateAction(this.getSelfUrl(), this, "INNAGE_ADJUST");
	}

	@Override
	public Layout initLayout() {
		  ViewPageLayout layout = new ViewPageLayout(this);
		  layout.addControl(this.InnageAdList);
		  return layout;
	}
	
	 public void pageLoad(QueryPageData queryPageData) {
		    try {      
		    	AdjustCommBiz biz  = new AdjustCommBiz(this.getUserACL());
		     this.InnageAdList.bindData(biz.queryStockList(queryPageData,this.InnageAdList.getFieldList()));    
		    } catch(BizException ex) {
		      throw ex;
		    } catch(Exception e) {
		      throw new BizException(BizException.SYSTEM,"页面加载异常");
		    }
		  }
	 

}
