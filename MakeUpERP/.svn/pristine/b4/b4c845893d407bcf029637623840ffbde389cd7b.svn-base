package com.ga.click.exception;

/**
 * 业务异常,当需要给用户展示友好的信息时,抛出该异常.
 * 可以指定异常页面的导航.
 */
public class BizException extends RuntimeException{
  
  private int code;
  private String navType = "";
  private boolean ajaxRequest = true;  //是否ajax请求,不同请求的异常处理模式有区别,ajax:返回json,其他:重定向到错误显示页面.
  private String callBackType = "";

  public static final int AJAXEND = 0;    //AJAX结束信息(对于ajax的提示信息只能以异常方式中断处理流程)
  public static final int SYSTEM = -1;    //系统异常,不能直接抛给用户
  public static final int DB = -2;        //数据库连接异常
  public static final int COMMBIZ = -3;    //普通业务异常,需要显示信息给用户 
  //public static final int CHECKBIZ = -4;    //验证异常
  //public static final int SPECIFIC_COMMBIZ = -5;    //特殊业务异常，该异常指定异常发生后跳转到哪个页面
  
  
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
