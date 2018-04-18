<%--
代理商保证金修改
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="agent_deposit" />

<c:set var="pavaTitle" value="代理商保证金修改" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">代理商保证金修改</tiles:putAttribute>

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
							
						 		<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label  class="col-md-4 control-label">代理商</label>
											<div class="col-md-8">
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
											<div class="form-group">
											<label class="col-md-4 control-label">手机号</label>
												<div class="col-md-8">
													<input type="text" class="form-control input-sm" name="mobile" />
												</div>
											</div>
									</div>
									
									<div class="col-md-5">
										<div class="form-group">
											<label  class="col-md-2 control-label">最后修改时间</label>
											<div class="col-md-5">
													<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
														<input name="updateTimeFrom" class="form-control input-sm" /> <span class="input-group-btn">
															<button class="btn default btn-sm" type="button">
																<i class="fa fa-calendar"></i>
															</button>
														</span>
													</div>
											</div>
											<div class="col-md-5">
												<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
														<input name="updateTimeTo" class="form-control input-sm" /> <span class="input-group-btn">
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
									<th>代理商</th>
									<th>邮箱</th>
									<th>手机</th>
									<th>保证金额度￥</th>
									<th>账户余额￥</th>
									<th>可提取金额￥</th>
									<th>成本返点率%</th>
									<th>服务费返点率%</th>
									
									<th>近一个月收益</th>
									<th>卖家数量</th>
									<th>收益订单数</th>
					<!-- 			<th>注册时间</th>
									<th>审核时间</th> -->
									<th>最后修改时间</th>
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
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>


		<script>

		$(function(){
			xfy.initDataTable();
			var dt = $("#users_table").dataTable({
				iDisplayLength:10,
				serverSide: true,
				ajax: {
					url: "${pageContext.request.contextPath }/agent-Configure/agent-configure.json",
					data: function (d) {
 					d.params = $('#search_form').serializeObject();
						return JSON.stringify( d );
						
					},
					type: 'post',
					contentType:"application/json;charset=UTF-8"
				},
				columns: [
					{className: 'text-center',data: "userName"},
					{className: 'text-center',data: "email"},
					{className: 'text-center',data: "mobile"},
					{className: 'text-center',data: "bond"},
					{className: 'text-center',data: "deposit"},
					
					{className: 'text-center',
						data:function(row){

							return (row.deposit - row.bond).toFixed(2);
						}
					},
					
					{className: 'text-center',data: "costRebateRate"},
					{className: 'text-center',data: "serviceRebateRate"},
					
					{data:function(row){
						return getSumAmount(row.userId);
					}},
					
					{data:function(row){
						return '<a href="${pageContext.request.contextPath }/agent-rebate/countSellerOrder?userId='+row.userId+'" data-target="#ajax" data-toggle="modal">'+ getSellers(row.userId) + '</a>';
					}},
					
					{data:function(row){
						return getcountOrder(row.userId);
					}},
					

					{data:function(row){
						return '<a href="${pageContext.request.contextPath }/agent-Configure/configureLog?userId='+row.userId+'" data-target="#ajax" data-toggle="modal">'+ row.lastUpdate + '</a>';
					}},
					{
						data:function(row){
							var edit = '';
							edit += '<a href="${pageContext.request.contextPath }/agent-Configure/bondEdit-page?userId='+row.userId+'"  class="btn default btn-xs purple" data-target="#ajax" data-toggle="modal"> <i class="fa fa-edit"></i>修改保证金</a>';
							edit += '<a href="${pageContext.request.contextPath }/agent-Configure/rebateEdit-page?userId='+row.userId+'"  class="btn default btn-xs purple" data-target="#ajax" data-toggle="modal"> <i class="fa fa-edit"> </i>修改返点</a>';
							return edit;
						}
					}
				]
			});
			
			$('#form_search_btn').click(function() {
				dt.api().ajax.reload();
			});
		});
		
		
		
		function exportDeposit(){
			var path = '${pageContext.request.contextPath}/';
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
		
		var urlConfig = {
				getcountOrder :'${pageContext.request.contextPath }/agent-rebate/countOrder',
				getSumAmount :'${pageContext.request.contextPath }/agent-rebate/sumAmount',
				getSellers :'${pageContext.request.contextPath }/agent-rebate/getSellers'
			}
		
		function getcountOrder(userId){
			var count = 0;
			 $.ajax({
				type : "post",
				url : urlConfig.getcountOrder,
				async:false,
				data : { 
					userId:userId
				},
				dataType : "json",
				success : function(data) {
					count = data;
				}
			});
			return count;
		}
		
		function getSumAmount (userId){
			var amount = 0;
	 		 $.ajax({
				type : "post",
				url : "${pageContext.request.contextPath }/agent-rebate/sumAmount",
				async:false,
				data : { 
					userId:userId
				},
				dataType : "json",
				success : function(data) {
					amount = data;
				}
			});
			return amount;
		}
		
		function getSellers(userId){
			var count = 0;
	 		 $.ajax({
				type : "post",
				url : "${pageContext.request.contextPath }/agent-rebate/getSellers",
				async:false,
				data : { 
					userId:userId
				},
				dataType : "json",
				success : function(data) {
					count = data;
				}
			});
			return count;
		}
		</script>

	</tiles:putAttribute>

</tiles:insertDefinition>