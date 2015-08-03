package com.ga.click.control.button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.click.control.AbstractControl;
import org.apache.click.util.HtmlStringBuffer;
import org.json.JSONObject;
import com.ga.click.control.GaConstant;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.View;
import com.ga.click.util.ClickUtil;
import com.ga.click.util.GaUtil;

public class ActionButton extends AbstractControl{
	
  private static final long serialVersionUID = 1L;
  private boolean isLink = true; 
  private String ajaxMethod = "";
  private String clickJS = "";
  private String beforeClickJS = "";
    
  private String title = "";
  private String url = "";
  private String formID = "";
  private Map<String,Object> paramMap = null;  
  private Map<String,String> attributeMap = new HashMap<String,String>();
  private boolean hidden = false;
  private boolean disabled = false;
  private PageEvent event;
  
  //key:view;String[0]-FieldCode;String[1]-ParamName,String[2]-0参数;1:数据
  private Map<View,List<String[]>> viewBindParam = new HashMap<View,List<String[]>>(); 
  
  /**
   * 构建动作按钮对象(默认为link样式,可设置为button)
   * @param name
   * @param title
   * @param url
   * @param paraMap
   */
  public ActionButton(Class callClass,String id,String title,String url,Map<String,Object> paraMap) {
//    String className = callClass.getName();
//    if (className != "") {
//      className = className.substring(className.lastIndexOf(".") + 1);
//    }
//    this.setName(className+"."+id);
    this.setName(id);
    this.title = title;
    this.url = url;
    this.paramMap = paraMap;
  }
  
  
  
  
  public String getUrl() {
    return url;
  }




  public void setUrl(String url) {
    this.url = url;
  }




  public void addParam(String paramName,Object paramValue) {
    if (this.paramMap == null) this.paramMap = new HashMap<String,Object>();
    this.paramMap.put(paramName,paramValue);
  }

  /**
   * 此方法不建议使用,请使用SubmitTool.submitToAjax方法和替换
   * @deprecated
   * @param formID
   * @param ajaxMethod
   */
  public void setLinkButton(String ajaxMethod) {
    this.isLink = true;
    if (!GaUtil.isNullStr(ajaxMethod)) {
      this.ajaxMethod = ajaxMethod;  
    }
  }
  
  /**
   * 此方法不建议使用,请使用SubmitTool.submitToAjax方法和本对象的bindForm方法替换
   * @deprecated
   * @param formID
   * @param ajaxMethod
   */
  public void setFormButton(String formID,String ajaxMethod) {
    this.isLink = false;
    this.formID = formID;
    if (!GaUtil.isNullStr(ajaxMethod)) {
      this.ajaxMethod = ajaxMethod;  
    }
  }
  
  
  public void setBeforeClickJS(String beforeClickJS) {
    this.beforeClickJS = beforeClickJS;
  }

  /**
   * 绑定ajax方法
   * @param ajaxMethod
   */
  public void bindAjaxMethod(String ajaxMethod) {
    this.ajaxMethod = ajaxMethod;
  }
  
  /**
   * 设置按钮所提交的表单
   * @param formID 表单ID
   */
  public void bindForm(String formID) {
    this.isLink = false;
    this.formID = formID;
  }
  
  public void bindForm(String formID,boolean isLink) {
    this.isLink = isLink;
    this.formID = formID;
  }
  /**
   * 设置按钮所绑定的列表控件
   * (包括单行选中和复选框选中)
   * @param tableID 列表ID
   */
  public void bindTableRowData(String tableID) {
    if (this.paramMap == null) this.paramMap = new HashMap<String,Object>();
    this.paramMap.put(GaConstant.FIXPARAM_ROWDATA, "{row_data}"+tableID);
  }

  public void bindTableMultiSelect(String tableID) {
    if (this.paramMap == null) this.paramMap = new HashMap<String,Object>();
    this.paramMap.put(GaConstant.FIXPARAM_MULTISELECT, "{select_ids}"+tableID);
  }
  
  /**
   * 设置自定义的CLICK JS函数
   * @param clickJS
   */
  public void setOnClick(String clickJS) {
    this.clickJS = clickJS;
  }
  
