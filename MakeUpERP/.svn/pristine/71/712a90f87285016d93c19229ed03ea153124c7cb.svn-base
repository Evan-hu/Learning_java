package com.ga.click.layout;

import org.apache.click.util.HtmlStringBuffer;
import com.ga.click.control.table.QueryTable;

public class ListLayout extends AbstractLayout{
	
  private QueryTable queryTable;
  
  public ListLayout(QueryTable queryTable) {
    this.queryTable = queryTable;
    this.layoutH = 61;
  }
  
  public int autoLayoutH() {
    int autoH = 90;
    if (!this.queryTable.havePage()) {
      //无分页
      autoH = autoH - 24;
    }
    if (!this.queryTable.haveToolbar()) {
      //无工具条
      autoH = autoH - 24;
    }
    if (!this.queryTable.haveQuery()) {
      //无查询
      autoH = autoH - 38;
    } else {
      if (queryTable.getQueryForm().getQueryRows() > 1) {
        int height = (queryTable.getQueryForm().getQueryRows() - 1) * 25;
        autoH = autoH + height;
      }
    }
    return autoH;
  }
  
  public void render(HtmlStringBuffer buffer) {
    this.renderMain(buffer);
  }

  public void renderMain(HtmlStringBuffer buffer) {   
    buffer.append("<div id=\"").append(this.queryTable.getName()).append("\">\r\n");
    if (queryTable.getQueryForm() == null) {
      queryTable.setQueryForm();
      queryTable.getQueryForm().setHidden(true);
    }
      //渲染查询表单
    buffer.append("<div class=\"pageHeader\"");
    if (queryTable.getQueryForm().isHidden()) {
      buffer.append("style=\"display:none;\"");      
    } else {
      if (queryTable.getQueryForm().getQueryRows() > 1) {
        int height = queryTable.getQueryForm().getQueryRows() * 25;
        buffer.append("style=\"height:").append(height).append("px;\"");
      }
    }
    buffer.append(">");
    queryTable.getQueryForm().render(buffer);
    buffer.append("</div>");
    buffer.append("<div >");
    this.renderButton(buffer);  
    if (this.layoutH == 0) {
      buffer.append("<div style=\"overflow: auto;\" layoutH=\""+this.autoLayoutH()+"\">\r\n");            
    } else if (this.layoutH > 0) {
      buffer.append("<div style=\"overflow: auto;\" layoutH=\""+this.layoutH+"\">\r\n");            
    }
    queryTable.getListTable().render(buffer);
    if (this.layoutH >-1) {
      buffer.append("</div>\r\n");
    }

    //渲染分页
    if (queryTable.getListPagination()!= null) {      
      queryTable.getListPagination().render(buffer);
    }
    buffer.append("</div>\r\n");
    buffer.append("</div>");
  }
  
  public void renderButton(HtmlStringBuffer buff) {
    if (queryTable.getToolBar() != null) {      
      queryTable.getToolBar().render(buff);
    }
  }
}
