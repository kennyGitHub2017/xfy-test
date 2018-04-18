package com.xuanfeiyang.erp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.dao.CountryDao;
import com.xuanfeiyang.erp.domain.Country;
import com.xuanfeiyang.erp.service.CountryService;
@Service
public class CountryServiceImpl implements CountryService {
	@Resource
	private CountryDao countryDao;

	@Override
	public List<Country> find() {
		
		return countryDao.find();
	}

	@Override
	public boolean deleteById(Integer id) {
		int result = countryDao.deleteById(id);
		return result > 0;
	}

	@Override
	public boolean save(Country country) {
		int result = countryDao.insert(country);
		return result > 0;
	}

	@Override
	public boolean modify(Country country) {
		int result = countryDao.update(country);
		return result > 0;
	}

	@Override
	public Country findCountryById(Integer id) {
		return countryDao.getCountryById(id);
	}

	@Override
	public Page<Country> findPageCountry(PageRequest pageRequest,
			String keywords) {
		return countryDao.getPageCountry(pageRequest, keywords);
	}
	


}
