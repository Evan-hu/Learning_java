package com.ga.click.layout.struct;

import org.apache.click.util.HtmlStringBuffer;

public abstract class BaseStruct {
  protected int layoutH = -1;
  protected boolean hidden = false;
  public int setLayoutH(int layoutH) {
    this.layoutH = layoutH;
    return this.layoutH;
  }
  
  public int getLayoutH() {
    return this.layoutH;
  }
  
  
  
  public boolean isHidden() {
    return hidden;
  }

  public void setHidden(boolean isHidden) {
    this.hidden = isHidden;
  }

  public abstract String getName();
  
  public abstract void addSubStruct(BaseStruct struct);
  
  public abstract void render(HtmlStringBuffer buff);
}
