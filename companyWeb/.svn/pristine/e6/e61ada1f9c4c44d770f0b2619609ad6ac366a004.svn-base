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
  <title>门店详情</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">    
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" href="<%=basePath%>common/css/common.css" />
  <link rel="stylesheet" href="<%=basePath%>css/storecss/shop_detail.css" />
  </head>
  <body>
  <!-- header -->
  <jsp:include page="../common/header.jsp"/>
  <div class="hl">
  <div class="mh">
                当前位置:<a href="store/queryStore.do?tab=3">非泊门店</a> > 门店详情 
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
                      <span class="myscore">门店详情</span>
                 </p>
                     
                  <div class="mcd">
                    <div class="detail_info wrapfix">
                      <div class="detail_pic">
                          <img src="<%=basePath%>${storeExtend.imgUrl}" alt="详情图" width="490px" height="303px"/>
                        </div>
                        
                        <div class="adviser_intro">
                            <div class="adviser_name">
                              ${storeExtend.storeName}
                            </div>
                            <ul>
                              <li>
                                  <span class="la">面积:</span>
                                    <span class="va">${storeExtend.areaNum }</span>
                                    <span class="ui">m</span>
                                </li>
                                <li>
                                  <span class="la">日均客流数:</span>
                                    <span class="va">5000 人</span>
                                </li>
                                <li>
                                  <span class="la">联系电话:</span>
                                    <span class="va">${storeExtend.linkTel }</span>
                                </li>
                                <li>
                                  <span class="la">营业时间:</span>
                                    <span class="va">${storeExtend.openTime }</span>
                                </li>
                                <li>
                                  <span class="la">地址:</span>
                                    <span class="va">${storeExtend.addr }</span>
                                    <span class="map">
                                      <img src="<%=basePath%>images/shopimages/map_1.png" alt="地图" />
                                    </span>
                                </li>
                            </ul>
                            
                            <div class="intro">
                              <span class="adviser" style="margin-right:15px;">
                                  <!-- <a href="store/viewStoreCounselor.do?storeId=${storeExtend.storeId}">店铺顾问</a> -->
                                  <img src="<%=basePath%>images/shopimages/adviser_1.png" alt="美容顾问" id="adviser"/>
                                </span>
                                <span class="photo">
                                  <!-- <a href="store/queryStorePic.do?storeId=${storeExtend.storeId}">店铺掠影</a> -->
                                  <img src="<%=basePath%>images/shopimages/photo_1.png" alt="店鋪掠影" id="photo"/>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="shop_intro">
                      <div class="st">店铺介绍</div>
                        <div class="si">${storeExtend.content }</div>
                    </div>
                  </div>
                </div>
          </div>
        </div>
    </div>
    
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
		  if("adviser" == result){
			  location.href="store/viewStoreCounselor.do?storeId=${storeExtend.storeId}&&tab=3";
		  } else if("photo" == result){
			  location.href="store/queryStorePic.do?storeId=${storeExtend.storeId}&&tab=3";
		  }
	  });
  
    var msg = "${msg}";
    if(msg == "当前门店没有顾问!" && msg != ""){
  	  alert(msg);
    }
  
  });
  </script>
</html>