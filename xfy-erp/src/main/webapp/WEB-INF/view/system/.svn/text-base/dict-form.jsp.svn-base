<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">编辑</h4>
</div>
<form id="dict_form" class="form-horizontal" role="form" action="${pageContext.request.contextPath }/dict/${updateFlag == '1' ? 'update' : 'save' }" method="post">
	<div class="modal-body">

		<input type="hidden" name="type" value="${type }" />
		<div class="form-group form-group-sm">
			<label for="dict_code" class="col-md-3 control-label">代码值</label>
			<div class="col-md-6">
				<input type="text" class="form-control" id="dict_code" name="code" value="${dict.code }" ${updateFlag == '1' ? 'readonly' : '' } /> <span class="help-block">代码值为数字，不能和以前的重复。</span>
			</div>
		</div>
		<div class="form-group">
			<label for="inputPassword12" class="col-md-3 control-label">描述信息</label>
			<div class="col-md-6">
				<input type="text" class="form-control" id="dict_name" name="name" value="${dict.name }" />
			</div>
		</div>
	</div>

	<div class="modal-footer">
	<button type="button" class="btn default" data-dismiss="modal"><spring:message code="g.label.cancel"/></button>
		<button type="submit" class="btn blue">
			<spring:message code="g.label.save" />
		</button>
	</div>

</form>

<script>
	var form1 = $('#dict_form');
	var error1 = $('.alert-danger', form1);
	var success1 = $('.alert-success', form1);

	form1.validate({
		errorElement : 'span', //default input error message container
		errorClass : 'help-block help-block-error', // default input error message class
		focusInvalid : false, // do not focus the last invalid input
		ignore : "", // validate all fields including form hidden input
		messages : {
			name : '请输入描述信息',
			code : {
				required : '请输入代码值',
				number : '请输入数字'
			}
		},
		rules : {
			name : {
				required : true
			},
			code : {
				required : true,
				number : true
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