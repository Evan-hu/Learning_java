package com.ga.erp.biz.system;

import java.util.Map;
import org.apache.click.ActionResult;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;

@SuppressWarnings("serial")
public class OpPasswordDetailPage extends UnitPage {
	
  private  OpPasswordFromView formView;
  
  @Override
  public void initController() throws Exception {
    formView = new OpPasswordFromView("opPassFrom",this.modelMap);
    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE,"OP_ID");
    this.formView.regEditSaveEvent("savePassword", "updatePassword",this, true);
  }
  /**
   * 页面加载
   * @param id
   */
  public void pageLoad(Long id) {
    try {
        OpBiz biz = new OpBiz(this.getUserACL());
        this.formView.bindData(biz.queryOpPasswordDetail(id, this.formView.getFieldList()));     
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"查询详细异常");
    }
  }
  
  /**
   * 修改
   */
  public ActionResult updatePassword(Map<String,Object> valueMap){
    if(valueMap.get("PASSWORD") == null || String.valueOf(valueMap.get("PASSWORD")).trim().equals("d41d8cd98f00b204e9800998ecf8427e")){
      throw new BizException(BizException.SYSTEM,"用户密码为空！");
    }
    if(!String.valueOf(valueMap.get("PASSWORD")).equals(String.valueOf(valueMap.get("RE_PASSWORD")))){
      throw new BizException(BizException.SYSTEM,"两次密码不一致！");
    }
    try {
      OpBiz biz = new OpBiz(this.getUserACL());
      biz.updateOpPassword(valueMap);
    return this.createReturnJSON(true, "修改密码成功,重新加载本页面!",true,false,"","");
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"修改密码异常！");
    }
  }
  
  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(formView);
    return layout;
  }
}
