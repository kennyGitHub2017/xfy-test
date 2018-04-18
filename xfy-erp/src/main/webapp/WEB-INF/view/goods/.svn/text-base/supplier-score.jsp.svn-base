<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set scope="request" var="sysModule" value="purchase" />
<c:set scope="request" var="sysPage" value="supplier_score" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">供应商评分</tiles:putAttribute>
	
	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/select2/select2.css"/>

	</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
			<div class="row">
				<div class="col-md-12">
						<div class="portlet light">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-cogs font-green-sharp"></i>
									<span class="caption-subject font-green-sharp bold uppercase">供应商评分</span>
								</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"></a>
								</div>
							</div>
							<div class="portlet-body">
								<form:form id="searchform" action="score"  modelAttribute="search" method="post" class="form-horizontal" role="form" search="1">
								<div class="row">
									<div class="col-md-4">
										<div class="form-group form-group-sm">
											<label for="dict_code" class="col-md-2 control-label">供应商</label>
												<div class="col-md-10">
													<!--  
													<form:select path="supplierId" id="supplierId" cssClass="form-control">
														<form:option value="">请选择</form:option>
														<form:options items="${supplierList }" itemLabel="companyName" itemValue="id" />
													</form:select>
													-->
													
													<input type="text" class="form-control c-supplier-picker input-sm"  data-placeholder="${search.supplierName == null ? '请选择...' : search.supplierName}" />
													<form:hidden path="supplierId" id="supplierId" />
												</div>
										</div>
									</div>
									<div class="col-md-5">
										<div class="form-group">
												<label  class="col-md-2 control-label">入库日期</label>
												<div class="col-md-5">
														<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
															<input name="createdTimeFrom" value="${search.createdTimeFrom }" class="form-control input-sm" /> <span class="input-group-btn">
																<button class="btn default btn-sm" type="button">
																	<i class="fa fa-calendar"></i>
																</button>
															</span>
														</div>
												</div>
												<div class="col-md-5">
													<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
															<input name="createdTimeTo" value="${search.createdTimeTo }" class="form-control input-sm" /> <span class="input-group-btn">
																<button class="btn default btn-sm" type="button">
																	<i class="fa fa-calendar"></i>
																</button>
															</span>
													</div>
												</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group form-group-sm">
											<div class="col-md-12">
													<button type="button" id="searchbtn" class="btn green"><spring:message code="g.label.search" /></button>
													<button type="button" id="clearbtn" class="btn blue">清空</button>
											</div>
										</div>
									</div>
								  </div>		
								</form:form>
								<div class="table-scrollable">
									<table class="table table-hover table-striped table-bordered table-condensed" id="purchaseorder-table">
									<thead>
									<tr>
										<th>
											SKU
										</th>
										<th>
											名称
										</th>
										<th>
											采购入库单号
										</th>
										<th>
											入库日期
										</th>
										<th>
											检验数量
										</th>
										<th>
											不合格数量
										</th>
										<th>
											合格率
										</th>
										<th>
											不良率
										</th>
										<th>
											分数
										</th>
										<th>
											等级
										</th>
									</tr>
									</thead>
									<tbody>
										<c:forEach items="${detailList }" var="item" varStatus="idx">
											<tr style="${idx.last?'color:red':''}">
												<td >${item.testCount==null?'':item.goodsSku}</td>
												<td>
													${item.goodsName}
												</td>
												<td>${item.orderNo }</td>
												<td>
													<f:formatDate value="${item.orderCreated }"/>
												</td>
												<td>${item.testCount }</td>
												<td>${item.unqualifiedCount }</td>
												<td>${item.hgl }</td>
												<td>${ empty item.bll?'':item.bll.concat('%') }</td>
												<td>
													${idx.last?'供应商评分：':''}
													${item.score}
												</td>
												<td>
													<c:if test="${item.testCount!=null }">
															<c:choose>
																<c:when test="${item.score==100 }">
																	全优
																</c:when>
																<c:when test="${item.score==90 }">
																	优
																</c:when>
																<c:when test="${item.score==80 }">
																	良
																</c:when>
																<c:when test="${item.score==70 }">
																	及格
																</c:when>
																<c:when test="${item.score==60 }">
																	不合格
																</c:when>
															</c:choose>
													</c:if>
												</td>
											</tr>
										</c:forEach>
									</tbody>
									</table>
								</div>
							</div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
	<tiles:putAttribute name="js-page">
	<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/select2/select2.min.js" type="text/javascript"></script>
	<script>
		//datepicker show
		$('.date-picker').datepicker({
			orientation: "right",
			autoclose: true
		});
		$("#searchbtn").click(function(){
			var supplier = $("#supplierId").val();
			if (supplier==''){
				alert("请选择供应商");
				return;
			}
			$("#searchform").submit();
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