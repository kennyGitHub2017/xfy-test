<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>

<c:set scope="request" var="sysModule" value="purchase" />
<c:set scope="request" var="sysPage" value="purchaserequest_order" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">请购单详情</tiles:putAttribute>
	
	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>

	</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
			<div class="row">
				<div class="col-md-12">
						<div class="portlet light">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-cogs font-green-sharp"></i>
									<span class="caption-subject font-green-sharp bold uppercase">请购单详情</span>
								</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"></a>
								</div>
							</div>
							<div class="portlet-body">
								<div class="table-scrollable">
									<table class="table table-hover table-striped table-bordered" id="allocateorder-table">
									<thead>
									<tr>
										<th>
											编号
										</th>
										<th>
											申请日期
										</th>
										<th>
											请购单号
										</th>
										<th>
											SKU
										</th>
										<th>图片</th>
										<th>
											名称
										</th>
										<th>描述</th>
										<th>
											单位
										</th>
										<th>
											数量
										</th>
										<th>
											单价
										</th>
										<th>
											金额
										</th>
										<th>
											供应商
										</th>
										<th>
											交货日期
										</th>
										<th>
											采购日期
										</th>
										<th>
											申请人
										</th>
										<th>
											采购人员
										</th>
										<th>
											采购单号
										</th>
										<th>
											是否转采购
										</th>
										<th>
											源单类型
										</th>
										<th>
											订单编号
										</th>
									</tr>
									</thead>
									<tbody>
										<c:forEach items="${detailList }" var="item" varStatus="idx">
											<tr>
												<td>${idx.index+1}</td>
												<td>
													<f:formatDate value="${item.createdTime }"/>
												</td>
												<td>${item.orderNo }</td>
												<td>${item.goodsSku }</td>
												
												<td>
													<c:if test="${item.imgCount > 1}">
													<img alt="" class="small-img" src="${x:goodsImageThumbnailPath(item.goodsSku, 1,'S') }">
													</c:if>
												
												</td>
												<td>${item.goodsName }</td>
												<td>${item.color}-${item.goodsSize}-${item.rules}-${item.model}</td>
												<td>${item.goodsUnit }</td>
												<td>${item.goodsCount }</td>
												<td>${item.goodsCost }</td>
												<td>${item.goodsTotal }</td>
												<td>${item.supplierName }</td>
												<td>
													<f:formatDate value="${item.deliveryDate }"/>
												</td>
												<td>
													<f:formatDate value="${item.purchaseDate }"/>
												</td>
												<td>${item.createdUserName }</td>
												<td>${item.buyuserName }</td>
												<td>${item.purchaseOrderId }</td>
												<td>${item.status==2?'是':'否'}</td>
												<td>
													<c:choose>
														<c:when test="${item.type==1}">
															手工增加
														</c:when>
														<c:when test="${item.type==2}">
															库存预警
														</c:when>
														<c:when test="${item.type==3}">
															订单生成
														</c:when>
													</c:choose>
												</td>
												<td>
													${item.sellOrderId}
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
	</tiles:putAttribute>
</tiles:insertDefinition>