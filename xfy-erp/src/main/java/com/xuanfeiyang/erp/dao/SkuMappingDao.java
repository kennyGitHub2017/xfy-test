package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.SkuMapping;

public interface SkuMappingDao {
	
	public int insert(SkuMapping skuMapping);
	
	public int update(SkuMapping skuMapping);
	
	public int delete(String platformSku);
	
	public SkuMapping load(String platformSku);
	
	public List<SkuMapping> find();
	
	public Page<SkuMapping> findPage(PageRequest pageRequest, 
			@Param("param")SkuMapping skuMapping);
	
}
