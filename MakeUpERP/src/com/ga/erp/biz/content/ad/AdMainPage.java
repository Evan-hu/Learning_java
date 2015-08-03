package com.ga.erp.biz.content.ad;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class AdMainPage extends UnitPage{
	
  private static final long serialVersionUID = 1L;
  private ListView adList;

  @Override
  public void initController() throws Exception {
	  
    this.adList = new AdListView("adList", this.modelMap);
    this.adList.setQueryRows(2);

    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.adList, PageEvent.PARAMTYPE_PAGEQUERY);
    
    ActionButton action = this.adList.regAddAction("newAd","新建","/content/ad_detail.htm");
    SubmitTool.submitToDialog(action,"newAd", "新建广告",800,480,this.modelMap.getPreNavInfoStr());
    
    action = this.adList.regEditAction("editAd","查看/编辑", "/content/ad_detail.htm");
    SubmitTool.submitToDialog(action, "editAd", "查看/编辑",800,540,this.modelMap.getPreNavInfoStr());
  }
  
  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.adList);
    return layout;
  }
  
  public void pageLoad(QueryPageData queryPagedata) {
    try {
      AdBiz biz = new AdBiz(this.getUserACL());
      this.adList.bindData(biz.queryAdList(queryPagedata, this.adList.getFieldList()));
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM, "页面加载异常");
    }
  }
  
}
