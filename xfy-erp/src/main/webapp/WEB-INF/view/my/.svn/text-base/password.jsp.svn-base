<%--
字典管理
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:set scope="request" var="sysModule" value="personal_center" />
<c:set scope="request" var="sysPage" value="personal_center_modify_password" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">字典维护</tiles:putAttribute>
	
	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/admin/pages/css/todo.css"/>

	</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
			<div class="row">
				<div class="col-md-12">
					<%-- <div class="todo-sidebar">
						<div class="portlet light">
							<div class="portlet-title">
								<div class="caption" data-toggle="collapse" data-target=".todo-project-list-content">
									<span class="caption-subject font-green-sharp bold uppercase">用户中心 </span>
								</div>
							</div>
							<div class="portlet-body todo-project-list-content">
								<div class="todo-project-list">
									<ul class="nav nav-pills nav-stacked">
										<li>
											<a href="#">我的资料</a>
										</li>
										<li class="active">
											<a href="${pageContext.request.contextPath }/my/password">修改密码</a>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="todo-content"> --%>
						<div class="portlet light">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-cogs font-green-sharp"></i>
									<span class="caption-subject font-green-sharp bold uppercase">修改密码</span>
								</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"></a>
								</div>
							</div>
							<div class="portlet-body">
								<%@include file="../include/message.jsp" %>
							
								<form id="data_form" class="form-horizontal" role="form" action="${pageContext.request.contextPath }/my/password" method="post">
									
									<input type="hidden" name="id" value="${sessionUser.userId }" />
									
									<div class="form-group">
										<label for="dict_code" class="col-md-3 control-label">旧密码</label>
										<div class="col-md-6">
											<input type="password" class="form-control" name="oldPassword" id="oldPassword" />
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword12" class="col-md-3 control-label">新密码</label>
										<span class="help">至少6位</span>
										<div class="col-md-6">
											<input type="password" class="form-control" name="newPassword" id="newPassword" />
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword12" class="col-md-3 control-label">确认新密码</label>
										<div class="col-md-6">
											<input type="password" class="form-control" name="newPasswordConfirmed" />
										</div>
									</div>
									
									<div class="form-group">
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" class="btn green"><spring:message code="g.label.save"/></button>
											<button type="reset" class="btn default"><spring:message code="g.label.reset"/></button>
										</div>
									</div>
								</form>
							</div>
						</div>
					<%-- </div> --%>
				</div>
			</div>
			
			<%@include file="/WEB-INF/view/include/modal-ajax.jsp" %>
	</tiles:putAttribute>
	
		
	<%-- 页面级别的 JS --%>
	<tiles:putAttribute name="js-page">
<script>
$(function() {
	$("#data_form").validate({
		errorElement : 'span',
		errorClass : 'help-block help-block-error',
		focusInvalid : false,
		ignore : "",
		messages : {
			oldPassword: '请输入旧密码',
			newPassword: { required : '请输入密码，且不能和旧密码相同', notEqualTo : '新旧密码不能相同' },
			newPasswordConfirmed: { required: '请输入确认密码', equalTo: '确认密码输入不符' }
		},
		rules : {
			oldPassword : {
				required : true
			},
			newPassword : {
				required : true,
				minlength:6,
				maxlength:20,
				notEqualTo: '#data_form input[name=oldPassword]'
			},
			newPasswordConfirmed : {
				required : true,
				minlength:6,
				maxlength:20,
				equalTo : '#data_form input[name=newPassword]'
			},
		},

		highlight : function(element) { // hightlight error inputs
			$(element).closest('.form-group').addClass('has-error'); // set error class to the control group
		},

		unhighlight : function(element) { // revert the change done by hightlight
			$(element).closest('.form-group').removeClass('has-error'); // set error class to the control group
		},

		success : function(label) {
			label.closest('.form-group').removeClass('has-error'); // set success class to the control group
		}
	});
});
</script>
	</tiles:putAttribute>
</tiles:insertDefinition>