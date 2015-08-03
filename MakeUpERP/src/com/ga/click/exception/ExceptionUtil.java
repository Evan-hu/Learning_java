package com.ga.click.exception;

/**
 * �쳣��������. 
 */
public class ExceptionUtil {
  
  /**
   * �Զ��׳�/�½��쳣:
   * 1.����BizException �� SysException ֱ���׳�.
   * 2.���������쳣, �������msg�������ݰ�װ��SysException �׳�. 
   */
  public static void autoThrow(Exception e, String msg) {
    if (e instanceof BizException || e instanceof SysException) {
      throw (RuntimeException) e;
    } else {
      throw new SysException(msg, e);
    }
  }
  
}
