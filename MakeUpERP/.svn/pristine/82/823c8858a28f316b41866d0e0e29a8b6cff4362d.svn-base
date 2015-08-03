package com.ga.click.layout;

import org.apache.click.Control;
import org.apache.click.util.HtmlStringBuffer;
import com.ga.click.control.button.ActionButton;
import com.ga.click.control.form.DataForm;
import com.ga.click.control.table.QueryTable;
import com.ga.click.exception.BizException;
import com.ga.click.layout.struct.BaseStruct;
import com.ga.click.layout.struct.DivStruct;
import com.ga.click.layout.struct.PanelStruct;
import com.ga.click.layout.struct.TabPageStruct;
import com.ga.click.layout.struct.TabStruct;
import com.ga.click.page.MultiDivPage;
import com.ga.click.util.GaUtil;

public class DivPageLayout extends AbstractLayout {
  
  private MultiDivPage page;
  private Layout layout = null;  
  private TabStruct mainTab;
  

  public DivPageLayout(MultiDivPage divPage) {
    this.mainTab = new TabStruct("main");
    this.mainTab.setCustomPage(false);
    this.page = divPage;
  }
  

  public void addControl(String divID, Control control) {
    //创建div结构
    DivStruct div = new DivStruct(divID,control);
    this.setControlLayoutH(control, false);
    //加入到主tab
    if (this.mainTab.haveCustomPages()) {
      throw new BizException(BizException.COMMBIZ,"布局结构定义不合法,创建div时未指定选项卡信息");
    } else {
      this.mainTab.getBlankPage().addSubStruct(div);
    }
  }

  public void addControl(String panelName, String divID, Control control) {
    //创建div面板
    DivStruct div = new DivStruct(divID,control);
    this.setControlLayoutH(control, false);
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
  
  public void addControl(String tabName, String panelName, String divID,
      Control control) {
    this.mainTab.setCustomPage(true);
    this.setControlLayoutH(control, false);
    DivStruct div = new DivStruct(divID,control);
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
   * 创建包含在子tab中的空间
   * 
   * @param tabName  主tab页名称
   * @param subTabID  子tabID
   * @param tabPageName 子tab页名称
   * @param panelName 面板名称
   * @param divID divID
   * @param control 控件
   */
  public void addControl(String tabName,String subTabID,String tabPageName,String panelName, String divID,
      Control control) {
    this.mainTab.setCustomPage(true);
    this.setControlLayoutH(control, false);
    DivStruct div = new DivStruct(divID,control);
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
      buff.append("<div class=\"formBar\">\r\n");
      buff.append("<table cellspacing='0' cellpadding='0' border='0' align='center' style='margin: 0 auto;'>");
      buff.append("<tbody><tr>");
      for (ActionButton button : this.page.getPageActionList()) {
        buff.append("<td>"); 
        buff.append("<b class=\"submitBtn\">");
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
  
  
  @Override
  public void render(HtmlStringBuffer buff) {
    // TODO Auto-generated method stub
    if (!this.mainTab.haveCustomPages()) {
      buff.append(" <div class=\"pageContent\"  layoutH=\"").append(this.countLayout()).append("\">\r\n"); // 需要自动布局
    } else {
     this.mainTab.setLayoutH(this.countLayout());
    }    
//    buff.append(" <div class=\"pageContent\">\r\n"); // 需要自动布局
    this.renderMain(buff);
    if (!this.mainTab.haveCustomPages()) {
      buff.append(" </div>");
    }
    this.renderButton(buff);
   // System.out.println(buff);
  }


  public void setControlLayoutH(Control control, boolean needScrool) {
    if (control instanceof QueryTable) {
      QueryTable table = (QueryTable) control;
      if (!needScrool) {
        //取消滚动条
        Layout layout = table.getLayout();
        if (layout == null) {
          layout = new ListLayout(table);
          table.setLayout(layout);
        }
        layout.setLayoutH(-1);
      }
    } else if (control instanceof DataForm) {
      DataForm form = (DataForm) control;
      if (!needScrool) {
        //取消滚动条
        Layout layout = form.getLayout();
        if (layout == null) {
          layout = new FormLayout("", form, 1);
          form.setLayout(layout);
        }
        layout.setLayoutH(-1);
      }
    }
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
