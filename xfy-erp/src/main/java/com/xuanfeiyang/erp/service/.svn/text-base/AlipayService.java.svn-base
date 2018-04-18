package com.xuanfeiyang.erp.service;

import java.util.Map;

/**
 * 支付宝接口服务
 * @author Administrator
 *
 */
public interface AlipayService {
	
	/**
	 * 生成要请求给支付宝的参数数组
	 * @param para
	 * @return
	 */
	public Map<String, String> buildRequestPara(Map<String, String> para);
	
	/**
	 * 保存支付订单信息
	 * @param map
	 * @param sellerId
	 */
	public void addPayOrder (Map<String, String> map, String sellerId);
	
	/**
	 * 增加卖家保证金相关信息
	 * @param outTradeNo
	 */
	public void addDeposit(String outTradeNo);
}
