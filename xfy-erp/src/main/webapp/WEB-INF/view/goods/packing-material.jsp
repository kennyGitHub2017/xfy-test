<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>
<c:set scope="request" var="sysModule" value="product" />
<c:set scope="request" var="sysPage" value="packing_material" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">产品包装材料</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
			<div class="row">
				<div class="col-md-12">
				
				
				<!-- BEGIN SAMPLE TABLE PORTLET-->
			<div class="portlet light">
				
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs font-green-sharp"></i> <span
							class="caption-subject font-green-sharp bold uppercase">包装材料</span>
							
					</div>

				</div>


				<div class="table-toolbar">
					<div class="row">
						<div class="col-md-6">
							<div class="btn-group">
						<x:power type="function" code="packing_material_add">
						<a class=" btn green" href="${pageContext.request.contextPath }/packingMaterial/form?flag=0" 
						data-target="#ajax" data-toggle="modal" data-backdrop="static">
									<spring:message code="g.label.add"/> <i class="fa fa-plus"></i>
						</a></x:power>
							</div>
						</div>

					</div>
				</div>


				<div class="portlet-body">
					<%@include file="../include/message.jsp" %>
					<div class="table-scrollable">
						<table class="table table-hover table-bordered table-center table-condensed">
							<thead>
								<tr>
									
									<th>型号</th>
									<th>包装材料</th>
									<th>备注</th>
									<th>重量</th>
									<th>价格</th>
									<th>添加时间</th>
									<th>修改时间</th>
									<th>修改</th>
									<th>删除</th>
									
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${packingMaterial}" var="item">
								<tr>
								
									<td>${item.model}</td>
									<td>${item.rules}</td>
									<td>${item.note}</td>
									<td>${item.weight}</td>
									<td>${item.price}</td>
									<td><fmt:formatDate value="${item.createdTime}" type="both"/></td>
									<td><fmt:formatDate value="${item.lastUpdatedTime}" type="both"/></td>
									
								<td><x:power type="function" code="packing_material_edit"><a  href="${pageContext.request.contextPath }/packingMaterial/form?flag=1&id=${item.id}" data-target="#ajax" data-toggle="modal" data-backdrop="static" class="btn default btn-xs purple">
										<i class="fa fa-edit"></i> <spring:message code="g.label.edit"/></a></x:power></td>
								
								<td><x:power type="function" code="packing_material_delete"><a href="${pageContext.request.contextPath }/packingMaterial/remove?id=${item.id}" class="btn default btn-xs black c-del-btns">
										<i class="fa fa-trash-o"></i><spring:message code="g.label.delete"/> </a></x:power></td>
										
								</tr>
						</c:forEach>

							</tbody>
						</table>
					</div>
				</div>
		
			</div>
			<!-- END SAMPLE TABLE PORTLET-->
	

				
				
				
				
	
				</div>
			</div>
			<%@include file="/WEB-INF/view/include/modal-ajax.jsp" %>
	</tiles:putAttribute>
</tiles:insertDefinition>
