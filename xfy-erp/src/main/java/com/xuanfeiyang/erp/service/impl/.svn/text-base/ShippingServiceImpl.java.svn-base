package com.xuanfeiyang.erp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xuanfeiyang.erp.dao.ShippingAddressConfigDao;
import com.xuanfeiyang.erp.dao.ShippingDao;
import com.xuanfeiyang.erp.domain.Shipping;
import com.xuanfeiyang.erp.domain.ShippingAddressConfig;
import com.xuanfeiyang.erp.service.ShippingService;

@Service
public class ShippingServiceImpl implements ShippingService{
	@Resource
	private ShippingDao shippingdao;
	
	@Resource 
	private ShippingAddressConfigDao shippingAddressConfigDao;

	@Override
	public List<Shipping> find() {
		return shippingdao.find();
	}

	@Override
	public boolean add(Shipping shipping) {
		int result = shippingdao.save(shipping);
		if(result > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean delete(Integer id) {
		int result = shippingdao.delete(id);
		if(result > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Shipping findById(Integer id) {
		return  shippingdao.getById(id);
	}

	@Override
	public boolean modifyById(Shipping shipping) {
		int result = shippingdao.modifyById(shipping);
		if(result > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public ShippingAddressConfig loadAddressConfig(Integer shippingId) {
		return shippingId == null ? null : this.shippingAddressConfigDao.load(shippingId);
	}

	@Override
	public void saveAddressConfig(ShippingAddressConfig address) {
		if (address == null || address.getShippingId() == null) {
			return;
		}
		
		ShippingAddressConfig tmp = this.loadAddressConfig(address.getShippingId());
		
		if (tmp == null) {
			this.shippingAddressConfigDao.insert(address);
		} else {
			this.shippingAddressConfigDao.update(address);
		}
	}
	
}
