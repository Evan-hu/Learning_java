package com.ga.erp.biz.activity.card;

import java.util.Map;
import org.apache.click.ActionResult;
import com.ga.click.control.GaConstant;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;

public class CardDetailPage extends UnitPage {
  
  private static final long serialVersionUID = 1L;
  private FormView editFormView;

  @Override
  public void initController() throws Exception {
    
    this.editFormView = new CardFormView("editForm", this.modelMap);
    
    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.editFormView, PageEvent.PARAMTYPE_QUERYVALUE, "CARD_ID");
    
    this.editFormView.regEditSaveEvent("updateCardBtn", "updateCardInfo", this, false);
  }

  @Override
  public Layout initLayout() {
    ViewPageLayout layout=new ViewPageLayout(this);
    FormLayout formLayout=new FormLayout("", this.editFormView.getDataForm(), 2);
    formLayout.mergeCellField("auto", 0, 0, 1, "CARD_NUM");
    formLayout.mergeCellField("auto", 1, 0, 1, "MOBILE");
    formLayout.mergeCellField("auto", 3, 0, 1, "EXPLAIN");
    formLayout.mergeCellField("auto", 4, 0, 1, "NOTE");
    this.editFormView.getDataForm().setLayout(formLayout);
    layout.addControl("电子券信息",this.editFormView);
    return layout;
  }
  
  public void pageLoad(Long CardID){
    try {
      CardBiz cardBiz=new CardBiz(this.getUserACL());
      Map<String, Object> result = cardBiz.queryCardInfo(CardID);
      if((result.get("STATE") + "").equals("2")){
        this.editFormView.getFieldByCode("NEWSTATE").setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
      }
      this.editFormView.bindData(result);
    }catch (BizException e) {
      throw e;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"查询信息失败");
    }
  }
  
  public ActionResult updateCardInfo(Map<String, Object> vMap){
    try {
      CardBiz cardBiz=new CardBiz(this.getUserACL());
      if(null==vMap.get("NEWSTATE")){
        throw new BizException("<br>请选择状态；");
      }
      cardBiz.updateCardState(vMap,this.editFormView.getFuncMap());
      return this.createReturnJSON(true, "修改信息成功", true, false, "cardList", "");
    }catch (BizException e) {
      throw e;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"更新信息失败");
    }
  }

}
