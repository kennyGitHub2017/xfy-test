<%--
组合产品管理
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>

<c:set scope="request" var="sysModule" value="product" />
<c:set scope="request" var="sysPage" value="io-journal2"/>

<c:set var="pageTitle" value="出入库流水" />
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
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-cogs font-green-sharp"></i>
								<span class="caption-subject font-green-sharp bold uppercase">出入库流水明细</span>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"></a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-toolbar">
							</div>
						
							<%@include file="../include/message.jsp" %>
							
							<form id="search_form" method="post" class="form-horizontal" search="1">
							
								<div class="row">
										<div class="col-md-3">
											<div class="form-group form-group-sm">
											<label class="control-label col-md-4">SKU编号</label>
											<div class="col-md-8">
											<input type="text" class=" col-md-3 form-control"  name="goodsSku" />
											</div>
											</div>
										</div>
										
										<div class="col-md-3">
											<div class="form-group form-group-sm">
											<label class="control-label col-md-4">商品名称</label>
											<div class="col-md-8">
											<input type="text" class=" col-md-3 form-control" id="waybillNo"  name="goodsName" />
											</div>
											</div>
										</div>
										
										<div class="col-md-6">
											<div class="form-group">
												<label  class="col-md-2 control-label">时间</label>
												<div class="col-md-5">
														<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
															<input name="dateTimeFrom" class="form-control input-sm" /> <span class="input-group-btn">
																<button class="btn default btn-sm" type="button">
																	<i class="fa fa-calendar"></i>
																</button>
															</span>
														</div>
												</div>
												<div class="col-md-5">
													<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
															<input name="dateTimeTo" class="form-control input-sm" /> <span class="input-group-btn">
																<button class="btn default btn-sm" type="button">
																	<i class="fa fa-calendar"></i>
																</button>
															</span>
													</div>
												</div>
											</div>
										</div>
					
								</div>
								
								<div class="row">
										
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">SKU状态</label>
											<div class="col-md-8">
												<select class="form-control" name="goodsStatus">
													<option value="">请选择</option>
													<c:forEach var="item" items="${goodsStatus}">
													<option value="${item.name}">${item.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
										
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">采购人</label>
											<div class="col-md-8">
												<select class="form-control" name="buyUser">
													<option value="">请选择</option>
													<c:forEach var="item" items="${buyUser}">
														<option value="${item.name}">${item.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">出货单</label>
											<div class="col-md-8">
												<select class="form-control" name="typeDetail">
													<option value="">请选择</option>
													<c:forEach var="item" items="${typeAttr}">
															<c:if test="${item.code < 50}">
																<option value="${item.code}">${item.name}</option>
															</c:if>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">入库单</label>
											<div class="col-md-8">
												<select class="form-control" name="typeDetail2">
													<option value="">请选择</option>
													<c:forEach var="item" items="${typeAttr}">
															<c:if test="${item.code > 50}">
																<option value="${item.code}">${item.name}</option>
															</c:if>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									
								</div>
								
						<div class="row">
								<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">仓库</label>
											<div class="col-md-8">
											
												<select class="form-control" name="storeId" id="store_id">
													<option value="">请选择</option>
													<c:forEach var="item" items="${storeList}">
													<option value="${item.id}">${item.name}</option>
													</c:forEach>
												</select>
												
											</div>
										</div>
									</div>
									
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">仓位</label>
											<div class="col-md-8">
												<select class="form-control" name="storeShelfId" id="store_shelf_id_select">
													<option value="">请选择</option>
												</select>
											</div>
										</div>
									</div>
									
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">开发人</label>
											<div class="col-md-8">
												<select class="form-control" name="developUser">
													<option value="">请选择</option>
													<c:forEach var="item" items="${devUser}">
														<option value="${item.name}">${item.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									
									<div class="col-md-3">
										<div class="form-group">
										<div class="col-md-12">
											<button type="button" id="searchbtn" class="btn green">
											<spring:message code="g.label.search" />
											</button>
											<button type="button" id="clearbtn" class="btn blue">清空</button>
											<button type="button" id="exportIoOrder" onclick="exportItem();" class="btn green">导出订单</button>
											</div>
										</div>
									</div>
									
							
									
								</div>
							</form>
						
							<table id="list_table" class="table table-hover table-striped table-bordered table-condensed">
								<thead>
								<tr>
									<th>SKU</th>
									<th>产品状态</th>
									<th>商品名称</th>
									<th>开发人</th>
									<th>开发时间</th>
									<th>采购人</th>
									<th>日期</th>
									<th>单据类型</th>
									<th>单据名称</th>
									<th>单据号</th>
									<th>来源单号</th>
									<th>平台账号</th>
									<th>仓库</th>
									<th>仓位</th>
									<th>收发数量</th>
									<th>收发成本</th>
									<th>出库金额</th>
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
	initFun();
	shelfSelect();
});

function shelfSelect(){
	
	$('#store_id').change(function() {
		var selectedValue = $(this).val();
		if (selectedValue == '') {
			$('#store_id').html('<option value="">请选择...</option>');
			$('#store_shelf_id_select').html('<option value="">请选择...</option>');
			return;
		}
		$.post('${pageContext.request.contextPath }/store/shelf-json', {id: selectedValue}, function(data) {
			xfy.fillSelect('#store_shelf_id_select', data, 'id', 'code');
		}, 'json');
	});
}

function initFun(){
	xfy.initDataTable();
	$("#list_table").dataTable({
		/* searching: true, */
		serverSide: true,
		
		ajax : {
			url : "${pageContext.request.contextPath }/io-order/ioItem-json",
			type : 'post',
			contentType : "application/json;charset=UTF-8",
			data : function(d) {
				var jsondata = $("#search_form").serializeObject();  //转化为json
				d.params = jsondata;
				return JSON.stringify(d);
			}
		},
		
		columns: [
				{data: "goodsSku"},
				{data: "goodsStatus"},
				{data: "goodsName"},
				{data: "developUser"},
				{data: "developTime"},
				{data: "buyUser"},
				{data: "lastUpdatedTime", 
					render: function ( data, type, full, meta ) {
						return data == null ? "" : new Date(data).format("yyyy-MM-dd");
					}
				},
				{data: "typeName"},
				{data: "typeDetailName"},
				{data: "orderNo"},
				{data:"buyOrderNo"},
				{data:"accountName"},
				{data: "storeName"},
				{data: "shelfName"},
				{data: "qualifiedCount"},
				{data: "goodsCost"},
				{data:function(){return '';}},
		],

	});
}

$('#searchbtn').click(function(){
	$('#list_table').dataTable().fnDestroy();
	initFun();
});

function exportItem(){
	
	var path = '${pageContext.request.contextPath}/io-order/exportIoOrderItem';
	$.ajax({
		url:path,
		type:'post',
		data: $("#search_form").serializeObject(),
		async : false,
		success:function(data){
			if(data == 'error'){
				alert("导出数量不能大于3W条,根据时间分批导出");
			}else{
				$('#search_form').attr("action", path).submit();
			}
		}
	});
}

</script>

	</tiles:putAttribute>
</tiles:insertDefinition>