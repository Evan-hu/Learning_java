package com.ga.erp.biz.member;

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
public class MemberMainPage extends UnitPage{
	
  private ListView memberList;
  
  @Override
  public void initController() throws Exception {
	  
    this.memberList = new MemberListView("memberList", this.modelMap);
    this.memberList.setQueryRows(2);
    this.memberList.setExport(true);
    
    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.memberList, PageEvent.PARAMTYPE_PAGEQUERY);
    
    ActionButton action = this.memberList.regEditAction("editMember","查看/编辑", "/member/member_detail.htm");
    SubmitTool.submitToDialog(action, "editMember", "查看/编辑",800,480,this.modelMap.getPreNavInfoStr());
    
    this.memberList.regStateAction(this.getSelfUrl(), this, "MEMBER");
    
    action = this.memberList.regEditAction("editPwd","修改密码", "/member/pwd_detail.htm");
    SubmitTool.submitToDialog(action,"editPwd", "修改密码",400,230,this.modelMap.getPreNavInfoStr());
    
    
  }
  
  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.memberList);
    return layout;
  }
  
  /**
   * 页面初始化加载
   * @param modelMap
   */
  public void pageLoad(QueryPageData queryPagedata) {
    try {
      MemberBiz biz = new MemberBiz(this.getUserACL());
      this.memberList.bindData(biz.queryMemberList(queryPagedata, this.memberList.getFieldList(),null));
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM, "页面加载异常");
    }
  }
  
}
