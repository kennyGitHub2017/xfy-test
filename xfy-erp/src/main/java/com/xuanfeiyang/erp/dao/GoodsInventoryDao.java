package com.xuanfeiyang.erp.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.GoodsInventory;
import com.xuanfeiyang.erp.param.GoodsInventoryParam;

public interface GoodsInventoryDao {
	
	int getCount(String goodsSku, Integer storeId, Integer storeShelfId);
	
	/**
	 * 根据 sku，仓库，货架查询库存信息
	 * @param goodsSku
	 * @param storeId
	 * @param storeShelfId
	 * @return
	 */
	GoodsInventory load(@Param("goodsSku")String goodsSku,@Param("storeId") Integer storeId,@Param("storeShelfId") Integer storeShelfId);
	
	/**
	 * 新增库存信息
	 * @param goodsInventory
	 * @return
	 */
	int insert(GoodsInventory goodsInventory);

	/**
	 * 根据ID更新
	 * @param goodsInventory
	 * @return
	 */
	int updateById(GoodsInventory goodsInventory);

	/**
	 * 分页查询
	 * @param pageRequest
	 * @param goodsInventoryParam 
	 * @return
	 */
	Page<GoodsInventory> findPage(PageRequest pageRequest,
			@Param("param") GoodsInventoryParam goodsInventoryParam);
	/**
	 * 按条件查找
	 * @param goodsInventoryParam
	 * @return
	 */
	List<GoodsInventory> find(@Param("param") GoodsInventoryParam goodsInventoryParam);
	
	/**
	 * 根据SKU查询
	 * @param goodsSku
	 * @return
	 */
	GoodsInventory getBySku(@Param("goodsSku")String goodsSku);
	
	/**
	 * 根据SKU查出库存总数
	 * @param goodsSku
	 * @return
	 */
	Integer getCountBySku(@Param("goodsSku")String goodsSku);
	
	/**
	 * 根据sku查询可用库存数
	 * @param goodsSku
	 * @return
	 */
	Integer getAvailableCountBySku(@Param("goodsSku")String goodsSku);
	
	/**
	 * 将不同货位的库存合并到基础资料的货位上去
	 * 不能单独操作，需要和 deleteCombined 作为一个事务独立操作 
	 */
	void updateInventoryWitchInFewShelf();
	
	/**
	 * 删除被合并的记录
	 * 不能单独操作，需要和 updateInventoryWitchInFewShelf 作为一个事务独立操作
	 */
	void deleteCombined();
}
