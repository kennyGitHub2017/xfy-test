<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="metronicTemplate">
	<tiles:putAttribute name="title">卖家权限设置</tiles:putAttribute>
	
		<tiles:putAttribute name="page-content">
		
		<div class="row">
			<div class="col-md-12">
				<div class="portlet light ">
					<div class="portlet-title">
						<div class="caption caption-md">
							<i class="icon-note theme-font"></i> <span class="caption-subject theme-font bold uppercase">子账户权限管理</span>
						</div>
					</div>
					
					<div class="portlet-body">
						<%@include file="/WEB-INF/view/include/message.jsp"%>
						
						<form method="post" id="sellerPowerForm">
						<input type="hidden" name="userId" value="${userId}"/>
						
						<div>
							<h4>SMT账号</h4>
							<div class="row">
								
								<c:forEach var="smtItem" items="${smtAttr}" varStatus="status">
									<c:set var="checked" value="" />
									<c:set var="endVar" value="300"/>
									
									<c:forEach var="upItem" items="${accountList}" end="${endVar}">
										<c:if test="${smtItem.id == upItem.accountId}">
											<c:set var="checked" value="checked" />
											 <c:set var="endVar" value="0" /> 
										</c:if>
									</c:forEach> 
									<label class="col-md-2"> <input type="checkbox"  name="accountId" value="${smtItem.id}" ${checked }/>${smtItem.accountName}</label>
								</c:forEach>
							</div>
						</div>
						
						<div>
							<h4>Ebay账号</h4>
							<div class="row">
								<c:forEach var="ebayItem" items="${ebayAttr}" varStatus="status">
									<c:set var="checked" value="" />
									<c:set var="endVar" value="300"/>
									<c:forEach var="upItem" items="${accountList}" end="${endVar}">
										<c:if test="${ebayItem.id == upItem.accountId}">
											<c:set var="checked" value="checked" />
											 <c:set var="endVar" value="0" /> 
										</c:if>
									</c:forEach> 
									
									<label class="col-md-2"> <input type="checkbox"  name="accountId" value="${ebayItem.id}" ${checked}/>${ebayItem.accountName}</label>
								</c:forEach>
							</div>
						</div>
						
						
						<div>
							<h4>Amazon账号</h4>
							<div class="row">
								<c:forEach var="amzItem" items="${amzAttr}" varStatus="status">
									<c:set var="checked" value="" />
									<c:set var="endVar" value="300"/>
									<c:forEach var="upItem" items="${accountList}" end="${endVar}">
										<c:if test="${amzItem.id == upItem.accountId}">
											<c:set var="checked" value="checked" />
											 <c:set var="endVar" value="0" /> 
										</c:if>
									</c:forEach> 
									<label class="col-md-2"> <input type="checkbox"  name="accountId" value="${amzItem.id}" ${checked }/>${amzItem.accountName}</label>
								</c:forEach>
							</div>
						</div>
						
						<div>
							<h4>Wish账号</h4>
							<div class="row">
								<c:forEach var="wishItem" items="${wishAttr}" varStatus="status">
									<c:set var="checked" value="" />
									<c:set var="endVar" value="300"/>
									<c:forEach var="upItem" items="${accountList}" end="${endVar}">
										<c:if test="${wishItem.id == upItem.accountId}">
											<c:set var="checked" value="checked" />
											<c:set var="endVar" value="0" /> 
										</c:if>
									</c:forEach> 
									<label class="col-md-2"> <input type="checkbox"  name="accountId" value="${wishItem.id}" ${checked }/>${wishItem.accountName}</label>
								</c:forEach>
							</div>
						</div>
						
						<div>
							<h4>Lazada账号</h4>
							<div class="row">
								<c:forEach var="lazadaItem" items="${lazadaAttr}" varStatus="status">
									<c:set var="checked" value="" />
									<c:set var="endVar" value="300"/>
									<c:forEach var="upItem" items="${accountList}" end="${endVar}">
										<c:if test="${lazadaItem.id == upItem.accountId}">
											<c:set var="checked" value="checked" />
											<c:set var="endVar" value="0" /> 
										</c:if>
									</c:forEach> 
									<label class="col-md-2"> <input type="checkbox"  name="accountId" value="${lazadaItem.id}" ${checked }/>${lazadaItem.accountName}</label>
								</c:forEach>
							</div>
						</div>
						
						</form>
					</div>
					
					<div class="modal-footer">
						<button type="button" onclick="save();" class="btn blue"><spring:message code="g.label.save"/></button>
					</div>
					
					
						
					</div>
					
				</div>
			</div>
		</div>
	</tiles:putAttribute>
	
	<%-- 页面级别的 JS --%>
	<tiles:putAttribute name="js-page">
		<script type="text/javascript">
		
		function save(){
			var account = xfy.getCheckedValues('accountId');
			if(account == ''){
				alert("请选择账号");
				return;
			}
			
			var path = '${pageContext.request.contextPath }/seller/saveSellerPower';
			$('#sellerPowerForm').attr("action", path).submit();
		}
		
		</script>
	</tiles:putAttribute>
	</tiles:insertDefinition>