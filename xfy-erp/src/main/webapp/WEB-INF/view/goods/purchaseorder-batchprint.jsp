<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>采购单批量打印</title>
	<link rel="stylesheet" type="text/css" href="/resources/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
	<style type="text/css">
		table {border: 1px solid #000;width: 80%;table-layout:fixed}
		table tbody tr td{word-wrap:break-word;border: 1px solid #000;height: 110px}
	</style>
	<script src="${pageContext.request.contextPath }/resources/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
</head>
<body>
	<form action="${pageContext.request.contextPath}/purchaseorder/batch-print" method="post" id="mform">
		<input type="radio" name="group" value="0"
			<c:if test="${group==0 }">checked="checked"</c:if>
		 >按供应商分组&nbsp;&nbsp;&nbsp;
		<input type="radio" name="group" value="1" 
			<c:if test="${group==1 }">checked="checked"</c:if>
		>按采购员分组
		&nbsp;&nbsp;
		<a href="#" id="columnConfig">显示设置</a>
		<div id="columndiv" style="display: none;">
			<input type="checkbox" name="showcolum" value="0" />序号
			<input type="checkbox" name="showcolum" value="1" />采购单号
			<input type="checkbox" name="showcolum" value="2" />供应商
			<input type="checkbox" name="showcolum" value="3" />产品编号
			<input type="checkbox" name="showcolum" value="4" />图片
			<input type="checkbox" name="showcolum"  value="5" />产品名称->属性
			<input type="checkbox" name="showcolum"  value="6" />产品单位
			<input type="checkbox" name="showcolum"  value="7" />产品重量
			<input type="checkbox" name="showcolum"  value="8" />支付方式
			<input type="checkbox" name="showcolum"  value="9" />采购单价
			<input type="checkbox" name="showcolum"  value="10" />最新价
			<input type="checkbox" name="showcolum" value="11" />采购数量
			<input  type="button" id="show" value="确定" />
		</div>
		<c:forEach items="${printOrderId }" var="item">
			<input type="hidden" name="orderId" value="${item }">
		</c:forEach>
	</form>
	<table id="mtable" class="table table-hover table-striped table-bordered table-condensed">
		<thead id="mthead">
			<tr>
				<td width="40px">序号</td>
				<td width="100px">采购单号</td>
				<td width="100px">供应商</td>
				<td width="100px">产品编号</td>
				<td width="82px">图片</td>
				<td width="100px">产品名称->属性</td>
				<td width="50px">产品单位</td>
				<td width="50px">产品重量</td>
				<td width="80px">支付方式</td>
				<td width="80px">采购单价</td>
				<td width="80px">最新价</td>
				<td width="80px">采购数量</td>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty group}">
					<c:forEach items="${data }" var="item" varStatus="po">
						<tr>
							<td>${po.index+1}</td>
							<td>${item.orderNo}</td>
							<td>${item.supplierName}</td>
							<td>${ item.goodsSku}</td>
							<td>
								<c:if test="${item.imgCount > 1}">
										<img  width="80px" height="60px" src="${x:goodsImageThumbnailPath(item.goodsSku, 1,'S') }">
								</c:if>
							</td>
							<td>${item.goodsName }->
								${empty item.color?"":item.color}-
								${empty item.goodsSize?"":item.goodsSize}-
								${empty item.rules?"":item.rules}-
								${empty item.model?"":item.model}
							</td>
							<td>${item.goodsUnit }</td>
							<td>${item.goodsWeight }</td>
							<td>${item.payMethod }</td>
							<td>${item.goodsCost }</td>
							<td>${item.newCost }</td>
							<td>${item.goodsCount }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach items="${data }" var="m" >
							<tr>
								<td colspan="12">${group==0?"供应商名称":"采购员名称"}:${m.key}</td>
							</tr>
						<c:forEach items="${m.value }" var="item" varStatus="po">
							<tr>
								<td>${po.index+1 }</td>
								<td>${item.orderNo}</td>
								<td>${item.supplierName}</td>
								<td>${ item.goodsSku}</td>
								<td>
									<c:if test="${item.imgCount > 1}">
											<img  width="80px" height="60px"  src="${x:goodsImageThumbnailPath(item.goodsSku, 1,'S') }">
									</c:if>
								</td>
								<td>${item.goodsName }->
									${empty item.color?"":item.color}-
									${empty item.goodsSize?"":item.goodsSize}-
									${empty item.rules?"":item.rules}-
									${empty item.model?"":item.model}
								</td>
								<td>${item.goodsUnit }</td>
								<td>${item.goodsWeight }</td>
								<td>${item.payMethod }</td>
								<td>${item.goodsCost }</td>
								<td>${item.newCost }</td>
								<td>${item.goodsCount }</td>
							</tr>
						</c:forEach>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<tr>
				<td colspan="12">打印时间:<f:formatDate value="<%=new java.util.Date() %>" pattern="yyyy-MM-dd HH:mm:ss" />  </td>
			</tr>
		</tbody>
	</table>
	<script type="text/javascript">
		var origTable;
		$(document).ready(function(){
			origTable = $("mtable").clone(true);
		});
		$('input[name="group"]').click(function(){
				$("#mform").submit();	
		});
		$("#columnConfig").click(function(){
			$("#columndiv").toggle();
		});
		
		$("#show").click(function(){
			var tdLength = $("#mthead>tr>td").length;
			//显示表格所有列
			 $("#mtable tr").each(function(){
            		 for(var i=0;i<tdLength;i++){
                		 $("td:eq("+i+")",this).show();
                	 }
               });
			 
			   var tempAry = new Array();
			   $("input[name='showcolum']").not(":checked").each(function(){
            	   tempAry.push($(this).val());
               })
               
               //隐藏表格指定列
               $("#mtable tr").each(function(){
            	 if (typeof($(this).children("td:eq(0)").attr("colspan"))=="undefined"){
            		 for(var i=0;i<tempAry.length;i++){
                		 $("td:eq("+tempAry[i]+")",this).hide();
                	 }
            	 }else{
            		 $(this).children("td:eq(0)").attr("colspan",tdLength-tempAry.length);
            	 }
               });
		});
	</script>
</body>
</html>