package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.GoodsSupplierPrice;

/**
 * 供应商采购报价
 * @author Adam
 *
 */
public interface GoodsSupplierPriceDao {
	int delete(Integer id);

	int insert(GoodsSupplierPrice record);

	GoodsSupplierPrice load(Integer id);

	int update(GoodsSupplierPrice record);

	Page<GoodsSupplierPrice> findPage(PageRequest pageRequest, 
			@Param("param")GoodsSupplierPrice goodsSupplierPrice);
	
	List<GoodsSupplierPrice> findBySupplySku(@Param("supplyId")Integer supplyId,@Param("sku")String sku);
	
	GoodsSupplierPrice findByPriorityRuleSku(@Param("sku")String sku,@Param("priorityRule")Integer priorityRule);
	
	void batchAddSupplierPrice();
	
	/**
	 * 根据供应商ID 更新采购周期
	 * @param buyPeriod 采购周期
	 * @param supplierId 供应商ID
	 * @return
	 */
	public Integer modifyBySupplier(@Param("buyPeriod")Integer buyPeriod, @Param("supplierId")Integer supplierId);
}