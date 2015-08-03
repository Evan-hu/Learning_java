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
  <title>招聘详情</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">    
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" href="<%=basePath%>common/css/common.css" />
  <link rel="stylesheet" href="<%=basePath%>css/recruitcss/recruit_detail.css" />
  </head>
  
  <body>
  <!-- header -->
  <jsp:include page="../common/header.jsp"/>
    <div class="hl">
    <div class="mh">
                 当前位置 : <a href="<%=basePath%>recruiment/viewAll.do?tab=6">职位招聘</a> > 招聘详情
     </div>
   </div>
    <div class="container">
      <div class="cz">
            <div class="ct">
              <div class="recruitment"></div>
                <div class="mborder"></div>
            </div>
            <div class="mc">
            <div class="cNav">
              <div class="navPanel">
                  <div class="navItem curItem" onclick="forward('<%=basePath%>recruiment/viewAll.do?tab=6')">招兵买马</div>
                  <div class="navItem" onclick="forward('<%=basePath%>skip/appl.do?tab=6')">职位申请</div>
                 </div>
                   </div> 
              <div class="content">
                    <p class="rp">
                        <span class="myscore">职位详情</span>
                    </p>     
                    <div class="mcd">
                      <div class="mtitle">招聘职位</div>
                      <div class="mcontent">
                        <div class="rtitle">
                          <span class="subtitle">${recruiment.position.positionName}</span>
                          <span class="createTime">发布时间:<fmt:formatDate pattern=" yyyy-MM-dd" value="${recruiment.createTime}" type="both"/></span>
                        </div>
                        <form action="" method="post" id="form1" name="form1">
                          <div class="rdetail">
                              <p>基础信息</p>
                                <div class="mi">
                                  <ul class="wrapfix">
                                      <li class="rd" style="list-style-type:none;">
                                          <ul class="wrapfix">
                                              <li>
                                                  <span class="la">职位月薪:</span>
                                                  <span class="va">${recruiment.position.salary}元/月</span>
                                                </li>
                                                <li>
                                                  <span class="la">工作地点:</span>
                                                  <span class="va">${recruiment.addr}</span>
                                                </li>
                                            </ul>
                                        </li>
                                        <li class="rd" style="list-style-type:none;">
                                          <ul class="wrapfix">
                                              <li>
                                                  <span class="la">工作性质:</span>
                                                  <span class="va">${recruiment.note}</span>
                                                </li>
                                                <li>
                                                  <span class="la">工作经验:</span>
                                                  <span class="va">不限</span>
                                                </li>
                                            </ul>
                                        </li>
                                        <li class="rd" style="list-style-type:none;">
                                          <ul class="wrapfix">
                                              <li>
                                                  <span class="la">最低学历:</span>
                                                  <span class="va">大专</span>
                                                </li>
                                                <li>
                                                  <span class="la">招聘人数:</span>
                                                  <span class="va">${recruiment.cnt}人</span>
                                                </li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                                
                                <p>职位描述</p>
                                <div class="jobdetail">
                                  <ul>
                                      <li>${recruiment.position.note}</li>
                                    </ul>
                                </div>
                                
                                <div class="submitPanel">
                                  <input type="submit" class="submit" value="" />
                                </div>
                            </div>
                        </form>
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