<%--
供应商管理
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>
<c:set scope="request" var="sysModule" value="purchase" />
<c:set scope="request" var="sysPage" value="supplier" />

<c:set var="pageTitle" value="供应商" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">${pageTitle }</tiles:putAttribute>
	
	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">
	
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/admin/pages/css/todo.css"/>

	</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
			<div class="row">
				<div class="col-md-12">
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-cogs font-green-sharp"></i>
								<span class="caption-subject font-green-sharp bold uppercase">供应商管理</span>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="row">
									<div class="col-md-2">
										<div class="btn-group">
										<x:power type="function" code="supplier_add">
											<a class=" btn green" href="${pageContext.request.contextPath }/supplier/form" data-target="#modal-large" data-toggle="modal" data-backdrop="static">
												<spring:message code="g.label.add"/>  <i class="fa fa-plus"></i>
											</a>
										</x:power>
										</div>
									</div>
									<div class="col-md-2">
										<div class="btn-group">
										<x:power type="function" code="supplier_import_page">
											<a class=" btn green " href="${pageContext.request.contextPath }/supplier/import_page" target="_blank"> 供应商导入
											<i class="fa fa-plus"></i>
											</a>
										</x:power>
										</div>
									</div>
									
									<div class="col-md-2">
										<div class="btn-group">
										<x:power type="function" code="supplier_import_page">
											<a  class="btn blue" href="${pageContext.request.contextPath }/resources/template/supplier-import.xls">
												<i class="fa fa-download"></i> 
												<span>下载导入模板</span>
											</a>
										</x:power>
										</div>
									</div>
									
								</div>
							</div>
							
							<ul class="nav nav-tabs">
								<li class="${(param.status != 1 && param.status != 0) ? 'active' : '' }">
									<a href="${pageContext.request.contextPath }/supplier">全部供应商</a>
								</li>
								<li class="${param.status == 0 ? 'active' : '' }">
									<a href="${pageContext.request.contextPath }/supplier?status=0">未审核</a>
								</li>
								<li class="${param.status == 1 ? 'active' : '' }">
									<a href="${pageContext.request.contextPath }/supplier?status=1">已审核</a>
								</li>
							</ul>
						
							<%@include file="../include/message.jsp" %>
						
							<table id="list_table" class="table table-hover table-striped table-bordered table-condensed">
								<thead>
								<tr>
									<th>编码</th>
									<th>简称</th>
									<th>名称</th>
									<th>联系人</th>
									<th>电话</th>
									<th>手机</th>
									<th>采购周期</th>
									<th>支付方式</th>
									<th>采购员</th>
									<th>开发员</th>
									<th>状态</th>
									<th>审核人</th>
									<th>审核时间</th>
									<th>评分</th>
									<th>操作</th>
								</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
			
			<%@include file="/WEB-INF/view/include/modal-large.jsp" %>
	</tiles:putAttribute>
	
		
	<%-- 页面级别的 JS --%>
	<tiles:putAttribute name="js-page">
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>

<script>

$(function(){
	xfy.initDataTable();
	$("#list_table").dataTable({
		searching: true,
		serverSide: true,
		ajax: {
			url: "${pageContext.request.contextPath }/supplier/page-json?status=${param.status}",
			data: function (d) {
				return JSON.stringify( d );
			},
			type: 'post',
			contentType:"application/json;charset=UTF-8"
		},
		columns: [
			{data: "code"},     
			{data: "shortName"},
			{data: "companyName"},
			{data: "contactName"},
			{data: "contactTel"},
			{data: "contactMobile"},
			{data: "buyPeriod"},
			{data: "payMethod"},
			{data: "buyerName"},
			{data: "developerName"},
			{data: "status", render: function(data) {
					return data == 0 ? '未审核' : '已审核';
				} 
			},
			{data: "auditUserName"},
			{data: "auditTime"},
			{className: "text-center", data: function(row) {
					var url = '${pageContext.request.contextPath }/supplier-score/score?supplierId=' + row.id;
					return xfy.html.normalLink.format(url, '<spring:message code="g.label.view"/>');
				} 
			},
			{className: "text-center", data: function ( row ) {
					var html = '';
					
					html += xfy.html.editModalLarge.format('${pageContext.request.contextPath }/supplier/form?view=1&id=' + row.id, '<spring:message code="g.label.view"/>');
					
					<x:power type="function" code="supplier_edit">
					html += xfy.html.editModalLarge.format('${pageContext.request.contextPath }/supplier/form?id=' + row.id, '<spring:message code="g.label.edit"/>');
					</x:power>
					
					if (row.status === 0) {
						<x:power type="function" code="supplier_approved">
						html += xfy.html.confirmLink.format('${pageContext.request.contextPath }/supplier/approve?id=' + row.id + '&status=1', '<spring:message code="g.label.approved"/>');
						</x:power>
						<x:power type="function" code="supplier_delete">
						html += xfy.html.deleteLink.format('${pageContext.request.contextPath }/supplier/delete?id=' + row.id , '<spring:message code="g.label.delete"/>');
						</x:power>
					} else if (row.status === 1) {
						<x:power type="function" code="supplier_approved">
						html += xfy.html.confirmLink2.format('${pageContext.request.contextPath }/supplier/approve?id=' + row.id + '&status=0', '<spring:message code="g.label.unapproved"/>');
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