<%--
物流公司交接
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:set scope="request" var="sysModule" value="order" />
<c:set scope="request" var="sysPage" value="package-shipping" />

<c:set var="pageTitle" value="物流公司交接" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">${pageTitle }</tiles:putAttribute>

	<%-- 页面级别的 CSS --%>
	<%-- <tiles:putAttribute name="css-page">

	</tiles:putAttribute> --%>

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
						<div class="table-toolbar">
							<div class="row">
								<div id="sp_search_form" class="col-md-12 form-horizontal" role="form">
									<div class="col-md-3">
										<div class="form-group form-group-sm">
											<label for="dict_code" class="col-md-3 control-label">追踪号</label>
											<div class="col-md-9">
												<input type="text" class="form-control" name="trackNumber" id="track_number_input" value="${param.sku }" />
												<input type="hidden" name="id" id="id_input" value="" />
											</div>
										</div>
									</div>
									<div class="col-md-3" >
										<button type="button" id="confirm_btn" class="btn blue" style="display:none" data-loading-text="加载中...">
											<spring:message code="g.label.confirm" />
										</button>
									</div>
								</div>
							</div>
						</div>
						<%@include file="../include/message.jsp"%>
						<table class="table table-striped table-bordered table-hover table-condensed" id="list_table">
							<thead>
								<tr>
									<th>订单编号</th>
									<th>包裹编号</th>
									<th>Buyer Email/ID</th>
									<th>运输方式</th>
									<th>跟踪号</th>
									<th>状态</th>
									<th>包装规格</th>
									<th>包裹重量</th>
									<th>电子称重量</th>
									<th>扫描时间</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

	</tiles:putAttribute>

	<%-- 页面级别的 JS --%>
	<tiles:putAttribute name="js-page">
		
		<script>
		var ary = [];
		$(function() {
			function clearExists() {
				$('#list_table tbody').html('');
				$('#id_input').val('');
				$('#confirm_btn').hide();
			}
		
			$('#track_number_input').bind('input propertychange', function() {
				var trackNumber = $.trim($(this).val());
				if (trackNumber.length <= 0) {
					//clearExists();
					return;
				}
				
				var url = '${pageContext.request.contextPath }/order-package/get-by-track-number.json?trackNumber=' + trackNumber;
				console.log(url);
				$.get(url, function(data) {
					console.log(data);
					if (! data) {
						$('#id_input').val("");
						return;
					}
					
					if (data.status != 5) {
						alert('已扫描的订单才能进行物流公司对接！');
						$('#id_input').val("");
						return;
					}
					
					var html = [
						'<tr>',
						'	<td>{0}</td>',
						'	<td>{1}</td>',
						'	<td>{2}</td>',
						'	<td>{3}</td>',
						'	<td>{4}</td>',
						'	<td>{5}</td>',
						'	<td>{6}</td>',
						'	<td>{7}</td>',
						'	<td>{8}</td>',
						'	<td>{9}</td>',
						'</tr>'
					].join('');
					var statusName  ="";
					if (data.status==1){
						statusName= "待处理";
					}
					if (data.status==2){
						statusName= "已匹配运输方式";
					}
					if (data.status==3){
						statusName= "已申请跟踪号";
					}
					if (data.status==4){
						statusName= "已打印";
					}
					if (data.status==5){
						statusName= "已扫描";
					}
					if (data.status==6){
						statusName= "已交接";
					}
					if (data.status==7){
						statusName= "已删除";
					}
					if (data.status==8){
						statusName= "物流退回";
					}
					html = html.format(data.orderId, data.id, data.buyerEmail+"/"+data.buyerUserId,
							data.shippingName, data.trackNumber, 
							statusName, data.specifications,data.packageWeight,
							data.electronWeight?data.electronWeight:data.packageWeight, new Date(data.scannedTime).format("yyyy-MM-dd HH:mm:ss"));
					//不处理重复的
					if (!ary[data.id]){
						$('#list_table tbody').append(html);
						ary[data.id] =data.id;
					}
					//$('#id_input').val(data.id);
					$('#id_input').val("");
					$('#track_number_input').val("");
					$('#confirm_btn').show();
					
				}, 'json');
			});

			$('#confirm_btn').click(function() {
				var $btn = $(this);
				var idAry = [];
				for(var p in ary){
					idAry.push(ary[p]);
				}
				console.log(idAry.join(","));
				var url = '${pageContext.request.contextPath }/order-package/confirm-ship.json?id=' + idAry.join(",") ;
				$.get(url, function(data) {
					if (data.success === true) {
						xfy.toastr("info", "", "操作成功！");
					} else {
						xfy.toastr("error", "", data.message);
					}
					$btn.button('reset');
				}, 'json');
			});
		});
		</script>
		
	</tiles:putAttribute>

</tiles:insertDefinition>