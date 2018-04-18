<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">
		<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
	</button>

	<h4 class="modal-title" id="">保证金修改</h4>
</div>


<div class="modal-body">

	<form class="form-horizontal form-group-sm" role="form" id="bond_edit_form" 
	method="post" action="${pageContext.request.contextPath}/agent-Configure/bond-edit">

		<input type="hidden" value="${userId}" name="userId">
		
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">保证金</label>
					<div class="col-md-8">
						<input type="text" class="form-control required digits" name="bond" value="">
					</div>
				</div>
			</div>
		</div>
	</form>

</div>


<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	<button type="button" class="btn btn-primary" data-dismiss="alert" onclick="subFrom();">提交</button>
</div>

<script type="text/javascript">

function subFrom() {
	
	$().ready(function() {

		$("#bond_edit_form").validate({
			errorClass : 'help-block help-block-error',
			rules : {
				bond : {
					required : true,
					min:1,
				}
			},

			messages : {
				bond : {
					required : "不能为空",
					min:"保证金大于0",
				}
			},

		});
	});

	if(window.confirm('请核对好,再确认提交!')){
	$("#bond_edit_form").submit()
	}
}

</script>
