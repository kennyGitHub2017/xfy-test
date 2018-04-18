<%--
卖家保证金流水日志
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="seller_login_log" />

<c:set var="pavaTitle" value="卖家登录日志" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">${pavaTitle }</tiles:putAttribute>

	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/select2/select2.css"/>


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
							<form id="search_form" class="form-horizontal" role="form" search="1">
								<input type="hidden" name="sellerId" value="${sellerId }" />
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label  class="col-md-4 control-label">卖家</label>
											<div class="col-md-8">
												<!-- <select class="form-control input-sm c-seller-picker" name="sellerId">
													<option value="" selected>请选择....</option>
												</select> -->
												<input type="text" name="sellerId" class="form-control input-sm c-seller-picker" value="" data-placeholder="请选择..." />
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label class="col-md-4 control-label">开始日期</label>
											<div class="col-md-8">
												<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
													<input name="loginTimeStart" class="form-control input-sm" /> <span class="input-group-btn">
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
													<input name="loginTimeEnd" class="form-control input-sm" /> <span class="input-group-btn">
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
											<button type="button" id="clearbtn" class="btn blue">清空</button>
										</div>
									</div>
								</div>
							</form>
						</div>

						<table class="table table-bordered table-hover table-condensed" id="users_table">
							<thead>
								<tr>
									<th>卖家</th>
									<th>登录名</th>
									<th>登录时间</th>
									<th>登录IP</th>
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
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/select2/select2.min.js" type="text/javascript"></script>


		<script>

		$(function(){
			xfy.initDataTable();
			var dt = $("#users_table").dataTable({
				searching: false,
				serverSide: true,
				ajax: {
					url: "${pageContext.request.contextPath }/user-login-log/page.json",
					data: function (d) {
						d.params = $('#search_form').serializeObject();
						return JSON.stringify( d );
					},
					type: 'post',
					contentType:"application/json;charset=UTF-8"
				},
				columns: [
					{data: "sellerContacts" },
					{data: "username" },
					{data: "loginTime"},
					{data: "loginIp"}
				]
			});
			
			$('#form_search_btn').click(function() {
				dt.api().ajax.reload();
			});
			
			$(".c-seller-picker").select2({
				minimumInputLength: 1,
				ajax: {
					delay: 1000,
					url: '${pageContext.request.contextPath }/seller/page-json',
					data: function (params) {
						var data = {
							draw: 1,
							length: 10,
							start: 0,
							search: {value: params}
						};
						return JSON.stringify( data );
					},
					cache: true,
					params: {
						type: 'post',
						contentType:"application/json;charset=UTF-8"
					},
					results: function (result) {
						var data = [];
						for (var i = 0; i < result.data.length; i++) {
							var item = result.data[i];
							data.push({id: item.id, text: item.contacts});
						}
						
						return {
							results: data
						};
					}
				},
				allowClear: true
			});
		});
		</script>

	</tiles:putAttribute>

</tiles:insertDefinition>