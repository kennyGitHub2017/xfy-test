<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<form class="form-horizontal" id="data_form" action="${pageContext.request.contextPath }/seller/deposit" method="post">
	<input type="hidden" name="id" value="${sellerDeposit.sellerId }" />
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h4 class="modal-title">修改保证金</h4>
	</div>
	<div class="modal-body">
		<div class="form-group">
			<label for="dict_code" class="col-md-3 control-label">操作</label>
			<div class="col-md-6">
				<select class="form-control" name="action">
					<option value="">请选择....</option>
					<option value="1">收入</option>
					<option value="0">支出</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label for="inputPassword12" class="col-md-3 control-label">金额</label>
			<div class="col-md-6">
				<input type="text" class="form-control" name="amount" value="" />
			</div>
		</div>
		<div class="form-group">
			<label for="inputPassword12" class="col-md-3 control-label">订单号</label>
			<div class="col-md-6">
				<input type="text" class="form-control" name="orderId" value="" />
			</div>
		</div>
		<div class="form-group">
			<label for="inputPassword12" class="col-md-3 control-label">备注</label>
			<div class="col-md-6">
				<input type="text" class="form-control" name="note" value="" maxlength="300" />
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

$('#data_form').validate({
	errorElement : 'span',
	errorClass : 'help-block help-block-error',
	focusInvalid : false,
	ignore : "", // validate all fields including form hidden input
	messages : {
		amount: {regex: '正数，小数点最多两位'}
	},
	rules : {
		action : {
			required : true
		},
		amount : {
			required : true,
			regex: '^[0-9]+(\\.[0-9]{0,2})?$'
		},
		orderId : {
			number:true,
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