// JavaScript Document
var page = {
	prev:$(".page").children(".forward").eq(0),
	next:$(".page").children(".forward").eq(1),
	init : function(){
		this.prev.hover(function(){
			changeImage(this,"_1.png", "_2.png");
		}, function(){
			changeImage(this,"_2.png", "_1.png");
		});
		
		this.next.hover(function(){
			changeImage(this,"_1.png", "_2.png");
		}, function(){
			changeImage(this,"_2.png", "_1.png");
		})
	}
} 

function changeImage(page, sourceSuffix, targetSuffix) {
	if (sourceSuffix == null || sourceSuffix == "" || targetSuffix == null || targetSuffix == "") {
		return ;
	}
		
	var img = $(page).find("img").eq(0);
	if(img.attr("src").indexOf(sourceSuffix) != -1){
		var path = img.attr("src").substring(0, img.attr("src").indexOf(sourceSuffix)) + targetSuffix;
		img.attr("src",path);
	}
}

page.init();