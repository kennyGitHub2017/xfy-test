<%--
角色页面
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:set var="pageTitle" value="权限列表" />
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
							<div class="table-scrollable">
								<table class="table table-bordered table-hover">
								<thead>
								<tr>
									<th>名称</th>
									<th>标识</th>
									<th>类型</th>
								</tr>
								</thead>
								<tbody>
								<c:forEach var="module" items="${modules }" varStatus="status">
								<tr>
									<td><label> <input type="checkbox" id="module_${module.code }" name="modulePower" value="${module.code }" /> ${module.name }</label></td>
									<td>${module.code }</td>
									<td>模块</td>
								</tr>
								
								<c:forEach var="page" items="${module.pages }" varStatus="status">
								<tr>
									<td><label style="padding-left: 30px;"> <input type="checkbox" id="page_${page.code }" pid="module_${module.code }" name="pagePower" value="${page.code }" /> ${page.name }</label></td>
									<td>${page.code }</td>
									<td>页面</td>
								</tr>
								
								<c:forEach var="function" items="${page.functions }" varStatus="status">
								<tr>
									<td><label style="padding-left: 60px;"> <input type="checkbox" id="function_${function.code }" pid="page_${page.code }" name="functionPower" value="${function.code }" /> ${function.name }</label></td>
									<td>${function.code }</td>
									<td>功能</td>
								</tr>
								</c:forEach>
								
								</c:forEach>
								
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
	<tiles:putAttribute name="js-page">
		<script>
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>