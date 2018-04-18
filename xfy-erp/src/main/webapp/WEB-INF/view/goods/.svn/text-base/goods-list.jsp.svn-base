
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>

<c:set scope="request" var="sysModule" value="product" />
<c:set scope="request" var="sysPage" value="goods" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">商品列表</tiles:putAttribute>

	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/blueimp-gallery/blueimp-gallery.min.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/css/jquery.fileupload.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/css/jquery.fileupload-ui.css"/>
	</tiles:putAttribute>

	<tiles:putAttribute name="page-content">
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light ">
					<div class="portlet-title">
						<div class="caption caption-md">
							<i class="icon-note theme-font"></i> <span class="caption-subject theme-font bold uppercase">商品列表</span>
						</div>
					</div>
					<div class="portlet-body">
						<%@include file="/WEB-INF/view/include/message.jsp"%>
						
						<form:form modelAttribute="goods" cssClass="form-horizontal" id="goods_search" search="1">
							<div class="row">
								
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-2">SKU编号</label>
										<div class="col-md-10">
											<input type="text" class="form-control" name="goodsSku" value="${goodsAttr.goodsSku}">
										</div>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-2">货品名称</label>
										<div class="col-md-10">
											<input type="text" class="form-control" name="name" value="${goodsAttr.name}">
										</div>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-2">货品状态</label>
										<div class="col-md-4">
											<select class="form-control" name="status">
												<option value="">请选择...</option>
												<c:forEach var="item" items="${goodsStatus }">
													<option value="${item.code }">${item.name }</option>
												</c:forEach>
											</select>
										</div>
										
										<label class="control-label col-md-2">货品所属</label>
										<div class="col-md-4">
												<form:select path="belongSelf" cssClass="form-control">
													<form:option value="">请选择...</form:option>
													<form:option value="1">公司</form:option>
													<form:option value="0">第三方</form:option>
												</form:select>
										</div>
									</div>
								</div>
							</div>
							
							
							
							<div class="row">
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-2">仓库</label>
										<div class="col-md-10">
											<form:select path="storeId" cssClass="form-control" id="store_id_select">
													<option value="">请选择...</option>
													<form:options items="${stores }" itemValue="id" itemLabel="name" />
											</form:select>
										</div>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-2">仓位</label>
										<div class="col-md-10">
											<select class="form-control" name="storeShelfId" id="store_shelf_id_select">
													<option value="">请选择...</option>
											</select>
										</div>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-2">开发员</label>
										<div class="col-md-10">
											<select class="form-control"  name="developUser">
											<option value="">请选择</option>
											<c:forEach var="item" items="${devUser}">
											<option value="${item.userId}">${item.name}</option>
											</c:forEach>
											</select>
										</div>
									</div>
								</div>
								
							</div>
							
							<div class="row">
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-2">产品大类</label>
										<div class="col-md-10">
										
												<form:select path="baseCategoryId" cssClass="form-control" id="base_category_id">
													<option value="">请选择...</option>
													<form:options items="${baseCategories }" itemValue="id" itemLabel="name" />
												</form:select> 
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-2">产品中类</label>
										<div class="col-md-10">
												<select class="form-control" name="midCategoryId" id="mid_category_id">
													<option value="">请选择...</option>
												</select>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-2">产品小类</label>
										<div class="col-md-10">
											<select class="form-control" name="categoryId" id="category_id">
													<option value="">请选择...</option>
											</select>
										</div>
									</div>
								</div>
							</div>
		
							
											
							<div class="row">
								
								<div class="col-md-4">
									<div class="form-group">
									<label class="control-label col-md-2">采购人</label>
									<div class="col-md-10">
											
											<select class="form-control"  name="buyUser" id="">
											<option value="">请选择</option>
											<c:forEach var="item" items="${buyUser}">
											<option value="${item.userId}">${item.name}</option>
											</c:forEach>
											</select>
									</div>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group">
									<label class="control-label col-md-2">包装人</label>
									<div class="col-md-10">
										<select class="form-control"  name="packUser" id="">
										<option value="">请选择</option>
										<c:forEach var="item" items="${packUser}">
										<option value="${item.userId}">${item.name}</option>
										</c:forEach>
										</select>
									</div>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-2">拣货人</label>
										<div class="col-md-10">
											<select class="form-control"  name="pickUser" id="">
											<option value="">请选择</option>
											<c:forEach var="item" items="${packUser}">
											<option value="${item.userId}">${item.name}</option>
											</c:forEach>
											</select>
										</div>
									</div>
								</div>
							
							</div>
							
							
						<div class="row">
							<div class="col-md-4">
								<div class="form-group form-group-sm">
									<label class="control-label col-md-2">供应商</label>
									<div class="col-md-10">
										<input type="text" class="form-control" name="supplier" value="">
									</div>
								</div>
							</div>
							
							<div class="col-md-4">
									<div class="form-group">
										<label  class="col-md-2 control-label">开发时间</label>
										<div class="col-md-5">
												<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
													<input name="createTimeFrom" class="form-control input-sm" /> <span class="input-group-btn">
														<button class="btn default btn-sm" type="button">
															<i class="fa fa-calendar"></i>
														</button>
													</span>
												</div>
										</div>
										<div class="col-md-5">
											<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
													<input name="createTimeTo" class="form-control input-sm" /> <span class="input-group-btn">
														<button class="btn default btn-sm" type="button">
															<i class="fa fa-calendar"></i>
														</button>
													</span>
											</div>
										</div>
									</div>
								</div>
								
							
							<div class="col-md-4">
								<div class="form-group">
								<label class="control-label col-md-2">库存数量</label>
									<div class="col-md-4">
									<select class="form-control"  name="count" id="">
									<option value="">请选择</option>
									<option value="1">大于0</option>
									<option value="0">等于0</option>
									</select>
									</div>
									
									<div class="col-md-6">
										<button type="button" id="searchbtn" class="btn green ">
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
								<div class="col-md-3">
									<x:power type="function" code="goods_add">
										<%-- <a class=" btn green btn-sm" href="${pageContext.request.contextPath }/goods/edit"> <spring:message code="g.label.add" /> <i class="fa fa-plus"></i></a> --%>
									</x:power>
									
									<x:power type="function" code="goods_open">
									<div class="btn-group">
										<button class="btn green dropdown-toggle btn-sm" type="button" data-toggle="dropdown" data-hover="dropdown" data-delay="500" data-close-others="true" aria-expanded="true">
											是否开放 <i class="fa fa-angle-down"></i>
										</button>
										<ul class="dropdown-menu" role="menu">
											<li><a herf="javascript:;" class="open_flag_selector" data="1">开放给卖家</a></li>
											<li><a herf="javascript:;" class="open_flag_selector" data="0">不开放给卖家</a></li>
										</ul>
									</div>
									</x:power>
								</div>
								
								<div class="col-md-5 text-right">
								<form action="${pageContext.request.contextPath }/goods/import" method="post" enctype="multipart/form-data">
									
									<a class=" btn blue btn-sm" href="${pageContext.request.contextPath }/resources/template/goods-import.xls">
										<i class="fa fa-download"></i> 
										<span>下载导入模板</span>
									</a>
									<span class="btn green btn-sm fileinput-button">
										<span>选择商品资料</span>
										<i class="fa fa-plus"></i>
										<input type="file" name="fileName" id="fileId"/>
									</span>
									<x:power type="function" code="goods_import">
									<button type="submit" class="btn blue btn-sm start">
									<i class="fa fa-upload"></i> 
									<span>开始导入</span>
									</button></x:power>
									</form>
								</div>
								<div class="col-md-2">
									<x:power type="function" code="goods_note_import">
										<a class=" btn blue btn-sm" href="${pageContext.request.contextPath }/goods/import-goods-desc" target="_blank"> 商品描述信息导入</a>
									</x:power>
								</div>
								<div class="col-md-2">
								<x:power type="function" code="goods_export">
								<button type="button" id="exportGoodsInfo" onclick="exportGoodsInfo();" class="btn green btn-sm">导出产品资料</button>
								</x:power>
								</div>
							</div>
						</div>
						
						<table class="table table-striped table-bordered table-hover table-condensed multi-header table-wrap-td" id="goods_table">
							<thead>
								<tr>
									<th rowspan="2"><input type="checkbox" class="c-all-and-no-select" data-selector="input[type=checkbox][name=id]" /></th>
									<th rowspan="2">图片</th>
									<th rowspan="2">SKU</th>
									<th rowspan="2">中文名称</th>
									<th rowspan="2">产品小类</th>
									<th rowspan="2">规格</th>
									<th rowspan="2">颜色</th>
									<th rowspan="2">尺码</th>
									<th rowspan="2">开发时间</th>
									<!-- <th rowspan="2">建议售价</th> -->
									<th rowspan="2">sku单价</th>
									<th rowspan="2">成本价</th>
									<th rowspan="2">导入价</th>
									<th rowspan="2">重量</th>
									<th colspan="3">销量</th>
									<th colspan="4">库存</th>
								<!-- 	<th rowspan="2">卖家成本</th> -->
									<th rowspan="2">采购人</th>
									<!-- <th rowspan="2">拣货人</th>
									<th rowspan="2">配货人</th> -->
									<th rowspan="2">开发员</th>
									<th colspan="2">包装 </th>
									<th rowspan="2">销售分析</th>
									<th rowspan="2">货位</th>
									<th rowspan="2">状态</th>
									<th rowspan="2">平台信息</th>
									<th rowspan="2">货品所属</th>
									<th rowspan="2">操作</th>
								</tr>
								<tr>
									<th>7天</th>
									<th>15天</th>
									<th>30天</th>
									
									<th>实际</th>
									<th>锁定</th>
									<th>在途</th>
									<th>销占数</th>
									
									<th>材料</th>
									<th>规格</th>
								</tr>
							</thead>
						</table>
						
						<div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls">
							<div class="slides"></div>
							<h3 class="title"></h3>
							<a class="prev">‹</a>
							<a class="next">›</a>
							<a class="close">×</a>
							<a class="play-pause"></a>
							<ol class="indicator"></ol>
						</div>
					</div>
				</div>
			</div>
		</div>

