<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">SKU映射表单</h4>
</div>
<form:form id="data_form" modelAttribute="skuMapping" action="${pageContext.request.contextPath}/sku-mapping/update" cssClass="form-horizontal" role="form" method="post">
<div class="modal-body">
		
		<div class="form-group form-group-sm">
			<label class="col-md-3 control-label">平台SKU</label>
			<div class="col-md-6">
				<input id="platformSku" name="platformSku" value="${skuMapping.platformSku }" class="form-control"  ${action == 'update' ? 'readonly="true"' : '' }/>
				<input id="action" value="${action }" name="action" type="hidden"/>
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label class="col-md-3 control-label">系统旧SKU</label>
			<div class="col-md-6">
				<form:input path="oldSku" cssClass="form-control" />
			</div>
		</div>  
		<div class="form-group form-group-sm">
			<label class="col-md-3 control-label">新SKU</label>
			<div class="col-md-6">
				<form:input path="newSku" cssClass="form-control" />
			</div>
		</div>
		<div class="form-group form-group-sm" style="display:none;">
			<label class="col-md-3 control-label">创建日期</label>
			<div class="col-md-6">
				<form:input path="createDate" cssClass="form-control" />
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
				platformSku: {remote:'此平台SKU已存在，请重新输入',required:"请输入平台SKU",regex:'请输入数字,字母或符号'},
				oldSku : "请输入系统旧SKU",
				newSku : "请输入系统新SKU",
			},
			rules : {
				platformSku : { required : true,
					regex: /[^\u4E00-\u9FA5]/g, 
					maxlength:15,
					<c:if test="${action == 'add' }">
					remote : {
						url:"${pageContext.request.contextPath}/sku-mapping/check_platform_sku.json",
						data : {
							platformSku: function(){
								return $('#platformSku').val();
							}
						}
					}
					</c:if>
				},
				oldSku : { required : true},
				newSku : { required : true}
			}
		});
		
			
	});

</script>