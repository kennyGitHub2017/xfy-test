<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set scope="request" var="sysModule" value="product" />
<c:set scope="request" var="sysPage" value="purchase_order_in" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="css-page">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>
	</tiles:putAttribute>
	<tiles:putAttribute name="title">采购入库单 </tiles:putAttribute>
	<tiles:putAttribute name="page-content">
		<div class="portlet-body form">
			<div class="portlet light ">
				<!-- BEGIN FORM-->
				<form:form id="data_form" modelAttribute="ioorder" action="${pageContext.request.contextPath}/stockin/add" cssClass="form-horizontal" role="form" method="post">
						<input type="hidden" name="token" value="${token}" />
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-3 control-label">运单号</label>
									<div class="col-md-4">
										<input type="text"  value="${purchaseOrder.waybillNo}" class="form-control" >
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-3 control-label">采购单号</label>
									<div class="col-md-7">
										<form:input path="buyOrderNo" id="buyOrderNo" cssClass="form-control"  readonly="true" />
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-3 control-label">入库单号</label>
									<div class="col-md-7">
										<form:input path="orderNo" cssClass="form-control" readonly="true"  />
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label for="dict_code" class="col-md-2 control-label">备注</label>
									<div class="col-md-5">
										<form:input path="note" cssClass="form-control" />
									</div>
								</div>
							</div>
						</div>
						
						<div class="table-scrollable">
							<table class="table table-hover table-striped table-bordered" id="purchasereqorderitem-table">
								<thead>
									<tr>
										<th>操作</th>
										<th>检验方式</th>
										<th>SKU</th>
										<th>名称</th>
										<th>描述</th>
										<th>单位</th>
										<th>采购数量</th>
										<th>已入库数量</th>
										<th>总重量</th>
										<th>检验数量</th>
										<th>合格数量</th>
										<th>入库仓库</th>
										<th>入库货位</th>
										<th>不合格数量</th>
										<th>不合格原因</th>
										<th>不合格入库货位</th>
										<th>合格率</th>
										<th>供应商</th>
										<th>采购员</th>
									</tr>
								</thead>
								<tbody id="tbody">
									<c:forEach items="${nostockList }" var="item" varStatus="o">
										<tr>
											<td><input type="button" itemdele="1" value="删除"></td>
											<td>
												<input type="hidden" name="remainCount" value="${item.remainCount}">
												<input type="hidden" name="itemId" value="${item.id}">
												<select name="itemInspect">
													<option value="0">全检</option>
													<option value="1">抽检</option>
												</select>
											</td>
											<td>${item.goodsSku } (${item.oldSku })<input type="hidden" name="itemSku" value="${ item.goodsSku}"></td>
											<td>${item.goodsName }<input type="hidden" name="itemName" value="${item.goodsName }" ></td>
											<td>${empty item.color?'':item.color}-${empty item.goodsSize?'':item.goodsSize}-${empty item.rules?'':item.rules}-${empty item.model?'':item.model}</td>
											<td>${item.goodsUnit }</td>
											<td>${item.goodsCount }
												<input type="hidden" name="buyCount" value="${item.goodsCount }" >
												<input type="hidden" name="itemCost" value="${item.goodsCost }" >
											</td>
											<td>${item.receivedCount}</td>
											<td><input type="text" name="skuWeight" style="width:60px"></td>
											<td><input type="text" name="testCount"  max="${item.goodsCount-item.receivedCount }" style="width:60px" value="${item.goodsCount-item.receivedCount}"></td>
											<td><input type="text" name="qualifiedCount" max="${item.goodsCount-item.receivedCount }" style="width:60px" value="${item.goodsCount-item.receivedCount }"></td>
											<td>
												<select name="storeId" id="storeId" idx="${o.index}" >
													<option value="">请选择</option>
													<c:forEach items="${storeList }" var="store">
														<!-- 过滤不合格仓库 -->
														<c:if test="${store.id!=142473}">
															<option value="${store.id }" <c:if test="${store.id==item.storeId }">selected="selected"</c:if> >${store.name }</option>
														</c:if>
													</c:forEach>
												</select>
											</td>
											<td>
												<select name="storeShelf" id="storeShelf${o.index}">
													<option value="${item.storeShelfId}">${item.storeShelf}</option>
												</select>
											</td>
											<td><input type='text' style='width:60px' name='unQualifiedCount' max="${item.goodsCount-item.receivedCount }" value='0' /></td>
											<td>
												<select name="reason" id="reason">
													<option value="">请选择</option>
													<c:forEach items="${dictList }" var="dict">
														<option value="${dict.code }">${dict.name }</option>
													</c:forEach>
												</select>
											</td>
											<td>
												<select name="unQualifiedStoreShelf" id="unQualifiedStoreShelf">
													<option value="">请选择</option>
													<c:forEach items="${unQualifiedStoreList }" var="shelf">
														<option value="${shelf.id }">${shelf.code }</option>
													</c:forEach>
												</select>
											</td>
											<td>100%</td>
											<td>${item.supplierName }</td>
											<td>${item.buyUserName }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div id="totaldiv" style="align:center;color:red;font-size:15px"></div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label for="dict_code" class="col-md-6 control-label">制单人:${ioorder.createdUserName}</label>
									<label for="dict_code" class="col-md-6 control-label">制单时间:<f:formatDate value="${ioorder.createdTime}"/></label>
								</div>
							</div>
						</div>
						<div class="form-actions">
							<div class="row">
								<div class="col-md-offset-3 col-md-9">
									<button type="submit" class="btn green" id="btn" >提交</button>
								</div>
							</div>
						</div>
				</form:form>
				</div>
			</div>
	</tiles:putAttribute>
	<tiles:putAttribute name="js-page">
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
		<script>
		var datavalid = true;
		$("input[max]").change(function(){
			var maxvalue = $(this).attr("max");
			var curvalue = $(this).val();
				var tr = $(this).closest('tr');
				var testCountValue = tr.children("td:eq(9)").children(0).val();
				var qualifiedCountValue = tr.children("td:eq(10)").children(0).val();
				var bhgValue = tr.children("td:eq(13)").children(0).val();
				if ($.trim(testCountValue)=="" || $.trim(qualifiedCountValue)=="" || $.trim(bhgValue)==""){
					alert("检验/合格/不合格 不能为空");
					datavalid = false;
					return;
				}
				if (isNaN(testCountValue) || isNaN(qualifiedCountValue) || isNaN(bhgValue) ){
					alert("检验/合格/不合格  请输入数字");
					datavalid = false;
					return;
				}
				if (parseInt(curvalue)<0 || parseInt(curvalue)>parseInt(maxvalue)){
					alert("请确定输入的数不大于" + maxvalue);
					datavalid = false;
					return;
				}
				var testCount = parseInt(testCountValue);
				var qualifiedCount = parseInt(qualifiedCountValue);
				var bhg = parseInt(bhgValue);
				//检验数量为0,不合格率无法计算  故不能提交
				if (testCount==0){
					alert("检验数量请输入正整数");
					datavalid = false;
					return;
				}
				/*
				if (testCount<qualifiedCount){
					alert("请确定合格数量不大于检验数量");
					datavalid = false;
					return;
				}
				*/
				if (bhg>0){
					var hgl = (1- bhg/testCount);
					tr.children("td:eq(16)").html(hgl.toFixed(4)*100+"%");
				}else{
					tr.children("td:eq(16)").html("100%");
				}
			//	var bhg = testCount - qualifiedCount;
			//	var s = "<input type='text' name='unQualifiedCount' style='width:60px' value=" + bhg +" />" ;
			//	tr.children("td:eq(10)").html(s);
				datavalid = true;
		});
		
		$("input[itemdele]").click(function(){
			var tr = $(this).closest('tr');
			tr.remove();
		})		
		
		//选择仓库，联动货位
	    $("select[name='storeId']").change(function(){
	    	var idx = $(this).attr("idx");
	    	$("#storeShelf"+idx).empty();
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
			    	xfy.fillSelect('#storeShelf'+idx, data, 'id', 'code');
			    }
			}); 
	    });
		
	    $("#data_form").submit(function(e){
	    	  var trList = $("#tbody tr");
	    	  var bhg = true;
	    	  trList.each(function(){
	    			  var tr = $(this);
	    			  var buyCount = tr.children("td:eq(6)").children(0).val();
	    			  var reason = tr.children("td:eq(14)").children(0).val();
	    			  var store = tr.children("td:eq(11)").children(0).val();
	    			  var storeShelf = tr.children("td:eq(12)").children(0).val();
	    			  var unQualifiedStoreShelf = tr.children("td:eq(15)").children(0).val();
	    			  var qualifiedCountValue = tr.children("td:eq(10)").children(0).val();  
	  				  var unQualifiedCountValue = tr.children("td:eq(13)").children(0).val();
	    			  var skuWeight = tr.children("td:eq(8)").children(0).val();
	  				  
	    			  if (store=="" || storeShelf==""){
	    				  alert("请选择合格仓库/合格货位");
						  bhg = false;
	    			  }
	  				  if (qualifiedCountValue=="0" && unQualifiedCountValue=="0"){
	  					  tr.remove();
	  				  }
	  				  if (unQualifiedCountValue !="0" && (reason=="" || unQualifiedStoreShelf=="")){
						alert("请选择不合格原因/不合极货位");
						bhg = false;
				  	  }
	  				  if (bhg && parseInt(qualifiedCountValue)+parseInt(unQualifiedCountValue)>parseInt(buyCount)){
	  					  alert("合格数量+不合格数量不能超出采购数量");
	  					  bhg = false;
	  				  }
	  				  if ($.trim(skuWeight)=="" || isNaN(skuWeight)){
	  					  alert("请输入正确的总重量");
	  					  bhg= false;
	  				  }
	  				  if (parseFloat(skuWeight)<=0){
	  					alert("请输入正确的总重量");
	  					bhg= false;
	  				  }
	    	  });
	    	  trList = $("#tbody tr");
	    	  if (trList.length==0){
	    		  alert("入库单至少需要填写一项sku信息");
	    		  return false;
	    	  }
	    	  return datavalid && bhg;
	    	});
		</script>
	</tiles:putAttribute>	
</tiles:insertDefinition>