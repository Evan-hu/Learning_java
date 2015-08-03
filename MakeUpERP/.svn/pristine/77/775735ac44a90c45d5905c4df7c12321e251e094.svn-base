package com.ga.click.control.table;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import org.apache.click.Context;
import org.apache.click.control.Column;
import org.apache.click.control.Field;
import org.apache.click.util.HtmlStringBuffer;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.util.ClickUtil;
import com.ga.click.util.DataPropertyUtils;
import com.ga.click.util.GaUtil;

/**
 * 同DbField绑定的表格列对象
 *
 */
public class ListColumn extends Column  {

  private static final long serialVersionUID = 1L;

  private List<DbField> dbFieldList = null;
  private Map<String,DbField> allField = null;
  private DbField mainField = null;
  private int editMode = GaConstant.EDITMODE_DISP;
  private boolean isMTableMode = false;
  
  public ListColumn() {
    super();
  }

//  public ListColumn(String mainFieldCode,List<DbField> dbFieldList) {
//    this(NoahConstant.EDITMODE_DISP,mainFieldCode,dbFieldList);
//  }
  
  public ListColumn(int editMode,String mainFieldCode,List<DbField> dbFieldList,Map<String,DbField> allField) {
    this.editMode = editMode;
    DbField mainField = null;
    for(DbField field : dbFieldList) {
       if (field.getFieldCode().equals(mainFieldCode)) {
         mainField = field;
         break;
       }
    }
    this.setName(mainField.getFieldCode(false));
    this.dbFieldList = dbFieldList;
    this.mainField = mainField;
    this.allField = allField;
  }
  
  /**
   * 渲染单元格容器 (td)
   * @param row
   * @param buffer
   * @param context
   * @param rowIndex
   * @param editParms
   * @param imgLinkUrl
   */
  public void renderTableData(Object row, HtmlStringBuffer buffer, int rowIndex,Context contex) {
    buffer.elementStart("td");
    buffer.appendAttribute("class", getDataClass());
    if (getTitleProperty() != null) {
      Object titleValue = null;
      if (row instanceof ResultSet) {
        titleValue = DataPropertyUtils.getValue(row, getTitleProperty(), methodCache);
      }
      else {
        titleValue = ClickUtil.getRowData(getTitleProperty(), row);
      }
      buffer.appendAttributeEscaped("title", titleValue);
    }
    if (hasAttributes()) {
      buffer.appendAttributes(getAttributes());
    }
    if (hasDataStyles()) {
      buffer.appendStyleAttributes(getDataStyles());
    }
    if (this.mainField != null && this.mainField.getColumnWidth() == -1) {
      buffer.append(" style=\"display:none\"");
    }
    buffer.closeTag();
    if (this.editMode != GaConstant.EDITMODE_DISP) {
      renderEditTableDataContent(row,buffer,rowIndex,contex);      
    } else {
      renderTableDataContent(row,buffer,rowIndex,contex);      
    }
    buffer.elementEnd("td");
    buffer.append("\n");
  }

  /**
   * 输出列数据
   * @param row
   * @param buffer
   * @param context
   * @param rowIndex
   * @param editParms
   */
  protected void renderTableDataContent(Object row, HtmlStringBuffer buffer,int rowIndex,Context contex) {
    //修饰渲染
    if (getDecorator() != null) {
      String value = getDecorator().render(row,contex);
      if (value != null) {
        buffer.append(value);
      }
      return;
    }
    for(DbField field : this.dbFieldList) {
      String value =  this.getFieldDisplayString(field, row);
      if (field.getColumnDecorator() != null) {
        if (field.getColumnDecorator().getLinkeFieldCode() != null) {
          for(String linkCode  : field.getColumnDecorator().getLinkeFieldCode()){ 
            DbField linkField = this.allField.get(linkCode);
            if (linkField != null) {
              String linkV = this.getFieldDisplayString(linkField, row);
              field.getColumnDecorator().setLinkFieldValue(linkCode, linkV,linkField);
            }
          }
        }
        value = field.getColumnDecorator().decorator(value);
        buffer.append(value);
      } else {
        if (getEscapeHtml()) {
          buffer.appendEscaped(value);
        } else {
          buffer.append(value);
        }
      }
      //输出隐藏域
      if (this.isMTableMode) {
      	Object columnValue = ClickUtil.getRowData(field.getFieldCode(false),row); 
      	String vStr = "<input type='hidden' name='items["+rowIndex+"]."+field.getFieldCode(false)+"' value='"+columnValue+"'";
      	if (field.isPK()) {
      		vStr = vStr + " pk='1' ";
      	}
      	vStr =vStr + " >";
      	 buffer.append(vStr);
      }
    }
  }
  
  
  
  public void setMTableMode(boolean isMTableMode) {
	this.isMTableMode = isMTableMode;
}

/**
   * 获取指定字段值(处理别名情况)并进行格式化处理
   * @param field
   * @param row
   * @return
   */
  private String getFieldDisplayString(DbField field,Object row) {
//    String fieldCode = "";
//    if (NoahUtil.isNullStr(field.getAliasCode())) {
//      fieldCode = field.getFieldCode();
//    } else {
//      fieldCode = field.getFieldCode().replace(field.getAliasCode()+".","");
//    }
    Object columnValue = ClickUtil.getRowData(field.getFieldCode(false),row); 
    String value =  ClickUtil.getDisplayValue(field,columnValue);
    return value;
  }
  
