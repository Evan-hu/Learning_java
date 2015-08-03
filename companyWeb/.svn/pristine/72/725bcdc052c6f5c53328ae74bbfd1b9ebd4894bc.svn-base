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
  <title>咨询加盟</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">    
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" href="<%=basePath%>common/css/common.css" />
  <link rel="stylesheet" href="<%=basePath%>css/condition.css" />
  </head>
  
  <body>
  <!-- header -->
  <jsp:include page="../common/header.jsp"/>
  <div class="hl">
    <div class="mh">
                 当前位置 : 咨询加盟  > 加盟流程
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
                    <div class="navItem curItem" onclick="forward('<%=basePath%>league/process.do?tab=5')">加盟流程</div>
                    <div class="navItem" onclick="forward('<%=basePath%>league/appl.do?tab=5')">門店加盟</div>
                 </div>
                 </div>
                 <div class="content">
                    <p class="rp">
                      <span class="myscore">加盟流程</span>
                     </p>
                <div class="mcd">
                        <p class="bg"></p>
                        <div class="leagua_intro">
                          <p class="title">加盟条件</p>
                            <div class="detail">
                            123312546
                            ${article.content}
                            </div>
                        </div>
                        <div class="leagua_intro">
                          <p class="title">政策与支持</p>
                            <div class="detail">
                            12123123
                            ${article.content}
                            </div>
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
</html>