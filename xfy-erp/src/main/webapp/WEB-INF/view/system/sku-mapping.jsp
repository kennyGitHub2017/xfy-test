<%--
SKU映射维护
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>

<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="sku_mapping" />

<c:set var="pageTitle" value="SKU映射维护" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">${pageTitle }</tiles:putAttribute>
	
		<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/select2/select2.css"/>
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
								<div class="row">
									<div class="col-md-3">
										<div class="btn-group">
											<a class=" btn green" href="${pageContext.request.contextPath }/sku-mapping/form?action=add" data-target="#ajax" data-toggle="modal" data-backdrop="static">
												<spring:message code="g.label.add"/>  <i class="fa fa-plus"></i>
											</a>
										</div>
									</div>
									<form id="sm_search_form" class="col-md-9 form-horizontal" role="form" search="1">
										<div class="col-md-4">
											<div class="form-group form-group-sm">
												<label for="dict_code" class="col-md-3 control-label">平台SKU</label>
												<div class="col-md-8">
													<input type="text" class="form-control" name="platformSku"  />
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group form-group-sm">
												<label for="dict_code" class="col-md-3 control-label">系统旧SKU</label>
												<div class="col-md-8">
													<input type="text" class="form-control" name="oldSku"  />
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group form-group-sm">
												<label for="dict_code" class="col-md-3 control-label">系统新SKU</label>
												<div class="col-md-8">
													<input type="text" class="form-control" name="newSku"  />
												</div>
											</div>
										</div>
										
										<div class="col-md-6">
											<button type="button" id="sm_search_btn" class="btn blue"><spring:message code="g.label.search"/></button>
											<button type="button" id="clearbtn" class="btn blue">清空</button>
											<button type="button" id="sm_search_btn_reset" class="btn black"><spring:message code="g.label.reset"/></button>
										</div>
									</form>
								</div>
							</div>
							<table class="table table-striped table-bordered table-hover table-condensed" id="list_table">
							<thead>
							<tr>
								<th>平台SKU</th>
								<th>系统旧SKU</th>
								<th>系统新SKU</th>
								<th>创建时间</th>
								<th>操作</th>
							</tr>
							</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
			
			<%@include file="/WEB-INF/view/include/modal-ajax.jsp" %>
	</tiles:putAttribute>

	<%-- 页面级别的 JS --%>
	<tiles:putAttribute name="js-page">
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/select2/select2.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>



<script>

xfy.SkuMapping = {
	handleDataTable: function() {
		xfy.initDataTable();
		var $datatable = $("#list_table").dataTable({
			serverSide: true,
			ajax: {
				type: 'post',
				url: "${pageContext.request.contextPath }/sku-mapping/page.json",
				data: function (d) {
					d.params = $('#sm_search_form').serializeObject();
					return JSON.stringify( d );
				},
				dataType: 'json',
				contentType:"application/json;charset=UTF-8"
			},
			columns: [
				{data: "platformSku"},
				{data: "oldSku"},
				{data: "newSku"},
				{data: "createDate"},
				{className: "text-center", data: function ( row ) {
					var html = '';
					html += xfy.html.editLinkAjaxModal.format('${pageContext.request.contextPath }/sku-mapping/form?action=update&platformSku=' + row.platformSku, '<spring:message code="g.label.edit"/>');
					html += xfy.html.deleteLink.format('${pageContext.request.contextPath }/sku-mapping/delete?platformSku=' + row.platformSku, '<spring:message code="g.label.delete"/>');
					return html;
					}
				}
			],

		});
		
		return $datatable;
	},
	
};

$(function(){
	var $datatable = xfy.SkuMapping.handleDataTable();
	
	$('#sm_search_btn').click(function() {
		if ($datatable) {
			$datatable.api().ajax.reload();
			return false;
		}
		return false;
	});
	
	$('#sm_search_btn_reset').click(function() {
		$('input[name=platformSku]').val('');
		$('input[name=oldSku]').val('');
		$('input[name=newSku]').val('');
	});
});
</script>

	</tiles:putAttribute>

</tiles:insertDefinition>