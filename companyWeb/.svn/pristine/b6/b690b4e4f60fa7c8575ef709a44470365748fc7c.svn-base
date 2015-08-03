<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="page" uri="/WEB-INF/page.tld" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <base href="<%=basePath%>">
  <title>门店展示</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" href="<%=basePath%>common/css/common.css" />
  <link rel="stylesheet" href="<%=basePath%>css/storecss/search.css" />
  </head>
  
  <body>
  <!-- header -->
  <jsp:include page="../common/header.jsp"/>
   <div class="hl">
    <div class="mh">
                当前位置 :门店展示 
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
                  <div class="navItem curItem" onclick="forward('<%=basePath%>store/queryStore.do?tab=3')">非泊门店</div>
                  <div class="navItem" onclick="forward('<%=basePath%>store/viewAllCounselor.do?tab=3')">美容顾问</div>
                 </div>
               </div>
              <div class="content">
                <p class="rp">
                      <span class="myscore">非泊门店</span>
                 </p>
                  <div class="mcd">
                      <div class="shopPage wrapfix">
                        <c:forEach var="i" items="${page.results}" varStatus="status">
                           <c:if test="${status.count % 2 != 0 }" >
                              <div class="shop single_shop shop_bottom">
                              <div class="shop_pic"  onclick="forward('<%=basePath%>store/detail.do?storeId=${i.storeId}&&tab=3')">
                                   <img src="<%=basePath%>${i.imgUrl}" width="365px" height="222px" />
                               </div>
                               <div class="shop_info">
                                <a href="<%=basePath%>store/detail.do?storeId=${i.storeId}&&tab=3">
                                  <span>${i.storeName}</span>
                                </a>
                                </div>
                            </div>
                           </c:if>
                           <c:if test="${status.count % 2 == 0 }" >
                              <div class="shop shop_bottom">
                              <div class="shop_pic" onclick="forward('<%=basePath%>store/detail.do?storeId=${i.storeId}&&tab=3')">
                                   <img src="<%=basePath%>${i.imgUrl}" width="365px" height="222px" />
                               </div>
                               <div class="shop_info">
                                <a href="<%=basePath%>store/detail.do?storeId=${i.storeId}&&tab=3">
                                  <span>${i.storeName}</span>
                                </a>
                                </div>
                            </div>
                           </c:if>
                               <page:nav url="store/queryStore.do" page="${page}" />
                        </c:forEach>
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