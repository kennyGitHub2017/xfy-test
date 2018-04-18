package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.erp.domain.GoodsDesc;

public interface GoodsDescDao {
	int deleteBySku(String goodsSku);

	int insert(GoodsDesc record);

	List<GoodsDesc> findBySku(String goodsSku);
	
	GoodsDesc loadBySkuAndLanguage(@Param("goodsSku") String goodsSku, 
			@Param("language") String language);

	int update(GoodsDesc record);
}