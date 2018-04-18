<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>出入库单打印</title>
</head>
<body onload="printPage()">
    <table style="width: 800px; line-height: 20px; border: 1px solid #000;" cellspacing="0" cellpadding="0">
        <tr>
            <td colspan="2">
                <div style="width: 100px; margin: 0 auto; padding: 20px; text-align: center; border-bottom: 1px solid #000; padding-bottom: 3px;">${order.type == '1' ? '出':'入'}库单</div>
            </td>
        </tr>
        <tr>
            <td style="width: 50%; padding: 20px 50px;">${order.type == '1' ? '出':'入'}库单号：<span style="border-bottom: 1px solid #000; width: 20%;">${order.orderNo}</span></td>
            <td style="width: 50%; padding: 20px 50px;">${order.type == '1' ? '出':'入'}库流水号：<span style="border-bottom: 1px solid #000; width: 20%;">${order.serialNumber}</span></td>
        </tr>
        
        <tr>
            <td style="width: 50%; padding: 20px 50px;">${order.type == '1' ? '出':'入'}库类型：<span style="border-bottom: 1px solid #000; width: 20%;">${order.typeName}</span></td>
            <td style="width: 50%; padding: 20px 50px;">${order.type == '1' ? '出':'入'}库日期：<span style="border-bottom: 1px solid #000; width: 20%;"><fmt:formatDate value="${order.auditTime}" type="both"/></span></td>
            
        </tr>
        <tr>
        	<td colspan="2" style="width: 50%; padding: 20px 50px;">订单备注：<span style="border-bottom: 1px solid #000; width: 20%;">${order.note}</span></td>
        </tr>
        <tr>
            <td colspan="5">
                <table style="width: 100%; text-align: center; line-height: 20px; border-right: none; border-left: none;" cellspacing="0" cellpadding="0" border="1">
                    <tr>
                        <td style="width: 16%;">SKU</td>
                        <td style="width: 16%;">旧SKU</td>
                        <td style="width: 20%;">名称</td>
                        <td style="width: 16%;">数量</td>
                        <td style="width: 10%;">成本价</td>
                        <td style="width: 20%;">货位</td>

                    </tr>
					
					<c:forEach var="item" items="${orderItem}">
                    <tr>
                        <td>${item.goodsSku}</td>
                        <td>${item.oldSku}</td>
                        <td>${item.goodsName }</td>
                        <td>${item.qualifiedCount}</td>
                        <td>${item.goodsCost}</td>
                        <td>${item.shelfName}</td>
                    </tr>
                  </c:forEach>
                  
                </table>
            </td>
        </tr>
    </table>
</body>
<script type="text/javascript">


function printPage(){
	window.print();
}
</script>
</html>
