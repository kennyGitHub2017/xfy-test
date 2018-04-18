<%--
编辑菜单页面
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="edit_menu" />

<c:set var="pageTitle" value="编辑菜单" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">${pageTitle }</tiles:putAttribute>
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
											<a class=" btn green btn-sm" href="${pageContext.request.contextPath }/sys-module/form?action=add" data-target="#ajax" data-toggle="modal" data-backdrop="static">
												添加模块 <i class="fa fa-plus"></i>
											</a>
											<a class=" btn green btn-sm" href="${pageContext.request.contextPath }/sys-page/form?action=add" data-target="#ajax" data-toggle="modal" data-backdrop="static">
												添加页面 <i class="fa fa-plus"></i>
											</a>
											<a class=" btn green btn-sm" href="${pageContext.request.contextPath }/sys-function/form?action=add" data-target="#ajax" data-toggle="modal" data-backdrop="static">
												添加功能 <i class="fa fa-plus"></i>
											</a>
									</div>
								</div>
							</div>
							<div class="table-scrollable">
								<form action="${pageContext.request.contextPath }/sys-module" method="get">
								<div>
									<table class="table table-bordered table-hover table-condensed">
									<thead>
									<tr>
										<th>名称</th>
										<th>编码</th>
										<th>路径</th>
										<th>排序</th>
										<th>模块编码</th>
										<th>所属系统</th>
										<th>类型</th>
										<th>操作</th>
									</tr>
									</thead>
									<tbody>
									<c:forEach var="module" items="${modules }" varStatus="status">
									<tr>
										<td><label>${module.name }</label></td>
										<td>${module.code }</td>
										<td>${module.url }</td>
										<td>${module.sort }</td>
										<td></td>
										<td></td>
										<td>模块</td>
										<td>
											<a class="btn default btn-xs purple" href="${pageContext.request.contextPath }/sys-module/form?action=update&code=${module.code }" data-target="#ajax" data-toggle="modal" data-backdrop="static">
												<i class="fa fa-edit"></i> <spring:message code="g.label.edit"/>
											</a>
											<a href="${pageContext.request.contextPath }/sys-module/delete?code=${module.code }" class="btn default btn-xs black c-del-btns">
												<i class="fa fa-trash-o"></i> <spring:message code="g.label.delete"/>
											</a>
										</td>
									</tr>
									
									<c:forEach var="page" items="${module.pages }" varStatus="status">
										<tr>
											<td><label style="padding-left: 30px;">${page.name }</label></td>
											<td>${page.code }</td>
											<td>${page.url }</td>
											<td>${page.sort }</td>
											<td>${page.moduleCode }</td>
											<td>${page.sysCode }</td>
											<td>页面</td>
											<td>
												<a class="btn default btn-xs purple" href="${pageContext.request.contextPath }/sys-page/form?action=update&code=${page.code}" data-target="#ajax" data-toggle="modal" data-backdrop="static">
													<i class="fa fa-edit"></i> <spring:message code="g.label.edit"/>
												</a>
												<a href="${pageContext.request.contextPath }/sys-page/delete?code=${page.code}" class="btn default btn-xs black c-del-btns">
													<i class="fa fa-trash-o"></i> <spring:message code="g.label.delete"/>
												</a>
											</td>
										</tr>
										
										<c:forEach var="function" items="${page.functions }" varStatus="status">
										<tr>
											<td><label style="padding-left: 60px;">${function.name }</label></td>
											<td>${function.code }</td>
											<td></td>
											<td></td>
											<td>${function.pageCode }</td>
											<td></td>
											<td>功能</td>
											<td>
												<a class="btn default btn-xs purple" href="${pageContext.request.contextPath }/sys-function/form?action=update&code=${function.code}" data-target="#ajax" data-toggle="modal" data-backdrop="static">
													<i class="fa fa-edit"></i> <spring:message code="g.label.edit"/>
												</a>
												<a href="${pageContext.request.contextPath }/sys-function/delete?code=${function.code}" class="btn default btn-xs black c-del-btns">
												<i class="fa fa-trash-o"></i> <spring:message code="g.label.delete"/>
												</a>
											</td>
										</tr>
										</c:forEach>
									</c:forEach>
									
									</c:forEach>
									</tbody>
									</table>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
			
		<%@include file="/WEB-INF/view/include/modal-ajax.jsp" %>
	</tiles:putAttribute>
	
</tiles:insertDefinition>