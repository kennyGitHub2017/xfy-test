<%--
角色页面
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="sysinfo" />

<c:set var="pageTitle" value="系统资讯" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">${pageTitle }</tiles:putAttribute>
	
		<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.css"/>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
			<div class="row">
				<div class="col-md-12">
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-cogs font-green-sharp"></i>
								<span class="caption-subject font-green-sharp bold uppercase">${pageTitle }</span>
							</div>
						</div>
						<div class="portlet-body">
							<%@include file="../include/message.jsp" %>
						
							<div class="table-toolbar">
								<div class="row">
									<form id="search_form" class="col-md-9 form-horizontal" role="form">
										<input type="hidden" name="type" value="${type}">
									</form>
								</div>
							</div>
						
							<table class="table table-striped table-bordered table-hover" id="allocateorder-table">
								<thead>
									<tr>
										<th>类型</th>
										<th>标题</th>
										<th>内容</th>
										<th>更新时间</th>
										<th>操作</th>
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
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-select/bootstrap-select.min.js" type="text/javascript"></script>
		<script>

		$(function(){
			xfy.initDataTable();
			var $datatable = loadDate();
			
			$('#search_btn').click(function() {
				if ($datatable) {
					$datatable.api().ajax.reload();
				}
				
				return false;
			});
		});
		
		//ajax load tabledata
		function loadDate(){
			return $("#allocateorder-table").dataTable({
				searching: true,
				serverSide: true,
				ordering:false,
				ajax: {
					url:"${pageContext.request.contextPath }/sysinfo/pageJson",
					contentType:"application/json;charset=UTF-8",	
					type: 'post',
					data: function (d) {
						d.params = $('#search_form').serializeObject();
						return JSON.stringify( d );
					}
				},
				columns: [
					{data: function(data){
						return data.type == 0 ? '卖家' : '内部'; 
					}},
					{data: "title"},
					{data: function(data){
						return data.content.substring(0,10)+"...."; 
					}},
					{data: function(data){
						return  new Date(data.lastUpdatedTime).format("yyyy-MM-dd HH:mm:ss");
					}},
					{data: function(row){
						return "";
					}},
				]
			});
		}
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>