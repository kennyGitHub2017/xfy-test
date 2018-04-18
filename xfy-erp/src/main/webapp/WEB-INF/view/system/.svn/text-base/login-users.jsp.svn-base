<%--
卖家账户列表
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>

<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="login_users" />

<c:set var="pageTitle" value="登录用户管理" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">${pageTitle }</tiles:putAttribute>
	
	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" />
	</tiles:putAttribute>

	<tiles:putAttribute name="page-content">
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light ">
					<div class="portlet-title">
						<div class="caption caption-md">
							<i class="icon-note theme-font"></i> <span class="caption-subject theme-font bold uppercase">${pageTitle }</span>
						</div>
					</div>
					<div class="portlet-body">
						
						<%@include file="../include/message.jsp"%>
						
						<div class="table-toolbar">
							<div class="row">
								<div class="col-md-6">
									<div class="btn-group">
										<x:power type="function" code="login_users_add">
										<a class=" btn green" href="${pageContext.request.contextPath }/user/login-user-form" data-target="#ajax" data-toggle="modal" data-backdrop="static"> <spring:message code="g.label.add" /> <i class="fa fa-plus"></i>
										</a>
										</x:power>
									</div>
								</div>
							</div>
						</div>
						<table class="table table-striped table-bordered table-hover" id="users_table">
							<thead>
								<tr>
									<th>姓名</th>
									<th>登录名</th>
									<th>账户类型</th>
									<th>创建时间</th>
									<th>操作</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>

		<%@include file="/WEB-INF/view/include/modal-ajax.jsp"%>
	</tiles:putAttribute>

	<%-- 页面级别的 JS --%>
	<tiles:putAttribute name="js-page">
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
		
		<script>
		var masterFlag = ${masterFlag};
		$(function(){
			xfy.initDataTable();
			$("#users_table").dataTable({
				searching: true,
				serverSide: true,
				ajax: {
					url: "${pageContext.request.contextPath }/user/page.json",
					data: function (d) {
						d.params = {sellerId: '${sessionUser.sellerId}'};
						return JSON.stringify( d );
					},
					type: 'post',
					contentType:"application/json;charset=UTF-8"
				},
				columns: [
					{data: "name"},
					{data: "username"},
					{data: "isMaster", render: function(data) { return data === 1 ? '主账户' : '子账户'; } },
					{data: "createdTime" },
					{className: "text-center", data: function ( row ) {
							var html = '';
							/* html += xfy.html.normalLinkNewWin.format('${pageContext.request.contextPath }/user/power?id=' + row.userId, '<spring:message code="g.label.set.power"/>'); */
							if (masterFlag == 1 || row.isMaster === 0) {
								<x:power type="function" code="login_users_account_power">
								html += xfy.html.normalLinkNewWin.format('${pageContext.request.contextPath }/seller/sellerPower-page?userId1='+row.userId, '平台账号权限');
								</x:power>
								<x:power type="function" code="login_users_edit_power">
								html += xfy.html.normalLinkNewWin.format('${pageContext.request.contextPath }/user/power-setting?id='+row.userId, '操作权限');
								</x:power>
								<x:power type="function" code="login_users_edit_password">
								html += xfy.html.editLinkAjaxModal.format('${pageContext.request.contextPath }/user/login-form?id=' + row.userId, '密码');
								</x:power>
								<x:power type="function" code="login_users_lock">
								if (row.locked === 1) {
									html += xfy.html.confirmLink.format('${pageContext.request.contextPath }/user/unlock?id=' + row.userId , '解锁');
								} else {
									html += xfy.html.confirmLink2.format('${pageContext.request.contextPath }/user/lock?id=' + row.userId , '锁定');
								}
								</x:power>
								<x:power type="function" code="login_users_delete">
								html += xfy.html.deleteLink.format('${pageContext.request.contextPath }/user/delete?id=' + row.userId, '<spring:message code="g.label.delete"/>');
								</x:power>
							}
							return html;
						}
					}

				],
		
			});
		});
		</script>

	</tiles:putAttribute>

</tiles:insertDefinition>