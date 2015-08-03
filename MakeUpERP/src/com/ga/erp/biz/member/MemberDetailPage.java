package com.ga.erp.biz.member;

import java.util.Map;

import org.apache.click.ActionResult;

import com.ga.click.control.GaConstant;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.ClickUtil;
import com.ga.erp.biz.member.activelog.ActiveLogBiz;
import com.ga.erp.biz.member.activelog.ActiveLogListView;
import com.ga.erp.biz.member.viplog.VipLogBiz;
import com.ga.erp.biz.member.viplog.VipLogListView;

@SuppressWarnings("serial")
public class MemberDetailPage extends UnitPage {
	
  private FormView memberForm;
  private ListView vipLogList;
  private ListView activeLogList;
  
  @Override
  public void initController() throws Exception {
	  
    this.memberForm = new MemberFormView("member",this.modelMap);   
    this.vipLogList = new VipLogListView("vipLogList", this.modelMap);
    this.activeLogList = new ActiveLogListView("activeLogList",this.modelMap);
    
    PageEvent event = this.regPageLoadEvent("pageLoad"); 
    event.addEventParam(this.memberForm, PageEvent.PARAMTYPE_QUERYVALUE, "MEMBER_ID");
    event.addEventParam(this.vipLogList, PageEvent.PARAMTYPE_PAGEQUERY);
    
    event = this.regListViewLoadEvent(this.vipLogList, "reloadVipLogList");
    event.addEventParam(this.memberForm, PageEvent.PARAMTYPE_QUERYVALUE, "MEMBER_ID");
    event.addEventParam(this.vipLogList, PageEvent.PARAMTYPE_PAGEQUERY);
    
    event = this.regListViewLoadEvent(this.activeLogList, "reloadActiveLogList");
    event.addEventParam(this.memberForm, PageEvent.PARAMTYPE_QUERYVALUE, "MEMBER_ID");
    event.addEventParam(this.activeLogList, PageEvent.PARAMTYPE_PAGEQUERY);
    
    this.memberForm.regEditSaveEvent("saveMember", "saveMember", this, true);
  }

  @Override
  public Layout initLayout() {
	FormLayout formLayout = new FormLayout("",this.memberForm.getDataForm(),2);
	this.memberForm.getDataForm().setLayout(formLayout);
	ViewPageLayout layout = new ViewPageLayout(this);
	layout.addControl("会员基本信息","",this.memberForm);
	if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
    layout.addControl("会员等级变更日志", "", this.vipLogList);
    layout.addControl("会员激活日志", "", this.activeLogList);
  }
    return layout;
  }

  public void pageLoad(Long memberId, QueryPageData pageData) {
	try {   
	  if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
  		  MemberBiz biz = new MemberBiz(this.getUserACL()); 
  		  this.memberForm.bindData(biz.queryMemberDetail(memberId, this.memberForm.getFieldList()));
  		  
  		  VipLogBiz vipBiz = new VipLogBiz(this.getUserACL()); 
        this.vipLogList.bindData(vipBiz.queryVipLogList(new QueryPageData() ,this.vipLogList.getFieldList(),memberId));
        
        ActiveLogBiz actBiz = new ActiveLogBiz(this.getUserACL()); 
        this.activeLogList.bindData(actBiz.queryActLogList(new QueryPageData(), this.activeLogList.getFieldList(),memberId));
        
       }
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"页面加载异常");
    }
  }
  
  /**
   * 保存
   */
  public ActionResult saveMember(Map<String, Object> valueMap) {
	MemberBiz biz = new MemberBiz(this.getUserACL());
    biz.saveMember(valueMap, false);
    return this.createReturnJSON(true, "保存成功", true, false, "memberList", "");
  }
  
  public ActionResult reloadVipLogList(Long memberId, QueryPageData pageData) {
    try {
      VipLogBiz biz = new VipLogBiz(this.getUserACL());
      this.vipLogList.bindData(biz.queryVipLogList(pageData, this.vipLogList.getFieldList(), memberId));
      ClickUtil.setControlLayoutH(this.vipLogList.getViewControl(), 120);
      return this.linkView(this.vipLogList);
    } catch (BizException e) {
      throw e;
    } catch (Exception ex) {
      throw new BizException("加载页面异常");
    }
  }
  
  public ActionResult reloadActiveLogList(Long memberId, QueryPageData pageData) {
    try {
      ActiveLogBiz biz = new ActiveLogBiz(this.getUserACL());
      this.activeLogList.bindData(biz.queryActLogList(pageData, this.activeLogList.getFieldList(), memberId));
      ClickUtil.setControlLayoutH(this.activeLogList.getViewControl(), 120);
      return this.linkView(this.activeLogList);
    } catch (BizException e) {
      throw e;
    } catch (Exception ex) {
      throw new BizException("加载页面异常");
    }
  }
  
  
}
