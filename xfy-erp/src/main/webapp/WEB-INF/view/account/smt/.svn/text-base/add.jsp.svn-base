<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="smt_account" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">SMT帐号管理 </tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
	
		<div class="portlet-body form">
													<!-- BEGIN FORM-->
				<form  class="form-horizontal" id="smtform"
					action="${pageContext.request.contextPath}/account/smt/login" method="post">
					<input type="hidden" name="id" value="${id}">
					<div class="form-body">
						<div class="form-group">
							<label class="col-md-3 control-label">速卖通账号:</label>
							<div class="col-md-4">
								<input type="text" class="form-control" placeholder="请输入速卖通账号" name="accountName" value="${accountName}">
								<span class="help-inline">
										输入帐号，选择关联速卖通按钮后，系统将会打印速卖通的登陆连接，如果没有打开，请选择下方的授权连接
								 </span>
							</div>
						</div>
					</div>
					<div class="form-actions">
						<div class="row">
							<div class="col-md-offset-3 col-md-9">
								<button type="submit" class="btn green">登陆账号</button>
							</div>
						</div>
					</div>
				</form>
				<hr style="height:1px;border:none;border-top:1px dashed #0066CC;">
				<a onclick="window.open('${requestScope.authUrl}')" href="javascript:void(0)"><c:out value="${requestScope.authUrl}"></c:out></a>
			</div>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="js-page">
		<script>
		//form validate
		  var form1 = $('#smtform');
			var error1 = $('.alert-danger', form1);
			var success1 = $('.alert-success', form1);

			form1.validate({
				errorElement : 'span', //default input error message container
				errorClass : 'help-block help-block-error', // default input error message class
				focusInvalid : false, // do not focus the last invalid input
				ignore : "", // validate all fields including form hidden input
				messages : {
					accountName: '请输入 Smt 帐号'
				},
				rules : {
					accountName : {
						required : true
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
		
		//自动弹出smt账户授权窗口
		 <c:if test="${not empty requestScope.authUrl }">
		 	$(function(){
		 		var url = "${requestScope.authUrl}";
				window.open(url);
			})
		 </c:if>
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>