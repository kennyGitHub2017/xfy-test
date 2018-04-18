<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
input.error {
	border: 1px solid red;
}

label.error {
	padding-left: 16px;
	padding-bottom: 2px;
	color: red;
}
</style>


<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">
		<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
	</button>

	<h4 class="modal-title" id="">添加汇率</h4>
</div>


<div class="modal-body">


	<form class="form-horizontal form-group-sm" role="form" id="rate_edit_form" method="post" action="${pageContext.request.contextPath}/currencyRates/${subFlag == '1' ? 'update' : 'add'}">

		<input type="hidden" name="id" value="${rateAttr.id}" />
		<div class="form-group">

			<label for="inputEmail3" class="col-sm-3 control-label">货币</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="currency_id" name="currency" value="${rateAttr.currency}" placeholder="货币">
			</div>
		</div>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-3 control-label">兑换率</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" name="exchangeRate" value="${rateAttr.exchangeRate}" id="exchangeRate_id" placeholder="兑换率">
			</div>
		</div>

	</form>

</div>

<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	<button type="button" class="btn btn-primary" onclick="subForm();">提交</button>
</div>

<script>
	function subForm() {
		$.validator.addMethod("isExchangeRate",
				function(value, element, params) {
					var exp = new RegExp(/^(0|[1-9]\d{0,4})(\.\d{1,4})?$/);
					return exp.test(value);
				}, "输入内容有误");

		$().ready(function() {
			$("#rate_edit_form").validate({
				errorClass : "error",
				rules : {
					currency : "required",
					exchangeRate : {
						required : true,
						isExchangeRate : "/^(0|[1-9]\d{0,4})(\.\d{1,4})?$/"
					}
				},
				messages : {
					currency : "请输入货币",
					exchangeRate : {
						required : "请输入汇率",
						isExchangeRate : "输入内容有误",
					},
				},

			});

		});

		$("#rate_edit_form").submit()
	}
</script>




