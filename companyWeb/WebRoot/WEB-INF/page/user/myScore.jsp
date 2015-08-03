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
  <title>我的积分</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">    
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" href="<%=basePath%>common/css/common.css" />
  <link rel="stylesheet" href="<%=basePath%>css/myscore.css" />
  <style>
    .mtabbar {
      height: 33px;
      margin: 0 auto 19px;
      width: 828px;
    }
    .mtabbar a {
        display: inline-block;
        height: 33px;
        width: 100px;
    }
    .mtabbar a span.curtab {
        border-bottom: 2px solid #f96f0a;
        color: #f96f0a;
        display: inline-block;
        font-family: "宋体";
        height: 31px;
        line-height: 31px;
        text-align: center;
        width: 100%;
    }
    .mtabbar a span.tab {
        color: #f96f0a;
        display: inline-block;
        font-family: "宋体";
        height: 31px;
        line-height: 31px;
        text-align: center;
        width: 100%;
    }
  </style>
  </head>
  
  <body>
  <!-- header -->
  <jsp:include page="../common/header.jsp"/>
  <div class="hl">
    <div class="mh">
              当前位置 : <a href="<%=basePath%>user/goto.do?memberId=${memberId}&&type=1">会员中心</a> > 积分查询
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
                    <div class="navItem curItem" onclick="forward('<%=basePath%>user/queryScore.do?memberId=${memberId}&&type=1')">积分查询</div>
                    <div class="navItem" onclick="forward('<%=basePath%>user/myMessageList.do?memberId=${memberId}')">站内信</div>
                 </div>
             </div>
                    
                 <div class="content">
                    <p class="rp">
                      <span class="myscore">我的积分</span>
                    </p>
                    <p class="ex_rp">
                      <span class="scoreLabel">您当前的积分:</span>
                        <span class="scoreValue">${scoreBalance}</span>
                        <span class="condition">
                          <select name="timescope">
                              <option value="1">最近3个月</option>
                            </select>
                        </span>
                     </p>
                     
                     <div class="mcd">
                      <div class="mtabbar">
                        <a href="<%=basePath%>user/queryScore.do?memberId=${memberId}&&type=1">
                        <%-- 设置type的类型，1：获取的积分； 2：消耗的积分         系统默认查询获取的积分  --%>
                          <c:if test="${type == 1}">
                          <span class="curtab">获取积分</span>
                          </c:if>
                          <c:if test="${type != 1}">
                          <span class="tab">获取积分</span>
                          </c:if>
                        </a>
                        <a href="<%=basePath%>user/queryScore.do?memberId=${memberId}&&type=2">
                          <c:if test="${type == 2}">
                          <span class="curtab">消耗积分</span>
                          </c:if>
                          <c:if test="${type != 2}">
                          <span class="tab">消耗积分</span>
                          </c:if>
                        </a>
                      </div>
                      <div class="mtitle">
                          <span class="scoredate">日期</span>
                          <%-- 设置type的类型，1：获取的积分； 2：消耗的积分 --%>
                          <c:if test="${type == 1}">
                            <span class="score">获取积分</span>
                          </c:if>
                          <c:if test="${type == 2}">
                            <span class="cost">消耗积分</span>
                          </c:if>
                            <span class="detail">详细说明</span>
                        </div>
                        
                        <div class="mcontent">
                         <div class="single">
                          <c:forEach var="i" items="${page.results}">
                              <span class="scoredate" title="2014.5.26 18:32:32"><fmt:formatDate pattern=" yyyy-MM-dd" value="${i.createTime}" type="both"/></span>
                                <span class="score sv" title="25">${i.score}</span>
                                <span class="cost noe" title="-">-</span>
                                <span class="detail" title="购物产生">购物产生</span>
                            </c:forEach>
                            </div>
                            <%--<div class="double">
                              <span class="scoredate" title="2014.5.26 18:32:32">2014.5.26 18:32:32</span>
                                <span class="score noe" title="-">-</span>
                                <span class="cost cv" title="25">25</span>
                                <span class="detail" title="购物产生,购物消耗">购物产生,购物消耗</span>
                            </div>
                        --%></div>
                        
                        <div class="page">
                          <span class="forward">
                              <img src="<%=basePath%>common/images/left_1.png" />
                            </span>
                            <span class="curPageNum">1</span>
                            <span class="forward">
                              <img src="<%=basePath%>common/images/right_1.png" />
                            </span>
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