package com.ga.click.layout.struct;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.click.util.HtmlStringBuffer;

public class TabPageStruct extends BaseStruct {
  private String pageName = "";
  List<BaseStruct> structList = new ArrayList<BaseStruct>();
  private Set<String> leftPanelSet = new HashSet<String>();
  private int leftWidth = 50;
  
  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return this.pageName;
  } 
  
  public TabPageStruct(String pageName) {
    this.pageName = pageName;
  }
  
  
  public BaseStruct getStruct(String structName) {
    for(BaseStruct struct: this.structList) {
      if (struct.getName().equals(structName)) {
        return struct;
      }
    }
    return null;
  }
  
  public void render(HtmlStringBuffer buff)  {
    //分左右
    List<BaseStruct> leftList = new ArrayList<BaseStruct>();
    List<BaseStruct> rightList = new ArrayList<BaseStruct>();
    for (BaseStruct subStruct : this.structList) {      
      if (this.leftPanelSet.contains(subStruct.getName())) {
        leftList.add(subStruct);
      } else {
        rightList.add(subStruct);
      }
    }   
    //先输出左边面板
    if (leftList.size() > 0) {
      buff.append(" <div id=\"").append(this.pageName).append("\" style=\"float:left; display:block; overflow:auto;width:")
        .append(this.leftWidth)
        .append("%;\">");
    }
    for (BaseStruct struct : leftList) {
      struct.render(buff);
    }
    if (leftList.size() > 0) {
      buff.append(" </div>");
    }
    //在输出右边面板
    if (leftList.size() > 0) {
      buff.append(" <div style=\"float:left;width:").append(100-this.leftWidth-2).append("%\">");
    }
    for (BaseStruct struct : rightList) {
      struct.render(buff);
    }
    if (leftList.size() > 0) {
      buff.append(" </div>");
    }
  }
 
  @Override
  public void addSubStruct(BaseStruct struct) {
    // TODO Auto-generated method stub
    this.structList.add(struct);
  } 
  
  /**
   * 设置左面板
   * @param tabName
   * @param panelName
   */
  public void setLeftPanel(String subStruct) {
    if (!this.leftPanelSet.contains(subStruct)){
      this.leftPanelSet.add(subStruct);
    }
  }
  
  /**
   * 设置左面板所在宽度比例
   * @param leftWidth
   */
  public void setLeftWidth(int leftWidth) {
    this.leftWidth = leftWidth;
  }
}
