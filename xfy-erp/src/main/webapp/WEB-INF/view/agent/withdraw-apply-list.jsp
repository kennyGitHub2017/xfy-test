
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>

<c:set scope="request" var="sysModule" value="agent" />
<c:set scope="request" var="sysPage" value="withdraw_apply_agent" />

<c:set var="type" value="${param.status == 0 ? '未审核' : param.status == 1?'已审核':'已提现' }"></c:set>
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">我的提款申请${type }</tiles:putAttribute>

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
							<i class="fa fa-cogs font-green-sharp"></i> <span class="caption-subject font-green-sharp bold uppercase">我的提款申请</span>
						</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
						</div>
					</div>
					<div class="portlet-body">
						<div class="table-toolbar">
							<div class="row">
								<div class="col-md-6">
									<div class="btn-group">
											<a class=" btn green" href="javascript:void(0)" id="draw_apply_btn">
												申请提现 <i class="fa fa-plus"></i>
											</a>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="btn-group">
										
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-3">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">账号总余额</label>
										<div class="col-md-8">
											${agentInfo.deposit }￥
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">可提取金额</label>
										<div class="col-md-8">
											${agentInfo.deposit>agentInfo.bond?agentInfo.deposit-agentInfo.bond-applyAmount:0}￥
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">保证金</label>
										<div class="col-md-8">
											${agentInfo.bond }￥
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">近1个月收入</label>
										<div class="col-md-8">
											${inCome }￥
										</div>
									</div>
								</div>
							</div>
						</div>
						<ul class="nav nav-tabs">
							<li class="${param.status == 0? 'active' : '' }"><a href="${pageContext.request.contextPath }/withdraw/my-apply?status=0">未审核</a></li>
							<li class="${param.status == 1? 'active' : '' }"><a href="${pageContext.request.contextPath }/withdraw/my-apply?status=1">已审核</a></li>
							<li class="${param.status == 2? 'active' : '' }"><a href="${pageContext.request.contextPath }/withdraw/my-apply?status=2">已付款</a></li>
						</ul>
					
						<form id="search_order" class="form-horizontal" search="1">
							<input type="hidden" name="status" value="${param.status }" />
							<input type="hidden" name="userId" value="${sessionScope.sessionUser.userId }" />
							<!-- search start -->
							
							<div class="row">
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
								<div class="col-md-3">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">提款金额</label>
										<div class="col-md-8">
											<div class="input-group ">
												<input id="slider_2_input_start" type="text" maxlength="15" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" class="form-control" name="amountFrom"> <span class="input-group-addon"> to </span> <input id="slider_2_input_end" type="text" maxlength="15" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" class="form-control" name="amountTo">
											</div> 
										</div>
									</div>
								</div>
							</div>
							
							
							<div class="row">
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
								<div class="col-md-7">
									<div class="form-group">
										<label class="control-label col-md-4"></label>
										<div class="col-md-8">
											<button type="button" id="searchbtn" class="btn green">
												<spring:message code="g.label.search" />
											</button>
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
									<th>收款人</th>
									<th>申请提取金额</th>
									<th>开户银行</th>
									<th>卡号</th>
									<th>备注</th>
									<th>申请时间</th>
									<c:if test="${param.status == 1 }">
									<th>审核时间</th>
									<th>审核人</th>
									</c:if>
									<c:if test="${param.status == 2 }">
									<th>付款时间</th>
									<th>付款银行</th>
									<th>付款卡号</th>
									</c:if>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
		<%@include file="/WEB-INF/view/include/modal-ajax.jsp" %>
		
		<!-- form -->
		<div class="modal fade" id="withdrawapplywindow" role="dialog"
  			 aria-labelledby="myModalLabel" aria-hidden="true">
  		   <form id="data_form"  action="${pageContext.request.contextPath }/withdraw/apply" method="post" enctype="multipart/form-data">
  		   <input type="hidden" id="aviablewithdraw" name="aviableWithdraw"  value="${agentInfo.deposit-100>=agentInfo.bond?agentInfo.deposit-agentInfo.bond-applyAmount:0}" />
  		   <input type="hidden" name="status" value="0" />
  		   <input type="hidden" name="token" value="${token}" />
  		   <input type="hidden" name="applyUser" value="${sessionUser.userId }" >
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal"
		               aria-hidden="true">×
		            </button>
					            <h4 class="modal-title" id="myModalLabel">
					               		提现申请操作		            
					            </h4>
					         </div>
					         <div class="modal-body">
					         	<div style="color:red">
									<h4>提款须知：</h4>
									1. 您的保证金在您注销账号前不能提取。<br>
									
									2. 您所有的收益可提取金额必须大于100￥。<br>
									
									3. 提取的金额到账时间，根据不同银行的规则将会有3-7个工作日不同时间点的到账时间，请确定您输入的资料的正确性。<br>
									
									4.带*为必填项
								</div>
								<br>
								<table border="0"  style="width: 100%">
									<tr>
										<td>*收款人姓名</td>
										<td width="200px"><input type="text"  value=""  name="payeeName" id="payeeName"></td>
										<td></td>
									</tr>
									<tr>
										<td>*开户银行</td>
										<td width="200px">
											<input type="text" value=""  name="payeeBank" id="payeeBank">
										 </td>
										 <td></td>
									</tr>
									<tr>
										<td>*卡号</td>
										<td width="200px">
											<input type="text" value=""  size=35 name="payeeCardno" id="payeeCardno">
										 </td>
										 <td></td>
									</tr>
									<tr>
										<td>*提现金额 </td>
										<td width="200px"><input type="text" value="" name="amount">
										</td>
										<td></td>
									</tr>
									<tr>
										<td>备注</td>
										<td colspan="2">
											<textarea rows="7" cols="70" name="note"></textarea>
										</td>
									</tr>
								</table>
		         			</div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default"
					               data-dismiss="modal">关闭
					            </button>
					            <button type="submit" id="save_btn" class="btn btn-primary">申请</button>
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
		
