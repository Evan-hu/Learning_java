package com.ga.erp.biz.stock.shelf;

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
public class ShelfDetailPage extends UnitPage {
	
  private FormView shelfForm;
  
  @Override
  public void initController() throws Exception {
	  
    this.shelfForm = new ShelfFormView("shelfForm",this.modelMap);   
    PageEvent event = this.regPageLoadEvent("pageLoad");   
    event.addEventParam(this.shelfForm, PageEvent.PARAMTYPE_REQUEST, "pID");
    event.addEventParam(this.shelfForm, PageEvent.PARAMTYPE_REQUEST,"pName");
    
    this.shelfForm.regAddSaveEvent("saveShelf", "saveShelf", this, false);//设置事件的自动隐藏 this表示当前页面
  }

  @Override
  public Layout initLayout() {
	FormLayout formLayOut = new FormLayout("",this.shelfForm.getDataForm(),2);
	this.shelfForm.getDataForm().setLayout(formLayOut);
	ViewPageLayout layout = new ViewPageLayout(this);
	layout.addControl(this.shelfForm);
    return layout;
  }

  public void pageLoad(String pID, String pName) {
	try {   
	    Map<String, Object> paraMap=new HashMap<String, Object>();
	    paraMap.put("P_SHELF_ID", pID);
	    paraMap.put("P_SHELF_NUM", pName);
	    this.shelfForm.bindData(paraMap);
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"页面加载异常");
    }
  }
  
  public ActionResult saveShelf(Map<String, Object> sortMap){
	    try {
	    	ShelfBiz biz = new ShelfBiz(this.getUserACL());
	      biz.saveShelf(sortMap, true);
	      return this.createReturnJSON(true, "保存货架成功", true, true, "shelfTree", "");
	    } catch (BizException ex) {
	        throw ex;
	    }catch (Exception e) {
	      throw new BizException(BizException.SYSTEM,"保存货架异常");
	    }
	  }
  
}
