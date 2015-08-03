package com.ga.click.demo;

import org.apache.click.Page;
import org.apache.click.util.Bindable;
import com.ga.click.control.tree.PanelTreeMenuControl;
import com.ga.click.control.tree.TopMenuControl;
import com.ga.click.exception.BizException;

public class MainPage extends Page {
  private static final long serialVersionUID = 1L;
  
  @Bindable
  private TopMenuControl topMenu;
  @Bindable
  private PanelTreeMenuControl panelTree;
  
  
  public MainPage() {
  }

  
  @Override
  public void onInit() {
    // TODO Auto-generated method stub
    Menu menu = new Menu();
    this.addControl(menu.getTopMenu());
    this.addControl(menu.getDefaultMenu());
  }



  @Override
  public void onRender() {
      try {
          super.onRender();
      } catch (BizException e) {
          throw e;
      } catch (Exception ex) {
          throw new BizException(BizException.SYSTEM, "‰÷»æ“≥√Ê ß∞‹", ex);
      }
  }
  
  @Override
  public String getTemplate() {
      return "/clicktemplate/main.htm";
  }
}
