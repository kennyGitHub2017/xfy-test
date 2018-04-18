<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">产品大类信息</h4>
</div>
<form:form id="data_form" modelAttribute="category" action="${pageContext.request.contextPath}/goodscategory/${updateFlag == '1' ? 'firstcgy-edit' : 'firstcgy-add' }" cssClass="form-horizontal form-group-sm" role="form" method="post">
	<div class="modal-body">
		<input type="hidden" name="id" id="id" value="${category.id }" /> <input type="hidden" name="parent.id" value="0" />
		<form:hidden path="id" />
		<div class="form-group">
			<label for="dict_code" class="col-md-3 control-label">产品类别</label>
			<div class="col-md-6">
				<form:input path="name" cssClass="form-control" id="cgyname" />
			</div>
		</div>
		<div class="form-group">
			<label for="dict_code" class="col-md-3 control-label">编码</label>
			<div class="col-md-6">
				<form:input path="code" cssClass="form-control" id="cgycode" />
			</div>
		</div>
		<div class="form-group">
			<label for="inputPassword12" class="col-md-3 control-label">备注</label>
			<div class="col-md-6">
				<form:input path="note" cssClass="form-control" />
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

</form:form>

<script>
	var form1 = $('#data_form');

	form1.validate({
		errorElement : 'span', //default input error message container
		errorClass : 'help-block help-block-error', // default input error message class
		focusInvalid : false, // do not focus the last invalid input
		ignore : "", // validate all fields including form hidden input
		messages : {
			name : {
				remote : '此类别已存在,请重新输入'
			},
			code : {
				remote : '此编码已存在,请重新输入'
			}
		},
		rules : {
			name : {
				required : true,
				remote : {
					url : "${pageContext.request.contextPath }/goodscategory/checkCategory",
					type : "post",
					dataType : "json",
					data : {
						name : function() {
							return $("#cgyname").val();
						},
						id : function() {
							return "${category.id }";
						}
					}
				}
			},
			code : {
				required : true,
				remote : {
					url : "${pageContext.request.contextPath }/goodscategory/checkCategory",
					type : "post",
					dataType : "json",
					data : {
						code : function() {
							return $("#cgycode").val();
						},
						id : function() {
							return "${category.id }";
						}
					}
				}
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