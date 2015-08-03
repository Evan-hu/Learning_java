/**
 * Copyright (c) 2011 Noah
 * All right reserved.
 */
package com.ga.erp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorityFilter implements Filter {

  public void destroy() {}
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;
    
//    String requestURI = req.getRequestURI();
//    String reqQueryString = req.getQueryString();
    req.getSession().getAttribute("userAcl");
    
//    if (!"".equals(reqQueryString) && reqQueryString != null) {
//      requestURI += "?" + reqQueryString;
//    }
//    
//    if(req.getSession(false) != null){
//      if(req.getSession(false).getAttribute("userAcl") == null){
//        ((HttpServletResponse)request).sendRedirect("/first.jsp");
//      }
//    } else {
//      ((HttpServletResponse)response).sendRedirect("/first.jsp");
//    }
//    
    chain.doFilter(req, resp);
    
  }
  public void init(FilterConfig arg0) throws ServletException {}
}
