package com.ga.click.layout.struct;

import java.util.ArrayList;
import java.util.List;
import org.apache.click.util.HtmlStringBuffer;
import com.ga.click.util.GaUtil;

public class PanelStruct extends BaseStruct{
  private String panelName = "";
  private List<BaseStruct> structList = new ArrayList<BaseStruct>() ; 
  private String panelID = "";;
  
  public PanelStruct(String panelName) {
    this.panelName = panelName;
    this.panelID = panelName;
  }
  
  public void render(HtmlStringBuffer buff)  {
    //判断是否子tab

    if (!GaUtil.isNullStr(panelName)) {
      if (this.isHidden()) {
        buff.append("  <div id=\"").append(panelID).append("\" class=\"panel\" style=\"display:none;margin-top: 3px;\">\r\n");
      } else {
        buff.append("  <div id=\"").append(panelID).append("\" class=\"panel\" style=\"margin-top: 3px;\">\r\n");
      }
      buff.append("   <h1>").append(panelName).append("</h1>\r\n");
      //输出内容div
      for(BaseStruct struct: structList) {
       struct.render(buff); 
      }
      buff.append("  </div>\r\n");
    } else {
      //输出内容div
      if (this.isHidden()) {
        buff.append("  <div id=\"").append(panelID).append(" style=\"display:none;padding: 5px 5px 1px;\">\r\n");
      } else {
        buff.append("  <div  style=\"padding: 5px 5px 1px;\">\r\n");
      }
      for(BaseStruct struct: structList) {
       struct.render(buff); 
      }
      buff.append("</div>");
    }

  } 
  
  @Override
  public void addSubStruct(BaseStruct struct) {
    // TODO Auto-generated method stub
    this.structList.add(struct);
  } 
  
  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return this.panelName;
  } 
}
