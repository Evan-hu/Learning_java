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
  <title>門店加盟</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">    
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" href="<%=basePath%>common/css/common.css" />
  <link rel="stylesheet" href="<%=basePath%>common/upload/css/uploadify.css" />
  <link rel="stylesheet" href="<%=basePath%>css/league_apply.css" />
  </head>
  
  <body>
  <!-- header -->
  <jsp:include page="../common/header.jsp"/>
  <div class="hl">
    <div class="mh">
                当前位置 : 咨询加盟  > 門店加盟
    </div>
  </div>
  
   <div class="container">
      <div class="cz">
            <div class="ct">
              <div class="advicelogo"></div>
                <div class="mborder"></div>
            </div>
        <div class="mc">
              <div class="cNav">
                <div class="navPanel">
                   <div class="navItem" onclick="forward('<%=basePath%>league/add.do?tab=5')">加盟条件及策略</div>
                    <div class="navItem" onclick="forward('<%=basePath%>league/process.do?tab=5')">加盟流程</div>
                    <div class="navItem curItem" onclick="forward('<%=basePath%>league/appl.do?tab=5')">門店加盟</div>
                </div>
              </div>
      
      <div class="content">
                <p class="rp">
                      <span class="myscore">門店加盟</span>
                 </p>
                  <div class="mcd">
                    <p class="mtitle">
                      <span class="lv">基本信息</span>
                      <span class="rv"></span>
                    </p>
                    <form method="post" action="appl/Appl.do" id="application">
                        <div class="mi">
                            <ul class="wrapfix">
                                <li style="list-style-type: none;" class="rd">
                                  <ul class="wrapfix">
                                    <li>
                                       <span class="la">联系人</span>
                                       <span class="va">
                                          <input type="text" name="username" id="username" class="truename" />
                                       </span>
                                    </li>
                                    <li>
                                      <span class="la">联系电话</span>
                                       <span class="va">
                                          <input type="text" name="tel" id="tel" class="truename" />
                                       </span>
                                    </li>
                                  </ul>
                                </li>
                                <li class="rd">
                                  <span class="label">門店名称</span>
                                  <span class="mv" style="margin-left:-4px;">
                                      <input type="text" name="compnyName" id="compnyName" maxlength="40" class="job"/>
                                  </span>
                                </li>
                                <li class="rd">
                                  <span class="label">通信地址</span>
                                  <span class="mv" style="margin-left:-4px;">
                                      <input type="text" name="addr" id="addr" maxlength="40" class="job"/>
                                  </span>
                                </li>
                                <li class="rd">
                                   <span class="label">Email</span>
                                    <span class="mv" style="margin-left:-4px;">
                                        <input type="text" name="email" id="email" maxlength="40" class="job"/>
                                    </span>
                                </li>
                                <li class="rd" style="height: 100px; line-height: 100px;">
                                  <span class="label" style="height: 100px; line-height: 100px;">留言</span>
                                  <span class="mv" style="height: 100px; line-height: 100px;">
                                    <textarea rows="" cols="" name="note"></textarea>
                                  </span>
                                </li>
                            </ul>
                            <div class="submitPanel">
                              <input type="button" class="submit" value="" id="applBtn"/>
                            </div>
                        </div>
                    </form>
                  </div>
                </div>
                <div class="clear"></div>
          </div>
        </div>
    </div>        
   
  <!-- link -->
  <jsp:include page="../common/link.jsp"/>
  <!-- footer -->
  <jsp:include page="../common/footer.jsp"/>
  
  </body>
<script type="text/javascript" src="<%=basePath%>common/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>common/upload/scripts/swfobject.js"></script>
<script type="text/javascript" src="<%=basePath%>common/upload/scripts/jquery.uploadify.v2.1.4.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/button.skip.js"></script>
<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1399183794965592" charset="utf-8"/></script>
<%--<script type="text/javascript" src="<%=basePath%>js/Calendar.js" charset="utf-8"></script>--%>
  <script type="text/javascript">
  $(function(){
    $(document).ready(function () {
             $("#uploadify").uploadify({
        uploader : '../../common/upload/scripts/uploadify.swf',
        method : 'post',
        script : '<%=basePath%>file/fileupload.do',
        buttonText : '上传简历',
        buttonImage : '../../common/upload/up_img.png',
        cancelImg : '../../common/upload/cancel.png',
        queueID : 'fileQueue',
        auto : true,
        multi : false,
        simUploadLimit : 1,
        fileExt : '*.doc',
        fileDesc : '*.doc',
        onComplete : function(event, queueId, fileObj, response, data) {
           $("#uploadintro").val(response);
        },
        onError:function(event,ID,fileObj,errorObj){
          alert("未知错误!");
        } 
      });
    });   
  });

  function check(){
	  var companyName = $("#compnyName").val();
	  var username = $("#username").val();
	  var tel = $("#tel").val();
	  var addr = $("#addr").val();
	  var email = $("#email").val();
	  if(tel == "" || companyName == "" || addr == "" || email == "" || username == ""){
		  alert("公司名， 联系电话，通讯地址，邮箱不允许为空!");
		  return false;
	  } else {
		  $("#application").submit();
	  }
  }
  
  $(function(){
	  $("#applBtn").click(function(){
		  check();
	  });
  });
  
  var msg = "${msg}";
  if(msg == "申请成功" || msg == "申请失败"){
    alert(msg+"....即将跳转到首页");
    window.location.href = "index.do";
  }
  
</script> 
</html>