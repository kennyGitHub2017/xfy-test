package com.xuanfeiyang.erp.dao;
import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.ShippingFee2;

public interface ShippingFee2Dao {
	
	public Page<ShippingFee2> getPage(PageRequest pageRequest, ShippingFee2 shippingFee2);
	
	public void add(ShippingFee2 param);
	
	public ShippingFee2 getFeeByWeight(@Param("shippId")Integer shippId, @Param("orderWeight")BigDecimal orderWeight,
			@Param("country")String country);
	
	public Integer deleteById(Integer id);
	
	public ShippingFee2 getById(@Param("id")Integer id);
	
	public void updateById(@Param("param")ShippingFee2 param);
	
}
