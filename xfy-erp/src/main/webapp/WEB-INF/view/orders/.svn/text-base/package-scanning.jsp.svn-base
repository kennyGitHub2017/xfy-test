<%--
扫描工作台
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:set scope="request" var="sysModule" value="order" />
<c:set scope="request" var="sysPage" value="package-scanning" />

<c:set var="pageTitle" value="扫描工作台" />
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
									<div class="col-md-2">
										<input class="form-control" type="text" name="deviceNo" id="deviceNo_id">
									</div>
									
									<div class="col-md-6">
										<div class="form-group form-group-lg">
											<label class="col-sm-2 control-label" for="formGroupInputLarge">输入追踪号</label>
											<div class="col-sm-10">
											  <input class="form-control" type="text" name="trackNumber"  id="track_number_input">
											</div>
										</div>
									</div>
									
									<div class="col-md-4">
									<h1 id="tips_id"></h1>
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
									<th>扫描人</th>
									<th>原因描述</th>
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
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
		
		<script>
		$(function() {
			$('#track_number_input').focus();
			
			var time = null;
			var requesting = false;
			var flag = 0;
			
			$('#track_number_input').bind('input', function(e) {
				time = e.timeStamp;
			});
			
			
			var request = function() {
				var trackNumber = $.trim($('#track_number_input').val());
				if (trackNumber == '') {
					$('#track_number_input').val('');
					requesting = false;
					return;
				}
				var deviceNo = $.trim($('#deviceNo_id').val());
				var url = '${pageContext.request.contextPath }/order-package/confirm-scan.json?trackNumber=' + trackNumber;
				
				$.get(url, {deviceNo:deviceNo,flag: flag}, function(res) {
					if (res.success === true) {
						
						$('#tips_id').html('<span class="text-success">扫描成功</span>');
						showPackage(res.data, '成功');
						
					} else {
						
						$('#tips_id').html('<span class="text-danger">' + res.message + '</span>');
						
						if(res.confirmFlag === true){
							if(window.confirm("重量差距太大,确认继续？")){
								flag = 1;
								request();
								flag = 0;
								return;
							} /* else {
								
								$.post('${pageContext.request.contextPath }/order-package/cancelWeigh',
										{trackNumber:trackNumber},function(res){
										});
							}  */
						}
						
						$('#tips_id').html("扫描失败");
						if (res.data != null) {
							showPackage(res.data, res.message);
						}
						$('#tips_id').html('<span class="text-danger">扫描失败：' + res.message + '</span>');
						
					}

					requesting = false;
					time = null;
					
					$('#track_number_input').val('').focus();

				}, 'json');
			}
			
			var check = function() {
				
				if (time == null || requesting === true) {
					return;
				}
				
				var interval = new Date().getTime() - time;
				//console.log(interval);
				if (interval >= 300) {
					requesting = true;
					
					request();
				}
				
			};
			
			var timeChecker = setInterval( check , 100);
			

			var htmlPattern = [
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
				'	<td>{10}</td>',
				'	<td>{11}</td>',
				'</tr>'
			].join('');
			
			function showPackage(data, desc) {
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
				var html = htmlPattern.format(data.orderId, data.id, data.buyerEmail+"/"+data.buyerUserId, 
						data.shippingName, data.trackNumber, 
						statusName, data.specifications,data.packageWeight,
						data.electronWeight?data.electronWeight:'',
						data.scannedTime ? new Date(data.scannedTime).format("yyyy-MM-dd HH:mm:ss") : '', 
						data.scannedUser, desc);
				
				$('#list_table tbody').prepend(html);
				
			}
		});
		</script>
		
	</tiles:putAttribute>

</tiles:insertDefinition>