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
  <title>联系我们</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">    
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" href="<%=basePath%>common/css/common.css" />
  <link rel="stylesheet" href="<%=basePath%>css/contact.css" />
  </head>
  
  <body>
  <!-- header -->
  <jsp:include page="common/header.jsp"/>
  <div class="hl">
  <div class="mh">
              当前位置 : 联系我们
    </div>
    </div>
    <div class="container">
      <div class="cz">
          
            <div class="ct">
              <div class="contactlogo"></div>
                <div class="mborder"></div>
            </div>
            <div class="mc">
            <div class="cNav">
              <div class="navPanel">
                  <div class="navItem curItem" onclick="forward('<%=basePath%>skip/contact.do?tab=4')">联系我们</div>
                 </div>
                 </div>
                 <div class="content">
                    <p class="rp">
                      <span class="myscore">所在详情</span>
                     </p>
                         
                <div class="mcd">
                        <div class="map">
                          <img src="<%=basePath%>images/map.jpg" alt="非泊地址" />
                        </div>
                        <div class="info">
                          <p class="cm">成都非泊企业管理有限公司</p>
                            <div class="oi">
                              地址：北京市朝阳区建国路93号万达广场B座
                            </div>
                            <div class="oi">
                              邮编：100022
                            </div>
                            <div class="oi">
                              电话：010-85853888
                            </div>
                            <div class="oi">
                              电话：010-85853222
                            </div>
                        </div>
                      </div>
                  </div>           
             </div>
        </div>
    </div>
      
  <!-- link -->    
  <jsp:include page="common/link.jsp"/>
  <!-- footer -->
  <jsp:include page="common/footer.jsp"/>
  
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
	       }
	     });
	   }); 
  
  
  </script>
</html>
