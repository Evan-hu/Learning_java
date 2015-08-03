// JavaScript Document
var login = {
	form:$("#form1"),
	user:$("#username"),
	pwd:$("#password"),
	auth:$("#authCode"),
	init: function(){
		this.user.focusin(function(){
			$("#password").removeClass("input_focus");
			$("#password").addClass("input_normal");
			$("#password").find("span").eq(0).removeClass("plaf");
			$("#password").find("span").eq(0).removeClass("pwd_logo_focus");
			$("#password").find("span").eq(0).addClass("pla");
			$("#password").find("span").eq(0).addClass("pwd_logo_normal");
			$("#authCode").removeClass("auth_focus");
			$("#authCode").addClass("auth_normal");
			$(this).removeClass("input_normal");
			$(this).addClass("input_focus");
			$(this).find("span").eq(0).removeClass("ula");
			$(this).find("span").eq(0).removeClass("user_logo_normal");
			$(this).find("span").eq(0).addClass("ulaf");
			$(this).find("span").eq(0).addClass("user_logo_focus");
		});
		
		this.user.focusout(function(){
			$(this).removeClass("input_focus");
			$(this).addClass("input_normal");
			$(this).find("span").eq(0).removeClass("ulaf");
			$(this).find("span").eq(0).removeClass("user_logo_focus");
			$(this).find("span").eq(0).addClass("ula");
			$(this).find("span").eq(0).addClass("user_logo_normal");
		});
		
		this.pwd.focusin(function(){
			$(this).removeClass("input_normal");
			$(this).addClass("input_focus");
			$(this).find("span").eq(0).removeClass("pla");
			$(this).find("span").eq(0).removeClass("pwd_logo_normal");
			$(this).find("span").eq(0).addClass("plaf");
			$(this).find("span").eq(0).addClass("pwd_logo_focus");					  
		});
		
		this.pwd.focusout(function(){
								   
		});
		this.auth.focusin(function(){
		});
		this.auth.focusout(function(){
									
		});
		
		this.form.submit(function(){
			if($("#username").val() == null || $("#username").val() == "") {
				alert("请输入用户名!");
				return false;
			}
			else if ($("#password").val() == null || $("#password").val() == "") {
				alert("请输入密码!");
				return false;
			}
			else if($("#authCode").val() == null || $("#authCode").val() == "") {
				alert("请输入验证码!");
				return false;
			}
			return true;
		});
	}
}

login.init();