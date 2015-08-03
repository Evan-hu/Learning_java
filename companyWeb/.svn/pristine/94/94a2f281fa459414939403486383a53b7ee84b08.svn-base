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
  <title>申请</title>
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
  <!-- 这是最新修改的  -->
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
             <!-- 这个是最新添加的 -->
              <div class="cNav">
                <div class="navPanel">
                   <!-- 这里是最新修改的 -->
                   <div class="navItem" onclick="forward('<%=basePath%>recruiment/viewAll.do?tab=6')">招兵买马</div>
                   <div class="navItem curItem" onclick="forward('<%=basePath%>skip/appl.do?tab=6')">职位申请</div>
                </div>
              </div>
                    
              <div class="content">
                <p class="rp">
                      <span class="myscore">职位申请</span>
                 </p>
                  <div class="mcd">
                    <p class="mtitle">
                       <!-- 这是最新修改的 -->
                      <span class="lv">基本信息</span>
                      <span class="rv"></span>
                    </p>
                    <form action="recruiment/applRecriment.do" method="post" enctype="multipart/form-data" id="form1">
                        <div class="mi">
                            <ul class="wrapfix">
                                <li>
                                  <span class="label">职位申请</span>
                                  <span class="mv" style="margin-left:-4px;">
                                      <input type="text" name="job" id="job" maxlength="40" class="job"/>
                                  </span>
                                </li>
                                <li style="list-style-type:none;" class="rd">
                                  <ul class="wrapfix">
                                      <li>
                                          <span class="la">姓名</span>
                                            <span class="va">
                                              <input type="text" name="name" id="name" class="truename" />
                                            </span>
                                        </li>
                                        <li>
                                          <span class="la">出生日期</span>
                                            <span class="va"> 
                                              <input type="text" name="birthdate" id="birthdate" class="birthday" onclick="SelectDate(this,'yyyy\-MM\-dd')"/>
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
                                              <span class="sexPanel"><input type="radio" name="sex" id="sex" value="1" class="sex" />男</span>
                                              <span class="sexPanel"><input type="radio" name="sex" id="sex" value="0" class="sex"/>女</span>
                                            </span>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                            
                            <div class="uz">
                              <p class="mtitle">
                                <span class="lv">上传简历</span>
                                <span class="rv"></span>                                                       
                             </p> 
                                <div class="upload">
                                  <span class="fileLogo"></span>
                                    <span class="uploadifyFile">
                                      <input type="text" name="resumePath" id="resumePath" value="简历采用WORD编辑的DOC文件格式上传" disabled="disabled"/>
                                    </span>
                                      <input type="file" name="uploadify" id="uploadify"  />
                                </div>
                            </div>
                            
                            <div class="submitPanel">
                              <input type="hidden" name="RECRUIMENT_ID" id="RECRUIMENT_ID">
                              <input type="submit" class="submit" value=""/>
                            </div>
                        </div>
                    </form>
                  </div>
                </div>
                <div class="clear"></div>
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
<script type="text/javascript" src="<%=basePath%>js/Calendar.js" charset="utf-8"></script>
  <script type="text/javascript">
  $(function(){
    $(document).ready(function () {
        $("#uploadify").uploadify({
          uploader : '<%=basePath%>common/upload/scripts/uploadify.swf',
          method : 'post',
          script : '<%=basePath%>file/uploadResume.do',
          buttonText : '上传简历',
          buttonImage : '<%=basePath%>common/upload/up_img.png',
          cancelImg : '<%=basePath%>common/upload/cancel.png',
          queueID : 'fileQueue',
          auto : true,
          multi : false,
          simUploadLimit : 1,
          fileExt : '*.doc',
          fileDesc : '*.doc',
          onComplete : function(event, queueId, fileObj, response, data) {
          	 $("#resumePath").val(response);
          },
          onError:function(event,ID,fileObj){
          	alert("未知错误!");
          } 
      });
        
      $("#form1").submit(function(){
    	   if ($("#job").val() == null || $("#job").val() == "") {
    		   alert("请填写你要申请的职位!");
    		   return false;
    	   } 
    	   else if ($("#name").val() == null || $("#name").val() == "") {
    		   alert("请填写姓名!");
    		   return false;
    	   }
    	   else if ($("#birthdate").val() == null || $("#birthdate").val() == "") {
    		   alert("请选择出生日期!");
    		   return false;
    	   }
    	   else if ($("#tel").val() == null || $("#tel").val() == "") {
    		   alert("请填写联系电话!");
    		   return false;
    	   }
    	   else if ($("#sex").val() == null || $("#sex").val() == ""){
    		   alert("请选择性别!");
    		   return false;
    	   }
    	   
    	   return true;
      });
    
      function onInit() {
    	  var msg = "${msg eq null ? '':msg}";
        if (msg != null && msg != "") {
            alert(msg);
        }
      }
      
      onInit();
    });   
  });
</script> 
</html>