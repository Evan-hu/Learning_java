package com.ga.click.layout;

import java.io.Serializable;
import org.apache.click.util.HtmlStringBuffer;

public interface Layout extends Serializable{
  public void render(HtmlStringBuffer buff);
  public void renderMain(HtmlStringBuffer buff);
  public void renderButton(HtmlStringBuffer buff);
  public void setLayoutH(int layoutH);
  public int getLayoutH();
}
