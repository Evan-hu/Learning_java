package com.ga.click.control.form;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.apache.click.Control;
import org.apache.click.control.Container;
import org.apache.click.control.Field;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Label;
import org.apache.click.util.ContainerUtils;
import org.apache.click.util.HtmlStringBuffer;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.UserACL;
import com.ga.click.util.ClickUtil;
import com.ga.click.util.GaUtil;

/**
 * ��ѯ���ؼ�������dbField�Ĳ�ѯ���ý����Զ�����
 * @author Administrator
 *
 */
public class ListForm  extends Form{
    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_PAGESIZE = 20;

    private List<ActionButton> buttonList = new ArrayList<ActionButton>();
    private boolean haveAdvQuery = false;
    private ActionButton submitButton = null;
    private boolean isHidden = false;
    private int queryRows = 1;
    private String tipInfo = "";
    private boolean autoHideButton = true; //�Ƿ��Զ����ز�ѯ��ť 
    private UserACL userAcl =null;
    private String viewID=null;
    
    public ListForm(List<DbField> dbField,UserACL acl,String viewID) {
      super("pagerForm");
      try {
        this.viewID = viewID;
        //this.setAttribute("rel", "pagerForm");  
        //���������ֶδ����ؼ�������������
        boolean haveQuery = false;
        this.userAcl = acl;
        //�������������ֶ�
        List<DbField> queryList = new ArrayList<DbField>();
        for (int i = 0; i < dbField.size(); ++i) {
          DbField field = dbField.get(i);
          if (field.getQueryOpera() > 0 ) {
            queryList.add(field);
          }
        }
        Collections.sort(queryList,new CompareQueryField());
        for (int i = 0; i < queryList.size(); ++i) {
          DbField field = queryList.get(i);     
          List<Field> queryControlList = ClickUtil.createQueryControl(field, viewID);
          if (queryControlList != null) {
            for(Field  control : queryControlList) {
              if (control != null) {
                this.add(control);
                haveQuery = true;
              }
            }
          }
        }
          //��ʼ��Ĭ���ύ��ť
        submitButton = new ActionButton(this.getClass(),"qrybutton","��ѯ","",null);
        submitButton.addStyleClass("searchButton");
        submitButton.bindForm(this.getName());
        this.regFormButton(submitButton);
      } catch(Exception ex) {
          ex.printStackTrace();
        throw new BizException(BizException.SYSTEM,"������ѯ���쳣;"+ex.getMessage());
      }
    }
  
  public String getFieldValue(String fieldCode) {
    Field paramField = getField(fieldCode);
    if (paramField != null) {
        return paramField.getValue();
    } else {
      return "";
    }
  }
  
  
  
  /**
   * ���������ύ��ť(mvcģʽ��������)
   * @param submitButton
   */
  public void setSubmitButton(ActionButton submitButton) {
    this.buttonList.remove(this.submitButton);
    this.submitButton = submitButton;
    if (this.submitButton != null) {
      this.submitButton.addStyleClass("searchButton");
      this.submitButton.bindForm(this.getName());
      this.regFormButton(submitButton);
    }
  }

  public void setQueryNavInfo(String navInfo,String preNavInfo) {
    if (this.submitButton != null) {
      this.submitButton.setAttribute(GaConstant.FIXPARAM_WINDOWNAV, navInfo);
      this.submitButton.setAttribute(GaConstant.FIXPARAM_PREWINDOWNAV, preNavInfo);
    }
    HiddenField field  = new HiddenField(GaConstant.FIXPARAM_WINDOWNAV,navInfo);
    this.add(field);    
    field  = new HiddenField(GaConstant.FIXPARAM_PREWINDOWNAV,preNavInfo);
    this.add(field);
  }
  
  public void regFormButton(ActionButton button) {
    this.buttonList.add(button);
  }
    
  @Override
  public void render(HtmlStringBuffer buffer) {
    if (this.getControls() != null && this.getControls().size() > 0) {
      List<Field> formFields = ContainerUtils.getInputFields(this);
      renderHeader(buffer, formFields);
      //��Ⱦ��ͨ�ؼ�
      renderFields(buffer);
      //������Ⱦ
      buffer.elementEnd(getTag());
      buffer.append("\n");
    }
      //renderTagEnd(formFields, buffer);
  }
    
  /**
   * ��ȾFormͷ�������ؿؼ�
   */
  protected void renderHeader(HtmlStringBuffer buffer, List<Field> formFields) {
    buffer.elementStart(getTag());
//    buffer.appendAttribute("onkeypress","getEnter();");
    buffer.appendAttribute("method", "post");
    buffer.appendAttribute("name", getName());
    buffer.appendAttribute("id", getId());
    buffer.appendAttribute("action", getActionURL());
    buffer.appendAttribute("enctype", getEnctype());
    if (this.isHidden) {
      buffer.appendAttribute("style", "display:none;");
    }
//    if (NoahUtil.isNullStr(this.onSubmitJS)) {
//      buffer.appendAttribute("onsubmit", "return navTabSearch(this);"); 
//    } else {
//      buffer.appendAttribute("onsubmit", this.onSubmitJS);
//    } 
    buffer.closeTag();
    buffer.append("\n");
    // Render hidden fields
    for (Field field : ContainerUtils.getHiddenFields(this)) {
        field.render(buffer);
        buffer.append("\n");
    }
  }
  
