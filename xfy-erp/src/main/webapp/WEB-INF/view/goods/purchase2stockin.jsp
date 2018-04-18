<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>

<c:set scope="request" var="sysModule" value="product" />
<c:set scope="request" var="sysPage" value="purchase_order_in" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">采购待入库列表 </tiles:putAttribute>
	
	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/select2/select2.css"/>

	</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
			<div class="row">
				<div class="col-md-12">
						<div class="portlet light">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-cogs font-green-sharp"></i>
									<span class="caption-subject font-green-sharp bold uppercase">采购待入库列表</span>
								</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"></a>
								</div>
							</div>
							<div class="portlet-body">
								<%@include file="../include/message.jsp" %>
								<form:form id="searchform" modelAttribute="search" method="post" class="form-horizontal" role="form" search="1">
									<!-- 过滤已审核的采购单入库 -->
									<input type="hidden" name="arrived" value="1" >
									<input type="hidden" name="status" value="2,5,-1">
									
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-group-sm">
											<label class="control-label col-md-4">采购单号</label>
											<div class="col-md-8">
											<input type="text" class=" col-md-3 form-control"  name="orderNo" />
											</div>
											</div>
										</div>
										
										<div class="col-md-4">
											<div class="form-group form-group-sm">
											<label class="control-label col-md-4">运单号</label>
											<div class="col-md-8">
											<input type="text" class=" col-md-3 form-control" id="waybillNo"  name="waybillNo" />
											</div>
											</div>
										</div>
										
										<div class="col-md-4">
											<div class="form-group form-group-sm">
											<label class="control-label col-md-4">供应商</label>
											<div class="col-md-8">
												<input type="text" class="form-control c-supplier-picker" value="" data-placeholder="请选择..." />
												<form:hidden path="supplierId" id="supplierId" />
											</div>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-group-sm">
											<label class="control-label col-md-4">产品大类</label>
											<div class="col-md-8">
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
											<label class="control-label col-md-4">产品中类</label>
											<div class="col-md-8">
											<form:select path="secondCategory" name="secondCategory" cssClass="form-control" id="secondCategory">
											</form:select>
											</div>
											</div>
										</div>
										
										<div class="col-md-4">
											<div class="form-group form-group-sm">
											<label class="control-label col-md-4">产品小类</label>
											<div class="col-md-8">
											<form:select path="thirdCategory" name="thirdCategory" cssClass="form-control" id="thirdCategory">
											</form:select>
											</div>
											</div>
										</div>
										
									</div>
									
									<div class="row">
										<div class="col-md-4">
											<label class="col-md-4 control-label">SKU</label>
												<div class="col-md-8">
													<input type="text" class="form-control"  name="goodsSku" />
												</div>
										</div>
										<div class="col-md-4">
											<div class="form-group form-group-sm">
												<label class="control-label col-md-4"></label>
												<div class="col-md-8">
												<button type="button" id="searchbtn" class="btn green"><spring:message code="g.label.search" /></button>
												<button type="button" id="clearbtn" class="btn blue">清空</button>
												</div>
											</div>
										</div>
									</div>
									
									
								</form:form>
								
								<div class="">
									<table class="table table-hover table-striped table-bordered table-condensed" id="allocateorder-table">
									<thead>
									<tr>
										<th>
											采购单号
										</th>
										<th>
											采购日期
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
											操作
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
					ordering:false,
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
					     {data: function(data){
								return "<a href='${pageContext.request.contextPath}/purchaseorder/detail/"+data.id+"'>"+data.orderNo+"</a>"
						}},
						{data: "purchaseDate", 
							render: function ( data, type, full, meta ) {
								return data == null ? "" : new Date(data).format("yyyy-MM-dd");
							}
						},
						{data: "supplierName"},
						{data: "buyUserName"},
						{data: "deliveryDate",
							render: function ( data, type, full, meta ) {
							return data == null ? "" : new Date(data).format("yyyy-MM-dd");
						}},
						{data: "freight"},
						{data: "payMethod"},
						{data: "purchaseRequestId"},
						{data: "purchaseRequestCreater"},
						{data: "auditUserName"},
						{data: "auditTime", 
							render: function ( data, type, full, meta ) {
								return data == null ? "" : new Date(data).format("yyyy-MM-dd");
							}
						},
						{data: function(data){
							if (data.type==1){
								return "请购转入";	
							}else if (data.type==2){
								return "手工新增";
							}
						}},
						{data:function(data){
								var ruku = '';
								<x:power type="function" code="purchase_order_in_audit">
								ruku += xfy.html.normalLink.format('${pageContext.request.contextPath}/stockin/form?purchaseOrderNo='+data.orderNo,'<spring:message code="g.label.stockin"/>');
								</x:power>
								return ruku;
						}}
					]
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