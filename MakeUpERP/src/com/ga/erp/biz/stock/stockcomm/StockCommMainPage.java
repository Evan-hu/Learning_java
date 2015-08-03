package com.ga.erp.biz.stock.stockcomm;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.GaUtil;

public class StockCommMainPage extends UnitPage{

	private static final long serialVersionUID = 1L;
	private ListView stockComm;
	private String inBatchID;
	private String stockId;
	@Override
	public void initController() throws Exception {
		
		stockComm = new StockCommListView("stockCommList", this.modelMap);
		
		inBatchID=this.stockComm.getRequestValue("inBatchID");
		stockId=this.stockComm.getRequestValue("stockId");
		
		PageEvent event = this.regPageLoadEvent("pageLoad");
		event.addEventParam(this.stockComm, PageEvent.PARAMTYPE_PAGEQUERY);
	    
	    ActionButton action = this.stockComm.regAddAction("newstockComm","��ӿ����Ʒ","/stock/comm_detail.htm");
	    SubmitTool.submitToDialog(action,"newstockComm", "��ӿ����Ʒ",800,300,this.modelMap.getPreNavInfoStr());
	    
	    action = this.stockComm.regEditAction("editockComm","�鿴/�༭", "/stock/comm_detail.htm");
	    SubmitTool.submitToDialog(action, "editockComm", "�鿴/�༭",800,320,this.modelMap.getPreNavInfoStr());
	    
	    stockComm.regStateAction(this.getSelfUrl(), this,"STOCK_COMM");
  
	}

	@Override
	public Layout initLayout() {
		ViewPageLayout layout = new ViewPageLayout(this);
		layout.addControl(this.stockComm);
		return layout;
	}
	
	 public void pageLoad(QueryPageData queryPageData) {
		 try {      
		      StockCommBiz biz  = new StockCommBiz(this.getUserACL());
		     this.stockComm.bindData(biz.queryStockCommList(queryPageData,this.stockComm.getFieldList(),null,GaUtil.isNullStr(inBatchID)?null:Long.parseLong(inBatchID)
		         ,GaUtil.isNullStr(stockId)?null:Long.parseLong(stockId)));    
		    } catch(BizException ex) {
		      throw ex;
		    } catch(Exception e) {
		      throw new BizException(BizException.SYSTEM,"ҳ������쳣");
		    }
		  }
}
