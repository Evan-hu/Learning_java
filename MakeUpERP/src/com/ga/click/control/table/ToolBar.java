package com.ga.click.control.table;

import java.util.ArrayList;
import java.util.List;
import org.apache.click.control.AbstractControl;
import org.apache.click.util.HtmlStringBuffer;
import com.ga.click.control.button.ActionButton;

public class ToolBar extends AbstractControl {
	
  private static final long serialVersionUID = 1L; 
  protected List<ActionButton> toolbarList = new ArrayList<ActionButton>();
  
  public ToolBar(String name,List<ActionButton> toolbarList) {
    this.setName(name);
    this.toolbarList = toolbarList;
  }
  
  public ToolBar(String name) {
    this.setName(name);
  }
  
  public void regToolBar(ActionButton button) {
    this.toolbarList.add(button);
  }
  
  @Override
  public void render(HtmlStringBuffer buffer) {
    boolean isHidden = true;
    if (this.toolbarList != null) {
      for(ActionButton action : this.toolbarList) {
        if (!action.isHidden()) {
           isHidden = false;
           break;
        }
      }
    }  
    buffer.elementStart(getTag());
    buffer.append(" style=\"border-width:1px;\"");
    buffer.appendAttribute("class","panelBar");
    if (isHidden) {
      buffer.appendAttribute("style","display:none;");
    }
    buffer.closeTag();
    buffer.append("\n");
    if (this.toolbarList != null) {
      buffer.append("  <ul class=\"toolBar\">\n");
      for(ActionButton action : this.toolbarList) {
        buffer.append("    <li>\n");
        action.render(buffer);
        buffer.append("    </li>\n");
      }
      buffer.append("  </ul>\n");
    }
    //Ω· ¯‰÷»æ
    buffer.elementEnd(getTag());
    buffer.append("\n");
  }
  
  
  
  @Override
  public boolean onProcess() {
    // TODO Auto-generated method stub
    for(ActionButton action : this.toolbarList) {
       action.onProcess();
    }
    return super.onProcess();
  }

  public String getTag() {
    // TODO Auto-generated method stub
    return "div";
  }
}

