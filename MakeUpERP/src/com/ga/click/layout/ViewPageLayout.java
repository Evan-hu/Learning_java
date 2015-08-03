package com.ga.click.layout;

import org.apache.click.Control;
import org.apache.click.util.HtmlStringBuffer;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.struct.BaseStruct;
import com.ga.click.layout.struct.DivStruct;
import com.ga.click.layout.struct.PanelStruct;
import com.ga.click.layout.struct.TabPageStruct;
import com.ga.click.layout.struct.TabStruct;
import com.ga.click.mvc.UnitPage;
import com.ga.click.mvc.View;
import com.ga.click.util.ClickUtil;
import com.ga.click.util.GaUtil;

public class ViewPageLayout extends AbstractLayout {
  
  private UnitPage page;
  private Layout layout = null;  
  private TabStruct mainTab;
  

  public ViewPageLayout(UnitPage unitPage) {
    this.mainTab = new TabStruct("main");
    this.mainTab.setCustomPage(false);
    this.page = unitPage;
  }
  

  public void addControl(View control) {
    //����div�ṹ
    DivStruct div = new DivStruct(control.getDivID(),control.getViewControl());
    this.setControlLayoutH(control.getViewControl(), -1);
    //���뵽��tab
    if (this.mainTab.haveCustomPages()) {
      throw new BizException(BizException.COMMBIZ,"���ֽṹ���岻�Ϸ�,����divʱδָ��ѡ���Ϣ");
    } else {
      this.mainTab.getBlankPage().addSubStruct(div);
    }
  }

  public void addControl(String panelName, View control) {
    //����div���
    DivStruct div = new DivStruct(control.getDivID(),control.getViewControl());
    this.setControlLayoutH(control.getViewControl(), -1);
    if (this.mainTab.haveCustomPages()) {
      throw new BizException(BizException.COMMBIZ,"���ֽṹ���岻�Ϸ�,�������ʱδָ��ѡ���Ϣ");
    } else {
      BaseStruct struct = this.mainTab.getBlankPage().getStruct(panelName);
      if (struct == null) {
        //û�д����,����������page
        struct =new PanelStruct(panelName);
        this.mainTab.getBlankPage().addSubStruct(struct);
      }
      //������div
      struct.addSubStruct(div);
    }
  }

  
  public void addControl(String tabName, String panelName,View control) {
    this.mainTab.setCustomPage(true);
    this.setControlLayoutH(control.getViewControl(), -1);
    DivStruct div = new DivStruct(control.getDivID(),control.getViewControl());
    //��ȡ��page
    TabPageStruct pageStruct = this.mainTab.getPageStruct(tabName);
    if (pageStruct == null) {
      pageStruct = new TabPageStruct(tabName);
      this.mainTab.addPageStruct(pageStruct);
    }
    //��ȡ���
    BaseStruct struct = pageStruct.getStruct(panelName);
    if (struct == null) {
      struct = new PanelStruct(panelName);
      pageStruct.addSubStruct(struct);
    }
    //����div
    struct.addSubStruct(div);   
  }
  
