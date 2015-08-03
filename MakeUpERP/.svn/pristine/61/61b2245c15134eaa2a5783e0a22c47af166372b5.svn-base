package com.ga.erp.biz.member.vipLoyaltyAn;

import java.util.Map;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;
import com.ga.erp.biz.supplier.contract.ContractBiz;

public class VipLoyaltyAnMainPage extends UnitPage{

	private static final long serialVersionUID = 1L;
	private ListView vipLoyaltyAnList;
	 
	@Override
	public void initController() throws Exception {
		
		   vipLoyaltyAnList = new VipLoyaltyAnListView("vipLoyaltyAnList", this.modelMap);
		
		   vipLoyaltyAnList.setQueryRows(2);
		   
		   PageEvent event = this.regPageLoadEvent("pageLoad");
		   event.addEventParam(this.vipLoyaltyAnList, PageEvent.PARAMTYPE_PAGEQUERY);
		   event.addEventParam(this.vipLoyaltyAnList, PageEvent.PARAMTYPE_QUERYMAP);
		   
		   
	}

	@Override
	public Layout initLayout() {
		  ViewPageLayout layout = new ViewPageLayout(this);
		  layout.addControl(this.vipLoyaltyAnList);
		  return layout;
	}
	
	 public void pageLoad(QueryPageData queryPageData,Map<String, Object> querMap) {
		try {      
		     VipLoyaltyAnBiz biz  = new VipLoyaltyAnBiz(this.getUserACL());
		     this.vipLoyaltyAnList.bindData(biz.queryStockList(queryPageData,this.vipLoyaltyAnList.getFieldList()));    
		    } catch(BizException ex) {
		      throw ex;
		    } catch(Exception e) {
		      throw new BizException(BizException.SYSTEM,"“≥√Êº”‘ÿ“Ï≥£");
		    }
		  }
	 

}
