<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>
<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="shipping" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">发货方式</tiles:putAttribute>

	<tiles:putAttribute name="page-content">
		<div class="row">

			<div class="col-md-12">

				<!-- BEGIN SAMPLE TABLE PORTLET-->
				<div class="portlet light">

					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-cogs font-green-sharp"></i> <span class="caption-subject font-green-sharp bold uppercase">发货方式</span>
						</div>

					</div>


					<div class="table-toolbar">
						<div class="row">

							<div class="col-md-6">
								<div class="btn-group">
									<x:power type="function" code="shipping_add">
										<a class=" btn green" href="${pageContext.request.contextPath }/shipping/openEdit"> <spring:message code="g.label.add" /> <i class="fa fa-plus"></i>
										</a>
									</x:power>
								</div>
							</div>

						</div>
					</div>


					<div class="portlet-body">

						<%@include file="../include/message.jsp"%>
						<table class="table table-hover table-bordered table-condensed">
	
							<thead>
								<tr>
									<th>#ID</th>
									<th>发货方式名称</th>
									<th>物流公司代号</th>
									<th>优先级</th>
									<th>备注/公司</th>
									<th>修改</th>
									<th>运费</th>
									<th>地址配置</th>
									<th>删除</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${shippingAttr}" var="shippingAttr">
									<tr align="center">
										<td>${shippingAttr.id}</td>
										<td>${shippingAttr.shippingName}</td>
										<td>${shippingAttr.carrierSn}</td>
										<td>${shippingAttr.priority}</td>
										<td>${shippingAttr.note}</td>
										<td>
											<x:power type="function" code="shipping_edit">
												<a href="${pageContext.request.contextPath }/shipping/openEdit?flag=1&id=${shippingAttr.id}" class="btn default btn-xs purple"> <i class="fa fa-edit"></i> <spring:message code="g.label.edit" /></a>
											</x:power>
										</td>
										<td>
											<c:if test="${shippingAttr.shippFeeType != 1}">
											<a href="${pageContext.request.contextPath }/shipping/shippingFee?id=${shippingAttr.id}" class="btn default btn-xs purple" target="_blank"> <i class="fa fa-edit"></i> 运费</a>
											</c:if>
											
											<c:if test="${shippingAttr.shippFeeType == 1}">
											<a href="${pageContext.request.contextPath }/shipping/shippingFee2?id=${shippingAttr.id}" class="btn default btn-xs purple" target="_blank"> <i class="fa fa-edit"></i> 运费</a>
											</c:if>
											
										</td>
										<td>
											<a href="${pageContext.request.contextPath }/shipping/address?id=${shippingAttr.id}" class="btn default btn-xs purple" target="_blank"> <i class="fa fa-edit"></i> 地址配置</a>
										</td>
										<td>
											<x:power type="function" code="shipping_delete">
												<a href="${pageContext.request.contextPath }/shipping/remove?id=${shippingAttr.id}" class="btn default btn-xs black c-del-btns"> <i class="fa fa-trash-o"></i> <spring:message code="g.label.delete" /></a>
											</x:power>
										</td>
									</tr>
								</c:forEach>
	
							</tbody>
						</table>
					</div>

				</div>
				<!-- END SAMPLE TABLE PORTLET-->



			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>