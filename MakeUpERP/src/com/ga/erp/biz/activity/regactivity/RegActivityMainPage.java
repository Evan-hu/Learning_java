package com.ga.erp.biz.activity.regactivity;

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
public class RegActivityMainPage extends UnitPage{
	
  private ListView regActivityListView;
  
  @Override
  public void initController() throws Exception {
	  
    this.regActivityListView = new RegActivityListView("regActivityListView", this.modelMap);
    this.regActivityListView.setQueryRows(2);
    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.regActivityListView, PageEvent.PARAMTYPE_PAGEQUERY);
    
    ActionButton action = this.regActivityListView.regAddAction("newReg","新建","/regactivity/regActivity_mtpage.htm");
    SubmitTool.submitToDialog(action,"newReg", "新建",1000,600,this.modelMap.getPreNavInfoStr());
    
    action = this.regActivityListView.regEditAction("editReg","查看/编辑", "/regactivity/regActivity_mtpage.htm");
    SubmitTool.submitToDialog(action, "editReg", "查看/编辑",1000,650,this.modelMap.getPreNavInfoStr());
  }
  
  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.regActivityListView);
    return layout;
  }
  
  public void pageLoad(QueryPageData queryPagedata) {
    try {
      RegActivityBiz biz = new RegActivityBiz(this.getUserACL());
      this.regActivityListView.bindData(biz.queryRegActivityList(queryPagedata, this.regActivityListView.getFieldList()));
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM, "页面加载异常");
    }
  }
  
}
