package com.ga.erp.biz.purchase.list;

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

public class PurListDetailPage extends UnitPage {

	private static final long serialVersionUID = 1L;
	private PurListFormView formView;
	
	@Override
	public void initController() throws Exception {
		
		 this.formView = new PurListFormView("purListDetail", this.modelMap);
		 
		 PageEvent event = this.regPageLoadEvent("pageLoad");
		 event.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE,"PURCHASE_LIST_ID");
		    
		 ActionButton action = new ActionButton(this.getClass(), "savePurList", "保存", this.getSelfUrl(), null);
		 action.bindForm(this.formView.getViewID(),true);
		 SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());
		    
		 action.addParam(GaConstant.FIXPARAM_EDITMODE, this.modelMap.getPageEditMode());
		 this.formView.regAction(action);
		    
		 event = this.regPageEvent(action, "savePurList");
		 event.addEventParam(this.formView, PageEvent.PARAMTYPE_DATAMAP);
	}

	@Override
	public Layout initLayout() {
		FormLayout formLayOut = new FormLayout("",this.formView.getDataForm(),2);
		//合并单元格
		//formLayOut.mergeCellField("auto", 2, 0, 1, "STOCK");
	  	this.formView.getDataForm().setLayout(formLayOut);
	  	ViewPageLayout layout = new ViewPageLayout(this);
		layout.addControl(this.formView);
	    return layout;
	}
	
	  public void pageLoad(Long id) {
		 try {
		    	 if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
		    		 PurListBiz biz  = new PurListBiz(this.getUserACL());
		    		 this.formView.bindData(biz.queryPurDetail(id));    
		    	 }
		    } catch(BizException ex) {
		         throw ex;
		    }  catch(Exception e) {
		          throw new BizException(BizException.SYSTEM,"页面加载异常");
		    }
		  }
	  
	  public ActionResult savePurList(Map<String, Object> valueMap) {
		  PurListBiz biz = new PurListBiz(this.getUserACL());
		      if (this.getModelMap().getPageEditMode() == GaConstant.EDITMODE_NEW) {
		        biz.savePurList(valueMap, true);
		      } else if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
		        biz.savePurList(valueMap, false);
	  }
		return this.createReturnJSON(true, "保存成功", true, false, "purList", "");
	  }
}
