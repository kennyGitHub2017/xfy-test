<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>

<c:set scope="request" var="sysModule" value="order" />
<c:set scope="request" var="sysPage" value="seller-order" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">订单列表</tiles:putAttribute>
	
	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">
	<style>
		td.details-control {
		    background: url('${pageContext.request.contextPath }/resources/assets/global/img/details_open.png') no-repeat center center;
		    cursor: pointer;
		}
		tr.details td.details-control {
		    background: url('${pageContext.request.contextPath }/resources/assets/global/img/details_close.png') no-repeat center center;
		}
	</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/blueimp-gallery/blueimp-gallery.min.css"/>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
			<div class="row">
				<div class="col-md-12">
						<div class="portlet light">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-cogs font-green-sharp"></i>
									<span class="caption-subject font-green-sharp bold uppercase">订单列表</span>
								</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"></a>
								</div>
							</div>
							<div class="portlet-body">
								<%@include file="../include/message.jsp" %>
								<form:form id="searchform" search="1" modelAttribute="search" method="post" class="form-horizontal" role="form">
									<input type="hidden" name="combine" value="0" />
									<c:if test="${empty userIdFlag}">
									<input type="hidden" value="${sessionUser.sellerId }" name="sellerId">
									</c:if>
									<c:if test="${not empty userIdFlag}">
									<input type="hidden" value="${userIdFlag}" name="userId">
									</c:if>
									<div class="row">
										<div class="col-md-2">
											<div class="form-group form-group-sm">
												<label  class="col-md-4 control-label">平台</label>
												<div class="col-md-8">
													<form:select path="platform" cssClass="form-control">
														<form:option value="">请选择</form:option>
														<form:option value="amazon">amazon</form:option>
														<form:option value="ebay">ebay</form:option>
														<form:option value="smt">smt</form:option>
														<form:option value="wish">wish</form:option>
														<form:option value="lazada">lazada</form:option>
													</form:select>
												</div>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group form-group-sm">
												<label  class="col-md-4 control-label">币种</label>
												<div class="col-md-8">
													<form:select path="currency" cssClass="form-control">
														<form:option value="">请选择</form:option>
														<form:options items="${rateList }" itemLabel="currency" itemValue="currency" />
													</form:select>
												</div>
											</div>
										</div>
										
										<div class="col-md-2">
											<div class="form-group form-group-sm">										
												<label  class="col-md-4 control-label">备注</label>
												<div class="col-md-8">
													<form:select path="note" cssClass="form-control">
														<form:option value="">请选择</form:option>
														<form:option value="1">有note</form:option>
														<form:option value="0">无note</form:option>
													</form:select>
												</div>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group form-group-sm">										
												<label  class="col-md-4 control-label">混合订单</label>
												<div class="col-md-8">
														<select name="mixed" class="form-control">
															<option value="">请选择</option>
															<option value="1">是</option>
															<option value="0">否</option>
														</select>
												</div>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group form-group-sm">										
												<label  class="col-md-4 control-label">打印/出库</label>
												<div class="col-md-8">
														<form:select path="printedSend" cssClass="form-control">
															<form:option value="">请选择</form:option>
															<form:option value="p1">未打印</form:option>
															<form:option value="p2">已打印</form:option>
															<form:option value="s1">未出库</form:option>
															<form:option value="s2">已出库</form:option>
														</form:select>
												</div>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group form-group-sm">										
												<label  class="col-md-4 control-label">是否补发</label>
												<div class="col-md-8">
														<form:select path="reissue" cssClass="form-control">
															<form:option value="">请选择</form:option>
															<form:option value="0">否</form:option>
															<form:option value="1">是</form:option>
														</form:select>
												</div>
											</div>
										</div>
									</div>
									
									<div class="row">
										<!--  
										<div class="col-md-2">
											<div class="form-group form-group-sm">
												<label  class="col-md-4 control-label">筛选条件</label>
												<div class="col-md-8">
														<select name="skuTypeSearch" class="form-control">
															<option value="">请选择</option>
															<option value="s1">无对应sku</option>
															<option value="s4">无跟踪号</option>
															<option value="s5">无发货方式</option>
															<option value="s2">缺货订单</option>
															<option value="s3">物流退回</option>
															<option value="s6">已退款</option>
															<option value="s7">已取消未退款</option>
														</select>
												</div>
											</div>
										</div>
										-->
										<div class="col-md-2">
											<div class="form-group form-group-sm">
												<label  class="col-md-4 control-label">国家</label>
												<div class="col-md-8">
													<input type="text" class="form-control" name="country" />
												</div>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group form-group-sm">
												<label  class="col-md-4 control-label">是否标发</label>
												<div class="col-md-8">
													<form:select path="isSend" cssClass="form-control">
														<form:option value="">请选择</form:option>
														<form:option value="1">是</form:option>
														<form:option value="0">否</form:option>
													</form:select>
												</div>
											</div>
										</div>
										
										<div class="col-md-2">
											<div class="form-group form-group-sm">
												<label  class="col-md-4 control-label">类型</label>
												<div class="col-md-8">
													<form:select path="type" cssClass="form-control">
														<form:option value="">请选择</form:option>
														<form:options items="${orderTypeList }" itemLabel="name" itemValue="code" />
													</form:select>
												</div>
											</div>
										</div>
										
										<div class="col-md-2">
											<div class="form-group form-group-sm">
												<label  class="col-md-4 control-label">状态</label>
												<div class="col-md-8">
														<form:select path="status" cssClass="form-control">
															<form:option value="">请选择</form:option>
															<c:forEach items="${orderstatusList }" var="item">
																<option  value="${item.code }" 
																<c:if test="${item.code==1 }">selected="selected" </c:if>
																>${item.name }</option>
															</c:forEach>
															<!--<form:options items="${orderstatusList }" itemLabel="name" itemValue="code" /> -->
														</form:select>
												</div>
											</div>
										</div>
										
										<div class="col-md-2">
											<div class="form-group form-group-sm">
												<label  class="col-md-4 control-label">运输方式</label>
												<div class="col-md-8">
														<select name="shipping" class="form-control" id="columnValue0">
															<option value="">请选择</option>
															<c:forEach items="${shippingList}" var="item">
																<option value="${item.shippingName}">${item.shippingName }</option>
															</c:forEach>
														</select>
												</div>
											</div>
										</div>
										
										<div class="col-md-2">
											<div class="form-group form-group-sm">
												<label  class="col-md-4 control-label">账号</label>
												<div class="col-md-8">
														<form:select path="accountId" cssClass="form-control">
															<form:option value="">请选择</form:option>
															<form:options items="${accountIdList}" itemLabel="accountName" itemValue="id" />
														</form:select>
												</div>
											</div>
										</div>
										
									</div>
									
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label  class="col-md-2 control-label">搜索</label>
												<div class="col-md-10">
														<div class="input-group">
															<div class="input-group-btn">
																<button type="button" id="searchdropdown" class="btn default dropdown-toggle btn-sm" data-toggle="dropdown">
																	请选择 <i class="fa fa-angle-down"></i>
																</button>
																<input type="hidden" name="searchColumn" id="searchColumn">
																<ul class="dropdown-menu" id="search-menu">
																	<li><a href="javascript:;" cid=""> 请选择 </a></li>
																	<li><a href="javascript:;" cid="b.buyer_user_id"> 客户ID </a></li>
																	<li><a href="javascript:;" cid="b.buyer_email"> 客户邮箱 </a></li>
																	<li><a href="javascript:;" cid="c.sku"> SKU</a></li>
																	<li><a href="javascript:;" cid="c.item_id"> Item Number</a></li>
																	<li><a href="javascript:;" cid="a.id"> 订单编号</a></li>
																	<li><a href="javascript:;" cid="a.srn"> Record NO</a></li>
																	<li><a href="javascript:;" cid="d.contacts"> 卖家姓名</a></li>
																	<li><a href="javascript:;" cid="b.shipping_name"> 客户姓名</a></li>
																	<li><a href="javascript:;" cid="a.paypaltransid"> Paypal交易ID</a></li>
																	<li><a href="javascript:;" cid="a.track_number"> 跟踪号</a></li>
																	<li><a href="javascript:;" cid="b.shipping_street1"> 运送地址</a></li>
																	<li><a href="javascript:;" cid="a.order_sn"> 平台订单编号 </a></li>
																</ul>
															</div>
															<!-- /btn-group -->
															<input type="text" class="form-control input-sm" id="searchValue" name="searchValue">
														</div>
												</div>
											</div>
										</div>
										
										<div class="col-md-4">
											<div class="form-group form-group-sm">
												<label  class="col-md-2 control-label">价格</label>
												<div class="col-md-10">
														<div class="input-group input-medium">
															<input id="slider_2_input_start" type="text" class="form-control" name="amountFrom"> <span class="input-group-addon"> to </span> <input id="slider_2_input_end" type="text" class="form-control" name="amountTo">
														</div>
												</div>
											</div>
										</div>
										
										<div class="col-md-4">
											<div class="form-group form-group-sm">
												<label  class="col-md-2 control-label">重量</label>
												<div class="col-md-10">
													<div class="input-group input-medium">
														<input id="slider_2_input_start" type="text" class="form-control" name="packageWeightFrom"> <span class="input-group-addon"> to </span> <input id="slider_2_input_end" type="text" class="form-control" name="packageWeightTo">
													</div>
												</div>
											</div>
										</div>
										
									</div>
									
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label  class="col-md-2 control-label">付款时间</label>
												<div class="col-md-5">
														<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
															<input name="payDateFrom" class="form-control input-sm" /> <span class="input-group-btn">
																<button class="btn default btn-sm" type="button">
																	<i class="fa fa-calendar"></i>
																</button>
															</span>
														</div>
												</div>
												<div class="col-md-5">
													<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
															<input name="payDateTo" class="form-control input-sm" /> <span class="input-group-btn">
																<button class="btn default btn-sm" type="button">
																	<i class="fa fa-calendar"></i>
																</button>
															</span>
													</div>
												</div>
											</div>
										</div>
										
										<div class="col-md-4">
											<div class="form-group">
												<label  class="col-md-2 control-label">销售时间</label>
												<div class="col-md-5">
														<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
															<input name="saleDateFrom" class="form-control input-sm" /> <span class="input-group-btn">
																<button class="btn default btn-sm" type="button">
																	<i class="fa fa-calendar"></i>
																</button>
															</span>
														</div>
												</div>
												<div class="col-md-5">
													<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
															<input name="saleDateTo" class="form-control input-sm" /> <span class="input-group-btn">
																<button class="btn default btn-sm" type="button">
																	<i class="fa fa-calendar"></i>
																</button>
															</span>
														</div>
												</div>
											</div>
										</div>
										
										<div class="col-md-4">
											<div class="form-group">
												<label  class="col-md-2 control-label">扫描时间</label>
												<div class="col-md-5">
														<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
															<input name="scanDateFrom" class="form-control input-sm" /> <span class="input-group-btn">
																<button class="btn default btn-sm" type="button">
																	<i class="fa fa-calendar"></i>
																</button>
															</span>
														</div>
												</div>
												<div class="col-md-5">
													<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
															<input name="scanDateTo" class="form-control input-sm" /> <span class="input-group-btn">
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
											<div class="form-group">
												<label  class="col-md-2 control-label">退货时间</label>
												<div class="col-md-5">
														<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
															<input name="returnTimeFrom" class="form-control input-sm" /> <span class="input-group-btn">
																<button class="btn default btn-sm" type="button">
																	<i class="fa fa-calendar"></i>
																</button>
															</span>
														</div>
												</div>
												<div class="col-md-5">
													<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
															<input name="returnTimeTo" class="form-control input-sm" /> <span class="input-group-btn">
																<button class="btn default btn-sm" type="button">
																	<i class="fa fa-calendar"></i>
																</button>
															</span>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group form-group-sm">										
												<label  class="col-md-4 control-label">是否退款</label>
												<div class="col-md-8">
														<select name="refund" class="form-control">
															<option value="">请选择</option>
															<option value="ALL">所有退款</option>
															<option value="PARTIALLYREFUNDED">部分</option>
															<option value="REFUNDED">全额</option>
														</select>
												</div>
											</div>
										</div>
									</div>
		
									<div class="row">
										<div class="col-md-3">
											<div class="form-group form-group-sm">
												<label  class="col-md-3 control-label">产品大类</label>
												<div class="col-md-9">
														<form:select path="firstCategory" name="firstCategory" cssClass="form-control" id="firstCategory">
															<option value="">请选择</option>
															<c:forEach items="${ categoryList}" var="item">
																<option value="${item.id}">${item.name }</option>
															</c:forEach>
														</form:select>
												</div>
											</div>
										</div>
										
										<div class="col-md-3">
											<div class="form-group form-group-sm">
												<label  class="col-md-3 control-label">产品中类</label>
												<div class="col-md-9">
													<form:select path="secondCategory" name="secondCategory" cssClass="form-control" id="secondCategory">
													</form:select>
												</div>
											</div>
										</div>
										
										<div class="col-md-3">
											<div class="form-group form-group-sm">
												<label  class="col-md-3 control-label">产品小类</label>
												<div class="col-md-9">
														<form:select path="thirdCategory" name="thirdCategory" cssClass="form-control" id="thirdCategory">
														</form:select>
												</div>
											</div>
										</div>
									
										<div class="col-md-3">
											<div class="form-group form-group-sm">
												<label  class="col-md-3 control-label">产品状态</label>
												<div class="col-md-9">
														<form:select path="skuStatus" name="skuStatus" cssClass="form-control">
															<form:option value="">请选择</form:option>
															<form:options itemLabel="name" itemValue="code" items="${goodsStatus }"/>
														</form:select>
												</div>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="col-md-12">
											<div class="form-group form-group-sm">
												<label  class="col-md-1 control-label">异常订单</label>
												<div class="col-md-11">
													  	<form:radiobuttons  items="${exceptionOrder}"  path="skuTypeSearch" cssClass="form-control"  />
												</div>
											</div>
										</div>
									</div>
									
								</form:form>
								<span id="rowselectshow"></span>
								<div class="table-toolbar">
									<x:power type="function" code="seller-order-offline-order">
									<button type="button" id="offlineorder" class="btn green btn-sm">线下订单</button>
									</x:power>
									<x:power type="function" code="seller-order-batch-update">
									<a class="btn green btn-sm" id="batcheditbtn" href="${pageContext.request.contextPath }/order/batchedit-page" data-target="#ajax" data-toggle="modal" data-backdrop="static">
										批量修改
									</a>
									</x:power>
									<x:power type="function" code="seller-order-copy">
									<button type="button" id="copyorderbtn" class="btn green btn-sm">复制</button>
									</x:power>
									<x:power type="function" code="seller-order-approve">
									<button type="button" id="auditbtn" class="btn green btn-sm">审核</button>
									</x:power>
									<x:power type="function" code="seller-order-split-contact">
									<button type="button" id="sdsplitbtn" class="btn green btn-sm">手动拆分</button>
									<button type="button" id="sdmergebtn" class="btn green btn-sm">手动合并</button>
									<button type="button" id="zdmergebtn" class="btn green btn-sm">自动合并</button>
									</x:power>
									<x:power type="function" code="seller-order-pause">
									<button type="button" id="orderPause" class="btn green btn-sm">订单暂停</button>
									</x:power>
									<x:power type="function" code="seller-order-resume">
									<button type="button" id="orderResume" class="btn green btn-sm">订单启用</button>
									</x:power>
									<x:power type="function" code="seller-order-sendmsg">
										<button type="button" id="sendmsg" class="btn green btn-sm">Send EbayMsg</button>
										<!--  <button type="button" id="sendmsg2" class="btn green btn-sm">Send SmtMsg</button> -->
									</x:power>
									<x:power type="function" code="seller-order-mark-shipping">
									<a herf="javascript:;" id="mark_shipping" class="btn green btn-sm">标发</a>
									</x:power>
									<x:power type="function" code="seller-order-export-shipping">
									<button type="button" id="exportShipping" onclick="exportShipping();" class="btn green btn-sm">导出发货清单</button>
									</x:power>
									<x:power type="function" code="seller-order-export-order">
									<button type="button" id="exportOrder" onclick="exportOrder();" class="btn green btn-sm">导出订单</button>
									</x:power>
									<x:power type="function" code="seller-order-export-sales">
									<button type="button" id="exportSale" onclick="exportSale();" class="btn green btn-sm">销售报表</button>
									</x:power>
									<button type="button" id="searchbtn" class="btn blue btn-sm"><i class="fa fa-search"></i> <spring:message code="g.label.search" /></button>
									<button type="button" id="clearbtn" class="btn blue btn-sm">清空</button>
								</div>
								<div id="orderStatistic"></div>
								<form id="batcheditform" method="post">
								<div class="">
									<table class="table table-hover table-striped table-bordered table-center table-condensed" id="order-table">
									<thead>
									<tr>
										<th><img style="cursor: pointer;" id="detailopbtn" src='${pageContext.request.contextPath }/resources/assets/global/img/details_close.png' /></th>
										<th>
											<input type="button" value="select" id="orderselect" />
										</th>
										<th>
											编号
										</th>
										<th>
											RECORD NO
										</th>
										<th>
											账号/卖家名称
										</th>
										<th>
											Buyer Email/ID
										</th>
										<th>
											订单状态
										</th>
										<th>
											合并订单
										</th>
										<th>
											国家
										</th>
										<th>
											跟踪号
										</th>
										<th>
											运输方式
										</th>
										<th>
											运费￥
										</th>
										<th>
											标发/打印/扫描
										</th>
										<th>
											总价
										</th>
										<th>
											订单成本￥
										</th>
										<th>
											申请退款
										</th>
										<th>
											订单处理费￥
										</th>
										<th>称重重量</th>
										<th>称重运费￥</th>
									</tr>
									</thead>
									<tbody>
									</tbody>
									</table>
								</div>
								</form>
							</div>
				</div>
			</div>
		</div>
		<%@include file="/WEB-INF/view/include/modal-ajax.jsp" %>
		<%@include file="/WEB-INF/view/include/modal-large.jsp" %>
		
		<!-- 退款form -->
		<div class="modal fade" id="refundwindow" tabindex="-1" role="dialog"
  			 aria-labelledby="myModalLabel" aria-hidden="true">
  		<form:form modelAttribute="refund" id="refund" action="${pageContext.request.contextPath}/order/refund" method="post">
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal"
		               aria-hidden="true">×
		            </button>
					            <h4 class="modal-title" id="myModalLabel">
					               		订单退款申请操作		            
					            </h4>
					         </div>
					         <div class="modal-body">
									订单号:<input type="text" readonly="readonly" class="form-control"  id="refundOrderId" name="orderId">
									SKU:<div id="skuCheckboxs"></div>
									<!-- <input type="hidden" name="sku"> -->
									Paypal交易ID:<input type="text"  name="transactionid" class="form-control" id="transactionid">
									退款金额:<input type="text" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')"  name="total" id="total" class="form-control" />
									订单金额:<input type="text"  name="orderAmount" id="orderAmount" class="form-control" disabled="disabled" />
									自定义退款原因:
									<select name="refundresonSelf"  class="form-control">
										<c:forEach items="${refundReasonList}" var="item">
											<option value="${item.code }">${item.name }</option>
										</c:forEach>
									</select>
									自定义退款类型:
									<select name="refundtypeSelf"  class="form-control">
										<c:forEach items="${refundTypeList}" var="item">
											<option value="${item.code }">${item.name }</option>
										</c:forEach>
									</select>
									收款账号:<input type="text" name="buyerrevAccount" class="form-control">
									截止时间:<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
													<input name="maxTime" class="form-control input-sm" /> <span class="input-group-btn">
														<button class="btn default btn-sm" type="button">
															<i class="fa fa-calendar"></i>
														</button>
													</span>
											</div>
									备注:<textarea class="form-control" rows="4" cols="80" name="mome"></textarea>
		         			</div>
					         <div class="modal-footer">
					            <button type="button" class="btn btn-default"
					               data-dismiss="modal">关闭
					            </button>
					            <button type="submit" class="btn btn-primary">提交</button>
		         </div>
		      </div><!-- /.modal-content -->
		   </div><!-- /.modal-dialog -->
		  </form:form><!-- /.form -->
		</div><!-- /.modal -->
		
		<div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls">
			<div class="slides"></div>
			<h3 class="title"></h3>
			<a class="prev">‹</a>
			<a class="next">›</a>
			<a class="close">×</a>
			<a class="play-pause"></a>
			<ol class="indicator"></ol>
		</div>
	</tiles:putAttribute>
	
		
	<%-- 页面级别的 JS --%>
	<tiles:putAttribute name="js-page">
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery-file-upload/blueimp-gallery/jquery.blueimp-gallery.min.js"></script>
		<script>
		
		$(function () {
			$('#refund').validate({
				errorElement : 'span', //default input error message container
				errorClass : 'help-block help-block-error', // default input error message class
				focusInvalid : false, // do not focus the last invalid input
				ignore : "", // validate all fields including form hidden input
				rules : {
					'transactionid' : { required : true },
					'total' : { required : true ,min:0,max:function(){ return parseFloat($("#orderAmount").val()); }},
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
		});
		
		// 标识是否第一次搜索,由于第一次搜索 未审核, 搜索完成后将状态条件清空
		var firstSearch = 1;
		
		//open and close order items
		$("#detailopbtn").toggle(function(){
			$(this).attr("src","${pageContext.request.contextPath }/resources/assets/global/img/details_open.png");
			$('#order-table tbody td.details-control').click();
		},function(){
			$(this).attr("src","${pageContext.request.contextPath }/resources/assets/global/img/details_close.png");
			$('#order-table tbody td.details-control').click();
		});
		
		function displayselect(){
			var ids =$.trim(xfy.getCheckedValues('id'));
			ids = (ids.length==0)?new Array():ids.split(",");
			$('#rowselectshow').html("您已经选择 <font color=red>"+ids.length+"</font> 条记录 ^_^");
		}
		
		function setSearchOrderId(orderId){
			$("#search-menu li").each(function(){
				var atag = $(this).children(0);
				if (atag.attr("cid")=="a.id"){
					var btnhtml = atag.text() +" <i class='fa fa-angle-down'></i>";
					$("#searchdropdown").html(btnhtml);
				}
			});
			$("#searchColumn").val("a.id");
			$("#searchValue").val(orderId);
		}
		
		$("#offlineorder").click(function(){
			window.open("${pageContext.request.contextPath }/order/editpage?id=0&type=2","_blank");
		});
		
		//Buttons With Dropdowns
		$(".dropdown-menu li").click(function(){
			var atag=$(this).children(0); 
			var cid = atag.attr("cid");
			$("#searchColumn").val(cid);
			var btnhtml = atag.text() +" <i class='fa fa-angle-down'></i>";
			$("#searchdropdown").html(btnhtml);
		});
		
		//select order checkbox
		$("#orderselect").click(function(){
			$("#order-table input[type='checkbox'][oid]").each(function(i){
				if($(this).attr("checked")){
					$(this).removeAttr("checked");
				}else{
					$(this).attr("checked",'true');
				}
			});
			displayselect();
		});
		
		$("#sendmsg").click(function(){
			var ids = xfy.getCheckedValues('id');
			if (ids == '') {
				alert('请选择要发送消息的ebay订单');
				return;
			}
			window.open("${x:getConfig('kandeng.base.url')}/ebay/Message/SendMessageToBuyer.aspx?id="+ids,"_blank");	
		});
		
		$("#sendmsg2").click(function(){
			var ids = xfy.getCheckedValues('id');
			if (ids == '') {
				alert('请选择要发送消息的smt订单');
				return;
			}
			if (ids.split(",").length>1){
				window.open("${x:getConfig('kandeng.base.url')}/smt/msg/BatchMsgReply.aspx?_orderIds="+ids,"_blank");
			}else{
				window.open("${x:getConfig('kandeng.base.url')}/smt/msg/MessageDetailOrder.aspx?_orderId="+ids,"_blank");	
			}
		});

		//copy select orders 
		$("#copyorderbtn").click(function(){
			var ids = xfy.getCheckedValues('id');
			console.log(ids);
			if (ids == '') {
				alert('请选择要复制的订单');
				return;
			}
			if (ids.indexOf(',') != -1) {
				alert('每次只能复制一个订单');
				return;
			}
			if (confirm('请确认是否继续操作: ' + $(this).text() + '？')) {
				$.ajax({
				    url:'${pageContext.request.contextPath }/order/copy',     
				    type:'post',
				    data: {"id":ids}, 
				    async : false, //默认为true 异步
				    complete:function(){
				    	tb.api().ajax.reload();
				    },
				    error:function(){
				       alert('订单复制失败');
				    }, 
				    success:function(data){
				    	var jsondata = eval("("+data+")");
				    	if(jsondata.result){
				    		if (jsondata.orderId && jsondata.orderId!=-1){
				    			alert("订单复制成功");
					    		window.open("${pageContext.request.contextPath }/order/editpage?type=2&id="+jsondata.orderId,"_blank");
				    		}else if(jsondata.orderId && jsondata.orderId==-1){
				    			alert("订单复制失败,订单已复制");
				    		}
				    	}else if (!jsondata.result){
				    		alert("订单复制错误,系统异常");
				    	}
				    }
				});
			}
		});
		
		//datepicker show
		$('.date-picker').datepicker({
			orientation: "right",
			autoclose: true
		});
		
			var tb;
			//搜索按钮
			$("#searchbtn").click(function(){
				var searchColumn = $("#searchColumn").val();
				var searchValue = $("#searchValue").val();
				if ($.trim(searchColumn)=='a.id' && isNaN($.trim(searchValue))){
					alert("订单编号请输入数字");
					return false;
				}
				 var resultStr = searchValue.replace(new RegExp(/(\\s*|\t|\r|\n)/g), "");
			     $("#searchValue").val(resultStr);
				var val=$('input:radio[name="skuTypeSearch"]:checked').val();
				var userId = '${sessionUser.userId }',sellerId='${sessionUser.sellerId==1?'':sessionUser.sellerId }';
				
				if (val!=null){
					//更新导常订单统计数
					$.ajax({
						url:'${pageContext.request.contextPath }/order/exceptionOrderCount',     
						type:'post',
						data: {sellerId:(sellerId==''?null:sellerId),userId:userId}, 
						async : true, //默认为true 异步  
						error:function(){
							alert('ajax error');
						}, 
						success:function(data){
							var jsondata = eval("("+data+")");
							if (jsondata.result){
								$("label[for='skuTypeSearch1']").html(jsondata.s3);
								$("label[for='skuTypeSearch2']").html(jsondata.s4);
								$("label[for='skuTypeSearch3']").html(jsondata.s5);
								$("label[for='skuTypeSearch4']").html(jsondata.s6);
								$("label[for='skuTypeSearch5']").html(jsondata.s10);
								$("label[for='skuTypeSearch6']").html(jsondata.s7);
								$("label[for='skuTypeSearch7']").html(jsondata.s8);
								$("label[for='skuTypeSearch8']").html(jsondata.s9);
								$("label[for='skuTypeSearch9']").html(jsondata.s1);
								$("label[for='skuTypeSearch10']").html(jsondata.s2);
							}
						}
					});
				}
				tb.api().ajax.reload();
				ajaxorderStatistic();
			});
			
			//审核
			$("#auditbtn").click(function(){
				var disCount = 0;
				var ckbox = $("#order-table input[type='checkbox'][oid]");
				ckbox.each(function()
						{
							if(!$(this).attr("checked")){
								disCount++;
							}
						})
				if (disCount==ckbox.length){
					alert("请选择要审核的订单");
					return false;
				}
				var ids = xfy.getCheckedValues('id');
				$.ajax({
				    url:'${pageContext.request.contextPath }/order/audit',     
				    type:'post',
				    data: {id:ids}, 
				    async : false, //默认为true 异步     
				    complete:function(){
				    	tb.api().ajax.reload();
				    },
				    error:function(){
				       alert('ajax error');
				    }, 
				    success:function(data){
				    	var jsondata = eval("("+data+")");
				    	if (!jsondata.result){
				    		alert(jsondata.error);
				    	}
				    }
				});
			});
			
			//手动合并
			$("#sdmergebtn").click(function(){
				var disCount = 0;
				var ckbox = $("#order-table input[type='checkbox'][oid]");
				ckbox.each(function()
						{
							if(!$(this).attr("checked")){
								disCount++;
							}
						})
				if (disCount==ckbox.length){
					alert("请选择要合并的订单");
					return false;
				}
				if (confirm('请确认是否继续操作: ' + $(this).text() + '？')) {
					var ids = xfy.getCheckedValues('id');
					$.ajax({
					    url:'${pageContext.request.contextPath }/order/merge',     
					    type:'post',
					    data: {id:ids}, 
					    async : false, //默认为true 异步
					    complete:function(){
					    	tb.api().ajax.reload();
					    },
					    error:function(){
					       alert('ajax error');
					    }, 
					    success:function(data){
					    	var jsondata = eval("("+data+")");
					    	if (!jsondata.result){
					    		alert(jsondata.msg);
					    	}
					    }
					});	
				}
			});
			
			
			//手动拆分
			$("#sdsplitbtn").click(function(){
				var ids = xfy.getCheckedValues('id');
				if (ids.length == 0) {
					alert("请选择要操作的订单");
					return false;
				}
				
				if (confirm('请确认是否继续操作: ' + $(this).text() + '？')) {
					$.ajax({
						url:'${pageContext.request.contextPath }/order/split',     
						type:'post',
						data: {id:ids}, 
						async : false, //默认为true 异步
						complete:function(){
							tb.api().ajax.reload();
						},
						error:function(){
							alert('ajax error');
						}, 
						success:function(data){
							var jsondata = eval("("+data+")");
							alert(jsondata.msg);
						}
					});
				}
			});
			
			//自动合并
			$("#zdmergebtn").click(function(){
				if (confirm('请确认是否继续操作: ' + $(this).text() + '？')) {
					var d = new Object();
					var jsondata = $("#searchform").serializeObject();  //转化为json
					d.params = jsondata;
					var data =  JSON.stringify( d );
					$(this).attr("disabled","disabled");
					$.ajax({
					    url:'${pageContext.request.contextPath }/order/auto-merge',     
					    type:'post',
					    contentType:"application/json;charset=UTF-8",
					    data: data, 
					    async : false, //默认为true 异步     
					    error:function(){
					       alert('订单自动合并失败');
					       $("#zdmergebtn").removeAttr("disabled");
					    }, 
					    success:function(data){
					    	var jsondata = eval("("+data+")");
					    	if(!jsondata.result){
					    		alert("订单自动合并失败");
					    	}else{
					    		alert("订单自动合并成功");
					    	}
					    	$("#zdmergebtn").removeAttr("disabled"); 
					    }
					});
				}
			});
			
			//页面load完成
			$(function(){
				xfy.initDataTable();
				<c:if test="${not empty orderId}">
					var orderId = ${orderId};
					setSearchOrderId(orderId);
				</c:if>
				loadDate();
				
				$('#order-table tbody').on( 'click', 'td.details-control', function () {
				    	var tr = $(this).closest('tr');
				        var row = tb.api().row( tr );
				        if ( row.child.isShown() ) {
				            tr.removeClass( 'details' );
				            row.child.hide();
				        }
				        else {
				        	row.child( formatitem( row.data() ) ).show();	
				            tr.addClass( 'details' );
				        }
				    });
				
				tb.api().on('draw', function () {
					$('#order-table tbody td.details-control').click();
			    });
				
				ajaxorderStatistic();
				
				$('#mark_shipping').click(function() {
					var ids = xfy.getCheckedValues('id');
					if (ids == '') {
						alert('请选择要标发的订单');
						return;
					}
					
					if (confirm('请确认是否继续操作：' + $(this).text() + '？')) {
						xfy.requestByForm({
							method: 'post',
							action: '${pageContext.request.contextPath}/order/mark-shipping',
							target: "_blank",
							data: {id : ids}
						});
					}
				});
			});
			
			//订单统计数据
			function ajaxorderStatistic(){
				/*
				$.ajax({
					url:'${pageContext.request.contextPath }/order/orderStatistic.json',
					type:'post',
					data:$("#searchform").serialize(), 
					dataType:'json',
					async : true, //默认为true 异步  
					error:function(){
						alert('ajax error');
					},
					success:function(data){
						$("#orderStatistic").html("");
						if (data){
							$("#orderStatistic").html("<font color='red'>订单金额汇总:{0},订单sku汇总:{1}</font>".format(data.amountCount.toFixed(2),data.skuCount));	
						}
					}
				});
				*/
			}
			
			//ajax load tabledata
			function loadDate(){
				tb = $("#order-table").dataTable({
					searching: false,
					serverSide: true,
					ordering:true,
					ajax: {
						url:"${pageContext.request.contextPath }/order/pageJson",
						contentType:"application/json;charset=UTF-8",	
						type: 'post',
						data: function (d) {
						var jsondata = $("#searchform").serializeObject();  //转化为json
						d.params = jsondata;
							return JSON.stringify( d );
						}
					},
					columns: [
						{
							    "class":          "details-control",
							    "sortable":false,
							    "data":           null,
							    "defaultContent": ""
						 },  
						 {data:function(data){
							 var retStr ="&nbsp;<input type='checkbox' onchange='displayselect()' name='id' class='chk_order_id' value=" + data.id+" oid=" + data.id +" /><br>"; 
							 if (data.suspend && data.suspend==1){
								 retStr = retStr + "<span style='color:red'>暂停</span>";
							 }
							 return retStr;
						 },"sortable":false},
						 {data:function(data){
							 var skuAry = new Array();
					    	 var itemIdAry = new Array();
					    	 if (data.items){
					    		 for(var i=0;i<data.items.length;i++){
					    			 skuAry.push(data.items[i].sku);
					    			 itemIdAry.push(data.items[i].itemId);
						    	 }
					    	 }
					    	 var retStr = data.orderPlatform+"<br>"+"<a target='blank' id='vieworder' data-target='#modal-large' data-toggle='modal' data-backdrop='static' href='${pageContext.request.contextPath}/order/editpage?type=1&id=" +data.id+"' >"+data.id+"</a>";
							 var str = "{\'id\':{0},\'amount\':{1},\'currency\':\'{2}\',\'paypaltransid\':\'{3}\',\'sku\':'{4}',\'itemId\':'{5}'}".format(data.id,data.amount,data.currency,data.paypaltransid,skuAry,itemIdAry);
					    	 var jsMethod = "javascript:reFund({0})".format(str);
					    	 var tuikuan = '<a class="btn default btn-xs {0}" href="{1}"><i class="fa fa-edit"></i> {2}</a>'.format('blue',jsMethod,'退款'); 
					    	 var tuikuanEnable = '<a class="btn default btn-xs {0}" href="{1}"><i class="fa fa-edit"></i> {2}</a>'.format('green',jsMethod,'退款');
					    	 var tuikuanDisable = '<a class="btn default btn-xs {0}" enabled=false><i class="fa fa-edit"></i> {1}</a>'.format('gray','退款');
							 if (data.orderPlatform=="ebay"){
								 	if (data.amount==data.refundFee){
								 		retStr= retStr + "<br>"+tuikuanDisable;	
								 	}else if (data.refundFee && data.amount>data.refundFee){
								 		retStr= retStr + "<br>"+tuikuanEnable;	
								 	}else{
								 		retStr= retStr + "<br>"+tuikuan;	
								 	}
							 }
							 //retStr= retStr + "<br>"+tuikuan;
					    	return retStr;
					    //	 var retStr = data.orderPlatform+"<br>"+"<a target='blank' id='vieworder' data-target='#modal-large' data-toggle='modal' data-backdrop='static' href='${pageContext.request.contextPath}/order/editpage?type=1&id=" +data.id+"' >"+data.id+"</a>";
					    //	return retStr;
					     },"sortable":false},
						{data:"srn","sortable":false},
						{data:function(data){
							return data.accountName +"/"+data.accountRealName;
						},"sortable":false},
						{data:function(obj){
							var retStr = "";
							if (obj.buyinfo && obj.buyinfo.buyerEmail){
								retStr = retStr+obj.buyinfo.buyerEmail;
							}
							if (obj.buyinfo && obj.buyinfo.buyerUserId){
								if (retStr.length>0){
									retStr+="<br>";
								}
								retStr = retStr+obj.buyinfo.buyerUserId;
							}
							var ordersns = obj.orderSn.split(",");
							retStr += '<br/>' + ordersns[0];
							return retStr;
						},"sortable":false},
						{data:function(obj){
							var retStr='';
							if (obj.orderStatus==1){
								retStr = "未审核";
							}else if (obj.orderStatus==2){
								retStr =  "待锁定";
							}
							else if (obj.orderStatus==3){
								retStr =  "已锁定";
							}
							else if (obj.orderStatus==4){
								retStr =  "待包裹";
							}
							else if (obj.orderStatus==5){
								retStr =  "生成包裹";
							}
							else if (obj.orderStatus==6){
								retStr =  "取消";
							}
							else if (obj.orderStatus==7){
								retStr =  "发货";
							}else if(obj.orderStatus==8){
								retStr="退货订单";
							}else if(obj.orderStatus==9){
								retStr="已处理退货订单";
							}
							 var jsMethod = "javascript:orderEnable(" + obj.id+")";
						     var orderEnable =  xfy.html.normalLink.format(jsMethod,'重新启用');
						     if(obj.orderStatus==6){
									// 取消订单重新启用
									retStr = retStr + "<br>" +orderEnable;
							}
						     return retStr;
						},"sortable":false},
						{data:function(obj){
							var retStr = "";
							if (obj.combineOrders && obj.combineOrders.length>0){
								retStr ="<a href='javascript:void(0)' id=combineOrders" + obj.id +" title='" +obj.combineOrders +"' >√</a>";
								$("#combineOrders"+obj.id).tooltip({placement:'bottom right'});
							}
							else{
								retStr = "×";
							}
							return retStr;
						},"sortable":false},
						{data:function(obj){
							if (obj.buyinfo && obj.buyinfo.shippingCountryName){
								return obj.buyinfo.shippingCountryName;
							}
							if (obj.buyinfo && obj.buyinfo.shippingCountry){
								return obj.buyinfo.shippingCountry;
							}
							return "";
						},"sortable":false},
						{data:function(data){
							if (data.trackNumber){
								if (data.shippingName=="国际小包平邮"){
									return  "<a target='blank' href='http://khcx.gdpost.com.cn/mail/mgnt/InterSatPostState.do?method=firstMailBatch2&mailnum={0}'>{1}</a>".format(data.trackNumber,data.trackNumber);
								}else if (data.shippingName=="顺邮宝挂号" || data.shippingName=="顺邮宝平邮"){
									return  "<a target='blank' href='http://www.pos.com.my/postal-services/quick-access/?track-trace#trackingIds={0}'>{1}</a>".format(data.trackNumber,data.trackNumber);
								}
								else if (data.shippingName=="www.bpostinternational.com/en/index.html"){
									return  "<a target='blank' href='http://www.bpost2.be/bpostinternational/track_trace/find.php?search=s&lng=en&trackcode={0}'>{1}</a>".format(data.trackNumber,data.trackNumber);
								}
								else if (data.shippingName=="俄罗斯小包平邮"){
									return  "<a target='blank' href='http://www.sf-express.com/us/en/dynamic_functions/waybill/#search/bill-number/{0}'>{1}</a>".format(data.trackNumber,data.trackNumber);
								}
								else if (data.shippingName=="DHL(大陆)"){
									return  "<a target='blank' href='http://www.cn.dhl.com/zh/express/tracking.html?brand=DHL&AWB={0}'>{1}</a>".format(data.trackNumber,data.trackNumber);
								}
								else if (data.shippingName=="FEDEX"){
									return  "<a target='blank' href='https://www.fedex.com/apps/fedextrack/?action=track&trackingnumber={0}&cntry_code=us'>{1}</a>".format(data.trackNumber,data.trackNumber);
								}
								else if (data.shippingName=='欧洲小包平邮'){
									return  "<a target='blank' href='javascript:void(0)' onclick=qryTrackNumber('{0}')>{1}</a>".format(data.trackNumber,data.trackNumber);
								}
								else if (data.shippingName=='俄罗斯小包挂号'){
									return  "<a target='blank' href='http://www.17track.net/zh-cn/track?nums={0}'>{1}</a>".format(data.trackNumber,data.trackNumber);
								}
								else{
									return  "<a target='blank' href='http://www.17track.net/zh-hk/result/post.shtml?nums={0}'>{1}</a>".format(data.trackNumber,data.trackNumber);	
								}
							}
							return "";
						},"sortable":true,name:"track_number"},
						{data:"shippingName","sortable":false},
						{data:"shippingFee","sortable":true,name:"shipping_fee"},
						{data:function(data){
								var t  = "<img src={0}><img src={1}><img src={2}>";
								var imgSrc = "${pageContext.request.contextPath }/resources/assets/global/img/";
								var sendImg = data.isSend==1?"ship1.png":"ship.png";
								var printImg = data.printedFlag==1?"print1.png":"print.png";
								var scanImg = data.scannedTime?"scan1.png":"scan.png";
								return t.format(imgSrc+sendImg,imgSrc+printImg,imgSrc+scanImg);
						},"sortable":false},
						{data:"amount","render":function(data, type, row){
							return row["amount"] +"<br>"+row["currency"];	
						},"sortable":true,name:"amount"},
						{data:function(data){
							if (data.orderStatus==1){
								return "";
							}
						//	var pmf = data.packingMaterialFee?data.packingMaterialFee:0;
						//	return data.cost?(data.cost+pmf).toFixed(1):"";
							return data.cost?data.cost.toFixed(1):"";
						},"sortable":false},
						{data:function(data){
							if (data.refundFee){
								var  ret ="<a href='javascript:void(0)' id=refund" + data.id +" title='" +data.refundReason +"' >"+data.refundFee +"</a>";
								if (data.refundFee && data.refundReason){
									$("#refund"+data.id).tooltip({placement:'bottom right'});
								}
								return ret;	
							}else{
								return "";	
							}
						},"sortable":true,name:"refund_fee"},
						{data:function(data){
							return 2;
						},"sortable":false},
						{data:"packageWeight","sortable":true,name:"package_weight"},
						{data:"packageShipfee","sortable":true,name:"package_shipfee"}
					],
					drawCallback: function(settings) {
						// 第一次搜索完成后,将未审核条件清空
						if (firstSearch == 1) {
							$("#searchform select[name=status]").val('');
							firstSearch = 0;
						}
						var rows = $("#order-table tbody tr");
						$.each(rows,function(i,row){
							row.className="info";
						});
					}
				});
			}
			
			//退款
			function reFund(order){
				$("#refundOrderId").val(order.id);
				$("#orderAmount").val(order.amount+order.currency);
				$("#transactionid").val(order.paypaltransid);
				if ($.trim(order.sku)!=''){
					var cheboxStr ="";
					var hiddenStr = "";
					var skuAry = $.trim(order.sku).split(",");
					var itemId = $.trim(order.itemId).split(",");
					for(var i=0; i<skuAry.length;i++){
						if (i%5==0){
							cheboxStr+="<br>";
						}
						cheboxStr +="<input type='checkbox' value='{0}'  name='sku' />{1}".format(skuAry[i],skuAry[i]);
						hiddenStr +="<input type='hidden' value='{0}'  name='itemid' />".format(itemId[i]);
					}
					$("#skuCheckboxs").html(cheboxStr+hiddenStr);
				}
				$("#refundwindow").modal({keyboard: false,backdrop:'static'});
			}
			
			function orderEnable(orderId){
				$.ajax({
				    url:"${pageContext.request.contextPath}/order/orderEnable",     
				    type:'post',
				    data:{orderId : orderId},
				    async : false, //默认为true 异步
				    complete:function(){
				    	tb.api().ajax.reload();
				    },
				    error:function(){
				       alert('ajax error');
				    }, 
				    success:function(data){
				    	var jsondata = eval("("+data+")");
				    	if (!jsondata.result){
				    		alert(jsondata.error);	
				    	}else{
				    		alert("该订单已重新启用");
				    	}
				    }
				});
			}
			
			function qryTrackNumber(trackNumber){
				xfy.requestByForm({
					method: 'post',
					action: 'http://www.sfb2c.com/?a=track',
					target: "_blank",
					data: {keywords : trackNumber}
				});
			}
			
			/*
			function getShipFee(orderId){
			
				var html = '<select id="shipping-selector-{0}" >'.format( orderId );
				
				$.ajax({
					type : "post",
					async: false,
					url : "${pageContext.request.contextPath }/order/shipFee-json",
					data : { 
						orderId:orderId
					},
					dataType : "json",
					success : function(data) {
					$.each(data, function(commentIndex, comment){
						html += '<option value='+comment.id+'>{0}--{1}</option>'.format(comment.shippingName,comment.shippingFee);
					});
					
					}
				});
				
				html += '</select>';
				html += '<input type="button" value="保存" onclick="saveShippingInfo({0})" />'.format(orderId);
				return html;
			}
			*/
			
			function saveShippingInfo(orderId){
				var commentId = $('#shipping-selector-' + orderId).val();
				//alert(orderId + " - " + commentId);
				$.post('${pageContext.request.contextPath }/order/setShipping',
						{id:commentId},function(data){
							if(data == true){
								tb.api().ajax.reload();
							}else{
								alert("保存失败");	
							}
						},'json');
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
		    
		  
		    //订单行取消按钮事件
		    function itemcancel(btn){
		    	var td = $(btn).parent('td');
		    	var orderId = td.attr("orderid");
		    	var sku = td.attr("sku");
		    	var itemId = td.attr('itemId');
		    	var cancel = window.prompt("请输入取消数量",1);
		    	if (cancel=='' || cancel==null || isNaN(cancel) || parseInt(cancel)<0){
		    		alert("请输入正确的取消数量");
		    		return ;
		    	}
		    	
			    $.ajax({
					    url:'${pageContext.request.contextPath}/order/cancelOrderItem.json',     
					    type:'post',
					    data:{itemId: itemId, "cancelAmount":cancel},     
					    async : false, //默认为true 异步     
					    error:function(){
					       alert('ajax error');     
					    }, 
					    success:function(data){
					    	if(!data.result){
					    		alert("sku取消异常:" + data.error);
					    	}
					    	tb.api().ajax.reload();
					    }
					}); 
		    };
		    
		    function formatitem(order) {
		    	var items = order.items;
		    	var orderSaleTime = order.orderSaleTime?new Date(order.orderSaleTime).format("yyyy-MM-dd"):'';
		    	var orderPaidTime = order.orderPaidTime?new Date(order.orderPaidTime).format("yyyy-MM-dd"):'';
		    	var shippedTime = order.shippedTime?new Date(order.shippedTime).format("yyyy-MM-dd HH:mm:ss"):'';
		    	var scannedTime = order.scannedTime?new Date(order.scannedTime).format("yyyy-MM-dd HH:mm:ss"):'';
		    	var returnTime = order.returnTime?new Date(order.returnTime).format("yyyy-MM-dd HH:mm:ss"):'';
		    	var noteStr = order.note;
		    	var mesages = "";
		    	if (order.orderPlatform=='ebay'){
		    		mesages = order.buyerNote;
		    	}else if (order.orderPlatform=='smt'){
		    		//读取smt订单留言
		    		if (order.items && order.items.length>0){
		    			$.ajax({
						    url:'${pageContext.request.contextPath}/order/smtMessage.json',     
						    type:'post',
						    data:{"orderSn":order.orderSn},     
						    async : true, //默认为true 异步     
						    error:function(){
						      	console.log('read smt message error');     
						    }, 
						    success:function(data){
						    	if (data.result){
						    		for(var i=0;i<data.messages.length;i++){
						    			if ($.trim(data.messages[i])==""){
						    				continue;
						    			}
						    			mesages = mesages + "{0}<br>".format(data.messages[i]);
						    		}
						    		//alert(mesages);
						    		$("#msg"+order.id).html(mesages);
						    	}
						    }
						}); 
		    		}
		    	}
		    	
		    	
		    	var otherStr = "<div style='color:#31708f;background:#d9edf7' align='left'>下单日期:{0}&nbsp;&nbsp;&nbsp;支付日期:{1}&nbsp;&nbsp;&nbsp;标发时间:{2}&nbsp;&nbsp;&nbsp;扫描时间:{3}&nbsp;备注:<span style='color:red'>{4}</span></div>".format(orderSaleTime,orderPaidTime,shippedTime,scannedTime,noteStr);
		    	otherStr = otherStr + "<div  align='left' style='white-space:normal; width:1200px;word-break:break-all;'>买家留言:<span  id='msg{0}' style='color:red;'>{1}</span></div>".format(order.id,mesages);
		    	otherStr = otherStr + "<div  align='left' style='white-space:normal; width:1200px;word-break:break-all;'>退货原因:<span style='color:red;'>{0}</span>&nbsp;时间:{1}</div>".format(order.returnNote,returnTime);
		    	var retStr = "<table class='table-wrap-td table-center' border='1' style='background:#FFF;'><thead><tr><th width='5%'></th><th width='5%'>产品图片</th><th width='20%'>产品名称</th><th width='2%'>状态</th><th width='15%'>sku名称</th><th width='10%'>SKU</th><th width='7%'>产品成本</th><th width='5%'>数量</th><th width='5%'>可售数量</th><th width='5%'>包裹数量</th><th width='5%'>锁定数量</th><th width='5%'>出货数量</th><th width='5%'>取消数量</th><th width='5%'>采购数量</th><th>平台运费</th></tr></thead>";
		    	for(var i=0;i<items.length;i++){
		    		var img = "";
		    		var sku = items[i].sku;
		    		if (sku && /[0-9]{9,10}/.test(sku)){
		    			var imgUrlS = '${x:getConfig("images.base.url")}/g/' + sku.substring(0,2) + '/' + sku + '/g-1-S.jpg';
		    			var imgUrlM = '${x:getConfig("images.base.url")}/g/' + sku.substring(0,2) + '/' + sku + '/g-1-M.jpg'; // 中图
		    			img = '<a href="{1}" target="_blank" data-gallery><img class="small-img" src="{0}" /></a>'.format(imgUrlS, imgUrlM);
		    		}else{
		    			img = "<img src='" + "${pageContext.request.contextPath }/resources/assets/global/img/noimg.png' width=60 height=45 />";
		    		}
		    		var title="("+items[i].itemId+")<br>";
		    		
		    		title = title+items[i].itemTitle;
		    		var skutitle=items[i].skuName?items[i].skuName:"";
		    		var itemId = items[i].id;
		    		var skupurchasePrice = items[i].purchasePrice?items[i].purchasePrice:0;
		    		var skupackingMaterialFee =items[i].packingMaterialFee?items[i].packingMaterialFee:0;
		    		var skuCost = items[i].itemCost;
		    		var ftd = '<td orderid="{0}" sku="{1}" itemId="{2}">&nbsp;&nbsp;&nbsp;<input type="button" onclick="itemcancel(this)"  value="取消" /> <br>&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="itemId" value="{2}-{3}-{4}"/></td>'.format(order.id,(items[i].sku==null?'':items[i].sku) ,itemId,order.id,order.orderPlatform);
		    		var sku = '<td><input type="text" style="width:100px" value="{0}" /><input type="button" onclick="editSku(this, {1}, \'{0}\',{2})" value="Save" /></td>'.format( (items[i].sku==null?items[i].itemSku:items[i].sku),items[i].id,order.id);
		    		var rowStr = "<tr>"+ftd+"<td>"+ img+"</td><td>"+title+"</td><td>"+ items[i].skuStatus+ "</td><td>"+skutitle +"</td>" + sku + "<td>"+ (skuCost?skuCost.toFixed(1):"") +"</td><td>"+items[i].itemQuantity+"</td><td><span style='color:red'>"+(items[i].countInstore==null?'':items[i].countInstore)+"</span></td><td>" ;
		    	//	var rowStr = "<tr>"+ftd+"<td>"+ img+"</td><td>"+title+"</td><td>"+ items[i].skuStatus+ "</td><td>"+skutitle +"</td>" + sku + "<td>"+ (skupurchasePrice+skupackingMaterialFee).toFixed(1) +"</td><td>"+items[i].itemQuantity+"</td><td><span style='color:red'>"+(items[i].countInstore==null?'':items[i].countInstore)+"</span></td><td>" ;
		    		rowStr = rowStr +(items[i].packageAmount==null?'':items[i].packageAmount)+"</td><td>" +(items[i].lockAmount==null?'':items[i].lockAmount)+"</td><td>" +(items[i].shipmentAmount==null?'':items[i].shipmentAmount)+"</td><td>" +(items[i].cancelAmount==null?'':items[i].cancelAmount)+"</td><td>" +(items[i].needPurchase==null?'':items[i].needPurchase)+"</td><td>"+ (items[i].platShipfee==null?'':items[i].platShipfee)+ "</td></tr>";
		    		retStr+=rowStr;
		    	}
		    	retStr+="</table>";
		    	if (items.length>0){
		    		return otherStr+retStr;	
		    	}
		    	return otherStr+"";
		    }
		    
		  //批理生成包裹
			$("#gepackage").click(function(){
				var ids = xfy.getCheckedValues('id');
				var itemIds = xfy.getCheckedValues('itemId');
				if (ids.length<2){
					alert("请选择要生成包裹的订单");
					return false;
				}
				$.ajax({
				    url:'${pageContext.request.contextPath }/order/gepack',     
				    type:'post',
				    data:{orderId : ids,itemsId:itemIds},
				    async : false, //默认为true 异步
				    complete:function(){
				    	tb.api().ajax.reload();
				    },
				    error:function(){
				       alert('ajax error');
				    }, 
				    success:function(data){
				    	var jsondata = eval("("+data+")");
				    	if (!jsondata.result){
				    		alert(jsondata.error);	
				    	}
				    }
				});
			});
		    
			function editSku(curobj,id,sku,oid){
		    	var newsku = $(curobj).prev().val();
		    //	alert(sku+"\t" + newsku);
		    	if ($.trim(newsku)==''){
		    		alert("请输入sku");
		    		return false;
		    	}
		    	
		    	/* if ($.trim(newsku)==$.trim(sku)){
		    		return false;
		    	} */
		    	
		    	$.ajax({
				    url:'${pageContext.request.contextPath}/order/editSku-json',     
				    type:'post',
				    data:{"id":id,"sku":newsku,"oid":oid},     
				    async : false, //默认为true 异步     
				    error:function(){
				       alert('ajax error');     
				    }, 
				    success:function(data){
				    	var jsondata = eval("("+data+")");
				    	if(!jsondata.result){
				    		alert(jsondata.error);
				    		return;
				    	}
				    	tb.api().ajax.reload();
				    }
				}); 
			}
			
/* 		function exportShipping(){
				var path = '${pageContext.request.contextPath}/order/exportShipping';
				$('#searchform').attr("action", path).submit();; 
		} */
		
		function exportShipping(){
			var path = '${pageContext.request.contextPath}/order/exportShipping';
			$.ajax({
					url:path,
					type:'post',
					data: $("#searchform").serializeObject(),
					async : false,
					success:function(data){
						if(data == 'error'){
							alert("导出数量不能大于3W条,根据时间分批导出");
						}else{
							
							$('#searchform').attr("action", path).submit();
						}
					}
				}); 
		}
		
/* 		function exportOrder(){
			var path = '${pageContext.request.contextPath}/order/exportOrder';
			$('#searchform').attr("action", path).submit();
		} */
		
		function exportOrder(){
			var path = '${pageContext.request.contextPath}/order/exportOrder';
			var ids = xfy.getCheckedValues('id');
			if(ids == ''){
				$.ajax({
					url:path,
					type:'post',
					data: $("#searchform").serializeObject(),
					async : false,
					success:function(data){
						if(data == 'error'){
							alert("导出数量不能大于3W条,根据时间分批导出");
						}else{
							$('#searchform').attr("action", path).submit();
						}
					}
				});
				
			}else{
				 xfy.requestByForm({
					method: 'post',
					action: path,
					data: {ids : ids}
				});
			}
		}
		
/* 		function exportSale(){
			var path = '${pageContext.request.contextPath}/order/exportSale';
			$('#searchform').attr("action", path).submit();
		} */
		
		function exportSale(){
			var path = '${pageContext.request.contextPath}/order/exportSale';
			var ids = xfy.getCheckedValues('id');
			if(ids == ''){
				if(window.confirm('确定全部导出！')){
					$.ajax({
						url:path,
						type:'post',
						data: $("#searchform").serializeObject(),
						async : false,
						success:function(data){
							if(data == 'error'){
								alert("导出数量不能大于3W条,根据时间分批导出");
							}else{
								$('#searchform').attr("action", path).submit();
							}
						}
					});
				}
			}else{
				xfy.requestByForm({
					method: 'post',
					action: path,
					data: {ids : ids}
				});
			}
		}
		
		$("#orderPause").click(function(){
			var ids =$.trim(xfy.getCheckedValues('id'));
			var idAry = (ids.length==0)?new Array():ids.split(",");
			if (idAry.length<1){
				alert("请选择要暂停的订单");
				return;
			}
			if (confirm('请确认是否继续操作：' + $(this).text() + '？')) {
				$.ajax({
				    url:'${pageContext.request.contextPath }/order/gqhqy',     
				    type:'post',
				    data: {"orderId":ids,"suspendEnbale":1}, 
				    async : false, //默认为true 异步
				    complete:function(){
				    	tb.api().ajax.reload();
				    }
				});
			}
		});
		
		$("#orderResume").click(function(){
			var ids = $.trim(xfy.getCheckedValues('id'));
			var idAry = (ids.length==0)?new Array():ids.split(",");
			if (idAry.length<1){
				alert("请选择要暂停的订单");
				return;
			}
			if (confirm('请确认是否继续操作：' + $(this).text() + '？')) {
				$.ajax({
				    url:'${pageContext.request.contextPath }/order/gqhqy',     
				    type:'post',
				    data: {"orderId":ids,"suspendEnbale":0}, 
				    async : false, //默认为true 异步
				    complete:function(){
				    	tb.api().ajax.reload();
				    }
				});
			}
		});
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>