package com.ga.click.page;

import org.apache.click.Control;
import org.apache.click.Page;
import com.ga.click.control.button.ActionButton;
import com.ga.click.layout.Layout;

public abstract class UnitPage extends Page {
  
  public void regPageAction(ActionButton pageAction) {
    
  }
  
  public void regControl(Control control) {
    
  }
  
  
  public void loadDiv() {
    
  }

  /**
   * 初始化布局
   * @return 返回页面布局对象(如不需要页面布局返回空)
   */
  public abstract Layout initLayout();
  
  /**
   * 初始化业务构件
   */
  public abstract void initUnit();
  
  public abstract void regControl();
  
  
}
