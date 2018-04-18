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

<c:set var="pageTitle" value="公告管理" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">${pageTitle }</tiles:putAttribute>
	
	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">
	
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-summernote/summernote.css">

	</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
			<div class="row">
				<div class="col-md-12">
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-cogs font-green-sharp"></i>
								<span class="caption-subject font-green-sharp bold uppercase">公告管理</span>
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
											<a class=" btn green" href="${pageContext.request.contextPath }/notice/form" data-target="#modal-large" data-toggle="modal" data-backdrop="static">
												<spring:message code="g.label.add"/>  <i class="fa fa-plus"></i>
											</a>
										</x:power>
										</div>
									</div>
									
								</div>
							</div>
						
							<%@include file="../include/message.jsp" %>
						
							<table id="list_table" class="table table-hover table-striped table-bordered table-condensed">
								<thead>
								<tr>
									<th>编码</th>
									<th>标题</th>
									<th>内容</th>
									<th>开始时间</th>
									<th>结束时间</th>
									<th>创建时间</th>
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
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-summernote/summernote.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-summernote/summernote-zh-CN.js" type="text/javascript"></script>
<script>

$(function(){
	xfy.initDataTable();
	$("#list_table").dataTable({
		searching: false,
		serverSide: true,
		ajax: {
			url: "${pageContext.request.contextPath }/notice/page.json",
			data: function (d) {
				return JSON.stringify( d );
			},
			type: 'post',
			contentType:"application/json;charset=UTF-8"
		},
		columns: [
			{data: "id"},
			{data: "title"},
			{data: "content", className: "c-wrap"},
			{data: "startTime", className: "text-right"},
			{data: "endTime", className: "text-right"},
			{data: "createdTime", className: "text-right"},
			{className: "text-center", data: function ( row ) {
					var html = '';
					html += xfy.html.editModalLarge.format('${pageContext.request.contextPath }/notice/form?id=' + row.id, '<spring:message code="g.label.edit"/>');
					html += xfy.html.deleteLink.format('${pageContext.request.contextPath }/notice/delete?id=' + row.id , '<spring:message code="g.label.delete"/>');
					
					return html;
				}
			}
		],

	});
});
</script>

	</tiles:putAttribute>
</tiles:insertDefinition>