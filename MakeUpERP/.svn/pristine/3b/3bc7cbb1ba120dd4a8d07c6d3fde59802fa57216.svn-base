package com.ga.erp.biz.modifyprice;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class ModifyPriceMainPage extends UnitPage{

	private static final long serialVersionUID = 1L;
	private ListView modifyPricList;
	private String objType;
	 
	@Override
	public void initController() throws Exception {
		
		modifyPricList = new ModifyPriceListView("modifyPriceList", this.modelMap);
		//获取 价格调整单类型
		objType=this.modifyPricList.getRequestValue("objType");
		PageEvent event = this.regPageLoadEvent("pageLoad");
		event.addEventParam(this.modifyPricList, PageEvent.PARAMTYPE_PAGEQUERY);
	    
	    ActionButton action = this.modifyPricList.regAddAction("newinbill","增加价格调整单","/modifyprice/bill_table.htm?objType="+objType);
	    SubmitTool.submitToDialog(action,"newinventory", "增加价格调整单",800,500,this.modelMap.getPreNavInfoStr());
	    
	    
	    action = this.modifyPricList.regEditAction("mtestOp","查看", "/modifyprice/bill_table.htm?objType="+objType);
	    action.addParam("OBJECT_ID", "OBJECT_ID");
	    SubmitTool.submitToDialog(action,"mtestOp", "查看",1000,500,this.modelMap.getPreNavInfoStr());
	      
	    modifyPricList.regStateAction(this.getSelfUrl(), this,"MODIFY_PRICE");
	    
	}

	@Override
	public Layout initLayout() {
		ViewPageLayout layout = new ViewPageLayout(this);
		layout.addControl(this.modifyPricList);
		return layout;
	}
	
	 public void pageLoad(QueryPageData queryPageData) {
		 try { 
		   ModifyPriceBiz biz  = new ModifyPriceBiz(this.getUserACL());
		     this.modifyPricList.bindData(biz.queryModifyPriceList(queryPageData, this.modifyPricList.getFieldList(),objType));    
		    } catch(BizException ex) {
		      throw ex;
		    } catch(Exception e) {
		      throw new BizException(BizException.SYSTEM,"页面加载异常");
		    }
		  }
	  
	  
}
	 
