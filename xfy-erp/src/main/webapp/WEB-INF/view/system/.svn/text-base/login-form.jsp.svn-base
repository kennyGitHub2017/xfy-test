<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">编辑登录信息</h4>
</div>
<form id="data_form" class="form-horizontal" role="form" action="${pageContext.request.contextPath }/user/login-info" method="post">
<div class="modal-body">
	
	<input type="hidden" name="userId" value="${user.userId }" id="login_id" />
	
	<c:if test="${sellerId != null }">
		<input type="hidden" name="sellerId" value="${sellerId }" />
		<input type="hidden" name="flag" value="seller" />
	</c:if>
	
		<div class="form-group">
			<label for="dict_code" class="col-md-3 control-label">登录名</label>
			<div class="col-md-6">
				<input type="text" class="form-control" name="username" value="${user.username }" readonly="readonly" />
			</div>
		</div>
		<div class="form-group">
			<label for="inputPassword12" class="col-md-3 control-label">登陆密码</label>
			<div class="col-md-6">
				<input type="text" class="form-control" name="password" value="" />
				<span class="help-block">为空则密码不被修改。</span>
			</div>
		</div>
		
</div>

<div class="modal-footer">
	<button type="button" class="btn default" data-dismiss="modal"><spring:message code="g.label.cancel"/></button>
	<button type="submit" class="btn blue"><spring:message code="g.label.save"/></button>
</div>

</form>
<script>
	var form1 = $('#data_form');
	var error1 = $('.alert-danger', form1);
	var success1 = $('.alert-success', form1);

	form1.validate({
		errorElement : 'span', //default input error message container
		errorClass : 'help-block help-block-error', // default input error message class
		focusInvalid : false, // do not focus the last invalid input
		ignore : "", // validate all fields including form hidden input
		messages : {
			username: '请输入用户名',
			password: '请输入密码'
		},
		rules : {
			username : {
				required : true
			},
			password : {
				required : function() {
					var id = $.trim($('#login_id').val());
					if (id.length > 0) {
						return false;
					}
					return true;;
				},
			}
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
</script>