<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">卖家信息</h4>
</div>
<form:form id="data_form" modelAttribute="seller" action="${pageContext.request.contextPath}/seller/${updateFlag == '1' ? 'update' : 'save' }" cssClass="form-horizontal" role="form" method="post">

<div class="modal-body">
	
		<form:hidden path="id" />
		
		<div class="form-group">
			<label for="dict_code" class="col-md-2 control-label">联系人</label>
			<div class="col-md-4">
				<form:input path="contacts" cssClass="form-control" />
			</div>
			<label for="dict_code" class="col-md-2 control-label">公司名称</label>
			<div class="col-md-4">
				<form:input path="comName" cssClass="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label for="dict_code" class="col-md-2 control-label">电话</label>
			<div class="col-md-4">
				<form:input path="phone" cssClass="form-control" />
			</div>
			<label for="dict_code" class="col-md-2 control-label">手机</label>
			<div class="col-md-4">
				<form:input path="mobile" cssClass="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label for="dict_code" class="col-md-2 control-label">邮箱</label>
			<div class="col-md-10">
				<form:input path="email" cssClass="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label for="dict_code" class="col-md-2 control-label">地址</label>
			<div class="col-md-10">
				<form:input path="address" cssClass="form-control" />
			</div>
		</div>

</div>

<div class="modal-footer">
	<button type="button" class="btn default" data-dismiss="modal"><spring:message code="g.label.cancel"/></button>
	<button type="submit" class="btn blue"><spring:message code="g.label.save"/></button>
</div>

</form:form>


<script>
$('.date-picker').datepicker({
	orientation: "right",
	autoclose: true
});

var form1 = $('#data_form');

form1.validate({
	errorElement : 'span', //default input error message container
	errorClass : 'help-block help-block-error', // default input error message class
	focusInvalid : false, // do not focus the last invalid input
	ignore : "", // validate all fields including form hidden input
	messages : {
		contacts: "请输入联系人",
		email : "邮箱格式不正确"
	},
	rules : {
		contacts : {required: true},
		email : {email: true}
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