package com.xuanfeiyang.erp.service;

import java.util.List;

import com.xuanfeiyang.erp.domain.OrderLog;

public interface OrderLogService {
	void delete(Integer id);

	Integer insert(OrderLog record);
    
    void update(OrderLog record);

    OrderLog load(Integer id);
    
    List<OrderLog> queryByOrder(Integer orderId);
}
