<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html>
<style>
*{margin:0;padding:0;}
html {color: #000;background: #FFF;}
body{font-family: 'Arial Unicode MS',"宋体", "Helvetica Neue", Helvetica, Arial, sans-serif;font-size:9pt;color:#000;}
.doc{width:970px;margin:10px auto;}
li{list-style:none;}
.l{float:left;}
.item{width:100mm;margin:0 auto;}
.main{width:97.3541667mm;height:97.3541667mm;border:1px dashed #000;position:relative;padding:5px;margin-bottom:5px;margin-right:10px;}
.info{width:97.3541667mm;height:97.3541667mm;border:1px dashed #666;padding:5px;margin-bottom:5px;margin-right:10px;position:relative;}
.main img.logo{width:130px;}
.to{position:absolute;top:60px;left:8px;font-size:13px;}
.code,.text-info{position:absolute;bottom:20px;width:100%;}
.sp-code{letter-spacing:5px;font-size:13px;}
.text-info{position:absolute;bottom:5px;left:0;font-size:12px;width:100%;text-align:center;}
.sf-no{position: absolute;bottom: 99px;left: 12px;}
.labelZxing .sf-no{font-size:11px;position: static;}
.c-code{position:absolute;right:40px;top:110px;font-size:30px;}
.info table{border-collapse:collapse;width:100%;margin-top:-1px;line-height:17px;}
.info table td,.info table th{border:1px solid #000;padding:0.6px 1px;text-align:left;font-size:11px;line-height:16px;}
.info{line-height:14px;}
.tips{margin:10px 0;border: 1px solid #fae2ba;background: #ffffe0;padding: 7px 10px 7px 30px;color:#666; line-height:22px;}
.tips h2{font-size:12px;color:#666;}
.goodsTab { height:28px; border-left:1px solid #ccc; background: url(../images/bg_goodsTab01.gif) bottom repeat-x;position:relative;}
.goodsTab ul {}
.goodsTab ul li { background:#fff; border-top:1px solid #ccc; border-right:1px solid #ccc; height:25px; padding:0px 15px; float:left; line-height:25px; }
.goodsTab ul li.chooseTag { height:26px; background:#429ae1; float:left; padding:0px 15px; border:none; } 
.goodsTab li.chooseTag a { color:#fff;}
.goodsTab a { color:#666;text-decoration:none;}
.goodsTab a:hover { color:#687ba3;}
.ph{border-collapse:collapse;}
.ph th{font-size:12px;}
.ph th,.ph td{border:1px solid #000;text-align:center;padding:3px;}
.ph td{font-size:12px;word-wrap: break-word;word-break: break-all;}
.print-date{position: absolute;bottom: 80px;left: 12px;}
.c-tel{position: absolute;top: 300px;left: 11px;font-size: 12px;}
.addr-list{width:43mm;height:46mm;position:absolute;right:10px;top:171px;font-size:12px;line-height:16px;}
.addr-list p{white-space:pre-wrap;word-wrap:break-word;word-break:break-word;-webkit-hyphens: auto;-moz-hyphens: auto;hyphens: auto;}
.cn22{font-size:16px;position:absolute;right:10px;top:10px;}
.logo{position: absolute;top: 226px;left: 10px;}
.sender{height:7mm;text-align:center;font-size:10px;}
.text{position:absolute;bottom:5px;left:5px;width:100%;text-align:left;}
.tel-mobile{margin:0 20px;}
@media screen {
    .goodsTab,.tips {
		display:block;
    }
}

@media print {
	body{font-size: 12pt!important; font-family:'Arial Unicode MS',"宋体", "Helvetica Neue", Helvetica, Arial, sans-serif;}
	body .sender{font-size: 11px;}
	body .text{font-size:12px;}
    .goodsTab,.tips {
		display:none;
    }
	.A4{margin-top:5px;}
	.doc{width:auto;margin:0;}
	.label{margin-bottom:2px;margin-right:0;}
	.labelZxing .main{margin-bottom:0;}
	.labelZxing .tel-mobile{font-size:12px;}
	@media print and (-webkit-min-device-pixel-ratio:0)
	{
		.labelZxing .label{margin-bottom:0;width:97mm;height:98.3mm;}
	}
	.labelZxing .sf-no{font-size:11px;position: static;}
}

	 .info table td table td{border:none;}
	 .hide{display:none;}
</style>
<body  onload="javascript:window.print()">
		<div class="doc">
			<div id="printLabel">
			<c:set var="rowcount" value="${fn:length(printPackage)}"></c:set>
			<c:forEach items="${printPackage}" varStatus="pkeidx"  var="pke">
						<div class="item labelItem" style="width: 812px;">
      						<div class="main printMark l A4">
      							<table style="width:100%">
									<tbody><tr>
										<td style="width:50mm;height:15mm"><img src="${pageContext.request.contextPath }/resources/assets/global/img/print/logo-code.png" style="width:50mm;:15mm"></td>
										<td style="width:50mm;height:15mm"><img src="${pageContext.request.contextPath }/resources/assets/global/img/print/logo1.png" style="width:46mm;height:15mm"></td>
										</tr>
									</tbody>
								</table>
								<table style="width:100%">
									<tbody><tr>
										<td class="sender">Sender: H-11940SFT, Postbus 7040, 3109AA Schiedam The Netherlands</td>
									</tr>
									</tbody>
								</table>
								<table>
										<tbody><tr>
											<td width="91" style="text-align:center;"><span style="display:$disp"><img src="${pageContext.request.contextPath }/resources/assets/global/img/print/return.gif"></span></td>
											<td height="60" width="272.125984252" style="text-align:center;text-indent:15px;">
												<strong>R</strong>        Registered/recommandé
												 <img src="${pageContext.request.contextPath}/barcode?keycode=${pke.trackNumber}" width="302" height="35px"><span style="font-weight: bold;  font-size: 16px;letter-spacing: 8px;line-height: 10%;text-align: text-top;">${pke.trackNumber}</span>
											</td>
										</tr>
										</tbody>
								</table>
								<div style="width:50mm;height:20mm;position:absolute;top:182px;left:5px;"></div>
								<div class="c-tel">
									<span>Tel: ${empty pke.order.buyinfo.shippingPhone?pke.order.buyinfo.shippingMobile:pke.order.buyinfo.shippingPhone }</span>
								</div>
								<div class="print-date">
									<fmt:formatDate value="<%= new java.util.Date() %>" pattern="yyyy-MM-dd HH:mm:ss"/>
								</div>
								<strong style="margin-right:20px;font-size:28px;position:absolute;top:224px;left:135px;">GB</strong>
								<div class="logo"><img src="${pageContext.request.contextPath }/resources/assets/global/img/print/logo-l.png"></div>
								<div class="addr-list">
									<strong>Deliver To:</strong>
										<p>${empty pke.order? "":pke.order.buyinfo.shippingName}</p>
										<p>${empty pke.order? "":pke.order.buyinfo.shippingStreet1}</p>
										<p>${empty pke.order?"":pke.order.buyinfo.shippingStreet2}</p>
										<p>${empty pke.order? "":pke.order.buyinfo.shippingCity}</p>
										<p>${empty pke.order? "":pke.order.buyinfo.shippingState}</p>
										<p>${empty pke.order? "":pke.order.buyinfo.shippingCountry}</p>
								</div>
								<p class="sf-no"></p>
								<p class="text">【5510014598】Ref No:${pke.order.id}<fmt:formatDate value="<%= new java.util.Date() %>" pattern="yyyy-MM-dd HH:mm:ss"/>
								 <br>&nbsp;&nbsp;<span class="jianban">
								 	<c:forEach items="${pke.orderPackageItem }" var="pitem" varStatus="idx">
								 		${pitem.sku }*${pitem.orderAmount }&nbsp;
								 	</c:forEach>
								 </span></p>
      						</div>
      						<div class="info printMark l A4">
      							<table>
      								<tbody>
      									<tr>
      										<td>
												<strong>
												CUSTOMS <br>DECLARATION </strong> 
											</td>
											<td>
												<div style="position:relative;" class="open">
													<span>May be opened <br>officially</span> <strong class="cn22">CN22&nbsp;&nbsp;</strong><br>
												</div>
											</td>
      									</tr>
      									<tr>
      										<td>
												Designated operator
											</td>
											<td><p><strong>important!</strong></p><p>See instructions on the back</p></td>
      									</tr>
      								</tbody>
      							</table>
      							<table>
      								<tbody>
      									<tr>
											<td width="28" style="text-align:center;"> &nbsp;&nbsp;</td>
											<td width="125">Gift</td>
											<td width="14">&nbsp;&nbsp;</td>
											<td>Commercial Sample</td>
										</tr>
										<tr>
											<td>&nbsp;&nbsp; </td>
											<td>Documents      </td>
											<td> √</td>
											<td>Other&nbsp;&nbsp;&nbsp;&nbsp; Tick one more boxes</td>
										</tr>
      								</tbody>
      							</table>
      							<table>
      								<thead>
										<tr>
											<th width="198">Quantity and detailed description of contents </th>
											<th>Weight(in kg)</th>
											<th>Value(USD)</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td colspan="3">
												<div style="height:103px;overflow:hidden;">
													<table>
														<tbody>
															<c:set  var="totalWeight" value="0" />
															<c:set  var="totalSbjz" value="0" />
															<c:forEach items="${pke.orderPackageItem }" var="pitem" varStatus="idx">
																<c:set var="totalWeight" value="${totalWeight+pitem.goods.weight }"  />
																<c:set var="totalSbjz" value="${totalSbjz+pitem.goods.declarationCost }"  />
																<tr>
																	<td>${empty pitem.goods?"":pitem.goods.enName}</td>
																	<td>${empty pitem.goods?"":pitem.goods.weight}</td>
																	<td>${empty pitem.goods?"":pitem.goods.declarationCost}</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</td>
										</tr>
									</tbody>
									<tfoot>
										<tr>
											<td rowspan="2" style="border-bottom:none;width:160px;">For commerical items only  If known,HS tariff number and country of origin of goods</td>
											<td>Total Weight(in kg)</td>
											<td>Total Value(USD)</td>
										</tr>
										<tr>
										  <td><strong>${totalWeight }</strong></td>
									      <td><strong>${totalSbjz }</strong></td>
									  	</tr>
									</tfoot>
      							</table>
      							<table>
      								<tbody>
      									<tr>
											<td>I, the undersigned, whose name and address are given on the item, certify that the particulars given in this declaration are correct and that this item does not contain any dangerous article or articles prohibited by legislation or by postal or customs regulations
												Date and sender's signature
											</td>
										</tr>
									</tbody>
      							</table>
      						</div>
    					</div>
			</c:forEach>
			</div>
		</div>
</body>
</html>