<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>
<c:set scope="request" var="sysModule" value="product" />
<c:set scope="request" var="sysPage" value="store" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">仓库 仓位</tiles:putAttribute>

	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/admin/pages/css/todo.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>

	</tiles:putAttribute>

	<tiles:putAttribute name="page-content">
		<div class="row">
			<div class="col-md-12">

				<!-- START -->
				<div class="todo-content">
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-cogs font-green-sharp"></i> <span class="caption-subject font-green-sharp bold uppercase">${flag == '0'? '仓库' : '仓位'}</span>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
							</div>
						</div>
						<div class="portlet-body">
							<%@include file="../include/message.jsp"%>

							<div class="table-toolbar">
								<div class="row">
									<div class="col-md-6">
										<div class="btn-group"><x:power type="function" code="store_shelf_add">
											<a class=" btn green" href="${pageContext.request.contextPath }/store/${flag == '0' ? 'storeForm' : 'shelfForm'}?editType=0" data-target="#ajax" data-toggle="modal" data-backdrop="static"> <spring:message code="g.label.add" /> <i class="fa fa-plus"></i>
											</a></x:power>
										</div>
									</div>
								</div>
							</div>
							
							<ul class="nav nav-tabs">
						
									<li class="${flag == 0 ? 'active' : '' }">
										<a href="${pageContext.request.contextPath }/store">仓库</a>
									</li>
									<li class="${flag == 1 ? 'active' : '' }">
										<a href="${pageContext.request.contextPath }/store/shelf">仓位</a>
									</li>
							</ul>
								
							<table class="table table-hover table-striped table-bordered table-condensed" id="shelf_table">
								<thead>
									<tr>
										<th>#ID</th>
										<th>仓位编码</th>
										<th>所属仓库</th>
										<th>备注</th>
										<th>修改</th>
										<th>删除</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<!-- END -->

			</div>
		</div>
		<%@include file="/WEB-INF/view/include/modal-ajax.jsp"%>
	</tiles:putAttribute>
	<%-- 页面级别的 JS --%>
	<tiles:putAttribute name="js-page">
	<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
	<script>
	
	$(function(){
		xfy.initDataTable();
		initFun();
	});

	function initFun() {
		$("#shelf_table").dataTable({
			serverSide : true,
			searching : true,
			ajax : {
				url : "${pageContext.request.contextPath }/store/shelf-page.json?storeId=${param.id}",
				type : 'post',
				contentType : "application/json;charset=UTF-8",
				data : function(d) {
					return JSON.stringify(d);
				}
			},
			columns : [
					
					{data: "id"},
					{data: "code"},
					{data: "storeName"},
					{data: "note"},
					{data: function(row){
						var html = '',
						html = '<a href="${pageContext.request.contextPath }/store/shelfForm?editType=1&id='+row.id+'" data-target="#ajax" data-toggle="modal" class="btn default btn-xs purple"> <i class="fa fa-edit"></i> <spring:message code="g.label.edit" /></a>';
						return html;
					}},
					{data: function(row){
						var html = '';
						html = '<a href="${pageContext.request.contextPath }/store/removeShelf?id='+row.id+'" class="btn default btn-xs black c-del-btns"> <i class="fa fa-trash-o"></i> <spring:message code="g.label.delete" /></a>';
						return html;
					}},
					
					]
			});
		}
		
	</script>
	
	</tiles:putAttribute>
</tiles:insertDefinition>