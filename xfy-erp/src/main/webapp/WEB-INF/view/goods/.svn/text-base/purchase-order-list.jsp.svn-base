<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>

<c:set scope="request" var="sysModule" value="purchase" />
<c:set scope="request" var="sysPage" value="purchase_order" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">采购单</tiles:putAttribute>
	
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
									<span class="caption-subject font-green-sharp bold uppercase">采购单</span>
								</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"></a>
								</div>
							</div>					
							<div class="portlet-body">
								<%@include file="../include/message.jsp" %>
							
								<div class="table-toolbar">
									<div class="row">
										<div class="col-md-6">
											<div class="btn-group">
											<x:power type="function" code="purchase_order_add">
												<a class=" btn green" href="${pageContext.request.contextPath}/purchaseorder/form">
													<spring:message code="g.label.add"/>  <i class="fa fa-plus"></i>
												</a></x:power>
											</div>
										</div>
									</div>
								</div>
								<form:form id="searchform" modelAttribute="search" method="post" class="form-horizontal" role="form" search="1">
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-group-sm">
												<label class="col-md-2 control-label">采购单号</label>
												<div class="col-md-10">
													<input type="text" class="form-control"  name="orderNo" />
												</div>
											</div>
										</div>
										<div class="col-md-4">
												<div class="form-group form-group-sm">
													<label class="col-md-2 control-label">运单号</label>
													<div class="col-md-10">
														<input type="text" class="form-control"  name="waybillNo" id="waybillNo" />
													</div>
												</div>
										</div>
										<div class="col-md-4">
											<div class="form-group form-group-sm">
												<label class="col-md-2 control-label">产品名称</label>
												<div class="col-md-10">
													<input type="text" class="form-control"  name="goodsName" />
												</div>
											</div>
										</div>
									</div>	
									
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-group-sm">
												<label class="col-md-2 control-label">SKU</label>
												<div class="col-md-10">
													<input type="text" class="form-control"  name="goodsSku" />
												</div>
											</div>
										</div>
										
										<div class="col-md-4">
											<div class="form-group">
												<label  class="col-md-2 control-label">采购日期</label>
												<div class="col-md-5">
														<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
															<input name="purchaseDateFrom" class="form-control input-sm" /> <span class="input-group-btn">
																<button class="btn default btn-sm" type="button">
																	<i class="fa fa-calendar"></i>
																</button>
															</span>
														</div>
												</div>
												<div class="col-md-5">
													<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
															<input name="purchaseDateTo" class="form-control input-sm" /> <span class="input-group-btn">
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
												<label  class="col-md-2 control-label">交货日期</label>
												<div class="col-md-5">
														<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
															<input name="deliveryDateFrom" class="form-control input-sm" /> <span class="input-group-btn">
																<button class="btn default btn-sm" type="button">
																	<i class="fa fa-calendar"></i>
																</button>
															</span>
														</div>
												</div>
												<div class="col-md-5">
													<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
															<input name="deliveryDateTo" class="form-control input-sm" /> <span class="input-group-btn">
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
										<div class="col-md-4">
											<div class="form-group form-group-sm">
												<label for="dict_code" class="col-md-2 control-label">支付方式</label>
												<div class="col-md-10">
													<form:select path="payMethod" cssClass="form-control">
														<form:option value="">请选择</form:option>
													<form:option value="月结">月结</form:option>
													<form:option value="周结">周结</form:option>
													<form:option value="货到转账">货到转账</form:option>	
													<form:option value="快递代收">快递代收</form:option>
													<form:option value="款到发货">款到发货</form:option>	
													<form:option value="支付宝">支付宝</form:option>
													</form:select>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group form-group-sm">
													<label for="dict_code" class="col-md-2 control-label">采购单状态</label>
													<div class="col-md-10">
														<form:select path="status" cssClass="form-control">
															<option value="">请选择</option>
															<option value="1">待审核</option>
															<option value="2">已审核</option>
															<option value="5">审核已接受</option>
															<option value="-1">异常入库</option>
															<option value="3">正常关闭</option>
															<option value="4">手工结案</option>
														</form:select>
													</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group form-group-sm">
												<label for="dict_code" class="col-md-2 control-label">供应商</label>
												<div class="col-md-10">
													<!--  
													<form:select path="supplierId" cssClass="form-control">
														<option value="">请选择</option>
														<c:forEach items="${ supplyList}" var="item">
																<option  value="${item.id}">${item.companyName }
										                     	</option>
														</c:forEach>
													</form:select>
													-->
													<input type="text" class="form-control c-supplier-picker input-sm" value="" data-placeholder="请选择..." />
													<form:hidden path="supplierId" id="supplierId" />
												</div>
											</div>
										</div>
									</div>
									
									
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-group-sm">
												<label class="col-md-2 control-label">产品大类</label>
												<div class="col-md-10">
													<form:select path="firstCategory" name="firstCategory"  cssClass="form-control" id="firstCategory">
														<option value="">请选择</option>
														<c:forEach items="${ categoryList}" var="item">
																<option  value="${item.id}">${item.name }</option>
														</c:forEach>
													</form:select>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group form-group-sm">
													<label class="col-md-2 control-label">产品中类</label>
													<div class="col-md-10">
														<form:select path="secondCategory" name="secondCategory" cssClass="form-control" id="secondCategory">
														</form:select>
													</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group form-group-sm">
												<label class="col-md-2 control-label">产品小类</label>
												<div class="col-md-10">
													<form:select path="thirdCategory" name="thirdCategory" cssClass="form-control" id="thirdCategory">
													</form:select>
												</div>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="col-md-2">
											<div class="form-group form-group-sm">
												<label class="col-md-4 control-label">采购员</label>
												<div class="col-md-8">
													<input type="text" class="form-control" id="buyUserName"  name="buyUserName" />
												</div>	
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group form-group-sm">
												<label class="col-md-4 control-label">采购类型</label>
												<div class="col-md-8">
													<select name="isSample" class="form-control">
														<option value="">请选择</option>
														<option value="1">样品采购</option>
														<option value="0">采购订单</option>
													</select>
												</div>	
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group form-group-sm">
												<label class="col-md-2 control-label">付款状态</label>
												<div class="col-md-10">
													<select name="payStatus" class="form-control">
														<option value="">请选择</option>
														<option value="0">未付款</option>
														<option value="1">部分付款</option>
														<option value="2">全部付款</option>
													</select>
												</div>	
											</div>
										</div>
										 
										<div class="col-md-4">
											<div class="form-group">
												<label  class="col-md-2 control-label">付款日期</label>
												<div class="col-md-5">
														<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
															<input name="paidDateFrom" class="form-control input-sm" /> <span class="input-group-btn">
																<button class="btn default btn-sm" type="button">
																	<i class="fa fa-calendar"></i>
																</button>
															</span>
														</div>
												</div>
												<div class="col-md-5">
													<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
															<input name="paidDateTo" class="form-control input-sm" /> <span class="input-group-btn">
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
										<label  class="col-md-2 control-label"></label>
										<div class="col-md-10">
											<div class="form-group form-group-sm">
													<button type="button" id="searchbtn" class="btn green"><spring:message code="g.label.search" /></button>
													<button type="button" id="clearbtn" class="btn blue">清空</button>
													<button type="button" id="bfpaidbtn" class="btn green">部分付款</button>
													<button type="button" id="qbpaidbtn" class="btn green">全部付款</button>
													<button type="button" id="batchaudit" class="btn green">批量审核</button>
													<button type="button" id="batchprintbtn" class="btn green">批量打印</button>
													<button type="button" id="batchhtprintbtn" class="btn green">合同打印</button>
													<button type="button" id="ydimportbtn" class="btn green">运单号导入</button>
													<button type="button" id="batchexport" class="btn green">批量导出</button>
													<button type="button" id="batchdele" class="btn green">批量删除</button>
											</div>
										</div>
									</div>
										
								</form:form>
								
								<div class="">
									<table class="table table-hover table-striped table-bordered table-condensed" id="allocateorder-table">
									<thead>
									<tr>
										<th>
											<input type="button" id="selectAllBtn" value="select">
										</th>
										<th>
											操作
										</th>
										<th>
											采购单号
										</th>
										<th>
											流水号
										</th>
										<th>
											采购类型
										</th>	
										<th>
											运单号
										</th>
										<th>
											物流公司
										</th>
										<th>
											采购日期
										</th>
										
										<th>
											付款状态
										</th>
										  
										<th>
											付款日期
										</th>
										
										<th>
											供应商名称
										</th>
										<th>
											采购员
										</th>
										<th>
											要求交货日期
										</th>
										<th>
											运费
										</th>
										<th>
											支付方式
										</th>
										<th>
											请购单号
										</th>
										<th>
											请购人
										</th>
										<th>
											审核人
										</th>
										<th>
											审核日期
										</th>
										<th>
											源单类型
										</th>
										<th>
											状态
										</th>
										<th>
											处理方式
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
		
		<!-- form -->
		<div class="modal fade" id="ydimport" tabindex="-1" role="dialog"
  			 aria-labelledby="myModalLabel" aria-hidden="true">
  		   <form action="${pageContext.request.contextPath}/purchaseorder/importYdh" onsubmit="importYdh()" method="post" enctype="multipart/form-data">
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal"
		               aria-hidden="true">×
		            </button>
					            <h4 class="modal-title" id="myModalLabel">
					               		采购运单导入操作		            
					            </h4>
					         </div>
					         <div class="modal-body">
									上传文件:<input type="file" class="form-control"  name="importYdhFile" /><br>
									<div class="h4" style="color:red">
										模板范本(xls/xlsx)：
										<table>
											<thead>
												<tr>
													<td>采购单号</td>
													<td>运单号</td>
													<td>物流公司</td>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>PO-20150704-1046090002</td>
													<td>AZ-243z-323243</td>
													<td>顺丰快递</td>
												</tr>
											</tbody>
										</table>	
									</div>	
		         			</div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default"
					               data-dismiss="modal">关闭
					            </button>
					            <button type="submit" class="btn btn-primary" id="ydhimpbtn">导入</button>
		         </div>
		      </div><!-- /.modal-content -->
		   </div><!-- /.modal-dialog -->
		   </form><!-- /.form -->
		</div><!-- /.modal -->
	
	<!-- form -->
		<div class="modal fade" id="fillzlfs" tabindex="-1" role="dialog"
  			 aria-labelledby="myModalLabel" aria-hidden="true">
  		   <form action="${pageContext.request.contextPath}/purchaseorder/fillHandle" method="post" enctype="multipart/form-data">
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal"
		               aria-hidden="true">×
		            </button>
					            <h4 class="modal-title" id="myModalLabel">
					               		处理方式填写		            
					            </h4>
					         </div>
					         <div class="modal-body">
					         		<input type="hidden" name="purchaseOrderNo" id="purchaseOrderNo">
									<textarea rows="4" cols="80" name="handleDesc"></textarea>
		         			</div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default"
					               data-dismiss="modal">关闭
					            </button>
					            <button type="submit" class="btn btn-primary">确定</button>
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
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/select2/select2.min.js" type="text/javascript"></script>
		<script>
		
		
		 $(function() {
			 	var url = "${pageContext.request.contextPath }/user/getUserByDeparment.json";
			    $( "#buyUserName" ).autocomplete({
			      minLength:0,
			      focus: function (event, ui) {
			          $("#purchaseBuyer").val(ui.item.label);
			          return false;
			      },
			      source: function (request, response) {
			            $.post(url, {
			            	prefixName: request.term,depId:142679
			            },response,'json');
			      }
			    });
		});
		
		$("#batchexport").click(function(){
			var path = '${pageContext.request.contextPath}/purchaseorder/exportPurchaseOrder';
			var ids = xfy.getCheckedValues('orderId');
			if(ids == ''){
				$.ajax({
					url:path,
					type:'post',
					data: $("#searchform").serializeObject(),
					async : false,
					success:function(data){
						if(data == 'error'){
							alert("导出数量不能大于3W条,请根据条件分批导出");
						}else{
							$('#searchform').attr("action", path).submit();
						}
					}
				});
				
			}else{
				 xfy.requestByForm({
					method: 'post',
					action: path,
					data: {ids : ids}
				});
			}
		});
		
		$("#batchdele").click(function(){
			var path = '${pageContext.request.contextPath}/purchaseorder/batchremove.json';
			var ids = xfy.getCheckedValues('orderId');
			if(ids == ''){
				alert('请选择要删除的采购单');
			}else{
				if (confirm("请确认删除操作？")){
					$.ajax({
					    url:path,     
					    type:'post',
					    data:{'ids':$.trim(ids)},     
					    async : false, //默认为true 异步     
					    error:function(){
					       alert('ajax error');     
					    }, 
					    success:function(data){
					    	reloadTable();
					    }
					}); 		
				}
			}
		});
		
		function importYdh(){
			$("#ydhimpbtn").attr("disabled","disabled");
		}
		
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
			
			$("#ydimportbtn").click(function(){
				$("#ydimport").modal({keyboard: false,backdrop:'static'});
			});
			
			function fillHandleResult(purchaseOrderNo){
				$("#purchaseOrderNo").val(purchaseOrderNo);
				$("#fillzlfs").modal({keyboard: false,backdrop:'static'});
			}
				
			
			//select按钮
			$("#selectAllBtn").click(function(){
				$("#allocateorder-table input[name='orderId']").each(function(i){
					if($(this).attr("checked")){
						$(this).removeAttr("checked"); 
					}else{
						$(this).attr("checked",'true');
					}
				});
			});
			
			//部分付款按钮
			$("#bfpaidbtn").click(function(){
				var ids = xfy.getCheckedValues('orderId');
				if ($.trim(ids)==""){
					alert("请选择要付款的采购单");
					return false;
				}
				if (confirm('请确认是否继续操作: ' + $(this).text() + '？')) {
					
					$.ajax({
					    url:'${pageContext.request.contextPath}/purchaseorder/orderpaid.json',     
					    type:'post',
					    data:{'orderId':$.trim(ids),'payStatus':1},     
					    async : false, //默认为true 异步     
					    error:function(){
					       alert('ajax error');     
					    }, 
					    success:function(data){
					    	reloadTable();
					    }
					}); 
				}
			});
			
			//全部付款按钮
			$("#qbpaidbtn").click(function(){
				var ids = xfy.getCheckedValues('orderId');
				if ($.trim(ids)==""){
					alert("请选择要付款的采购单");
					return false;
				}
				if (confirm('请确认是否继续操作: ' + $(this).text() + '？')) {
					
					$.ajax({
					    url:'${pageContext.request.contextPath}/purchaseorder/orderpaid.json',     
					    type:'post',
					    data:{'orderId':$.trim(ids),'payStatus':2},     
					    async : false, //默认为true 异步     
					    error:function(){
					       alert('ajax error');     
					    }, 
					    success:function(data){
					    	reloadTable();
					    }
					}); 
				}
			});
			
			//批量审核
			$("#batchaudit").click(function(){
				var ids = xfy.getCheckedValues('orderId');
				if ($.trim(ids)==""){
					alert("请选择要审核的采购单");
					return false;
				}
				xfy.requestByForm({
					method: 'post',
					target: "_blank",
					action: '${pageContext.request.contextPath}/purchaseorder/batch-audit',
					data: {orderId : ids}
				});
				window.setTimeout("reloadTable()", 2000);
			});
			
			function reloadTable(){
				tb.fnDestroy();
				loadDate();
			}
			
			//批量打印
			$("#batchprintbtn").click(function(){
				var ids = xfy.getCheckedValues('orderId');
				if ($.trim(ids)==""){
					alert("请选择要打印的采购单");
					return false;
				}
				xfy.requestByForm({
					method: 'post',
					target: "_blank",
					action: '${pageContext.request.contextPath}/purchaseorder/batch-print',
					data: {orderId : ids}
				});
			});
			
			//采购合同批量打印
			$("#batchhtprintbtn").click(function(){
				var ids = xfy.getCheckedValues('orderId');
				if ($.trim(ids)==""){
					alert("请选择要打印的采购单");
					return false;
				}
				xfy.requestByForm({
					method: 'post',
					target: "_blank",
					action: '${pageContext.request.contextPath}/purchaseorder/print',
					data: {orderId : ids}
				});
			});
			
			$(function(){
				xfy.initDataTable();
				loadDate();
				$("#waybillNo").focus();
				
				var time = null;
				var requesting = false;
				$('#waybillNo').bind('input', function(e) {
					//console.log(e.timeStamp);
					time = e.timeStamp;
				});
				
				var timeChecker = setInterval(function() {
					//console.log('setInterval');
					if (time == null || requesting === true) {
						return;
					}
					
					var interval = new Date().getTime() - time;
					//console.log(interval);
					if (interval >= 300) {
						requesting = true;
						$("#searchbtn").click();
						requesting = false;
						time = null;
						$('#waybillNo').val('').focus();
					}
					
				}, 100);
			});
			
			//ajax load tabledata
			function loadDate(){
				tb = $("#allocateorder-table").dataTable({
					searching: false,
					serverSide: true,
					ordering:true,
					ajax: {
						url:"${pageContext.request.contextPath }/purchaseorder/pageJson",
						contentType:"application/json;charset=UTF-8",	
						type: 'post',
						data: function (d) {
						var jsondata = $("#searchform").serializeObject();  //转化为json
						d.params = jsondata;
							return JSON.stringify( d );
						}
					},
					columns: [
					      {data:function(data){
					    	  return "<input type='checkbox' name='orderId' value={0} />".format(data.id);
					      },"sortable":false},
					      {data:function(data){
								var del = xfy.html.deleteLink.format('${pageContext.request.contextPath}/purchaseorder/remove/'+data.id,'<spring:message code="g.label.delete"/>');
								var edit =  xfy.html.editLink.format('${pageContext.request.contextPath}/purchaseorder/form?id='+data.id,'<spring:message code="g.label.edit"/>');
								var sougongjiean = xfy.html.confirmLink.format('${pageContext.request.contextPath}/purchaseorder/manualClosing?id='+data.id,'手工结案');
								var fanshenhe = xfy.html.confirmLink.format('${pageContext.request.contextPath}/purchaseorder/reverse_audit?id='+data.id,'反审核');
								//采购单审核后不能操作,只能 入库
								if (data.status==1){
									return edit+"&nbsp;&nbsp;"+del;
								}
								else if(data.status==2 || data.status==5 || data.status==-1){
									var retStr = "";
									if (data.status==2 || data.status==5){
										retStr= (data.paidTime?"":fanshenhe)+"&nbsp;&nbsp;"
									}
									var psougongjiean = "<x:power type='function' code='purchase_order_sougongjiean'>" + sougongjiean+"</x:power>";
									return retStr+psougongjiean;
								}
								return "";
						},"sortable":false},
					    {data: function(data){
								//return  "<span onclick='location.href=${pageContext.request.contextPath}/purchaseorder/detail/"+data.id+"'>"+data.orderNo+"</span>"
								var url = "${pageContext.request.contextPath}/purchaseorder/detail/"+data.id;
								return "<span style='cursor:pointer' onclick=window.open('{0}')>{1}</span>".format(url,data.orderNo);
						},"sortable":false},
						{data:"serialNumber"},
						{data:function(data){
							return data.isSample==1?"样品采购":"采购订单";
						},"sortable":false},
						{data:function(data){
							return "<a href='${pageContext.request.contextPath}/purchaseorder/waybill?orderNo={0}' target='blank'>{1}</a>".format(data.orderNo,data.waybillNos);
						},"sortable":false},
						{data:"logisticsCompanys","sortable":false},
						{data: "purchaseDate", 
							render: function ( data, type, full, meta ) {
								return data == null ? "" : new Date(data).format("yyyy-MM-dd");
							},"sortable":false
						},
						{
							data:function(data){
								return !data.payStatus?"":	(data.payStatus==1?"部分付款":"全部付款");
							},"sortable":false
						},
						{data: "paidTime", 
							render: function ( data, type, full, meta ) {
								return data == null ? "" : new Date(data).format("yyyy-MM-dd HH:mm:ss");
							},"sortable":false
						},
						{data: "supplierName","sortable":false},
						{data: "buyUserName","sortable":false},
						{data: "deliveryDate",
							render: function ( data, type, full, meta ) {
							return data == null ? "" : new Date(data).format("yyyy-MM-dd");
						},"sortable":false},
						{data: "freight","sortable":false},
						{data: "payMethod","sortable":false},
						{data: "purchaseRequestId","sortable":false},
						{data: "purchaseRequestCreater","sortable":false},
						{data: "auditUserName","sortable":false},
						{data: "auditTime", 
							render: function ( data, type, full, meta ) {
								return data == null ? "" : new Date(data).format("yyyy-MM-dd");
							},"sortable":false
						},
						{data: function(data){
							if (data.type==1){
								return "请购转入";	
							}else if (data.type==2){
								return "手工新增";
							}
						},"sortable":false},
						
						{className: "text-center", data: function(row){
							
							if(row.status == -1){
								return "异常入库";
							}
							else if(row.status == 1){
								return "未审核";
							}else if(row.status == 2){
						 		var htm = '';
								<x:power type="function" code="purchase_order_audit">
								//htm = '<a  data-toggle="modal" class="btn default btn-xs purple"  href="${pageContext.request.contextPath }/purchaseorder/receive?state=5&orderNo='+row.orderNo+'"><i class=""></i>审核未接收</a>'
								htm += xfy.html.editLink.format('${pageContext.request.contextPath }/purchaseorder/receive?state=5&orderNo='+row.orderNo+'','审核未接收');
								</x:power>
								return htm;
							}else if(row.status == 5){
								<x:power type="function" code="purchase_order_audit">
									var htm = '<a  data-toggle="modal" class="btn default btn-xs purple"  href="${pageContext.request.contextPath }/purchaseorder/receive?state=2&orderNo='+row.orderNo+'"><i class=""></i>审核已接收</a>'
								</x:power>
								return htm;
							}else if(row.status == 3){
								return '正常关闭';
							}else if(row.status == 4){
								return '手工结案';
							}else{
								return '';	
							}
						},"sortable":false},
						{data: function(data){
							var btn = "<input  onclick=fillHandleResult('{0}') type='button' value='填写'>".format(data.orderNo);
							var note = data.note?data.note:"";
							return (data.status==4 || data.status==3 )?data.note:(note+btn);
						},"sortable":false}
					]
					,drawCallback: function(settings) {
						var rows = $("#allocateorder-table tbody tr");
						if (rows.length>1){
							$.each(rows,function(i,row){
								var poNo = row.cells[2].innerText;
									showWarn(poNo,row);	
							});	
						}
					}
				});
			}
			
			function showWarn(poNo,row){
				$.ajax({
				    url:'${pageContext.request.contextPath}/purchaseorder/overDue',     
				    type:'post',
				    data:'poNo='+$.trim(poNo),     
				    async : true, //默认为true 异步     
				    error:function(){
				    }, 
				    success:function(overdue){
				    	if(overdue=="true"){
				    		row.className="danger";
				    	}
				    }
				}); 
			}
			
			//一级分类select
		    $("#firstCategory").change(function(){
		    	$("#secondCategory").empty();
		    	$("#thirdCategory").empty();
		    	if ($.trim($(this).val())==''){
		    		return;
		    	}
				$.ajax({
				    url:'${pageContext.request.contextPath}/goodscategory/secondCategory',     
				    type:'post',
				    data:'id='+$.trim($(this).val()),     
				    async : false, //默认为true 异步     
				    error:function(){
				       alert('ajax error');     
				    }, 
				    success:function(data){
				    	var jsondata = eval("("+data+")");
				    	if(jsondata.result){
				    		var secondCategory = jsondata.datas;
				    		$("#secondCategory").append("<option value=''>请选择</option>");
				    		for(var i=0;i<secondCategory.length;i++){
				    			var newoption = "<option value=" +secondCategory[i].id+">"+secondCategory[i].name+"</option>";
				    			$("#secondCategory").append(newoption);
				    		}
				    	}
				    }
				}); 
		    });
			
		  //二级分类select
		    $("#secondCategory").change(function(){
		    	$("#thirdCategory").empty();
		    	if ($.trim($(this).val())==''){
		    		return;
		    	}
				$.ajax({
				    url:'${pageContext.request.contextPath}/goodscategory/secondCategory',     
				    type:'post',
				    data:'id='+$.trim($(this).val()),     
				    async : false, //默认为true 异步     
				    error:function(){
				       alert('ajax error');     
				    }, 
				    success:function(data){
				    	var jsondata = eval("("+data+")");
				    	if(jsondata.result){
				    		var secondCategory = jsondata.datas;
				    		$("#thirdCategory").append("<option value=''>请选择</option>");
				    		for(var i=0;i<secondCategory.length;i++){
				    			var newoption = "<option value=" +secondCategory[i].id+">"+secondCategory[i].name+"</option>";
				    			$("#thirdCategory").append(newoption);
				    		}
				    	}
				    }
				}); 
		    });
		  
		    function handleSupplierSelect(hiddenFieldId) {
				$(".c-supplier-picker").select2({
					minimumInputLength: 1,
					ajax: {
						delay: 1000,
						url: xfy.contextPath+'/supplier/page-json?status=1',
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
							return {
								results: result.data
							};
						}
					},
					formatResult: function(item) {
						return item.companyName;
					},
					formatSelection: function(item) {
						$('#' + hiddenFieldId).val(item.id);
						return item.companyName;
					}
				});
			}
		   
		   $(function(){
			   handleSupplierSelect('supplierId');
		   });
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>