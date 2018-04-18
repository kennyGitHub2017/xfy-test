<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>采购单审核结果</title>
	<style type="text/css">
		table {border: 1px solid #000;width: 90%;table-layout:fixed}
		table tbody tr td{word-wrap:break-word;border: 1px solid #000;height: 80px}
	</style>
	<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
</head>
<body>
	<table  style="width:90%">
		<thead>
			<tr>
				<td>采购单号</td>
				<td>审核结果</td>
				<td>审核描述</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${errorMap }" var="item">
				<tr>
					<td>${item.key}</td>
					<td>审核失败</td>
					<td>${item.value }</td>
				</tr>	
			</c:forEach>
		</tbody>
	</table>
</body>
</html>