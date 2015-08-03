package com.ga.click.control.extra;

import org.apache.click.control.Field;
import org.apache.click.util.HtmlStringBuffer;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.util.ClickUtil;
import com.ga.click.util.GaUtil;

public class PopSelect extends Field {
  private static final long serialVersionUID = 1L;
  private String popID;
  private String popSelectFields;
  private String popPageUrl;
  private int popWidth = 400;
  private int popHeight = 300;
  private String popBindField;
  private boolean popDisplay;
  private String popConfirm = "";
  private int allInputWidth = 0;
  private String inputJS = "";
  private DbField defiField = null;
  private boolean popEdit = false;
  /**
   * 构建顶部菜单,显示根节点的直接子节点
   * @param name
   * @param rootNode   * 
   */
//  public PopSelect(String label,String name,String popID,String bindField,boolean isDisplay,String popUrl,String selectFields,int popWidth,int popHeight) {
//    this(label,name,popID,bindField,isDisplay,popUrl,selectFields,"",popWidth,popHeight);
//  }
  
  /**
   * 构建具备确认操作的查找带回
   */
  public PopSelect(String label,String name,String popID,String bindField,boolean isDisplay,String popUrl,String selectFields,String confrimInfo,int popWidth,int popHeight) {
    this.setName(name);
    this.setLabel(label);
    this.popID = popID;
    this.popBindField = bindField;
    this.popDisplay = isDisplay;
    this.popHeight = popHeight;
    this.popWidth = popWidth;
    this.popPageUrl = popUrl;
    this.popSelectFields = selectFields;
    this.popConfirm = confrimInfo;
  }
  
  public PopSelect(String label,String name,String popID,String bindField,boolean isDisplay) {
    this.setName(name);
    this.setLabel(label);
    this.popID = popID;
    this.popBindField = bindField;
    this.popDisplay = isDisplay;
  }
  
  
  
  public void setPopEdit(boolean popEdit) {
    this.popEdit = popEdit;
  }

  public void setDbField(DbField defiField) {
    this.defiField = defiField;
  }
  
  @Override
  public void render(HtmlStringBuffer buffer) {
    // TODO Auto-generated method stub
    if (this.popDisplay) {
      if (this.defiField.getPopSubInput() > -1) {
        this.defiField.setInput(this.defiField.getPopSubInput());
        Field subInput = ClickUtil.createControl(defiField); 
        this.defiField.setPopSubInput(-1);
        this.defiField.setInput(GaConstant.INPUT_POPSELECT);
        if (!this.popEdit) {
          subInput.addStyleClass("readonly");
          subInput.setAttribute("readonly", "readonly");
        }
        subInput.setAttribute("lookupGroup", popID);    
        //subInput.setAttribute("name", popID+"."+this.popBindField);
        subInput.setName(popID+"."+this.popBindField);
        if (allInputWidth > 40) {
          subInput.setAttribute("style", "width:"+(this.allInputWidth - 40) +"px;");  
        } 
        subInput.setValue(value);
        subInput.render(buffer);       
        return;
      } else { 
        buffer.elementStart(getTag());
        buffer.appendAttribute("type", "text");
        if (!this.popEdit) {
          buffer.appendAttribute("class", "readonly");
          buffer.appendAttribute("readonly", "readonly");
        }
      }
    } else {
      buffer.elementStart(getTag());
      buffer.appendAttribute("type", "hidden");
    }
    if (this.defiField != null) {
      buffer.appendAttribute("value", ClickUtil.getDisplayValue(this.defiField,value));
    } else {
      buffer.appendAttribute("value", value);
    }

    buffer.appendAttribute("lookupGroup", popID);    
    buffer.appendAttribute("name", popID+"."+this.popBindField);
    if (allInputWidth > 40) {
      buffer.appendAttribute("style", "width:"+(this.allInputWidth - 40) +"px;");  
    } 
    buffer.elementEnd();
    if (!GaUtil.isNullStr(this.popPageUrl)) {
      //输出选择控件
      String linkUrl = "?";
      if (this.popPageUrl.indexOf("?") > 0) {
        linkUrl = "&";
      }      
      linkUrl = this.popPageUrl + linkUrl + "lookupMode="+this.popSelectFields + 
                 "&popsize="+this.popWidth+","+this.popHeight +
                 "&poptarget="+this.popID+"Link";
      String linkConfirm = "\"";
      if (!GaUtil.isNullStr(this.popConfirm) && !GaUtil.isNullStr(value)) {
        linkConfirm = "\" confirm='"+this.popConfirm+"' ";
      }
      String clickjs = "";
      if (!GaUtil.isNullStr(this.inputJS)) {
        clickjs = " onclick=\"" + this.inputJS + ("(this);\" ");
      }
      buffer.append("<a id=\"").append(this.popID).append("Link\" class=\"btnLook\" width=\"").append(this.popWidth)
        .append("\" height=\"").append(this.popHeight)
        .append("\" href=\"").append(linkUrl)
        .append(linkConfirm)
        .append(clickjs)
        .append(" lookupGroup=\"").append(this.popID).append("\">")
        .append("选择</a>")
        ;
    }
    buffer.append("\r\n");    
  }
  
  @Override
  public String getTag() {
    // TODO Auto-generated method stub
    return "input";
  }

  public void setAllInputWidth(int inputWidth) {
    this.allInputWidth = inputWidth;
  }

  public boolean isPopDisplay() {
    return popDisplay;
  }

  @Override
  public boolean isHidden() {
    // TODO Auto-generated method stub
    return !this.popDisplay;
  }

  @Override
  protected String getRequestValue() {
    // TODO Auto-generated method stub
    String requestValue = getContext().getRequestParameter(this.popID+"."+this.popBindField);
    getContext().getRequest();
    if (requestValue != null) {
        if (isTrim()) {
            return requestValue.trim();
        } else {
            return requestValue;
        }
    } else {
        return "";
    }
  }

  public void setPopPageUrl(String popPageUrl) {
    this.popPageUrl = popPageUrl;
  }

  public void setInputJS(String inputJS) {
    this.inputJS = inputJS;
  }

  public void setPopSelectFields(String popSelectFields) {
    this.popSelectFields = popSelectFields;
  }  
  
}
