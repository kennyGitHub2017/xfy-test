<%--
卖家保证金管理
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>

<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="seller_deposit" />

<c:set var="pavaTitle" value="卖家保证金管理" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">${pavaTitle }</tiles:putAttribute>
	
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
								<span class="caption-subject theme-font bold uppercase">${pavaTitle }</span>
							</div>
						</div>
						<div class="portlet-body">
						
							<%@include file="/WEB-INF/view/include/message.jsp" %>
							
							 <div class="table-toolbar">
								<div class="row">
								<%--<div class="col-md-6">
										<div class="btn-group">
											<a class=" btn green" href="${pageContext.request.contextPath }/seller/form" data-target="#ajax" data-toggle="modal" data-backdrop="static">
												<spring:message code="g.label.add"/>  <i class="fa fa-plus"></i>
											</a>
										</div>
									</div> --%>
							<div>
							<a class="btn green" href="${pageContext.request.contextPath}/seller/depositLogExport-page"  target="_blank">导出报表</a>
							</div>
									
								</div>
							</div>
							<table class="table table-striped table-bordered table-hover" id="users_table">
							<thead>
							<tr>
								<th>卖家类型</th>
								<th>联系人</th>
								<th>邮箱</th>
								<th>手机</th>
								<th>地址</th>
								<th>保证金(元)</th>
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
	var dt = $("#users_table").dataTable({
		searching: true,
		serverSide: true,
		//ajax: "${pageContext.request.contextPath }/seller/page-json",
		ajax: {
			url: "${pageContext.request.contextPath }/seller/page-json?for=deposit",
			data: function (d) {
				d.params = {status: 2};
				return JSON.stringify( d );
			},
			type: 'post',
			contentType:"application/json;charset=UTF-8"
		},
		columns: [
			{data: function(row) { return row.type === 0 ? '个人卖家' : (row.type === 1 ? '公司卖家' : ''); } },
			{data: "contacts"},
			{data: "email"},
			{data: "mobile"},
			{data: "address"},
			{
				className:'text-right',
				data: "deposit", 
				render: function(data) {
					return data != null ? data.toFixed(2) : '0.00';
				}
			},
			{
				className:'text-center',
				data: function(row) {
					var html = '';
					<x:power type="function" code="seller_deposit_setting">
					html += xfy.html.editLinkAjaxModal.format('${pageContext.request.contextPath }/seller/deposit-form?id=' + row.id, '设置保证金');
					</x:power>
					html += xfy.html.normalLinkNewWin.format('${pageContext.request.contextPath }/seller/deposit-log?id=' + row.id, '明细')
					return html;
				}
			}
		]
	});
});
</script>

	</tiles:putAttribute>

</tiles:insertDefinition>