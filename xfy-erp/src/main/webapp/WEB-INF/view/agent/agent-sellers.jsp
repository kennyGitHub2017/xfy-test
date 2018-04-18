<%--
卖家列表
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>

<c:set scope="request" var="sysModule" value="agent" />
<c:set scope="request" var="sysPage" value="agent-sellers" />

<c:set var="pavaTitle" value="卖家管理" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">卖家管理</tiles:putAttribute>
	
		<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>


	</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
			<div class="row">
				<div class="col-md-12">
					<div class="portlet light ">
						<div class="portlet-title">
							<div class="caption caption-md">
								<i class="icon-note theme-font"></i>
								<span class="caption-subject theme-font bold uppercase">${pavaTitle }</span>
							</div>
						</div>
						<div class="portlet-body">
						
							
							<ul class="nav nav-tabs">
								<li class="${status == 1 ? 'active' : '' }">
									<a href="${pageContext.request.contextPath }/seller/agent-sellers?status=1">待审核</a>
								</li>
								<li class="${status == 2 ? 'active' : '' }">
									<a href="${pageContext.request.contextPath }/seller/agent-sellers?status=2">已审核通过</a>
								</li>
								<li class="${status == 3 ? 'active' : '' }">
									<a href="${pageContext.request.contextPath }/seller/agent-sellers?status=3">已审核不通过</a>
								</li>
								<li class="${status == 0 ? 'active' : '' }">
									<a href="${pageContext.request.contextPath }/seller/agent-sellers?status=0">待提交资料</a>
								</li>
							</ul>
						
							<%@include file="/WEB-INF/view/include/message.jsp" %>
							
							<%-- <div class="table-toolbar">
								<div class="row">
									<div class="col-md-6">
										<div class="btn-group">
											<a class=" btn green" href="${pageContext.request.contextPath }/seller/form" data-target="#ajax" data-toggle="modal" data-backdrop="static">
												<spring:message code="g.label.add"/>  <i class="fa fa-plus"></i>
											</a>
										</div>
									</div>
								</div>
							</div> --%>
							
						<form id="seller_search" search="1" class="form-horizontal" method="post">
						<input type="hidden" name="status" value="${status}">
						<input type="hidden" name="agentUserId" value="${agentUserId}" />
							<div class="row">
							
							<div class="col-md-2">
								<div class="form-group">
								<label class="control-label col-md-4">手机</label>
										<div class="col-md-8">
											<input type="text" class="form-control" name="mobile">
										</div>
								</div>
							</div>
							
							<div class="col-md-2">
								<div class="form-group">
								<label class="control-label col-md-4">联系人</label>
										<div class="col-md-8">
											<input type="text" class="form-control" name="contacts">
										</div>
								</div>
							</div>
								<div class="col-md-4">
									<div class="form-group">
										<label  class="col-md-2 control-label">注册时间</label>
										<div class="col-md-5">
												<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
													<input name="createTimeFrom" class="form-control input-sm" /> <span class="input-group-btn">
														<button class="btn default btn-sm" type="button">
															<i class="fa fa-calendar"></i>
														</button>
													</span>
												</div>
										</div>
										<div class="col-md-5">
											<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
													<input name="createTimeTo" class="form-control input-sm" /> <span class="input-group-btn">
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
										<label  class="col-md-2 control-label">状态改变时间</label>
										<div class="col-md-5">
												<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
													<input name="statusTimeFrom" class="form-control input-sm" /> <span class="input-group-btn">
														<button class="btn default btn-sm" type="button">
															<i class="fa fa-calendar"></i>
														</button>
													</span>
												</div>
										</div>
										<div class="col-md-5">
											<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
													<input name="statusTimeTo" class="form-control input-sm" /> <span class="input-group-btn">
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
								<div class="col-md-2">
									<div class="form-group">
									<label class="control-label col-md-12"></label>
									<button type="button" id="searchbtn" class="btn green">
										<spring:message code="g.label.search" />
									</button>
									</div>
								</div>
							</div>
							
						</form>
													
		
								
							<br>
							<table class="table table-striped table-bordered table-hover" id="users_table">
							<thead>
							<tr>
								<th>类型</th>
								<th>卖家ID</th>
								<th>联系人</th>
								<th>邮箱</th>
								<th>手机</th>
								<th>地址</th>
								<c:if test="${status != 0}">
								<th>${status == 1 ? "资料提交时间" : (status == 2 || status == 3 ? '审核时间' : '') }</th>
								</c:if>
								<c:if test="${status == 1}">
									<th>是否超期</th>
								</c:if>
								<c:if test="${status == 3}">
									<th>不通过原因</th>
								</c:if>
								
								<th>VIP</th>
								<th>注册时间</th>
								<th>状态改变时间</th>
								<th>操作</th>
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

$('#searchbtn').click(function(){
	$('#users_table').dataTable().fnDestroy();
	initFun();
});

$(function(){
	initFun();
});
	
	
function initFun(){
	xfy.initDataTable();
	dt = $("#users_table").dataTable({
		searching: true,
		serverSide: true,
		ajax: {
			url: "${pageContext.request.contextPath }/seller/page-json",
			data: function (d) {
				var jsondata = $("#seller_search").serializeObject();  //转化为json
				d.params = jsondata;
				/* d.params = {status: ${status}}; */
				return JSON.stringify( d );
			},
			type: 'post',
			contentType:"application/json;charset=UTF-8"
		},
		columns: [
			{data: function(row) { return row.type === 0 ? '个人' : (row.type === 1 ? '公司' : ''); } },
			{data: "id"},
			{data: "contacts"},
			{data: "email"},
			{data: "mobile"},
			{data: "address"},
			<c:if test="${status != 0}">
				{data: function(row) {
					return new Date(row.statusTime).format('yyyy-MM-dd HH:mm:ss');
				}},
			</c:if>
			<c:if test="${status == 1}">
				{
					data: function(row) {
						return (new Date().getTime() - row.statusTime > 24 * 60 * 60 * 1000) ? '是' : '否';
					},
				},
			</c:if>
			<c:if test="${status == 3}">
				{data: "statusNote"},
			</c:if>
	
			{
				data: function(row) {
					return row.vipFlag === '1' ? '是' : '否';
				}
			},
		
		
			{data: function(row) {
				return new Date(row.createdTime).format('yyyy-MM-dd HH:mm:ss');
			}},
			{data: function(row) {
				return new Date(row.statusTime).format('yyyy-MM-dd HH:mm:ss');
			}},
			
			{
				className: 'text-center',
				data: function(row) {
					var html = xfy.html.editLinkAjaxModal.format('${pageContext.request.contextPath }/seller/view?id=' + row.id, '<spring:message code="g.label.view"/>');

					html += xfy.html.editLinkAjaxModal.format('${pageContext.request.contextPath }/platform-account/account-info?sellerId=' + row.id, '查看账号');
					if(row.status === 2){
					
						if (row.locked === 1) {
							html += xfy.html.confirmLink.format('${pageContext.request.contextPath }/seller/unlock?id=' + row.id , '解锁');
						} else {
							html += xfy.html.confirmLink2.format('${pageContext.request.contextPath }/seller/lock?id=' + row.id , '锁定');
						}
					}
					return html;
				}
			}
		]
	});
	}
</script>

	</tiles:putAttribute>

</tiles:insertDefinition>