  /**
   * ��Ⱦ����DIV����
   */
  protected void renderFields(HtmlStringBuffer buffer) {
    // If Form contains only the FORM_NAME HiddenField, exit early
    if (getControls().size() == 1) {
        if (getControlMap().containsKey(FORM_NAME)) {
            return;
        } else {
            Map<String, Field> fieldMap = ContainerUtils.getFieldMap(this);
            if (fieldMap.containsKey(FORM_NAME)) {
                return;
            }
        }
    }
    if (this.queryRows > 1) {
      buffer.append("<div class=\"searchBar\" style=\"height:").append(this.queryRows * 25).append("px\">\n");
    } else {
      buffer.append("<div class=\"searchBar\">\n");
    }
    renderControls(buffer, this, getControls(), getFieldWidths(), getColumns());
    buffer.append("</div>\n");
    //��Ⱦ��ť
    renderButton(buffer);
  }
  
  /**
   * ��Ⱦ����ؼ�
   */
  protected void renderControls(HtmlStringBuffer buffer, Container container,
      List<Control> controls, Map<String, Integer> fieldWidths, int columns) {
    if (this.queryRows > 1) {
      buffer.append("<ul class=\"searchContent\" style=\"height:").append(this.queryRows * 25).append("px\">\n");
    } else {
      buffer.append("<ul class=\"searchContent\">");
    }
    for (Control control : controls) {
      if (control instanceof Field) {
        if (!((Field)control).isHidden()) {
          //��Ⱦ�������������ֶ� 
          if (control instanceof Label) {
            renderLabel(control,buffer);
          } else {
            renderField(control,buffer);
            autoHideButton = false;
          }
        }
      }
    }
    //��Ⱦtip��Ϣ
    if (!GaUtil.isNullStr(this.tipInfo)) {
      buffer.append("<li><Label>");
      buffer.append(this.tipInfo);
      buffer.append("</Label></li>\n");
    }
    buffer.append("</ul>");
  }
  
  /**
   * ��Ⱦ��ť��
   */
  protected void renderButton(HtmlStringBuffer buffer)  {
    if (this.autoHideButton) {
      buffer.append("<div class=\"subBar\" style=\"display:none\">\n");
    } else {
      buffer.append("<div class=\"subBar\">\n");
    }
    buffer.append("  <ul style=\"float:right;\">\n");
    buffer.append("    <li><div class=\"buttonActive\"><div class=\"buttonContent\">");
    for (ActionButton button : this.buttonList) {
      button.render(buffer);
    }
    buffer.append("</div></div></li>\n");
    //buffer.append("    <li><a class=\"button\" href=\"javascript:void(0)\" target=\"dialog\" mask=\"true\" title=\"��ѯ��\"><span>�߼�����</span></a></li>\n");
    buffer.append("  </ul>\n");
    buffer.append("</div>\n");
  }
  /**
   * ��Ⱦlabel�ؼ�
   * @param control
   * @param buffer
   */
  private void renderLabel(Control control,HtmlStringBuffer buffer) {
    Label label = (Label) control;
    buffer.append("<li><label>");
    label.render(buffer);
    buffer.append("</label></li>\n");
  }

  /**
   * ��Ⱦ��������ؼ�
   * @param control
   * @param buffer
   */
  private void renderField(Control control,HtmlStringBuffer buffer) {
    Field field = (Field) control;
    buffer.append("<li><label>");
    buffer.append(field.getLabel());
    buffer.append("</label>");
    field.render(buffer);
    buffer.append("</li>\n");
  }
  
  @Override
  public String getTag() {
    // TODO Auto-generated method stub
    return "Form";
  }

  public void setHidden(boolean isHidden) {
    this.isHidden = isHidden;
  }

  
  
  public boolean isHidden() {
    return isHidden;
  }

  public ActionButton getSubmitButton() {
    return submitButton;
  }
  
  public String getTipInfo() {
    return tipInfo;
  }

  public void setTipInfo(String tipInfo) {
    this.tipInfo = tipInfo;
  }

  public void setQueryRows(int rows) {
    this.queryRows = rows;
  }
  
  public int getQueryRows() {
    return queryRows;
  }
} 
  class CompareQueryField implements Comparator{
    public int compare(Object arg0, Object arg1) {
      DbField field1=(DbField)arg0;
      DbField field2=(DbField)arg1;
      //���ȱȽ����䣬���������ͬ����Ƚ�����
      if (field1.getQueryOrder() < field2.getQueryOrder()) {
        return -1;
      } else if (field1.getQueryOrder() > field2.getQueryOrder()) { 
        return 1;
      }
      return 0;
  }
}
