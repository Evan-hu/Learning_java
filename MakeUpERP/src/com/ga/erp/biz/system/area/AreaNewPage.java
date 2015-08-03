package com.ga.erp.biz.system.area;

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
public class AreaNewPage extends UnitPage {
  private AreaFormView areaFormView;
  
  @Override
  public void initController() throws Exception {
    // TODO Auto-generated method stub
    this.areaFormView = new AreaFormView("areaFormView",this.modelMap);    
    //load事件必须注册
    PageEvent loadEvent = this.regPageLoadEvent("pageLoad");   
    loadEvent.addEventParam(this.areaFormView,PageEvent.PARAMTYPE_QUERYMAP);
    //注册新建保存事件
    ActionButton action  = new ActionButton(this.getClass(),"saveNewArea","保存",this.getSelfUrl(),null);
    action.bindForm(this.areaFormView.getViewID(),true);
    SubmitTool.submitMvc(action,this.modelMap.getPreNavInfoStr());
    PageEvent event = this.regPageEvent(action, "saveNewArea");
    event.addEventParam(this.areaFormView,PageEvent.PARAMTYPE_DATAMAP);
    this.areaFormView.regAction(action);
  }

  @Override
  public Layout initLayout() {
    // TODO Auto-generated method stub
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.areaFormView);
    return layout;
  }

  public void pageLoad(Map<String,Object> paramMap)  {
    this.areaFormView.bindData(paramMap);
  }
  
  /**
   * 执行新建保存
   * @param valueMap
   * @return
   */
  public ActionResult saveNewArea(Map<String,Object> valueMap) {
    try {
      AreaBiz biz = new AreaBiz(this.getUserACL());
      biz.saveNewArea(valueMap, this.areaFormView.getFuncMap());
      return this.createReturnJSON(true, "新建地区成功,重新加载本页面！",true,true,"","");
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"新建地区异常！");
    }
  }
}
