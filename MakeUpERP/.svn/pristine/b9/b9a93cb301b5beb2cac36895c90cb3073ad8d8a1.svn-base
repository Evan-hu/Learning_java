package com.ga.erp.biz.supplier.contract;

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
import com.ga.click.util.GaUtil;

public class ContractDeatilPage extends UnitPage {

	private static final long serialVersionUID = 1L;
	private ContractFormView formView;
	Map<String, Object> map = null;

	  @Override
	  public void initController() throws Exception {

	    this.formView = new ContractFormView("contractDetail", this.modelMap);

	    PageEvent event = this.regPageLoadEvent("pageLoad");
	    event.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE,"CONTRACT_ID");
	    
	    ActionButton action = new ActionButton(this.getClass(), "saveContract", "保存", this.getSelfUrl(), null);
	    action.bindForm(this.formView.getViewID(),true);
	    SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());
	    
	    action.addParam(GaConstant.FIXPARAM_EDITMODE, this.modelMap.getPageEditMode());
	    this.formView.regAction(action);
	    
	    event = this.regPageEvent(action, "saveContract");
	    event.addEventParam(this.formView, PageEvent.PARAMTYPE_DATAMAP);
	    
	}

	@Override
	public Layout initLayout() {
		FormLayout formLayOut = new FormLayout("",this.formView.getDataForm(),2);
		formLayOut.mergeCellField("auto", this.modelMap.getPageEditMode() == 
				GaConstant.EDITMODE_NEW ? 2 : GaUtil.isNullStr(map.get("SUPPLIER_ID") + "") == true ? 4 : 3 , 0, 1, "CONTENT");
	  	this.formView.getDataForm().setLayout(formLayOut);
	  	ViewPageLayout layout = new ViewPageLayout(this);
	  	layout.addControl(this.formView);
	    return layout;
	}
	
	  public void pageLoad(Long id) {
		    try {
				map = this.formView.getViewParam();
	    		if(!GaUtil.isNullStr(map.get("SUPPLIER_ID") + "")){
					this.formView.getFieldList().remove(0);
					this.formView.getFieldList().remove(1);
				}
		    	if(this.modelMap.getPageEditMode() == GaConstant.EDITMODE_NEW){
				    this.formView.bindData(this.formView.getViewParam());
		    	} else {
		    		 ContractBiz biz  = new ContractBiz(this.getUserACL());
		    		 this.formView.bindData(biz.queryContractDetail(id));    
		    	 }
		    } catch(BizException ex) {
		         throw ex;
		    }  catch(Exception e) {
		          throw new BizException(BizException.SYSTEM,"页面加载异常");
		    }
		  }
	  
	  public ActionResult saveContract(Map<String, Object> valueMap) {
			 ContractBiz biz = new ContractBiz(this.getUserACL());
		      if (this.getModelMap().getPageEditMode() == GaConstant.EDITMODE_NEW) {
		        biz.saveContract(valueMap, true);
		      } else if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
		        biz.saveContract(valueMap, false);
	  }
			return this.createReturnJSON(true, "保存成功", true, false, valueMap.get("SUPPLIER_NAME") != null ? "contractList" : "contractListView", "");
	  }
}
	  

