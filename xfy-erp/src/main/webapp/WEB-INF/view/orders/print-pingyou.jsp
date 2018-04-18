<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html>
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
	font-size: 12px;
	font-family: arial;
}

table {
	border-collapse: collapse;
	width: 100%;
}

table td, table th {
	border: 1px solid #000;
	vertical-align: middle;
	padding: 0 1px;
	white-space: nowrap;
}

table caption {
	text-align: left;
}

table th {
	font-weight: normal;
	text-align: left;
}

#page {
	width: 880px;
	margin: 0 auto;
}

.md {
	width: 430px;
	margin-bottom: 15px;
}

.p {
	position: relative;
	width: 430px;
	height: 370px;
	overflow: hidden;
}

.phd {
	padding-top: 10px;
	height: 36px;
	overflow: hidden;
	line-height: 1;
}

.t {
	position: relative;
	height: 70px;
	border: 1px solid #000;
}

.tl {
	position: absolute;
	padding-top:1px;
	top: 0;
	left: 0;
	width: 130px;
}

.tr {
	position: absolute;
	top: 10px;
	left: 132px;
	text-align: center;
}

.tr span {
	font-weight: bold;
	font-size: 14px;
}

.tl span {
	display: block;
	text-align: center;
}

.td{
	position: relative;
	top: -7px;
}

.tc {
	font-size: 16px;
	font-weight: bold;
}

.a {
	position: relative;
	height: 130px;
	border: 1px solid #000;
}

.af {
	position: absolute;
	top: 0;
	left: 0;
	width: 135px;
	height: 80px;
	border-bottom: 1px solid #000;
	padding-left: 10px;
}

.af span {
	display: block;
	padding-top: 10px;
	font-weight: bold;
}

.at {
	position: absolute;
	top: 0;
	right: 0;
	width: 282px;
	height: 130px;
	border-left: 1px solid #000;
}

.at span {
	display: block;
	padding-left:5px;
}

.att, .ata, .atp {
	font-size: 14px;
}

.ata {
	padding: 5px 0;
}

.ac {
	position: absolute;
	top: 82px;
	left: 0;
	width: 140px;
	height: 40px;
	padding-left: 10px;
}

.l {
	padding-left: 10px;
	height:20px;
	line-height: 20px;
	border: 1px solid #000;
	border-width: 0 1px;
}

.s {
	border: 1px solid #000;
	border-top-width: 0;
	font-size: 9px;
	line-height: 9px;
	height: 44px;
}

.ss {
	display:block;
	padding-top: 2px;
	font-size: 14px;
	height: 18px;
	line-height: 18px;
}

.c {
	position: absolute;
	bottom: 0;
	right: 5px;
	font-size: 14px;
	font-weight: bold;
}

.d0, .d1, .d2 {
	padding-left: 5px;
	height: 18px;
}

.d1 {
	height: 41px;
	line-height: 1;
	white-space: normal;
	word-break:break-all;
}

.logo {
	width: 120px;
	overflow:hidden;
}

.barimg {
	width: 225px;
	height: 40px;
}

.fl {
	float: left;
}

.fr {
	float: right;
}

.cb {
	clear: both;
}

.pba {
	page-break-before: always;
}
</style>
<script type="text/javascript">
	function getPingyouCode(){
		return "301610";
	}
	function getPingyouCode1(countryCode){
		var codeAry = [];
		codeAry["US"]="平7  A";
		codeAry["GB"]="平8  A";
		codeAry["RU"]="平9  A";
		codeAry["BR"]="平10 A";
		codeAry["AU"]="平11 A";
		codeAry["FR"]="平12 A";
		codeAry["AR"]="平19 A";
		codeAry["CA"]="平13 A";
		codeAry["DE"]="平15 A";
		codeAry["ES"]="平14 A";
		codeAry["HR"]="平21 A";
		codeAry["HU"]="平21 A";
		codeAry["IL"]="平16 A";
		codeAry["NL"]="平21 A";
		codeAry["NO"]="平18 A";
		codeAry["SE"]="平17 A";
		codeAry["UA"]="平20 A";
		if (codeAry[countryCode]){
			return codeAry[countryCode]; 
		}
		return "平1 B";
	}
	
	function getPingyouCode2(countryCode){
		var codeAry = [];
		codeAry["RU"]="序1";
		codeAry["US"]="序2";
		codeAry["BR"]="序3";
		codeAry["GB"]="序4";
		codeAry["SE"]="序5";
		codeAry["AU"]="序6";
		codeAry["AR"]="序7";
		codeAry["UA"]="序8";
		codeAry["JP"]="序9";
		codeAry["IL"]="序10";
		codeAry["CA"]="序11";
		codeAry["NO"]="序12";
		codeAry["ES"]="序13";
		codeAry["FR"]="序14";
		codeAry["DE"]="序15";
		codeAry["TR"]="序16";
		codeAry["IT"]="序17";
		codeAry["FI"]="序18";
		codeAry["BE"]="序19";
		codeAry["CL"]="序20";
		codeAry["HR"]="序21";
		codeAry["CZ"]="序22";
		codeAry["GR"]="序23";
		codeAry["TW"]="序24";
		codeAry["HU"]="序25";
		codeAry["PT"]="序26";
		codeAry["IE"]="序27";
		codeAry["DK"]="序28";
		codeAry["NL"]="序29";
		codeAry["BY"]="序30";
		codeAry["MX"]="序31";
		codeAry["LV"]="序32";
		codeAry["PL"]="序33";
		codeAry["SK"]="序34";
		codeAry["LT"]="序35";
		codeAry["SG"]="序36";
		codeAry["AT"]="序37";
		codeAry["MT"]="序38";
		codeAry["EE"]="序39";
		codeAry["BG"]="序40";
		if (codeAry[countryCode]){
			return codeAry[countryCode]; 
		}
		return "序41";
	}
