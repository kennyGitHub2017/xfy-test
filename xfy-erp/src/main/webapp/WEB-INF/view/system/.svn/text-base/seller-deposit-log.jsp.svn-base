<%--
卖家保证金流水日志
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%-- <c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="seller" /> --%>

<c:set var="pavaTitle" value="账户资金流水日志" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">${pavaTitle }</tiles:putAttribute>

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
							<form id="search_form" class="form-horizontal" role="form">
								<input type="hidden" name="sellerId" value="${sellerId }" />
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label  class="col-md-4 control-label">类型</label>
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
											<label class="col-md-4 control-label">开始日期</label>
											<div class="col-md-8">
												<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
													<input name="startDateStr" class="form-control input-sm" /> <span class="input-group-btn">
														<button class="btn default btn-sm" type="button">
															<i class="fa fa-calendar"></i>
														</button>
													</span>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="col-md-4 control-label">结束日期</label>
											<div class="col-md-8">
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
									<div class="col-md-3">
										<div class="form-group">
											<button type="button" id="form_search_btn" class="btn green btn-sm">
												<spring:message code="g.label.search" />
											</button>
										</div>
									</div>
								</div>
							</form>
						</div>

						<table class="table table-bordered table-hover" id="users_table">
							<thead>
								<tr>
									<th>时间</th>
									<th>类型</th>
									<th>金额(元)</th>
									<th>余额(元)</th>
									<th>原因</th>
									<th>操作人</th>
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
				searching: true,
				serverSide: true,
				//ajax: "${pageContext.request.contextPath }/seller/page-json",
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
					{className: 'text-center',data: "createdTime"},
					{
						className: 'text-center',
						data: "type", 
						render: function(data) {
							return data === 1 ? '收入' : (data === 0 ? '支出' : data);
						}
					},
					{
						className: 'text-right',
						data: "amount",
						render: function(data) {
							return data != null ? data.toFixed(2) : data;
						}
					},
					{
						className: 'text-right',
						data: "balanceAfter",
						render: function(data) {
							return data != null ? data.toFixed(2) : data;
						}
					},
					{
						className: 'c-wrap',
						data: "note"
					},
					{data: "operName"}
				]
			});
			
			$('#form_search_btn').click(function() {
				dt.api().ajax.reload();
			});
		});
		</script>

	</tiles:putAttribute>

</tiles:insertDefinition>