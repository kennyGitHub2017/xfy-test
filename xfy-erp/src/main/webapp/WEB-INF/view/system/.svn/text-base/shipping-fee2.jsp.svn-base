<%--
运费参数
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:set var="pavaTitle" value="重量区间运费参数" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">重量区间运费参数</tiles:putAttribute>

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
							<div class="table-toolbar">
							  <form id="search_form" class="form-horizontal" role="form" search="1" method="post">
							 <input value="${shipId}" type="hidden" name="shippingId">
						 		<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label  class="col-md-2 control-label">国家</label>
												<div class="col-md-6">
													<input type="text" class="form-control input-sm" name="country" />
												</div>
										</div>
									</div>
										
							
									
									<div class="col-md-3">
										<div class="form-group">
											<label  class="col-md-2 control-label">重量区间</label>
											<div class="col-md-3">
													<input type="text" class="form-control input-sm" name="weightFrom" />
											</div>
										
											<div class="col-md-3">
													<input type="text" class="form-control input-sm" name="weightTo" />
											</div>
										</div>
									</div>
									
									<div class="col-md-3">
										<div class="form-group">
											<label  class="col-md-2 control-label">运费</label>
												<div class="col-md-6">
													<input type="text" class="form-control input-sm" name="shippFee" />
												</div>
										</div>
									</div>
									
									<div class="col-md-2">
										<div class="form-group">
											<button type="button" id="form_search_btn" class="btn green btn-sm">
												<spring:message code="g.label.search" />
											</button>
											<button type="button" id="clearbtn" class="btn blue btn-sm">清空</button>
										</div>
									</div>
									
								</div> 
								
							</form>  
						</div>
						
						
						
									<div class="row">
							<div class="col-md-6">
								<div class="btn-group">
										<a class=" btn green" href="${pageContext.request.contextPath }/shipping/editFee2?shipId=${shipId}" 
											data-target="#ajax" data-toggle="modal" data-backdrop="static">
										<spring:message code="g.label.add"/> <i class="fa fa-plus"></i>
									</a>
								</div>
							</div>
						</div>
					
						</div>

						<table class="table table-bordered table-hover" id="fee_table">
							<thead>
								<tr>
									<th>ID</th>
									<th>运输方式</th>
									<th>国家</th>
									<th>重量从Kg</th>
									<th>重量到Kg</th>
									<th>运费(元)</th>
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
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>


		<script>
		
		$(function(){
			xfy.initDataTable();
			loadDate();
		});
		
		function loadDate(){
			tb = $("#fee_table").dataTable({
				searching: false,
				serverSide: true,
				ordering:false,
				ajax: {
					url:"${pageContext.request.contextPath }/shipping/page-json",
					contentType:"application/json;charset=UTF-8",	
					type: 'post',
					data: function (d) {
						 d.params = $('#search_form').serializeObject();
						return JSON.stringify( d );
					}
				},
				columns: [
			  		{data: "id"},
			  		{data:"shippingName"},
					{data: "country"},
					{data: "weightFrom"},
					{data: "weightTo"},
					{data: "shippFee"},
					{
						data:function(row){
							var edit = '';
							edit +='<a href="${pageContext.request.contextPath }/shipping/removeShipFee2?id= '+row.id+'" + id class="btn default btn-xs black c-del-btns"> <i class="fa fa-trash-o"></i> <spring:message code="g.label.delete" /></a>';
							edit += '<a href="${pageContext.request.contextPath }/shipping/updateShippFee2?id='+row.id+'"  class="btn default btn-xs purple" data-target="#ajax" data-toggle="modal"> <i class="fa fa-edit"></i>修改运费</a>';
							return edit;
						}
					}
				]
			});
			
			$('#form_search_btn').click(function() {
				tb.api().ajax.reload();
			});
		}

		
		</script>

	</tiles:putAttribute>

</tiles:insertDefinition>