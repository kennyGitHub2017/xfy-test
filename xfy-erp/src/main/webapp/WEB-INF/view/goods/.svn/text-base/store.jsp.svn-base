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
	</tiles:putAttribute>

	<tiles:putAttribute name="page-content">
		<div class="row">
			<div class="col-md-12">
			
		
<%-- 				<div class="todo-sidebar">
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption" data-toggle="collapse" data-target=".todo-project-list-content">
								<span class="caption-subject font-green-sharp bold uppercase">仓库/仓位</span>
							</div>
						</div>
						<div class="portlet-body todo-project-list-content">
							<div class="todo-project-list">
								<ul class="nav nav-pills nav-stacked">
									<li class="${flag == '0'? 'active' : '' }"><a href="${pageContext.request.contextPath }/store">仓库</a></li>
									<li class="${flag == '1'? 'active' : '' }"><a href="${pageContext.request.contextPath }/store/shelf">仓位</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div> --%>
				
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
										<div class="btn-group">
											<x:power type="function" code="store_add">
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
							
							
							<div class="table-scrollable">
								<table class="table table-hover table-striped table-bordered table-condensed">
									<thead>
										<tr>
											<th>仓库名称</th>
											<th>仓库编码</th>
											<th>类型</th>
											<th>地址</th>
											<th>备注</th>
											<th>修改</th>
											<th>删除</th>
											<th>仓位</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item" items="${store}" varStatus="status">
											<tr>
												<td>${item.name}</td>
												<td>${item.code}</td>
												<td>${item.type == 0 ? '可用仓' : (item.type == 1 ? '虚拟仓' : (item.type == 2 ? '不合格仓' : '&nbsp;'))}</td>
												<td>${item.location}</td>
												<td>${item.note}</td>
												<td><x:power type="function" code="store_edit">
												<a href="${pageContext.request.contextPath }/store/storeForm?editType=1&id=${item.id}" data-target="#ajax" data-toggle="modal" class="btn default btn-xs purple"> <i class="fa fa-edit"></i> <spring:message code="g.label.edit" /></a>
												</x:power></td>
												<td><x:power type="function" code="store_delete">
												<a href="${pageContext.request.contextPath }/store/removeStore?id=${item.id}" class="btn default btn-xs black c-del-btns"> <i class="fa fa-trash-o"></i> <spring:message code="g.label.delete" /></a></x:power></td>
												<td>
													<a href="${pageContext.request.contextPath }/store/shelf?id=${item.id}" class="btn default btn-xs black "> <i class="fa fa-edit"></i>所有仓位
													</a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@include file="/WEB-INF/view/include/modal-ajax.jsp"%>
	</tiles:putAttribute>
</tiles:insertDefinition>