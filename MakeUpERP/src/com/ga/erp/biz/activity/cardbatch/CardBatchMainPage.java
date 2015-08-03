package com.ga.erp.biz.activity.cardbatch;

import java.util.Map;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

@SuppressWarnings("serial")
public class CardBatchMainPage extends UnitPage {
  
  private CardBatchListView batchListView;

  @Override
  public void initController() throws Exception {
    
    this.batchListView = new CardBatchListView("batchListView", this.modelMap);
    
    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.batchListView, PageEvent.PARAMTYPE_PAGEQUERY);
    event.addEventParam(this.batchListView, PageEvent.PARAMTYPE_QUERYMAP);
    
    //***********ע����Ӱ�ť
    ActionButton action = this.batchListView.regAddAction("addBatchBtn", "��������", "/activity/cardbatch_detail.htm");
    SubmitTool.submitToDialog(action, "addBatchDlg", "������Ϣ", 700, 340, this.modelMap.getPreNavInfoStr());
    
    //***********ע��༭��ť
    action = this.batchListView.regEditAction("editBatchBtn", "�鿴/�༭", "/activity/cardbatch_detail.htm");
    SubmitTool.submitToDialog(action, "editBatchDlg", "������Ϣ", 700, 312, this.modelMap.getPreNavInfoStr());
    
  }

  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.batchListView);
    return layout;
  }
  
  public void pageLoad(QueryPageData pageData,Map<String, Object> map){
    try {
      BatchBiz batchBiz=new BatchBiz(this.getUserACL());
      PageResult result=batchBiz.queryCardBatchLists(pageData, map, this.batchListView.getFieldList());
      this.batchListView.bindData(result);
    }catch (BizException e) {
      throw e;
    }
    catch (Exception e) {

    }
  }

}
