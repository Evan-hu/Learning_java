package com.ga.erp.biz.stock.inventorybill.comm;

import java.util.List;
import java.util.Map;
import org.apache.click.ActionResult;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;

public class CommDetailPage extends UnitPage {

	private static final long serialVersionUID = 1L;
	private CommFormView commForm;
	private String inBatchID;
	private String inBatchType;
	
	@Override
	public void initController() throws Exception {
		
		 this.commForm = new CommFormView("ICommDetail", this.modelMap);
		 
		 inBatchID = this.commForm.getRequestValue("inBatchId");
		 inBatchType = this.commForm.getRequestValue("batchType");
		 
		 PageEvent event = this.regPageLoadEvent("pageLoad");
		// inventoryType =this.formView.getRequestValue("inventoryType");
		 event.addEventParam(this.commForm, PageEvent.PARAMTYPE_QUERYVALUE,"INVENTORY_COMM_ID");
		 event.addEventParam(this.commForm, PageEvent.PARAMTYPE_REQUEST, "inBatchId");
	   event.addEventParam(this.commForm, PageEvent.PARAMTYPE_REQUEST,"batchType");
	   
     ActionButton action = new ActionButton(this.getClass(), "saveIComm","保存", this.getSelfUrl(), null);
     action.bindForm(this.commForm.getViewID(), true);
     SubmitTool.submitToParentTable(action,this.modelMap.getPreNavInfoStr(),"tableICommList");//返回主表页面

     action.addParam(GaConstant.FIXPARAM_EDITMODE,this.modelMap.getPageEditMode());
     this.commForm.regAction(action);   
	}

	@Override
	public Layout initLayout() {
		FormLayout formLayOut = new FormLayout("",this.commForm.getDataForm(),2);
		//合并单元格
		//formLayOut.mergeCellField("auto", 2, 0, 1, "STOCK");
	  	this.commForm.getDataForm().setLayout(formLayOut);
	  	ViewPageLayout layout = new ViewPageLayout(this);
		layout.addControl(this.commForm);
	    return layout;
	}
	
	  public void pageLoad(Long id,String batchID,String bathType) {
		 try {
			 Map<String, Object> map = this.commForm.getViewParam();
			 List<DbField> field=this.commForm.getFieldList();
			 inBatchID=batchID==null?inBatchID:batchID;
			 inBatchType=bathType==null?inBatchType:bathType;
			 if("2".equals(inBatchType)){
			   field.remove(4);
			   this.commForm.getFieldByCode("COMMODITY_NAME").setPopSelect("commSelect","COMMODITY_NAME",true,
	           "/store/comm_main.htm?inBatchID="+inBatchID,
	           "COMMODITY_ID,COMMODITY_NAME,STORE_ID,STORE_NAME,CNT,cid_storeCommList",800,400);
			   
			 }else{
			   field.remove(6);
         this.commForm.getFieldByCode("COMMODITY_NAME").setPopSelect("commSelect","COMMODITY_NAME",true,
           "/stock/comm_main.htm?inBatchID="+inBatchID,
           "COMMODITY_ID,COMMODITY_NAME,STOCK_ID,NAME,CNT,cid_stockCommList",800,400);
		   
			 }
			 if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
         CommBiz biz  = new CommBiz(this.getUserACL());
//         map=biz.queryICommDetail(this.commForm.getFieldList(),id);
         map=biz.queryICommDetail(id);
         this.commForm.bindData(map);   
          }
		    } catch(BizException ex) {
		         throw ex;
		    }  catch(Exception e) {
		          throw new BizException(BizException.SYSTEM,"页面加载异常");
		    }
		  }
	  
	  public ActionResult saveIComm(Map<String, Object> valueMap) {
		  
		  CommBiz biz = new CommBiz(this.getUserACL());
		
		      if (this.getModelMap().getPageEditMode() == GaConstant.EDITMODE_NEW) {
		        biz.saveIComm(valueMap, true);
		      } else if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
		        biz.saveIComm(valueMap, false);
	  }
		return this.createReturnJSON(true, "保存成功", true, false, valueMap.get("INVENTORY_FLOW_NUM") != null ? "ICommList" : "inventoryCommList", "");
	  }
}
