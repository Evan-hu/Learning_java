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
  <title>My JSP 'test.jsp' starting page</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">    
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" href="<%=basePath%>common/css/common.css" />
  <link rel="stylesheet" href="<%=basePath%>common/upload/css/uploadify.css" />
  <link rel="stylesheet" href="<%=basePath%>css/recruitcss/apply.css" />
  </head>
  
  <body>
  <!-- header -->
  <jsp:include page="../common/header.jsp"/>
  <div class="hl">
  <div class="mh">
              当前位置 : 职位申请
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
                 <div class="navItem" onclick="forward('<%=basePath%>recruiment/viewAll.do')">招兵买马</div>
                  <div class="navItem curItem" onclick="forward('<%=basePath%>skip/appl.do')">职位申请</div>
                 </div>
               </div> 
              <div class="content">
                <p class="rp">
                      <span class="myscore">职位申请</span>
                 </p>
                     
                  <div class="mcd">
                    <p class="mtitle">基本信息</p>
                    <form action="" method="post" enctype="multipart/form-data">
                        <div class="mi">
                            <ul class="wrapfix">
                                <li><span class="label">职位申请</span>
                                    <span class="mv" style="margin-left:-4px;">
                                        <input type="text" name="job" id="job" maxlength="40" class="job"/>
                                    </span>
                                </li>
                                <li style="list-style-type:none;" class="rd">
                                  <ul class="wrapfix">
                                      <li>
                                          <span class="la">姓名</span>
                                            <span class="va">
                                              <input type="text" name="truename" id="truename" class="truename" />
                                            </span>
                                        </li>
                                        <li>
                                          <span class="la">出生日期</span>
                                            <span class="va">
                                              <input type="text" name="year" id="year" class="year" onclick="SelectDate(this,'yyyy')" />年
                                            <input type="text" name="month" id="month" class="month" onclick="SelectDate(this,'MM')" />月
                                            </span>
                                        </li>
                                    </ul>
                                </li>
                                <li class="rd" style="list-style-type:none;" class="rd">
                                  <ul class="wrapfix">
                                      <li>
                                          <span class="la">联系电话</span>
                                            <span class="va">
                                              <input type="text" name="tel" id="tel" class="tel" maxlength="15" />
                                            </span>
                                        </li>
                                        <li>
                                          <span class="la" style="width:44px;">性别</span>
                                            <span class="va">
                                              <span class="sexPanel"><input type="radio" name="sex" id="sex" value="男" class="sex" />男</span>
                                            <span class="sexPanel"><input type="radio" name="sex" id="sex" value="女" class="sex"/>女</span>
                                            </span>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                            
                            <div class="uz">
                              <p>
                                  上传简历
                                </p> 
                                <div class="upload">
                                  <span class="fileLogo"></span>
                                    <span class="uploadifyFile">
                                      <input type="text" name="uploadintro" id="uploadintro" value="简历采用WORD编辑的DOC文件格式上传" disabled="disabled"/>
                                    </span>
                                      <input type="file" name="uploadify" id="uploadify"  />
                                </div>
                            </div>
                            
                            <div class="submitPanel">
                              <input type="button" class="submit" />
                                <input type="hidden" id="docUrl" name="docUrl" />
                            </div>
                        </div>
                    </form>
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
<script type="text/javascript" src="<%=basePath%>common/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>common/upload/scripts/swfobject.js"></script>
<script type="text/javascript" src="<%=basePath%>common/upload/scripts/jquery.uploadify.v2.1.4.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/button.skip.js"></script>
<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1399183794965592" charset="utf-8"/></script>
<script type="text/javascript" src="<%=basePath%>common/js/Calendar.js"></script>
  <script type="text/javascript">
  $(function(){
    $(document).ready(function () {
             $("#uploadify").uploadify({
        'uploader' : '../common/upload/scripts/uploadify.swf',
        'method' : 'post',
        'script' : '/join',
        'buttonImage' : '../common/upload/up_img.png',
        'cancelImg' : '../common/upload/cancel.png',
        'queueID' : 'fileQueue',
        'auto' : true,
        'multi' : true,
        'simUploadLimit' : 1,
        'fileExt' : '*.doc',
        'fileDesc' : '*.doc',
        'buttonText' : '上传简历',
        'onComplete' : function(event, queueId,fileObj, response, data) {
          //  alert(response);
          $('#docUrl').val(response);
    
        }
      });

        });   
  });
</script> 
</html>