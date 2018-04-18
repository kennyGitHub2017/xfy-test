<%--
代理商申请审核
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>

<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="agent_application" />

<c:set var="agentAppTitle" value="代理商申请审核" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">${agentAppTitle }</tiles:putAttribute>
	
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
								<span class="caption-subject theme-font bold uppercase">${agentAppTitle }</span>
							</div>
						</div>
						<div class="portlet-body">
							<ul class="nav nav-tabs">
								<li class="${status == 0 ? 'active' : '' }">
									<a href="${pageContext.request.contextPath }/user/agent-application?status=0">未审核</a>
								</li>
								<li class="${status == 1 ? 'active' : '' }">
									<a href="${pageContext.request.contextPath }/user/agent-application?status=1">已审核</a>
								</li>
								<li class="${status == 2 ? 'active' : '' }">
									<a href="${pageContext.request.contextPath }/user/agent-application?status=2">审核未通过</a>
								</li>
							</ul>
						
							<%@include file="/WEB-INF/view/include/message.jsp" %>
							
						<form id="agent_search" search="1" class="form-horizontal" method="post">
						<input type="hidden" name="status" value="${status}">
							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
									<label class="control-label col-md-4">代理商</label>
											<div class="col-md-8">
												<input type="text" class="form-control" name="name">
											</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
									<label class="control-label col-md-4">手机号</label>
											<div class="col-md-8">
												<input type="text" class="form-control" name="mobile">
											</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label  class="col-md-2 control-label">申请时间</label>
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
								<div class="col-md-2">
									<div class="form-group">
									<label class="control-label col-md-3"></label>
									<button type="button" id="searchbtn" class="btn green">
										<spring:message code="g.label.search" />
									</button>
									<button type="button" id="clearbtn" class="btn blue btn-sm">清空</button>
									</div>
								</div>
							</div>
						</form>
						
							<table class="table table-striped table-bordered table-hover" id="agent_table">
							<thead>
							<tr>
								<th>代理商</th>
								<th>邮箱</th>
								<th>手机</th>
								<th>地址</th>
								<th>申请时间</th>
								<th>是否卖家</th>
								<c:if test="${status != 0}">
								<th>${status == 1 ? "卖家数量" : (status == 2 ? '不通过原因' : '') }</th>
								</c:if>
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
	$('#agent_table').dataTable().fnDestroy();
	initFun();
});

$(function(){
	initFun();
});
	
	
function initFun(){
	xfy.initDataTable();
	dt = $("#agent_table").dataTable({
		searching: true,
		serverSide: true,
		ajax: {
			url: "${pageContext.request.contextPath }/user/agent-page-json",
			data: function (d) {
				var jsondata = $("#agent_search").serializeObject();  //转化为json
				d.params = jsondata;
				return JSON.stringify( d );
			},
			type: 'post',
			contentType:"application/json;charset=UTF-8"
		},
		columns: [
			{data: "name"},
			{data: "email"},
			{data: "mobile"},
			{data: "address"},
			{data: "createdTime"},
			{data: function(row) { return row.type === 2 ? '是' : (row.sellerId > 1 ? '是' : '否'); } },
			<c:if test="${status == 1}">
				{data: "count"},
			</c:if>
			<c:if test="${status == 2}">
				{data: "note"},
			</c:if>
			{
				className: 'text-center',
				data: function(row) {
					var html = xfy.html.editLinkAjaxModal.format('${pageContext.request.contextPath }/user/agent-view?userId=' + row.userId, '<spring:message code="g.label.view"/>');
					<x:power type="function" code="agent_approve">
					html += xfy.html.editLinkAjaxModal.format('${pageContext.request.contextPath }/user/agent-approve-page?userId=' + row.userId, '<spring:message code="g.label.approve"/>');
					</x:power>
					
					<x:power type="function" code="agent_delete">
					if (row.status != 1) {
						html += xfy.html.deleteLink.format('${pageContext.request.contextPath }/user/agent-delete?userId=' + row.userId, '<spring:message code="g.label.delete"/>');
					}
					</x:power>
				
					<x:power type="function" code="agent_lock">
					if (row.locked == 1) {
						html += xfy.html.confirmLink.format('${pageContext.request.contextPath }/user/agent-unlock?userId=' + row.userId , '解锁');
					} else {
						html += xfy.html.confirmLink2.format('${pageContext.request.contextPath }/user/agent-lock?userId=' + row.userId , '锁定');
					}
					</x:power>
					
					return html;
				}
			}
		]
	});
	}
</script>

	</tiles:putAttribute>

</tiles:insertDefinition>