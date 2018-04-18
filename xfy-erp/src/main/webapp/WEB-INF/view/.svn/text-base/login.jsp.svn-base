<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath }/resources/favicon.ico">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/left-sidebar/css/login.css" />
<style type="text/css">
#password-btn {
	display:inline-block;
	width: 55px;
	font-weight:bold;
	margin-right: 94px;
}

.x-btn-aw {
	margin-left: 0 !important;
	width: 90px !important;
}

#register-btn {
	margin-right: 31px;
}
</style>
</head>
<body>

<div id="login_wrapper">
	<div id="login">
		<div id="login_msg"><spring:message code="${message }" text="${message }" /></div>
		<form action="${pageContext.request.contextPath }/login" method="post">
			<input type="hidden" name="url" value="${param.url}" />
			<div class="form-row">
				<label for="username">用户名:</label>
				<input type="text" name="username" id="username" value="" placeholder="手机/邮箱" />
			</div>
			<div class="form-row">
				<label for="password">密　码:</label>
				<input type="password" name="password" id="password" value="" />
			</div>
			<div class="form-row">
				<a href="${pageContext.request.contextPath}/forget-password" id="password-btn">忘记密码?</a>
				<button type="submit" class="x-btn">登 录</button>
			</div>
			<div class="form-row" style="margin-top:20px;">
				<button type="button" class="x-btn x-btn-aw" id="register-btn">注册卖家</button>
				<button type="button" class="x-btn x-btn-aw" id="agent-btn">注册代理商</button>
			</div>
		</form>
	</div>
	
	<div style="text-align:center;padding-top:10%;">客服联系QQ 3274948164</div>
	<div style="text-align:center;padding-top:10px;">推荐最佳兼容浏览器: <a href="http://www.google.cn/intl/zh-CN/chrome/browser/desktop/index.html" target="_blank" rel="nofollow">Google Chrome</a></div>
	
</div>

<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script>
$(function() {	
	$('#register-btn').click(function() {
		window.location.href="${pageContext.request.contextPath }/register";
	});

	$('#agent-btn').click(function() {
		window.location.href="${pageContext.request.contextPath }/register-agent";
	});
	
	$("form").submit(function() {
		if ($.trim($('#username').val()) == '' || $.trim($('#password').val()) == '') {
			$('#login_msg').text('请输入完整登录信息');
			return false;
		}
		return true;
	});
});


</script>
</body>
</html>