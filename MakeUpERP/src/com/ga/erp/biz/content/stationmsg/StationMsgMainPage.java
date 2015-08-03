package com.ga.erp.biz.content.stationmsg;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class StationMsgMainPage extends UnitPage{
	
  private static final long serialVersionUID = 1L;
  private ListView msgList;
  private String type = "";
  
  @Override
  public void initController() throws Exception {
	   
    this.msgList = new StationMsgListView("msgList", this.modelMap);

    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.msgList, PageEvent.PARAMTYPE_PAGEQUERY);
    
    type = this.modelMap.getRequest().getParameter("type");
    ActionButton action = this.msgList.regAddAction("newMsg","新建","/content/stationmsg_detail.htm");
    SubmitTool.submitToDialog(action,"newMsg", "新建",800,320,this.modelMap.getPreNavInfoStr());
    action.addParam("TYPE", type);
    
    action = this.msgList.regEditAction("editMsg","详情", "/content/stationmsg_detail.htm");
    SubmitTool.submitToDialog(action, "editMsg", "详情",700,400,this.modelMap.getPreNavInfoStr());
    action.addParam("TYPE", type);
  }
  
  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.msgList);
    return layout;
  }
  
  public void pageLoad(QueryPageData queryPagedata) {
    try {
      StationMsgBiz biz = new StationMsgBiz(this.getUserACL());
      this.msgList.bindData(biz.queryMsgList(queryPagedata, this.msgList.getFieldList(), type));
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM, "页面加载异常");
    }
  }
}
