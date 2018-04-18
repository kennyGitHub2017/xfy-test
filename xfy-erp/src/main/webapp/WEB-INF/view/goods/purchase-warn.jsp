<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set scope="request" var="sysModule" value="purchase" />
<c:set scope="request" var="sysPage" value="purchase_warn" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">库存预警</tiles:putAttribute>
	
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
									<span class="caption-subject font-green-sharp bold uppercase">库存预警</span>
								</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"></a>
								</div>
							</div>
							<div class="portlet-body">
								<%@include file="../include/message.jsp" %>
								<form:form id="searchform" modelAttribute="search" method="post" class="form-horizontal" role="form" search="1">
									<div class="form-group form-group-sm">
											<label class="col-md-1 control-label">产品大类</label>
										<div class="col-md-2">
											<form:select path="firstCategory" name="firstCategory"  cssClass="form-control" id="firstCategory">
												<option value="">请选择</option>
												<c:forEach items="${ categoryList}" var="item">
														<option  value="${item.id}" <c:if test="${item.id==search.firstCategory }">selected="selected"</c:if> >${item.name }</option>
												</c:forEach>
											</form:select>
										</div>
										<label class="col-md-1 control-label">产品中类</label>
										<div class="col-md-2">
											<form:select path="secondCategory" name="secondCategory" cssClass="form-control" id="secondCategory">
												<option value="">请选择</option>
												<c:forEach items="${ secondCategoryList}" var="item">
														<option  value="${item.id}" <c:if test="${item.id==search.secondCategory }">selected="selected"</c:if> >${item.name }</option>
												</c:forEach>
											</form:select>
										</div>
										<label class="col-md-1 control-label">产品小类</label>
										<div class="col-md-2">
											<form:select path="thirdCategory" name="thirdCategory" cssClass="form-control" id="thirdCategory">
												<option value="">请选择</option>
												<c:forEach items="${ thirdCategoryList}" var="item">
														<option  value="${item.id}" <c:if test="${item.id==search.thirdCategory }">selected="selected"</c:if> >${item.name }</option>
												</c:forEach>
											</form:select>
										</div>
										
										<label class="col-md-1 control-label">SKU</label>
										<div class="col-md-2">
											<input type="text" class="form-control"  name="goodsSku"  id="goodsSku_id"/>
										</div>
									</div>
									<div class="form-group form-group-sm">
									
											<label class="col-md-1 control-label">获取规则</label>
										<div class="col-md-2">
											<form:select path="priorityRule" name="priorityRule" id="priorityRule"  cssClass="form-control">
												<option value="0" <c:if test="${0==search.priorityRule }">selected="selected"</c:if> >优先级</option>
												<option value="1" <c:if test="${1==search.priorityRule }">selected="selected"</c:if> >采购周期优先</option>
												<option value="2" <c:if test="${2==search.priorityRule }">selected="selected"</c:if> >价格优先</option>
											</form:select>
										</div>
										
										<label class="col-md-1 control-label">状态</label>
										
										<div class="col-md-2">
										<select class="form-control" name="goodsStatus" id="goodsStatus_id">
										<option value="">请选择...</option>
										<c:forEach var="item" items="${goodsStatus}">
										<option value="${item.code }">${item.name }</option>
										</c:forEach>
										</select>
										</div>
										
										
										<label class="col-md-1 control-label">供应商</label>
										
										<div class="col-md-2">
											<input type="text" class="form-control c-supplier-picker input-sm"  data-placeholder="${search.supplierName == null ? '请选择...' : search.supplierName}" />
											<form:hidden path="supplierId" id="supplierId" />
										</div>
										
										<label class="col-md-1 control-label">采购员</label>
										<div class="col-md-2">
											<input type="text" class="form-control" id="purchaseBuyer" name="purchaseBuyer" />
										</div>
									</div>
									
									<div class="table-toolbar">
												<button type="button" id="searchbtn" class="btn green"><spring:message code="g.label.search" /></button>
												<button type="button" id="clearbtn" class="btn blue">清空</button>
												<button type="button" id="generateOrderReq" class="btn green">生成请购单</button>
									</div>
								</form:form>
								
								<table class="table table-hover table-striped table-bordered" id="allocateorder-table">
									<thead>
									<tr>
										<th>
											<input type="button" data-selector="input[name='suplierId']"  class="c-reserve-select" value="select">
										</th>
										<th>
											sku
										</th>
										<th>
											开发时间
										</th>
										<th>
											采购员
										</th>
										<th>
											名称
										</th>
										<th>描述</th>
										<th>
											单位
										</th>
										<th>
											类别
										</th>
										<th>
											成本/最新价
										</th>
										<th>状态</th>
										<th>
											30天
										</th>
										<th>
											15天
										</th>
										<th>
											7天
										</th>
										<th>
											实际库存
										</th>
										<th>
											在途
										</th>
										<th>
											下单数量
										</th>
										<!--
										<th>
											锁定数
										</th>
										<th>
											可用库存
										</th>
										-->
										<th>
											销售占用
										</th>
										<th>
											建议采购
										</th>
										<th>
											实际采购
										</th>
										<th>
											周期
										</th>
										<th>
											建议价
										</th>
										<th>
											建议供应商
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
	</tiles:putAttribute>
	
		
	<%-- 页面级别的 JS --%>
	<tiles:putAttribute name="js-page">
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/select2/select2.min.js" type="text/javascript"></script>
		<script>
		/*
		$("#searchbtn").click(function(){
			$("#searchform").attr("action","${pageContext.request.contextPath }/purchase-warn/list").submit();
		});
		*/
		
		 $(function() {
			 	var url = "${pageContext.request.contextPath }/user/getUserByDeparment.json";
			    $( "#purchaseBuyer" ).autocomplete({
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
		
		var tb ;
		$("#searchbtn").click(function(){
			tb.api().ajax.reload();
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
					url:"${pageContext.request.contextPath }/purchase-warn/pageJson",
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
				    	 var ret = "<input type='checkbox' name='suplierId' idx='{0}' value='{1}'>".format(data.goodsSku,data.supplierId);
						 ret = ret + "<input type='hidden' name='sku' id='{0}' value='{1}'>".format("sku"+data.goodsSku,data.goodsSku);	
						 ret = ret + "<input type='hidden' name='name' id='{0}' value='{1}'>".format("name"+data.goodsSku,data.name); 	
						 ret = ret + "<input type='hidden' name='unit' id='{0}' value='{1}'>".format("unit"+data.goodsSku,data.unit);
						 ret = ret + "<input type='hidden' name='category' id='{0}' value='{1}'>".format("category"+data.goodsSku,data.categoryId);	
						 ret = ret + "<input type='hidden' name='price' id='{0}' value='{1}'>".format("price"+data.goodsSku,data.suggestBuyprice);	
						 ret = ret + "<input type='hidden' name='actualPurchase' id='{0}' value='{1}'>".format("actualPurchase"+data.goodsSku,data.actualPurchase);
						return ret;	
					}},
					{data: "goodsSku"},
					{data: function(data){
						return data.developTime?new Date(data.developTime).format("yyyy-MM-dd"):"";
					}},
					{data:"purchaseBuyer"},
					{data: "name"},
					{data: function(data){
						return "{0}-{1}-{2}-{3}".format(data.color,data.goodsSize,data.model,data.rules);
					}},
					{data: "unit"},
					{data: "categoryName"},
					{data: function(data){
						return data.cost+"/" + (data.newCost==null?"":data.newCost);
					}},
					{data: "goodsStatus"},
					{data: "sales30"},
					{data: "sales15"},
					{data: "sales7"},
					{data: "allCount"},
					{data: "purchaseCount"},
					{data: "placeOrderCount"},
					//{data: "lockCount"},
					//{data: "availableCount"},
					{data: "unOutstoreSale"},
					{data: function(data){
						return data.suggestPurchase-data.placeOrderCount;
					}},
					{data: function(data){
						return "<input type='text' style='width:60px' name='actualPurchase' value=" +(data.actualPurchase-data.placeOrderCount) + " onchange='setValue(this.value,{0})' />".format(data.goodsSku);
					}},
					{data: "buyPeriod"},
					{data: "suggestBuyprice"},
					{data: "suggestSupplier"},
				]
			});
		}
		
		
		$("#generateOrderReq").click(function(){
			var suplierAry =[],nameAry=[],skuAry=[],unitAry=[],categoryAry=[],priceAry=[],actualPurchaseAry=[];
			$("input[name=suplierId]").each(function(i){
				var idx = $(this).attr("idx");
				if($(this).attr("checked")){
					var curValue = $(this).val();
					if ($.trim(curValue)!=''){
						suplierAry.push(curValue);
						nameAry.push($("#name"+idx).val().replace(/[,&]/g, " "));
						skuAry.push($("#sku"+idx).val());
						unitAry.push($("#unit"+idx).val());
						categoryAry.push($("#category"+idx).val());
						priceAry.push($("#price"+idx).val());
						actualPurchaseAry.push($("#actualPurchase"+idx).val());
					}
				}
			});
			if (suplierAry.length==0){
				alert("生成采购单失败，所选sku均没有供应商");
				return;
			}
			var postdata ='suplierId='+suplierAry.join(",")+"&sku="+skuAry.join(",")+"&name="+nameAry.join(",")+"&unit="+unitAry.join(",")+"&category="+categoryAry.join(",")+"&price="+priceAry.join(",")+"&actualPurchase="+actualPurchaseAry.join(",");
			$(this).attr("disabled","disabled");
			$.ajax({
			    url:'${pageContext.request.contextPath}/purchase-warn/generate-reqorder.json',     
			    type:'post',
			    data:postdata,     
			    async : false, //默认为true 异步     
			    error:function(){
			       alert('ajax error');     
			    }, 
			    success:function(jsondata){
			    //	var jsondata = eval("("+data+")");
			    //	alert(jsondata.result);
			    	if(jsondata.result){
			    		alert("成功生成请购单");
			    		tb.api().ajax.reload();
			    		/*
			    		var fc = $("#firstCategory").val();
			    		var sc = $("#secondCategory").val();
			    		var tc = $("#thirdCategory").val();
			    		var p = $("#priorityRule").val();
			    		var gu = $("#goodsSku_id").val();
			    		var gs = $("#goodsStatus_id").val();
			    		window.location.href=window.location.href+"?firstCategory="+fc+"&secondCategory="+sc+"&thirdCategory="+tc+"&priorityRule="+p+"&goodsSku="+gu+"&goodsStatus="+gs;
			    		*/
			    	}else{
			    		alert(jsondata.error);
			    	}
			    }
			});
			$(this).removeAttr("disabled");
		});
		
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
			
			function setValue(actualPurchase,idx){
			//	alert(actualPurchase+"\t" + idx);
				$("#actualPurchase"+idx).val(actualPurchase);
			//	alert($("#actualPurchase"+idx).val());
				//alert($("#actualPurchase"+idx).val());
			}
			
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