package com.ga.erp.biz.activity.scorelog;

import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

@SuppressWarnings("serial")
public class ScoreLogMainPage extends UnitPage{
	
  private ListView scoreLogList;
  
  @Override
  public void initController() throws Exception {
	  
    this.scoreLogList = new ScoreLogListView("scoreLogList", this.modelMap);
    this.scoreLogList.setQueryRows(2);

    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.scoreLogList, PageEvent.PARAMTYPE_PAGEQUERY);
  }
  
  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.scoreLogList);
    return layout;
  }
  
  public void pageLoad(QueryPageData queryPagedata) {
    try {
      String type = this.modelMap.getRequest().getParameter("TYPE");
      ScoreLogBiz biz = new ScoreLogBiz(this.getUserACL());
      this.scoreLogList.bindData(biz.queryScoreLogList(queryPagedata, this.scoreLogList.getFieldList(), null, type));
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM, "“≥√Êº”‘ÿ“Ï≥£");
    }
  }
  
}
