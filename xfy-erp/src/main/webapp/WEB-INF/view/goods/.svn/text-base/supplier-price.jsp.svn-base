<%--
供应商 采购报价
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>

<c:set scope="request" var="sysModule" value="purchase" />
<c:set scope="request" var="sysPage" value="supplier_price" />

<c:set var="pageTitle" value="采购报价" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">${pageTitle }</tiles:putAttribute>
	
		<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/select2/select2.css"/>
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
								<div class="row">
									<div class="col-md-3">
										<div class="btn-group">
										<x:power type="function" code="supplier_price_add">
											<a class=" btn green" href="${pageContext.request.contextPath }/supplier-price/form" data-target="#ajax" data-toggle="modal" data-backdrop="static">
												<spring:message code="g.label.add"/>  <i class="fa fa-plus"></i>
											</a></x:power>
										</div>
									</div>
									<form id="sp_search_form" class="col-md-9 form-horizontal" role="form" search="1">
										<div class="col-md-3">
											<div class="form-group form-group-sm">
												<label for="dict_code" class="col-md-3 control-label">SKU</label>
												<div class="col-md-9">
													<input type="text" class="form-control" name="goodsSku" value="${param.sku }" />
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group form-group-sm">
												<label for="dict_code" class="col-md-3 control-label">名称</label>
												<div class="col-md-9">
													<input type="text" class="form-control" name="goodsName" value="${param.goodsName }" />
												</div>
											</div>
										</div>
										<div class="col-md-5">
											<div class="form-group form-group-sm">
												<label for="dict_code" class="col-md-3 control-label">供应商</label>
												<div class="col-md-9">
													<input type="text" class="form-control c-supplier-picker" value="" data-placeholder="请选择..." />
													<input type="hidden" name="supplierId" id="supplierId" value="" />
												</div>
											</div>
										</div>
										<div class="col-md-5">
											<button type="button" id="sp_search_btn" class="btn blue"><spring:message code="g.label.search"/></button>
											<!-- <button type="button" id="clearbtn" class="btn blue">清空</button> -->
											<button type="button" id="sp_search_btn_reset" class="btn black"><spring:message code="g.label.reset"/></button>
										</div>
									</form>
								</div>
							</div>
							<table class="table table-striped table-bordered table-hover table-condensed" id="list_table">
							<thead>
							<tr>
								<th>SKU</th>
								<th>图片</th>
								<th>名称</th>
								<th>供应商名称</th>
								<th>单位</th>
								<th>采购量从</th>
								<th>采购量到 </th>
								<th>报价</th>
								<th>生效日期</th>
								<th>失效日期</th>
								<th>采购周期</th>
								<th>优先级</th>
								<th>维护人</th>
								<th>操作</th>
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
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/select2/select2.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>



<script>

xfy.SupplierPrice = {
	handleDataTable: function() {
		xfy.initDataTable();
		var $datatable = $("#list_table").dataTable({
			serverSide: true,
			ajax: {
				type: 'post',
				url: "${pageContext.request.contextPath }/supplier-price/page-json",
				data: function (d) {
					d.params = $('#sp_search_form').serializeObject();
					return JSON.stringify( d );
				},
				dataType: 'json',
				contentType:"application/json;charset=UTF-8"
			},
			columns: [
				{data: "goodsSku"},
				{className: "text-center", data: function(row) {
					var imgUrl = '${x:getConfig("images.base.url")}/g/' + row.goodsSku.substring(0,2) + '/' + row.goodsSku + '/g-1-S.jpg';
					return '<img class="small-img" src="' + imgUrl + '" />';
				}},
				{data: "goodsName"},
				{data: "supplierName"},
				{data: "goodsUnit"},
				{data: "countMin"},
				{data: "countMax"},
				{data: "price"},
				{data: "startDate"},
				{data: "endDate"},
				{data: "buyPeriod"},
				{data: "priority"},
				{data: "operUserName"},
				{className: "text-center", data: function ( row ) {
					var html = '';
					<x:power type="function" code="supplier_price_edit">
					html += xfy.html.editLinkAjaxModal.format('${pageContext.request.contextPath }/supplier-price/form?id=' + row.id, '<spring:message code="g.label.edit"/>');
					</x:power>
					<x:power type="function" code="supplier_price_delete">
					html += xfy.html.deleteLink.format('${pageContext.request.contextPath }/supplier-price/delete?id=' + row.id, '<spring:message code="g.label.delete"/>');
					</x:power>
					return html;
					}
				}
			],

		});
		
		return $datatable;
	},
	
	handleSupplierSelect: function(hiddenFieldId) {
		$(".c-supplier-picker").select2({
			minimumInputLength: 1,
			ajax: {
				delay: 1000,
				url: '${pageContext.request.contextPath }/supplier/page-json?status=1',
				data: function (params) {
					var data = {
						draw: 1,
						length: 10,
						start: 0,
						search: {value: params}
					};
					return JSON.stringify( data );
				},
				cache: true,
				params: {
					type: 'post',
					contentType:"application/json;charset=UTF-8"
				},
				results: function (result) {
					return {
						results: result.data
					};
				}
			},
			formatResult: function(item) {
				return item.companyName;
			},
			formatSelection: function(item) {
				$('#' + hiddenFieldId).val(item.id);
				return item.companyName;
			}
		});
	},
	

};

$(function(){
	xfy.SupplierPrice.handleSupplierSelect('supplierId');
	var $datatable = xfy.SupplierPrice.handleDataTable();
	
	$('#sp_search_btn').click(function() {
		if ($datatable) {
			$datatable.api().ajax.reload();
			return false;
		}
		
		return false;
	});
	
	$('#sp_search_btn_reset').click(function() {
		$('input[name=goodsSku]').val('');
		$('#supplierId').val('');
		$('.c-supplier-picker').select2('data', null);
	});
});
</script>

	</tiles:putAttribute>

</tiles:insertDefinition>