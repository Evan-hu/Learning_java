package com.ga.click.exception;

/**
 * 系统异常,当需要抛出一个异常来中断程序执行, 但并不希望让用户看到错误信息,
 * 则抛出该异常.
 */
public class SysException extends RuntimeException{
  
  private static final long serialVersionUID = -5364830575742964581L;

  /**
   * 包含说明和相关的异常.
   * 说明和相关的异常均不会用于显示,系统会自动将其录入日志.
   * @param message 相关说明. 有助于增加日志的可读性.
   * @param cause 相关的异常
   */
  public SysException(String message, Throwable cause) {
    super(message, cause);
  }
  
  /**
   * 包含相关的异常.
   * 相关的异常不会用于显示,系统会自动将其录入日志.
   * @param cause 相关的异常
   */
  public SysException(Throwable cause) {
    super(cause);
  }
}
