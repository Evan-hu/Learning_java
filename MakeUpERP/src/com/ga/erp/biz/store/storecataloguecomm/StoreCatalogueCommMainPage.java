package com.ga.erp.biz.store.storecataloguecomm;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

@SuppressWarnings("serial")
public class StoreCatalogueCommMainPage extends UnitPage {

  private ListView storeCatalogueCommListView;
  
  @Override
  public void initController() throws Exception {
    
    storeCatalogueCommListView = new StoreCatalogueCommListView("storeCatalogueCommListView",this.modelMap);
    this.storeCatalogueCommListView.setQueryRows(2);
    
    PageEvent event  = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.storeCatalogueCommListView,PageEvent.PARAMTYPE_PAGEQUERY);
    
    ActionButton action = this.storeCatalogueCommListView.regAddAction("addStoreCatalogueComm","新建", "/store/storecataloguecomm_detail.htm");
    SubmitTool.submitToDialog(action, "addStoreCatalogueComm","新建",800, 200,this.modelMap.getPreNavInfoStr());
    
    action = this.storeCatalogueCommListView.regEditAction("editStoreCatalogueComm", "查看 /编辑", "/store/storecataloguecomm_detail.htm");
    SubmitTool.submitToDialog(action, "editStoreCatalogueComm","查看/编辑",800, 250,this.modelMap.getPreNavInfoStr());
  }

  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.storeCatalogueCommListView);
    return layout;	
  }
  
  public void pageLoad(QueryPageData queryPageData) {
    try {      
      StoreCatalogueCommBiz biz  = new StoreCatalogueCommBiz(this.getUserACL());
      this.storeCatalogueCommListView.bindData(biz.queryStoreTypeList(queryPageData,this.storeCatalogueCommListView.getFieldList()));      
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"页面加载异常");
    }
  }
  
}