  /**
   * 设置按钮触发后的回调JS（
   * @param jsFunc
   */
  public void setMvcCallbackJS(String jsFunc) {
    this.setAttribute("_mvccallback", jsFunc);
  }
  
  /**
   * 设置确认提示
   * @param msg
   */
  public void setConfirm(String msg) throws Exception {
    JSONObject json = new JSONObject();
    json.put("chk", "");
    json.put("pass", msg);
    json.put("err","");
    this.attributeMap.put("confirm", json.toString().replace("\"", "'"));
  }
  
  /**
   * 设置按钮确认提示,可设置检查当前按钮所提交表单的参数值
   * @param checkExpress 检查表达式,
   *    表达式格式如: [paramname]>0,表示某参数大于0就通过
   *            如要检查rowdata的变量则应设置为:_rowdata[paramname]=='a'
   * @param passInfo 如检查通过，并且设置了passInfo，则会提示此信息，并让用户选择是否继续
   * @param errInfo 如检查未通过，则显示此信息,返回
   */
  public void setConfirm(String checkExpress,String passInfo,String errInfo) throws Exception {
    JSONObject json = new JSONObject();
    json.put("chk", checkExpress);
    json.put("pass", passInfo);
    json.put("err",errInfo);
    this.attributeMap.put("confirm", json.toString().replace("\"", "'"));
  }
  
  /**
   * 设置属性
   */
  public void setAttribute(String name,String value) {
    this.attributeMap.put(name, value);
  }
  
