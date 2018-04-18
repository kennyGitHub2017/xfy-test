<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>
<c:set scope="request" var="sysModule" value="order" />
<c:set scope="request" var="sysPage" value="order-package" />

<c:set var="pageTitle" value="订单包裹" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">${pageTitle }</tiles:putAttribute>

	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/select2/select2.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css" />
		<style>
			td.details-control {
				background:
					url('${pageContext.request.contextPath}/resources/assets/global/img/details_open.png')
					no-repeat center center;
				cursor: pointer;
			}
			
			tr.details td.details-control {
				background:
					url('${pageContext.request.contextPath }/resources/assets/global/img/details_close.png')
					no-repeat center center;
			}
		</style>
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
						<%@include file="../include/message.jsp"%>
						
						<form id="package_search" search="1" class="form-horizontal" method="post">
							<!--/row-->
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-2">订单ID</label>
										<div class="col-md-10">
											<input type="text" class="form-control" name="orderNumber" value="${orderId }"  id="orderId_id">
										</div>
									</div>
								</div>

								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-2">包裹编号</label>
										<div class="col-md-10">
											<input type="text" class="form-control" name="packageNo">
										</div>
									</div>
								</div>

								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-2">SKU编号</label>
										<div class="col-md-10">
											<input type="text" class="form-control" name="goodsSku">
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-2">名称</label>
										<div class="col-md-10">
											<input type="text" class="form-control" name="goodsName">
										</div>
									</div>
								</div>

								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-2">运输方式</label>
										<div class="col-md-10">
											<select class="form-control" name="shippingName">
												<option value="">请选择</option>

												<c:forEach items="${shippingAttr}" var="item">
													<option value="${item.shippingName}">${item.shippingName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>

			<!-- 					<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-2">混合</label>
										<div class="col-md-10">
											<select class="form-control" name="isMix">
												<option value="">请选择</option>
												<option value="0">否</option>
												<option value="1">是</option>
											</select>
										</div>
									</div>
								</div> -->
								
								<div class="col-md-4">
									<div class="form-group">
									<label class="control-label col-md-2">是否暂停</label>
										<div class="col-md-10">
											<select class="form-control" name="suspendFlag">
											<option value="">请选择</option>
											<option value="0">否</option>
											<option value="1">是</option>
											</select>
										</div>
									</div>
								</div>
								
							</div>

							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-2">打印</label>
										<div class="col-md-4">
											<select class="form-control" name="printedFlag">
												<option value="">请选择</option>
												<option value="0">未打印</option>
												<option value="1">已打印</option>
											</select>
										</div>
										
										<label class="control-label col-md-2">扫描</label>
										<div class="col-md-4">
											<select class="form-control" name="scanFlag">
												<option value="">请选择</option>
												<option value="0">否</option>
												<option value="1">是</option>
											</select>
										</div>
									</div>
								</div>

								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-2">跟踪号</label>
										<div class="col-md-10">
											<input type="text" class="form-control" name="trackNumber">
										</div>
									</div>
								</div>

								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-md-2">包裹状态</label>
										<div class="col-md-10">
											<select class="form-control" name="status">
												<option value="">请选择</option>
												<option value="1">待处理</option>
												<option value="2">匹配运输方式</option>
												<option value="3">申请跟踪号</option>
												<option value="4">已打印</option>
												<option value="11">待发货</option>
												<option value="5">已扫描</option>
												<option value="6">已交接</option>
												<option value="7">已删除</option>
												<option value="9">物流退回</option>
											</select>
										</div>
									</div>
								</div>
							</div>
						
						
						<div class="row">
						 <div class="col-md-2">
							<div class="form-group">
							<label class="control-label col-md-4">有跟踪号</label>
								<div class="col-md-8">
										<select class="form-control" name="isTrackNumber">
										<option value="">请选择</option>
										<option value="0">否</option>
										<option value="1">是</option>
										</select>
								</div>
							</div>
							</div>
							
							<div class="col-md-2">
							<div class="form-group">
							<label class="control-label col-md-4">有运送方式</label>
								<div class="col-md-8">
									<select class="form-control" name="isShip">
									<option value="">请选择</option>
										<option value="0">否</option>
										<option value="1">是</option>
									</select>
								</div>
							</div>
							</div>
							
							
							<div class="col-md-2">
							<div class="form-group">
							<label class="control-label col-md-4">有无note</label>
								<div class="col-md-8">
									<select class="form-control" name="isNote">
									<option value="">请选择</option>
									<option value="0">有</option>
									<option value="1">无</option>
									</select>
								</div>
							</div>
							</div>
							
							<div class="col-md-2">
							<div class="form-group">
							<label class="control-label col-md-4">是否标发</label>
								<div class="col-md-8">
									<select class="form-control" name="isMarkShip">
									<option value="">请选择</option>
									<option value="0">否</option>
									<option value="1">是</option>
									</select>
								</div>
							</div>
							</div>
							
							
								<div class="col-md-4">
									<div class="form-group">
										<label  class="col-md-2 control-label">打印时间</label>
										<div class="col-md-5">
												<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
													<input name="cPrintFrom" class="form-control input-sm" /> <span class="input-group-btn">
														<button class="btn default btn-sm" type="button">
															<i class="fa fa-calendar"></i>
														</button>
													</span>
												</div>
										</div>
										<div class="col-md-5">
											<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
													<input name="cPrintTo" class="form-control input-sm" /> <span class="input-group-btn">
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
									<div class="form-group">
										<label  class="col-md-2 control-label">扫描时间</label>
										<div class="col-md-5">
												<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
													<input name="scannedFrom" class="form-control input-sm" /> <span class="input-group-btn">
														<button class="btn default btn-sm" type="button">
															<i class="fa fa-calendar"></i>
														</button>
													</span>
												</div>
										</div>
										<div class="col-md-5">
											<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
													<input name="scannedTo" class="form-control input-sm" /> <span class="input-group-btn">
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
										<label  class="col-md-2 control-label">物流退回</label>
										<div class="col-md-5">
												<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
													<input name="cRejectFrom" class="form-control input-sm" /> <span class="input-group-btn">
														<button class="btn default btn-sm" type="button">
															<i class="fa fa-calendar"></i>
														</button>
													</span>
												</div>
										</div>
										<div class="col-md-5">
											<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
													<input name="cRejectTo" class="form-control input-sm" /> <span class="input-group-btn">
														<button class="btn default btn-sm" type="button">
															<i class="fa fa-calendar"></i>
														</button>
													</span>
											</div>
										</div>
									</div>
								</div>
								
								<div class="col-md-2">
									<div class="form-group">
										<label class="control-label col-md-2">扫描人</label>
										<div class="col-md-8">
											<input type="text" class="form-control" name="scannedUser">
										</div>
									</div>
								</div>
							
								<div class="col-md-2">
									<div class="form-group">
									<label class="control-label col-md-12"></label>
									<button type="button" id="searchbtn" class="btn green">
										<spring:message code="g.label.search" />
									</button>
									<button type="button" id="clearbtn" class="btn blue">清空</button>
									</div>
								</div>
								
							</div> 


						</div>

						</form>
						<div class="table-toolbar"><x:power type="function" code="order-package_markship">
							<a herf="javascript:;" id="mark_shipping" class="btn blue">标发</a></x:power>
							<x:power type="function" code="order-package_editship">
							<a herf="javascript:;" id="batchedit_shipping" class="btn blue">批量修改发货方式</a></x:power>
							
							<%-- 
							<x:power type="function" code="order-package_marktrack">
							<div class="btn-group">
								<button class="btn green dropdown-toggle" type="button" data-toggle="dropdown" data-hover="dropdown" data-delay="500" data-close-others="true" aria-expanded="true">
									跟踪号 <i class="fa fa-angle-down"></i>
								</button>
								<ul class="dropdown-menu" role="menu">
									<li><a herf="javascript:;" class="track_number_btn" data="Manual">平邮跟踪号申请</a></li>
									<li><a herf="javascript:;" class="track_number_btn" data="Apac">eBay亚太物流 跟踪号申请</a></li>
									<li><a herf="javascript:;" class="track_number_btn" data="Apac" action="cancel">eBay亚太物流 删除跟踪号</a></li>
									<li><a herf="javascript:;" class="track_number_btn" data="Apac" action="confirm">eBay亚太物流  交运</a></li>
									<li><a herf="javascript:;" class="track_number_btn" data="YanWen">燕文跟踪号申请</a></li>
									<li><a herf="javascript:;" class="track_number_btn" data="bPost">bPost跟踪号申请</a></li>
									<li><a herf="javascript:;" class="track_number_btn" data="SfEuropeA1">顺丰欧洲小包</a></li>
									<li><a herf="javascript:;" class="track_number_btn" data="SfRussia9">顺丰俄罗斯平邮</a></li>
									<li><a herf="javascript:;" class="track_number_btn" data="SfRussia10">顺丰俄罗斯挂号</a></li>
									<li><a herf="javascript:;" class="track_number_btn" data="Eub">青岛Eub</a></li>
									<li><a herf="javascript:;" class="track_number_btn" data="Eub2">(湘)Eub</a></li>
									<li><a herf="javascript:;" class="track_number_btn" data="DongGuanEub">国际物流(东莞)跟踪号申请</a></li>
									<li><a herf="javascript:;" class="track_number_btn" data="DongGuanEubDelete">国际物流(东莞)订单删除</a></li>
								</ul>
							</div>
							<div class="btn-group">
								<button class="btn green dropdown-toggle" type="button" data-toggle="dropdown" data-hover="dropdown" data-delay="500" data-close-others="true" aria-expanded="true">
									顺邮宝申请 <i class="fa fa-angle-down"></i>
								</button>
									<ul class="dropdown-menu" role="menu">
									<li><a herf="javascript:;" class="track_syb_btn" data="1">顺邮宝平邮</a></li>
									<li><a herf="javascript:;" class="track_syb_btn" data="2">顺邮宝挂号</a></li>
									<li><a herf="javascript:;" class="track_syb_btn" data="3">马来西亚挂号</a></li>
									<li><a herf="javascript:;" class="track_syb_btn" data="4">香港挂号</a></li>
									<li><a herf="javascript:;" class="track_syb_btn" data="5">瑞士挂号</a></li>
									
									</ul>
							</div>
							</x:power>
							--%>
							<x:power type="function" code="order-package_matchingship">
							<button type="button"  onclick="matchingShip();" class="btn green">自动匹配物流</button></x:power>
							<button type="button"  onclick="exportPackage();" class="btn green">包裹数据导出</button>
							<button type="button"  onclick="rejectedPackage();" class="btn green">物流退回导入</button>
						</div>
						
						<table class="table table-striped table-bordered table-hover table-condensed" id="list_table">
							<thead>
								<tr>
									<th>&nbsp;</th>
									<th><input type="checkbox" class="c-all-and-no-select" data-selector="input[type=checkbox][name=id]" /></th>
									<th>订单ID</th>
									<th>包裹编号/ID</th>
									<th>运输方式</th>
									<th>跟踪号</th>
									<th>是否标发</th>
									<th>打印</th>
									<th>打印时间</th>
									<th>状态</th>
									<th>包装规格</th>
									<th>重量</th>
									<th>电子称重量</th>
									<th>运费</th>
								<!-- 	<th>付款时间</th> -->
									<th>平台</th>
									<th>扫描时间</th>
									<th>扫描人</th>
									<th>备注</th>
									<th>物流退回</th>
									<th>物流退回时间</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>

		<%@include file="/WEB-INF/view/include/modal-ajax.jsp"%>
		
		<!-- form -->
		<div class="modal fade" id="rejectpackage" tabindex="-1" role="dialog"
  			 aria-labelledby="myModalLabel" aria-hidden="true">
  		   <form action="${pageContext.request.contextPath}/order-package/importRejectPack" method="post" enctype="multipart/form-data">
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal"
		               aria-hidden="true">×
		            </button>
					            <h4 class="modal-title" id="myModalLabel">
					               		物流退回导入操作		            
					            </h4>
					         </div>
					         <div class="modal-body">
									上传文件:<input type="file" class="form-control"  name="rejectPackFile" /><br>
		         			</div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default"
					               data-dismiss="modal">关闭
					            </button>
					            <button type="submit" class="btn btn-primary">导入</button>
		         </div>
		      </div><!-- /.modal-content -->
		   </div><!-- /.modal-dialog -->
		   </form><!-- /.form -->
		</div><!-- /.modal -->
		
	</tiles:putAttribute>

	<%-- 页面级别的 JS --%>
	<tiles:putAttribute name="js-page">
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/select2/select2.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/admin/pages/scripts/table-advanced.js"></script>
		<script>
	$(function(){
		initFun();
	});
	
	$("#package_search").validate({
		errorElement : 'span',
		errorClass : 'help-block error',
		focusInvalid : false,
		ignore : "ignore",
		rules:{
			orderNumber : {
				digits:true,
			}
		}
	}); 
	
	$('.date-picker').datepicker({
		orientation: "right",
		autoclose: true
	});
	
	$("#searchbtn").click(function(){
		var valid = $("#package_search").validate().element($('input[name=orderNumber]'));
		if(valid === true){
			if (dt != null) {
				dt.api().ajax.reload();
			} else {
				initFun();
			}
		} else {
			alert("请输入正确的订单编号");
		}
	});


var dt = null;
	
function initFun(){
	
	xfy.initDataTable();
	dt = $("#list_table").dataTable({
		serverSide : true,
		ajax : {
			url : "${pageContext.request.contextPath }/order-package/pageJson",
			type : 'post',
			contentType : "application/json;charset=UTF-8",
			data : function(d) {
				var jsondata = $("#package_search").serializeObject();  //转化为json
				d.params = jsondata;
				return JSON.stringify(d);
			}
		},
		columns : [
				
				{
					"class": "details-control",
					"orderable":false,
					"data":null,
					"defaultContent": ""
				},
				{data:function(data){return "<input type='checkbox' name='id' value="+data.id+">"}},
			
				{data:"orderId"},
				{data:"id"},
				{data:"shippingName"},
				{data:"trackNumber"},
				
				{data:"isSend",render:function(data){
					if(data == '0'){
						return '否';
					}else if(data == '1'){
						return '是';
					}else{
						return '';
					}
				}},
				
				{data:"printedFlag",render:function(data){
					if(data == '0'){
						return '否';
					}else if(data == '1'){
						return '是';
					}else{
						return '';
					}
				}},
				
				{data:"printedTime"},

				{data:"status",render:function(data){
					if(data == '1'){
						return '待处理';
					}else if(data == '2'){
						return '匹配运输方式';
					}else if(data == '3'){
						return '申请跟踪号';
					}else if(data == '4'){
						return '已打印';
					}else if(data == '5'){
						return '已扫描';
					}else if(data == '6'){
						return '已交接';
					}else if(data == 7){
							return '已删除';
					}else if(data == '9'){
						return '物流退回';
					}else if(data == '11'){
						return '待发货';
					}
					else{
						return data;
					}
				}},
				{data:"specifications"},
				{data:"packageWeight"},
				{data:"electronWeight"},
				{data:"shippingFee"},
		/* 		{data:function(data){
					return data.orderPaidTime?new Date(data.orderPaidTime).format("yyyy-MM-dd HH:mm:ss"):"";
				}}, */
				{data:"orderPlatform"},
				{data:function(data){
					return data.orderPaidTime?new Date(data.scannedTime).format("yyyy-MM-dd HH:mm:ss"):"";
				}},
				{data:"scannedUser"},
				{data:"note"},
		/* 		{data:function(data){
					return getShipFee(data.orderId,data.id);
				}}, */
				{data:function(row){
					var htm = xfy.html.confirmLink.format("${pageContext.request.contextPath }/order-package/back?idList="+row.id,"退回物流");
					//var htm = '<a  data-toggle="modal" class="btn default btn-xs purple" href="${pageContext.request.contextPath }/order-package/back?idList='+row.id+'"><i></i>退回物流</a>'
					return htm; 
					}},
				{data:function(row){
					return row.rejectedTime?new Date(row.rejectedTime).format("yyyy-MM-dd HH:mm:ss"):"";
				}}	
		]
	});
	
	function getShipFee(orderId,packageId){
		
		var html = '<select id="shipping-selector-{0}" >'.format( orderId );
		$.ajax({
			type : "post",
			async: false,
			url : "${pageContext.request.contextPath }/order/shipFee-json",
			data : { 
				orderId:orderId
			},
			dataType : "json",
			success : function(data) {
			$.each(data, function(commentIndex, comment){
				html += '<option value='+comment.id+'>{0}--{1}</option>'.format(comment.shippingName,comment.shippingFee);
			});
			
			}
		});
		
		html += '</select>';
		html += '<input type="button" value="保存" onclick="saveShippingInfo({0},{1})" />'.format(orderId,packageId);
		return html;
	}
	
	var detailRows = [];
	$('#list_table').on('click', 'tr td.details-control', function() {
		var tr = $(this).closest('tr');
		var row = dt.api().row(tr);
		//var idx = $.inArray(tr.attr('id'), detailRows);
			if (row.child.isShown()) {
				tr.removeClass('details');
				row.child.hide();
			}else{
				tr.addClass('details');
				row.child(format(row.data())).show();
			}

	});
		
		function format(d){
			
			var html = '<table>'
				+ '<tr><th>名称</th><th>SKU</th><th>订单数量</th>'
				+ '<th>包裹数量</th><th>出货数量</th><th>单价</th><th>包装规格</th>'
				+ '<th>仓库</th></tr>'
				
			$.ajax({
				type : "post",
				async: false,
				url : "${pageContext.request.contextPath }/order-package/packageItem",
				data : { 
					id : d.id,
				},
				dataType : "json",
				success : function(data) {
				
					$.each(data, function(commentIndex, comment){
						
						html += '<tr><th>'+comment.goodsName+'</th><th>'+comment.sku+'</th><th>'+comment.orderAmount+'</th>'+
								'<th>'+comment.packageAmount+'</th><th>'+comment.shipmentAmount+'</th><th>'+comment.price+'</th><th>'+comment.specifications+'</th>'+
								'<th>'+comment.storeName+'</th></tr>'
							});
						html += '</table>';
				}
			});
			
			return html;
		}

	

	}
	

//批量操作
function kk(){
	var s ="";
	 $('#list_table [type="checkbox"]:checked').each(function(){ 
		s+=$(this).val()+',';
		}); 
	 
	 var idList =  s.substring(0, s.length-1);
			$.post('${pageContext.request.contextPath }/order-package/back',
			{idList : idList},
			function(){
				window.location.href="${pageContext.request.contextPath }/order-package";
			});
	}
		

$(function() {
	$('#mark_shipping').click(function() {
		var ids = xfy.getCheckedValues('id');
		console.log(ids == '');
		if (ids == '') {
			alert('请选择要标发的订单');
			return;
		}
		
		xfy.requestByForm({
			method: 'post',
			action: '${pageContext.request.contextPath}/order-package/mark-shipping',
			target: "_blank",
			data: {id : ids}
		});
	});
	
	$('#batchedit_shipping').click(function() {
		var ids = xfy.getCheckedValues('id');
		console.log(ids == '');
		if (ids == '') {
			alert('请选择要修改发货方式的包裹');
			return;
		}
		
		xfy.requestByForm({
			method: 'post',
			action: '${pageContext.request.contextPath}/order-package/batchedit-page',
			target: "_blank",
			data: {id : ids}
		});
	});
	
	
	$(".track_number_btn").click(function() {
		var ids = xfy.getCheckedValues('id');
		if (ids == '') {
			alert('请选择要申请跟踪号的包裹');
			return;
		}
		
		xfy.requestByForm({
			method: 'post',
			action: '${pageContext.request.contextPath}/order-package/tracker-number',
			target: "_blank",
			data: {id : ids, type : $(this).attr('data'), action : $(this).attr('action')}
		});
	});
});

function saveShippingInfo(orderId,packageId){
	var commentId = $('#shipping-selector-' + orderId).val();
	$.post('${pageContext.request.contextPath }/order-package/update-shipping.json',
			{id:commentId,packageId:packageId},function(data){
				if (data === true) {
					window.location.reload();
				} else {
					alert("保存失败");
				}
			},'json');
}

function matchingShip(){
	window.location.href='${pageContext.request.contextPath }/order-package/matchingShipping';
}

function rejectedPackage(){
	$("#rejectpackage").modal({keyboard: false,backdrop:'static'});
}


$(".track_syb_btn").click(function() {
	var ids = xfy.getCheckedValues('id');
	if (ids == '') {
		alert('请选择要申请跟踪号的包裹');
		return;
	}
	
	xfy.requestByForm({
		method: 'post',
		action: '${pageContext.request.contextPath}/order-package/syb-trackernumber',
		target: "_blank",
		data: {id : ids, type : $(this).attr('data'), action : $(this).attr('action')}
	});
});
 
function exportPackage(){
	var ids = xfy.getCheckedValues('id');
	var path = '${pageContext.request.contextPath}/order-package/exportPrint';
	if(ids == ''){
		$('#package_search').attr("method","post");
		$('#package_search').attr("action", path).submit();	
	}else{
		xfy.requestByForm({
			method: 'post',
			action: path,
			data: {ids : ids}
		});
	}
}

</script>

	</tiles:putAttribute>
</tiles:insertDefinition>