<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="amazon_account" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">Amazon帐号管理 </tiles:putAttribute>
	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
			<%@include file="../../include/message.jsp" %>
			<div class="row">
				<div class="col-md-12">
					<div class="portlet light ">
						<div class="portlet-title">
							<div class="caption caption-md">
								<i class="icon-note theme-font"></i>
								<span class="caption-subject theme-font bold uppercase">Amazon帐号管理</span>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="row">
									<div class="col-md-6">
										<div class="btn-group">
											<button id="sample_editable_1_new" class="btn green">
											<spring:message code="g.label.add"/> <i class="fa fa-plus"></i>
											</button>
										</div>
									</div>
								</div>
							</div>
							<table class="table table-striped table-bordered table-hover" id="account_table">
							<thead>
							<tr>
									<th>
										帐号名称
									</th>
									<th class="hidden-xs">
										 Merchant ID
									</th>
									<th>
										 MarketPlace ID
									</th>
									<th>
										Service URL
									</th>
									<th>
										帐号状态
									</th>
									<th>
										操作
									</th>
							</tr>
							</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
	</tiles:putAttribute>
	
	<tiles:putAttribute name="js-page">
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
		<script>
			$(function(){
				xfy.initDataTable();
				$("#account_table").dataTable({
					searching: true,
					serverSide: true,
					ordering:true,
					ajax: {
						url:"${pageContext.request.contextPath }/account/am/pageJson",
						contentType:"application/json;charset=UTF-8",	
						type: 'post',
						data: function (d) {
							return JSON.stringify( d );
						}
					},
					columns: [
						{data: "accountName",name:"ACCOUNT_NAME"},
						{data: "amMerchantId","sortable":false},
						{data: "amMarketplaceId","sortable":false},
						{data: "amServiceUrl","sortable":false},
						{
						 data:function(obj){
							 if (obj.status=='0'){
								 return "未启用";
							 }else if(obj.status=='1'){
								 return "正常";
							 }else if(obj.status=='2'){
								return "<font style='color:red'>已删除</font>"; 
							 }
						 },"sortable":false
						},
						{data: function(obj){
								var del = xfy.html.deleteLink.format('${pageContext.request.contextPath}/account/am/remove/'+obj.id,'<spring:message code="g.label.delete"/>');
								//var del= "<a  href='${pageContext.request.contextPath}/account/am/remove/" +obj.id + "' class='btn default btn-xs black c-del-btns'><i class='fa fa-trash-o'></i><spring:message code='g.label.delete'/></a>" ;
								//var edit = "<a href='${pageContext.request.contextPath}/account/am/editPage/" +obj.id + "' class='btn default btn-xs purple'><i class='fa fa-edit'></i><spring:message code='g.label.edit'/></a>" ;
								var edit =  xfy.html.editLink.format('${pageContext.request.contextPath}/account/am/editPage/'+obj.id,'<spring:message code="g.label.edit"/>');
								if (obj.status!='2'){
									return edit+"&nbsp;&nbsp;"+del;	
								}else{
									return "";	
								}
							},"sortable":false
						}
					]
				});
				
				$("#sample_editable_1_new").click(function(){
					location.href="${pageContext.request.contextPath}/account/am/addPage";
				});
				
			});
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>