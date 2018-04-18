package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.erp.domain.OrderItem;
import com.xuanfeiyang.erp.domain.OrderItemWithShippingInfo;

public interface OrderItemDao {
	void delete(Integer id);

	void deleteByOrder(@Param("orderId") Integer orderId);

	List<OrderItem> queryNoSku();

	int insert(OrderItem record);

	void update(OrderItem record);

	void updateSkuByoldSku(@Param("id") Integer id,
			@Param("oldSku") String oldSku);

	OrderItem load(Integer id);

	List<OrderItem> queryByOrder(@Param("orderId") Integer orderId);

	List<OrderItemWithShippingInfo> findForShippingByOrderId(
			@Param("orderId") Integer orderId);

	OrderItem queryByOrderAndSku(@Param("orderId") Integer orderId,
			@Param("sku") String sku);

	void updateBySkuAndOrderId(OrderItem record);

	/**
	 * 还原订单item至审核前的数据,清空item包裹数量/锁定数量/取消数量/需采购数量/出货数量总和
	 */
	void restoreItem(@Param("orderId") Integer orderId);
	
	/**
	 * 查询oldsku为null 的记录
	 * 
	 * @return
	 */
	List<OrderItem> findByOldSkuNull();

	void batchUpdateSkuByoldSku(@Param("items") List<OrderItem> updatingItems);

	List<Integer> findCombItemIds();

	/**
	 * 单纯只更新新sku
	 * @param updatingNewSkuItems
	 */
	void batchUpdateSku(@Param("items") List<OrderItem> updatingNewSkuItems);
	
	List<OrderItem> findDetailByParam(@Param("trackNumber") String trackNumber);
}
