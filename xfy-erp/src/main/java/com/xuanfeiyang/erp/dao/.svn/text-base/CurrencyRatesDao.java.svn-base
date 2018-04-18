package com.xuanfeiyang.erp.dao;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.erp.domain.CurrencyRates;

/**
 * 汇率信息
 * @author kenny
 *
 */
public interface CurrencyRatesDao {
	
	/**
	 * 获取汇率
	 * @param pageRequest
	 * @return
	 */
	public List<CurrencyRates> find();

	/**
	 * 根基ID删除汇率信息
	 * @param id
	 * @return
	 */
	public int delete(Long id);
	
	/**
	 * 添加汇率信息
	 * @param CurrencyRates
	 * @return
	 */
	public int save(@Param("param")CurrencyRates currencyRates);
	
	/**
	 * 修改汇率信息
	 * @param currencyRates
	 * @return
	 */
	public int update(@Param("param")CurrencyRates currencyRates);
	
	/**
	 * 
	 * 根据id查询汇率信息
	 * @param id
	 * @return
	 */
	public CurrencyRates findById(Long id);
	
	/***
	 * 
	 * @param currency
	 * @return
	 */
	public CurrencyRates findByCurrency(String currency);
	
}
