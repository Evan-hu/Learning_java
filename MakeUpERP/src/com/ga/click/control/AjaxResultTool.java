package com.ga.click.control;

import org.apache.click.ActionResult;
import org.json.JSONObject;
import com.ga.click.control.table.QueryTable;
import com.ga.click.control.tree.TreeControl;

public class AjaxResultTool {
  /**
   * ����ajax���÷�����Ϣ
   * @param sucess �Ƿ�ɹ�
   * @param infoMsg ��Ҫ��ʾ����Ϣ,Ϊ�ջ�""����ʾ
   * @param isCloseDialog �Ƿ�رյ�ǰ�Ի���
   * @param reloadTable ��Ҫˢ�µ��б�ؼ�
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
      //����table�ؼ���ˢ��(�в�ѯ��ť��ִ�в�ѯ)

    } catch(Exception ex) {
      obj.optJSONObject("{\"success\":\"0\", \"message\":\"�������쳣\"}");
    }
    return new ActionResult(obj.toString(),ActionResult.JSON);
  }
  
  /**
   * ����ajax���÷�����Ϣ
   * @param sucess �Ƿ�ɹ�
   * @param errMsg ��Ҫ��ʾ�Ĵ�����Ϣ
   * @param isCloseDialog �Ƿ�رյ�ǰ�ؼ�
   * @param reloadTree ��Ҫˢ�µ����ؼ�
   * @return
   */
  public static String createAjaxResult(boolean sucess,String errMsg,boolean isCloseDialog,TreeControl reloadTree) {
    return null;
  }
  
  
  /**
   * ����ajax���÷�����Ϣ
   * @param sucess �Ƿ�ɹ�
   * @param errMsg ��Ҫ��ʾ�Ĵ�����Ϣ
   * @param isCloseDialog �Ƿ�رյ�ǰ�ؼ�
   * @param reloadTree ��Ҫˢ�µ����ؼ�
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
      json.optJSONObject("{\"success\":\"0\", \"message\":\"�������쳣\"}");
    }
    return new ActionResult(json.toString(),ActionResult.JSON);
  }
}
