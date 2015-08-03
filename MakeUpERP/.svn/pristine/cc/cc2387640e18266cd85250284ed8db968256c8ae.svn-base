package com.ga.erp.biz.system.opstore;

import java.util.Map;
import org.apache.click.ActionResult;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.erp.biz.system.OpBiz;

public class OpStoreDetailPage extends UnitPage {
  
  private static final long serialVersionUID = 1L;
  private FormView opStoreForm;
  
  @Override
  public void initController() throws Exception {
    
    opStoreForm = new OpStoreFormView("opStoreForm",this.modelMap);

    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.opStoreForm, PageEvent.PARAMTYPE_QUERYVALUE,"OP_ID");
    
    this.opStoreForm.regAddSaveEvent("add","addStoreOp",this, true);
    
  }
  
  @Override
  public Layout initLayout() {
    
    ViewPageLayout layout = new ViewPageLayout(this);
    FormLayout formLayout = new FormLayout("",this.opStoreForm.getDataForm(),2);
    this.opStoreForm.getDataForm().setLayout(formLayout);
    layout.addControl(opStoreForm);
    return layout;
  }
  
  public void pageLoad(Long opId) {
    try {
      this.opStoreForm.bindData(new OpBiz(this.getUserACL()).queryOpDetail(opId, this.opStoreForm.getFieldList(), "2"));
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"查询详细异常");
    }
  }
  
  public ActionResult addStoreOp(Map<String,Object> valueMap){
    try {
      OpBiz biz = new OpBiz(this.getUserACL());
      biz.addStoreOp(valueMap.get("OP_ID") + "", valueMap.get("STORE_IDS") + "");
      return this.createReturnJSON(true, "新建操作员成功,重新加载本页面!", true, false, "opStoreList","");
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"新建操作员异常");
    }
  }
  
}
