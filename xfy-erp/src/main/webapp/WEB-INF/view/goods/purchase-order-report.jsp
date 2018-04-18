<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set scope="request" var="sysModule" value="purchase" />
<c:set scope="request" var="sysPage" value="report-page" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">采购单查询</tiles:putAttribute>
	
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
									<span class="caption-subject font-green-sharp bold uppercase">采购单查询</span>
								</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"></a>
								</div>
							</div>
							<form:form id="searchform" modelAttribute="search"  method="post" class="form-horizontal" role="form" search="1">
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-group-sm">
												<label class="col-md-2 control-label">采购单号</label>
												<div class="col-md-10">
													<input type="text" class="form-control"  name="purchaseOrderId" />
												</div>
											</div>
										</div>
										<div class="col-md-4">
												<div class="form-group form-group-sm">
													<label for="dict_code" class="col-md-2 control-label">采购单状态</label>
													<div class="col-md-10">
														<form:select path="purchaseOrderStatus" cssClass="form-control">
															<option value="">请选择</option>
															<option value="-1">异常入库</option>
															<option value="1">待审核</option>
															<option value="2">已审核</option>
															<option value="5">审核已接受</option>
															<option value="3">正常关闭</option>
															<option value="4">手工结案</option>
														</form:select>
													</div>
												</div>
										</div>
										<div class="col-md-4">
											<div class="form-group form-group-sm">
												<label class="col-md-2 control-label">是否关闭</label>
												<div class="col-md-10">
													<form:select path="closed" class="form-control">
														<form:option value="">请选择</form:option>
														<form:option value="1">是</form:option>
														<form:option value="0">否</form:option>
													</form:select>
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
												<div class="form-group form-group-sm">
													<label class="col-md-2 control-label">产品名称</label>
													<div class="col-md-10">
														<input type="text" class="form-control"  name="goodsName" />
													</div>
												</div>
										</div>
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
									</div>
									
									<div class="row">
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
													<input type="text" class="form-control c-supplier-picker" value="" data-placeholder="请选择..." />
													<form:hidden path="supplierId" id="supplierId" />
												</div>
											</div>
										</div>
										<div class="col-md-4">
												<div class="form-group form-group-sm">
													
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
											<div class="form-group form-group-sm">
													<label  class="col-md-2 control-label">入库日期</label>
														<div class="col-md-5">
																<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
																	<input name="inStorageDateFrom" class="form-control input-sm" /> <span class="input-group-btn">
																		<button class="btn default btn-sm" type="button">
																			<i class="fa fa-calendar"></i>
																		</button>
																	</span>
																</div>
														</div>
														<div class="col-md-5">
															<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
																	<input name="inStorageDateTo" class="form-control input-sm" /> <span class="input-group-btn">
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
												<label class="col-md-2 control-label">产品大类</label>
												<div class="col-md-10">
													<form:select path="firstCategory" name="firstCategory"  cssClass="form-control" id="firstCategory">
														<option value="">请选择</option>
														<c:forEach items="${ categoryList}" var="item">
																<option  value="${item.id}" <c:if test="${item.id==search.firstCategory }">selected="selected"</c:if> >${item.name }</option>
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
														<option value="">请选择</option>
													</form:select>
												</div>
											</div>
										</div>
									
									 
										<div class="col-md-4">
											<div class="form-group form-group-sm">	
												<label class="col-md-2 control-label">产品小类</label>
												<div class="col-md-10">
													<form:select path="thirdCategory" name="thirdCategory" cssClass="form-control" id="thirdCategory">
														<option value="">请选择</option>
													</form:select>
												</div>
											</div>		
										</div>
									</div>
									
									<div class="row">
										<div class="col-md-4">
											<div class="form-group form-group-sm">
												<label class="col-md-2 control-label">制单人</label>
												<div class="col-md-10">
													<input type="text" class="form-control"  name="creater" />
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group form-group-sm">
												<label class="col-md-2 control-label">采购员</label>
												<div class="col-md-10">
													<input type="text" class="form-control"  name="buyer" />
												</div>
											</div>
										</div>
										<div class="col-md-4">
												<div class="form-group">
													<button type="button" id="searchbtn" class="btn green"><spring:message code="g.label.search" /></button>
													<button type="button" id="clearbtn" class="btn blue">清空</button>
													<button type="button" id="exportbtn" class="btn green">导出</button>
													<button type="button" id="importpricebtn" class="btn green">价格导入</button>
												</div>
										</div>
									</div>
								
								</form:form>					
							<div class="portlet-body">
								<%@include file="../include/message.jsp" %>
									<table class="table table-hover table-striped table-bordered table-condensed" id="purchaseorder-table">
									<thead>
									<tr>
										<th>
											采购单号
										</th>
										<th>
											采购日期
										</th>
										<th>
											入库日期
										</th>
										<th>
											采购员
										</th>
										<th>
											SKU
										</th>
										<th>
											产品类型
										</th>
										<th>
											名称
										</th>
										<th>
											描述
										</th>
										<th>
											单位
										</th>
										<th>
											数量
										</th>
										<th>
											采购单价
										</th>
										<th>
											金额
										</th>
										<th>
											已交数量
										</th>
										<th>
											未交数量
										</th>
										<th>
											合格数量
										</th>
										<th>
											不合格数量
										</th>
										<th>
											供应商
										</th>
										<th>
											要求交货日期
										</th>
										<th>
											状态
										</th>
										<th>
											源单类型
										</th>
										<th>
											运费
										</th>
										<th>
											支付方式
										</th>
										<th>
											制单人
										</th>
										<th>
											制单日期
										</th>
										<th>
											行关闭
										</th>
										<th>
											关闭
										</th>
									</tr>
									</thead>
									<tbody id="tbody">
									</tbody>
									</table>
							</div>
							
							<!-- form -->
							<div class="modal fade" id="skupriceimport" tabindex="-1" role="dialog"
					  			 aria-labelledby="myModalLabel" aria-hidden="true">
					  		   <form action="${pageContext.request.contextPath}/purchaseorder/importSkuPrice" onsubmit="importSkuPrice()" method="post" enctype="multipart/form-data">
							   <div class="modal-dialog">
							      <div class="modal-content">
							         <div class="modal-header">
							            <button type="button" class="close" data-dismiss="modal"
							               aria-hidden="true">×
							            </button>
										            <h4 class="modal-title" id="myModalLabel">
										               		采购单Sku价格导入操作		            
										            </h4>
										         </div>
										         <div class="modal-body">
														上传文件:<input type="file" class="form-control"  name="importSkuPriceFile" /><br>
														<div class="h4" style="color:red">
															模板范本(xls/xlsx)：
															<table>
																<thead>
																	<tr>
																		<td>采购单号</td>
																		<td>支付方式</td>
																		<td>运费</td>
																		<td>SKU</td>
																		<td>数量</td>
																		<td>采购价</td>
																	</tr>
																</thead>
																<tbody>
																	<tr>
																		<td>PO-20151216-1517049272</td>
																		<td>支付宝</td>
																		<td>10</td>
																		<td>1300857001</td>
																		<td>5</td>
																		<td>40</td>
																	</tr>
																</tbody>
															</table>	
														</div>	
							         			</div>
										         <div class="modal-footer">
										            <button type="button" class="btn btn-default"
										               data-dismiss="modal">关闭
										            </button>
										            <button type="submit" class="btn btn-primary" id=impbtn>导入</button>
							         </div>
							      </div><!-- /.modal-content -->
							   </div><!-- /.modal-dialog -->
							   </form><!-- /.form -->
							</div><!-- /.modal -->
		
				</div>
			</div>
		</div>
	</tiles:putAttribute>
	<tiles:putAttribute name="js-page">
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/select2/select2.min.js" type="text/javascript"></script>
		<script>
		
		$("#importpricebtn").click(function(){
			$("#skupriceimport").modal({keyboard: false,backdrop:'static'});
		});
		
		function importSkuPrice(){
			$("#impbtn").attr("disabled","disabled");
		}
		
		var totalCount=0,totalCost=0,totalReceived=0,totalUnReceived=0,totalQualified=0,totalUnqualified=0,freightTotal=0,orderCountPage=0;
		var tempAry =[];
		//datepicker show
		$('.date-picker').datepicker({
			orientation: "right",
			autoclose: true
		});
	
		$(function(){
			xfy.initDataTable();
			loadDate()
		});
		
		window.onload=function(){
			$("select[name='purchaseorder-table_length']").change(function(){
				totalCount=0,totalCost=0,totalReceived=0,totalUnReceived=0,totalQualified=0,totalUnqualified=0,freightTotal=0,orderCountPage=0;
				tempAry =[];
			});
			
			$("#purchaseorder-table_paginate").click(function(){
				totalCount=0,totalCost=0,totalReceived=0,totalUnReceived=0,totalQualified=0,totalUnqualified=0,freightTotal=0,orderCountPage=0;
				tempAry =[];
			});
		}
		
		function loadDate(){
			
			tb = $("#purchaseorder-table").dataTable({
				searching: false,
				serverSide: true,
				ordering:false,
				ajax: {
					url:"${pageContext.request.contextPath }/purchaseorder/report",
					contentType:"application/json;charset=UTF-8",	
					type: 'post',
					data: function (d) {
					var jsondata = $("#searchform").serializeObject();  //转化为json
					d.params = jsondata;
						return JSON.stringify( d );
					}
				},
				columns: [
					 {data:"orderNo"},
				     {data:function(data){
				    	 	totalCount +=data.goodsCount;
		    				totalCost +=data.goodsTotal;
		    				totalReceived +=data.receivedCount;
		    				totalUnReceived +=data.unReceivedCount;
		    				totalQualified+=data.qualifiedCount;
		    				totalUnqualified +=data.unQualifiedCount;
		    				if (!tempAry[data.orderNo]){
								freightTotal+=data.freight;
								orderCountPage++;
		    				}
		    				tempAry[data.orderNo]=data.orderNo;
		    				//console.log(tempAry);
				    	 return  new Date(data.purchaseDate).format("yyyy-MM-dd");
				     }},
				    {data:function(data){
				    	 return  data.ioDate?new Date(data.ioDate).format("yyyy-MM-dd"):"";
				    }},
					{data:"buyUserName"},
					{data:"goodsSku"},
					{data:"goodsCategory"},
					{data:"goodsName"},
					{data:function(data){
						return "{0}-{1}-{2}-{3}".format(data.color,data.goodsSize,data.rules,data.model);
					}},
					{data:"goodsUnit"},
					{data:"goodsCount"},
					{data:"goodsCost"},
					{data:"goodsTotal"},
					{data:"receivedCount"},
					{data:"unReceivedCount"},
					{data:"qualifiedCount"},
					{data:"unQualifiedCount"},
					{data:"supplierName"},
					{data:function(data){
						return new Date(data.deliveryDate).format("yyyy-MM-dd");
					}},
					{data:function(data){
						var statusName="";
						if (data.status==-1){
	    					statusName ="异常入库";
	    				}
	    				else if (data.status==1){
	    					statusName ="待审核";
	    				}
	    				else if (data.status==2 || data.status==5){
	    					statusName ="已审核";
	    				}
	    				else if (data.status==3){
	    					statusName ="正常关闭";
	    				}
	    				else if (data.status==4){
	    					statusName="手动结案";
	    				}
						return statusName;
					}},
					{data:function(data){
						var orderType = "";
						if (data.type==1){
	    					orderType ="请购转入";
	    				}else if (data.type==2){
	    					orderType ="手工新增";
	    				}
						return orderType;
					}},
					{data:"freight"},
					{data:"payMethod"},
					{data:"createdUserName"},
					{data:function(data){
						return new Date(data.createdTime).format("yyyy-MM-dd");
					}},
					{data:function(data){
						return data.orderItemClose?"是":"否";
					}},
					{data:function(data){
						return data.orderClose?"是":"否";
					}},
				],
				drawCallback: function(settings) {
					if (totalCount>0){
						var totalrow = "<tr style='color:green'><td>汇总:" +orderCountPage + "</td><td colspan=8></td><td>汇总:" + totalCount+"</td><td></td><td>汇总" +totalCost.toFixed(2)+"</td><td>汇总:" + totalReceived+"</td><td>汇总:" +totalUnReceived+"</td><td>汇总:"+ totalQualified+"</td><td>汇总:"+totalUnqualified+"</td><td colspan=4></td><td>汇总:"+freightTotal.toFixed(2)+"</td><td colspan=5></td>" ;
						$("#tbody").append(totalrow);
						totalInfo();
					}
					totalCount=0,totalCost=0,totalReceived=0,totalUnReceived=0,totalQualified=0,totalUnqualified=0,freightTotal=0,orderCountPage=0;
					tempAry =[];
				}
			});
		}
		
		
		function totalInfo(){
			var totalrow = "<tr style='color:red'><td>汇总:{0}</td><td colspan=8></td><td>汇总:{1}</td><td></td><td>汇总{2}</td><td>汇总:{3}</td><td>汇总:{4}</td><td>汇总:{5}</td><td>汇总:{6}</td><td colspan=4></td><td>汇总:{7}</td><td colspan=5></td>" ;
			$.ajax({
			    url:'${pageContext.request.contextPath}/purchaseorder/purchaseReportTotal.json',     
			    type:'post',
			    data:$("#searchform").serialize(),
			    async : false, //默认为true 异步     
			    error:function(){
			       alert('ajax error');     
			    }, 
			    success:function(data){
			    	$("#tbody").append(totalrow.format(data.orderCount,data.buyCount,data.costTotal,data.testTotal,data.buyCount-data.testTotal,data.qualifiedTotal,data.unQualifiedTotal,data.freightTotal));
			    }
			}); 
		}
		
		
		$("#exportbtn").click(function(){
			var path = '${pageContext.request.contextPath}/purchaseorder/export';
			$('#searchform').attr("method","post");
			$('#searchform').attr("action", path).submit();
		});
		
		$("#searchbtn").click(function(){
			totalCount=0,totalCost=0,totalReceived=0,totalUnReceived=0,totalQualified=0,totalUnqualified=0,freightTotal=0,orderCountPage=0;
			tempAry =[];
			if (tb){
				tb.api().ajax.reload();	
			}
		});
		
		/*
		$("#searchbtn").click(function(){
			var d = new Object();
			var jsondata = $("#searchform").serializeObject();
			d.params = jsondata;
			var data =  JSON.stringify( d );
			$.ajax({
			    url:'${pageContext.request.contextPath }/purchaseorder/report',     
			    type:'post',
			    contentType:"application/json;charset=UTF-8",
			    data: data, 
			    async : false, 
			    error:function(){
			       alert('load data error');
			    }, 
			    success:function(data){
			    	var jsondata = eval("("+data+")");
			    	if(!jsondata.result){
			    		alert("load data error");
			    	}else{
			    		if (jsondata.datas.length>0){
			    			var tb = $("#tbody");
			    			tb.empty();
			    			var totalCount=0,totalCost=0,totalReceived=0,totalUnReceived=0,totalQualified=0,totalUnqualified=0;
			    			for(i=0;i<jsondata.datas.length;i++){
			    				var data = jsondata.datas[i];
			    				var statusName,orderType;
			    				if (data.status==-1){
			    					statusName ="异常入库";
			    				}
			    				else if (data.status==1){
			    					statusName ="待审核";
			    				}
			    				else if (data.status==2){
			    					statusName ="已审核";
			    				}
			    				else if (data.status==3){
			    					statusName ="正常关闭";
			    				}
			    				else if (data.status==4){
			    					statusName ="手工结案";
			    				}
			    				else if (data.status==5){
			    					statusName ="审核已接收";
			    				}
			    				if (data.type==1){
			    					orderType ="请购转入";
			    				}else if (data.type==2){
			    					orderType ="手工新增";
			    				}
			    				var newtr = "<tr><td>" + (i+1)+ "</td><td>" + new Date(data.purchaseDate).format("yyyy-MM-dd")+"</td><td>" + data.buyUserName +"</td><td>"+ data.orderNo+"</td><td>" +data.goodsSku+"</td><td>" +data.goodsCategory+"</td><td>"+data.goodsName+"</td><td>"+"{0}-{1}-{2}-{3}".format(data.color,data.goodsSize,data.rules,data.model)+"</td>";
			    				newtr = newtr+"<td>" + data.goodsUnit+"</td><td>"+data.goodsCount+"</td><td>" + data.goodsCost+"</td><td>" +data.goodsTotal+"</td><td>"+ data.receivedCount+"</td><td>" +data.unReceivedCount+"</td><td>"+data.qualifiedCount+"</td><td>"+data.unQualifiedCount+"</td><td>"+data.supplierName+"</td>";
			    				newtr = newtr + "<td>" +  new Date(data.deliveryDate).format("yyyy-MM-dd")+"</td><td>" + statusName+"</td><td>"+orderType+"</td><td>" +data.freight+"</td><td>"+data.payMethod+"</td><td>"+data.createdUserName+"</td>";
			    				newtr = newtr+"<td>" + new Date(data.createdTime).format("yyyy-MM-dd")+"</td>";
			    				newtr = newtr+"<td>"+(data.orderItemClose?"是":"否")+"</td>"+ "<td>" + (data.orderClose?"是":"否")+"</td></tr>"
			    				totalCount +=data.goodsCount;
			    				totalCost +=data.goodsTotal;
			    				totalReceived +=data.receivedCount;
			    				totalUnReceived +=data.unReceivedCount;
			    				totalQualified+=data.qualifiedCount;
			    				totalUnqualified +=data.unQualifiedCount;
			    				//alert(newtr);
			    					tb.append(newtr);
			    			}
			    			totalrow = "<tr><td colspan=9></td><td>数量汇总:" + totalCount+"</td><td></td><td>金额汇总" +totalCost+"</td><td>汇总:" + totalReceived+"</td><td>汇总:" +totalUnReceived+"</td><td>汇总:"+ totalQualified+"</td><td>汇总:"+totalUnqualified+"</td><td colspan=2></td>" ;
			    			tb.append(totalrow);
			    		}
			    	}
			    }
			});
		})
		*/
		
		
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
					},
					allowClear: true
				});
			}
		   
		   $(function(){
			   handleSupplierSelect('supplierId');
		   });
		</script>
	</tiles:putAttribute>	
</tiles:insertDefinition>