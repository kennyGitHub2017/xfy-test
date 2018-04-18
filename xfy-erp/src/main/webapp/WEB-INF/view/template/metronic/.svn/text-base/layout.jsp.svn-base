<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%-- 设置整体页面布局： container, container-fluid --%>
<c:set var="layoutClass" value="container-fluid" scope="request" />
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<meta charset="utf-8"/>
<title>CSS | <tiles:insertAttribute name="title" ignore="true" /></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="" name="description"/>
<meta content="" name="author"/>
<%-- 全局样式开始 --%>
<%-- <link href="${pageContext.request.contextPath }/resources/assets/global/css/google.fonts.css" rel="stylesheet" type="text/css"> --%>
<link href="${pageContext.request.contextPath }/resources/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/resources/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/resources/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-toastr/toastr.min.css" rel="stylesheet" type="text/css">
<%-- 全局样式结束 --%>
<%-- 当前页面级别的样式开始 --%>
<tiles:insertAttribute name="css-page" ignore="true" />
<%-- 当前页面级别的样式结束 --%>
<%--  主题相关的样式开始 --%>
<link href="${pageContext.request.contextPath }/resources/assets/global/css/components-rounded.css" id="style_components" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/resources/assets/global/css/plugins.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/resources/assets/admin/layout3/css/layout.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/resources/assets/admin/layout3/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color">
<link href="${pageContext.request.contextPath }/resources/assets/admin/layout3/css/custom.css" rel="stylesheet" type="text/css">
<%--  主题相关的样式结束 --%>
<link rel="shortcut icon" href="favicon.ico"/>
<script type="text/javascript">
var xfy = window.xfy || {};
xfy.contextPath = '${pageContext.request.contextPath }';
</script>
</head>
<body class="page-header-menu-fixed">
<!-- 页面头部 -->
<tiles:insertAttribute name="page-header" />
<div class="page-container">
	<div class="page-head">
		<div class="${layoutClass }">
			<%-- 页面大标题 --%>
			<div class="page-title">
				<h1><tiles:insertAttribute name="title" ignore="true" /></h1>
			</div>
		</div>
	</div>
	<%-- 页面内容区 --%>
	<div class="page-content">
		<div class="${layoutClass }">
			<%-- 面包屑导航 --%>
			<%-- 
			<!-- BEGIN PAGE BREADCRUMB -->
			<ul class="page-breadcrumb breadcrumb">
				<li>
					<a href="#">Home</a><i class="fa fa-circle"></i>
				</li>
				<li>
					<a href="layout_fluid.html">Features</a>
					<i class="fa fa-circle"></i>
				</li>
				<li class="active">
					 Fluid Layout
				</li>
			</ul>
			<!-- END PAGE BREADCRUMB -->
			--%>
			
			<%-- 主要内容区 --%>
			<tiles:insertAttribute name="page-content" />
			<%-- 主要内容区 --%>
		</div>
	</div>
</div>
<%-- 页脚 --%>
<tiles:insertAttribute name="page-footer" />

<%-- javascript 引入 --%>
<%-- 核心javascript插件 --%>
<!--[if lt IE 9]>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/respond.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<%-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip --%>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-toastr/toastr.min.js" type="text/javascript"></script>
<%-- 核心javascript插件 --%>
<script src="${pageContext.request.contextPath }/resources/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/admin/layout3/scripts/layout.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/admin/layout3/scripts/demo.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/master.js" type="text/javascript"></script>
<script>
jQuery(document).ready(function() {    
	Metronic.init(); // init metronic core components
	Layout.init(); // init current layout
	Demo.init(); // init demo features
});
</script>

<%-- 当前页面级别javascript插件 --%>
<tiles:insertAttribute name="js-page" ignore="true" />

</body>
</html>