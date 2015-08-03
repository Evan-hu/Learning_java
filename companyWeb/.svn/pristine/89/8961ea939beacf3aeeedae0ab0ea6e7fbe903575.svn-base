<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script type="text/javascript">
 function sendSmsDialog(backFn){
	  var phoneNum = '${loginMember.mobile}';
		if(!phoneNum){
			alert("对不起，绑定手机号码！");
			window.location="/member/memberCenter/accountSecurity.do?type=mobile";
			return;
		}
		$("#dialogPhone").text(phoneNum);
		$.Dialog({
			width: 460,
			height: 290,
			title: "陆路联盟-短信验证",
			contentDom: '#DialogVeri'
		});
		$("#dialogPhoneBtn").click(function(){
			var authCode = $("#dialogAuthCode").val();
			  if(!authCode){
					alert('手机验证码不能为空');
				  return;
			  }
				backFn(authCode);
		});
	}
 
 	function getMobileAuthCodeDialog(){
		$.getJSON("/member/memberCenter/bindSendSms.do", function(e) {
			 alert(e.message);
		});
	}
</script>

<div class="dialogVeri" id="DialogVeri" style="display: none;">
	<div class="con">
		<h3>绑定手机：<span id="dialogPhone">13400000000</span></h3>
		<p><input id="dialogAuthCode" type="text" class="inputText"><a href="javascript:;" onclick="getMobileAuthCodeDialog();" class="getCode">获取验证码</a></p>
		<p>获取验证码可能存在延时</p>
		<p><a href="javascript:;" class="btnSubmit" id="dialogPhoneBtn">确 定</a></p>
	</div>
</div>

