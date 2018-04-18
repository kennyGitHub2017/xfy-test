<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:set scope="request" var="sysModule" value="product" />
<c:set scope="request" var="sysPage" value="goods_category" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">产品分类</tiles:putAttribute>
	
	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/admin/pages/css/todo.css"/>

	</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
			<div class="row">
				<div class="col-md-12">
				
				<%-- <div class="todo-sidebar">
						<div class="portlet light">
							<div class="portlet-title">
								<div class="caption" data-toggle="collapse" data-target=".todo-project-list-content">
									<span class="caption-subject font-green-sharp bold uppercase">产品分类 </span>
								</div>
								<div class="actions">
									<div class="btn-group">
										<a class="btn green-haze btn-circle btn-sm todo-projects-config" href="${pageContext.request.contextPath }/goodscategory/firstcgy-list">
										<i class="icon-settings"></i>
										</a>
									</div>
								</div>
							</div>
							<div class="portlet-body todo-project-list-content">
								<div class="todo-project-list">
									<ul class="nav nav-pills nav-stacked">
										<li class='active'>
											<a href="${pageContext.request.contextPath }/goodscategory/firstcgy-list">产品大类</a>
										</li>
										<li>
											<a href="${pageContext.request.contextPath }/goodscategory/categorytype-list">产品类目</a>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div> --%>
					
					
					<div class="todo-content">
						<div class="portlet light">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-cogs font-green-sharp"></i>
									<span class="caption-subject font-green-sharp bold uppercase">产品大类</span>
								</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"></a>
								</div>
							</div>
							<div class="portlet-body">
								<%@include file="../include/message.jsp" %>
							
								<div class="table-toolbar">
									<div class="row">
										<div class="col-md-6">
											<div class="btn-group">
												<a class=" btn green" href="${pageContext.request.contextPath}/goodscategory/firstcgy-form" data-target="#ajax" data-toggle="modal" data-backdrop="static">
													<spring:message code="g.label.add"/>  <i class="fa fa-plus"></i>
												</a>
											</div>
										</div>
									</div>
								</div>
								
							<ul class="nav nav-tabs">
									<li  class="active">
										<a href="${pageContext.request.contextPath }/goodscategory/firstcgy-list">产品大类</a>
									</li>
									
									<li>
										<a href="${pageContext.request.contextPath }/goodscategory/categorytype-list">产品类目</a>
									</li>
							</ul>
								
							
								<div class="table-scrollable">
									<table class="table table-hover table-striped table-bordered table-condensed">
									<thead>
										<tr>
											<th>编号</th>
											<th>产品类别</th>
											<th>编码</th>
											<th>备注</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="cgy" items="${firstCategory}" varStatus="status">
									<tr>
										<td>${status.index + 1 }</td>
										<td>${cgy.name }</td>
										<td>${cgy.code }</td>
										<td>${cgy.note }</td>
										<td>
											<a class="btn default btn-xs purple" href="${pageContext.request.contextPath }/goodscategory/firstcgy-form?id=${cgy.id}" data-target="#ajax" data-toggle="modal" data-backdrop="static">
												<i class="fa fa-edit"></i> <spring:message code="g.label.edit"/>
											</a>
											<a href="${pageContext.request.contextPath }/goodscategory/firstcgy-remove/${cgy.id}" class="btn default btn-xs black c-del-btns">
												<i class="fa fa-trash-o"></i> <spring:message code="g.label.delete"/>
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
			
			<%@include file="/WEB-INF/view/include/modal-ajax.jsp" %>
	</tiles:putAttribute>
	
		
	<%-- 页面级别的 JS --%>
	<%-- <tiles:putAttribute name="js-page"></tiles:putAttribute> --%>
</tiles:insertDefinition>