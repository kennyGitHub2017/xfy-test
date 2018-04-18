package com.xuanfeiyang.erp.service.impl;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xuanfeiyang.erp.dao.OrderDao;
import com.xuanfeiyang.erp.dao.StatSkuSalesDao;
import com.xuanfeiyang.erp.domain.OrderStatistic;
import com.xuanfeiyang.erp.service.StatisticService;

@Service
public class StatisticServiceImpl implements StatisticService {

	@Resource
	private StatSkuSalesDao statSkuSalesDao;
	
	@Resource
	private OrderDao orderDao;
	
	// sku 统计运行状态
	private static AtomicBoolean statSkuSalesRunning = new AtomicBoolean(false);

	@Override
	public void statSkuSales() {
		
		// 如果正在运行，则退出
		if (statSkuSalesRunning.get()) {
			return;
		}
		
		statSkuSalesRunning.compareAndSet(false, true);
		
		try {
			// 1, 系统sku 销量统计
			// 首先清空表
			this.statSkuSalesDao.deleteAll();
			// 重新统计销量
			this.statSkuSalesDao.statSales();
			
			// 2. 平台 item 销量统计
			this.statSkuSalesDao.deleteStatSkuSalesListing();
			this.statSkuSalesDao.statSkuSalesListing();
			
			// 3. 平台 sku 销量统计
			this.statSkuSalesDao.deleteStatSkuSalesListingSku();
			this.statSkuSalesDao.statSkuSalesListingSku();
			
		} catch (Throwable r) {
			statSkuSalesRunning.compareAndSet(true, false);
			throw r;
		}
		
		statSkuSalesRunning.compareAndSet(true, false);
	}

	@Override
	public OrderStatistic statOrderStatusCount(Integer sellerId) {
		return this.orderDao.statOrderStatusCount(sellerId);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void statSkuSalesAccupy() {
		this.statSkuSalesDao.deleteStatSkuSalesAccupy();
		this.statSkuSalesDao.statSkuSalesAccupy();
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void statSkuLatestCost() {
		this.statSkuSalesDao.deleteStatSkuLatestCost();
		this.statSkuSalesDao.statSkuLatestCost();
	}

	
	
}
