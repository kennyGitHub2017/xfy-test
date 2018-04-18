<%--
部门管理
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x" %>

<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="department" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">部门管理</tiles:putAttribute>
	
	<%-- 页面级别的 CSS --%>
	<%-- <tiles:putAttribute name="css-page"></tiles:putAttribute> --%>
	
	<tiles:putAttribute name="page-content">
			<div class="row">
				<div class="col-md-12">
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-cogs font-green-sharp"></i>
								<span class="caption-subject font-green-sharp bold uppercase">部门管理</span>
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
										<x:power type="function" code="deparment_add">
											<a class=" btn green" href="${pageContext.request.contextPath }/department/form" data-target="#ajax" data-toggle="modal" data-backdrop="static">
												<spring:message code="g.label.add"/> <i class="fa fa-plus"></i>
											</a>
										</x:power>
										</div>
									</div>
								</div>
							</div>
						
							<div class="table-scrollable">
								<table class="table table-striped table-bordered table-hover">
								<thead>
								<tr>
									<th>#</th>
									<th>部门名称</th>
									<th>部门领导</th>
									<th>备注</th>
									<th class="text-center">更新时间</th>
									<th class="text-center">操作</th>
								</tr>
								</thead>
								<tbody>
								<c:forEach var="item" items="${departments }" varStatus="status">
								<tr>
									<td>${status.index + 1 }</td>
									<td>${item.name }</td>
									<td>${item.leaderName }</td>
									<td>${item.note }</td>
									<td class="text-center"><fmt:formatDate value="${item.lastUpdatedTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td class="text-center">
										<x:power type="function" code="deparment_edit">
											<a class="btn default btn-xs purple" href="${pageContext.request.contextPath }/department/form?id=${item.id }" data-target="#ajax" data-toggle="modal" data-backdrop="static">
												<i class="fa fa-edit"></i> <spring:message code="g.label.edit"/>
											</a>
										</x:power>
										<x:power type="function" code="deparment_delete">
											<a href="${pageContext.request.contextPath }/department/delete?id=${item.id }" class="btn default btn-xs black c-del-btns">
												<i class="fa fa-trash-o"></i> <spring:message code="g.label.delete"/>
											</a>
										</x:power>
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