package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.erp.domain.OrderShippingFee;

public interface OrderShippingFeeDao {
	
	void insert(@Param("param")OrderShippingFee param);
	
	List<OrderShippingFee> getByOrderId(@Param("orderId")Integer orderId);
	
	OrderShippingFee getById(@Param("id")Integer id);
	
	void deleteByOrderId(@Param("orderId") Integer orderId);
}
