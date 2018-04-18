<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">组合产品信息</h4>
</div>
<form:form id="data_form" modelAttribute="gc" action="${pageContext.request.contextPath}/goods-combination/save" cssClass="form-horizontal" role="form" method="post">
<div class="modal-body">
	
		<form:hidden path="id" />
		<%-- <div class="form-group form-group-sm">
			<label for="inputPassword12" class="col-md-3 control-label">产品名称</label>
			<div class="col-md-9">
				<form:input path="name" cssClass="form-control" />
			</div>
		</div> --%>
		<div class="form-group form-group-sm">
			<label for="dict_code" class="col-md-3 control-label">产品编码</label>
			<div class="col-md-9">
				<form:input path="combSku" cssClass="form-control" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label for="inputPassword12" class="col-md-3 control-label">产品明细</label>
			<div class="col-md-9">
				<form:input path="itemsStr" cssClass="form-control" />
				<span class="help-block">老SKU*数量, 逗号分隔; eg. EJK35*1, EJK36*1</span>
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
	messages : {
		name: {required:'请输入部门名称',remote:'此名称已存在,请重新填写'},
		leaderId: '请选择部门领导'
	},
	rules : {
		combSku : { required : true },
		itemsStr : { required : true }
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