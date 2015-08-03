var service = {
  navService:$(".home_service"),
  init:function() {
    $.each(this.navService.find("div").eq(0).find("span"), function(key, value){
      $(value).hover(function(){
        $(this).stop(true, true).animate({top:'-=30'},"fast", function(){
          var target = $(this).children().eq(0);
          if (target.attr("src").indexOf("_1.png") != -1) {
            target.attr("src", target.attr("src").substring(0, target.attr("src").indexOf("_1.png")) + "_2.png");
          }
        });         
      }, 
      function(){
        $(this).stop(true, true).animate({top:'+=30'},"fast", function(){
          var target = $(this).children().eq(0);
          if (target.attr("src").indexOf("_2.png") != -1) {
            target.attr("src", target.attr("src").substring(0, target.attr("src").indexOf("_2.png")) + "_1.png"); 
          }                        
        }); 
      });
    });
  }
}

$(function(){
  $(document).ready(function(){
    $("#adPrev").hover(function(){
      $(this).find("img").eq(0).attr("src","./images/prev_2.png");
    }, function(){
       $(this).find("img").eq(0).attr("src","./images/prev_1.png");
    }) ;
    
    $("#adNext").hover(function(){
      $(this).find("img").eq(0).attr("src","./images/next_2.png");
    }, function(){
      $(this).find("img").eq(0).attr("src","./images/next_1.png");
    }) ;
  });
});

service.init();