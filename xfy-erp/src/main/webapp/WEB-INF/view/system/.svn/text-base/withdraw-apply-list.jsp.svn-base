
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>

<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="withdraw_apply_system" />

<c:set var="type" value="${param.status == 0 ? '未审核' : param.status == 1?'已审核':'已付款' }"></c:set>
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">代理商提款申请${type }</tiles:putAttribute>

	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/admin/pages/css/todo.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css" />
	</tiles:putAttribute>

	<tiles:putAttribute name="page-content">
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-cogs font-green-sharp"></i> <span class="caption-subject font-green-sharp bold uppercase">代理商提款申请</span>
						</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
						</div>
					</div>
					<div class="portlet-body">
						<ul class="nav nav-tabs">
							<li class="${param.status == 0? 'active' : '' }"><a href="${pageContext.request.contextPath }/withdraw/?status=0">未审核</a></li>
							<li class="${param.status == 1? 'active' : '' }"><a href="${pageContext.request.contextPath }/withdraw/?status=1">已审核</a></li>
							<li class="${param.status == 2? 'active' : '' }"><a href="${pageContext.request.contextPath }/withdraw/?status=2">已付款</a></li>
						</ul>
					
						<form id="search_form" method="post" class="form-horizontal" search="1">
							<input type="hidden" name="status" value="${param.status }" />
							<!-- search start -->
							
							<div class="row">
								<div class="col-md-3">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">代理商</label>
										<div class="col-md-8">
											<input class="form-control" name="agentName" />
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">收款人</label>
										<div class="col-md-8">
											<input class="form-control" name="payeeName" />
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">开户行</label>
										<div class="col-md-8">
											<input class="form-control" name="payeeBank" />
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">卡号</label>
										<div class="col-md-8">
											<input class="form-control" name="payeeCardNo" />
										</div>
									</div>
								</div>
							</div>
							
							
							<div class="row">
								<div class="col-md-3">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">提款金额</label>
										<div class="col-md-8">
											<div class="input-group ">
												<input id="slider_2_input_start" type="text" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" class="form-control" name="amountFrom"> <span class="input-group-addon"> to </span> <input id="slider_2_input_end" type="text" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" class="form-control" name="amountTo">
											</div> 
										</div>
									</div>
								</div>
								
								<div class="col-md-5">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-2">申请日期</label>
										<div class="col-md-10">
											<div class="col-md-6">
												<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
													<input name="applyTimeBegin" class="form-control input-sm" /> <span class="input-group-btn">
														<button class="btn default btn-sm" type="button">
															<i class="fa fa-calendar"></i>
														</button>
													</span>
												</div>
											</div>
											<div class="col-md-6">
												<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
														<input name="applyTimeEnd" class="form-control input-sm" /> <span class="input-group-btn">
															<button class="btn default btn-sm" type="button">
																<i class="fa fa-calendar"></i>
															</button>
														</span>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>	
							
							<c:if test="${param.status==2  or param.status==1}">
								<div class="row">
									<c:if test="${param.status==2}" >
										<div class="col-md-3">
											<div class="form-group form-group-sm">
												<label class="control-label col-md-4">付款银行</label>
												<div class="col-md-8">
													<input class="form-control" name="payedBank" />
												</div>
											</div>
										</div>
									</c:if>
									<c:if test="${param.status==2}" >
										<div class="col-md-3">
											<div class="form-group form-group-sm">
												<label class="control-label col-md-4">付款卡号</label>
												<div class="col-md-8">
													<input class="form-control" name="payedCardNo" />
												</div>
											</div>
										</div>
									</c:if>
									<div class="col-md-5">
											<div class="form-group form-group-sm">
												<label class="control-label col-md-2">付款时间</label>
												<div class="col-md-10">
													<div class="col-md-6">
														<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
															<input name="payedTimeBegin" class="form-control input-sm" /> <span class="input-group-btn">
																<button class="btn default btn-sm" type="button">
																	<i class="fa fa-calendar"></i>
																</button>
															</span>
														</div>
													</div>
													<div class="col-md-6">
														<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
																<input name="payedTimeEnd" class="form-control input-sm" /> <span class="input-group-btn">
																	<button class="btn default btn-sm" type="button">
																		<i class="fa fa-calendar"></i>
																	</button>
																</span>
														</div>
													</div>
												</div>
											</div>
									</div>
								</div>	
							</c:if>
							
							<div class="row">
								<div class="col-md-6"></div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4"></label>
										<div class="col-md-8">
											<button type="button" id="searchbtn" class="btn green">
												<spring:message code="g.label.search" />
											</button>
											<button type="button" id="export" class="btn green">导出</button>
											<button type="button" id="clearbtn" class="btn blue">清空</button>
										</div>
									</div>
								</div>
							</div>
								
						</form>
						
						<%@include file="/WEB-INF/view/include/message.jsp" %>
						<table class="table table-hover table-striped table-bordered table-condensed" id="withdraw_table">
							<thead>
								<tr>
									<th></th>
									<th>代理商</th>
									<th>保证金额度（￥）</th>
									<th>账号余额（￥）</th>
									<th>可提取金额</th>
									<th>申请提取金额</th>
									<th>收款人</th>
									<th>开户银行</th>
									<th>卡号</th>
									<th>备注</th>
									<th>申请时间</th>
									<c:if test="${param.status == 1 }">
									<th>审核时间</th>
									<th>审核人</th>
									</c:if>
									<c:if test="${param.status == 1 or  param.status == 0}">
									<th>操作</th>
									</c:if>
									<c:if test="${param.status == 2 }">
									<th>付款时间</th>
									<th>付款银行</th>
									<th>付款卡号</th>
									</c:if>
								</tr>
							</thead>
							<tfoot style="color:red;">
								<tr>
									<th colspan="5"></th>
									<th  id="total-amount-td"></th>
									<th colspan="5"></th>
									<c:if test="${param.status == 1 }">
										<th colspan="2"></th>
									</c:if>
									<c:if test="${param.status == 1 or  param.status == 0}">
										<th></th>
									</c:if>
									<c:if test="${param.status == 2 }">
										<th colspan="3"></th>
									</c:if>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
			</div>
		</div>
		<%@include file="/WEB-INF/view/include/modal-ajax.jsp" %>
		<!-- form -->
		<div class="modal fade" id="ydimport" tabindex="-1" role="dialog"
  			 aria-labelledby="myModalLabel" aria-hidden="true">
  		   <form action="${pageContext.request.contextPath }/withdraw/payForApply" onsubmit="importYdh()" method="post" enctype="multipart/form-data">
  		   <input type="hidden" name="id" id="id">
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal"
		               aria-hidden="true">×
		            </button>
					            <h4 class="modal-title" id="myModalLabel">
					               		付款操作		            
					            </h4>
					         </div>
					         <div class="modal-body">
									<div class="h4">
										<table>
											<tbody>
												<tr>
													<td>选择付款银行卡:</td>
													<td>
														<select id="bkName">
															<option value="">请选择</option>
															<c:forEach items="${cards }" var="item">
																<option value="${item.id }">${item.bankCardName }</option>
															</c:forEach>
														</select>
													</td>
												</tr>
												<tr>
													<td colspan="2"></td>
												</tr>
												<tr>
													<td>银行卡号:</td>
													<td>
														<input type="hidden" name="bankName" id="bankName" >
														<input type="text" name="cardNo" id="cardNo" readonly="readonly" size="30">
													</td>
												</tr>
											</tbody>
										</table>	
									</div>	
		         			</div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default"
					               data-dismiss="modal">关闭
					            </button>
					            <button type="submit" class="btn btn-primary" id="ydhimpbtn">确定</button>
		         </div>
		      </div><!-- /.modal-content -->
		   </div><!-- /.modal-dialog -->
		   </form><!-- /.form -->
		</div><!-- /.modal -->
	</tiles:putAttribute>
	

	<%-- 页面级别的 JS --%>
	<tiles:putAttribute name="js-page">
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>

		<script>
		
		$("#export").click(function(){
			var path = '${pageContext.request.contextPath}/withdraw/export';
			var ids = xfy.getCheckedValues('id');
			if(ids == ''){
				$.ajax({
					url:path,
					type:'post',
					data: $("#search_form").serializeObject(),
					async : false,
					success:function(data){
						if(data == 'error'){
							alert("导出数量不能大于3W条,请根据条件分批导出");
						}else{
							$('#search_form').attr("action", path).submit();
						}
					}
				});
				
			}else{
				 xfy.requestByForm({
					method: 'post',
					action: path,
					data: {'ids' : ids,'status':'${param.status }'}
				});
			}
		});

