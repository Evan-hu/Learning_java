package com.ga.erp.biz.member.pwd;

import java.util.Map;
import org.apache.click.ActionResult;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.erp.biz.member.MemberBiz;

@SuppressWarnings("serial")
public class MemberPwdDetailPage extends UnitPage {
	
  private FormView formView;
  
  @Override
  public void initController() throws Exception {
    formView = new MemberPwdFromView("memberPwd",this.modelMap);
    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE,"MEMBER_ID");

    this.formView.regEditSaveEvent("savePwd", "updatePassword",this, true);
  }
  
  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(formView);
    return layout;
  }
  
  public void pageLoad(Long id) {
    try {
        MemberBiz biz = new MemberBiz(this.getUserACL());
        this.formView.bindData(biz.queryMemberDetail(id, this.formView.getFieldList()));     
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"查询详细异常");
    }
  }
  
  /**
   * 修改
   */
  public ActionResult updatePassword(Map<String,Object> valueMap){
    if(valueMap.get("PASSWORD") == null || String.valueOf(valueMap.get("PASSWORD")).
    		trim().equals("d41d8cd98f00b204e9800998ecf8427e")){
      throw new BizException(BizException.SYSTEM,"会员密码为空！");
    }
    if(!String.valueOf(valueMap.get("PASSWORD")).equals(String.valueOf(valueMap.get("RE_PASSWORD")))){
      throw new BizException(BizException.SYSTEM,"两次密码不一致！");
    }
    try {
      MemberBiz biz = new MemberBiz(this.getUserACL());
      biz.updateMemberPwd(valueMap);
      return this.createReturnJSON(true, "修改密码成功!",true,false,"","");
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"修改密码异常！");
    }
  }
  
}
