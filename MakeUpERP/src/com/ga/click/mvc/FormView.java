package com.ga.click.mvc;

import java.util.HashMap;
import java.util.Map;
import org.apache.click.control.HiddenField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;

public class FormView  extends View{
  public FormView(String viewID,ModelMap modelMap) {
    super(viewID,GaConstant.VIEWTYPE_FORM,modelMap);
  }
  /**
   * 注册默认编辑保存按钮(默认保存方法入参是DATA_MAP
   * @param actionID 按钮ID
   * @param saveMethod 保存事件
   * @param page 页面
   */
  public ActionButton regEditSaveEvent(String actionID,String saveMethod,UnitPage page,boolean autoHidden) {
    try {
      if (!autoHidden || this.viewEditMode ==  GaConstant.EDITMODE_EDIT) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put(GaConstant.FIXPARAM_EDITMODE, GaConstant.EDITMODE_EDIT);
        ActionButton action = new ActionButton(this.getClass(),actionID,"保存",page.getSelfUrl(),paramMap);
        action.bindForm(this.viewID);
        SubmitTool.submitMvc(action,page.getModelMap().getPreNavInfoStr());
        PageEvent event  = page.regPageEvent(action,saveMethod);
        event.addEventParam(this,PageEvent.PARAMTYPE_DATAMAP);
        this.regAction(action);    
        return action;
      } else {
        return null;
      }
    }
    catch (BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"注册保存按钮失败");
    }
  }
  
  /**
   * 注册默认编辑保存按钮(默认保存方法入参是DATA_MAP
   * @param actionID 按钮ID
   * @param saveMethod 保存事件
   * @param page 页面
   */
  public ActionButton regAddSaveEvent(String actionID,String saveMethod,UnitPage page,boolean autoHidden) {
    try {
      if (!autoHidden || this.viewEditMode ==  GaConstant.EDITMODE_NEW) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put(GaConstant.FIXPARAM_EDITMODE, GaConstant.EDITMODE_NEW);
        ActionButton action = new ActionButton(this.getClass(),actionID,"保存",page.getSelfUrl(),paramMap);
        action.bindForm(this.viewID);
        SubmitTool.submitMvc(action,page.getModelMap().getPreNavInfoStr());
        PageEvent event  = page.regPageEvent(action,saveMethod);
        event.addEventParam(this,PageEvent.PARAMTYPE_DATAMAP);
        this.regAction(action);  
        return action;
      } else {
        return null;
      }
    }
    catch (BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"注册新建保存按钮失败");
    }
  }
  
  /**
   * 注册新建事件(只打开新窗口)
   * @param actionID 按钮ID
   * @param actionTitle 按钮标题
   * @param winUrl 新窗口URL
   * @param page 当前页面
   * @return 新建按钮
   */
  public ActionButton regAddEventByOpenDialog(String actionID,String actionTitle,String winUrl,UnitPage page) {
    try {
      Map<String,Object> paramMap = new HashMap<String,Object>();
      paramMap.put(GaConstant.FIXPARAM_EDITMODE, GaConstant.EDITMODE_NEW);
      ActionButton action = new ActionButton(this.getClass(),actionID,actionTitle,winUrl,paramMap);
      SubmitTool.submitToDialog(action, actionID,actionTitle,600, 410,page.getModelMap().getPreNavInfoStr());
      page.regInitView(this); //需要初始化参数相关的视图
      this.regAction(action);  
      return action;
    }
    catch (BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"注册新建打开按钮失败");
    }
  }
  
  /**
   * 新增参数
   * @param name
   * @param value
   */
  public void addHiddenParam(String name,Object value) {
    if (this.viewControl == null) {
      throw new BizException(BizException.SYSTEM, "控件未创建,无法添加隐藏字段,请尝试在绑定值后添加字段");
    }
    HiddenField field = new HiddenField(name,value);
    this.getDataForm().add(field);
  }
}
