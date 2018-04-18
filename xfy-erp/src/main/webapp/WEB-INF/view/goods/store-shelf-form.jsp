<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
	input.error { border: 1px solid red; }
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
			
			<h4 class="modal-title" id="">编辑货位</h4>
	</div>


		<div class="modal-body">

			<form class="form-horizontal form-group-sm" role="form" id="shelf_edit_form" method="post"
			
			action="${pageContext.request.contextPath}/store/${subFlag == '0' ? 'addShelf' : 'updateShelf'}">

				<input type="hidden" name="id" id="shelf_id" value="${shelfAttr.id}"/>
				<div class="form-group">

					<label for="inputEmail3" class="col-sm-3 control-label">仓位编码</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="code_id1" name="code" value="${shelfAttr.code}"
							placeholder="仓位编码">
					</div>
					
				</div>

				<div class="form-group">
					<label for="inputPassword3" class="col-sm-3 control-label">备注</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="note" value="${shelfAttr.note}"
							id="" placeholder="备注">
					</div>
				</div>
				

				
					<div class="form-group">
					<label class="control-label col-md-3">所属仓库 </label>
					<div class="col-md-6">
						<select class="form-control" name="storeId">
							<c:forEach var="ss" items="${store}">
							<option value="${ss.id}" <c:if test="${ss.id == shelfAttr.storeId}">selected</c:if> >${ss.name}</option>
							</c:forEach>
						</select>
					</div>
					</div>

			</form>

			</div>



		<div class="modal-footer">
		<button type="button" class="btn btn-primary"  data-dismiss="alert" onclick="subForm();">提交</button>
		<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		</div>
		<script type="text/javascript">
		function subForm(){
			
			$().ready(function() {

				$("#shelf_edit_form").validate({
					errorClass : 'error', 
					rules : {
						
						note:{
							maxlength:20,
						},

						code :{
							required:true,
							remote:{
								url:'${pageContext.request.contextPath }/store/checkShelf',
								type:'post',
								dataType: "json",
								data:{
									code:function(){
										return $('#code_id1').val();
									},
									id:function(){
										return $('#shelf_id').val();
									}
								}
								
							}
						}
		
					},

					messages : {
						code : {
							required : "请输入编码",
							remote:"编码已经存在！",
						}
						
					},
					
				});

			});
			$("#shelf_edit_form").submit()
		}
		
		</script>
		