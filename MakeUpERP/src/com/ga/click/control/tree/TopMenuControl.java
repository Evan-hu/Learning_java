package com.ga.click.control.tree;

import org.apache.click.control.AbstractControl;
import org.apache.click.util.HtmlStringBuffer;

public class TopMenuControl extends AbstractControl {
  private static final long serialVersionUID = 1L;
  private TreeNode rootNode;
  
  /**
   * 构建顶部菜单,显示根节点的直接子节点
   * @param name
   * @param rootNode   * 
   */
  public TopMenuControl(String name,TreeNode rootNode) {
    this.setName(name);
    this.rootNode = rootNode;    
  }
  @Override
  public void render(HtmlStringBuffer buffer) {
    // TODO Auto-generated method stub
    buffer.elementStart(getTag());
    buffer.appendAttribute("id", "navMenu");
    buffer.elementEnd();
    buffer.append("\r\n");    
    buffer.append("  <ul>\r\n");
    //逐步解析(默认选中第一个)    
    if (this.rootNode != null && this.rootNode.getChildNodes() != null) {
      int i=0;
      for(TreeNode node : this.rootNode.getChildNodes()) {
        if (i == 0) {
          buffer.append("    <li class=\"selected\"><a href=\"");
          if (node.getNodeValue().indexOf("?") > 0) {
            //有参数
            buffer.append(node.getNodeValue()).append("&_menuid=").append(node.getNodeID());
          } else {
            //无参数
            buffer.append(node.getNodeValue()).append("?_menuid=").append(node.getNodeID());
          }          
          buffer.append("\"><span>");
          buffer.append(node.getNodeName());
          buffer.append("</span></a></li>\r\n");
        } else {          
          buffer.append("    <li ><a href=\"");
          if (node.getNodeValue().indexOf("?") > 0) {
            //有参数
            buffer.append(node.getNodeValue()).append("&_menuid=").append(node.getNodeID());
          } else {
            //无参数
            buffer.append(node.getNodeValue()).append("?_menuid=").append(node.getNodeID());
          }  
          buffer.append("\"><span>");
          buffer.append(node.getNodeName());
          buffer.append("</span></a></li>\r\n");        
        }
        i++; 
      }      
    }    
    buffer.append("  </ul>\r\n");
    buffer.elementEnd("div");
    buffer.append("\r\n");
  }
  
  @Override
  public String getTag() {
    // TODO Auto-generated method stub
    return "div";
  }  
}
