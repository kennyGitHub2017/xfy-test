<%--
标发结果页面
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:set scope="request" var="sysModule" value="order" />
<c:set scope="request" var="sysPage" value="order-list" />

<c:set var="pageTitle" value="申请跟踪号" />
<tiles:insertDefinition name="emptyMetronicTemplate">
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
								<table class="table table-striped table-bordered table-hover table-condensed">
								<thead>
								<tr>
									<th>#</th>
									<th>订单ID</th>
									<th>包裹ID</th>
									<th>结果</th>
									<th>跟踪号</th>
									<th>备注</th>
								</tr>
								</thead>
								<tbody>
								<c:forEach var="item" items="${results }" varStatus="status">
								<tr class="${item.success ? 'success' : 'danger' }">
									<td>${status.index + 1 }</td>
									<td>${item.orderId }</td>
									<td>${item.packageId }</td>
									<td>${item.success ? '成功' : '失败' }</td>
									<td>${item.trackNumber }</td>
									<td>${item.message }</td>
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