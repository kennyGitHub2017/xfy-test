package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.Country;

/**
 * 地区信息
 * @author kenny
 *
 */
public interface CountryDao {

	/**
	 * 获取国家信息
	 * @param pageRequest
	 * @return
	 */
	public List<Country> find();
	
	/**
	 *根据id删除地区信息
	 * @param id
	 * @return
	 */
	public int deleteById(Integer id);
	
	/**
	 * 添加地区信息
	 * @param countries
	 * @return
	 */
	public int insert(@Param("param")Country countries);
	
	/**
	 * 修改
	 * @param countries
	 * @return
	 */
	public int update(@Param("param")Country country);
	
	/**
	 * 根据id查询地区信息
	 * @param id
	 * @return
	 */
	public Country getCountryById(Integer id);
	
	
	/**
	 * 分页查找
	 * @param pageRequest
	 * @param keywords
	 * @return
	 */
	public Page<Country>  getPageCountry(PageRequest pageRequest, @Param("keywords") String keywords);

}
