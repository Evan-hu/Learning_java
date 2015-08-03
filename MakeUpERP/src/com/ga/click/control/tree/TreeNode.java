package com.ga.click.control.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.ga.click.exception.BizException;

public class TreeNode {
	
  private String nodeName; //�ڵ�����
  private Long nodeID;     //�ڵ�ID
  private String nodeValue; //�ڵ�ֵ
  private Map<String,Object> nodeParam; //�ڵ�󶨵Ĳ���
  private boolean checked;  //�Ƿ�ѡ��
  private List<TreeNode> childNodes; //�ӽڵ�
  private int nodeLv;
  
  public TreeNode(Long nodeID,String nodeName,String nodeValue) {
    this.nodeID = nodeID;
    this.nodeName = nodeName;
    this.nodeValue = nodeValue;
  }
  
  
  public Map<String, Object> getNodeParam() {
    return nodeParam;
  }


  public void setNodeParam(Map<String, Object> nodeParam) {
    this.nodeParam = nodeParam;
  }


  public String getNodeName() {
    return nodeName;
  }
  public void setNodeName(String nodeName) {
    this.nodeName = nodeName;
  }
  public Long getNodeID() {
    return nodeID;
  }
  public void setNodeID(Long nodeID) {
    this.nodeID = nodeID;
  }
  public String getNodeValue() {
    return nodeValue;
  }
  public void setNodeValue(String nodeValue) {
    this.nodeValue = nodeValue;
  }
  public boolean isChecked() {
    return checked;
  }
  public void setChecked(boolean checked) {
    this.checked = checked;
  }
  
  public List<TreeNode> getChildNodes() {
    return childNodes;
  }
  
  public void addChildNode(TreeNode childNode) {
    if (findNode(childNode.nodeID) != null) {
      throw new BizException(BizException.SYSTEM,"�˵�ID��������ͬ");
    }
    if (this.childNodes == null) {
      this.childNodes = new ArrayList<TreeNode>();
    }
    this.childNodes.add(childNode);
  }
  
  public int getNodeLv() {
    return nodeLv;
  }
  public void setNodeLv(int nodeLv) {
    this.nodeLv = nodeLv;
  }
  
  
  public void addChildNode(Long parentNodeID,TreeNode childNode) {   
    if (findNode(childNode.nodeID) != null) {
    	System.out.println(childNode.nodeID);
      throw new BizException(BizException.SYSTEM,"�˵�ID��������ͬ");
    }
    TreeNode getNode = findNode(parentNodeID);
    if (getNode != null) {
      getNode.addChildNode(childNode);
    }    
  }
  public TreeNode findNode(Long treeNodeID) {
    return this.findNode(this,treeNodeID);
  }
  
  private TreeNode findNode(TreeNode treeNode,Long treeNodeID) {
    if (treeNode.nodeID == treeNodeID ||  treeNode.nodeID.equals(treeNodeID)) {
      return treeNode;
    }    
    TreeNode getNode = null;
    if (treeNode.childNodes != null) {
      for(TreeNode node : treeNode.childNodes) {
        if (node.getNodeID() == treeNodeID || node.getNodeID().equals(treeNodeID)) {
          return node;
        }
        getNode = findNode(node,treeNodeID);
        if (getNode != null) {
          return getNode;
        }
      }
    }
    return null;
  }


  public void setChildNodes(List<TreeNode> childNodes) {
    this.childNodes = childNodes;
  }
  
}
