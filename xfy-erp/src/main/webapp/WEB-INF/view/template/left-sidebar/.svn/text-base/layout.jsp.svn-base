<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]--> 
<head>
<title>CSS | <tiles:insertAttribute name="title" ignore="true" /></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="" name="description"/>
<meta content="" name="author"/>
<link rel="shortcut icon" href="${pageContext.request.contextPath }/resources/favicon.ico">
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
<script type="text/javascript">
var xfy = window.xfy || {};
xfy.contextPath = '${pageContext.request.contextPath }';
</script>

<link href="${pageContext.request.contextPath }/resources/left-sidebar/css/default.css" rel="stylesheet" />
</head>
<body>
	<div class="default_main">
		<div class="main_top" id="main_top">
			<div class="header_left">
				<div class="logo"></div>
			</div>
			<div class="header_right">
				<div>
					<div class="top">
						<div class="help">
							<a href="javascript:showabout2();">充值流程</a><a href="#">关于</a>
						</div>
					</div>
					<div class="navb">
						<div class="nav">
							<li><a href="${pageContext.request.contextPath }/">首页</a></li>
							<li><a href="${x:getConfig('kandeng.base.url')}/ebay/profile/ProfileEdit_1_onekey.aspx" target="_blank">ebay刊登</a></li>
							<li><a href="${x:getConfig('kandeng.base.url')}/smt/SelectItem.aspx">速卖通刊登</a></li>
							<li><a href="${x:getConfig('kandeng.base.url')}/amazon/myproductlist.aspx">AM刊登</a></li>
							<li><a href="${x:getConfig('kandeng.base.url')}/ui/ProductList.aspx">产品管理</a></li>
							<li><a href="${x:getConfig('kandeng.base.url')}/UserControl/SelectSkuLibraryManager.aspx">图片管理</a></li>
							<li><a href="${pageContext.request.contextPath }/order/seller-order">订单管理</a></li>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="default_Prompt">
			<div class="prompt_z">
				<span>欢迎您 : ${sessionUser.name}</span>
				<span><a href="${pageContext.request.contextPath }/my/password">修改密码 </a></span>
				<span><a href="${pageContext.request.contextPath }/logout">退出</a></span>
			</div>
		</div>
		<div class="default_article">
			<div class="article_left">
				<tiles:insertAttribute name="sidebar" />
			</div>
			<div class="article_right">
				<div class="cont">
					<div class="main">
					<%-- 主要内容区 --%>
					<tiles:insertAttribute name="page-content" />
					<%-- 主要内容区 --%>
					</div>
				</div>
			</div>
		</div>

	</div>
	
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
<script>
$(document).ready(function() {

	// 左侧菜单效果
	$('#left_nav li span').click(function() {
		var $this = $(this);
		var $thisUl = $this.next('ul');
		var $thisLi = $this.closest('li');
		
		$('#left_nav li ul').not($thisUl).slideUp(400);
		$thisUl.slideToggle(400);
		
		$('#left_nav li').not($thisLi).removeClass('current');
		$thisLi.addClass('current');
		
		return false;
	});
	$('.nav').children('li:last-child').addClass('.last-child');
	
	<%-- 临时处理：对未经审核的卖家不能进行访问其他页面，后台也做了处理，查看：SessionInterceptor  --%>
	<c:if test="${seller.id > 1 && seller.status != 2}">
	$('#left_nav a').each(function() {
		var $this = $(this);
		var href = $this.attr('href');
		if (href.indexOf('ebay/Profile/AccountManage.aspx') != -1 ||
				href.indexOf('Amazon/Amazon_account.aspx') != -1 ||
				href.indexOf('smt/SMTAccountManage.aspx') != -1) {
			return;
		}
		$this.attr('href', '#');
	});
	$('#left_nav a[href=#]').click(function() {
		//xfy.toastr("error", "", "您还未通过审核，暂不能体验该功能111");
		
		if(${seller.status} == 0 && ${platformAccountSize} == 0){
			showabout.showWin('750', '500', '账户审核流程', '${pageContext.request.contextPath}/process1-page');
		}else if(${seller.status} == 1){
			showabout.showWin('750', '500', '账户审核流程', '${pageContext.request.contextPath}/process2-page');
		}
		
		return false;
	});
	</c:if>
});

