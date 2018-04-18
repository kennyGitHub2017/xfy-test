<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">编辑登录信息</h4>
</div>
<form id="data_form" class="form-horizontal" role="form" action="${pageContext.request.contextPath }/user/login-user-form" method="post">
	<div class="modal-body">

		<input type="hidden" name="sellerId" value="${sellerId }" />

		<div class="form-group form-group-sm">
			<label for="dict_code" class="col-md-3 control-label">姓名</label>
			<div class="col-md-6">
				<input type="text" class="form-control" name="name" value="${user.name }" />
			</div>
		</div>

		<div class="form-group form-group-sm">
			<label for="dict_code" class="col-md-3 control-label">登录名</label>
			<div class="col-md-6">
				<input type="text" class="form-control" name="username" value="${user.username }" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label for="inputPassword12" class="col-md-3 control-label">登陆密码</label>
			<div class="col-md-6">
				<input type="password" class="form-control" name="password" value="" /><!--  <span class="help-block">添加账号时必须输入；为空则不被修改。</span> -->
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label for="inputPassword12" class="col-md-3 control-label">确认密码</label>
			<div class="col-md-6">
				<input type="password" class="form-control" name="passwordConfirmed" value="" />
			</div>
		</div>

	</div>

	<div class="modal-footer">
		<button type="button" class="btn default" data-dismiss="modal">
			<spring:message code="g.label.cancel" />
		</button>
		<button type="submit" class="btn blue">
			<spring:message code="g.label.save" />
		</button>
	</div>

</form>
<script>
	var form1 = $('#data_form');
	var error1 = $('.alert-danger', form1);
	var success1 = $('.alert-success', form1);

	form1.validate({
		errorElement : 'span',
		errorClass : 'help-block help-block-error',
		focusInvalid : false,
		ignore : "",
		messages : {
			username: {remote: '用户名已被占用，请使用其他用户名'}
		},
		rules : {
			name: {
				required : true
			},
			username: {
				required : true,
				remote: {
					url: "${pageContext.request.contextPath }/user/check-username.json",
					data: {
						username: function () {
							return $("input[name=username]").val();
						}
					}
				}
			},
			password: {
				required : true,
				minlength: 6
			},
			passwordConfirmed: {
				required : true,
				equalTo : '#data_form input[name=password]'
			},
		},

		highlight : function(element) {
			$(element).closest('.form-group').addClass('has-error');
		},

		unhighlight : function(element) {
			$(element).closest('.form-group').removeClass('has-error');
		},
		success : function(element) {
			$(element).closest('.form-group').removeClass('has-error');
			$(element).closest('.form-group').addClass('has-success');
		}

	});
</script>