<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>
<c:set scope="request" var="sysModule" value="product" />
<c:set scope="request" var="sysPage" value="allocate_order" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">调拨单</tiles:putAttribute>
	
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
									<span class="caption-subject font-green-sharp bold uppercase">调拨单</span>
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
											<div class="btn-group"><x:power type="function" code="allocate_order_add">
												<a class=" btn green" href="${pageContext.request.contextPath}/allocateOrder/form" data-target="#ajax" data-toggle="modal" data-backdrop="static">
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
										<label class="control-label col-md-4">调拨单号:</label>
										<div class="col-md-8">
											<input type="text" class=" col-md-3 form-control"  name="orderNo" />
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">SKU:</label>
										<div class="col-md-8">
											<input type="text" class="form-control"  name="goodsSku"  />
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">名称:</label>
										<div class="col-md-8">
											<input type="text" class="form-control"  name="name"  />
										</div>
									</div>
								</div>
								
							</div>
							
							<div class="row">
								<div class="col-md-4">
									<div class="form-group form-group-sm">
										<label class="control-label col-md-4">调拨人员:</label>
										<div class="col-md-8">
											<input type="text" class="form-control"  name="userName" />
										</div>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group form-group-sm">
									<label class="col-md-4 control-label">调出仓库:</label>
									<div class="col-md-8">
									<form:select path="fromStoreId"  cssClass="form-control" id="fromStoreId" onchange="storeShelf('fromStoreId','fromStoreShelf')">
										<option value="">请选择</option>
										<c:forEach items="${ storeList}" var="item">
										<option  value="${item.id}">${item.name }</option>
										</c:forEach>
									</form:select>
									</div>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group form-group-sm">
									<label class="col-md-4 control-label">调出货位:</label>
									<div class="col-md-8">
										<form:select path="fromStoreShelf"  cssClass="form-control" id="fromStoreShelf">
										</form:select>
										</div>
									</div>
								</div>
							
							</div>
							
							<div class="row">
								<div class="col-md-4">
								<div class="form-group form-group-sm">
								<label class="col-md-4 control-label">调入仓库:</label>
									<div class="col-md-8">
											<form:select path="toStoreId"  cssClass="form-control" id="toStoreId" onchange="storeShelf('toStoreId','toShoreShelf')">
													<option value="">请选择</option>
													<c:forEach items="${ storeList}" var="item">
													<option  value="${item.id}">${item.name }</option>
													</c:forEach>
											</form:select>
									</div>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group form-group-sm">
									<label class="col-md-4 control-label">调入货位:</label>
									<div class="col-md-8">
											<form:select path="toShoreShelf"  cssClass="form-control" id="toShoreShelf">
											</form:select>
										</div>
									</div>
								</div>
							</div>
							
							<div class="row">
							
								<div class="col-md-4">
									<div class="form-group form-group-sm">
									<label class="control-label col-md-4">产品大类:</label>
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
									<label class="control-label col-md-4">产品中类:</label>
									<div class="col-md-8">
									<form:select path="secondCategory" name="secondCategory" cssClass="form-control" id="secondCategory">
									</form:select>
									</div>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group form-group-sm">
									<label class="control-label col-md-4">产品小类:</label>
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
									<label class="control-label col-md-4">调拨日期(从):</label>
										<div class="col-md-8">
											<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
												<input name="transferDateFrom" class="form-control" /> <span class="input-group-btn">
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
									<label class="control-label col-md-4">调拨日期(到):</label>
										<div class="col-md-8">
											<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
												<input name="transferDateTo" class="form-control" /> <span class="input-group-btn">
													<button class="btn default btn-sm" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
											</div>
										</div>
									</div>
								</div>
								
								
								<div class="col-md-4 form-group-sm">
									<div class="form-group">
										<label class="control-label col-md-4"></label>
										<div class="col-md-8">
									<button type="button" id="searchbtn" class="btn green"><spring:message code="g.label.search" /></button>
									<button type="button" id="clearbtn" class="btn blue">清空</button>
										</div>
									</div>
								</div>
							</div>
							
							
							
									
								</form:form>
								
								<div class="table-scrollable">
									<table class="table table-hover table-striped table-bordered table-condensed" id="allocateorder-table">
									<thead>
									<tr>
										<th>
											调拨日期
										</th>
										<th>
											调拨单号
										</th>
										<th>
											流水号
										</th>
										<th>
											SKU
										</th>
										<th>
											名称
										</th>
										<th>
											单位
										</th>
										<th>
											数量
										</th>
										<th>
											调入仓库
										</th>
										<th>
											调入货位
										</th>
										<th>
											调出仓库
										</th>
										<th>
											调出货位
										</th>
										<th>
											调拨人
										</th>
										<th>
											备注
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
			<%@include file="/WEB-INF/view/include/modal-ajax.jsp" %>
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
						url:"${pageContext.request.contextPath }/allocateOrder/pageJson",
						contentType:"application/json;charset=UTF-8",	
						type: 'post',
						data: function (d) {
						var jsondata = $("#searchform").serializeObject();  //转化为json
						d.params = jsondata;
							return JSON.stringify( d );
						}
					},
					columns: [
						{data: "createdTime", 
							render: function ( data, type, full, meta ) {
								return data == null ? "" : new Date(data).format("yyyy-MM-dd");
							}
						},
						{data: "orderNo"},
						{data: "serialNumber"},
						{data: function(row) { return '{0} ({1})'.format(row.goodsSku, row.oldSku); } },
						{data: "name"},
						{data: "unit"},
						{data: "goodsCount"},
						{data: "toStore"},
						{data: "toShelfName"},
						{data: "fromStore"},
						{data: "fromShelfName"},
						{data: "userName"},
						{data: "note"}
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
		    
		    
		    //仓库选择
		    function storeShelf(storeId,shelftId){
		    	var p1 = "#"+storeId;
		    	var p2 = "#"+shelftId;
		    	$(p2).empty();
		    	if ($.trim($(p1).val())==''){
		    		return;
		    	}
				$.ajax({
				    url:'${pageContext.request.contextPath}/allocateOrder/getStoreShelf', 
				    type:'post',
				    data:'storeId='+$.trim($(p1).val()),     
				    async : false, //默认为true 异步     
				    error:function(){
				       alert('ajax error');     
				    }, 
				    success:function(data){
				    	var jsondata = eval("("+data+")");
				    	if(jsondata.result){
				    		var fromStoreShelf = jsondata.datas;
				    		$(p2).append("<option value=''>请选择</option>");
				    		for(var i=0;i<fromStoreShelf.length;i++){
				    			var newoption = "<option value=" +fromStoreShelf[i].id+">"+fromStoreShelf[i].code+"</option>";
				    			$(p2).append(newoption);
				    		}
				    	}
				    }
				}); 
		    }		    
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>