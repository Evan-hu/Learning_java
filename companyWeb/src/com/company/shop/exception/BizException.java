package com.company.shop.exception;

/**
 * 业务异常,当需要给用户展示友好的信息时,抛出该异�?
 */
public class BizException extends RuntimeException{
  
  /**
   * 给用户显示友好的异常信息.
   * 该异常不会记录入日志.
   * @param message 显示给用户的异常信息.
   */
  public BizException(String message) {
    super(message);
  }
  
  /**
   * 给用户显示友好的异常信息.
   * 同时�?��传入�?��相关的异�?
   * 传入的异常不会用于显�?系统会自动将其录入日�?
   * @param message 显示给用户的异常信息.
   * @param cause 相关的异�?
   */
  public BizException(String message, Throwable cause) {
    super(message, cause);
  }
  
}
