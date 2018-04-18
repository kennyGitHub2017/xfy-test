<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x" %>

<c:set scope="request" var="sysModule" value="purchase" />
<c:set scope="request" var="sysPage" value="purchase_order" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="css-page">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/select2/select2.css"/>
	</tiles:putAttribute>
	<tiles:putAttribute name="title">新增采购单 </tiles:putAttribute>
	<tiles:putAttribute name="page-content">
		<div class="portlet-body form">
			<div class="portlet light ">
				<!-- BEGIN FORM-->
				<form:form id="data_form" modelAttribute="purchaseorder" action="${pageContext.request.contextPath}/purchaseorder/${updateFlag == '1' ? 'edit' : 'add'}" cssClass="form-horizontal" role="form" method="post">
						<form:hidden path="purchaseRequestId"/>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">采购单号</label>
									<div class="col-md-8">
										<form:input path="orderNo" cssClass="form-control" readonly="true" />
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">采购日期</label>
									<div class="col-md-8">
										<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
												<c:if test="${empty  purchaseorder.purchaseDate}">
													<input name="purchaseDate" class="form-control" id="purchaseDate" value="<f:formatDate value='${purchaseorder.createdTime}'/>"/>
												</c:if>
												<c:if test="${not empty  purchaseorder.purchaseDate}">
													<input name="purchaseDate" class="form-control" id="purchaseDate" value="<f:formatDate value='${purchaseorder.purchaseDate }'/>"/>	
												</c:if>
												<span class="input-group-btn">
													<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
												</span>
											</div>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">要求交货日期</label>
									<div class="col-md-8">
										<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
												<input name="deliveryDate" class="form-control" id="deliveryDate" value="<f:formatDate value='${purchaseorder.deliveryDate }'/>" />
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
									<label for="dict_code" class="col-md-4 control-label">运单号</label>
									<div class="col-md-8">
										<form:input path="waybillNo" cssClass="form-control" />
									</div>
								</div>
							</div>
							 
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">运费</label>
									<div class="col-md-8">
										<form:input path="freight" cssClass="form-control" />
									</div>
								</div>
							</div>
							  
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">物流公司</label>
									<div class="col-md-8">
										<form:input path="logisticsCompany" cssClass="form-control" />
									</div>
								</div>
							</div>
							
						</div>
						
						
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label for="dict_code" class="col-md-4 control-label">供应商</label>
									<div class="col-md-8">
									<!--  
											<form:select path="supplierId" id="supplierId" cssClass="form-control">
												<form:option value="">请选择</form:option>
												<c:forEach items="${ supplyList}" var="item">
														<form:option  value="${item.id}">${item.companyName }
								                     	</form:option>
												</c:forEach>
											</form:select>
									-->
										<input type="text" class="form-control c-supplier-picker" value="" data-placeholder="${purchaseorder.supplierName == null ? '请选择...' : purchaseorder.supplierName }" />
										<form:hidden path="supplierId" id="supplierId" />
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
										<label for="dict_code" class="col-md-4 control-label">支付方式</label>
										<div class="col-md-8">
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
							<div class="col-md-4">
											<div class="form-group form-group-sm">
												<label class="col-md-2 control-label">采购类型</label>
												<div class="col-md-10">
													<form:select path="isSample" cssClass="form-control">
														<form:option value="0">采购订单</form:option>
														<form:option value="1">样品采购</form:option>
													</form:select>
												</div>	
											</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<input type="hidden" name="id" id="id" value="${purchaseorder.id}">
									<input type="hidden" name="type" value="2">
									<input type="hidden" name="status" value="1">
									<label for="dict_code" class="col-md-2 control-label">备注</label>
									<div class="col-md-5">
										<form:input path="note" cssClass="form-control" id="note" />
									</div>
								</div>
							</div>
						</div>
						
	
						
						<div class="table-scrollable">
							
							<table class="table table-hover table-striped table-bordered"  id="purchasereqorderitem-table">
								<thead>
									<tr>
										<td colspan="14">
											<div align="right">
												<!--  
												<c:if test="${updateFlag != '1'}">
													<input type="button" id="addBtn" onclick="addRow('addBtn')" value="Add">
												</c:if>
												-->	
												<input type="button" id="addBtn" onclick="addRow('addBtn')" value="Add">
											</div>
										</td>
									</tr>
									<tr>
										<th>SKU</th>
										<th style="white-space: normal !important;">名称</th>
										<th>描述</th>
										<th>图片</th>
										<th>单位</th>
										<th>最新价</th>
										<th>采购价</th>
										<th>数量</th>
										<th>金额</th>
										<th>重量</th>
										<th>产品分类</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="tbody">
										<c:choose>
											<c:when test="${updateFlag == '1'}">
												<c:forEach items="${itemList}" var="item" varStatus="status">
													<tr id="tr${status.index+1}">
														<td><input type="text" readonly="readonly"  value="${item.goodsSku}" name="itemSku" /></td>
														<td><input type="hidden"   value="${item.goodsName}" name="itemName" />${item.goodsName}</td>
														<td>${empty item.color?"":item.color}-${empty item.goodsSize?"":item.goodsSize}-${empty item.rules?"":item.rules}-${empty item.model?"":item.model}</td>
														<td><img alt="" class="small-img" src="${x:goodsImagePath(item.goodsSku, 1) }"></td>
														<td><input type="hidden"   value="${item.goodsUnit}" name="itemUnit" />${item.goodsUnit}</td>
														<td>${item.newCost}</td>
														<td><input type="text"   value="${item.goodsCost}" name="itemCost" id="itemCost${status.index+1}"  style="width:80px" onblur='totalCost($(this))' /></td>
														<td countTotal="1"><input type="text"   value="${item.goodsCount}"  style="width:80px" name="itemCount" id="itemCount${status.index+1}" onblur='totalCost($(this))' /></td>
														<td costTotal="1">${item.goodsCost*item.goodsCount}</td>
														<td weightTotal="1"><input type='text' name='itemWeight' id="itemWeight${status.index+1}"  style="width:80px" onblur='totalCost($(this))' value='${item.goodsWeight }' /></td>
														<td><input type="hidden" name="itemCategory" value="${item.goodsCategory}">${item.goodsCategoryName }</td>
														<td>
															<input value="Del" type="button" onclick="DelRow('${status.index+1}')" >&nbsp;&nbsp;
														</td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr id="tr1">	
													<td><input type="text" id="goodsSku"  class="form-control" placeholder="Enter SKU" name="itemSku" onchange="goodsInfo('goodsSku')" /></td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td><input type='text' name='itemCost' id="itemCost" style='width:60px'  onblur='totalCost($(this))' /></td>
													<td countTotal="1"><input type='text' name='itemCount'  id="itemCount" style='width:60px' onblur='totalCost($(this))' /></td>
													<td costTotal="1"></td>
													<td weightTotal="1"><input type='text' name='itemWeight' id='itemWeight' value='' onblur='totalCost($(this))' style='width:60px' /></td>
													<td></td>
													<td></td>
												</tr>	
											</c:otherwise>
										</c:choose>
								</tbody>
							</table>
							<div id="totaldiv" style="align:center;color:red;font-size:12px"></div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="dict_code" class="col-md-6 control-label">制单人:${purchaseorder.purchaseRequestCreater}</label>
										<label for="dict_code" class="col-md-6 control-label">制单时间:<f:formatDate value="${purchaseorder.createdTime}"/></label>
									</div>
								</div>
							</div>
						</div>
						<div class="form-actions">
							<div class="row">
								<div class="col-md-offset-3 col-md-9">
									<button type="submit" class="btn green">提交</button>
									<x:power type="function" code="purchase_order_audit">
									<c:if test="${updateFlag == '1'}">
										<button type="button" id="auditBtn" class="btn green">审核</button>
									</c:if>
									</x:power>
									<button type="button" class="btn green" id="printbtn"  class="btn green">打印</button>
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
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/select2/select2.min.js" type="text/javascript"></script>
		<script>
		var baseImgUrl = '${x:getConfig("images.base.url")}/g/';
		$("#printbtn").click(function(){
			window.open("${pageContext.request.contextPath }/purchaseorder/print?orderId=${purchaseorder.id}","_blank");
		});
		</script>
		<script src="${pageContext.request.contextPath }/resources/pages/purchase-order-form.js" type="text/javascript"></script>
	</tiles:putAttribute>	
</tiles:insertDefinition>