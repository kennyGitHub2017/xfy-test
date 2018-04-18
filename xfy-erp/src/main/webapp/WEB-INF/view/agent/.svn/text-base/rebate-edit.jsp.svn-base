<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">
		<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
	</button>

	<h4 class="modal-title" id="">返点率修改</h4>
</div>


<div class="modal-body">

	<form class="form-horizontal form-group-sm" role="form" id="rate_edit_form" 
	method="post" action="${pageContext.request.contextPath}/agent-Configure/update-rebate">

		<input type="hidden" value="${userId}" name="userId">
		<div class="row">
			
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">成本返点%</label>
					<div class="col-md-8">
						<input type="text" class="form-control required number" name="costRate" value="">
					</div>
				</div>
			</div>
			
			<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">服务费返点%</label>
						<div class="col-md-8">
							<input type="text" class="form-control required" name="serviceRate" value="">
						</div>
					</div>
			</div>
				
		</div>
		
		<div class="row">
		1.代理商旗下卖家的订单只要扫描发货后的返点收益为=服务费*服务费返点值+产品成本*产品成本返点。返点收益产生后需要在代理资金明细中加入记录
		</div>
	</form>

</div>



<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	<button type="button" class="btn btn-primary" data-dismiss="alert" onclick="subFrom();">提交</button>
</div>

<script>
	function subFrom() {

		$.validator.addMethod("isEn", function(value, element, params) {
			var exp = new RegExp(/^[0-9]+([.]{1}[0-9]+){0,1}$/);
			return exp.test(value);
		}, "输入内容有误");

	$().ready(function() {

			$("#rate_edit_form").validate({
				errorClass : 'help-block help-block-error',
				rules : {

					costRate : {
						required : true,
						isEn : "/^([a-zA-Z]+)$/"
					},

					serviceRate : {
						required : true,
						isEn : "/^([a-zA-Z]+)$/"
					},

				},

				messages : {
					costRate : {
						required : "不能为空",
						isEn : "输入内容有误"
					},
					serviceRate : {
						required : "不能为空",
						isEn : "输入内容有误"
					}
				},

			});

		});

	if(window.confirm('请核对好,再确认提交!')){
		$("#rate_edit_form").submit()
	}
	}
</script>