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
  <title>修改密码</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">    
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" href="<%=basePath%>common/css/common.css" />
  <link rel="stylesheet" href="<%=basePath%>css/forgetPwd.css" />
  </head>
  
  <body>
  <!-- header -->
  <jsp:include page="../common/header.jsp"/>
    <div class="hl">
   <div class="mh">
              当前位置 : <a href="<%=basePath%>user/goto.do?memberId=${member.memberId}&&type=1">会员中心</a> > 修改密码
  </div>
  </div>
    <div class="container">
      <div class="cz">
            <div class="ct">
              <div class="memberlogo"></div>
                <div class="mborder"></div>
            </div>
            <div class="mc">
              <div class="cNav">
              <div class="navPanel">
                    <div class="navItem" onclick="forward('<%=basePath%>user/goto.do?memberId=${member.memberId}&&type=1')">修改资料</div>
                    <div class="navItem curItem" onclick="forward('<%=basePath%>user/goto.do?memberId=${member.memberId}&&type=2')">修改密码</div>
                    <div class="navItem" onclick="forward('<%=basePath%>user/mangGift.do?memberId=${member.memberId}&&state=1')">礼卷管理</div>
                    <div class="navItem" onclick="forward('<%=basePath%>user/queryScore.do?memberId=${member.memberId}&&type=1')">积分查询</div>
                    <div class="navItem" onclick="forward('<%=basePath%>user/myMessageList.do?memberId=${member.memberId}')">站内信</div>
                 </div>
             </div>
                    
                 <div class="content">
                <p class="rp">
                      <span class="myscore">修改密码</span>
                 </p>
                     
                  <div class="mcd">
                    <form action="user/updatePasswd.do" method="post" id="form1">
                        <div class="mi">
                            <div class="fpwd">
                              <ul>
                                  <li>
                                      <span class="label">输入旧密码</span>
                                        <span class="mv">
                                        <input type="hidden" name="memberId" value="${member.memberId}" />
                                          <input name="passwdOld" id="oldPwd" class="opwd" type="password" maxlength="25"/>
                                            <span class="import">*</span>
                                            <a href="<%=basePath%>user/gotoval.do?type=3" target="_blank">忘记密码</a>
                                        </span>
                                    </li>
                                    <li>
                                      <span class="label">输入新密码</span>
                                        <span class="mv">
                                          <input name="passwdFront" id="newPwd" class="npwd" type="password" maxlength="25"/>
                                            <span class="import">*</span>
                                        </span>
                                    </li>
                                    <li>
                                      <span class="label">确认新密码</span>
                                        <span class="mv">
                                          <input name="passwd" id="surePwd" class="surePwd" type="password" maxlength="25"/>
                                            <span class="import">*</span>
                                        </span>
                                    </li>
                              </ul>
                                <div class="submitPanel">
                                    <input type="submit" class="submit" value=""/>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
          </div>
          </div>
        </div>
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
	  $(".submit").click(function(){
		  $("#form1").submit();
	  });
  });
  
  var msg="${msg}";
  if(msg == "密码错误" || msg == "更新密码成功" || msg == "更新密码出错"){
	  alert(msg);
  }
  
  var pwd = {
    oldPwd : $(".opwd"),
    newPwd : $(".npwd"),
    surePwd : $(".surePwd"),
    dataForm:$("#form1"),
    submitBtn : $(".submit"),
    init : function() {
      this.dataForm.submit(function(){
        if($(".opwd").val() == null || $(".opwd").val() == "" || $(".npwd").val() == null || $(".npwd").val() == "" || $(".surePwd").val() == null || $(".surePwd").val() == "") {
        	alert("请输入完整信息!");
        	return false;
        }
        if ($(".npwd").val() != $(".surePwd").val()) {
          alert("两次输入的密码不一致!");
          return false;
        }
        return true;
      });
    }
  };
  pwd.init();
  
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