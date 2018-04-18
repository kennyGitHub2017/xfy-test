<%--
库存预警
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:set var="pageTitle" value="库存预警" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">${pageTitle }</tiles:putAttribute>
	
		<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>

	</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
			<div class="row">
				<div class="col-md-12">
					<div class="portlet light ">
						<div class="portlet-title">
							<div class="caption caption-md">
								<i class="icon-note theme-font"></i>
								<span class="caption-subject theme-font bold uppercase">${pageTitle }</span>
							</div>
						</div>
						<div class="portlet-body">
							<%@include file="../include/message.jsp" %>
							<div class="table-toolbar">
								<form id="search_form" class="col-md-12 form-horizontal" role="form">
									<div class="row">
										<div class="col-md-3">
											<div class="form-group">
												<label for="dict_code" class="col-md-4 control-label">SKU</label>
												<div class="col-md-8">
													<input type="text" class="form-control" name="goodsSku" value="${param.sku }" />
												</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label for="dict_code" class="col-md-4 control-label">仓库</label>
												<div class="col-md-8">
													<select class="form-control" name="storeId" id="store_id_select">
														<option value="">请选择...</option>
														<c:forEach var="item" items="${stores }">
															<option value="${item.id }">${item.name }</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label for="dict_code" class="col-md-4 control-label">货位</label>
												<div class="col-md-8">
													<select class="form-control" name="storeShelfId" id="store_shelf_id_select">
														<option value="">请选择...</option>
													</select>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-3">
											<div class="form-group">
												<label for="dict_code" class="col-md-4 control-label">产品大类</label>
												<div class="col-md-8">
													<select class="form-control" name="baseCategoryId" id="base_category_id">
														<option value="">请选择...</option>
														<c:forEach var="item" items="${baseCategories }">
															<option value="${item.id }">${item.name }</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label for="dict_code" class="col-md-4 control-label">产品中类</label>
												<div class="col-md-8">
													<select class="form-control" name="midCategoryId" id="mid_category_id">
														<option value="">请选择...</option>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label for="dict_code" class="col-md-4 control-label">产品小类</label>
												<div class="col-md-8">
													<select class="form-control" name="categoryId" id="category_id">
														<option value="">请选择...</option>
													</select>
												</div>
											</div>
										</div>
										
									</div>
									
									<div class="row">
										<div class="col-md-3">
											<div class="form-group">
												<label for="dict_code" class="col-md-4 control-label">产品名称</label>
												<div class="col-md-8">
												<input type="text" class="form-control" name="goodsName"/>
												</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label for="dict_code" class="col-md-4 control-label"></label>
												<div class="col-md-8">
													
												</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label for="dict_code" class="col-md-8 control-label"></label>
												<div class="col-md-4">
											<button type="button" id="search_form_btn" class="btn blue"><spring:message code="g.label.search"/></button>
												</div>
											</div>
										</div>
									
									</div>
									
									
								</form>
							</div>
							<table class="table table-striped table-bordered table-hover table-condensed" id="list_table">
							<thead>
							<tr>
								<th>SKU</th>
								<th>商品名</th>
								<th>商品单位</th>
								<th>产品类别</th>
								<th>30天销量</th>
								<th>15天销量</th>
								<th>7天销量</th>
								<th>实际库存</th>
								<th>已订购</th>
								<th>锁定量</th>
								<th>可用库存</th>
								<th>建议供应商</th>
								<th>建议采购价</th>
							</tr>
							</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
			
			<%@include file="/WEB-INF/view/include/modal-ajax.jsp" %>
	</tiles:putAttribute>

	<%-- 页面级别的 JS --%>
	<tiles:putAttribute name="js-page">
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>



<script>

$(function(){
	xfy.initDataTable();
	var $datatable = $("#list_table").dataTable({
		serverSide: true,
		ajax: {
			type: 'post',
			url: "${pageContext.request.contextPath }/inventory/page-json",
			data: function (d) {
				d.params = $('#search_form').serializeObject();
				return JSON.stringify( d );
			},
			dataType: 'json',
			contentType:"application/json;charset=UTF-8"
		},
		columns: [
			{data: "goodsSku"},
			{data: "goodsName"},
			{data: "goodsUnit"},
			{data: "count"},
			{data: "storeName"},
			{data: "storeShelfCode"},
			{data: "goodsBaseCategoryName"},
			{data: "goodsMidCategoryName"},
			{data: "goodsCategoryName"}
		]
	});
	
	// 搜索按钮
	$('#search_form_btn').click(function() {
		if ($datatable) {
			$datatable.api().ajax.reload();
			return false;
		}
		
		return false;
	});

	// 仓库级联显示
	$('#store_id_select').change(function() {
		var selectedValue = $(this).val();
		if (selectedValue == '') {
			$('#store_shelf_id_select').html('<option value="">请选择...</option>');
			return;
		}
		
		$.post('${pageContext.request.contextPath }/store/shelf-json', {id: selectedValue}, function(data) {
			xfy.fillSelect('#store_shelf_id_select', data, 'id', 'code');
		}, 'json');
	});
	
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
		}, 'json');
	});
});
</script>

	</tiles:putAttribute>

</tiles:insertDefinition>