<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/ad.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
  <meta http-equiv="Content-Type" content="text/html; charset=GBK" />
  <title>非舶来品首页</title>
  <link rel="stylesheet" href="<%=basePath%>common/css/common.css" />
  <link rel="stylesheet" href="<%=basePath%>css/login.css" />
</head>

<body>
   <jsp:include page="common/header.jsp"/>
   <div class="container">
      <div class="loginC">
          <form action="user/login.do" method="post" id="form1">
                <div class="loginP">
                    <div id="username" class="input_normal">
                        <span class="ula user_logo_normal"></span>
                        <span class="vaf">
                            <input type="text" name="userName" id="userName" maxlength="30" value="卡号/手机号码"/>
                        </span>
                    </div>
                    <div id="password" class="input_normal">
                      <span class="pla pwd_logo_normal"></span>
                        <span class="vaf">
                          <input type="password" name="passwd" id="pwd" maxlength="20" value="" />
                        </span>
                    </div>
                    <%--
                    <div class="authPanel">
                      <span id="authCode" class="authZ auth_normal">
                          <span class="ala auth_logo_normal"></span>
                            <span class="va">
                            <input type="text" name="auth" id="auth" class="uf"/>
                            </span>
                        </span>
                        <span class="authImg">
                          <img title="看不清楚？点击更换！" style="cursor :pointer" src="/authimg.jpg" height="19" onclick="this.src='/authimg.jpg?'+Math.random();"/>
                        </span>
                    </div>
                    --%>
                    <div class="submitPanel">
                      <input type="button" name="login" id="login" class="login" value=""/>
                    </div>
                </div>
            </form>
        </div>
    </div>
   
   
   
    <!-- link -->    
    <jsp:include page="common/link.jsp"/>
    <!-- footer -->
    <jsp:include page="common/footer.jsp"/>
</body>
<script type="text/javascript" src="<%=basePath%>common/js/jquery-1.7.min.js"></script>
<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1399183794965592" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath%>common/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/slides.min.jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/index.js"></script>
<script type="text/javascript" src="<%=basePath%>js/button.skip.js"></script>
<script type="text/javascript">
function check(){
    var username = $("#userName").val();
    var pwd = $("#pwd").val();
    var auth = $("#auth").val();
    alert(username+"--"+pwd+"=="+auth);
    if(auth == "" || pwd == "" || username == ""){
      alert("请输入账号、密码、验证码!");
    } else {
      $("#form1").submit();
    }
  }
  
  $(function(){
	  $("#userName").focus(function(){
		  this.value = "";
	  });
	  $("#pwd").focus(function(){
	      this.value = "";
	    });
	  $("#auth").focus(function(){
	      this.value = "";
	    });
	  $("#login").click(function(){
		  check();
		  });
  });
</script>
</html>
