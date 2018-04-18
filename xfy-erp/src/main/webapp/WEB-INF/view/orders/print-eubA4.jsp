<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html>
<style type="text/css">
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
.main{
	width:792px;
	margin:40px auto;
	font-size:12px;
	font-family: arial;
	height:470px;
}
.leftbox{
	border:1px solid #000000;
	width:388px;
	height:394px;
	float:left;
}
.rightbox{
	border:1px solid #000000;
	width:388px;
	height:394px;
	float:right;
}
.leftbox .lnav1{
	border-bottom:1px solid #000000;
	width:100%;
	height:94px;
}
.leftbox .lnav1 .lf{
	width:73px;
	height:84px;
	float:left;
	margin:5px;
}
.leftbox .lnav1 .lf img{
	width:72px;
	heght:72px;
	margin-top:3px
}
.leftbox .lnav1 .lf span{
	margin:2px;
	font-size:11px;
}
.leftbox .lnav1 .cimg{
	width:160px;
	height:84px;
	float:left;
	margin:5px 0 0 15px;
	text-align:center;
}
.leftbox .lnav1 .cimg .llc1{
	width:106px;
	height:30px;
	margin-left:-20px;
}
.leftbox .lnav1 .cimg .llc3{
	background-color:#000000;
	margin: 3px 0 3px 10px;
    width: 128px;
	height:1px;
}
.leftbox .lnav1 .cimg .llc2{
	width:155px;
	height:33px;
}
.leftbox .lnav1 .rwz{
	border: 1px solid #000000;
    float: right;
    height: 36px;
    margin: 10px 17px 0 0;
    padding: 3px 0 3px 7px;
    width: 92px;
}
.leftbox .lnav1 .rwz li{
	line-height:12px;
}
.leftbox .lnav2{
	border-bottom:1px solid #000000;
	width:100%;
	height:97px;
}
.leftbox .lnav2 .l2l{
	border-right:1px solid #000000;
	width:55%;
	height:97px;
	float:left;
	position: relative;
}
.leftbox .lnav2 .l2r{
	width:44%;
	height:97px;
	float:right;
}
.leftbox .lnav3{
	border-bottom:4px solid #000000;
	width:100%;
	height:84px;
}
.leftbox .lnav3 .tobox{
	border-right: 2px solid #000000;
    height: 55px;
    padding: 30px 0 0 30px;
    width: 15%;
	font-size:20px;
	float:left;
}
.leftbox .lnav3 .l3r{
	float:right;
	width:75%;
	height:84px;
}
.leftbox .lnav4{
	width:100%;
	height:102px;
	text-align:center;
	border-bottom:4px solid #000000;
}
.rightbox .rnav1{
	float:left;
	width:100%;
	height:202px;
	border-bottom:1px solid #000000;
}
.rightbox .rnav1 .rr1l{
	width:50%;
	float:left;
}
.rightbox .rnav1 .rr1l .rr1lt{
	height:160px;
	width:100%;
	border-bottom:1px solid #000000;
	position:relative;
}
.rightbox .rnav1 .rr1l .rr1lc{
	height:20px;
	width:100%;
	border-bottom:1px solid #000000;
}
.rightbox .rnav1 .rr1l .rr1lb{
	height:20px;
	width:100%;
}
.rightbox .rnav1 .rr1r{
	width:50%;
	float:right;
}
.rightbox .rnav1 .rr1r .rr1rt{
	height:90px;
	width:100%;
	border-bottom:1px solid #000000;
	position:relative;
}
.rightbox .rnav1 .rr1r .rr1rb{
	border-left: 1px solid #000000;
    font-size: 12px;
    height: 105px;
    padding: 5px 0 0 5px;
    width: 189px;
	position: absolute;
}
.rightbox .rnav2{
	float:left;
	width:100%;
	height:152px;
	border-bottom:1px solid #000000;
}
.rr1ltimg{
	float: left;
    height: 30px;
    margin: 7px 0 0 13px;
    width: 133px;
}
.rrul2{
	width:90%;
	line-height:12px;
}
.rrul{
	font-size: 9px;
    line-height: 9px;
    margin-left: 3px;
    width: 100px;
}
.rightbox .rnav2 .borderb{
	border-bottom:1px solid #000000;
}
.rightbox .rnav2 .borderr{
	border-right:1px solid #000000;
}
.rightbox .rnav2 .borderbr{
	border-bottom:1px solid #000000;
	border-right:1px solid #000000;
}
.rightbox .rnav2 .bordertr{
	border-top:1px solid #000000;
	border-right:1px solid #000000;
}
.rightbox .rnav3{
	float:left;
	height:38px;
	width:100%;
}
.rrwz{
	border: 1px solid #000000;
    float: right;
    font-family: Arial;
    font-size: 18px;
    font-weight: bold;
    height: 29px;
    line-height: 29px;
    margin: 6px 49px 0 0;
    text-align: center;
    width: 31px;
}
</style>
<body onload="javascript:window.print()">
	<c:set var="rowcount" value="${fn:length(printPackage)}"></c:set>
	<c:forEach items="${printPackage}" varStatus="pkeidx"  var="pke">
		<div class='main' style='margin-top:${pkeidx.index+1%2==0?10:0}mm'>
			<div class = 'leftbox'>
				<div class='lnav1'>
					<div class='lf'>
						<img src='${pageContext.request.contextPath }/resources/assets/global/img/print/ff.jpg'/>
						<span>From:</span>
					</div>
					<div class='cimg'>
						<img src='${pageContext.request.contextPath }/resources/assets/global/img/print/01.jpg' class='llc1'/>
						<div class='llc3'></div>
						<img src='${pageContext.request.contextPath }/resources/assets/global/img/print/02.jpg' class='llc2'/>
						<font style="font-family:Arial, Helvetica, sans-serif"><strong>ePacket&#8482;</strong></font>
					</div>
					<div class='rwz'>
						<ul class='llrul'>
							<li>Aimail</li>
							<li>Postage Paid</li>
							<li>China Post</li>
						</ul>
					</div>
					<span style="float: right; font-family: Arial; font-size: 18px; font-weight: bold; height: 20px; margin: 10px 57px 0 0; width: 20px;">
						${fn:substring(pke.order.buyinfo.shippingPostcode,0,1)>='6'?'2':'1'}
					</span>
					<div style="clear:both;"></div>
				</div>
				<div class='lnav2'>
					<div class='l2l'> 	
						<ul style='padding-left:10px'>
							<li>${empty pke.eub? "":pke.eub.name}</li>
							<li>${empty pke.eub? "":pke.eub.street}</li>
							<li>${empty pke.eub? "":pke.eub.city}&nbsp;${empty pke.eub? "":pke.eub.province}</li>
							<li>China ${empty pke.eub? "":pke.eub.zipCode}</li>
						</ul>
						<div style="font-family:Arial, Helvetica, sans-serif; font-size:6.2px; position: absolute; bottom:0px; left:10px;"> Customs information avaliable on attached CN22.<br />
		                USPS Personnel Scan barcode below for delivery event information </div>
					</div>
					<div class='l2r'>
						<img src="${pageContext.request.contextPath}/barcode?keycode=${empty pke.order? "":pke.order.buyinfo.shippingPostcode}" width="150" height="55" style='margin:13px 9px 0 9px;' />
						<div style="font-size:12px;margin-left: 50px; margin-top: 5px;"><strong>ZIP ${empty pke.order? "":pke.order.buyinfo.shippingPostcode}</strong></div>
					</div>
					<div style="clear:both;"></div>
				</div>
				<div class='lnav3'>
					<div class='tobox'><b>TO</b></div>
					<div class='l3r'>
						<div style="font-size:14px;line-height:15px">
							${empty pke.order.buyinfo.shippingStreet1? "":pke.order.buyinfo.shippingStreet1}<br>
							<c:if test="not empty pke.order.buyinfo.shippingStreet2">
								pke.order.buyinfo.shippingStreet2<br>
							</c:if>
							${empty pke.order? "":pke.order.buyinfo.shippingCity}<br>
							${empty pke.order? "":pke.order.buyinfo.shippingState}<br>
							${empty pke.order? "":pke.order.buyinfo.shippingCountry} <br>
							${empty pke.order? "":pke.order.buyinfo.shippingPostcode}
						</div>
					</div>
					<div style="clear:both;"></div>
				</div>
				<div class='lnav4'>
					<span style=" font-family: Arial, Helvetica, sans-serif; font-size:14px;display: block; margin:8px 0 0px 0"><strong>USPS DELIVERY CONFIRMATION</strong><span></span></span>
					<img src="${pageContext.request.contextPath}/barcode?keycode=${pke.trackNumber}" width="340" height="63" />
					<div style="font-size:12px; margin-top:3px"><strong>${pke.trackNumber}</strong></div>
				</div>
				<div style="clear:both;"></div>
			</div>
			<div class = 'rightbox'>
				<div class='rnav1'>
					<div class='rr1l'>
						<div class='rr1lt'>
							 <img src='${pageContext.request.contextPath }/resources/assets/global/img/print/01.jpg' class='rr1ltimg'/>
							 <span class='rrwz'>${fn:substring(pke.order.buyinfo.shippingPostcode,0,1)>='6'?'2':'1'}</span>
							  <ul class='rrul'>
							   <li>IMPORTANT:</li>
			                   <li>The item/parcel may be </li>
			                   <li>opened officially.</li>
			                   <li>Please print in English</li>
			                  </ul>
							  <ul style='padding-left:10px' class='rrul2'>
								<li>${empty pke.eub? "":pke.eub.name}</li>
								<li>${empty pke.eub? "":pke.eub.street}</li>
								<li>${empty pke.eub? "":pke.eub.city}&nbsp;${empty pke.eub? "":pke.eub.province}</li>
								<li>China ${empty pke.eub? "":pke.eub.zipCode}</li>
							  </ul>
						</div>
						<div class='rr1lc'>Fees(US $):</div>
						<div class='rr1lb'>Certificate No.</div>
						<div style="clear:both;"></div>
					</div>
					<div class='rr1r'>
						<div class='rr1rt'>
							<img src="${pageContext.request.contextPath}/barcode?keycode=${pke.trackNumber}>" width="232" height="50" style="position:absolute; left:-40px; top:15px;" />
							<div style="bottom: 5px;font-size: 12px; left: 30px; position: absolute;"><strong>${pke.trackNumber}</strong></div>
						</div>
						<div class='rr1rb'>SHIP TO:
							${empty pke.order.buyinfo.shippingStreet1? "":pke.order.buyinfo.shippingStreet1}<br>
							<c:if test="not empty pke.order.buyinfo.shippingStreet2">
								pke.order.buyinfo.shippingStreet2<br>
							</c:if>
							${empty pke.order? "":pke.order.buyinfo.shippingCity}<br>
							${empty pke.order? "":pke.order.buyinfo.shippingState}<br>
							${empty pke.order? "":pke.order.buyinfo.shippingCountry} <br>
							${empty pke.order? "":pke.order.buyinfo.shippingPostcode}
						<div style=" font-size:12px; position:absolute; bottom:0px; left:6px; width: 184px;">PHONE: ${empty pke.order.buyinfo.shippingPhone? pke.order.buyinfo.shippingMobile:pke.order.buyinfo.shippingPhone }</div>
					</div>
				</div>
			</div>
			<div style="clear:both;"></div>
			<div class='rnav2'>
				<table cellspacing="0" width='100%'>
					<tr height='19' style='font-size:10px'>
						<td class='borderbr' width='15' align="center" style='line-height:19px;'>No</td>
						<td class='borderbr' align="center" style='line-height:19px;' width='20'>Qty</td>
						<td class='borderbr' align="center" style='line-height:19px;' width='150'>Description of Contents</td>
						<td class='borderbr' align="center" style='line-height:19px;' width='30'>Kg.</td>
						<td class='borderbr' align="center" style='line-height:19px;' width='50'>Val(us$)</td>
						<td class='borderb' align="center" style='line-height:19px;' width='55'>Goods Origin</td>
					</tr>
					<c:set var="itemrowcount" value="${fn:length(pke.orderPackageItem)}"></c:set>
					<c:set var="totalQty" value="0"></c:set>
					<c:set var="totalWeight" value="0"></c:set>
					<c:set var="totalSbjz" value="0"></c:set>
					 <c:forEach items="${pke.orderPackageItem }" var="pitem" varStatus="idx">
					 	 <tr>
					 	 	<c:set var="totalQty" value="${totalQty+pitem.orderAmount }"></c:set>
					 	 	<c:set var="totalWeight" value="${totalWeight+pitem.goods.weight }"></c:set>
					 	 	<c:set var="totalSbjz" value="${totalSbjz+pitem.goods.declarationCost }"></c:set>
			                <td align="center"  style=" border-right:#000 1px solid;">${idx.index+1 }</td>
			                <td align="right"  style=" border-right:#000 1px solid;">${pitem.orderAmount}</td>
			                <td height="19" align="center"  style=" border-right:#000 1px solid;font-size:12px;">
			                	${not empty pitem.goods? pitem.goods.declarationNameEn:""}&nbsp;${not empty pitem.goods? pitem.goods.declarationNameCn:""}&nbsp;
			                	${not empty pitem.goods? pitem.goods.goodsSku:""}
			                </td>
			                <td align="center"  style=" border-right:#000 1px solid;">${not empty pitem.goods? pitem.goods.weight:"" }</td>
			                <td align="right"  style=" border-right:#000 1px solid;">${not empty pitem.goods? pitem.goods.declarationCost:"" }</td>
			                <td align="center">China</td>
              			</tr>
					 </c:forEach>
					 <c:if test="${itemrowcount <7}">
					 		 <c:forEach begin="0" end="${5-itemrowcount}" step="1">
							 	<tr>
									<td height=19 align="center"  style=" border-right:#000 1px solid;border-bottom: 0px solid #000;">&nbsp;</td>
									<td align="right"  style=" border-right:#000 1px solid;border-bottom: 0px solid #000;">&nbsp;</td>
									<td height="100%" align="center"  style=" border-right:#000 1px solid;font-size:12px;border-bottom: 0px solid #000;">&nbsp;</td>
									<td align="center"  style=" border-right:#000 1px solid;border-bottom: 0px solid #000;">&nbsp;</td>
									<td align="right"  style=" border-right:#000 1px solid;border-bottom: 0px solid #000;">&nbsp;</td>
									<td align="center"  style=" ">&nbsp;</td>
						  		</tr>
							 </c:forEach>
					 </c:if>
				 <tr  height='19'>
					<td class='bordertr'>&nbsp;</td>
					<td class='bordertr' align="right">${totalQty }&nbsp;</td>
					<td style="border-top:1px #000 solid;font-size:10px;">Total Gross Weight (Kg.): &nbsp;</td>
					<td class='bordertr' align="center">${totalWeight }&nbsp;</td>
					<td class='bordertr' align="right"> ${totalSbjz }&nbsp;</td>
					<td  style="border-top:1px #000 solid">&nbsp; </td>
				  </tr>
				</table>
			</div>	
			<div style="clear:both;"></div>
			<div class='rnav3'>
				<div style="font-family:Arial; font-size:6px; margin:5px 5px 0 5px">
				I certify the particulars given in this customs declaration are correct. This item does not contain any dangerous article, or articles prohibited by<br />
                legislation or by postal or customs regulations. I have met all applicable export filing requirements under the Foreign Trade Regulations. 
				</div>
				<div style="float: left;font-family: Arial;font-size: 8px; margin:5px 5px;width: 140px;"><strong>Sender's Signature &amp; Date Signed:</strong></div>
				<div style="float: right;font-family: Arial;font-size: 18.5px;margin-top: 2px;text-align: left;width: 60px;">CN22</div>
				<div style="clear:both;"></div>
			</div>
			<div style="clear:both;"></div>
		</div>
		<div style="clear:both"></div>
		<div style="margin:20px 0 60px 10px; float:left">
			配货信息:${pke.phxx }
		</div>
    	</div>
    	<div style="clear:both"></div>
    	<c:if test="${pkeidx.index+1%2==0}">
			<div style="page-break-after:always;"></div> 
 		</c:if>		
	</c:forEach>
</body>
</html>