  /**
   * ������ؿؼ�����
   * @param tabName 
   * @param panelID
   * @param panelName
   * @param control
   */
  public void addHiddenControl(String tabName,String panelName,View control) {
    this.mainTab.setCustomPage(true);
    this.setControlLayoutH(control.getViewControl(), -1);
    DivStruct div = new DivStruct(control.getDivID(),control.getViewControl());
    //��ȡ��page
    TabPageStruct pageStruct = this.mainTab.getPageStruct(tabName);
    if (pageStruct == null) {
      pageStruct = new TabPageStruct(tabName);
      this.mainTab.addPageStruct(pageStruct);
    }
    //��ȡ���
    BaseStruct struct = pageStruct.getStruct(panelName);
    if (struct == null) {
      struct = new PanelStruct(panelName);
      ((PanelStruct)struct).setHidden(true);
      pageStruct.addSubStruct(struct);
    }
    //����div
    struct.addSubStruct(div); 
  }
  
  
  public void setTabPageHidden(String tabPageName) {
    TabPageStruct pageStruct = this.mainTab.getPageStruct(tabPageName);
    if (pageStruct != null) {
      pageStruct.setHidden(true);
    }
  }
  /**
   * ������������tab�еĿռ�
   * 
   * @param tabName  ��tabҳ����,(������tab,������Ϊ"")
   * @param subTabID  ��tabID
   * @param tabPageName ��tabҳ����
   * @param panelName �������
   * @param divID divID
   * @param control �ؼ�
   */
  public void addControl(String tabName,String subTabID,String tabPageName,String panelName, View control) {
    //this.mainTab.setCustomPage(true);
    this.setControlLayoutH(control.getViewControl(), -1);
    DivStruct div = new DivStruct(control.getDivID(),control.getViewControl());
    //��ȡ��page
    TabPageStruct pageStruct;
    if (!GaUtil.isNullStr(tabName)) {
      this.mainTab.setCustomPage(true);
      //����tab
      pageStruct = this.mainTab.getPageStruct(tabName);
      if (pageStruct == null) {
        pageStruct = new TabPageStruct(tabName);
        this.mainTab.addPageStruct(pageStruct);
      }
    } else {
      pageStruct = this.mainTab.getBlankPage();
    }
    //��ȡ��tab
    BaseStruct struct = pageStruct.getStruct(subTabID);
    if (struct == null) {
      struct = new TabStruct(subTabID);
      pageStruct.addSubStruct(struct);
    }
    TabStruct subTab = null;
    if (struct instanceof TabStruct) {
      subTab = (TabStruct) struct;
      subTab.setMainTab(false);
    } else {
      throw new BizException(BizException.COMMBIZ,"���ֽṹ���岻�Ϸ�,��tab id�Ѿ���ռ��");
    }
    //��ȡ��tab page
    TabPageStruct subPageStruc = subTab.getPageStruct(tabPageName);
    if (subPageStruc == null) {
      subPageStruc = new TabPageStruct(tabPageName);
      subTab.addPageStruct(subPageStruc);
    }
    //��ȡ���
    BaseStruct panelStruct = subPageStruc.getStruct(panelName);
    if (panelStruct == null) {
      panelStruct = new PanelStruct(panelName);
      subPageStruc.addSubStruct(panelStruct);
    }
    //����div
    panelStruct.addSubStruct(div);   
  }
  
  /**
   * ���������
   * @param tabName
   * @param panelName
   */
  public void setLeftPanel(String tabName,String subStructName) {
    if (GaUtil.isNullStr(tabName)) {
      //��ָ����tab
      if (this.mainTab.haveCustomPages()) {
        throw new BizException(BizException.COMMBIZ,"δָ����ѡ�");
      } else {
        this.mainTab.getBlankPage().setLeftPanel(subStructName);
      }
    } else {
      TabPageStruct page = this.mainTab.getPageStruct(tabName);
      if (page == null) {
        throw new BizException(BizException.COMMBIZ,"��Ч����ѡ�����");
      } else {
        page.setLeftPanel(subStructName);
      }
    }
  }
  
  
  public void setLeftWidth(int leftWidth) {
    this.setLeftWidth("", leftWidth);
  }
  /**
   * ������������ڿ�ȱ���
   * @param leftWidth
   */
  public void setLeftWidth(String tabName,int leftWidth) {
    if (GaUtil.isNullStr(tabName)) {
      //��ָ����tab
      if (this.mainTab.haveCustomPages()) {
        throw new BizException(BizException.COMMBIZ,"δָ����ѡ�");
      } else {
        this.mainTab.getBlankPage().setLeftWidth(leftWidth);
      }
    } else {
      TabPageStruct page = this.mainTab.getPageStruct(tabName);
      if (page == null) {
        throw new BizException(BizException.COMMBIZ,"��Ч����ѡ�����");
      } else {
        page.setLeftWidth(leftWidth);
      }
    }
  }
  
  /**
   * ������tab�е������
   * @param tabName
   * @param panelName
   */
  public void setLeftPanel(String tabName,String subTabID,String subTabPage,String subStructName) {
    this.getSubPage(tabName, subTabID, subTabPage).setLeftPanel(subStructName);
  }

  /**
   * ������tab�е��������
   * @param tabName
   * @param subTabID
   * @param subTabPage
   * @param leftWidth
   */
  public void setLeftWidth(String tabName,String subTabID,String subTabPage,int leftWidth) {
    this.getSubPage(tabName, subTabID, subTabPage).setLeftWidth(leftWidth);
  }
  
