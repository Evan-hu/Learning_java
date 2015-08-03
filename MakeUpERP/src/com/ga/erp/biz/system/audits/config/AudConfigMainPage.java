package com.ga.erp.biz.system.audits.config;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class AudConfigMainPage extends UnitPage{
	
  private static final long serialVersionUID = 1L;
  private ListView audConfigList;
  
  @Override
  public void initController() throws Exception {
	  
    this.audConfigList = new AudConfigListView("audConfigList", this.modelMap);
    
    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.audConfigList, PageEvent.PARAMTYPE_PAGEQUERY);
    
    ActionButton action = this.audConfigList.regAddAction("newAudConfig","新建","/system/audconfig_detail.htm");
    SubmitTool.submitToDialog(action,"newAudConfig", "新建",750,210,this.modelMap.getPreNavInfoStr());
    
    action = this.audConfigList.regEditAction("editAudConfig","查看/编辑", "/system/audconfig_detail.htm");
    SubmitTool.submitToDialog(action, "editAudConfig", "查看/编辑",650,340,this.modelMap.getPreNavInfoStr());
    this.audConfigList.regStateAction(this.getSelfUrl(), this, "AUDITING_CONFIG");

  }
  
  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.audConfigList);
    return layout;
  }
  
  public void pageLoad(QueryPageData queryPagedata) {
    try {
      AudConfigBiz biz = new AudConfigBiz(this.getUserACL());
      this.audConfigList.bindData(biz.queryAudConfigList(queryPagedata, this.audConfigList.getFieldList()));
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM, "页面加载异常");
    }
  }
  
}
