<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">运输运费</tiles:putAttribute>

	<tiles:putAttribute name="page-content">
		<div class="row">
		
			<div class="col-md-12">
				<div class="portlet light ">

					<div class="portlet-title">
						<div class="caption caption-md">
					<div class="caption">
						<i class="fa fa-cogs font-green-sharp"></i> <span
							class="caption-subject font-green-sharp bold uppercase">运输运费</span>
					</div>
						</div>
					</div>

					<div class="table-toolbar">
						<div class="row">
							<div class="col-md-6">
								<div class="btn-group">

								<%-- 	<a class=" btn green"
										href="${pageContext.request.contextPath }/shipping/editFee"
										data-target="#modal-large" data-toggle="modal"> --%>
										
										<a class=" btn green" href="${pageContext.request.contextPath }/shipping/editFee?shipId=${shipId}&type=0" data-target="#modal-large" data-toggle="modal" data-backdrop="static">
										<spring:message code="g.label.add"/> <i class="fa fa-plus"></i>
									</a>
								</div>
							</div>
						</div>
					</div>

					<%@include file="../include/message.jsp" %>
					<div class="table-scrollable">
						<table class="table table-hover">
						
							<thead>
								<tr>
									<th>计费类型</th>
									<th>增项费</th>
									<th>减项费</th>
									<th>邮费</th>
									
									<th>区域</th>
								<!-- 	<th>国家</th> -->
									<th>首重G</th>
									<th>首重金额G/元</th>
									<th>继重</th>
									<th>继重金额G/元</th>
									<th>挂号费</th>
									<th>处理费</th>
									<th>折扣率</th>
									<th>折扣费</th>
									<th>最小重量</th>
									<th>最小金额</th>
									<th>删除</th>
									<th>修改</th>
									<th>国家查看</th
									
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${shipFee}" var="item">
								<tr align="center">
									<td>${item.type == '1' ? '重量计算' : '首/续计算' }</td>
									<td>${item.addFee}</td>
									<td>${item.subtractFee}</td>
									<td>${item.postFee}</td>
									
									<td>${item.region}</td>
									<%-- <td>${item.country}</td> --%>
									<td>${item.firstWeight}</td>
									<td>${item.firstWeightAmount}</td>
									<td>${item.addWeight}</td>
									<td>${item.addWeightAmount}</td>
									<td>${item.registeredFee}</td>
									<td>${item.serviceFee}</td>
									<td>${item.discountRate}</td>
									<td>${item.discount}</td>
									<td>${item.minWeight}</td>
									<td>${item.minWeightAmount}</td>
									<td><a href="${pageContext.request.contextPath }/shipping/removeShipFee?id=${item.id}&shipId=${item.shippingId}" class="btn default btn-xs black c-del-btns">
								<i class="fa fa-trash-o"></i> <spring:message code="g.label.delete" /></a></td>
								
								<td>
								<a href="${pageContext.request.contextPath }/shipping/editFee?shipId=${item.shippingId}&id=${item.id}" data-target="#modal-large" data-toggle="modal"  class="btn default btn-xs purple">
								<i class="fa fa-edit"></i> <spring:message code="g.label.edit" /></a></td>
									
								<td>
									<a href="${pageContext.request.contextPath }/shipping/shipFeeCountry-log?id=${item.id}" data-target="#modal-large" data-toggle="modal" data-backdrop="static" class="btn default btn-xs purple">
									<i class="fa fa-edit"></i> 查看国家</a></td>
								</td>
								</tr>
							</c:forEach>

							</tbody>
						</table>




				</div>
			</div>

				<!-- 添加对话话框 -->
				<div id="editshippingFeeModal" class="modal fade modal-scroll"
					tabindex="-1" data-replace="true">
					<div class="modal-dialog">
						<div class="modal-content"></div>
					</div>
				</div>
				
				
				
				
			</div>
		</div>
			<%@include file="/WEB-INF/view/include/modal-large.jsp" %>
	</tiles:putAttribute>

</tiles:insertDefinition>