package com.ga.erp.biz.store.storealert;

import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.GaUtil;

@SuppressWarnings("serial")
public class StoreAlertMainPage extends UnitPage{
	
  private ListView storeAlertList;

  @Override
  public void initController() throws Exception {
	  
    this.storeAlertList = new StoreAlertListView("storeAlertList", this.modelMap);
    
    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.storeAlertList, PageEvent.PARAMTYPE_PAGEQUERY); 
  }
  
  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.storeAlertList);
    layout.setControlLayoutH(this.storeAlertList.getViewControl(),0);
    return layout;
  }
  
  public void pageLoad(QueryPageData queryPagedata) {
    try {
      String type = this.modelMap.getRequest().getParameter("type");
      if(GaUtil.isNullStr(type)){
        type = "1";
      }
      StoreAlertBiz biz = new StoreAlertBiz(this.getUserACL());
      this.storeAlertList.bindData(biz.queryStoreAlertList(queryPagedata, this.storeAlertList.getFieldList(),type));
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
    	e.printStackTrace();
      throw new BizException(BizException.SYSTEM, "“≥√Êº”‘ÿ“Ï≥£");
    }
  }  
}
