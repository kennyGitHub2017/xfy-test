<%--
平台账号查询
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="platform_account" />

<c:set var="pavaTitle" value="平台账号查询" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">${pavaTitle }</tiles:putAttribute>

	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css" />


	</tiles:putAttribute>

	<tiles:putAttribute name="page-content">
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light ">
					<div class="portlet-title">
						<div class="caption caption-md">
							<i class="icon-note theme-font"></i> <span class="caption-subject theme-font bold uppercase">${pavaTitle }</span>
						</div>
					</div>
					<div class="portlet-body">
					
						<div class="table-toolbar">
							<form id="search_form" class="form-horizontal" role="form"  modelAttribute="search" search="1" method="post">
								<div class="row">
								
									<div class="col-md-3">
										<div class="form-group">
											<label  class="col-md-4 control-label">卖家账号</label>
											 <div class="col-md-8">
												<input type="text" name="accountName" class="form-control c-supplier-picker input-sm" value=""  />
											</div>
										</div>
									</div>
									
										<div class="col-md-3">
										<div class="form-group">
											<label  class="col-md-4 control-label">是否自营</label>
											 <div class="col-md-8">
											 	<select name="selfFlag" class="form-control">
											 		<option value="">请选择</option>
											 		<option value="1">是</option>
											 		<option value="0">否</option>
											 	</select>
											</div>
										</div>
									</div>
									
									<div class="col-md-3">
										<div class="form-group">
											<label  class="col-md-4 control-label">是否Vip</label>
											<div class="col-md-8">
												<select name="vipFlag" class="form-control">
													<option value="">请选择</option>
													<option value="1">是</option>
													<option value="0">否</option>
												</select>
											</div>
										</div>
									</div>
									
									<div class="col-md-3">
										<div class="form-group">
											<label  class="col-md-3 control-label"></label>
											<button type="button" id="form_search_btn" class="btn green btn-sm">
												<spring:message code="g.label.search" />
											</button>
											<button type="button" id="clearbtn" class="btn blue btn-sm">清空</button>
										</div>
									</div>
									
								</div>
								
								
								<div class="row">
										
									<div class="col-md-3">
											<div class="form-group">
											<label class="col-md-4 control-label">所属平台</label>
												<div class="col-md-8">
													<select name="platform" class="form-control">
														<option value="">请选择</option>
														<option value="smt">smt</option>
														<option value="amazon">amazon</option>
														<option value="ebay">ebay</option>
														<option value="wish">wish</option>
													</select>
												</div>
											</div>
									</div>
									
									<div class="col-md-4">
										<div class="form-group">
											<label  class="col-md-2 control-label">资料审核</label>
											<div class="col-md-5">
													<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
														<input name="auditDateStart" class="form-control input-sm" /> <span class="input-group-btn">
															<button class="btn default btn-sm" type="button">
																<i class="fa fa-calendar"></i>
															</button>
														</span>
													</div>
											</div>
											<div class="col-md-5">
												<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
														<input name="auditDateEnd" class="form-control input-sm" /> <span class="input-group-btn">
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
											<label  class="col-md-2 control-label">资料提交</label>
											<div class="col-md-5">
													<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
														<input name="applyDateStart" class="form-control input-sm" /> <span class="input-group-btn">
															<button class="btn default btn-sm" type="button">
																<i class="fa fa-calendar"></i>
															</button>
														</span>
													</div>
											</div>
											<div class="col-md-5">
												<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
														<input name="applyDateEnd" class="form-control input-sm" /> <span class="input-group-btn">
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
									<div class="col-md-3">
										<div class="form-group form-group-sm">										
											<label  class="col-md-4 control-label">卖家选择</label>
											<div class="col-md-8">
													<select name="sellerId" class="form-control">
														<option value="">请选择</option>
														<c:forEach var="item" items="${sellerList}">
														<option value="${item.id}">${item.contacts}</option>
														</c:forEach>
													</select>
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>
						

						<table class="table table-bordered table-hover" id="account_table">
							<thead>
								<tr>
									<th>平台账号</th>
									<th>所属平台</th>
									<th>所属卖家</th>
									<th>是否自营</th>
									<th>是否VIP</th>
									<th>订单数量</th>
									<th>资料提交时间</th>
								</tr>
							</thead>
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


		<script>
		var dt;
		
		function loadPageData(){
			dt = $("#account_table").dataTable({
				serverSide: true,
				ajax: {
					url: "${pageContext.request.contextPath }/platform-account/pageJson",
					data: function (d) {
						d.params = $('#search_form').serializeObject();
						return JSON.stringify( d );
					},
					type: 'post',
					contentType:"application/json;charset=UTF-8"
				},
				columns: [
					{className: 'text-center',
						data: function(data){
							if(data.platform == "ebay"){
								return "<a target='blank' href='http://www.ebay.com/usr/" +data.accountName+"' >"+data.accountName+"</a>";
							} else if(data.platform == "amazon"){
								var url;
								if(data.marketPlaceId == "ATVPDKIKX0DER"){
									url = "http://www.amazon.com/s?marketplaceID={0}&me={1}&merchant={2}&redirect=true".format(data.marketPlaceId,data.merchantId,data.merchantId);
									return "<a target='blank' href='" + url +"' >"+data.accountName+"</a>";
								}  else if(data.marketPlaceId == "A1F83G8C2ARO7P"){
									url = "http://www.amazon.co.uk/s?marketplaceID={0}&me={1}&merchant={2}&redirect=true".format(data.marketPlaceId,data.merchantId,data.merchantId);
									return "<a target='blank' href='" + url +"' >"+data.accountName+"</a>";
								} else if(data.marketPlaceId == "A13V1IB3VIYZZH"){
									url = "http://www.amazon.fr/s?marketplaceID={0}&me={1}&merchant={2}&redirect=true".format(data.marketPlaceId,data.merchantId,data.merchantId);
									return "<a target='blank' href='" + url +"' >"+data.accountName+"</a>";
								} else if(data.marketPlaceId == "A1PA6795UKMFR9"){
									url = "http://www.amazon.de/s?marketplaceID={0}&me={1}&merchant={2}&redirect=true".format(data.marketPlaceId,data.merchantId,data.merchantId);
									return "<a target='blank' href='" + url +"' >"+data.accountName+"</a>";
								} else if(data.marketPlaceId == "A1RKKUPIHCS9HS"){
									url = "http://www.amazon.es/s?marketplaceID={0}&me={1}&merchant={2}&redirect=true".format(data.marketPlaceId,data.merchantId,data.merchantId);
									return "<a target='blank' href='" + url +"' >"+data.accountName+"</a>";
								} else if(data.marketPlaceId == "A2EUQ1WTGCTBG2"){
									url = "http://www.amazon.ca/s?marketplaceID={0}&me={1}&merchant={2}&redirect=true".format(data.marketPlaceId,data.merchantId,data.merchantId);
									return "<a target='blank' href='" + url +"' >"+data.accountName+"</a>";
								} else if(data.marketPlaceId == "A21TJRUUN4KGV"){
									url = "http://www.amazon.in/s?marketplaceID={0}&me={1}&merchant={2}&redirect=true".format(data.marketPlaceId,data.merchantId,data.merchantId);
									return "<a target='blank' href='" + url +"' >"+data.accountName+"</a>";
								} else if(data.marketPlaceId == "AAHKV2X7AFYLW"){
									url = "http://www.amazon.cn/s?marketplaceID={0}&me={1}&merchant={2}&redirect=true".format(data.marketPlaceId,data.merchantId,data.merchantId);
									return "<a target='blank' href='" + url +"' >"+data.accountName+"</a>";
								} else if(data.marketPlaceId == "A1VC38T7YXB528"){
									url = "http://www.amazon.co.jp/s?marketplaceID={0}&me={1}&merchant={2}&redirect=true".format(data.marketPlaceId,data.merchantId,data.merchantId);
									return "<a target='blank' href='" + url +"' >"+data.accountName+"</a>";
								}  else if(data.marketPlaceId == "APJ6JRA9NG5V4"){
									url = "http://www.amazon.it/s?marketplaceID={0}&me={1}&merchant={2}&redirect=true".format(data.marketPlaceId,data.merchantId,data.merchantId);
									return "<a target='blank' href='" + url +"' >"+data.accountName+"</a>";
								}  
							} else if(data.platform == "wish"){
								return "<a target='blank' href='https://www.wish.com/merchant/" +data.merchantName+"' >"+data.accountName+"</a>";
							}
							else {
								return data.accountName;
							}
							
						}},
					{className: 'text-center',data: "platform"},  
					{className: 'text-center',data: "contacts"},  
					{className: 'text-center',data: function(data){
						return data.selfFlag==1?'是':'否';
					}},  
					{className: 'text-center',data: function(data){
						return data.vipFlag==1?'是':'否';
					}},  
					{className: 'text-center',data: "orderCount"},
					{data:function(data){
						return data.applyCertTime?data.applyCertTime:"";
					},className: 'text-center'}
				]
			});
		}
		
		$(function(){
			xfy.initDataTable();
			loadPageData();
			$('#form_search_btn').click(function() {
				if (!dt){
					loadPageData();
				}else{
					dt.api().ajax.reload();	
				}
			});
		});
		
		
		
		</script>

	</tiles:putAttribute>

</tiles:insertDefinition>