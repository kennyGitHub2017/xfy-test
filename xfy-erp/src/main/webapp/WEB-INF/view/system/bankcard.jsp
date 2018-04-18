<%--
	银行卡管理
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="bankcard" />

<c:set var="bankcardTitle" value="银行卡管理" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">${bankcardTitle }</tiles:putAttribute>

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
							<i class="icon-note theme-font"></i> <span class="caption-subject theme-font bold uppercase">${bankcardTitle }</span>
						</div>
					</div>
					<div class="portlet-body">
					<%@include file="../include/message.jsp" %>
						<div class="table-toolbar">
							<form id="search_form" search="1" class="form-horizontal" role="form" method="post">
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label  class="col-md-4 control-label">银行卡号</label>
											 <div class="col-md-8">
												<input type="text" name="cardNumber" class="form-control c-supplier-picker input-sm" value=""  />
											 </div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<label  class="col-md-4 control-label">银行卡名称</label>
											 <div class="col-md-8">
												<input type="text" name="bankCardName" class="form-control c-supplier-picker input-sm" value=""  />
											 </div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label  class="col-md-2 control-label">添加时间</label>
											<div class="col-md-5">
													<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
														<input name="createdTimeFrom" class="form-control input-sm" /> <span class="input-group-btn">
															<button class="btn default btn-sm" type="button">
																<i class="fa fa-calendar"></i>
															</button>
														</span>
													</div>
											</div>
											<div class="col-md-5">
												<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
														<input name="createdTimeTo" class="form-control input-sm" /> <span class="input-group-btn">
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
											<label  class="col-md-4 control-label">支行信息</label>
											 <div class="col-md-8">
												<input type="text" name="branchInfo" class="form-control c-supplier-picker input-sm" value=""  />
											 </div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label  class="col-md-2 control-label">修改时间</label>
											<div class="col-md-5">
													<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
														<input name="lastUpdatedTimeFrom" class="form-control input-sm" /> <span class="input-group-btn">
															<button class="btn default btn-sm" type="button">
																<i class="fa fa-calendar"></i>
															</button>
														</span>
													</div>
											</div>
											<div class="col-md-5">
												<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
														<input name="lastUpdatedTimeTo" class="form-control input-sm" /> <span class="input-group-btn">
															<button class="btn default btn-sm" type="button">
																<i class="fa fa-calendar"></i>
															</button>
														</span>
												</div>
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
										</div>
									</div>
								</div>
							</form>
						</div>
						<div class="table-toolbar">
								<div class="row">
									<div class="col-md-6">
										<div class="btn-group">
											<a class=" btn green" href="${pageContext.request.contextPath }/bankcard/form?action=add" data-target="#ajax" data-toggle="modal" data-backdrop="static">
												<spring:message code="g.label.add"/> <i class="fa fa-plus"></i>
											</a>
										</div>
									</div>
								</div>
							</div>
						<table class="table table-bordered table-hover" id="bankcard_table">
							<thead>
								<tr>
									<th>银行卡名称</th>
									<th>支行信息</th>
									<th>银行卡号</th>
									<th>添加时间</th>
									<th>修改时间</th>
									<th>删除时间</th>
									<th>备注</th>
									<th class="text-center">操作</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	<%@include file="/WEB-INF/view/include/modal-ajax.jsp" %>
	</tiles:putAttribute>

	<%-- 页面级别的 JS --%>
	<tiles:putAttribute name="js-page">
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>


		<script>
		var dt;
		
		function loadPageData(){
			dt = $("#bankcard_table").dataTable({
				serverSide: true,
				ajax: {
					url: "${pageContext.request.contextPath }/bankcard/bankcardPageJson",
					data: function (d) {
						d.params = $('#search_form').serializeObject();
						return JSON.stringify( d );
					},
					type: 'post',
					contentType:"application/json;charset=UTF-8"
				},
				columns: [
					{className: 'text-center',data: "bankCardName"},
					{className: 'text-center',data: "branchInfo"},  
					{className: 'text-center',data: "cardNumber"}, 
					{className: 'text-center',data: "createdTime"},  
					{className: 'text-center',data: "lastUpdatedTime"},  
					{className: 'text-center',data: "deletedTime"},  
					{className: 'text-center',data: "note"},
					{className: 'text-center',
						data: function(row) {
							var html = "<a class='btn default btn-xs purple' href='${pageContext.request.contextPath }/bankcard/form?action=update&id=" + row.id + "' data-target='#ajax' data-toggle='modal' data-backdrop='static'>修改</a>"; 
							var html2 = "<a class='btn default btn-xs purple c-del-btns' href='${pageContext.request.contextPath }/bankcard/delete?id=" + row.id + "'>删除</a>";
							return html + html2;
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
		
		
		
		</script>

	</tiles:putAttribute>

</tiles:insertDefinition>