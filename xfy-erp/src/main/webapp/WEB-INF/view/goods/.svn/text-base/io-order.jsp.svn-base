
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>

<c:set scope="request" var="sysModule" value="product" />
<c:set scope="request" var="sysPage" value="io_order_${param.type }" />

<c:set var="ioOrderTypeDesc" value="${param.type == 0 ? '入库' : '出库' }"></c:set>
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">${ioOrderTypeDesc }单</tiles:putAttribute>

	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/admin/pages/css/todo.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css" />
	</tiles:putAttribute>

	<tiles:putAttribute name="page-content">
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-cogs font-green-sharp"></i> <span class="caption-subject font-green-sharp bold uppercase">${ioOrderTypeDesc }单</span>
						</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
						</div>
					</div>
					<div class="portlet-body">
						<ul class="nav nav-tabs">
							<li class="${param.status != 1? 'active' : '' }"><a href="${pageContext.request.contextPath }/io-order/?type=${param.type}&status=0">未审核</a></li>
							<li class="${param.status == 1? 'active' : '' }"><a href="${pageContext.request.contextPath }/io-order?type=${param.type}&status=1">已审核</a></li>
						</ul>
					
						<form id="search_order" class="form-horizontal" search="1">
							<input type="hidden" name="type" value="${param.type }" />
							<input type="hidden" name="auditStatus" value="${param.status == 1 ? 1 : 0 }" />
							<!-- search start -->
							
							<div class="row">
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">${ioOrderTypeDesc }单号</label>
										<div class="col-md-8">
											<input class="form-control" name="orderNo" />
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">产品SKU</label>
										<div class="col-md-8">
											<input class="form-control" name="goodsSku" />
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">产品名称</label>
										<div class="col-md-8">
											<input class="form-control" name="goodsName" />
										</div>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">${ioOrderTypeDesc }类型</label>
										<div class="col-md-8">
											<select class="form-control" name="typeDetail">
												<option value="">选择</option>
												<c:choose>
													<c:when test="${param.type == '0'}">
														<c:forEach var="item" items="${typeAttr}">
															<c:if test="${item.code > 50}">
																<option value="${item.code}">${item.name}</option>
															</c:if>
														</c:forEach>
													</c:when>
													<c:when test="${param.type == '1'}">
														<c:forEach var="item" items="${typeAttr}">
															<c:if test="${item.code < 50}">
																<option value="${item.code}">${item.name}</option>
															</c:if>
														</c:forEach>
													</c:when>
												</c:choose>
											</select>
										</div>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">仓库</label>
										<div class="col-md-8">
											<select class="form-control" name="storeId">
												<option value="">请选择</option>
												<c:forEach var="ss" items="${store}">
													<option value="${ss.id}">${ss.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">制单人</label>
										<div class="col-md-8">
											<select class="form-control" name="createdUserId">
												<option value="">选择</option>
												<c:forEach items="${user}" var="u">
													<option value="${u.userId}">${u.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
							</div>
							
							
							<div class="row">
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">产品大类</label>
										<div class="col-md-8">
											<select class="form-control" id="base_category_id" name="firstCategory">
												<option value="">请选择</option>
												<c:forEach var="item" items="${baseCategories }">
												<option value="${item.id}">${item.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="col-md-4 form-group-sm">
									<div class="form-group">
										<label class="control-label col-md-4">产品中类</label>
										<div class="col-md-8">
											<select class="form-control" name="secondCategory" id="mid_category_id">
												<option value="">请选择...</option>
											</select>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">产品小类</label>
										<div class="col-md-8">
										 <select class="form-control" name="thirdCategory" id="category_id">
										<option value="">请选择...</option>
										</select>
										</div>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">开始时间</label>
										<div class="col-md-8">
											<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
												<input name="inOutDateFrom" class="form-control" /> <span class="input-group-btn">
													<button class="btn default btn-sm" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group  form-group-sm">
										<label class="control-label col-md-4">结束时间</label>
										<div class="col-md-8">
											<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
												<input name="inOutDateTo" class="form-control" /> <span class="input-group-btn">
													<button class="btn default btn-sm" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
											</div>
										</div>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">来源单号</label>
										<div class="col-md-8">
											<input class="form-control" name="purchaseOrderId" />
										</div>
									</div>
								</div>
								
							</div>
							
							<div class="row">
								<div class="col-md-8"></div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-4"></label>
										<div class="col-md-8">
											<button type="button" id="searchbtn" class="btn green">
												<spring:message code="g.label.search" />
											</button>
											<button type="button" id="clearbtn" class="btn blue">清空</button>
										</div>
									</div>
								</div>
							</div>
								
						</form>
						
						<%@include file="/WEB-INF/view/include/message.jsp" %>
						<div class="table-toolbar">
							<div class="row">
								<div class="col-md-6">
									<div class="btn-group">
										<c:if test="${param.type == 0 }">
										<x:power type="function" code="io_order_0_add">
											<a class=" btn green" href="${pageContext.request.contextPath }/io-order/edit?type=${param.type}" data-backdrop="static"> <spring:message code="g.label.add" /> <i class="fa fa-plus"></i></a>
										</x:power>
										</c:if>
										<c:if test="${param.type == 1 }">
										<x:power type="function" code="io_order_1_add">
											<a class=" btn green" href="${pageContext.request.contextPath }/io-order/edit?type=${param.type}" data-backdrop="static"> <spring:message code="g.label.add" /> <i class="fa fa-plus"></i></a>
										</x:power>
										</c:if>
									</div>
									
						<a class=" btn green" href="${pageContext.request.contextPath }/io-order/import-page?type=${param.type}" data-backdrop="static">
						导入<i class="fa fa-plus"></i></a>  

								</div>
							</div>
						</div>
						<table class="table table-hover table-striped table-bordered table-condensed" id="IoOrder_table">
							<thead>
								<tr>
									<th>单号</th>
									<c:if test="${param.status==1}">
										<th>流水号</th>	
									</c:if>
									<th>类型</th>
									<th>来源单号</th>
									<th>仓库</th>
									<th>创建时间</th>
									<th>制单人</th>
									<th>审核状态</th>
									<c:if test="${param.status == 1 }">
									<th>审核时间</th>
									<th>审核人</th>
									</c:if>
									<th>操作</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>


	<%-- 页面级别的 JS --%>
	<tiles:putAttribute name="js-page">
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>

		<script>

$(function(){
	initFun();
	xfy.GoodsEdit.handleCategorySelect('${goods.midCategoryId}', '${goods.categoryId}');
	$('#base_category_id').change();
});

xfy.GoodsEdit = {
		
		handleCategorySelect: function(midCatId, catId) {
			
			// 大类级联显示中类
			$('#base_category_id').change(function() {
				var selectedValue = $(this).val();
				if (selectedValue == '') {
					$('#mid_category_id').html('<option value="">请选择...</option>');
					$('#category_id').html('<option value="">请选择...</option>');
					return;
				}
				
				$.post('${pageContext.request.contextPath }/goodscategory/list-json', {id: selectedValue}, function(data) {
					xfy.fillSelect('#mid_category_id', data, 'id', 'name');
					$('#category_id').html('<option value="">请选择...</option>');
					if (midCatId != '') {
						$('#mid_category_id').val(midCatId);
						$('#mid_category_id').change();
					}
				}, 'json');
			});
			
			// 中类级联显示小类
			$('#mid_category_id').change(function() {
				var selectedValue = $(this).val();
				if (selectedValue == '') {
					$('#category_id').html('<option value="">请选择...</option>');
					return;
				}
				
				$.post('${pageContext.request.contextPath }/goodscategory/list-json', {id: selectedValue}, function(data) {
					xfy.fillSelect('#category_id', data, 'id', 'name');
					if (catId != '') {
						$('#category_id').val(catId);
					}
				}, 'json');
			});
		}
	
}

$('.date-picker').datepicker({
	orientation: "right",
	autoclose: true
});

$("#searchbtn").click(function(){
	$('#IoOrder_table').dataTable().fnDestroy();
	initFun();
});


function initFun(){
	xfy.initDataTable();
	$("#IoOrder_table").dataTable({
		serverSide: true,
		ordering:true,
		ajax: {
			url: "${pageContext.request.contextPath }/io-order/page-json",
					
			data: function (d) {
				var data = $("#search_order").serializeObject();
				d.params = data;
				return JSON.stringify( d );
			},
			type: 'post',
			contentType:"application/json;charset=UTF-8"
		},
		columns: [
			{data: "orderNo","sortable":false},
			 <c:if test="${param.status==1}">
	     		{data: "serialNumber"},
	     	 </c:if>
			{data: "typeName","sortable":false},
			{data: "buyOrderNo","sortable":false},
			{data: "storeName","sortable":false},
			{data: "lastUpdatedTime","sortable":false},
			{data: "createdUserName","sortable":false},
			{data: function(row) { 
					return row.auditStatus == 1 ? '已审核' : '未审核'; 
				},"sortable":false 
			},
			<c:if test="${param.status == 1 }">
			{data: "auditTime","sortable":false},
			{data: "auditUserName","sortable":false},
			</c:if>
			{
				data: function(row) {
					var html = '';
					<c:if test="${param.type == 0 }">
					<x:power type="function" code="io_order_0_edit">
					html += xfy.html.editLink.format('${pageContext.request.contextPath }/io-order/edit?id=' + row.id, '详情');
					</x:power>
					</c:if>
					<c:if test="${param.type == 1 }">
					<x:power type="function" code="io_order_1_edit">
					html += xfy.html.editLink.format('${pageContext.request.contextPath }/io-order/edit?id=' + row.id, '详情');
					</x:power>
					</c:if>
					if (row.auditStatus == 0) {
						<c:if test="${param.type == 0 }">
						<x:power type="function" code="io_order_0_delete">
						html += xfy.html.deleteLink.format('${pageContext.request.contextPath }/io-order/delete?type=${param.type}&id=' + row.id, '<spring:message code="g.label.delete"/>');
						</x:power>
						</c:if>
						<c:if test="${param.type == 1 }">
						<x:power type="function" code="io_order_1_delete">
						html += xfy.html.deleteLink.format('${pageContext.request.contextPath }/io-order/delete?type=${param.type}&id=' + row.id, '<spring:message code="g.label.delete"/>');
						</x:power>
						</c:if>
					} 
					return html;
				},"sortable":false
			}
	

		],

	});
}

</script>


	</tiles:putAttribute>


</tiles:insertDefinition>