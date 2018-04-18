<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Timer" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>忘记密码</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath }/resources/favicon.ico">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/left-sidebar/css/login.css" />
</head>
<body>
	<div class="container">
		<div id="login_wrapper">
			<div id="login">
				<div style="margin: 0 auto; width: 250px; height: 20px;">
					<h3>请输入您需要找回登录密码的手机号</h3>
				</div>
				<div id="login_msg">
					<spring:message code="${message }" text="${message }"/>
				</div>
				<form action="${pageContext.request.contextPath }/forget-password" id="forgetPwd-form" method="POST">
					<div class="form-row">
						<label>手机号</label> <input type="text" name="mobile" id="mobile" autocomplete="off" value="${mobile}"/>
					</div>
					<div>
						<button class="x-btn" id="authCodeBtn" style="width:auto;">
						<span id="dyMobileButton">获取短信验证码</span></button>
						<span id="msg" style="display:none"><strong id="timer"></strong>秒后重新获取</span>
					</div>
					<div class="form-row">
						<label>验证码</label> <input type="text" name="authCode" id="authCode" value="" autocomplete="off" />
					</div>
					<div class="form-row">
						<%-- <a href="${pageContext.request.contextPath }/register">注册</a> --%>
						<button type="submit" class="x-btn" id="forgetPwd-btn" style="width: 100px;">提交</button>
					</div>
				</form>
			</div>

			<div style="text-align:center;padding-top:10%;">客服联系QQ 3274948164</div>
			<div style="text-align:center;padding-top:10px;">推荐最佳兼容浏览器: <a href="http://www.google.cn/intl/zh-CN/chrome/browser/desktop/index.html" target="_blank" rel="nofollow">Google Chrome</a></div>
		
		</div>
	</div>
	
	<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/resources/master.js" type="text/javascript"></script>
	<script>
		$(function() {	
			
			  $('#forgetPwd-form').validate({
				errorElement : 'span', //default input error message container
				errorClass : 'help-block error', // default input error message class
				focusInvalid : false, // do not focus the last invalid input
				ignore : "", // validate all fields including form hidden input
				messages : {
					mobile: {remote:'此手机号未注册，请注册！', regex: '请输入正确手机号！'},
					authCode: {remote: '验证码不正确'},
				},
				rules : {
					mobile: {
						required : true,
						digits: true,
						regex: xfy.regex.mobile,
						remote: {
							url: "${pageContext.request.contextPath }/user/check-mobile.json"
						}
					},
					
					authCode: {
						required : true,
						minlength: 4,
						maxlength: 4,
						remote: {
							url: "${pageContext.request.contextPath }/check-auth-code.json",
							data: {
								mobile: function () {
									return $("#mobile").val();
								}
							}
						}
						
					}
					
				}
	
			});
			 
			  var count=0;
			  var flag=null;
			  var val = null;
			  var $codeBtn = $('#authCodeBtn');
			  function sendMobileCode(){
			    if(count == 0){
				    $codeBtn.attr("disabled",false);
				    $('#timer').text(count);
				    $codeBtn.removeClass("code-btn");
				    clearInterval(flag);
				    $codeBtn.text("获取短信验证码");
			    }else{
				    $('#timer').text(count--);
				    val = $('#msg').text();
				    $codeBtn.text(val);
			    }
			  } 
			  
			$('#authCodeBtn').click(function() {
				var valid = $('#forgetPwd-form').validate().element($('input[name=mobile]'));
				// 手机号验证通过后再能申请发送验证码
				
				 if (valid === true) {
					var url = '${pageContext.request.contextPath}/get-auth-code.json?mobile=' + $('#mobile').val();
					$.get(url, function(r){
						alert(r === true ? '验证码已发送成功，请留意手机短信！' : '验证码发送失败，请重试，或检查网络。');
							if(r === true & count==0){
							    count=60;
							 	$('#timer').text(count);
							    val = $('#msg').text();
							    $codeBtn.text(val);
							    flag=setInterval(sendMobileCode,1000);
							    $codeBtn.attr("disabled",true);
							    $codeBtn.addClass("code-btn");
						    }
						    else{
						      	return;
						    } 
					}, 'json'); 
					
				} else {
					alert('请修正手机号后再申请验证码');
				}  
				
			});
			
		});
	
	</script>
		
</body>
</html>