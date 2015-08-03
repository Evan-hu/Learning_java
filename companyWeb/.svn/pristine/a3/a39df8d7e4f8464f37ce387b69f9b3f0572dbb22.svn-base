package com.company.shop.exception;


/**
 * 异常处理复杂�? 
 */
public class ExceptionUtil {
  
  /**
   * 自动抛出/新建异常:
   * 1.对于BizException �?SysException 直接抛出.
   * 2.对于其他异常, 按传入的msg参数内容包装成SysException 抛出. 
   */
  public static void autoThrow(Exception e, String msg) {
    if (e instanceof BizException || e instanceof SysException) {
      throw (RuntimeException) e;
    } else {
      throw new SysException(msg, e);
    }
  }
  
}
