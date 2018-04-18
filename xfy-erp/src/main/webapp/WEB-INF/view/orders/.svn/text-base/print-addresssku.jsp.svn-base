<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html>
<style media="print"> 
.noprint { display: none } 
</style> 
<style type="text/css">
.table {border: 1px dotted #CCCCCC; }
.STYLE16 {font-family: Arial, Helvetica, sans-serif}
.STYLE17 {font-size: 12px}
</style>
<body  onload="javascript:window.print()">
			<c:forEach items="${printPackage}" varStatus="pkeidx"  var="pke">
						<table width="712" border="1" cellpadding="0" cellspacing="0" bordercolor="#000000" style="width:200mm; text-align: right;">
      						<tbody>
      							 <tr>
       									<td width="480" align="center" valign="middle">
       									<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding:4px">
       									 		<tbody>
	       									 			<tr>
															 <td width="0%"><span class="STYLE16"></span></td>
       														 <td colspan="3">
       														 	<div align="left" style="margin:0px 0px 5px 10px; font-size:18px; font-family:Arial, Helvetica, sans-serif">
																	TO:<br>${empty pke.order? "":pke.order.buyinfo.shippingStreet1},${empty pke.order? "":pke.order.buyinfo.shippingStreet2}
																	   <br>${empty pke.order? "":pke.order.buyinfo.shippingCity},${empty pke.order? "":pke.order.buyinfo.shippingState},${empty pke.order? "":pke.order.buyinfo.shippingPostcode}
																	   <br>${empty pke.order? "":pke.order.buyinfo.shippingCountry}(${empty pke.order? "":pke.order.buyinfo.shippingCountryName})
																	   <br>Tel/Mobile:${empty pke.order? "":pke.order.buyinfo.shippingPhone}/${empty pke.order? "":pke.order.buyinfo.shippingMobile}
																</div>
															 </td>
															 <td width="31%" height="100" valign="top">
															 	<span class="STYLE16"></span>
															 	 <table width="80%" border="0" cellspacing="0" cellpadding="0">
															 	 		<tr>
              																<td><img src="${pageContext.request.contextPath}/barcode?keycode=${pke.trackNumber}"  border="0" alt="" width="144" height="61"/></td>
              															</tr>
              															<tr>
	              															 <td>
	              															 	<div align="center">${pke.trackNumber }</div>
	              															 	 <div align="center"></div>
	              															 </td>
              															 </tr>
															 	 </table>
															 </td>
														 </tr> 
	       									 	</tbody>	
		 									</table>
		 								</td>
		 								<td width="308" align="center" valign="top">
		 									<table width="100%" border="0" cellspacing="1" cellpadding="3">
		 									 	<tr>
         											 	<td>
         											 			
         											 		<div style='position:relative;width:308px'>
         											 			<div align="left" style="margin:10px; font-size:12px; font-family:Arial, Helvetica, sans-serif;width:160px;float:left">
         											 				<c:forEach items="${pke.orderPackageItem }" var="pitem" varStatus="idx">
         											 					<c:if test="${idx.last }">
         											 						<c:set var="imageUrl"  value="${pitem.oim.itemPic }"  />
         											 					</c:if>
         											 					<font style='font-weight: bold;'>${pitem.sku }(${pitem.goodsName}) X ${pitem.orderAmount}/</font><br>
         											 				</c:forEach>
         											 			</div>
         											 			<img src='${imageUrl}' width='50' height='50' style='float:right; margin:5px' />
         											 			<span style='position:absolute; font-size:12px; top:6px; right:60px;'>
         											 				<fmt:formatDate value="${pke.order.orderPaidTime }" />
         											 			</span>
         											 		</div>
         										 		</td>
         										</tr>
         										<tr>
         											 <td>
	         											 <span class="STYLE17">运输方式:${pke.shippingName } <br>
	         											 	包装规格:${pke.specifications }
	         											 </span>
         											 </td>	 
         										</tr>
         										<tr>
         											<td align="right">
         												<span class="STYLE17">&nbsp;</span>
         													<table width="100%" border="0" cellspacing="0" cellpadding="0">
         														 <tr>
                													<td>
	                													<div align="right">
	                														<img src="${pageContext.request.contextPath}/barcode?keycode=${pke.trackNumber}" alt="" width="119" height="42"/>
	                													</div>
                													</td>
             													 </tr>
             													 <tr>
             													 	<td>
             													 		<div align="right" style='width:100%'>
             													 			<span style="margin-left:10px; float:left; width:170px; font-size:12px;">${pke.order.orderNote }</span>
             													 			<span style='margin-right:5px; float:right'>${pke.trackNumber }</span>
             													 		</div>
             													 		 <div align="right"></div><div align="right"></div><div align="center"></div>
             													 	</td>
             													 </tr>
         													</table>
         											</td>
         										</tr>
         									</table>		 
		 								</td>
		 						  </tr>
						        </tbody>
    					</table>
    					<table width="100%" border="0" cellspacing="0" cellpadding="0">
    						 <tr>
       							<td width="5" style='4px'>&nbsp;</td>
     						 </tr>
     					</table>	 
    					<c:if test="${pkeidx.index+1%5==0}">
  								<div style="PAGE-BREAK-AFTER: always">&nbsp;&nbsp;</div>
    					</c:if>
			</c:forEach>
</body>
</html>