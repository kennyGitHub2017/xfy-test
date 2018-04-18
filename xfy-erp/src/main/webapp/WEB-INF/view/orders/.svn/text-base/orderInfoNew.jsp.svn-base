<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set scope="request" var="sysModule" value="order" />
<c:set scope="request" var="sysPage" value="order-list" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="css-page">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>
	</tiles:putAttribute>
	<tiles:putAttribute name="title">
		${not empty readonly ?'查看':'编辑' }订单
	</tiles:putAttribute>
	<tiles:putAttribute name="page-content">
		<div class="portlet-body form">
			<div class="portlet light ">
				<!-- BEGIN FORM-->
				<form:form id="data_form" modelAttribute="order" action="${pageContext.request.contextPath}/order/edit" cssClass="form-horizontal" role="form" method="post">
						<c:if test="${not empty order.id}">
							<form:hidden path="id"/>
							<form:hidden path="orderSn"/>
							<form:hidden path="buyinfo.id"/>
							<form:hidden path="buyinfo.orderSn"/>
						</c:if>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									第一步：地址输入完成后，请先将地址保存
								</div>
							</div>
						</div>
						<c:if test="${empty order.id}">
							<input type="hidden" name="order.payStatus"  value="Complete"  />
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label for="dict_code" class="col-md-4 control-label">订单平台</label>
										<div class="col-md-8">
											<form:select path="orderPlatform" id="orderPlatform" cssClass="form-control">
													<form:option value="">请选择</form:option>
													<form:option value="amazon">amazon</form:option>
													<form:option value="ebay">ebay</form:option>
													<form:option value="smt">smt</form:option>
											</form:select>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label for="dict_code" class="col-md-4 control-label">平台订单编号</label>
										<div class="col-md-8">
											<form:input path="orderSn" id="orderSn" cssClass="form-control" />
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label for="dict_code" class="col-md-5 control-label">Country Short Name</label>
										<div class="col-md-7">
											<form:input path="buyinfo.shippingCountry" id="shippingCountry" cssClass="form-control" />
										</div>
									</div>
								</div>
							</div>
						</c:if>
						
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">Full name</label>
									<div class="col-md-8">
										<form:input path="buyinfo.shippingName" id="shippingName" cssClass="form-control" /> 
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">Street1</label>
									<div class="col-md-8">
										<form:input path="buyinfo.shippingStreet1"   id="shippingStreet1" cssClass="form-control" /> 
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">Street2</label>
									<div class="col-md-8">
										<form:input path="buyinfo.shippingStreet2" id="shippingStreet2" cssClass="form-control" />
									</div>
								</div>
							</div>
						</div>
						
						
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">City</label>
									<div class="col-md-8">
										<form:input path="buyinfo.shippingCity"  id="shippingCity" cssClass="form-control" />  
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">State</label>
									<div class="col-md-8">
										<form:input path="buyinfo.shippingState" id="shippingState" cssClass="form-control" />  
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">Country</label>
									<div class="col-md-8">
										<form:input path="buyinfo.shippingCountryName" id="shippingCountryName" cssClass="form-control" />  
									</div>
								</div>
							</div>
						</div>
						
						
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">Postcode</label>
									<div class="col-md-8">
										<form:input path="buyinfo.shippingPostcode"  id="shippingPostcode" cssClass="form-control" />  
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
										<label for="dict_code" class="col-md-4 control-label">Tel</label>
										<div class="col-md-8">
												<form:input path="buyinfo.shippingPhone" id="shippingPhone" cssClass="form-control" /> 
										</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
										<label for="dict_code" class="col-md-4 control-label">User Mail</label>
										<div class="col-md-8">
												<form:input path="buyinfo.buyerEmail" id="buyerEmail" cssClass="form-control" />
										</div>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
										<label for="dict_code" class="col-md-4 control-label">客户ID</label>
										<div class="col-md-8">
												<form:input path="buyinfo.buyerUserId" id="buyerUserId" cssClass="form-control" />  
										</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">订单状态</label>
									<div class="col-md-8">
										<form:select path="orderStatus"  id="orderStatus" cssClass="form-control" disabled="true">
											<form:options  items="${orderstatusList }" itemLabel="name" itemValue="code" />  
										</form:select>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">总运费</label>
									<div class="col-md-8">
										<form:input path="shippingFee" id="shippingFee" cssClass="form-control" />
									</div>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
										<label for="dict_code" class="col-md-4 control-label">总金额</label>
										<div class="col-md-8">
												<form:input path="amount" id="amount" cssClass="form-control" />
												
										</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">币种</label>
									<div class="col-md-8">
										<form:select path="currency" id="currency" cssClass="form-control">
											<form:option value="">请选择</form:option>
											<form:options  items="${rateList }" itemLabel="currency" itemValue="currency" />
										</form:select>
										
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">付款时间</label>
									<div class="col-md-8">
										<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
												<input name="orderPaidTime" class="form-control" id="orderPaidTime" value="<f:formatDate value='${order.orderPaidTime }'/>" />
												<span class="input-group-btn">
													<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
												</span>
											</div>
									</div>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">发货时间</label>
									<div class="col-md-8">
										<div class="input-group " data-date-format="yyyy-mm-dd">
												<input name="shippedTime" class="form-control" id="shippedTime" readonly="readonly"  value="<f:formatDate value='${order.shippedTime }'/>" />
											</div>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
										<label for="dict_code" class="col-md-4 control-label">卖家账号</label>
										<div class="col-md-8">
												<%-- <form:select path="accountId" cssClass="form-control">
													<form:option value="">请选择</form:option>
													<form:options items="${accountIdList }" itemLabel="accountName" itemValue="id"  />
												</form:select> --%>
												<c:choose>
													<c:when test="${empty order.id }">
														 <form:select path="accountId" id="accountId" cssClass="form-control">
															<form:option value="">请选择</form:option>
															<form:options items="${accountIdList}" itemLabel="accountName" itemValue="id" />
														</form:select>
													</c:when>
													<c:otherwise>
														<%-- 此处禁止修改账号 --%>
														<form:input path="accountName" id="accountName" cssClass="form-control"  disabled="true" />
													</c:otherwise>
												</c:choose>
												
										</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">下单时间</label>
									<div class="col-md-8">
										<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
												<input name="orderSaleTime" class="form-control" id="orderSaleTime" value="<f:formatDate value='${order.orderSaleTime }'/>" />
												<span class="input-group-btn">
													<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
												</span>
											</div>
									</div>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">订单类型</label>
									<div class="col-md-8">
										<form:select path="orderType" id="orderType" cssClass="form-control">
											<form:option value="">请选择</form:option>
											<form:options items="${orderTypeList }" itemLabel="name" itemValue="code" />
										</form:select>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">交易号</label>
									<div class="col-md-8">
										<form:input path="paypaltransid" id="paypaltransid" cssClass="form-control" />
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">订单备注</label>
									<div class="col-md-8">
										<form:textarea path="note" cols="30" rows="4" />
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">运输方式</label>
									<div class="col-md-8">
										<select name="shippingName" id="shippingName" class="form-control">
												<option value="">请选择</option>
												<c:forEach items="${shippingList}" var="item">
													<option value="${item.shippingName}">${item.shippingName }</option>
												</c:forEach>
										</select>
									</div>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">平台运输方式</label>
									<div class="col-md-8">
										${order.platShippinMethod }
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">平台运费</label>
									<div class="col-md-8">
											${order.platShippingFee }
									</div>
								</div>
							</div>
							
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">买家留言</label>
									<div class="col-md-8">
											${order.buyerNote }
									</div>
								</div>
							</div>
						</div>
						
						<div class="table-scrollable">
							第二步：地址保存 完成后，请在添加订单中的产品
							<table class="table table-hover table-striped table-bordered" id="qorderitem-table">
								<thead>
									<tr>
										<td colspan="12">
											<div align="right">
												<c:if test="${empty readonly}">
													<input type="button" id="addBtn" onclick="addRow()" value="Add">
												</c:if>	
											</div>
										</td>
									</tr>
									<tr>
										<th>ItemId</th>
										<th>Item Sku</th>
										<th>名称</th>
										<th>价格</th>
										<th>数量</th>
										<th>金额</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="tbody">
										<c:forEach items="${order.items}" var="item" varStatus="status">
											<c:if test="${empty readonly}">
												<tr id="tr${status.index+1}">
													<td><input type="text"  value="${item.itemId}" name="itemId" />
														<input type="hidden"  value="${item.itemPic}" name="itemPic" />
														<input type="hidden"  value="${item.itemUrl}" name="itemUrl" />
														<input type="hidden"  value="${item.orderSn}" name="itemOrderSn" />
													</td>
													<td>
														<input type="text"  value="${empty item.sku ?item.itemSku:item.sku}" name="systemSku" />
														<input type="hidden"  value="${item.itemSku}" name="platsku" />
													</td>
													<td><input type="text"  value="${item.itemTitle}" name="itemTitle" /></td>
													<td><input type="text"  value="${item.itemPrice}" name="itemCost"  style="width:80px" onblur='totalInfo($(this))' /></td>
													<td><input type="text"  value="${item.itemQuantity}"  style="width:80px" name="itemCount" onblur='totalInfo($(this))' /></td>
													<td>${item.itemPrice*item.itemQuantity}</td>
													<td>
															<a href="javascript:DelRow(${status.index+1})">Remove</a>
													</td>
												</tr>
											</c:if>
											<!--  
											<c:if test="${ not empty readonly}">
												<tr id="tr${status.index+1}">
													<td>${item.itemId}</td>
													<td>${item.sku}</td>
													<td>${item.itemTitle}</td>
													<td>${item.itemPrice}</td>
													<td>${item.itemQuantity}</td>
													<td>${item.itemPrice*item.itemQuantity}</td>
													<td>
														&nbsp;
													</td>
												</tr>
											</c:if>
											-->
										</c:forEach>
								</tbody>
							</table>
							<br><br>
							订单操作日志：
							<table class="table table-hover table-striped table-bordered" id="qorderlog-table">
								<thead>
									<tr>
										<th width="10%">订单号</th>
										<th width="10%">操作人</th>
										<th width="10%">操作人姓名</th>
										<th width="15%">操作时间</th>
										<th>备注</th>
									</tr>
								</thead>
								<tbody>
										<c:forEach items="${orderLogList}" var="item" varStatus="status">
												<tr>
													<td>${item.orderId}</td>
													<td>${item.operUserId}</td>
													<td>${item.operUserName}</td>
													<td> <f:formatDate value="${item.operTime}"  pattern="yyyy-MM-dd HH:mm:ss"/> </td>
													<td>${item.log}</td>
												</tr>
										</c:forEach>
								</tbody>
							</table>
							<br><br>
							订单备注：
							<table class="table table-hover table-striped table-bordered" id="qordernote-table">
								<thead>
									<tr>
										<th width="25%">编号</th>
										<th width="25%">添加人</th>
										<th width="25%">添加时间</th>
										<th width="25%">备注</th>
									</tr>
								</thead>
								<tbody>
										<c:forEach items="${orderNoteList}" var="item" varStatus="status">
												<tr>
													<td>${item.id}</td>
													<td>${item.operUserId}</td>
													<td><f:formatDate value="${item.createdTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
													<td>${item.content}</td>
												</tr>
										</c:forEach>
								</tbody>
							</table>
							<br><br>
							备注:
							<form:textarea cssClass="form-control"  path="orderNote" cols="80"  rows="3"/>
						</div>
						<div id="totaldiv" style="color:red;font-size:12px"></div>
						<div class="form-actions">
							<div class="row">
								<div class="col-md-offset-3 col-md-9">
										<input type="submit" id="saveBtn" value="保存" class="btn green">
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
		var rowNum = 10000;	
		//datepicker show
		$('.date-picker').datepicker({
			orientation: "right",
			autoclose: true
		});
		
		$(function () {
			$('#data_form').validate({
				errorElement : 'span', //default input error message container
				errorClass : 'help-block help-block-error', // default input error message class
				focusInvalid : false, // do not focus the last invalid input
				ignore : "", // validate all fields including form hidden input
				rules : {
					<c:if test="${empty order.id}">
					'orderPlatform':{required : true},
					'orderSn' : {
						required : true,
						remote:{                                          //验证order_sn是否存在
				               type:"POST",
				               url:"${pageContext.request.contextPath }/order/check-ordersn.json",            
				               data:{
				                 orderSn:function(){return $.trim($("#orderSn").val());}
				               }
				        }
					},
					</c:if>
					'buyinfo.shippingName' : {
						required : true
					},
					'buyinfo.shippingStreet1':{
						required : true
					},
					'buyinfo.shippingCity' : {
						required : true,
					},
					'buyinfo.shippingState' : {
						required : true
					},	
					'buyinfo.shippingCountry':{
						required : true
					},
					'buyinfo.shippingCountryName':{
						required : true
					},
					'paypaltransid':{required : true},
					'buyinfo.shippingPostcode' : { required : true},
					'buyinfo.buyerUserId' : { required : true},
					'amount' : { required : true },
					'currency' : { required : true },
					'orderPaidTime' : { required : true },
					'orderSaleTime' : { required : true },
					'accountId' : { required : true },
					'itemId' : { required : true },
					'systemSku' : { required : true },
					'itemTitle' : { required : true },
					'itemCost' : { required : true ,number:true},
					'itemCount' : { required : true,digits:true }
				},
				highlight : function(element) { // hightlight error inputs
					$(element).parent().addClass('has-error'); // set error class to the control group
				},
				unhighlight : function(element) { // revert the change done by hightlight
					$(element).parent().removeClass('has-error'); // set error class to the control group
				},
				success : function(label) {
					label.parent().removeClass('has-error'); // set success class to the control group
				},
				messages:{
					'orderSn':{remote:$.validator.format("平台订单编号已存在")}
				},
				submitHandler:function(form){
					ajaxSubmit();
					return false;
				}
			});
		});
			
		
		var skuerror = false;
		function   ajaxSubmit(){
			if ($('#data_form').valid()){
				var tablerow = $("#tbody").find("tr").length;
				if (tablerow<1){
					alert("请输入订单行数据");
					return false;
				}
				/*
				var skuerror = false;
				$("#tbody").find("tr").each(function(){
					var sku = $(this).children("td:eq(1)").children(0).val();
					if ($.trim(sku)==""){
						skuerror =true;
					}
				});
				if (skuerror){
					alert("订单sku不能为空");
					return false;
				}
				*/
				$("#orderStatus").attr("disabled",false);
				var jsondata = $("#data_form").serializeArray();  //转化为json
				console.log(jsondata);
				$("#saveBtn").attr("disabled", "disabled");
				
				$.ajax({
				    url:'${pageContext.request.contextPath}/order/edit',     
				    type:'post',
				    data:jsondata,     
				    async : false, //默认为true 异步    
				    error:function(XMLHttpRequest, textStatus, errorThrown){
				    	alert(XMLHttpRequest.status+"\t"+XMLHttpRequest.readyState+"\t"+textStatus);
				    },
				    success:function(data){
				    	var jsondata = eval("("+data+")");
				    	if(jsondata.result){
				    		alert("订单操作成功");
				    		window.close();
				    	}else{
				    		alert(jsondata.error);
				    	}
				    }
				});
				$("#saveBtn").removeAttr("disabled");
			}
		};
		
		function totalInfo(ele){
			var tr = ele.closest('tr');
			var priceTd = tr.children("td:eq(3)").children(0);
			var quantityTd = tr.children("td:eq(4)").children(0);
			var amountTd = tr.children("td:eq(5)");
			/*
			if (0==ele.val().length || isNaN(ele.val())){
				alert("请输入数字");
				amountTd.text("");
				$("#totaldiv").html("");
				return;
			}
			*/
			/*
			if (0==priceTd.val().length || isNaN(priceTd.val())){
				alert("请输入数字");
				return;
			}
			if (0==quantityTd.val().length || isNaN(quantityTd.val())){
				alert("请输入数字");
				return;
			}
			*/
			if (priceTd.val().length>0 && !isNaN(priceTd.val()) && quantityTd.val().length>0 && !isNaN(quantityTd.val())){
				var temp = parseFloat(priceTd.val())*parseFloat(quantityTd.val());
				amountTd.text(temp.toFixed(2));
				totalAmount();	
			}else{
				amountTd.text("");
				$("#totaldiv").html("");
			}
		}
		
		function totalAmount(){
			var totalaMount =0;
			$("#tbody").find("tr").each(function(){
				var amountTd = $(this).children("td:eq(5)");
				totalaMount +=parseFloat(amountTd.text());
			});
			if (totalaMount>0){
				$("#totaldiv").html("<span style='color:red'>汇总金额:" + totalaMount.toFixed(2)+"</span>");	
			}
		}
		
		function addRow(){
			var tablerow = $("#tbody").find("tr").length+1;
			var newtr= $("<tr id='tr{0}''></tr>".format(tablerow));
			//alert(newtr);
			var td0 = "<input type='text' id='itemId_{0}'  name='itemId'  /><input type='hidden'  value='' name='itemPic' /><input type='hidden'  value='' name='itemUrl' /><input type='hidden'  value='{1}' name='itemOrderSn' />".format(rowNum,'${order.orderSn}');
			var td1 = "<input type='text' id='systemSku_{0}' name='systemSku' onblur='goodsInfo($(this))' /><input type='hidden'  value='' name='platsku' />".format(rowNum);
			var td2 = "<input type='text' id='itemTitle_{0}' name='itemTitle'  />".format(rowNum);
			var td3 = "<input type='text' id='itemCost_{0}' name='itemCost' style='width:80px' onblur='totalInfo($(this))' />".format(rowNum);
			var td4 = "<input type='text' id='itemCount_{0}' style='width:80px' name='itemCount' onblur='totalInfo($(this))'  />".format(rowNum);
			newtr.append("<td>"+td0+"</td>");
			newtr.append("<td>"+td1+"</td>");
			newtr.append("<td>"+td2+"</td>");
			newtr.append("<td>"+td3+"</td>");
			newtr.append("<td>"+td4+"</td>");
			newtr.append("<td></td>");
			newtr.append("<td>"+"<a href='javascript:DelRow({0})'>Remove</a>".format(tablerow)+"</td>");
			$("#tbody").append(newtr);
			rowNum++;
		}
		
		function DelRow(id){
			var p1 = "#tr"+id;
			var tr = $(p1).closest('tr').remove();
			totalAmount();
		}
		
		function goodsInfo(skuId){
			if ($.trim(skuId.val())==''){
				return;
			}
			var data ='sku='+skuId.val();
			$.ajax({
				 url:xfy.contextPath+'/purchaserequest-order/goodsInfo', 
			    type:'post',
			    data:data,     
			    async : false, //默认为true 异步     
			    error:function(){
			       alert('ajax error');
			    }, 
			    success:function(data){
			    	var jsondata = eval("("+data+")");
			    	if(!jsondata.result){
			    		alert("sku错误");
			    		skuId.val("");
			    	}
			    }
			}); 
		}
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>