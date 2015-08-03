var index = {
	member:$(".ml"),
	provider:$(".su"),
	collection:$(".coll"),
	pageLink:$(".link"),
	share:$(".share"),
	init:function() {
		$.each(this.pageLink.find("a"), function(key, value){
			$(value).hover(function(){
				var img = $(this).find("span").eq(0).find("img");
				if (img.attr("src").indexOf("_1.png") != -1) {
					var path = img.attr("src").substring(0, img.attr("src").indexOf("_1.png")) + "_2.png";
					img.attr("src",path);
				}
			}, function(){
				var img = $(this).find("span").eq(0).find("img");
				if (img.attr("src").indexOf("_2.png") != -1) {
					var path = img.attr("src").substring(0, img.attr("src").indexOf("_2.png")) + "_1.png";
					img.attr("src",path);
				}
			});
		});
		
		this.member.hover(function(){
			var img = $(this).children().eq(0);
			if (img.attr("src").indexOf("_1.png") != -1) {
				var path = img.attr("src").substring(0, img.attr("src").indexOf("_1.png")) + "_2.png";
				img.attr("src",path);
			}
		}, function(){
			var img = $(this).children().eq(0);
			if (img.attr("src").indexOf("_2.png") != -1) {
				var path = img.attr("src").substring(0, img.attr("src").indexOf("_2.png")) + "_1.png";
				img.attr("src",path);
			}
		});
		
		this.provider.hover(function(){
			var img = $(this).children().eq(0);
			if (img.attr("src").indexOf("_1.png") != -1) {
				var path = img.attr("src").substring(0, img.attr("src").indexOf("_1.png")) + "_2.png";
				img.attr("src",path);
			}
		}, function(){
			var img = $(this).children().eq(0);
			if (img.attr("src").indexOf("_2.png") != -1) {
				var path = img.attr("src").substring(0, img.attr("src").indexOf("_2.png")) + "_1.png";
				img.attr("src",path);
			}
		});
		
		this.collection.hover(function(){
			var img = $(this).children().eq(0);
			if (img.attr("src").indexOf("_1.png") != -1) {
				var path = img.attr("src").substring(0, img.attr("src").indexOf("_1.png")) + "_2.png";
				img.attr("src",path);
			}
		}, function(){
			var img = $(this).children().eq(0);
			if (img.attr("src").indexOf("_2.png") != -1) {
				var path = img.attr("src").substring(0, img.attr("src").indexOf("_2.png")) + "_1.png";
				img.attr("src",path);
			}
		});
		
		this.share.hover(function(){
			var source = $(this).children(".jiathis").eq(0);
			if (source.css("background-image").indexOf("_1.png") != -1) {
			   var path = source.css("background-image").substring(0, source.css("background-image").indexOf("_1.png")) +'_2.png")';
         source.css("background-image", path);
         
			}
		}, function(){
			var source = $(this).children(".jiathis").eq(0);  
			 if (source.css("background-image").indexOf("_2.png") != -1) {
         var path = source.css("background-image").substring(0, source.css("background-image").indexOf("_2.png")) +'_1.png")';
         source.css("background-image", path);
      }
		});	
	},
	login:function(){
		window.location.href = "";
	},
	providerLogin:function(){
		window.location.href = "";
	},
	collectionPage: function() {
		if (document.all) {
			window.external.addFavorite('http://www.fable360.com','鎴愰兘闈炴硦浼佷笟绠＄悊鏈夐檺鍏徃');
		}
		else if (window.sidebar){
			window.sidebar.addPanel('鎴愰兘闈炴硦浼佷笟绠＄悊鏈夐檺鍏徃', 'http://www.fable360.com', "");
		}
	}
}

function forward(url) {
	window.location.href = url;
}

$(function(){
  $(document).ready(function(){
    $(".navPanel").css("height", parseInt($(".mc").outerHeight()) - 3);
  });
});

index.init();