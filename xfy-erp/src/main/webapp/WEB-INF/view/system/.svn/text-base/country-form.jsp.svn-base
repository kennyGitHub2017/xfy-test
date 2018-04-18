<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">
		<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
	</button>

	<h4 class="modal-title" id="">添加地区信息</h4>
</div>


<div class="modal-body">

	<form class="form-horizontal form-group-sm" role="form" id="country_edit_form" method="post" action="${pageContext.request.contextPath}/country/${subFlag == '1' ? 'update' : 'add'}">

		<input type="hidden" name="id" value="${countAttr.id}" />
		<div class="form-group">

			<label for="inputEmail3" class="col-sm-3 control-label">国家名称(英文)</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="enName_id" name="enName" value="${countAttr.enName}" placeholder="英文">
			</div>
		</div>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-3 control-label">国家名称(中文)</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" name="cnName" value="${countAttr.cnName}" id="cnName_id" placeholder="中文">
			</div>
		</div>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-3 control-label">国家名称(缩写)</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" name="shortName" value="${countAttr.shortName}" id="shortName_id" placeholder="缩写">
			</div>
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
			var exp = new RegExp(/^([a-zA-Z]+)$/);
			return exp.test(value);
		}, "输入内容有误");

		$().ready(function() {

			$("#country_edit_form").validate({
				errorClass : 'help-block help-block-error',
				rules : {

					enName : {
						required : true,
						isEn : "/^([a-zA-Z]+)$/"
					},
					cnName : "required",
					shortName : {
						required : true,
						isEn : "/^([a-zA-Z]+)$/"
					},

				},

				messages : {
					enName : {
						required : "请输入地区名英文名",
						isEn : "输入内容有误"
					},
					cnName : {
						required : "请输入地区名中文名",
					},
					shortName : {
						required : "请输入地区名的缩写",
						isEn : "输入内容有误"
					}
				},

			});

		});

		$("#country_edit_form").submit()
	}
</script>