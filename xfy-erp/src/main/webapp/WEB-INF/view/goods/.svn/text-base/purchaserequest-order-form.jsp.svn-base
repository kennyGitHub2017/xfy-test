<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x" %>

<c:set scope="request" var="sysModule" value="purchase" />
<c:set scope="request" var="sysPage" value="purchaserequest_order" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="css-page">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/select2/select2.css"/>
	</tiles:putAttribute>
	<tiles:putAttribute name="title">请购单 </tiles:putAttribute>
	<tiles:putAttribute name="page-content">
		<div class="portlet-body form">
			<div class="portlet light ">
				<!-- BEGIN FORM-->
				<form:form id="data_form" modelAttribute="purchasereq" action="${pageContext.request.contextPath}/purchaserequest-order/${updateFlag == '1' ? 'edit' : 'add'}" cssClass="form-horizontal" role="form" method="post">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label for="dict_code" class="col-md-2 control-label">请购单号</label>
									<div class="col-md-5">
										<form:input path="orderNo" readonly="true"  cssClass="form-control" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="dict_code" class="col-md-2 control-label">采购员</label>
									<div class="col-md-3">
										<form:select path="buyUserId" cssClass="form-control">
													<option value="">请选择...</option>
													<form:options items="${users }" itemValue="userId"  itemLabel="name" />
										</form:select>
									</div>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label for="dict_code" class="col-md-2 control-label">供应商</label>
									<div class="col-md-5">
											<input type="text" class="form-control c-supplier-picker" value="" data-placeholder="${purchasereq.supplierName == null ? '请选择...' : purchasereq.supplierName }" />
											<form:hidden path="supplierId" id="supplierId" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="dict_code" class="col-md-2 control-label">要求交货日期</label>
									<div class="col-md-4">
										<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
													<input name="deliveryDate" class="form-control"  id="deliveryDate"  value="<f:formatDate value='${purchasereq.deliveryDate}'/>"  />
													<span class="input-group-btn">
													<button class="btn default" type="button"><i class="fa fa-calendar"></i></button>
													</span>
										</div>
									</div>
								</div>
							</div>
						</div>
						
						
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<input type="hidden" name="id" value="${purchasereq.id}">
									<input type="hidden" name="type" value="1">
									<input type="hidden" name="status" value="1"> 
									<label for="dict_code" class="col-md-2 control-label">备注</label>
									<div class="col-md-5">
										<form:input path="note" cssClass="form-control" id="note" />
									</div>
								</div>
							</div>
						</div>
						
						
						<div class="table-scrollable">
								
							<table class="table table-hover table-striped table-bordered" id="purchasereqorderitem-table">
								<thead>
									<tr>
										<td colspan="12">
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
										<th>名称</th>
										<th>描述</th>
										<th>图片</th>
										<th>单位</th>
										<th>最新采购价</th>
										<th>本次采购价</th>
										<th>数量</th>
										<th>金额</th>
										<th>要求交货日期</th>
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
														<td>${item.color}-${item.goodsSize}-${item.rules}-${item.model}</td>
														<td><img alt="" class="small-img" src="${x:goodsImagePath(item.goodsSku, 1) }"></td>
														<td><input type="hidden"   value="${item.goodsUnit}" name="itemUnit" />${item.goodsUnit}</td>
														<td>${item.newCost}</td>
														<td><input type="text"   value="${item.goodsCost}" name="itemCost" style='width:80px' onblur='totalCost($(this))' /></td>
														<td countTotal="1"><input type="text"   value="${item.goodsCount}" style='width:80px' name="itemCount" onblur='totalCost($(this))' /></td>
														<td costTotal="1">${item.goodsCost*item.goodsCount}</td>
														<td deliveryDate="1">
															<input type="hidden"   value="<f:formatDate value='${item.deliveryDate}'/>" name="itemDeliveryDate" />
														 	<f:formatDate value='${item.deliveryDate}'/>
														 </td>
														<td><input type="hidden"   value="${item.goodsCategory}" name="itemCategory" />${item.goodsCategoryName}</td>
														<td><input value="Del" type="button" onclick="DelRow('${status.index+1}')" >&nbsp;</td>
														
													</tr>
											</c:forEach>
										</c:when>
										<c:otherwise>
												<!-- total属性标识是否已纳入统计 -->
												<tr id="tr1">
													<td><input type="text" id="goodsSku1"  class="form-control input-small" placeholder="Enter SKU" name="itemSku" onchange="goodsInfo(this.id)" /></td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td countTotal="1"></td>
													<td costTotal="1"></td>
													<td deliveryDate="1"></td>
													<td></td>
													<td></td>
													
												</tr>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
							<div id="totaldiv" style="align:center;color:red;font-size:15px"></div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label for="dict_code" class="col-md-6 control-label">制单人:${purchasereq.createdUserName}</label>
									<label for="dict_code" class="col-md-6 control-label">制单时间:<f:formatDate value="${purchasereq. createdTime}"/></label>
								</div>
							</div>
						</div>		
						<div class="form-actions">
							<div class="row">
								<div class="col-md-offset-3 col-md-9">
									<button type="submit" class="btn green">提交</button>
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
		var baseImgUrl = '${x:getConfig("images.base.url")}/g/'
		</script>
		<script src="${pageContext.request.contextPath }/resources/pages/purchaserequest-order-form.js" type="text/javascript"></script>
	</tiles:putAttribute>	
</tiles:insertDefinition>