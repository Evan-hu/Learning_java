package com.ga.erp.biz.modifyprice.comm;

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
	private String objectID;
	private String billType;
	
	@Override
	public void initController() throws Exception {
		
		 this.commForm = new CommFormView("mdCommDetail", this.modelMap);
		 
		 objectID = this.commForm.getRequestValue("objectID");
		 billType = this.commForm.getRequestValue("billType");
		 
		 PageEvent event = this.regPageLoadEvent("pageLoad");
		// inventoryType =this.formView.getRequestValue("inventoryType");
		 event.addEventParam(this.commForm, PageEvent.PARAMTYPE_QUERYVALUE,"MODIFY_PRICE_COMMODITY_ID");
		 event.addEventParam(this.commForm, PageEvent.PARAMTYPE_REQUEST, "objectID");
	   event.addEventParam(this.commForm, PageEvent.PARAMTYPE_REQUEST,"billType");
	   
     ActionButton action = new ActionButton(this.getClass(), "savemdComm","保存", this.getSelfUrl(), null);
     action.bindForm(this.commForm.getViewID(), true);
     SubmitTool.submitToParentTable(action,this.modelMap.getPreNavInfoStr(),"tabledbCommList");//返回主表页面

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
	
	  public void pageLoad(Long id,String objectID,String billType ) {
		 try {
		   
		   this.billType=billType!=null?billType:this.billType;
		   this.objectID=objectID!=null?objectID:this.billType;
			 Map<String, Object> map = this.commForm.getViewParam();
			
			 //根据类型  设置商品放大镜  1 供应商 2DC  3 门店
			 removeDbfield();
			 if (this.getModelMap().getPageEditMode() == GaConstant.EDITMODE_NEW) {

			   if("1".equals(billType)){
			   this.commForm.getFieldByCode("OBJECT_ID").setPopSelect("mdcommSelect", "SUPPLIER_COMMODITY_ID", false);
         this.commForm.getFieldByCode("OLD_PURCHASE_PRICE").setPopSelect("mdcommSelect", "SUPPLY_PRICE", true);
         this.commForm.getFieldByCode("OBJECT_NAME").setPopSelect("mdcommSelect","COMMODITY_NAME",true,
             "/supplier/commodity/suppliercommodity_main.htm?supplierId="+objectID,
             "COMMODITY_ID,COMMODITY_NAME,SUPPLY_PRICE,SUPPLIER_COMMODITY_ID,cid_supplierCommList",800,400);
			   
			 }else if("2".equals(billType)){
			   this.commForm.getFieldByCode("OBJECT_ID").setPopSelect("mdcommSelect", "STOCK_COMM_ID", false);
         this.commForm.getFieldByCode("OLD_PURCHASE_PRICE").setPopSelect("mdcommSelect", "STOCK_PRICE", true);
         this.commForm.getFieldByCode("OLD_TRADE_PRICE").setPopSelect("mdcommSelect", "WHOLESALE_PRICE", true);
         this.commForm.getFieldByCode("OLD_SELL_PRICE").setPopSelect("mdcommSelect", "RETAIL_PRICE", true);
         this.commForm.getFieldByCode("OLD_MEMBER_PRICE").setPopSelect("mdcommSelect", "MEM_PRICE", true);
         this.commForm.getFieldByCode("OLD_SEND_PRICE").setPopSelect("mdcommSelect", "DISTRIBUT_PRICE", true);
         this.commForm.getFieldByCode("OBJECT_NAME").setPopSelect("mdcommSelect","COMMODITY_NAME",true,
             "/stock/comm_main.htm?stockId="+objectID,
             "COMMODITY_ID,COMMODITY_NAME,STOCK_COMM_ID,STOCK_PRICE,WHOLESALE_PRICE,RETAIL_PRICE,MEM_PRICE,DISTRIBUT_PRICE," +
             "cid_stockCommList",800,400);
		   
			 }else{
			   this.commForm.getFieldByCode("OBJECT_ID").setPopSelect("mdcommSelect", "STORE_COMM_ID", false);
			   this.commForm.getFieldByCode("OLD_SELL_PRICE").setPopSelect("mdcommSelect", "RETAIL_PRICE", true);
			   this.commForm.getFieldByCode("OBJECT_NAME").setPopSelect("mdcommSelect","COMMODITY_NAME",true,
             "/store/comm_main.htm?storeId="+objectID,
             "COMMODITY_ID,COMMODITY_NAME,STORE_COMM_ID,RETAIL_PRICE,MEMBER_PRICE,cid_storeCommList",800,400);
			 }
			 }
			 if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
         CommBiz biz  = new CommBiz(this.getUserACL());
//         map=biz.queryICommDetail(this.commForm.getFieldList(),id);
         map=biz.querymdCommDetail(id);
         this.commForm.bindData(map);   
          }
		    } catch(BizException ex) {
		         throw ex;
		    }  catch(Exception e) {
		          throw new BizException(BizException.SYSTEM,"页面加载异常");
		    }
		  }
	  
	  /**
     * 
     */
    private void removeDbfield() {
      
      if("1".equals(billType)){
        
        commForm.removeDbField("OLD_MEMBER_PRICE");
        commForm.removeDbField("MEMBER_PRICE");
        commForm.removeDbField("OLD_SELL_PRICE");
        commForm.removeDbField("SELL_PRICE");
        commForm.removeDbField("OLD_SEND_PRICE");
        commForm.removeDbField("SEND_PRICE");
        commForm.removeDbField("OLD_TRADE_PRICE");
        commForm.removeDbField("TRADE_PRICE");
       
      }
      else if("2".equals(billType)){
//        
//        commForm.removeDbField("OLD_MEMBER_PRICE");
//        commForm.removeDbField("MEMBER_PRICE");
//        commForm.removeDbField("OLD_SELL_PRICE");
//        commForm.removeDbField("SELL_PRICE");
//        commForm.removeDbField("OLD_SEND_PRICE");
//        commForm.removeDbField("SEND_PRICE");
//        commForm.removeDbField("OLD_TRADE_PRICE");
//        commForm.removeDbField("OLD_PURCHASE_PRICE");
//        commForm.removeDbField("TRADE_PRICE");
       
      }else{
       
        commForm.removeDbField("OLD_PURCHASE_PRICE");
        commForm.removeDbField("PURCHASE_PRICE");
        commForm.removeDbField("OLD_SEND_PRICE");
        commForm.removeDbField("SEND_PRICE");
        commForm.removeDbField("OLD_TRADE_PRICE");
        commForm.removeDbField("TRADE_PRICE");
        
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
