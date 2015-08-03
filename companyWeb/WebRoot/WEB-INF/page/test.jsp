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
    
    <title>测试数据</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" href="<%=basePath%>common/css/common.css" />
  <link rel="stylesheet" href="<%=basePath%>css/member.css" />

  </head>
  
  <body>
  
    <jsp:include page="common/header.jsp"/>
    
  <div class="container">
        <div class="cz">
            <div class="mh">
                当前位置 : 最新动态 > 门店展示 > 美容顾问 > 顾问详情
              </div>
              <div class="ct">
                <div class="memberlogo"></div>
                  <div class="mborder"></div>
              </div>
              <div class="mc">
                <div class="navPanel">
                    <div class="navItem">资料修改</div>
                      <div class="navItem">密码修改</div>
                      <div class="navItem">礼券管理</div>
                      <div class="navItem curItem">积分查询</div>
                      <div class="navItem">站内信</div>
                   </div>
                      
                   <div class="content">
                      <%--
                      <p class="rp">
                        <span class="myscore">我的积分</span>
                      </p>
                      <p class="rp">
                        <span class="scoreLabel">您当前的积分:</span>
                         计算当前积分值 
                          <span class="scoreValue">${scoreLog.score}-${scoreLog.nowScore}</span>
                          <span class="condition">
                            <select name="timescope">
                                <option value="1">最近3个月</option>
                              </select>
                          </span>
                       </p>
                       
                       <div class="mcd">
                        <div class="mtitle">
                            <span class="scoredate">日期</span>
                              <span class="score">获取积分</span>
                              <span class="cost">消耗积分</span>
                              <span class="detail">详细说明</span>
                          </div>
                          
                          <div class="mcontent">
                            <div class="single">
                                <span class="scoredate" title="2014.5.26 18:32:32">2014.5.26 18:32:32</span>
                                  <span class="score sv" title="25">25</span>
                                  <span class="cost noe" title="-">-</span>
                                  <span class="detail" title="购物产生">购物产生</span>
                              </div>
                              <div class="double">
                                <span class="scoredate" title="2014.5.26 18:32:32">2014.5.26 18:32:32</span>
                                  <span class="score noe" title="-">-</span>
                                  <span class="cost cv" title="25">25</span>
                                  <span class="detail" title="购物产生,购物消耗">购物产生,购物消耗</span>
                              </div>
                          </div>
                          
                          <div class="page">
                            <span class="forward">
                                <img src="<%=basePath%>common/images/left_1.png" />
                              </span>
                              <span class="curPageNum">1</span>
                              <span class="pageNum">2</span>
                              <span class="pageNum">3</span>
                              <span class="pageNum">4</span>
                              <span class="pageNum">5</span>
                              <span class="pageNum">…</span>
                              <span class="pageNum">12</span>
                              <span class="forward">
                                <img src="<%=basePath%>common/images/right_1.png" />
                              </span>
                          </div>
                       </div>
                   --%>
                   </div>
            </div>
          </div>
      </div>
    
    <jsp:include page="common/footer.jsp"/>
    
  </body>
  <script type="text/javascript" src="<%=basePath%>common/js/jquery-1.7.min.js"></script>
  <script type="text/javascript" src="<%=basePath%>common/js/common.js"></script>
  <script type="text/javascript" src="<%=basePath%>common/js/page.js"></script>
  <!-- JiaThis Button BEGIN -->
  <script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1399183794965592" charset="utf-8"></script>
  <!-- JiaThis Button END -->
</html>