$(function(){
	initFun();
	$("#draw_apply_btn").click(function(){
		<c:if test="${ ! (agentInfo.deposit-100-applyAmount>=agentInfo.bond) }">
			alert("可提取金额>=100,方可申请提现");
			return;
		</c:if>
		$("#withdrawapplywindow").modal({keyboard: false,backdrop:'static'});
		$("#save_btn").removeAttr("disabled");
	});
	$('#data_form').validate({
		errorPlacement: function(error, element) {
			error.appendTo( element.parent("td").next("td")); 
		}, 
		errorClass : 'help-block help-block-error', // default input error message class
		focusInvalid : false, // do not focus the last invalid input
		ignore : "", // validate all fields including form hidden input
		rules : {
			'payeeName':{required : true},
			'payeeBank' : { required : true},
			'payeeCardno' : { required : true,regex:"^(([1-9]{1}[0-9]{18}|[1-9]{1}[0-9]{15}))$"},
			'amount' : { required : true,number:true,min:1,max:function(){return parseFloat($("#aviablewithdraw").val());} }
		},
		messages : {
			payeeCardno : { regex : "请输入正确的16位或19位银行卡号!"}
		},
		highlight : function(element) { // hightlight error inputs
			$(element).parent("td").next("td").addClass('has-error'); // set error class to the control group
		},
		unhighlight : function(element) { // revert the change done by hightlight
			$(element).parent("td").next("td").removeClass('has-error'); // set error class to the control group
		},
		success : function(label) {
			label.parent("td").next("td").removeClass('has-error'); // set success class to the control group
		}
	});
});


$('.date-picker').datepicker({
	orientation: "right",
	autoclose: true
});

$("#searchbtn").click(function(){
	$('#withdraw_table').dataTable().fnDestroy();
	initFun();
});


$("#save_btn").click(function(){
	$("#data_form").submit();
	$(this).attr("disabled","disabled");
});

function initFun(){
	xfy.initDataTable();
	$("#withdraw_table").dataTable({
		serverSide: true,
		ordering:true,
		ajax: {
			url: "${pageContext.request.contextPath }/withdraw/pageJson",
					
			data: function (d) {
				var data = $("#search_order").serializeObject();
				d.params = data;
				return JSON.stringify( d );
			},
			type: 'post',
			contentType:"application/json;charset=UTF-8"
		},
		columns: [
			{data: "payeeName","sortable":false},
			{data: "amount","sortable":false},
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
			<c:if test="${param.status == 2 }">
			{data: "payedTime","sortable":false},
			{data: "payedBank","sortable":false},
			{data: "payedCardno","sortable":false},
			</c:if>
		],

	});
}

</script>
	</tiles:putAttribute>
</tiles:insertDefinition>