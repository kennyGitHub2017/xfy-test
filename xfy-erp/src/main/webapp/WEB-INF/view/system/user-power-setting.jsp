<%--
角色页面
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:set var="pageTitle" value="用户权限" />
<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">${pageTitle }</tiles:putAttribute>
	
	<%-- 页面级别的 CSS --%>
	<%-- <tiles:putAttribute name="css-page"></tiles:putAttribute> --%>
	
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
							
							<form action="${pageContext.request.contextPath }/user/power-setting" method="post">
							<input type="hidden" name="id" value="${param.id }" />
							<div class="table-scrollable">
								<table class="table table-bordered table-hover">
								<thead>
								<tr>
									<th>名称</th>
									<th>标识</th>
									<th>类型</th>
								</tr>
								</thead>
								<tbody>
								<c:forEach var="module" items="${modules }" varStatus="status">
								<tr>
									<td><label> <input type="checkbox" id="module_${module.code }" name="modulePower" value="${module.code }" ${powersMap.module.contains(module.code) ? 'checked' : '' } /> ${module.name }</label></td>
									<td>${module.code }</td>
									<td>模块</td>
								</tr>
								
								<c:forEach var="page" items="${module.pages }" varStatus="status">
								<tr>
									<td><label style="padding-left: 30px;"> <input type="checkbox" id="page_${page.code }" pid="module_${module.code }" name="pagePower" value="${page.code }" ${powersMap.page.contains(page.code) ? 'checked' : '' } /> ${page.name }</label></td>
									<td>${page.code }</td>
									<td>页面</td>
								</tr>
								
								<c:forEach var="function" items="${page.functions }" varStatus="status">
								<tr>
									<td><label style="padding-left: 60px;"> <input type="checkbox" id="function_${function.code }" pid="page_${page.code }" name="functionPower" value="${function.code }" ${powersMap.function.contains(function.code) ? 'checked' : '' } /> ${function.name }</label></td>
									<td>${function.code }</td>
									<td>功能</td>
								</tr>
								</c:forEach>
								
								</c:forEach>
								
								</c:forEach>
								</tbody>
								</table>
							</div>
							
							<div class="table-toolbar">
								<div class="row">
									<div class="col-md-10">
										<div class="btn-group">
											<button type="button" class="btn btn-default c-all-select" data-selector="input[type=checkbox]">全选</button>
											<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><i class="fa fa-angle-down"></i></button>
											<ul class="dropdown-menu" role="menu">
												<li>
													<a href="javascript:;" class="c-no-select" data-selector="input[type=checkbox]">全不选</a>
												</li>
												<li>
													<a href="javascript:;" class="c-reserve-select" data-selector="input[type=checkbox]">反选</a>
												</li>
											</ul>
										</div>
										<button type="submit" class="btn blue" > 保存 </button>
									</div>
								</div>
							</div>
							
							</form>
						</div>
					</div>
				</div>
			</div>
			
			<%@include file="/WEB-INF/view/include/modal-ajax.jsp" %>
	</tiles:putAttribute>
	
	<%-- 页面级别的 JS --%>
	<tiles:putAttribute name="js-page">
<script>
$(function() {
	$('input[type=checkbox]').click(function() {
		// 选中节点时判断需要选中上代节点
		// 取消选中时，若孩子节点为选中，则不能取消
		var $this = $(this);
		if ($this.attr('checked')) {
			var $cur = $this;
			while (true) {
				var pid = $cur.attr('pid');
				if (pid && $.trim(pid).length > 0) {
					$cur = $('#' + pid);
					if ($cur.length == 0) {
						break;
					}
					$cur.attr('checked', 'checked');
					$cur.uniform();
				} else {
					break;
				}
			}
		} else {
			var id = $this.attr('id');
			var checked = false;
			$('input[pid=' + id + ']').each(function() {
				if ($(this).attr('checked')) {
					checked = true;
				}
			});
			
			if (checked) {
				$(this).attr('checked', 'checked');
				$(this).uniform();
			}
		}
		
	});
});
</script>
	</tiles:putAttribute>
</tiles:insertDefinition>