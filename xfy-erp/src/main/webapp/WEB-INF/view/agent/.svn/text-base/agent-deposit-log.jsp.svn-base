<%--
卖家资金流水明细
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:set scope="request" var="sysModule" value="agent" />
<c:set scope="request" var="sysPage" value="agent_deposit_log" />

<c:set var="pavaTitle" value="卖家资金流水明细" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">卖家资金流水明细</tiles:putAttribute>

	<%-- 页面级别的 CSS --%>
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
							<i class="icon-note theme-font"></i> <span class="caption-subject theme-font bold uppercase">${pavaTitle }</span>
						</div>
					</div>
					<div class="portlet-body">
					
						<div class="table-toolbar">
							<form id="search_form" class="form-horizontal" role="form" search="1" method="post">

								<input type="hidden" name="agentUserId" value="${agentUserId}" />
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label  class="col-md-4 control-label">流水类型</label>
											<div class="col-md-8">
												<select class="form-control input-sm" name="type">
													<option value="">请选择....</option>
													<option value="1">收入</option>
													<option value="0">支出</option>
												</select>
											</div>
										</div>
									</div>
										
									<div class="col-md-3">
											<div class="form-group">
											<label class="col-md-4 control-label">订单号</label>
												<div class="col-md-8">
													<input type="text" class="form-control input-sm" name="orderId" />
												</div>
											</div>
									</div>
									
									<div class="col-md-5">
										<div class="form-group">
											<label  class="col-md-2 control-label">时间</label>
											<div class="col-md-5">
													<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
														<input name="startDateStr" class="form-control input-sm" /> <span class="input-group-btn">
															<button class="btn default btn-sm" type="button">
																<i class="fa fa-calendar"></i>
															</button>
														</span>
													</div>
											</div>
											<div class="col-md-5">
												<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
														<input name="endDateStr" class="form-control input-sm" /> <span class="input-group-btn">
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
											<label  class="col-md-4 control-label">卖家类型</label>
											<div class="col-md-8">
												<select class="form-control input-sm" name="isCompanySeller">
													<option value="">请选择....</option>
													<option value="1">公司</option>
													<option value="0">个人</option>
												</select>
											</div>
										</div>
									</div>
									
									
									<div class="col-md-3">
										<div class="form-group">
											<label  class="col-md-4 control-label">卖家选择</label>
											<div class="col-md-8">
												<select class="form-control input-sm" name="contacts">
													<option value="">请选择....</option>
													
													<c:forEach var="item" items="${agentSellers}">
														<option value="${item.contacts}">${item.contacts}</option>
													</c:forEach>
													
												</select>
											</div>
										</div>
									</div>
									
																
									<div class="col-md-3">
								<!-- 			<div class="form-group">
											<label class="col-md-4 control-label">卖家账号</label>
												<div class="col-md-8">
													<input type="text" class="form-control input-sm" name="" />
												</div>
											</div> -->
									</div>
									
									<div class="col-md-2">
										<div class="form-group">
											<button type="button" id="form_search_btn" class="btn green btn-sm">
												<spring:message code="g.label.search" />
											</button>
											<button type="button" id="clearbtn" class="btn blue btn-sm">清空</button>
										<!-- 	<button type="button" onclick="exportDeposit();" class="btn green btn-sm">导出</button> -->
										</div>
									</div>
									
								</div>
								
								
							</form>
						</div>

						<table class="table table-bordered table-hover" id="users_table">
							<thead>
								<tr>
									<th>卖家</th>
									<th>时间</th>
									<th>类型</th>
									<th>是否VIP</th>
									<th>订单号</th>
									<th>产品成本(元)</th>
									<th>运费(元)</th>
									<th>订单处理费(元)</th>
									<th>流水金额(元)</th>
									<th>余额(元)</th>
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
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>


		<script>

		$(function(){
			xfy.initDataTable();
			var dt = $("#users_table").dataTable({
				serverSide: true,
				ajax: {
					url: "${pageContext.request.contextPath }/seller/deposit-log.json",
					data: function (d) {
						d.params = $('#search_form').serializeObject();
						return JSON.stringify( d );
					},
					type: 'post',
					contentType:"application/json;charset=UTF-8"
				},
				columns: [
				    {className: 'text-center',data: "contacts"},
					{className: 'text-center',data: "createdTime"},
					{
						className: 'text-center',
						data: "type", 
						render: function(data) {
							return data === 1 ? '收入' : (data === 0 ? '支出' : data);
						}
					},
					{	data:"vipSeller",
						className: 'text-center',
						render: function(data) {
							return data === 1 ? '是' : (data === 0 ? '否' : data);
						}
					},
					{data: "orderId",className: 'text-center'},
					{
						className: 'text-right',
						data: "orderCost",
						render:function(data){
							return data != null ? (data+0.001).toFixed(1) : data;
						}
					},
					{
						data:"orderShippingFee",
						className: 'text-right',
						render:function(data){
							return data != null ? (data+0.001).toFixed(1) : data;
						}
					},
					{
						data: "orderFee",
						className: 'text-right',
						render:function(data){
							return data != null ? (data).toFixed(1) : data;
						}
					},
					{
						className: 'text-right',
						data: "amount",
						render: function(data) {
							return data != null ? (data+0.001).toFixed(1) : data;
						}
					},
					
					{
						className: 'text-right',
						data: "balanceAfter",
						render: function(data) {
							return data != null ? (data+0.001).toFixed(1) : data;
						}
					}
				]
			});
			
			$('#form_search_btn').click(function() {
				dt.api().ajax.reload();
			});
		});
		
		function exportDeposit(){
			var path = '${pageContext.request.contextPath}/seller/exportDeposit';
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