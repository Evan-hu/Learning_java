<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="page" uri="/WEB-INF/page.tld" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <base href="<%=basePath%>">
  <title>用户中心</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">    
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" href="<%=basePath%>common/css/common.css" />
  <link rel="stylesheet" href="<%=basePath%>css/member.css" />
  <link rel="stylesheet" href="<%=basePath%>css/mycoupon.css" />
  </head>
  
  <body>
  <!-- header -->
  <jsp:include page="../common/header.jsp"/>
  
    <div align="center">
      <form action="user/validate.do" method="post">
        <table>
        <tr>
          <td>找回密码</td>
        </tr>
        <tr>
          <td align="center">会员编号：</td>
          <td align="left"><input type="text" name="memberNum" /></td>
        </tr>
        <tr>
          <td align="center">手机号码：</td>
          <td align="left"><input type="text" name="tel" /></td>
        </tr>
        <tr>
          <td></td>
          <td align="left"><input type="submit" value="提交"></td>
        </tr>
        </table>
      </form>
    </div>
    
    
  <!-- link -->    
  <jsp:include page="../common/link.jsp"/>
  <!-- footer -->
  <jsp:include page="../common/footer.jsp"/>
  
  </body>
  <script type="text/javascript" src="<%=basePath%>common/js/jquery-1.7.min.js"></script>
  <script type="text/javascript" src="<%=basePath%>common/js/common.js"></script>
  <script type="text/javascript" src="<%=basePath%>common/js/page.js"></script>
  <script type="text/javascript" src="<%=basePath%>js/button.skip.js"></script>
  <!-- JiaThis Button BEGIN -->
  <script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1399183794965592" charset="utf-8"></script>
  <!-- JiaThis Button END -->
  <script type="text/javascript">
  $(function(){
       $('img').click(function(){
         var result = $(this).attr("id");
         if("join" == result){
           location.href ="league/add.do";
         } else if("contact" == result){
           location.href="skip/contact.do";
         }
       });
     });
  </script>
</html>