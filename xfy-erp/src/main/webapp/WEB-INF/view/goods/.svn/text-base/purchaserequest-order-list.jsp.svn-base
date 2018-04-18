<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>

<c:set scope="request" var="sysModule" value="purchase" />
<c:set scope="request" var="sysPage" value="purchaserequest_order" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">请购单</tiles:putAttribute>
	
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
									<span class="caption-subject font-green-sharp bold uppercase">请购单</span>
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
											<div class="btn-group"><x:power type="function" code="purchaserequest_order_add">
												<a class=" btn green" href="${pageContext.request.contextPath}/purchaserequest-order/form">
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
												<label class="col-md-2 control-label">请购单号</label>
												<div class="col-md-10">
													<input type="text" class="col-md-3 form-control"  name="orderNo" />
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group form-group-sm">
												<label class="col-md-2 control-label">申请人</label>
												<div class="col-md-4">
													<input type="text" name="applier" id="applier" class="col-md-3 form-control" />
												</div>
												<label class="col-md-2 control-label">SKU</label>
												<div class="col-md-4">
													<input type="text" class="form-control"  name="goodsSku" />
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
											<div class="form-group">
												<label  class="col-md-2 control-label">申请日期</label>
												<div class="col-md-5">
														<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
															<input name="applyDateFrom" class="form-control input-sm" /> <span class="input-group-btn">
																<button class="btn default btn-sm" type="button">
																	<i class="fa fa-calendar"></i>
																</button>
															</span>
														</div>
												</div>
												<div class="col-md-5">
													<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
															<input name="applyDateTo" class="form-control input-sm" /> <span class="input-group-btn">
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
										<div class="col-md-4">
											<div class="form-group form-group-sm">
												<label for="dict_code" class="col-md-2 control-label">转采购</label>
												<div class="col-md-10">
													<form:select path="status" cssClass="form-control">
														<option value="">请选择</option>
														<form:option value="2">已转采购</form:option>
														<form:option value="1">待转采购</form:option>
													</form:select>
												</div>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="col-md-3">
											<div class="form-group form-group-sm">
												<label class="col-md-3 control-label">产品大类</label>
												<div class="col-md-9">
													<form:select path="firstCategory" name="firstCategory"  cssClass="form-control" id="firstCategory">
														<option value="">请选择</option>
														<c:forEach items="${ categoryList}" var="item">
																<option  value="${item.id}">${item.name }</option>
														</c:forEach>
													</form:select>
												</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group form-group-sm">
												<label class="col-md-3 control-label">产品中类</label>
												<div class="col-md-9">
													<form:select path="secondCategory" name="secondCategory" cssClass="form-control" id="secondCategory">
													</form:select>
												</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group form-group-sm">
												<label class="col-md-3 control-label">产品小类</label>
												<div class="col-md-9">
													<form:select path="thirdCategory" name="thirdCategory" cssClass="form-control" id="thirdCategory">
													</form:select>
												</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group form-group-sm">
												<button type="button" id="searchbtn" class="btn green"><spring:message code="g.label.search" /></button>
												<button type="button" id="clearbtn" class="btn blue">清空</button>
											</div>
										</div>
									</div>
								</form:form>
								<div class="table-toolbar">
									<div class="row">
										<div class="col-md-6">
											<div class="btn-group">
											<x:power type="function" code="purchaserequest_order_purchase">
												<button id="sample_editable_1_new" class="btn green" style="color:red" >
													生成采购单<i class="fa fa-plus"></i>
												</button>
											</x:power>
											</div>
										</div>
									</div>
								</div>
								<div class="">
									<table class="table table-hover table-striped table-bordered table-condensed" id="allocateorder-table">
									<thead>
									<tr>
										<th>
											<input type="button" id="selectAllBtn" value="select">
										</th>
										<th>
											请购单号
										</th>
										<th>
											申请日期
										</th>
										<th>
											申请人
										</th>
										<th>
											供应商
										</th>
										<th>
											要求交货日期
										</th>
										<th>
											是否转采购
										</th>
										<th>
											采购单号
										</th>
										<th>
											采购人员
										</th>
										<th>
											源单类型
										</th>
										<!--  
										<th>
											订单编号
										</th>
										-->
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
		
		 $(function() {
			 	var url = "${pageContext.request.contextPath }/user/getUserByDeparment.json";
			    $( "#applier" ).autocomplete({
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
		 
		var selectOrderId="";
		$("#selectAllBtn").click(function(){
			$("#allocateorder-table input[pcrtid]").each(function(i){
				if($(this).attr("checked")){
					$(this).removeAttr("checked"); 
				}else{
					$(this).attr("checked",'true');
				}
			});
		});
		
		$("#sample_editable_1_new").click(function(){
			selectOrderId ="";	
			$("#allocateorder-table input[pcrtid]").each(function(i){
				if($(this).attr("checked")){
					selectOrderId +=$(this).attr("pcrtid")+"|";
				}
			});
			if (selectOrderId.length>0){
				selectOrderId = selectOrderId.substring(0,selectOrderId.length-1);
			}else{
				alert("请选择要转采购的请购单");
				return;
			}
			if (!confirm("确定要将所选请购单转采购？")){
				return false;
			}
			$.ajax({
				 url:xfy.contextPath+'/purchaserequest-order/convert', 
			    type:'post',
			    data:'ids='+selectOrderId,     
			    error:function(){
			       alert('ajax error'); 
			    }, 
			    success:function(data){
			    	var jsondata = eval("("+data+")");
			    	if(jsondata.result){
			    		alert(jsondata.ids);
			    		window.location.href="${pageContext.request.contextPath }/purchaseorder/list";	
			    	}else{
			    		alert(jsondata.errorMsg);
			    	}
			    }
			}); 
		});
		
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
				tb = $("#allocateorder-table").dataTable({
					searching: false,
					serverSide: true,
					ordering:false,
					ajax: {
						url:"${pageContext.request.contextPath }/purchaserequest-order/pageJson",
						contentType:"application/json;charset=UTF-8",	
						type: 'post',
						data: function (d) {
						var jsondata = $("#searchform").serializeObject();  //转化为json
						d.params = jsondata;
							return JSON.stringify( d );
						}
					},
					columns: [
					     {data:function(obj){
					    	 //已转采购,不可勾选 
					    	 if (obj.status==2){
					    		 return "";
					    	 }
					    	 var chkbox = "<input type='checkbox' pcrtid=" + obj.id + " />";
					    	 return chkbox;
					     }},  
					     {data: function(data){
								return "<a href='${pageContext.request.contextPath}/purchaserequest-order/detail/"+ data.id+"'>" +data.orderNo+"</a>"
						}},
						{data: "createdTime", 
							render: function ( data, type, full, meta ) {
								return data == null ? "" : new Date(data).format("yyyy-MM-dd");
							}
						},
						{data: "createdUserName"},
						{data: "supplierName"},
						{data: "deliveryDate",
							render: function ( data, type, full, meta ) {
							return data == null ? "" : new Date(data).format("yyyy-MM-dd");
						}},
						{data: function(data){
							if (data.status==1){
								return "否";
							}else if (data.status==2){
								return "是";
							}
						}},
						{data: "purchaseOrderNo"},
						{data: "buyuserName"},
						{data: function(data){
							if (data.type==1){
								return "手工增加";
							}else if(data.type==2){
								return "库存预警";
							}
							else if(data.type==3){
								return "订单生成";
							}
						}},
						//{data: "sellOrderId"},
						{data:function(data){
							var html = '';
							<x:power type="function" code="purchaserequest_order_delete">
							html += xfy.html.deleteLink.format('${pageContext.request.contextPath}/purchaserequest-order/remove/'+data.id,'<spring:message code="g.label.delete"/>');
							</x:power>
							<x:power type="function" code="purchaserequest_order_edit">
							html +=  xfy.html.editLink.format('${pageContext.request.contextPath}/purchaserequest-order/form?id='+data.id,'<spring:message code="g.label.edit"/>');
							</x:power>
							//请购单转采购后不能编辑删除
					 		if (data.status==1){
								return html;	
							}else{
								return "";	
							} 
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