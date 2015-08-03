package com.company.shop.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

  public static Cookie getCookie(HttpServletRequest request, String name) {
    Cookie cookies[] = request.getCookies();
    if (cookies == null || name == null || name.length() == 0) {
      return null;
    }
    String path = getPath(request);
    for (int i = 0; i < cookies.length; i++) {
      if (name.equals(cookies[i].getName()) && (cookies[i].getPath() == null || path.equals(cookies[i].getPath()))) {
        return cookies[i];
      }
    }
    return null;
  }

  public static void deleteCookie(HttpServletRequest request,
      HttpServletResponse response, Cookie cookie) {
    if (cookie != null) {
      cookie.setPath(getPath(request));
      cookie.setValue("");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }
  }

  public static void setCookie(HttpServletRequest request,
      HttpServletResponse response, String name, String value) {
    setCookie(request, response, name, value, 0x278d00);
  }

  public static void setCookie(HttpServletRequest request,
      HttpServletResponse response, String name, String value, int maxAge) {
    Cookie cookie = new Cookie(name, value == null ? "" : value);
    cookie.setMaxAge(maxAge);
    cookie.setPath(getPath(request));
    response.addCookie(cookie);
  }

  private static String getPath(HttpServletRequest request) {
    String path = request.getContextPath();
    return (path == null || path.length()==0) ? "/" : path;
  }

}
