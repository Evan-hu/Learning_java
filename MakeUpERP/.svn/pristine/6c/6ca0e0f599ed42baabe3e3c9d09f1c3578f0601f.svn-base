package com.ga.erp.biz.stock.inventorydiffbill.comm;

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
	private CommFormView formView;
	
	@Override
	public void initController() throws Exception {
		
		 this.formView = new CommFormView("DBCommDetail", this.modelMap);
		 
		 PageEvent event = this.regPageLoadEvent("pageLoad");
		 event.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE,"DIFF_BILL_COMM_ID");
		    
		 ActionButton action = new ActionButton(this.getClass(), "saveIComm","保存", this.getSelfUrl(), null);
	   action.bindForm(this.formView.getViewID(), true);
	   SubmitTool.submitToParentTable(action,this.modelMap.getPreNavInfoStr(),"tableDiFiComm");//返回主表页面

	   action.addParam(GaConstant.FIXPARAM_EDITMODE,this.modelMap.getPageEditMode());
	   this.formView.regAction(action);   
	}

	@Override
	public Layout initLayout() {
		FormLayout formLayOut = new FormLayout("",this.formView.getDataForm(),2);
	  	this.formView.getDataForm().setLayout(formLayOut);
	  	ViewPageLayout layout = new ViewPageLayout(this);
		layout.addControl(this.formView);
	    return layout;
	}
	
	  public void pageLoad(Long id) {
		 try {
		   
		   List<DbField> fielList=formView.getFieldList();
		    for(int i=1 ;i<fielList.size()-1;i++){
		      fielList.get(i).setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
		    }
		    	CommBiz biz  = new CommBiz(this.getUserACL());
		    	this.formView.bindData(biz.queryDBCommDetail(id));    
		    	 
		  
		    }  catch(Exception e) {
		          throw new BizException(BizException.SYSTEM,"页面加载异常");
		    }
		  }
	  
	  public ActionResult saveDBComm(Map<String, Object> valueMap) {
		  CommBiz biz = new CommBiz(this.getUserACL());
		  if (this.getModelMap().getPageEditMode() == GaConstant.EDITMODE_NEW) {
        biz.saveDBComm(valueMap, true);
      } else if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
        biz.saveDBComm(valueMap, false);
      }
      System.out.println(valueMap.get("BILL_NUM")+"bill_num---------");
	    return this.createReturnJSON(true, "保存成功", true, false, valueMap.get("BILL_NUM") != null ? "DBCommList" : "diffbillCommList", "");
	  }
}
