package com.xuanfeiyang.erp.service;

import java.util.List;
import java.util.Map;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.PayOrder;
import com.xuanfeiyang.erp.param.PayOrderParams;

public interface PayOrderService {
	
	/***
	 * 
	 * 微信扫码支付下单
	 * @param amount
	 * @param body
	 * @param ip
	 * @param sellerId
	 * @return
	 */
	public String weixinPay(String amount, String body, String ip, Integer sellerId);
	
	/****
	 * 
	 * 手机app微信支付下单
	 * @return
	 */
	public Map<String, String> weixinPaying(String amount, String body, String ip, Integer sellerId);
	
	
	public String getNotifyInfo(String res);
	
	/**
	 * 查询充值记录页面的分页数据
	 * @param pageRequest
	 * @param params
	 * @return
	 */
	public Page<PayOrder> findPage(PageRequest pageRequest, PayOrderParams params);
	
	/**
	 * 导出查询充值记录的数据
	 * @param params
	 * @return
	 */
	public List<PayOrder> export(PayOrderParams params);

}
