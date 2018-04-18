package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.erp.domain.ShippingFee;

public interface ShippingFeeDao {
	
	int insert(@Param("param")ShippingFee param);
	
	List<ShippingFee> getByShipId(@Param("shipId")Integer shipId);
	
	void delete(Integer id);
	
	ShippingFee getByCountry(@Param("country")String country, @Param("shipId")Integer shipId);
	
	ShippingFee getById(Integer id);
	
	void update(@Param("param")ShippingFee param);
}
