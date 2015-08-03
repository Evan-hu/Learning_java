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
  <title>门店掠影</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">    
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" href="<%=basePath%>common/css/common.css" />
  <link rel="stylesheet" href="<%=basePath%>css/storecss/photography.css" />
  <link rel="stylesheet" href="<%=basePath%>css/storecss/photo_effect.css" />
  
  </head>
  
  <body>
  <!-- header -->
  <jsp:include page="../common/header.jsp"/>
    <div class="hl">
      <div class="mh">
                当前位置:<a href="store/queryStore.do?tab=3">非泊门店</a> > <a href="store/detail.do?storeId=${storeId}&&?tab=3">门店详情</a> > 门店掠影 
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
                      <span class="myscore">店铺摄影</span>
                     </p>
                         
                <div class="mcd">
                          <DIV class="MainBg">
                                <DIV class="HS10"></DIV>                                
                                <DIV class="Title">                                
                                <H1>${storeName}</H1><SPAN class="Counter">（<SPAN class="CounterCurrent">1</SPAN>/${size}）</SPAN></DIV>
                                <DIV class="SpaceLine"></DIV>
                                <DIV class="OriginalPicBorder">
                                <DIV id="OriginalPic">
                                <DIV id="aPrev" class="CursorL"></DIV>
                                <DIV id="aNext" class="CursorR"></DIV>
                              <c:forEach var="i" items="${page.results}">
                                <P class="Hidden">
                                  <SPAN class="SliderPicBorder FlRight">
                                  <IMG src="<%=basePath%>${i.imgUrl}">
                                  </SPAN>
                                  <SPAN class="Clearer"></SPAN>
                                  <SPAN class="Summary FlLeft">
                                  ${i.content}
                                  </SPAN>
                                </P>
                              </c:forEach>
                             </DIV>
                             </DIV>
                                <DIV class="SpaceLine"></DIV>
                                <DIV class="HS15"></DIV>
                                <DIV class="ThumbPicBorder">
                                  <IMG style="cursor: pointer;" id="btnPrev" class="FlLeft" src="<%=basePath%>images/shopimages/prev_1.png">
                                  <DIV class="jCarouselLite FlLeft">
                                
                                        <UL id="ThumbPic">
                                        <c:forEach var="i" items="${page.results}" varStatus="status">
                                        <LI rel="${status.count}"><IMG src="<%=basePath%>${i.imgUrl}"></LI>
                                        </c:forEach>
                                        </UL>
                                        <DIV class="Clearer"></DIV>
                                  </DIV>
                                <IMG style="cursor: pointer;" id="btnNext" class="FlRight" src="<%=basePath%>images/shopimages/next_1.png">
                                <DIV class="Clearer"></DIV></DIV>
                                <DIV class="HS15"></DIV></DIV>   
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
  <script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1399183794965592" charset="utf-8"></script>
  <script type="text/javascript" src="<%=basePath%>js/jquery.jcarousellite.min.js"></script>
<script type="text/javascript">
//缩略图滚动事件
$(".jCarouselLite").jCarouselLite({
  btnNext: "#btnNext",
  btnPrev: "#btnPrev",
  scroll: 1,
  speed: 240,
  circular: false,
  visible: 5 //显示图片个数
});
</script>
<script type="text/javascript">
var currentImage;
var currentIndex = -1;

//显示大图(参数index从0开始计数)
function showImage(index){

  //更新当前图片页码
  $(".CounterCurrent").html(index + 1);

  //隐藏或显示向左向右鼠标手势
  var len = $('#OriginalPic img').length;
  if(index == len - 1){
    $("#aNext").hide();
  }else{
    $("#aNext").show();
  }

  if(index == 0){
    $("#aPrev").hide();
  }else{
    $("#aPrev").show();
  }

  //显示大图            
  if(index < $('#OriginalPic img').length){
    var indexImage = $('#OriginalPic p')[index];

    //隐藏当前的图
    if(currentImage){
      if(currentImage != indexImage){
        $(currentImage).css('z-index', 2);  
        $(currentImage).fadeOut(0,function(){
          $(this).css({'display':'none','z-index':1})
        });
      }
    }

    //显示用户选择的图
    $(indexImage).show().css({'opacity': 0.4});
    $(indexImage).animate({opacity:1},{duration:200});

    //更新变量
    currentImage = indexImage;
    currentIndex = index;

    //移除并添加高亮
    $('#ThumbPic img').removeClass('active');
    $($('#ThumbPic img')[index]).addClass('active');

    //设置向左向右鼠标手势区域的高度                        
    //var tempHeight = $($('#OriginalPic img')[index]).height();
    //$('#aPrev').height(tempHeight);
    //$('#aNext').height(tempHeight);                        
  }
}

//下一张
function ShowNext(){
  var len = $('#OriginalPic img').length;
  var next = currentIndex < (len - 1) ? currentIndex + 1 : 0;
  showImage(next);
}

//上一张
function ShowPrep(){
  var len = $('#OriginalPic img').length;
  var next = currentIndex == 0 ? (len - 1) : currentIndex - 1;
  showImage(next);
}

//下一张事件
$("#aNext").click(function(){
  ShowNext();
  if($(".active").position().left >= 127 * 5){
    $("#btnNext").click();
  }
});

//上一张事件
$("#aPrev").click(function(){
  ShowPrep();
  if($(".active").position().left <= 127 * 5){
    $("#btnPrev").click();
  }
});

//初始化事件
$(".OriginalPicBorder").ready(function(){
  ShowNext();

  //绑定缩略图点击事件
  $('#ThumbPic li').bind('click',function(e){
    var count = $(this).attr('rel');
    showImage(parseInt(count) - 1);
  });
});

$("#aPrev").hover(function(){
  $(this).removeClass("CursorL");
  $(this).addClass("CurrentCursorL");
}, function(){
  $(this).removeClass("CurrentCursorL");
  $(this).addClass("CursorL");
});

$("#aNext").hover(function(){
  $(this).removeClass("CursorR");
  $(this).addClass("CurrentCursorR");
}, function(){
  $(this).removeClass("CurrentCursorR");
  $(this).addClass("CursorR");
});

$("#btnPrev").hover(function(){
  window.changeImage(this, "_1.png", "_2.png"); 
}, function(){
  window.changeImage(this, "_2.png", "_1.png"); 
});

$("#btnNext").hover(function(){
  changeImage(this, "_1.png", "_2.png"); 
}, function(){
  changeImage(this, "_2.png", "_1.png"); 
});

function changeImage(obj, sourceSuffix, targetSuffix) {
  if (sourceSuffix == null || sourceSuffix == "" || targetSuffix == null || targetSuffix == "") {
    return ;
  }
    
  var img = $(obj);
  if(img.attr("src").indexOf(sourceSuffix) != -1){
    var path = img.attr("src").substring(0, img.attr("src").indexOf(sourceSuffix)) + targetSuffix;
    img.attr("src",path);
  }
}

</script>
</html>
