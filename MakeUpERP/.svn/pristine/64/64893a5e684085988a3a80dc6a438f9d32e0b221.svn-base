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

public class LoginFilter implements Filter{

  public void destroy() {
  }

  public void doFilter(ServletRequest sReq, ServletResponse sResp,
      FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) sReq;
    HttpServletResponse resp = (HttpServletResponse) sResp;
    @SuppressWarnings("unused")
	  String requestURI = req.getRequestURI();
    String reqQueryString = req.getQueryString();
    if (!"".equals(reqQueryString) && reqQueryString != null) {
      requestURI += "?" + reqQueryString;
    }
    
    if(req.getSession(false) != null){
      if(req.getSession(false).getAttribute("userAcl") == null){
        ((HttpServletResponse)sResp).sendRedirect("/first.jsp");
      }
    } else {
      ((HttpServletResponse)sResp).sendRedirect("/first.jsp");
    }
    chain.doFilter(req, resp);
  }

  public void init(FilterConfig arg0) throws ServletException {
  }
}
