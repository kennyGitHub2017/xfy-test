<%--
账户充值查询
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="account_recharge_list" />

<c:set var="accountTitle" value="账户充值查询" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">${accountTitle }</tiles:putAttribute>

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
							<i class="icon-note theme-font"></i> <span class="caption-subject theme-font bold uppercase">${accountTitle }</span>
						</div>
					</div>
					<div class="portlet-body">
						<div class="table-toolbar">
							<form id="search_form" search="1" class="form-horizontal" role="form" method="post">
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label  class="col-md-4 control-label">姓名</label>
											 <div class="col-md-8">
												<input type="text" name="contacts" class="form-control c-supplier-picker input-sm" value=""  />
											 </div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label  class="col-md-4 control-label">订单号</label>
											 <div class="col-md-8">
												<input type="text" name="outTradeNo" class="form-control c-supplier-picker input-sm" value=""  />
											 </div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label  class="col-md-2 control-label">充值时间</label>
											<div class="col-md-5">
													<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
														<input name="startDate" class="form-control input-sm" /> <span class="input-group-btn">
															<button class="btn default btn-sm" type="button">
																<i class="fa fa-calendar"></i>
															</button>
														</span>
													</div>
											</div>
											<div class="col-md-5">
												<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
														<input name="endDate" class="form-control input-sm" /> <span class="input-group-btn">
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
										<div class="form-group form-group-sm">										
											<label  class="col-md-4 control-label">充值类型</label>
											<div class="col-md-8">
													<select name="type" class="form-control">
														<option value="">请选择</option>
														<option value="0">微信充值</option>
														<option value="1">支付宝充值</option>
													</select>
											</div>
										</div>
									</div>
									<div class="col-md-3">
											<div class="form-group">
											<label class="col-md-4 control-label">充值金额</label>
												<div class="col-md-4">
													<input type="text" class="form-control input-sm" style="width: 90px" name="amountFrom" />
												</div>
												<div class="col-md-4">
													<input type="text" class="form-control input-sm" style="width: 90px" name="amountTo" />
												</div>
											</div>
									</div>
									<div class="col-md-3">
										<div class="form-group form-group-sm">										
											<label  class="col-md-4 control-label">支付状态</label>
											<div class="col-md-8">
													<select name="status" class="form-control">
														<option value="">请选择</option>
														<option value="0">支付失败</option>
														<option value="1">支付成功</option>
													</select>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group" style="text-align:center;">
											<label  class="col-md-3 control-label"></label>
											<button type="button" id="form_search_btn" class="btn green btn-sm" >
												<spring:message code="g.label.search" />
											</button>
											<button type="button" id="clearbtn" class="btn blue btn-sm" >清空</button>
											<button type="button"  onclick="exportData();" class="btn green btn-sm">数据导出</button>
										</div>
									</div>
								</div>
							</form>
						</div>
						
						<table class="table table-bordered table-hover" id="account_table">
							<thead>
								<tr>
									<th>姓名</th>
									<th>手机号</th>
									<th>订单号</th>
									<th>充值金额</th>
									<th>充值描述</th>
									<th>支付时间</th>
									<th>支付状态</th>
									<th>充值类型</th>
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
		var dt;
		
		function loadPageData(){
			dt = $("#account_table").dataTable({
				serverSide: true,
				ajax: {
					url: "${pageContext.request.contextPath }/seller/payOrderPageJson",
					data: function (d) {
						d.params = $('#search_form').serializeObject();
						return JSON.stringify( d );
					},
					type: 'post',
					contentType:"application/json;charset=UTF-8"
				},
				columns: [
					{className: 'text-center',data: "contacts"},
					{className: 'text-center',data: "mobile"},  
					{className: 'text-center',data: "outTradeNo"}, 
					{className: 'text-center',data: "totalFee"},  
					{className: 'text-center',data: "body"},  
					{className: 'text-center',data: "createdTime"},  
					{className: 'text-center',
						data: "status", 
						render: function(data) {
							return data === 1 ? '支付成功' : '支付失败';
						}
					},
					{className: 'text-center',
						data: "type", 
						render: function(data) {
							return data === 1 ? '支付宝充值' : '微信充值';
						}
					}
				]
			});
		}
		
		$(function(){
			xfy.initDataTable();
			loadPageData();
			$('#form_search_btn').click(function() {
				if (!dt){
					loadPageData();
				}else{
					dt.api().ajax.reload();	
				}
			});
		});
		
		function exportData(){
			var path = '${pageContext.request.contextPath}/seller/exportData';
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