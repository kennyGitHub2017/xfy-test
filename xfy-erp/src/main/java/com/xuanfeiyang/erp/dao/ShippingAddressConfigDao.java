package com.xuanfeiyang.erp.dao;

import com.xuanfeiyang.erp.domain.ShippingAddressConfig;

public interface ShippingAddressConfigDao {

	int insert(ShippingAddressConfig address);

	ShippingAddressConfig load(Integer shippingId);

	int update(ShippingAddressConfig address);

	ShippingAddressConfig loadByShippingName(String shippingName);
}