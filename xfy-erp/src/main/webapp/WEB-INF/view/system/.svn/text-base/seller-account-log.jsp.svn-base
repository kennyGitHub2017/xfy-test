<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">绑定的账号</h4>
</div>

<div class="modal-body">

		<div class="row">
		
		
								<div class="col-md-12">
									<div class="form-group">
										<label class="col-md-3 control-label">SMT账号</label>
										<div class="col-md-9">
											<c:set var="str" value="" />
											<c:forEach items="${accountPlatform['smt']}" var="smt" varStatus="status">
												<c:set var="str" value="${smt.accountName}, ${str }" />
											</c:forEach>
											<textarea class="form-control"  rows="3" readonly="readonly">${str }</textarea>
										</div>
									</div>
								</div>
								<p>
								
								<div class="col-md-12">
									<div class="form-group">
										<label class="col-md-3 control-label">AMAZON账号</label>
										<div class="col-md-9">
											<c:set var="str1" value="" />
											<c:forEach items="${accountPlatform['amazon']}" var="amazon" varStatus="status">
												<c:set var="str1" value="${amazon.accountName}, ${str1 }" />
											</c:forEach>
											<textarea class="form-control"  rows="3" readonly="readonly">${str1 }</textarea>
										</div>
									</div>
								</div>
								
								<p>
								
								<div class="col-md-12">
									<div class="form-group">
										<label class="col-md-3 control-label">EBAY账号</label>
										<div class="col-md-9">
											
											<c:set var="str2" value="" />
											<c:forEach items="${accountPlatform['ebay']}" var="ebay" varStatus="status">
												<c:set var="str2" value="${ebay.accountName}, ${str2 }" />
											</c:forEach>
											<textarea class="form-control"  rows="3" readonly="readonly">${str2 }</textarea>
											
										</div>
									</div>
								</div>
								<p>
								
								<div class="col-md-12">
									<div class="form-group">
										<label class="col-md-3 control-label">Wish账号</label>
										<div class="col-md-9">
											<c:set var="str3" value="" />
											<c:forEach items="${accountPlatform['wish']}" var="wish" varStatus="status">
												<c:set var="str3" value="${wish.accountName}, ${str3 }" />
											</c:forEach>
											<textarea class="form-control"  rows="3" readonly="readonly">${str3 }</textarea>
										</div>
									</div>
								</div>
								
								
		
		</div>

				
				



</div>



<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
<script>


</script>
