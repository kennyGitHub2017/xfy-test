<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set scope="request" var="sysModule" value="product" />
<c:set scope="request" var="sysPage" value="purchase_order_in_list" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">采购入库单</tiles:putAttribute>
	
	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>

	</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
			<div class="row">
				<div class="col-md-12">
						<div class="portlet light">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-cogs font-green-sharp"></i>
									<span class="caption-subject font-green-sharp bold uppercase">采购入库单</span>
								</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"></a>
								</div>
							</div>
							<div class="portlet-body">
								<%@include file="../include/message.jsp" %>
								<form:form id="searchform" modelAttribute="search" method="post" class="form-horizontal" role="form" search="1">
									<input type="hidden"  value="0" name="type">
									<input type="hidden"  value="56" name="typeDetail">
									
									<div class="row">
										<div class="col-md-4">
										<div class="form-group form-group-sm">
											<label class="col-md-4 control-label">入库单号</label>
											<div class="col-md-8">
											<input type="text" class=" col-md-3 form-control"  name="orderNo" />
											</div>
										</div>
										</div>
										
										<div class="col-md-4">
											<div class="form-group form-group-sm">
											<label class="col-md-4 control-label">采购单号</label>
											<div class="col-md-8">
											<input type="text" class="form-control"  name="purchaseOrderId" />
											</div>
											</div>
										</div>
										
										<div class="col-md-4">
											<div class="form-group form-group-sm">
											<label class="col-md-4 control-label">SKU</label>
											<div class="col-md-8">
											<input type="text" class="form-control"  name="goodsSku" />
											</div>
											</div>
										</div>
									</div>
									
								<div class="row">
										<div class="col-md-4">
										<div class="form-group form-group-sm">
											<label class="col-md-4 control-label">仓库</label>
											<div class="col-md-8">
												<form:select path="storeId" id="storeId" cssClass="form-control">
													<form:option value="">请选择</form:option>
													<c:forEach items="${storeList }" var="item">
														<form:option value="${item.id }">${item. name}</form:option>	
													</c:forEach>
												</form:select>
											</div>
										</div>
										</div>
										
										<div class="col-md-4">
											<div class="form-group form-group-sm">
											<label class="col-md-4 control-label">仓位</label>
											<div class="col-md-8">
											<select name="storeShelf" id="storeShelf" class="form-control" >
											</select>
											</div>
											</div>
										</div>
										
										<div class="col-md-4">
											<div class="form-group form-group-sm">
											<label class="col-md-4 control-label">产品名称</label>
											<div class="col-md-8">
											<input type="text" class="form-control"  name="goodsName" />
											</div>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-group-sm">
											<label class="col-md-4 control-label">产品大类</label>
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
											<label class="col-md-4 control-label">产品中类</label>
											<div class="col-md-8">
											<form:select path="secondCategory" name="secondCategory" cssClass="form-control" id="secondCategory">
											</form:select>
											
											</div>
											</div>
										</div>
										
										<div class="col-md-4">
											<div class="form-group form-group-sm">
											<label class="col-md-4 control-label">产品小类</label>
											<div class="col-md-8">
											<form:select path="thirdCategory" name="thirdCategory" cssClass="form-control" id="thirdCategory">
											</form:select>
											</div>
											</div>
										</div>
										
									</div>
									
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-group-sm">
												<label  class="col-md-4 control-label">SKU状态</label>
												<div class="col-md-8">
													<form:select path="skuStatus" cssClass="form-control">
														<form:option value="">请选择</form:option>
														<form:options items="${goodsStatus }" itemLabel="name" itemValue="code" />
													</form:select>
												</div>
											</div>
										</div>
										
										<div class="col-md-4">
											<div class="form-group form-group-sm">										
												<label  class="col-md-4 control-label">采购单状态</label>
												<div class="col-md-8">
													<form:select path="purchaseOrderStatus" cssClass="form-control">
														<form:option value="">请选择</form:option>
														<form:option value="1">待审核</form:option>
														<form:option value="2">已审核</form:option>
														<form:option value="3">正常关闭</form:option>
														<form:option value="4">手工结案</form:option>
														<form:option value="-1">异常入库</form:option>
													</form:select>
												</div>
											</div>
										</div>
										
										<div class="col-md-2">
											<div class="form-group form-group-sm">
											<label class="col-md-4 control-label">采购人</label>
											<div class="col-md-8">
												<input name="purchaserBuyer" type="text" class="form-control" />
											</div>
											</div>
										</div>
										
										<div class="col-md-2">
											<div class="form-group form-group-sm">
											<label class="col-md-4 control-label">制单人</label>
											<div class="col-md-8">
												<input name="createdUserName" type="text" class="form-control"  />
											</div>
											</div>
										</div>
										
									</div>
								
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label  class="col-md-2 control-label">入库日期</label>
												<div class="col-md-5">
													<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
														<input name="inOutDateFrom" class="form-control input-sm" /> <span class="input-group-btn">
															<button class="btn default btn-sm" type="button">
																<i class="fa fa-calendar"></i>
															</button>
														</span>
													</div>
												</div>
												
												<div class="col-md-5">
												<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
														<input name="inOutDateTo" class="form-control input-sm" /> <span class="input-group-btn">
															<button class="btn default btn-sm" type="button">
																<i class="fa fa-calendar"></i>
															</button>
														</span>
												</div>
											</div>
										</div>
									</div>
									
									<div class="col-md-6">
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
								</div>
								
								<div class="row">
										<div class="col-md-4">
											<div class="form-group form-group-sm">
												<label class="col-md-4 control-label">运单号</label>
												<div class="col-md-8">
													<input name="waybillNo" id="waybillNo" type="text" class="form-control"  />
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group form-group-sm">
												<label  class="col-md-4 control-label"></label>
												<div class="col-md-8">
													<button type="button" id="searchbtn" class="btn green"><spring:message code="g.label.search" /></button>
													<button type="button" id="clearbtn" class="btn blue">清空</button>
												</div>
											</div>
										</div>
								 </div>
								</form:form>
								
								<div class="">
									<div align="right" style="color:red" id="totalmsg"></div>
									<table class="table table-hover table-striped table-bordered table-condensed" id="allocateorder-table">
									<thead>
									<tr>
										<th>
											入库单号
										</th>
										<th>
											入库单类型
										</th>
										<th>
											入库单状态
										</th>
										<th>
											入库日期
										</th>
										<th>
											制单人
										</th>
										<th>
											采购单号
										</th>
										<th>
											采购单类型
										</th>
										<th>
											采购单状态
										</th>
										<th>
											采购日期
										</th>
										<th>
											采购人
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
				$.ajax({
				    url:'${pageContext.request.contextPath}/stockin/purchaseTotal.json',
				    type:'post',
				    data: $("#searchform").serialize(),
				    async : false, //默认为true 异步     
				    error:function(){
				       alert('ajax error');     
				    }, 
				    success:function(data){
				    		var msg  ="汇总：入库单" + data.orderCount+",采购数量" + data.buyCount +",检验数量" + data.testTotal+",合格数量"+data.qualifiedTotal+ ",不合格数量"+data.unQualifiedTotal;
							$("#totalmsg").text(msg);
				    }
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
					ordering:false,
					ajax: {
						url:"${pageContext.request.contextPath }/stockin/pageJson",
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
							var s = "<a href='${pageContext.request.contextPath }/stockin/detail/" +data.orderNo+"'> "+data.orderNo+"</a>";
							return s;
						}},
						{data: "typeName"},
						{data: function(){	return "已审核"}},
						{data: "createdTime"},
						{data: "createdUserName"},
						{data: function(data){
							return "<a href='${pageContext.request.contextPath}/purchaseorder/detail/"+data.buyOrderId+"'>"+data.buyOrderNo+"</a>";
						}},
						{data: function(data){
							if (data.purchaseOrderType==1){
								return "请购转入";
							}else if(data.purchaseOrderType==2){
								return "手工新增";
							}
							return "";
						}},
						{data: function(data){
							if (data.purchaseOrderStatus==-1){
								return "异常入库";
							}else if (data.purchaseOrderStatus==1){
								return "待审核";
							}else if (data.purchaseOrderStatus==2){
								return "已审核未接收";
							}else if (data.purchaseOrderStatus==3){
								return "正常关闭";
							}else if (data.purchaseOrderStatus==4){
								return "手工结案";
							}else if (data.purchaseOrderStatus==5){
								return "审核已接收";
							}
							return "";
						}},
						{data: function(data){
							return new Date(data.purchaseDate).format("yyyy-MM-dd");
						}},
						{data: "purchaseBuyer"}
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
		  
		  //选择仓库，联动货位
		    $("#storeId").change(function(){
		    	$("#storeShelf").empty();
		    	if ($.trim($(this).val())==''){
		    		return;
		    	}
				$.ajax({
				    url:'${pageContext.request.contextPath }/store/shelf-json',     
				    type:'post',
				    data:'id='+$.trim($(this).val()),     
				    async : false, //默认为true 异步     
				    error:function(){
				       alert('ajax error');     
				    }, 
				    success:function(data){
				    	var data = eval("("+data+")");
				    	xfy.fillSelect('#storeShelf', data, 'id', 'code');
				    }
				}); 
		    });
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>