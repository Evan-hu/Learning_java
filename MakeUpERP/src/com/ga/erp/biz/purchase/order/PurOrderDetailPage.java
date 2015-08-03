package com.ga.erp.biz.purchase.order;

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
import com.ga.erp.biz.purchase.list.PurListBiz;
import com.ga.erp.biz.purchase.list.PurListView;
import com.ga.erp.biz.store.storeorder.OrderDeliveryRecordListView;

public class PurOrderDetailPage extends UnitPage {

	private static final long serialVersionUID = 1L;
	private PurOrderFormView purOrderForm;
	private ListView purCommList;
	private ListView deliRecordList;

	@Override
	public void initController() throws Exception {
		 this.purOrderForm = new PurOrderFormView("purOrderDetail", this.modelMap);
		 this.purCommList = new PurListView("purComm", this.modelMap);
		 this.purCommList.showPage(false);
     this.purCommList.showQuery(false);
     this.purCommList.setExport(true);
		 this.deliRecordList = new OrderDeliveryRecordListView("orderDeliveryRecordListView", this.modelMap);
		 this.deliRecordList.showPage(false);
		 this.deliRecordList.showQuery(false);
		 
		 PageEvent event = this.regPageLoadEvent("pageLoad");
		 event.addEventParam(this.purOrderForm, PageEvent.PARAMTYPE_QUERYVALUE,"PURCHASE_ORDER_ID");
		 event.addEventParam(this.purOrderForm,PageEvent.PARAMTYPE_REQUEST,"orderType");
		 
		 ActionButton action = this.purCommList.regAddAction("newPurList","�½�","/purchase/purList_detail.htm");
	   SubmitTool.submitToDialog(action,"newPurList", "�½�",800,330,this.modelMap.getPreNavInfoStr());
	        
	   action = this.purCommList.regEditAction("editPurList","�鿴/�༭", "/purchase/purList_detail.htm");
	   SubmitTool.submitToDialog(action, "editPurList", "�鿴/�༭",800,330,this.modelMap.getPreNavInfoStr());
		 
		 action = new ActionButton(this.getClass(), "savePurOrder", "����", this.getSelfUrl(), null);
		 action.bindForm(this.purOrderForm.getViewID(),true);
		 SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());
		    
		 action.addParam(GaConstant.FIXPARAM_EDITMODE, this.modelMap.getPageEditMode());
		 this.purOrderForm.regAction(action);
		    
		 event = this.regPageEvent(action, "savePurOrder");
		 event.addEventParam(this.purOrderForm, PageEvent.PARAMTYPE_DATAMAP);
	}

	@Override
	public Layout initLayout() {
	  
		FormLayout formLayOut = new FormLayout("",this.purOrderForm.getDataForm(),2);
		//�ϲ���Ԫ��
		//formLayOut.mergeCellField("auto", 2, 0, 1, "STOCK");
	  	this.purOrderForm.getDataForm().setLayout(formLayOut);
	  	ViewPageLayout layout = new ViewPageLayout(this);
	 	if(this.modelMap.getPageEditMode() == GaConstant.EDITMODE_NEW){
	 	  	layout.addControl(this.purOrderForm);
 	  	} else {
	  	  layout.setControlLayoutH(this.deliRecordList.getViewControl(), 50);
	  	  layout.addControl("������Ϣ","",this.purOrderForm);
	  	  layout.addControl("�ɹ���Ʒ","",this.purCommList);
	  	  layout.addControl("������¼","",this.deliRecordList);
 	  	}
	    return layout;
	}
	
	  public void pageLoad(Long id,String type) {
		 try {
		    	 if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_NEW) {
		    	   this.purOrderForm.bindData(purOrderForm.getViewParam());
		    	 } else {
		         PurOrderBiz biz = new PurOrderBiz(this.getUserACL());
             this.purOrderForm.bindData(biz.queryStockDetail(id));
             PurListBiz listBiz = new PurListBiz(this.getUserACL());
             this.purCommList.bindData(listBiz.queryPurList(new QueryPageData(), this.purCommList.getFieldList(), id));
             this.deliRecordList.bindData(biz.queryOrderDeliveryRecord(id, this.deliRecordList.getFieldList()));
			    }
		    } catch(BizException ex) {
	          throw ex;
		    }  catch(Exception e) {
	          throw new BizException(BizException.SYSTEM,"ҳ������쳣");
		    }
		  }
	  
	  public ActionResult savePurOrder(Map<String, Object> valueMap) {
		  PurOrderBiz biz = new PurOrderBiz(this.getUserACL());
		  if (this.getModelMap().getPageEditMode() == GaConstant.EDITMODE_NEW) {
        biz.savePurOrder(valueMap, true);
      } else if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
        biz.savePurOrder(valueMap, false);
  	  }
	  	return this.createReturnJSON(true, "����ɹ�", true, false, "purOrderList", "");
	  }
}
