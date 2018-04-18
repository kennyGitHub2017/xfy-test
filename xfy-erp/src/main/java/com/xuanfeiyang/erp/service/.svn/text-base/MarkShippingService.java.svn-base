package com.xuanfeiyang.erp.service;

import java.util.List;

import com.xuanfeiyang.erp.domain.OrderMarkShipping;
import com.xuanfeiyang.erp.domain.OrderMarkShippingResult;

/**
 * 标记发货业务逻辑
 * 
 * @author Adam
 *
 */
public interface MarkShippingService {
	
	/**
	 * 订单标记发货
	 * 
	 * @param orderIds 待标发的订单ID列表
	 */
	public List<OrderMarkShippingResult> mark(List<Integer> orderIds);
	
	/**
	 * 订单标发
	 * @param orders 待标发的订单列表
	 * @return 
	 */
	public List<OrderMarkShippingResult> markOrders(List<OrderMarkShipping> orders);
	
	/**
	 * 根据包裹ID标发订单
	 * 
	 * @param packageIds
	 * @return
	 */
	public List<OrderMarkShippingResult> markPackages(List<String> packageIds);
}
