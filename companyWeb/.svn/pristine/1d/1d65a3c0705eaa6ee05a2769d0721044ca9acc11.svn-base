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
  <title>非泊新闻</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">    
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" href="<%=basePath%>common/css/common.css" />
  <link rel="stylesheet" href="<%=basePath%>css/newscss/news_search.css" />
  </head>
  
  <body>
  <!-- header -->
  <jsp:include page="../common/header.jsp"/>
   <div class="hl">
          <div class="mh">
          <c:if test="${code=='news'}">
                      当前位置 : 最新动态  > 非泊新闻
          </c:if> 
          <c:if test="${code == 'active'}">
                      当前位置 : 最新动态 > 最新活动
          </c:if> 
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
                 <c:if test="${code == 'news' }">
                   <div class="navItem curItem" onclick="forward('<%=basePath%>article/list.do?code=news&&tab=1')">非泊新闻</div>
                   <div class="navItem" onclick="forward('<%=basePath%>article/mienList.do?tab=1')">非泊风采</div>
                   <div class="navItem" onclick="forward('<%=basePath%>article/list.do?code=active&&tab=1')">最新活动</div>
                   </c:if>
                   <c:if test="${code == 'active' }">
                   <div class="navItem" onclick="forward('<%=basePath%>article/list.do?code=news&&tab=1')">非泊新闻</div>
                   <div class="navItem" onclick="forward('<%=basePath%>article/mienList.do?tab=1')">非泊风采</div>
                   <div class="navItem curItem" onclick="forward('<%=basePath%>article/list.do?code=active&&tab=1')">最新活动</div>
                   </c:if>
                 </div>
           </div>
              <div class="content">
                <p class="rp">
                  <c:if test="${code == 'news' }">                         
                  <span class="myscore">新闻详情 </span>
                  </c:if>
                  <c:if test="${code == 'active' }">                    
                  <span class="myscore">活动详情 </span>
                  </c:if>
                 </p>
                     
                  <div class="mcd">
                      <div class="shopPage wrapfix">
                          <ul>
                            <c:forEach var="i" items="${page.results}">
                              <li>
                              <c:if test="${code == 'news' }">
                              <a href="<%=basePath%>article/detailList.do?articleId=${i.articleId}&&code=news&&tab=1">${i.title}</a>
                              <span><fmt:formatDate pattern=" yyyy-MM-dd" value="${i.createTime}" type="both"/></span>
                              </c:if>
                              <c:if test="${code == 'active' }">
                              <a href="<%=basePath%>article/detailList.do?articleId=${i.articleId}&&code=active&&tab=1">${i.title}</a>
                              <span><fmt:formatDate pattern=" yyyy-MM-dd" value="${i.createTime}" type="both"/></span>
                              </c:if>
                              </li>
                              </c:forEach>
                            </ul>
                      </div>
                      <div class="page">
                           <c:if test="${code == 'news' }">
                           <page:nav url="article/list.do?code=news" page="${page}"/>
                           </c:if>
                           <c:if test="${code == 'active' }">
                           <page:nav url="article/list.do?code=active" page="${page}"/>
                           </c:if>
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