<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">代理商信息 </h4>
</div>

<div class="modal-body">
	<form class="form-horizontal form-group-sm" role="form">
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">联系人</label>
					<div class="col-md-8">
						<input name="name" class="form-control" value="${userInfo.name }" readonly />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">身份证号</label>
					<div class="col-md-8">
						<input name="idCardNo" class="form-control" value="${userInfo.idCardNo }" readonly />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">手机</label>
					<div class="col-md-8">
						<input name="mobile" class="form-control" value="${userInfo.mobile }" readonly />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label for="dict_code" class="col-md-4 control-label">邮箱</label>
					<div class="col-md-8">
						<input name="email" class="form-control" value="${userInfo.email }" readonly />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="form-group">
					<label for="dict_code" class="col-md-2 control-label">居住地址</label>
					<div class="col-md-10">
						<input name="address" class="form-control" value="${userInfo.address }" readonly />
					</div>
				</div>
			</div>
		</div>
	</form>
</div>

<div class="modal-footer">
	<button type="button" class="btn default" data-dismiss="modal"><spring:message code="g.label.close"/></button>
</div>

<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
