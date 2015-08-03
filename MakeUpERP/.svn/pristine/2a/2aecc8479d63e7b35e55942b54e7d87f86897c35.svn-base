package com.ga.erp.biz.store;

import java.util.Map;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class StoreMainPage extends UnitPage{
	
  private static final long serialVersionUID = 1L;
  private ListView storeList;
  
  @Override
  public void initController() throws Exception {
	  
    this.storeList = new StoreListView("storeList", this.modelMap);
    this.storeList.setQueryRows(2);
    
    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.storeList, PageEvent.PARAMTYPE_PAGEQUERY);
    event.addEventParam(this.storeList, PageEvent.PARAMTYPE_QUERYMAP);
    
    ActionButton action = this.storeList.regAddAction("newStore","新建","/store/store_detail.htm");
    SubmitTool.submitToDialog(action,"newStore", "新建",800,250,this.modelMap.getPreNavInfoStr());
    
    action = this.storeList.regEditAction("editStore","查看/编辑", "/store/store_detail.htm");
    SubmitTool.submitToDialog(action, "editStore", "查看/编辑",1000,460,this.modelMap.getPreNavInfoStr());
    
    action = this.storeList.regEditAction("mtestOp","多tab测试", "/system/mtab_test.htm");
    SubmitTool.submitToDialog(action,"mtestOp", "多tab测试",1000,700,this.modelMap.getPreNavInfoStr());
    
    this.storeList.regStateAction(this.getSelfUrl(), this, "STORE");
    
  }
  
  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.storeList);
    return layout;
  }
  
  public void pageLoad(QueryPageData queryPagedata, Map<String, Object> valueMap) {
    try {
      StoreBiz biz = new StoreBiz(this.getUserACL());
      this.storeList.bindData(biz.queryStoreList(queryPagedata, valueMap, this.storeList.getFieldList(),null));
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM, "页面加载异常");
    }
  }
  
}
