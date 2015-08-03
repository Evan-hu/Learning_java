<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="page" uri="/WEB-INF/page.tld" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <base href="<%=basePath%>">
  <title>非泊风采</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">    
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" href="<%=basePath%>/common/css/common.css" />
  <link rel="stylesheet" href="<%=basePath%>/css/newscss/mien_search.css" />
  </head>
  
  <body>
  <!-- header -->
  <jsp:include page="../common/header.jsp"/>
  <div class="hl">
  <div class="mh">
              当前位置 : 非泊风采
       </div>
   </div>
   <div class="container">
      <div class="cz">
            <div class="ct">
              <div class="lastlogo"></div>
                <div class="mborder"></div>
            </div>
            <div class="mc">
            <div class="cNav">
              <div class="navPanel">
                 <div class="navItem" onclick="forward('<%=basePath%>/article/list.do?code=news&&tab=1')">非泊新闻</div>
                  <div class="navItem curItem" onclick="forward('<%=basePath%>/article/mienList.do?tab=1')">非泊风采</div>
                  <div class="navItem" onclick="forward('<%=basePath%>/article/list.do?code=active&&tab=1')">最新活动</div>
                 </div>
               </div>     
              <div class="content">
                <p class="rp">
                      <span class="myscore">非泊风采</span>
                 </p>
                     
                  <div class="mcd">
                      <div class="shopPage wrapfix">
                      <c:forEach var="i" items="${page.results}" varStatus="status">
                         <c:if test="${status.count % 2!=0 }">
                          <div class="shop single_shop shop_bottom">
                          <div class="shop_pic">
                                 <img src="${i.imgUrl}" alt="非泊店铺" style="width:365px;height:225px;" />
                             </div>
                             <div class="shop_info"><a href="<%=basePath%>/article/mienDetail.do?articleId=${i.articleId}&&tab=1">${i.title}</a></div>
                         </div>
                         </c:if>
                         <c:if test="${status.count % 2 ==0 }">
                          <div class="shop shop_bottom">
                          <div class="shop_pic">
                                 <img src="${i.imgUrl}" alt="非泊店铺" style="width:365px;height:225px;" />
                             </div>
                             <div class="shop_info"><a href="<%=basePath%>/article/mienDetail.do?articleId=${i.articleId}&&tab=1">${i.title}</a></div>
                         </div>
                         </c:if>
                      </c:forEach>
                  </div>
                     <div class="page">
                     <page:nav page="${page}" url="article/mienList.do" />
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
  <script type="text/javascript" src="<%=basePath%>/common/js/jquery-1.7.min.js"></script>
  <script type="text/javascript" src="<%=basePath%>/common/js/common.js"></script>
  <script type="text/javascript" src="<%=basePath%>/common/js/page.js"></script>
  <script type="text/javascript" src="<%=basePath%>/js/button.skip.js"></script>
  <!-- JiaThis Button BEGIN -->
  <script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1399183794965592" charset="utf-8"></script>
  <!-- JiaThis Button END -->
</html>
