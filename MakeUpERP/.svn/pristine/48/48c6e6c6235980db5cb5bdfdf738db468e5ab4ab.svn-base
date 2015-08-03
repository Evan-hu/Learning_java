package com.ga.erp.biz.activity.card;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class CardMainPage extends UnitPage {
  
  private static final long serialVersionUID = 1L;
  private CardListView cardListView;

  @Override
  public void initController() throws Exception {
    
    this.cardListView = new CardListView("cardList", this.modelMap);
    
    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.cardListView, PageEvent.PARAMTYPE_PAGEQUERY);
    
    ActionButton action = this.cardListView.regEditAction("edtiCardBtn", "±‡º≠", "/activity/card_detail.htm");
    SubmitTool.submitToDialog(action, "editCardDlg", "±‡º≠", 600, 350, this.modelMap.getPreNavInfoStr());
  }

  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.cardListView);
    return layout;
  }
  
  public void pageLoad(QueryPageData pageData){
    try {
      CardBiz cardBiz = new CardBiz(this.getUserACL());
      this.cardListView.bindData(cardBiz.queryCardLists(pageData, this.cardListView.getFieldList()));
    }catch (BizException e) {
      throw e;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"≤È—Ø–≈œ¢ ß∞‹");
    }
  }
  
  

}