  @Override
  public void render(HtmlStringBuffer buffer) {
    // TODO Auto-generated method stub
    try {
      parseViewBindParam();
      if (this.isHidden()) {
        this.setAttribute("style","display:none;");
      }
      if (this.isLink) {
        this.renderLink(buffer);
      } else {
        this.renderButton(buffer);
      } 
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"渲染按钮异常."+ex.getMessage(),ex);
    }    
  }
  /**
   * 渲染连接
   * @param buffer
   * @throws Exception
   */
  private void renderLink(HtmlStringBuffer buffer) throws Exception {
    String tmpV = this.getName().replace(".","_");
    if (!this.disabled) {
        buffer.append("<a");
        buffer.append(" id=\"").append(tmpV).append("\"");
        buffer.append(" url=\"").append(this.url).append("\"");
        buffer.append(" href=\"javascript:void(0);\"");
        if (!GaUtil.isNullStr(this.clickJS)) { 
          buffer.append(" onclick=\"").append(this.clickJS).append("\"");
        } else {
          if (GaUtil.isNullStr(this.formID)) {
            buffer.append(" onclick=\"doLinkPost(this)\"");
          } else {
            buffer.append(" onclick=\"doFormPost(this,'").append(this.formID).append("')\"");
          }
        }    
        if (!GaUtil.isNullStr(this.ajaxMethod)) { 
          buffer.append(" _pageAction=\"").append(this.ajaxMethod).append("\"");
        }
        if (!GaUtil.isNullStr(this.beforeClickJS)) { 
          buffer.append(" beforeClickJS=\"").append(this.beforeClickJS).append("\"");
        }    
        //添加actionid
        buffer.append(" _actionid=\"").append(this.name).append("\"");
        if (this.paramMap != null) {
          JSONObject obj = ClickUtil.getJSONObject(this.paramMap);
          if (obj != null) {
            buffer.append(" param=\"").append(obj.toString().replace("\"","'")).append("\"");  
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
        buffer.append("<span>").append(this.title).append("</span>");
        buffer.append("</a>"); 
    } else {
      //按钮禁止状态
      buffer.append("<span style=\"background:gray\">");  
      buffer.append(this.title);
      buffer.append("</span>");
    }
  }
  
  /**
   * 渲染按钮
   * @param buffer
   */
  private void renderButton(HtmlStringBuffer buffer) throws Exception {
    if (!this.disabled) {
      String tmpV = this.getName().replace(".","_");
      if (this.getName().endsWith(".submit")) {
        buffer.append("<input type=\"submit\"");  
      } else {
        buffer.append("<input type=\"button\"");
        buffer.append(" id=\"").append(tmpV).append("\"");
        buffer.append(" url=\"").append(this.url).append("\"");
        if (!GaUtil.isNullStr(this.clickJS)) { 
          buffer.append(" onclick=\"").append(this.clickJS).append("\"");
        } else {
          buffer.append(" onclick=\"doFormPost(this,'").append(this.formID).append("')\"");
        }    
        if (!GaUtil.isNullStr(this.ajaxMethod)) { 
          buffer.append(" _pageAction=\"").append(this.ajaxMethod).append("\"");
        }
        if (!GaUtil.isNullStr(this.beforeClickJS)) { 
          buffer.append(" beforeClickJS=\"").append(this.beforeClickJS).append("\"");
        }
        //添加actionid
        buffer.append(" _actionid=\"").append(this.name).append("\"");
        if (this.paramMap != null) {
          JSONObject obj = ClickUtil.getJSONObject(this.paramMap);
          if (obj != null) {
            buffer.append(" param=\"").append(obj.toString().replace("\"","'")).append("\"");  
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
      }    
      buffer.append(" value=\"").append(this.title).append("\">");
      buffer.append("</input>");
    } else {
      //禁止状态
      buffer.append("<span style=\"background:gray\">");  
      buffer.append(this.title);
      buffer.append("</span>");
    }
  }
  
  @Override
  public String getTag() {
    // TODO Auto-generated method stub
    return "a";
  }
  
  public void setTitle(String title) {
    this.title = title;
  }


  public boolean isHidden() {
    return hidden;
  }


  public void setHidden(boolean hidden) {
    this.hidden = hidden;
  }
  
  
  public void setDisabled(boolean disabled) {
    this.disabled = disabled;
  }

  
  public Map<String, Object> getParamMap() {
      return paramMap == null ? new HashMap<String, Object>() : paramMap;
  }


  public String getAjaxMethod() {
    return ajaxMethod;
  }


  public void setClickJS(String clickJS) {
    this.clickJS = clickJS;
  }

  public Map<String, String> getAttributeMap() {
    return attributeMap;
  }  
  
  
  /**
   * 绑定视图参数
   * @param view 视图对象
   * @param paramName 提交的参数名
   * @param viewFieldCode 视图对象对应的DBFIELD
   * @param isData 是否于输入还是参数(true:输入)
   */
  public void bindViewParam(View view,String paramName,String viewFieldCode,boolean isData) {
    List<String[]> paramInfoList = this.viewBindParam.get(view);
    if (paramInfoList == null) {
      paramInfoList = new ArrayList<String[]>();
      this.viewBindParam.put(view,paramInfoList);
    }
    String[] paramInfo = new String[3];
    paramInfo[0] = viewFieldCode;
    paramInfo[1] = paramName;
    if (isData) {
      paramInfo[2] = "1";
    } else {
      paramInfo[2] = "0";
    }
    paramInfoList.add(paramInfo);
  }
  
  
  private void parseViewBindParam() {
    for(View view: this.viewBindParam.keySet()) {
      List<String[]> paramInfoList = this.viewBindParam.get(view);
      if (paramInfoList != null) {
        for (String[] paramInfo : paramInfoList) {
          Object value;
          if (paramInfo[2].equals("1")) {
            value = view.getFieldInputValue(paramInfo[0]);
          } else {
            value = view.getFieldParamValue(paramInfo[0]);
          }
          if (this.paramMap == null) this.paramMap = new HashMap<String,Object>();
          this.paramMap.put(paramInfo[1],value);
        }
      }     
    }
  }

  public PageEvent getEvent() {
    return event;
  }

  public void setEvent(PageEvent event) {
    this.event = event;
  }
  
  public void setQueryNavInfo(String navInfo,String preNavInfo) {
    this.setAttribute(GaConstant.FIXPARAM_WINDOWNAV, navInfo);
    this.setAttribute(GaConstant.FIXPARAM_PREWINDOWNAV, preNavInfo);
  }




  public String getTitle() {
    return title;
  }
  
}
