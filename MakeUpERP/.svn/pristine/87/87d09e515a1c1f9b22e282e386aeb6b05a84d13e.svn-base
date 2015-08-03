package com.ga.click.demo;

import org.apache.click.Page;
import org.apache.click.util.Bindable;
import com.ga.click.control.tree.PanelTreeMenuControl;
import com.ga.click.control.tree.TopMenuControl;
import com.ga.click.control.tree.TreeNode;

/**
 * 菜单解析,根据登录用户和权限设置构建菜单
 * 格式为固定的顶部菜单结合左侧菜单,一级菜单为顶部导航,二三级菜单为左侧面板树
 * @author Administrator
 *
 */
public class Menu extends Page {
  private static final long serialVersionUID = 1L;
  @Bindable
  private PanelTreeMenuControl panelTree;
  private TreeNode treeNode;
  private Long defaultMenuID = 1L;
  
  /**
   * 构建时初始化菜单结构，并将第一个菜单设为模式显示菜单
   */
  public Menu() {
    treeNode = this.initTreeNode();
    if (treeNode != null && treeNode.getChildNodes() != null) {
      this.defaultMenuID = treeNode.getChildNodes().get(0).getNodeID();
    }
  }
  
  /**
   * 初始化菜单
   * @return
   */
  private TreeNode initTreeNode() {
    TreeNode treeNode = new TreeNode(0L,"根节点","");
    
    treeNode.addChildNode(new TreeNode(9L,"样例演示","/menu.htm"));
    treeNode.addChildNode(9L,new TreeNode(91L,"常规演示","/menu.htm"));
    treeNode.addChildNode(91L,new TreeNode(911L,"典型列表演示","/demo/demo_list.htm"));
    treeNode.addChildNode(91L,new TreeNode(912L,"查找带回演示","/demo/demo_lookup.htm"));
    treeNode.addChildNode(91L,new TreeNode(913L,"页面布局演示","/demo/demo_pagelayout.jsp"));
    treeNode.addChildNode(91L,new TreeNode(913L,"新控件演示","/demo/demo_newlist.htm"));
    treeNode.addChildNode(91L,new TreeNode(914L,"组合页面演示(左右)","/demo/demo_compise.htm"));
    treeNode.addChildNode(91L,new TreeNode(914L,"组合页面演示(上下)","/demo/demo_compise2.htm"));
    
    treeNode.addChildNode(new TreeNode(1L,"系统管理","/menu.htm"));
    treeNode.addChildNode(1L,new TreeNode(11L,"权限管理",""));
    treeNode.addChildNode(11L,new TreeNode(111L,"菜单管理","/system/menu_list.htm"));
    treeNode.addChildNode(11L,new TreeNode(112L,"操作员管理","/system/op_list.htm"));
    treeNode.addChildNode(11L,new TreeNode(113L,"角色管理","/system/role_list.htm"));
    treeNode.addChildNode(11L,new TreeNode(114L,"权限管理","/system/rbac_list.htm"));
    
    treeNode.addChildNode(1L,new TreeNode(12L,"数据安全",""));
    treeNode.addChildNode(1L,new TreeNode(13L,"信息维护",""));
    
    treeNode.addChildNode(new TreeNode(2L,"商品管理","/menu.htm")); 
    treeNode.addChildNode(2L, new TreeNode(21L, "分类管理", ""));
    treeNode.addChildNode(2L, new TreeNode(22L, "商品管理", ""));
    treeNode.addChildNode(21L, new TreeNode(211L,"商品分类管理","/commodity/sort_list.htm"));
    treeNode.addChildNode(22L, new TreeNode(221L, "商品管理", "/commodity/commodity_list.htm"));
    
    treeNode.addChildNode(new TreeNode(3L,"订单管理","/menu.htm"));
    treeNode.addChildNode(new TreeNode(4L,"会员管理","/menu.htm"));
    treeNode.addChildNode(new TreeNode(5L,"营销管理","/menu.htm"));
    treeNode.addChildNode(5L,new TreeNode(51L,"活动管理","/activity/activity_list.htm"));
    treeNode.addChildNode(51L,new TreeNode(511L,"活动管理","/activity/activity_list.htm"));
    treeNode.addChildNode(51L,new TreeNode(521L,"规则管理","/activity/activityRule_list.htm"));
    treeNode.addChildNode(51L,new TreeNode(531L,"订单优惠列表","/activity/discountType_list.htm"));
    treeNode.addChildNode(51L,new TreeNode(541L,"订单明细优惠列表","/activity/discountOrderParam_list.htm"));
    treeNode.addChildNode(new TreeNode(6L,"店铺管理","/menu.htm"));
    return treeNode;
  }
  
  /**
   * 获取默认二级菜单
   * @return
   */
  public PanelTreeMenuControl getDefaultMenu() {
    PanelTreeMenuControl getMenu = new PanelTreeMenuControl("panelTree",treeNode,this.defaultMenuID);
    return getMenu;
  }
  
  /**
   * 获取顶部菜单
   * @return
   */
  public TopMenuControl getTopMenu() {
    TopMenuControl topMenu = new TopMenuControl("topMenu",treeNode);
    return topMenu;
  }
  
  
  @Override
  public void onInit() {
    // TODO Auto-generated method stub
    //获取id参数
    String tmpStr = getContext().getRequest().getParameter("_menuid");
    Long tmpV = -1L;
    try {
      tmpV = Long.parseLong(tmpStr);
    } catch (Exception e) {     
    }
    if (tmpV <0) tmpV = this.defaultMenuID;
    if (tmpV >=0) {    
      panelTree = new PanelTreeMenuControl("panelTree",treeNode,tmpV);
      this.addControl(panelTree);
    }
  }
  
  @Override
  public String getTemplate() {
      return "/clicktemplate/menu.htm";
  }
}
