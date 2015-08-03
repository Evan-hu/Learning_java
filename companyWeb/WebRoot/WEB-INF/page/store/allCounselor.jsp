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
  <title>顾问列表</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">    
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" href="<%=basePath%>common/css/common.css" />
  <link rel="stylesheet" href="<%=basePath%>css/storecss/adviser.css" />
  
  </head>
  
  <body>
  <!-- header -->
  <jsp:include page="../common/header.jsp"/>
  <div class="hl">
  <div class="mh">
              当前位置 : 美容顾问 
  </div>
  </div>
   <div class="container">
      <div class="cz">
            <div class="ct">
              <div class="storelogo"></div>
                <div class="mborder"></div>
            </div>
            <div class="mc">
              <div class="cNav">
                <div class="navPanel">
                  <div class="navItem" onclick="forward('<%=basePath%>store/queryStore.do?tab=3')">非泊门店</div>
                  <div class="navItem curItem" onclick="forward('<%=basePath%>store/viewAllCounselor.do?tab=3')">美容顾问</div>
                 </div>
               </div>    
              <div class="content">
                <p class="rp">
                      <span class="myscore">美容顾问</span>
                 </p>
                     
                  <div class="mcd">
                  
                      <div class="shopPage wrapfix">
                      <c:forEach var="i" items="${page.results}">
                         <div class="shop single_shop shop_bottom">
                             <div class="shop_pic">
                                 <img src="<%=basePath%>images/shopimages/2_1.jpg" alt="非泊店铺" />
                             </div>
                             <div class="shop_info">
                              <span class="adviser_name"><a href="<%=basePath%>store/viewCounselor.do?counselorId=${i.counselorId}&&tab=3">${i.counselorName}</a></span>
                                <span class="antag">${i.counselorId}</span>
                                <span class="ap">${i.store.storeName}</span>
                             </div>
                         </div>
                         </c:forEach>
                     <div class="page">
                         <page:nav url="store/viewAllCounselor.do" page="${page}"/>
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