</script>
<body  onload="javascript:window.print()">
		<div id="page">
			<c:forEach items="${printPackage}" varStatus="pkeidx"  var="pke">
				<div class="md ${pkeidx.index%2==0?'fl':'fr'}">
					<div class="p">
						<div class="t">
							<div class="tl">
								<span><img class="logo" src="${pageContext.request.contextPath }/resources/assets/global/img/print/01.jpg" /></span>
								<span class="td">Small Packet By AIR</span>
								<span class="tc">${empty pke.order? "":pke.order.buyinfo.shippingCountry}</span>
							</div>
							<div class="tr">
								<img class="barimg" src="${pageContext.request.contextPath}/barcode?keycode=${pke.trackNumber}" />
								<span>${pke.trackNumber}</span>
							</div>
						</div>
						<div class="l">协议客户:北京燕文物流有限公司(11010502740000)</div>
						<div class="a">
							<div class="af">
								FROM:北京市朝阳区万红路5号蓝涛中心 A101<br/>
								<span><script>document.write(getPingyouCode());document.write(getPingyouCode1('${pke.order.buyinfo.shippingCountry}'));
								document.write(getPingyouCode2('${pke.order.buyinfo.shippingCountry}'));</script></span>
							</div>
							<div class="ac">自编号:<br/>AZ${empty pke.order?"":pke.order.id }</div>
							<div class="at">
								<span class="att">SHIP TO:</span>
								<span class="ata">
									<strong>${empty pke.order?"":pke.order.buyinfo.shippingName }</strong><br />
									${empty pke.order? "":pke.order.buyinfo.shippingStreet1}<br>
									${empty pke.order? "":pke.order.buyinfo.shippingStreet2}<br>
									<!--cwmbran Torfaen NP447NX UNITED KINGDOM-->
									${empty pke.order? "":pke.order.buyinfo.shippingCity}
									${empty pke.order? "":pke.order.buyinfo.shippingState}
									${empty pke.order? "":pke.order.buyinfo.shippingCountryName}
								</span>
								<span class="atp">Tel/Mobile:${empty pke.order? "":pke.order.buyinfo.shippingPhone}/${empty pke.order? "":pke.order.buyinfo.shippingMobile}
								 </span>
							</div>
						</div>
						<div class="l">退件单位:北京国际邮电局集中收寄网点</div>
						<div class="dl">
							<table>
								<tr>
									<th class="d0">description of contents</th>
									<th>Kg</th>
									<th class="col3">Val(US $)</th>					
								</tr>
								<c:set var="totalWeight" value="0"></c:set>
								<c:set var="totalSbjz" value="0"></c:set>
								<c:forEach items="${pke.orderPackageItem }" var="pitem" varStatus="idx">
									<c:set var="totalWeight" value="${totalWeight+pitem.goods.weight }"></c:set>
					 	 			<c:set var="totalSbjz" value="${totalSbjz+pitem.goods.declarationCost }"></c:set>
								</c:forEach>
								<tr>
										<td class="d1">${pke.descriptionContent}</td>
										<td>${totalWeight}</td>
										<td>${totalSbjz}</td>
								</tr>
								<tr>
									<td class="d2">Total Gross Weight(Kg)</td>
									<td>${totalWeight }</td>
									<td>${totalSbjz }</td>				
								</tr>
							</table>
						</div>
						<div class="s">
							<p>I certify that the particulars given in this declaration are correct and this item does not contain any dangerous articles prohi bited by legislation or by postal or customs regulations.</p>
							<span class="ss" style="float: left;">Sender's signature</span><span style="display: block;padding-top: 2px;font-size: 14px;height: 18px;line-height: 18px;float: left;margin-left: 10px;">${pke.trackNumber}</span>
						</div>
						<div class="c">CN22</div>
					</div>
					<div class="phd">
						<span>
							<c:forEach items="${pke.orderPackageItem }" var="pitem" varStatus="idx">
								${pitem.goods.name }*${pitem.orderAmount }(${pitem.goods.storeShelfCode }) ${pitem.goods.packingMaterialId}  ,
							</c:forEach>
						</span>
						<span></span>
						<span></span>
					</div>
				</div>
    					<c:if test="${pkeidx.index%2==1}">
    								<div class="cb"></div>
    					</c:if>
    					<c:if test="${pkeidx.index%6==5}">
    							<div class="pba"></div>
    					</c:if>
			</c:forEach>
		</div>
</body>
</html>