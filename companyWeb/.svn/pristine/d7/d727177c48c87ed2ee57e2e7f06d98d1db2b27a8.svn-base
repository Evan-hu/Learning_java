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
  <title>顾问详情</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">    
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" href="<%=basePath%>common/css/common.css" />
  <link rel="stylesheet" href="<%=basePath%>css/storecss/detail.css" />
  </head>
  
  <body>
  <!-- header -->
  <jsp:include page="../common/header.jsp"/>
  <div class="hl">
  <div class="mh">
              当前位置 : <a href="store/queryStore.do?tab=3">非泊门店</a> > <a href="<%=basePath%>store/viewAllCounselor.do?tab=3">美容顾问</a> > 顾问详情
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
                      <span class="myscore">顾问详情</span>
                 </p>
                     
                  <div class="mcd">
                    <div class="detail_info wrapfix">
                      <div class="detail_pic">
                          <img src="<%=basePath%>images/shopimages/3_1.jpg" alt="详情图" />
                        </div>
                        
                        <div class="adviser_intro">
                          <div class="adviser_name">
                              <span class="la">美容顾问</span>
                                <span class="va">${counselor.counselorName}</span>
                            </div>
                            <ul>
                              <li>
                                  <span class="la">美容驻点:</span>
                                    <span class="va">${counselor.store.storeName}</span>
                                </li>
                                <li>
                                  <span class="la">美容热线:</span>
                                    <span class="va">${counselor.tel}</span>
                                </li>
                                <li>
                                  <span class="la">美容资历:</span>
                                    <span class="va">${counselor.except}</span>
                                </li>
                                <li>
                                  <span class="la">美容专长:</span>
                                    <span class="va">${counselor.speciality}</span>
                                </li>
                            </ul>
                            
                            <div class="intro">${counselor.intro}<br/>1988年之后对的剩余几天之内我还是不知道怎么会回事</div>
                        </div>
                    </div>
                      
                     <p class="other_pic">
                      <img src="<%=basePath%>images/shopimages/other_pic.jpg" />
                     </p>
                     
                     <p class="other_pic">
                      <img src="<%=basePath%>images/shopimages/other_pic.jpg" />
                     </p>
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