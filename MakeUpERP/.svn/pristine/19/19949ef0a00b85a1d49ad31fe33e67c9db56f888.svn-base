package com.ga.erp.biz.stock.inventorybatch.imports;

import java.util.Map;

import org.apache.click.ActionResult;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.erp.biz.stock.inventorybatch.BatchBiz;

public class ImportDetailPage extends UnitPage {

	private static final long serialVersionUID = 1L;
	private FormView importForm;
	
	@Override
	public void initController() throws Exception {
		
		 this.importForm = new ImportFormView("importform", this.modelMap);
		 
		 PageEvent event = this.regPageLoadEvent("pageLoad"); 
		 event.addEventParam(this.importForm, PageEvent.PARAMTYPE_QUERYVALUE,"INVENTORY_BATCH_ID");
		 
		 this.importForm.regEditSaveEvent("importBatchComm", "importBatchComm", this, true);
	}

	@Override
	public Layout initLayout() {
    FormLayout formLayOut = new FormLayout("",this.importForm.getDataForm(),2);
    formLayOut.mergeCellField("auto", 3, 0, 1, "FILEPATH");
		this.importForm.getDataForm().setLayout(formLayOut);
		ViewPageLayout layout = new ViewPageLayout(this);
		layout.addControl(this.importForm);
	  return layout;
	}
	
  public void pageLoad(Long id) {
    try {
      BatchBiz biz = new BatchBiz(this.getUserACL());
      for (DbField field : this.importForm.getFieldList()) {
        if(!field.getFieldCode().equals("FILEPATH")){
          field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
        }
      }
      this.importForm.bindData(biz.queryInventoryBatchDetail(id));
   } catch(BizException ex) {
      throw ex;
   } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"页面加载异常");
   }
 }
	  
  public ActionResult importBatchComm(Map<String, Object> valueMap ) {
	    ImportBiz biz = new ImportBiz(this.getUserACL());
		  String msg = biz.importBatchComm(valueMap);
		  return this.createReturnJSON(true, "导入盘点商品成功;\n" + msg + "\n", true, false, "inventoryBatchList", "");
	  }
	  
}
