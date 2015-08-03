/**
 * Copyright (c) 2011 Noah
 * All right reserved.
 */
package com.ga.erp.biz.activity.activity;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;

/**
 * 类描述
 * @author SPE
 * @create_time 2012-7-5 下午03:29:41
 * @project ShopMgr
 */
public class ActivityListPage extends UnitPage {

  private static final long serialVersionUID = 1L;
  ActivityListView listView;
  
  @Override
  public void initController() throws Exception {
    this.listView = new ActivityListView("activityListView", this.modelMap);
    listView.setMultiSelect(true);
    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(listView, PageEvent.PARAMTYPE_REQUEST);
    ActionButton action;
    
    action = this.listView.regAddAction("addActivity", "添加活动", "/activity/activity_edit.htm");
    SubmitTool.submitToDialog(action,"addActivity", "添加活动",1100,600,this.modelMap.getPreNavInfoStr());
    
    action = this.listView.regEditAction("editActivity","编辑活动", "/activity/activity_edit.htm");
    action.bindTableRowData(this.listView.getViewID());
    SubmitTool.submitToDialog(action,"editActivity", "编辑活动",1100,600,this.modelMap.getPreNavInfoStr());
     
    this.listView.regStateAction(this.getSelfUrl(), this, "DISCOUNT_ACTIVITY");

  }

  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.listView);
    layout.setControlLayoutH(this.listView.getViewControl(),0);
    return layout;
  }

  /**
   * 页面加载
   * @param modelMap
   */
  public void pageLoad(String activityType) {
    try {
      ActivityBiz biz = new ActivityBiz(this.getUserACL());
//      if(GaUtil.isNumber(activityType)){
//        getContext().getRequest().getSession().setAttribute("activityType", activityType);
//      } else {
//        activityType = String.valueOf(getContext().getRequest().getSession().getAttribute("activityType"));
//      }
      PageResult rs = biz.queryActivityList(this.listView.getViewPageQuery(),this.listView.getFieldList(), activityType);
      this.listView.bindData(rs);
      this.listView.bindListQueryForm(this.listView.getViewParam());
    } catch(BizException e) {
    	e.printStackTrace();
    	
      throw e;
    } catch(Exception ex) {
    	ex.printStackTrace();
      throw new BizException(BizException.SYSTEM,"页面加载异常");
    }
  }
}
