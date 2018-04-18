<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

	<h4 class="modal-title" id="">添加Paypal账号</h4>
</div>


<div class="modal-body">
	<form class="form-horizontal form-group-sm" role="form" id="paypal_edit_form" method="post" action="${pageContext.request.contextPath }/paypalAccount/${subFlag == '1' ? 'update' : 'add'}">

		<input type="hidden" name="id" value="${paypalAccAttr.id}" />

		<div class="form-group">
			<label for="" class="col-sm-3 control-label">paypal账号：</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="account_id" name="account" value="${paypalAccAttr.account}" placeholder="paypal账号">
			</div>
		</div>

		<div class="form-group">
			<label for="" class="col-sm-3 control-label">API_UserName：</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="apiUserName_id" name="apiUserName" value="${paypalAccAttr.apiUserName}" placeholder="API_UserName">
			</div>
		</div>

		<div class="form-group">
			<label for="" class="col-sm-3 control-label">API_Password：</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="apiPassword_id" name="apiPassword" value="${paypalAccAttr.apiPassword}" placeholder="API_Password">
			</div>
		</div>

		<div class="form-group">
			<label for="" class="col-sm-3 control-label">API_Signature：</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="apiSignature_id" name="apiSignature" value="${paypalAccAttr.apiSignature}" placeholder="API_Signature">
			</div>
		</div>

		<div class="form-group">
			<label for="" class="col-sm-3 control-label">paypal费率：</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="fees_id" name="fees" value="${paypalAccAttr.fees}" placeholder="paypal费率">
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-md-3">所属ebay账号 </label>
			<div class="col-md-6">
				<select class="form-control" name="ebayAccount">
					<c:forEach var="item" items="${accountAttr}">
						<option value="${item.accountName}" <c:if test="${item.accountName == paypalAccAttr.ebayAccount}">selected</c:if>>${item.accountName}</option>

					</c:forEach>

				</select>
			</div>
		</div>

	</form>
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="subForm();">提交</button>
	<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
</div>

<script type="text/javascript">
	function subForm() {
		$().ready(function() {
			$("#paypal_edit_form").validate({
				errorClass : 'error',
				rules : {
					account : "required",
				},
				messages : {
					account : {
						required : "请输入Paypal账号",
					},
				},
			});
		});
		$("#paypal_edit_form").submit()
	}
</script>



