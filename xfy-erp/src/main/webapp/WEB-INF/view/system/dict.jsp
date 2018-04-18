<%--
字典管理
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="dict_code" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">字典维护</tiles:putAttribute>
	
	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/admin/pages/css/todo.css"/>

	</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
			<div class="row">
				<div class="col-md-12">
					<div class="todo-sidebar">
						<div class="portlet light">
							<div class="portlet-title">
								<div class="caption" data-toggle="collapse" data-target=".todo-project-list-content">
									<span class="caption-subject font-green-sharp bold uppercase">字典类型 </span>
								</div>
								<div class="actions">
									<div class="btn-group">
										<a class="btn green-haze btn-circle btn-sm todo-projects-config" href="${pageContext.request.contextPath }/dict">
										<i class="icon-settings"></i>
										</a>
									</div>
								</div>
							</div>
							<div class="portlet-body todo-project-list-content">
								<div class="todo-project-list">
									<ul class="nav nav-pills nav-stacked">
										<c:forEach var="dict" items="${typeList }" varStatus="status">
										<li class="${dict.code == curType.code ? 'active' : '' }">
											<a href="${pageContext.request.contextPath }/dict?type=${dict.code}">${dict.name }</a>
										</li>
										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="todo-content">
						<div class="portlet light">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-cogs font-green-sharp"></i>
									<span class="caption-subject font-green-sharp bold uppercase">${curType.name }</span>
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
												<a class=" btn green" href="${pageContext.request.contextPath }/dict/form?type=${curType.code }" data-target="#ajax" data-toggle="modal" data-backdrop="static">
													<spring:message code="g.label.add"/>  <i class="fa fa-plus"></i>
												</a>
											</div>
										</div>
									</div>
								</div>
							
								<div class="table-scrollable">
									<table class="table table-hover table-striped table-bordered">
									<thead>
									<tr>
										<th>#</th>
										<th>代码值</th>
										<th>描述信息</th>
										<th>&nbsp;</th>
									</tr>
									</thead>
									<tbody>
									<c:forEach var="dict" items="${dictList }" varStatus="status">
									<tr>
										<td>${status.index + 1 }</td>
										<td>${dict.code }</td>
										<td>${dict.name }</td>
										<td>
											<a class="btn default btn-xs purple" href="${pageContext.request.contextPath }/dict/form?type=${dict.type }&code=${dict.code }" data-target="#ajax" data-toggle="modal" data-backdrop="static">
												<i class="fa fa-edit"></i> <spring:message code="g.label.edit"/>
											</a>
											<a href="${pageContext.request.contextPath }/dict/delete?type=${dict.type}&code=${dict.code }" class="btn default btn-xs black c-del-btns">
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