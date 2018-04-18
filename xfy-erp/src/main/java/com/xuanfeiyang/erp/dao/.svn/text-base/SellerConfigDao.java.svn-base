package com.xuanfeiyang.erp.dao;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.erp.domain.SellerConfig;

/**
 * 卖家相关设置
 * @author Administrator
 *
 */
public interface SellerConfigDao {
	
	/**
	 * 添加卖家发货设置
	 * @param sellerConfig
	 * @return
	 */
	public int saveShip(@Param("param")SellerConfig sellerConfig);
	
	/**
	 * 根据卖家id 获得卖家相关设置信息
	 * @param id
	 * @return
	 */
	public SellerConfig getById(Long id);
	
	/**
	 * 根据卖家ID修改卖家发货的设置
	 * @param sellerConfig
	 * @return
	 */
	public int updateShip(@Param("param")SellerConfig sellerConfig);

}
