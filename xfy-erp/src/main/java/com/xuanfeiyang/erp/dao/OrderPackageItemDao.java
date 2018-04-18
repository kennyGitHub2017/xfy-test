package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.erp.domain.OrderPackageItem;

/**
 * 包裹明细
 * @author kenny
 *
 */
public interface OrderPackageItemDao {
	
	/**
	 * 根据包裹Id查询明细
	 * @param id
	 * @return
	 */
	public List<OrderPackageItem> findByPackage(String id);
	
	/**
	 * 根据系统订单ID查询明细
	 * @param orderId
	 * @return
	 */
	public List<OrderPackageItem> findByOrderId(Integer orderId);
	
	/***
	 * 添加
	 * @param orderPackageItem
	 */
	void insert(OrderPackageItem orderPackageItem);
	
	
	void update(OrderPackageItem orderPackageItem);
	
	void deleteByOrderId(Integer orderId);
	
	/***
	 * 根据 条件 查询
	 * @param packageId 包裹ID
	 * @param orderId 订单ID
	 * @param sku SKU
	 * @return
	 */
	public OrderPackageItem getPackageItemByParam(@Param("packageId")String packageId,@Param("orderId")Integer orderId, @Param("sku")String sku);

	public List<OrderPackageItem> getByTrackNo(@Param("trackNo")String trackNo);
}
