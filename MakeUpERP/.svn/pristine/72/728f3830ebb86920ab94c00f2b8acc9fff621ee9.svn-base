package com.ga.erp.biz.stock.inventorybatch;

import java.util.List;
import java.util.Map;

import org.apache.click.ActionResult;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.util.GaUtil;

public class BatchDetailPage extends UnitPage {

	private static final long serialVersionUID = 1L;
	private BatchFormView formView;
	
	
	@Override
	public void initController() throws Exception {
		
		 this.formView = new BatchFormView("inventoryBatchDetail", this.modelMap);
		 
		 PageEvent event = this.regPageLoadEvent("pageLoad"); 
		 event.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE,"INVENTORY_BATCH_ID");
		 
		 this.formView.regAddSaveEvent("saveInventoryBatch", "saveInventoryBatch", this, true);
		 
		 
	}

	@Override
	public Layout initLayout() {
	
	    FormLayout formLayOut = new FormLayout("",this.formView.getDataForm(),2);
		this.formView.getDataForm().setLayout(formLayOut);
		ViewPageLayout layout = new ViewPageLayout(this);
		layout.addControl("盘点批次信息", "", this.formView);
	    return layout;
	}
	
	  public void pageLoad(Long id) {
		 try {
	
			     if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
			        List<DbField> fielList=formView.getFieldList();
			        for(int i=1 ;i<fielList.size();i++){
			          fielList.get(i).setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
			        }
			        BatchBiz biz  = new BatchBiz(this.getUserACL());
			        this.formView.bindData(biz.queryInventoryBatchDetail(id));    
			     }else{
				        String type = this.modelMap.getRequest().getParameter("type");
				        DbField dbField = formView.getFieldByCode("TYPE");
				        dbField.setDefaultValue(type);
				        DbField typeField = formView.getFieldByCode("TYPE_ID");
				        DbField typeNameField = formView.getFieldByCode("TYPE_NAME");
				        
				        if(type.equals("2")){//选择门店
				        	typeNameField.setPopSelect("typeSelect","STORE_NAME",true,
							          "/store/store_main.htm",
							          "STORE_ID,STORE_NAME,cid_storeList",800,400);
				        	typeField.setPopSelect("typeSelect", "STORE_ID", false);
				        }else{
				        	typeNameField.setPopSelect("typeSelect","NAME",true,
									 "/stock/stock_main.htm",
									 "STOCK_ID,NAME,cid_stockList",600,300);
				        	typeField.setPopSelect("typeSelect", "STOCK_ID", false);
						}
			     }
		    } catch(BizException ex) {
		         throw ex;
		    }  catch(Exception e) {
		    	e.printStackTrace();
		          throw new BizException(BizException.SYSTEM,"页面加载异常");
		    }
		  }
	  
	  public ActionResult saveInventoryBatch(Map<String, Object> valueMap ) {
		  if(GaUtil.isNullStr(valueMap.get("TYPE_ID")+"")){
		    valueMap.put("TYPE_ID", this.modelMap.getRequest().getParameter("typeSelect.STOCK_ID"));
		    
		  }
		  if(GaUtil.isNullStr(valueMap.get("OBJECT_IDS")+"")){
        valueMap.put("OBJECT_IDS", this.modelMap.getRequest().getParameter("scopeSelect.BRAND_ID"));
      }
		  if(GaUtil.isNullStr(valueMap.get("OBJECT_NAMES")+"")){
        valueMap.put("OBJECT_NAMES", this.modelMap.getRequest().getParameter("scopeSelect.NAME"));
      }
	   
		  BatchBiz biz = new BatchBiz(this.getUserACL());
		   biz.saveInventoryBatch(valueMap, true);
		 return this.createReturnJSON(true, "保存成功", true, false, "inBatchList", "");
	  }
	  
	  
}
