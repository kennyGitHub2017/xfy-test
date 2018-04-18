<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html>
<style media="print"> 
.noprint { display: none } 
</style> 
<style type="text/css">
	body {
	margin-top: 0px;
}
.STYLE5 {font-size: 10px}
</style>
<body  onload="javascript:window.print()">
		<div style='width:210mm'>
			<c:set var="rowcount" value="${fn:length(printPackage)}"></c:set>
			<c:forEach items="${printPackage}" varStatus="pkeidx"  var="pke">
						<table width="200" height="100" border="0" cellpadding="0" cellspacing="0" style="border:1px dashed #999999; width:105mm; height:73mm; padding:1mm;font-size:14px; float:left">
      						<tbody>
      							 <tr>
       									 <td width="100%" valign="top" height="55">
       									 	<table border="0" cellpadding="0" cellspacing="0" style="width:100%; font-size:12px;">
       									 		<tbody>
       									 		<tr>
	       									 		<c:forEach items="${pke.orderPackageItem }" var="pitem" varStatus="idx">
	       									 				<c:if test="${idx.index!=0 && idx.index%2==0}">
	       									 					</tr>
	       									 					<tr id='newrow'>
	       									 				</c:if>
															<td style="height:18px;width:50%;">${pitem.sku }+${pitem.goodsName }*${pitem.orderAmount}<input type="checkbox" /></div></td>
															<c:if test="${idx.last }">
																<c:if test="${idx.index!=0 && idx.index+1%2==0 }">
																	</tr>
																</c:if>
																<c:if test="${idx.index!=0 && idx.index+1%2!=0 }">
																	<td style="width:50%;height:18px;">&nbsp;</td></tr>	
																</c:if>
	       									 				</c:if>
	       									 		</c:forEach>
	       									 	</tbody>	
		 									</table>
		 								</td>
		 						  </tr>
						          <tr>
						            <td>
									<div style='width:62%;float:left;height:180px;padding-left:5px;font-size:12px;line-height:20px'>
										${pke.printReceiverInfo}<br>
									</div>
									<div style='width:35%;float:right;line-height:25px'>
										<span style='font-size:12px'>运输:</span><br/><span style='font-size:12px'>${pke.shippingName }</span><br/>
										<span style='font-size:12px'>包装:${pke.specifications }</span><br/>
										<span style='font-size:12px'>付款</span>: <fmt:formatDate value="${pke.order.orderPaidTime }" /><br/>
										<img src="${pageContext.request.contextPath}/barcode?keycode=${pke.trackNumber}" alt="" style='width:99%;height:50px;'/>
										<span style='margin-left:45px'>${pke.trackNumber}</span>
									</div>
									<div style='clear:both'></div>
									</td>
						          </tr>
						        </tbody>
    					</table>
    					<c:if test="${pkeidx.index+1%8==0}">
    							<c:if test="${pkeidx.index!=rowcount}">
    								<div style='clear:both'></div>
  									<div style="page-break-after:always;"></div> 
    							</c:if>
    					</c:if>
			</c:forEach>
		</div>
</body>
</html>