package com.ga.erp.biz.activity.scorerule;

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
public class ScoreRuleMainPage extends UnitPage{
	
  private ListView ruleList;
  
  @Override
  public void initController() throws Exception {
	  
    this.ruleList = new ScoreRuleListView("ruleList", this.modelMap);

    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.ruleList, PageEvent.PARAMTYPE_PAGEQUERY);
    
    ActionButton action = this.ruleList.regAddAction("newRule","�½�","/activity/scorerule_detail.htm");
    SubmitTool.submitToDialog(action,"newRule", "�½����ֹ���",800,280,this.modelMap.getPreNavInfoStr());
    
    action = this.ruleList.regEditAction("editRule","�鿴/�༭", "/activity/scorerule_detail.htm");
    SubmitTool.submitToDialog(action, "editRule", "�鿴/�༭",800,280,this.modelMap.getPreNavInfoStr());
  }
  
  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.ruleList);
    return layout;
  }
  
  public void pageLoad(QueryPageData queryPagedata) {
    try {
      ScoreRuleBiz biz = new ScoreRuleBiz(this.getUserACL());
      this.ruleList.bindData(biz.queryRuleList(queryPagedata, this.ruleList.getFieldList()));
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM, "ҳ������쳣");
    }
  }
  
}
