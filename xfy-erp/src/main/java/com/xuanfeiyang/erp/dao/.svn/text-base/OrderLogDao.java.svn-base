package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.erp.domain.OrderLog;

public interface OrderLogDao {
	void delete(Integer id);

    int insert(OrderLog record);
    
    void update(OrderLog record);

    OrderLog load(Integer id);
    
    List<OrderLog> queryByOrder(@Param("orderId") Integer orderId);
}
