<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set scope="request" var="sysModule" value="order" />
<c:set scope="request" var="sysPage" value="order-package" />

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">包裹批量修改</tiles:putAttribute>
	
	<%-- 页面级别的 CSS --%>
	<tiles:putAttribute name="css-page">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>

	</tiles:putAttribute>
	
	<tiles:putAttribute name="page-content">
			<div class="row">
				<div class="col-md-12">
						<div class="portlet light">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-cogs font-green-sharp"></i>
									<span class="caption-subject font-green-sharp bold uppercase">包裹批量修改</span>
								</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"></a>
								</div>
							</div>
					<form  action="${pageContext.request.contextPath }/order-package/batchedit" name="form1"  id="form1" method="post">
							<c:forEach items="${ids}" var="item">
								<input type="hidden" value="${item}" name="id">
							</c:forEach>
							<div class="portlet-body">
								<div class="table-toolbar">
									<div class="row">
										<div class="col-md-6">
											<div>
												批量修改：您已经选择了
													<c:if test="${empty ids}">
															所有
													</c:if>
													<c:if test="${not empty ids}">
															${idCount}条
													</c:if>
												记录，请在需要批量修改的地方输入新值
											</div>
										</div>
									</div>
								</div>
								<div class="">
									<table border="0" style="width:360px;border-collapse:separate; border-spacing:10px;" >
									<tr>
										<td width="80px">
											运输方式
										</td>
										<td width="200px">
											<select name="shippingName" class="form-control" id="shippingName">
												<c:forEach items="${shippingList}" var="item">
													<option value="${item.shippingName}">${item.shippingName }</option>
												</c:forEach>
											</select>
										</td>
										<td>
											&nbsp;
										</td>
									</tr>
									<tr>
										<td colspan="3" align="center">
											<input type="submit" id="saveBtn" value="保存数据" />
										</td>
									</tr>
									</table>
								</div>
							</div>
						</form>	
				</div>
			</div>
		</div>
	</tiles:putAttribute>
	
		
	<%-- 页面级别的 JS --%>
	<tiles:putAttribute name="js-page">
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>