<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">功能信息</h4>
</div>
<form:form id="data_form" modelAttribute="function" action="${pageContext.request.contextPath}/sys-function/update" cssClass="form-horizontal" role="form" method="post">
<div class="modal-body">
		<div class="form-group form-group-sm">
			<label class="col-md-3 control-label">功能名称</label>
			<div class="col-md-6">
				<form:input path="name" cssClass="form-control" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label class="col-md-3 control-label">功能编码</label>
			<div class="col-md-6">
				<input id="code" name="code" value="${function.code }" class="form-control"  ${action == 'update' ? 'readonly="true"' : '' }/>
				<input id="action" value="${action }" name="action" type="hidden"/>
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label class="col-md-3 control-label">页面编码</label>
			<div class="col-md-6">
				<form:input path="pageCode" cssClass="form-control" />
			</div>
		</div>
</div>

<div class="modal-footer">
	<button type="button" class="btn default" data-dismiss="modal"><spring:message code="g.label.cancel"/></button>
	<button type="submit" class="btn blue"><spring:message code="g.label.save"/></button>
</div>

</form:form>

<script>

	 $(function(){ 
		$('#data_form').validate({
			errorElement : 'span', //default input error message container
			errorClass : 'help-block help-block-error', //default input error message class
			focusInvalid : false, // do not focus the last invalid input
			ignore : "", //validate all fields including form hidden input
			messages : {
				name : "请输入功能名称",
				code: {remote:'此功能编码已存在，请重新输入',required:"请输入功能编号"},
				pageCode : "请输入页面编码",
			},
			rules : {
				code : { required : true,
					<c:if test="${action == 'add' }">
						remote : {
							url:"${pageContext.request.contextPath}/sys-function/check_function_code.json",
							data : {
								code: function(){
									return $('#code').val();
								}
							}
						}
					</c:if>
				},
				name : { required : true},
				pageCode : { required : true }
			}
			
		});
			
	}); 
	
</script>