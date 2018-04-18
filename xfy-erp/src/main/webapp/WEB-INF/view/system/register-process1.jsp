<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tags.xuanfeiyang.com/tags" prefix="x" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath }/resources/style/master.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body style="padding:0;">

<div id="div1" class="center_div">
	<section class="popup">
	   <div><img src="${pageContext.request.contextPath }/resources/style/img/pop01.jpg" alt="账户审核流程图" /></div>
	   <div class="pop_con">尊敬的用户，您还未绑定平台账号，请先绑定<br />
	   <a href="${x:getConfig('kandeng.base.url')}/ebay/Profile/AccountManage.aspx" target="_blank">[eBay账号]</a>&nbsp;&nbsp;
	   <a href="${x:getConfig('kandeng.base.url')}/Amazon/Amazon_account.aspx" target="_blank">[Amazon账号]</a>&nbsp;&nbsp;
	   <a href="${x:getConfig('kandeng.base.url')}/smt/SMTAccountManage.aspx" target="_blank">[速卖通账号]</a>&nbsp;&nbsp;
	   <a href="${x:getConfig('kandeng.base.url')}/wish/Account_list.aspx" target="_blank">[Wish账号]</a>
	   <br />任意一个账号，然后提交审核资料就可以体验所有功能<br />您还可以在<a href="http://sale.chinasalestore.com:8083/ui/hotProduct.aspx" target="_parent">产品查询</a>中查看到部分热销产品。</div>
	   <!-- <button class="btn" onclick="javascript:window.close();">知道了</button> -->
	</section>
</div>
</body>
</html>