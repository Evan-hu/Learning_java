package com.ga.click.mvc;

import com.ga.click.control.GaConstant;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;

public class TreeView extends View{
  
  public TreeView(String viewID,ModelMap modelMap) {
    super(viewID,GaConstant.VIEWTYPE_TREE,modelMap);
    this.viewEditMode = GaConstant.EDITMODE_DISP;
  }
  
  /**
   * 注册点击事件
   * @param clickUrl 点击链接
   * @param eventMethod 绑定的点击方法事件
   * @param page 当前页面
   * @return
   */
  public ActionButton regClickEvent(String clickUrl,String eventMethod,UnitPage page) {
    try { 
      //编辑扩展属性事件
      ActionButton action = new ActionButton(this.getClass(),this.viewID,"",clickUrl,null);
      this.regAction(action); //注册到视图
      page.regPageEvent(action,eventMethod);
      return action;
    }
    catch (BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"注册树点击事件失败");
    }
  }
}