  /**
   * 
   * @param row
   * @param buffer
   * @param rowIndex
   * @param contex
   */
  protected void renderEditTableDataContent(Object row, HtmlStringBuffer buffer,int rowIndex,Context contex) {
    String fieldName = "";
    int fieldSize = 12;
    String fieldCode ="";
    Object columnValue = null;
    String value = "";
    
    for(DbField field : this.dbFieldList) {
      fieldCode = field.getFieldCode(false);
      if (field.getDataLength() > 0) {
        fieldSize = field.getDataLength();
      }
      columnValue = ClickUtil.getRowData(fieldCode,row); 
      value =  ClickUtil.getDisplayValue(field,columnValue);
          //处理图片列的显示
      if (field.getColumnDecorator() != null && field.getColumnDecorator().isImageDecorator()) {
        //如是图片显示则只显示，不提供输入处理
        value =  this.getFieldDisplayString(field, row);
        if (field.getColumnDecorator().getLinkeFieldCode() != null) {
          for(String linkCode  : field.getColumnDecorator().getLinkeFieldCode()){ 
            DbField linkField = this.allField.get(linkCode);
            if (linkField != null) {
              String linkV = this.getFieldDisplayString(linkField, row);
              field.getColumnDecorator().setLinkFieldValue(linkCode, linkV,linkField);
            }
          }
        }
        value = field.getColumnDecorator().decorator(value);
        buffer.append(value);
        continue;
      }
      fieldName = "items["+rowIndex+"]."+fieldCode;
      //根据设置创建控件
      int inputMode = field.getInputMode(editMode);
      if (inputMode == GaConstant.INPUTMODE_NORMAL) {        
        //有编辑控件设置
        switch (field.getInputType())
        {
        case GaConstant.INPUT_DATETIME:
          //创建日期输入框
          buffer.append("<input type=\"text\" name=\"").append(fieldName)
          .append("\" value=\"").append(value)
          .append("\" class=\"date\" format=\"").append(field.getFormatPattern())
          .append("\" size=\"").append(fieldSize)
          .append("\" />");
          buffer.append("<a class=\"inputDateButton\" href=\"javascript:void(0)\">选择</a>");
          break;
        case GaConstant.INPUT_SELECT:
          Field fieldControl = ClickUtil.createControl(field);
          fieldControl.setName(fieldName);
          fieldControl.setValueObject(columnValue);
          fieldControl.render(buffer);
          break;
        case GaConstant.INPUT_POPSELECT:
          //创建查找带回
          fieldName = "items["+rowIndex+"]."+field.getPopID()+"."+field.getPopBindField();
          String fieldGroup = "items["+rowIndex+"]."+field.getPopID();
            buffer.append("<input type=\"text\" name=\"").append(fieldName)
            .append("\" readonly=\"readonly\" value=\"").append(value)
            .append("\" size=\"").append(fieldSize);
            if (!GaUtil.isNullStr(field.getInputCustomStyle())) {
              String styleString = field.getInputCustomStyle();
              if (styleString.startsWith(";")) styleString = styleString.substring(1);
              buffer.append("\" style=\"").append(styleString);
            }
            buffer.append("\" />");
          if (!GaUtil.isNullStr(field.getPopPageUrl())) {
            String linkChar = "?";
            if (field.getPopPageUrl().indexOf("?") > 0) {
              linkChar = "&";
            }
            buffer.append("<a class=\"btnLook\"")
            .append(" id=\"").append(fieldGroup).append("Link")
            .append("\" href=\"").append(field.getPopPageUrl())
            .append(linkChar)
            .append("lookupMode=").append(field.getPopSelectFields())
            .append("&").append("poptarget=").append(fieldGroup).append("Link")
            .append("\" lookupGroup=\"").append(fieldGroup)
            .append("\" >").append("查找带回").append("</a>");  
          }       
           break;
        default:
          //创建输入框          
          buffer.append("<input type=\"text\" name=\"").append(fieldName)
          .append("\" value=\"").append(value)
          .append("\" size=\"").append(fieldSize);
          if (!GaUtil.isNullStr(field.getInputCustomStyle())) {
            String styleString = field.getInputCustomStyle();
            if (styleString.startsWith(";")) styleString = styleString.substring(1);
            buffer.append("\" style=\"").append(styleString);
          }
          buffer.append("\" />");
        }
      } else {
        //不允许编辑的输入框
        buffer.append("<input type=\"text\" name=\"").append(fieldName)
          .append("\" value=\"").append(value)
          .append("\" size=\"").append(fieldSize)
          .append("\" readonly=\"readonly ");
        if (!GaUtil.isNullStr(field.getInputCustomStyle())) {
          String styleString = field.getInputCustomStyle();
          if (styleString.startsWith(";")) styleString = styleString.substring(1);
          buffer.append("\" style=\"").append(styleString);
        }
        buffer.append("\" />");
      }
    }
  }

  /**
   * 设置所有字段
   * @param allField
   */
  public void setAllField(Map<String, DbField> allField) {
    this.allField = allField;
  }
}