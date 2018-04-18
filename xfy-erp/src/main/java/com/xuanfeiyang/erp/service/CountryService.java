package com.xuanfeiyang.erp.service;

import java.util.List;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.Country;

/**
 * 地区信息
 * @author kenny
 *
 */
public interface CountryService {
	
	/**
	 * 获取信息
	 * @return
	 */
	public List<Country> find();
	
	/**
	 * 
	 * 根据id删除国家信息
	 * @param id
	 * @return
	 */
	public boolean	deleteById(Integer id);
	
	/**
	 * 添加地区信息
	 * @param countries
	 * @return
	 */
	public boolean save(Country country);
	
	/**
	 * 根据id修改地区信息
	 * @param countries
	 * @return
	 */
	public boolean modify(Country country);
	
	/**
	 * 根据id查询地区信息
	 * @param id
	 * @return
	 */
	public Country findCountryById(Integer id);
	
	/**
	 * 分页查找
	 * @param pageRequest
	 * @param keywords
	 * @return
	 */
	public Page<Country>  findPageCountry(PageRequest pageRequest,  String keywords);
}
