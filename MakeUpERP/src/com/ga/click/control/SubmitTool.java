package com.ga.click.control;

import org.apache.click.control.AbstractControl;

import com.ga.click.control.button.ActionButton;
import com.ga.click.control.table.QueryTable;
import com.ga.click.control.tree.TreeControl;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.View;

public class SubmitTool {
  
  /**
   * 刷新本窗口的提交模式(nav方式刷新主窗口,dialog刷新对话框,div刷新碎片)
   * @param form
   * @param windowNavType
   * @param windowNavID
   */
  public static void submitToWindow(AbstractControl control,String windowNavType,String windowNavID,String preNavInfo) {
    if (control == null) return;
    String navInfo = windowNavType+","+windowNavID;
    if (control instanceof QueryTable) {
      QueryTable table = (QueryTable) control;
      if (table.getQueryForm() != null) {
        table.getQueryForm().setQueryNavInfo(navInfo,preNavInfo);
      } 
      if (table.getListPagination() != null) {
        table.getListPagination().setNavInfo(windowNavType,windowNavID, preNavInfo);
      }
    } else if (control instanceof ActionButton || control instanceof TreeControl) {
      control.setAttribute(GaConstant.FIXPARAM_WINDOWNAV,navInfo);
      control.setAttribute(GaConstant.FIXPARAM_PREWINDOWNAV,preNavInfo);
    }
  }
  
  /**
   * 设置控件触发div刷新操作
   * @param control 控件
   * @param divID 刷新的divID,多个divID用"|"分隔
   * @param preNavInfo 
   */
  public static void submitToDiv(AbstractControl control,String divID,String preNavInfo) {
    if (control == null) return;
    String navInfo = GaConstant.NAVTYPE_DIV + "," + divID;
    if (control instanceof QueryTable) {
      QueryTable table = (QueryTable) control;
      if (table.getQueryForm() != null) {
        table.getQueryForm().setQueryNavInfo(navInfo,preNavInfo);
        table.getQueryForm().setAttribute(GaConstant.FIXPARAM_DIVMODE, divID);
      } 
      if (table.getListPagination() != null) {
        table.getListPagination().setNavInfo(GaConstant.NAVTYPE_DIV,divID, preNavInfo);         
      }
    } else if (control instanceof ActionButton || control instanceof TreeControl) {
      control.setAttribute(GaConstant.FIXPARAM_DIVMODE, divID);
      control.setAttribute(GaConstant.FIXPARAM_WINDOWNAV,navInfo);
      control.setAttribute(GaConstant.FIXPARAM_PREWINDOWNAV,preNavInfo);
    }
  }
  
  public static void submitToParentTable(ActionButton button,String preNavInfo,String pViewID) {
	  button.setAttribute(GaConstant.FIXPARAM_PARENTTABLE,pViewID+"List"); //指向listview的table
	  button.setAttribute(GaConstant.FIXPARAM_PREWINDOWNAV,preNavInfo);
  }
  
  /**
   * 设置控件为AJAX提交
   * @param control 控件
   * @param ajaxMethod ajax方法
   * @param preNavInfo
   */
  public static void submitToAjax(AbstractControl control,String ajaxMethod,String divID,String preNavInfo) {
    if (control == null) return;
    String navInfo = GaConstant.NAVTYPE_AJAX + "," + divID;
    if (control instanceof ActionButton || control instanceof TreeControl) {
      ((ActionButton)control).bindAjaxMethod(ajaxMethod);
      control.setAttribute(GaConstant.FIXPARAM_DIVMODE, divID);
      control.setAttribute(GaConstant.FIXPARAM_WINDOWNAV,navInfo);
      control.setAttribute(GaConstant.FIXPARAM_PREWINDOWNAV,preNavInfo);
    }
  }
  
  /**
   * 设置控件提交到主窗口
   * @param control 控件
   * @param title 主窗口标题
   * @param preNavInfo 
   */
  public static void submitToNavtab(AbstractControl control,String title,String preNavInfo) {
    if (control == null) return;
    String navInfo = GaConstant.NAVTYPE_TAB + ",main," + title;
    if (control instanceof QueryTable) {
      QueryTable table = (QueryTable) control;
      if (table.getQueryForm() != null) {
        table.getQueryForm().setQueryNavInfo(navInfo,preNavInfo);
      } 
      if (table.getListPagination() != null) {
        table.getListPagination().setNavInfo(GaConstant.NAVTYPE_TAB,"main", preNavInfo);          
      }
    } else if (control instanceof ActionButton || control instanceof TreeControl) {
      control.setAttribute(GaConstant.FIXPARAM_WINDOWNAV,navInfo);
      control.setAttribute(GaConstant.FIXPARAM_PREWINDOWNAV,preNavInfo);
    }
  }
  
