package com.xuanfeiyang.erp.service.impl;



import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xuanfeiyang.erp.dao.CurrencyRatesDao;
import com.xuanfeiyang.erp.domain.CurrencyRates;
import com.xuanfeiyang.erp.service.CurrencyRatesService;

@Service
public class CurrencyRatesServiceImpl implements CurrencyRatesService {
	@Resource
	private CurrencyRatesDao currencyRatesDao;
	
	@Override
	public boolean delete(Long id) {
		int result = currencyRatesDao.delete(id);
		return result > 0;
	}

	@Override
	public boolean add(CurrencyRates currencyRates) {
		int result = currencyRatesDao.save(currencyRates);
		return result > 0;
	}

	@Override
	public boolean modify(CurrencyRates currencyRates) {
		int result = currencyRatesDao.update(currencyRates);
		return result > 0;
		
	}

	@Override
	public List<CurrencyRates> find() {
		return currencyRatesDao.find();
		 
	}

	@Override
	public CurrencyRates findById(Long id) {
		return currencyRatesDao.findById(id);
	}

	@Override
	public CurrencyRates findByCurrency(String currency) {
		return currencyRatesDao.findByCurrency(currency);
	}

	@Override
	public BigDecimal currencyTranslateRate(String fromCurrency, String toCurrency) {
		CurrencyRates _fromCurrency = findByCurrency(fromCurrency);
		CurrencyRates _toCurrency = findByCurrency(toCurrency);
		if (_fromCurrency!=null && _toCurrency!=null){
			return _fromCurrency.getExchangeRate().divide(_toCurrency.getExchangeRate(),4);
		}
		return BigDecimal.valueOf(0);
	}

}
