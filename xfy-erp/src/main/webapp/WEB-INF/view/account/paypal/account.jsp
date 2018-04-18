<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x" %>

<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="paypal_account" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">paypal账号页面</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
			<div class="row">
	
	<div class="col-md-12">
	
			<!-- BEGIN SAMPLE TABLE PORTLET-->
			<div class="portlet light">
				
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs font-green-sharp"></i> <span
							class="caption-subject font-green-sharp bold uppercase">Paypal信息</span>
							
					</div>

				</div>




				<div class="table-toolbar">
					<div class="row">
						<div class="col-md-6">
							<div class="btn-group">
			<x:power type="function" code="paypal_account_add">
			<a class=" btn green" href="${pageContext.request.contextPath }/paypalAccount/form?flag=0" 
					data-target="#editPaypalAccountModal" data-toggle="modal">
			<spring:message code="g.label.add"/> <i class="fa fa-plus"></i>
			</a></x:power>
							</div>
						</div>

					</div>
				</div>



				<div class="portlet-body">
				<%@include file="../../include/message.jsp" %>
					<div class="table-scrollable">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>#ID</th>
									<th>paypal账号</th>
									<th>api用户名</th>
									<th>api密码</th>
									<th>api签名</th>
									<th>所属ebay账号</th>
									<th>费率</th>
									<th>添加时间</th>
									<th>最后更新时间</th>
									<th>修改</th>
									<th>删除</th>
									
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${paypalList}" var="paypal">
								<tr>
									<td>${paypal.id}</td>
									<td>${paypal.account}</td>
									<td>${paypal.apiUserName}</td>
									<td>${paypal.apiPassword}</td>
									<td>${paypal.apiSignature}</td>
									<td>${paypal.ebayAccount}</td>
									<td>${paypal.fees}</td>
									<td><fmt:formatDate value="${paypal.createdTime}" type="both"/></td>
									<td><fmt:formatDate value="${paypal.lastUpdatedTime}" type="both"/></td>
									
									<td><x:power type="function" code="paypal_account_edit"><a href="${pageContext.request.contextPath }/paypalAccount/form?flag=1&id=${paypal.id}" 
									data-target="#editPaypalAccountModal" data-toggle="modal" class="btn default btn-xs purple">
										<i class="fa fa-edit"></i><spring:message code="g.label.edit"/></a></x:power></td>
									<td><x:power type="function" code="paypal_account_delete">
									 <a href="${pageContext.request.contextPath }/paypalAccount/remove?id=${paypal.id}" class="btn default btn-xs black c-del-btns">
										<i class="fa fa-trash-o"></i> <spring:message code="g.label.delete"/>  </a></x:power></td>
								</tr>
						</c:forEach>

							</tbody>
						</table>
					</div>
				</div>
		
			</div>
			<!-- END SAMPLE TABLE PORTLET-->

<!-- 添加对话话框 -->
<div id="editPaypalAccountModal" class="modal fade modal-scroll" tabindex="-1" data-replace="true">
	<div class="modal-dialog">
		<div class="modal-content">
		</div>
	</div>
</div>

	</div>
			</div>
	</tiles:putAttribute>
</tiles:insertDefinition>