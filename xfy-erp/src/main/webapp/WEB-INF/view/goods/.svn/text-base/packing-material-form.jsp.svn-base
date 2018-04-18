<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<h4 class="modal-title" id="">编辑包装材料</h4>
	</div>


		<div class="modal-body">

			<form class="form-horizontal" role="form" id="edit_Material_form" method="post"
			
			action="${pageContext.request.contextPath}/packingMaterial/${subFlag == '1' ? 'update' : 'add'}">

				<input type="hidden" name="id" id="material_id" value="${material.id}"/>
	

				<div class="form-group">
					<label for="inputPassword3" class="col-sm-3 control-label">型号</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="model_id" name="model" value="${material.model}"
							id="" placeholder="">
					</div>
				</div>
				
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-3 control-label">规格</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="rules" value="${material.rules}"
							id="" placeholder="">
					</div>
				</div>
				
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-3 control-label">备注</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="note" value="${material.note}"
							id="" placeholder="">
					</div>
				</div>
				
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-3 control-label">重量</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="weight" value="${material.weight}"
							id="" placeholder="">
					</div>
				</div>
				
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-3 control-label">价格</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="price" value="${material.price}"
							id="" placeholder="">
					</div>
				</div>
				

			</form>

			</div>

				

		<div class="modal-footer">
		<button type="button" class="btn btn-primary"  data-dismiss="alert" onclick="subForm();">提交</button>
		<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		</div>
		
		<script>
		
		function subForm(){
			
			$.validator.addMethod("isValidate",function(value,element,params){
				 var exp = new RegExp(/^(0|[1-9]\d{0,4})(\.\d{1,4})?$/);
				 return exp.test(value); 
				},"输入内容有误");
 
			$().ready(function() {

				$("#edit_Material_form").validate({
					errorClass : 'error', 
					rules : {
						model : {
							required:true,
							remote:{
								url:'${pageContext.request.contextPath }/packingMaterial/checkModel',
								type:'post',
								dataType: "json",
								data:{
									model:function(){
										return $('#model_id').val();
									},
									id:function(){
										return $('#material_id').val();
									}
									
								}
								
							}
							},
						rules:"required",
						weight : {
							required:true,
							isValidate: "/^(0|[1-9]\d{0,4})(\.\d{1,4})?$/"
									},
						price: {	
							required:true,
							isValidate: "/^(0|[1-9]\d{0,4})(\.\d{1,4})?$/"
									},
							
						note: "required",
					},

					messages : {
						model : {
							required : "请输入型号",
							remote:"型号已经存在！",
						},
						rules : {
							required : "请输入规格",
						}, 
						weight : {
							required : "请输入重量",
							isValidate:"输入内容有误",
						},
						price : {
							required : "请输入价格",
							isValidate:"输入内容有误",
						},
						note : {
							required : "请输入备注",
						},

					},
					
				});

			});
			
			$("#edit_Material_form").submit()
		}
		

		</script>