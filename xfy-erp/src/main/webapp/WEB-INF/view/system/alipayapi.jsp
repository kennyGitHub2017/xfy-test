<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@page import="com.xuanfeiyang.erp.App"%>
<%@page import="com.xuanfeiyang.erp.service.impl.AlipayServiceImpl"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>支付宝即时到账交易接口</title>
	</head>
	<%!
	
	/**
     * 建立请求，以表单HTML形式构造（默认）
     * @param sParaTemp 请求参数数组
     * @param strMethod 提交方式。两个值可选：post、get
     * @param strButtonName 确认按钮显示文字
     * @return 提交表单HTML文本
     */
	private String buildRequest(Map<String, String> para, String strMethod, String strButtonName) {
		
		//待请求参数数组
		AlipayServiceImpl service = new AlipayServiceImpl();
		Map<String, String> sPara = service.buildRequestPara(para);
		List<String> keys = new ArrayList<String>(sPara.keySet());
		
		StringBuffer sbHtml = new StringBuffer();
		
		sbHtml.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"" + "https://mapi.alipay.com/gateway.do?"
				+ "_input_charset=utf-8" + "\" method=\"" + strMethod + "\">");
		
		for (int i = 0; i < keys.size(); i++) {
			String name = (String) keys.get(i);
			String value = (String) sPara.get(name);
			sbHtml.append("<input type=\"hidden\" name=\"" + name +"\" value=\"" + value + "\"/>");
		}
		//submit按钮控件请不要含有name属性
		sbHtml.append("<input type=\"submit\" value=\"" + strButtonName + "\" style=\"display:none;\"></form>");
		sbHtml.append("<script>document.forms['alipaysubmit'].submit();</script>");
		return sbHtml.toString();
	}
	%>
	<%
		////////////////////////////////////请求参数//////////////////////////////////////

		//支付类型
		String payment_type = "1";
		//必填，不能修改
		//服务器异步通知页面路径
		String notify_url = App.getConfig("alipay.notify_url");
		//需http://格式的完整路径，不能加?id=123这类自定义参数

		//页面跳转同步通知页面路径
		String return_url = App.getConfig("alipay.return_url");
		//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

		//商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no"));
		//商户网站订单系统中唯一订单号，必填

		//订单名称
		String subject = new String(request.getParameter("subject"));
		//必填

		//付款金额
		String total_fee = new String(request.getParameter("total_fee"));
		
		//必填
		
		//卖家ID
		String sellerId = new String(request.getParameter("sellerId"));
		
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_direct_pay_by_user");
        sParaTemp.put("partner", App.getConfig("alipay.partner"));
        sParaTemp.put("seller_email", App.getConfig("alipay.seller_email"));
        sParaTemp.put("_input_charset", "utf-8");
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		
		//建立请求
		String sHtmlText = buildRequest(sParaTemp,"post","下一步");
		out.println(sHtmlText);
	%>
	<body>
	</body>
</html>
