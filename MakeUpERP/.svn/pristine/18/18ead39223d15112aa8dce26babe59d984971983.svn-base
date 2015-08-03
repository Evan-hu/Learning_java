package com.ga.click.control.button;

import java.util.HashMap;
import java.util.Map;
import org.apache.click.Context;
import org.apache.click.control.ActionLink;
import org.apache.click.util.ClickUtils;
import org.apache.click.util.HtmlStringBuffer;
import org.apache.commons.lang.StringUtils;

public class ColumnActionLink extends ActionLink{
  
  private static final long serialVersionUID = 1L;
  private int actionType = 1; //默认为链接打开;1.打开链接;2.PAGE提交
  private String linkUrl = "";  //链接地址
  
  private Map<String,String> bindFieldCodes = new HashMap<String,String>();
  
 
  /**
   * Create an ActionLink with no name defined. <b>Please note</b> the
   * control's name must be defined before it is valid.
   */
  public ColumnActionLink() {
    //默认生成NAME    
  }
  

  public void AddBindFieldPara(String fieldCode) {
    this.bindFieldCodes.put(fieldCode,fieldCode);
  }
  
  public void AddBindFieldPara(String fieldCode,String para) {
    this.bindFieldCodes.put(fieldCode,para);
  }
  
  public Map<String,String> GetBindFieldPara() {
    return this.bindFieldCodes;
  }
  
  
  public void SetLinkListener(String label,Object listener,String funcName,Map paraMap) {
    this.name = "linkListener";
    this.actionType = 2;
    this.setName("linkListener");
    this.setLabel(label);
    this.setAttribute("external", "false");
    this.setAttribute("target", "navTab");
    this.setAttribute("rel", label); 
    this.setParameters(paraMap);
    this.setListener(listener,funcName);
  }
  
  public void SetLinkHref(String label,String linkUrl,Map paraMap,String targetType) {
    this.name = "linkHref";
    this.actionType = 1;
    this.setName("linkHref");
    this.setLabel(label);
    this.setAttribute("external", "false");
    this.setAttribute("target", "navTab"); 
    this.setAttribute("rel", label); 
    this.setParameters(paraMap);
    this.linkUrl = linkUrl;    
  }
  
  public void SetTarget(String targetName,boolean isDialog) {    
    if (isDialog) {
      this.setAttribute("target","dialog");
    } else {
      this.setAttribute("target", "navTab");
    }
    this.setAttribute("rel", "targetName"); 
  }
    
  //重载Label
  public String getLabel() {
    if (label == null) {
        label = getMessage(getName() + ".label");
    }
    if (label == null) {
        label = ClickUtils.toLabel(getName());
    }
    label = "<span>"+label+"</span>";
    return label;
  }
  
  @Override
  public void render(HtmlStringBuffer buffer) {
      if (isDisabled()) {
          buffer.elementStart("span");
          buffer.appendAttribute("id", getId());
          addStyleClass("disabled");
          buffer.appendAttribute("class", getAttribute("class"));

          if (hasAttribute("style")) {
              buffer.appendAttribute("style", getAttribute("style"));
          }
          buffer.closeTag();
          if (StringUtils.isBlank(getImageSrc())) {
              buffer.append(getLabel());

          } else {
              renderImgTag(buffer);
              if (isRenderLabelAndImage()) {
                  buffer.elementStart("span").closeTag();
                  buffer.append(getLabel());
                  buffer.elementEnd("span");
              }
          }
          buffer.elementEnd("span");
      } else {
          buffer.elementStart(getTag());
          removeStyleClass("disabled");
          buffer.appendAttribute("href", getHref());
          buffer.appendAttribute("id", getId());
          buffer.appendAttributeEscaped("title", getTitle());
          if (getTabIndex() > 0) {
              buffer.appendAttribute("tabindex", getTabIndex());
          }

          appendAttributes(buffer);
          buffer.closeTag();
          if (StringUtils.isBlank(getImageSrc())) {
              buffer.append(getLabel());
          } else {
              renderImgTag(buffer);
              if (isRenderLabelAndImage()) {
                  buffer.elementStart("span").closeTag();
                  buffer.append(getLabel());
                  buffer.elementEnd("span");
              }
          }
          buffer.elementEnd(getTag());
      }
  }
  
  
  public String getHref(Object value) {
    if (this.actionType == 1) {
      return getLinkHref(value);
    } else {
      return getListenerHref(value);
    }
  }
  
  /**
   * 生成提交页面的链接
   * @param value
   * @return
   */
  public String getListenerHref(Object value) {
    Context context = getContext();
    String uri = ClickUtils.getRequestURI(context.getRequest());
    HtmlStringBuffer buffer =
            new HtmlStringBuffer(uri.length() + getName().length() + 40);
    buffer.append(uri);
    buffer.append("?");
    buffer.append(ACTION_LINK);
    buffer.append("=");
    buffer.append(getName());
    if (value != null) {
        buffer.append("&amp;");
        buffer.append(VALUE);
        buffer.append("=");
        buffer.append(ClickUtils.encodeUrl(value, context));
    }
    if (hasParameters()) {
        for (String paramName : getParameters().keySet()) {
            if (!paramName.equals(ACTION_LINK) && !paramName.equals(VALUE)) {
                Object paramValue = getParameters().get(paramName);

                // Check for multivalued parameter
                if (paramValue instanceof String[]) {
                    String[] paramValues = (String[]) paramValue;
                    for (int j = 0; j < paramValues.length; j++) {
                        buffer.append("&amp;");
                        buffer.append(paramName);
                        buffer.append("=");
                        buffer.append(ClickUtils.encodeUrl(paramValues[j],
                            context));
                    }
                } else {
                    if (paramValue != null) {
                        buffer.append("&amp;");
                        buffer.append(paramName);
                        buffer.append("=");
                        buffer.append(ClickUtils.encodeUrl(paramValue,
                                                           context));
                    }
                }
            }
        }
    }
    return context.getResponse().encodeURL(buffer.toString());
  }

  
  /**
   * 生成页面链接
   * @param value
   * @return
   */
  private String getLinkHref(Object value) {
      Context context = getContext();
      String uri = this.linkUrl;
      HtmlStringBuffer buffer =
              new HtmlStringBuffer(uri.length() + getName().length() + 40);
      buffer.append(uri);
      if (hasParameters()) {
        if (uri.indexOf("?") == -1 ) {
          buffer.append("?");
        }
        StringBuffer paraBuff = new StringBuffer();
        for (String paramName : getParameters().keySet()) {
              if (!paramName.equals(ACTION_LINK) && !paramName.equals(VALUE)) {
                  Object paramValue = getParameters().get(paramName);
                  // Check for multivalued parameter                 
                  if (paramValue instanceof String[]) {
                      String[] paramValues = (String[]) paramValue;
                      for (int j = 0; j < paramValues.length; j++) {
                        paraBuff.append("&amp;");
                        paraBuff.append(paramName);
                        paraBuff.append("=");
                        paraBuff.append(ClickUtils.encodeUrl(paramValues[j],
                              context));
                      }
                  } else {
                      if (paramValue != null) {
                        paraBuff.append("&amp;");
                        paraBuff.append(paramName);
                        paraBuff.append("=");
                        paraBuff.append(ClickUtils.encodeUrl(paramValue,context));
                      }
                  }
              }
          }
        if (paraBuff.length() > 0) {
          buffer.append(paraBuff.substring(5));
        } else {
          buffer.append(paraBuff);
        }
      }
      return context.getResponse().encodeURL(buffer.toString());
    }

}