$(function(){
	initFun();
});


$('.date-picker').datepicker({
	orientation: "right",
	autoclose: true
});

$("#searchbtn").click(function(){
	$('#withdraw_table').dataTable().fnDestroy();
	initFun();
});


function initFun(){
	xfy.initDataTable();
	$("#withdraw_table").dataTable({
		serverSide: true,
		ordering:true,
		ajax: {
			url: "${pageContext.request.contextPath }/withdraw/pageJson",
					
			data: function (d) {
				var data = $("#search_form").serializeObject();
				d.params = data;
				return JSON.stringify( d );
			},
			type: 'post',
			contentType:"application/json;charset=UTF-8"
		},
		columns: [
			{data: function(data){
				return "<input type='checkbox' name='id' value='{0}'>".format(data.id);
			},"sortable":false},      
			{data: "applyUserName","sortable":false},
			{data: "bond","sortable":false},
			{data: "deposit","sortable":false},
			{data: "aviableWithdraw","sortable":false},
			{data: "amount","sortable":false},
			{data: "payeeName","sortable":false},
			{data: "payeeBank","sortable":false},
			{data: "payeeCardno","sortable":false},
			{data: "note","sortable":false},
			{data: function(obj){
				return new Date(obj.createdTime).format("yyyy-MM-dd");
			},"sortable":false},
			<c:if test="${param.status == 1 }">
			{data: function(obj){
				return new Date(obj.auditTime).format("yyyy-MM-dd");
			},"sortable":false},
			{data: "auditUserName","sortable":false},
			</c:if>
			<c:if test="${param.status == 1 or  param.status == 0}">
			{
				data: function(row) {
					var html = '';
					<c:if test="${param.status == 0 }">
					<x:power type="function" code="withdraw_audit">
					html += xfy.html.confirmLink.format('${pageContext.request.contextPath }/withdraw/audit?id=' + row.id, '审核');
					</x:power>
					</c:if>
					<c:if test="${param.status == 1 }">
					<x:power type="function" code="withdraw_reverseAudit">
					html += xfy.html.confirmLink.format('${pageContext.request.contextPath }/withdraw/reverseAudit?id=' + row.id, '反审核');
					</x:power>
					<x:power type="function" code="withdraw_confirm">
					html += xfy.html.confirmLink.format('javascript:showFukuanWindow({0})'.format(row.id), '确认付款');
					</x:power>
					</c:if>
					return html;
				},"sortable":false
			},
			</c:if>
			<c:if test="${param.status == 2 }">
			{data: function(obj){
				return new Date(obj.payedTime).format("yyyy-MM-dd");
			},"sortable":false},
			{data: "payedBank","sortable":false},
			{data: "payedCardno","sortable":false},
			</c:if>
		],
		drawCallback: function(settings) {
			var data = this.api().rows( {page:'current'} ).data();
			if (data.length<1){
				return;
			}
			var total = 0.0;
			for (var i = 0; i < data.length; i++) {
				var row = data[i];
				total += (row.amount ? row.amount : 0);
			}
			$('#total-amount-td').text("汇总:" + total.toFixed(2));
		}
	});
}


function showFukuanWindow(id){
	$("#id").val(id);
	$("#ydimport").modal({keyboard: false,backdrop:'static'});
}

$("#bkName").change(function(){
	var selectedValue = $(this).val();
	<c:forEach items="${cards }" var="item">
		if ('${item.id}'==selectedValue){
			$("#cardNo").val("${item.cardNumber}");
			$("#bankName").val("${item.bankCardName}");
		}
	</c:forEach>
});
</script>
	</tiles:putAttribute>
</tiles:insertDefinition>