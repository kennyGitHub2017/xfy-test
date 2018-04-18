package com.xuanfeiyang.erp.service;

import java.util.List;

import com.xuanfeiyang.erp.domain.OrderItem;

public interface OrderItemService {
	
	public void updateSku(Integer id, String sku);
	
	/**
	 * 自动更新 更新平台SKU到 老系统SKU 与 系统SKU 
	 */
	public void updateAllSku();
	
	/**
	 * 拆分组合商品
	 */
	public void splitCombineItems();
	
	/**
	 * 拆分组合商品,单商品行
	 * 
	 * @param id
	 */
	public void splitCombineItem(Integer id);
	
	List<OrderItem> findDetailByParam(String trackNumber);
	public String getOldSkuFromPlatformSku(String platformSku);
}
