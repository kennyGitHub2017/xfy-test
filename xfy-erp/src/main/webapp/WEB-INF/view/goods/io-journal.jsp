<%--
商品收发明细
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:set scope="request" var="sysModule" value="product" />
<c:set scope="request" var="sysPage" value="io-journal" />

<c:set var="pageTitle" value="出入库流水账" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">${pageTitle }</tiles:putAttribute>
	<tiles:putAttribute name="css-page">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css" />
	</tiles:putAttribute>

	<tiles:putAttribute name="page-content">
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light ">
					<div class="portlet-title">
						<div class="caption caption-md">
							<i class="icon-note theme-font"></i> <span class="caption-subject theme-font bold uppercase">${pageTitle }</span> <span class="caption-helper"></span>
						</div>
					</div>
					
					<div class="portlet-body">
						<form id="goods_io_search" class="form-horizontal" method="post" search="1">
							<!--/row-->
							<div class="row">
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">SKU编号</label>
										<div class="col-md-8">
											<input type="text" class="form-control" name="goodsSku">
										</div>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">开始时间</label>
										<div class="col-md-8">
											<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
												<input name="startDate" class="form-control" /> <span class="input-group-btn">
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
										<label class="control-label col-md-4">结束时间</label>
										<div class="col-md-8">
											<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
												<input name="endDate" class="form-control" /> <span class="input-group-btn">
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
										<label class="control-label col-md-4">货位</label>
										<div class="col-md-8">
											<select class="form-control" name="storeShelf">
												<option value="">请选择</option>
												<c:forEach var="sf" items="${shelf}">
													<option value="${sf.id}">${sf.code}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">商品名</label>
										<div class="col-md-8">
										<input type="text" class="form-control" name="goodsName">
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
						</form>
						<div align="right" style="color:red" id="totalmsg"></div>
						<table class="table table-striped table-bordered table-hover table-condensed" id="goods_io_table">
							<thead>
								<tr>	
									<td colspan="8"></td>
									<td><span id="totalin" style="color:red"></span></td>
									<td><span id="totalout" style="color:red"></span></td>
								</tr>
								<tr>
									<th>#</th>
									<th>SKU编号</th>
									<th>商品名</th>
									<th>日期</th>
									<th>单据类型</th>
									<th>单号</th>
									<th>仓库</th>
									<th>仓位</th>
									<th class="text-center">收入数量</th>
									<th class="text-center">发出数量</th>
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
			$('.date-picker').datepicker({
				orientation: "right",
				autoclose: true
			});
			
			
			$(function(){
				var $datatable = null;
				$("#searchbtn").click(function() {
					
/* 					if ($.trim($('input[name=goodsSku]').val()) == '') {
						alert('请输入SKU编码');
						$('input[name=goodsSku]').focus();
						return false;
					} */
					
					if ($datatable == null) {
						$datatable = handleDataTable();
					} else {
						$datatable.api().ajax.reload();
					}
					//发送ajax请滶汇总数据
					$.ajax({
					    url:'${pageContext.request.contextPath}/io-order/inOutTotal.json',
					    type:'post',
					    data: $("#goods_io_search").serialize(),
					    async : false, //默认为true 异步     
					    error:function(){
					       alert('ajax error');     
					    }, 
					    success:function(data){
								$("#totalin").text(data.totalIn?data.totalIn:"");
					    		$("#totalout").text(data.totalOut?data.totalOut:"");
					    }
					}); 
					
				});
			});
			
			
			
			function handleDataTable(){
				xfy.initDataTable();
				var $datatable = $("#goods_io_table").dataTable({
					serverSide: true,
			 		ajax: {
						url: "${pageContext.request.contextPath }/io-order/json-page",
						type: 'post',
						contentType:"application/json;charset=UTF-8",
						data: function (d) {
							 var data = $("#goods_io_search").serializeObject();
							 d.params = data;
							return JSON.stringify( d );
						}
					},
					columns: [
						{
							data: function(row, type, val, meta) {
								return meta.row + 1;
							}
						},
						{data: "goodsSku"},
						{data: "goodsName"},
						{data: "lastUpdatedTime", 
							render: function ( data, type, full, meta ) {
								return data == null ? "" : new Date(data).format("yyyy-MM-dd HH:mm:ss");
							}
						},
						{
							data: function(row) {
								return row.typeName + ' - ' + row.typeDetailName
							}
						},
						{data: "orderNo"},
						{data: "storeName"},
						{data: "storeShelf"},
						{
							data: function(row) {
								return row.type == 0 ? (row.qualifiedCount + row.unqualifiedCount) : '';
							}
						},
						{
							data: function(row) {
								return row.type == 1 ? row.qualifiedCount : '';
							}
						}
					]
				});
				return $datatable;
			};
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>