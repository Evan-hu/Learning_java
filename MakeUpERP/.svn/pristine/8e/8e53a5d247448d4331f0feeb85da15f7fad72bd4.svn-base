package com.ga.click.control;

import org.apache.click.ActionResult;
import org.json.JSONObject;
import com.ga.click.control.table.QueryTable;
import com.ga.click.control.tree.TreeControl;

public class AjaxResultTool {
  /**
   * 创建ajax调用返回信息
   * @param sucess 是否成功
   * @param infoMsg 需要提示的消息,为空或""则不提示
   * @param isCloseDialog 是否关闭当前对话框
   * @param reloadTable 需要刷新的列表控件
   * @return
   */
  public static ActionResult createAjaxResult(boolean sucess,String infoMsg,boolean isCloseDialog,QueryTable reloadTable) {
    JSONObject obj = new JSONObject();
    try {
      if (sucess) {
        obj.put("success", "1");
      } else {
        obj.put("success", "0");
      }
      obj.put("message", infoMsg);
      if (isCloseDialog) {
        obj.put("closedialog", "1");
      } else {
        obj.put("closedialog", "0");
      }
      //处理table控件的刷新(有查询按钮则执行查询)

    } catch(Exception ex) {
      obj.optJSONObject("{\"success\":\"0\", \"message\":\"处理返回异常\"}");
    }
    return new ActionResult(obj.toString(),ActionResult.JSON);
  }
  
  /**
   * 创建ajax调用返回信息
   * @param sucess 是否成功
   * @param errMsg 需要提示的错误消息
   * @param isCloseDialog 是否关闭当前控件
   * @param reloadTree 需要刷新的树控件
   * @return
   */
  public static String createAjaxResult(boolean sucess,String errMsg,boolean isCloseDialog,TreeControl reloadTree) {
    return null;
  }
  
  
  /**
   * 创建ajax调用返回信息
   * @param sucess 是否成功
   * @param errMsg 需要提示的错误消息
   * @param isCloseDialog 是否关闭当前控件
   * @param reloadTree 需要刷新的树控件
   * @return
   */
  public static String createAjaxResult(boolean sucess,String errMsg,boolean isCloseDialog,String reloadDiv) {
    return null;
  }
  
  public static ActionResult createErrorResult(String actionid,Exception biz) {
    JSONObject json = new JSONObject();
    try {
      if (biz != null) {            
        json.put("actionid", actionid);
        json.put("message", biz.getMessage());
        json.put("success","0");
      }
    } catch(Exception e) {
      json.optJSONObject("{\"success\":\"0\", \"message\":\"处理返回异常\"}");
    }
    return new ActionResult(json.toString(),ActionResult.JSON);
  }
}
