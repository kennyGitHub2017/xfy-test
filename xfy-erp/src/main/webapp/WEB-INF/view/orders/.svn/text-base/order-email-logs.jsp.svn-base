<%--
推送邮件记录
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="order-email-logs" />

<c:set var="pavaTitle" value="推送邮件记录" />
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
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label class="col-md-4 control-label">开始日期</label>
											<div class="col-md-8">
												<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
													<input name="startDate" class="form-control input-sm" /> <span class="input-group-btn">
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
													<input name="endDate" class="form-control input-sm" /> <span class="input-group-btn">
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
									<th>ID</th>
									<th>推送人</th>
									<th>接收人</th>
									<th>主题</th>
									<th>内容</th>
									<th>发送时间</th>
									<th>操作</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
		<%@include file="/WEB-INF/view/include/modal-large-scroll.jsp" %>
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
					url: "${pageContext.request.contextPath }/order-email/email-logs.json",
					data: function (d) {
						d.params = $('#search_form').serializeObject();
						return JSON.stringify( d );
					},
					type: 'post',
					contentType:"application/json;charset=UTF-8"
				},
				columns: [
					{ data: "id" },
					{ data: "creator" },
					{ 
						data: "receiverEmail",
						render: function(data) {
							var emails = data.split(',');
							if (emails.length > 1) {
								return '<span title="{0}">{1} ... ({2})</span>'.format(data, emails[0], emails.length);
							}
							
							return data;
						}
					},
					{ 
						data: "subject",
						render: function(data) {
							var maxDisplayLength = 30;
							if (data.length > maxDisplayLength) {
								return '<span title="{0}">{1}...</span>'.format(data, data.substring(0, maxDisplayLength));
							}
							
							return data;
						}
					},
					{ 
						data: "content",
						render: function(data) {
							var textContent = data.replace(/(<.*?>)/g, '');
							var maxDisplayLength = 40;
							if (textContent.length > maxDisplayLength) {
								return textContent.substring(0, maxDisplayLength) + '...';
							}
							
							return textContent;
						}
					},
					{ 
						data: "createdTime",
						render: function(data) {
							return new Date(data).format('yyyy-MM-dd HH:mm:ss');
						}
					},
					{
						data: function(row) {
							return xfy.html.editModalLarge.format('${pageContext.request.contextPath }/order-email/view?&id=' + row.id, '<spring:message code="g.label.view"/>');
						}
					}
				]
			});
			
			$('#form_search_btn').click(function() {
				dt.api().ajax.reload();
			});
			
		});
		</script>

	</tiles:putAttribute>

</tiles:insertDefinition>