<%--
用户列表
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.xuanfeiyang.erp.App"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x" %>

<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="user" />

<c:set var="pageTitle" value="职员管理" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">${pageTitle }</tiles:putAttribute>
	
		<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>


	</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
			<div class="row">
				<div class="col-md-12">
					<div class="portlet light ">
						<div class="portlet-title">
							<div class="caption caption-md">
								<i class="icon-note theme-font"></i>
								<span class="caption-subject theme-font bold uppercase">${pageTitle }</span>
							</div>
						</div>
						<div class="portlet-body">
							<%@include file="../include/message.jsp" %>
							<div class="table-toolbar">
								<div class="row">
									<div class="col-md-6">
										<div class="btn-group">
											<x:power type="function" code="user_add">
												<a class=" btn green" href="${pageContext.request.contextPath }/user/form" data-target="#ajax" data-toggle="modal" data-backdrop="static">
													<spring:message code="g.label.add"/>  <i class="fa fa-plus"></i>
												</a>
											</x:power>
										</div>
									</div>
								</div>
							</div>
							<table class="table table-striped table-bordered table-hover" id="users_table">
							<thead>
							<tr>
								<th>工号</th>
								<th>姓名</th>
								<th>英文名</th>
								<th>登录名</th>
								<th>部门</th>
								<th>角色</th>
								<th>操作</th>
							</tr>
							</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
			
			<%@include file="/WEB-INF/view/include/modal-ajax.jsp" %>
	</tiles:putAttribute>

	<%-- 页面级别的 JS --%>
	<tiles:putAttribute name="js-page">
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>


<script>

$(function(){
	xfy.initDataTable();
	$("#users_table").dataTable({
		searching: true,
		serverSide: true,
		ajax: {
			url: "${pageContext.request.contextPath }/user/page.json",
			data: function (d) {
				d.params = {sellerId: 1};
				return JSON.stringify( d );
			},
			type: 'post',
			contentType:"application/json;charset=UTF-8"
		},
		columns: [
			{data: "code"},
			{data: "name"},
			{data: "enName"},
			{data: "username"},
			{data: "departmentName"},
			{data: function(user) {  // 拼接角色名称
				if (user.roles && user.roles.length > 0) {
					var roles = user.roles;
					var len = roles.length;
					var roleNames = "";
					for (var i = 0; i < len; i++) {
						if (roles[i] != null) {
							roleNames += (roles[i].name + ",");
						}
					}
					
					return roleNames;
				}
				
				return "&nbsp";
			} },
			{className: "text-center", data: function ( row ) {
					var html = '';
					<x:power type="function" code="user_edit">
					html += xfy.html.editLinkAjaxModal.format('${pageContext.request.contextPath }/user/form?id=' + row.userId, '<spring:message code="g.label.edit"/>');
					</x:power>
					<x:power type="function" code="user_set_power">
					html += xfy.html.editLinkAjaxModal.format('${pageContext.request.contextPath }/user/login-form?id=' + row.userId, '登录账号');
					</x:power>
					html += xfy.html.normalLinkNewWin.format('${pageContext.request.contextPath }/user/power?id=' + row.userId, '<spring:message code="g.label.set.power"/>');
					<x:power type="function" code="user_delete">
					html += xfy.html.deleteLink.format('${pageContext.request.contextPath }/user/delete?id=' + row.userId, '<spring:message code="g.label.delete"/>');
					</x:power>
					
					<x:power type="function" code="user_lock">
					if (row.locked === 1) {
						html += xfy.html.confirmLink.format('${pageContext.request.contextPath }/user/unlock?id=' + row.userId , '解锁');
					} else {
						html += xfy.html.confirmLink2.format('${pageContext.request.contextPath }/user/lock?id=' + row.userId , '锁定');
					}
					</x:power>
					
					return html;
				}
			}
		],

	});
});
</script>

	</tiles:putAttribute>

</tiles:insertDefinition>