<%--
角色页面
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="role" />

<c:set var="pageTitle" value="角色" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">${pageTitle }</tiles:putAttribute>
	
	<%-- 页面级别的 CSS --%>
	<%-- <tiles:putAttribute name="css-page"></tiles:putAttribute> --%>
	
	<tiles:putAttribute name="page-content">
			<div class="row">
				<div class="col-md-12">
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-cogs font-green-sharp"></i>
								<span class="caption-subject font-green-sharp bold uppercase">${pageTitle }</span>
							</div>
						</div>
						<div class="portlet-body">
							<%@include file="../include/message.jsp" %>
						
							<div class="table-toolbar">
								<div class="row">
									<div class="col-md-6">
										<div class="btn-group">
											<a class=" btn green" href="${pageContext.request.contextPath }/role/form" data-target="#ajax" data-toggle="modal" data-backdrop="static">
												<spring:message code="g.label.add"/> <i class="fa fa-plus"></i>
											</a>
										</div>
									</div>
								</div>
							</div>
						
							<div class="table-scrollable">
								<table class="table table-striped table-bordered table-hover">
								<thead>
								<tr>
									<th>#</th>
									<th>角色名称</th>
									<th>备注</th>
									<th>更新时间</th>
									<th>操作</th>
								</tr>
								</thead>
								<tbody>
								<c:forEach var="item" items="${items }" varStatus="status">
								<tr>
									<td>${status.index + 1 }</td>
									<td>${item.name }</td>
									<td>${item.note }</td>
									<td><fmt:formatDate value="${item.lastUpdatedTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td>
										<a class="btn default btn-xs purple" href="${pageContext.request.contextPath }/role/form?id=${item.id }" data-target="#ajax" data-toggle="modal" data-backdrop="static">
											<i class="fa fa-edit"></i> <spring:message code="g.label.edit"/>
										</a>
										<a class="btn default btn-xs purple" href="${pageContext.request.contextPath }/role/power?id=${item.id }" target="_role_power">
											<i class="fa fa-edit"></i> <spring:message code="g.label.set.power" />
										</a>
										<c:if test="${item.id >= 10 }">
										<a href="${pageContext.request.contextPath }/role/delete?id=${item.id }" class="btn default btn-xs black c-del-btns">
											<i class="fa fa-trash-o"></i> <spring:message code="g.label.delete"/>
										</a>
										</c:if>
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
			
			<%@include file="/WEB-INF/view/include/modal-ajax.jsp" %>
	</tiles:putAttribute>
	
	<%-- 页面级别的 JS --%>
	<%-- <tiles:putAttribute name="js-page"></tiles:putAttribute> --%>
</tiles:insertDefinition>