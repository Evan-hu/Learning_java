<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addSupplier.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  <script type="text/javascript" src="<%=basePath%>js/jquery-1.7.1.js"></script>
	<script type="text/javascript">
  $(function(){
      $(".applForm").click(function(){
    	  $("#application").submit();
      });
   }); 
  </script>

  </head>
  
  <body>
    <div align="center">
    <br />
    <br />
    <br /> 
      <sf:form method="post" action="appl/Appl.do" commandName="application" >
        <table>
          <tr>
            <td align="center">供应商申请</td>
          </tr>
          <tr>
            <td align="right">公司名称：</td>
            <td align="left"><sf:input path="companyName"/></td>
          </tr>
          <tr>
            <td align="right">联系人：</td>
            <td align="left"><sf:input path="username"/></td>
          </tr>
          <tr>
            <td align="right">联系电话：</td>
            <td align="left"><sf:input path="tel"/></td>
          </tr>
          <tr>
            <td align="right">通讯地址：</td>
            <td align="left"><sf:input path="addr"/></td>
          </tr>
          <tr>
            <td align="right">Email：</td>
            <td align="left"><sf:input path="email"/></td>
          </tr>
          <tr>
            <td align="right">公司简介：</td>
            <td align="left"><sf:input path="descr"/></td>
          </tr>
          <tr>
            <td align="right">上传公司简介：</td>
            <td align="left"><sf:input path="filePath" cssClass="inputFile"/></td>
          </tr><%--
          <tr>
            <td align="right">上传营业执照</td>
            <td align="left"><sf:input path=""/></td>
          </tr>
          <tr>
            <td align="right">上传税务登记证</td>
            <td align="left"><sf:input path=""/></td>
          </tr>
          <tr>
            <td align="right">上传组织机构代码证</td>
            <td align="left"><sf:input path=""/></td>
          </tr>
          --%><tr>
            <td></td>
            <td><a href="javascript:;" class="applForm">提交</a></td>
          </tr>
        </table>
      </sf:form>
    </div>
  </body>
</html>
