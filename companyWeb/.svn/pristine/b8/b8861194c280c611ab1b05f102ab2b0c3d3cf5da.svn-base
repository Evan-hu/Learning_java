$(function(){
     $('img').click(function(){
       var result = $(this).attr("id");
       if("fable" == result){
         location.href ="article/mienList.do?sortId=10";
       } else if("news" == result){
         location.href ="article/list.do?sortId=4";
       } else if("stores" == result){
         location.href ="store/queryStore.do";
       } else if("bc" == result){
         location.href ="store/viewAllCounselor.do";
       } else if("join" == result){
         location.href ="league/add.do";
       } else if("contact" == result){
         location.href="skip/contact.do";
       } else if("friend" == result){
         location.href="skip/appl.do";
       } else if("advise" == result){
         alert("建设中....");
       } else if("nav" == result){
         alert("建设中....");
       }
     });
   }); 
   
   
   //会员中心相关
   function login(memberId){
     if(memberId != null){
         location.href="user/goto.do?type=3&&memberId="+memberId;
     } else {
       alert("请登录!");
       location.href="user/gotoval.do?type=1";
     }
   }
   
   //跳转到供应商中心
   function providerLogin(){
     alert("Waiting......");
     //location.href="";
   }
   

 function dropMenu(obj){
     $(obj).each(function(){
       var theSpan = $(this);
       var theMenu = theSpan.find(".submenu");
       var tarHeight = theMenu.outerHeight();
       theMenu.css({height:0,opacity:0});
       
       var t1;
       
       function expand() {
         clearTimeout(t1);
         var img = theSpan.find('a').find('img');
         if (img.attr("src").indexOf("_1.jpg") == -1) {
           if (img.attr("src").indexOf("_2.jpg") == -1){
             img.attr("src",img.attr("src").substring(0, img.attr("src").indexOf(".jpg")) +"_2.jpg");
           }
         }
         theMenu.stop().show().animate({height:tarHeight,opacity:1},200);
       }
       
       function collapse() {
         clearTimeout(t1);
         t1 = setTimeout(function(){
           var img = theSpan.find('a').find('img');
           if (img.attr("src").indexOf("_1.jpg") == -1) {
             if (img.attr("src").indexOf("_2.jpg") != -1){
               img.attr("src",img.attr("src").substring(0, img.attr("src").indexOf("_2.jpg")) +".jpg");
             }
           }
           theMenu.stop().animate({height:0,opacity:0},200,function(){
             //$(this).css({display:"none"});
           });
         }, 200);
       }
       
       theSpan.hover(expand, collapse);
       theMenu.hover(expand, collapse);
     });
   }
   
   dropMenu(".drop-menu-effect");