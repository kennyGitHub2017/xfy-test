<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">代理商审核 </h4>
</div>

<div class="modal-body">
	<c:if test="${userInfo.status == 0 }">
		<form id="agent_form" class="form-horizontal form-group-sm" role="form" action="${pageContext.request.contextPath}/user/agent-bond" method="post">
			<input type="hidden" name="userId" value="${userInfo.userId }">
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<label for="dict_code" class="col-md-4 control-label">保证金设置：</label>
						<div class="col-md-8">
							<input name="bond" id="bond" type="text" class="form-control input-inline" value=""/><span class="help-inline">¥</span>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<label for="dict_code" class="col-md-4 control-label">服务费返点：</label>
						<div class="col-md-8">
							<input name="serviceRebate" id="serviceRebate" class="form-control input-inline" value=""/><span class="help-inline">%</span>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<label for="dict_code" class="col-md-4 control-label">产品成本返点：</label>
						<div class="col-md-8">
							<input name="costRebate" id="costRebate" class="form-control input-inline" value=""/><span class="help-inline">%</span>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group">
					<h4 style="text-align:center">代理商审核必须设置初始保证金和返点设置,保证金和返点都必须大于0!</h4>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn default" data-dismiss="modal"><spring:message code="g.label.close"/></button>
				<button type="button" class="btn red" id="user_unapprove_btn"><spring:message code="g.label.unapproved"/></button>
				<button type="submit" class="btn green" id="user_approve_btn1"><spring:message code="g.label.approved"/></button>
			</div>
		</form>
	</c:if>
	
	<c:if test="${userInfo.status == 1 || userInfo.status == 2}">
		<form id="agent_form2" class="form-horizontal form-group-sm" role="form" method="post">
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<label for="dict_code" class="col-md-4 control-label">保证金：</label>
						<div class="col-md-8">
							<input name="bond" id="bond" type="text" class="form-control input-inline" value="${agentConfigure.bond }" readonly/><span class="help-inline">¥</span>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<label for="dict_code" class="col-md-4 control-label">服务费返点：</label>
						<div class="col-md-8">
							<input name="serviceRebate" id="serviceRebate" class="form-control input-inline" value="${agentConfigure.serviceRebateRate }" readonly/><span class="help-inline">%</span>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<label for="dict_code" class="col-md-4 control-label">产品成本返点：</label>
						<div class="col-md-8">
							<input name="costRebate" id="costRebate" class="form-control input-inline" value="${agentConfigure.costRebateRate }" readonly/><span class="help-inline">%</span>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn default" data-dismiss="modal"><spring:message code="g.label.close"/></button>
					<c:if test="${userInfo.status == 1}">
						<button type="button" class="btn red" id="user_unapprove_btn"><spring:message code="g.label.unapproved"/></button>
					</c:if>
					<c:if test="${userInfo.status == 2}">
						<button type="button" class="btn green" id="user_approve_btn2"><spring:message code="g.label.approved"/></button>
					</c:if>
			</div>
		</form>
	</c:if>
</div>

<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
<script>

$(function(){ 
	$('#agent_form').validate({
		errorElement : 'span', //default input error message container
		errorClass : 'help-block help-block-error', //default input error message class
		focusInvalid : false, // do not focus the last invalid input
		ignore : "", //validate all fields including form hidden input
		messages : {
			bond : {remote : "保证金已经设置!", regex : "请输入大于0的正整数!"}
		},
		rules : {
			bond : { required : true,
				digits : true,
				regex : "^([1-9]{1}[0-9]{0,})$",
				remote : {
					url : '${pageContext.request.contextPath}/user/check-bond.json'
				}},
			serviceRebate : { required : true, digits : true},
			costRebate : { required : true, digits : true}
		}
	});
}); 
	
	$('#user_approve_btn2').click(function() {
		_user_approve('reExamine', null);
	});

	$('#user_unapprove_btn').click(function() {
		 bootbox.prompt({
			title: "请输入审核不通过原因",
			callback: function(result) {
				if (! (result === null)) {
					result = $.trim(result);
					if (result.length > 0) {
						_user_approve('unapprove', result);
					} else {
						alert('审核不通过原因必须输入');
					}
				}
			}
		});  
	});

	function _user_approve(action, note) {
		if (confirm("请确认是否继续操作？")) {
			xfy.requestByForm({
				action: '${pageContext.request.contextPath }/user/' + action,
				method: 'post',
				data: {
					userId: ${userInfo.userId},
					note: note
				}
			});
		}
	}

		


</script>
