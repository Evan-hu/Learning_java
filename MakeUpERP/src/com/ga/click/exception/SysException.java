package com.ga.click.exception;

/**
 * ϵͳ�쳣,����Ҫ�׳�һ���쳣���жϳ���ִ��, ������ϣ�����û�����������Ϣ,
 * ���׳����쳣.
 */
public class SysException extends RuntimeException{
  
  private static final long serialVersionUID = -5364830575742964581L;

  /**
   * ����˵������ص��쳣.
   * ˵������ص��쳣������������ʾ,ϵͳ���Զ�����¼����־.
   * @param message ���˵��. ������������־�Ŀɶ���.
   * @param cause ��ص��쳣
   */
  public SysException(String message, Throwable cause) {
    super(message, cause);
  }
  
  /**
   * ������ص��쳣.
   * ��ص��쳣����������ʾ,ϵͳ���Զ�����¼����־.
   * @param cause ��ص��쳣
   */
  public SysException(Throwable cause) {
    super(cause);
  }
}