function showabout2() {
    showabout.showWin('750', '500', '充值流程', '/ui/about2.html?t='+new Date().getSeconds());
}
var showabout = {
    scrolling: 'yes',
    //是否显示滚动条 no,yes,auto
    int: function () {
        this.mouseClose();
        this.closeMask();
        //this.mouseDown();
    },
    showWin: function (width, height, title, src) {
        var iframeHeight = height - 52;
        var marginLeft = width / 2;
        var marginTop = height / 2;
        var inntHtml = '';
        inntHtml += '<div id="mask" style="width:100%; height:100%; position:fixed; top:0; left:0; z-inde:20999;background:#cccccc; filter:alpha(opacity=50); -moz-opacity:0.5; -khtml-opacity: 0.5; opacity:0.5;"></div>'
        inntHtml += '<div id="maskTop" style="width: ' + width + 'px; height: ' + height + 'px; border: #999999 1px solid; background: #fff; color: #333; position: fixed; top: 50%; left: 50%; margin-left: -' + marginLeft + 'px; margin-top: -' + marginTop + 'px; z-index: 2999; filter: progid:DXImageTransform.Microsoft.Shadow(color=#909090,direction=120,strength=4); -moz-box-shadow: 2px 2px 10px #909090; -webkit-box-shadow: 2px 2px 10px #909090; box-shadow: 2px 2px 10px #909090;">'
        inntHtml += '<div id="maskTitle" style="height: 40px; line-height: 40px; font-family: Microsoft Yahei; font-size: 20px; color: #333333; padding-left: 20px; background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAyCAYAAABlG0p9AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAABvSURBVEhL1cq5DcAwDENR7T+sL9lOOoUbkCoCwwKewOJbiGe+31BkwgeDM18YgrPhxuBs4CkS4cQQZMKFwd0R+gzFJaFjcD+EfXgoMuHA4O4Iew/FJWHD4BJhwxDoYcNTIKwY3NGwYggQFgxODEt8xO1/6P+HHxEAAAAASUVORK5CYII=); border-bottom: 1px solid #999999; position: relative;">'
        inntHtml += '' + title + ''
        inntHtml += '<div class="btngreen" style="width: 60px; height: 30px;background-color:#bbb;color:#fff; font-size:14px;line-height: 30px; float:right; margin:5px 20px 0px 0px; text-align:center;  cursor: pointer;border-radius: 4px;" onclick="javascript:$(\'#mask,#maskTop\').fadeOut(function (){$(this).remove();});">关闭</div>'
        inntHtml += '</div>'
        inntHtml += '<iframe id="iframecheckimage" width="' + width + '" height="' + iframeHeight + '" frameborder="0" scrolling="' + this.scrolling + '" src="' + src + '"></iframe>';

        $("body").append(inntHtml);
        this.int();
    },
    mouseClose: function () {
    },
    closeMask: function () {
    }
};

function showabout2() {
    showabout.showWin('750', '500', '充值流程', '${x:getConfig("kandeng.base.url")}/ui/about2.html?t='+new Date().getSeconds());
}
var showabout = {
    scrolling: 'yes',
    //是否显示滚动条 no,yes,auto
    int: function () {
        this.mouseClose();
        this.closeMask();
        //this.mouseDown();
    },
    showWin: function (width, height, title, src) {
        var iframeHeight = height - 52;
        var marginLeft = width / 2;
        var marginTop = height / 2;
        var inntHtml = '';
        inntHtml += '<div id="mask" style="width:100%; height:100%; position:fixed; top:0; left:0; z-inde:20999;background:#cccccc; filter:alpha(opacity=50); -moz-opacity:0.5; -khtml-opacity: 0.5; opacity:0.5;"></div>'
        inntHtml += '<div id="maskTop" style="width: ' + width + 'px; height: ' + height + 'px; border: #999999 1px solid; background: #fff; color: #333; position: fixed; top: 50%; left: 50%; margin-left: -' + marginLeft + 'px; margin-top: -' + marginTop + 'px; z-index: 2999; filter: progid:DXImageTransform.Microsoft.Shadow(color=#909090,direction=120,strength=4); -moz-box-shadow: 2px 2px 10px #909090; -webkit-box-shadow: 2px 2px 10px #909090; box-shadow: 2px 2px 10px #909090;">'
        inntHtml += '<div id="maskTitle" style="height: 40px; line-height: 40px; font-family: Microsoft Yahei; font-size: 20px; color: #333333; padding-left: 20px; background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAyCAYAAABlG0p9AAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAABvSURBVEhL1cq5DcAwDENR7T+sL9lOOoUbkCoCwwKewOJbiGe+31BkwgeDM18YgrPhxuBs4CkS4cQQZMKFwd0R+gzFJaFjcD+EfXgoMuHA4O4Iew/FJWHD4BJhwxDoYcNTIKwY3NGwYggQFgxODEt8xO1/6P+HHxEAAAAASUVORK5CYII=); border-bottom: 1px solid #999999; position: relative;">'
        inntHtml += '' + title + ''
        inntHtml += '<div class="btngreen" style="width: 60px; height: 30px;background-color:#bbb;color:#fff; font-size:14px;line-height: 30px; float:right; margin:5px 20px 0px 0px; text-align:center;  cursor: pointer;border-radius: 4px;" onclick="javascript:$(\'#mask,#maskTop\').fadeOut(function (){$(this).remove();});">关闭</div>'
        inntHtml += '</div>'
        inntHtml += '<iframe id="iframecheckimage" scrolling="no" width="' + width + '" height="' + iframeHeight + '" frameborder="0" scrolling="' + this.scrolling + '" src="' + src + '"></iframe>';

        $("body").append(inntHtml);
        this.int();
    },
    mouseClose: function () {
    },
    closeMask: function () {
    }
};
</script>

<%-- 当前页面级别javascript插件 --%>
<tiles:insertAttribute name="js-page" ignore="true" />
</body>
</html>
