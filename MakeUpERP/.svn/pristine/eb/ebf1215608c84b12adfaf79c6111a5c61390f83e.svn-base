package com.ga.click.services;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ga.click.util.GaUtil;

public class UrlFilter implements Filter {
  
  public void doFilter(ServletRequest servletRequest,
      ServletResponse servletResponse, FilterChain chain) throws IOException,
      ServletException {
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    String menuID = request.getParameter("_menuid");
    if (!GaUtil.isNullStr(menuID)) {
      ((HttpServletRequest)servletRequest).getSession(true).setAttribute("MENUID",Long.parseLong(menuID));
    }
    
    String requestedWith = request.getHeader("x-requested-with");  
    String type = request.getContentType();

    if (requestedWith != null && "XMLHttpRequest".equals(requestedWith) 
        && null != type 
        && ("application/x-www-form-urlencoded".equals(type)||"application/x-www-form-urlencoded; charset=GBK".equals(type))) {       
      request.setCharacterEncoding("GBK");
      //response.setCharacterEncoding("GBK");  
    } else {
      request.setCharacterEncoding("GBK");
    }
    
    //request.setCharacterEncoding("GBK");
    //response.setCharacterEncoding("GBK");
//    String requestURI = request.getRequestURI();
//    String reqQueryString = request.getQueryString(); 
//    if (!"".equals(reqQueryString) && reqQueryString != null) {
//      requestURI += "?" + reqQueryString;
//    }
//    String scheme = request.getScheme();
//    String serverName = request.getServerName();
//    int serverPort = request.getServerPort();
//    
//    System.out.println(request.getServletPath());
    //response.setCharacterEncoding("GBK");
    chain.doFilter(request, response);
    //response.setCharacterEncoding("GBK");
  }

  public void destroy() {
    
  }

  public void init(FilterConfig arg0) throws ServletException {
    
  }
}
