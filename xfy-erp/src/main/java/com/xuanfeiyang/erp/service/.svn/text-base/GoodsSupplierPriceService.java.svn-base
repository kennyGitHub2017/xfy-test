package com.xuanfeiyang.erp.service;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.GoodsSupplierPrice;

/**
 * 供应商采购报价
 * @author Adam
 *
 */
public interface GoodsSupplierPriceService {
	
	/**
	 * 分页查询
	 * @param pageRequest
	 * @param goodsSupplierPrice 
	 * @return
	 */
	public Page<GoodsSupplierPrice> findPage(PageRequest pageRequest, 
			GoodsSupplierPrice goodsSupplierPrice);
	
	
	/**
	 * 新增
	 * @param goodsSupplierPrice
	 */
	public void save(GoodsSupplierPrice goodsSupplierPrice);
	
	/**
	 * 修改
	 * @param goodsSupplierPrice
	 */
	public void update(GoodsSupplierPrice goodsSupplierPrice);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Integer id);


	public GoodsSupplierPrice load(Integer id);
	
	/**
	 * 根据skup和供应商获取最新供应商商品报价
	 * @param supplyId
	 * @param sku
	 * @return
	 */
	public GoodsSupplierPrice findBySupplySku(Integer supplyId,String sku);
	/**
	 * 根据
	 * @param sku
	 * @param priorityRule  0:<!-- 供应商优先级 -->  1:<!-- 采购周期优先 --> 2:<!-- 采购价格优先 -->
	 * @return
	 */
	public  GoodsSupplierPrice findByPriorityRuleSku(String sku,Integer  priorityRule);
	
	/**
	 * 根据商品和供应商的关系为没有报价单的商品生成报价单
	 */
	public void batchAddSupplierPrice();
}
