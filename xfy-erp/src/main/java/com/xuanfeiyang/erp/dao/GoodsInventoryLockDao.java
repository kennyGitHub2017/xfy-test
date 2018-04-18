package com.xuanfeiyang.erp.dao;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.erp.domain.GoodsInventoryLock;

public interface GoodsInventoryLockDao {
	
	/**
	 * 根据记录ID进行删除
	 * @param id
	 * @return
	 */
	int deleteById(Integer id);
	
	/**
	 * 删除销售订单的所有记录
	 * 
	 * @param orderId
	 * @return
	 */
	int deleteBySaleOrderId(Integer orderId);
	
	/**
	 * 删除销售订单指定sku锁定记录
	 * 
	 * @param orderId
	 * @param sku
	 * @return
	 */
	int  deleteBySaleOrderIdAndSku(@Param("orderId")Integer orderId,@Param("sku")String sku);

	/**
	 * 增加一个SKU库存锁定
	 * 
	 * @param record
	 * @return
	 */
	int insert(GoodsInventoryLock record);
	/**
	 * 修改sku库存锁定
	 * @param record
	 */
	void update(GoodsInventoryLock record);
	
	/**
	 * 获得订单行锁定记录
	 * @param orderId
	 * @param sku
	 * @return
	 */
	GoodsInventoryLock findByOrderIdAndSku(@Param("orderId")Integer orderId,@Param("sku")String sku);
	
	/**
	 * 根据SKU查询锁定库存总数
	 * @param goodsSku
	 * @return
	 */
	Integer getLockCountBySku(@Param("goodsSku")String goodsSku);
	
	
	void updateByOrderIdAndSku(GoodsInventoryLock record);
	
}