<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
		 	<c:if test="${authed==true}">
		 		<h2><spring:message  code="account.ebay.authsucc" arguments="${accountName}" /></h2>
		 	</c:if>
		  <c:if test="${authed==false}">
		  	<h2><spring:message code="account.ebay.authFail" arguments="${accountName}" /></h2>	
		  </c:if>
<script type="text/javascript">
	//关闭子窗口刷新父窗口
	function closeSelfAndRefreshEbayList(){
		if (window.opener!=null){
			window.opener.location.href="${pageContext.request.contextPath}/account/ebay/list";
		}
		window.opener=null;
		window.close();
	}
	
	window.onload=function(){
		window.setTimeout("closeSelfAndRefreshEbayList()", 3000);
	}
</script>