package com.ga.erp.biz.system.purview;

import java.util.Map;
import org.apache.click.ActionResult;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;

@SuppressWarnings("serial")
public class PurviewNewPage extends UnitPage {
  private PurviewFormView purviewFormView;
  
  @Override
  public void initController() throws Exception {
    // TODO Auto-generated method stub
    this.purviewFormView = new PurviewFormView("purviewForm",this.modelMap);    
    //load�¼�����ע��
    PageEvent loadEvent = this.regPageLoadEvent("pageLoad");   
    loadEvent.addEventParam(this.purviewFormView,PageEvent.PARAMTYPE_QUERYMAP);
    //ע���½������¼�
    ActionButton action  = new ActionButton(this.getClass(),"saveNewPurview","����",this.getSelfUrl(),null);
    action.bindForm(this.purviewFormView.getViewID(),true);
    SubmitTool.submitMvc(action,this.modelMap.getPreNavInfoStr());
    PageEvent event = this.regPageEvent(action, "saveNewPurview");
    event.addEventParam(this.purviewFormView,PageEvent.PARAMTYPE_DATAMAP);
    this.purviewFormView.regAction(action);
  }

  @Override
  public Layout initLayout() {
    // TODO Auto-generated method stub
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.purviewFormView);
    return layout;
  }

  public void pageLoad(Map<String,Object> paramMap)  {
    this.purviewFormView.bindData(paramMap);
  }
  
  /**
   * ִ���½�����
   * @param valueMap
   * @return
   */
  public ActionResult saveNewPurview(Map<String,Object> valueMap) {
    try {
      PurviewBiz biz = new PurviewBiz(this.getUserACL());
      biz.saveNewPurview(valueMap, this.purviewFormView.getFuncMap());
      return this.createReturnJSON(true, "�½�Ȩ�޳ɹ�,���¼��ر�ҳ�棡",true,true,"","");
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"�½�Ȩ���쳣��");
    }
  }
}
