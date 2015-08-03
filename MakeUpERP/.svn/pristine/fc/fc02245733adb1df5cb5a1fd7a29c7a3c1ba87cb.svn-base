package com.ga.erp.biz.stock.inventorydiffbill;

import java.util.Map;
import org.apache.click.ActionResult;
import com.ga.click.control.GaConstant;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;
import com.ga.erp.biz.stock.inventorydiffbill.comm.CommBiz;
import com.ga.erp.biz.stock.inventorydiffbill.comm.CommListView;

public class DiffBillDetailPage extends UnitPage {

	private static final long serialVersionUID = 1L;
	private DiffBillFormView formView;
	private ListView DBCommList;
	
	@Override
	public void initController() throws Exception {
		
		 this.formView = new DiffBillFormView("IDiffBillDetail", this.modelMap);
		 this.DBCommList = new CommListView("diffbillCommList", this.modelMap);
		 this.DBCommList.showQuery(false);
     this.DBCommList.showPage(false);
		 
		 PageEvent event = this.regPageLoadEvent("pageLoad");
		 event.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE,"INVENTORY_DIFF_BILL_ID");
		    
		 ActionButton action = new ActionButton(this.getClass(), "saveIDiffBill", "保存", this.getSelfUrl(), null);
		 action.bindForm(this.formView.getViewID(),true);
		 SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());
		    
		 action.addParam(GaConstant.FIXPARAM_EDITMODE, this.modelMap.getPageEditMode());
		 this.formView.regAction(action);
		    
		 event = this.regPageEvent(action, "saveIDiffBill");
		 event.addEventParam(this.formView, PageEvent.PARAMTYPE_DATAMAP);
		 
		 //DBCommList
     action = this.DBCommList.regAddAction("newDBCommList", "增加盘点差异商品",
         "/stock/diffbill_mtable.htm");
     SubmitTool.submitToDialog(action, "newDBCommList", "增加盘点差异商品", 800,300,
          this.modelMap.getPreNavInfoStr());
     action.bindViewParam(this.formView, "INVENTORY_DIFF_BILL_ID", "INVENTORY_DIFF_BILL_ID", false);

     action = this.DBCommList.regEditAction("editDBCommList", "查看/编辑",
         "/stock/diffbill_mtable.htm");
     SubmitTool.submitToDialog(action, "editDBCommList", "查看/编辑", 800, 300,
          this.modelMap.getPreNavInfoStr());
     action.bindViewParam(this.formView, "INVENTORY_DIFF_BILL_ID", "INVENTORY_DIFF_BILL_ID", false);

     event = this.regListViewLoadEvent(this.DBCommList, "reloadDBCommList");
     event.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE,"INVENTORY_DIFF_BILL_ID");
	}

	@Override
	public Layout initLayout() {
		FormLayout formLayOut = new FormLayout("",this.formView.getDataForm(),2);
	  	this.formView.getDataForm().setLayout(formLayOut);
	  	ViewPageLayout layout = new ViewPageLayout(this);
	  	layout.addControl("盘点差异单", "", this.formView);
	    if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
	      layout.addControl("盘点差异商品", "", this.DBCommList);
	    }
	    return layout;
	}
	
	  public void pageLoad(Long id) {
		 try {
		 
		    if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
		    	DiffBillBiz biz  = new DiffBillBiz(this.getUserACL());
		    	this.formView.bindData(biz.queryIDiffBillDetail(id));    
		    	
		    	CommBiz commbiz  = new CommBiz(this.getUserACL());
          this.DBCommList.bindData(commbiz.queryDBCommList(new QueryPageData(),this.DBCommList.getFieldList(), id));
		    	 }
		    } catch(BizException ex) {
		         throw ex;
		    }  catch(Exception e) {
		          throw new BizException(BizException.SYSTEM,"页面加载异常");
		    }
		  }
	  
	  public ActionResult saveIDiffBill(Map<String, Object> valueMap) {
		  DiffBillBiz biz = new DiffBillBiz(this.getUserACL());
		  if (this.getModelMap().getPageEditMode() == GaConstant.EDITMODE_NEW) {
        biz.saveIDiffBill(valueMap, true);
      } else if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
        biz.saveIDiffBill(valueMap, false);
      }
		      
		return this.createReturnJSON(true, "保存成功", true, false, "IDiffBillList", "");
	  }
	  
	   public ActionResult reloadDBCommList(Long id) {
	      try {
	        CommBiz commbiz  = new CommBiz(this.getUserACL());
          this.DBCommList.bindData(commbiz.queryDBCommList(new QueryPageData(), this.DBCommList.getFieldList(), id));
	        return this.linkView(this.DBCommList);
	      } catch (BizException e) {
	        throw e;
	      } catch (Exception ex) {
	        throw new BizException("加载页面异常");
	      }
	    }
	    
}
