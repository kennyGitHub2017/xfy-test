<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <style type="text/css">
.et7
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et7
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}td
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et9
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et9
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et11
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et12
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et13
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et13
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et13
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et13
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et13
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et17
{color:#000000;
font-size:12.0pt;
font-family:新宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et18
{color:#000000;
font-size:12.0pt;
font-family:新宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et18
{color:#000000;
font-size:12.0pt;
font-family:新宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et18
{color:#000000;
font-size:12.0pt;
font-family:新宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et21
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et22
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et23
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et23
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et23
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et23
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et24
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et26
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et27
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et28
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et29
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et47
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et48
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et49
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et32
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et32
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et34
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et34
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et34
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et34
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et34
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et34
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et36
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et36
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et39
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et39
{color:#000000;
font-size:12.0pt;
font-family:宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}.et45
{color:#000000;
font-size:12.0pt;
font-family:新宋体;
font-weight:400;
font-style:normal;
text-decoration:none;
text-align:general;
vertical-align:middle;
}</style>
</head>
<body onload="javascript:window.print()">
  <c:forEach items="${poList }" var="porder" varStatus="status">
    <table height="953" style="border-collapse:collapse;width:563.25pt;" width="751">
        <colgroup>
            <col style="width:29.25pf;" width="39" />
            <col style="width:49.40pf;" width="65" />
            <col style="width:65.65pf;" width="87" />
            <col style="width:48.75pf;" width="65" />
            <col style="width:51.90pf;" width="69" />
            <col style="width:35.60pf;" width="47" />
            <col style="width:51.75pf;" width="69" />
            <col style="width:49.35pf;" width="65" />
            <col style="width:57.50pf;" width="76" />
            <col style="width:51.85pf;" width="69" />
            <col style="width:75.00pf;" width="100" />
        </colgroup>
        <tr height="37" style="height:28.00pf;">
            <td class="et7" colspan="11" height="37" style="color:#000000;font-size:18.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;mso-protection:locked visible;height:27.75pt;width:517.50pt;" width="690" x:str="">深圳市轩飞扬贸易有限公司</td>
        </tr>
        <tr height="26" style="height:20.00pf;">
            <td class="et9" colspan="11" height="26" style="color:#000000;font-size:16.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;mso-protection:locked visible;height:19.50pt;width:517.50pt;" width="690" x:str="">订<span style="mso-spacerun:yes">&nbsp;</span>货<span style="mso-spacerun:yes">&nbsp;</span>合<span style="mso-spacerun:yes">&nbsp;</span>同</td>
        </tr>
        <tr height="21" style="height:16.00pf;">
            <td class="et11" height="21" colspan="11" style="color:#000000;font-size:10.5pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:bottom;border-bottom: 2.0pt double #000000;mso-protection:locked visible;height:15.75pt;width:29.25pt;" width="39" x:str="">公司名称：深圳市轩飞扬贸易有限公司<span style="mso-spacerun:yes">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>公司地址：广东省深圳市宝安区西乡宝田工业区宝田三路69号</td>

        </tr>
        <tr height="28" style="height:21.00pf;">
            <td class="et13" height="28" style="color:#000000;font-size:11.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:general;vertical-align:middle;mso-protection:locked visible;height:21.00pt;width:29.25pt;" width="39"></td>
            <td class="et13" height="28" style="color:#000000;font-size:11.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:general;vertical-align:middle;mso-protection:locked visible;height:21.00pt;width:48.75pt;" width="65"></td>
            <td class="et13" height="28" style="color:#000000;font-size:11.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:general;vertical-align:middle;mso-protection:locked visible;height:21.00pt;width:65.25pt;" width="87"></td>
            <td class="et13" height="28" style="color:#000000;font-size:11.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:general;vertical-align:middle;mso-protection:locked visible;height:21.00pt;width:48.75pt;" width="65"></td>
            <td height="28"></td>
            <td height="28"></td>
            <td height="28"></td>
            <td class="et17" height="28" style="color:#000000;font-size:10.5pt;font-weight:400;font-style:normal;text-decoration:none;text-align:justify;vertical-align:middle;mso-protection:locked visible;height:21.00pt;width:48.75pt;" width="65"></td>
            <td class="et18" colspan="3" height="28" style="color:#000000;font-size:10.5pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;mso-protection:locked visible;height:21.00pt;width:165.75pt;" width="221" x:str="">合同编号：${porder.orderNo }</td>
        </tr>
        <tr height="24" style="height:18.00pf;">
            <td class="et18" colspan="5" height="24" style="color:#000000;font-size:10.5pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;mso-protection:locked visible;height:18.00pt;width:221.25pt;" width="295" x:str="">需方：深圳市轩飞扬贸易有限公司<span style="mso-spacerun:yes">&nbsp;</span></td>
            <td height="24"></td>
            <td height="24"></td>
            <td class="et13" height="24" style="color:#000000;font-size:11.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:general;vertical-align:middle;mso-protection:locked visible;height:18.00pt;width:48.75pt;" width="65"></td>
            <td class="et18" colspan="3" height="24" style="color:#000000;font-size:10.5pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;mso-protection:locked visible;height:18.00pt;width:165.75pt;" width="221" x:str="">签订地点：深圳</td>
        </tr>
        <tr height="24" style="height:18.00pf;">
            <td class="et18" height="24" style="color:#000000;font-size:10.5pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;mso-protection:locked visible;height:18.00pt;width:39.25pt;" width="50" x:str="">供方：</td>
            <td class="et21" height="24" style="color:#000000;font-size:11.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;white-space:normal;mso-protection:locked visible;height:18.00pt;width:48.75pt;" width="65"><span height="2" style="position:absolute;margin-left:0;margin-top:21;" width="241">
                <span style="position:absolute;margin-left:200">${porder.supplierName }</span> <img height="2" src="${pageContext.request.contextPath }/resources/assets/global/img/print/clip_image1.png" width="241" /></span></td>
            <td class="et22" height="24" style="color:#000000;font-size:11.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;mso-protection:locked visible;height:18.00pt;width:65.25pt;" width="87"></td>
            <td class="et22" height="24" style="color:#000000;font-size:11.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;mso-protection:locked visible;height:18.00pt;width:48.75pt;" width="65"></td>
            <td height="24"></td>
            <td height="24"></td>
            <td height="24"></td>
            <td class="et13" height="24" style="color:#000000;font-size:11.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:general;vertical-align:middle;mso-protection:locked visible;height:18.00pt;width:48.75pt;" width="65"></td>
            <td class="et18" colspan="3" height="24" style="color:#000000;font-size:10.5pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;mso-protection:locked visible;height:18.00pt;width:165.75pt;" width="221" x:str="">签订时间：<span style="mso-spacerun:yes"> <f:formatDate value="${porder.purchaseDate}" pattern="yyyy年MM月dd日" /> </span></td>
        </tr>
        <tr height="28" style="height:21.00pf;">
            <td class="et18" colspan="7" height="28" style="color:#000000;font-size:10.5pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;mso-protection:locked visible;height:21.00pt;width:308.25pt;" width="411" x:str="">一、产品名称、规格、数量、价格及交货期如下：</td>
            <td class="et13" height="28" style="color:#000000;font-size:11.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:general;vertical-align:middle;mso-protection:locked visible;height:21.00pt;width:48.75pt;" width="65"></td>
            <td height="28"></td>
            <td height="28"></td>
            <td height="28"></td>
        </tr>
        <tr height="21" style="height:16.00pf;border:#FF0000 solid 1px;">
            <td class="et24"  style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;white-space:normal;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:15.75pt;width:48.75pt;" width="65" x:str="">产品编码</td>
            <td class="et24"  style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;white-space:normal;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:15.75pt;width:48.75pt;" width="65" x:str="">货位号</td>
            <td class="et23"  style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:15.75pt;width:65.25pt;" width="87" x:str="">产品名称</td>
            <td class="et23"  style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:15.75pt;width:48.75pt;" width="65" x:str="">图片</td>
            <td class="et24"  style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;white-space:normal;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:15.75pt;width:51.75pt;" width="69" x:str="">描述</td>
            <td class="et23"  style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:15.75pt;width:35.25pt;" width="47" x:str="">单位</td>
            <td class="et23"  style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:15.75pt;width:29.25pt;" width="39" x:str="">重量</td>
            <td class="et23"  style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:15.75pt;width:51.75pt;" width="69" x:str="">数量</td>
            <td class="et23"  style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:15.75pt;width:48.75pt;" width="65" x:str="">单价</td>
            <td class="et23"  style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:15.75pt;width:57.00pt;" width="76" x:str="">金额</td>
            <td class="et23"  style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:15.75pt;width:51.75pt;" width="69" x:str="">交货日期</td>
        </tr>
        <c:set var="total" value="0"></c:set>
        <c:forEach items="${porder.items }" varStatus="pkidx" var="item">
        	<c:set var="total" value="${total+item.goodsCount*item.goodsCost }"></c:set>
        	<tr height="60" style="height:32.00pf;border:#FF0000 solid 1px;">
	            <td class="et26"  style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:31.50pt;width:48.75pt;" width="65">${item.goodsSku }<br>(${item.oldSku })</td>
	            <td class="et26"  style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:31.50pt;width:48.75pt;" width="65">${item.storeShelf }</td>
	            <td class="et27"  style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;white-space:normal;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:31.50pt;width:65.25pt;" width="87">${item.goodsName }</td>
	            <td class="et28" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:general;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:31.50pt;width:48.75pt;" width="65"><img alt="" width="60px" height="60px" src="${x:goodsImageThumbnailPath(item.goodsSku, 1,'S') }"></td>
	            <td class="et23" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:31.50pt;width:51.75pt;" width="69">${item.color}-${item.goodsSize}-${item.rules}-${item.model}</td>
	            <td class="et23"  style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:31.50pt;width:35.25pt;" width="47">${item.goodsUnit }</td>
	            <td 00000000000000="" class="et23" height="42" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:31.50pt;width:29.25pt;" width="39" x:num="1">${item.goodsWeight}</td>
	            <td class="et23"  style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:31.50pt;width:51.75pt;" width="69">${item.goodsCount }</td>
	            <td class="et29"  style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:31.50pt;width:48.75pt;" width="65">${item.goodsCost }</td>
	            <td class="et29"  style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:31.50pt;width:57.00pt;" width="76">${item.goodsCount*item.goodsCost }</td>
	            <td class="et47"  style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:31.50pt;width:51.75pt;" width="69"><f:formatDate value="${porder.deliveryDate }" /> </td>
        	</tr>
        </c:forEach>
        
        <tr height="28" style="height:21.00pf;border:#FF0000 solid 1px;">
            <td class="et23" colspan="2" height="28" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:21.00pt;width:58.50pt;" width="78" x:str="">小<span style="mso-spacerun:yes">&nbsp;</span>计</td>
            <td class="et23" colspan="6" height="28" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:21.00pt;width:318.00pt;" width="424"></td>
            <td 000000000000000="" class="et29" height="28" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:21.00pt;width:57.00pt;" width="76" x:num="0"></td>
            <td class="et23" height="28" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:21.00pt;width:51.75pt;" width="69">${total }<span style="mso-spacerun:yes">&nbsp;</span></td>
            <td class="et23" height="28" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:21.00pt;width:75.00pt;" width="100"></td>
        </tr>
        <tr height="28" style="height:21.00pf;border:#FF0000 solid 1px;">
            <td class="et23" colspan="2" height="28" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:21.00pt;width:58.50pt;" width="78" x:str="">运费</td>
            <td class="et23" colspan="6" height="28" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:21.00pt;width:318.00pt;" width="424"></td>
            <td 000000000000000="" class="et29" height="28" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:21.00pt;width:57.00pt;" width="76" x:num="0"></td>
            <td class="et23" height="28" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:21.00pt;width:51.75pt;" width="69"><f:formatNumber value="${porder.freight }" maxFractionDigits="2" /><span style="mso-spacerun:yes">&nbsp;</span></td>
            <td class="et23" height="28" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:21.00pt;width:75.00pt;" width="100"></td>
        </tr>
        <tr height="28" style="height:21.00pf;border:#FF0000 solid 1px;">
            <td class="et23" colspan="2" height="28" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:21.00pt;width:58.50pt;" width="78" x:str="">合计</td>
            <td class="et23" colspan="6" height="28" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:21.00pt;width:318.00pt;" width="424"></td>
            <td 000000000000000="" class="et48" height="28" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;mso-protection:locked visible;height:21.00pt;width:57.00pt;" width="76" x:num="0"></td>
            <td class="et49" height="28" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;mso-protection:locked visible;height:21.00pt;width:51.75pt;" width="69"><f:formatNumber value="${total+porder.freight }" maxFractionDigits="2" /><span style="mso-spacerun:yes">&nbsp;</span></td>
            <td class="et49" height="28" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;mso-protection:locked visible;height:21.00pt;width:75.00pt;" width="100"></td>
        </tr>
        <tr height="26" style="height:20.00pf;">
            <td class="et32" colspan="2" height="26" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;mso-protection:locked visible;height:19.50pt;width:58.50pt;" width="78" x:str="">货款结算方式</td>
            <td class="et34" colspan="9" height="26" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:19.50pt;width:475.50pt;" width="634" x:str="">${porder.payMethod }<span style="mso-spacerun:yes">&nbsp;</span></td>
        </tr>
        <tr height="26" style="height:20.00pf;">
            <td class="et34" colspan="2" height="26" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:19.50pt;width:58.50pt;" width="78" x:str="">收款帐号信息</td>
            <td class="et23" colspan="9" height="26" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:center;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:19.50pt;width:475.50pt;" width="634">${porder.supplier.bankAccountName } &nbsp;&nbsp;&nbsp;${porder.supplier.bankAccountCode }&nbsp;&nbsp;&nbsp;   ${porder.supplier.bankName }</td>
        </tr>
        <tr height="81" style="height:61.00pf;">
            <td class="et36" colspan="2" height="81" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;white-space:normal;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:60.75pt;width:58.50pt;" width="78" x:str="">二、产品质量要求</td>
            <td class="et36" colspan="9" height="81" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;white-space:normal;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:60.75pt;width:475.50pt;" width="634" x:str="">1、供方负责提供需方所要求型号、品牌的原厂生产、符合原厂技术规格，并且为近两月内生产、未被使用过的全新原装产品；保证产品质量及售后服务。<br />
                2、所有货物必须有防潮、防尘、防挤压等保护措施，如出现水浸、破损或其他损坏现象，需方有权拒收，并立即通知供方按需方要求处理，被需方拒收的物料所产生的费用均由供方承担。<br />
                3、因需方依约拒收导致收延迟而给需方造成损失，按本合同第八条承担违约责任。</td>
        </tr>
        <tr height="21" style="height:16.00pf;">
            <td class="et34" colspan="2" height="21" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:15.75pt;width:58.50pt;" width="78" x:str="">三、运输方式</td>
            <td class="et34" colspan="9" height="21" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:15.75pt;width:475.50pt;" width="634" x:str="">□快递<span style="mso-spacerun:yes">&nbsp;&nbsp;&nbsp;&nbsp;</span>□物流<span style="mso-spacerun:yes">&nbsp;&nbsp;&nbsp;&nbsp;</span>□其它   &nbsp;&nbsp;&nbsp;&nbsp; <span>物流公司/运单号:${porder.logisticsCompany }/${porder.waybillNo }</span>    </td>
        </tr>
        <tr height="21" style="height:16.00pf;">
            <td class="et34" colspan="2" height="21" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:15.75pt;width:58.50pt;" width="78" x:str="">四、交货地点</td>
            <td class="et34" colspan="9" height="21" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:15.75pt;width:475.50pt;" width="634" x:str="">广东省深圳市宝安区宝田工业区宝田三路69号1楼<span style="mso-spacerun:yes">&nbsp;</span>，轩飞扬贸易公司仓库</td>
        </tr>
        <tr height="20" style="height:15.00pf;">
            <td class="et39" colspan="2" height="20" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;white-space:normal;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;mso-protection:locked visible;height:15.00pt;width:58.50pt;" width="78" x:str="">五、运费承担方</td>
            <td class="et32" colspan="9" height="20" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;mso-protection:locked visible;height:15.00pt;width:475.50pt;" width="634">□供方<span style="mso-spacerun:yes">&nbsp;&nbsp;&nbsp;&nbsp;</span>□需方</td>
        </tr>
        <tr height="21" style="height:16.00pf;">
            <td class="et34" colspan="2" height="21" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:15.75pt;width:58.50pt;" width="78" x:str="">六、检验方式</td>
            <td class="et34" colspan="9" height="21" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:15.75pt;width:475.50pt;" width="634" x:str="">以产品资料和需方要求为准。</td>
        </tr>
        <tr height="21" style="height:16.00pf;">
            <td class="et34" height="21" colspan="2" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:15.75pt;width:29.25pt;" width="39" x:str="">七、产品保修</td>
            <td class="et34" colspan="9" height="21" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:15.75pt;width:475.50pt;" width="634" x:str="">本合同产品自出售之日起免费保修一年。</td>
        </tr>
        <tr height="34" style="height:26.00pf;">
            <td class="et34" height="34" colspan="2" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:25.50pt;width:29.25pt;" width="39" x:str="">八、违约责任</td>
            <td class="et36" colspan="9" height="34" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;white-space:normal;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:25.50pt;width:475.50pt;" width="634" x:str="">1、因供方原因无法在需方要求的时间内保质保量地交货，则需方有权向供方提出本合同金额30%的赔偿金。<br />
                2、按《深圳市轩飞扬贸易有限公司供应商供应协议》及合同法有关规定执行。</td>
        </tr>
        <tr height="24" style="height:18.00pf;">
            <td class="et34" colspan="11" height="24" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:18.00pt;width:517.50pt;" width="690" x:str="">九、本合同经供、需双方盖章签字后，立即生效。扫描件等效于正式合同，具有同等法律效应。</td>
        </tr>
        <tr height="24" style="height:18.00pf;">
            <td class="et34" colspan="11" height="24" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:18.00pt;width:517.50pt;" width="690" x:str="">十、合同未尽事宜，供需双方协商解决。</td>
        </tr>
        <tr height="24" style="height:18.00pf;">
            <td class="et34" colspan="11" height="24" style="color:#000000;font-size:10.0pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;border-left: .5pt solid #000000;border-right: .5pt solid #000000;border-top: .5pt solid #000000;border-bottom: .5pt solid #000000;mso-protection:locked visible;height:18.00pt;width:517.50pt;" width="690" x:str="">十一、补充说明：</td>
        </tr>
        <tr height="19">
            <td height="19"></td>
            <td height="19"></td>
            <td height="19"></td>
            <td height="19"></td>
            <td height="19"></td>
            <td height="19"></td>
            <td height="19"></td>
            <td height="19"></td>
            <td height="19"></td>
            <td height="19"></td>
            <td height="19"></td>
        </tr>
        <tr height="19">
            <td class="et45" colspan="5" height="19" style="color:#000000;font-size:10.5pt;font-weight:700;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;mso-protection:locked visible;height:14.25pt;width:221.25pt;" width="320" x:str="">需方：深圳市轩飞扬贸易有限公司<span style="mso-spacerun:yes"></span></td>
            <td height="19"></td>
            <td class="et45" colspan="5" height="19" style="color:#000000;font-size:10.5pt;font-weight:700;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;mso-protection:locked visible;height:14.25pt;width:261.00pt;" width="348" x:str="">供方：<span height="2" style="position:absolute;margin-left:42;margin-top:15;" width="241"> <span style="position:absolute;margin-left:200">${porder.supplierName }</span><img height="2" src="${pageContext.request.contextPath }/resources/assets/global/img/print/clip_image3.png" width="241" /> </span></td>
        </tr>
        <tr height="19">
            <td class="et18" colspan="5" height="19" style="color:#000000;font-size:10.5pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;mso-protection:locked visible;height:14.25pt;width:221.25pt;" width="295" x:str="">单位名称(章)：深圳市轩飞扬贸易有限公司<span style="mso-spacerun:yes">&nbsp;</span></td>
            <td height="19"></td>
            <td class="et18" colspan="5" height="19" style="color:#000000;font-size:10.5pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;mso-protection:locked visible;height:14.25pt;width:261.00pt;" width="348" x:str="">单位名称（章）：${porder.supplier.companyName }</td>
        </tr>
        <tr height="19">
            <td class="et18" colspan="5" height="19" style="color:#000000;font-size:10.5pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;mso-protection:locked visible;height:14.25pt;width:221.25pt;" width="295" x:str="">单位地址：深圳市宝安区宝田工业区宝田三路69号</td>
            <td height="19"></td>
            <td class="et18" colspan="5" height="19" style="color:#000000;font-size:10.5pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;mso-protection:locked visible;height:14.25pt;width:261.00pt;" width="348" x:str="">单位地址：${porder.supplier.contactAddress }</td>
        </tr>
        <tr height="22" style="height:17.00pf;">
            <td class="et18" colspan="5" height="22" style="color:#000000;font-size:10.5pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;mso-protection:locked visible;height:16.50pt;width:221.25pt;" width="295" x:str="">拟制：${porder.createdUserName}</td>
            <td height="22"></td>
            <td class="et18" colspan="5" height="22" style="color:#000000;font-size:10.5pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;mso-protection:locked visible;height:16.50pt;width:261.00pt;" width="348" x:str="">法定代表人：${porder.supplier.contactName }</td>
        </tr>
        <tr height="24" style="height:18.00pf;">
            <td class="et18" colspan="5" height="24" style="color:#000000;font-size:10.5pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;mso-protection:locked visible;height:18.00pt;width:221.25pt;" width="295" x:str="">审核：${porder.auditUserName }</td>
            <td height="24"></td>
            <td class="et18" colspan="5" height="24" style="color:#000000;font-size:10.5pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;mso-protection:locked visible;height:18.00pt;width:261.00pt;" width="348" x:str="">委托代理人：${porder.supplier.contactName }</td>
        </tr>
        <tr height="25" style="height:19.00pf;">
            <td class="et18" colspan="5" height="25" style="color:#000000;font-size:10.5pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;mso-protection:locked visible;height:18.75pt;width:221.25pt;" width="295" x:str="">批准：</td>
            <td height="25"></td>
            <td class="et18" colspan="5" height="25" style="color:#000000;font-size:10.5pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;mso-protection:locked visible;height:18.75pt;width:261.00pt;" width="348" x:str="">电话：${porder.supplier.contactTel }</td>
        </tr>
        <tr height="19">
            <td class="et18" colspan="5" height="19" style="color:#000000;font-size:10.5pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;mso-protection:locked visible;height:14.25pt;width:221.25pt;" width="295" x:str="">电话：0755-29418546</td>
            <td height="19"></td>
            <td class="et18" colspan="5" height="19" style="color:#000000;font-size:10.5pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;mso-protection:locked visible;height:14.25pt;width:261.00pt;" width="348" x:str="">传真：${porder.supplier.contactFax }</td>
        </tr>
        <tr height="19">
            <td class="et18" colspan="5" height="19" style="color:#000000;font-size:10.5pt;font-weight:400;font-style:normal;text-decoration:none;text-align:left;vertical-align:middle;mso-protection:locked visible;height:14.25pt;width:221.25pt;" width="295" x:str="">传真：0755-29418546</td>
            <td height="19"></td>
            <td height="19"></td>
            <td height="19"></td>
            <td height="19"></td>
            <td height="19"></td>
            <td height="19"></td>
        </tr>
    </table>
   <c:if test="${ ! status.last }">
   		<div style="page-break-after:always;"></div>
   </c:if>
  </c:forEach>
</body>
</html>	