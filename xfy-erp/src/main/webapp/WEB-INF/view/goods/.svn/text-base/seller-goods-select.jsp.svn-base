<%--
卖家商品选择页面
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:set scope="request" var="sysModule" value="product" />
<c:set scope="request" var="sysPage" value="seller_goods" />

<c:set var="pageTitle" value="卖家商品管理" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">${pageTitle }</tiles:putAttribute>

	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/css/jquery.fileupload.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/css/jquery.fileupload-ui.css"/>
	</tiles:putAttribute>

	<tiles:putAttribute name="page-content">
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light ">
					<div class="portlet-title">
						<div class="caption caption-md">
							<i class="icon-note theme-font"></i> <span class="caption-subject theme-font bold uppercase">${pageTitle }</span>
						</div>
					</div>
					<div class="portlet-body">
						<ul class="nav nav-tabs">
							<li>
								<a href="${pageContext.request.contextPath }/seller-goods">已收藏产品</a>
							</li>
							<li class="active">
								<a href="${pageContext.request.contextPath }/seller-goods/select">选择产品</a>
							</li>
						</ul>
						
						<%@include file="/WEB-INF/view/include/message.jsp"%>
						
						<form:form modelAttribute="goodsParam" cssClass="form-horizontal" id="goods_search" search="1">
							<c:if test="${seller.selfFlag == 0 }">
							<input type="hidden" name="openFlag" value="1" />
							</c:if>
							<div class="row">
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">SKU编号</label>
										<div class="col-md-8">
											<input type="text" class="form-control" name="goodsSku" value="${goodsAttr.goodsSku}">
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">货品名称</label>
										<div class="col-md-8">
											<input type="text" class="form-control" name="name" value="${goodsAttr.name}">
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">货品状态</label>
										<div class="col-md-8">
											<select class="form-control" name="status">
												<option value="">请选择...</option>
												<c:forEach var="item" items="${goodsStatus }">
													<option value="${item.code }">${item.name }</option>
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
										
												<form:select path="baseCategoryId" cssClass="form-control" id="base_category_id">
													<option value="">请选择...</option>
													<form:options items="${baseCategories }" itemValue="id" itemLabel="name" />
												</form:select> 
												
												
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">产品中类</label>
										<div class="col-md-8">
												<select class="form-control" name="midCategoryId" id="mid_category_id">
													<option value="">请选择...</option>
												</select>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">产品小类</label>
										<div class="col-md-8">
											<select class="form-control" name="categoryId" id="category_id">
													<option value="">请选择...</option>
												</select>
										</div>
									</div>
								</div>
					
							</div>
							<div class="row">
								<div class="col-md-8"></div>
								<div class="col-md-4">
									<div class="form-group form-group-sm">
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
						</form:form>
						
						<div class="table-toolbar">
							<div class="row">
								<div class="col-md-6">
									<div class="btn-group">
										<a class=" btn green" href="javascript:;" id="seller_add_goods_btn"> 收藏  <i class="fa fa-plus"></i></a>
									</div>
								</div>
							</div>
						</div>
						
						<form id="seller_goods_select_form" action="${pageContext.request.contextPath }/seller-goods/add">
						<table class="table table-striped table-bordered table-hover table-condensed" id="goods_table">
							<thead>
								<tr>
									<th><!-- <input type="checkbox" class="c-reserve-select" data-selector="input[name=goodsId]" /> --></th>
									<th>图片</th>
									<th>SKU</th>
									<th>中文名称</th>
									<th>开发时间</th>
									<th>建议售价</th>
									<th>7天销量</th>
									<th>15天销量</th>
									<th>30天销量</th>
									<th>产品小类</th>
									<th>颜色</th>
									<th>成本</th>
									<th>重量</th>
									<th>是否含电池</th>
									<th>是否侵权</th>
									<th>包装材料</th>
									<th>包装规格</th>
									<th>单位</th>
									<th>状态</th>
								</tr>
							</thead>
						</table>
						</form>
					</div>
				</div>
			</div>
		</div>

		<%@include file="/WEB-INF/view/include/modal-ajax.jsp"%>
	</tiles:putAttribute>

	<%-- 页面级别的 JS --%>
	<tiles:putAttribute name="js-page">
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/select2/select2.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function() {
				initFun();
				xfy.GoodsEdit.handleStoreSelect('${goods.storeShelfId}');
				xfy.GoodsEdit.handleCategorySelect('${goods.midCategoryId}', '${goods.categoryId}');
				$('#store_id_select').change();
				$('#base_category_id').change();
				
				$('#seller_add_goods_btn').click(function() {
					$('#seller_goods_select_form').submit();
				});
				
				$('#seller_goods_select_form').submit(function() {
					var checkedNumber = $(this).find("input[name=goodsId]:checked").length;
					if (checkedNumber == 0) {
						alert('请选择要收藏的商品');
						return false;
					}
					return true;
				});
			});
			
			xfy.GoodsEdit = {
					
					handleStoreSelect: function(storeShelfId) {
						// 仓库级联显示
						$('#store_id_select').change(function() {
							var selectedValue = $(this).val();
							if (selectedValue == '') {
								$('#store_shelf_id_select').html('<option value="">请选择...</option>');
								return;
							}
							
							$.post('${pageContext.request.contextPath }/store/shelf-json', {id: selectedValue}, function(data) {
								xfy.fillSelect('#store_shelf_id_select', data, 'id', 'code');
								if (storeShelfId != '') {
									$('#store_shelf_id_select').val(storeShelfId);
								}
							}, 'json');
						});
					},
					
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
				};
			

			
			$("#searchbtn").click(function() {
				$('#goods_table').dataTable().fnDestroy();
				initFun();
			});

			function initFun() {
				xfy.initDataTable();
				$("#goods_table").dataTable({
					serverSide : true,
					ajax : {
						url : "${pageContext.request.contextPath }/goods/page-json",
						type : 'post',
						contentType : "application/json;charset=UTF-8",
						data : function(d) {
							d.params = $("#goods_search").serializeObject();
							return JSON.stringify(d);
						}
					},
					columns : [
							{
								data: function(row) {
									return '<input type="checkbox" name="goodsId" value="' + row.id + '" />';
								}
							},
							{
								data : "imgUrl",
								render : function(data, type, full, meta) {
									var img = '<img  style="width:60px;height:60px;" src="${pageContext.request.contextPath }/images/'+ data +' " >';
									return img;
								}
							},
							{data : "goodsSku"},
							{data : "name"},
							{data : "developTime"},
							{data : "price"},
							{data:"sales7"},
							{data:"sales15"},
							{data:"sales30"},
							{data:"categoryName"},
							{data:"color"},
							{data:"cost"},
							{data:"weight"},
							{data:"isBattery",
								render:function(data){
									if(data == '1'){
										return "是";
									}else if(data == '0'){
										return "否";
									}else{
										return "";
									}
								}},
							{data:"isCopyright",
									render:function(data){
										if(data == '1'){
											return "是";
										}else if(data == '0'){
											return "否";
										}else{
											return "";
										}
									}},
							{data:"packingCapacity"},
							{data:"packingMaterialId"},
							
							{data : "unit"},
							{data : "statusDesc"}
					]
				});
			};
			
			
		</script>

	</tiles:putAttribute>

</tiles:insertDefinition>