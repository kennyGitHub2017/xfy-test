<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>

<c:set scope="request" var="sysModule" value="purchase" />
<c:set scope="request" var="sysPage" value="purchase_order" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">采购单详情</tiles:putAttribute>
	
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
									<span class="caption-subject font-green-sharp bold uppercase">采购单详情</span>
								</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"></a>
								</div>
							</div>					
							<div class="portlet-body">
								<div><input type="button" class="btn green btn-sm" id="printbtn" value="打印"></div>
								<div class="table-scrollable">
									<table class="table table-hover table-striped table-bordered" id="purchaseorder-table">
									<thead>
									<tr>
										<th>
											编号
										</th>
										<th>
											采购日期
										</th>
										<th>
											采购单号
										</th>
										<th>
											SKU
										</th>
										<th>
											货位号
										</th>
										<th>描述</th>
										<th>图片</th>
										<th>
											产品类型
										</th>
										<th>
											名称
										</th>
										<th>
											单位
										</th>
										<th>
											数量
										</th>
										<th>
											采购单价
										</th>
										<th>
											金额
										</th>
										<th>
											已交数量
										</th>
										<th>
											未交数量
										</th>
										<th>
											合格数量
										</th>
										<th>
											不合格数量
										</th>
										<th>
											供应商
										</th>
										<th>
											要求交货日期
										</th>
										<th>
											状态
										</th>
										<th>
											源单类型
										</th>
										<th>
											运单号
										</th>
										<th>
											运费
										</th>
										<th>
											支付方式
										</th>
										<th>
											请购单号
										</th>
										<th>
											请购人
										</th>
										<th>
											审核人
										</th>
										<th>
											审核日期
										</th>
										<th>
											行关闭
										</th>
										<th>
											关闭
										</th>
									</tr>
									</thead>
									<tbody>
									<c:set var="totalCost" value="0"></c:set>
									<c:set var="totalBuy" value="0"></c:set>
									<c:set var="totalReceived" value="0"></c:set>
									<c:set var="totalUnReceived" value="0"></c:set>
									<c:set var="totalQualified" value="0"></c:set>
									<c:set var="totalunQualified" value="0"></c:set>
										<c:forEach items="${detailList }" var="item" varStatus="idx">
											<c:set var="totalCost" value="${totalCost+ item.goodsTotal}"></c:set>
										<c:set var="totalBuy" value="${totalBuy+ item.goodsCount}"></c:set>
										
										<c:set var="totalReceived"   value="${totalReceived+ item.receivedCount}"></c:set>
										<c:set var="totalUnReceived" value="${totalUnReceived+ item.unReceivedCount}"></c:set>
										<c:set var="totalQualified"   value="${totalQualified+ item.qualifiedCount}"></c:set>
										<c:set var="totalunQualified" value="${totalunQualified+ item.unQualifiedCount}"></c:set>
											<tr>
												<td>${idx.index+1}</td>
												<td>
													<f:formatDate value="${ item.purchaseDate }"/>
												</td>
												<td>${item.orderNo }</td>
												<td>${item.goodsSku }(${item.oldSku })</td>
												<td>${item.storeName }<br>${item.storeShelf }</td>
												<td>${item.color}-${item.goodsSize}-${item.rules}-${item.model}</td>
												<td>
												<c:if test="${item.imgCount > 1}">
												<img alt="" class="small-img" src="${x:goodsImageThumbnailPath(item.goodsSku, 1,'S') }">
												</c:if>
												</td>
												<td>${item.goodsCategory }</td>
												<td>${item.goodsName }</td>
												<td>${item.goodsUnit }</td>
												<td>${item.goodsCount }</td>
												<td>${item.goodsCost }</td>
												<td>${item.goodsTotal }</td>
												<td>
													${item.receivedCount}
												</td>
												<td>
													${item.unReceivedCount}
												</td>
												<td>
													${item.qualifiedCount}
												</td>
												<td>
													${item.unQualifiedCount}
												</td>
												<td>${item.supplierName }</td>
												<td>
													<f:formatDate value="${item.deliveryDate }"/>
												</td>
												<td>
													<c:choose>
														<c:when test="${item.status==1 }">
															待审核
														</c:when>
														<c:when test="${item.status==2 }">
															已审核
														</c:when>
														<c:when test="${item.status==3 }">
															正常关闭
														</c:when>
														<c:when test="${item.status==4 }">
															手工结案
														</c:when>
													</c:choose>
												</td>
												<td>
													<c:choose>
														<c:when test="${item.type==1 }">
															请购转入
														</c:when>
														<c:when test="${item.type==2 }">
															手工新增
														</c:when>
													</c:choose>
												</td>
												<td>${item.waybillNo }</td>
												<td>${item.freight }</td>
												<td>${item.payMethod }</td>
												<td>${item.purchaseRequestId }</td>
												<td>
													${item.buyUserName }
												</td>
												<td>
													${item.auditUserName}
												</td>
												<td>
													<f:formatDate value="${item.auditTime}"/>
												</td>
												<td>
													${item.orderItemClose?"是":"否"}
												</td>
												<td>
													${item.orderClose?"是":"否"}
												</td>
											</tr>
										</c:forEach>
										<tr style="color:red">
											<td colspan="10">汇总</td>
											<td>${totalBuy }</td>
											<td></td>
											<td>${totalCost }</td>
											<td>${totalReceived }</td>
											<td>${totalUnReceived }</td>
											<td>${totalQualified }</td>
											<td>${totalunQualified }</td>
											<td colspan="11"></td>
										</tr>
									</tbody>
									</table>
									<table class="table table-hover table-striped table-bordered" id="purchaseorderlog-table">
									<thead>
									<tr>
										<th>
											编号
										</th>
										<th>
											操作内容
										</th>
										<th>
											操作人
										</th>
										<th>
											操作时间
										</th>
									</tr>
									</thead>
									<tbody>
										<c:forEach items="${logList }" var="item" varStatus="idx">
											<tr>
												<td>${idx.index+1}</td>
												<td>
													${item.content }
												</td>
												<td>
													${item.operUser }
												</td>
												<td>
													<f:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.createdTime }"/>
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
	<tiles:putAttribute name="js-page">
		<script>
			$("#printbtn").click(function(){
				window.open("${pageContext.request.contextPath }/purchaseorder/print?orderId=${id}","_blank");
			});
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>