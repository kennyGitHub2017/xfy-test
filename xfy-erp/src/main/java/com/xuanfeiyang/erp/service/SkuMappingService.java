package com.xuanfeiyang.erp.service;

import java.util.List;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.SkuMapping;

public interface SkuMappingService {
	
	public void insert(SkuMapping skuMapping);
	
	public void update(SkuMapping skuMapping);
	
	public void delete(String platformSku);
	
	public SkuMapping load(String platformSku);
	
	public List<SkuMapping> find();
	
	/**
	 * 分页查询
	 * @param pageRequest
	 * @param goodsSupplierPrice 
	 * @return
	 */
	public Page<SkuMapping> findPage(PageRequest pageRequest, 
			SkuMapping skuMapping); 
	
	public void saveOrUpdate(SkuMapping skuMapping);
	
}
