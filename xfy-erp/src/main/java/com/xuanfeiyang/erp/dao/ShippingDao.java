package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.erp.domain.Shipping;

/**
 * 发货方式
 * @author Administrator
 *
 */
public interface ShippingDao {
	
	/**
	 * 获取发货方式
	 * @return
	 */
	public List<Shipping>  find();
	
	/**
	 * 添加发货信息
	 * @param shipping
	 * @return
	 */
	public int  save(@Param("param")Shipping shipping);
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public Shipping getById(Integer id);

	/**
	 * 根据id修改
	 * @param shipping
	 * @return
	 */
	public int modifyById(@Param("param")Shipping shipping);
	
	/**
	 * 根据商品的参数匹配物流
	 * @return
	 */
	public List<Shipping> findByParam(@Param("param")Shipping shipping, @Param("skus")List<String> skus);
	
	/***
	 * 根据name查询
	 * @param name
	 * @return
	 */
	public Shipping getByShipName(@Param("name")String name);
	
	
	

}
