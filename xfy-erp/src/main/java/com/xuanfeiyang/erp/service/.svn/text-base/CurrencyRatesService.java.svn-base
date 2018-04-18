package com.xuanfeiyang.erp.service;



import java.math.BigDecimal;
import java.util.List;

import com.xuanfeiyang.erp.domain.CurrencyRates;

public interface CurrencyRatesService {
	
	/**
	 * 获取汇率
	 * @param pageRequest
	 * @return
	 */
	public List<CurrencyRates> find();
	
	/**
	 * 根据id删除汇率信息
	 * @param id
	 * @return
	 */
	public boolean delete(Long id);
	
	/**
	 * 添加汇率信息
	 * @param currencyRates
	 * @return
	 */
	public boolean add(CurrencyRates currencyRates);
	
	/***
	 * 修改信息
	 * @param currencyRates
	 * @return
	 */
	public boolean modify(CurrencyRates currencyRates);
	
	/**
	 * 
	 * 根据id修改汇率信息
	 * @return
	 */
	public CurrencyRates findById(Long id);
	
	
	public CurrencyRates findByCurrency(String currency);
	/**
	 * 货币转换汇率
	 * @param fromCurrency  待转的币种
	 * @param toCurrency    所需的币种
	 * @return
	 */
	public BigDecimal currencyTranslateRate(String fromCurrency,String toCurrency);
	
}
