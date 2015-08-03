package com.ga.erp.biz.system;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.ClickUtil;
import com.ga.click.util.GaUtil;

@SuppressWarnings("serial")
public class OpMainPage  extends UnitPage{
	
  private OpListView opListView;
  private String type;
  
  @Override
  public void initController() throws Exception {
    this.opListView = new OpListView("opList",this.modelMap);
    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.opListView,PageEvent.PARAMTYPE_PAGEQUERY);
    
    type = this.modelMap.getRequest().getParameter("type");
    ActionButton action = this.opListView.regAddAction("newOp","新建","/system/op_detail.htm");
    SubmitTool.submitToDialog(action,"newOp", "新建操作员",800,380,this.modelMap.getPreNavInfoStr());
    action.addParam("TYPE", type);
    
    action = this.opListView.regEditAction("editOp","查看/编辑", (type!= null && "1".equals(type)) ? "/system/op_detail.htm" : "/system/op_noedit_detail.htm");
    SubmitTool.submitToDialog(action,"editOp", "查看/编辑",900,430,this.modelMap.getPreNavInfoStr());
    action.addParam("TYPE", type);
    
    this.opListView.regStateAction(this.getSelfUrl(), this, "OP");
    
    action = this.opListView.regEditAction("editPassword","修改密码", "/system/op_pass_detail.htm");
    SubmitTool.submitToDialog(action,"editPassword", "修改密码",400,230,this.modelMap.getPreNavInfoStr());
  }

  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.opListView);
    ClickUtil.setControlLayoutH(this.opListView.getViewControl(), 0);
    return layout;
  }
  
  public void pageLoad(QueryPageData page) {
    try {
      if(GaUtil.isNullStr(type)){
        type = "1";
      }
      OpBiz biz = new OpBiz(this.getUserACL());
      this.opListView.bindData(biz.queryOpList(page,this.opListView.getFieldList(), type));
    } catch(BizException e) {
      throw e;
    } catch(Exception e) {
      throw new BizException("查询操作员列表异常");
    }
  }

  
}
