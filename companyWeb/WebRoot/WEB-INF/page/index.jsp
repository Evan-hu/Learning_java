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
  <link rel="stylesheet" href="<%=basePath%>css/index.css" />
  <link rel="stylesheet" href="<%=basePath%>css/global.css" type="text/css"/>
</head>

<body>
   <jsp:include page="common/header.jsp"/>
    <div id="container">
      <div id="example">
          <div id="slides">
               <div class="slides_container">
                  <c:forEach var="ad" items="${adList }">
                    <div>
                        <a href="${ad.addrs }" title="${ad.adName }" target="_blank">
                            <img src="<%=basePath%>images/${ad.adId }.jpg" alt="Slide 1"></a>
                    </div>
                  </c:forEach>
                </div>
                <div class="adNav">
                    <div class="transition">
                        <div id="adPrev" class="aprev">
                            <img src="<%=basePath%>images/prev_1.png" alt="向左" />                        
                        </div>
                        <div id="adNext" class="anext">
                            <img src="<%=basePath%>images/next_1.png" alt="向右" />                        
                        </div>
                    </div>
              </div>
            </div>
        </div>
    </div>
    
    <div class="home_service">
      <div>
          <span class="nd">
              <img src="<%=basePath%>images/fable_1.png" id="fable" />
            </span>
            <span class="nd">
              <img src="<%=basePath%>images/news_1.png" id="news" />
            </span>
            <span class="nd">
              <img src="<%=basePath%>images/stores_1.png" id="stores" />
            </span>
            <span>
              <img src="<%=basePath%>images/bc_1.png" id="bc" />
            </span>
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
    $(function(){
      $('#slides').slides({
        preload: true,
        preloadImage: 'img/loading.gif',
        play: 5000,
        pause: 2500,
        hoverPause: true,
        animationStart: function(){
          $('.caption').animate({
            bottom:-35
          },100);
        },
        animationComplete: function(current){
          $('.caption').animate({
            bottom:0
          },200);
          if (window.console && console.log) {
            // example return of current slide number
            console.log(current);
          };
        }
      });
    });
  </script>
</html>