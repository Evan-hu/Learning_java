package com.ga.erp.biz;

import org.apache.click.Page;
import com.ga.click.exception.BizException;

public class MainPage extends Page {
  private static final long serialVersionUID = 1L;
  
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
