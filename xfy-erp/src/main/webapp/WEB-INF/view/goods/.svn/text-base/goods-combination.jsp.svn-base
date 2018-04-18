<%--
组合产品管理
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>

<c:set scope="request" var="sysModule" value="picture_admin" />
<c:set scope="request" var="sysPage" value="goods-combination" />
<c:if test="${param.forwhat == 'agent' }">
<c:set scope="request" var="sysModule" value="agent" />
<c:set scope="request" var="sysPage" value="agent-goods-combination" />
</c:if>
<c:set var="pageTitle" value="组合产品" />
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
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-cogs font-green-sharp"></i>
								<span class="caption-subject font-green-sharp bold uppercase">${pageTitle }</span>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="row">
									<div class="col-md-6">
										<x:power type="function" code="goods-combination-add">
										<div class="btn-group">
										
										<c:if test="${param.forwhat != 'agent' }">
											<a class=" btn green" href="${pageContext.request.contextPath }/goods-combination/form" data-target="#ajax" data-toggle="modal" data-backdrop="static">
												<spring:message code="g.label.add"/>  <i class="fa fa-plus"></i>
											</a>
										</c:if>
										
										</div>
										</x:power>
									</div>
								</div>
							</div>
						
							<%@include file="../include/message.jsp" %>
						
							<table id="list_table" class="table table-hover table-striped table-bordered table-condensed">
								<thead>
								<tr>
									<th>产品编码</th>
									<th>产品明细</th>
									<th>产品明细</th>
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
	$("#list_table").dataTable({
		searching: true,
		serverSide: true,
		ajax: {
			url: "${pageContext.request.contextPath }/goods-combination/page.json",
			data: function (d) {
				return JSON.stringify( d );
			},
			type: 'post',
			contentType:"application/json;charset=UTF-8"
		},
		columns: [
			{data: "combSku"},     
			{className: 'c-wrap', data: function(row) {
				var d = '';
				var items = row.items;
				for (var i = 0; i < items.length; i++) {
					var item = items[i];
					d += (item.oldSku + '*' + item.quantity + ", ");
				}
				return d;
			}},
			{className: 'c-wrap', data: function(row) {
				var d = '';
				var items = row.items;
				for (var i = 0; i < items.length; i++) {
					var item = items[i];
					d += (item.newSku + '*' + item.quantity + ", ");
				}
				return d;
			}},
			{className: "text-center", data: function ( row ) {
					var html = '';

				if(${param.forwhat != 'agent' }){
						
					<x:power type="function" code="goods-combination-modify">
					html += xfy.html.editLinkAjaxModal.format('${pageContext.request.contextPath }/goods-combination/form?id=' + row.id, '<spring:message code="g.label.edit"/>');
					</x:power>
					<x:power type="function" code="goods-combination-delete">
					html += xfy.html.deleteLink.format('${pageContext.request.contextPath }/goods-combination/delete?id=' + row.id , '<spring:message code="g.label.delete"/>');
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