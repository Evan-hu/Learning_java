package com.ga.erp.biz.statistic.abc;

import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.GaUtil;

public class AbcMainPage extends UnitPage{
	
  private static final long serialVersionUID = 1L;
  private ListView abcListView;
  
  @Override
  public void initController() throws Exception {
	  
    this.abcListView = new AbcListView("abcListView", this.modelMap);

    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.abcListView, PageEvent.PARAMTYPE_PAGEQUERY);
    
//    ActionButton action = this.costOrderList.regAddAction("addCostOrder","新增业务单", "/costorder/costorder_detail.htm");
//    SubmitTool.submitToDialog(action, "addCostOrder", "新增业务单",800,370,this.modelMap.getPreNavInfoStr());
//    action.addParam("type", type);
//    
//    action = this.costOrderList.regEditAction("editCostOrder","查看/编辑", "/costorder/costorder_detail.htm");
//    SubmitTool.submitToDialog(action, "editCostOrder", "查看/编辑",800,420,this.modelMap.getPreNavInfoStr());
//    action.addParam("type", type);
    
  }
  
  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.abcListView);
    return layout;
  }
  
  public void pageLoad(QueryPageData queryPagedata) {
    try {
      String storeID=this.getUserACL().getStoreID();
      
      String[] storeIds=storeID.split(",");
      if(!GaUtil.isNullStr(storeID)&&storeIds.length==1){
        abcListView.removeDbField("STORE_NAME");
      }
      AbcBiz biz = new AbcBiz(this.getUserACL());
      StringBuilder builder = new StringBuilder("<lable style='color:red;'>销售占比70%为A类，销售占比70%-90%为B类，其他为C类</lable>");
      this.abcListView.setQueryTip(builder.toString());
      abcListView.bindData(biz.queryComm(queryPagedata,this.abcListView.getFieldList()));
      
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM, "页面加载异常");
    }
  }
  
}
