package com.ga.erp.biz.system.syscode;

import java.util.Map;

import org.apache.click.ActionResult;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;

public class SyscodeDetailPage extends UnitPage {
	
  private static final long serialVersionUID = 1L;
  private SyscodeFormView syscodeFormView;
  
  @Override
  public void initController() throws Exception {

	this.syscodeFormView = new SyscodeFormView("syscodeForm",this.modelMap);    
    //load�¼�����ע��
    PageEvent loadEvent = this.regPageLoadEvent("pageLoad");   
    loadEvent.addEventParam(this.syscodeFormView,PageEvent.PARAMTYPE_QUERYMAP);
    //ע���½������¼�
    ActionButton action  = new ActionButton(this.getClass(),"saveNewSyscode","����",this.getSelfUrl(),null);
    action.bindForm(this.syscodeFormView.getViewID(),true);
    SubmitTool.submitMvc(action,this.modelMap.getPreNavInfoStr());
    PageEvent event = this.regPageEvent(action, "saveNewSyscode");
    event.addEventParam(this.syscodeFormView,PageEvent.PARAMTYPE_DATAMAP);
    this.syscodeFormView.regAction(action);
  }

  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.syscodeFormView);
    return layout;
  }

  public void pageLoad(Map<String,Object> paramMap)  {
    this.syscodeFormView.bindData(paramMap);
  }
  
  /**
   * ִ���½�����
   * @param valueMap
   * @return
   */
  public ActionResult saveNewSyscode(Map<String,Object> valueMap) {
    try {
      SysCodeBiz biz = new SysCodeBiz(this.getUserACL());
      biz.saveNewSyscode(valueMap);
      return this.createReturnJSON(true, "�½�����ɹ�,���¼��ر�ҳ��",true,true,"","");
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"���������ϸ�쳣");
    }
  }
}
