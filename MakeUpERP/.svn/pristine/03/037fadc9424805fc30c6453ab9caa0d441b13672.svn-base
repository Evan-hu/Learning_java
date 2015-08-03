/**
 * Copyright (c) 2014 GA
 * All right reserved.
 */
package com.ga.erp.biz.member.viplog;

import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;
/**
 * 类描述
 * @author jzk
 * @create_time 2014-5-2 上午11:43:53
 * @project MakeUpERP
 */
public class ConsumeLogMainPage extends UnitPage{

  private static final long serialVersionUID = 1L;
  private ListView consumeLogList;

  @Override
  public void initController() throws Exception {
    
    this.consumeLogList = new ConsumeLogListView("vipLogList", this.modelMap);
    
    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.consumeLogList, PageEvent.PARAMTYPE_PAGEQUERY);
  }

  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.consumeLogList);
    return layout;
  }

  public void pageLoad(QueryPageData pageData){
    try {
      ConsumeLogBiz biz = new ConsumeLogBiz(this.getUserACL());
      this.consumeLogList.bindData(biz.queryConsumeLogList(pageData, this.consumeLogList.getFieldList()));
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM, "页面加载异常");
    }
  }
}
