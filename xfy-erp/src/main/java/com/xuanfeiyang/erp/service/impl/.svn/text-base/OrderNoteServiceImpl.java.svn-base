package com.xuanfeiyang.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xuanfeiyang.erp.dao.OrderNoteDao;
import com.xuanfeiyang.erp.domain.OrderNote;
import com.xuanfeiyang.erp.service.OrderNoteService;
@Service("orderNoteService")
public class OrderNoteServiceImpl implements OrderNoteService {
	@Autowired
	private OrderNoteDao orderNoteDao;

	public void setOrderNoteDao(OrderNoteDao orderNoteDao) {
		this.orderNoteDao = orderNoteDao;
	}

	@Override
	public void delete(Integer id) {
		orderNoteDao.delete(id);
	}

	@Override
	public Integer insert(OrderNote record) {
		orderNoteDao.insert(record);
		return record.getId();
	}

	@Override
	public void update(OrderNote record) {
		orderNoteDao.update(record);
	}

	@Override
	public OrderNote load(Integer id) {
		return orderNoteDao.load(id);
	}

	@Override
	public List<OrderNote> queryByOrder(Integer orderId) {
		return orderNoteDao.queryByOrder(orderId);
	}

}
