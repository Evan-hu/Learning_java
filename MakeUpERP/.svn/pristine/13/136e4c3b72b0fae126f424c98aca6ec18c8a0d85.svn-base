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
    //创建div结构
    DivStruct div = new DivStruct(control.getDivID(),control.getViewControl());
    this.setControlLayoutH(control.getViewControl(), -1);
    //加入到主tab
    if (this.mainTab.haveCustomPages()) {
      throw new BizException(BizException.COMMBIZ,"布局结构定义不合法,创建div时未指定选项卡信息");
    } else {
      this.mainTab.getBlankPage().addSubStruct(div);
    }
  }

  public void addControl(String panelName, View control) {
    //创建div面板
    DivStruct div = new DivStruct(control.getDivID(),control.getViewControl());
    this.setControlLayoutH(control.getViewControl(), -1);
    if (this.mainTab.haveCustomPages()) {
      throw new BizException(BizException.COMMBIZ,"布局结构定义不合法,创建面板时未指定选项卡信息");
    } else {
      BaseStruct struct = this.mainTab.getBlankPage().getStruct(panelName);
      if (struct == null) {
        //没有此面板,将此面板加入page
        struct =new PanelStruct(panelName);
        this.mainTab.getBlankPage().addSubStruct(struct);
      }
      //面板加入div
      struct.addSubStruct(div);
    }
  }

  
  public void addControl(String tabName, String panelName,View control) {
    this.mainTab.setCustomPage(true);
    this.setControlLayoutH(control.getViewControl(), -1);
    DivStruct div = new DivStruct(control.getDivID(),control.getViewControl());
    //获取主page
    TabPageStruct pageStruct = this.mainTab.getPageStruct(tabName);
    if (pageStruct == null) {
      pageStruct = new TabPageStruct(tabName);
      this.mainTab.addPageStruct(pageStruct);
    }
    //获取面板
    BaseStruct struct = pageStruct.getStruct(panelName);
    if (struct == null) {
      struct = new PanelStruct(panelName);
      pageStruct.addSubStruct(struct);
    }
    //加入div
    struct.addSubStruct(div);   
  }
  
  /**
   * 添加隐藏控件区域
   * @param tabName 
   * @param panelID
   * @param panelName
   * @param control
   */
  public void addHiddenControl(String tabName,String panelName,View control) {
    this.mainTab.setCustomPage(true);
    this.setControlLayoutH(control.getViewControl(), -1);
    DivStruct div = new DivStruct(control.getDivID(),control.getViewControl());
    //获取主page
    TabPageStruct pageStruct = this.mainTab.getPageStruct(tabName);
    if (pageStruct == null) {
      pageStruct = new TabPageStruct(tabName);
      this.mainTab.addPageStruct(pageStruct);
    }
    //获取面板
    BaseStruct struct = pageStruct.getStruct(panelName);
    if (struct == null) {
      struct = new PanelStruct(panelName);
      ((PanelStruct)struct).setHidden(true);
      pageStruct.addSubStruct(struct);
    }
    //加入div
    struct.addSubStruct(div); 
  }
  
  
  public void setTabPageHidden(String tabPageName) {
    TabPageStruct pageStruct = this.mainTab.getPageStruct(tabPageName);
    if (pageStruct != null) {
      pageStruct.setHidden(true);
    }
  }
  /**
   * 创建包含在子tab中的空间
   * 
   * @param tabName  主tab页名称,(如无主tab,则设置为"")
   * @param subTabID  子tabID
   * @param tabPageName 子tab页名称
   * @param panelName 面板名称
   * @param divID divID
   * @param control 控件
   */
  public void addControl(String tabName,String subTabID,String tabPageName,String panelName, View control) {
    //this.mainTab.setCustomPage(true);
    this.setControlLayoutH(control.getViewControl(), -1);
    DivStruct div = new DivStruct(control.getDivID(),control.getViewControl());
    //获取主page
    TabPageStruct pageStruct;
    if (!GaUtil.isNullStr(tabName)) {
      this.mainTab.setCustomPage(true);
      //有主tab
      pageStruct = this.mainTab.getPageStruct(tabName);
      if (pageStruct == null) {
        pageStruct = new TabPageStruct(tabName);
        this.mainTab.addPageStruct(pageStruct);
      }
    } else {
      pageStruct = this.mainTab.getBlankPage();
    }
    //获取子tab
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
      throw new BizException(BizException.COMMBIZ,"布局结构定义不合法,子tab id已经被占用");
    }
    //获取子tab page
    TabPageStruct subPageStruc = subTab.getPageStruct(tabPageName);
    if (subPageStruc == null) {
      subPageStruc = new TabPageStruct(tabPageName);
      subTab.addPageStruct(subPageStruc);
    }
    //获取面板
    BaseStruct panelStruct = subPageStruc.getStruct(panelName);
    if (panelStruct == null) {
      panelStruct = new PanelStruct(panelName);
      subPageStruc.addSubStruct(panelStruct);
    }
    //加入div
    panelStruct.addSubStruct(div);   
  }
  
  /**
   * 设置左面板
   * @param tabName
   * @param panelName
   */
  public void setLeftPanel(String tabName,String subStructName) {
    if (GaUtil.isNullStr(tabName)) {
      //不指定主tab
      if (this.mainTab.haveCustomPages()) {
        throw new BizException(BizException.COMMBIZ,"未指明主选项卡");
      } else {
        this.mainTab.getBlankPage().setLeftPanel(subStructName);
      }
    } else {
      TabPageStruct page = this.mainTab.getPageStruct(tabName);
      if (page == null) {
        throw new BizException(BizException.COMMBIZ,"无效的主选项卡名称");
      } else {
        page.setLeftPanel(subStructName);
      }
    }
  }
  
  
  public void setLeftWidth(int leftWidth) {
    this.setLeftWidth("", leftWidth);
  }
  /**
   * 设置左面板所在宽度比例
   * @param leftWidth
   */
  public void setLeftWidth(String tabName,int leftWidth) {
    if (GaUtil.isNullStr(tabName)) {
      //不指定主tab
      if (this.mainTab.haveCustomPages()) {
        throw new BizException(BizException.COMMBIZ,"未指明主选项卡");
      } else {
        this.mainTab.getBlankPage().setLeftWidth(leftWidth);
      }
    } else {
      TabPageStruct page = this.mainTab.getPageStruct(tabName);
      if (page == null) {
        throw new BizException(BizException.COMMBIZ,"无效的主选项卡名称");
      } else {
        page.setLeftWidth(leftWidth);
      }
    }
  }
  
  /**
   * 设置子tab中的左面板
   * @param tabName
   * @param panelName
   */
  public void setLeftPanel(String tabName,String subTabID,String subTabPage,String subStructName) {
    this.getSubPage(tabName, subTabID, subTabPage).setLeftPanel(subStructName);
  }

  /**
   * 设置子tab中的左面板宽度
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
      //不指定主tab
      if (this.mainTab.haveCustomPages()) {
        throw new BizException(BizException.COMMBIZ,"未指明主选项卡");
      } else {
        mainPage= this.mainTab.getBlankPage();
      }
    } else {
      mainPage = this.mainTab.getPageStruct(tabName);     
    }
    if (mainPage == null) {
      throw new BizException(BizException.COMMBIZ,"无效的主选项卡名称");
    }     
    BaseStruct struct = mainPage.getStruct(subTabID);
    if (struct == null || !(struct instanceof TabStruct)) {
      throw new BizException(BizException.COMMBIZ,"无效的子选项卡ID");
    }
    TabStruct subTab = (TabStruct)struct;
    TabPageStruct subPage = subTab.getPageStruct(subTabPage);
    if (subPage == null) {
      throw new BizException(BizException.COMMBIZ,"无效的子选项卡页");
    }
    return subPage;
  }
  // 执行渲染
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
          //buff.append("<div class=\"button\"><div class=\"buttonContent\"><button type=\"button\" >取消</button></div></div>");
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
          //buff.append("<div class=\"button\"><div class=\"buttonContent\"><button type=\"button\" >取消</button></div></div>");
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
      buff.append(" <div class=\"pageContent\" layoutH=\"").append(this.countLayout()).append("\">\r\n"); // 需要自动布局
    } else {
     this.mainTab.setLayoutH(this.countLayout());
    }    
    //buff.append(" <div>\r\n"); // 需要自动布局
    this.renderMain(buff);
    if (!this.mainTab.haveCustomPages()) {
      buff.append(" </div>");
    }
    this.renderButton(buff);
   // System.out.println(buff);
  }
  


  /**
   * 设置控件容器高度
   * -1:不出现滚动条
   * 0:自动计算高度(单List页面通常用自动)
   * 其他:手动设置高度
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
