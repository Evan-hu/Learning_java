package com.ga.erp.biz.stock.inventorybatch;

import org.apache.click.ActionResult;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;
import com.ga.erp.biz.stock.inventorybatch.imports.ImportBiz;

public class BatchMainPage extends UnitPage{

	private static final long serialVersionUID = 1L;
	private ListView inventoryBatchList;
	 
	@Override
	public void initController() throws Exception {
	  
	  inventoryBatchList = new BatchListView("inBatchList", this.modelMap);
    
    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.inventoryBatchList, PageEvent.PARAMTYPE_PAGEQUERY);
    
    ActionButton action = this.inventoryBatchList.regAddAction("newinventory","增加盘点批次","/stock/batch_detail.htm");
    SubmitTool.submitToDialog(action,"newinventory", "增加盘点批次",800,300,this.modelMap.getPreNavInfoStr());
	action.addParam("type", this.modelMap.getRequest().getParameter("type"));

    
    action = this.inventoryBatchList.regEditAction("editinventory","查看", "/stock/batch_detail.htm");
    SubmitTool.submitToDialog(action, "editinventory", "查看",800,300,this.modelMap.getPreNavInfoStr());
    
    inventoryBatchList.regStateAction(this.getSelfUrl(), this,"INVENTORY_BATCH");
    
    action = new ActionButton(this.getClass(),"exportExcel","导出EXCEL",this.getSelfUrl(),null);
    SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());
    action.bindTableRowData(this.inventoryBatchList.getViewID());
    action.setConfirm("确认导出");
    this.inventoryBatchList.regAction(action);
    event = this.regPageEvent(action, "exportExcel");
    event.addEventParam(inventoryBatchList, PageEvent.PARAMTYPE_QUERYVALUE,"INVENTORY_BATCH_ID");
    
    action = new ActionButton(this.getClass(),"exportCsv","导出CSV",this.getSelfUrl(),null);
    SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());
    action.bindTableRowData(this.inventoryBatchList.getViewID());
    action.setConfirm("确认导出");
    this.inventoryBatchList.regAction(action);
    event = this.regPageEvent(action, "exportCsv");
    event.addEventParam(inventoryBatchList, PageEvent.PARAMTYPE_QUERYVALUE,"INVENTORY_BATCH_ID");
      
    action = this.inventoryBatchList.regEditAction("importExcel","导入EXCEL/CSV", "/stock/import_detail.htm");
    SubmitTool.submitToDialog(action, "importExcel", "导入EXCEL/CSV",800,300,this.modelMap.getPreNavInfoStr());
     
	}

	@Override
	public Layout initLayout() {
		ViewPageLayout layout = new ViewPageLayout(this);
		layout.addControl(this.inventoryBatchList);
		return layout;
	}
	
	 public void pageLoad(QueryPageData queryPageData) {
		 try {
		   int type = Integer.parseInt(this.modelMap.getRequest().getParameter("type"));
		   BatchBiz biz  = new BatchBiz(this.getUserACL());
		   this.inventoryBatchList.bindData(biz.queryInventoryBatchList(queryPageData, this.inventoryBatchList.getFieldList(),type));    
		    } catch(BizException ex) {
		      throw ex;
		    } catch(Exception e) {
		      throw new BizException(BizException.SYSTEM,"页面加载异常");
		    }
		  }
	 
	 public ActionResult exportExcel(Long id) {
	   try {
       ExportBiz biz  = new ExportBiz();
       String file = biz.exportBatchComm(ImportBiz.EXCEL_PATTERN, id);
       return this.createReturnJSON(true, file, false, false, this.inventoryBatchList.getViewID(), "");
     }catch(BizException e) {
       throw e;
     } catch(Exception ex) {
       ex.printStackTrace();
       throw new BizException("导出异常");
     }
	 }
	 
	 public ActionResult exportCsv(Long id) {
     try {
       ExportBiz biz  = new ExportBiz();
       String file = biz.exportBatchComm(ImportBiz.CSV_PATTERN, id);
       return this.createReturnJSON(true, file, false, false, this.inventoryBatchList.getViewID(), "");
     }catch(BizException e) {
       throw e;
     } catch(Exception ex) {
       ex.printStackTrace();
       throw new BizException("导出异常");
     }
   }
	  
}
	 
