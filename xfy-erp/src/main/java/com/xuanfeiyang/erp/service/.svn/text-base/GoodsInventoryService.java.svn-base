package com.xuanfeiyang.erp.service;

import java.math.BigDecimal;
import java.util.List;
import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.GoodsInventory;
import com.xuanfeiyang.erp.domain.GoodsInventoryCost;
import com.xuanfeiyang.erp.domain.GoodsInventoryStat;
import com.xuanfeiyang.erp.param.GoodsInventoryParam;

public interface GoodsInventoryService {

	/**
	 * 分页查询库存信息
	 * @param pageRequest
	 * @param goodsInventoryParam 
	 * @return
	 */
	public Page<GoodsInventory> findPage(PageRequest pageRequest, 
			GoodsInventoryParam goodsInventoryParam);
	
	/**
	 * 按条件查找即时库存
	 * @param goodsInventoryParam
	 * @return
	 */
	public List<GoodsInventory>  find(GoodsInventoryParam goodsInventoryParam);
	
	/**
	 * 分页查询库存信息
	 * @param pageRequest
	 * @param goodsInventoryParam 
	 * @return
	 */
	public Page<GoodsInventoryStat> findStatPage(PageRequest pageRequest, 
			GoodsInventoryParam goodsInventoryParam);
	/**
	 * 按条件查找库存状态
	 * @param goodsInventoryParam
	 * @return
	 */
	public List<GoodsInventoryStat> findStat(GoodsInventoryParam goodsInventoryParam);
	
	/**
	 * 获取指定SKU所有的库存
	 * 
	 * @param sku
	 * @return
	 */
	public int getInventory(String sku);
	
	/**
	 * 获取指定SKU某个货架上的库存数
	 * 
	 * @param sku
	 * @param storeId
	 * @param storeShelfId
	 */
	public int getInventory(String sku, Integer storeId, Integer storeShelfId);

	/**
	 * <p>增加指定SKU在 指定仓库和指定货架上的库存，更新库存为最新。
	 * <p>当对SKU做入库时可调用此方法
	 * 
	 * @param sku           sku编码
	 * @param storeId       仓库ID
	 * @param storeShelfId  货架号ID
	 * @param increaseCount        增加的数量
	 */
	public void increase(String sku, int storeId, 
			int storeShelfId, int increaseCount);
	
	/**
	 * <p>扣减指定SKU在 指定仓库和指定货架上的库存，更新库存为最新。
	 * <p>当对SKU做入库时可调用此方法
	 * 
	 * @param sku           sku编码
	 * @param storeId       仓库ID
	 * @param storeShelfId  货架号ID
	 * @param decreaseCount 扣减的数量
	 * 
	 * @return 
	 */
	public void decrease(String sku, int storeId, 
			int storeShelfId, int decreaseCount)
			throws InventoryShortageException;
	
	/**
	 * 扣减库存时更新为最新的成本信息
	 * 
	 * @param goodsSku sku
	 * @param count 扣减的数量
	 * 
	 * @return 最新的SKU成本信息
	 * @throws InventoryShortageException 
	 */
	public GoodsInventoryCost updateCostWhenDecrease(String goodsSku, int count) throws InventoryShortageException;
	
	/**
	 * 增加库存时更新为最新的成本信息
	 * 
	 * @param goodsSku 商品SKU
	 * @param count 增加的库存数
	 * @param price 本次采购价
	 * @return 最新的SKU成本信息
	 */
	public GoodsInventoryCost updateCostWhenIncrease(String goodsSku, int count, BigDecimal price);
	
	/**
	 * 获取最新的历史成本价，指历史上使用移动加权算法算出的成本价
	 * 
	 * @param goodsSku
	 * @return
	 */
	public BigDecimal getLatestGoodsCostBySku(String goodsSku);
	
	/***
	 * 根据sku统计锁定的总数量
	 * 
	 * @param sku
	 * @return
	 */
	public Integer getLockCountBySku(String sku);
	
	
	/****
	 * 根据SKU统计实际的库存总数量
	 * 
	 * @param sku
	 * @return
	 */
	public Integer getCountBySku(String sku);

	/**
	 * 将不同货位的库存合并到基础资料的货位上去
	 */
	void combineStoreShelf();
}
