package com.ga.erp.biz.comm;

import org.apache.click.ActionResult;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class CommMainPage extends UnitPage{
	
  private static final long serialVersionUID = 1L;
  private ListView commList;
  
  @Override
  public void initController() throws Exception {
	  
    this.commList = new CommListView("commList", this.modelMap);
    this.commList.setQueryRows(3);
    this.commList.setMultiSelect(true);

    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.commList, PageEvent.PARAMTYPE_PAGEQUERY);

    ActionButton action = this.commList.regAddAction("newComm","新建","/comm/comm_detail.htm");
    SubmitTool.submitToDialog(action,"newComm", "新建商品",1000,480,this.modelMap.getPreNavInfoStr());
    
    action = this.commList.regEditAction("editComm","查看/编辑", "/comm/comm_detail.htm");
    SubmitTool.submitToDialog(action, "editComm", "查看/编辑",1000,630,this.modelMap.getPreNavInfoStr());
    
    this.commList.regStateAction(this.getSelfUrl(), this, "COMMODITY");   
    
    this.commList.regAuditAction(this.commList, "COMMODITY");    
  }
  
  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.commList);
    return layout;
  }
  
  public void pageLoad(QueryPageData queryPagedata) {
    try {
      CommBiz biz = new CommBiz(this.getUserACL());
      this.commList.bindData(biz.queryCommList(queryPagedata, this.commList.getFieldList()));
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
    	e.printStackTrace();
      throw new BizException(BizException.SYSTEM, "页面加载异常");
    }
  }
  
  public ActionResult downComm(String ids) {
    try {
      CommBiz biz = new CommBiz(this.getUserACL());
      biz.stopCommodity(ids);
      return this.createReturnJSON(true, "下架成功",false,false,this.commList.getViewID(),"");
    } catch(BizException e) {
      throw e;
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"");
    }
  }

}
