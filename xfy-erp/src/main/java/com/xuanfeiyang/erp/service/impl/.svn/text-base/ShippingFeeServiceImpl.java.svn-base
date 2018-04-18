package com.xuanfeiyang.erp.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xuanfeiyang.erp.dao.ShippingFee2Dao;
import com.xuanfeiyang.erp.dao.ShippingFeeDao;
import com.xuanfeiyang.erp.domain.ShippingFee;
import com.xuanfeiyang.erp.domain.ShippingFee2;
import com.xuanfeiyang.erp.service.ShippingFeeService;
@Service
public class ShippingFeeServiceImpl implements ShippingFeeService {
	@Resource
	private ShippingFeeDao shippingFeeDao;
	@Resource
	private ShippingFee2Dao shippingFee2Dao;

	@Override
	public boolean insert(ShippingFee param) {
		String countryFlag = "";
		String countryStr = param.getCountry();
		String country = countryStr.replaceAll("，", ",");
		String[] cArray = country.split(",");
		for (String str : cArray) {
			countryFlag += str.trim()+",";
		}
		param.setCountry(countryFlag);
		shippingFeeDao.insert(param);
		return true;
	}

	@Override
	public List<ShippingFee> getByShipId(Integer shipId) {
		return shippingFeeDao.getByShipId(shipId);
	}

	@Override
	public void delete(Integer id) {
		 shippingFeeDao.delete(id);
	}

	@Override
	public ShippingFee getById(Integer id) {
		return shippingFeeDao.getById(id);
	}

	@Override
	public void update(ShippingFee param) {
		String countryFlag = "";
		String countryStr = param.getCountry();
		String country = countryStr.replaceAll("，", ",");
		String[] cArray = country.split(",");
		for (String str : cArray) {
			countryFlag += str.trim()+",";
		}
		param.setCountry(countryFlag);
		shippingFeeDao.update(param);
	}

	@Override
	public void addShippFee2(List<BigDecimal> weightFrom,
			List<BigDecimal> weightTo, List<BigDecimal> shippFee,
			Integer shippingId, String country) {
		
		for (int i = 0; i < weightFrom.size(); i++) {
			ShippingFee2 sf = new ShippingFee2();
			sf.setCountry(country); //国家
			sf.setShippingId(shippingId);//运输方式Id
			
			sf.setWeightFrom(weightFrom.get(i));
			sf.setWeightTo(weightTo.get(i));
			sf.setShippFee(shippFee.get(i));
			
			this.shippingFee2Dao.add(sf);
		}
		
		
		
		
	}

}