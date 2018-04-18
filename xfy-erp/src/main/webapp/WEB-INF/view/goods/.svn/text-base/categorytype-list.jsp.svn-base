<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:set scope="request" var="sysModule" value="product" />
<c:set scope="request" var="sysPage" value="goods_category" />



<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">产品分类</tiles:putAttribute>
	
	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/admin/pages/css/todo.css"/>

	</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
			<div class="row">
				<div class="col-md-12">
					<%-- <div class="todo-sidebar">
						<div class="portlet light">
							<div class="portlet-title">
								<div class="caption" data-toggle="collapse" data-target=".todo-project-list-content">
									<span class="caption-subject font-green-sharp bold uppercase">产品分类 </span>
								</div>
								<div class="actions">
									<div class="btn-group">
										<a class="btn green-haze btn-circle btn-sm todo-projects-config" href="${pageContext.request.contextPath }/goodscategory/firstcgy-list">
										<i class="icon-settings"></i>
										</a>
									</div>
								</div>
							</div>
							<div class="portlet-body todo-project-list-content">
								<div class="todo-project-list">
									<ul class="nav nav-pills nav-stacked">
										<li>
											<a href="${pageContext.request.contextPath }/goodscategory/firstcgy-list">产品大类</a>
										</li>
										<li  class='active'>
											<a href="${pageContext.request.contextPath }/goodscategory/categorytype-list">产品类目</a>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div> --%>
					<div class="todo-content">
						<div class="portlet light">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-cogs font-green-sharp"></i>
									<span class="caption-subject font-green-sharp bold uppercase">产品类别</span>
								</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"></a>
								</div>
							</div>
							<div class="portlet-body">
								<%@include file="../include/message.jsp" %>
								
								<div class="table-toolbar">
									<div class="row">
										<div class="col-md-6">
											<div class="btn-group">
												<a class=" btn green" href="${pageContext.request.contextPath}/goodscategory/categorytype-form" data-target="#ajax" data-toggle="modal" data-backdrop="static">
													<spring:message code="g.label.add"/>  <i class="fa fa-plus"></i>
												</a>
											</div>
										</div>
										
										<div class="col-md-6">
											<div class="form-group form-group-sm">
												<div class="col-md-8">
													<input id="keys" type="text" class="form-control" name="country" />
												</div>
												<div class="col-md-4">
													<button type="button" id="searchbtn" class="btn blue btn-sm"><i class="fa fa-search"></i> <spring:message code="g.label.search" /></button>
												</div>
											</div>
										</div>
									</div>
								</div>
								
								
								
							<ul class="nav nav-tabs">
									<li>
										<a href="${pageContext.request.contextPath }/goodscategory/firstcgy-list">产品大类</a>
									</li>
									
									<li  class="active">
										<a href="${pageContext.request.contextPath }/goodscategory/categorytype-list">产品类目</a>
									</li>
							</ul>
							
								<div class="table-scrollable">
									<table id="category_list" class="table table-hover table-striped table-bordered table-condensed">
									<thead>
									<tr>
										<th>#</th>
										<th>产品类目</th>
										<th>编码</th>
										<th>备注</th>
										<th>类目级别</th>
										<th>操作</th>
									</tr>
									</thead>
									<tbody>
									<c:set var="rownum" value="1" />
									<c:forEach var="base" items="${allCategory}" varStatus="status">
										<tr>
											<td>${rownum }<c:set var="rownum" value="${rownum+1 }" /></td>
											<td>${base.name }</td>
											<td>${base.code }</td>
											<td>${base.note }</td>
											<td>1</td>
											<td>&nbsp;</td>
										</tr>
										<c:forEach var="mid" items="${base.childs }">
											<tr>
												<td>${rownum }<c:set var="rownum" value="${rownum+1 }" /></td>
												<td><span style="padding-left:25px;">${mid.name }</span></td>
												<td>${mid.code }</td>
												<td>${mid.note }</td>
												<td>2</td>
												<td>
													<a class="btn default btn-xs purple" href="${pageContext.request.contextPath }/goodscategory/categorytype-form?id=${mid.id}" data-target="#ajax" data-toggle="modal" data-backdrop="static">
														<i class="fa fa-edit"></i> <spring:message code="g.label.edit"/>
													</a>
													<a href="${pageContext.request.contextPath }/goodscategory/categorytype-remove/${mid.id}" class="btn default btn-xs black c-del-btns">
														<i class="fa fa-trash-o"></i> <spring:message code="g.label.delete"/>
													</a>
												</td>
											</tr>
											<c:forEach var="leaf" items="${mid.childs }">
												<tr>
													<td>${rownum }<c:set var="rownum" value="${rownum+1 }" /></td>
													<td><span style="padding-left:50px;">${leaf.name }</span></td>
													<td>${leaf.code }</td>
													<td>${leaf.note }</td>
													<td>3</td>
													<td>
														<a class="btn default btn-xs purple" href="${pageContext.request.contextPath }/goodscategory/categorytype-form?id=${leaf.id}" data-target="#ajax" data-toggle="modal" data-backdrop="static">
															<i class="fa fa-edit"></i> <spring:message code="g.label.edit"/>
														</a>
														<a href="${pageContext.request.contextPath }/goodscategory/categorytype-remove/${leaf.id}" class="btn default btn-xs black c-del-btns">
															<i class="fa fa-trash-o"></i> <spring:message code="g.label.delete"/>
														</a>
													</td>
												</tr>
											</c:forEach>
										</c:forEach>
									</c:forEach>
									</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<%@include file="/WEB-INF/view/include/modal-ajax.jsp" %>
	</tiles:putAttribute>
	
		
	<%-- 页面级别的 JS --%>
	 <tiles:putAttribute name="js-page">
	 <script>
		$("#searchbtn").click(function(){
			console.log('----------');
			var keywords = $("#keys").val();
			var $table = $('#category_list');
			var $trs = $table.find('tbody tr');
			
			$trs.each(function() {
				var $tr = $(this);
				var $tds = $tr.find('td');
				for (var i = 0; i < $tds.length; i++) {
					var $catNameTd = $($tds[1]);
					if ($catNameTd.text().indexOf(keywords) != -1) {
						 $catNameTd.closest('tr').addClass('success');
					} else {
						$catNameTd.closest('tr').removeClass('success');
					}
					
				}
			});
			
		});
	</script>
	 </tiles:putAttribute> 
</tiles:insertDefinition>