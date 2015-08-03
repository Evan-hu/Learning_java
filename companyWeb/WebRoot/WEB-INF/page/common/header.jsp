<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Integer val = (Integer)request.getSession().getAttribute("memberId");
%>
<div class="header">
      <div class="main">
            <div class="flink wrapfix">
                <div class="link_right">
                    <span class="ml" onclick="login(<%=val%>);" style="float:left;margin-right: 12px">
                        <img src="<%=basePath%>common/images/member_1.png" class="member_1"/>
                    </span>
                    <span class="su" onclick="providerLogin();" style="float:left;">
                        <img src="<%=basePath%>common/images/supplier_1.png" />
                    </span>
                    <span class="coll" onclick="collectionPage();" style="float:left;">
                        <img src="<%=basePath%>common/images/coll_1.png" />
                    </span>
                    <div class="share jiathis_style_32x32" style="float: left;">
                        <a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank" style="background:url(<%=basePath%>common/images/share_1.png) no-repeat scroll 0 0"></a>
                    </div>
                </div>
            </div>
            
            
              <div class="navBar">
                <div class="nav wrapfix">
                    <div class="logo" onclick='forward("/index.do?tab=0")'></div>
                      <ul class="wrapfix" style="margin-top:2px;" id="navBar">
                            <li id="index" class="drop-menu-effect">  
                                <a href="index.do?tab=0" class="adlink"><img src="<%=basePath%>common/images/nav/index${ param.tab eq null or param.tab eq 0?'_1.jpg':'.jpg'}" alt="首页"/></a>
                             </li>
                            <li id="news" class="drop-menu-effect">
                                <a href="<%=basePath%>article/list.do?code=news&tab=1" class="adlink"><img src="<%=basePath%>common/images/nav/news${param.tab eq 1?'_1.jpg':'.jpg' }" alt="最新动态"/></a>
                                 <ul class="submenu">
                                  <li><a href="<%=basePath %>/article/list.do?code=news&tab=1">企业新闻</a></li>
                                    <li><a href="<%=basePath %>/article/mienList.do?tab=1">非泊风采</a></li>
                                    <li><a href="<%=basePath %>/article/list.do?code=active&tab=1">最新活动</a></li>
                                 </ul>
                             </li>
                            <li id="intro" class="drop-menu-effect">
                                <a href="<%=basePath%>skip/companyInfo.do?code=manager&tab=2" class="adlink"><img src="<%=basePath%>common/images/nav/company${param.tab eq 2?'_1.jpg':'.jpg' }" alt="公司介绍"/></a>
                                 <ul class="submenu">
                                  <li><a href="<%=basePath%>skip/companyInfo.do?code=manager&tab=2">总经理致辞</a></li>
                                    <li><a href="<%=basePath%>skip/companyInfo.do?code=about&tab=2">关于非泊</a></li>
                                    <li><a href="<%=basePath%>skip/companyInfo.do?code=organiz&tab=2">组织架构</a></li>
                                 </ul>
                            </li>
                          <li id="shop" class="drop-menu-effect">
                              <a href="<%=basePath%>store/queryStore.do?tab=3" class="adlink"><img src="<%=basePath%>common/images/nav/shop${param.tab eq 3?'_1.jpg':'.jpg' }" alt="门店展示"/></a>
                                 <ul class="submenu">
                                  <li><a href="<%=basePath%>store/queryStore.do?tab=3">非泊门店</a></li>
                                  <li><a href="<%=basePath%>store/viewAllCounselor.do?tab=3">门店顾问</a></li>
                                 </ul>
                            </li>
                          <li id="contact" class="drop-menu-effect">
                              <a href="<%=basePath%>skip/contact.do?tab=4" class="adlink"><img src="<%=basePath%>common/images/nav/contact${param.tab eq 4?'_1.jpg':'.jpg' }" alt="联系我们"/></a>
                          </li>
                          <li id="join" class="drop-menu-effect">
                            <a href="<%=basePath%>league/add.do?tab=5" class="adlink"><img src="<%=basePath%>/common/images/nav/join${param.tab eq 5?'_1.jpg':'.jpg' }" alt="咨询加盟"/></a>
                                 <ul class="submenu">
                                  <li><a href="<%=basePath%>league/add.do?tab=5">条件与政策</a></li>
                                    <li><a href="<%=basePath%>league/process.do?tab=5">加盟流程</a></li>
                                    <li><a href="<%=basePath%>league/appl.do?tab=5">门店加盟</a></li>
                                 </ul>
                           </li>
                            <li id="friend" class="drop-menu-effect">
                                <a href="<%=basePath%>recruiment/viewAll.do?tab=6" class="adlink"><img src="./common/images/nav/friend${param.tab eq 6?'_1.jpg':'.jpg' }" alt="伙伴招募"/></a>
                                 <ul class="submenu">
                                    <li><a href="<%=basePath%>recruiment/viewAll.do?tab=6">招兵买马</a></li>
                                    <li><a href="<%=basePath%>skip/appl.do?tab=6">职位申请</a></li>
                                 </ul>
                            </li>
>>>>>>> .r145
                    </ul>
                </div>
<<<<<<< .mine
            </div>
          
            <div id="navSlider" class="navSlider">
            <ul class="wrapfix" id="floatMenu">
                  <li id="index_slider" style="padding:0px;">
                    </li>
                    
                    <li id="news_slider">
                      <span class="nocur">
                  �ǲ�����
                        </span>
                        <span class="cur">
                            <font>�ǲ����</font>
                        </span>
                        <span class="nocur">
                            <font>���»</font>
                        </span>
                    </li>
                  
                     <li id="intro_slider">
                      <span class="nocur">
                  �ǲ�����
                        </span>
                        <span class="cur">
                            <font>�ǲ����</font>
                        </span>
                        <span class="nocur">
                            <font>���»</font>
                        </span>
                    </li>
                    
                     <li id="shop_slider">
                      <span class="nocur">
                  �ǲ�����
                        </span>
                        <span class="cur">
                            <font>�ǲ����</font>
                        </span>
                        <span class="nocur">
                            <font>���»</font>
                        </span>
                    </li>
                    
                    <li id="contact_slider">
                      <span class="nocur">
                  �ǲ�����
                        </span>
                        <span class="cur">
                            <font>�ǲ����</font>
                        </span>
                        <span class="nocur">
                            <font>���»</font>
                        </span>
                    </li>
                    
                    <li id="join_slider">
                      <span class="nocur">
                  �ǲ�����
                        </span>
                        <span class="cur">
                            <font>�ǲ����</font>
                        </span>
                        <span class="nocur">
                            <font>���»</font>
                        </span>
                    </li>
                    
                    <li id="friend_slider">
                      <span class="nocur">
                  �ǲ�����
                        </span>
                        <span class="cur">
                            <font>�ǲ����</font>
                        </span>
                        <span class="nocur">
                            <font>���»</font>
                        </span>
                    </li>
                </ul>
            </div>
        </div>
    </div>
 
