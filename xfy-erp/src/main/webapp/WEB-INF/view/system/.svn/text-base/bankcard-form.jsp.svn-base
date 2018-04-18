<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">银行卡信息</h4>
</div>
<form:form id="data_form" modelAttribute="bankCard" action="${pageContext.request.contextPath}/bankcard/update" cssClass="form-horizontal" role="form" method="post">
<div class="modal-body">
		<div class="form-group form-group-sm">
			<label class="col-md-3 control-label">银行卡名称</label>
			<div class="col-md-6">
				<form:input path="bankCardName" cssClass="form-control" />
				<input id="action" value="${action }" name="action" type="hidden"/>
				<input id="userId" value="${userId }" name="userId" type="hidden"/>
			</div>
		</div>  
		
		<div class="form-group form-group-sm">
			<label class="col-md-3 control-label">支行信息</label>
			<div class="col-md-6">
				<form:input path="branchInfo" cssClass="form-control" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label class="col-md-3 control-label">银行卡号</label>
			<div class="col-md-6">
				<form:input path="cardNumber" cssClass="form-control" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label class="col-md-3 control-label">备注</label>
			<div class="col-md-6">
				<form:input path="note" cssClass="form-control" />
				<form:input path="id" cssClass="form-control" type="hidden"/>
			</div>
		</div>
</div>

<div class="modal-footer">
	<button type="button" class="btn default" data-dismiss="modal"><spring:message code="g.label.cancel"/></button>
	<button type="submit" id="btn" class="btn blue"><spring:message code="g.label.save"/></button>
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
				bankCardName : "请输入银行卡名称",
				branchInfo : "请输入支行信息",
				cardNumber: {remote:'此银行卡号已存在，请重新输入',required:"请输入银行卡号"},
			},
			rules : {
				bankCardName : { required : true},
				branchInfo : { required : true},
				cardNumber : { required : true,
					<c:if test="${action == 'add' }">
					remote : {
						url:"${pageContext.request.contextPath}/bankcard/check_cardNumber.json",
						data : {
							cardNumber: function(){
								return $('#cardNumber').val();
							}
						}
					}
					</c:if>
				}
			}
		});
			
	}); 

</script>