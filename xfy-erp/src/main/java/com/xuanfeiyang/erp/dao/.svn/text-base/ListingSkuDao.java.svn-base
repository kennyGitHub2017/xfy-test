package com.xuanfeiyang.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanfeiyang.erp.domain.ListingSku;


public interface ListingSkuDao {

	ListingSku get(Integer id);
	
	List<ListingSku> getListByplatFormType(@Param("platformType")String platformType,@Param("sku")String sku);

    int update(@Param("param")ListingSku record);
}