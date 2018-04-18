<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>采购单运号列表</title>
	<style type="text/css">
		table {border: 1px solid #000;width: 90%;table-layout:fixed}
		table tbody tr td{word-wrap:break-word;border: 1px solid #000;height: 80px}
	</style>
	<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
</head>
<body>
	<table  style="width:80%">
		<thead>
			<tr>
				<td>采购单号</td>
				<td>运单号</td>
				<td>物流公司</td>
				<td>创建时间</td>
				<td width="120px">操作</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${wayBillNos }" var="item" varStatus="idx">
				<c:if test="${not finished }">
					<form:form cssClass="form-horizontal" onsubmit="return  validSave(this)"  method="post"  action="${pageContext.request.contextPath}/purchaseorder/editWaybillNo" modelAttribute="${item }">
						<input  type="hidden" name="waybillNo" value="${item.waybillNo }">
						<input  type="hidden" name="logisticsCompany" value="${item.logisticsCompany }">
						<input  type="hidden" name="firstWaybillNo" value="${item.firstWaybillNo }">
						<tr>
							<td><input name="orderNo" readonly="readonly" value="${item.orderNo}" ></td>
							<td><input name="waybillNoNew"  value="${item.waybillNo }" ></td>
							<td><input name="logisticsCompanyNew"  value="${item.logisticsCompany }" ></td>
							<td><f:formatDate value="${item.createdTime }"  pattern="yyyy-MM-dd hh:mm:ss" /></td>
							<td>
									<input type="submit" value="保存">
							</td>
						</tr>
					</form:form>
				</c:if>
				<c:if test="${finished }">
					<tr>
							<td>${item.orderNo}</td>
							<td>${item.waybillNo }</td>
							<td>${item.logisticsCompany }</td>
							<td><f:formatDate value="${item.createdTime }"  pattern="yyyy-MM-dd hh:mm:ss" /></td>
							<td></td>
						</tr>
				</c:if>	
			</c:forEach>
		</tbody>
	</table>
	<script>
		//未发生改变，则不提交修改
		function validSave(frm){
			var oldWayBillNo = frm.elements["waybillNo"].value;
			var oldlogisticsCompany = frm.elements["logisticsCompany"].value;
			var newWayBillNo = frm.elements["waybillNoNew"].value;
			var newlogisticsCompany = frm.elements["logisticsCompanyNew"].value;
			//alert(oldWayBillNo+"\t"+newWayBillNo+"\t" +oldlogisticsCompany+"\t"+newlogisticsCompany);
			if ($.trim(oldWayBillNo)==$.trim(newWayBillNo) && $.trim(oldlogisticsCompany)==$.trim(newlogisticsCompany)){
				return false;
			}
			return true;
		}
	</script>
</body>
</html>