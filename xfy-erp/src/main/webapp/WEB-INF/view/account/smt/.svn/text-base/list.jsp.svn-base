<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:set scope="request" var="sysModule" value="system" />
<c:set scope="request" var="sysPage" value="smt_account" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">Smt 帐号管理 </tiles:putAttribute>
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
								<span class="caption-subject theme-font bold uppercase">Smt 帐号管理 </span>
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
										Smt帐号
									</th>
									<th>
										Mail
									</th>
									<th>
										 注册日期
									</th>
									<th>
										Token 有效期
									</th>
									<th>
										简称
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
					ajax: {url:"${pageContext.request.contextPath }/account/smt/pageJson",
							contentType:"application/json;charset=UTF-8",	
							type: 'post',
							data: function (d) {
								return JSON.stringify( d );
							}
						},
					columns: [
						{data: "accountName",name:'ACCOUNT_NAME'},
						{data: "mail","sortable":false},
						{data: "createdTime", className: "text-right", 
							render: function ( data, type, full, meta ) {
								return data == null ? "" : new Date(data).format("yyyy-MM-dd HH:mm:ss");
							},name:'CREATED_TIME'
						},
						{data:function(obj){
								    var ret = obj.smtExpireTime == null ? "" : new Date(obj.smtExpireTime).format("yyyy-MM-dd HH:mm:ss");
									if (obj.smtExpire){
										return "<font style='color:red'>" + ret+"</font>";
									}
									return ret;
							},className: "text-right",
							name:'SMT_EXPIRETIME'
						},
						{data: "abbreviation","sortable":false},
						{data: function(obj){
								var del = xfy.html.deleteLink.format('${pageContext.request.contextPath}/account/smt/remove/'+obj.id,'<spring:message code="g.label.delete"/>');
								var edit =  xfy.html.editLink.format('${pageContext.request.contextPath}/account/smt/editPage/'+obj.id,'<spring:message code="g.label.edit"/>');
								var updateToken =  xfy.html.editLink.format('${pageContext.request.contextPath}/account/smt/updateToken/'+obj.id,'<spring:message code="g.label.updateToken"/>');
								//var del= "<a  href='${pageContext.request.contextPath}/account/smt/remove/" +obj.id + "' class='btn default btn-xs black c-del-btns''><i class='fa fa-trash-o'></i><spring:message code='g.label.delete'/></a>" ;
								//var edit = "<a href='${pageContext.request.contextPath}/account/smt/editPage/" +obj.id + "' class='btn default btn-xs purple'><i class='fa fa-edit'></i><spring:message code='g.label.edit'/></a>" ;
								//var updateToken = "<a href='${pageContext.request.contextPath}/account/smt/updateToken/" +obj.id + "' class='btn default btn-xs purple'><i class='fa fa-edit'></i><spring:message code='g.label.updateToken'/></a>" ;
								if (obj.status!='2'){
									return edit+"&nbsp;&nbsp;"+updateToken+"&nbsp;&nbsp;"+del;	
								}else{
									return "";	
								}
							},"sortable":false
						}
					]
				});
				
				$("#sample_editable_1_new").click(function(){
					location.href="${pageContext.request.contextPath}/account/smt/addPage";
				});
			});
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>	