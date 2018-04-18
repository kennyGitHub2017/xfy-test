package com.xuanfeiyang.erp.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.xuanfeiyang.erp.dao.OrderDao;
import com.xuanfeiyang.erp.service.OrderItemService;

@Service("orderSyncService")
public class OrderSyncService {
	@Resource
	private OrderDao orderDao;
	@Resource
	private OrderItemService orderItemService;
	
	@Transactional(rollbackFor=Exception.class)
	public void syncOrder(List<Integer> idList) throws Exception 
	{
		// 批量插入订单行
		orderDao.batchOrderItem(idList);
		// 批量插入收货人信息
		orderDao.batchAddOrderBuyerInfo(idList);
		// 批量插入订单
		orderDao.batchinsert(idList);
		// 更新刊登系统订单的同步状态字段
		orderDao.updateOrderSyncflag(idList);

		// 转换平台SKU为系统SKU
		this.orderItemService.updateAllSku();
		// 组合SKU订单行拆分多行
		this.orderItemService.splitCombineItems();
	}
}
