package com.xuanfeiyang.erp.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xuanfeiyang.erp.dao.SellerConfigDao;
import com.xuanfeiyang.erp.domain.SellerConfig;
import com.xuanfeiyang.erp.service.SellerConfigService;
@Service
public class SellerConfigServiceImpl implements SellerConfigService{
	@Resource
	private SellerConfigDao sellerConfigDao;

	@Override
	public boolean saveShip(SellerConfig sellerConfig) {
		int result = sellerConfigDao.saveShip(sellerConfig);
		if(result > 0){
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public SellerConfig getById(Long id) {
		return sellerConfigDao.getById(id);
	}

	@Override
	public boolean updateShip(SellerConfig sellerConfig) {
		int result = sellerConfigDao.updateShip(sellerConfig);
		if(result > 0){
			return true;
		}else{
			return false;
		}
	}
	
}
