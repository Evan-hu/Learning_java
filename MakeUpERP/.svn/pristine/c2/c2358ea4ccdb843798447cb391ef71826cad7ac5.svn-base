package com.ga.erp.biz.stock.stockcomm;

import java.util.HashMap;
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

public class StockCommDetailPage extends UnitPage {

	private static final long serialVersionUID = 1L;
	private StockCommFormView formView;
	
	
	@Override
	public void initController() throws Exception {
		
		 this.formView = new StockCommFormView("stocktDetail", this.modelMap);
		 
		 PageEvent event = this.regPageLoadEvent("pageLoad"); 
		 event.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE,"STOCK_COMM_ID");
		 event.addEventParam(this.formView, PageEvent.PARAMTYPE_REQUEST, "ShelfID");
		 event.addEventParam(this.formView, PageEvent.PARAMTYPE_REQUEST,"ShelfNum");
		    
		 ActionButton action = new ActionButton(this.getClass(), "saveStockComm", "保存", this.getSelfUrl(), null);
		 action.bindForm(this.formView.getViewID(),true);
		 SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());
		    
		 action.addParam(GaConstant.FIXPARAM_EDITMODE, this.modelMap.getPageEditMode());
		 this.formView.regAction(action);
		    
		 event = this.regPageEvent(action, "saveStockComm");
		 event.addEventParam(this.formView, PageEvent.PARAMTYPE_DATAMAP);
	}

	@Override
	public Layout initLayout() {
	
	    FormLayout formLayOut = new FormLayout("",this.formView.getDataForm(),2);
//		formLayOut.mergeCellField("auto", 6, 0, 1, "FANGMIAN");
//		formLayOut.mergeCellField("auto", 7, 0, 1, "NOTE");
		this.formView.getDataForm().setLayout(formLayOut);
		ViewPageLayout layout = new ViewPageLayout(this);
		layout.addControl(this.formView);
	    return layout;
	}
	
	  public void pageLoad(Long id,String shelfId,String shelfNum) {
		 try {
		   Map<String, Object> map = this.formView.getViewParam();
		   if(map.get("STOCK_ID") != null){
         this.formView.getFieldList().remove(4);
       }
		   if(!GaUtil.isNullStr(shelfNum)){
		     this.formView.getFieldByCode("SHELF_ID").setDefaultValue(shelfId);
		     this.formView.getFieldList().remove(6);
		   }
		   
		     if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
		       StockCommBiz biz  = new StockCommBiz(this.getUserACL());
		       this.formView.bindData(biz.queryStockCommDetail(id, this.formView.getFieldList()));   
		      }else{
		    	  Map<String, Object> paraMap=new HashMap<String, Object>();
//				    paraMap.put("SHELF_ID", shelfId);
				    paraMap.put("SHELF_NUM", shelfNum);  
				    paraMap.put("RELOAD_VIEW", this.modelMap.getRequest().getParameter("RELOAD_VIEW")); 
				   map.putAll(paraMap);
				    this.formView.bindData(paraMap);
		      }
		  
		    } catch(BizException ex) {
		         throw ex;
		    }  catch(Exception e) {
		          throw new BizException(BizException.SYSTEM,"页面加载异常");
		    }
		  }
	  
	  public ActionResult saveStockComm(Map<String, Object> valueMap ) {
		  
		 StockCommBiz biz = new StockCommBiz(this.getUserACL());
		 if (this.getModelMap().getPageEditMode() == GaConstant.EDITMODE_NEW) {
		   biz.saveStockComm(valueMap, true);
		  } else if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
		     biz.saveStockComm(valueMap, false);
	      }
		 String reloadViewStr = valueMap.get("SHELF_NUM") + "";
	  if(GaUtil.isNullStr(reloadViewStr)){
		 return this.createReturnJSON(true, "保存成功", true, false, "shelfCommList", "");
	  }else{
	  return this.createReturnJSON(true, "保存成功", true, false, valueMap.get("NAME") != null ? "stockCommList" : "stock_stockCommList", "");
	    
	  }
	  }
}
