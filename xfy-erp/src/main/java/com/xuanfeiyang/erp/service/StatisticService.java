package com.xuanfeiyang.erp.service;

import com.xuanfeiyang.erp.domain.OrderStatistic;

/**
 * 统计相关的业务逻辑
 * 
 * @author Adam
 *
 */
public interface StatisticService {
	
	/**
	 * 统计SKU销量
	 */
	public void statSkuSales();
	
	/**
	 * 按状态统计订单
	 * 
	 * @param sellerId
	 * @return
	 */
	public OrderStatistic statOrderStatusCount(Integer sellerId);
	
	public void statSkuSalesAccupy();
	
	public void statSkuLatestCost();
	
}
