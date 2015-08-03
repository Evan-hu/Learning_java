// JavaScript Document
var member = {
	truename:$(".truename"),
	sex:$(".sex"),
	year:$(".year"),
	month:$(".month"),
	date:$(".day"),
	tel:$(".tel"),
	apply:$(".apply"),
	code:$(".code"),
	sure:$(".sure"),
	address:$(".address"),
	email:$(".email"),
	submitBtn:$(".submit"),
	init: function(){
		this.submitBtn.submit(function(){
			return false;
		});
	}
}