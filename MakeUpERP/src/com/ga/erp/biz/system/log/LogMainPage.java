package com.ga.erp.biz.system.log;

import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.ClickUtil;

@SuppressWarnings("serial")
public class LogMainPage extends UnitPage{
  private LogListView  logListView;
  @Override
  public void initController() throws Exception {
    // TODO Auto-generated method stub
    this.logListView = new LogListView("logListView",this.modelMap);
    PageEvent event =this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.logListView,PageEvent.PARAMTYPE_PAGEQUERY);
  }
  
  public void pageLoad(QueryPageData queryData) {
    try {
      this.logListView.bindAllLog(queryData);
    } catch(BizException e) {
      throw e;
    } catch(Exception e) {
      throw new BizException("查询日志列表异常");
    }
  }

  @Override
  public Layout initLayout() {
    // TODO Auto-generated method stub
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(logListView);
    ClickUtil.setControlLayoutH(this.logListView.getViewControl(), 0);
    return layout;
  }
  
}
