package com.ga.erp.biz.stock;

import java.util.Map;

import org.apache.click.ActionResult;

import com.ga.click.control.GaConstant;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.erp.biz.stock.stockcomm.StockCommBiz;
import com.ga.erp.biz.stock.stockcomm.StockCommListView;

public class StockDetailPage extends UnitPage {

	private static final long serialVersionUID = 1L;
	private StockFormView formView;
	private StockCommListView stockCommList;
	
	@Override
	public void initController() throws Exception {
		
		 this.formView = new StockFormView("stocktDetail", this.modelMap);
		 this.stockCommList=new StockCommListView("stock_stockCommList", this.modelMap); 
     this.stockCommList.showQuery(false);
     this.stockCommList.showPage(false);
		 
		 PageEvent event = this.regPageLoadEvent("pageLoad");
		 event.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE,"STOCK_ID");
		    
		 ActionButton action = new ActionButton(this.getClass(), "saveStock", "����", this.getSelfUrl(), null);
		 action.bindForm(this.formView.getViewID(),true);
		 SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());
		    
		 action.addParam(GaConstant.FIXPARAM_EDITMODE, this.modelMap.getPageEditMode());
		 this.formView.regAction(action);
		    
		 event = this.regPageEvent(action, "saveStock");
		 event.addEventParam(this.formView, PageEvent.PARAMTYPE_DATAMAP);
		 
		 
		 //IncommList
     action = this.stockCommList.regAddAction("newstockCommList", "���ӿ����Ʒ","/stock/comm_detail.htm");
     SubmitTool.submitToDialog(action, "newstockCommList", "���ӿ����Ʒ", 800, 320,this.modelMap.getPreNavInfoStr());
     action.bindViewParam(this.formView, "STOCK_ID", "STOCK_ID", false);

     action = this.stockCommList.regEditAction("editstockComm", "�鿴/�༭","/stock/comm_detail.htm");
     SubmitTool.submitToDialog(action, "editstockComm", "�鿴/�༭", 800, 360, this.modelMap.getPreNavInfoStr());
     action.bindViewParam(this.formView, "STOCK_ID", "STOCK_ID", false);

     event = this.regListViewLoadEvent(this.stockCommList, "reloadSComm");
     event.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE, "STOCK_ID");
	}

	@Override
	public Layout initLayout() {
		FormLayout formLayOut = new FormLayout("",this.formView.getDataForm(),1);
		//�ϲ���Ԫ��
		//formLayOut.mergeCellField("auto", 2, 0, 1, "STOCK");
	  	this.formView.getDataForm().setLayout(formLayOut);
	  	ViewPageLayout layout = new ViewPageLayout(this);
	  	layout.addControl("�ֿⵥ��Ϣ", "", this.formView);
	    if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
	      layout.addControl("�����Ʒ��Ϣ", "", this.stockCommList);
	    }
	    return layout;
	}
	
	  public void pageLoad(Long id) {
		 try {
		    	 if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
		    		 StockBiz biz  = new StockBiz(this.getUserACL());
		    		 this.formView.bindData(biz.queryStockDetail(id)); 
		    		 
		    		 StockCommBiz commbiz  = new StockCommBiz(this.getUserACL());
	           this.stockCommList.bindData(commbiz.queryStockCommDetail( this.stockCommList.getFieldList(),id));
	          } 
		 
		    }  catch(Exception e) {
		          throw new BizException(BizException.SYSTEM,"ҳ������쳣");
		    }
		  }
	  
	  public ActionResult saveStock(Map<String, Object> valueMap) {
		 StockBiz biz = new StockBiz(this.getUserACL());
		      if (this.getModelMap().getPageEditMode() == GaConstant.EDITMODE_NEW) {
		        biz.saveStock(valueMap, true);
		      } else if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
		        biz.saveStock(valueMap, false);
	  }
		return this.createReturnJSON(true, "����ɹ�", true, false, "stockList", "");
	  }
	  /**
	   *
	   * @param id
	   * @return
	   */
	  public ActionResult reloadSComm(Long id) {
      try {
        StockCommBiz commbiz  = new StockCommBiz(this.getUserACL());
        this.stockCommList.bindData(commbiz.queryStockCommDetail( this.stockCommList.getFieldList(),id));
        return this.linkView(this.stockCommList);
      } catch (BizException e) {
        throw e;
      } catch (Exception ex) {
        throw new BizException("����ҳ���쳣");
      }
    }

}
