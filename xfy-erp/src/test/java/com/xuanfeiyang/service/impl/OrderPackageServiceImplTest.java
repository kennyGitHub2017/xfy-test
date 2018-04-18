package com.xuanfeiyang.service.impl;

import javax.annotation.Resource;

import org.junit.Test;

import com.xuanfeiyang.erp.dao.SellerDepositLogDao;
import com.xuanfeiyang.erp.param.SellerDepositParams;
import com.xuanfeiyang.erp.service.AgentConfigureService;
import com.xuanfeiyang.erp.service.OrderPackageService;
import com.xuanfeiyang.erp.service.PayOrderService;
import com.xuanfeiyang.test.BaseTestCase;

public class OrderPackageServiceImplTest extends BaseTestCase {
	
	@Resource
	private OrderPackageService orderPackageService;
	@Resource
	private PayOrderService payOrderService;
	@Resource
	private AgentConfigureService agentConfigureService;
	
	@Resource
	private SellerDepositLogDao sellerDepositLogDao;
	
	

	
	@Test
	public void shippedOrderRebate(){
		this.agentConfigureService.shippedOrderRebate();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/***
	 * 
	 * 
		44599317991
		44561986492
		44562085747
		44599188064
	 */
	@Test
	public void testReadPackageWeight() {
		String requestUrl = "1111";
		String trackNumber = "44562085747";
		try {
			orderPackageService.readPackageWeight(trackNumber , requestUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	private void test(){
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		sb.append("<appid>"); sb.append("</appid>");//公众账号ID -- 是
		sb.append("<mch_id>"); sb.append("</mch_id>");//商户号 -- 是
		sb.append("<device_info>");sb.append("<device_info>");;//设备号 ---------------否
		sb.append("<nonce_str>");sb.append("</nonce_str>");//随机字符串 ---是
		sb.append("<sign>");sb.append("</sign>");//签名 -- 是
		sb.append("<body>");sb.append("</body>");//商品描述 -- 是
		sb.append("<detail>");sb.append("</detail>");//商品详情 -----------否
		sb.append("<attach>");sb.append("</attach>");//附加数据 ---否
		sb.append("<out_trade_no>");sb.append("</out_trade_no>");//商户订单号 ---是
		sb.append("<fee_type>");sb.append("</fee_type>");//货币类型  -----否
		sb.append("<total_fee>");sb.append("</total_fee>");//总金额 ---是
		sb.append("<spbill_create_ip>");sb.append("</spbill_create_ip>");//终端IP ---是
		sb.append("<time_start>");sb.append("</time_start>");//交易起始时间  --- 否
		sb.append("<time_expire>");sb.append("</time_expire>");//交易结束时间  --- 否
		sb.append("<goods_tag>");sb.append("</goods_tag>");//商品标记  ---否
		sb.append("<notify_url>");sb.append("</notify_url>");//通知地址 --是
		sb.append("<trade_type>");sb.append("</trade_type>");//交易类型  ---是
		sb.append("<product_id>");sb.append("</product_id>");//商品ID   ---否
		sb.append("<limit_pay>");sb.append("</limit_pay>");//指定支付方式    ---否
		sb.append("<openid>");sb.append("</openid>");//用户标识 ---否
		sb.append("</xml>");
		
	}
	
	@Test
	public void  weixinpay(){
		payOrderService.weixinPay("1000", "KENNY", "1.1.1.1", 58);
	}
	
	
}

