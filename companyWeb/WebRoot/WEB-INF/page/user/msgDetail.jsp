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
  <title>信息详情</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">    
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" href="<%=basePath%>common/css/common.css" />
  <link rel="stylesheet" href="<%=basePath%>css/msg.css" />
  </head>
  
  <body>
  <!-- header -->
  <jsp:include page="../common/header.jsp"/>
  <div class="hl">
    <div class="mh">
              当前位置 : <a href="<%=basePath%>user/goto.do?memberId=${memberId}&&type=1">会员中心</a> > <a href="<%=basePath%>user/myMessageList.do?memberId=${memberId}">站内信</a>   > 信息详情
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
                    <div class="navItem" onclick="forward('<%=basePath%>user/goto.do?memberId=${memberId}&&type=1')">修改资料</div>
                    <div class="navItem" onclick="forward('<%=basePath%>user/goto.do?memberId=${memberId}&&type=2')">修改密码</div>
                    <div class="navItem" onclick="forward('<%=basePath%>user/mangGift.do?memberId=${memberId}&&state=1')">礼卷管理</div>
                    <div class="navItem" onclick="forward('<%=basePath%>user/queryScore.do?memberId=${memberId}&&type=1')">积分查询</div>
                    <div class="navItem curItem" onclick="forward('<%=basePath%>user/myMessageList.do?memberId=${memberId}')">站内信</div>
                 </div>
             </div>
              <div class="content">
                    <p class="rp">
                      <span class="myscore">站内信详情</span>
                    </p>
                <p class="ex_rp" style="margin-bottom:10px; border-bottom:1px solid #000;">
                      <span class="scoreLabel">[标题]</span>
                        <span class="scoreValue">${sMsg.title}</span>
                        <span class="condition">
                                     发布时间:<fmt:formatDate pattern=" yyyy-MM-dd" value="${sMsg.createTime}" type="both"/>
                        </span>
                  </p>
                     
                  <div class="mcd">
                    <div class="mid">
                    content: ${sMsg.content}
                    note : ${sMsg.note}
                    </div> 
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