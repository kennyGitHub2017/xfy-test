<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

	<h4 class="modal-title" id="">编辑仓库</h4>
</div>


<div class="modal-body">

	<form class="form-horizontal form-group-sm" role="form" id="store_edit_form" method="post" action="${pageContext.request.contextPath}/store/${subFlag == '0' ? 'addStore' : 'updateStore'}">

		<input type="hidden" name="id" id="store_id" value="${storeAttr.id}" />
		<div class="form-group">

			<label for="inputEmail3" class="col-sm-3 control-label">仓库名称</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="" name="name" value="${storeAttr.name}" placeholder="仓库名称">
			</div>

		</div>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-3 control-label">仓库编码</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" name="code" id="code_id" value="${storeAttr.code}" id="" placeholder="仓库编码">
			</div>
		</div>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-3 control-label">仓库地址</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" name="location" value="${storeAttr.location}" id="" placeholder="仓库地址">
			</div>

		</div>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-3 control-label">备注</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" name="note" value="${storeAttr.note}" id="" placeholder="备注">
			</div>

		</div>

		<div class="form-group">
			<label class="control-label col-md-3">仓库属性 </label>
			<div class="col-md-6">
				<select class="form-control" name="type">

					<c:forEach items="${typeList}" var="item">
						<option value="${item.code}" <c:if test="${item.code == storeAttr.type}">selected</c:if>>${item.name}</option>
					</c:forEach>


				</select>
			</div>
		</div>

	</form>

</div>


<div class="modal-footer">
	<button type="button" class="btn btn-primary" data-dismiss="alert" onclick="subForm();">提交</button>
	<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
</div>


<script type="text/javascript">
function subForm(){
	
	$().ready(function() {

		$("#store_edit_form").validate({
			errorClass : 'error', 
			rules : {
				name:{
					maxlength:20,
					required:true,
				},
				code : {
					maxlength:25,
					required:true,
					remote:{
						url:'${pageContext.request.contextPath }/store/checkStore',
						type:'post',
						dataType: "json",
						data:{
							code:function(){
								return $('#code_id').val();
							},
							id:function(){
								return $('#store_id').val();
							}
						}
						
					}
					
				},
				location : {
					maxlength:50,
					required:true,
				}
			},

			messages : {
				name : {
					required : "请输入仓库名",
				},
				code : {
					required : "请输入编码",
					remote:"编码已经存在 ！",
				},
				location : {
					required : "请输入地址",
				},
				
			},
			
		});

	});
	$("#store_edit_form").submit()
}
</script>		
		
		
		