<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">${not empty readonly ?'查看':'编辑' }订单(订单编号：${order.id })</h4>
</div>
		<div class="modal-body">
			<div class="portlet light ">
				<!-- BEGIN FORM-->
				<form:form id="data_form" modelAttribute="order" action="${pageContext.request.contextPath}/order/edit" cssClass="form-horizontal form-group-sm" role="form" method="post">
						<form:hidden path="id"/>
						<form:hidden path="orderSn"/>
						<form:hidden path="buyinfo.id"/>
						<form:hidden path="buyinfo.orderSn"/>
						<form:hidden path="printedFlag"/>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<h4>第一步：地址输入完成后，请先将地址保存</h4>
								</div>
							</div>
						</div>
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
										<form:input path="buyinfo.shippingStreet1" id="shippingStreet1" cssClass="form-control" />
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
										<form:input path="buyinfo.shippingCity" id="shippingCity" cssClass="form-control" />
									</div>
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<label for="dict_code" class="col-md-3 control-label">State</label>
									<div class="col-md-8">
										<form:input path="buyinfo.shippingState" id="shippingState" cssClass="form-control" />
									</div>
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
									<label for="dict_code" class="col-md-5 control-label">国家缩写</label>
									<div class="col-md-7">
										<form:input path="buyinfo.shippingCountry" id="shippingCountry" cssClass="form-control" />
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">国家</label>
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
										<form:input path="buyinfo.shippingPostcode" id="shippingPostcode" cssClass="form-control" />
									</div>
								</div>
							</div>
							<div class="col-md-2">
								<div class="form-group">
										<label for="dict_code" class="col-md-4 control-label">Mobile</label>
										<div class="col-md-8">
												<form:input path="buyinfo.shippingMobile" id="shippingMobile" cssClass="form-control" />
										</div>
								</div>
							</div>
							<div class="col-md-2">
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
										<form:select path="orderStatus" id="orderStatus" cssClass="form-control" disabled="true">
											<form:option value="">请选择</form:option>
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
													<button class="btn default btn-sm" type="button"><i class="fa fa-calendar"></i></button>
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
													<form:options items="${accountIdList}" itemLabel="accountName" itemValue="id" />
												</form:select> --%>
												<%-- 此处禁止修改账号 --%>
												<form:input path="accountName" cssClass="form-control" disabled="true" />
										</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">跟踪号</label>
									<div class="col-md-8">
										<form:input path="trackNumber" id="trackNumber" cssClass="form-control" />
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
										<label for="dict_code" class="col-md-4 control-label">包裹计算重量</label>
										<div class="col-md-8">
												<form:input path="calcWeight" id="calcWeight" cssClass="form-control"/>
										</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">包裹实际重量</label>
									<div class="col-md-8">
										<form:input path="packageWeight" id="packageWeight" cssClass="form-control" readonly="true"/>
										<span style="color:red">▲电子称同步重量</span>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">订单备注</label>
									<div class="col-md-8">
										<form:textarea path="note" cols="25" rows="4" />
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">运输方式</label>
									<div class="col-md-8">
										<select name="shippingName" class="form-control">
												<option value="">请选择</option>
												<c:forEach items="${shippingList}" var="item">
													<option  value="${item.shippingName}" 
														<c:if test="${order.shippingName==item.shippingName}">selected="selected"</c:if>
													>
														${item.shippingName }
													</option>
												</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">Paypal交易号</label>
									<div class="col-md-8">
										<form:input path="paypaltransid" readonly="true"/>
									</div>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">平台运输方式</label>
									<div class="col-md-8">
										<div class="form-control">
											${order.platShippinMethod }	
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">平台运费</label>
									<div class="col-md-8">
										<div class="form-control">
											${order.platShippingFee }
										</div>
									</div>
								</div>
							</div>
							
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">买家留言</label>
									<div class="col-md-8">
											<div class="form-control">
												${order.buyerNote }
											</div>
									</div>
								</div>
							</div>
						</div>
						<div>
							<h4>第二步：地址保存 完成后，请在添加订单中的产品</h4>
							<div class="row text-right"><input type="button" id="addBtn" onclick="orderEdit.addRow()" value="Add"></div>
							<table class="table table-bordered table-condensed" id="qorderitem-table">
								<thead>
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
														<input type="hidden"  value="${item.ebayFee}" name="platFee" />
														<input type="hidden"  value="${item.orderLimitId }" name="orderLimitId" >
													</td>
													<td>
														<input type="text"  value="${empty item.sku ?item.itemSku:item.sku}" name="systemSku" />
														<input type="hidden"  value="${item.itemSku}" name="platsku" />
													</td>
													<td><input type="text"  value="${item.itemTitle}" name="itemTitle" /></td>
													<td><input type="text"  value="${item.itemPrice}" name="itemCost"  style="width:80px" onblur='orderEdit.totalInfo($(this))' /></td>
													<td><input type="text"  value="${item.itemQuantity}"  style="width:80px" name="itemCount" onblur='orderEdit.totalInfo($(this))' /></td>
													<td>${item.itemPrice*item.itemQuantity}</td>
													<td>
															<a href="javascript:orderEdit.DelRow(${status.index+1})">Remove</a>
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
							<h5>订单操作日志</h5>
							<table class="table table-bordered table-condensed" id="qorderlog-table">
								<thead>
									<tr>
										<th width="10%">订单号</th>
										<th width="10%">操作人</th>
										<th width="10%">操作人姓名</th>
										<th width="15%">操作时间</th>
										<th>备注</th>
									</tr>
								</thead>
								<tbody id="tbody2">
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
							<h5>订单备注</h5>
							<table class="table table-bordered table-condensed" id="qordernote-table">
								<thead>
									<tr>
										<th width="25%">编号</th>
										<th width="25%">添加人</th>
										<th width="25%">添加时间</th>
										<th width="25%">备注</th>
									</tr>
								</thead>
								<tbody id="tbody3">
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
		
		<script>
		var rowNum = 10000;
		(function(w) {
			w.orderEdit = {};
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
						'buyinfo.shippingPostcode' : { required : true},
						'buyinfo.buyerUserId' : { required : true},
						'amount' : { required : true },
						'currency' : { required : true },
						'orderPaidTime' : { required : true },
						'accountId' : { required : true },
						'itemId' : { required : true },
						'systemSku' : { required : true },
						'itemTitle' : { required : true },
						'itemCost' : { required : true ,number:true},
						'itemCount' : { required : true,digits:true }
					},
					highlight : function(element) { // hightlight error inputs
						$(element).parent().removeClass('has-error');
						$(element).parent().addClass('has-error'); // set error class to the control group
						
					},
					unhighlight : function(element) { // revert the change done by hightlight
						$(element).parent().removeClass('has-error'); // set error class to the control group
					},
					success : function(label) {
						label.parent().removeClass('has-error'); // set success class to the control group
					},
					submitHandler:function(form){
						ajaxSubmit();
						return false;
					}
				});
			});
			
			
			function ajaxSubmit(){
				if ($('#data_form').valid()){
					var orderStatus = "${order.orderStatus}";
					var orderSuspend = "${order.suspend}";
					var tablerow = $("#tbody").find("tr").length;
					if (tablerow<1){
						alert("请输入订单行数据");
						return false;
					}
					if (orderStatus!="1" && orderSuspend!="1"){
						alert("修改已审核订单,必须先暂停该订单");
						return false;
					}
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
					    		tb.api().ajax.reload();
					    	}else{
					    		alert(jsondata.error);	
					    	}
					    	$("#modal-large").modal('hide');
					    }
					});
					$("#saveBtn").removeAttr("disabled");
				}
			}
			
			orderEdit.totalInfo = function(ele){
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
			
			orderEdit.DelRow = function(id){
				var p1 = "#tr"+id;
				var tr = $(p1).closest('tr').remove();
				totalAmount();
			}
			
			orderEdit.addRow = function(){
				var tablerow = $("#tbody").find("tr").length+1;
				//如果订单没有任何sku行数据
				if (tablerow==1){
					addNewRow();
				}
				var tr = $("#tr"+(tablerow-1));
				var newtr = tr.clone(true);
				newtr.attr("id","tr"+tablerow);
				var td0 = "<input type='text' id='itemId_{0}'     name='itemId'  /><input type='hidden'  value='' name='itemPic' /><input type='hidden'  value='' name='itemUrl' /><input type='hidden'  value='{1}' name='itemOrderSn' /><input type='hidden'  value='' name='platFee' /><input type='hidden'  value='' name='orderLimitId' />".format(rowNum,'${order.orderSn}');
				var td1 = "<input type='text' id='systemSku_{0}'  name='systemSku' onblur='orderEdit.goodsInfo($(this))' /><input type='hidden'  value='' name='platsku' />".format(rowNum);
				var td2 = "<input type='text' id='itemTitle_{0}'  name='itemTitle'  />".format(rowNum);
				var td3 = "<input type='text' id='itemCost_{0}'   name='itemCost'  style='width:80px' onblur='orderEdit.totalInfo($(this))' />".format(rowNum);
				var td4 = "<input type='text' id='itemCount_{0}'  name='itemCount' style='width:80px'  onblur='orderEdit.totalInfo($(this))'  />".format(rowNum);
				newtr.children("td:eq(0)").html(td0);
				newtr.children("td:eq(1)").html(td1);
				newtr.children("td:eq(2)").html(td2);
				newtr.children("td:eq(3)").html(td3);
				newtr.children("td:eq(4)").html(td4);
				newtr.children("td:eq(5)").html("");
				newtr.children("td:eq(6)").html("<a href='javascript:orderEdit.DelRow({0})'>Remove</a>".format(tablerow));
				$(newtr).insertAfter(tr);
				rowNum++;
			}
			
			//根据sku异步获取产品信息
			orderEdit.goodsInfo = function(skuId){
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
			
		})(window);
		
		function addNewRow(){
			var tablerow = $("#tbody").find("tr").length+1;
			var newtr= $("<tr id='tr{0}''></tr>".format(tablerow));
			//alert(newtr);
			var td0 = "<input type='text'  id='itemId_{0}'  name='itemId'  /><input type='hidden'  value='' name='itemPic' /><input type='hidden'  value='' name='itemUrl' /><input type='hidden'  value='{1}' name='itemOrderSn' />".format(rowNum,'${order.orderSn}');
			var td1 = "<input type='text'  id='systemSku_{0}'  name='systemSku' onblur='orderEdit.goodsInfo($(this))' /><input type='hidden'  value='' name='platsku' />".format(rowNum);
			var td2 = "<input type='text'  id='itemTitle_{0}'  name='itemTitle'  />".format(rowNum);
			var td3 = "<input type='text'  id='itemCost_{0}'  name='itemCost'  style='width:80px' onblur='orderEdit.totalInfo($(this))' />".format(rowNum);
			var td4 = "<input type='text'  id='itemCount_{0}'  name='itemCount' style='width:80px' onblur='orderEdit.totalInfo($(this))'  />".format(rowNum);
			newtr.append("<td>"+td0+"</td>");
			newtr.append("<td>"+td1+"</td>");
			newtr.append("<td>"+td2+"</td>");
			newtr.append("<td>"+td3+"</td>");
			newtr.append("<td>"+td4+"</td>");
			newtr.append("<td></td>");
			newtr.append("<td>"+"<a href='javascript:orderEdit.DelRow({0})'>Remove</a>".format(tablerow)+"</td>");
			$("#tbody").append(newtr);
			rowNum++;
		}
		</script>
