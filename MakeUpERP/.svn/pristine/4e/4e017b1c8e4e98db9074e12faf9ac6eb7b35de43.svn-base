package com.ga.click.layout;

import org.apache.click.util.HtmlStringBuffer;
import com.ga.click.exception.BizException;

public abstract class AbstractLayout implements Layout{
	
  protected int layoutH = -1;
  
  @Override
  public String toString() {
     try {
      HtmlStringBuffer buffer = new HtmlStringBuffer(100);
      this.render(buffer);
      return buffer.toString();
     } catch(BizException e) {
       if (e.getMessage().startsWith("导出完成:")) {
         return e.getMessage().replace("导出完成:", "");
       } else {
         throw e;
       }
     }
  }
  
  public void render(HtmlStringBuffer buff) {
    this.renderMain(buff);
    this.renderButton(buff);
  }
  
  public void setLayoutH(int layoutH) {
    this.layoutH = layoutH;
  }

  public int getLayoutH() {
    return layoutH;
  }
  
}
