package com.ga.erp.biz.comm.sort;

import java.util.HashMap;
import java.util.Map;
import org.apache.click.ActionResult;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;

@SuppressWarnings("serial")
public class SortDetailPage extends UnitPage {
	
  private FormView sortForm;
  
  @Override
  public void initController() throws Exception {
	  
    this.sortForm = new SortFormView("sortForm",this.modelMap);   
    
    PageEvent event = this.regPageLoadEvent("pageLoad");   
    event.addEventParam(this.sortForm, PageEvent.PARAMTYPE_REQUEST, "pID");
    event.addEventParam(this.sortForm, PageEvent.PARAMTYPE_REQUEST,"pName");
    
    this.sortForm.regAddSaveEvent("saveSort", "saveSort", this, false);
  }

  @Override
  public Layout initLayout() {
  	FormLayout formLayOut = new FormLayout("",this.sortForm.getDataForm(),2);
  	this.sortForm.getDataForm().setLayout(formLayOut);
  	ViewPageLayout layout = new ViewPageLayout(this);
  	layout.addControl(this.sortForm);
    return layout;
  }

  public void pageLoad(String pID, String pName) {
	try {   
	    Map<String, Object> paraMap=new HashMap<String, Object>();
	    paraMap.put("P_ID", pID);
	    paraMap.put("PSORT_NAME", pName);
	    this.sortForm.bindData(paraMap);
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"ҳ������쳣");
    }
  }
  
  public ActionResult saveSort(Map<String, Object> sortMap){
	    try {
	      SortBiz biz = new SortBiz(this.getUserACL());
	      biz.saveSort(sortMap, true);
	      return this.createReturnJSON(true, "�������ɹ�", true, true, "sortTree", "");
	    } catch (BizException ex) {
	        throw ex;
	    }catch (Exception e) {
	      throw new BizException(BizException.SYSTEM,"��������쳣");
	    }
	  }
  
}
