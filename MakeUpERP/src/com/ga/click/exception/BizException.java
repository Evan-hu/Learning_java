package com.ga.click.exception;

/**
 * ҵ���쳣,����Ҫ���û�չʾ�Ѻõ���Ϣʱ,�׳����쳣.
 * ����ָ���쳣ҳ��ĵ���.
 */
public class BizException extends RuntimeException{
  
  private int code;
  private String navType = "";
  private boolean ajaxRequest = true;  //�Ƿ�ajax����,��ͬ������쳣����ģʽ������,ajax:����json,����:�ض��򵽴�����ʾҳ��.
  private String callBackType = "";

  public static final int AJAXEND = 0;    //AJAX������Ϣ(����ajax����ʾ��Ϣֻ�����쳣��ʽ�жϴ�������)
  public static final int SYSTEM = -1;    //ϵͳ�쳣,����ֱ���׸��û�
  public static final int DB = -2;        //���ݿ������쳣
  public static final int COMMBIZ = -3;    //��ͨҵ���쳣,��Ҫ��ʾ��Ϣ���û� 
  //public static final int CHECKBIZ = -4;    //��֤�쳣
  //public static final int SPECIFIC_COMMBIZ = -5;    //����ҵ���쳣�����쳣ָ���쳣��������ת���ĸ�ҳ��
  
  
  public BizException(String msg) {
    super(msg);
    this.code = COMMBIZ;
  }
  public BizException(int code, String msg) {
    super(msg);
    this.code = code;
  }
  public BizException(int code, String msg, String navType,String callbackType) {
    super(msg);
    this.code = code;
    if(null != navType && !"".equals(navType)){
      this.navType = navType;
    }else{
      this.navType = "";
    }
  }
  public BizException(int code, String msg,Exception ex) {
    super(msg);
    this.code = code;
  }
  public BizException(int code, String msg, String navType,String callbackType,Exception ex) {
    super(msg);
    this.code = code;
    if(null != navType && !"".equals(navType)){
      this.navType = navType;
    }else{
      this.navType = "";
    }
    this.callBackType = callbackType;
  }
  public int getCode() {
    return code;
  }
  public String getNavType() {
    return navType;
  }
  public boolean isAjaxRequest() {
    return ajaxRequest;
  }
  public void setAjaxRequest(boolean isAjaxRequest) {
    this.ajaxRequest = isAjaxRequest;
  }
  public String getCallBackType() {
    return callBackType;
  }
  
  public void setCallBackType(String funcName) {
    this.callBackType = funcName;
  }
  
}
