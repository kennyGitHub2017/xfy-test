<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
		  <h2><spring:message  code="account.smt.authInfo" arguments="${accontName}"  /></h2>
<script type="text/javascript">
	//关闭子窗口刷新父窗口
	function closeSelfAndRefreshSmtList(){
		if (window.opener!=null){
			window.opener.location.href="${pageContext.request.contextPath}/account/smt/list";
		}
		window.opener=null;
		window.close();
	}
	
	window.onload=function(){
		window.setTimeout("closeSelfAndRefreshSmtList()", 3000);
	}
</script>