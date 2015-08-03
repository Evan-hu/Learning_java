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
   * ע�����¼�
   * @param clickUrl �������
   * @param eventMethod �󶨵ĵ�������¼�
   * @param page ��ǰҳ��
   * @return
   */
  public ActionButton regClickEvent(String clickUrl,String eventMethod,UnitPage page) {
    try { 
      //�༭��չ�����¼�
      ActionButton action = new ActionButton(this.getClass(),this.viewID,"",clickUrl,null);
      this.regAction(action); //ע�ᵽ��ͼ
      page.regPageEvent(action,eventMethod);
      return action;
    }
    catch (BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"ע��������¼�ʧ��");
    }
  }
}
