package com.ga.click.control.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.click.control.AbstractControl;
import org.apache.click.util.HtmlStringBuffer;
import org.json.JSONObject;
import com.ga.click.control.GaConstant;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.UserACL;
import com.ga.click.util.ClickUtil;
import com.ga.click.util.GaUtil;

public class TreeControl  extends AbstractControl { 
  private TreeNode rootNode = null;
  private boolean haveCheck = false;
  private String onCheck = "";
  private boolean isExpand = false;
  private int layoutH = -1;
  private int showlv = 1;
  
  private List<String> checkValues = new ArrayList<String>();
  String url = ""; 
  private String ajaxMethod = "";
  private String clickJS = "";
  private Map<String,Object> paramMap = new HashMap<String,Object>();
  private Map<String,String> attributeMap = new HashMap<String,String>();
  
  private UserACL userAcl;
  
  public void setExpandLv(int showlv) {
    this.showlv = showlv;
  }
  /**
   * 创建树形控件
   * @param name 控件名称
   * @param rootNode 根节点
   * @param haveCheck 是否选中
   * @param isExpand 是否展开
   */
  public TreeControl(String name,TreeNode rootNode,String url,boolean isExpand,UserACL acl) {
    this.userAcl = acl;
    this.setName(name);
    this.rootNode = rootNode;
    this.isExpand  = isExpand;
    this.url = url;
  }
  
  public void SetCheck(boolean haveCheck,String onCheck) {
    this.haveCheck = haveCheck;
    this.onCheck = onCheck;
  }
  
  public List<String> getCheckValues() {
    return checkValues;
  }
    
  public void setCheckValues(List<String> checkValues) {
    this.checkValues = checkValues;
  }

@Override
  public void render(HtmlStringBuffer buffer) {
    // TODO Auto-generated method stub
    try {
      buffer.elementStart(getTag());
      buffer.appendAttribute("class", "tree");   
      buffer.append(">\r\n");    
      if (this.rootNode != null) {
        //递归渲染子节点
          buffer.append("  <ul class=\"tree treeFolder");
          if (this.haveCheck) {
            buffer.append(" treeCheck");
          }
          if (this.isExpand) {
            buffer.append(" expand");
          } else {
            buffer.append(" collapse");            
          }
          
          buffer.append("\"");
          if (this.showlv > 0) {
            buffer.append(" showlv=\"").append(this.showlv).append("\"");
          }
          //设置oncheck
          if (!GaUtil.isNullStr(this.onCheck)) {
            buffer.append(" oncheck=\"").append(this.onCheck).append("\"");
          }
          buffer.append(">\r\n");
          //先渲染根节点
          buffer.append("    <li>");
          renderLink(this.rootNode,buffer);
          if(this.rootNode.getChildNodes() != null){
              buffer.append(" <ul>"); 
              renderChildTree(this.rootNode,buffer,true);
          }         
          buffer.append("  </li>\r\n");
      } 
      buffer.elementEnd("ul");
      buffer.append("\r\n");
      
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"渲染失败");
    }
  }

  @Override
  public String getTag() {
    // TODO Auto-generated method stub
    return "ul";
  }
  
  /**
   * 将指定节点的所有下级节点以树的形式输出
   * @param rooNode
   * @param buffer
   */
  private void renderChildTree(TreeNode topNode,HtmlStringBuffer buffer,boolean isTop) throws Exception {
    if (topNode == null || topNode.getChildNodes() == null) return;
    
    for(TreeNode node : topNode.getChildNodes()) {
      buffer.append("    <li>");
      renderLink(node,buffer);
      if(node.getChildNodes() != null){
          buffer.append(" <ul>");          
      }
      renderChildTree(node,buffer,false);
      buffer.append("</li>\r\n");  
    }
    buffer.append("  </ul>\r\n");
    
   
  }
  
  /**
   * 绑定ajax方法
   * @param ajaxMethod
   */
  public void bindAjaxMethod(String ajaxMethod) {
    this.ajaxMethod = ajaxMethod;
  }
  
  /**
   * 设置按钮所绑定的列表控件
   * @param tableID 列表ID
   */
  public void bindTableRowData(String tableID) {
    if (this.paramMap == null) this.paramMap = new HashMap<String,Object>();
    this.paramMap.put(GaConstant.FIXPARAM_ROWDATA, "{row_data}"+tableID);
  }
  
  /**
   * 设置自定义的CLICK JS函数
   * @param clickJS
   */
  public void setOnClick(String clickJS) {
    this.clickJS = clickJS;
  }
  
  /**
   * 设置属性
   */
  public void setAttribute(String name,String value) {
    this.attributeMap.put(name, value);
  }
  
  public void setParam(String name,Object value) {
    this.paramMap.put(name, value);
  }
  
  private void renderLink(TreeNode node, HtmlStringBuffer buffer) throws Exception {
    buffer.append("<a");
    buffer.append(" id=\"").append(node.getNodeID()).append("\"");
    buffer.append(" tname=\"").append(this.getName()).append("\"");
    buffer.append(" tvalue=\"").append(node.getNodeValue()).append("\"");
    if(!haveCheck){
    	
    	buffer.append(" href=\"javascript:void(0);\"");
    	if(!GaUtil.isNullStr(url)){
	      buffer.append(" url=\"").append(this.url).append("\"");
	      if (!GaUtil.isNullStr(this.clickJS)) { 
	        buffer.append(" onclick=\"").append(this.clickJS).append("\"");
	      } else {
	        buffer.append(" onclick=\"doLinkPost(this)\"");
	      }    
    	}
        if (!GaUtil.isNullStr(this.ajaxMethod)) { 
          buffer.append(" _pageAction=\"").append(this.ajaxMethod).append("\"");
        }
        //添加绑定
        if (node.getNodeParam() != null) {
          this.paramMap.putAll(node.getNodeParam());
        }
        buffer.append(" _actionid=\"").append(this.name).append("\"");
        if (this.paramMap != null) {
          JSONObject obj = ClickUtil.getJSONObject(this.paramMap);
          if (obj != null) {
            buffer.append(" param=\"").append(obj.toString().replace("\"","'")).append("\"");  
          }      
        }    
    } else{
        for(String checkValue : checkValues){
          
          if(checkValue.equals(node.getNodeValue())){
              buffer.append(" checked=\"true\"");
          }
        }
    }
    if (this.attributeMap != null) {
      for (String key : this.attributeMap.keySet()) {
        String value = this.attributeMap.get(key);
        if (!GaUtil.isNullStr(value)) {
          buffer.append(" ").append(key).append("=\"").append(value).append("\"");
        }
      }
    }
    buffer.append(">");
    buffer.append(node.getNodeName());
    buffer.append("</a>");
    
  }

  public int getLayoutH() {
    return layoutH;
  }

  /**
   * 设置自动布局高度
   * -1.不出现滚动条，实际高度多少显示多少
   * 其他.手动设置的layoutH高度
   * @param layoutH
   */
  public void setLayoutH(int layoutH) {
    this.layoutH = layoutH;
  }
  
  
  
}