<!-- 添加对话话框 -->

<div class="modal fade bs-modal-lg text-center" id="listingModal" role="basic" aria-hidden="true">
	<div class="modal-dialog modal-lg" style="display: inline-block; width: auto;">
		<div class="modal-content">
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
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/blueimp-gallery/jquery.blueimp-gallery.min.js"></script>
		<script type="text/javascript">
			$(function() {
				initFun();
				xfy.GoodsEdit.handleStoreSelect('${goods.storeShelfId}');
				xfy.GoodsEdit.handleCategorySelect('${goods.midCategoryId}', '${goods.categoryId}');
				$('#store_id_select').change();
				$('#base_category_id').change();
				
				$('.open_flag_selector').click(function() {
					var ids = xfy.getCheckedValues('id');
					if (ids == '') {
						alert('请选择商品');
						return;
					}
					
					if (confirm("请确认操作？")) {
						var url = '${pageContext.request.contextPath}/goods/open-flag.json';
						var openFlag = $(this).attr('data');
						$.post(url, {id: ids, open: openFlag}, function(res) {
							dt.api().ajax.reload(null, false);
							$('.c-all-and-no-select').attr('checked', false);
							$.uniform.update('.c-all-and-no-select');
						});
					}
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
				}
			

			
			$("#searchbtn").click(function() {
				$('#goods_table').dataTable().fnDestroy();
				initFun();
			});

			var dt = null;
			function initFun() {
				xfy.initDataTable();
				dt = $("#goods_table").dataTable({
					serverSide : true,
					iDisplayLength:10,
					searching: true,
					ordering:true,
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
									return '<input type="checkbox" name="id" value="{0}" />'.format(row.id);
								},"sortable":false
							},
							{
								data : function(row) {
									var url = '';
									var urlO = ''; //原图url
									if (row.imgCount ==0) {
										url = '${pageContext.request.contextPath}/resources/assets/global/img/noimg.png';
									} else {
										var randomParam = '?_=' + new Date().getTime();
										var sku = row.goodsSku;
										url = '${x:getConfig("images.base.url")}/g/' + sku.substring(0,2) + '/' + sku + '/g-1-S.jpg' + randomParam;
										urlO = '${x:getConfig("images.base.url")}/g/' + sku.substring(0,2) + '/' + sku + '/g-1-M.jpg' + randomParam;
									}
									
									var img = '<img class="small-img" src="'+ url +'" />';
									if (urlO != '') {
										img = '<a href="{0}" target="_blank" data-gallery>{1}</a>'.format(urlO, img);
									}
									return img;
								},"sortable":false
							},
							{
								data : function(row) {
									var val = row.goodsSku; 
									val += '<br />' + row.oldSku;
									val += '<br />';
									val += (row.openFlag == 0) ? '<span class="label label-sm label-default">未开放</span>' : '<span class="label label-sm label-success">已开放</span>';
									return val;
								},"sortable":true
							},
							
							{data : "name","sortable":false},
							{data:"categoryName","sortable":false},
							{data:"rules","sortable":false},
							{data:"color","sortable":false},
							/* {data:"rules","sortable":true}, */
							{data:"goodsSize","sortable":false},
							{data : "developTime","sortable":false},
							/* {data : "price"}, */
							{data : "cost","sortable":false},
							
					/* 		{
								data:function(row) {
									return row.cost != null ? (row.cost * 1.1).toFixed(2) : '';
								}
							}, */
							{data:"customCost","sortable":false},
							{data:"firstCost","sortable":false},
							{data:"weight","sortable":false},
							
							{data:"sales7","sortable":true},
							{data:"sales15","sortable":true},
							{data:"sales30","sortable":true},
							{data:"count","sortable":false},
							{data:"lockCount","sortable":false},
							{data:"purchaseCount","sortable":false},
							{data:"occupy","sortable":false},

				/* 			{
								data:function(row) {
									return row.cost != null ? (row.cost * 1.1).toFixed(2) : '';
								}
							}, */
							
							{data:"buyUserName","sortable":false},
			/* 				{data:"pickUserName","sortable":false},
							{data:"assembleUserName","sortable":false}, */
							{data:"developUserName","sortable":false},
							
							{data:"packingCapacity","sortable":false},
							{data:"materialModel","sortable":false},
							
							{data:function(){return '';},"sortable":false},
							
							
							{data : "storeShelfCode","sortable":false},
							{data : "statusDesc","sortable":false},
							/* {data:function(){return '';},"sortable":false}, */
					 		{data:function(row){
							//return	getListingCount(row.goodsSku);},
							return '<span class="_my_listing_container" data-sku="' + row.goodsSku + '" id="_my_listing_container_' + row.goodsSku + '"></span>';},
							"sortable":false},
							{data:function(row){
							return row.belongSelf==1?"公司":row.belongSelf==0?"第三方":"";
							},"sortable":false},
					
							{data : function(row) {
									var edit = '';
									<x:power type="function" code="goods_select">
									edit += '<a href="${pageContext.request.contextPath }/goods/edit?actionType=0&id='+row.id+'"  class="btn default btn-xs purple"> <i class="fa fa-edit"></i>查看</a>';
									</x:power>
									
									<x:power type="function" code="goods_edit">
									edit += '<a href="${pageContext.request.contextPath }/goods/edit?actionType=1&id='+row.id+'"  class="btn default btn-xs purple"> <i class="fa fa-edit"></i>修改</a>';
									</x:power>
									<%-- var drop = '<a  class="btn default btn-xs black c-del-btns" href="${pageContext.request.contextPath }/goods/delete?id=' + row.id + '"> <i class="fa fa-trash-o"></i> <spring:message code="g.label.delete"/> </a>';--%>
									
									<x:power type="function" code="goods_image">
									edit += '<div><a href="${pageContext.request.contextPath }/goods-image?sku='+row.goodsSku+'" class="btn default btn-xs blue" target="_blank">图片维护</a></div>';
									</x:power>
									
									<x:power type="function" code="goods_cost_update">
									edit += '<a href="${pageContext.request.contextPath }/goods/costPage?id='+row.id+'"  class="btn default btn-xs purple" data-target="#listingModal" data-toggle="modal"> <i class="fa fa-edit"></i>修改价格</a>';
									</x:power>
									
									return edit;
								},"sortable":false
							} 
					],
	 			drawCallback: function( settings ) {
		/* 			$('._my_listing_container').each(function() {
			   		var goods_sku = $(this).attr('data-sku');
			   		getListingCount(goods_sku);
			   		}); */
					var skuArray = '';
			 		var table = $('#goods_table').DataTable();
					table.data().each( function (d) {
						skuArray += d.goodsSku + ',';
					});
					var param = skuArray.substring(0,skuArray.length-1);
			 		var path = '${pageContext.request.contextPath }/goods/getListingCount';
					
			 		$.post(path,{sku:param},function(result){
						
						$.each(result, function(commentIndex, comment){
						var url = '${pageContext.request.contextPath }/goods/skuListingPage?goodsSku=';
						var html = '<a href='+ url + comment.goodsSku +	'&count='+	comment.count + ' data-target="#listingModal" data-toggle="modal">{0}</a>'.format(comment.count);
						$('#_my_listing_container_' + comment.goodsSku).html(html);
						
						});
						
					},'json');
				}
					
				});
			};
			
			function exportGoodsInfo(){
				var ids = xfy.getCheckedValues('id');
				if(ids == ''){
					if(window.confirm('确定全部导出！')){
						var path = '${pageContext.request.contextPath }/goods/exportInfo';
						$('#goods_search').attr("action", path).submit();
					}
				}else{
					
					xfy.requestByForm({
							method: 'post',
							action: '${pageContext.request.contextPath }/goods/exportInfo',
							data: {ids : ids}
					});
				}
			}
			
			function getListingCount(sku){
				var url = '${pageContext.request.contextPath }/goods/skuListingPage?goodsSku=';
				
		 		$.ajax({
					type : "post",
					url : "${pageContext.request.contextPath }/goods/getListingCount",
					async: true,
					data : { 
						goodsSku:sku
					},
					dataType : "json",
					success : function(data) {
						var html = '<a href='+ url + sku+' data-target="#listingModal" data-toggle="modal">{0}</a>'.format(data);
						$('#_my_listing_container_' + sku).html(html);
					}
				});
			}
			
			
			
		</script>

	</tiles:putAttribute>

</tiles:insertDefinition>