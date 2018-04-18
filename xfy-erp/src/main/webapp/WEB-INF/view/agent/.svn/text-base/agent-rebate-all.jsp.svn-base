<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>

<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="agent_rebate_all" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">资金流水(SYSTEM)</tiles:putAttribute>
	
	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-ui/jquery-ui.min.css"/>

	</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
			<div class="row">
				<div class="col-md-12">
						<div class="portlet light">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-cogs font-green-sharp"></i>
									<span class="caption-subject font-green-sharp bold uppercase">资金流水</span>
								</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"></a>
								</div>
							</div>					
							<div class="portlet-body">
								<%@include file="../include/message.jsp" %>
								
								<form id="searchform" class="form-horizontal" role="form" search="1" method="post">
								
									<div class="row">
										<div class="col-md-3">
											<div class="form-group form-group-sm">
												<label class="col-md-2 control-label">代理商</label>
												<div class="col-md-10">
													<select class="form-control input-sm" name="userId">
														
														<option value="">请选择....</option>
														<c:forEach var="item" items="${agentList}">
														<option value="${item.userId}">${item.name}</option>
														</c:forEach>
														
													</select>
												</div>
											</div>
										</div>
										
										<div class="col-md-3">
											<div class="form-group form-group-sm">
												<label class="col-md-2 control-label">卖家</label>
												<div class="col-md-10">
													<select class="form-control input-sm" name="sellerId">
														<option value="">请选择....</option>
														<c:forEach items="${sellers}" var="item">
														<option value="${item.id}">${item.contacts}</option>
														</c:forEach>
														
													</select>
												</div>
											</div>
										</div>
										
										<div class="col-md-3">
											<div class="form-group form-group-sm">
												<label class="col-md-2 control-label">账号</label>
												<div class="col-md-8">
												<input type="text" class="col-md-3 form-control"  name="accountName" />
												</div>
											</div>
										</div>
										
										<div class="col-md-3">
											<div class="form-group form-group-sm">
												<label class="col-md-2 control-label">订单号</label>
												<div class="col-md-8">
													<input type="text" class="col-md-3 form-control"  name="orderId" />
												</div>
											</div>
										</div>
									</div>
									
									<div class="row">
									
									<div class="col-md-5">
											<div class="form-group form-group-sm">
												<label class="control-label col-md-2">创建日期</label>
												<div class="col-md-10">
													<div class="col-md-6">
														<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
															<input name="createTimeBegin" class="form-control input-sm" /> <span class="input-group-btn">
																<button class="btn default btn-sm" type="button">
																	<i class="fa fa-calendar"></i>
																</button>
															</span>
														</div>
													</div>
													<div class="col-md-6">
														<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
																<input name="createTimeEnd" class="form-control input-sm" /> <span class="input-group-btn">
																	<button class="btn default btn-sm" type="button">
																		<i class="fa fa-calendar"></i>
																	</button>
																</span>
														</div>
													</div>
												</div>
											</div>
										</div>
										
										
														<div class="col-md-3">
											<div class="form-group form-group-sm">
												<label for="dict_code" class="col-md-2 control-label">类型</label>
												<div class="col-md-10">
													<select name="type" class="form-control">
														<option value="">请选择</option>
														<option value="0">收入-订单交易成功</option>
														<option value="1">支出-订单取消返点退回</option>
														<option value="2">支出-提款</option>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label col-md-2"></label>
												<div class="col-md-10">
													<button type="button" id="searchbtn" class="btn green">
														<spring:message code="g.label.search" />
													</button>
												</div>
											</div>
										</div>
									
									
									</div>
									
								</form>
								
								
								
								<div class="">
									<table class="table table-hover table-striped table-bordered table-condensed" id="table-list">
									<thead>
									<tr>
									<th>代理商</th>
										<th>
											卖家名称
										</th>
										<th>
											卖家账号
										</th>
										<th>
											订单号
										</th>
										<th>
											所属平台
										</th>
										<th>
											流水类型
										</th>
										<th>
											流水金额（￥）
										</th>
										<th>
											余额（￥）
										</th>
										<th>
											创建日期
										</th>
									</tr>
									</thead>
									<tbody>
									</tbody>
									</table>
								</div>
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
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/select2/select2.min.js" type="text/javascript"></script>
		<script>
		
		//datepicker show
		$('.date-picker').datepicker({
			orientation: "right",
			autoclose: true
		});
		
			var tb ;
			$("#searchbtn").click(function(){
				tb.fnDestroy();
				loadDate();
			});
			
			$(function(){
				xfy.initDataTable();
				loadDate();
			});
			
			//ajax load tabledata
			function loadDate(){
				tb = $("#table-list").dataTable({
					searching: false,
					serverSide: true,
					ordering:false,
					ajax: {
						url:"${pageContext.request.contextPath }/agent-rebate/pageJson",
						contentType:"application/json;charset=UTF-8",	
						type: 'post',
						data: function (d) {
						var jsondata = $("#searchform").serializeObject();  //转化为json
						d.params = jsondata;
							return JSON.stringify( d );
						}
					},
					columns: [
						{data:"userName"},
						{data:"contacts"},
						{data:"accountName"},
					    {data:"orderId"},      
						{data:"platForm"},
						{data: function(data){
							if (data.type==0){
								return "收入-订单交易成功";
							}else if(data.type==1){
								return "支出-订单取消返点退回";
							}else if (data.type==2){
								return "支出-提款";
							}
						}},
						{data: "amount"},
						{data:"currentDeposit"},
						{data: function(data){
							return new Date(data.createdTime).format("yyyy-MM-dd HH:mm:ss");
						}}
					]
				});
			}
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>