<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">采购入库单详情</tiles:putAttribute>

	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" />
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css" />

	</tiles:putAttribute>

	<tiles:putAttribute name="page-content">
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-cogs font-green-sharp"></i> <span
								class="caption-subject font-green-sharp bold uppercase">采购入库单详情</span>
						</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
						</div>
					</div>
					<div class="portlet-body">
						<div class="">
							<table class="table table-hover table-striped table-bordered"
								id="allocateorder-table">
								<thead>
									<tr>
										<th>入库日期</th>
										<th>入库单号</th>
										<th>SKU</th>
										<th>名称</th>
										<th>入库重量</th>
										<th>采购数量</th>
										<th>检验方式</th>
										<th>检验数量</th>
										<th>合格数量</th>
										<th>仓库</th>
										<th>货位</th>
										<th>不合格数量</th>
										<th>不合格货位</th>
										<th>合格率</th>
										<th>采购单号</th>
										<th>制单人</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="totalTest" value="0"></c:set>
									<c:set var="totalBuy" value="0"></c:set>
									<c:set var="totalQualified" value="0"></c:set>
									<c:set var="totalunQualified" value="0"></c:set>
									<c:forEach items="${detailList }" var="item">
										<c:set var="totalTest" value="${totalTest+ item.testCount}"></c:set>
										<c:set var="totalBuy" value="${totalBuy+ item.buyCount}"></c:set>
										<c:set var="totalQualified"
											value="${totalQualified+ item.qualifiedCount}"></c:set>
										<c:set var="totalunQualified"
											value="${totalunQualified+ item.unqualifiedCount}"></c:set>
										<tr>
											<td><f:formatDate value="${item.orderCreated }" /></td>
											<td>${item.orderNo }</td>
											<td>${item.goodsSku } (${item.oldSku })</td>
											<td>${item.goodsName }</td>
											<td>${item.goodsWeight }</td>
											<td>${item.buyCount }</td>
											<td>${item.testType ==0?"全检":"抽检" }</td>
											<td>${item.testCount }</td>
											<td>${item.qualifiedCount }</td>
											<td>${item.storeName }</td>
											<td>${item.shelfName }</td>
											<td>${item.unqualifiedCount }</td>
											<td>${item.unqualifiedShelf }</td>
											<td>${item.hgl }</td>
											<td>${item.buyOrderNo }</td>
											<td>${item.createUserName }</td>
										</tr>
									</c:forEach>
									<tr style="color: red">
										<td colspan="4">汇总</td>
										<td>${totalBuy }</td>
										<td></td>
										<td>${totalTest }</td>
										<td>${totalQualified }</td>
										<td colspan="2"></td>
										<td>${totalunQualified }</td>
										<td colspan="4"></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>