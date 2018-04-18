<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set scope="request" var="sysModule" value="product" />
<c:set scope="request" var="sysPage" value="io_order_${order.type }" />

<c:set var="ioOrderTypeDesc" value="${order.type == 0 ? '入库' : '出库' }"></c:set>
<c:set var="pageTitle" value="添加${ioOrderTypeDesc }单" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">${pageTitle }</tiles:putAttribute>
	<tiles:putAttribute name="page-content">
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-cogs font-green-sharp"></i> <span class="caption-subject font-green-sharp bold uppercase">${pageTitle }</span>
						</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"></a>
						</div>
					</div>
					<div class="portlet-body">
						<%@include file="/WEB-INF/view/include/message.jsp" %>
						
						
	<form:form cssClass="form-horizontal" role="form" method="post"  action="${pageContext.request.contextPath}/io-order/save" id="io_order_form" modelAttribute="order">
	
							<input type="hidden" name="id" value="${order.id }" />
							<input type="hidden" name="type" value="${order.type }" />
							<fieldset>
								<legend>
								基本信息
						<button type="button" class="btn btn-primary" data-dismiss="alert" onclick="printOrder();">打印</button>
								</legend>
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-4">${ioOrderTypeDesc}单号</label>
											<div class="col-md-8">
												<input type="text" readonly="readonly" class="form-control" name="orderNo" value="${order.orderNo}">
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-4">${ioOrderTypeDesc}流水号 </label>
											<div class="col-md-8">
												<input type="text" readonly="readonly" class="form-control" name=serialNumber" value="${order.serialNumber}">
											</div>
										</div>
									</div>
									 <div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-4">${ioOrderTypeDesc}日期</label>
											<div class="col-md-8">
												<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
													<form:input path="ioDate" maxlength="10" cssClass="form-control" />
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
											<label class="control-label col-md-4">${ioOrderTypeDesc}类型</label>
											<div class="col-md-8">
												<select class="form-control" name="typeDetail">
													<option value="">请选择...</option>
													<c:choose>
														<c:when test="${order.type == '0'}">
															<c:forEach var="item" items="${typeAttr}">
																<c:if test="${item.code > 50}">
																	<option value="${item.code}" ${order.typeDetail == item.code ? 'selected' : '' }>${item.name}</option>
																</c:if>
															</c:forEach>
														</c:when>
														<c:when test="${order.type == '1'}">
															<c:forEach var="item" items="${typeAttr}">
																<c:if test="${item.code < 50}">
																	<option value="${item.code}" ${order.typeDetail == item.code ? 'selected' : '' }>${item.name}</option>
																</c:if>
															</c:forEach>
														</c:when>
													</c:choose>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-4">备注</label>
											<div class="col-md-8">
												<input type="text" class="form-control" name="note" value="${order.note}">
											</div>
										</div>
									</div>
								</div>
							</fieldset>
							<fieldset>
								<legend>
									物品信息
									<button type="button" class="btn btn-primary" data-dismiss="alert" onclick="addEditRow();">添加</button>
								</legend>
								<table class="table table-hover table-bordered table-condensed" id="IoOrder_add_table">
									<thead>
										<tr id="info_tr">
											<th>SKU</th>
											<th>旧SKU</th>
											<th>名称</th>
											<th>库存</th>
											<th>锁定库存</th>
											<th>成本价</th>
											<th>数量</th>
											<th>仓库</th>
											<th>货位</th>
											<th></th>
										</tr>
									</thead>
									<tbody id="info_tb">
										<c:forEach items="${order.items}" var="item" varStatus="status">
											<tr>
												<td><input name="goodsSku" value="${item.goodsSku }" class="form-control input-sm" id="goodsSku_${status.index }" readonly="readonly" size="11" /></td>
												<td><input name="oldSku" value="${item.oldSku }" class="form-control input-sm" id="oldSku_${status.index }" readonly="readonly" size="5" /></td>
												<td><input name="goodsName" value="${item.goodsName }" class="form-control input-sm" id="goodsName_${status.index }" readonly="readonly" /></td>
											
												<td><input name="inventory" value="" class="form-control input-sm" id="" readonly="readonly" size="5" /></td>
												<td><input name="inventoryLock" value="" class="form-control input-sm" id="" readonly="readonly" size="5" /></td>
												
												<td><input name="goodsCost" value="${item.goodsCost }" class="form-control input-sm" id="goodsCost_${status.index }" size="5" /></td>
												<td><input name="goodsCount" value="${item.qualifiedCount }" class="form-control input-sm" id="goodsCount_${status.index }" size="5" /></td>
												<td>
													<select class="form-control input-sm" name="storeId" id="storeId_${status.index }" shelfId="${item.storeShelfId }">
														<option value="">请选择...</option>
														<c:forEach var="ss" items="${store}">
															<option value="${ss.id}" ${item.storeId == ss.id ? 'selected' : '' }>${ss.name}</option>
														</c:forEach>
													</select>
												</td>
												<td>
													<%-- <select class="form-control input-sm" name="storeShelfId" id="storeShelf_${status.index }">
														<option value="">请选择...</option>
													</select> --%>
													<input type="text" name="storeShelf" value="${item.storeShelf }" class="form-control input-sm" size="10" />
													<input type="hidden" name="storeShelfId" value="${item.storeShelfId }" />
												</td>
												<td>
													<c:if test="${order.id != null && order.auditStatus == 0}">
													<a href="javascript:;" class="btn default btn-xs black c-row-del-btns">
														<i class="fa fa-trash-o"></i> <spring:message code="g.label.delete"/>
													</a>
													</c:if>
												</td>
											</tr>
										</c:forEach>
									</tbody>
									<tbody>
										<tr>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td class="text-right">总成本：</td>
											<td id="sum_cost_td">&nbsp;</td>
											<td id="sum_count_td">&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>
									</tbody>
								</table>
							</fieldset>
							<fieldset>
								<legend>操作</legend>
								<c:if test="${order.id != null }">
								<div class="row">
									<div class="col-md-12">
										制单时间:  <fmt:formatDate value="${order.createdTime}" pattern="yyyy-MM-dd HH:mm:ss" />
										制单人: ${order.createdUserName}
									</div>
								</div>
								<c:if test="${order.auditStatus == 1 }">
								<div class="row">
									<div class="col-md-12">
										审核时间: <fmt:formatDate value="${order.auditTime}" pattern="yyyy-MM-dd HH:mm:ss" />
										审核人: ${order.auditUserName}
									</div>
								</div>
								</c:if>
								</c:if>
								<div class="row text-center">
									<%-- 新建或者未审核时可保存 --%>
									<c:if test="${order.id == null || order.auditStatus == 0}">
										<button type="submit" id="sub_id" class="btn btn-primary"><spring:message code="g.label.save"/></button>
									</c:if>
									<%-- 未审核才能审核 --%>
									<c:if test="${order.id != null && order.auditStatus == 0}">
										<a href="${pageContext.request.contextPath }/io-order/approve?id=${order.id}&type=${order.type}" class="btn btn-primary c-confirm-link"><spring:message code="g.label.approved"/></a>
									</c:if>
								</div>
							</fieldset>
						
						</form:form>
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
			var trHtmlPattern = 
					'<tr>' +
					'	<td><input name="goodsSku" class="form-control input-sm" id="goodsSku_{0}" size="11" /></td>'+
					'	<td><input name="oldSku" class="form-control input-sm" id="oldSku_{0}" readonly="readonly" /></td>' +
					'	<td><input name="goodsName" class="form-control input-sm" id="goodsName_{0}" readonly="readonly" /></td>' +
					'	<td><input name="inventory" class="form-control input-sm" id="inventory_{0}" readonly="readonly" size="5" /></td>'+
					'	<td><input name="inventoryLock" class="form-control input-sm" id="inventoryLock_{0}" readonly="readonly" size="5" /></td>'+
					'	<td><input name="goodsCost" class="form-control input-sm" id="goodsCost_{0}" size="5" /></td>' +
					'	<td><input name="goodsCount" class="form-control input-sm" id="goodsCount_{0}" size="5" /></td>' +
					'	<td>' +
					'		<select class="form-control input-sm" name="storeId" id="storeId_{0}">' +
					'			<option value="">请选择...</option>' +
					'			<c:forEach var="ss" items="${store}">' +
					'				<option value="${ss.id}">${ss.name}</option>' +
					'			</c:forEach>' +
					'		</select>' +
					'	</td>' +
					'	<td>' +
					'		<input type="text" name="storeShelf" id="storeShelf_{0}" class="form-control input-sm" size="10" />' +
					'		<input type="hidden" name="storeShelfId" id="storeShelfId_{0}" value="${item.storeShelfId }" />' +
					'	</td>' +
					'	<td>' +
					'		<a href="javascript:;" class="btn default btn-xs black c-row-del-btns">' +
					'			<i class="fa fa-trash-o"></i> <spring:message code="g.label.delete"/>' +
					'		</a>' +
					'	</td>' +
					'</tr>';
		
			$(function() {
				$(document).on('blur', 'input[name=goodsSku]', function() {
					var $this = $(this);
					var sku = $.trim($this.val());
					$this.val(sku);
					if (sku == '')
						return false;
					
					var skuCounter = 0;
					$('input[name=goodsSku]').each(function() {
						if ($(this).val() == sku) {
							skuCounter++;
						}
					});
					
					if (skuCounter > 1) {
						alert('已经存在相同SKU行，请在同一行编辑');
						$this.val('');
						$this.blur();
						return;
					}
					
					// 查询商品信息
					$.get('${pageContext.request.contextPath }/goods/load-by-sku.json?sku=' + sku, function(goods) {
					
						if (goods == null) {
							$this.parents('tr').find('input[name=goodsName], input[name=goodsCost]').val('');
							alert(sku + '不存在！')
							return;
						}

						var $tr = $this.closest('tr');
						$tr.find('input[name=goodsName]').val(goods.name);
						$tr.find('input[name=oldSku]').val(goods.oldSku);
						$tr.find('select[name=storeId]').val(goods.storeId);
						$tr.find('input[name=storeShelfId]').val(goods.storeShelfId);
						$tr.find('input[name=storeShelf]').val(goods.storeShelfCode);
						

						// 查询最近价格
						$.get('${pageContext.request.contextPath }/io-order/get-goods-cost?sku=' + sku + '&type=${order.type }', function(goodsCost) {
							if (goodsCost == null) {
								return;
							}
							
							$this.parents('tr').find('input[name=goodsCost]').val(goodsCost);
						}, 'json');
						
						$.get('${pageContext.request.contextPath }/io-order/goods-inventory-json?sku='+ sku ,function(data){
							$this.parents('tr').find('input[name=inventory]').val(data.Inventory);
							$this.parents('tr').find('input[name=inventoryLock]').val(data.InventoryLock);
							
						},'json');
						
						var type = ${order.type};
						if(type == 1){
							$($this.parents('tr').find('input[name=goodsCount]')).change(function(){
								var $tr = $this.parents('tr');
									var salecount = parseInt($tr.find('input[name=inventory]').val()) - parseInt($tr.find('input[name=inventoryLock]').val());
									var goodsCount = $(this).val();
									if(goodsCount > salecount){
										alert("出货数不能大于可用数");
										$this.focus();
										$this.parents('tr').find('input[name=goodsCount]').val('');
										return false;
									}
							});
						}
						
					}, 'json');
				});
				
				
				
				
				$(document).on('click', '.c-row-del-btns', function() {
					$(this).parents('tr').remove();
				});
				
				
				$('#io_order_form').validate({
					errorElement : 'span', //default input error message container
					errorClass : 'help-block help-block-error', // default input error message class
					focusInvalid : false, // do not focus the last invalid input
					ignore : "", // validate all fields including form hidden input
					messages: {
						storeShelfId: '必须输入或输入不正确'
					},
					rules : {
						ioDate : { required : true },
						typeDetail : { required : true },
						goodsSku : { required : true },
						//goodsName : { required : true },
						goodsCount : { required : true, digits: true, min : 1 },
						goodsCost : { required : true, number: true },
						storeId : { required : true },
						storeShelfId : { required : true },
						storeShelf: { required : true }
					},

					highlight : function(element) { // hightlight error inputs
						$(element).parent().addClass('has-error'); // set error class to the control group
					},
					unhighlight : function(element) { // revert the change done by hightlight
						$(element).parent().removeClass('has-error'); // set error class to the control group
					},
					success : function(label) {
						label.parent().removeClass('has-error'); // set success class to the control group
					}
				});
				
				$('#io_order_form').submit(function() {
					if ($('input[name=goodsSku]').length == 0) {
						alert('请添加SKU信息');
						return false;
					}
					
					return true;
				});
				
				$(document).on('change', 'select[name=storeId]', function() {
					var $this = $(this);
					$tr = $this.closest('tr');console.log('$tr.length - ' + $tr.length);
					$tr.find('input[name=storeShelf]').val('');
					$tr.find('input[name=storeShelfId]').val('');
				});
				
				$(document).on('change', 'input[name=goodsCount], input[name=goodsCost]', function() {
					sumCost();
				});
				
				$(document).on('change', 'input[name=storeShelf]', function() {
					var $this = $(this);
					$this.siblings('input[name=storeShelfId]').val('');
					var storeId = $this.closest('tr').find('select[name=storeId]').val();
					if (storeId == '') {
						alert('请先选择仓库');
						return;
					}
					
					var code = $.trim($this.val());
					if (code == '') {
						return;
					}
					var url = '${pageContext.request.contextPath}/store/get-store-shelf-by-code.json?code=' + code;
					$.getJSON(url).done(function(result){
						console.log(result);
						var $storeShelfIdInput = $this.siblings('input[name=storeShelfId]');
						if (result == null) {
							$this.focus();
							$storeShelfIdInput.val('');
							alert('仓位号输入错误，请重新输入');
							return;
						}
						
						if (storeId == result.storeId) {
							$storeShelfIdInput.val(result.id);
						} else {
							$storeShelfIdInput.val('');
							$this.val('');
							alert('仓位号输入错误，请重新输入');
						}
					});
				});
			});

			var rowNum = 10000;
			function addEditRow() {
				rowNum ++;
				var html = trHtmlPattern.format(rowNum);
				
				$("#info_tb").append(html);
			}

			function subFrom(){
				$('#io_order_form').submit();
			}
		
			function dropHtml(rowNum) {
				$('#' + rowNum).remove();
			}
		
			function requestShelfList($tr, selectedValue, shelfId) {
				$.get('${pageContext.request.contextPath }/store/shelf-json?id=' + selectedValue, function(data) {
					xfy.fillSelect($tr.find('select[name=storeShelfId]'), data, 'id', 'code');
					if (shelfId != '') {
						$tr.find('select[name=storeShelfId]').val(shelfId);
					}
				}, 'json');
			}

			$('.date-picker').datepicker({
				orientation: "right",
				autoclose: true
			});
			
			function sumCost() {
				
				var sumCost = 0;
				var sumCount = 0;
				
				$('input[name=goodsCost]').each(function() {
					var $this = $(this);
					var goodsCostStr = $.trim($this.val());
					if (goodsCostStr == '') {
						return;
					}
					
					var goodsCostFloat = parseFloat(goodsCostStr);
					
					var goodsCountId = $this.attr('id').replace('goodsCost_', 'goodsCount_');
					var goodsCountStr = $.trim($('#' + goodsCountId).val());
					
					if (goodsCountStr == '') {
						return;
					}
					
					var goodsCountFloat = parseFloat(goodsCountStr);
					
					sumCost += (goodsCostFloat * goodsCountFloat);
					sumCount += goodsCountFloat;
					
				});
				
				$('#sum_cost_td').html(sumCost);
				$('#sum_count_td').html(sumCount);
			}
			
			sumCost();
			
			function printOrder(){
				window.open("${pageContext.request.contextPath }/io-order/ioOrderPrint?orderNo=${order.orderNo}");
			}
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>