package com.ga.erp.biz.supplier.contract;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class ContractMainPage extends UnitPage {
	
    private static final long serialVersionUID = 1L;
    private ListView contractList;
	  
	@Override
	public void initController() throws Exception {
		  contractList = new ContractListView("contractList",this.modelMap);
		  contractList.setQueryRows(2);
		 
		    PageEvent event = this.regPageLoadEvent("pageLoad");
		    event.addEventParam(this.contractList, PageEvent.PARAMTYPE_PAGEQUERY);
		    
		    ActionButton action = this.contractList.regAddAction("newContract","新建合同","/supplier/contract_detail.htm");
		    SubmitTool.submitToDialog(action,"newContract", "新建合同",800,230,this.modelMap.getPreNavInfoStr());
		    
		    action = this.contractList.regEditAction("editContract","查看/编辑", "/supplier/contract_detail.htm");
		    SubmitTool.submitToDialog(action, "editContract", "查看/编辑",800,300,this.modelMap.getPreNavInfoStr());
		    

//		    action =this.contractList.regEditAction("deleContract","删除",this.getSelfUrl());
//		    SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());
//		    action.bindTableRowData(this.contractList.getViewID());
//		    action.setConfirm("确认删除！");
//		    event = this.regPageEvent(action, "deleContract");
//		    event.addEventParam(this.contractList, PageEvent.PARAMTYPE_QUERYVALUE,"CONTRACT_ID");
//		    
		
	}

	@Override
	public Layout initLayout() {
		   ViewPageLayout layout = new ViewPageLayout(this);
		    layout.addControl(this.contractList);
		    return layout;
	}
	  /**
	   * 页面加载事件
	   * @param queryPageData
	   */
	  public void pageLoad(QueryPageData queryPageData) {
	    try {      
	      Long supplierId = this.modelMap.getUserACL().getSupplierID();
	      ContractBiz biz  = new ContractBiz(this.getUserACL());
	     this.contractList.bindData(biz.queryContractList(queryPageData,this.contractList.getFieldList(), supplierId > 0 ? supplierId : null));    
	     
	    } catch(BizException ex) {
	      throw ex;
	    } catch(Exception e) {
	      throw new BizException(BizException.SYSTEM,"页面加载异常");
	    }
	  }

}
