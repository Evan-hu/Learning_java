package com.ga.erp.biz.store.storecomm;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.GaUtil;

public class StoreCommMainPage extends UnitPage{
	
  private static final long serialVersionUID = 1L;
  private ListView storeCommList;
  private String inBatchId;
  private String batchStoreId;
  
  @Override
  public void initController() throws Exception {
   
    
    this.storeCommList = new StoreCommListView("storeCommList", this.modelMap);
    inBatchId=this.storeCommList.getRequestValue("inBatchID");
    batchStoreId=this.storeCommList.getRequestValue("storeId");

    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.storeCommList, PageEvent.PARAMTYPE_PAGEQUERY);
    
    ActionButton action = this.storeCommList.regAddAction("newStoreComm","新建","/storeComm/storecomm_detail.htm");
    SubmitTool.submitToDialog(action,"newStoreComm", "新建",950,250,this.modelMap.getPreNavInfoStr());
    
    action = this.storeCommList.regEditAction("editStoreComm","查看/编辑", "/storeComm/storecomm_detail.htm");
    SubmitTool.submitToDialog(action, "editStoreComm", "查看/编辑",1000,380,this.modelMap.getPreNavInfoStr());
  }
  
  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.storeCommList);
    return layout;
  }
  
  public void pageLoad(QueryPageData queryPagedata) {
    try {
      StoreCommBiz biz = new StoreCommBiz(this.getUserACL());
      this.storeCommList.bindData(biz.queryStoreCommList(queryPagedata, this.storeCommList.getFieldList(), null,
          GaUtil.isNullStr(inBatchId)?null:Long.parseLong(inBatchId),GaUtil.isNullStr(batchStoreId)?null:Long.parseLong(batchStoreId)
          ));
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM, "页面加载异常");
    }
  }
  
}