  private TabPageStruct getSubPage(String tabName,String subTabID,String subTabPage) {
    TabPageStruct mainPage;
    if (GaUtil.isNullStr(tabName)) {
      //��ָ����tab
      if (this.mainTab.haveCustomPages()) {
        throw new BizException(BizException.COMMBIZ,"δָ����ѡ�");
      } else {
        mainPage= this.mainTab.getBlankPage();
      }
    } else {
      mainPage = this.mainTab.getPageStruct(tabName);     
    }
    if (mainPage == null) {
      throw new BizException(BizException.COMMBIZ,"��Ч����ѡ�����");
    }     
    BaseStruct struct = mainPage.getStruct(subTabID);
    if (struct == null || !(struct instanceof TabStruct)) {
      throw new BizException(BizException.COMMBIZ,"��Ч����ѡ�ID");
    }
    TabStruct subTab = (TabStruct)struct;
    TabPageStruct subPage = subTab.getPageStruct(subTabPage);
    if (subPage == null) {
      throw new BizException(BizException.COMMBIZ,"��Ч����ѡ�ҳ");
    }
    return subPage;
  }
  // ִ����Ⱦ
  public void renderMain(HtmlStringBuffer buff) {    
    this.mainTab.render(buff);
  }

  public void renderButton(HtmlStringBuffer buff) {
    if (this.page.getPageActionList().size() > 0) {
        buff.append("<div class=\"formBar\" id=\"pageButtonZone\">\r\n");
        buff.append("<table cellspacing='0' cellpadding='0' border='0' align='center' style='margin: 0 auto;'>");
        buff.append("<tbody><tr>");
        for (ActionButton button : this.page.getPageActionList()) {
          buff.append("<td>"); 
          if (button.isHidden()) {
            buff.append("<b class=\"submitBtn\" style=\"display:none\">");
          } else {
            buff.append("<b class=\"submitBtn\">");
          }
          button.render(buff);
          buff.append("</b>&nbsp;&nbsp;");
          //buff.append("<div class=\"button\"><div class=\"buttonContent\"><button type=\"button\" >ȡ��</button></div></div>");
          buff.append("</td>\r\n");
        }
        buff.append("</tr></tbody>\r\n");
        buff.append("</table>\r\n");
        buff.append("</div>\r\n");
      }
  }
  
  public void renderButtonNoDiv(HtmlStringBuffer buff) {
    if (this.page.getPageActionList().size() > 0) {
        buff.append("<table cellspacing='0' cellpadding='0' border='0' align='center' style='margin: 0 auto;'>");
        buff.append("<tbody><tr>");
        for (ActionButton button : this.page.getPageActionList()) {
          buff.append("<td>"); 
          if (button.isHidden()) {
            buff.append("<b class=\"submitBtn\" style=\"display:none\">");
          } else {
            buff.append("<b class=\"submitBtn\">");
          }
          button.render(buff);
          buff.append("</b>&nbsp;&nbsp;");
          //buff.append("<div class=\"button\"><div class=\"buttonContent\"><button type=\"button\" >ȡ��</button></div></div>");
          buff.append("</td>\r\n");
        }
        buff.append("</tr></tbody>\r\n");
        buff.append("</table>\r\n");
      }
  }
  
  
  @Override
  public void render(HtmlStringBuffer buff) {
    // TODO Auto-generated method stub
    if (!this.mainTab.haveCustomPages()) {
      buff.append(" <div class=\"pageContent\" layoutH=\"").append(this.countLayout()).append("\">\r\n"); // ��Ҫ�Զ�����
    } else {
     this.mainTab.setLayoutH(this.countLayout());
    }    
    //buff.append(" <div>\r\n"); // ��Ҫ�Զ�����
    this.renderMain(buff);
    if (!this.mainTab.haveCustomPages()) {
      buff.append(" </div>");
    }
    this.renderButton(buff);
   // System.out.println(buff);
  }
  


  /**
   * ���ÿؼ������߶�
   * -1:�����ֹ�����
   * 0:�Զ�����߶�(��Listҳ��ͨ�����Զ�)
   * ����:�ֶ����ø߶�
   * @param control
   * @param autoH
   */
  public void setControlLayoutH(Control control, int autoH) {
    ClickUtil.setControlLayoutH(control, autoH);
  }

  private int countLayout() {
    if (this.page.getPageActionList() != null && this.page.getPageActionList().size() > 0) {
      return this.layoutH + 35;
    } else {
      return this.layoutH;
    }
  }

  public Layout getLayout() {
    return this.layout;
  }
}
