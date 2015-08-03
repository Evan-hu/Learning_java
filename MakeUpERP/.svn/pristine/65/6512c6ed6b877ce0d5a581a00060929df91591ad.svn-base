package com.ga.erp.biz.content.msgsend;

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
public class MsgSendMainPage extends UnitPage{
	
  private ListView shopList;
  
  @Override
  public void initController() throws Exception {
	  
    this.shopList = new MsgSendListView("shopList", this.modelMap);

    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.shopList, PageEvent.PARAMTYPE_PAGEQUERY);
    
    ActionButton action = this.shopList.regAddAction("newShop","新建","/system/shop_detail.htm");
    SubmitTool.submitToDialog(action,"newShop", "新建店家",800,480,this.modelMap.getPreNavInfoStr());
    
    action = this.shopList.regEditAction("editShop","查看/编辑", "/system/shop_detail.htm");
    SubmitTool.submitToDialog(action, "editShop", "查看/编辑",800,480,this.modelMap.getPreNavInfoStr());
  }
  
  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.shopList);
    return layout;
  }
  
  /**
   * 页面初始化加载
   * @param modelMap
   */
  public void pageLoad(QueryPageData queryPagedata) {
    try {
      MsgSendBiz biz = new MsgSendBiz(this.getUserACL());
      this.shopList.bindData(biz.queryShopList(queryPagedata, this.shopList.getFieldList()));
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM, "页面加载异常");
    }
  }
  
}
