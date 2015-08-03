// JavaScript Document
var err = {
	prev:$(".pr").find("img"),
	index:$(".ind").find("img"),
	init:function(){
		this.prev.hover(function(){
			focusIn(this);			
		}, function(){
			focusOut(this);
		});	
		
		this.index.hover(function(){
			focusIn(this);
		}, function(){
			focusOut(this);
		});
	},
	skipPrev:function(){
		history.go(-1);
	},
	skipIndex:function(){
		window.location.href = "/";	
	}
}

/**
*焦点放上去
*/
function focusIn(source) {
	if ($(source).attr("src").indexOf("_1.png") != -1) {
		var path = $(source).attr("src").substring(0, $(source).attr("src").indexOf("_1.png")) + "_2.png";
		$(source).attr("src", path);
	}
}

/**
*焦点离开
*/
function focusOut(source){
	if ($(source).attr("src").indexOf("_2.png") != -1) {
		var path = $(source).attr("src").substring(0, $(source).attr("src").indexOf("_2.png")) + "_1.png";
		$(source).attr("src", path);
	}
}

err.init();