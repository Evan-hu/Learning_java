package com.ga.click.control.tree;

import org.apache.click.control.AbstractControl;
import org.apache.click.util.HtmlStringBuffer;

public class PanelTreeMenuControl extends AbstractControl {
  private static final long serialVersionUID = 1L;
  private TreeNode rootNode;
  private Long parentNodeID;
  
  /**
   * 构建顶部菜单,显示根节点的直接子节点
   * @param name
   * @param rootNode   * 
   */
  public PanelTreeMenuControl(String name,TreeNode rootNode,Long parentNodeID) {
    this.setName(name);
    this.rootNode = rootNode;
    this.parentNodeID = parentNodeID;
  }
  
  @Override
  public void render(HtmlStringBuffer buffer) {
    // TODO Auto-generated method stub
    buffer.elementStart(getTag());
    buffer.appendAttribute("class", "accordion");
    buffer.appendAttribute("fillSpace", "sidebar");
    buffer.append(">\r\n");    
    if (this.rootNode != null) {
      TreeNode dispNode = this.rootNode.findNode(parentNodeID);
      if (dispNode != null && dispNode.getChildNodes() != null) {        
        for(TreeNode node : dispNode.getChildNodes()) {
          //输出第一级面板
          buffer.append("  <div class=\"accordionHeader\">\r\n");
          buffer.append("    <h2><span>Folder</span>").append(node.getNodeName()).append("</h2>\r\n");
          buffer.append("  </div>\r\n");
          //输出本级的菜单树
          buffer.append("  <div class=\"accordionContent\">\r\n");
          renderChildTree(node,buffer,true);
          buffer.append("  </div>\r\n");
        }
      }
    } 
    buffer.elementEnd("div");
    buffer.append("\r\n");
  }
  
  /**
   * 将指定节点的所有下级节点以树的形式输出
   * @param rooNode
   * @param buffer
   */
  private void renderChildTree(TreeNode topNode,HtmlStringBuffer buffer,boolean isTop) {
    if (topNode == null || topNode.getChildNodes() == null) return;
    if (isTop) {
      buffer.append("  <ul class=\"tree treeFolder\">\r\n");  
    } else {
      buffer.append("  <ul>\r\n");
    }
    for(TreeNode node : topNode.getChildNodes()) {
      buffer.append("    <li>");
      if (node.getNodeValue().indexOf("?") > 0) {
        buffer.append("<a href=\"").append(node.getNodeValue())
        .append("&_menuid=")
        .append(node.getNodeID())
        .append("\" target=\"navTab\" rel=\"m_"+node.getNodeID()+"\">")
        .append(node.getNodeName())
        .append("</a>");  
      } else {
        buffer.append("<a href=\"").append(node.getNodeValue())
        .append("?_menuid=")
        .append(node.getNodeID())
        .append("\" target=\"navTab\"  rel=\"m_"+node.getNodeID()+"\">")
        .append(node.getNodeName())
        .append("</a>"); 
      }
      renderChildTree(node,buffer,false);
      buffer.append("</li>\r\n");  
    }
    buffer.append("  </ul>\r\n");
  }
  
  @Override
  public String getTag() {
    // TODO Auto-generated method stub
    return "div";
  }  
}
