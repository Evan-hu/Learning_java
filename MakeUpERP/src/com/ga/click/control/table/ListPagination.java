package com.ga.click.control.table;

import org.apache.click.control.AbstractControl;
import org.apache.click.util.HtmlStringBuffer;
import com.ga.click.control.GaConstant;
import com.ga.click.dbutil.PageParam;
import com.ga.click.util.GaUtil;

public class ListPagination extends AbstractControl {
	
  private static final long serialVersionUID = 1L; 
  private PageParam pageInfo;
  private String targetType = "navTab" ;
  private String divRel = "" ;

  public ListPagination() {
    this.setName("paginate"); 
  }
  
  public void setPageInfo(PageParam pageInfo) { 
    this.pageInfo = pageInfo;
  }
  
  public void setNavInfo(String navType,String navID,String preNavInfo) {
    if (navType.equals(GaConstant.NAVTYPE_DIV)) {
      divRel = navID;
    }    
    if (!GaUtil.isNullStr(preNavInfo)) {
      String[] navInfoList = preNavInfo.split(",");
      targetType = navInfoList[0];
    }    
  }
  
  @Override
  public void render(HtmlStringBuffer buffer) {
    if (this.pageInfo != null) {
      buffer.elementStart(getTag());
      buffer.appendAttribute("class","panelBar");
      buffer.closeTag();
      buffer.append("\n");
      buffer.append("<div class=\"pages\">\n");
      buffer.append("  <span>显示</span>\n");
      buffer.append("   <select class=\"combox\" name=\"numPerPage\"");
      if (targetType.equals(GaConstant.NAVTYPE_DIALOG)) {
        if (!GaUtil.isNullStr(divRel)) {    
          buffer.append(" onchange=\"dialogPageBreak({numPerPage:this.value},'").append(divRel).append("\')\">\n");
        } else {
          buffer.append(" onchange=\"dialogPageBreak({numPerPage:this.value})\">\n");
        }        
      } else {
        if (!GaUtil.isNullStr(divRel)) {    
          buffer.append(" onchange=\"navTabPageBreak({numPerPage:this.value},'").append(divRel).append("')\">\n");
        } else {
          buffer.append(" onchange=\"navTabPageBreak({numPerPage:this.value})\">\n");
        }
      }
      if (this.pageInfo.getPageSize() == 10) {
        buffer.append("     <option value=\"10\" selected>10</option>\n");
      } else {
        buffer.append("     <option value=\"10\">10</option>\n");
      }
      if (this.pageInfo.getPageSize() == 20) {
        buffer.append("     <option value=\"20\" selected>20</option>\n");
      } else {
        buffer.append("     <option value=\"20\">20</option>\n");
      }
      if (this.pageInfo.getPageSize() == 50) {
        buffer.append("     <option value=\"50\" selected>50</option>\n");
      } else {
        buffer.append("     <option value=\"50\">50</option>\n");
      }
      if (this.pageInfo.getPageSize() == 100) {
        buffer.append("     <option value=\"100\" selected>100</option>\n");
      } else {
        buffer.append("     <option value=\"100\">100</option>\n");
      }
      buffer.append("   </select>\n");
      buffer.append("  <span>条，共").append(this.pageInfo.getRowCount()).append("条</span>\n");      
      buffer.append("</div>\n");
      buffer.append("<div class=\"pagination\" totalCount=\"").append(this.pageInfo.getRowCount())
          .append("\" targetType=\"").append(this.targetType)
          .append("\" rel=\"").append(this.divRel)
          .append("\" numPerPage=\"").append(this.pageInfo.getPageSize())
          .append("\" pageNumShown=\"").append(10)
          .append("\" currentPage=\"").append(this.pageInfo.getPageNumber())
          .append("\"></div>\n");
      //结束渲染
      buffer.elementEnd(getTag());
      buffer.append("\n");
    }
      //renderTagEnd(formFields, buffer);
  }
  
  public String getTag() {
    // TODO Auto-generated method stub
    return "div";
  }
}
