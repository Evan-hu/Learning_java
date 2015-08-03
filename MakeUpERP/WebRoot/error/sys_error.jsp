<%@page import="org.apache.log4j.Logger"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page isErrorPage="true"%>
<%
 Logger logger = Logger.getLogger("SYS_ERROR");
 logger.error("SYS_ERROR",exception);
%>
<div style="margin: 0px auto 0px auto;text-align: center;">
很抱歉!系统错误.
<a href="/">去首页>></a>
</div>