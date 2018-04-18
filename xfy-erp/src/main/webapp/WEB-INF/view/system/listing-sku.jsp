
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:set var="pageTitle" value="listingku 映射维护" />

<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="listing-sku" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">${pageTitle }</tiles:putAttribute>
	
		<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>

	</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
			<div class="row">
				<div class="col-md-12">
					<div class="portlet light ">
						<div class="portlet-title">
							<div class="caption caption-md">
								<i class="icon-note theme-font"></i>
								<span class="caption-subject theme-font bold uppercase">${pageTitle }</span>
							</div>
						</div>
						<div class="portlet-body">
							<%@include file="../include/message.jsp" %>
							<div class="table-toolbar">
								<form id="search_form" class="col-md-12 form-horizontal" method="post"  role="form" action="${pageContext.request.contextPath}/sku-mapping/listing-sku">
									<div class="row">
										<div class="col-md-3">
											<div class="form-group">
												<label for="dict_code" class="col-md-4 control-label">listing平台</label>
												<div class="col-md-8">
													<select class="form-control" name="platForm" id="platForm">
														<option value="">请选择...</option>
														<option value="smt"  <c:if test="${platForm=='smt'}">selected="selected"</c:if> >smt</option>
														<option value="amazon" <c:if test="${platForm=='amazon'}">selected="selected"</c:if>>amazon</option>
														<option value="ebay"  <c:if test="${platForm=='ebay'}">selected="selected"</c:if>>ebay</option>
													</select>
												</div>
											</div>
										</div>
										
										<div class="col-md-3">
											<div class="form-group">
												<label for="dict_code" class="col-md-4 control-label">平台SKU</label>
												<div class="col-md-8">
													<input type="text" class="form-control" name="sku" value="${sku }" />
												</div>
											</div>
										</div>
										
										<div class="col-md-3">
											<div class="form-group">
												<label for="dict_code" class="col-md-4 control-label"></label>
												<div class="col-md-8">
													<button type="submit" id="search_form_btn" class="btn blue"><spring:message code="g.label.search"/></button>
												</div>
											</div>
										</div>
									</div>
									
								</form>
							</div>
							<form action="${pageContext.request.contextPath}/sku-mapping/save-listing-sku" method="post">
								<input type="hidden" name="platForm" value="${platForm }">
								<c:if test="${not empty listSku}">
									<div align="right"><button type="submit" id="save_form_btn" class="btn green"><spring:message code="g.label.save"/></button></div>
								</c:if>
								<table class="table table-striped table-bordered table-hover table-condensed" id="list_table">
									<thead>
										<tr>
											<th></th>
											<th>所属平台</th>
											<th>平台SKU</th>
											<th>系统SKU</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${empty listSku}">
											<tr>
												<td colspan="4" align="center">暂无记录</td>
											</tr>
										</c:if>	
										<c:forEach items="${listSku }" var="item" varStatus="idx">
											<tr>
												<td>${idx.index +1}
													<input type="hidden" value="${item.id }" name="listSku[${idx.index}].id" />
													<input type="hidden" value="${item.platForm }" name="listSku[${idx.index}].platForm" />
												</td>
												<td align="center">${item.platForm }</td>
												<td align="center">${item.sku }<input type="hidden" value="${item.sku }" name="listSku[${idx.index}].sku" /></td>
												<td align="center"><input type="text" name="listSku[${idx.index}].goodsSku" value="${item.goodsSku }"></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<c:if test="${not empty listSku}">
									<div align="right"><button type="submit"  class="btn green"><spring:message code="g.label.save"/></button></div>
								</c:if>
							</form>	
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
			$("#save_form_btn").click(function(){
				
			});
		</script>
	</tiles:putAttribute>

</tiles:insertDefinition>