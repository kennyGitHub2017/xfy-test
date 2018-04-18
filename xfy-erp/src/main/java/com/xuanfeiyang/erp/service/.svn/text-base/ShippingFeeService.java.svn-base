package com.xuanfeiyang.erp.service;

import java.math.BigDecimal;
import java.util.List;

import com.xuanfeiyang.erp.domain.ShippingFee;

public interface ShippingFeeService {
	
	boolean insert(ShippingFee param);

	List<ShippingFee> getByShipId(Integer shipId);
	
	void delete(Integer id);
	
	ShippingFee getById(Integer id);

	void update(ShippingFee param);
	
	/***
	 * 添加DHL运费参数
	 * @param weightFrom
	 * @param weightTo
	 */
	public void addShippFee2(List<BigDecimal>weightFrom,List<BigDecimal>weightTo,
			List<BigDecimal>shippFee,Integer shippingId,String country);
	
}
