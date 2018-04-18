<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html>
<style media=print>
.Noprint{display:none;}
.PageNext{page-break-after: always;}
</style>
<style>
html, body, div, span, applet, object, iframe,h1, h2, h3, h4, h5, h6, p, blockquote, pre,a, abbr, acronym, address, big, cite, code,del, dfn, em, font, img, ins, kbd, q, s, samp,small, strike, strong, sub, sup, tt, var,b, u, i, center,dl, dt, dd, ol, ul, li,fieldset, form, label, legend,table, caption, tbody, tfoot, thead, tr, th, td,p {
 margin: 0;
 padding: 0;
 border: 0;
 outline: 0;
 font-size: 100%;
 vertical-align: baseline;
 background: transparent;
 list-style:none;
}
</style>
<body  onload="javascript:window.print()" style="padding:0;margin:0">
		<div style="width:210mm; font-family: arial;">
			<c:set var="rowcount" value="${fn:length(printPackage)}"></c:set>
			<c:forEach items="${printPackage}" varStatus="pkeidx"  var="pke">
				<div style="width:102mm;height:146mm;float:left;border:1px solid #000000;margin:3px;">
						<div style="width:100%;border-bottom:0px solid #000000; height:20mm; margin-left:20px">
         					<div style="float:left;width:20%;height:5mm;line-height:4mm;border-right:0px solid #000000;text-align:  left; font-size:12px; margin-top:20px">Order No.<br>${pke.order.id }<BR><BR><span style="font-size:22px; font-weight:bold">TO:</span></div>
							<div style="float:left; text-align:center; color:red; width:59%;height:12mm;line-height:15mm;">	<img src='${pageContext.request.contextPath }/resources/assets/global/img/print/a.jpg' width="280" height="95" style='margin:1mm 1mm 0.5mm 1mm;'></div>
            				<div style='clear:both;'></div>
            			</div>
            			<div style='width:100%; height:282px; position:relative'>
            				<div style='width:70%;float:left;margin-left:18px; margin-top:3px;  overflow:hidden;color:red; font-size:14px'>
								<ul style="margin:20px;">
									<li style="width:100%; line-height:6.5mm">${empty pke.order? "":pke.order.buyinfo.shippingName}</li>
									<li style="width:100%; line-height:6.5mm">${empty pke.order? "":pke.order.buyinfo.shippingStreet1}</span></li>
									<c:if test="${not empty pke.order and not empty pke.order.buyinfo.shippingStreet2}">
										<li style="width:100%; line-height:6.5mm">${pke.order.buyinfo.shippingStreet2}</span></li>	
									</c:if>
									<li style="width:100%; line-height:6.5mm">${empty pke.order? "":pke.order.buyinfo.shippingCity} &nbsp;${empty pke.order? "":pke.order.buyinfo.shippingState},${empty pke.order? "":pke.order.buyinfo.shippingPostcode}</li>
									<li style="width:100%; line-height:6.5mm">${empty pke.order? "":pke.order.buyinfo.shippingCountry} &nbsp;</li>
									<c:if test="not empty pke.order and (not empty pke.order.buyinfo.shippingPhone or not empty pke.order.buyinfo.shippingMobile)">
										<li style="width:100%; line-height:6.5mm"><strong>TEL/Mobile.${pke.order.buyinfo.shippingPhone }/${pke.order.buyinfo.shippingMobile }</strong></li>
									</c:if>
								</ul>
							</div>
							<div style='width:98%; height:105px; position:absolute; bottom:0px; left:0px;text-align:center;font-size:16px; border-bottom:0px solid #000000; border-top:0px solid #000000;border-right:0px solid #000000;'>
								<img src="${pageContext.request.contextPath}/barcode?keycode=${pke.trackNumber}"  width="360" height="100" style='margin:20px 4px 5px 4px;' />
								<div style="font-size:16px; color:red"><strong>${pke.trackNumber }</strong></div>
							</div>
							<div style='clear:both;'></div>
            			</div>
            			 <br /><br />
            			  <div style="width:100%;border-bottom:0px solid #000000; height:20mm; font-size:13px; margin-left:21px">
							    From：Shenzhen X-feiyang Trading Company<br />
								8 Floor baoyu building,52 district,Baoan,Shenzhen Guangdong<br />
							    Content: ${pke.blsContent }<br />
							    total price: ${pke.order.amount } ${pke.order.currency }<br />
							    <c:forEach items="${pke.orderPackageItem }" var="pitem" varStatus="idx">
							    		<c:if test="${idx.index!=0 && idx.index%2==0}">
	       									 	</tr>
	       									 	<tr id='newrow'>
	       								</c:if>
							    	 ${pitem.sku }(${pitem.goodsName })*${pitem.orderAmount} <input type="checkbox" /><br>
								    	<c:if test="${idx.last }">
											<c:if test="${idx.index!=0 && idx.index+1%2==0 }">
												</tr>
											</c:if>
											<c:if test="${idx.index!=0 && idx.index+1%2!=0 }">
												<td style="width:50%;height:18px;">&nbsp;</td></tr>	
											</c:if>
	  									</c:if>
							    </c:forEach>  
						 </div>
    			</div>
    			<c:if test="${pkeidx.index+1%2==0}">
    					<div style='clear:both'></div>
    			</c:if>
    			<c:if test="${pkeidx.index+1%4==0}">
  						<div style="page-break-after:always;"></div> 
    			</c:if>
			</c:forEach>
		</div>
</body>
</html>