package com.ga.erp.biz.stock.inventorybill;

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
import com.ga.erp.biz.stock.inventorybill.comm.CommBiz;

public class BillDetailPage extends UnitPage {

	private static final long serialVersionUID = 1L;
	private BillFormView formView;
	
	@Override
	public void initController() throws Exception {
		
		 this.formView = new BillFormView("inventoryBillDetail", this.modelMap);
		 
		 PageEvent event = this.regPageLoadEvent("pageLoad"); 
		 event.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE,"INVENTORY_BILL_ID");
		 
		    
		  ActionButton action = new ActionButton(this.getClass(), "saveInventoryBaill","保存", this.getSelfUrl(), null);
	    action.bindForm(this.formView.getViewID(), true);
	    SubmitTool.submitToParentTable(action,this.modelMap.getPreNavInfoStr(),"billList");//?

	    action.addParam(GaConstant.FIXPARAM_EDITMODE,this.modelMap.getPageEditMode());
	    this.formView.regAction(action);   
	
		 
	}

	@Override
	public Layout initLayout() {
	
	    FormLayout formLayOut = new FormLayout("",this.formView.getDataForm(),2);
//		formLayOut.mergeCellField("auto", 6, 0, 1, "FANGMIAN");
//		formLayOut.mergeCellField("auto", 7, 0, 1, "NOTE");
		this.formView.getDataForm().setLayout(formLayOut);
		ViewPageLayout layout = new ViewPageLayout(this);
		layout.addControl("盘点单信息", "", this.formView);
	    return layout;
	}
	
	  public void pageLoad(Long id){
		 try {
		   
		     
		     Map<String, Object> map = this.formView.getViewParam();
         if(map.get("INVENTORY_BATCH_ID") != null){
           
           this.formView.getFieldList().remove(3);
         }
		     if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
		     List<DbField>	 filedList=this.formView.getFieldList();
  		   for(DbField dbField:filedList){
  		     
  		     dbField.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
  		     
  		   }
		       BillBiz biz  = new BillBiz(this.getUserACL());
		       this.formView.bindData(biz.queryInventoryBillDetail(id));  
		      } 
		     else{
	          this.formView.bindData(map);
	        }
		  
		    } catch(BizException ex) {
		         throw ex;
		    }  catch(Exception e) {
		          throw new BizException(BizException.SYSTEM,"页面加载异常");
		    }
		  }
	  
	  public ActionResult saveInventoryBaill(Map<String, Object> valueMap ) {
		  
		 BillBiz biz = new BillBiz(this.getUserACL());
		 if (this.getModelMap().getPageEditMode() == GaConstant.EDITMODE_NEW) {
		   biz.saveInventoryBill(valueMap, true);
		  } else if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
		     biz.saveInventoryBill(valueMap, false);
	      }
		 System.out.println(valueMap.get("INVENTORY_BATCH_NUM")+"  test--valueMap.get(INVENTORY_BATCH_NUM) ---");
		  return this.createReturnJSON(true, "保存成功", true, false, valueMap.get("INVENTORY_BATCH_NUM") != null ? "BillList" : "batchBillList", "");
	  }
	  
	  public ActionResult reloadInCommList(Long id) {
			try {
				CommBiz posBiz = new CommBiz(this.getUserACL());
//				this.inCommList.bindData(posBiz.queryICommDetail(
//						this.inCommList.getFieldList(), id));
//				return this.linkView(this.inCommList);
				return null;
			} catch (BizException e) {
				throw e;
			} catch (Exception ex) {
				throw new BizException("加载页面异常");
			}
		}
	  
}
