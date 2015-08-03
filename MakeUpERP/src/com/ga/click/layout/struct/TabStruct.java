package com.ga.click.layout.struct;

import java.util.ArrayList;
import java.util.List;
import org.apache.click.util.HtmlStringBuffer;
import com.ga.click.exception.BizException;

public class TabStruct extends BaseStruct{
  private String  tabID ;
  List<TabPageStruct> pageList = new ArrayList<TabPageStruct>();
  private boolean customPage = true;
  private TabPageStruct blankPage = new TabPageStruct("blank");
  private boolean isMainTab = true;
  
  public TabStruct(String tabID) {
    this.tabID = tabID;
  }
  
  public void addPageStruct(TabPageStruct pageStruct) {
    this.pageList.add(pageStruct);
  }
  
  public TabPageStruct getPageStruct(String pageName) {
    for(TabPageStruct struct: this.pageList) {
      if (struct.getName().equals(pageName)) {
        return struct;
      }
    }
    return null;
  }
  
  
  public void setMainTab(boolean isMainTab) {
    this.isMainTab = isMainTab;
  }

  //设置为不渲染
  public void setCustomPage(boolean isCustom) {
    customPage = isCustom;
  }
  
  public boolean haveCustomPages() {
    return customPage;
  }
  
  public TabPageStruct getBlankPage() {
    if (this.customPage) {
     throw new BizException(BizException.COMMBIZ,"已定义主选项卡时,不能使用空白选项卡");
    } else {
      return this.blankPage;
    }    
  }
  
  
  
  @Override
  public int setLayoutH(int layoutH) {
    // TODO Auto-generated method stub
    if (this.customPage) {
      //需tab头部
      this.layoutH = 38 + layoutH;
    } else {
      //不需tab头部
      this.layoutH = layoutH;
    }
    return this.layoutH;
  }

  public void render(HtmlStringBuffer buff)  {  
    //渲染tab头
    if (this.customPage) {
     buff.append("<div class=\"tabs\"  style=\"margin-top: 5px;\">\r\n");
      buff.append(" <div class=\"tabsHeader\">\r\n");
      buff.append("  <div class=\"tabsHeaderContent\">\r\n");
      buff.append("   <ul>\r\n");
      String isSelect = "class=\"selected\"";
      for (TabPageStruct page : pageList) {
        if (page.isHidden()) {
          buff.append("     <li id=\"").append(page.getName()).append("Head\" style=\"display:none\" ").append(isSelect).append(
          "><a href=\"#\"><span>").append(page.getName()).append(
          "</span></a></li>\r\n");
        } else {
          buff.append("     <li id=\"").append(page.getName()).append("Head\"").append(isSelect).append(
          "><a href=\"#\"><span>").append(page.getName()).append(
          "</span></a></li>\r\n");
        }

        isSelect = "";
      }
      buff.append("   </ul>\r\n");
      buff.append("  </div>\r\n");
      buff.append(" </div>\r\n");
      // 内容
      if (isMainTab) {
        buff.append(" <div class=\"tabsContent\"  style=\"border-width: 0 1px 1px 1px;\">\r\n");  
      } else {
        buff.append(" <div class=\"tabsContent\"  style=\"border-width: 0 1px 1px 1px;\">\r\n");
      }      
      for (TabPageStruct page : pageList) {
        buff.append("  <div>\r\n");
        if (isMainTab) {
            buff.append("   <div  layoutH=\"").append(this.layoutH).append("\">\r\n"); // 需要自动布局  
        } else {
            buff.append("   <div  >\r\n"); // 需要自动布局          
        }
        
        page.render(buff);
        buff.append("    </div>\r\n");
        buff.append("  </div>\r\n");
      }
      buff.append(" </div>\r\n");
      buff.append("</div>\r\n");
    } else {
      //空白页
      this.blankPage.render(buff);
    }
  }
  
  @Override
  public void addSubStruct(BaseStruct struct) {
    // TODO Auto-generated method stub
    throw new BizException(BizException.SYSTEM,"tab不能直接添加子结构");
  } 
  
  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return this.tabID;
  } 
  
}
