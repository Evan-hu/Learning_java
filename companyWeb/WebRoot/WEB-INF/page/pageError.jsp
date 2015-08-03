<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <base href="<%=basePath%>">
  <title>系統異常</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=basePath%>common/css/common.css" />
<link rel="stylesheet" href="<%=basePath%>css/sys_error.css" />
</head>

<body>
  <div class="header">
      <div class="main">
            <div class="link wrapfix">
                <div class="link_right">
                    <span class="ml" onclick="index.login();">
                        <img src="<%=basePath%>common/images/member_1.png" />
                    </span>
                    <span class="su" onclick="index.providerLogin();">
                        <img src="<%=basePath%>common/images/supplier_1.png" />
                    </span>
                    <span class="coll" onclick="index.collectionPage();">
                        <img src="<%=basePath%>common/images/coll_1.png" />
                    </span>
                    <div class="share jiathis_style_32x32">
                        <a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank" style="background:url(<%=basePath%>common/images/share_1.png) no-repeat scroll 0 0 transparent;"></a>
                    </div>
                </div>
            </div>
            
            <div class="navBar">
                <div class="nav wrapfix">
                    <div class="logo"></div>
                    <ul class="wrapfix" style="margin-top:2px;" id="navBar">
                        <li id="index">  <a href="#" target="_blank" class="adlink"><img src="<%=basePath%>common/images/nav/index_1.jpg" alt="首页"/></a></li>
                        <li id="news"><a href="#" target="_blank" class="adlink"><img src="<%=basePath%>common/images/nav/news.jpg" alt="最新动态"/></a></li>
                        <li id="intro"><a href="#" target="_blank" class="adlink"><img src="<%=basePath%>common/images/nav/company.jpg" alt="公司介绍"/></a></li>
                        <li id="shop"><a href="#" target="_blank" class="adlink"><img src="<%=basePath%>common/images/nav/shop.jpg" alt="门店展示"/></a></li>
                        <li id="contact"><a href="#" target="_blank" class="adlink"><img src="<%=basePath%>common/images/nav/contact.jpg" alt="联系我们"/></a></li>
                        <li id="join"><a href="#" target="_blank" class="adlink"><img src="<%=basePath%>common/images/nav/join.jpg" alt="咨询加盟"/></a></li>
                        <li id="friend"><a href="#" target="_blank" class="adlink"><img src="<%=basePath%>common/images/nav/friend.jpg" alt="伙伴招募"/></a></li>
                    </ul>
                </div>
            </div>
  </div>
 </div>
    <div class="container">
      <p class="sec">
        <span class="setip"></span>
      </p>
</div>

    <div class="footer">
      <p>版本所有权@2014-2015 成都非泊企业管理有限公司</p>
        <p>Copyright@2014-2015&nbsp;fable360.com &nbsp;All Right Reserved备案号&nbsp;蜀ICP备案13004321</p>
    </div>
</body>
<script type="text/javascript" src="<%=basePath%>common/js/jquery-1.7.min.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/common.js"></script>
<!-- JiaThis Button BEGIN -->
<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1399183794965592" charset="utf-8"></script>
<!-- JiaThis Button END -->
</html>