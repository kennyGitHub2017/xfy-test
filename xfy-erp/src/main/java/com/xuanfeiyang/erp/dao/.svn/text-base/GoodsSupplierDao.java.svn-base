package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.common.mybatis.pageable.Page;
import com.xuanfeiyang.common.mybatis.pageable.PageRequest;
import com.xuanfeiyang.erp.domain.GoodsSupplier;

public interface GoodsSupplierDao {
	int delete(Integer id);

	int save(GoodsSupplier supplier);

	GoodsSupplier load(Integer id);
	
	List<GoodsSupplier> find();

	int update(GoodsSupplier supplier);

	Page<GoodsSupplier> findPage(PageRequest pageRequest, 
			@Param("status") Integer status, 
			@Param("keywords") String keywords);
	
	GoodsSupplier findByCode(@Param("code")String code);

}