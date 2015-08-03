package com.ga.click.layout.struct;

import org.apache.click.Control;
import org.apache.click.util.HtmlStringBuffer;
import com.ga.click.control.tree.TreeControl;
import com.ga.click.exception.BizException;

public class DivStruct extends BaseStruct {
  private String divID = "";
  private Control divControl;

  
  public DivStruct(String divID,Control control) {
    this.divID = divID;
    this.divControl = control;
  }

  
  public void render(HtmlStringBuffer buff)  {
    if (divControl instanceof TreeControl) {
      //树形控件较为特殊，通常是左右布局，需要在容器控制自动高度
      int layoutH = ((TreeControl)divControl).getLayoutH();
      if (layoutH > -1) {
        buff.append("    <div  id=\"").append(divID).append("\" layoutH=\"").append(layoutH).append("\">");
      } else {
        buff.append("    <div  id=\"").append(divID).append("\">");
      }
    } else {
      buff.append("    <div  id=\"").append(divID).append("\">");
    }
    divControl.render(buff);
    buff.append("    </div>");
  }


  @Override
  public void addSubStruct(BaseStruct struct) {
    // TODO Auto-generated method stub
    throw new BizException(BizException.SYSTEM,"div不能添加子结构");
  } 
  
  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return this.divID;
  } 
}
