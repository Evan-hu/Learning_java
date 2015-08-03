package com.ga.click.dbutil;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 数据库连接管理过滤器,用于自动关闭连接
 */
public class ConnManageFilter implements Filter {

  @Override
  public void destroy() {
    
  }

  @Override
  public void doFilter(ServletRequest arg0, ServletResponse arg1,
      FilterChain arg2) throws IOException, ServletException {
    try {
      arg2.doFilter(arg0, arg1);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DbUtils.close();
    }
  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {

  }

}
