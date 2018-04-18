<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">部门信息</h4>
</div>
<form:form id="data_form" modelAttribute="role" action="${pageContext.request.contextPath}/role/save" cssClass="form-horizontal" role="form" method="post">
<div class="modal-body">
	
		<form:hidden path="id" />
		<div class="form-group form-group-sm">
			<label for="dict_code" class="col-md-3 control-label">角色名称</label>
			<div class="col-md-6">
				<form:input path="name" cssClass="form-control" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label for="inputPassword12" class="col-md-3 control-label">备注</label>
			<div class="col-md-6">
				<form:input path="note" cssClass="form-control" />
			</div>
		</div>
</div>

<div class="modal-footer">
	<button type="button" class="btn default" data-dismiss="modal"><spring:message code="g.label.cancel"/></button>
	<button type="submit" class="btn blue"><spring:message code="g.label.save"/></button>
</div>

</form:form>

<script>
var form1 = $('#data_form');

form1.validate({
	errorElement : 'span', //default input error message container
	errorClass : 'help-block help-block-error', // default input error message class
	focusInvalid : false, // do not focus the last invalid input
	ignore : "", // validate all fields including form hidden input
	rules : {
		name : { required : true }
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