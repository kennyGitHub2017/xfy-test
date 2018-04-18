package com.xuanfeiyang.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuanfeiyang.erp.dao.OrderLogDao;
import com.xuanfeiyang.erp.domain.OrderLog;
import com.xuanfeiyang.erp.service.OrderLogService;
@Service("orderLogService")
public class OrderLogServiceImpl implements OrderLogService {

	@Autowired
	private OrderLogDao orderLogDao;
	
	public void setOrderLogDao(OrderLogDao orderLogDao) {
		this.orderLogDao = orderLogDao;
	}

	@Override
	public void delete(Integer id) {
		orderLogDao.delete(id);
	}

	@Override
	public Integer insert(OrderLog record) {
		orderLogDao.insert(record);
		return record.getId();
	}

	@Override
	public void update(OrderLog record) {
		orderLogDao.update(record);
	}

	@Override
	public OrderLog load(Integer id) {
		return orderLogDao.load(id);
	}

	@Override
	public List<OrderLog> queryByOrder(Integer orderId) {
		return orderLogDao.queryByOrder(orderId);
	}

}