  /**
   * 设置控件提交到dialog方式
   * @param control 控件
   * @param dialogID dialogID
   * @param title 对话框标题
   * @param width
   * @param height
   * @param preNavInfo
   */
  public static void submitToDialog(AbstractControl control,String dialogID,String title,int width,int height,String preNavInfo) {
    if (control == null) return;
    String navInfo = GaConstant.NAVTYPE_DIALOG + ","+dialogID+"," + title+","+width+","+height;
    if (control instanceof QueryTable) {
      QueryTable table = (QueryTable) control;
      if (table.getQueryForm() != null) {
        table.getQueryForm().setQueryNavInfo(navInfo,preNavInfo);
      }
      if (table.getListPagination() != null) {
        table.getListPagination().setNavInfo(GaConstant.NAVTYPE_DIALOG,dialogID, preNavInfo);
        
      }
    } else if (control instanceof ActionButton || control instanceof TreeControl) {
      control.setAttribute(GaConstant.FIXPARAM_WINDOWNAV,navInfo);
      control.setAttribute(GaConstant.FIXPARAM_PREWINDOWNAV,preNavInfo);
    }    
  }
  

  

//  /**
//   * 当前窗口刷新
//   * @param button
//   * @param modelMap
//   */
//  public static void submitMvcToSelf(ActionButton button,ModelMap modelMap) {
//    if (button == null) return;
//    String navInfo = modelMap.getWindowNavType()+","+modelMap.getWindowNavID();
//    button.setAttribute(NoahConstant.FIXPARAM_WINDOWNAV,navInfo);
//    button.setAttribute(NoahConstant.FIXPARAM_PREWINDOWNAV,modelMap.getPreNavInfoStr());
//    button.addParam(NoahConstant.FIXPARAM_ACTIONID,button.getId());
//    button.bindAjaxMethod("doController");
//  }
  
  /**
   * DIV局部刷新
   * @param button
   * @param modelMap
   */
  public static void submitMvc(ActionButton button,String preNavInfo,View...viewList) {
    submitMvc(button,preNavInfo,false,viewList);
  }
  
  /**
   * DIV局部刷新(可设置是否刷新页面区域)
   * @param button
   * @param preNavInfo
   * @param reloadPageButton
   * @param viewList
   */
  public static void submitMvc(ActionButton button,String preNavInfo,boolean reloadPageButton,View...viewList) {
    if (button == null) return;
    String navInfo = GaConstant.NAVTYPE_MVC;
    if (viewList == null || viewList.length == 0) {
      navInfo =  navInfo + ",0"; //不需要刷新
    } else {
      String divID = "";
      for (View view : viewList) {
        divID = divID + "|" + view.getDivID();
      }
      if (reloadPageButton) {
        divID = divID + "|pageButtonZone";
      }
      if (divID.startsWith("|")) divID = divID.substring(1);
      navInfo = navInfo + "," + divID;
    }
    button.setAttribute(GaConstant.FIXPARAM_WINDOWNAV,navInfo);
    button.setAttribute(GaConstant.FIXPARAM_PREWINDOWNAV,preNavInfo);
    button.addParam(GaConstant.FIXPARAM_ACTIONID,button.getId());
    button.bindAjaxMethod("doController");
  }
//  /**
//   * 打开新对话窗
//   * @param button
//   * @param dialogID
//   * @param title
//   * @param width
//   * @param height
//   * @param preNavInfo
//   */
//  public static void submitMvcToDialog(ActionButton button,String dialogID,String title,int width,int height,String preNavInfo) {
//    if (button == null) return;
//    String navInfo = NoahConstant.NAVTYPE_DIALOG + ","+dialogID+"," + title+","+width+","+height;
//    button.setAttribute(NoahConstant.FIXPARAM_WINDOWNAV,navInfo);
//    button.setAttribute(NoahConstant.FIXPARAM_PREWINDOWNAV,preNavInfo);
//    button.addParam(NoahConstant.FIXPARAM_ACTIONID,button.getId());
//    button.bindAjaxMethod("doController");
//  }
}
