<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>请登录</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="" name="description"/>
<meta content="" name="author"/>
<link href="${pageContext.request.contextPath }/resources/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath }/resources/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath }/resources/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath }/resources/assets/admin/pages/css/login.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath }/resources/assets/global/css/components-rounded.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath }/resources/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath }/resources/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath }/resources/assets/admin/layout/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="${pageContext.request.contextPath }/resources/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<link rel="shortcut icon" href="favicon.ico"/>
</head>
<body class="login">
<div class="menu-toggler sidebar-toggler">
</div>
<div class="logo">
	<a href="#" style="font-size:24px;">
	<%-- <img src="${pageContext.request.contextPath }/resources/assets/admin/layout3/img/logo-big.png" alt=""/> --%>
	CSS - 跨境交易系统
	</a>
</div>
<div class="content">
	<form class="login-form" action="${pageContext.request.contextPath }/login" method="post">
		<input type="hidden" name="url" value="${param.url}" />
		<h3 class="form-title">请输入登陆信息</h3>
		<div class="alert alert-danger display-hide">
			<button class="close" data-close="alert"></button>
			<span>请输入用户名和密码</span>
		</div>
		<%@include file="/WEB-INF/view/include/message.jsp" %>
		<div class="form-group">
			<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
			<label class="control-label visible-ie8 visible-ie9">用户名</label>
			<input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="用户名" name="username" />
		</div>
		<div class="form-group">
			<label class="control-label visible-ie8 visible-ie9">密码</label>
			<input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="密码" name="password"/>
		</div>
		<div class="form-actions">
			<div class="row">
				<div class="col-md-6">
					&nbsp;
				</div>
				<div class="col-md-6 text-right">
					<button type="submit" class="btn btn-success uppercase">登 录</button>
				</div>
			</div>
		</div>
		<div class="create-account">
			<p>
				&nbsp;
			</p>
		</div>
	</form>
</div>
<div class="copyright">
	 2014 © CSS - 跨境交易系统.
</div>

<!--[if lt IE 9]>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/respond.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${pageContext.request.contextPath }/resources/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<%-- <script src="${pageContext.request.contextPath }/resources/assets/admin/pages/scripts/login.js" type="text/javascript"></script> --%>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
jQuery(document).ready(function() {     
	Metronic.init(); // init metronic core components
	Layout.init(); // init current layout
	/* Login.init(); */
	Demo.init();
});

$('.login-form').validate({
	rules : {
		username : {
			required : true
		},
		password : {
			required : true
		},
		remember : {
			required : false
		}
	},
	invalidHandler : function(event, validator) { //display error alert on form submit   
		$('.alert-danger', $('.login-form')).show();
	},

	errorPlacement : function(error, element) {
		error.insertAfter(element.closest('.input-icon'));
	}
});

$('.login-form input').keypress(function(e) {
	if (e.which == 13) {
		if ($('.login-form').validate().form()) {
			$('.login-form').submit();
		}
		return false;
	}
});
$('.login-form input[name=username]').focus();
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>